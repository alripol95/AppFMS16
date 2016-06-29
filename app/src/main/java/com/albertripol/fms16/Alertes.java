package com.albertripol.fms16;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Alertes extends Activity {

    private Activitat act;
    private ArrayList<Activitat> info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertes);
        info = Controlador.getInfo();
        if(info.size()==0) Toast.makeText(Alertes.this, "No hi ha activitats", Toast.LENGTH_LONG).show();
        else registrarEventos();
    }

    private void addEventToCalendar(String a){
        boolean noTrobat = true;
        for(int i=0; i<info.size() && noTrobat; i++){
            act = info.get(i);
            if(act.nom.equals(a))noTrobat=false;
        }

        if(act.afegit) confirmDialog();
        if(!act.afegit) {
            act.afegit=true;
            Calendar cal = Calendar.getInstance();

            cal.set(Calendar.DAY_OF_MONTH, act.day);
            cal.set(Calendar.MONTH, 7);
            cal.set(Calendar.YEAR, 2016);

            cal.set(Calendar.HOUR_OF_DAY, act.h);
            cal.set(Calendar.MINUTE, act.min);

            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");

            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis());
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.getTimeInMillis() + act.dur * 3600000);

            intent.putExtra(CalendarContract.Events.ALL_DAY, false);
            intent.putExtra(CalendarContract.Events.TITLE, act.nom);
            intent.putExtra(CalendarContract.Events.DESCRIPTION, act.desc);
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, act.lloc);

            startActivity(intent);
        }
    }

    private void confirmDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        act.afegit=false;
                        Toast.makeText(Alertes.this, "Torna a clicar sobre l'activitat per afegir-la", Toast.LENGTH_LONG).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ja has afegit aquesta activitat al calendari. Vols tornar-la a afegir?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show();
    }

    private void registrarEventos(){

        ArrayList<String> noms = new ArrayList<>();
        for(int i=0; i<info.size(); i++){noms.add(info.get(i).nom);}
        ArrayAdapter<String> adaptador;
        ListView llista = (ListView) findViewById(R.id.actsList);
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, noms);
        llista.setAdapter(adaptador);

        llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSeleccionado = adapterView.getItemAtPosition(i).toString();
                addEventToCalendar(itemSeleccionado);
            }
        });
    }
}
