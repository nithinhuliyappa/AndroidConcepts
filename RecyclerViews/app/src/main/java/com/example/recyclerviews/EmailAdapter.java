package com.example.recyclerviews;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.ViewHolder> {

    ArrayList<Email> mData;

    public EmailAdapter(ArrayList<Email> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.email_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Email email = mData.get(i);
        viewHolder.textViewSubject.setText(email.subject);
        viewHolder.textViewSender.setText(email.sender);
        viewHolder.textViewSummary.setText(email.summary);
        viewHolder.email = email;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewSubject, textViewSender, textViewSummary;
        Email email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewSubject = itemView.findViewById(R.id.subject);
            textViewSender = itemView.findViewById(R.id.sender);
            textViewSummary = itemView.findViewById(R.id.summary);

            itemView.findViewById(R.id.doButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("demo","Do button of " + email.subject);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("demo", "Clicked on "+email.subject);
                }
            });
        }
    }


}
