package com.alexpetrov.loftmoney;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.alexpetrov.loftmoney.screens.dashboard.DashBoardFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    TabLayout tabs;
    ViewPager2 pages;
    FrameLayout containerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = findViewById(R.id.tabs);
        pages = findViewById(R.id.pages);
        containerView = findViewById(R.id.containerView);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerView, new DashBoardFragment())
                .commitNow();

        //        connect pages and fragments
        pages.setAdapter(new MainPagerAdapter(this));
  //      pages.setOffscreenPageLimit(3);

//        connect tabs and pages
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabs, pages, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                tab.setText(getResources().getStringArray(R.array.main_pager_titles)[position]);
            }
        });
        tabLayoutMediator.attach();
    }

    private class MainPagerAdapter extends FragmentStateAdapter {

        public MainPagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public Fragment createFragment(int position) {
            if (position == 2){
       //         return new BalanceFragment();
                return BudgetFragment.newInstance(position);
            }
            else {
                return BudgetFragment.newInstance(position);
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }














}