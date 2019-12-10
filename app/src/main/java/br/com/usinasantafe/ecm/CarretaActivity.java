package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CarretaActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carreta);

        ecmContext = (ECMContext) getApplication();
        Button buttonOkCarreta = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancCarreta = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkCarreta.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    int verCarreta = ecmContext.getCertifCanaCTR().verCarreta(Long.parseLong(editTextPadrao.getText().toString()),2L);

                    if(verCarreta == 1) {
                        if (ecmContext.getVerPosTela() == 5){
                            Intent it = new Intent(CarretaActivity.this, OSActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);
                            startActivity(it);
                            finish();
                        }
                    }
                    else{
                        String msg = "";
                        Long numCarreta = ecmContext.getCertifCanaCTR().getPosCarreta(2L) + 1;
                        switch(ecmContext.getCertifCanaCTR().verCarreta(Long.parseLong(editTextPadrao.getText().toString()),2L)){
                            case 2:
                                msg = "CARRETA INEXISTENTE NA BASE DE DADOS! POR FAVOR, ATUALIZE OS DADOS.";
                                break;
                            case 3:
                                msg = "ESSA CARRETA JÁ FOI INSERIDA. VERIFIQUE NOVAMENTE A NUMERAÇÃO DA CARRETA.";
                                break;
                            case 4:
                                msg = "A NUMERAÇÃO DIGITADA NÃO CORRESPONDE DA CARRETA " + numCarreta +". VERIFIQUE SE VOCÊ NÃO ESTA INVERTENDO AS CARRETAS.";
                                break;
                        }
                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage(msg);
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

        buttonCancCarreta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);
        startActivity(it);
        finish();
    }

}
