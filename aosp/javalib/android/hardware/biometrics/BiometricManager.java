package android.hardware.biometrics;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.RemoteException;
import android.util.Slog;

public class BiometricManager {
  public static final int BIOMETRIC_ERROR_HW_UNAVAILABLE = 1;
  
  public static final int BIOMETRIC_ERROR_NONE_ENROLLED = 11;
  
  public static final int BIOMETRIC_ERROR_NO_HARDWARE = 12;
  
  public static final int BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED = 15;
  
  public static final int BIOMETRIC_SUCCESS = 0;
  
  private static final String TAG = "BiometricManager";
  
  private final Context mContext;
  
  private final IAuthService mService;
  
  public BiometricManager(Context paramContext, IAuthService paramIAuthService) {
    this.mContext = paramContext;
    this.mService = paramIAuthService;
  }
  
  @Deprecated
  public int canAuthenticate() {
    return canAuthenticate(255);
  }
  
  public int canAuthenticate(int paramInt) {
    return canAuthenticate(this.mContext.getUserId(), paramInt);
  }
  
  public int canAuthenticate(int paramInt1, int paramInt2) {
    if (this.mService != null)
      try {
        String str = this.mContext.getOpPackageName();
        return this.mService.canAuthenticate(str, paramInt1, paramInt2);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Slog.w("BiometricManager", "hasEnrolledBiometrics(): Service not connected");
    return 1;
  }
  
  public long[] getAuthenticatorIds() {
    IAuthService iAuthService = this.mService;
    if (iAuthService != null)
      try {
        return iAuthService.getAuthenticatorIds();
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    Slog.w("BiometricManager", "getAuthenticatorIds(): Service not connected");
    return new long[0];
  }
  
  public boolean hasEnrolledBiometrics(int paramInt) {
    IAuthService iAuthService = this.mService;
    if (iAuthService != null)
      try {
        return iAuthService.hasEnrolledBiometrics(paramInt, this.mContext.getOpPackageName());
      } catch (RemoteException remoteException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Remote exception in hasEnrolledBiometrics(): ");
        stringBuilder.append(remoteException);
        Slog.w("BiometricManager", stringBuilder.toString());
        return false;
      }  
    return false;
  }
  
  public void registerEnabledOnKeyguardCallback(IBiometricEnabledOnKeyguardCallback paramIBiometricEnabledOnKeyguardCallback) {
    IAuthService iAuthService = this.mService;
    if (iAuthService != null) {
      try {
        iAuthService.registerEnabledOnKeyguardCallback(paramIBiometricEnabledOnKeyguardCallback);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } else {
      Slog.w("BiometricManager", "registerEnabledOnKeyguardCallback(): Service not connected");
    } 
  }
  
  public void resetLockout(byte[] paramArrayOfbyte) {
    IAuthService iAuthService = this.mService;
    if (iAuthService != null) {
      try {
        iAuthService.resetLockout(paramArrayOfbyte);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } else {
      Slog.w("BiometricManager", "resetLockout(): Service not connected");
    } 
  }
  
  public void setActiveUser(int paramInt) {
    IAuthService iAuthService = this.mService;
    if (iAuthService != null) {
      try {
        iAuthService.setActiveUser(paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      } 
    } else {
      Slog.w("BiometricManager", "setActiveUser(): Service not connected");
    } 
  }
  
  public static interface Authenticators {
    @SystemApi
    public static final int BIOMETRIC_CONVENIENCE = 4095;
    
    public static final int BIOMETRIC_MAX_STRENGTH = 1;
    
    public static final int BIOMETRIC_MIN_STRENGTH = 32767;
    
    public static final int BIOMETRIC_STRONG = 15;
    
    public static final int BIOMETRIC_WEAK = 255;
    
    public static final int DEVICE_CREDENTIAL = 32768;
    
    @SystemApi
    public static final int EMPTY_SET = 0;
    
    public static @interface Types {}
  }
  
  public static @interface Types {}
  
  static @interface BiometricError {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/biometrics/BiometricManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */