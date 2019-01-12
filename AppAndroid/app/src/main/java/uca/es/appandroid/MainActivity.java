package uca.es.appandroid;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar itemmm clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(item.getItemId() == R.id.home)
        {
            finish();
            startActivity(getIntent());
        }
        if(item.getItemId() == R.id.attendees)
        {
            Intent i = new Intent(MainActivity.this, AttendeesActivity.class);
            startActivity(i);
        }
        if(item.getItemId() == R.id.program)
        {
            Intent i = new Intent(MainActivity.this, ProgramPpalActivity.class);
            startActivity(i);
        }
        if(item.getItemId() == R.id.dates)
            Toast.makeText(this,"Dates activity", Toast.LENGTH_SHORT).show();
        if(item.getItemId() == R.id.location)
            Toast.makeText(this,"Location activity", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}