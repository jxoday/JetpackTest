package com.jinxin.paging.pagekeyeddatasource.paging;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.jinxin.paging.pagekeyeddatasource.model.User;

/**
 * @author JinXin 2020/10/11
 */
public class UserViewModel extends ViewModel {

    private static final String TAG = "UserViewModel";

    public LiveData<PagedList<User>> userPagedList;

    public UserViewModel() {

        Log.d(TAG, "UserViewModel: paging初始化");
        PagedList.Config config = new PagedList.Config.Builder()
                // 用于设置控件占位
                .setEnablePlaceholders(false)
                // 设置每页大小
                .setPageSize(UserDataSource.PER_PAGE)
                // 设置当距离底部还有多少条数据时开始加载下一页数据
                .setPrefetchDistance(1)
                // 设置首次加载数据的数量。该值要求时 PageSize的整倍数。默认是 PageSize的3倍
                .setInitialLoadSizeHint(UserDataSource.PER_PAGE)
                // 设置 PagedList 所能承受的最大数量，一般来说是 PageSize的许多倍，超过该值可能会出现异常
                .setMaxSize(100 * UserDataSource.PER_PAGE)
                .build();

        userPagedList = (new LivePagedListBuilder<>(new UserDataSourceFactory(), config)).build();
    }
}
