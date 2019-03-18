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
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.SampleContent;
import com.getmobileltd.trafficbar.orderfood.menudetails.MenuDetailsActivity;
import com.getmobileltd.trafficbar.orderfood.menulist.adapter.MenuAdapter;
import com.getmobileltd.trafficbar.orderfood.menulist.menulistener.MenuOnClickListener;
import com.getmobileltd.trafficbar.orderfood.menulist.model.MenuModel;

import java.util.List;

public class ListAvailableFoodActivity extends AppCompatActivity implements MenuOnClickListener {
    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private List<MenuModel> modelList = SampleContent.MYMENU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_available_food);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Menu");
        recyclerView = findViewById(R.id.recycler_view_menu);
        adapter = new MenuAdapter(modelList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(MenuModel model) {
        Toast.makeText(this, "You clicked on" + model.getName(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MenuDetailsActivity.class));

    }
}
