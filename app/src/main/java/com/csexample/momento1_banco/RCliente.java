package com.csexample.momento1_banco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RCliente extends AppCompatActivity {

    EditText idclienter,nombrer,direccionr,contrasenar,telefonor,saldor;
    Button registror, iniciarsesionr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcliente);

        idclienter=findViewById(R.id.etidclienteR);
        nombrer=findViewById(R.id.etnombreR);
        direccionr=findViewById(R.id.etdireccionR);
        contrasenar=findViewById(R.id.etcontrasenaR);
        telefonor = findViewById(R.id.ettelefonoR);
        saldor = findViewById(R.id.etsaldoR);
        registror=findViewById(R.id.btnregistrarr);
        iniciarsesionr = findViewById(R.id.btniniciarsesionr);

        registror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "Entrando...", Toast.LENGTH_SHORT).show();
            RegistrarCliente("http://192.168.1.55:82/ClaseServiciosWebAndroidPHP/ActividadBanco/Cliente/CCliente.php?idcliente="+idclienter.getText().toString()+"&nombre="+nombrer.getText().toString()+"&contrasena="+contrasenar.getText().toString()+"&direccion="+direccionr.getText().toString()+"&telefono="+telefonor.getText().toString());
            }
        });
        iniciarsesionr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencion = new Intent(RCliente.this,SesionFragment.class);
                startActivity(intencion);
            }
        });
    }

    private void RegistrarCliente(String URL) {
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
                parametros.put("idcliente",idclienter.getText().toString());
                parametros.put("contrasena",contrasenar.getText().toString());
                parametros.put("nombre",nombrer.getText().toString());
                parametros.put("direccion",direccionr.getText().toString());
                parametros.put("telefono",telefonor.getText().toString());
                parametros.put("saldo",saldor.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}