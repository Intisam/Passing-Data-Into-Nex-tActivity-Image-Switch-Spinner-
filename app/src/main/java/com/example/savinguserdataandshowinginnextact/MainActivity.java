package com.example.savinguserdataandshowinginnextact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    ImageView imageView2;
    Spinner spinner;
    Button continuebtn;
    Bitmap bitmap;
    EditText nameBox,phoneBox;
    String choice;
    Switch switch1;
    TextView abovetv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.gender);
        imageView2=findViewById(R.id.imageView2);
        continuebtn=findViewById(R.id.continuebtn);
        nameBox=findViewById(R.id.nameBox);
        phoneBox=findViewById(R.id.phoneBox);
        switch1=findViewById(R.id.switch1);


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Gender,R.layout.selected_item);
        adapter.setDropDownViewResource(R.layout.dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
               // Intent intent = new Intent();
               // intent.setAction(Intent.ACTION_GET_CONTENT);
              //  intent.setType("image/*");
               // startActivityForResult(intent, 101);
            }
        });

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch1.isChecked()){
                    switch1.setText("Above 18");
                }else{
                    switch1.setText("Below 18");
                }
            }
        });

        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bitmap != null){
                    ByteArrayOutputStream bytes=new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,bytes);
                    byte[] byteArray=bytes.toByteArray();
                    Intent intent=new Intent(MainActivity.this,DataActivity.class);
                    intent.putExtra("Image",byteArray);
                    intent.putExtra("name", nameBox.getText().toString());
                    intent.putExtra("phoneNumber", phoneBox.getText().toString());
                    intent.putExtra("gender",choice);
                    intent.putExtra("age",switch1.getText().toString());
                    startActivity(intent);
                }

            }
        });

    }




    private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }else {
            openCamera();
        }
    }


    private void openCamera() {
        Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choice= parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            bitmap=(Bitmap)data.getExtras().get("data");
            imageView2.setImageBitmap(bitmap);
        }
    }
}