package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseActivity extends AppCompatActivity {
    Button delete, update;

    ListView listView;
    private DBAdapter dbAdapter;
    private SQLiteDatabase db;

    String selectedID;

    ArrayList<String> ordersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        delete = findViewById(R.id.btnDelete);
        update = findViewById(R.id.btnUpdate);

        listView = findViewById(R.id.lvCustomerDatabase);
        listView.setItemsCanFocus(true);

        dbAdapter = new DBAdapter(this);
        db = dbAdapter.getWritableDatabase();

        populateListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Selects the item in the listview
/*
                listView.setItemChecked(position, true);
                listView.setSelection(position);
*/

                //Regex to select the ID from the order so I can find it in the DB
                selectedID = String.valueOf(listView.getItemAtPosition(position));
                Pattern pattern = Pattern.compile("Order ID:(\\d+)");
                Matcher matcher = pattern.matcher(selectedID);
                if (matcher.find()) {
                    selectedID = matcher.group(1);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rowDeleted = db.delete(DBAdapter.DBTABLE, DBAdapter.ORDER_ID + "=" + selectedID, null);
                populateListView();
                Toast.makeText(DatabaseActivity.this, rowDeleted + " rows deleted", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void populateListView() {
        // Query the database to retrieve all the pizza orders
        Cursor cursor = db.query(DBAdapter.DBTABLE, null, null, null, null, null, null);

        // Create an array list to store the pizza orders
        ArrayList<String> ordersList = new ArrayList<>();

        // Iterate through the cursor and add each pizza order to the array list
        while (cursor.moveToNext()) {
            int orderID = cursor.getInt(cursor.getColumnIndex(DBAdapter.ORDER_ID));
            String customerName = cursor.getString(cursor.getColumnIndex(DBAdapter.CUSTOMER_NAME));
            String pizzaSize = cursor.getString(cursor.getColumnIndex(DBAdapter.PIZZA_SIZE));
            String pizzaTopping = cursor.getString(cursor.getColumnIndex(DBAdapter.PIZZA_TOPPING));
            String orderDateTime = cursor.getString(cursor.getColumnIndex(DBAdapter.ORDER_DATE_TIME));

            String orderText = "Order ID:" + orderID + "\n" +
                    "Customer: " + customerName + "\n"
                    + "Size: " + pizzaSize + "\n"
                    + "Toppings: " + pizzaTopping + "\n"
                    + "Order Time: " + orderDateTime;
            ordersList.add(orderText);
        }

        // Create an adapter to populate the ListView with the pizza orders
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ordersList);
        listView.setAdapter(adapter);
    }
}