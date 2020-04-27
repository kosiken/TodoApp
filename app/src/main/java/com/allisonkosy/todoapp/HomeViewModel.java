package com.allisonkosy.todoapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<TodoItem>> mutableLiveData = new MutableLiveData<>();
    private FileOutputStream stream;
    String url = "https://my-json-server.typicode.com/kosiken/lion-vigenere/posts";
    private RequestQueue requestQueue;
    public HomeViewModel() {

    }

    public LiveData<ArrayList<TodoItem>> getData() {

        return  mutableLiveData;
    }

public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
}

public LiveData<ArrayList<TodoItem>> getIt(){
    return  mutableLiveData;
}

public void setOut(FileOutputStream stream) {
        this.stream = stream;
}

public void setIt(ArrayList<TodoItem> todoItems) {
        mutableLiveData.setValue(todoItems);


        String json = "[";

    ArrayList<TodoItem> todoItems1 =  mutableLiveData.getValue();


    }
}