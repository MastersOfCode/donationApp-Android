package com.mastersofcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by T on 2015/08/22.
 */
public class CustomAdapterProduct extends ArrayAdapter<Product>
{
    private LayoutInflater layoutInflater;

    public CustomAdapterProduct(Context context, int textViewResourceID, List<Product> objects)
    {
        super(context, textViewResourceID, objects);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Product product = (Product)getItem(position);

        if (null == convertView)
        {
            convertView = layoutInflater.inflate(R.layout.product_list_row, null);
        }

        TextView txvName = (TextView)convertView.findViewById(R.id.txv_product_name);
        txvName.setText(product.getName());

        TextView txvPrice = (TextView)convertView.findViewById(R.id.txv_product_price);
        txvPrice.setText(product.getPrice());

        TextView txvQuantity = (TextView)convertView.findViewById(R.id.txv_product_quantity);
        txvQuantity.setText(product.getQuantity());

        return convertView;
    }
}
