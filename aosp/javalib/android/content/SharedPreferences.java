package android.content;

import java.util.Map;
import java.util.Set;

public interface SharedPreferences {
  boolean contains(String paramString);
  
  Editor edit();
  
  Map<String, ?> getAll();
  
  boolean getBoolean(String paramString, boolean paramBoolean);
  
  float getFloat(String paramString, float paramFloat);
  
  int getInt(String paramString, int paramInt);
  
  long getLong(String paramString, long paramLong);
  
  String getString(String paramString1, String paramString2);
  
  Set<String> getStringSet(String paramString, Set<String> paramSet);
  
  void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener);
  
  void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener);
  
  public static interface Editor {
    void apply();
    
    Editor clear();
    
    boolean commit();
    
    Editor putBoolean(String param1String, boolean param1Boolean);
    
    Editor putFloat(String param1String, float param1Float);
    
    Editor putInt(String param1String, int param1Int);
    
    Editor putLong(String param1String, long param1Long);
    
    Editor putString(String param1String1, String param1String2);
    
    Editor putStringSet(String param1String, Set<String> param1Set);
    
    Editor remove(String param1String);
  }
  
  public static interface OnSharedPreferenceChangeListener {
    void onSharedPreferenceChanged(SharedPreferences param1SharedPreferences, String param1String);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SharedPreferences.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */