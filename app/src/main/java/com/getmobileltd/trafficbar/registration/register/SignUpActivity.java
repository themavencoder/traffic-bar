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
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.databinding.SignUpActivityBinding;
import com.getmobileltd.trafficbar.registration.register.handlers.RegisterClickHandler;
import com.getmobileltd.trafficbar.registration.register.model.User;
import com.getmobileltd.trafficbar.registration.register.mvp.RegisterContract;
import com.getmobileltd.trafficbar.registration.register.mvp.RegisterPresenter;

public class SignUpActivity extends AppCompatActivity implements RegisterContract.View {
    private SignUpActivityBinding binding;
    private RegisterClickHandler handler;
    private RegisterPresenter presenter;
    private User user;
    private String firstName, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        user = new User();
        presenter = new RegisterPresenter(user,this);
        //firstName = binding.editTextFirstName.getText().toString();
        //lastName = binding.editTextLastName.getText().toString();

        handler = new RegisterClickHandler(this,presenter);
        binding.setHandlers(handler);





    }

    @Override
    public void showError() {



    }

    @Override
    public void buttonClick() {
        String firstName = binding.editTextFirstName.getText().toString();
        String lastName = binding.editTextLastName.getText().toString();
        boolean result = presenter.checkParameters(firstName,lastName);



    }
}
