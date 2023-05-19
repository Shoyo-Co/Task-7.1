package com.example.task71;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.os.Bundle;
import android.os. Bundle;
import android.text. TextUtils;
import android. view. View;
import android.widget.Button;
import android.widget.EditText;
import android.widget .ListView;
import android.widget. Toast;
import java.util.List;

public class CreateActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ListView nameListView;
    private ItemAdapter itemAdapter;
    private EditText nameEditText, phoneEditText, descriptionEditText, dateEditText, locationEditText;
    private AlertDialog.Builder itemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);

        databaseHelper = new DatabaseHelper(context:this);
        phoneEditText = findViewById(R.id.phoneEditText);
        nameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        dateEditText = findViewById(R.id.dateEditText);
        locationEditText = findViewById(R.id.locationEditText);

        loadItems();
        Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }
    private void loadItems() {
        List<Item> items = databaseHelper.getAllItems();
        if (itemAdapter == null) {
            itemAdapter = new ItemAdapter(this, items);
            itemsListView.setAdapter(itemAdapter);
        }
        else{
            itemAdapter.clear();
            itemAdapter.addAll(items);
            itemAdapter.notifyDataSetChanged();
        }
    }
    private void addItem() {
        String name = nameEditText.getText ().toString();
        String phone = phoneEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String location = locationEditText.getText().toString();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) &&!TextUtils.isEmpty (description) && !TextUtils.isEmpty (date) && !TextUtils.isEmpty(location)) {
            Item newItem = new Item(id:0, name, phone, description, date, location);
            databaseHelper.insertItem(newItem);
            loadItems();

            nameEditText.setText("");
            phoneEditText.setText("");
            descriptionEditText.setText("");
            dateEditText.setText("");
            locationEditText.setText("");
        }
        else{
            Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
        }
    }
