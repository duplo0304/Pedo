package com.projektarbeit.duplo.pedo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    public final static String EXTRA_MESSAGE = "com.projektarbeit.duplo.pedo.MESSAGE"; // Key für Intent in public Variable = good practice
    ImageButton imgButton;
    private static final int SETTINGS_RESULT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton1);
        btn1.setOnClickListener(this);

        ImageButton btn2 = (ImageButton)findViewById(R.id.imageButton2);
        btn2.setOnClickListener(this);

        ImageButton btn3 = (ImageButton)findViewById(R.id.imageButton3);
        btn3.setOnClickListener(this);

        ImageButton btn4 = (ImageButton)findViewById(R.id.imageButton4);
        btn4.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            /*
            case R.id.action_search:

                //openSearch();
                Toast.makeText(this, "Suche wurde ausgewählt", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_refresh:
                //openRefresh();
                Toast.makeText(this,"Aktualisieren wurde ausgewählt", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_settings:
                //openSettings();
                Toast.makeText(this,"Einstellungen wurde ausgewählt", Toast.LENGTH_SHORT).show();
                return true;

             */
            case R.id.action_exit:
                System.exit(0);

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /** Wird aufgerufen, wenn User den 'Senden' Button drückt **/
    /*
    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class); //(Context, da-soll-System-den-Intent-Hinschicken
        EditText editText = (EditText) findViewById(R.id.edit_message); // Inhalt des Wertes bekommen

        if (editText.getText().toString().trim().length()==0){
            // überprüft, ob Eingabe leer ist; falls ja nicht weiter & Toast geworfen
            Context context = getApplicationContext();
            CharSequence text = " Feld darf nicht leer sein";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context,text, duration);
            toast.show();
        }

        else {
            String message = editText.getText().toString(); //lokale Variable 'Message" zum Speichern des String
            intent.putExtra(EXTRA_MESSAGE, message); //putExtra zum Anhängen des Textwerts an Intent
            startActivity(intent);
        }

    } */

    @Override
    public void onClick(View v) {
        Toast pieceToast=null;

        switch(v.getId()){
            case R.id.imageButton1:
                pieceToast= Toast.makeText(getApplicationContext(), "Image Button One Clicked", Toast.LENGTH_SHORT);
                pieceToast.show();
                break;

            case R.id.imageButton2:
                pieceToast= Toast.makeText(getApplicationContext(), "Image Button Two Clicked", Toast.LENGTH_SHORT);
                pieceToast.show();
                break;

            /*
            // öffnet Export-View
            case R.id.imageButton3:
                Intent intent3 = new Intent(this, ExportActivity.class);
                startActivity(intent3);
                break;
            */
            case R.id.imageButton4:
                Intent los = new Intent(this, DeviceScanActivity.class);
                startActivity(los);
                break;

            default:
                break;
        }
    }


}
