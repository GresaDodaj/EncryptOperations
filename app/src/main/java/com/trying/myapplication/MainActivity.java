package com.trying.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Gresa";

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



        FirebaseFirestore database = FirebaseFirestore.getInstance();
        Map<String, Object> myPhone = new HashMap<>();
        myPhone.put("AES_1KB",5);
        myPhone.put("3DES_1KB", 6);
        myPhone.put("BLOWFISH_1KB", 7);
        database.collection("myPhone").document("_1KB").set(myPhone)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });


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
                    //Intent intent = new Intent(MainActivity.this, selectFileSize.class);

            }

        }
    };


}
