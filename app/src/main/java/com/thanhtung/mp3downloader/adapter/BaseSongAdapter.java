package com.thanhtung.mp3downloader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;
import com.thanhtung.mp3downloader.model.BaseModel;
import java.util.List;

public class BaseSongAdapter<T extends BaseModel> extends RecyclerView.Adapter<BaseSongAdapter.BaseHolder> {
    private List<T> data;
    private LayoutInflater inflater;
    private int layoutId;
    private BaseItemlistener itemlistener;

    public void setItemlistener(BaseItemlistener itemlistener) {
        this.itemlistener = itemlistener;
    }

    public BaseSongAdapter(Context context, int layoutId) {
        inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        return new BaseHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull BaseSongAdapter.BaseHolder baseHolder, int i) {
        T item = data.get(i);
        baseHolder.binding.setVariable(BR.item, item);
        baseHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class BaseHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;

        public BaseHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public interface BaseItemlistener{}
}
