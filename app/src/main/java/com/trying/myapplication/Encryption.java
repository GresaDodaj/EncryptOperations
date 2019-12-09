package com.trying.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Encryption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);


        LinearLayout btnAES = findViewById(R.id.btnAES);
        final String _1KBfile = ReadFile.readFile("_1KBfile.txt", Encryption.this);
        final String _5KBfile = ReadFile.readFile("_5KBfile.txt", Encryption.this);
        final String _10KBfile = ReadFile.readFile("_10KBfile.txt", Encryption.this);
        final String _50KBfile = ReadFile.readFile("_500KBfile.txt", Encryption.this);
        final String _100KBfile = ReadFile.readFile("_100KBfile.txt", Encryption.this);
        final String _1MBfile = ReadFile.readFile("_1MBfile.txt", Encryption.this);


        btnAES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    long startTime = System.nanoTime();
                String text3 = null;
                try {
                    text3 = AES.encrypt(_1KBfile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //String text4 = AES.decrypt(text3);
                long endTime = System.nanoTime();
                //time of the execution in nanoseconds/1000000 = time in milliseconds
                timeLengthAES[0] = (endTime - startTime) / 1000000;
                txt1.setText(text3);
                txt2.setText((int) timeLengthAES[0] + " milliseconds");*/
            }
        });

    }
}
