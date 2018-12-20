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

        // Añade fragmentos de forma dinámica a la actividad
        FragmentManager fragmentManager = getSupportFragmentManager();

       mBlueFragment = (BlueFragment) fragmentManager.findFragmentById(R.id.blue_fragment_container);
        if (mBlueFragment == null) {
            Log.d("fragmento","Crando fragmento azul");
            mBlueFragment = new BlueFragment();
            fragmentManager.beginTransaction().add(R.id.blue_fragment_container, mBlueFragment).commit();
        }

        mGreenFragment = (GreenFragment) fragmentManager.findFragmentById(R.id.green_fragment_container);
        if (mGreenFragment == null) {
            Log.d("fragmento","Crando fragmento verde");
            mGreenFragment = new GreenFragment();
            fragmentManager.beginTransaction().add(R.id.green_fragment_container, mGreenFragment).commit();
        }

    }

    // La actividad recive un mensaje desde el fragmento verde
    // y se lo pasa al fragmento azul
    @Override
    public void mensajeDesdeFragmentoVerde(String message) {

        mBlueFragment.youveGotMail(message);
    }
}
