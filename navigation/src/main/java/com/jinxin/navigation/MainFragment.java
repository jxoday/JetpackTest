package com.jinxin.navigation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author JinXin
 */
public class MainFragment extends Fragment {

    private static final String CHANNEL_ID = "1";
    private static final int notificationId = 8;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // 跳转页面方法1
        view.findViewById(R.id.btn_to_second_fragment_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 常见的传递参数方式
//                Bundle bundle = new Bundle();
//                bundle.putString("user_name", "Michael");
//                bundle.putInt("age", 30);
//                Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_secondFragment, bundle);

                // 使用 safe args 传递参数 写法一
                Bundle bundle = new SecondFragmentArgs.Builder()
                        .setUserName("Michael")
                        .setAge(30)
                        .build().toBundle();
                Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_secondFragment, bundle);
            }
        });

        // 跳转页面方法二
        view.findViewById(R.id.btn_to_second_fragment_2)
                .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_secondFragment));

        view.findViewById(R.id.btn_send_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
        return view;
    }

    /**
     * 向通知栏发送一条通知，模拟用户收到一条推送的情况
     */
    private void sendNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importanceDefault = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "ChannleName", importanceDefault);
            channel.setDescription("description");
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("DeepLinkDemo")
                .setContentText("Test")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // 设置 PendingIntent
                .setContentIntent(getPendingIntent())
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify(notificationId, builder.build());
    }

    /**
     * 构建PendingIntent对象
     * 在其中其中设置，当通知被点击时需要跳转到的目的地（destination），以及传递的参数
     * @return PendingIntent
     */
    private PendingIntent getPendingIntent() {
        Bundle bundle = new Bundle();
        bundle.putString("userName", "Michael");
        bundle.putInt("age", 30);
        return Navigation
                .findNavController(requireActivity(), R.id.btn_send_notification)
                .createDeepLink()
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.secondFragment)
                .setArguments(bundle)
                .createPendingIntent();
    }
}