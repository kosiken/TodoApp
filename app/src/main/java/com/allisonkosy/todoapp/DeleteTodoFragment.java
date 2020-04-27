package com.allisonkosy.todoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DeleteTodoFragment extends Fragment {

    private ArrayList<TodoItem> todoItems;
private MyDeleteAdapter deleteAdapter;
private ListView listView;


private HomeViewModel model;
private boolean allSel = true;

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);

        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_delete, container, false);

        listView = root.findViewById(R.id.list_view4);

        return root;


    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view,savedInstanceState);
        model = ViewModelProviders.of(getActivity())
                .get(HomeViewModel.class);


        model.getData().observe(this, s -> {
                    todoItems = s;

                    deleteAdapter = new MyDeleteAdapter(getContext(), todoItems);
                    listView.setAdapter(deleteAdapter);
                    // Toast.makeText(getContext(), todoItems.get(0).getTitle(), Toast.LENGTH_LONG).show();
                }
        );

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.del_menu, menu);
        return;
    }
@Override
public void onDetach() {
        super.onDetach();
        flushTodoItems();
}

@Override
public void onPause() {
        super.onPause();
        flushTodoItems();
}

@Override
public void onDestroy() {
        super.onDestroy();
        flushTodoItems();
}

    private void flushTodoItems() {
        for(TodoItem todoItem: todoItems) {
            todoItem.setDeleteIt(false);
        }
    }


    private boolean checkTodos() {
        int n = 0;

        for(TodoItem todoItem: todoItems) {
            if(todoItem.getDeleteIt()) {
                n++;
            }
        }

        if(n>0) {
            return true;
        }
        return false;
    }


    private void deleteTodos(){
        ArrayList<TodoItem> todoItems1 = new ArrayList<>();
        for(TodoItem todoItem: todoItems) {
            if(!todoItem.getDeleteIt()){
                todoItems1.add(todoItem);
            }
        }

        model.setIt(todoItems1);
    }

    private void warnTodos() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete selected todos");
        builder.setMessage("Are you sure you want to delete the todos");
        builder.setPositiveButton("Delete todos", (arg0,arg1)->{
            deleteTodos();

        });
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.action_delete_todos:
                    if(checkTodos()){
                        warnTodos();
                    }
                    break;
            case R.id.action_select_all:
                allSel=!allSel;
                menuItem.setChecked(allSel);
                selectAllTodos();
                break;
            default:
                requireActivity().onBackPressed();
                break;

        }
        return true;
    }

    private void selectAllTodos(){

        for(TodoItem todoItem: todoItems){
            todoItem.setDeleteIt(allSel);
        }
        model.setIt(todoItems);
    }
    }
