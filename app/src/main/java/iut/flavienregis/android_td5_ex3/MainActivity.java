package iut.flavienregis.android_td5_ex3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends Activity {

    private TabHost lesOnglets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lesOnglets = (TabHost) findViewById(R.id.tableOnglet);
        lesOnglets.setup();

        // On ajoute les onglet
        TabHost.TabSpec specification = lesOnglets.newTabSpec("Sélection");
        specification.setIndicator(getResources().getString(R.string.onglet_selection));
        specification.setContent(R.id.selection_onglet);
        lesOnglets.addTab(specification);

        lesOnglets.addTab(lesOnglets.newTabSpec("Correction")
                            .setIndicator(getResources().getString(R.string.onglet_correction))
                            .setContent(R.id.correction_onglet));


        lesOnglets.setCurrentTab(0);

    }
}
