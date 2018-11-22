package com.Alatheer.Projects.lailaky.ApiServices;

import com.Alatheer.Projects.lailaky.Models.AboutUsModel;
import com.Alatheer.Projects.lailaky.Models.ContactModel;
import com.Alatheer.Projects.lailaky.Models.ImgModel;
import com.Alatheer.Projects.lailaky.Models.ModelContactUs;
import com.Alatheer.Projects.lailaky.Models.OfferModel;
import com.Alatheer.Projects.lailaky.Models.PaperModel;
import com.Alatheer.Projects.lailaky.Models.ResponseModel;
import com.Alatheer.Projects.lailaky.Models.UserModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

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

    @GET("Api/ContactUs")
    Call<ModelContactUs> GetContactUs();

    @GET("Api/AllOffers")
    Call<List<OfferModel>> GetOffers();




    @GET("Api/MyProfile/{id}")
    Call<UserModel> Profile(@Path("id") String user_id);

    @FormUrlEncoded
    @POST("Api/UpdatePass/{id}")
    Call<ResponseModel> UpdatePassword(@Path("id")String user_id,@Field("user_pass") String password);

    @FormUrlEncoded
    @POST("Api/SubscribeOffer")
    Call<UserModel> BookAlbum(@Field("images[]")List<String> imageList,
                              @Field("user_id")String userid,
                              @Field("offer_id")String offerid
                              ,@Field("paper_id")String paper_id);

    @GET("Api/MyOffers/{user_id}")
    Call<List<OfferModel>> MyOffer(@Path("user_id") String user_id);

    @GET("Api/MyOffersImages/{album_id}")
    Call<List<ImgModel>> GallaryMyOffer(@Path("album_id") String offer_id);

    @FormUrlEncoded
    @POST("Api/UpdateProfile/{id}")
    Call<UserModel> UpdateProfile(@Path("id")String user_id,@FieldMap Map<String,String> map);
    @FormUrlEncoded
    @POST("Api/AddAlbumImage/{album_id}")
    Call<List<ImgModel>> AddImages(@Path("album_id") String album_id,@Field("images[]")List<String> imagesList);

    @FormUrlEncoded
    @POST("Api/DeleteImage")
    Call<ResponseModel> deleteImages(@Field("image_id[]") List<String> imgList);

    @Multipart
    @POST("Api/AddAlbumImage/{user_id}/{offer_id}/{paper_id}")
    Call<ResponseModel> uploadAlbumImages(@Path("user_id") String user_id,@Path("offer_id") String offer_id,@Path("paper_id") String paper_id,@Part List<MultipartBody.Part> imagesList);

    @FormUrlEncoded
    @POST("Api/forgetPassword")
    Call<UserModel> forgetPassword(@Field("user_email") String user_email);

    @GET("Api/papers")
    Call<List<PaperModel>> getTypePaper();
}
