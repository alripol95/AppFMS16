package com.albertripol.fms16;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarActivitat extends AppCompatActivity {

    EditText nom;
    EditText pass;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_activitat);

        nom = (EditText) findViewById(R.id.txtDelAct);
        pass = (EditText) findViewById(R.id.txtDelContra);
        b = (Button) findViewById(R.id.btnDelAct);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean trobat=false;
                if (pass.getText().toString().equals("senterada")) {
                    if (nom.getText().toString().isEmpty()) Toast.makeText(EliminarActivitat.this,
                            "Introdueix un nom per a l'activitat", Toast.LENGTH_SHORT).show();
                    else {
                        for (int i = 0; i < Controlador.getInfo().size(); i++) {
                            Controlador.setAct(Controlador.getInfo().get(i));
                            if (nom.getText().toString().equals(Controlador.getAct().nom)) {
                                Toast.makeText(EliminarActivitat.this, "Activitat eliminada", Toast.LENGTH_SHORT).show();
                                Controlador.delActInfo(i);
                                trobat = true;
                                Intent intent = new Intent(EliminarActivitat.this, Menu.class);
                                startActivityForResult(intent, 0);
                            }
                        }
                        if (!trobat)
                            Toast.makeText(EliminarActivitat.this, "Activitat no trobada", Toast.LENGTH_SHORT).show();
                    }
                }
                else Toast.makeText(EliminarActivitat.this, "Contrassenya incorrecta :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
