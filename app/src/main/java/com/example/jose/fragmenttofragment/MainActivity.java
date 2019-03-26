package com.example.jose.fragmenttofragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements GreenFragment.OnGreenFragmentListener{

    BlueFragment mBlueFragment;
    GreenFragment mGreenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Añade fragmentos de forma dinámica a la actividad con el FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Se referencia al contenedor de fragment
       mBlueFragment = (BlueFragment) fragmentManager.findFragmentById(R.id.blue_fragment_container);

       // Creación del objeto fragment si aun no ha sido creado.
        if (mBlueFragment == null) {
            Log.d("fragmento","Creando fragmento azul");
            mBlueFragment = new BlueFragment();
            fragmentManager.beginTransaction().add(R.id.blue_fragment_container, mBlueFragment).commit();
        }

        // Igual para el otro fragmento, el verde
        mGreenFragment = (GreenFragment) fragmentManager.findFragmentById(R.id.green_fragment_container);
        if (mGreenFragment == null) {
            Log.d("fragmento","Crando fragmento verde");
            mGreenFragment = new GreenFragment();
            fragmentManager.beginTransaction().add(R.id.green_fragment_container, mGreenFragment).commit();
        }

    }

    // Implementa método de la interface GreenFragment.OnGreenFragmentListener
    // La actividad recive un mensaje desde el fragmento verde
    // y se lo pasa al fragmento azul
    @Override
    public void mensajeDesdeFragmentoVerde(String mensaje) {

        mBlueFragment.tienesUnMensaje(mensaje);
    }
}
