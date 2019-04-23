/*
 * *
 * Creator: Tobiloba Adejumo on 2/22/19 4:19 PM Last modified: 2/22/19 4:17 PM Copyright: All rights reserved Ⓒ 2019
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
  * Unless required by applicable law or agreed to in writing, software distributed under the License is
   *distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. S
   * ee the License for the specific language governing permissions and limitations under the License.
 * /
 */

package com.getmobileltd.trafficbar.intro;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.intro.adapter.IntroAdapter;
import com.getmobileltd.trafficbar.registration.register.SignUpActivity;

/**
 * Created by themavencoder
 */
public class IntroActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager mViewPager;
    private IntroAdapter mIntroAdapter;
    private int[] layouts = {R.layout.first_intro_screen, R.layout.second_intro_screen, R.layout.third_intro_screen, R.layout.fourth_intro_screen};
    private LinearLayout mDotsLayout;
    private TextView mTvNext, mTvSkip;
    private Button buttonGetStarted;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        UiSettings.colorStatusbar(this,R.color.app_background);
        init();
        createDots(0);
        pageListener();



    }

    private void activateGetStarted() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fourth_intro_screen,null);
        buttonGetStarted = view.findViewById(R.id.button_get_started);
        buttonGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
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
        mTvNext = findViewById(R.id.textview_next);
        mTvSkip = findViewById(R.id.textview_skip);
        mTvSkip.setOnClickListener(this);
        mTvNext.setOnClickListener(this);
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
            if (current_position == 3) {
                mTvNext.setVisibility(View.GONE);
                mTvSkip.setVisibility(View.GONE);

            } else {
                mTvSkip.setVisibility(View.VISIBLE);
                mTvNext.setVisibility(View.VISIBLE);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textview_skip) {
            startActivity(new Intent(this, SignUpActivity.class));

        }
        if (v.getId() == R.id.textview_next) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        }
    }
}
