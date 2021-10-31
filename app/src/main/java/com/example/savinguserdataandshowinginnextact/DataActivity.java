package com.example.savinguserdataandshowinginnextact;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.inputmethod.InputMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {


    public static final String NAME="NAME";
    public static final String PHONE="PHONE";

    ImageView imageView3;
    TextView nametv,phonetv,gendertv,agetv;
    private String name;
    private int phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        imageView3=findViewById(R.id.imageView3);
        nametv=findViewById(R.id.nametv);
        phonetv=findViewById(R.id.phonetv);
        gendertv=findViewById(R.id.gendertext);
        agetv=findViewById(R.id.agetv);
        Bundle ex=getIntent().getExtras();
        byte[] byteArray=ex.getByteArray("Image");
        Bitmap bmp= BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        imageView3.setImageBitmap(bmp);

        String name = getIntent().getStringExtra("name");
        nametv.setText("NAME: " +name);
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        phonetv.setText("PHONE: " +phoneNumber);
        String gender = getIntent().getStringExtra("gender");
        gendertv.setText("GENDER: " +gender);
        String age = getIntent().getStringExtra("age");
        agetv.setText("Age: " +age);


    }
}