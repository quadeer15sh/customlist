package com.example.quadeer.customlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MyCustomListAdapter extends ArrayAdapter<Items> {

    Context mCtx;
    int resource;
    List<Items> itemsList;

    public MyCustomListAdapter(MainActivity mCtx, int resource, List<Items> itemsList){
        super(mCtx, resource, itemsList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.my_list_item,null);

        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewPrice = view.findViewById(R.id.textViewPrice);
        TextView textViewUnits = view.findViewById(R.id.textViewUnit);

        Items item = itemsList.get(position);

        textViewName.setText(item.getProduct());
        textViewPrice.setText(item.getPrice());
        textViewUnits.setText(item.getQuantity());

        return view;
    }
}
