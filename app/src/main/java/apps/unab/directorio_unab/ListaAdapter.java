package apps.unab.directorio_unab;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rene on 13/03/15.
 */
public class ListaAdapter extends ArrayAdapter<Docente> {

    private final Activity context;
    private ArrayList<Docente> docentesList;



    public ListaAdapter(Activity context, ArrayList<Docente> d) {
        super(context, R.layout.item, d);
        this.context = context;
        this.docentesList=d;

        Log.i("cons",d.get(0).getNombre());


    }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.item, null, true);

            Log.i("adapter","entro");

            Docente object = docentesList.get(position);


            Log.i("adapter",object.getNombre());

            TextView nombre = (TextView)rowView.findViewById(R.id.NombreDocente);
            TextView cargo = (TextView)rowView.findViewById(R.id.CargoDocente);
            TextView telefono = (TextView)rowView.findViewById(R.id.TelefonoDocente);
            TextView correo = (TextView)rowView.findViewById(R.id.CorreoDocente);
            ImageView foto = (ImageView)rowView.findViewById(R.id.FotoDocente);

            nombre.setText(object.getNombre());
            cargo.setText(object.getCargo());
            telefono.setText(object.getTelefono());
            correo.setText(object.getCorreo());
            foto.setImageResource(context.getResources().getIdentifier(object.getFoto(),"drawable","apps.unab.directorio_unab"));


            return rowView;

        }



}
