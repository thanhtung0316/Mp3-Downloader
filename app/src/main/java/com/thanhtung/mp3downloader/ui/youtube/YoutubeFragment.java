package com.thanhtung.mp3downloader.ui.youtube;

import android.os.Bundle;
import android.util.Log;
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
import com.thanhtung.mp3downloader.databinding.FragmentYoutubeBinding;
import com.thanhtung.mp3downloader.model.youtubemodel.Item;

import java.util.List;

public class YoutubeFragment extends Fragment implements SearchView.OnQueryTextListener {
    private FragmentYoutubeBinding binding;
    private YoutubeViewModel viewModel;
    private BaseAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_youtube, container, false);
        adapter = new BaseAdapter(getContext(), R.layout.item_video);
        binding.searchView.setOnQueryTextListener(this);
        viewModel = ViewModelProviders.of(requireActivity()).get(YoutubeViewModel.class);

        viewModel.getData().observe(requireActivity(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                adapter.setData(items);
                binding.rvVideo.setAdapter(adapter);
            }
        });
        return binding.getRoot();
    }

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


