package com.cormaclavery.tuneaday.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cormaclavery.tuneaday.R;
import com.cormaclavery.tuneaday.model.Tune;
import com.cormaclavery.tuneaday.model.TuneBook;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.randomTuneButton) Button mRandomTuneButton;
    @Bind(R.id.titleView) TextView mTitleView;
    @Bind(R.id.abcView) TextView mAbcView;

    private Tune mTune;
    private TuneBook mTunebook;
    private Random rn = new Random();
    private int index;
    private int TuneBookSize;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Gson mGson = new Gson();


        BufferedReader br = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.tunes)));
        mTunebook = mGson.fromJson(br, TuneBook.class);

        TuneBookSize = mTunebook.getTuneList().size();



        mRandomTuneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRandomTune();
                updateDisplay();
            }
        });

    }

    private String getTune(TuneBook tuneBook){
        return null;
    }

    private Tune setTune(String jsonData) throws JSONException{

        JSONObject jsonTune = new JSONObject(jsonData);

        Tune tune = new Tune();
        tune.setName(jsonTune.getString("name"));
        tune.setTune(jsonTune.getString("title"));
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

    private void setRandomTune(){

        index = rn.nextInt(TuneBookSize -1);
        mTune = mTunebook.getTuneAtIndex(index);


        if(mTune.getAbc()==null){
            setRandomTune();
        }
        if(mTune.getName().length()>5) {
            if (mTune.getName().substring(mTune.getName().length() - 5).equals(", The")) {
                mTune.setName("The " + mTune.getName().substring(0, mTune.getName().length() - 5));
            }
        }

    }

}
