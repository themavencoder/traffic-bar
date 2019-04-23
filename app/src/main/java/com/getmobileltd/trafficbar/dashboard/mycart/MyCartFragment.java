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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.application.SampleContent;
import com.getmobileltd.trafficbar.checkout.CheckoutActivity;
import com.getmobileltd.trafficbar.dashboard.mycart.adapter.MyCartAdapter;
import com.getmobileltd.trafficbar.dashboard.mycart.model.MyCartModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment implements View.OnClickListener {
    private View v;
    private RecyclerView mRecyclerView;
    private MyCartAdapter mAdapter;
    private List<MyCartModel> cartList = SampleContent.MYCART;
    private Button mButtonCheckout;


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
        mAdapter = new MyCartAdapter(getContext(),cartList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);
        mButtonCheckout = v.findViewById(R.id.button_checkout);
        mButtonCheckout.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), CheckoutActivity.class));
    }
}
