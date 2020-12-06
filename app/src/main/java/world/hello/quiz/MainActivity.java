package world.hello.quiz;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enables Always-on
    }

    public void doStartTesting(View view){
        Intent intent = new Intent(MainActivity.this, Testing.class);
        startActivity(intent);
    }

    public void doTerminateApp(View view){
        finish();
        System.exit(0);
    }
}