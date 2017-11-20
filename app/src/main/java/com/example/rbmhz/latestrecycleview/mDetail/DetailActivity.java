package com.example.rbmhz.latestrecycleview.mDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rbmhz.latestrecycleview.MainActivity;
import com.example.rbmhz.latestrecycleview.R;
import com.example.rbmhz.latestrecycleview.mData.CRUD;
import com.example.rbmhz.latestrecycleview.mData.Spacecraft;
import com.example.rbmhz.latestrecycleview.mData.GetterSetterCollections;

/**
 * @author Bikram Maharjan
 * @version 1.1
 * @Date 4/10/2017.
 */

public class DetailActivity extends AppCompatActivity {
    EditText nameTxt, detailTxt;
    String name, des;
    int pos;

    Button getBtnSave, getBtndelete, btnAdd;
    CRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameTxt = (EditText) findViewById(R.id.name_detail);
        detailTxt = (EditText) findViewById(R.id.description_detail);
        getBtnSave = (Button) findViewById(R.id.btn_save11);
        getBtndelete = (Button) findViewById(R.id.btn_delete11);
        btnAdd = (Button) findViewById(R.id.btn_add);
        //recive data

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            name = i.getExtras().getString("NameKey");
            des = i.getExtras().getString("DesKey");
            pos = i.getExtras().getInt("PosKey");
            detailTxt.setText(des);
            btnAdd.setVisibility(View.GONE);
            getBtndelete.setVisibility(View.VISIBLE);

        } else {
            getBtnSave.setVisibility(View.GONE);
            getBtndelete.setVisibility(View.GONE);
        }

        //Bind
        nameTxt.setText(name);
        crud = new CRUD(GetterSetterCollections.getSpacecrafts());


//
        getBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spacecraft s = new Spacecraft();
                name = nameTxt.getText().toString();
                des = detailTxt.getText().toString();

                s.setName(name);
                s.setDescription(des);
                if (crud.update(pos, s)) {

                    nameTxt.setText(name);
                    detailTxt.setText(des);

                    DetailActivity.this.finish();

                }

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passdata();
                DetailActivity.this.finish();
            }
        });
        getBtndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplication(),"asdf",Toast.LENGTH_SHORT).show();
                if (crud.delete(pos)) {
                    //kill this activity and go back to master activity
                    DetailActivity.this.finish();
                }
            }
        });

    }

    public void passdata() {

        Intent i = new Intent(DetailActivity.this, MainActivity.class);
        String date1 = nameTxt.getText().toString();
        String particular = detailTxt.getText().toString();
        i.putExtra("NAME1", date1);
        i.putExtra("LNAME1", particular);
        setResult(1, i);
        finish();
    }


}
