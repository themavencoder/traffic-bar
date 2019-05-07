/*
 * *
 * Creator: Tobiloba Adejumo on 3/6/19 3:44 PM Last modified: 3/6/19 3:44 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.orderfood.menudetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.SampleContent;
import com.getmobileltd.trafficbar.application.TrafficBarApplication;
import com.getmobileltd.trafficbar.application.TrafficBarService;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.AddToCartActivity;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.adapter.AddToCartAdapter;
import com.getmobileltd.trafficbar.orderfood.menudetails.adapter.MenuDetailsAdapter;
import com.getmobileltd.trafficbar.orderfood.menudetails.listener.MenuDetailsOnClickListener;
import com.getmobileltd.trafficbar.orderfood.menudetails.model.MenuDetailsInformation;
import com.getmobileltd.trafficbar.orderfood.menudetails.model.MenuDetailsModel;
import com.getmobileltd.trafficbar.orderfood.menudetails.model.Menus;
import com.getmobileltd.trafficbar.orderfood.menulist.model.Information;

import java.util.ArrayList;
import java.util.List;

import static com.getmobileltd.trafficbar.orderfood.menulist.ListAvailableFoodActivity.EXTRA_MENU_DETAILS_ID;

public class MenuDetailsActivity extends AppCompatActivity  {
    public static final String MENU_DETAILS_KEY = "menu_details_key";
        private RecyclerView recyclerView;
        private List<Menus> modelList = new ArrayList<>();
        private MenuDetailsAdapter adapter;
        private Call<MenuDetailsModel> menuDetailsCall;
        private TrafficBarService trafficBarService;
    private MenuDetailsOnClickListener mlistener;
    private int id;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        trafficBarService = TrafficBarApplication.get(this).getTrafficBarService();
        setSupportActionBar(toolbar);
        setTitle("Traffic Breakfast");
        Intent intent = getIntent();
        id = intent.getIntExtra(EXTRA_MENU_DETAILS_ID, -1);
        clickMenuDetails();
        frameLayout = findViewById(R.id.progress_view);
        frameLayout.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.recycler_view_menu_details);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenuDetailsAdapter(modelList);
        adapter.setmenuClickListener(mlistener);
        recyclerView.setAdapter(adapter);

        getMenuDetails();



    }

    private void clickMenuDetails() {
        mlistener = new MenuDetailsOnClickListener() {
            @Override
            public void onClick(Menus model) {
                Intent intent = new Intent(MenuDetailsActivity.this,AddToCartActivity.class);
                intent.putExtra(MENU_DETAILS_KEY,model);
                startActivity(intent);

            }
        };
    }

    private void getMenuDetails() {
                menuDetailsCall = trafficBarService.getMenuCategoryDetails(id);
        menuDetailsCall.enqueue(new Callback<MenuDetailsModel>() {
            @Override
            public void onResponse(Call<MenuDetailsModel> call, Response<MenuDetailsModel> response) {
                assert response.body() != null;
                if (response.body().getCode() == 200) {
                    frameLayout.setVisibility(View.GONE);
                    MenuDetailsInformation information = response.body().getData();
                   List<Menus> menus =  information.getMenus();
                   for (Menus menu : menus) {
                       modelList.add(new Menus(menu.getMenu_name(),menu.getMenu_price(),menu.getMenu_image_sm(),menu.getMenu_image_bg(),menu.getMenu_description(),menu.getId(),menu.getMenu_extra()));
                      // Toast.makeText(MenuDetailsActivity.this, "" + menu.getMenu_image_sm(), Toast.LENGTH_SHORT).show();
                       Toast.makeText(MenuDetailsActivity.this, "" + menu.getMenu_extra(), Toast.LENGTH_SHORT).show();
                   }

                    adapter = new MenuDetailsAdapter(modelList);
                    adapter.setmenuClickListener(mlistener);
                    recyclerView.setAdapter(adapter);

                } else {
                    frameLayout.setVisibility(View.GONE);
                    Toast.makeText(MenuDetailsActivity.this, "A problem occurred, please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MenuDetailsModel> call, Throwable t) {
                frameLayout.setVisibility(View.GONE);
                Toast.makeText(MenuDetailsActivity.this, "Cannot connect to network", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
