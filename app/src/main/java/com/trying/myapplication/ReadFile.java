package com.trying.myapplication;
import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class ReadFile {

    private static final String TAG = "Reading File- ";

    static String readFile(String filepath, Context context){

        try {
            //AssetManager aManager = selectFileSize.this.getAssets();
            InputStream inputStream = context.getAssets().open(filepath);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            final StringBuilder stringBuilder = new StringBuilder();
            boolean done = false;
            while (!done) {
                final String line = reader.readLine();
                done = (line == null);

                if (line != null) {
                    stringBuilder.append(line);
                }
            }
            reader.close();
            inputStream.close();
            return  stringBuilder.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "readFile():" + e.getMessage() );
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "readFile(): " + e.getMessage() );
        }
        return null;
    }


}
