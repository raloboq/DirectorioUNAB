package apps.unab.directoriounab;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ListaAdapter adapter;
    ArrayList<Docente> docentes = new ArrayList<Docente>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //docentes = new ArrayList<Docente>();

        Docente d1 = new Docente();
        d1.setNombre("Wilson Briceño");
        d1.setCargo("Decano");
        d1.setTelefono("1234");
        d1.setFoto("foto1");
        Docente d2 = new Docente();
        d2.setNombre("René Lobo");
        d2.setTelefono("123");
        d2.setCargo("Docente");
        d2.setFoto("foto1");


        docentes.add(d1);
        docentes.add(d2);
        //docentes.add(d3);

        adapter= new ListaAdapter(this,docentes);

        Log.i("main", docentes.get(0).getNombre());


        ListView list = (ListView) findViewById(R.id.listViewDocente);
        list.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
