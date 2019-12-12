package com.thanhtung.mp3downloader.ui.online.search;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.thanhtung.mp3downloader.R;
import com.thanhtung.mp3downloader.adapter.SongAdapter;
import com.thanhtung.mp3downloader.databinding.FragmentOnlineSearchMusicBinding;
import com.thanhtung.mp3downloader.model.Song;
import com.thanhtung.mp3downloader.ui.online.OnlineMusicFragment;
import com.thanhtung.mp3downloader.ui.online.OnlineRankingViewModel;

import java.util.List;

public class OnlineSearchMusicFragment extends Fragment implements SearchView.OnQueryTextListener, SongAdapter.ItemClickListener, View.OnClickListener {
    private FragmentOnlineSearchMusicBinding binding;
    private OnlineSearchMusicViewModel musicSearchViewModel;
    private String TAG = "OnlineSearchMusicFragment";
    private static final String baseLink = "https://chiasenhac.vn/tim-kiem?q=";
    private SongAdapter adapter;
    private Context context;
    private OnlineMusicFragment fmOnline;
    private OnlineRankingViewModel viewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_online_search_music,
                container, false);
        context = container.getContext();
        init();
        return binding.getRoot();
    }

    private void init() {
        adapter = new SongAdapter(context);
        fmOnline = (OnlineMusicFragment) getParentFragment();
        adapter.setListener(this);
        binding.searchView.setOnQueryTextListener(this);
        binding.imvBack.setOnClickListener(this);
//        viewModel = ViewModelProviders.of(this).get(OnlineRankingViewModel.class);
//        viewModel.getSongs().observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
//            @Override
//            public void onChanged(List<Song> songs) {
//                Log.e("OnlineMusicFragment","SIZE: "+songs.size());
//                adapter.setData(songs);
//                binding.rvSong.setAdapter(adapter);
//            }
//        });


        musicSearchViewModel = ViewModelProviders.of(requireActivity()).get(OnlineSearchMusicViewModel.class);
        musicSearchViewModel.getSongs().observe(requireActivity(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                adapter.setData(songs);
                binding.rvSong.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        musicSearchViewModel.setLinkToRequest(baseLink + query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onItemSongClicked(Song song) {
        musicSearchViewModel.setItemSongSelected(song);
        fmOnline.showFragment(fmOnline.getFmDetail(), android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }


    @Override
    public void onClick(View v) {
        fmOnline.showFragment(fmOnline.getFmRanking(), android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}