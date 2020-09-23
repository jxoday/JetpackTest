package com.jinxin.jetpacktest.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jinxin.jetpacktest.R;

import java.util.List;

/**
 * @author JinXin 2020/9/23
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Book> books;

    public RecyclerViewAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerViewBindingBinding itemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_recycler_view_binding, parent, false);
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = books.get(position);
        holder.itemBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemRecyclerViewBindingBinding itemBinding;

        public MyViewHolder(@NonNull ItemRecyclerViewBindingBinding itemView) {
            // getRoot()返回的是布局文件的最外层UI视图
            super(itemView.getRoot());
            itemBinding = itemView;
        }
    }
}
