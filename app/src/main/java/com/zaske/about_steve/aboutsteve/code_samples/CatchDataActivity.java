package com.zaske.about_steve.aboutsteve.code_samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zaske.about_steve.aboutsteve.R;

public class CatchDataActivity extends AppCompatActivity {
    private TextView mPassedTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_data);

        mPassedTitleTextView = (TextView) findViewById(R.id.passedTitle);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        mPassedTitleTextView.setText(title);

    }
}
