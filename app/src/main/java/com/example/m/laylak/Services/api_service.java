package com.example.m.laylak.Services;

import com.example.m.laylak.Models.ResponsModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Emad on 2018-04-03.
 */

public interface api_service {

    @FormUrlEncoded
    @POST("Api/InsertRegistration")
    Call<ResponsModel> Register(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("Api/Login")
    Call<ResponsModel> Login(@FieldMap Map<String,String> map);

}
