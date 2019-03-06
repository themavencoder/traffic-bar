/*
 * *
 * Creator: Tobiloba Adejumo on 3/4/19 4:14 PM Last modified: 3/4/19 4:14 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.mycart.model;

/**
 * Created by themavencoder on 04,March,2019
 */
public class MyCartModel {
    private String heading_one;
    public MyCartModel() {

    }
    public MyCartModel(String heading_one) {
        this.heading_one = heading_one;

    }

    public String getHeading_one() {
        return heading_one;
    }

    public void setHeading_one(String heading_one) {
        this.heading_one = heading_one;
    }
}
