package com.zaske.about_steve.aboutsteve.code_samples;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zaske.about_steve.aboutsteve.R;


public class PassDataActivity extends AppCompatActivity {
    static String validator = "steve";
    Button mPassDataButton;
    private TextView mSentTitleView;
    int validationAttempts = -1;
    String[] validationResponses = new String[] {
            "Please enter text about Steve",
            "Let me be clear, the text has to include the word 'Steve'",
            "Now you're just being stubborn"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms);

        //My instructor required me to implement the OnClickListener interface,
        // which is why this code is not using ButterKnife
        mPassDataButton = (Button) findViewById(R.id.passDataButton);
        mPassDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                    Intent jumpToFormsSample = new Intent(PassDataActivity.this, CatchDataActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    EditText titleEdit = (EditText) findViewById(R.id.titleInput);
                    String title = titleEdit.getText().toString();
                    jumpToFormsSample.putExtra("title", title);
                    startActivity(jumpToFormsSample);
                } else {
                    Snackbar.make(v, validationResponses[validationAttempts], Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    public boolean isValid(){
        boolean results = false;
        TextView title = (TextView) findViewById(R.id.titleInput);
        if(title.getText().toString().toLowerCase().contains(validator)){
            validationAttempts = -1;
            results = true;
        } else {
            if(validationAttempts<validationResponses.length - 1){
                validationAttempts++;
            }
        }
        return results;
    }
}
