package android.app.timezonedetector;

public interface TimeZoneDetector {
  static ManualTimeZoneSuggestion createManualTimeZoneSuggestion(String paramString1, String paramString2) {
    ManualTimeZoneSuggestion manualTimeZoneSuggestion = new ManualTimeZoneSuggestion(paramString1);
    manualTimeZoneSuggestion.addDebugInfo(new String[] { paramString2 });
    return manualTimeZoneSuggestion;
  }
  
  void suggestManualTimeZone(ManualTimeZoneSuggestion paramManualTimeZoneSuggestion);
  
  void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion paramTelephonyTimeZoneSuggestion);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezonedetector/TimeZoneDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */