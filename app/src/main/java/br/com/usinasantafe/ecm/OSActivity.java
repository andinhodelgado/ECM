package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class OSActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkOS = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    if(ecmContext.getCertifCanaCTR().verNroOS(Long.parseLong(editTextPadrao.getText().toString()))){

                        ecmContext.getCertifCanaCTR().setNroOS(Long.parseLong(editTextPadrao.getText().toString()));
                        Intent it = new Intent(OSActivity.this, LibOSActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("O.S. NÃO CORRESPONDENTE A ATIVIDADE ANTERIORMENTE DIGITADA. POR FAVOR, DIGITE A O.S. CORRESPONDE A MESMA.");

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                editTextPadrao.setText("");
                            }
                        });
                        alerta.show();

                    }

                }
            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        if(!ecmContext.getCertifCanaCTR().verQtdeCarreta(1L)){
            Intent it = new Intent(OSActivity.this, CaminhaoActivity.class);
            startActivity(it);
            finish();
        }
        else{
            Intent it = new Intent(OSActivity.this, CarretaActivity.class);
            startActivity(it);
            finish();
        }
    }

}
