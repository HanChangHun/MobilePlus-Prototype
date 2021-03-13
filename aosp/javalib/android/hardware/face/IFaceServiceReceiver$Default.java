package android.hardware.face;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IFaceServiceReceiver {
  public IBinder asBinder() {
    return null;
  }
  
  public void onAcquired(long paramLong, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onAuthenticationFailed(long paramLong) throws RemoteException {}
  
  public void onAuthenticationSucceeded(long paramLong, Face paramFace, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void onEnrollResult(long paramLong, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onEnumerated(long paramLong, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onError(long paramLong, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void onFeatureGet(boolean paramBoolean1, int paramInt, boolean paramBoolean2) throws RemoteException {}
  
  public void onFeatureSet(boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public void onRemoved(long paramLong, int paramInt1, int paramInt2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/IFaceServiceReceiver$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */