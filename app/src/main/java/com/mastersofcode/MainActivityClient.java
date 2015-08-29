package com.mastersofcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T on 2015/08/22.
 */
public class MainActivityClient extends Activity {

    Button btnClient;
    Button btnMerchant;
    TextView txvAccountNum;
    TextView txvUserName;
    TextView txvTotal;
    TextView txvSpendable;
    ListView lsvProducts;
    List<Product> products;
    CustomAdapterProduct customAdapterProduct;


    /*** for debug ***/
    TextView debugTextView;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);

        debugTextView = (TextView)findViewById(R.id.txv_debug);
        debugTextView.setText("Debug: You are a client");

        products = new ArrayList<Product>();

        findViews();
        buttonHandler();

        updateAccountInfo();
        //readData();

    }

    void findViews()
    {
        btnClient = (Button)findViewById(R.id.btn_client);
        btnMerchant = (Button)findViewById(R.id.btn_merchant);
        txvAccountNum = (TextView)findViewById(R.id.txv_account_num);
        txvUserName = (TextView)findViewById(R.id.txv_user_name);
        txvTotal = (TextView)findViewById(R.id.txv_total);
        txvSpendable = (TextView)findViewById(R.id.txv_spendable);
        lsvProducts = (ListView)findViewById(R.id.lsv_products);
    }

    void buttonHandler()
    {
        btnMerchant.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityClient.this, MainActivityMerchant.class);
                startActivity(intent);
            }
        });

        btnClient.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //do nothing
            }
        });
    }

    void updateAccountInfo()
    {
        txvAccountNum.setText(": 1234-1234-1234-1234");
        txvUserName.setText(": Tak Sato");
        txvTotal.setText(": $1,234,000");
        txvSpendable.setText(": $300,000");
    }

    void readData()
    {
        Product product = new Product("name1", "price1", "quantity1");

        products.add(product);
        customAdapterProduct = new CustomAdapterProduct(this, 0, products);
        lsvProducts.setAdapter(customAdapterProduct);
    }

}
