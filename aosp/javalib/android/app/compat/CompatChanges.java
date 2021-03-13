package android.app.compat;

import android.annotation.SystemApi;
import android.compat.Compatibility;
import android.os.UserHandle;

@SystemApi
public final class CompatChanges {
  private static final ChangeIdStateCache QUERY_CACHE = new ChangeIdStateCache();
  
  public static boolean isChangeEnabled(long paramLong) {
    return Compatibility.isChangeEnabled(paramLong);
  }
  
  public static boolean isChangeEnabled(long paramLong, int paramInt) {
    return ((Boolean)QUERY_CACHE.query(ChangeIdStateQuery.byUid(paramLong, paramInt))).booleanValue();
  }
  
  public static boolean isChangeEnabled(long paramLong, String paramString, UserHandle paramUserHandle) {
    return ((Boolean)QUERY_CACHE.query(ChangeIdStateQuery.byPackageName(paramLong, paramString, paramUserHandle.getIdentifier()))).booleanValue();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/compat/CompatChanges.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */