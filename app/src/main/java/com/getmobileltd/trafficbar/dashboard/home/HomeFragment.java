/*
 * *
 * Creator: Tobiloba Adejumo on 2/25/19 11:51 AM Last modified: 2/25/19 11:51 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.home;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.SampleContent;
import com.getmobileltd.trafficbar.application.TrafficBarApplication;
import com.getmobileltd.trafficbar.application.TrafficBarService;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.dashboard.DashboardActivity;
import com.getmobileltd.trafficbar.dashboard.discover.DiscoverFragment;
import com.getmobileltd.trafficbar.dashboard.home.drinks.DrinkData;
import com.getmobileltd.trafficbar.dashboard.home.drinks.DrinkResponse;
import com.getmobileltd.trafficbar.dashboard.home.drinks.DrinksAdapter;
import com.getmobileltd.trafficbar.dashboard.home.drinks.DrinksModel;
import com.getmobileltd.trafficbar.dashboard.home.drinks.DrinksOnClickListener;
import com.getmobileltd.trafficbar.dashboard.home.food.FoodAdapter;
import com.getmobileltd.trafficbar.dashboard.home.food.FoodData;
import com.getmobileltd.trafficbar.dashboard.home.food.FoodModel;
import com.getmobileltd.trafficbar.dashboard.home.food.FoodOnClickListener;
import com.getmobileltd.trafficbar.dashboard.home.food.FoodResponse;
import com.getmobileltd.trafficbar.dashboard.home.trend.TrendAdapter;
import com.getmobileltd.trafficbar.dashboard.home.trend.TrendData;
import com.getmobileltd.trafficbar.dashboard.home.trend.TrendModel;
import com.getmobileltd.trafficbar.dashboard.home.trend.TrendOnClickListener;
import com.getmobileltd.trafficbar.dashboard.home.trend.TrendingResponse;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.AddToCartActivity;
import com.getmobileltd.trafficbar.database.OnRetrieveUserApi;
import com.getmobileltd.trafficbar.database.repository.UserRepository;
import com.getmobileltd.trafficbar.orderfood.menulist.ListAvailableFoodActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerViewTrend, recyclerViewFood, recyclerViewDrink;
    private List<TrendData> modelList = new ArrayList<>();
    private List<FoodData> modelListFood = new ArrayList<>();
    private List<DrinkData> modelListDrinks = new ArrayList<>();
    private TrendAdapter adapterTrend;
    private FoodAdapter adapterFood;
    private DrinksAdapter adapterDrink;
    private View v;
    private CardView cardViewFood, cardViewSomeone;
    private TrafficBarService trafficBarService;
    private Call<TrendingResponse> trendingResponseCall;
    private Call<DrinkResponse> drinkResponseCall;
    private Call<FoodResponse> foodResponseCall;
    private TrendOnClickListener trendOnClickListener;
    private FoodOnClickListener foodOnClickListener;
    private DrinksOnClickListener drinksOnClickListener;
    private FrameLayout framelayout;
    public static final String INTENT_POPULAR_TREMDS_KEY = "popular_trends_key";
    public static final String INTENT_POPULAR_FOOD_KEY = "popular_food_key";
    public static final String INTENT_POPULAR_DRINK_KEY = "popular_drink_key";
    private UserRepository repository;
    private String repoApiKey;
    private TextView mLocation, mChangeLocation;
    private TextView mTrendingCount, mFoodCount, mDrinksCount;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_home, container, false);
        UiSettings.colorStatusbar(getActivity(), R.color.deep_ash);
        repoApiKey = ((DashboardActivity) getActivity()).getApiKeyFromDatabase();
        trafficBarService = TrafficBarApplication.get(getActivity()).getTrafficBarService();
        init(v);


        trendClick();
        foodClick();
        drinkClick();
        getTrending();
        getFood();
        getDrinks();
        return v;
    }

    private void drinkClick() {
        drinksOnClickListener = new DrinksOnClickListener() {
            @Override
            public void onClick(DrinkData model) {
                Intent intent = new Intent(getActivity(), AddToCartActivity.class);
                intent.putExtra(INTENT_POPULAR_DRINK_KEY, model);
                startActivity(intent);

            }
        };
    }

    private void getDrinks() {
        framelayout.setVisibility(View.VISIBLE);
        drinkResponseCall = trafficBarService.getDrinks(repoApiKey);
        drinkResponseCall.enqueue(new Callback<DrinkResponse>() {
            @Override
            public void onResponse(Call<DrinkResponse> call, Response<DrinkResponse> response) {
                assert response.body() != null;
                if (response.body().getCode() == 200) {
                    mDrinksCount.setText(String.valueOf(response.body().getCount()));
                    modelListDrinks.clear();
                    framelayout.setVisibility(View.GONE);

                    List<DrinkData> data = response.body().getData();
                    for (DrinkData datas : data) {
                        modelListDrinks.add(new DrinkData(datas.getId(), datas.getName(), datas.getPrice(), datas.getImage(), datas.getHref(), datas.getMenu_category_name(), datas.getBig_image(), datas.getDescription()));
                    }
                    adapterDrink = new DrinksAdapter(modelListDrinks);
                    adapterDrink.setDrinkListener(drinksOnClickListener);
                    recyclerViewDrink.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    recyclerViewDrink.setAdapter(adapterDrink);

                } else {
                    framelayout.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Unable to get popular drinks. Try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DrinkResponse> call, Throwable t) {
                framelayout.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "unable to connect to network! Try again. ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void foodClick() {
        foodOnClickListener = new FoodOnClickListener() {
            @Override
            public void onClick(FoodData model) {
                Intent intent = new Intent(getActivity(), AddToCartActivity.class);
                intent.putExtra(INTENT_POPULAR_FOOD_KEY, model);
                startActivity(intent);

            }
        };
    }

    private void getFood() {
        framelayout.setVisibility(View.VISIBLE);
        foodResponseCall = trafficBarService.getFood(repoApiKey);
        foodResponseCall.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                assert response.body() != null;
                if (response.body().getCode() == 200) {
                    mFoodCount.setText(String.valueOf(response.body().getCount()));
                    modelListFood.clear();
                    framelayout.setVisibility(View.GONE);
                    List<FoodData> data = response.body().getData();
                    for (FoodData datas : data) {
                        modelListFood.add(new FoodData(datas.getId(), datas.getName(), datas.getPrice(), datas.getImage(), datas.getHref(), datas.getMenu_category_name(), datas.getBig_image(), datas.getDescription()));

                    }
                    adapterFood = new FoodAdapter(modelListFood);
                    adapterFood.setFoodListener(foodOnClickListener);
                    recyclerViewFood.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    recyclerViewFood.setAdapter(adapterFood);
                } else {
                    framelayout.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Error connecting. Try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                framelayout.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Unknown problem occurred", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void trendClick() {
        trendOnClickListener = new TrendOnClickListener() {
            @Override
            public void onClick(TrendData model) {
                Intent intent = new Intent(getActivity(), AddToCartActivity.class);
                intent.putExtra(INTENT_POPULAR_TREMDS_KEY, model);
                startActivity(intent);

            }
        };
    }

    private void getTrending() {
        framelayout.setVisibility(View.VISIBLE);
        trendingResponseCall = trafficBarService.getTrending(repoApiKey);
        trendingResponseCall.enqueue(new Callback<TrendingResponse>() {
            @Override
            public void onResponse(Call<TrendingResponse> call, Response<TrendingResponse> response) {
                assert response.body() != null;
                if (response.body().getCode() == 200) {
                    mTrendingCount.setText(String.valueOf(response.body().getCount()));
                    modelList.clear();
                    framelayout.setVisibility(View.GONE);
                    List<TrendData> data = response.body().getData();
                    for (TrendData datas : data) {
                        modelList.add(new TrendData(datas.getId(), datas.getName(), datas.getPrice(), datas.getImage(), datas.getHref(), datas.getMenu_category_name(), datas.getBig_image(), datas.getDescription()));
                    }

                    adapterTrend = new TrendAdapter(modelList);
                    adapterTrend.setTrendOnClickListener(trendOnClickListener);
                    recyclerViewTrend.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    recyclerViewTrend.setAdapter(adapterTrend);
                } else {
                    framelayout.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Error occurred. Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TrendingResponse> call, Throwable t) {
                framelayout.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Unknown error occurred! Try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cardview_order_for_food:
                startActivity(new Intent(getActivity(), ListAvailableFoodActivity.class));
                break;
            case R.id.cardview_order_for_someone:
                startActivity(new Intent(getActivity(), ListAvailableFoodActivity.class));
                break;

            case R.id.textview_change_location:
                DiscoverFragment discoverFragment = new DiscoverFragment();
                ((DashboardActivity) getActivity()).setFragment(discoverFragment);
                break;
        }


    }

    private void init(View v) {
        mTrendingCount = v.findViewById(R.id.textview_trending_count);
        mFoodCount = v.findViewById(R.id.textview_food_count);
        mDrinksCount = v.findViewById(R.id.textview_drinks_count);
        mChangeLocation = v.findViewById(R.id.textview_change_location);
        mChangeLocation.setOnClickListener(this);
        mLocation = v.findViewById(R.id.textview_location);
        String location = ((DashboardActivity) getActivity()).getTickedLocation();
        mLocation.setText(location);
        cardViewFood = v.findViewById(R.id.cardview_order_for_food);
        cardViewFood.setOnClickListener(this);
        cardViewSomeone = v.findViewById(R.id.cardview_order_for_someone);
        cardViewSomeone.setOnClickListener(this);
        recyclerViewTrend = v.findViewById(R.id.recycler_view_trending);
        recyclerViewFood = v.findViewById(R.id.recycler_view_food);
        recyclerViewDrink = v.findViewById(R.id.recycler_view_drinks);
        adapterTrend = new TrendAdapter(modelList);
        adapterTrend.setTrendOnClickListener(trendOnClickListener);
        adapterFood = new FoodAdapter(modelListFood);
        adapterFood.setFoodListener(foodOnClickListener);
        adapterDrink = new DrinksAdapter(modelListDrinks);
        adapterDrink.setDrinkListener(drinksOnClickListener);
        recyclerViewTrend.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewTrend.setAdapter(adapterTrend);
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewFood.setAdapter(adapterFood);
        recyclerViewDrink.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewDrink.setAdapter(adapterDrink);
        framelayout = v.findViewById(R.id.progress_view);


    }


}
