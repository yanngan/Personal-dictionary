package com.ymg.personaldictionary.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ymg.personaldictionary.R;
import com.ymg.personaldictionary.logic.Logic;
import com.ymg.personaldictionary.logic.Word;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ymg.personaldictionary.ui.PopupEditWord;

public class rowAdaptor extends RecyclerView.Adapter<rowAdaptor.ViewHolder> {
    private static final String TAG = "rowAdaptor";
    private ArrayList<Word> rows = new ArrayList<>();
    private Context myContext;

    public rowAdaptor(HomeFragment theHomeFragment, ArrayList<Word> rows){
        this.rows = rows;
        this.myContext = theHomeFragment.getContext();
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home,parent,false);
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        //Log.d("in onBindViewHolder",rows.get(i).source);
        viewHolder.textViewTop.setText(rows.get(i).source);
        viewHolder.textViewdown.setText(rows.get(i).meaning);
        viewHolder.textViewdescription.setText(rows.get(i).description);
        viewHolder.textViewScore.setText(rows.get(i).score);
        viewHolder.imageButtonRiseScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newScore = Integer.parseInt(viewHolder.textViewScore.getText().toString());
                newScore++;
                if(newScore==11){
                    Toast.makeText(myContext.getApplicationContext(),"can't be more then 10..",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!Logic.changeScore(viewHolder.itemView.getContext(),newScore,rows.get(i).id)){
                    Toast.makeText(myContext.getApplicationContext(),"change score fail",Toast.LENGTH_LONG).show();
                    return;
                }
                viewHolder.textViewScore.setText(newScore+"");
            }
        });
        viewHolder.imageButtonLowerScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newScore = Integer.parseInt(viewHolder.textViewScore.getText().toString());
                newScore--;
                if(newScore==-1){
                    Toast.makeText(myContext.getApplicationContext(),"can't be less then 0..",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!Logic.changeScore(viewHolder.itemView.getContext(),newScore,rows.get(i).id)){
                    Toast.makeText(myContext.getApplicationContext(),"change score fail",Toast.LENGTH_LONG).show();
                    return;
                }
                viewHolder.textViewScore.setText(newScore+"");
            }
        });
        viewHolder.imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//how create an popup https://www.youtube.com/watch?v=fn5OlqQuOCk
                Intent intent = new Intent(myContext, PopupEditWord.class);
                intent.putExtra("idRow", rows.get(i).id);
                myContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTop;
        TextView textViewdown;
        TextView textViewdescription;
        TextView textViewScore;
        ImageButton imageButtonRiseScore;
        ImageButton imageButtonLowerScore;
        ImageButton imageButtonEdit;
        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTop = itemView.findViewById(R.id.textViewTop);
            textViewdown = itemView.findViewById(R.id.textViewdown);
            textViewdescription = itemView.findViewById(R.id.textViewdescription);
            textViewScore = itemView.findViewById(R.id.textViewScore);
            imageButtonRiseScore = itemView.findViewById(R.id.imageButtonRiseScore);
            imageButtonLowerScore = itemView.findViewById(R.id.imageButtonLowerScore);
            imageButtonEdit = itemView.findViewById(R.id.imageButtonEdit);
            this.itemView = itemView;
        }
    }


}
