package com.jinxin.mvvm.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jinxin.mvvm.MvvmApplication;
import com.jinxin.mvvm.db.UserDao;
import com.jinxin.mvvm.db.UserDatabase;
import com.jinxin.mvvm.model.User;
import com.jinxin.mvvm.repository.UserRepository;

/**
 * @author JinXin 2020/10/18
 */
public class UserViewModel extends AndroidViewModel {

    private static final String TAG = "UserViewModel";

    private LiveData<User> liveUser;
    private UserRepository userRepository;
    private String userName = "MichaelYe";

    public UserViewModel(@NonNull Application application) {
        super(application);
        UserDatabase userDatabase = MvvmApplication.getUserDatabase();
        UserDao userDao = userDatabase.userDao();
        userRepository = new UserRepository(userDao, MvvmApplication.getApi());
        liveUser = userRepository.getUser(userName);
    }

    public LiveData<User> getUser() {
        return liveUser;
    }

    public void refresh() {
        userRepository.refresh(userName);
    }
}
