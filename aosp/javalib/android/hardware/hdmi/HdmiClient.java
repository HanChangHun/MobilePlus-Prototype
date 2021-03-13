package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.os.RemoteException;
import android.util.Log;

@SystemApi
public abstract class HdmiClient {
  private static final String TAG = "HdmiClient";
  
  private IHdmiVendorCommandListener mIHdmiVendorCommandListener;
  
  final IHdmiControlService mService;
  
  HdmiClient(IHdmiControlService paramIHdmiControlService) {
    this.mService = paramIHdmiControlService;
  }
  
  private static IHdmiVendorCommandListener getListenerWrapper(final HdmiControlManager.VendorCommandListener listener) {
    return new IHdmiVendorCommandListener.Stub() {
        public void onControlStateChanged(boolean param1Boolean, int param1Int) {
          listener.onControlStateChanged(param1Boolean, param1Int);
        }
        
        public void onReceived(int param1Int1, int param1Int2, byte[] param1ArrayOfbyte, boolean param1Boolean) {
          listener.onReceived(param1Int1, param1Int2, param1ArrayOfbyte, param1Boolean);
        }
      };
  }
  
  public HdmiDeviceInfo getActiveSource() {
    try {
      return this.mService.getActiveSource();
    } catch (RemoteException remoteException) {
      Log.e("HdmiClient", "getActiveSource threw exception ", (Throwable)remoteException);
      return null;
    } 
  }
  
  abstract int getDeviceType();
  
  public void sendKeyEvent(int paramInt, boolean paramBoolean) {
    try {
      this.mService.sendKeyEvent(getDeviceType(), paramInt, paramBoolean);
    } catch (RemoteException remoteException) {
      Log.e("HdmiClient", "sendKeyEvent threw exception ", (Throwable)remoteException);
    } 
  }
  
  public void sendVendorCommand(int paramInt, byte[] paramArrayOfbyte, boolean paramBoolean) {
    try {
      this.mService.sendVendorCommand(getDeviceType(), paramInt, paramArrayOfbyte, paramBoolean);
    } catch (RemoteException remoteException) {
      Log.e("HdmiClient", "failed to send vendor command: ", (Throwable)remoteException);
    } 
  }
  
  public void sendVolumeKeyEvent(int paramInt, boolean paramBoolean) {
    try {
      this.mService.sendVolumeKeyEvent(getDeviceType(), paramInt, paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      Log.e("HdmiClient", "sendVolumeKeyEvent threw exception ", (Throwable)remoteException);
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void setVendorCommandListener(HdmiControlManager.VendorCommandListener paramVendorCommandListener) {
    if (paramVendorCommandListener != null) {
      if (this.mIHdmiVendorCommandListener == null) {
        try {
          IHdmiVendorCommandListener iHdmiVendorCommandListener = getListenerWrapper(paramVendorCommandListener);
          this.mService.addVendorCommandListener(iHdmiVendorCommandListener, getDeviceType());
          this.mIHdmiVendorCommandListener = iHdmiVendorCommandListener;
        } catch (RemoteException remoteException) {
          Log.e("HdmiClient", "failed to set vendor command listener: ", (Throwable)remoteException);
        } 
        return;
      } 
      throw new IllegalStateException("listener was already set");
    } 
    throw new IllegalArgumentException("listener cannot be null");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */