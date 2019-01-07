package iut.flavienregis.android_td5_ex3;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity {

    final public int NB_MOT_SELECT = 10;

    private TabHost lesOnglets;

    private ArrayList<String> motSelect;

    private SelectionMot lesMots = new SelectionMot();

    private ArrayList<String> motAfficher;

    private ArrayAdapter<String> adaptateurMotCorrect;

    private ArrayAdapter<String> adaptateurListeMot;

    private Spinner spinnerListeMotCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Les onglet
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

        // La liste
        motAfficher = new ArrayList<>();
        adaptateurListeMot = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, motAfficher);
        setListAdapter(adaptateurListeMot);
        refreshListe();

        // Spinner

        spinnerListeMotCorrect = (Spinner) findViewById(R.id.listCorrect);
        motSelect = new ArrayList<>();
        adaptateurMotCorrect = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,motSelect);
        spinnerListeMotCorrect.setAdapter(adaptateurMotCorrect);

    }

    public void refreshListe() {

        motAfficher = lesMots.selectionnerMot(NB_MOT_SELECT);
        adaptateurListeMot.clear();
        adaptateurListeMot.addAll(motAfficher);

    }

    public void Valider(View view) {
        SparseBooleanArray elementCoche = getListView().getCheckedItemPositions();
        motSelect = new ArrayList<>();
        for (int i = 0; i < elementCoche.size(); i++) {
            if (elementCoche.valueAt(i)){
                motSelect.add(getListView().getAdapter().getItem(elementCoche.keyAt(i)).toString());
            }
        }

        Toast.makeText(getApplicationContext(), Arrays.toString(motSelect.toArray()), Toast.LENGTH_LONG).show();

        // remplir le spinner
        adaptateurMotCorrect.clear();
        adaptateurMotCorrect.addAll(motSelect);

    }

    public void Annuler(View view)  {
        getListView().clearChoices();
        getListView().requestLayout();
        adaptateurMotCorrect.clear();
    }

    public void Reinitiliser(View view)  {
        refreshListe();
        adaptateurMotCorrect.clear();
    }
}
