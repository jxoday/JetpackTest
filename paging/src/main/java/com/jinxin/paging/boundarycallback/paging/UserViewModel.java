package com.jinxin.paging.boundarycallback.paging;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.jinxin.paging.boundarycallback.db.UserDatabase;
import com.jinxin.paging.boundarycallback.model.User;

/**
 * @author JinXin 2020/10/11
 */
public class UserViewModel extends AndroidViewModel {

    private static final String TAG = "UserViewModel";

    public static final int PER_PAGE = 8;
    public LiveData<PagedList<User>> userPagedList;

    public UserViewModel(@NonNull Application application) {
        super(application);

        // Room 组件对 Paging组件提供了原生支持，因此 LivePagedListBuilder在创建PagedList时，可以直接将 Room作为数据源。
        // 接着，再通过 setBoundaryCallback()方法，将 PagedList 与 BoundaryCallback关联起来
        UserDatabase dataSource = UserDatabase.getDatabase(application);
        userPagedList = (new LivePagedListBuilder<>(dataSource.userDao().getUserList(), PER_PAGE))
                .setBoundaryCallback(new UserBoundaryCallback(application))
                .build();
    }

    /**
     * 刷新数据
     * 当用户执行下拉刷新时，调用 refresh()方法，开启一个工作线程，清空数据库
     */
    public void refresh() {

        AsyncTask.execute(() -> {
            UserDatabase.getDatabase(getApplication())
                    .userDao()
                    .clear();
        });
    }
}