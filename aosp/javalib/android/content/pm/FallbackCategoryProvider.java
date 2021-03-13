package android.content.pm;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FallbackCategoryProvider {
  private static final String TAG = "FallbackCategoryProvider";
  
  private static final ArrayMap<String, Integer> sFallbacks = new ArrayMap();
  
  public static int getFallbackCategory(String paramString) {
    return ((Integer)sFallbacks.getOrDefault(paramString, Integer.valueOf(-1))).intValue();
  }
  
  public static void loadFallbacks() {
    sFallbacks.clear();
    if (SystemProperties.getBoolean("fw.ignore_fb_categories", false)) {
      Log.d("FallbackCategoryProvider", "Ignoring fallback categories");
      return;
    } 
    AssetManager assetManager = new AssetManager();
    assetManager.addAssetPath("/system/framework/framework-res.apk");
    Resources resources = new Resources(assetManager, null, null);
    try {
      BufferedReader bufferedReader = new BufferedReader();
      null = new InputStreamReader();
      this(resources.openRawResource(17825796));
      this(null);
      try {
        while (true) {
          String str = bufferedReader.readLine();
          if (str != null) {
            if (str.charAt(0) == '#')
              continue; 
            String[] arrayOfString = str.split(",");
            if (arrayOfString.length == 2)
              sFallbacks.put(arrayOfString[0], Integer.valueOf(Integer.parseInt(arrayOfString[1]))); 
            continue;
          } 
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Found ");
          stringBuilder.append(sFallbacks.size());
          stringBuilder.append(" fallback categories");
          Log.d("FallbackCategoryProvider", stringBuilder.toString());
          return;
        } 
      } finally {
        try {
          bufferedReader.close();
        } finally {
          bufferedReader = null;
        } 
      } 
    } catch (IOException|NumberFormatException iOException) {
      Log.w("FallbackCategoryProvider", "Failed to read fallback categories", iOException);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/FallbackCategoryProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */