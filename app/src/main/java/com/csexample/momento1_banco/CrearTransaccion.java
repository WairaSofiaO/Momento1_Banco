package com.csexample.momento1_banco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class CrearTransaccion extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    RequestQueue rq;
    JsonRequest jrq;
    public static final String idcliente="idcliente";
    EditText cuentaorig,cuentadest,tipo,valor;
    TextView ctidcliente;
    Button transferir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_transaccion);

        rq = Volley.newRequestQueue(getApplicationContext()); //Requerimiento volley
        ctidcliente = findViewById(R.id.tvctidcliente);
        cuentaorig = findViewById(R.id.etcuentaorig);
        cuentadest = findViewById(R.id.etcuentadest);
        tipo = findViewById(R.id.ettipo);
        valor = findViewById(R.id.etvalor);
        transferir = findViewById(R.id.btntransferir);

        //Recibir parametros: idcliente
        final String idcliente = getIntent().getStringExtra("ridcliente");
        ctidcliente.setText(idcliente); //txtusuario es el id del edit text en el Activity main que se le manda la variable usuario
        //final String cuentaorig = getIntent().getStringExtra("rcuentaorig");

        transferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearTransaccion("http://192.168.1.55:82/ActividadBanco/Transaccion/CTransaccion.php?");
            }
        });
    }
    private void CrearTransaccion(String URL) {
        /*String url="http://172.16.58.128:8081/ActividadBanco/Transaccion/CTransaccion.php?";
        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq.add(jrq);*/
        //finish();

        StringRequest stringRequest=new StringRequest(Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(getApplicationContext(), "Registro Transaccion Exitoso", Toast.LENGTH_SHORT).show();
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
                parametros.put("idcliente",ctidcliente.getText().toString());
                parametros.put("nrocuenta_orig",cuentaorig.getText().toString());
                parametros.put("nrocuenta_dest",cuentadest.getText().toString());
                parametros.put("tipo",tipo.getText().toString());
                parametros.put("valor",valor.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Transaccion trans = new Transaccion(); //instanciar
        cuenta cuenta = new cuenta();

        //datos: arreglo que env�a los datos en formato JSON, en el archiv php
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);//posici�n 0 del arreglo....
            cuenta.setSaldo(jsonObject.optString("saldo"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Registro Transaccion Exitoso", Toast.LENGTH_SHORT).show();
        //Hcer que el numero de la cuenta no exista

    }
}
