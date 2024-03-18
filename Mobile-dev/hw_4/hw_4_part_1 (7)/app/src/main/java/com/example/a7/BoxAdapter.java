package com.example.a7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;

    BoxAdapter(Context context, ArrayList<Product> products) {
        ctx = context; objects = products;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount () { return objects.size(); }

    @Override
    public Object getItem(int pos) { return objects.get(pos); }

    @Override
    public long getItemId(int pos) { return pos; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = lInflater.inflate(R.layout.item, parent, false);
        Product p = getProduct(position);

        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView) view.findViewById(R.id.tvPrice)).setText(p.price + "");
        ((ImageView) view.findViewById(R.id.tvImage)).setImageResource(p.image);

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
        cbBuy.setOnCheckedChangeListener(myCheckChangeList);
        cbBuy.setTag(position);
        cbBuy.setChecked(p.box);
        return view;
    }

    Product getProduct (int pos) {
        return (Product) getItem(pos);
    }

    ArrayList<Product> getBox () {
        ArrayList<Product> box = new ArrayList<Product>();
        for (Product p : objects)
            if (p.box) box.add(p);
        return box;
    }

    OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
            getProduct((Integer) buttonView.getTag()).box = isChecked;
        }
    };
}
