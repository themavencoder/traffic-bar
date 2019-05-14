/*
 * *
 * Creator: Tobiloba Adejumo on 5/13/19 12:55 PM Last modified: 5/13/19 12:55 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.orderfood.menudetails.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by themavencoder on 13,May,2019
 */
public class Payment implements Parcelable {

    private List<List<String>> menu_extras;

    protected Payment(Parcel in) {
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel in) {
            return new Payment(in);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };

    public List<List<String>> getMenu_extras() {
        return menu_extras;
    }

    public void setMenu_extras(List<List<String>> menu_extras) {
        this.menu_extras = menu_extras;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
