package com.Alatheer.Projects.laylaky.ApiServices;

import com.Alatheer.Projects.laylaky.Models.AboutUsModel;
import com.Alatheer.Projects.laylaky.Models.ContactModel;
import com.Alatheer.Projects.laylaky.Models.OfferModel;
import com.Alatheer.Projects.laylaky.Models.UserModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Emad on 2018-04-03.
 */

public interface Services {

    @FormUrlEncoded
    @POST("Api/InsertRegistration")
    Call<UserModel> Register(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("Api/Login")
    Call<UserModel> Login(@FieldMap Map<String,String> map);

    @GET("Api/AboutUs")
    Call<List<AboutUsModel>> GetAboutUs();

    @FormUrlEncoded
    @POST("Api/ContactUs")
    Call<ContactModel> ContactUs(@FieldMap Map<String,String> map);

    @GET("Api/AllOffers")
    Call<List<OfferModel>> GetOffers();

}
