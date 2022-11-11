package com.masum.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button save, refresh;
    EditText name;// salary;

    private ListView listView;
    @Override
    protected void onCreate(Bundle readInstanceState) {
        super.onCreate(readInstanceState);
        setContentView(R.layout.activity_main);

        final DataBaseHelper helper = new DataBaseHelper(this);
        final ArrayList array_List = helper.getAllCotacts();

        name = findViewById(R.id.name);
        //salary =findViewById(R.id.salary);
        listView =findViewById(R.id.listView);

        final ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, array_List);

        listView.setAdapter(arrayAdapter);
        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                array_List.clear();
                array_List.addAll(helper.getAllCotacts());
                arrayAdapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.refreshDrawableState();
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               if(!name.getText().toString().isEmpty()){
                   if(helper.insert(name.getText().toString())){
                       Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                       name.setText("");
                   }
                   else{
                       Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_LONG).show();
                   }
               }
               else{
                   name.setError("Enter Name");
                   //salary.setError("Enter Salary");
               }
            }
        });
    }



}