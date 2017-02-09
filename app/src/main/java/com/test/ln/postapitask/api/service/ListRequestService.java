package com.test.ln.postapitask.api.service;

import com.test.ln.postapitask.api.request.ListRequest;
import com.test.ln.postapitask.api.response.ListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ln-300 on 8/2/17.
 */

public interface ListRequestService {

    @POST("get_orders.php")
    Call<ListResponse> getOrderList(@Body ListRequest listRequest);
}
