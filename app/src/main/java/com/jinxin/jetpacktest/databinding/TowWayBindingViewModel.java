package com.jinxin.jetpacktest.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

/**
 * 用于存放于实现双向绑定相关的业务逻辑
 * 注：该类继承自BaseObservable，而非ViewModel。无论是单向绑定还在双向绑定，本质都是观察者模式。
 * BaseObservables是DataBinding库为了方便实现观察者模式而提供的类
 *
 * @author JinXin 2020/9/22
 */
public class TowWayBindingViewModel extends BaseObservable {

    private LoginModel loginModel;

    /**
     * 构造器中为字段userName设置了默认值
     */
    public TowWayBindingViewModel() {
        loginModel =  new LoginModel();
        loginModel.setUserName("Michael");
    }

    /**
     * 为userName写Getter方法
     * @Bindable 表示对这个字段进行双向绑定
     * @return userName
     */
    @Bindable
    public String getUserName() {
        return loginModel.getUserName();
    }

    /**
     * 为userName写Setter方法
     * Setter方法会在用户编辑EditText中的内容时，被自动调用，需要在该方法内对userName字段进行手动更新
     *
     * 注意，在对字段更新前，需要判断新值和旧值是否不同。因为在更新后，会调用notifyPropertyChanged()方法通知观察者，数据已经更新。
     * 观察者在收到通知后，会对Setter方法进行调用。因此，如果对值进行判断，那么则会引发循环调用的问题
     */
    public void setUserName(String userName) {
        //
        if (userName != null && userName.equals(loginModel.getUserName())) {
            loginModel.setUserName(userName);
            // notifyPropertyChanged()是BaseObservable类中的一个方法
            // 可以在此处理一些与业务相关的逻辑，例如保存userName字段
            notifyPropertyChanged(BR.userName);
        }
    }
}
