package com.test.ln.postapitask.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.test.ln.postapitask.R;
import com.test.ln.postapitask.adapter.RestaurantListAdapter;
import com.test.ln.postapitask.api.ApiFactory;
import com.test.ln.postapitask.api.request.ListRequest;
import com.test.ln.postapitask.api.response.ListResponse;
import com.test.ln.postapitask.api.service.ListRequestService;
import com.test.ln.postapitask.model.RestaurantDetails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private ProgressDialog mProgressDialog;
    private RecyclerView mRecyclerView;
    private ArrayList<RestaurantDetails> restaurantDetailses;
    private RestaurantListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.str_please_wait));
        mRecyclerView=(RecyclerView)findViewById(R.id.order_list);
        restaurantDetailses=new ArrayList<>();
        getOrderList();
        Log.i("response","Out getOrderList");




    }

    private void getOrderList()
    {
        mProgressDialog.show();
        ListRequest listRequest=new ListRequest();
        ListRequest.Data requestData=new ListRequest().new Data();
        requestData.setStatus("past");
        requestData.setPage("1");
        requestData.setUserId("225995");
        listRequest.setData(requestData);
        Log.i("response",listRequest.getData().getPage()+listRequest.getData().getStatus()+listRequest.getData().getUserId());
        makeWsCallforOrderList(listRequest);

    }


    private void makeWsCallforOrderList(ListRequest listRequest)
    {
        Log.i("response","In makeWs");
        ListRequestService listRequestService= ApiFactory.createService(ListRequestService.class);
        Call<ListResponse> listResponseCall=listRequestService.getOrderList(listRequest);
        listResponseCall.enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                mProgressDialog.dismiss();
                Log.i("response", String.valueOf(response.code()));
                Log.i("response", response.raw().toString());
                if(response.isSuccessful()) {
                    Log.i("response","Successful Response");
                    if(response.body().getStatus().equals("1")) {
                        for(int i=0;i<response.body().getData().size();i++) {
                            RestaurantDetails restaurantDetails=new RestaurantDetails();
                            restaurantDetails.setRes_name(response.body().getData().get(i).getTitle());
                            restaurantDetails.setRes_logo(response.body().getData().get(i).getResLogo());
                            restaurantDetails.setRes_loc_lat(response.body().getData().get(i).getResLat());
                            restaurantDetails.setRes_loc_lng(response.body().getData().get(i).getResLong());
                            restaurantDetailses.add(restaurantDetails);

                            /*String rest_name = response.body().getData().get(i).getTitle();
                            Log.i("response", rest_name);*/
                        }

                    }
                }
                Log.i("response","Out onResponse");
                adapter=new RestaurantListAdapter(MainActivity.this,restaurantDetailses);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Log.i("response","In Failure");
                mProgressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Please Try Again Later",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
