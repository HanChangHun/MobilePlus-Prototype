package android.database.sqlite;

import android.app.ActivityThread;
import android.app.Application;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.KeyValueListParser;
import android.util.Log;

public class SQLiteCompatibilityWalFlags {
  private static final String TAG = "SQLiteCompatibilityWalFlags";
  
  private static volatile boolean sCallingGlobalSettings;
  
  private static volatile boolean sInitialized;
  
  private static volatile boolean sLegacyCompatibilityWalEnabled;
  
  private static volatile long sTruncateSize = -1L;
  
  private static volatile String sWALSyncMode;
  
  public static long getTruncateSize() {
    initIfNeeded();
    return sTruncateSize;
  }
  
  public static String getWALSyncMode() {
    initIfNeeded();
    if (sLegacyCompatibilityWalEnabled)
      return sWALSyncMode; 
    throw new IllegalStateException("isLegacyCompatibilityWalEnabled() == false");
  }
  
  public static void init(String paramString) {
    StringBuilder stringBuilder;
    if (TextUtils.isEmpty(paramString)) {
      sInitialized = true;
      return;
    } 
    KeyValueListParser keyValueListParser = new KeyValueListParser(',');
    try {
      keyValueListParser.setString(paramString);
      sLegacyCompatibilityWalEnabled = keyValueListParser.getBoolean("legacy_compatibility_wal_enabled", false);
      sWALSyncMode = keyValueListParser.getString("wal_syncmode", SQLiteGlobal.getWALSyncMode());
      sTruncateSize = keyValueListParser.getInt("truncate_size", -1);
      stringBuilder = new StringBuilder();
      stringBuilder.append("Read compatibility WAL flags: legacy_compatibility_wal_enabled=");
      stringBuilder.append(sLegacyCompatibilityWalEnabled);
      stringBuilder.append(", wal_syncmode=");
      stringBuilder.append(sWALSyncMode);
      Log.i("SQLiteCompatibilityWalFlags", stringBuilder.toString());
      sInitialized = true;
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Setting has invalid format: ");
      stringBuilder1.append((String)stringBuilder);
      Log.e("SQLiteCompatibilityWalFlags", stringBuilder1.toString(), illegalArgumentException);
      sInitialized = true;
      return;
    } 
  }
  
  private static void initIfNeeded() {
    Application application1;
    if (sInitialized || sCallingGlobalSettings)
      return; 
    ActivityThread activityThread = ActivityThread.currentActivityThread();
    if (activityThread == null) {
      activityThread = null;
    } else {
      application1 = activityThread.getApplication();
    } 
    Application application2 = null;
    if (application1 == null) {
      Log.w("SQLiteCompatibilityWalFlags", "Cannot read global setting sqlite_compatibility_wal_flags - Application state not available");
      application1 = application2;
    } else {
      try {
        sCallingGlobalSettings = true;
        String str = Settings.Global.getString(application1.getContentResolver(), "sqlite_compatibility_wal_flags");
        sCallingGlobalSettings = false;
        return;
      } finally {
        sCallingGlobalSettings = false;
      } 
    } 
    init((String)application1);
  }
  
  public static boolean isLegacyCompatibilityWalEnabled() {
    initIfNeeded();
    return sLegacyCompatibilityWalEnabled;
  }
  
  public static void reset() {
    sInitialized = false;
    sLegacyCompatibilityWalEnabled = false;
    sWALSyncMode = null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteCompatibilityWalFlags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */