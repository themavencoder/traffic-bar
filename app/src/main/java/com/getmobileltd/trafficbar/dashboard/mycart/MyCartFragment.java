/*
 * *
 * Creator: Tobiloba Adejumo on 3/4/19 11:34 AM Last modified: 3/4/19 11:34 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.mycart;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.getmobileltd.trafficbar.AppInstance;
import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.TrafficBarApplication;
import com.getmobileltd.trafficbar.application.TrafficBarService;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.application.SampleContent;
import com.getmobileltd.trafficbar.checkout.CheckoutActivity;
import com.getmobileltd.trafficbar.dashboard.mycart.adapter.MyCartAdapter;
import com.getmobileltd.trafficbar.dashboard.mycart.deletecart.DeleteResponse;
import com.getmobileltd.trafficbar.dashboard.mycart.listener.CartOnClickListener;
import com.getmobileltd.trafficbar.dashboard.mycart.model.CartData;
import com.getmobileltd.trafficbar.dashboard.mycart.model.MyCartModel;
import com.getmobileltd.trafficbar.dashboard.mycart.model.MyCartResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment implements View.OnClickListener {
    private View v;
    private RecyclerView mRecyclerView;
    private MyCartAdapter mAdapter;
    private List<CartData> cartList =new ArrayList<>();
    private Button mButtonCheckout;
    private TrafficBarService trafficBarService;
    private Call<MyCartResponse> cartResponseCall;
    private Call<DeleteResponse> deleteResponseCall;
    private AppInstance appInstance;
    private CartOnClickListener mListener;
    private FrameLayout frameLayout;


    public MyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        UiSettings.colorStatusbar(getActivity(),R.color.colorAccent);
        v =  inflater.inflate(R.layout.fragment_my_cart, container, false);
        mRecyclerView = v.findViewById(R.id.recycler_view);
        mAdapter = new MyCartAdapter(cartList);
        checkout();
        mAdapter.setCartClickListener(mListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);

        mButtonCheckout = v.findViewById(R.id.button_checkout);
        mButtonCheckout.setOnClickListener(this);
        trafficBarService = TrafficBarApplication.get(getActivity()).getTrafficBarService();
        appInstance = AppInstance.getInstance();
        frameLayout = v.findViewById(R.id.progress_view);
        getCart();


        return v;
    }

    private void checkout() {
        mListener = new CartOnClickListener() {
            @Override
            public void add(CartData model, View v) {
                Toast.makeText(getActivity(), "add", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDelete(CartData model) {
                Toast.makeText(getActivity(), "Delete", Toast.LENGTH_SHORT).show();
                deleteResponseCall = trafficBarService.deleteCart(appInstance.getApi_key(),model.getId());
                frameLayout.setVisibility(View.VISIBLE);
                deleteResponseCall.enqueue(new Callback<DeleteResponse>() {
                    @Override
                    public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                        assert response.body() != null;
                        if (response.body().getCode() == 201) {
                            frameLayout.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Item deleted successfully", Toast.LENGTH_SHORT).show();
                            getCart();
                        } else {
                            frameLayout.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Error in deleting Try again!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<DeleteResponse> call, Throwable t) {
                        frameLayout.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Unable to connect to network", Toast.LENGTH_SHORT).show();

                    }
                });


            }

            @Override
            public void onMinus(CartData model) {
                Toast.makeText(getActivity(), "Minus", Toast.LENGTH_SHORT).show();
            }
        };

    }

    @Override
    public void onClick(View v) {
       // startActivity(new Intent(getActivity(), CheckoutActivity.class));



    }

    private void getCart() {
        frameLayout.setVisibility(View.VISIBLE);
        cartResponseCall = trafficBarService.getCart(appInstance.getApi_key());
        cartResponseCall.enqueue(new Callback<MyCartResponse>() {
            @Override
            public void onResponse(Call<MyCartResponse> call, @NotNull Response<MyCartResponse> response) {
                assert response.body() != null;
                if (response.body().status.equals("success")) {
                    cartList.clear();
                    frameLayout.setVisibility(View.GONE);
                    List<CartData> cartData = response.body().getData();
                    for (CartData datas : cartData) {
                        cartList.add(new CartData(datas.getId(),datas.getUser_id(),datas.getMenu_id(),datas.getName(),datas.getCategory(),datas.getPrice(),datas.getImage(),datas.getQuantity(),datas.getTotal(),datas.getTax()));
                    }
                    mAdapter = new MyCartAdapter(cartList);
                    mAdapter.setCartClickListener(mListener);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    frameLayout.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Could not process. Try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MyCartResponse> call, Throwable t) {
                frameLayout.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Unable to connect to network!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
