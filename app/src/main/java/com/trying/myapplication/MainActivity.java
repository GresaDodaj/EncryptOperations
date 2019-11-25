package com.trying.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout aesButton = findViewById(R.id.btnAES);
        LinearLayout desButton = findViewById(R.id.btnDES);
        LinearLayout _3desButton = findViewById(R.id.btn3DES);
        LinearLayout blowfishButton = findViewById(R.id.btnBlowfish);

        aesButton.setOnClickListener(onClickListener);
        desButton.setOnClickListener(onClickListener);
        _3desButton.setOnClickListener(onClickListener);
        blowfishButton.setOnClickListener(onClickListener);


     /*   desButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {

                Intent intent = new Intent(MainActivity.this, selectFileSize.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });*/


    }
    //e kom deklaru qe me thirr te njejten kalse prej krejt butonave.. ne vend se me shkru des.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View view ) {....}
     private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, selectFileSize.class);
            switch(v.getId()){
                case R.id.btnAES:
                case R.id.btnDES:
                case R.id.btn3DES:
                case R.id.btnBlowfish:
                    //Intent intent = new Intent(MainActivity.this, selectFileSize.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;

            }

        }
    };


}
