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

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.getmobileltd.trafficbar.AppInstance;
import com.getmobileltd.trafficbar.application.TrafficBarApplication;
import com.getmobileltd.trafficbar.application.TrafficBarService;
import com.getmobileltd.trafficbar.dashboard.DashboardActivity;
import com.getmobileltd.trafficbar.dashboard.mycart.MyCartFragment;
import com.getmobileltd.trafficbar.orderfood.menudetails.model.Menus;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.SampleContent;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.adapter.AddToCartAdapter;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.listener.AddCartExtraListener;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.model.AddToCartModel;

import java.math.BigDecimal;
import java.util.List;

import static com.getmobileltd.trafficbar.orderfood.menudetails.MenuDetailsActivity.MENU_DETAILS_KEY;

public class AddToCartActivity extends AppCompatActivity implements AddCartExtraListener, View.OnClickListener {
    private boolean mAppBarExpanded;
    private Menu mCollapsedMenu;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private RecyclerView mRecyclerView;
    private AddToCartAdapter mAdapter;
    private List<AddToCartModel> modeList = SampleContent.MYEXTRAS;
    private Button mButtonAddToCart;
    public static final String EXTRA_CART = "com.getmobileltd.trafficbar.addtocart.AddToCartActivity";
    private Menus menus;
    private TextView mTvName, mTvPrice, mTvDescription, mTvQuantity;
    private ImageView mImageHeader, mImageMinus, mImagePlus;
    public static final String one = "1";
    private TrafficBarService trafficBarService;
    private Call<AddToCartResponse> cartResponse;
    private AppInstance appInstance;
    private FrameLayout frameLayout;

    public double initial_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        CoordinatorLayout mCoordinatorLayout = findViewById(R.id.coordinatorLayout);
        setSupportActionBar(toolbar);
        appInstance = AppInstance.getInstance();
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp));
        // toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mCollapsingToolbar = findViewById(R.id.collapsing_toolbar);
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
        trafficBarService = TrafficBarApplication.get(this).getTrafficBarService();
        init();
        gradientImage();
        menus = getIntent().getParcelableExtra(MENU_DETAILS_KEY);
        if (menus != null) {
            getData();
        }


    }

    private void getData() {


        mTvName.setText(menus.getMenu_name());
        mTvPrice.setText(menus.getMenu_price());
        initial_price = Double.parseDouble(mTvPrice.getText().toString());
        mTvDescription.setText(menus.getMenu_description());
        Glide.with(this)
                .load(menus.getMenu_image_bg())
                .apply(new RequestOptions()
                        .placeholder(R.color.colorAccent)
                        .error(R.color.black))
                .into(mImageHeader);
        mTvQuantity.setText(one);

    }

    private void init() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new AddToCartAdapter(this, modeList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        frameLayout = findViewById(R.id.progress_view);
        mButtonAddToCart = findViewById(R.id.button_add_to_cart);
        mButtonAddToCart.setOnClickListener(this);
        mTvName = findViewById(R.id.textview_name);
        mTvPrice = findViewById(R.id.textview_price);
        mTvDescription = findViewById(R.id.textview_description);
        mImageHeader = findViewById(R.id.imageview_header);
        mTvQuantity = findViewById(R.id.textview_quantity);
        mImagePlus = findViewById(R.id.imageview_plus);
        mImageMinus = findViewById(R.id.imageview_minus);
        mImageMinus.setOnClickListener(this);
        mImagePlus.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add_to_cart:
                addToCart();

                break;
            case R.id.imageview_plus:
                add(mTvQuantity.getText().toString());
                break;
            case R.id.imageview_minus:
                sub(mTvQuantity.getText().toString());
                break;
            default:

        }


    }

    private void addToCart() {
        String quantity = mTvQuantity.getText().toString();
        int quantityInt = Integer.parseInt(quantity);
        AddToCartModel model = new AddToCartModel(menus.getId(), quantityInt);
        cartResponse = trafficBarService.createCart(appInstance.getApi_key(),model);
        mButtonAddToCart.setBackgroundColor(getResources().getColor(R.color.deep_ash));
        mButtonAddToCart.setEnabled(false);
        mButtonAddToCart.setText(getString(R.string.addingToCart));
        mButtonAddToCart.setTextColor(getResources().getColor(R.color.black));
        frameLayout.setVisibility(View.VISIBLE);
        cartResponse.enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                assert response.body() != null;
                if (response.body().getStatus().equals("success") ) {
                    frameLayout.setVisibility(View.GONE);
                    mButtonAddToCart.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    mButtonAddToCart.setEnabled(true);
                    mButtonAddToCart.setText(getResources().getString(R.string.addTocart));
                    mButtonAddToCart.setTextColor(getResources().getColor(R.color.white));
                    Toast.makeText(AddToCartActivity.this, "Added to cart successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddToCartActivity.this, DashboardActivity.class);
                    intent.putExtra(EXTRA_CART, "default");
                    startActivity(intent);
                } else {
                    frameLayout.setVisibility(View.GONE);
                    mButtonAddToCart.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    mButtonAddToCart.setEnabled(true);
                    mButtonAddToCart.setText(getString(R.string.retry));
                    Toast.makeText(AddToCartActivity.this, "Error to add to cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddToCartResponse> call, Throwable t) {
                frameLayout.setVisibility(View.GONE);;
                mButtonAddToCart.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                mButtonAddToCart.setEnabled(true);
                mButtonAddToCart.setText(getString(R.string.retry));
                Toast.makeText(AddToCartActivity.this, "Unknown error occurred", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void add(String j) {
        int i = Integer.parseInt(j);
        int result = i + 1;

        double price = Double.parseDouble(menus.getMenu_price());
        BigDecimal priceDecimal = BigDecimal.valueOf(price);
        BigDecimal resultDecimal = BigDecimal.valueOf(result);
        BigDecimal resultPrice = priceDecimal.multiply(resultDecimal);
        mTvQuantity.setText(String.valueOf(result));
        mTvPrice.setText(String.valueOf(resultPrice));


    }

    private void sub(String j) {
        int i = Integer.parseInt(j);
        if (i == 1) {
            mTvQuantity.setText(one);
            Double price = Double.parseDouble(menus.getMenu_price());
            mTvPrice.setText(String.valueOf(price));

        } else {
            int result = i - 1;
            double price = Double.parseDouble(menus.getMenu_price());
            BigDecimal priceDecimal = BigDecimal.valueOf(price);
            BigDecimal resultDecimal = BigDecimal.valueOf(result);
            BigDecimal resultPrice = priceDecimal.multiply(resultDecimal);
            mTvQuantity.setText(String.valueOf(result));
            mTvPrice.setText(String.valueOf(resultPrice));



        }


    }
}
