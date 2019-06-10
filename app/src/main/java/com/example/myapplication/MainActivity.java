package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TheAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button trans;
    private Button ex;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.result);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        trans = findViewById(R.id.trans);
        ex = findViewById(R.id.ex);
        editText = findViewById(R.id.editText);

        adapter = new TheAdapter();
        recyclerView.setAdapter(adapter);


        trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setDataSet(
                        translate(editText.getText().toString()));
                adapter.notifyDataSetChanged();

            }
        });

        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    String[] translate(String word){
        List<String> words = new ArrayList();
        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(getAssets().open("swahili_english.csv")))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(word)){
                    words.add(values[0]);
                }
            }

            String[] arr = new String[words.size()];
            for (int i = 0; i < words.size(); i++) {
                arr[i] = words.get(i);
            }

            return arr;

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new String[]{"No Words Translated"};

    }
}
