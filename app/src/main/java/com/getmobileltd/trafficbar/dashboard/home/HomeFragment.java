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
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.SampleContent;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.dashboard.home.drinks.DrinksAdapter;
import com.getmobileltd.trafficbar.dashboard.home.drinks.DrinksModel;
import com.getmobileltd.trafficbar.dashboard.home.drinks.DrinksOnClickListener;
import com.getmobileltd.trafficbar.dashboard.home.food.FoodAdapter;
import com.getmobileltd.trafficbar.dashboard.home.food.FoodModel;
import com.getmobileltd.trafficbar.dashboard.home.food.FoodOnClickListener;
import com.getmobileltd.trafficbar.dashboard.home.trend.TrendAdapter;
import com.getmobileltd.trafficbar.dashboard.home.trend.TrendModel;
import com.getmobileltd.trafficbar.dashboard.home.trend.TrendOnClickListener;
import com.getmobileltd.trafficbar.orderfood.menulist.ListAvailableFoodActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, TrendOnClickListener, FoodOnClickListener, DrinksOnClickListener {
    private RecyclerView recyclerViewTrend, recyclerViewFood, recyclerViewDrink;
    private List<TrendModel> modelList = SampleContent.MYTRENDS;
    private List<FoodModel> modelListFood = SampleContent.MYFOOD;
    private List<DrinksModel> modelListDrinks = SampleContent.MYDRINKS;
    private TrendAdapter adapterTrend;
    private FoodAdapter adapterFood;
    private DrinksAdapter adapterDrink;
    private View v;
    private CardView cardViewFood;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_home, container, false);
        UiSettings.colorStatusbar(getActivity(),R.color.deep_ash);

        init(v);

        return v;
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

        }


    }

    private void init(View v) {
        cardViewFood = v.findViewById(R.id.cardview_order_for_food);
        cardViewFood.setOnClickListener(this);
        recyclerViewTrend = v.findViewById(R.id.recycler_view_trending);
        recyclerViewFood = v.findViewById(R.id.recycler_view_food);
        recyclerViewDrink = v.findViewById(R.id.recycler_view_drinks);
        adapterTrend = new TrendAdapter(this, modelList);
        adapterFood = new FoodAdapter(this, modelListFood);
        adapterDrink = new DrinksAdapter(this, modelListDrinks);
        recyclerViewTrend.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewTrend.setAdapter(adapterTrend);
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewFood.setAdapter(adapterFood);
        recyclerViewDrink.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewDrink.setAdapter(adapterDrink);

    }

    @Override
    public void onClick(TrendModel model) {
        Toast.makeText(getActivity(), "You clicked " + model.getPrice(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(FoodModel model) {
        Toast.makeText(getActivity(), "You clicked " + model.getPrice(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(DrinksModel model) {
        Toast.makeText(getActivity(), "You clicked" + model.getPrice(), Toast.LENGTH_SHORT).show();
    }
}
