package com.summit.summitproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.summit.summitproject.prebuilt.model.Transaction;
import com.summit.summitproject.prebuilt.model.TransactionAdapter;

import java.util.List;

public class SummaryActivity extends AppCompatActivity {
    private TextView title;
    private TextView subtitle;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);
        recyclerView = findViewById(R.id.transaction_list);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String cardNum = intent.getStringExtra("cardNum");
        List<Transaction> transactions = (List<Transaction>) intent.getSerializableExtra("transactions");

        title.setText("Hello Shefali");
        subtitle.setText("Your recent transactions for Card x" + cardNum + ":");

        TransactionAdapter adapter = new TransactionAdapter(transactions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}