package com.mastersofcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivityMerchant extends Activity {

    Button btnClient, btnMerchant;


    /*** for debug ***/
    TextView debugTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_merchant);

        debugTextView = (TextView)findViewById(R.id.txv_debug);
        debugTextView.setText("Debug: You are a merchant");

        findViews();
        buttonHandler();
    }

    void findViews()
    {
        btnClient = (Button)findViewById(R.id.btn_client);
        btnMerchant = (Button)findViewById(R.id.btn_merchant);
    }

    void buttonHandler()
    {
        btnMerchant.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //do nothing
            }
        });

        btnClient.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //go to client page
                Intent intent = new Intent(MainActivityMerchant.this, MainActivityClient.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
