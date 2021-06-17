package com.example.autolavado;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.autolavado.sqlite.ConnectionSQLite;
import com.example.autolavado.utilities.Utilities;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewCarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private TextInputLayout editTextPrice, editTypeEntry;
    private AutoCompleteTextView autoCompleteTextView;
    private Button btn_save_entry;
    ConnectionSQLite connection;

    public NewCarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewCarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewCarFragment newInstance(String param1, String param2) {
        NewCarFragment fragment = new NewCarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_new_car, container, false);
        init();
        return view;
    }

    private void init() {
        editTextPrice = view.findViewById(R.id.input_layout_price);
        editTypeEntry = view.findViewById(R.id.input_layout_type_entry);
        btn_save_entry = view.findViewById(R.id.btn_save_entry);
        btn_save_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_entries();
//                register_entries_sql();
            }
        });
        init_dropdown_options();
    }

    private void init_dropdown_options() {
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTypeEntry);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.type_entries_list_item,Utilities.OPTIONS_TYPE_ENTRIES);


        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(),false);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }

    private void register_entries_sql() {
        connection = new ConnectionSQLite(getContext(), Utilities.DB_NAME, null, 1);
        SQLiteDatabase db = connection.getWritableDatabase();

        String insert = "INSERT INTO " + Utilities.TABLE_ENTRIES + " (" +
                Utilities.PRICE + "," +
                Utilities.TYPE_ENTRY + ") values (" +
                editTextPrice.getEditText().getText().toString() + "," +
                editTypeEntry.getEditText().getText().toString() + ")";
        db.execSQL(insert);
        db.close();
    }

    private void register_entries() {
        ConnectionSQLite connection = new ConnectionSQLite(getContext(), Utilities.DB_NAME, null, 1);
        SQLiteDatabase db = connection.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilities.PRICE, editTextPrice.getEditText().getText().toString());
        values.put(Utilities.TYPE_ENTRY,editTypeEntry.getEditText().getText().toString());
        Long result = db.insert(Utilities.TABLE_ENTRIES, Utilities.PRICE, values);
        Toast.makeText(getContext(), "resultante " + result, Toast.LENGTH_LONG).show();
        db.close();

        getFragmentManager().beginTransaction().remove(NewCarFragment.this).commit();

    }

}