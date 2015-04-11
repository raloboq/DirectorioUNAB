package apps.unab.directorio_unab;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by rene on 11/04/15.
 */
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Bundle extras = new Bundle();
        extras  = getIntent().getExtras();

        int iddocente = extras.getInt("iduser");



    }
}
