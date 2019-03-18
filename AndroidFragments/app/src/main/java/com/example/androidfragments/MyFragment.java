package com.example.androidfragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {


    public MyFragment() {
        // Required empty public constructor
    }

    public void changeColor(int color){
        getView().setBackgroundColor(color);
        //getActivity().findViewById(R.id.my1Fragment).setBackgroundColor(color);
    }

    OnFragmentTextChange mlistener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("demo", "MyFragment-> OnAttach");
        try{
            mlistener = (OnFragmentTextChange)context;

        } catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()+" Should Implement OnFragmentTextChange");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*getActivity().findViewById(R.id.clickMeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Button Pressed", Toast.LENGTH_SHORT).show();
            }
        });*/
        getActivity().findViewById(R.id.clickMeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) getActivity().findViewById(R.id.editText);
                mlistener.onTextChanged(editText.getText().toString());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("demo", "MyFragment-> OnCreateView");
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_my, container, false);
        view.findViewById(R.id.clickMeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Button Pressed", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("demo", "MyFragment-> OnCreate");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("demo", "MyFragment-> OnResume");
    }

    public interface OnFragmentTextChange{
        void onTextChanged(String text);
    }
}
