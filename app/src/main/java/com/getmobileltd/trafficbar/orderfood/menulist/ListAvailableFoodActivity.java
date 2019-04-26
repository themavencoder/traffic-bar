/*
 * *
 * Creator: Tobiloba Adejumo on 3/6/19 1:34 PM Last modified: 3/6/19 1:34 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.orderfood.menulist;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.SampleContent;
import com.getmobileltd.trafficbar.application.TrafficBarApplication;
import com.getmobileltd.trafficbar.application.TrafficBarService;
import com.getmobileltd.trafficbar.orderfood.menudetails.MenuDetailsActivity;
import com.getmobileltd.trafficbar.orderfood.menulist.adapter.MenuAdapter;
import com.getmobileltd.trafficbar.orderfood.menulist.menulistener.MenuOnClickListener;
import com.getmobileltd.trafficbar.orderfood.menulist.model.Information;
import com.getmobileltd.trafficbar.orderfood.menulist.model.MenuResponse;

import java.util.ArrayList;
import java.util.List;

public class ListAvailableFoodActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private List<Information> menuList = new ArrayList<>();
    private Call<MenuResponse> menuCall;
    private TrafficBarService trafficBarService;
    private FrameLayout frameLayout;
    private MenuOnClickListener menuOnClickListener;
    public static final String EXTRA_MENU_DETAILS_ID = "extra_menu";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_available_food);
        trafficBarService = TrafficBarApplication.get(this).getTrafficBarService();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Menu");
        menuClickListener();
        frameLayout = findViewById(R.id.progress_view);
        frameLayout.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.recycler_view_menu);
        adapter = new MenuAdapter(menuList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter.setMenuListner(menuOnClickListener);
        recyclerView.setAdapter(adapter);
        getMenuList();


    }

    private void menuClickListener() {
        menuOnClickListener = new MenuOnClickListener() {
            @Override
            public void onClick(Information model) {
                Intent intent = new Intent(ListAvailableFoodActivity.this, MenuDetailsActivity.class);
                intent.putExtra(EXTRA_MENU_DETAILS_ID,model.getId());
                Toast.makeText(ListAvailableFoodActivity.this, "" + model.getName(), Toast.LENGTH_SHORT).show();
               startActivity(intent);

            }
        };
    }

    private void getMenuList() {
        menuCall = trafficBarService.getMenuCategory();
        menuCall.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                assert response.body() != null;
                menuList.clear();
                if (response.body().getCode() == 200) {
                    frameLayout.setVisibility(View.GONE);
                    List<Information> data = response.body().getData();
                    for (Information information : data) {
                         menuList.add(new Information(information.getName(),information.getCount(),information.getId()));

                    }
                    adapter = new MenuAdapter(menuList);
                    adapter.setMenuListner(menuOnClickListener);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(adapter);


                } else  {
                   frameLayout.setVisibility(View.GONE);
                    Toast.makeText(ListAvailableFoodActivity.this, "An error occurred! Try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
            frameLayout.setVisibility(View.GONE);
                Toast.makeText(ListAvailableFoodActivity.this, "Cannot connect to the internet", Toast.LENGTH_SHORT).show();

            }
        });
    }



}
