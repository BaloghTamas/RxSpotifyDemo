package hu.bme.aut.android.spotifydemo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import hu.bme.aut.android.spotifydemo.R;
import hu.bme.aut.android.spotifydemo.SpotifyDemoApplication;
import hu.bme.aut.android.spotifydemo.ui.artists.ArtistsActivity;

public class MainActivity extends AppCompatActivity implements MainScreen {

    public static final String KEY_ARTIST = "KEY_ARTIST";
    @Inject
    MainPresenter presenter;
    private EditText etArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpotifyDemoApplication.injector.inject(this);

        etArtist = (EditText) findViewById(R.id.etArtist);

        Button btnShowSongs = (Button) findViewById(R.id.btnShowArtists);
        btnShowSongs.setOnClickListener(v -> presenter.showArtistsSearchList(etArtist.getText().toString()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attach(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detach();
    }

    @Override
    public void showArtists(String artistSearchTerm) {
        Intent intent = new Intent(MainActivity.this, ArtistsActivity.class);
        intent.putExtra(KEY_ARTIST, artistSearchTerm);
        startActivity(intent);
    }
}
