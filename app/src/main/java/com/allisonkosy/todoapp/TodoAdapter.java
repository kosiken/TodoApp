package com.allisonkosy.todoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;


import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

public class TodoAdapter extends BaseAdapter {
    Context context;
    ArrayList<TodoItem> todoItems;

    public TodoAdapter(Context context, ArrayList<TodoItem> todoItems) {

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
    public View getView(final int position, View view, ViewGroup parent){
        view = LayoutInflater.from(context).inflate(R.layout.todo_list, parent, false);

        TextView title = view.findViewById(R.id.textView2);
        final CheckBox checkbox = view.findViewById(R.id.checkbox);
        final TodoItem todo = todoItems.get(position);

        title.setText(todo.getTitle());
        checkbox.setChecked(todo.getCompleted());


        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                todo.toggleCompleted();

                checkbox.setChecked(todo.getCompleted());



                Toast.makeText(context, todo.getTitle() + " clicked", Toast.LENGTH_SHORT).show();

            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Bundle bundle = new Bundle();

                bundle.putInt("index", position);


                NavController navController = Navigation.findNavController(v);

                navController.navigate(R.id.action_homeFragment_to_todoEditFragment, bundle);

                return false;
            }
        });
        checkbox.setOnCheckedChangeListener((v, ch) -> {

            todo.toggleCompleted();

        });
        return view;

    }
}
