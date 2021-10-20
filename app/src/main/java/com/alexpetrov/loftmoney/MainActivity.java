package com.alexpetrov.loftmoney;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView itemsView;

    ItemsAdapter itemsAdapter = new ItemsAdapter();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureRecycleView();

    }

       private void configureRecycleView() {
        itemsView = findViewById(R.id.list_item);
        itemsView.setAdapter(itemsAdapter);
        itemsView.setLayoutManager(new LinearLayoutManager(this));





    }




}



