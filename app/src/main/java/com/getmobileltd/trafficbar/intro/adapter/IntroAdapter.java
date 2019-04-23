/*
 * *
 * Creator: Tobiloba Adejumo on 2/22/19 4:19 PM Last modified: 2/22/19 4:17 PM Copyright: All rights reserved Ⓒ 2019
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 * /
 */

package com.getmobileltd.trafficbar.intro.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.registration.register.SignUpActivity;

/**
 * Created by themavencoder
 */
public class IntroAdapter extends PagerAdapter {
    private int[] layouts;
    private LayoutInflater inflater;
    private Context context;
    private Button buttonGetStarted;


    public IntroAdapter(int[] layouts, Context context) {
        this.layouts = layouts;
        this.context = context;
       inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final View view = inflater.inflate(layouts[position], container, false);
        container.addView(view);

        buttonGetStarted = view.findViewById(R.id.button_get_started);
        if (buttonGetStarted != null) {
            buttonGetStarted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.getContext().startActivity(new Intent(view.getContext(), SignUpActivity.class));
                }
            });

        }


        return view;

    }

   

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view  = (View) object;
            container.removeView(view);
    }
}
