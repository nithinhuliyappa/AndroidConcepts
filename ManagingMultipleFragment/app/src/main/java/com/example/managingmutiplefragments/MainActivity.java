package com.example.managingmutiplefragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new FirstFragment(),"firstFragment")
                .commit();
    }

    @Override
    public void goToSecondFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SecondFragment(),"secondFragment")
                .addToBackStack(null)
                .commit();
    }


    //For Older Version we need to modify the back button behaviour to pop the previous fragment
/*    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }*/
}
