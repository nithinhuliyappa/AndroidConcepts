package com.example.recyclerviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Email> emails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emails.add(new Email("Email 1", "Summary 1", "Sender 1"));
        emails.add(new Email("Email 2", "Summary 2", "Sender 2"));
        emails.add(new Email("Email 3", "Summary 3", "Sender 3"));
        emails.add(new Email("Email 4", "Summary 4", "Sender 4"));
        emails.add(new Email("Email 5", "Summary 5", "Sender 5"));
        emails.add(new Email("Email 6", "Summary 6", "Sender 6"));

        recyclerView = findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new EmailAdapter(emails);
        recyclerView.setAdapter(mAdapter);
    }
}
