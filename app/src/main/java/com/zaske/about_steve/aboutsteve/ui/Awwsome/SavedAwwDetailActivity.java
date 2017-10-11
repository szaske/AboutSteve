package com.zaske.about_steve.aboutsteve.ui.Awwsome;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.zaske.about_steve.aboutsteve.Constants;
import com.zaske.about_steve.aboutsteve.R;
import com.zaske.about_steve.aboutsteve.models.Aww;

import cn.jzvd.JZVideoPlayerStandard;

public class SavedAwwDetailActivity extends AppCompatActivity {

    private PhotoView mAwwPhotoView;
    private JZVideoPlayerStandard jzVideoPlayerStandard;
    private Aww mAww;
    private static final String AWW_KEY = "AWW";
    private DatabaseReference mAwwFireBaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAwwFireBaseReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_AWW);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_aww_detail);

        mAww = (Aww) getIntent().getSerializableExtra(AWW_KEY);


        // This section determines if the image is
        //
        //

        if (mAww.getUrl().endsWith(".mp4")){
            //hide ImageView
            mAwwPhotoView = findViewById(R.id.awwPhotoView);
            mAwwPhotoView.setVisibility(View.GONE);

            JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.awwVideoView);
            jzVideoPlayerStandard.setUp(mAww.getUrl()
                    , JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,mAww.getTitle());
            jzVideoPlayerStandard.startVideo();

        } else {
            // Hide video view
            jzVideoPlayerStandard = findViewById(R.id.awwVideoView);
            jzVideoPlayerStandard.setVisibility(View.GONE);

            // Show image
            final PhotoView mAwwPhotoView = findViewById(R.id.awwPhotoView);
            // mAwwPhotoView.setImageResource(R.drawable.image);

            Picasso.with(this)
                    .load(mAww.getUrl())
                    .into(mAwwPhotoView);
        }
      }
}
