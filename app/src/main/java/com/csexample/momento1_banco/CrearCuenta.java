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

    //Constantes que van a recibir las variables de Main2Activity
    public static final String idcliente="idcliente";
    public static final String saldo="saldo";
    public static final String nrocuenta="nrocuenta";

    //TextView nrocuentaCC;
    EditText saldoInicialCC;
    Button guardarcuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        //nrocuentaCC = findViewById(R.id.tvnrocuenta);
        saldoInicialCC = findViewById(R.id.etsaldoInicialCCuenta);
        guardarcuenta = findViewById(R.id.btnguardarcuenta);

        //Recibir parametros de Main2activity
        final String midcliente = getIntent().getStringExtra("idcliente");
        //rnrocuenta.setText(rnrocuenta.getText().toString()+" "+mnrocuenta);
        Toast.makeText(getApplicationContext(), "id:"+midcliente, Toast.LENGTH_SHORT).show();

        guardarcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Guardando cuenta...", Toast.LENGTH_SHORT).show();
                RegistrarCuenta("http://192.168.1.55:82/ClaseServiciosWebAndroidPHP/ActividadBanco/Cuenta/CCuenta.php?idcliente="+midcliente+"&saldo"+saldoInicialCC.getText().toString());

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
                parametros.put("saldo",saldoInicialCC.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
