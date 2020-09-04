package com.addv.mychatutils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> names;
    private ArrayList<String> msgs;
    private ArrayList<String> hrs;

    public CustomAdapter(Context context, int layout, ArrayList<String> names,  ArrayList<String> msgs,  ArrayList<String> hrs){
        this.context = context;
        this.layout = layout;
        this.names = names;
        this.msgs = msgs;
        this.hrs = hrs;
    }

    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.list_item, null);

        TextView name = (TextView) v.findViewById(R.id.name);
        TextView msg = (TextView) v.findViewById(R.id.msg);
        TextView hr = (TextView) v.findViewById(R.id.hr);
        name.setText(names.get(position));
        msg.setText(msgs.get(position));
        hr.setText(hrs.get(position));
        return v;
    }
}
