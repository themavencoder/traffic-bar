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
import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.EmailValidator;
import com.getmobileltd.trafficbar.application.TrafficBarApplication;
import com.getmobileltd.trafficbar.application.TrafficBarService;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.registration.login.mvp.LoginActivity;
import com.getmobileltd.trafficbar.registration.register.confirmregister.dialog.ConfirmSignUpDialog;
import com.getmobileltd.trafficbar.registration.register.confirmregister.mvp.ConfirmRegisterContract;
import com.getmobileltd.trafficbar.registration.register.confirmregister.mvp.ConfirmRegisterPresenter;
import com.getmobileltd.trafficbar.registration.register.model.User;
import com.getmobileltd.trafficbar.registration.register.networkresponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private Button mButtonAgree;
    private Call<SignUpResponse> signUpCall;
    private TrafficBarService trafficBarService;
    private boolean checkAgree = false;
    private Drawable drawableChecked, drawableUnChecked;
    private CoordinatorLayout mCoordinatorLayout;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_register);
        UiSettings.colorStatusbar(this,R.color.app_background);
        trafficBarService = TrafficBarApplication.get(this).getTrafficBarService();
        passedDataFromSignUp();
        init();
        setDrawable();

        presenter = new ConfirmRegisterPresenter(this);
        mButtonAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkAgree) {
                    mButtonAgree.setCompoundDrawables(drawableChecked, null, null, null);
                    checkAgree = true;
                    return;


                }
                if (checkAgree) {
                    mButtonAgree.setCompoundDrawables(drawableUnChecked, null, null, null);
                    checkAgree = false;
                    return;

                }

            }
        });
    }

    private void setDrawable() {
        drawableChecked = ContextCompat.getDrawable(
                this,
                R.drawable.checked
        );
        drawableUnChecked = ContextCompat.getDrawable(
                this,
                R.drawable.unchecked
        );
        drawableChecked.setBounds(
                0, // left
                0, // top
                drawableChecked.getIntrinsicWidth(), // right
                drawableChecked.getIntrinsicHeight() // bottom
        );

        drawableUnChecked.setBounds(
                0, // left
                0, // top
                drawableUnChecked.getIntrinsicWidth(), // right
                drawableUnChecked.getIntrinsicHeight() // bottom
        );
    }

    private void init() {
        mEditEmailAddress = findViewById(R.id.edit_text_email_address);
        mEditPassword = findViewById(R.id.edit_text_password);
        mButtonSignUp = findViewById(R.id.button_signup);
        mButtonSignUp.setOnClickListener(this);
        mConfirmSignUpDialog = new ConfirmSignUpDialog();
        mButtonAgree = findViewById(R.id.button_agree);
        mCoordinatorLayout = findViewById(R.id.coordinatorLayout);
        frameLayout = findViewById(R.id.progress_view);

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void buttonClick() {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    private void passedDataFromSignUp() {
        Intent intent = getIntent();
        firstName = intent.getStringExtra(INTENT_FIRSTNAME);
        lastName = intent.getStringExtra(INTENT_LASTNAME);
        //    Toast.makeText(this, "Your first name is" + firstName + "your last name is " + lastName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        String emailAddress = mEditEmailAddress.getText().toString();
        String password = mEditPassword.getText().toString();
        presenter.saveName(emailAddress, password);
        if (presenter.checkParameters()) {
            if (!checkAgree) {
                LoginActivity.errorMessage("Please agree to receive newsletter and updates!", mCoordinatorLayout, this);
                //Toast.makeText(this, "Please agree to receive newsletter and update", Toast.LENGTH_SHORT).show();
                return;
            }
            frameLayout.setVisibility(View.VISIBLE);
            //  mConfirmSignUpDialog.setCancelable(false);
            // mConfirmSignUpDialog.show(getSupportFragmentManager(),"my_dialog");
            user = new User(firstName, lastName, emailAddress, password);
            insertUser(user);
        } else {

            if (!EmailValidator.isValidEmail(emailAddress)) {
                LoginActivity.errorMessage("Enter a valid email address", mCoordinatorLayout, this);

            } else if (password.length() <= 5) {
                LoginActivity.errorMessage("Password should be more than 5 characters", mCoordinatorLayout, this);
            }

        }


    }

    private void insertUser(User user) {
        signUpCall = trafficBarService.createUser(user);
        signUpCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                assert response.body() != null;

                if (response.code() == 200) {
                    // mConfirmSignUpDialog.dismiss();
                    frameLayout.setVisibility(View.GONE);
                    LoginActivity.errorMessage("Account created successfully",mCoordinatorLayout,getApplicationContext());
                 //   Toast.makeText(ConfirmRegisterActivity.this, "Successful login", Toast.LENGTH_SHORT).show();
                    presenter.navigateToNextActivity();
                } else if (response.code() == 409) {
                    frameLayout.setVisibility(View.GONE);
                    LoginActivity.errorMessage("A user with this email already exist. Try a different one", mCoordinatorLayout, getApplicationContext());
                    // Toast.makeText(ConfirmRegisterActivity.this, "Problem occured, try again!" + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    frameLayout.setVisibility(View.GONE);
                    LoginActivity.errorMessage("Problem creating an account. Try again", mCoordinatorLayout, getApplicationContext());

                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                frameLayout.setVisibility(View.GONE);
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

    @Override
    public void onBackPressed() {

        if (frameLayout.getVisibility() == View.VISIBLE) {
            if (signUpCall != null){
                signUpCall.cancel();
            }
            frameLayout.setVisibility(View.GONE);

        } else if (frameLayout.getVisibility() == View.GONE) {
            super.onBackPressed();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        }

    }
}

