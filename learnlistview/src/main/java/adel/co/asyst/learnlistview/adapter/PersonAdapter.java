package adel.co.asyst.learnlistview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import adel.co.asyst.learnlistview.R;
import adel.co.asyst.learnlistview.model.Person;

public class PersonAdapter extends ArrayAdapter<Person> {

    ArrayList<Person> listPerson;

    public PersonAdapter(Context context, ArrayList<Person> listPerson) {
        super(context, R.layout.item_person, listPerson);

        this.listPerson = listPerson;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, null);
        TextView nametv = rootView.findViewById(R.id.text_view_name);
        TextView addresstv = rootView.findViewById(R.id.text_view_address);

        nametv.setText(listPerson.get(position).getName());
        addresstv.setText(listPerson.get(position).getAddress());

        return rootView;

    }
}
