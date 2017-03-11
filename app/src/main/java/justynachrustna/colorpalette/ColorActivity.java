package justynachrustna.colorpalette;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class ColorActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);//wyświetli się strzałka
        //obsługa kliknięcia na strzałkę

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);//przejście w górę
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
