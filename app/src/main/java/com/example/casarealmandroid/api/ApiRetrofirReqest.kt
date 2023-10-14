package com.example.casarealmandroid.api


import com.example.casarealmandroid.response_data.asl.Assortiment
import com.example.casarealmandroid.response_data.token.AuthentificateUserResult
import com.example.casarealmandroid.response_data.token.Token
import com.example.casarealmandroid.response_data.workplace.WorkSpace
import com.example.casarealmandroid.response_data.workplace_seting.SettingWorkSpace
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRetrofirReqest {

    @GET("/epos/json/AuthentificateUser")
     suspend fun getTokenUser(@Query("userLogin")long: String, @Query("userPass")password: String ): Token


    @GET("/epos/json/GetWorkPlaces")
    suspend fun getWorkPlace(@Query("Token")token: String): WorkSpace

    @GET("/epos/json/GetAssortmentList")
    suspend fun getAslWP(@Query("Token")token: String, @Query("WorkplaceId")casa: String): Assortiment

    @GET("/epos/json/GetWorkPlaces")
    fun getPlace(@Query("Token")token: String): Call<WorkSpace>
    @GET("/epos/json/GetWorkplaceSettings")
    suspend fun getWorkplace(@Query("Token")token: String, @Query("WorkplaceId")casa: String): SettingWorkSpace
}