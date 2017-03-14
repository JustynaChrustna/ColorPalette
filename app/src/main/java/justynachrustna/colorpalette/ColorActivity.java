package justynachrustna.colorpalette;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.Random;

import butterknife.ButterKnife;

import butterknife.BindView;
import butterknife.OnClick;

public class ColorActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";
    @BindView(R.id.redSeekBar)
    SeekBar redSeekBar;
    @BindView(R.id.greenSeekBar)
    SeekBar greenSeekBar;
    @BindView(R.id.blueSeekBar)
    SeekBar blueSeekBar;
    @BindView(R.id.generateButton)
    Button generateButton;
    @BindView(R.id.saveButton)
    Button saveButton;
    @BindView(R.id.colorLinearLayout)
    LinearLayout colorLinearLayout;

    private int red;
    private int green;
    private int blue;

    private ActionBar actionBar;
    private Random random= new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        ButterKnife.bind(this);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);//wyświetli się strzałka
        //obsługa kliknięcia na strzałkę
       redSeekBar.setOnSeekBarChangeListener(this);
        greenSeekBar.setOnSeekBarChangeListener(this);
        blueSeekBar.setOnSeekBarChangeListener(this);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);//przejście w górę
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @OnClick(R.id.generateButton)
    public void generate(){
        red=random.nextInt(256);
        green=random.nextInt(256);
        blue=random.nextInt(256);
        redSeekBar.setProgress(red);
        greenSeekBar.setProgress(green);
        blueSeekBar.setProgress(blue);

        updateBackgroundColor();

    }

    private void updateBackgroundColor() {
        int color= Color.rgb(red, green, blue);
        colorLinearLayout.setBackgroundColor(color);
    }

    @OnClick(R.id.saveButton)
    public void save(){

    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch(seekBar.getId()){
            case R.id.redSeekBar:
                red=progress;
                break;
            case R.id.greenSeekBar:
                red=progress;
                break;
            case R.id.blueSeekBar:
                red=progress;
                break;
        }
        updateBackgroundColor();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(RED, red);
        outState.putInt(GREEN, green);
        outState.putInt(BLUE, blue);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        red=savedInstanceState.getInt(RED);
        green=savedInstanceState.getInt(GREEN);
        blue=savedInstanceState.getInt(BLUE);

    }
}
