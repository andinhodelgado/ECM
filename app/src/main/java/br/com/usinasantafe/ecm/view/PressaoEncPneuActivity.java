package br.com.usinasantafe.ecm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;

public class PressaoEncPneuActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressao_enc_pneu);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkPressaoEnc = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancPressaoEnc = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkPressaoEnc.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    Long qtde = Long.parseLong(editTextPadrao.getText().toString());

                    if (qtde < 1000) {

                        ecmContext.getPneuCTR().getItemPneuBean().setPressaoEncItemPneu(qtde);

                        Intent it = new Intent(PressaoEncPneuActivity.this, PressaoColPneuActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(PressaoEncPneuActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("VALOR DE CALIBRAGEM ACIMA DO PERMITIDO! FAVOR VERIFICA A MESMA.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    }
                }

            }

        });

        buttonCancPressaoEnc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(PressaoEncPneuActivity.this, PneuActivity.class);
        startActivity(it);
        finish();
    }

}
