package android.app;

import java.util.LinkedHashMap;
import java.util.Map;

class null extends LinkedHashMap<Query, Result> {
  null(int paramInt1, float paramFloat, boolean paramBoolean) {
    super(paramInt1, paramFloat, paramBoolean);
  }
  
  protected boolean removeEldestEntry(Map.Entry paramEntry) {
    boolean bool;
    if (size() > maxEntries) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PropertyInvalidatedCache$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */