package com.example.customlistviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

        ListView listView = findViewById(R.id.listView);

        EmailAdapter adapter = new EmailAdapter(this, R.layout.email_item, emails);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", "Clicked on "+position);
            }
        });
    }
}
