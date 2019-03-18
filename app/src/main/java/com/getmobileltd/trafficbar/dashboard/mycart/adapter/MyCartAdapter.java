/*
 * *
 * Creator: Tobiloba Adejumo on 3/4/19 4:13 PM Last modified: 3/4/19 4:13 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.mycart.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.dashboard.mycart.listener.CartOnClickListener;
import com.getmobileltd.trafficbar.dashboard.mycart.model.MyCartModel;

import java.util.List;

/**
 * Created by themavencoder on 04,March,2019
 */
public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {
    private Context context;
    private List<MyCartModel> modelList;
    private CartOnClickListener mListener;
    public MyCartAdapter(Context context, List<MyCartModel> modelList) {
        this.context = context;
        this.modelList = modelList;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cart,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return null != modelList ? modelList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
