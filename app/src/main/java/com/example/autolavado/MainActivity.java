package com.example.autolavado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.autolavado.adapters.EntriesAdapter;
import com.example.autolavado.models.Entry;
import com.example.autolavado.sqlite.ConnectionSQLite;
import com.example.autolavado.utilities.Utilities;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment newCarFragment;
    FloatingActionButton floatingActionButton;

    RecyclerView recyclerView;
    EntriesAdapter entriesAdapter;
    public ArrayList<Entry> listEntries;
    ConnectionSQLite connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void connection() {
        connection = new ConnectionSQLite(this, Utilities.DB_NAME, null, 1);
    }


    public void init() {
        connection();
        get_entries();
        fragmentManager = getSupportFragmentManager();
        newCarFragment = new NewCarFragment();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_view_entries);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        entriesAdapter = new EntriesAdapter(this, listEntries);
        recyclerView.setAdapter(entriesAdapter);

    }

    private void get_entries() {
        SQLiteDatabase db = connection.getReadableDatabase();
        Entry entry = null;
        listEntries = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilities.TABLE_ENTRIES,null);
        while (cursor.moveToNext()){
            entry = new Entry();
            entry.setPrice(cursor.getString(0));
            entry.setDescription(cursor.getString(1));
            listEntries.add(entry);
        }
        db.close();
    }

    public void open_form_new_car(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, newCarFragment);
        fragmentTransaction.commit();
        floatingActionButton = view.findViewById(R.id.fab_new_car);
        floatingActionButton.hide();
    }

}