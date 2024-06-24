package com.example.sharedprefrencesapplicationlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextMajor, editTextStudentID;
    private TextView txvName, txvMajor, txvID;

    private Switch pageColorSwitch;

    private LinearLayout pageLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextMajor = findViewById(R.id.editTextMajor);
        editTextStudentID = findViewById(R.id.editTextStudentID);

        txvName = findViewById(R.id.txvName);
        txvMajor = findViewById(R.id.txvMajor);
        txvID = findViewById(R.id.txvID);

        pageLayout = findViewById(R.id.pageLayout);

        pageColorSwitch = findViewById(R.id.pageColorSwitch);
        pageColorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setPageColor(isChecked);
            }
        });

        //load data from shared prefrences
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean("yellow", false);
        pageColorSwitch.setChecked(isChecked);

    }
    private void setPageColor(boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.apply();

        pageLayout.setBackgroundColor(isChecked? Color.YELLOW: Color.WHITE);
    }
    public void saveData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("my_pref_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // save data as key value pairs
        editor.putString("Name", editTextName.getText().toString());
        editor.putString("Major", editTextMajor.getText().toString());
        editor.putString("ID", editTextStudentID.getText().toString());

        editor.apply(); // asyncronous

    }

    public void  loadData(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("my_pref_file", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", "Name is not available!");
        String major = sharedPreferences.getString("Major", "Major is not available!");
        String id = sharedPreferences.getString("ID", "ID is not available!");

        txvName.setText(name);
        txvMajor.setText(major);
        txvID.setText(id);
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}