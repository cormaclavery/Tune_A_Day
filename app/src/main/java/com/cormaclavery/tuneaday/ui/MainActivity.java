package com.cormaclavery.tuneaday.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.cormaclavery.tuneaday.R;
import com.cormaclavery.tuneaday.tunes.Tune;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.randomTuneButton) Button mRandomTuneButton;
    @Bind(R.id.titleView) TextView mTitleView;
    @Bind(R.id.abcView) TextView mAbcView;

    private Tune mTune;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTune = new Tune("Annaghbeg, The", "|: F/G/ |\\\\\r\nA/B/A/F/ Ad | A/B/A/F/ Ad | ce B>c | BA FG |\r\nA/B/A/F/ Ad | A/A/F/ Ad|c>e Bc | ed d :|\r\n|: d/e/ |\\\\\r\nfg/f/ ed | fg/f/ ed | ce B>c |\r\n[1 BA F/G/A | f2 ed | ~f2 ed | c>e Bc | ed d :|\r\n[2 BA E/F/G |A/B/A/F/ Ad | ~A2 A/B/d | c>e Bc | ed d |]" );



        mRandomTuneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDisplay();
            }
        });

    }

    private Tune setTune(String jsonData) throws JSONException{

        JSONObject jsonTune = new JSONObject(jsonData);

        Tune tune = new Tune();
        tune.setName(jsonTune.getString("name"));
        tune.setId(jsonTune.getString("title"));
        tune.setType(jsonTune.getString("type"));
        tune.setMeter(jsonTune.getString("meter"));
        tune.setMode(jsonTune.getString("mode"));
        tune.setAbc(jsonTune.getString("abc"));

        return tune;

    }

    private void updateDisplay(){
        mTitleView.setText(mTune.getName());
        mAbcView.setText(mTune.getAbc());
    }

}
