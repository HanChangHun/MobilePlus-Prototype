package android.app;

import android.util.ArrayMap;

public class PausedNotedAppOpsCollection {
  final ArrayMap<String, long[]> mCollectedNotedAppOps;
  
  final int mUid;
  
  PausedNotedAppOpsCollection(int paramInt, ArrayMap<String, long[]> paramArrayMap) {
    this.mUid = paramInt;
    this.mCollectedNotedAppOps = paramArrayMap;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$PausedNotedAppOpsCollection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */