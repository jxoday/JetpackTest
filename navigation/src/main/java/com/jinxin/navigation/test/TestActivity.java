package com.jinxin.navigation.test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jinxin.navigation.R;

/**
 * @author JinXin
 */
public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // NavController用于页面的导航和切换
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_test);
        // AppBarConfiguration用于Appbar的配置
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        // 将Appbar和NavController绑定起来
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                Log.d(TAG, "onDestinationChanged: 切换事件");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // 实例化菜单
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: ");
        // 由于再导航图和菜单的布局文件中，已经为TwoTestFragment设置好了相同的id（即twoTestFragment）
        // 因此，在onOptionsItemSelected()方法中，通过NavigationUI便可以自动完成页面跳转
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.d(TAG, "onSupportNavigateUp: ");
        // 覆盖onSupportNavigationUp()方法，当在SettingsFragment中单击ActionBar左边的返回按钮时，
        // NavigationUI可以帮助settingsFragment回到MainFragment
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}