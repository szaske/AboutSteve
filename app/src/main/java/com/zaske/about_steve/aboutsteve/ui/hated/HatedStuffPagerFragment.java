package com.zaske.about_steve.aboutsteve.ui.hated;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.zaske.about_steve.aboutsteve.R;
import com.zaske.about_steve.aboutsteve.models.Stuff;
import org.parceler.Parcels;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HatedStuffPagerFragment extends Fragment {
    @BindView(R.id.NameTextView)
    TextView mNameTextView;
    @BindView(R.id.hatedImageView)
    ImageView mHatedImageView;
    @BindView(R.id.levelTextView)
    TextView mLevelTextView;
    @BindView(R.id.causeTextView)
    TextView mCauseTextView;
    @BindView(R.id.linkTextView)
    TextView mLinkTextView;

    private Stuff mHated;

    public static HatedStuffPagerFragment newInstance(Stuff hated) {
        HatedStuffPagerFragment hatedStuffPagerFragment = new HatedStuffPagerFragment();
        Bundle args = new Bundle();
        args.putParcelable("hated", Parcels.wrap(hated));
        hatedStuffPagerFragment.setArguments(args);
        return hatedStuffPagerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHated = Parcels.unwrap(getArguments().getParcelable("hated"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hated_stuff_pager, container, false);

        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mHated.getImgUrl())
                .fit()
                .centerInside()
                .into(mHatedImageView);

        mNameTextView.setText(mHated.getName());
        mCauseTextView.setText("Why? " + mHated.getCause());
        mLevelTextView.setText("Hatred level: " + Integer.toString(mHated.getLevel()) + "/10");

        final String tester = mHated.getLink();
        mLinkTextView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tester));
                startActivity(browserIntent);
            }
        });

        return view;
    }
}
