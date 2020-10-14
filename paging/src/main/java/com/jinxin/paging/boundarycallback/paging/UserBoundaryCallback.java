package com.jinxin.paging.boundarycallback.paging;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.jinxin.paging.boundarycallback.api.RetrofitClient;
import com.jinxin.paging.boundarycallback.db.UserDao;
import com.jinxin.paging.boundarycallback.db.UserDatabase;
import com.jinxin.paging.boundarycallback.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author JinXin 2020/10/11
 */
public class UserBoundaryCallback extends PagedList.BoundaryCallback<User> {

    private static final String TAG = "UserBoundaryCallback";

    private Application application;

    public UserBoundaryCallback(Application application) {
        this.application = application;
    }

    /**
     * 当数据库为空时，回调该方法，在该方法内请求第一页的数据
     */
    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        getTopData();
    }

    @Override
    public void onItemAtFrontLoaded(@NonNull User itemAtFront) {
        super.onItemAtFrontLoaded(itemAtFront);
        // 该方法暂时用不到，什么都不做
    }

    /**
     * 当用户滑动到页面的最下方，并且数据库中的数据已全部加载完毕时，该方法会被回调，在该方法内请求下一页数据
     *
     * 注：该方法的参数返回的是数据库中最后一条数据，请求下一页所需的 key就在该数据中，在本例中为User对象的 id字段
     * @param itemAtEnd user
     */
    @Override
    public void onItemAtEndLoaded(@NonNull User itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        getTopAfterData(itemAtEnd);
    }

    /**
     * 加载第一页数据
     */
    private void getTopData() {

        int since = 0;
        RetrofitClient.getInstance()
                .getGithubApi()
                .getUsers(since, UserViewModel.PER_PAGE)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                        List<User> userList = response.body();
                        boolean successful = response.isSuccessful();
                        if (!successful || userList == null) {
                            Log.d(TAG, "onResponse: 请求失败");
                            return;
                        }
                        insertUsers(userList);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                        Log.d(TAG, "onFailure: 请求失败");
                    }
                });
    }

    /**
     * 加载下一页数据
     */
    private void getTopAfterData(User user) {

        RetrofitClient.getInstance()
                .getGithubApi()
                .getUsers(user.id, UserViewModel.PER_PAGE)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                        List<User> userList = response.body();
                        boolean successful = response.isSuccessful();
                        if (!successful || userList == null) {
                            Log.d(TAG, "onResponse: 请求失败");
                            return;
                        }
                        insertUsers(userList);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                        Log.d(TAG, "onFailure: 请求失败");
                    }
                });
    }

    /**
     * 本地数据库插入数据
     * @param users 用户列表
     */
    private void insertUsers(final List<User> users) {
        // 在子线程中进行数据库操作
        AsyncTask.execute(() -> {
            UserDao userDao = UserDatabase.getDatabase(application).userDao();
            userDao.insertUsers(users);
        });
    }
}
