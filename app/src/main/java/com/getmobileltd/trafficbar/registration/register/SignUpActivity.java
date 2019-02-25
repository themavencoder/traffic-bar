/*
 * *
 * Creator: Tobiloba Adejumo on 2/23/19 4:45 PM Last modified: 2/22/19 9:13 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.registration.register;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.databinding.SignUpActivityBinding;
import com.getmobileltd.trafficbar.registration.register.handlers.RegisterClickHandler;
import com.getmobileltd.trafficbar.registration.register.model.User;

public class SignUpActivity extends AppCompatActivity {
    private SignUpActivityBinding binding;
    private RegisterClickHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        handler = new RegisterClickHandler(this,new User());
        binding.setHandlers(handler);


    }
}
