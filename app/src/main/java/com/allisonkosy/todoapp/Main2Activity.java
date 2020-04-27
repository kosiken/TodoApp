package com.allisonkosy.todoapp;

import android.app.job.JobScheduler;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.view.Menu;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private AppBarConfiguration mAppBarConfiguration;
    private HomeViewModel model;
//    private ArrayList To
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        requestQueue = Volley.newRequestQueue(this);

        model = ViewModelProviders.of(this)
                .get(HomeViewModel.class);
        model.setRequestQueue(requestQueue);
        try{
            FileInputStream inputStream = openFileInput("todos.json");
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String json = "", mes;
            while ((mes = bufferedReader.readLine())!= null){
                stringBuilder.append(mes);
            }
            json = stringBuilder.toString();

            JSONArray array = new JSONArray(json);

            ArrayList<TodoItem> todoItems = new ArrayList<>();


            for(int i =0; i< array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);
                todoItems.add(
                        new TodoItem(

                                jsonObject.getString("title"),
                                jsonObject.getString("description"),
                                jsonObject.getBoolean("completed")
                        )
                );
            }

            model.setIt(todoItems);



        }catch (Exception e) {
            e.printStackTrace();
            ArrayList<TodoItem> todoItems = new ArrayList<>();
            model.setIt(todoItems);

        }

        model.getData().observe(this, todos -> {

            try{
String json = "[";
                int n = 0;
                for (TodoItem todoItem: todos) {
                    json+= "{\"title\": \"" + todoItem.getTitle() + "\",";
                    json+= "\"description\":\"" + todoItem.getDescription() + "\",";

                    json+= "\"completed\":" + todoItem.getCompleted().toString() + "}";

                    if(n< todos.size()-1) {
                        json+=",";

                    }

                    n++;

                }
                json += "]";
//                Log.println;
                Log.println(Log.INFO, "MainStore", json);

                FileOutputStream outputStream = openFileOutput("todos.json", MODE_PRIVATE);
                outputStream.write(json.getBytes());
                outputStream.close();


            }
            catch (Exception e) {
                e.printStackTrace();
            }


        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
