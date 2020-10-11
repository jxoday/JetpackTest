package com.jinxin.paging.positionaldatasource.paging;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.jinxin.paging.positionaldatasource.model.Movie;

/**
 * 在 MovieViewModel 中通过 LivePagedListBuilder创建和配置 PagedList，
 * 并使用 LiveData 包装 PagedList，将其暴露给 MainActivity
 * @author JinXin 2020/10/9
 */
public class MovieViewModel extends ViewModel {

    private static final String TAG = "MovieViewModel";

    public LiveData<PagedList<Movie>> moviePagedList;

    public MovieViewModel() {
        Log.d(TAG, "MovieViewModel: paging初始化");
        PagedList.Config config = new PagedList.Config.Builder()
                // 用于设置控件占位
                .setEnablePlaceholders(false)
                // 设置每页大小
                .setPageSize(MovieDataSource.PER_PAGE)
                // 设置当距离底部还有多少条数据时开始加载下一页数据
                .setPrefetchDistance(1)
                // 设置首次加载数据的数量。该值要求时 PageSize的整倍数。默认是 PageSize的3倍
                .setInitialLoadSizeHint(MovieDataSource.PER_PAGE)
                // 设置 PagedList 所能承受的最大数量，一般来说是 PageSize的许多倍，超过该值可能会出现异常
                .setMaxSize(100 * MovieDataSource.PER_PAGE)
                .build();

        moviePagedList = (new LivePagedListBuilder<>(new MovieDataSourceFactory(), config)).build();
    }
}
