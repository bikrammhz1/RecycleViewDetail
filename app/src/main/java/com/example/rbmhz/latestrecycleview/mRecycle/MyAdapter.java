package com.example.rbmhz.latestrecycleview.mRecycle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.rbmhz.latestrecycleview.R;
import com.example.rbmhz.latestrecycleview.mData.CRUD;
import com.example.rbmhz.latestrecycleview.mData.Spacecraft;
import com.example.rbmhz.latestrecycleview.mDetail.DetailActivity;

import java.util.ArrayList;

/**
 * @author Bikram Maharjan
 * @version 1.1
 * @Date 4/10/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        Context c;
    CRUD crud;
    ArrayList<Spacecraft> spacecrafts;

    public MyAdapter(Context c,ArrayList<Spacecraft> spacecrafts ){
        this.c = c;
        this.spacecrafts = spacecrafts;

    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final String name = spacecrafts.get(position).getName();
        final String des = spacecrafts.get(position).getDescription();
        final int pos = position;

        //Bind
        holder.nameTxt.setText(name);
        holder.desTxt.setText(des);

        Spacecraft spacecraft = spacecrafts.get(position);
        holder.setData(spacecraft, position);


        holder.setListeners();


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {

                openDatailActivity(name,des,pos);

            }
        });

        holder.tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatailActivity(name,des,pos);
            }
        });

    }



    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }


    public void openDatailActivity(String name, String des,int Position){
        Intent i = new Intent(c, DetailActivity.class);
        i.putExtra("NameKey",name);
        i.putExtra("DesKey",des);
        i.putExtra("PosKey",Position);
        c.startActivity(i);
    }



    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameTxt;
        TextView desTxt;
        TextView tv_delete;
        TextView tv_update;
        ItemClickListener itemClickListener;
        CRUD crud;
        int i;

        public MyHolder(View itemView) {
            super(itemView);
            nameTxt = (TextView) itemView.findViewById(R.id.name_txt);
            desTxt = (TextView) itemView.findViewById(R.id.descrepion_txt);
            tv_delete = (TextView)itemView.findViewById(R.id.delet_txt);
            tv_update = (TextView)itemView.findViewById(R.id.tv_update);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener= itemClickListener;
        }

        private int position;
        private Spacecraft currentObject;


        public void setData(Spacecraft currentObject, int position) {
            this.position = position;
            this.currentObject = currentObject;


        }


        public void setListeners() {
            nameTxt = (TextView) itemView.findViewById(R.id.name_txt);
            desTxt = (TextView) itemView.findViewById(R.id.descrepion_txt);
            tv_delete = (TextView)itemView.findViewById(R.id.delet_txt);
            tv_update = (TextView)itemView.findViewById(R.id.tv_update);
            tv_delete.setOnClickListener(MyHolder.this);
        }



        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.delet_txt){
                removeItem(position);
//               final String name1 = spacecrafts.get(position).getName();

            }else{
                this.itemClickListener.onItemClick(getLayoutPosition());
            }


        }
        public void removeItem(int position) {
            spacecrafts.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, spacecrafts.size());
            notifyDataSetChanged();
        }
    }

}
