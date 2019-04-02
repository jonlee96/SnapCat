package com.example.snapcat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NameAdapter extends BaseAdapter {

    ArrayList<String> nicknames;
    ArrayList<String> ids;
    LayoutInflater mInflator;

    public NameAdapter(Context context, ArrayList<String> names, ArrayList<String> idCodes){
        nicknames=names;
        ids = idCodes;
        mInflator= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return nicknames.size();
    }

    @Override
    public Object getItem(int position) {
        return nicknames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflator.inflate(R.layout.friend_listview_detail,null);
        TextView nameTextView = v.findViewById(R.id.nicknameTextView);
        TextView idTextView = v.findViewById(R.id.idTextView);

        String name = nicknames.get(position);
        String idCode = ids.get(position);

        nameTextView.setText(name);
        idTextView.setText(idCode);

        return v;
    }
}