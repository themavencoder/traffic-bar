/*
 * *
 * Creator: Tobiloba Adejumo on 3/6/19 3:47 PM Last modified: 3/6/19 3:47 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.orderfood.menudetails.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.orderfood.menudetails.listener.MenuDetailsOnClickListener;
import com.getmobileltd.trafficbar.orderfood.menudetails.model.MenuDetailsModel;
import com.getmobileltd.trafficbar.orderfood.menudetails.model.Menus;
import com.getmobileltd.trafficbar.orderfood.menulist.menulistener.MenuOnClickListener;

import java.util.List;

/**
 * Created by themavencoder on 06,March,2019
 */
public class MenuDetailsAdapter extends RecyclerView.Adapter<MenuDetailsAdapter.MyViewModel> {

    private List<Menus> modelList;
    private MenuDetailsOnClickListener mListener;

    public MenuDetailsAdapter(List<Menus> modelList) {
        this.modelList = modelList;


    }
    public void     setmenuClickListener(MenuDetailsOnClickListener listener) {
        this.mListener = listener;

    }

    @NonNull
    @Override
    public MyViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu_details,viewGroup,false);

        return new MyViewModel(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewModel myViewModel, int i) {
        Menus model = modelList.get(i);
        myViewModel.bind(mListener,model);

    }

    @Override
    public int getItemCount() {
        return null != modelList ? modelList.size() : 0;
    }

    class MyViewModel extends RecyclerView.ViewHolder {
        String link = "https://i.ibb.co/yn8s6Yv/bread-butter-medium.png";
        private TextView textViewName, textViewPrice;
        private Button buttonOrderNow;
        private ImageView imageViewSmall;
        MyViewModel(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textview_food_name);
            textViewPrice = itemView.findViewById(R.id.textview_food_price);
            buttonOrderNow = itemView.findViewById(R.id.button_order_now);
            imageViewSmall = itemView.findViewById(R.id.imageview_menu_food);
        }



        void bind(final MenuDetailsOnClickListener mListener, final Menus model) {
            buttonOrderNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(model);
                }
            });
            textViewPrice.setText(model.getMenu_price());
            textViewName.setText(model.getMenu_name());

            Glide.with(itemView.getContext())
                    .load(model.getMenu_image_sm())
                    .apply(new RequestOptions()
                    .placeholder(R.color.colorAccent)
                    .error(R.color.black))
                    .into(imageViewSmall);
        }

    }
}
