package com.jinxin.paging.positionaldatasource.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jinxin.paging.R;
import com.jinxin.paging.positionaldatasource.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * @author JinXin 2020/10/9
 */
public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {

    private static final String TAG = "MovieAdapter";

    public MovieAdapter() {
        super(diffCallback);
    }

    private static DiffUtil.ItemCallback<Movie> diffCallback = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = getItem(position);

        if (movie == null) {
            holder.ivMovie.setImageResource(R.drawable.treasure_8k);
            holder.tvTitle.setText("");
            holder.tvYear.setText("");
        } else {
            Picasso.get()
                    .load(movie.images.large)
                    .placeholder(R.drawable.invitation_8k)
                    .error(R.drawable.treasure_8k)
                    .into(holder.ivMovie);

            holder.tvTitle.setText(movie.title);
            holder.tvYear.setText("上映年份：" + movie.year);

        }
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMovie;
        TextView tvTitle;
        TextView tvYear;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            ivMovie = itemView.findViewById(R.id.iv_movie);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvYear = itemView.findViewById(R.id.tv_year);
        }
    }
}
