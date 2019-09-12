package com.csexample.momento1_banco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CrearCuenta extends AppCompatActivity {

    //Constantes que van a recibir las variables de SesionFragment
    //public static final String idcliente="idcliente";
    EditText ccsaldoInicial;
    Button guardarcuenta;
    TextView ccidcliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        //nrocuentaCC = findViewById(R.id.tvnrocuenta);
        ccidcliente = findViewById(R.id.tvccidcliente);
        ccsaldoInicial = findViewById(R.id.etccsaldoInicial);
        guardarcuenta = findViewById(R.id.btnguardarcuenta);

        //Recibir parametros: idcliente
        final String idcliente = getIntent().getStringExtra("ridcliente");
        ccidcliente.setText(idcliente); //txtusuario es el id del edit text en el Activity main que se le manda la variable usuario

        guardarcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Guardando...", Toast.LENGTH_SHORT).show();
                RegistrarCuenta("http://192.168.1.55:82/ClaseServiciosWebAndroidPHP/ActividadBanco/Cuenta/CCuenta.php?idcliente="+idcliente+"&saldo="+ccsaldoInicial.getText().toString());


            }
        });

    }

    private void RegistrarCuenta(String URL) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("idcliente",ccidcliente.getText().toString());
                parametros.put("saldo",ccsaldoInicial.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
