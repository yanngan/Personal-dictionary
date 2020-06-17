package com.ymg.personaldictionary.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ymg.personaldictionary.R;
import com.ymg.personaldictionary.logic.Logic;
import com.ymg.personaldictionary.logic.Word;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements Serializable {

    private HomeViewModel homeViewModel;
    public ArrayList<Word> rows;
    public RecyclerView RecyclerView;
    public String condition;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        RecyclerView = root.findViewById(R.id.RecyclerView);
        rows = new ArrayList<>();
        condition = "";
        /*homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        Logic.getAllRow(this);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Logic.getAllRow(this);
    }

    public void creatTheRecyclerView() {
        rowAdaptor theRowdAdaptor = new rowAdaptor(this,rows);
        RecyclerView.setAdapter(theRowdAdaptor);
        RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void refreshe(){
        Logic.getAllRow(this);
    }
}

