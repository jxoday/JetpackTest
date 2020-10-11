package com.jinxin.paging.itemkeyeddatasrource.paging;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.jinxin.paging.pagekeyeddatasource.model.User;
import com.jinxin.paging.pagekeyeddatasource.paging.UserDataSource;

/**
 * @author JinXin 2020/10/11
 */
public class UserDataSourceFactory extends DataSource.Factory<Integer, User> {

    @NonNull
    @Override
    public DataSource<Integer, User> create() {
        UserDataSource userDataSource = new UserDataSource();
        return userDataSource;
    }
}
