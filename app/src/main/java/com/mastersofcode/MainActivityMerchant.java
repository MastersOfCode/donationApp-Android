package com.mastersofcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivityMerchant extends Activity {

    Button btnClient, btnMerchant;
    ListView lsvProducts;
    EditText edtProductName;
    EditText edtProductPrice;
    Button btnRegister;
    List<Product> products;
    CustomAdapterProduct customAdapterProduct;

    /**
     * for debug **
     */
    TextView debugTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_merchant);

        debugTextView = (TextView) findViewById(R.id.txv_debug);
        debugTextView.setText("Debug: You are a merchant");

        products = new ArrayList<Product>();

        findViews();
        buttonHandler();

        readData();
    }

    void findViews() {
        btnClient = (Button) findViewById(R.id.btn_client);
        btnMerchant = (Button) findViewById(R.id.btn_merchant);
        lsvProducts = (ListView) findViewById(R.id.lsv_products);
        edtProductName = (EditText) findViewById(R.id.edt_product_name);
        edtProductPrice = (EditText) findViewById(R.id.edt_product_price);
        btnRegister = (Button) findViewById(R.id.btn_register);
    }

    void buttonHandler() {
        btnMerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do nothing
            }
        });

        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to client page
                Intent intent = new Intent(MainActivityMerchant.this, MainActivityClient.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityMerchant.this, MainActivityMerchant.class);
                startActivity(intent);
            }
        });
    }

    void readData()
    {
        Product product = new Product();
        product.setName("name1");
        product.setPrice("price1");

        products.add(product);
        customAdapterProduct = new CustomAdapterProduct(this, 0, products);
        lsvProducts.setAdapter(customAdapterProduct);
    }

    void saveData()
    {

    }
}