package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.os.RemoteException;
import android.util.Log;

@SystemApi
public final class HdmiPlaybackClient extends HdmiClient {
  private static final int ADDR_TV = 0;
  
  private static final String TAG = "HdmiPlaybackClient";
  
  HdmiPlaybackClient(IHdmiControlService paramIHdmiControlService) {
    super(paramIHdmiControlService);
  }
  
  private IHdmiControlCallback getCallbackWrapper(final DisplayStatusCallback callback) {
    return new IHdmiControlCallback.Stub() {
        public void onComplete(int param1Int) {
          callback.onComplete(param1Int);
        }
      };
  }
  
  private IHdmiControlCallback getCallbackWrapper(final OneTouchPlayCallback callback) {
    return new IHdmiControlCallback.Stub() {
        public void onComplete(int param1Int) {
          callback.onComplete(param1Int);
        }
      };
  }
  
  public int getDeviceType() {
    return 4;
  }
  
  public void oneTouchPlay(OneTouchPlayCallback paramOneTouchPlayCallback) {
    try {
      this.mService.oneTouchPlay(getCallbackWrapper(paramOneTouchPlayCallback));
    } catch (RemoteException remoteException) {
      Log.e("HdmiPlaybackClient", "oneTouchPlay threw exception ", (Throwable)remoteException);
    } 
  }
  
  public void queryDisplayStatus(DisplayStatusCallback paramDisplayStatusCallback) {
    try {
      this.mService.queryDisplayStatus(getCallbackWrapper(paramDisplayStatusCallback));
    } catch (RemoteException remoteException) {
      Log.e("HdmiPlaybackClient", "queryDisplayStatus threw exception ", (Throwable)remoteException);
    } 
  }
  
  public void sendStandby() {
    try {
      this.mService.sendStandby(getDeviceType(), HdmiDeviceInfo.idForCecDevice(0));
    } catch (RemoteException remoteException) {
      Log.e("HdmiPlaybackClient", "sendStandby threw exception ", (Throwable)remoteException);
    } 
  }
  
  public static interface DisplayStatusCallback {
    void onComplete(int param1Int);
  }
  
  public static interface OneTouchPlayCallback {
    void onComplete(int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiPlaybackClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */