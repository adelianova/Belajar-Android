package adel.co.asyst.formadel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import adel.co.asyst.formadel.utility.Constant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    Button btn_tbl;
    EditText editText1;
    String editText, selectedGender, selectedCity;
    TextView textView;
    RadioGroup genderRadioGroup;
    CheckBox cbNasgor, cbMi, cbCapjay;
    Spinner citySpinner;
    ArrayList<String> listFood = new ArrayList<>();
    ArrayList<String> listCity = new ArrayList<>();
    Switch sw1;
    ToggleButton tb1;


    String foods = "";

    /*EditText editTextA,editTextB;
    Button btn_tambah,btn_kurang,btn_kali,btn_bagi;
    TextView textViewHasil;
    String A,B;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*editTextA = findViewById(R.id.a);
        editTextB = findViewById(R.id.b);
        btn_tambah = findViewById(R.id.tambah);
        btn_kurang = findViewById(R.id.kurang);
        btn_kali = findViewById(R.id.kali);
        btn_bagi = findViewById(R.id.bagi);
        textViewHasil = findViewById(R.id.hasil);

        btn_tambah.setOnClickListener(this);
        btn_kurang.setOnClickListener(this);
        btn_kali.setOnClickListener(this);
        btn_bagi.setOnClickListener(this);*/

        btn_tbl = findViewById(R.id.btn_tombol);
        editText1 = findViewById(R.id.edit1);
        textView = findViewById(R.id.text_view);
        genderRadioGroup = findViewById(R.id.radioGroup);
        genderRadioGroup.setOnCheckedChangeListener(this);
        cbNasgor = findViewById(R.id.nasgor);
        cbMi = findViewById(R.id.mi);
        cbCapjay = findViewById(R.id.capjay);
        ((RadioButton) findViewById(R.id.radioPerempuan)).setChecked(true);
        btn_tbl.setOnClickListener(this);
        cbNasgor.setOnCheckedChangeListener(this);
        cbCapjay.setOnCheckedChangeListener(this);
        cbMi.setOnCheckedChangeListener(this);
        citySpinner = findViewById(R.id.kota);
        citySpinner.setOnItemSelectedListener(this);
        sw1 = findViewById(R.id.switch1);
        tb1 = findViewById(R.id.toggle);

        sw1.setOnCheckedChangeListener(this);
        tb1.setOnCheckedChangeListener(this);
        listCity.add("Malang");
        listCity.add("Batu");
        listCity.add("Tuban");
        listCity.add("Lumajang");
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listCity);
        citySpinner.setAdapter(cityAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_tombol:
                editText = editText1.getText().toString();
                if (!TextUtils.isEmpty(editText)) {
                    foods = "";
                    for (int i = 0; i < listFood.size(); i++) {
                        foods = foods + " " + listFood.get(i);
                    }
                    // textView.setText("Welcome "+editText +", kamu " +selectedGender+" suka"+foods + " tinggal di "+selectedCity);
                    AlertDialog.Builder alertDia = new AlertDialog.Builder(this);
                    alertDia.setTitle("Confirmation").setCancelable(false).setMessage("Are you sure?").
                            setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                                    String result = "Welcome " + editText + ", kamu " + selectedGender + " suka" + foods + " tinggal di " + selectedCity;
                                    intent.putExtra(Constant.KEY_RESULT, result);
                                    startActivity(intent);
                                }
                            }).setNegativeButton("No", null).show();

                }

                //finish();
                //Cara pakai toast untuk menampilkan text
                /*Toast.makeText(getApplicationContext(),"Welcome "+editText , Toast.LENGTH_SHORT).show();*/
                break;
          /*  case R.id.tambah :
               HasilTambah();
            break;
            case R.id.kurang :
                HasilKurang();
                break;
            case R.id.kali :
                HasilKali();
                break;
            case R.id.bagi :
                HasilBagi();*/
        }
    }

    /*cara select radio button*/
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.radioLaki:
                selectedGender = "Laki-laki";
                break;
            case R.id.radioPerempuan:
                selectedGender = "Perempuan";
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id) {
            case R.id.nasgor:
                if (isChecked) {
                    listFood.add(" Nasi Goreng");
                } else {
                    listFood.remove(" Nasi Goreng");
                }
                break;
            case R.id.mi:
                if (isChecked) {
                    listFood.add(" Mie Ayam");
                } else {
                    listFood.remove(" Mie Ayam");
                }
                break;
            case R.id.capjay:
                if (isChecked) {
                    listFood.add(" Capjay");
                } else {
                    listFood.remove(" Capjay");
                }
                break;
            case R.id.switch1:
                Log.d("MainAct tesSwitch", String.valueOf(isChecked));
                break;
            case R.id.toggle:
                Log.d("MainAct tesToggle", String.valueOf(isChecked));
                break;
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedCity = citySpinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

   /* private void HasilBagi() {
        A = editTextA.getText().toString();
        B = (editTextB.getText().toString());
        if (TextUtils.isEmpty(A)){
            editTextA.setError("Maaf angka 1 belum diisi");
        }else if(TextUtils.isEmpty(B)){
            editTextB.setError("Maaf angka 2 belum diisi");
        }else{
            int a = Integer.parseInt(editTextA.getText().toString());
            int b = Integer.parseInt(editTextB.getText().toString());
            if(b==0){
                editTextB.setError("Maaf angka 2 tidak boleh 0");
            }else{
                int hasil = a / b ;
                String hasilBagi = Integer.toString(hasil);
                textViewHasil.setText(a + " : " +b +" = " +hasilBagi);
            }
        }
    }

    private void HasilKali() {
        A = editTextA.getText().toString();
        B = (editTextB.getText().toString());
        if (TextUtils.isEmpty(A)){
            editTextA.setError("Maaf angka 1 belum diisi");
        }else if(TextUtils.isEmpty(B)){
            editTextB.setError("Maaf angka 2 belum diisi");
        }else{
            int a = Integer.parseInt(editTextA.getText().toString());
            int b = Integer.parseInt(editTextB.getText().toString());
            int hasil = a * b ;
            String hasilKali = Integer.toString(hasil);
            textViewHasil.setText(a + " x " +b +" = " +hasilKali);
        }
    }

    private void HasilKurang() {
        A = editTextA.getText().toString();
        B = (editTextB.getText().toString());
        if (TextUtils.isEmpty(A)){
            editTextA.setError("Maaf angka 1 belum diisi");
        }else if(TextUtils.isEmpty(B)){
            editTextB.setError("Maaf angka 2 belum diisi");
        }else{
            int a = Integer.parseInt(editTextA.getText().toString());
            int b = Integer.parseInt(editTextB.getText().toString());
            int hasil = a - b ;
            String hasilKurang = Integer.toString(hasil);
            textViewHasil.setText(a + " - " +b +" = " +hasilKurang);
        }
    }

    private void HasilTambah() {
        A = editTextA.getText().toString();
        B = (editTextB.getText().toString());
        if (TextUtils.isEmpty(A)){
            editTextA.setError("Maaf angka 1 belum diisi");
        }else if(TextUtils.isEmpty(B)){
            editTextB.setError("Maaf angka 2 belum diisi");
        }else{
            int a = Integer.parseInt(editTextA.getText().toString());
            int b = Integer.parseInt(editTextB.getText().toString());
            int hasil = a + b ;
            String hasilTambah = Integer.toString(hasil);
            textViewHasil.setText(a + " + " +b +" = " +hasilTambah);
        }
    }*/

}
