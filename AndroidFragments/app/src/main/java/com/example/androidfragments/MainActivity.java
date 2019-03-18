package com.example.androidfragments;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyFragment.OnFragmentTextChange {
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("demo", "MainActivity-> OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("demo", "MainActivity-> OnResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("demo", "MainActivity-> OnDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("demo", "MainActivity-> OnCreate: Before Inflating ");
        setContentView(R.layout.activity_main);
        Log.d("demo", "MainActivity-> OnCreate: After Inflating ");

        final MyFragment myFragment = (MyFragment) getSupportFragmentManager().findFragmentById(R.id.myFragment);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.redRadioButton){
                    myFragment.changeColor(Color.RED);
                }else if(checkedId == R.id.blueRadioButton){
                    myFragment.changeColor(Color.BLUE);
                }else{
                    myFragment.changeColor(Color.GREEN);
                }
            }
        });
    }

    @Override
    public void onTextChanged(String text) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(text);
    }
}
