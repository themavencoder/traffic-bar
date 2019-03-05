/*
 * *
 * Creator: Tobiloba Adejumo on 2/27/19 12:22 PM Last modified: 2/27/19 12:22 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.discover;

import com.getmobileltd.trafficbar.dashboard.discover.model.DiscoveryModel;
import com.getmobileltd.trafficbar.dashboard.favourite.model.FavouriteModel;
import com.getmobileltd.trafficbar.dashboard.home.trend.TrendModel;
import com.getmobileltd.trafficbar.dashboard.mycart.model.MyCartModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by themavencoder on 27,February,2019
 */
public class SampleContent {

    public static List<DiscoveryModel> RESTAURANT = new ArrayList<>();
    public static List<FavouriteModel> FAVOURITES = new ArrayList<>();
    public static List<MyCartModel> MYCART = new ArrayList<>();
    public static List<TrendModel> MYTRENDS = new ArrayList<>();

    static {
        DiscoveryModel model1 = new DiscoveryModel("15 Montgomerry Road,", "Igando, Lagos","4.1");
        DiscoveryModel model2 = new DiscoveryModel("15 Victoria Garden City,", "Igando, Lagos","4.2");
        DiscoveryModel model3 = new DiscoveryModel("15 Assam Esso Street,", "Igando, Lagos","4.3");
        RESTAURANT.add(model1);
        RESTAURANT.add(model2);
        RESTAURANT.add(model3);
        RESTAURANT.add(model1);
        RESTAURANT.add(model2);
        RESTAURANT.add(model3);

        FavouriteModel model = new FavouriteModel("Mashed potatoes","Sliced yam and beans","4.8","N36,000");
            FAVOURITES.add(model);
        FAVOURITES.add(model);
        FAVOURITES.add(model);
        FAVOURITES.add(model);
        FAVOURITES.add(model);
        FAVOURITES.add(model);
        FAVOURITES.add(model);
        FAVOURITES.add(model);
        FAVOURITES.add(model);
        FAVOURITES.add(model);

        MyCartModel cartone = new MyCartModel("Mushedroom tomatoes");
        MyCartModel cartTwo = new MyCartModel("Beans and Bread");
        MYCART.add(cartone);
        MYCART.add(cartTwo);
        MYCART.add(cartone);
        MYCART.add(cartTwo);
        MYCART.add(cartone);
        MYCART.add(cartTwo);
        MYCART.add(cartone);
        MYCART.add(cartTwo);
        MYCART.add(cartone);
        MYCART.add(cartTwo);
        MYCART.add(cartone);
        MYCART.add(cartTwo);

        TrendModel trendModel1 = new TrendModel("40,000");
        TrendModel trendModel2 = new TrendModel("50,000");
        TrendModel trendModel3 = new TrendModel("60,000");
        TrendModel trendModel4 = new TrendModel("70,000");
        MYTRENDS.add(trendModel1);
        MYTRENDS.add(trendModel2);
        MYTRENDS.add(trendModel3);
        MYTRENDS.add(trendModel4);
        MYTRENDS.add(trendModel1);
        MYTRENDS.add(trendModel2);
        MYTRENDS.add(trendModel3);
        MYTRENDS.add(trendModel4);


    }
}
