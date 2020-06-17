package com.ymg.personaldictionary.ui.addWord;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ymg.personaldictionary.DB.DBhelper;
import com.ymg.personaldictionary.R;
import com.ymg.personaldictionary.logic.Logic;

public class addWordFragment extends Fragment {

    private addWordViewModel addWordViewModel;
    DBhelper myDB;
    public EditText textViewSourceInput;
    public EditText textViewMeaningInput;
    public EditText editTextDescription;
    public EditText textViewScore;
    public Button buttonSub;
    public Button buttonRaise;
    public Button buttonSave;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Log.d("addWordFragment" ,"in onCreateView");
        addWordViewModel =
                ViewModelProviders.of(this).get(addWordViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_word, container, false);
        textViewSourceInput = (EditText) root.findViewById(R.id.textViewSourceInput);
        textViewMeaningInput = (EditText) root.findViewById(R.id.textViewMeaningInput);
        editTextDescription = (EditText) root.findViewById(R.id.editTextDescription);
        textViewScore = (EditText) root.findViewById(R.id.textViewScore);
        buttonSub = (Button) root.findViewById(R.id.buttonSub);
        buttonRaise = (Button) root.findViewById(R.id.buttonRaise);
        buttonSave = (Button) root.findViewById(R.id.buttonSave);

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theScore;
                try {
                    theScore = Integer.parseInt(textViewScore.getText().toString());
                }
                catch (Exception e){
                    theScore = 0;
                }
                if(theScore==0){
                    Toast.makeText(getActivity(),"can't be negative",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    theScore--;
                    textViewScore.setText(""+theScore);
                }
            }
        });

        buttonRaise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theScore;
                try {
                    theScore = Integer.parseInt(textViewScore.getText().toString());
                }
                catch (Exception e){
                    theScore = 0;
                }
                if(theScore==10){
                    Toast.makeText(getActivity(),"can't be more than 10",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    theScore++;
                    textViewScore.setText(""+theScore);
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textViewSourceInput.getText() == null || textViewSourceInput.getText().toString().equals("")
                    || textViewMeaningInput.getText() == null || textViewMeaningInput.getText().toString().equals("")) {
                    Toast.makeText(getActivity(),"the new word and the meaning can't by empty",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    String source = textViewSourceInput.getText().toString();
                    String meaning = textViewMeaningInput.getText().toString();
                    String score;
                    try {
                        score = textViewScore.getText().toString();
                    }
                    catch (Exception e){
                        score = ""+0;
                    }
                    String description;
                    try {
                        description = editTextDescription.getText().toString();
                    }
                    catch (Exception e){
                        description="";
                    }

                    String res = Logic.insertNewWord(getActivity(),source,meaning,Integer.parseInt(score),description);
                    if(res.equals("successes")){
                        textViewSourceInput.setText("");
                        textViewMeaningInput.setText("");
                        textViewScore.setText("0");
                        editTextDescription.setText("");
                        Toast.makeText(getActivity(),"successes",
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getActivity(),"insert new word fail, try again",
                                Toast.LENGTH_LONG).show();
                    }

                }
            }
        });



        return root;
    }
}