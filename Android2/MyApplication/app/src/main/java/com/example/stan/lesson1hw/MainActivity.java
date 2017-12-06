package com.example.stan.lesson1hw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final Random rnd = new Random();
    List<String> elements;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elements = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            elements.add("Element" + i);
        }
        listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.menu_edit:
                editElement(info.position);
                return true;
            case R.id.menu_delete:
                deleteElement(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteElement(int position) {
        String msg = "Pressed delete: showing sum " + (rnd.nextInt(100) + rnd.nextInt(100));
        showInToast(msg);
    }

    private void showInToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void editElement(int position) {
        String msg = "Pressed edit: showing multiplication " + (rnd.nextInt(100) * rnd.nextInt(100));
        showInToast(msg);
    }

    public void showPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_add:
                        addElement();
                        return true;
                    case R.id.menu_clear:
                        clearElement();
                        return true;
                    case R.id.menu_something:
                        somethingElement();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.inflate(R.menu.mymenu);
        popupMenu.show();
    }

    private void addElement() {
        String msg = "Pressed add: showing division " + (rnd.nextInt(100) / rnd.nextInt(100));
        showInToast(msg);
    }

    private void clearElement() {
        String msg = "Pressed clear: showing rest of division " + (rnd.nextInt(100) % rnd.nextInt(100));
        showInToast(msg);
    }

    private void somethingElement() {
        String msg = "Pressed something: showing min " + Math.min(rnd.nextInt(100), rnd.nextInt(100));
        showInToast(msg);
    }


}