package com.alexpetrov.loftmoney.screens;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alexpetrov.loftmoney.LoftApp;
import com.alexpetrov.loftmoney.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddMoneyActivity extends AppCompatActivity {

    private EditText moneyNameView;
    private EditText moneyPriceView;
    private Button moneyAddView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        moneyNameView = findViewById(R.id.moneyNameView);
        moneyPriceView = findViewById(R.id.moneyPriceView);
        moneyAddView = findViewById(R.id.moneyAddView);

        configureButton();
    }

    private void configureButton() {
        moneyAddView.setOnClickListener(v -> {
            if (moneyNameView.getText().equals("") || moneyPriceView.getText().equals("")) {
                Toast.makeText(getApplicationContext(), getString(R.string.fill_fields), Toast.LENGTH_LONG).show();
                return;
            }

            Disposable disposable = ((LoftApp) getApplication()).moneyApi.loadItems(
                    Integer.parseInt(moneyPriceView.getText().toString()),
                    moneyNameView.getText().toString(),
                    "income"
            )

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                     Toast.makeText(getApplicationContext(),getString(R.string.succes_added), Toast.LENGTH_LONG).show();
                     finish();
                    }, throwable -> {
                        Toast.makeText(getApplicationContext(), throwable.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    });


        });
    }
}
