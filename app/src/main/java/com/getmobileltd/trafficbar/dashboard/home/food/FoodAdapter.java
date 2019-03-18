/*
 * *
 * Creator: Tobiloba Adejumo on 3/6/19 11:32 AM Last modified: 3/6/19 11:32 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.home.food;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getmobileltd.trafficbar.R;

import java.util.List;

/**
 * Created by themavencoder on 06,March,2019
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
    private List<FoodModel> modelList;
    private Context context;
    private FoodOnClickListener mListener;

    public FoodAdapter(Context context, List<FoodModel> modelList) {
        this.context = context;
        this.modelList = modelList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        FoodModel model = modelList.get(i);
        myViewHolder.bind(mListener, model);

    }

    @Override
    public int getItemCount() {
        return null != modelList ? modelList.size() : 0;
    }

     class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewprice;
         MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewprice = itemView.findViewById(R.id.textview_price);

        }

        void bind(final FoodOnClickListener mListener, final FoodModel model) {
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     mListener.onClick(model);
                 }
             });

             textViewprice.setText(model.getPrice());
        }
    }
}
