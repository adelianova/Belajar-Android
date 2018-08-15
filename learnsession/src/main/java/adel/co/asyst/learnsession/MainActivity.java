package adel.co.asyst.learnsession;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import adel.co.asyst.learnsession.utility.SessionUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameet, addresset;
    Button submitbtn;
    Spinner eduspinner;
    RadioGroup genderrg;
    RadioButton malerb, femalerb;
    SessionUtils sessionUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameet = findViewById(R.id.edit_text_name);
        addresset = findViewById(R.id.edit_text_address);
        submitbtn = findViewById(R.id.button_submit);
        eduspinner = findViewById(R.id.spinner_education);
        genderrg = findViewById(R.id.radio_group_gender);
        malerb = findViewById(R.id.radio_button_male);
        femalerb = findViewById(R.id.radio_button_female);
        sessionUtils = new SessionUtils(this);
        submitbtn.setOnClickListener(this);

        String name = sessionUtils.loadName();
        String address = sessionUtils.loadAddress();
        String gender = sessionUtils.loadGender();
        if (gender.equalsIgnoreCase("Male")) {
            malerb.setChecked(true);
        } else {
            femalerb.setChecked(true);
        }
        String edu = sessionUtils.loadEdu();
        nameet.setText(name);
        addresset.setText(address);
        for (int i = 0; i < eduspinner.getAdapter().getCount(); i++) {
            if (eduspinner.getAdapter().getItem(i).toString().equalsIgnoreCase(edu)) {
                eduspinner.setSelection(i);
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_submit:
                String name = nameet.getText().toString();
                String address = addresset.getText().toString();
                String edu = eduspinner.getSelectedItem().toString();
                String gender;
                if (malerb.isChecked()) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }
                if (!TextUtils.isEmpty(nameet.getText().toString())) {
                    if (!TextUtils.isEmpty(addresset.getText().toString())) {

                        sessionUtils.saveName(name, address, gender, edu);
                        Toast.makeText(getApplicationContext(), "Biodata sudah tersimpan", Toast.LENGTH_SHORT).show();
                    } else {
                        addresset.setError("Alamat belum diisi");
                    }
                } else {
                    nameet.setError("Nama belum diisi");
                }
                break;
        }
    }
}
