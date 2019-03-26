package com.example.jose.fragmenttofragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GreenFragment extends Fragment {

    //Esta es la interface que la Activity implementará para que
    // este fragmento pueda comunicarse con ella
    public interface OnGreenFragmentListener {
        void mensajeDesdeFragmentoVerde(String text);
    }


    // Esto será una referencia a la activity que implementa la interface
    private OnGreenFragmentListener mActividadCallback;

    // Este método, que es el primero en ejecutarse en el ciclo de vida del fragment,
    // asegura que la Activity ha implementado nuestro listener y que no es null
    // Hace que la variable mActividadCallback apunte a la actividad para que cuando se pinche al boton
    // de este fragmento llame al metodo mensajeDesdeFragmentoVerde de su interface para recojer
    // el mensaje
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);  // context es la Activity que contiene el fragment
        // Si la Activity implementa la interface entonces es instancia de ella
        if (context instanceof OnGreenFragmentListener) {
            // Como la actividad implementa la interface OnGreenFragmentListener
            // puedo hacer esta asignación polimórfica.
            mActividadCallback = (OnGreenFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " La actividad no implementa el interface OnGreenFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_green, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "¡Hola Azul, yo soy Verde!";
                mActividadCallback.mensajeDesdeFragmentoVerde(message);
            }
        });
    }




    @Override
    public void onDetach() {
        super.onDetach();
        mActividadCallback = null;
    }
}
