package com.allisonkosy.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

public class MyDeleteAdapter extends BaseAdapter {
    Context context;
    ArrayList<TodoItem> todoItems;
    AppCompatActivity activity;

    public MyDeleteAdapter(Context context, ArrayList<TodoItem> todoItems) {

        this.context = context;

        this.todoItems = todoItems;
    }


    @Override
    public int getCount() {
        return todoItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.activity_re, parent, false);
        final CheckBox checkbox = view.findViewById(R.id.checkbox3);

        final TodoItem todo = todoItems.get(position);
//        HomeViewModel viewModel = ViewModelProviders.of(getv=)
        checkbox.setChecked(todo.getDeleteIt());
        checkbox.setText(todo.getTitle());
        checkbox.setOnCheckedChangeListener((v, ch) -> {

            todo.toggleDeleteIt();

        });


        return view;
    }
}
