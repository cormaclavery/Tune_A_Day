package com.cormaclavery.tuneaday.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cormaclavery.tuneaday.R;
import com.cormaclavery.tuneaday.model.Tune;
import com.cormaclavery.tuneaday.model.TuneBook;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends YouTubeBaseActivity {

    @Bind(R.id.randomTuneButton) Button mRandomTuneButton;
    @Bind(R.id.titleView) TextView mTitleView;
    @Bind(R.id.abcView) TextView mAbcView;
    @Bind(R.id.descriptionView) TextView mDescriptionView;
    @Bind(R.id.youtubePlayerView) YouTubePlayerView mYouTubePlayerView;

    private YouTubePlayer mYouTubePlayer;
    private YouTubePlayer.OnInitializedListener mOnInitializedListener;
    private Tune mTune;
    private TuneBook mTunebook;
    private Random rn = new Random();
    private int index;
    private int TuneBookSize;
    private String mTuneUrl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //parses in the tunes.JSON
        Gson mGson = new Gson();
        BufferedReader br = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.tunes)));
        mTunebook = mGson.fromJson(br, TuneBook.class);
        TuneBookSize = mTunebook.getTuneList().size();

        mYouTubePlayerView.setVisibility(View.INVISIBLE);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //youTubePlayer.cueVideo("dQw4w9WgXcQ");
                mYouTubePlayer = youTubePlayer;

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(MainActivity.this, "Please check your connection and try again later", Toast.LENGTH_LONG).show();
            }
        };

        mYouTubePlayerView.initialize("AIzaSyBoArySMvuNVw3W4tmKIhyftG4Xf9bmiH8",mOnInitializedListener);


        mYouTubePlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYouTubePlayerView.initialize("AIzaSyBoArySMvuNVw3W4tmKIhyftG4Xf9bmiH8",mOnInitializedListener);

            }
        });

        mRandomTuneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRandomTune();
                updateDisplay();
                mYouTubePlayer.cueVideo("ELoR4Qu9bpg");
                mYouTubePlayerView.setVisibility(View.VISIBLE);

            }
        });

    }


    //Updates the display
    private void updateDisplay(){
        mTitleView.setText(mTune.getName());
        mDescriptionView.setText(mTune.getType() + " in " + mTune.getMeter());
        mAbcView.setText(mTune.getAbc());

    }


    //uses random number generator to randomly select a tune.
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

    private void getSearchObjects(String search){

        String apiKey = "AIzaSyA0jSX9mrPUZN_Vkwh08RdesmkC1fZFB7A";


        String searchUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&q="+search+"&type=video&key="+apiKey;
        if(isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(searchUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                }
            });
        }
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo!=null && networkInfo.isConnected()){
            isAvailable = true;
        }

        return isAvailable;
    }

}
