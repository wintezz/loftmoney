package com.alexpetrov.loftmoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<Item> items = Collections.emptyList();
    private int positionTabs;

    public void setItems(List<Item> items, int currentPosition) {
        this.items = items;
        positionTabs = currentPosition;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.marcup, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        Item item = items.get(position);
        itemViewHolder.bind(item, positionTabs);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView price;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }

        public void bind(Item item, int currentPosition) {
            name.setText(item.getName());
            price.setText(item.getPrice());

        }
    }
}

