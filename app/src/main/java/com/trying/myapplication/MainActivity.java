package com.trying.myapplication;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout aesButton = findViewById(R.id.btnAES);
        LinearLayout desButton = findViewById(R.id.btnDES);
        LinearLayout _desButton = findViewById(R.id.btn3DES);
        LinearLayout blowfishButton = findViewById(R.id.btnBlowfish);
        LinearLayout showChart = findViewById(R.id.showChart);

        ImageButton closeButton = findViewById(R.id.btnInfo);
        final AlertDialog.Builder builder;

        aesButton.setOnClickListener(onClickListener);
        desButton.setOnClickListener(onClickListener);
        _desButton.setOnClickListener(onClickListener);
        blowfishButton.setOnClickListener(onClickListener);


        showChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Averages.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        builder = new AlertDialog.Builder(this);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setMessage("This app uses 4 file sizes to measure the performance of 4 Encryption Algorithms against time.")
                        .setCancelable(false)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Info");
                alert.show();
            }
        });

    }
     private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, EncryptionAlgorithm.class);

            switch(v.getId()){
                case R.id.btnAES:
                    intent.putExtra("ALG","AES");//to send a variable with the intent instance
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
                case R.id.btnDES:
                    intent.putExtra("ALG","DES");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
                case R.id.btn3DES:
                    intent.putExtra("ALG","3DES");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
                case R.id.btnBlowfish:
                    intent.putExtra("ALG","BLOWFISH");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
            }
        }
    };




}
