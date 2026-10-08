package com.example.smartpantrymanager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Switch;

public class SettingsActivity extends Activity {

    private static final String PREFS_NAME = "SmartPantryPrefs";
    private static final String KEY_NOTIFICATIONS = "notifications_enabled";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32, 32, 32, 32);

        Switch switchNotifications = new Switch(this);
        switchNotifications.setText("Enable Pantry Expiry Notifications");

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isEnabled = prefs.getBoolean(KEY_NOTIFICATIONS, true);
        switchNotifications.setChecked(isEnabled);

        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(KEY_NOTIFICATIONS, isChecked);
            editor.apply();
        });

        layout.addView(switchNotifications);
        setContentView(layout);
    }
}