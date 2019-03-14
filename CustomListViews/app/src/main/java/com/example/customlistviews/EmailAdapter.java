package com.example.customlistviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class EmailAdapter extends ArrayAdapter<Email> {
    public EmailAdapter(@NonNull Context context, int resource, @NonNull List<Email> objects) {
        super(context, resource, objects);
    }

    //@androidx.annotation.NonNull
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Email email = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.email_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textViewSubject = convertView.findViewById(R.id.subject);
            viewHolder.textViewSender = convertView.findViewById(R.id.sender);
            viewHolder.textViewSummary = convertView.findViewById(R.id.summary);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewSubject.setText(email.subject);
        viewHolder.textViewSender.setText(email.sender);
        viewHolder.textViewSummary.setText(email.summary);

        return convertView;
    }

    public static class ViewHolder{
        TextView textViewSubject;
        TextView textViewSender;
        TextView textViewSummary;
    }
}
