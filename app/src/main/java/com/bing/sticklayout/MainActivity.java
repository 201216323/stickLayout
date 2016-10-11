package com.bing.sticklayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity {


    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = ((FrameLayout) findViewById(R.id.frameLayout));
        changeFragment(MainFragmet.class.getName());
    }


    private static String nowFragmentName;
    private void changeFragment(String name) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        Fragment nowFragment = fragmentManager
                .findFragmentByTag(nowFragmentName);
        if (nowFragment != null) {
            fragmentTransaction.hide(nowFragment);
        }
        Fragment fragment = fragmentManager.findFragmentByTag(name);
        if (fragment != null && fragment.isAdded()) {
            fragmentTransaction.show(fragment);
            fragment.onResume();
        } else {
            try {
                fragment = (Fragment) Class.forName(name).newInstance();
                fragmentTransaction.add(R.id.frameLayout, fragment, name);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
        nowFragmentName = name;
    }
}
