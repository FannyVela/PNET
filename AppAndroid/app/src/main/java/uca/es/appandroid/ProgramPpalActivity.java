package uca.es.appandroid;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProgramPpalActivity extends AppCompatActivity {

    TextView day1,day2,day3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_ppal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        day1 = (TextView)findViewById(R.id.day1);
        day2 = (TextView)findViewById(R.id.day2);
        day3 = (TextView)findViewById(R.id.day3);

        //Para subrayar los links
        day1.setPaintFlags(day1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        day2.setPaintFlags(day2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        day3.setPaintFlags(day3.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProgramPpalActivity.this, ProgramActivity.class);

                Bundle b = new Bundle();
                b.putString("day", "DIA 1 - Martes 7 de Mayo");
                b.putInt("numDay", 1);
                intent.putExtras(b);

                startActivity(intent);
            }
        });

        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProgramPpalActivity.this, ProgramActivity.class);

                Bundle b = new Bundle();
                b.putString("day", "DIA 2 - Martes 8 de Mayo");
                b.putInt("numDay", 2);
                intent.putExtras(b);

                startActivity(intent);
            }
        });

        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProgramPpalActivity.this, ProgramActivity.class);

                Bundle b = new Bundle();
                b.putString("day", "DIA 3 - Martes 9 de Mayo");
                b.putInt("day3", 3);
                intent.putExtras(b);

                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.home)
        {
            Intent i = new Intent(ProgramPpalActivity.this, MainActivity.class);
            startActivity(i);
        }
        if(item.getItemId() == R.id.attendees)
        {
            Intent i = new Intent(ProgramPpalActivity.this, AttendeesActivity.class);
            startActivity(i);
        }
        if(item.getItemId() == R.id.program)
        {
            finish();
            startActivity(getIntent());
        }
        if(item.getItemId() == R.id.dates)
            Toast.makeText(this,"Dates activity", Toast.LENGTH_SHORT).show();
        if(item.getItemId() == R.id.location)
            Toast.makeText(this,"Location activity", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }

}
