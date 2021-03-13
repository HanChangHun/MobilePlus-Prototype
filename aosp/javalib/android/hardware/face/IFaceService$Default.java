package android.hardware.face;

import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IFaceService {
  public void addLockoutResetCallback(IBiometricServiceLockoutResetCallback paramIBiometricServiceLockoutResetCallback) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void authenticate(IBinder paramIBinder, long paramLong, int paramInt1, IFaceServiceReceiver paramIFaceServiceReceiver, int paramInt2, String paramString) throws RemoteException {}
  
  public void cancelAuthentication(IBinder paramIBinder, String paramString) throws RemoteException {}
  
  public void cancelAuthenticationFromService(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) throws RemoteException {}
  
  public void cancelEnrollment(IBinder paramIBinder) throws RemoteException {}
  
  public void enroll(int paramInt, IBinder paramIBinder, byte[] paramArrayOfbyte, IFaceServiceReceiver paramIFaceServiceReceiver, String paramString, int[] paramArrayOfint) throws RemoteException {}
  
  public void enumerate(IBinder paramIBinder, int paramInt, IFaceServiceReceiver paramIFaceServiceReceiver) throws RemoteException {}
  
  public long generateChallenge(IBinder paramIBinder) throws RemoteException {
    return 0L;
  }
  
  public long getAuthenticatorId(int paramInt) throws RemoteException {
    return 0L;
  }
  
  public List<Face> getEnrolledFaces(int paramInt, String paramString) throws RemoteException {
    return null;
  }
  
  public void getFeature(int paramInt1, int paramInt2, IFaceServiceReceiver paramIFaceServiceReceiver, String paramString) throws RemoteException {}
  
  public boolean hasEnrolledFaces(int paramInt, String paramString) throws RemoteException {
    return false;
  }
  
  public void initConfiguredStrength(int paramInt) throws RemoteException {}
  
  public boolean isHardwareDetected(String paramString) throws RemoteException {
    return false;
  }
  
  public void prepareForAuthentication(boolean paramBoolean, IBinder paramIBinder, long paramLong, int paramInt1, IBiometricServiceReceiverInternal paramIBiometricServiceReceiverInternal, String paramString, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws RemoteException {}
  
  public void remove(IBinder paramIBinder, int paramInt1, int paramInt2, IFaceServiceReceiver paramIFaceServiceReceiver, String paramString) throws RemoteException {}
  
  public void rename(int paramInt, String paramString) throws RemoteException {}
  
  public void resetLockout(byte[] paramArrayOfbyte) throws RemoteException {}
  
  public int revokeChallenge(IBinder paramIBinder) throws RemoteException {
    return 0;
  }
  
  public void setActiveUser(int paramInt) throws RemoteException {}
  
  public void setFeature(int paramInt1, int paramInt2, boolean paramBoolean, byte[] paramArrayOfbyte, IFaceServiceReceiver paramIFaceServiceReceiver, String paramString) throws RemoteException {}
  
  public void startPreparedClient(int paramInt) throws RemoteException {}
  
  public void userActivity() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/IFaceService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */