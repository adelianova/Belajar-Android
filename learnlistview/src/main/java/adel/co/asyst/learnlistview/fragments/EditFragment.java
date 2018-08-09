package adel.co.asyst.learnlistview.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import adel.co.asyst.learnlistview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    EditText nameET;
    Button editbtn;
    int position;
    EditFragment.OnSubmitButtonListener listener;


    public EditFragment() {
        // Required empty public constructor
    }

    public static EditFragment newInstance(String name, int position) {
        EditFragment editFragment = new EditFragment();
        Bundle bundle = new Bundle();
        bundle.putString("nama", name);
        bundle.putInt("position", position);
        editFragment.setArguments(bundle);
        return editFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        nameET = view.findViewById(R.id.edit_text_nama);
        editbtn = view.findViewById(R.id.button_add);
        nameET.setText(getArguments().getString("nama"));
        position = getArguments().getInt("position");
        editbtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add:

                listener.onSubmitButton(nameET.getText().toString(), position);
                dismiss();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditFragment.OnSubmitButtonListener) {
            listener = (EditFragment.OnSubmitButtonListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must implement interface OnSubmitButtonListener");
        }
    }

    public interface OnSubmitButtonListener {
        void onSubmitButton(String name, int position);
    }
}
