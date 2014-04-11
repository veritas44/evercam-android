package io.evercam.android.rvideo;

import io.evercam.android.utils.Constants;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceActivity;
import android.view.Menu;
import com.bugsense.trace.BugSenseHandler;
import io.evercam.android.R;
import com.google.analytics.tracking.android.EasyTracker;

public class RVideoPrefsActivity extends PreferenceActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		if (Constants.isAppTrackingEnabled) if (Constants.isAppTrackingEnabled) BugSenseHandler
				.initAndStartSession(this, Constants.bugsense_ApiKey);

		try
		{
			this.getActionBar().hide();
		}
		catch (Exception e)
		{
		}

		addPreferencesFromResource(R.layout.ivideopreferences);
		// Get the custom preference
		this.setDefaultKeyMode(DEFAULT_KEYS_DISABLE);

		SharedPreferences sharedprefs = PreferenceManager.getDefaultSharedPreferences(this);

		ListPreference prefNetwork = (ListPreference) this.findPreference("pref_enablocalnetwork");
		prefNetwork.setKey("pref_enablocalnetwork" + RVideoViewActivity.camera.getCameraID());
		prefNetwork.setValue(sharedprefs.getString("pref_enablocalnetwork"
				+ RVideoViewActivity.camera.getCameraID(), "0"));
		prefNetwork.setSummary(prefNetwork.getEntry());
		prefNetwork.setOnPreferenceChangeListener(new ListPreference.OnPreferenceChangeListener(){

			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue)
			{
				// TODO Auto-generated method stub
				((ListPreference) preference).setValue((String) newValue);
				preference.setSummary(((ListPreference) preference).getEntry());
				return true;
			}
		});
	}

	private String getTextLocalNetwork()
	{
		String text = null;

		return text;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu m)
	{
		return false;
	}

	@Override
	public void onStart()
	{
		super.onStart();

		if (Constants.isAppTrackingEnabled)
		{
			EasyTracker.getInstance().activityStart(this);
			if (Constants.isAppTrackingEnabled) BugSenseHandler.startSession(this);
		}
	}

	@Override
	public void onStop()
	{
		super.onStop();

		if (Constants.isAppTrackingEnabled)
		{
			EasyTracker.getInstance().activityStop(this);
			if (Constants.isAppTrackingEnabled) BugSenseHandler.closeSession(this);
		}
	}
}