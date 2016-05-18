package hu.bme.aut.android.spotifydemo.ui.artists;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.android.spotifydemo.R;
import hu.bme.aut.android.spotifydemo.SpotifyDemoApplication;
import hu.bme.aut.android.spotifydemo.model.Item;
import hu.bme.aut.android.spotifydemo.ui.main.MainActivity;

public class ArtistsFragment extends Fragment implements ArtistsScreen {

    @Inject
    ArtistsPresenter presenter;
    private EditText etArtist;
    private RecyclerView recyclerViewArtists;
    private SwipeRefreshLayout swipeRefreshLayoutArtists;
    private TextView tvEmpty;
    private List<Item> artistsList;
    private ArtistsAdapter artistsAdapter;
    private String artist = "queen";

    public ArtistsFragment() {
        SpotifyDemoApplication.injector.inject(this);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        artist = getActivity().getIntent().getStringExtra(MainActivity.KEY_ARTIST);
        presenter.attach(this);
    }

    @Override
    public void onDetach() {
        presenter.detach();
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists, container, false);
        etArtist = (EditText) view.findViewById(R.id.etArtist);
        etArtist.setText(artist);
        tvEmpty = (TextView) view.findViewById(R.id.tvEmpty);
        recyclerViewArtists = (RecyclerView) view.findViewById(R.id.recyclerViewArtists);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewArtists.setLayoutManager(llm);

        artistsList = new ArrayList<>();
        artistsAdapter = new ArtistsAdapter(getContext(), artistsList);
        recyclerViewArtists.setAdapter(artistsAdapter);

        swipeRefreshLayoutArtists = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutArtists);

        swipeRefreshLayoutArtists.setOnRefreshListener(() -> {
            artist = etArtist.getText().toString();
            presenter.refreshArtists(artist);
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.refreshArtists(artist);
    }

    public void showArtists(List<Item> artists) {
        if (swipeRefreshLayoutArtists != null) {
            swipeRefreshLayoutArtists.setRefreshing(false);
        }

        artistsList.clear();
        artistsList.addAll(artists);
        artistsAdapter.notifyDataSetChanged();

        if (artistsList.isEmpty()) {
            recyclerViewArtists.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            recyclerViewArtists.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNetworkError(String errorMsg) {
        if (swipeRefreshLayoutArtists != null) {
            swipeRefreshLayoutArtists.setRefreshing(false);
        }
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
    }
}
