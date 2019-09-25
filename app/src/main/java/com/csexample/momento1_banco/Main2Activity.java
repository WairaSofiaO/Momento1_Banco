package com.csexample.momento1_banco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        /*Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            String musuario = parametros.getString("musr");
            rusuario.setText(rusuario.getText().toString()+" "+musuario);
        }*/

        String mnombre = getIntent().getStringExtra("nombre");
        String mnrocuenta = getIntent().getStringExtra("nrocuenta");
        String msaldo = getIntent().getStringExtra("saldo");
        //ridcliente.setText(ridcliente.getText().toString()+" "+midcliente);
        rusuario.setText(rusuario.getText().toString()+" "+mnombre);
        rnrocuenta.setText(rnrocuenta.getText().toString()+" "+mnrocuenta);
        rsaldo.setText(rsaldo.getText().toString()+" "+msaldo);

        //Toast.makeText(getApplicationContext(),"ID usuario "+midcliente,Toast.LENGTH_SHORT).show();

        /* BOTONES
        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pasar a la actividad Crear Cuenta
                Intent conectado = new Intent(Main2Activity.this,CrearCuenta.class);
                //El constructor tiene 2 parametros, de donde viene (MainActivity), y a donde va (Main2Activity)
                conectado.putExtra("ridcliente",midcliente); //El metodo PutExtra manda la variable eusuario y la recibe la variable rusuario
                startActivity(conectado);
            }
        });

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

    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //activar el menú en esta actividad
        //getMenuInflater().inflate(R.menu.ppal,menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ppal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.macrearcuenta:
                //startActivity(new Intent(Main2Activity.this,CrearCuenta.class));

                Intent conectado = new Intent(Main2Activity.this,CrearCuenta.class);
                final String midcliente = getIntent().getStringExtra("idcliente");
                conectado.putExtra("ridcliente",midcliente);
                startActivity(conectado);

                break;

            case  R.id.matransaccion:
                Intent conectado1 = new Intent(Main2Activity.this,CrearTransaccion.class);
                final String midcliente1 = getIntent().getStringExtra("idcliente");
                conectado1.putExtra("ridcliente",midcliente1);
                startActivity(conectado1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
