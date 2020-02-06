package fr.epsi.gostyle;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private Utilisateur user1 = new Utilisateur();


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("fr.epsi.gostyle", appContext.getPackageName());
    }



    @Test
    public void affichagePageConnexionTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        appContext.getSystemService("MainActivity");
    }
}
