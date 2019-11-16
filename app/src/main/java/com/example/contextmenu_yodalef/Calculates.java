package com.example.contextmenu_yodalef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Calculates extends AppCompatActivity implements View.OnCreateContextMenuListener,AdapterView.OnItemLongClickListener {

    Intent gi;
    int check,location;
    double a1, d;
    Double[] Series = new Double[20];
    ListView Lv;
    TextView Tv;
    String oper,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculates);
        Lv = findViewById(R.id.Lv);
        Tv = findViewById(R.id.Tv);
        Lv.setOnItemLongClickListener(this);
        gi = getIntent();
        a1 = gi.getDoubleExtra("Limb", -2038549037.0);
        d = gi.getDoubleExtra("Series", -9218901287.0);
        check = gi.getIntExtra("Check", 91293912);
        if (check == 1) {
            for (int i = 0; i < Series.length; i++) {
                Series[i] = a1 + ((i+1) - 1) * d;
            }
        } else {

            for (int i = 0; i < Series.length; i++) {
                Series[i] = a1 * Math.pow(d, (i+1) - 1);
            }
        }
        ArrayAdapter<Double> adp = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,Series);
        Lv.setOnCreateContextMenuListener(this);
        Lv.setAdapter(adp);

    }

    /**
     * @since 11/16/2019
     *The method takes you to the last activity
     */

    public void Back_Info(View view) {
        finish();
    }

    /**
     * @since 16/11/2019
     *This method create the context menu
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Options");
        menu.add("location");
        menu.add("The sum of the values between the first till where you choose");
    }

    /**
     *
     * @since 16/11/2019
     * @return The method returns either the location of the selected number in the series or the sum
     */

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        double sum=0.0;
        oper = item.getTitle().toString();
        if (oper.equals("location")){
            Tv.setText(location+1+"");
        }
        else{
            for (int k=0;k<=location;k++){
                sum=Series[k]+sum;
            }
            Tv.setText("The sum is:"+" "+sum);
        }

        return true;
    }

    /**
     * @return The method returns the position of the current selected number in the series
     */

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        location=position;
        return false;
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
