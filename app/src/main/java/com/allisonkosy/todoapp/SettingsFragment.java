package com.allisonkosy.todoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.google.android.material.snackbar.Snackbar;

public class SettingsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
     //   setContentView(R.layout.fragment_settings);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragmentL())
                .commit();

        return root;
    }

    public static class SettingsFragmentL extends PreferenceFragmentCompat {
        ListPreference listPreference;
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

        @Override
        public boolean onPreferenceTreeClick(Preference preference){
         //   Snackbar.make(getView(),preference.getKey()+" feature is not yet available", Snackbar.LENGTH_LONG).show();
           // String sync = getString(R.string.pref_sync);
            switch (preference.getKey()){
                case "sync":

                    Snackbar.make(getView(),"This feature is not yet available", Snackbar.LENGTH_LONG).show();
                    break;

            }
            return true;
        }

        @Override
        public void onViewCreated(@NonNull View view,
                                 @Nullable Bundle savedInstanceState) {
//        loadIt();
            super.onViewCreated(view, savedInstanceState);
           listPreference = getPreferenceManager().findPreference("list_preference_1");
           listPreference.setOnPreferenceChangeListener((pref, newval)-> {
               String value = (String) newval;
               switch (value){
                   case "a":
                       getActivity().setTheme(R.style.AppTheme);
                       break;
                   case "b":
                       getActivity().setTheme(R.style.AppTheme_Blue);
                       break;
                   case "c":
                       getActivity().setTheme(R.style.AppTheme_Blue);
               }
               return true;
           });


        }
    }






}