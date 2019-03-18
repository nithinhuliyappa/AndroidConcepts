package com.example.dynamicfragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends Fragment {


    public DynamicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dynamic, container, false);
    }

    public void changeColor(int color){
        getView().setBackgroundColor(color);
        //getActivity().findViewById(R.id.my1Fragment).setBackgroundColor(color);
    }

    public interface OnFragmentTextChange{
        void OnTextChange(String text);
    };

    OnFragmentTextChange mlistener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mlistener = (OnFragmentTextChange)context;
        } catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()+" Should Implement OnFragmentTextChange");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.clickMeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = getView().findViewById(R.id.editText);
                mlistener.OnTextChange(editText.getText().toString());
            }
        });
    }
}
