/*
 * *
 * Creator: Tobiloba Adejumo on 3/2/19 3:47 PM Last modified: 3/2/19 3:46 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.favourite.model;

/**
 * Created by themavencoder on 02,March,2019
 */

public class FavouriteModel {
    private String header_one;
    private String header_two;
    private String rating;
    private String price;

    public FavouriteModel() {

    }
    public FavouriteModel(String header_one, String header_two, String rating, String price) {
        this.header_one  = header_one;
        this.header_two = header_two;
        this.rating = rating;
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public String getHeader_one() {
        return header_one;
    }

    public String getHeader_two() {
        return header_two;
    }

    public String getPrice() {
        return price;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setHeader_one(String header_one) {
        this.header_one = header_one;
    }

    public void setHeader_two(String header_two) {
        this.header_two = header_two;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}

