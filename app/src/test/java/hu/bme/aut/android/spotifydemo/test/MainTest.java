package hu.bme.aut.android.spotifydemo.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import hu.bme.aut.android.spotifydemo.ui.main.MainPresenter;
import hu.bme.aut.android.spotifydemo.ui.main.MainScreen;

import static hu.bme.aut.android.spotifydemo.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class MainTest {

    private MainPresenter mainPresenter;
    private MainScreen mainScreen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        mainScreen = mock(MainScreen.class);
        mainPresenter = new MainPresenter();
        mainPresenter.attach(mainScreen);
    }

    @Test
    public void testSearch() {
        String artist = "AC/DC";
        mainPresenter.showArtistsSearchList(artist);
        verify(mainScreen).showArtists(artist);
    }


    @After
    public void tearDown() {
        mainPresenter.detach();
    }

}