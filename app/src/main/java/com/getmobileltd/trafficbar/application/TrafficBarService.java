/*
 * *
 * Creator: Tobiloba Adejumo on 2/26/19 9:06 AM Last modified: 2/26/19 9:05 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.application;

import com.getmobileltd.trafficbar.dashboard.discover.response.DiscoverResponse;
import com.getmobileltd.trafficbar.dashboard.home.drinks.DrinkResponse;
import com.getmobileltd.trafficbar.dashboard.home.food.FoodResponse;
import com.getmobileltd.trafficbar.dashboard.home.trend.TrendingResponse;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.AddToCartResponse;
import com.getmobileltd.trafficbar.dashboard.mycart.addtocart.model.AddToCartModel;
import com.getmobileltd.trafficbar.dashboard.mycart.deletecart.DeleteResponse;
import com.getmobileltd.trafficbar.dashboard.mycart.model.MyCartResponse;
import com.getmobileltd.trafficbar.dashboard.mycart.updatecart.UpdateCartResponse;
import com.getmobileltd.trafficbar.dashboard.profile.editprofile.UserUpdateResponse;
import com.getmobileltd.trafficbar.orderfood.menudetails.model.MenuDetailsModel;
import com.getmobileltd.trafficbar.orderfood.menulist.model.MenuResponse;
import com.getmobileltd.trafficbar.registration.forgotpassword.ResetPasswordResponse;
import com.getmobileltd.trafficbar.registration.forgotpassword.ResetUser;
import com.getmobileltd.trafficbar.registration.login.networkresponse.LogInResponse;
import com.getmobileltd.trafficbar.registration.register.model.User;
import com.getmobileltd.trafficbar.registration.register.networkresponse.SignUpResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by themavencoder on 26,February,2019
 */
public interface TrafficBarService {
    String a = "category_details";

    @POST("user/create")
    Call<SignUpResponse> createUser(@Body User user);

    @POST("auth/login")
    Call<LogInResponse> logUser(@Body User user);

    @POST("auth/password/create")
    Call<ResetPasswordResponse> resetPassword(@Body ResetUser user);


    @GET("restaurants")
    Call<DiscoverResponse> getRestaurants(@Header("Authorization") String value);

    @GET("menu_category")
    Call<MenuResponse> getMenuCategory();

    @GET("menu_category/{id}")
    Call<MenuDetailsModel> getMenuCategoryDetails(@Path("id") int id);

    @POST("carts/create")
    Call<AddToCartResponse> createCart(@Header("Authorization") String value, @Body AddToCartModel model);

    @GET("carts")
    Call<MyCartResponse> getCart(@Header("Authorization") String value);


    @DELETE("carts/delete/{id}")
    Call<DeleteResponse> deleteCart(@Header("Authorization") String value, @Path("id") int id);

    @GET("dashboard/trending")
    Call<TrendingResponse> getTrending(@Header("Authorization") String value);

    @GET("dashboard/drink")
    Call<DrinkResponse> getDrinks(@Header("Authorization") String value);

    @GET("dashboard/Food")
    Call<FoodResponse> getFood(@Header("Authorization") String value);

    @PUT("carts/update/{id}")
    Call<UpdateCartResponse> updateCart(@Header("Authorization") String value, @Path("id") int id);

    @Multipart
    @POST("user/update/{id}")
    Call<UserUpdateResponse> updateUser(@Header("Authorization") String value, @Path("id") int id,@Part MultipartBody.Part photo, @Part MultipartBody.Part first_name,
                                        @Part MultipartBody.Part last_name
                                        );


   /* @Multipart
    @POST("user/update/{id}")
    Call<UserUpdateResponse> updateUser(@Header("Authorization") String value, @Path("id") int id,
                                        @Query("first_name") String first_name, @Query("last_name")
                                                String last_name, @Query("email") String email, @Query("password")
                                                String password, @Part MultipartBody.Part file);

*/
}
