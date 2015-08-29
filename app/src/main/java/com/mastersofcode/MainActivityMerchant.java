package com.mastersofcode;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.pubnub.api.*;
import org.json.*;


public class MainActivityMerchant extends Activity {

    public static final String URL_PRODUCT = "http://damp-taiga-7734.herokuapp.com/lists/show/?format=json";
    public static final String PRODUCT_NAME = "item_name";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_QUANTITY = "quantity";

    Button btnClient, btnMerchant;
    ListView lsvProducts;
    EditText edtProductName;
    EditText edtProductPrice;
    Button btnRegister;
    List<Product> products;
    CustomAdapterProduct customAdapterProduct;
    JSONParser jsonParser;
    Pubnub pubnub;
    /**
     * for debug **
     */
    TextView debugTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_merchant);

        debugTextView = (TextView) findViewById(R.id.txv_debug);
        debugTextView.setText("Debug: You are a merchant!");

        initialize();

        pubnub();

        findViews();
        buttonHandler();

        readData();
    }

    void initialize()
    {
        pubnub = new Pubnub("pub-c-bca08470-3499-4c9e-9be9-50fc721c45de", "sub-c-959c8362-499e-11e5-8287-0619f8945a4f");
        products = new ArrayList<Product>();
        jsonParser = new JSONParser();
    }

    void pubnub()
    {
        try {
            pubnub.subscribe("demo_tutorial", new Callback() {
                public void successCallback(String channel, Object message) {
                    //Log.d("MainActivityMerchant.java", message.toString());
                }

                public void errorCallback(String channel, PubnubError error) {
                    //Log.d("MainActivityMerchant.java", error.getErrorString());
                }
            });
        } catch (PubnubException e) {
            e.printStackTrace();
        }
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
                saveData();
                Intent intent = new Intent(MainActivityMerchant.this, MainActivityMerchant.class);
                startActivity(intent);
            }
        });
    }

    void readData()
    {
        new retrieveData().execute();
    }

    void saveData()
    {

    }

    class retrieveData extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... arg0)
        {
//            JSONObject jsonObject = jsonParser.makeHttpRequestJObj(MainActivityMerchant.URL_PRODUCT);

            JSONArray jsonArray = jsonParser.makeHttpRequestJArray(MainActivityMerchant.URL_PRODUCT);

            try
            {
                Log.d("Jsonarray length: " , String.valueOf(jsonArray.length()));

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jobj = jsonArray.getJSONObject(i);

                    Log.d("asdfasdf " + i, jobj.getString(MainActivityMerchant.PRODUCT_PRICE));

                    Product product = new Product(
                            jobj.getString(MainActivityMerchant.PRODUCT_NAME),
                            jobj.getString(MainActivityMerchant.PRODUCT_PRICE),
                            jobj.getString(MainActivityMerchant.PRODUCT_QUANTITY)
                    );
                    products.add(product);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            return "";
        }

        protected void onPostExecute(String str) {
            customAdapterProduct = new CustomAdapterProduct(getBaseContext(), 0, products);
            lsvProducts.setAdapter(customAdapterProduct);
        }
    }
}