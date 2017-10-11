package com.zaske.about_steve.aboutsteve.ui.Awwsome;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.zaske.about_steve.aboutsteve.Constants;
import com.zaske.about_steve.aboutsteve.R;
import com.zaske.about_steve.aboutsteve.models.Aww;

import com.google.gson.Gson;

import cn.jzvd.JZVideoPlayerStandard;

public class AwwDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private PhotoView mAwwPhotoView;
    private JZVideoPlayerStandard jzVideoPlayerStandard;
    private Button mAwwSavedButton;
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
        setContentView(R.layout.activity_aww_detail);

        mAwwSavedButton = (Button) findViewById(R.id.awwSaveButton);
        mAww = (Aww) getIntent().getSerializableExtra(AWW_KEY); //inflate the passed Awwsome sauce

        if (mAww.getUrl().endsWith(".mp4")){

            JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.awwVideoView);
            jzVideoPlayerStandard.setUp(mAww.getUrl()
                    , JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,mAww.getTitle());
            jzVideoPlayerStandard.startVideo();

            mAwwPhotoView = (PhotoView) findViewById(R.id.awwPhotoView);
            mAwwPhotoView.setVisibility(View.GONE);

        } else {

            // Hide video view
            jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.awwVideoView);
            jzVideoPlayerStandard.setVisibility(View.GONE);

            // Show image
            mAwwPhotoView = (PhotoView) findViewById(R.id.awwPhotoView);
            Picasso.with(this)
                    .load(mAww.getUrl())
                    .into(mAwwPhotoView);
        }

        mAwwSavedButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == mAwwSavedButton) {

            //Get the Firebase user
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference mAwwFireBaseReference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_AWW)
                    .child(uid);

            DatabaseReference pushRef = mAwwFireBaseReference.push(); // This makes a GUID
            String pushId = pushRef.getKey(); //this gets our unique GUID
            mAww.setPushId(pushId); // this sets our ID
            pushRef.setValue(mAww); // This saves the Aww

            Toast.makeText(v.getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveAwwToFirebase(Aww aww) {
        mAwwFireBaseReference.push().setValue(aww);
    }

}
