package ch.zli.july;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

public class InfoPage extends AppCompatActivity {

    EditText name;
    EditText info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        name = findViewById(R.id.input_name);
        info = findViewById(R.id.input_info);

        String nameRestore = sh.getString("name", String.valueOf(0));
        String infoRestore = sh.getString("info", String.valueOf(0));

        if(nameRestore.isEmpty() && infoRestore.isEmpty()){
            name.setText("");
            info.setText("");
        } else if (nameRestore.isEmpty()) {
            name.setText("");
            info.setText(infoRestore);
        } else if (infoRestore.isEmpty()) {
            info.setText("");
            name.setText(nameRestore);
        } else {
            name.setText(nameRestore);
            info.setText(infoRestore);
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("name", name.getText().toString());
        savedInstanceState.putString("info", info.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        name.setText(savedInstanceState.getString("name"));
        info.setText(savedInstanceState.getString("info"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String weightRestore = sh.getString("name", String.valueOf(0));
        String heightRestore = sh.getString("info", String.valueOf(0));
        name.setText(weightRestore);
        info.setText(heightRestore);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putString("name", String.valueOf(name.getText()));
        myEdit.putString("info", String.valueOf(info.getText()));
        myEdit.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putString("name", String.valueOf(name.getText()));
        myEdit.putString("info", String.valueOf(info.getText()));
        myEdit.apply();
    }
}