package com.example.cartoon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.cartoon.R;
import com.example.cartoon.pager.Pager_jiaren;
import com.example.cartoon.pager.Pager_laoshi;
import com.example.cartoon.pager.Pager_tongshi;
import com.example.cartoon.pager.Pager_tongxue;

/**
 * Created by 石头 on 2017/12/7.
 */

public class Radio_xiaoxi extends Fragment {
    private View view;
    private PagerSlidingTabStrip xiaoxi_PagerSliding;
    private ViewPager xiaoxi_Viewpager;
    Fragment fragment = null;

    String[] arr = {"我", "在", "路", "上"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radio_xiaoxi, container, false);
      //  fragment = new Pager_jiaren();
        initView(view);

//        xiaoxi_Viewpager.setAdapter(new MyAdapter(getActivity().getSupportFragmentManager()));
        xiaoxi_Viewpager.setAdapter(new MyAdapter(getChildFragmentManager()));
        xiaoxi_PagerSliding.setViewPager(xiaoxi_Viewpager);
        xiaoxi_Viewpager.setCurrentItem(0);
        return view;
    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return arr[position];
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {

                case 0:
                    fragment = new Pager_jiaren();
                    break;

                case 1:

                    fragment = new Pager_laoshi();
                    break;

                case 2:

                    fragment = new Pager_tongshi();
                    break;
                case 3:

                    fragment = new Pager_tongxue();
                    break;
            }

            return fragment;
        }
        @Override
        public int getCount() {
            return arr.length;
        }

    }
    //获得控件
    private void initView(View view) {
        xiaoxi_PagerSliding = (PagerSlidingTabStrip) view.findViewById(R.id.xiaoxi_pager_Sliding);
        xiaoxi_Viewpager = (ViewPager) view.findViewById(R.id.xiaoxi_viewpager);
    }
}
