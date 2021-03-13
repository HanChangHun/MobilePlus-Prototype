package android.app.timedetector;

import android.os.SystemClock;
import android.os.TimestampedValue;

public interface TimeDetector {
  static ManualTimeSuggestion createManualTimeSuggestion(long paramLong, String paramString) {
    ManualTimeSuggestion manualTimeSuggestion = new ManualTimeSuggestion(new TimestampedValue(SystemClock.elapsedRealtime(), Long.valueOf(paramLong)));
    manualTimeSuggestion.addDebugInfo(new String[] { paramString });
    return manualTimeSuggestion;
  }
  
  void suggestManualTime(ManualTimeSuggestion paramManualTimeSuggestion);
  
  void suggestNetworkTime(NetworkTimeSuggestion paramNetworkTimeSuggestion);
  
  void suggestTelephonyTime(TelephonyTimeSuggestion paramTelephonyTimeSuggestion);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timedetector/TimeDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */