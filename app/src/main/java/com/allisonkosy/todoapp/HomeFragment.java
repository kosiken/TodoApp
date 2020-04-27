package com.allisonkosy.todoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ListView listView;
    TodoAdapter todoAdapter;
    private ArrayList<TodoItem> todoItems;


    private HomeViewModel model;

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);

        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        listView = root.findViewById(R.id.list_view);




        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToEditFragment("New Todo", "A new Todo", view);

            }
        });

//
//    view = root;
        return root;

    }

    public void navigateToEditFragment(String title, String description, View view) {

        Bundle bundle = new Bundle();

        bundle.putString("title", title);
        bundle.putString("description", description);

        bundle.putInt("index", -1);

       NavController navController = Navigation.findNavController(view );

        navController.navigate(R.id.action_homeFragment_to_todoEditFragment, bundle);


    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
//        loadIt();
        super.onViewCreated(view, savedInstanceState);

//        loadIt();
       // Toast.makeText(getContext(), "lola", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getContext(), "lolao", Toast.LENGTH_LONG).show();


        model = ViewModelProviders.of(getActivity())
                .get(HomeViewModel.class);


        model.getData().observe(this, s -> {
                todoItems = s;

                todoAdapter = new TodoAdapter(getContext(), todoItems);
                listView.setAdapter(todoAdapter);
               // Toast.makeText(getContext(), todoItems.get(0).getTitle(), Toast.LENGTH_LONG).show();
            }
        );


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu);
        return;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        Log.println(Log.INFO, "option",Integer.toString(menuItem.getItemId()));
        switch (menuItem.getItemId()){
            case R.id.action_delete_todo:
                NavController navController = Navigation.findNavController(getView());
                navController.navigate(R.id.action_homeFragment_to_deleteTodoFragment);
                break;


             default:
                DrawerLayout drawer = getActivity().findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                 break;

        }
        return true;
    }


}
