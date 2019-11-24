package com.trying.myapplication;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class selectFileSize extends AppCompatActivity {



    public selectFileSize() throws Exception {
    }
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_file_size);
        Button btn1 = (Button) findViewById(R.id.btn1KB);
        final TextView txt1 = (TextView) findViewById(R.id.txtView);
        file = new File(Environment.getExternalStorageDirectory(), "_7KBfile.txt");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String test1 = "GRESA";
                String text2 = ReadFile.readFile("_7KBfile.txt", selectFileSize.this);
                try {
                    String text3 = AES.encrypt(text2);
                    String text4 = AES.decrypt(text3);
                    txt1.setText(text4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
               // txt1.setText(ReadFile.readFile("_7KBfile.txt", selectFileSize.this));
                String test123 = ReadFile.readFile("_7KBfile.txt",selectFileSize.this);
                Log.d("CREATION", ReadFile.readFile("_7KBfile.txt",selectFileSize.this));
            }
        });

    }


    public void encryptFile(View view) {

            }



}