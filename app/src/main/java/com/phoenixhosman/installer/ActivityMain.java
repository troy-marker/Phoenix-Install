package com.phoenixhosman.installer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import static android.view.View.inflate;

/**
 * Activity Main class.
 * The main class for the App
 * @author Troy L. Marker
 * @version 1.0.0
 */
public class ActivityMain extends AppCompatActivity implements View.OnClickListener {
    private EditText etCoName;
    private TextView tvCoName;
    private EditText etCoAddress;
    private EditText etCoCity;
    private EditText etCoState;
    private EditText etCoZip;
    private EditText etApiUrl;
    private TextView tvApiUrl;
    private EditText etLockPass;

    /**
     * onCreate method.
     * Used if activity is destroyed
     *
     * @param savedInstanceState current saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        etCoName = findViewById(R.id.etCoName);
        tvCoName = findViewById(R.id.tvCoName);
        etCoAddress = findViewById(R.id.etCompanyAddress);
        etCoCity = findViewById(R.id.etCompanyCity);
        etCoState = findViewById(R.id.etCompanyState);
        etCoZip = findViewById(R.id.etCompanyZip);
        etApiUrl = findViewById(R.id.etAPIUrl);
        tvApiUrl = findViewById(R.id.tvAPIUrl);
        etLockPass = findViewById(R.id.etLockPass);
        Button btnInstall = findViewById (R.id.btnInstall);
        Button btnLoad = findViewById (R.id.btnLoad);
        Button btnExit = findViewById (R.id.btnExit);
        btnInstall.setOnClickListener (this);
        btnLoad.setOnClickListener (this);
        btnExit.setOnClickListener (this);
        fillSettings(loadSettings ());
    }

    /**
     * onClick Listener
     * Listens for and handles click to the various view.
     *
     * @param view - the view being clicked.
     */
    @Override
    public void onClick(View view) {
        ObjectSettings settings;
        switch (view.getId()) {
            case R.id.btnInstall:
                settings = getSettings();
                if (checkSettings(settings)) {
                    saveSettings (settings);
                    clearEntries();
                }
                break;
            case R.id.btnLoad:
                fillSettings (loadSettings ());
                break;
            case R.id.btnExit:
                finishAffinity ();
            default:
        }
    }

    /**
     * Method to pull the settings from the input controls.
     *
     * @return ObjectSetting object containing the entered settings
     */
    private ObjectSettings getSettings () {
        ObjectSettings retval = new ObjectSettings();
        retval.setCoName( etCoName.getText().toString());
        retval.setCoAddress( etCoAddress.getText().toString());
        retval.setCoCity (etCoCity.getText().toString());
        retval.setCoState ( etCoState.getText().toString());
        retval.setCoZip ( etCoZip.getText().toString());
        retval.setApiUrl ( etApiUrl.getText().toString());
        retval.setLockPass (etLockPass.getText().toString());
        return retval;
    }

    /**
     * Method to ensure all settings have a value
     *
     * @param settings ObjectSetting object containing the settings
     * @return boolean indicating success or failure of the check
     */
    private Boolean checkSettings (ObjectSettings settings) {
        boolean retval = true;
        if (settings.getCoName ().isEmpty() ||
                settings.getCoAddress ().isEmpty() ||
                settings.getCoCity ().isEmpty() ||
                settings.getCoState ().isEmpty() ||
                settings.getCoZip ().isEmpty() ||
                settings.getApiUrl ().isEmpty() ||
                settings.getLockPass().isEmpty()) {
            Error ("\nAll settings are required,");
            retval = false;
        }
        return retval;
    }

    /**
     * Method to save the setting to the content provider.
     *
     * @param settings ObjectSetting object containing the settings
     */
    private void saveSettings (ObjectSettings settings) {
        getContentResolver ().delete(ProviderSettings.CONTENT_URI, null, null);
        ContentValues values = new ContentValues ();
        values.put(ProviderSettings.coname, settings.getCoName());
        values.put(ProviderSettings.coaddress, settings.getCoAddress ());
        values.put(ProviderSettings.cocity, settings.getCoCity ());
        values.put(ProviderSettings.costate, settings.getCoState ());
        values.put(ProviderSettings.cozip, settings.getCoZip ());
        values.put(ProviderSettings.apiurl, settings.getApiUrl ());
        values.put(ProviderSettings.lockpass, settings.getLockPass());
        getContentResolver().insert(ProviderSettings.CONTENT_URI, values);
    }

    /**
     * Method to load the settings form the content provider
     *
     * @return ObjectSetting object containing the settings
     */
    private ObjectSettings loadSettings () {
        ObjectSettings return_value = new ObjectSettings();
        @SuppressLint ("Recycle") Cursor cursor = getContentResolver().query(Uri.parse("content://com.phoenixhosman.installer.ProviderSettings/settings"), null, null, null, null, null);
        assert cursor != null;
        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                return_value.setCoName(cursor.getString(cursor.getColumnIndex("coname")));
                return_value.setCoAddress(cursor.getString(cursor.getColumnIndex("coaddress")));
                return_value.setCoCity(cursor.getString(cursor.getColumnIndex("cocity")));
                return_value.setCoState(cursor.getString(cursor.getColumnIndex("costate")));
                return_value.setCoZip(cursor.getString(cursor.getColumnIndex("cozip")));
                return_value.setApiUrl(cursor.getString(cursor.getColumnIndex("apiurl")));
                return_value.setLockPass(cursor.getString(cursor.getColumnIndex("lockpass")));
                cursor.moveToNext();
            }
        } else {
            return_value.setCoName(null);
            return_value.setCoAddress(null);
            return_value.setCoCity(null);
            return_value.setCoState(null);
            return_value.setCoZip(null);
            return_value.setApiUrl(null);
            return_value.setLockPass(null);
        }
        return return_value;
    }

    /**
     * Method to put the setting into the input controls
     *
     * @param settings ObjectSetting object containing the settings
     */
    private void fillSettings (ObjectSettings settings) {
        etCoName.setText(settings.getCoName ());
        etCoAddress.setText(settings.getCoAddress ());
        etCoCity.setText(settings.getCoCity ());
        etCoState.setText(settings.getCoState ());
        etCoZip.setText(settings.getCoZip ());
        etApiUrl.setText(settings.getApiUrl ());
        etLockPass.setText(settings.getLockPass());
    }

    /**
     * Method to clear the input controls
     */
    private void clearEntries () {
        etCoName.setText("");
        etCoAddress.setText("");
        etCoCity.setText("");
        etCoState.setText("");
        etCoZip.setText("");
        etApiUrl.setText("");
        etLockPass.setText("");
    }

    /**
     * Method to display error message on screen
     *
     * @param strError string containing the error message
     */
    @SuppressWarnings ("SameParameterValue")
    private void Error (String strError) {
        androidx.appcompat.app.AlertDialog.Builder mBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        View view = inflate(this, R.layout.dialog_error, null);
        Button btnExit = view.findViewById(R.id.btnExitButton);
        Button btnError = view.findViewById(R.id.btnErrorMessage);
        btnError.setText(getString(R.string.error, strError ));
        mBuilder.setView(view);
        final AlertDialog dialog = mBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
