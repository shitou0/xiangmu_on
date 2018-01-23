package com.example.cartoon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cartoon.R;
import com.example.cartoon.table.Table_beijing;
import com.example.cartoon.table.Table_guangzhou;
import com.example.cartoon.table.Table_tuijian;
import com.example.cartoon.table.Table_hainan;
import com.example.cartoon.table.Table_redian;
import com.example.cartoon.table.Table_shanghai;
import com.example.cartoon.table.Table_shenzhen;
import com.example.cartoon.table.Table_shipin;
import com.example.cartoon.table.Table_guanzhu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 石头 on 2017/12/7.
 */

public class Radio_shouye extends Fragment {
    private View view;
    private TabLayout my_Table;
    private List<String> tas=new ArrayList<>();
    private ViewPager viewpager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radio_shouye, container, false);
        initView(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tas.add("送");
        tas.add("您");
        tas.add("一");
        tas.add("朵");
        tas.add("康");
//        tas.add("广州");
        tas.add("乃");
        tas.add("馨");
        tas.add("✿");
        viewpager.setAdapter(new MyAdapter(getChildFragmentManager()));
        //建立关联
        my_Table.setupWithViewPager(viewpager);

        //一次加载所有的页面
        viewpager.setOffscreenPageLimit(tas.size());

    }
    class MyAdapter extends FragmentPagerAdapter {

        //得到页面的title,会添加到tabLayout控件上
        @Override
        public CharSequence getPageTitle(int position) {
            return tas.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f=null;
//            进行判断
            switch (position){
                case 0:
//                    f=new Table_redian();
                    f=new Table_tuijian();
                    break;
                case 1:
                    f=new Table_redian();
                    break;
                case 2:
                    f=new Table_shipin();
                    break;
                case 3:
                    f=new Table_guanzhu();
                    break;
                case 4:
                    f=new Table_beijing();
                    break;
//                case 5:
//                    f=new Table_guangzhou();
//                    break;
                case 5:
                    f=new Table_shanghai();
                    break;
                case 6:
                    f=new Table_shenzhen();
                    break;
                case 7:
                    f=new Table_hainan();
                    break;
            }
            return f;
        }

        //view的页数
        @Override
        public int getCount() {
            return tas.size();
        }

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
    }

    private void initView(View view) {
        my_Table = (TabLayout) view.findViewById(R.id.my_table);
        viewpager = (ViewPager) view.findViewById(R.id.vp);
    }
}
