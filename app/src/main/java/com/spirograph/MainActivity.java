package com.spirograph;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.spirograph.shapes.Line;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Button stopButton;
    Button resumeButton;
    Button restartButton;
    Button submitButton;
    LinearLayout dynamicEditTexts;
    SpiroGraphView spiroGraphView;

    EditTextCollection lengthsEditText = new EditTextCollection();
    EditTextCollection angleIncrementsEditTexts = new EditTextCollection();

    Boolean starClicked = Boolean.FALSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState   );
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("  " + "SpiroGraph");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_round);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        stopButton = findViewById(R.id.stopButton);
        resumeButton = findViewById(R.id.resumeButton);
        restartButton = findViewById(R.id.restartButton);
        submitButton = findViewById(R.id.submitButton);
        dynamicEditTexts = findViewById(R.id.dynamicEditTexts);

        linearLayout = findViewById(R.id.linearLayout);

        spiroGraphView = new SpiroGraphView(this);
        linearLayout.addView(spiroGraphView);

        final Spinner spinner = findViewById(R.id.spinner);
        String[] items = new String[]{"1", "2", "3", "4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                items
        );
        spinner.setAdapter(adapter);
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(
                new SpinnerOnItemSelectedListener(
                        spiroGraphView,
                        this,
                        dynamicEditTexts,
                        lengthsEditText,
                        angleIncrementsEditTexts
                )
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.invite_friend:  try {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"This message is from application under testing(AUT).Application is in the stage of maturity.PLEASE IGNORE \n"
                );
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS failed, please try again later!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
            case R.id.help:
                Toast.makeText(getApplicationContext(),"Under Development!!!",Toast.LENGTH_LONG).show();
                break;
            case R.id.star:
                Toast.makeText(getApplicationContext(),"Star clicked",Toast.LENGTH_LONG).show();
                starClicked = !starClicked;
                if (starClicked)
                item.setIcon(R.drawable.ic_star_full_white);
                else
                    item.setIcon(R.drawable.ic_star_white);

                List<Integer> lengths=lengthsEditText.getLengths();
                List<Integer> intAngleIncrements = angleIncrementsEditTexts.getLengths();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void submitButtonOnClick(View view) {
        try {
            List<Integer> lengths = lengthsEditText.getLengths();
            List<Integer> intAngleIncrements = angleIncrementsEditTexts.getLengths();
            List<Float> angleIncrements = new ArrayList<>();
            for (int i = 0; i < intAngleIncrements.size(); i++) {
                angleIncrements.add(intAngleIncrements.get(i) / 800.0f);
            }
            spiroGraphView.reset(lengths, angleIncrements);
        } catch (NumberFormatException ex) {
            Snackbar
                    .make(
                            linearLayout,
                            "Enter valid numbers",
                            Snackbar.LENGTH_LONG
                    ).show();
        }
    }

    public void stopButtonOnClick(View view) {
        spiroGraphView.stopButtonClicked();
    }

    public void resumeButtonOnClick(View view) {
        spiroGraphView.resumeButtonClicked();
    }

    public void restartButtonOnClick(View view) {
        try {
            List<Integer> lengths = lengthsEditText.getLengths();
            List<Integer> intAngleIncrements = angleIncrementsEditTexts.getLengths();
            List<Float> angleIncrements = new ArrayList<>();
            for (int i = 0; i < intAngleIncrements.size(); i++) {
                angleIncrements.add(intAngleIncrements.get(i) / 800.0f);
            }
            spiroGraphView.reset(lengths, angleIncrements);
        } catch (NumberFormatException ex) {
            spiroGraphView.reset(Line.getNumberOfLines());
        }
    }

}
