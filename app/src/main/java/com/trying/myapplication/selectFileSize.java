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

    public selectFileSize()  {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_file_size);
        Button btn1KB = (Button) findViewById(R.id.btn1KB);
        Button btn5KB = (Button) findViewById(R.id.btn5KB);
        Button btn10KB = (Button) findViewById(R.id.btn10KB);
        Button btn50KB = (Button) findViewById(R.id.btn50KB);
        Button btn100KB = (Button) findViewById(R.id.btn100KB);
        Button btn1MB = (Button) findViewById(R.id.btn1MB);


        Button btnEncrypt = (Button) findViewById(R.id.btnEncrypt);


        final TextView txt1 = (TextView) findViewById(R.id.txtView);
       // file = new File(Environment.getExternalStorageDirectory(), "_1KBfile.txt");

        btn1KB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text2 = ReadFile.readFile("_1KBfile.txt", selectFileSize.this);
                try {
                    String text3 = AES.encrypt(text2);
                    String text4 = AES.decrypt(text3);
                    txt1.setText(text4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn5KB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text2 = ReadFile.readFile("_5KBfile.txt", selectFileSize.this);
                try {
                    String text3 = AES.encrypt(text2);
                    String text4 = AES.decrypt(text3);
                    txt1.setText(text4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn10KB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text2 = ReadFile.readFile("_10KBfile.txt", selectFileSize.this);
                try {
                    String text3 = AES.encrypt(text2);
                    String text4 = AES.decrypt(text3);
                    txt1.setText(text4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String test123 = ReadFile.readFile("_10KBfile.txt",selectFileSize.this);
            }
        });

        btn50KB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text2 = ReadFile.readFile("_50KBfile.txt", selectFileSize.this);
                try {
                    String text3 = AES.encrypt(text2);
                    String text4 = AES.decrypt(text3);
                    txt1.setText(text4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String test123 = ReadFile.readFile("_50KBfile.txt",selectFileSize.this);
            }
        });


        btn100KB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text2 = ReadFile.readFile("_100KBfile.txt", selectFileSize.this);
                try {
                    String text3 = AES.encrypt(text2);
                    String text4 = AES.decrypt(text3);
                    txt1.setText(text4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String test123 = ReadFile.readFile("_100KBfile.txt",selectFileSize.this);
            }
        });

        btn1MB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text2 = ReadFile.readFile("_1MBfile.txt", selectFileSize.this);
                try {
                    String text3 = AES.encrypt(text2);
                    String text4 = AES.decrypt(text3);
                    txt1.setText(text4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String test123 = ReadFile.readFile("_1MBfile.txt",selectFileSize.this);
            }
        });



    }



    public void encryptFile(View view) {

            }



}