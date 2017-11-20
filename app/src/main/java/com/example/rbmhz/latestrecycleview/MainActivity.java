package com.example.rbmhz.latestrecycleview;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rbmhz.latestrecycleview.mData.CRUD;
import com.example.rbmhz.latestrecycleview.mData.Spacecraft;
import com.example.rbmhz.latestrecycleview.mData.GetterSetterCollections;
import com.example.rbmhz.latestrecycleview.mDetail.DetailActivity;
import com.example.rbmhz.latestrecycleview.mRecycle.MyAdapter;

/**
 * @author Bikram Maharjan
 * @version 1.1
 * @Date 4/10/2017.
 */

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    MyAdapter myAdapter;
    CRUD crud;
    EditText ed1, ed2;
    Button btnSave;
    Button getBtnIntent;
    String name;
    String lname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
//        getBtnIntent = (Button)findViewById(R.id.btn_intent);
        FloatingActionButton getBtnIntent = (FloatingActionButton) findViewById(R.id.btn_intent);
//        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        crud = new CRUD(GetterSetterCollections.getSpacecrafts());
        myAdapter = new MyAdapter(this, crud.getSpacecrafts());
        //rv.setAdapter(myAdapter);

        getBtnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, DetailActivity.class), 1);

//                Intent i = new Intent(MainActivity.this, DetailActivity.class);
//                startActivity(i);

//                Toast.makeText(getApplicationContext(),"asdfsadf",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Add Data


    @Override
    protected void onResume() {
        super.onResume();
        rv.setAdapter(myAdapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 1) {
                if (getIntent() != null) {
                    name = data.getStringExtra("NAME1");
                    lname = data.getStringExtra("LNAME1");
                    Log.d("value ", name);
                    Log.d("value 2", lname);


                    Spacecraft s = new Spacecraft();
                    s.setName(name);
                    s.setDescription(lname);
                    if (crud.addNew(s)) {
                        ed1.setText("");
                        ed2.setText("");
                        rv.setAdapter(myAdapter);
                    }


                }
            }

        } catch (Exception e) {

        }

    }
}

