package com.csexample.momento1_banco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    //Constantes que van a recibir las variables de SesionFragment
    public static final String idcliente="idcliente";
    public static final String nombre="nombre";
    public static final String saldo="saldo";
    public static final String nrocuenta="nrocuenta";
    TextView rusuario,rsaldo,rnrocuenta; //Variables que van a mostrar los datos recibidos
    Button crearCuenta,transaccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rusuario = findViewById(R.id.tvusuario);
        rsaldo = findViewById(R.id.tvsaldo);
        rnrocuenta = findViewById(R.id.tvnrocuenta);
        crearCuenta = findViewById(R.id.btncrearcuenta);
        transaccion = findViewById(R.id.btntransaccion);
        /*Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            String musuario = parametros.getString("musr");
            rusuario.setText(rusuario.getText().toString()+" "+musuario);
        }*/
        String midcliente = getIntent().getStringExtra("idcliente");
        String mnombre = getIntent().getStringExtra("nombre");
        String mnrocuenta = getIntent().getStringExtra("nrocuenta");
        String msaldo = getIntent().getStringExtra("saldo");
        //ridcliente.setText(ridcliente.getText().toString()+" "+midcliente);
        rusuario.setText(rusuario.getText().toString()+" "+mnombre);
        rnrocuenta.setText(rnrocuenta.getText().toString()+" "+mnrocuenta);
        rsaldo.setText(rsaldo.getText().toString()+" "+msaldo);

        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cliente usua = new cliente();
                Intent intencion = new Intent(Main2Activity.this,CrearCuenta.class);
                intencion.putExtra(CrearCuenta.idcliente,usua.getIdcliente()); //Mandar id cliente a crear cuenta
                startActivity(intencion);
            }
        });
        /*
        transaccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencion = new Intent(Main2Activity.this,Transaccion.class);
                //intencion.putExtra(Main2Activity.nombre,usua.getNombre());
                startActivity(intencion);
            }
        });
        */
    }
}
