package com.allisonkosy.todoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import com.allisonkosy.todoapp.R;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ConnectDesktopFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.connect_desktop2_fragment, container, false);
        return root;

    }








}
