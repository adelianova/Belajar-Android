package adel.co.asyst.learnfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import adel.co.asyst.learnfragment.fragments.FourthFragment;
import adel.co.asyst.learnfragment.fragments.InputBottomSheetFragment;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, FourthFragment.OnSubmitButtonListener, InputBottomSheetFragment.OnSubmitButtonListener {

    TextView tvName, tvAddress, tvDate;
    Button btnInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvName = findViewById(R.id.namaTV);
        tvAddress = findViewById(R.id.alamatTV);
        tvDate = findViewById(R.id.dateTV);
        btnInput = findViewById(R.id.inputBtn);
        btnInput.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inputBtn:
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FourthFragment fourthFragment = FourthFragment.newInstance(tvName.getText().toString(), tvAddress.getText().toString(), tvDate.getText().toString());
                transaction.replace(android.R.id.content, fourthFragment);
                transaction.addToBackStack(null);

                //untuk set argument dari activity ke fragment
//
//                Bundle bundle = new Bundle();
//                bundle.putString("nama",name);
//                bundle.putString("alamat", Address);
//                bundle.putString("birthday", birthday);
//
//                fourthFragment.setArguments(bundle);
                transaction.commit();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.edit_menu:
                InputBottomSheetFragment inputBottomSheetFragment = InputBottomSheetFragment.newInstance(tvName.getText().toString(), tvAddress.getText().toString(), tvDate.getText().toString());
                inputBottomSheetFragment.show(getSupportFragmentManager(), "InputBottomSheet");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSubmitButton(String name, String Address, String birthday) {
        tvName.setText(name);
        tvAddress.setText(Address);
        tvDate.setText(birthday);
    }
}
