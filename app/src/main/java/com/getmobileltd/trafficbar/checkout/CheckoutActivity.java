/*
 * *
 * Creator: Tobiloba Adejumo on 3/18/19 9:56 AM Last modified: 3/18/19 9:56 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.UiSettings;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        UiSettings.colorStatusbar(this,R.color.colorAccent);
    }
}
