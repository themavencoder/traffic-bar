/*
 * *
 * Creator: Tobiloba Adejumo on 2/25/19 1:27 PM Last modified: 2/25/19 1:27 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.registration.register.model;

import android.annotation.TargetApi;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.ObservableField;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;

import com.getmobileltd.trafficbar.BR;

import java.util.Objects;

/**
 * Created by themavencoder on 25,February,2019
 */
public class User extends BaseObservable {
    private String firstName;
    private String lastName;
    private boolean isEmpty;
    public ObservableField<String> text = new ObservableField<>();

    @Bindable
    public boolean getIsEmpty() {
        return isEmpty;
    }
    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setEmpty(boolean empty) {
        isEmpty = firstName.isEmpty() || lastName.isEmpty();
        isEmpty = empty;
        notifyPropertyChanged(BR.isEmpty);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);


    }




    public boolean checkInput(String firstName, String lastName) {
        /* if (firstName == null || lastName == null)  {
            return false;
         }
        return firstName.isEmpty() || lastName.isEmpty();
*/          if (firstName ==null || lastName == null)
                return false;
        return !firstName.isEmpty() && !lastName.isEmpty();
    }

}