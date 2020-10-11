package com.jinxin.paging.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.jinxin.paging.model.Movie;

/**
 * MovieDataSourceFactory 负责创建 MovieDataSource，并使用 LiveData 包装 MovieDataSource，将其暴露 MovieViewModel
 * @author JinXin 2020/10/9
 */
public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {

        MovieDataSource dataSource = new MovieDataSource();
        return dataSource;
    }
}
