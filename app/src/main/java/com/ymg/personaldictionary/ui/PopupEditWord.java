package com.ymg.personaldictionary.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ymg.personaldictionary.R;
import com.ymg.personaldictionary.logic.Logic;
import com.ymg.personaldictionary.logic.Word;
import com.ymg.personaldictionary.ui.home.HomeFragment;

public class PopupEditWord extends AppCompatActivity {
    int id;

    public EditText textViewSourceInput;
    public EditText textViewMeaningInput;
    public EditText editTextDescription;
    public EditText textViewScore;
    public Button buttonSub;
    public Button buttonRaise;
    public Button buttonSave;
    public Button buttonDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popup_edit_word);
        getSupportActionBar().hide();

        id = Integer.parseInt(getIntent().getStringExtra("idRow"));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = (int)(dm.widthPixels*0.8);
        int height = (int)(dm.heightPixels*0.8);

        textViewSourceInput = (EditText) findViewById(R.id.textViewSourceInput);
        textViewMeaningInput = (EditText) findViewById(R.id.textViewMeaningInput);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        textViewScore = (EditText) findViewById(R.id.textViewScore);
        buttonSub = (Button) findViewById(R.id.buttonSub);
        buttonRaise = (Button) findViewById(R.id.buttonRaise);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        getWindow().setLayout(width,height);

        Word theWord = Logic.getOneRowByID(this,id);


        textViewSourceInput.setText(theWord.source);
        textViewMeaningInput.setText(theWord.meaning);
        editTextDescription.setText(theWord.description);
        textViewScore.setText(theWord.score);

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
                    Toast.makeText(PopupEditWord.this,"can't be negative",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(PopupEditWord.this,"can't be more than 10",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(PopupEditWord.this, "the new word and the meaning can't by empty", Toast.LENGTH_LONG).show();
                } else {
                    String source = textViewSourceInput.getText().toString();
                    String meaning = textViewMeaningInput.getText().toString();
                    String score;
                    try {
                        score = textViewScore.getText().toString();
                    } catch (Exception e) {
                        score = "" + 0;
                    }
                    String description;
                    try {
                        description = editTextDescription.getText().toString();
                    } catch (Exception e) {
                        description = "";
                    }
                    //(Context context,String id,String sourch,String meaning,String score,String description )
                    if (Logic.editWord(PopupEditWord.this, PopupEditWord.this.id + "", source, meaning, score, description)) {
                        textViewSourceInput.setText("");
                        textViewMeaningInput.setText("");
                        textViewScore.setText("0");
                        editTextDescription.setText("");
                        Toast.makeText(PopupEditWord.this, "successes",Toast.LENGTH_LONG).show();
                        PopupEditWord.this.finish();

                    } else {
                        Toast.makeText(PopupEditWord.this, "insert new word fail, try again",
                                Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


    }
}
