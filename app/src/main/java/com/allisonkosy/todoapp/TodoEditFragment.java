package com.allisonkosy.todoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.navigation.NavController;

import androidx.navigation.Navigation;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

public class TodoEditFragment extends Fragment implements View.OnClickListener{
    EditText title;
    EditText description;
   private ArrayList<TodoItem> todoItems;
//   InputMethodManager
      private TodoItem todoItem;
    Button button;
    private Boolean isNew = false;

    private HomeViewModel model;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_todo_edit, container, false);

        title = root.findViewById(R.id.editText);

        description = root.findViewById(R.id.editText2);
        button = root.findViewById(R.id.button2);

        button.setOnClickListener(this);

return root;




    }

    public void onViewCreated(@NonNull View root, Bundle savedInstanceState) {


        super.onViewCreated(root, savedInstanceState);
        model = ViewModelProviders.of(getActivity())
                .get(HomeViewModel.class);

        Bundle args = getArguments();
        int position = args.getInt("index");
        model.getIt().observe(this, s -> {
            todoItems = s;


            if (position == -1) {
                String todoTitle = args.getString("title");
                String descriptione = args.getString("description");
            todoItem = new TodoItem(todoTitle, descriptione);

                title.setText(todoItem.getTitle());
                title = root.findViewById(R.id.editText);
                title.setText(todoItem.getTitle());
                description.setText(todoItem.getDescription());
                isNew = true;
            } else {
                //todoItem;

             todoItem = todoItems.get(position);
                title = root.findViewById(R.id.editText);
                title.setText(todoItem.getTitle());
                title = root.findViewById(R.id.editText);
                title.setText(todoItem.getTitle());
                description.setText(todoItem.getDescription());


            }

        });

    }

    @Override
    public void onClick(View view){
        if(isNew) {
            String todoTitle = title.getText().toString();
            String descriptione = description.getText().toString();
            TodoItem todoItem = new TodoItem(todoTitle, descriptione);
            todoItems.add(todoItem);





        }

         else
        {
              todoItem.setTitle(title.getText().toString());
              todoItem.setDescription(description.getText().toString());
        }
                     NavController navController = Navigation.findNavController(view );
            InputMethodManager imm = (InputMethodManager)getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(description.getWindowToken(), 0);
          model.setIt(todoItems);
          isNew = false;

          navController.navigate(R.id.action_todoEditFragment_to_homeFragment);
    }
}
