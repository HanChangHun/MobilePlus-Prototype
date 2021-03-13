package android.content;

import java.util.Set;

public interface Editor {
  void apply();
  
  Editor clear();
  
  boolean commit();
  
  Editor putBoolean(String paramString, boolean paramBoolean);
  
  Editor putFloat(String paramString, float paramFloat);
  
  Editor putInt(String paramString, int paramInt);
  
  Editor putLong(String paramString, long paramLong);
  
  Editor putString(String paramString1, String paramString2);
  
  Editor putStringSet(String paramString, Set<String> paramSet);
  
  Editor remove(String paramString);
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SharedPreferences$Editor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */