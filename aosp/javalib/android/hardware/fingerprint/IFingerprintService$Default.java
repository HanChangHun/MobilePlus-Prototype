package android.hardware.fingerprint;

import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IFingerprintService {
  public void addClientActiveCallback(IFingerprintClientActiveCallback paramIFingerprintClientActiveCallback) throws RemoteException {}
  
  public void addLockoutResetCallback(IBiometricServiceLockoutResetCallback paramIBiometricServiceLockoutResetCallback) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void authenticate(IBinder paramIBinder, long paramLong, int paramInt1, IFingerprintServiceReceiver paramIFingerprintServiceReceiver, int paramInt2, String paramString) throws RemoteException {}
  
  public void cancelAuthentication(IBinder paramIBinder, String paramString) throws RemoteException {}
  
  public void cancelAuthenticationFromService(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) throws RemoteException {}
  
  public void cancelEnrollment(IBinder paramIBinder) throws RemoteException {}
  
  public void cancelFingerprintDetect(IBinder paramIBinder, String paramString) throws RemoteException {}
  
  public void detectFingerprint(IBinder paramIBinder, int paramInt, IFingerprintServiceReceiver paramIFingerprintServiceReceiver, String paramString) throws RemoteException {}
  
  public void enroll(IBinder paramIBinder, byte[] paramArrayOfbyte, int paramInt1, IFingerprintServiceReceiver paramIFingerprintServiceReceiver, int paramInt2, String paramString) throws RemoteException {}
  
  public void enumerate(IBinder paramIBinder, int paramInt, IFingerprintServiceReceiver paramIFingerprintServiceReceiver) throws RemoteException {}
  
  public long getAuthenticatorId(int paramInt) throws RemoteException {
    return 0L;
  }
  
  public List<Fingerprint> getEnrolledFingerprints(int paramInt, String paramString) throws RemoteException {
    return null;
  }
  
  public boolean hasEnrolledFingerprints(int paramInt, String paramString) throws RemoteException {
    return false;
  }
  
  public void initConfiguredStrength(int paramInt) throws RemoteException {}
  
  public boolean isClientActive() throws RemoteException {
    return false;
  }
  
  public boolean isHardwareDetected(String paramString) throws RemoteException {
    return false;
  }
  
  public int postEnroll(IBinder paramIBinder) throws RemoteException {
    return 0;
  }
  
  public long preEnroll(IBinder paramIBinder) throws RemoteException {
    return 0L;
  }
  
  public void prepareForAuthentication(IBinder paramIBinder, long paramLong, int paramInt1, IBiometricServiceReceiverInternal paramIBiometricServiceReceiverInternal, String paramString, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws RemoteException {}
  
  public void remove(IBinder paramIBinder, int paramInt1, int paramInt2, int paramInt3, IFingerprintServiceReceiver paramIFingerprintServiceReceiver) throws RemoteException {}
  
  public void removeClientActiveCallback(IFingerprintClientActiveCallback paramIFingerprintClientActiveCallback) throws RemoteException {}
  
  public void rename(int paramInt1, int paramInt2, String paramString) throws RemoteException {}
  
  public void resetTimeout(byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void setActiveUser(int paramInt) throws RemoteException {}
  
  public void startPreparedClient(int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/IFingerprintService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */