package com.csexample.momento1_banco;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//Modificar exteds Fragment implements... (6)
//despues sale error y se da alt enter, implement methods (6)
public class SesionFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

//Objetos de conexion (6.1)
    RequestQueue rq;
    JsonRequest jrq;
    EditText idcliente,contrasena;
    Button iniciar, registrar;
//----(6.1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//------(6.1)
        //return inflater.inflate(R.layout.fragment_sesion, container, false);
        View vista = inflater.inflate(R.layout.fragment_sesion,container,false);
        idcliente = vista.findViewById(R.id.etusr);
        contrasena = vista.findViewById(R.id.etclave);
        iniciar = vista.findViewById(R.id.btniniciarsesion); //btnsesion
        registrar = vista.findViewById(R.id.btnregistrar);
        rq = Volley.newRequestQueue(getContext()); //Requerimiento volley
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarsesion(); //Crear alt enter-llamar metodo
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),RCliente.class));
            }
        });
        return vista;

    }

    private void iniciarsesion() {
        Toast.makeText(getContext(),"Iniciando sesion...",Toast.LENGTH_SHORT).show();
        //String url = "http://192.168.1.62/serviciosandroidphp/sesion.php?idcliente="+idcliente.getText().toString()+"&contrasena="+contrasena.getText().toString();
        //EN CASA: IP 192.168.1.63, CARPETA BANCO: ClaseServiciosWebAndroidPHP/ActividadBanco
        String url = "http://192.168.1.55:82/ClaseServiciosWebAndroidPHP/ActividadBanco/sesion.php?idcliente="+idcliente.getText().toString()+"&contrasena="+contrasena.getText().toString();
        //ClaseServiciosWebAndroidPHP/ActividadBanco/sesion.php?idcliente=111&contrasena=222
        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq.add(jrq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se ha encontrado el usuario "+idcliente.getText().toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(),"No se ha encontrado la contrasena "+contrasena.getText().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        //Se utiliza la clase cliente para tomar los campos del arreglo datos del archivo php
        cliente usua = new cliente();
        cuenta cuent = new cuenta(); //instanciar cuenta

        Toast.makeText(getContext(),"Se ha encontrado el usuario "+idcliente.getText().toString(),Toast.LENGTH_SHORT).show();
        //datos: arreglo que env�a los datos en formato JSON, en el archiv php
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);//posici�n 0 del arreglo....
            usua.setIdcliente(jsonObject.optString("idcliente"));
            usua.setContrasena(jsonObject.optString("contrasena"));
            usua.setNombre(jsonObject.optString("nombre"));
            usua.setDireccion(jsonObject.optString("direccion"));
            usua.setTelefono(jsonObject.optString("telefono"));
            cuent.setSaldo(jsonObject.optString("saldo")); //numeros enteros
            cuent.setNrocuenta(jsonObject.optString("nrocuenta"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Enviar variables a Main2Activity
        Intent intentMain2 = new Intent(getContext(),Main2Activity.class);
        intentMain2.putExtra(Main2Activity.nombre,usua.getNombre());
        intentMain2.putExtra(Main2Activity.nrocuenta,cuent.getNrocuenta());
        intentMain2.putExtra(Main2Activity.saldo,cuent.getSaldo());
        intentMain2.putExtra(Main2Activity.idcliente,usua.getIdcliente()); //Mandar id cliente a crear cuenta
        startActivity(intentMain2);
        //Enviar variables de Crear Cuenta
        //Intent intentCrearCuenta = new Intent(getContext(),CrearCuenta.class);
        //intentCrearCuenta.putExtra(CrearCuenta.idcliente,usua.getIdcliente()); //Mandar id cliente a crear cuenta
        //startActivity(intentCrearCuenta);

    }
//---------(6.1)
}
