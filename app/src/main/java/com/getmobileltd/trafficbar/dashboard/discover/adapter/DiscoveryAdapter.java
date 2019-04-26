/*
 * *
 * Creator: Tobiloba Adejumo on 2/27/19 11:38 AM Last modified: 2/27/19 11:38 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.discover.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.dashboard.discover.listener.RestaurantClickListener;
import com.getmobileltd.trafficbar.dashboard.discover.model.DiscoveryModel;
import com.getmobileltd.trafficbar.dashboard.discover.response.DiscoverResponse;
import com.getmobileltd.trafficbar.dashboard.discover.response.Restaurant;

import java.util.List;

/**
 * Created by themavencoder on 27,February,2019
 */
public class DiscoveryAdapter extends RecyclerView.Adapter<DiscoveryAdapter.DiscoveryViewHolder> {
    private Context context;
    private List<Restaurant> modelList;
    private RestaurantClickListener mRestaurantClickListener;
    private int selectedPos = RecyclerView.NO_POSITION;


    public DiscoveryAdapter(Context context, List<Restaurant> modelList) {
        this.context = context;
        this.modelList = modelList;
    }


    @NonNull
    @Override
    public DiscoveryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_discover_fragment_restaurant, viewGroup, false);
        return new DiscoveryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoveryViewHolder discoveryViewHolder, int i) {
        Restaurant model = modelList.get(i);
        discoveryViewHolder.itemView.setSelected(selectedPos == i);
        if (discoveryViewHolder.itemView.isSelected()) {
            discoveryViewHolder.restaurantSelected.setVisibility(View.VISIBLE);
        } else {
            discoveryViewHolder.restaurantSelected.setVisibility(View.INVISIBLE);
        }
        discoveryViewHolder.bind(mRestaurantClickListener, model);


    }

    @Override
    public int getItemCount() {
        return null != modelList ? modelList.size() : 0;
    }

    class DiscoveryViewHolder extends RecyclerView.ViewHolder {
        private TextView state, street, rating;
        private RestaurantTickVisible restaurantTickVisible;
        private ImageView restaurantSelected;

        DiscoveryViewHolder(@NonNull View itemView) {
            super(itemView);
            state = itemView.findViewById(R.id.textview_state);
            street = itemView.findViewById(R.id.textview_street);
            rating = itemView.findViewById(R.id.textview_rating);
            restaurantSelected = itemView.findViewById(R.id.imageview_selected);


        }


        void bind(final RestaurantClickListener clickListener, final Restaurant model) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyItemChanged(selectedPos);
                    selectedPos = getAdapterPosition();
                    notifyItemChanged(selectedPos);
                    clickListener.onClick(model, restaurantSelected);



                }
            });

            street.setText(model.getAddress());


        }
    }

    public void setmRestaurantClickListener(RestaurantClickListener restaurantClickListener) {
        this.mRestaurantClickListener = restaurantClickListener;
    }


}
