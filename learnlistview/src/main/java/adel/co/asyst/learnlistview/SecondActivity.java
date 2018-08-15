package adel.co.asyst.learnlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import adel.co.asyst.learnlistview.model.Person;

public class SecondActivity extends AppCompatActivity {

    TextView nameTV, addressTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nameTV = findViewById(R.id.text_name);
        addressTV = findViewById(R.id.text_address);

        if (getIntent().getExtras() != null) {
            Person person = getIntent().getExtras().getParcelable("name");
            nameTV.setText(person.getName());
            addressTV.setText(person.getAddress());
        }
    }
}
