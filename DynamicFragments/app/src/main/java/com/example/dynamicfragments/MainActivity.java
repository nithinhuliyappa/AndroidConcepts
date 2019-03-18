package com.example.dynamicfragments;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements DynamicFragment.OnFragmentTextChange{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     getSupportFragmentManager().beginTransaction()
                .add(R.id.containerLayout, new DynamicFragment(),"tag_DynamicFragment")
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerLayout, new DynamicFragment(),"tag_DynamicFragment1")
                .commit();

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                DynamicFragment dynamicFragment = (DynamicFragment) getSupportFragmentManager().findFragmentByTag("tag_DynamicFragment");
                DynamicFragment dynamicFragment1 = (DynamicFragment) getSupportFragmentManager().findFragmentByTag("tag_DynamicFragment1");
                if (checkedId == R.id.redRadioButton){
                    dynamicFragment.changeColor(Color.RED);
                    dynamicFragment1.changeColor(Color.RED);
                }else if(checkedId == R.id.yellowRadioButton){
                    dynamicFragment.changeColor(Color.YELLOW);
                    dynamicFragment1.changeColor(Color.YELLOW);
                }else{
                    dynamicFragment.changeColor(Color.GREEN);
                    dynamicFragment1.changeColor(Color.GREEN);
                }
            }
        });
    }

    @Override
    public void OnTextChange(String text) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(text);
    }
}
