package com.example.jose.fragmenttofragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GreenFragment extends Fragment {
    private OnGreenFragmentListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_green, container, false);

        Button button = v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "¡Hola Azul, yo soy Verde!";
                mCallback.mensajeDesdeFragmentoVerde(message);
            }
        });

        return v;
    }

    //Esta es la interface que la Activity implementará para que
    // este fragmento pueda comunicarse con ella
    public interface OnGreenFragmentListener {
        void mensajeDesdeFragmentoVerde(String text);
    }


    // Este método, que es el primero en ejecutarse en el ciclo de vida del fragment,
    // asegura que la Activity ha implementado nuestro listener y que no es null
    // Hace que la variable mCallback apunte a la actividad para que cuando se pinche al boton
    // de este fragmento llame al metodo mensajeDesdeFragmentoVerde de su interface para recojer
    // el mensaje
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);  // context es la Activity que contiene el fragment
        // Si la Activity implementa la interface entonces es instancia de ella
        if (context instanceof OnGreenFragmentListener) {
            // Como la actividad implementa la interface OnGreenFragmentListener
            // puedo hacer esta asignación polimórfica.
            mCallback = (OnGreenFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " La actividad no implementa el interface OnGreenFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}
