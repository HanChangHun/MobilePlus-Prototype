package android.content.om;

import android.annotation.SystemApi;
import android.compat.Compatibility;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import java.util.List;

@SystemApi
public class OverlayManager {
  private static final long THROW_SECURITY_EXCEPTIONS = 147340954L;
  
  private final Context mContext;
  
  private final IOverlayManager mService;
  
  public OverlayManager(Context paramContext) {
    this(paramContext, IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay")));
  }
  
  public OverlayManager(Context paramContext, IOverlayManager paramIOverlayManager) {
    this.mContext = paramContext;
    this.mService = paramIOverlayManager;
  }
  
  private void rethrowSecurityException(SecurityException paramSecurityException) {
    if (!Compatibility.isChangeEnabled(147340954L))
      throw new IllegalStateException(paramSecurityException); 
    throw paramSecurityException;
  }
  
  @SystemApi
  public OverlayInfo getOverlayInfo(String paramString, UserHandle paramUserHandle) {
    try {
      return this.mService.getOverlayInfo(paramString, paramUserHandle.getIdentifier());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public List<OverlayInfo> getOverlayInfosForTarget(String paramString, UserHandle paramUserHandle) {
    try {
      return this.mService.getOverlayInfosForTarget(paramString, paramUserHandle.getIdentifier());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void invalidateCachesForOverlay(String paramString, UserHandle paramUserHandle) {
    try {
      this.mService.invalidateCachesForOverlay(paramString, paramUserHandle.getIdentifier());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void setEnabled(String paramString, boolean paramBoolean, UserHandle paramUserHandle) throws SecurityException, IllegalStateException {
    try {
      if (!this.mService.setEnabled(paramString, paramBoolean, paramUserHandle.getIdentifier())) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this("setEnabled failed");
        throw illegalStateException;
      } 
    } catch (SecurityException securityException) {
      rethrowSecurityException(securityException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void setEnabledExclusiveInCategory(String paramString, UserHandle paramUserHandle) throws SecurityException, IllegalStateException {
    try {
      if (!this.mService.setEnabledExclusiveInCategory(paramString, paramUserHandle.getIdentifier())) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this("setEnabledExclusiveInCategory failed");
        throw illegalStateException;
      } 
    } catch (SecurityException securityException) {
      rethrowSecurityException(securityException);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/om/OverlayManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */