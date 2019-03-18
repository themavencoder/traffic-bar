/*
 * *
 * Creator: Tobiloba Adejumo on 3/16/19 2:21 PM Last modified: 3/16/19 2:21 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.mycart.addtocart.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.listener.AddCartExtraListener;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.model.AddToCartModel;

import java.util.List;

/**
 * Created by themavencoder on 16,March,2019
 */
public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.MyViewHolder> {
    private AddCartExtraListener mAddCartExtraListener;
    private List<AddToCartModel> modelList;

    public AddToCartAdapter(AddCartExtraListener listener, List<AddToCartModel> modelList) {
        this.modelList = modelList;
        this.mAddCartExtraListener = listener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_add_to_cart,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        AddToCartModel model = modelList.get(i);
        myViewHolder.bind(mAddCartExtraListener,model);

    }

    @Override
    public int getItemCount() {
        return modelList != null ? modelList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textviewName, textviewPrice;

         MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewName = itemView.findViewById(R.id.textview_extras_name);
            textviewPrice = itemView.findViewById(R.id.textview_extras_price);
        }

        void bind(final AddCartExtraListener mAddCartExtraListener, final AddToCartModel model) {
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     mAddCartExtraListener.onClick(model);
                 }
             });
             textviewPrice.setText(model.getPrice());
             textviewName.setText(model.getName());
        }
    }
}
