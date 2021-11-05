package com.alexpetrov.loftmoney.screens.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alexpetrov.loftmoney.R;
import com.alexpetrov.loftmoney.screens.balance.BalanceFragment;
import com.alexpetrov.loftmoney.screens.dashboard.adapter.FragmentAdapter;
import com.alexpetrov.loftmoney.screens.dashboard.adapter.FragmentItem;
import com.alexpetrov.loftmoney.screens.money.MoneyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<FragmentItem> fragments = new ArrayList<>();
        fragments.add(new FragmentItem(new MoneyFragment(), getString(R.string.title_expence)));
        fragments.add(new FragmentItem(new MoneyFragment(), getString(R.string.title_incomes)));
        fragments.add(new FragmentItem(new BalanceFragment(), getString(R.string.title_balance)));

        ViewPager containerView = view.findViewById(R.id.containerView);
        TabLayout tabContainerView = view.findViewById(R.id.tabContainerView);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragments, getChildFragmentManager(),0);
        containerView.setAdapter(fragmentAdapter);
        containerView.setOffscreenPageLimit(3);
        tabContainerView.setupWithViewPager(containerView);
    }
}
