package com.alexpetrov.loftmoney;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    TabLayout tabs;
    ViewPager2 pages;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = findViewById(R.id.tabs);
        pages = findViewById(R.id.pages);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> {
            final int activeFragmentIndex = pages.getCurrentItem();
            Fragment activeFragment = getSupportFragmentManager().getFragments().get(activeFragmentIndex);
            activeFragment.startActivity(new Intent(MainActivity.this, AddItemActivity.class));
        });

        pages.setAdapter(new MainPagerAdapter(this));
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
            if (position == 2) {
                //         return new BalanceFragment();
                return BudgetFragment.newInstance(position);
            } else {
                return BudgetFragment.newInstance(position);
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }




}