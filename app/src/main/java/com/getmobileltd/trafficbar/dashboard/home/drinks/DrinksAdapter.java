/*
 * *
 * Creator: Tobiloba Adejumo on 3/6/19 12:45 PM Last modified: 3/6/19 12:45 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.home.drinks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getmobileltd.trafficbar.R;

import java.util.List;

/**
 * Created by themavencoder on 06,March,2019
 */
public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.MyViewHolder> {
    private List<DrinksModel> modelList;
    private Context context;
    private DrinksOnClickListener mListener;

    public DrinksAdapter(Context context, List<DrinksModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_drinks,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        DrinksModel model = modelList.get(i);
        myViewHolder.bind(mListener, model);


    }

    @Override
    public int getItemCount() {
        return null != modelList ? modelList.size(): 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPrice;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPrice = itemView.findViewById(R.id.textview_price);
        }

        void bind(final DrinksOnClickListener mListener, final DrinksModel model) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(model);
                }
            });

            textViewPrice.setText(model.getPrice());
        }
    }
}
