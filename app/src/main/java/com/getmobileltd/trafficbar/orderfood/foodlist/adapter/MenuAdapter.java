/*
 * *
 * Creator: Tobiloba Adejumo on 3/6/19 2:38 PM Last modified: 3/6/19 2:38 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.orderfood.foodlist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.orderfood.foodlist.menulistener.MenuOnClickListener;
import com.getmobileltd.trafficbar.orderfood.foodlist.model.MenuModel;

import java.util.List;

/**
 * Created by themavencoder on 06,March,2019
 */
public class MenuAdapter  extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    private List<MenuModel> modelList;
    private MenuOnClickListener mListener;

    public MenuAdapter(List<MenuModel> modelList, MenuOnClickListener mListener) {
        this.modelList =modelList;
        this.mListener = mListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        MenuModel model = modelList.get(i);
        myViewHolder.bind(mListener,model);

    }

    @Override
    public int getItemCount() {
        return null != modelList ? modelList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewMenuName, textViewMenuNumber;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMenuName = itemView.findViewById(R.id.textview_menu_name);
            textViewMenuNumber = itemView.findViewById(R.id.textview_menu_number);
        }

        void bind(final MenuOnClickListener mListener, final MenuModel model) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(model);
                }
            });

            textViewMenuName.setText(model.getName());
            textViewMenuNumber.setText(model.getNumber());

        }
    }
}