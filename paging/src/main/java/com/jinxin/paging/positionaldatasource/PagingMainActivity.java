package com.jinxin.paging.positionaldatasource;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jinxin.paging.R;
import com.jinxin.paging.positionaldatasource.model.Movie;
import com.jinxin.paging.positionaldatasource.paging.MovieAdapter;
import com.jinxin.paging.positionaldatasource.paging.MovieViewModel;

/**
 * @author JinXin
 */
public class PagingMainActivity extends AppCompatActivity {

    private static final String TAG = "PagingMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_main);
        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        RecyclerView recyclerMovie = findViewById(R.id.recycle_movie);
        recyclerMovie.setLayoutManager(new LinearLayoutManager(this));
        // 当知道Adapter内Item的改变不会影响RecyclerView宽高的时候，可以设置为true让RecyclerView避免重新计算大小。
        recyclerMovie.setHasFixedSize(true);
        final MovieAdapter movieAdapter = new MovieAdapter();
        recyclerMovie.setAdapter(movieAdapter);

        movieViewModel.moviePagedList.observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                movieAdapter.submitList(movies);
            }
        });

    }
}