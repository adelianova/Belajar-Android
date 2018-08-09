package adel.co.asyst.learnfragment.fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import adel.co.asyst.learnfragment.R;
import adel.co.asyst.learnfragment.utility.DateUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputBottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    EditText nameET, addressET;
    TextView dateTV;
    Button submitBtn;
    ImageView dateIV;
    InputBottomSheetFragment.OnSubmitButtonListener listener;
    DatePickerDialog datePickerDialog;

    public InputBottomSheetFragment() {
        // Required empty public constructor
    }

    public static InputBottomSheetFragment newInstance(String name, String Address, String birthday) {
        InputBottomSheetFragment inputBottom = new InputBottomSheetFragment();

        Bundle bundle = new Bundle();
        bundle.putString("nama", name);
        bundle.putString("alamat", Address);
        bundle.putString("birthday", birthday);

        inputBottom.setArguments(bundle);
        return inputBottom;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_bottom_sheet, container, false);

        nameET = view.findViewById(R.id.namaEditText);
        addressET = view.findViewById(R.id.alamatEditText);
        dateTV = view.findViewById(R.id.textView_date);
        submitBtn = view.findViewById(R.id.inputFormBtn);
        dateIV = view.findViewById(R.id.imageView_date);

        if (getArguments() != null) {
            nameET.setText(getArguments().getString("nama", ""));
            addressET.setText(getArguments().getString("alamat", ""));
            dateTV.setText(getArguments().getString("birthday", ""));
        }
        Calendar calendar = Calendar.getInstance();
        int year = 1999;
        int month = 0;
        int day = 1;
        String selectedDate = dateTV.getText().toString();

        if (!selectedDate.equalsIgnoreCase("")) {

            calendar.setTime(DateUtils.dateFromString("dd MMMM yyyy", selectedDate));
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }

        datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        dateIV.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inputFormBtn:
                //inputkan data lalu dikirim dari fragment ke activity
                listener.onSubmitButton(nameET.getText().toString(), addressET.getText().toString(), dateTV.getText().toString());
                dismiss();
                break;
            case R.id.imageView_date:
                datePickerDialog.show();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + " " + (month + 1) + " " + year;
        dateTV.setText(DateUtils.formatDate("dd MM yyyy", "dd MMMM yyyy", date));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InputBottomSheetFragment.OnSubmitButtonListener) {
            listener = (InputBottomSheetFragment.OnSubmitButtonListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must implement interface OnSubmitButtonListener");
        }
    }

    public interface OnSubmitButtonListener {
        void onSubmitButton(String name, String Address, String birthday);
    }
}
