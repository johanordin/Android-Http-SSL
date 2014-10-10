package com.example.johanordin.lab4httpsslapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    public ProgressDialog loadingdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Http/SSL log", "Programmet har startat");
        Log.d("Http/SSL log", "This is a test");

        Button knapp1 = (Button)findViewById(R.id.knapp1);
        knapp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                buttonAction("https://www.liu.se/");
                /*
                Toast.makeText(getApplicationContext(),
                        "Knapp1 är tryckt", Toast.LENGTH_LONG).show();
                */
            }
        });
        Button knapp2 = (Button)findViewById(R.id.knapp2);
        knapp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonAction("https://tal-front.itn.liu.se/");

            }
        });

    }



    public void buttonAction(final String s_url) {

        loadingdialog = ProgressDialog.show(MainActivity.this, "", "Loading...", true);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //kod ska in här



                    showAlert(s_url, "HTTP Status: ");
                } catch (Exception e) {
                    //showAlert()
                    e.printStackTrace();
                    showAlert(s_url, "ERROR: " + e.getMessage());
                }

                loadingdialog.dismiss();
            }
        });

        thread.start();
    }

    public void showToast(final String toast) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void showAlert(final String header, final String message) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(header);
                alertDialog.setMessage(message);
                alertDialog.setCancelable(false);
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alertDialog.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
