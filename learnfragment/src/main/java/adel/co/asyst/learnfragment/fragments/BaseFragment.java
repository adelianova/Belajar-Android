package adel.co.asyst.learnfragment.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import adel.co.asyst.learnfragment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {

    Button btnFirst, btnSecond, btnThird;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        btnFirst = view.findViewById(R.id.buttonfragment_first);
        btnSecond = view.findViewById(R.id.buttonfragment_second);
        btnThird = view.findViewById(R.id.buttonfragment_third);

        btnFirst.setOnClickListener(this);
        btnSecond.setOnClickListener(this);
        btnThird.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.buttonfragment_first:
                fragment = new FirstFragment();
                break;
            case R.id.buttonfragment_second:
                fragment = new SecondFragment();
                break;
            case R.id.buttonfragment_third:
                fragment = new ThirdFragment();
                break;
        }

        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
