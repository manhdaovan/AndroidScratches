package com.manhdaovan.block08;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void enterSecondActivity(View v) {
        float rating = fetchRating();

        Intent goToSecond = new Intent();
        goToSecond.putExtra(Constants.INTENT_RATING_KEY, rating);
        goToSecond.setClass(this, SecondActivity.class);

        startActivity(goToSecond);
    }

    public void sendToSms(View v) {
        float rating = fetchRating();
        Uri destination = Uri.parse("smsto:5556");
        Intent sendToSms = new Intent(Intent.ACTION_SENDTO, destination);
        sendToSms.putExtra(Constants.INTENT_SMS_KEY, "You rated: " + rating);
        if (sendToSms.resolveActivity(getPackageManager()) != null) {
            startActivity(sendToSms);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Cannot send via sms: No sms app found in you phone",
                    Toast.LENGTH_LONG).show();
        }
    }

    private float fetchRating() {
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        return ratingBar.getRating();
    }
}
