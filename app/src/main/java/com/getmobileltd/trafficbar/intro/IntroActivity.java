package com.getmobileltd.trafficbar.intro;

import android.app.ActionBar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.intro.adapter.IntroAdapter;

/**
 * Created by themavencoder
 */
public class IntroActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private IntroAdapter mIntroAdapter;
    private int[] layouts = {R.layout.first_intro_screen, R.layout.second_intro_screen, R.layout.third_intro_screen, R.layout.fourth_intro_screen};
    private LinearLayout mDotsLayout;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        fullScreen();
        init();
        createDots(0);
        pageListener();
    }

    private void fullScreen() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

    }

    private void init() {
        mViewPager = findViewById(R.id.viewpager_intro);
        mIntroAdapter = new IntroAdapter(layouts, this);
        mDotsLayout = findViewById(R.id.linearlayout_dots);
        mViewPager.setAdapter(mIntroAdapter);
    }

    private void createDots(int current_position) {
        if (mDotsLayout != null) {
            mDotsLayout.removeAllViews();
        }
        ImageView[] dots = new ImageView[layouts.length];
        for (int i = 0; i < layouts.length; i++) {
            dots[i] = new ImageView(this);
            if (i == current_position) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));

            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            mDotsLayout.addView(dots[i], params);
        }


    }

    private void pageListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                createDots(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
