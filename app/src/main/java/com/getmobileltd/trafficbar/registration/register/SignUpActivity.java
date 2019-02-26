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

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.registration.register.confirmregister.ConfirmRegisterActivity;
import com.getmobileltd.trafficbar.registration.register.handlers.RegisterClickHandler;
import com.getmobileltd.trafficbar.registration.register.model.User;
import com.getmobileltd.trafficbar.registration.register.mvp.RegisterContract;
import com.getmobileltd.trafficbar.registration.register.mvp.RegisterPresenter;

public class SignUpActivity extends AppCompatActivity implements RegisterContract.View, View.OnClickListener {
    private RegisterClickHandler handler;
    private RegisterPresenter presenter;
    private User user;
    private Button mButtonContinue;
    private EditText mFirstName, mLastName;
    private String firstName, lastName;
    public static final String INTENT_FIRSTNAME = "com.getmobileltd.trafficbar.registration.register";
    public static final String INTENT_LASTNAME = "com.getmobileltd.trafficbar.registration.register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();

    }

    private void init() {
        user  = new User();
        presenter = new RegisterPresenter(user,this);
        mButtonContinue = findViewById(R.id.button_continue);
        mButtonContinue.setOnClickListener(this);
        mFirstName = findViewById(R.id.edit_text_first_name);
        mLastName = findViewById(R.id.edit_text_last_name);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void buttonClick() {
        Intent intent = new Intent(this, ConfirmRegisterActivity.class);
        intent.putExtra(INTENT_FIRSTNAME,presenter.firstName());
        intent.putExtra(INTENT_LASTNAME,presenter.lastName());
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        String firstName = mFirstName.getText().toString();
        String lastName = mLastName.getText().toString();
        presenter.saveName(firstName,lastName);
        if(presenter.checkParameters()) {
            presenter.navigateToNextActivity();
        } else {
            presenter.setError();

        }

    }
}