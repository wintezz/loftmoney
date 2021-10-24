package com.alexpetrov.loftmoney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class FragmentA extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }


    private class MainPagerAdapter extends FragmentStateAdapter {

        private final String[] titles;
        private final String[] types = {"expense",
                "income"};

        public MainPagerAdapter(FragmentActivity fragmentActivity) {
            super((fragmentActivity));
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 2) {
                return new BalanceFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}