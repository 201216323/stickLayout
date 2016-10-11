package com.bing.sticklayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hosa2015.
 * Time  2016-10-10--17:22
 * Project_name stickLayout
 * Author BruceChang
 */

public class MainFragmet extends Fragment {

    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_fragmet,container,false);
        initView(view);
        setViewpagerAdapter();
        return view;
    }

    private void initView(View view) {

        tabLayout = ((TabLayout) view.findViewById(R.id.tabLayout));
        viewPager = (ViewPager) view.findViewById(R.id.id_stickynavlayout_viewpager);
    }

    private void setViewpagerAdapter() {
        BasePagerAdapter mAdapter = new BasePagerAdapter(getFragmentManager());
        ListViewFragment listViewFragment = ListViewFragment.newInstance();// 基本信息fragment
        ListViewFragment2 listViewFragment2 = new ListViewFragment2();// 基本信息fragment
        ScrollViewFragment scrollViewFragment = ScrollViewFragment.newInstance();


        mAdapter.addFragment(listViewFragment, "ListView");
        mAdapter.addFragment(scrollViewFragment, "ScrollView");
        mAdapter.addFragment(listViewFragment2, "ListView");


        viewPager.setAdapter(mAdapter);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }
}
