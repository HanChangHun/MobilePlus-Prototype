package android.content.rollback;

import android.annotation.SystemApi;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.content.pm.VersionedPackage;
import android.os.RemoteException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@SystemApi
public final class RollbackManager {
  public static final String EXTRA_STATUS = "android.content.rollback.extra.STATUS";
  
  public static final String EXTRA_STATUS_MESSAGE = "android.content.rollback.extra.STATUS_MESSAGE";
  
  public static final String PROPERTY_ROLLBACK_LIFETIME_MILLIS = "rollback_lifetime_in_millis";
  
  public static final int STATUS_FAILURE = 1;
  
  public static final int STATUS_FAILURE_INSTALL = 3;
  
  public static final int STATUS_FAILURE_ROLLBACK_UNAVAILABLE = 2;
  
  public static final int STATUS_SUCCESS = 0;
  
  private final IRollbackManager mBinder;
  
  private final String mCallerPackageName;
  
  public RollbackManager(Context paramContext, IRollbackManager paramIRollbackManager) {
    this.mCallerPackageName = paramContext.getPackageName();
    this.mBinder = paramIRollbackManager;
  }
  
  public void blockRollbackManager(long paramLong) {
    try {
      this.mBinder.blockRollbackManager(paramLong);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void commitRollback(int paramInt, List<VersionedPackage> paramList, IntentSender paramIntentSender) {
    try {
      IRollbackManager iRollbackManager = this.mBinder;
      ParceledListSlice parceledListSlice = new ParceledListSlice();
      this(paramList);
      iRollbackManager.commitRollback(paramInt, parceledListSlice, this.mCallerPackageName, paramIntentSender);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void expireRollbackForPackage(String paramString) {
    try {
      this.mBinder.expireRollbackForPackage(paramString);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<RollbackInfo> getAvailableRollbacks() {
    try {
      return this.mBinder.getAvailableRollbacks().getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<RollbackInfo> getRecentlyCommittedRollbacks() {
    try {
      return this.mBinder.getRecentlyCommittedRollbacks().getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void reloadPersistedData() {
    try {
      this.mBinder.reloadPersistedData();
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Status {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/rollback/RollbackManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */