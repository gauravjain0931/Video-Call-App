package com.example.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Dashboard_Activity extends AppCompatActivity {

    EditText secretCodeBox;
    Button joinbtn, sharebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_);

        secretCodeBox = findViewById(R.id.codebox);
        joinbtn = findViewById(R.id.joinbtn);
        sharebtn = findViewById(R.id.sharebtn);

        URL serverURL;

        try {
            {
                serverURL = new URL("https://meet.jit.si");
                JitsiMeetConferenceOptions defaultoptions =
                        new JitsiMeetConferenceOptions.Builder()
                                .setServerURL(serverURL)
                                .setWelcomePageEnabled(false)
                                .build();
                JitsiMeet.setDefaultConferenceOptions(defaultoptions);
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(secretCodeBox.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(Dashboard_Activity.this, options);
            }
        });
    }

    public void Share_Now(View view)
    {
        Intent shareintent = new Intent (Intent.ACTION_SEND);
        shareintent.setType("text/plan");
        String shareBody = "Download this Application Here:- https://play.google.com/store/apps/details?id=org.jitsi.meet&hl=en";
        String sharesub = "Video Meet App";
        shareintent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
        shareintent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(shareintent, "Share Using"));
    }
}