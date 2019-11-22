package com.thanhtung.mp3downloader.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thanhtung.mp3downloader.databinding.ItemSongBinding;
import com.thanhtung.mp3downloader.model.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    private List<Song> data;
    private LayoutInflater inflater;
    private ItemClickListener listener;

    public SongAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Song> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSongBinding binding = ItemSongBinding.inflate(inflater,
                parent, false);
        return new SongHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, final int position) {
        holder.bindData(data.get(position));
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemSongClicked(data.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        private ItemSongBinding binding;

        public SongHolder(@NonNull ItemSongBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(Song item) {
            binding.setItem(item);
            Glide.with(binding.imvAvatar)
                    .load(item.getImageLink())
                    .into(binding.imvAvatar);
        }
    }
    public interface ItemClickListener {
        void onItemSongClicked(Song song);
    }
}