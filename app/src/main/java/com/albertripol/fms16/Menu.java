package com.albertripol.fms16;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void mostraCartell(View view){
        Intent intent = new Intent(Menu.this, Cartell.class);
        startActivity(intent);
    }
    public void mostraAlertes(View view){
            Intent intent = new Intent(Menu.this, Alertes.class);
            startActivity(intent);
    }

}
