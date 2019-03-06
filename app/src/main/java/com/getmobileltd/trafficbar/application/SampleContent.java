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
import com.getmobileltd.trafficbar.dashboard.mycart.model.MyCartModel;
import com.getmobileltd.trafficbar.orderfood.foodlist.model.MenuModel;

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
    public static List<MenuModel> MYMENU = new ArrayList<>();

    static {
        DiscoveryModel model1 = new DiscoveryModel("15 Montgomerry Road,", "Igando, Lagos", "4.1");
        DiscoveryModel model2 = new DiscoveryModel("15 Victoria Garden City,", "Igando, Lagos", "4.2");
        DiscoveryModel model3 = new DiscoveryModel("15 Assam Esso Street,", "Igando, Lagos", "4.3");
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

        MenuModel menuModel1 = new MenuModel("Traffic Breakfast", "20");
        MenuModel menuModel2 = new MenuModel("Nigeria Soup", "25");
        MenuModel menuModel3 = new MenuModel("Traffic On The Run", "25");
        MenuModel menuModel4 = new MenuModel("Salad Meals", "21");
        MenuModel menuModel5 = new MenuModel("Pasta Specials", "21");
        MenuModel menuModel6 = new MenuModel("Chicken Pot + Grills", "22");
        MenuModel menuModel7 = new MenuModel("Ethnic Culture Soups", "23");
        MenuModel menuModel8 = new MenuModel("Express Rice and Mixes", "42");
        MenuModel menuModel9 = new MenuModel("Additionals", "12");
        MenuModel menuModel10 = new MenuModel("Pizzas", "22");
        MenuModel menuModel11 = new MenuModel("Sandwiches & Sidekicks", "24");
        MenuModel menuModel12 = new MenuModel("Drinks", "14");
        MYMENU.add(menuModel1);
        MYMENU.add(menuModel2);
        MYMENU.add(menuModel3);
        MYMENU.add(menuModel4);
        MYMENU.add(menuModel5);
        MYMENU.add(menuModel6);
        MYMENU.add(menuModel7);
        MYMENU.add(menuModel8);
        MYMENU.add(menuModel9);
        MYMENU.add(menuModel10);
        MYMENU.add(menuModel11);
        MYMENU.add(menuModel12);


    }
}
