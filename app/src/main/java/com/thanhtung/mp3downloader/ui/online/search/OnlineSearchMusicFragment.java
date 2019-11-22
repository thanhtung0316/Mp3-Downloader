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
import java.util.List;

public class OnlineSearchMusicFragment extends Fragment implements SearchView.OnQueryTextListener, SongAdapter.ItemClickListener {
    private FragmentOnlineSearchMusicBinding binding;
    private OnlineSearchMusicViewModel musicViewModel;
    private String TAG = "OnlineSearchMusicFragment";
    private static final String baseLink = "https://chiasenhac.vn/tim-kiem?q=";
    private SongAdapter adapter;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_online_search_music,
                container, false);
        context = container.getContext();
        init();
        return binding.getRoot();
    }

    private void init() {
        musicViewModel = ViewModelProviders.of(this).get(OnlineSearchMusicViewModel.class);
        adapter = new SongAdapter(context);
        adapter.setListener(this);
        binding.searchView.setOnQueryTextListener(this);
        musicViewModel.getSongs().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                if (songs.size()!=0){
                    adapter.setData(songs);
                    binding.rvSong.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        musicViewModel.setLinkToRequest(baseLink + query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onItemSongClicked(Song song) {

    }
}