package com.zaske.about_steve.aboutsteve.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.zaske.about_steve.aboutsteve.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by steve on 9/8/2017.
 */

public class drawer_frag_resume extends Fragment {
    @BindView(R.id.pdfView) PDFView mPdfView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.drawer_resume_frag, container, false);

        // Binding to our layout objects
        ButterKnife.bind(this,view);

        mPdfView.fromAsset("files/resume_stz.pdf").load();

        return view;
    }

}
