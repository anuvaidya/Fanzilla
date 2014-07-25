package com.fantasysportsgroupproject.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity{
    private Button btnLogin;
    private Button btnStart;
    private ImageView imvLogo;
    private static final String tag = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupButton();
    }

    private void setupButton() {
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(btnLoginListener);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(btnStartListener);
    }

    private View.OnClickListener btnLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*Intent i = new Intent();
            startActivity(i); */
            /* Questions: Use Oauth so that the user doesn't have to pass the username and the
            password
            2. do we need another screen for login?
            Intent i = new Intent(getBaseContext(),LoginActivity.class);
            i.putExtra(userName,"username");
            i.putExtra(screenName,"screenName");
            startActivityForResult(i, REQUEST_CODE); */
            Log.d(tag,"Main Activity screen success");
        }
    };

    private View.OnClickListener btnStartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(tag,"On Click start search Activity class");
            Intent i = new Intent(getBaseContext(),SearchActivity.class);
            startActivity(i);
        }
    };
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
