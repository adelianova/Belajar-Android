package adel.co.asyst.learnlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import adel.co.asyst.learnlistview.fragments.EditFragment;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, View.OnClickListener, EditFragment.OnSubmitButtonListener {

    ListView listView;
    EditText nameET;
    Button addbtn;
    ArrayList<String> nama = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        addbtn = findViewById(R.id.button_add);
        nameET = findViewById(R.id.edit_text_nama);
        nama.add("Adelia");
        nama.add("Yeni");
        nama.add("Mifta");
        nama.add("Annisa");
        nama.add("Evi");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nama);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemLongClickListener(this);
        addbtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (!nameET.getText().toString().equalsIgnoreCase("")) {
            nama.add(nameET.getText().toString());
            nameET.setText("");
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        EditFragment editFragment = EditFragment.newInstance(nama.get(position), position);
        editFragment.show(getSupportFragmentManager(), "");
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onSubmitButton(String name, int position) {
        nama.set(position, name);
        arrayAdapter.notifyDataSetChanged();
    }
}
