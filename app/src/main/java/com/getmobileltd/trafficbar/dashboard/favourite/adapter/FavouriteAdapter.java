/*
 * *
 * Creator: Tobiloba Adejumo on 3/2/19 3:54 PM Last modified: 3/2/19 3:54 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.favourite.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.dashboard.favourite.listener.FavouriteClickListener;
import com.getmobileltd.trafficbar.dashboard.favourite.model.FavouriteModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by themavencoder on 02,March,2019
 */
public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder> {
    private List<FavouriteModel> modelList;
    private FavouriteClickListener mListener;
    private Context context;

    public FavouriteAdapter(Context context, List<FavouriteModel> modelList) {
        this.context = context;
   this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favourites,viewGroup,false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        FavouriteModel model = modelList.get(i);
        myViewHolder.bind(mListener,model);
    }

    @Override
    public int getItemCount() {
        return null != modelList ?  modelList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView header_one, header_two,rating,price;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            header_one = itemView.findViewById(R.id.textview_header_one);
            header_two = itemView.findViewById(R.id.textview_header_two);
            rating = itemView.findViewById(R.id.textview_rating);
            price = itemView.findViewById(R.id.textview_price);
        }
        void bind(final FavouriteClickListener listener, final FavouriteModel model) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(model);
                }
            });
            header_one.setText(model.getHeader_one());
            header_two.setText(model.getHeader_two());
            rating.setText(model.getRating());
            price.setText(model.getPrice());



        }
    }


}
