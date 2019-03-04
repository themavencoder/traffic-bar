/*
 * *
 * Creator: Tobiloba Adejumo on 2/25/19 11:52 AM Last modified: 2/25/19 11:52 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.favourite;


import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.dashboard.discover.SampleContent;
import com.getmobileltd.trafficbar.dashboard.discover.adapter.DiscoveryAdapter;
import com.getmobileltd.trafficbar.dashboard.discover.model.DiscoveryModel;
import com.getmobileltd.trafficbar.dashboard.favourite.adapter.FavouriteAdapter;
import com.getmobileltd.trafficbar.dashboard.favourite.model.FavouriteModel;
import com.getmobileltd.trafficbar.dashboard.favourite.utils.GridSpacingItemDecoration;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {
    private View v;
    private RecyclerView mRecyclerView;
    private FavouriteAdapter mAdapter;
    private List<FavouriteModel> discoveryList = SampleContent.FAVOURITES;



    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       UiSettings.colorStatusbar(getActivity(),R.color.deep_ash);
        // Inflate the layout for this fragment

        v =  inflater.inflate(R.layout.fragment_favourite, container, false);
        mRecyclerView = v.findViewById(R.id.recycler_view);
     mAdapter = new FavouriteAdapter(getContext(),discoveryList);
     mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(8),true));
     mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
