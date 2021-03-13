package android.app.compat;

import android.app.PropertyInvalidatedCache;
import android.os.Binder;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.internal.compat.IPlatformCompat;

public final class ChangeIdStateCache extends PropertyInvalidatedCache<ChangeIdStateQuery, Boolean> {
  private static final String CACHE_KEY = "cache_key.is_compat_change_enabled";
  
  private static final int MAX_ENTRIES = 20;
  
  private static boolean sDisabled = false;
  
  public ChangeIdStateCache() {
    super(20, "cache_key.is_compat_change_enabled");
  }
  
  public static void disable() {
    sDisabled = true;
  }
  
  public static void invalidate() {
    if (!sDisabled)
      PropertyInvalidatedCache.invalidateCache("cache_key.is_compat_change_enabled"); 
  }
  
  protected Boolean recompute(ChangeIdStateQuery paramChangeIdStateQuery) {
    IPlatformCompat iPlatformCompat = IPlatformCompat.Stub.asInterface(ServiceManager.getService("platform_compat"));
    long l = Binder.clearCallingIdentity();
    try {
      if (paramChangeIdStateQuery.type == 0) {
        boolean bool = iPlatformCompat.isChangeEnabledByPackageName(paramChangeIdStateQuery.changeId, paramChangeIdStateQuery.packageName, paramChangeIdStateQuery.userId);
        Binder.restoreCallingIdentity(l);
        return Boolean.valueOf(bool);
      } 
      if (paramChangeIdStateQuery.type == 1) {
        boolean bool = iPlatformCompat.isChangeEnabledByUid(paramChangeIdStateQuery.changeId, paramChangeIdStateQuery.uid);
        Binder.restoreCallingIdentity(l);
        return Boolean.valueOf(bool);
      } 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Invalid query type: ");
      stringBuilder.append(paramChangeIdStateQuery.type);
      this(stringBuilder.toString());
      throw illegalArgumentException;
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
      Binder.restoreCallingIdentity(l);
      throw new IllegalStateException("Could not recompute value!");
    } finally {}
    Binder.restoreCallingIdentity(l);
    throw paramChangeIdStateQuery;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/compat/ChangeIdStateCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */