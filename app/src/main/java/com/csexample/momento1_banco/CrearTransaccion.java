package com.csexample.momento1_banco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CrearTransaccion extends AppCompatActivity {
    EditText cuentaorig,cuentadest,tipo,valor;
    Button transferir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_transaccion);

        cuentaorig = findViewById(R.id.etcuentaorig);
        cuentadest = findViewById(R.id.etcuentadest);
        tipo = findViewById(R.id.ettipo);
        valor = findViewById(R.id.etvalor);
        transferir = findViewById(R.id.btntransferir);
        saldoCuenta();
        transferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Mensaje");
                //if (valor.getText().toString()> )
            }
        });

    }

    private void saldoCuenta() {

    }
}
