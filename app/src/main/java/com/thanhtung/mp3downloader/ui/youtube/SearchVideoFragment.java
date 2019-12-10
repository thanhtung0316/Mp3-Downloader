package com.thanhtung.mp3downloader.ui.youtube;

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
import com.thanhtung.mp3downloader.adapter.BaseAdapter;
import com.thanhtung.mp3downloader.databinding.FragmentSearchVideoBinding;
import com.thanhtung.mp3downloader.model.youtubemodel.Item;
import java.util.List;

public class SearchVideoFragment extends Fragment implements SearchView.OnQueryTextListener {
    private FragmentSearchVideoBinding binding;
    private SearchVideoViewModel viewModel;
    private BaseAdapter adapter;
    private YoutubeFragment fmYoutube;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_video, container, false);
        adapter = new BaseAdapter(getContext(), R.layout.item_video);
        fmYoutube = (YoutubeFragment) getParentFragment();
        adapter.setItemlistener(listener);
        binding.searchView.setOnQueryTextListener(this);
        viewModel = ViewModelProviders.of(requireActivity()).get(SearchVideoViewModel.class);
        viewModel.getData().observe(requireActivity(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                adapter.setData(items);
                binding.rvVideo.setAdapter(adapter);
            }
        });
        return binding.getRoot();
    }
    private ItemVideoListener listener = new ItemVideoListener() {
        @Override
        public void onVideoClicked(Item video) {
            fmYoutube.showFragment(fmYoutube.getFmPlayVideo(),android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            if (fmYoutube.getFmPlayVideo().isHidden()){
                fmYoutube.getFmPlayVideo().setVideo(video);
            }
        }
    };


    @Override
    public boolean onQueryTextSubmit(String query) {
        viewModel.setKeySearch(query);
        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}


