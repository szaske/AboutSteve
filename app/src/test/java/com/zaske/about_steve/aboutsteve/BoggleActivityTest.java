package com.zaske.about_steve.aboutsteve;

import android.os.Build;
import android.widget.TextView;

import com.zaske.about_steve.aboutsteve.code_samples.BoggleActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;


@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class BoggleActivityTest {
    private BoggleActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(BoggleActivity.class);
    }

    @Test
    public void validateTitleContent() {
        TextView boggleTitle = (TextView) activity.findViewById(R.id.boggleTitle);
        assertTrue("Play Boggle".equals(boggleTitle.getText().toString()));
    }

    @Test
    public void validateDiceRoll() {
        int random = activity.rollDie();
        int high = 5;
        int low = 0;
        assertThat(random, allOf(greaterThanOrEqualTo(low), lessThanOrEqualTo(high)));
    }

}
