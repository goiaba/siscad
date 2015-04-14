/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 * @author Joaquim
 */
public class SiscadMob extends Activity {

    Button bttBuscar;
    TextView txtRaAluno;
    EditText raAluno;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siscadmob);

        txtRaAluno = (TextView) findViewById(R.id.txtRaAluno);
        txtRaAluno.setTextColor(Color.BLACK);
        raAluno = (EditText) findViewById(R.id.raAluno);
        bttBuscar = (Button) findViewById(R.id.bttBuscar);

        bttBuscar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (raAluno.length() != 0) {
                    Intent tela = new Intent(SiscadMob.this, SiscadMobResponse.class);
                    tela.putExtra("raAluno", raAluno.getText().toString());
                    SiscadMob.this.startActivity(tela);
                    SiscadMob.this.finish();
                    startActivity(tela);
                }
//                else {
//                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//                    dialog.setMessage("Informe o RA do aluno.");
//                    dialog.setNeutralButton("OK", null);
//                    dialog.setTitle("Aviso");
//                    dialog.show();
//                }

            }
        });

    }
}
