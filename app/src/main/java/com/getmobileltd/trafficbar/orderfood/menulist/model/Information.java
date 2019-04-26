/*
 * *
 * Creator: Tobiloba Adejumo on 4/26/19 10:02 AM Last modified: 4/26/19 10:02 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.orderfood.menulist.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by themavencoder on 26,April,2019
 */
public class Information {

    public Information(String name, int count,int id) {
        this.name = name;
        this.count = count;
        this.id = id;
    }

    @SerializedName("href")
    private String href;

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    @SerializedName("count")
    private int count;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
