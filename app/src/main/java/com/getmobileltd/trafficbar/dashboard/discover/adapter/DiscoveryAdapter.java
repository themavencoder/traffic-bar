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
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.dashboard.discover.listener.RestaurantClickListener;
import com.getmobileltd.trafficbar.dashboard.discover.model.DiscoveryModel;

import java.util.List;

/**
 * Created by themavencoder on 27,February,2019
 */
public class DiscoveryAdapter extends RecyclerView.Adapter<DiscoveryAdapter.DiscoveryViewHolder> {
    private Context context;
    private List<DiscoveryModel> modelList;
    private RestaurantClickListener mRestaurantClickListener;



    public DiscoveryAdapter(Context context, List<DiscoveryModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }


    @NonNull
    @Override
    public DiscoveryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_discover_fragment_restaurant,viewGroup,false);
        return new DiscoveryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoveryViewHolder discoveryViewHolder, int i) {
        DiscoveryModel model = modelList.get(i);
       discoveryViewHolder.bind(mRestaurantClickListener,model);

    }

    @Override
    public int getItemCount() {
        return null != modelList ?  modelList.size() : 0;
    }

    class DiscoveryViewHolder extends RecyclerView.ViewHolder {
        private TextView state,street,rating;
        DiscoveryViewHolder(@NonNull View itemView) {
            super(itemView);
            state = itemView.findViewById(R.id.textview_state);
            street = itemView.findViewById(R.id.textview_street);
            rating = itemView.findViewById(R.id.textview_rating);
        }

        void bind(final RestaurantClickListener clickListener, final DiscoveryModel model) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(model);
                }
            });
            state.setText(model.getState());
            street.setText(model.getStreet());
            rating.setText(model.getRating());

        }
    }


}
