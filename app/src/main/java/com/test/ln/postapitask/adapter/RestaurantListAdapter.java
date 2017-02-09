package com.test.ln.postapitask.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.ln.postapitask.R;
import com.test.ln.postapitask.activity.ResLocationActivity;
import com.test.ln.postapitask.model.RestaurantDetails;

import java.util.ArrayList;

/**
 * Created by ln-300 on 9/2/17.
 */

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder> {

    ArrayList<RestaurantDetails> detailsArrayList;
    Context mContext;

    public RestaurantListAdapter(Context context,ArrayList<RestaurantDetails> detailses)
    {
        this.mContext=context;
        this.detailsArrayList=detailses;
        Log.i("response","In adapter Constructor");
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_activity_main,parent,false);
        Log.i("response","In onCreateViewHolder");

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.i("response","In onCreateViewHolder");
        holder.res_name.setText(detailsArrayList.get(position).getRes_name());
        holder.res_logo.setImageResource(R.drawable.res_img);
        holder.res_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,ResLocationActivity.class);
                intent.putExtra("lat",detailsArrayList.get(position).getRes_loc_lat());
                intent.putExtra("lng",detailsArrayList.get(position).getRes_loc_lng());
                intent.putExtra("name",detailsArrayList.get(position).getRes_name());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("response","In ItemCount"+detailsArrayList.size());
        return detailsArrayList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView res_name;
        public ImageView res_logo;
        public ImageView res_loc;

        public MyViewHolder(View view){
            super(view);
            Log.i("response","In MyViewHolder Constructor");
            res_name=(TextView)view.findViewById(R.id.tv_res_name);
            res_logo=(ImageView)view.findViewById(R.id.res_logo);
            res_loc=(ImageView)view.findViewById(R.id.loc_logo);
        }
    }


}
