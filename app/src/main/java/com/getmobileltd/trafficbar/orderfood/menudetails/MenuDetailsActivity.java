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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.SampleContent;
import com.getmobileltd.trafficbar.orderfood.menudetails.adapter.MenuDetailsAdapter;
import com.getmobileltd.trafficbar.orderfood.menudetails.listener.MenuDetailsOnClickListener;
import com.getmobileltd.trafficbar.orderfood.menudetails.model.MenuDetailsModel;

import java.util.List;

public class MenuDetailsActivity extends AppCompatActivity  implements MenuDetailsOnClickListener {
        private RecyclerView recyclerView;
        private List<MenuDetailsModel> modelList = SampleContent.MYMENUDETAILS;
        private MenuDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Traffic Breakfast");
        recyclerView = findViewById(R.id.recycler_view_menu_details);
        adapter = new MenuDetailsAdapter(modelList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(MenuDetailsModel model) {

    }
}
