package com.thisisabir.customfont;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void exportdata(View view) {

        //Generate csv data
        StringBuilder stringBuilder = new StringBuilder();
        // below LINE add you column name using comma seperation
        stringBuilder.append("Time,Distance");
        for (int i = 0; i < 5; i++)
        {
            // below line add your data using comma speraton accorning to column
            stringBuilder.append("\n"+String.valueOf(i)+","+String.valueOf(i*2));
        }

        try {

            // saving the file into device
            FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write(stringBuilder.toString().getBytes());
            out.close();

            // export the file into device
            Context context = getApplicationContext();
            File filelocation = new File(getFilesDir(),"data.csv");
            Uri path = FileProvider.getUriForFile(context,"com.thisisabir.customfont.fileprovider", filelocation);
            Intent fileintent = new Intent(Intent.ACTION_SEND);
            fileintent.setType("text/csv");
            fileintent.putExtra(Intent.EXTRA_SUBJECT, "Data");
            fileintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileintent.putExtra(Intent.EXTRA_STREAM,path);
            startActivity(Intent.createChooser(fileintent,"Send Mail"));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
