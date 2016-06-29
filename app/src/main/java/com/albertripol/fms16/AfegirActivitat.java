package com.albertripol.fms16;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AfegirActivitat extends AppCompatActivity {

    Activitat act = new Activitat();
    EditText contra;
    EditText nom;
    EditText desc;
    EditText lloc;
    EditText dia;
    EditText hora;
    EditText minut;
    EditText durada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_activitat);

        contra = (EditText) findViewById(R.id.txtDelContra);
        nom = (EditText) findViewById(R.id.txtNom);
        desc = (EditText) findViewById(R.id.txtDesc);
        lloc = (EditText) findViewById(R.id.txtLloc);
        dia = (EditText) findViewById(R.id.txtDia);
        hora = (EditText) findViewById(R.id.txtHora);
        minut = (EditText) findViewById(R.id.txtMin);
        durada = (EditText) findViewById(R.id.txtDur);
        afegirAct();
    }

    private void afegirAct(){
        Button btn = (Button) findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totPle()) {
                    carregaAtributs();
                    if (contra.getText().toString().equals("senterada")) {
                        Controlador.setAct(act);
                        Controlador.setActInfo(act);
                        Toast.makeText(AfegirActivitat.this, "Activitat afegida correctament", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AfegirActivitat.this, Menu.class);
                        startActivityForResult(intent, 0);
                    }
                    else Toast.makeText(AfegirActivitat.this, "Contrassenya incorrecta :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void carregaAtributs(){
        act.nom = nom.getText().toString();
        act.desc = desc.getText().toString();
        act.lloc = lloc.getText().toString();
        act.day = Integer.valueOf(dia.getText().toString());
        act.h = Integer.valueOf(hora.getText().toString());
        act.min = Integer.valueOf(minut.getText().toString());
        act.dur = Integer.valueOf(durada.getText().toString());
        act.afegit = false;
    }
    private boolean totPle(){
        if(nom.getText().toString().isEmpty()){
            Toast.makeText(AfegirActivitat.this, "Introdueix un nom per a l'activitat", Toast.LENGTH_SHORT).show();
            return false;
        }
        for(int i=0; i<Controlador.info.size(); i++){
            Controlador.setAct(Controlador.getInfo().get(i));
            if(Controlador.getAct().nom.equals(nom.getText().toString())){
                Toast.makeText(AfegirActivitat.this, "Activitat ja existent", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if(lloc.getText().toString().isEmpty()){
            Toast.makeText(AfegirActivitat.this, "Introdueix un lloc per a l'activitat", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(dia.getText().toString().isEmpty() || (Integer.valueOf(dia.getText().toString())>30 || Integer.valueOf(dia.getText().toString()) <1)){
            Toast.makeText(AfegirActivitat.this, "Introdueix un dia vàlid per a l'activitat", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(hora.getText().toString().isEmpty() || (Integer.valueOf(hora.getText().toString())>23 || Integer.valueOf(hora.getText().toString()) <0)){
            Toast.makeText(AfegirActivitat.this, "Introdueix una hora vàlida per a l'activitat", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(minut.getText().toString().isEmpty() || (Integer.valueOf(minut.getText().toString())>59 || Integer.valueOf(minut.getText().toString()) <0)){
            Toast.makeText(AfegirActivitat.this, "Introdueix un minut vàlid per a l'activitat", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(durada.getText().toString().isEmpty() || (Integer.valueOf(durada.getText().toString())>24 || Integer.valueOf(durada.getText().toString()) <0)){
            Toast.makeText(AfegirActivitat.this, "Introdueix una durada vàlidaper a l'activitat", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
