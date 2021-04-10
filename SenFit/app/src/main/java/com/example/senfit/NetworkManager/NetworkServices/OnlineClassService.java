/*
PRJ666 Sen-Fit
init date: April 9th 2021
Author Mitchell Culligan
Version 1.0
OnlineClassService
This interface repersents a service that interacts with the Sen-FIt API that specializes in dealing with OnlineClass data.
 */
package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.MemberOnlineClass;
import com.example.senfit.dataContext.entities.OnlineClass;
import com.example.senfit.generated.callback.OnClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OnlineClassService {

    @GET("/onlineClasses")
    public Call<List<OnlineClass>> getOnlineClasses();

    @POST("/onlimeClaases/enroll")
    public Call<OnlineClass> enrollOnlineClass(@Body MemberOnlineClass memberOnlineClass);
}
