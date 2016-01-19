package com.motorolasolutions.echee.customsettings;

import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    SeekBarPreference mSeekBarPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.app_settings);

        SwitchPreference locationSharing = (SwitchPreference) findPreference("pref_location_sharing");
        showIntervalSeekBar(locationSharing.isChecked());
        locationSharing.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {


        boolean switched = ((SwitchPreference) preference).isChecked();
        Log.i("Main", "switched to " + switched);

        showIntervalSeekBar(!switched);

        return true;
    }

    private void showIntervalSeekBar(boolean show) {
        PreferenceCategory preferenceScreen = (PreferenceCategory) findPreference("location_category");

        if (mSeekBarPreference == null) {
            mSeekBarPreference = new SeekBarPreference(this);
        }

        if (show) {
            preferenceScreen.addPreference(mSeekBarPreference);
        } else {
            preferenceScreen.removePreference(mSeekBarPreference);
        }
    }
}
