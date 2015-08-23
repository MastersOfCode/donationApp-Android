package com.mastersofcode;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
//import com.digits.sdk.android.Digits;
//import com.twitter.sdk.android.core.TwitterAuthConfig;
//import com.twitter.sdk.android.core.TwitterCore;
//import io.fabric.sdk.android.Fabric;

import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
/**
 * Created by T on 2015/08/22.
 */
public class LoginActivity extends Activity
{

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "XPoq5y490spjwXVTmTyhwePew";
    private static final String TWITTER_SECRET = "SM2ybwt7K6fBHdYqk98hrX7uOY8lEPCYHHQCuMnhwx6CHFl1vD";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits());
        //TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        //Fabric.with(this, new TwitterCore(authConfig), new Digits());
        setContentView(R.layout.activity_login);
        DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
        digitsButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // Do something with the session and phone number
            }

            @Override
            public void failure(DigitsException exception) {
                // Do something on failure
            }
        });
        findViews();
        buttonHandler();
    }

    void findViews()
    {

    }

    void buttonHandler()
    {

    }

}
