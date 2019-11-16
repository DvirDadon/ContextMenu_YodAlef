package com.example.contextmenu_yodalef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Information extends AppCompatActivity {

    /**
     * @author Dvir Dadon
     * @since 16/11/2019
     * @param Limb This parameter is the first limb of the series
     * @param Multi_diff This parameter is the number that multiply the series or show the differences
     * @param seriescheck This parameter is checking if the user choose geometric or arithmetic series
     */

    double Limb=0,Multi_diff=0;
    int seriescheck=0;
    String limb,Series,title;
    RadioButton Arithmetic;
    EditText first_num,Diff_multi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Arithmetic = findViewById(R.id.Arithmetic);
        first_num = findViewById(R.id.first_num);
        Diff_multi = findViewById(R.id.Diff_Multi);
    }

    /**
     * @Since 16/11/2019
     * The method checking what the series the user picked and the value he choose and moving him to the next activity
     */

    public void Info_Next(View view) {
        if (Arithmetic.isChecked()){
            seriescheck = 1;
        }
        else{
            seriescheck= -1;
        }
        limb=first_num.getText().toString();
        Series=Diff_multi.getText().toString();
        if (limb.equals("")||Series.equals("")||limb.equals("+")||Series.equals("+")||limb.equals("-")||Series.equals("-")||Series.equals(".")||limb.equals(".")){
            Toast.makeText(this, "Wrong input", Toast.LENGTH_SHORT).show();

        }
        else{
            Intent si = new Intent(this,Calculates.class);
            Limb=Double.parseDouble(limb);
            Multi_diff=Double.parseDouble(Series);
            si.putExtra("Check",seriescheck);
            si.putExtra("Limb",Limb);
            si.putExtra("Series",Multi_diff);
            startActivity(si);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"Credits");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        title=item.getTitle().toString();
        if (title.equals("Credits")){
            Intent si = new Intent(this,Credits.class);
            startActivity(si);

        }
        return true;
    }
}
