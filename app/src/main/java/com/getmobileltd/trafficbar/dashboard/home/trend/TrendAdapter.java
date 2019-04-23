/*
 * *
 * Creator: Tobiloba Adejumo on 3/5/19 3:51 PM Last modified: 3/5/19 3:51 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.home.trend;

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
 * Created by themavencoder on 05,March,2019
 */
public class TrendAdapter extends RecyclerView.Adapter<TrendAdapter.MyViewHolder> {
    private TrendOnClickListener trendOnClickListener;
    private Context context;
    private List<TrendModel> modelList;

    public TrendAdapter(TrendOnClickListener trendOnClickListener, List<TrendModel> modelList) {
        this.trendOnClickListener = trendOnClickListener;
        this.modelList = modelList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_trending,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        TrendModel model = modelList.get(i);
        myViewHolder.bind(trendOnClickListener, model);

    }

    @Override
    public int getItemCount() {
        return null != modelList ? modelList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView price;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.textview_price);
        }

        void bind(final TrendOnClickListener trendOnClickListener, final TrendModel model) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    trendOnClickListener.onClick(model);
                }
            });
            price.setText(model.getPrice());

        }
    }
}
