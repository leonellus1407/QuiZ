package world.hello.quiz;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ShowResultActivity extends AppCompatActivity {

    GridView gvMain;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        DBController db = new DBController(getApplicationContext());
        adapter = new ArrayAdapter<>(this, R.layout.grid_item, R.id.value, db.getAllResults());
        gvMain = (GridView) findViewById(R.id.gvMain);
        gvMain.setAdapter(adapter);
        gvMain.setNumColumns(2);
        // Enables Always-on
    }
}