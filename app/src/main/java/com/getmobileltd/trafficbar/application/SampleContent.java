/*
 * *
 * Creator: Tobiloba Adejumo on 3/6/19 2:51 PM Last modified: 3/6/19 1:59 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.application;

import com.getmobileltd.trafficbar.dashboard.discover.model.DiscoveryModel;
import com.getmobileltd.trafficbar.dashboard.favourite.model.FavouriteModel;
import com.getmobileltd.trafficbar.dashboard.home.drinks.DrinksModel;
import com.getmobileltd.trafficbar.dashboard.home.food.FoodModel;
import com.getmobileltd.trafficbar.dashboard.home.trend.TrendModel;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.model.AddToCartModel;
import com.getmobileltd.trafficbar.dashboard.mycart.model.MyCartModel;
import com.getmobileltd.trafficbar.orderfood.menudetails.model.MenuDetailsModel;
import com.getmobileltd.trafficbar.orderfood.menulist.model.MenuResponse;

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
    public static List<FoodModel> MYFOOD = new ArrayList<>();
    public static List<DrinksModel> MYDRINKS = new ArrayList<>();
    public static List<MenuResponse> MYMENU = new ArrayList<>();
    public static List<MenuDetailsModel> MYMENUDETAILS = new ArrayList<>();
    public static List<AddToCartModel> MYEXTRAS = new ArrayList<>();

    static {
        DiscoveryModel model1 = new DiscoveryModel("15 Montgomerry Road,");
        DiscoveryModel model2 = new DiscoveryModel("15 Victoria Garden City,");
        DiscoveryModel model3 = new DiscoveryModel("15 Assam Esso Street,");
        RESTAURANT.add(model1);
        RESTAURANT.add(model2);
        RESTAURANT.add(model3);
        RESTAURANT.add(model1);
        RESTAURANT.add(model2);
        RESTAURANT.add(model3);

        FavouriteModel model = new FavouriteModel("Mashed potatoes", "Sliced yam and beans", "4.8", "N36,000");
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
        MYTRENDS.add(trendModel1);
        MYTRENDS.add(trendModel2);
        MYTRENDS.add(trendModel3);
        MYTRENDS.add(trendModel4);

        FoodModel foodModel1 = new FoodModel("10,000");
        FoodModel foodModel2 = new FoodModel("10,000");
        FoodModel foodModel3 = new FoodModel("10,000");
        FoodModel foodModel4 = new FoodModel("10,000");
        FoodModel foodModel5 = new FoodModel("10,000");
        FoodModel foodModel6 = new FoodModel("10,000");
        MYFOOD.add(foodModel1);
        MYFOOD.add(foodModel2);
        MYFOOD.add(foodModel3);
        MYFOOD.add(foodModel4);
        MYFOOD.add(foodModel5);
        MYFOOD.add(foodModel6);

        DrinksModel drinkModel1 = new DrinksModel("20,000");
        DrinksModel drinksModel2 = new DrinksModel("30,000");
        MYDRINKS.add(drinkModel1);
        MYDRINKS.add(drinksModel2);
        MYDRINKS.add(drinkModel1);
        MYDRINKS.add(drinksModel2);
        MYDRINKS.add(drinkModel1);
        MYDRINKS.add(drinksModel2);
        MYDRINKS.add(drinkModel1);
        MYDRINKS.add(drinksModel2);
        MYDRINKS.add(drinkModel1);
        MYDRINKS.add(drinksModel2);

        MenuDetailsModel menuDetailsModel1 = new MenuDetailsModel("Beans and Plantain", "N3,500");
        MYMENUDETAILS.add(menuDetailsModel1);
        MYMENUDETAILS.add(menuDetailsModel1);
        MYMENUDETAILS.add(menuDetailsModel1);
        MYMENUDETAILS.add(menuDetailsModel1);
        MYMENUDETAILS.add(menuDetailsModel1);
        MYMENUDETAILS.add(menuDetailsModel1);
        MYMENUDETAILS.add(menuDetailsModel1);
        MYMENUDETAILS.add(menuDetailsModel1);
        MYMENUDETAILS.add(menuDetailsModel1);
        MYMENUDETAILS.add(menuDetailsModel1);
        MYMENUDETAILS.add(menuDetailsModel1);

        AddToCartModel cartmodel1 = new AddToCartModel("Coke","500");
        AddToCartModel cartModel2 = new AddToCartModel("Malt","200");
        AddToCartModel cartModel3 = new AddToCartModel("Beer", "1500");
        MYEXTRAS.add(cartmodel1);
        MYEXTRAS.add(cartModel2);
        MYEXTRAS.add(cartModel3);
        MYEXTRAS.add(cartmodel1);
        MYEXTRAS.add(cartModel2);
        MYEXTRAS.add(cartModel3);
        MYEXTRAS.add(cartmodel1);
        MYEXTRAS.add(cartModel2);
        MYEXTRAS.add(cartModel3);

    }
}
