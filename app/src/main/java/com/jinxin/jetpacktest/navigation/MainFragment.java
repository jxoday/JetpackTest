package com.jinxin.jetpacktest.navigation;

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

import com.jinxin.jetpacktest.R;

/**
 * @author JinXin
 */
public class MainFragment extends Fragment {

    private static final String CHANNEL_ID = "1";
    private static final int notificationId = 8;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        initView(root);
        return root;
    }

    private void initView(View root) {

        // 跳转界面的两种方式
        // 方法一
        root.findViewById(R.id.btn_to_second_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fragment常见的传递参数方式
//                Bundle bundle = new Bundle();
//                bundle.putString("user_name", "JinXin");
//                bundle.putInt("age", 24);

                // 使用safe args传递参数
                Bundle bundle = new MainFragmentArgs.Builder()
                        .setAge(10)
                        .setUserName("jinxin")
                        .build().toBundle();

                Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_sceondFragment, bundle);
            }
        });

        // 方法二
//        root.findViewById(R.id.btn_to_second_fragment)
//                .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_sceondFragment));



        /* **************************************************************************************/

        // DeepLink，即深层链接
        // 通过DeepLink，可以利用PendingIntent或一个真实的URL链接，直接跳转应用程序中的某个页面（Activity/Fragment）

        // PendingIntent方式。
        // 当应用程序接受到某个通知推送，用户点击该通知时，能够直接跳转到展示该通知内容的页面
        root.findViewById(R.id.btn_pending_intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
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
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("DeepLinkDemo")
                .setContentText("Test")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
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
        bundle.putString("params", "pendingIntent");
        return Navigation
                .findNavController(getActivity(), R.id.btn_pending_intent)
                .createDeepLink()
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.deepLinkFragment)
                .setArguments(bundle)
                .createPendingIntent();
    }
}