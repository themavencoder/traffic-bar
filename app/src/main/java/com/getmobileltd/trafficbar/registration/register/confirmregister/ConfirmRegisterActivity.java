/*
 * *
 * Creator: Tobiloba Adejumo on 2/23/19 4:44 PM Last modified: 2/23/19 4:40 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.registration.register.confirmregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.TrafficBarApplication;
import com.getmobileltd.trafficbar.application.TrafficBarService;
import com.getmobileltd.trafficbar.registration.login.LoginActivity;
import com.getmobileltd.trafficbar.registration.register.confirmregister.dialog.ConfirmSignUpDialog;
import com.getmobileltd.trafficbar.registration.register.confirmregister.mvp.ConfirmRegisterContract;
import com.getmobileltd.trafficbar.registration.register.confirmregister.mvp.ConfirmRegisterPresenter;
import com.getmobileltd.trafficbar.registration.register.model.User;
import com.getmobileltd.trafficbar.registration.register.networkresponse.SignUpResponse;

import okhttp3.internal.http2.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Headers;

import static com.getmobileltd.trafficbar.registration.register.SignUpActivity.INTENT_FIRSTNAME;
import static com.getmobileltd.trafficbar.registration.register.SignUpActivity.INTENT_LASTNAME;

public class ConfirmRegisterActivity extends AppCompatActivity implements ConfirmRegisterContract.View, View.OnClickListener {
    private String firstName;
    private String lastName;
    private EditText mEditEmailAddress, mEditPassword;
    private Button mButtonSignUp;
    private ConfirmSignUpDialog mConfirmSignUpDialog;
    private ConfirmRegisterContract.Presenter presenter;
    private User user;
    private Call<SignUpResponse> signUpCall;
    private TrafficBarService trafficBarService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_register);
        trafficBarService  = TrafficBarApplication.get(this).getTrafficBarService();
        passedDataFromSignUp();
        init();
        presenter = new ConfirmRegisterPresenter(this);
    }

    private void init() {
        mEditEmailAddress = findViewById(R.id.edit_text_email_address);
        mEditPassword = findViewById(R.id.edit_text_password);
        mButtonSignUp = findViewById(R.id.button_signup);
        mButtonSignUp.setOnClickListener(this);
        mConfirmSignUpDialog = new ConfirmSignUpDialog();

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void buttonClick() {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

    private void passedDataFromSignUp() {
        Intent intent = getIntent();
         firstName =  intent.getStringExtra(INTENT_FIRSTNAME);
        lastName =  intent.getStringExtra(INTENT_LASTNAME);
        Toast.makeText(this, "Your first name is" + firstName + "your last name is " + lastName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    String emailAddress = mEditEmailAddress.getText().toString();
    String password = mEditPassword.getText().toString();
    presenter.saveName(emailAddress,password);
    if (presenter.checkParameters()) {
        mConfirmSignUpDialog.setCancelable(false);
        mConfirmSignUpDialog.show(getSupportFragmentManager(),"my_dialog");
        user = new User(firstName,lastName,emailAddress,password);
        insertUser(user);        
    } else {
        presenter.setError();

    }


    }

    private void insertUser(User user) {
    signUpCall = trafficBarService.createUser(user);
    signUpCall.enqueue(new Callback<SignUpResponse>() {
        @Override
        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
            assert response.body() != null;

            if (response.code() == 200) {
                mConfirmSignUpDialog.dismiss();
                Toast.makeText(ConfirmRegisterActivity.this, "Successful login", Toast.LENGTH_SHORT).show();
                presenter.navigateToNextActivity();
            }
            else {
                mConfirmSignUpDialog.dismiss();
                Toast.makeText(ConfirmRegisterActivity.this, "Problem occured, try again!" + response.code(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<SignUpResponse> call, Throwable t) {
            mConfirmSignUpDialog.dismiss();
            Toast.makeText(ConfirmRegisterActivity.this, "Unable to connect to the internet " + t.getMessage(), Toast.LENGTH_SHORT).show();

        }
    });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (signUpCall != null) {
            signUpCall.cancel();
        }
    }
}

