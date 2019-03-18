/*
 * *
 * Creator: Tobiloba Adejumo on 3/13/19 8:11 AM Last modified: 3/13/19 8:11 AM Copyright: All rights reserved Ⓒ 2019
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *    Unless required by applicable law or agreed to in writing, software distributed under
 *    the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 *    OF ANY KIND, either express or implied. See the License for the specific language governing
 *    permissions and limitations under the License.
 * /
 */

package com.getmobileltd.trafficbar.dashboard.mycart.addtocart;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.SampleContent;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.adapter.AddToCartAdapter;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.listener.AddCartExtraListener;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.model.AddToCartModel;

import java.util.List;

public class AddToCartActivity extends AppCompatActivity implements AddCartExtraListener {
    private boolean mAppBarExpanded;
    private Menu mCollapsedMenu;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private RecyclerView mRecyclerView;
    private AddToCartAdapter mAdapter;
    private List<AddToCartModel> modeList = SampleContent.MYEXTRAS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        CoordinatorLayout mCoordinatorLayout = findViewById(R.id.coordinatorLayout);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp));
       // toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mCollapsingToolbar =findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.black));

        AppBarLayout appBarLayout = findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //  Vertical offset == 0 indicates appBar is fully  expanded.
                if (Math.abs(verticalOffset) > 300) {
                    mCollapsingToolbar.setTitle("Details");

                } else {
                    mCollapsingToolbar.setTitle(" ");

                }
            }
        });
        init();

        gradientImage();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new AddToCartAdapter(this,modeList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void gradientImage() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.sample_food);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@NonNull Palette palette) {
                int mutedColor = palette.getMutedColor(R.attr.colorPrimary);
                mCollapsingToolbar.setContentScrimColor(getResources().getColor(R.color.white));
            }
        });
    }

    @Override
    public void onClick(AddToCartModel model) {

    }
}
