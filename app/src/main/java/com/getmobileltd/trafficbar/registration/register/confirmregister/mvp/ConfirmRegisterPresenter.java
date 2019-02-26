package com.getmobileltd.trafficbar.registration.register.confirmregister.mvp;

import com.getmobileltd.trafficbar.registration.register.model.User;

/**
 * Created by themavencoder on 26,February,2019
 */
public class ConfirmRegisterPresenter implements ConfirmRegisterContract.Presenter {
    private User user;
    private ConfirmRegisterContract.View view;
    public ConfirmRegisterPresenter(ConfirmRegisterContract.View view) {
        user = new User();
        this.view = view;
    }
    @Override
    public boolean checkParameters() {
        String emailAddress = user.getEmailAddress();
        String password = user.getPassword();
        return !emailAddress.trim().isEmpty() && !password.trim().isEmpty();
    }

    @Override
    public void navigateToNextActivity() {
        view.buttonClick();
    }

    @Override
    public void saveName(String emailAddress, String password) {
        user.setEmailAddress(emailAddress);
        user.setPassword(password);
    }

    @Override
    public String emailAddress() {
        return user.getFirstName();
    }

    @Override
    public String password() {
        return user.getLastName();
    }

    @Override
    public void setError() {
        view.showError("No field should be empty");

    }
}
