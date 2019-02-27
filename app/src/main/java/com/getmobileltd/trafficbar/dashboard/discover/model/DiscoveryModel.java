/*
 * *
 * Creator: Tobiloba Adejumo on 2/27/19 11:17 AM Last modified: 2/27/19 11:17 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.discover.model;

/**
 * Created by themavencoder on 27,February,2019
 */
public class DiscoveryModel {

    private String street;
    private String country;
    private String rating;

    public DiscoveryModel(String street, String country, String rating) {
        this.street = street;
        this.country = country;
        this.rating = rating;

    }
    public DiscoveryModel() {

    }

    public String getState() {
        return country;
    }

    public String getRating() {
        return rating;
    }

    public String getStreet() {
        return street;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
