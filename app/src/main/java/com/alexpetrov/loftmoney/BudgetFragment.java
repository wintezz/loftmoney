package com.alexpetrov.loftmoney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alexpetrov.loftmoney.cells.Item;
import com.alexpetrov.loftmoney.cells.ItemsAdapter;
import com.alexpetrov.loftmoney.remote.MoneyRemoteItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BudgetFragment extends Fragment {

    private static final String ARG_CURRENT_POSITION = "position";
    private int currentPosition;
    private ItemsAdapter itemsAdapter = new ItemsAdapter();
    private List<Item> items = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentPosition = getArguments().getInt(ARG_CURRENT_POSITION);


        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_budget, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItems();
            }
        });


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


    }

    @Override
    public void onResume() {
        super.onResume();

        generateData();
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    private void generateData() {
        items.add(new Item("Car", "5000"));
        items.add(new Item("Plane", "50000000"));
        itemsAdapter.setItems(items, currentPosition);

        Disposable disposable = ((LoftApp) getApplication()).moneyApi.getMoneyItems("income")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moneyResponce -> {
                    if (moneyResponce.getStatus().equals("success")) {
                        List<Item> items = new ArrayList<>();


                        for (MoneyRemoteItem moneyRemoteItem : moneyResponce.getMoneyItemList()) {
                            items.add(Item.getInstance(moneyRemoteItem));
                        }

                        itemsAdapter.setData(items);

                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.connection_lost), Toast.LENGTH_LONG).show();
                    }
                }, throwable -> {
                    Toast.makeText(getApplicationContext(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                });

        compositeDisposable.add(disposable);


    }

    public static BudgetFragment newInstance(int position) {
        BudgetFragment budgetFragment = new BudgetFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CURRENT_POSITION, position);
        budgetFragment.setArguments(args);
        return budgetFragment;
    }
}
