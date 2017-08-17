package com.mal.walid.moviewalid;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.util.Log;

/**
 * Created by walid4444 on 9/15/16.
 */
public class SettingsActivity extends PreferenceActivity {
    protected static void setSortedByData(ListPreference lp) {
        CharSequence[] entries = {"most popular", "top rated", "favourite"};
        CharSequence[] entryValues = {"popular", "top_rated", "favourite"};
        lp.setEntries(entries);
        lp.setDefaultValue("popular");
        lp.setEntryValues(entryValues);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            addPreferencesFromResource(R.xml.sortedby);
        } catch (Exception e) {
            Log.e(getLocalClassName(), e.toString());
        }
        final ListPreference sortedby = (ListPreference) findPreference("sortedby");
        setSortedByData(sortedby);
    }
}
