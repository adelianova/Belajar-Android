package adel.co.asyst.formadel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import adel.co.asyst.formadel.utility.Constant;

public class ResultActivity extends AppCompatActivity {
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textViewResult = findViewById(R.id.resultTV);
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            textViewResult.setText(bundle.getString(Constant.KEY_RESULT));
        }

    }
}
