package com.example.a16046562.demofilereadwriting;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {
    String folderlocation,filelocation;
    File folder, targetfile;
    FileWriter writer;
    Button btnread, btnwrite;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

        folderlocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";

        folder = new File(folderlocation);
        if (folder.exists() == false){
            boolean result = folder.mkdir();
            if (result == true){
                Log.d("File Read/Write", "Folder created");
            }
        }

        btnwrite  = (Button) findViewById(R.id.btnWrite);
        btnwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for file writing
                try {
                    filelocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
                    targetfile = new File(folderlocation,"data.txt");

                    //true - append to existing data; false - overwrites existing data
                    writer = new FileWriter(targetfile, true);
                    writer.write("Hello World \n");
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,"Failed to write!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
        btnread  = (Button) findViewById(R.id.btnRead);
        btnread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for file reading
                filelocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
                targetfile = new File(folderlocation,"data.txt");

                if (targetfile.exists() == true) {
                    String data = "";
                    try {
                        FileReader reader = new FileReader(targetfile);
                        BufferedReader br = new BufferedReader(reader);

                        String line = br.readLine();
                        while (line != null){
                            data += line + "\n";
                            line = br.readLine();
                            tv.setText(data);
                        }
                        br.close();
                        reader.close();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this,"Failed to read!",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    Log.d("Content",data);
                }
            }
        });

    }
}
