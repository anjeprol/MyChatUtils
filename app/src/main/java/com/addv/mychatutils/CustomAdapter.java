package com.addv.mychatutils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> names;
    private ArrayList<String> msgs;
    private ArrayList<String> hrs;
    private ArrayList<Integer> profiles;

    public CustomAdapter(Context context, int layout, ArrayList<String> names,  ArrayList<String> msgs,  ArrayList<String> hrs){
        this.context = context;
        this.layout = layout;
        this.names = names;
        this.msgs = msgs;
        this.hrs = hrs;

        profiles = new ArrayList<Integer>();
        profiles.add(R.drawable.andrea);
        profiles.add(R.drawable.sergio);
        profiles.add(R.drawable.valeria);
        profiles.add(R.drawable.raul);
        profiles.add(R.drawable.sandra);
        profiles.add(R.drawable.bety);
        profiles.add(R.drawable.fede);
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

        ImageView avatarIV = (ImageView) v.findViewById(R.id.avatar);
        avatarIV.setImageResource(profiles.get(position));
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView msg = (TextView) v.findViewById(R.id.msg);
        TextView hr = (TextView) v.findViewById(R.id.hr);
        name.setText(names.get(position));
        msg.setText(msgs.get(position));
        hr.setText(hrs.get(position));
        return v;
    }
}
