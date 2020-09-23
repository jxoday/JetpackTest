package com.jinxin.jetpacktest.databinding;

import androidx.databinding.ObservableField;

/**
 * TowWayBindingViewModel类的双向绑定做法存在一些弊端，首先必须继承自BaseObservable，
 * 另外，在Getter方法前还需要加上@Bindable标签，以告诉编译器要绑定该字段，最后，在Setter
 * 方法中还需要收到调用 notifyPropertyChanged()方法以通知观察者
 *
 * 更简单的做法：
 * 使用ObservableField<T>，它能将普通对象包装成一个可观察对象。ObservableField可用于包装各种基本类型、
 * 集合数组类型和自定义类型的数据。
 *
 * @author JinXin 2020/9/23
 */
public class TowWaySimpleBindingViewModel {

    private ObservableField<LoginModel> loginModelObservableField;

    public TowWaySimpleBindingViewModel() {

        LoginModel loginModel = new LoginModel();
        loginModel.setUserName("Michael");

        // 使用ObservableField将LoginModel对象包装起来
        loginModelObservableField = new ObservableField<>();
        loginModelObservableField.set(loginModel);
    }

    public String getUserName() {
        return loginModelObservableField.get().getUserName();
    }

    public void setUserName(String userName) {
        loginModelObservableField.get().setUserName(userName);
    }
}
