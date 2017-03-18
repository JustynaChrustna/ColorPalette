package justynachrustna.colorpalette;

import android.content.Intent;
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
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ColorActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";
    public static final String COLOR_IN_HEX_KEY = "Color in hex:";
    public static final String OLD_COLOR_KEY = "old_color_key";
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
    @BindView(R.id.redLabel)
    TextView redLabel;
    @BindView(R.id.greenLabel)
    TextView greenLabel;
    @BindView(R.id.blueLabel)
    TextView blueLabel;

    private int red;
    private int green;
    private int blue;

    private ActionBar actionBar;
    private Random random = new Random();
    private String oldColor;

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
        Intent intent = getIntent();
        oldColor = intent.getStringExtra(OLD_COLOR_KEY);
        if (oldColor != null) {
            int color = Color.parseColor(oldColor);
            red = Color.red(color);
            green = Color.green(color);
            blue = Color.blue(color);
            updateSeekBars();
            updateBackgroundColor();
            generateButton.setVisibility(View.GONE);
            actionBar.setTitle(R.string.edit_color);
        }


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
    public void generate() {
        red = random.nextInt(256);
        green = random.nextInt(256);
        blue = random.nextInt(256);
        updateSeekBars();

        updateBackgroundColor();

    }

    private void updateSeekBars() {
        redSeekBar.setProgress(red);
        greenSeekBar.setProgress(green);
        blueSeekBar.setProgress(blue);
    }

    private void updateBackgroundColor() {
        int color = Color.rgb(red, green, blue);
        int textColor=PaletteActivity.getTextColorFromColor(color);
        redLabel.setTextColor(textColor);
        greenLabel.setTextColor(textColor);
        blueLabel.setTextColor(textColor);
        colorLinearLayout.setBackgroundColor(color);
    }

    @OnClick(R.id.saveButton)
    public void save() {
        Intent data = new Intent();
        data.putExtra(COLOR_IN_HEX_KEY, String.format("#%02X%02X%02X", red, green, blue));
        if (oldColor != null) {
            data.putExtra(OLD_COLOR_KEY, oldColor);
        }
        setResult(RESULT_OK, data);
        finish();
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.redSeekBar:
                red = progress;
                break;
            case R.id.greenSeekBar:
                green = progress;
                break;
            case R.id.blueSeekBar:
                blue = progress;
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(RED, red);
        outState.putInt(GREEN, green);
        outState.putInt(BLUE, blue);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        red = savedInstanceState.getInt(RED);
        green = savedInstanceState.getInt(GREEN);
        blue = savedInstanceState.getInt(BLUE);

    }
}
