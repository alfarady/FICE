package id.web.runup.fice.data.api;

import id.web.runup.fice.data.models.Application;
import id.web.runup.fice.data.models.Job;
import id.web.runup.fice.data.models.JobAction;
import id.web.runup.fice.data.models.JobFeed;
import id.web.runup.fice.data.models.JobPost;
import id.web.runup.fice.data.models.Notif;
import id.web.runup.fice.data.models.Register;
import id.web.runup.fice.data.models.UpdateUser;
import id.web.runup.fice.data.models.UploadAction;
import id.web.runup.fice.data.models.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IApiService {
    @FormUrlEncoded
    @POST("register")
    Call<Register> doRegister(@Field("email") String email,
                              @Field("password") String password,
                              @Field("phone") String phone,
                              @Field("name") String name,
                              @Field("age") String age,
                              @Field("country") String country,
                              @Field("notifToken") String notifToken,
                              @Field("coordinate") String coordinate);

    @FormUrlEncoded
    @POST("v1/user")
    Call<UpdateUser> doUpdate(@Header("Authorization") String token,
                              @Field("email") String email,
                              @Field("password") String password,
                              @Field("phone") String phone,
                              @Field("name") String name,
                              @Field("age") String age,
                              @Field("country") String country,
                              @Field("notifToken") String notifToken);

    @Multipart
    @POST("v1/user_ava")
    Call<UploadAction> uploadAva(@Header("Authorization") String token,
                                       @Part MultipartBody.Part file,
                                       @Part("input_image") RequestBody name);

    @Multipart
    @POST("v1/user_cv")
    Call<UploadAction> uploadCv(@Header("Authorization") String token,
                                 @Part MultipartBody.Part file,
                                 @Part("input_image") RequestBody name);

    @FormUrlEncoded
    @POST("login")
    Call<Register> doLogin(@Field("email") String email,
                           @Field("password") String password,
                           @Field("notifToken") String notifToken,
                           @Field("coordinate") String coordinate);

    @GET("v1/user")
    Call<User> getUser(@Header("Authorization") String token,
                       @Query("coordinate") String coordinate);

    @GET("v1/job")
    Call<JobFeed> getFeed(@Header("Authorization") String token);

    @GET("v1/job")
    Call<JobFeed> searchFeed(@Header("Authorization") String token,
                             @Query("search") String search);

    @GET("v1/job")
    Call<JobFeed> getFeedNearby(@Header("Authorization") String token,
                                @Query("near") String near);

    @GET("v1/job")
    Call<JobFeed> searchFeedNeary(@Header("Authorization") String token,
                             @Query("near") String near,
                             @Query("search") String search);

    @GET("v1/job")
    Call<JobFeed> getFeedMostView(@Header("Authorization") String token,
                                     @Query("mv") int mv);

    @GET("v1/job")
    Call<JobFeed> searchFeedMostView(@Header("Authorization") String token,
                                    @Query("mv") int mv,
                                    @Query("search") String search);

    @GET("v1/job")
    Call<JobFeed> getFeedByCat(@Header("Authorization") String token,
                               @Query("cat") int cat);

    @GET("v1/job")
    Call<JobFeed> getFeedByType(@Header("Authorization") String token,
                               @Query("type") int type);

    @GET("v1/job")
    Call<Job> getJob(@Header("Authorization") String token,
                     @Query("id_job") String id_job);

    @GET("v1/notif")
    Call<Notif> getNotif(@Header("Authorization") String token);

    @GET("v1/application")
    Call<Application> getApplication(@Header("Authorization") String token,
                                     @Query("id_trx") int id_trx);

    @FormUrlEncoded
    @POST("v1/job")
    Call<JobPost> postJob(@Header("Authorization") String token,
                          @Field("id_kategori") int id_kategori,
                          @Field("id_tipe") int id_tipe,
                          @Field("judul") String judul,
                          @Field("deskripsi") String deskripsi,
                          @Field("alamat") String alamat,
                          @Field("gaji") String gaji,
                          @Field("durasi") String durasi);

    @FormUrlEncoded
    @POST("v1/apply")
    Call<JobAction> applyJob(@Header("Authorization") String token,
                             @Field("id_job") int id_job);

    @FormUrlEncoded
    @POST("v1/approve")
    Call<JobAction> approveJob(@Header("Authorization") String token,
                             @Field("id_trx") int id_trx);

    @FormUrlEncoded
    @POST("v1/reject")
    Call<JobAction> rejectJob(@Header("Authorization") String token,
                             @Field("id_trx") int id_trx);
}
