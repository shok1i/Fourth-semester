package com.example.hw_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objs;
    TextView footerText;

    CustomAdapter(Context context, ArrayList<Product> products, TextView _footer){
        ctx = context;
        objs = products;
        lInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        footerText = _footer;
    }

    @Override
    public int getCount() {
        return objs.size();
    }

    @Override
    public Object getItem(int position) {
        return objs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        Product p = getProduct(position);

        ((TextView)view.findViewById(R.id.tvID)).setText(p.id + "");
        ((TextView)view.findViewById(R.id.tvName)).setText(p.name);
        ((TextView)view.findViewById(R.id.tvPrice)).setText(p.price + " rub");
        ((ImageView)view.findViewById(R.id.ivImage)).setImageResource(p.image);

        ((ImageView)view.findViewById(R.id.ivImage)).getLayoutParams().width = 250;
        ((ImageView)view.findViewById(R.id.ivImage)).getLayoutParams().height = 250;

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
        cbBuy.setOnCheckedChangeListener(myCheckChangeList);
        cbBuy.setTag(position);
        cbBuy.setChecked(p.box);

        return view;
    }

    Product getProduct(int position){
        return  ((Product) getItem(position));
    }

    public ArrayList<Product> getBox(){
        ArrayList<Product> box = new ArrayList<Product>();
        for (Product p : objs){
            if (p.box)
                box.add(p);
        }
        return box;
    }

    CompoundButton.OnCheckedChangeListener myCheckChangeList = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            getProduct((Integer)buttonView.getTag()).box = isChecked;
            if (footerText != null) footerText.setText("Item selected: " + getBox().size());
        }
    };
}
