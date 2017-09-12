package com.zaske.about_steve.aboutsteve.code_samples;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.zaske.about_steve.aboutsteve.R;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BoggleActivity extends AppCompatActivity {
    @BindView(R.id.boggleTitle)
    TextView mTitle;
    GridView gridView;
    ArrayList<String> roll = new ArrayList<String>();
    private String[][] die = new String[][] {
            {"R","I","F","O","B","X"},
            {"I","F","E","H","E","Y"},
            {"D","E","N","O","W","S"},
            {"U","T","O","K","N","D"},
            {"H","M","S","R","A","O"},
            {"L","U","P","E","T","S"},
            {"A","C","I","T","O","A"},
            {"Y","L","G","K","U","E"},
            {"Qu","B","M","J","O","A"},
            {"E","H","I","S","P","N"},
            {"V","E","T","I","G","N"},
            {"B","A","L","I","Y","T"},
            {"E","Z","A","V","N","D"},
            {"R","A","L","E","S","C"},
            {"U","W","I","L","R","G"},
            {"P","A","C","E","M","D"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boggle);

        // Binding to our layout objects
        ButterKnife.bind(this);

        //Setting fonts
        mTitle.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Lobster_1.3.otf"));

        for(int i=0; i<=15; i++){

            roll.add(die[i][rollDie()]);
        }

        gridView = (GridView) findViewById(R.id.baseGridView);
        gridView.setAdapter(new BoggleAdapter(this, roll));
    }

    //Create a Random roll
    public int rollDie() {
        int min = 0;
        int max  = 5;
        Random rand = new Random();

        int rollResults = rand.nextInt((max - min) + 1) + min;

        return rollResults;
    }
}


