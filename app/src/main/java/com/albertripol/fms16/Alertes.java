package com.albertripol.fms16;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Alertes extends Activity {

    private boolean primer = true;
    private ArrayList<Activitat> info = new ArrayList<>();
    private Activitat act;
    private boolean confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertes);

        int n=4;
        if(primer){
            for(int i=0; i<n; i++){
                act = new Activitat();
                act.afegit=false;
                act.day=12;
                act.desc="descripcio";
                act.dur=2;
                act.h=15;
                act.lloc="lloc";
                act.min=0;
                act.titol="act"+i;
                info.add(act);
            }
            primer = false;
        }
        registrarEventos();
    }

    private void addEventToCalendar(String a){
        boolean noTrobat = true;
        for(int i=0; i<info.size() && noTrobat; i++){
            act = info.get(i);
            if(act.titol.equals(a))noTrobat=false;
        }
        if(act.afegit) confirmDialog();
        if(confirm) {
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
            intent.putExtra(CalendarContract.Events.RRULE, "FREQ=DAILY");
            intent.putExtra(CalendarContract.Events.TITLE, act.titol);
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
                        confirm = true;
                        Toast.makeText(Alertes.this, "Yes Clicked", Toast.LENGTH_LONG).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        confirm = false;
                        Toast.makeText(Alertes.this, "No Clicked", Toast.LENGTH_LONG).show();
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

        /// selecciona la lista en pantalla segun su ID
        ListView lista1 = (ListView) findViewById(R.id.actsList);

        // registra una accion para el evento click
        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /// Obtiene el valor de la casilla elegida
                String itemSeleccionado = adapterView.getItemAtPosition(i).toString();
                addEventToCalendar(itemSeleccionado);
            }
        });

    }
}
