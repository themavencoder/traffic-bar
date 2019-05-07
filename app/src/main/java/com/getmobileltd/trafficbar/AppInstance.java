/*
 * *
 * Creator: Tobiloba Adejumo on 4/24/19 3:00 PM Last modified: 4/24/19 3:00 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar;

/**
 * Created by themavencoder on 24,April,2019
 */
public class AppInstance {
    private static AppInstance sInstance = null;

    private String api_key;

    private int cart_count;

    private String firstName;
    private String lastName;

    private int navlocation;
    public static AppInstance getInstance() {
        if (sInstance == null) {
            sInstance = new AppInstance();
        }
        return sInstance;
    }

    public String getApi_key() {
        return api_key;
    }

    public int getNavlocation() {
        return navlocation;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setNavlocation(int navlocation) {
        this.navlocation = navlocation;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public int getCart_count() {
        return cart_count;
    }

    public void setCart_count(int cart_count) {
        this.cart_count = cart_count;
    }
}
