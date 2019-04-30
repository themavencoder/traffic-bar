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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.dashboard.mycart.listener.CartOnClickListener;
import com.getmobileltd.trafficbar.dashboard.mycart.model.CartData;

import java.util.List;

/**
 * Created by themavencoder on 04,March,2019
 */
public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {

    private List<CartData> modelList;
    private CartOnClickListener mListener;

    public MyCartAdapter(List<CartData> modelList) {

        this.modelList = modelList;

    }

    public void setCartClickListener(CartOnClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cart, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        CartData data = modelList.get(i);
        myViewHolder.bind(data, mListener, myViewHolder.itemView);

    }

    @Override
    public int getItemCount() {
        return null != modelList ? modelList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewDelete, imageViewItem, imageViewAdd, imageViewMinus;
        private TextView textViewFoodName, textViewCategory, textViewPrice, textViewQuantity;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewItem = itemView.findViewById(R.id.imageview_item);
            textViewFoodName = itemView.findViewById(R.id.textview_food);
            textViewCategory = itemView.findViewById(R.id.textview_category);
            textViewQuantity = itemView.findViewById(R.id.textview_quantity);
            imageViewDelete = itemView.findViewById(R.id.imageview_delete);
            imageViewAdd = itemView.findViewById(R.id.imageview_plus);
            imageViewMinus = itemView.findViewById(R.id.imageview_minus);
            textViewPrice = itemView.findViewById(R.id.textview_price);


        }

        public void bind(final CartData data, final CartOnClickListener mListener, final View view) {

            imageViewMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onMinus(data);
                }
            });
            imageViewAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.add(data,view);
                }
            });
            imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onDelete(data);
                }
            });

            textViewFoodName.setText(data.getName());
            textViewQuantity.setText(String.valueOf(data.getQuantity()));
            textViewPrice.setText(data.getPrice());

            Glide.with(itemView.getContext()).load(data.getImage())
                    .apply(new RequestOptions()
                            .placeholder(R.color.colorAccent)
                            .error(R.color.black))
                    .into(imageViewItem);

        }
    }
}
