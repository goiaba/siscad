package br.com.r2jb.siscad.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class SiscadMobResponse extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);
        layout.setScrollContainer(true);
        layout.setScrollbarFadingEnabled(true);
        layout.setBackgroundColor(Color.WHITE);

        Button bttVoltar = new Button(this);
        bttVoltar.setText("Voltar");
        bttVoltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent tela = new Intent(SiscadMobResponse.this, SiscadMob.class);
                SiscadMobResponse.this.startActivity(tela);
                SiscadMobResponse.this.finish();
                startActivity(tela);
            }
        });

        TextView cabecalho;
        TextView raTXT[];
        TextView alunoTXT[];
        TextView turmaTXT[];
        TextView disciplinaTXT[];
        TextView notaTXT[];
        TextView semNota = null;

        Bundle tela = getIntent().getExtras();
        int ra = Integer.parseInt(tela.getString("raAluno"));


        try {

            SoapObject request = new SoapObject("http://ws.siscad.r2jb.com.br/", "notasPorAluno");

            SoapObject property = request.addProperty("raAluno", ra);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(property);
            HttpTransportSE androidHttpTransport = new HttpTransportSE("http://200.133.238.132:8084/SiscadWeb/Consulta?xsd=1");
            androidHttpTransport.call("http://ws.siscad.r2jb.com.br/notasPorAluno", envelope);
            SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;

            StringBuilder recorteNotas = new StringBuilder(resultsRequestSOAP.toString());

            if (recorteNotas.toString().equals("notasPorAlunoResponse{return=anyType{}; }")) {

                semNota = new TextView(this);
                semNota.setText("O RA informado não possui notas cadastradas!!!");
                semNota.setTextColor(Color.RED);
                semNota.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
                layout.addView(semNota);

            } else {

                String deleteIni = "notasPorAlunoResponse{return=";
                recorteNotas.delete(0, deleteIni.length());
                recorteNotas.setLength(recorteNotas.length() - 4);

                String notas = recorteNotas.toString();

                String[] notasResult = notas.split(";");

                cabecalho = new TextView(this);
                raTXT = new TextView[notasResult.length];
                alunoTXT = new TextView[notasResult.length];
                turmaTXT = new TextView[notasResult.length];
                disciplinaTXT = new TextView[notasResult.length];
                notaTXT = new TextView[notasResult.length];
                String discNome = null;

                cabecalho.setText("Consulta de notas");
                cabecalho.setTextColor(Color.BLUE);
                cabecalho.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);

                layout.addView(cabecalho);

                for (int i = 0; i < notasResult.length; i++) {

                    String[] notaResult = notasResult[i].toString().split(",");

                    for (int n = 0; n < notaResult.length; n++) {

                        if (n == 0 && i == 0) {

                            raTXT[n] = new TextView(this);
                            raTXT[n].setText("RA = " + notaResult[n].toString());
                            raTXT[0].setTextColor(Color.BLACK);

                            layout.addView(raTXT[n]);

                        }

                        if (n == 1 && i == 0) {

                            alunoTXT[n] = new TextView(this);
                            alunoTXT[n].setText("Aluno = " + notaResult[n].toString());
                            alunoTXT[n].setTextColor(Color.BLACK);

                            layout.addView(alunoTXT[n]);

                        }

                        if (n == 2 && i == 0) {

                            turmaTXT[n] = new TextView(this);
                            turmaTXT[n].setText("Turma = " + notaResult[n].toString());
                            turmaTXT[n].setTextColor(Color.BLACK);

                            layout.addView(turmaTXT[n]);

                        }

                        if (n == 3 && i == 0) {

                            disciplinaTXT[n] = new TextView(this);
                            disciplinaTXT[n].setText("Disciplina = " + notaResult[n].toString());
                            disciplinaTXT[n].setTextColor(Color.BLACK);

                            layout.addView(disciplinaTXT[n]);
                            discNome = notaResult[n].toString();

                        } else {
                            if (n == 3 && !notaResult[n].toString().equals(discNome.toString())) {

                                disciplinaTXT[n] = new TextView(this);
                                disciplinaTXT[n].setText("Disciplina = " + notaResult[n].toString());
                                disciplinaTXT[n].setTextColor(Color.BLACK);

                                layout.addView(disciplinaTXT[n]);
                                discNome = notaResult[n].toString();
                            }
                        }
                        if (n == 4) {

                            notaTXT[n] = new TextView(this);
                            notaTXT[n].setText(notaResult[n].toString());
                            notaTXT[n].setTextColor(Color.BLACK);

                            layout.addView(notaTXT[n]);

                        }

                    }

                }
            }

        } catch (Exception e) {
            System.out.println("Erro Android Excpetion = " + e);
        }

        layout.addView(bttVoltar);
        setContentView(layout);

    }
}
