/*
 * *
 * Creator: Tobiloba Adejumo on 2/26/19 1:36 PM Last modified: 2/26/19 1:19 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.registration.login.mvp;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.getmobileltd.trafficbar.AppInstance;
import com.getmobileltd.trafficbar.database.repository.UserRepository;
import com.getmobileltd.trafficbar.registration.forgotpassword.ForgotPasswordActivity;
import com.getmobileltd.trafficbar.registration.register.SignUpActivity;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getmobileltd.trafficbar.R;
import com.getmobileltd.trafficbar.application.TrafficBarApplication;
import com.getmobileltd.trafficbar.application.TrafficBarService;
import com.getmobileltd.trafficbar.application.UiSettings;
import com.getmobileltd.trafficbar.dashboard.DashboardActivity;
import com.getmobileltd.trafficbar.registration.login.dialog.LoginDialog;
import com.getmobileltd.trafficbar.registration.login.networkresponse.LogInResponse;
import com.getmobileltd.trafficbar.registration.register.model.User;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {
    public static final String TAG = LoginActivity.class.getSimpleName();
    private EditText mEditEmail, mEditPassword;
    private Button mButtonLogin;
    private TrafficBarService trafficBarService;
    private Call<LogInResponse> loginCall;
    private LoginContract.Presenter presenter;
    private User user;
    private LoginDialog mLoginDialog;
    private SmoothProgressBar mProgressBar;
    private FrameLayout frameLayout;
    private CoordinatorLayout mCoordinatorLayout;
    private TextView mTvSignup;
    private UserRepository repository;
    private TextView mTvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UiSettings.colorStatusbar(this, R.color.app_background);
        repository = new UserRepository(getApplication());
        init();
        trafficBarService = TrafficBarApplication.get(this).getTrafficBarService();

    }


    private void init() {
        mEditEmail = findViewById(R.id.edit_text_email_address);
        mEditPassword = findViewById(R.id.edit_text_password);
        mButtonLogin = findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(this);
        presenter = new LoginPresenter(this);
        mLoginDialog = new LoginDialog();
        // mProgressBar = findViewById(R.id.progress_view);
        frameLayout = findViewById(R.id.progress_view);
        mCoordinatorLayout = findViewById(R.id.coordinatorLayout);
        mTvSignup = findViewById(R.id.textview_sign_up);
        mTvForgotPassword = findViewById(R.id.textview_forgot_password);
        mTvForgotPassword.setOnClickListener(this);
        mTvSignup.setOnClickListener(this);

        //  frameLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

    }


    @Override
    public void onBackPressed() {

        if (frameLayout.getVisibility() == View.VISIBLE) {
            if (loginCall != null) {
                loginCall.cancel();
            }
            frameLayout.setVisibility(View.GONE);

        } else if (frameLayout.getVisibility() == View.GONE) {
            super.onBackPressed();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginCall != null) {
            loginCall.cancel();
        }

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void buttonClick() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        //    startActivity(new Intent(this, MainActivity.class));
        //  startActivity(new Intent());

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textview_forgot_password) {
            startActivity(new Intent(this, ForgotPasswordActivity.class));
        }
        else if (v.getId() == R.id.textview_sign_up) {
            startActivity(new Intent(this, SignUpActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }


        String email = mEditEmail.getText().toString();
        String password = mEditPassword.getText().toString();
        presenter.saveLoginCredentials(email, password);
        if (presenter.checkParameters()) {
              /*  mLoginDialog.setCancelable(false);
                mLoginDialog.show(getSupportFragmentManager(),"my_dialog");*/
            frameLayout.setVisibility(View.VISIBLE);
            user = new User(email, password);
            loginUser(user);
        } else {
            if (email.isEmpty()) {
                errorMessage("Email field is empty", mCoordinatorLayout, getApplication());
                return;
            }
            errorMessage("Password should be more than five characters", mCoordinatorLayout, getApplicationContext());
        }

    }

    private void loginUser(User user) {
        loginCall = trafficBarService.logUser(user);
        loginCall.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogInResponse> call, @NonNull Response<LogInResponse> response) {
                assert response.body() != null;
                if (response.body().getCode() == 200) {
                    // mLoginDialog.dismiss();
                    String apikey = response.body().getData().getUser().getKey();
                    String firstName = response.body().getData().getUser().getFirstName();
                    String lastName = response.body().getData().getUser().getLastName();
                    AppInstance appInstance = AppInstance.getInstance();
                    appInstance.setApi_key(apikey);
                    appInstance.setFirstName(firstName);
                    appInstance.setLastName(lastName);
                    com.getmobileltd.trafficbar.database.model.User user = new com.getmobileltd.trafficbar.database.model.User(1, apikey, firstName,lastName,Integer.parseInt(response.body().getData().getUser().getUserId()));
                    repository.insert(user);


                    frameLayout.setVisibility(View.GONE);
                    presenter.navigateToNextActivity();
                } else {
                    //  mLoginDialog.dismiss();
                    frameLayout.setVisibility(View.GONE);
                    //  presenter.setError();
                    errorMessage("Password and email do not match. Try again", mCoordinatorLayout, getApplicationContext());
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                //   mLoginDialog.dismiss();
                frameLayout.setVisibility(View.GONE);
                Log.i(TAG, t.getMessage());
                errorMessage("Unable to process your request. Please try again", mCoordinatorLayout, getApplicationContext());
                //  Toast.makeText(LoginActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public static void errorMessage(String s, CoordinatorLayout mCoordinatorLayout, Context activiy) {
        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, s, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        sbView.setBackgroundColor(activiy.getResources().getColor(R.color.colorAccent));
        TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
        textView.setTextColor(activiy.getResources().getColor(R.color.white));
        snackbar.show();

    }


}
