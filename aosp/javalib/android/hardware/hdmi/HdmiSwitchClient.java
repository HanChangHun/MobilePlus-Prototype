package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

@SystemApi
public class HdmiSwitchClient extends HdmiClient {
  private static final String TAG = "HdmiSwitchClient";
  
  HdmiSwitchClient(IHdmiControlService paramIHdmiControlService) {
    super(paramIHdmiControlService);
  }
  
  private static IHdmiControlCallback getCallbackWrapper(final OnSelectListener listener) {
    return new IHdmiControlCallback.Stub() {
        public void onComplete(int param1Int) {
          listener.onSelect(param1Int);
        }
      };
  }
  
  public List<HdmiDeviceInfo> getDeviceList() {
    try {
      return this.mService.getDeviceList();
    } catch (RemoteException remoteException) {
      Log.e("TAG", "Failed to call getDeviceList():", (Throwable)remoteException);
      return Collections.emptyList();
    } 
  }
  
  public int getDeviceType() {
    return 6;
  }
  
  public List<HdmiPortInfo> getPortInfo() {
    try {
      return this.mService.getPortInfo();
    } catch (RemoteException remoteException) {
      Log.e("TAG", "Failed to call getPortInfo():", (Throwable)remoteException);
      return Collections.emptyList();
    } 
  }
  
  public void selectDevice(int paramInt, OnSelectListener paramOnSelectListener) {
    Objects.requireNonNull(paramOnSelectListener);
    try {
      this.mService.deviceSelect(paramInt, getCallbackWrapper(paramOnSelectListener));
      return;
    } catch (RemoteException remoteException) {
      Log.e("HdmiSwitchClient", "failed to select device: ", (Throwable)remoteException);
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void selectDevice(int paramInt, Executor paramExecutor, OnSelectListener paramOnSelectListener) {
    Objects.requireNonNull(paramOnSelectListener);
    try {
      IHdmiControlService iHdmiControlService = this.mService;
      IHdmiControlCallback.Stub stub = new IHdmiControlCallback.Stub() {
          public void onComplete(int param1Int) {
            Binder.withCleanCallingIdentity(new _$$Lambda$HdmiSwitchClient$2$knvX6ZgANoRRFcb_fUHlUdWIjCQ(executor, listener, param1Int));
          }
        };
      super(this, paramExecutor, paramOnSelectListener);
      iHdmiControlService.deviceSelect(paramInt, stub);
      return;
    } catch (RemoteException remoteException) {
      Log.e("HdmiSwitchClient", "failed to select device: ", (Throwable)remoteException);
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void selectPort(int paramInt, OnSelectListener paramOnSelectListener) {
    Objects.requireNonNull(paramOnSelectListener);
    try {
      this.mService.portSelect(paramInt, getCallbackWrapper(paramOnSelectListener));
      return;
    } catch (RemoteException remoteException) {
      Log.e("HdmiSwitchClient", "failed to select port: ", (Throwable)remoteException);
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public void selectPort(int paramInt, Executor paramExecutor, OnSelectListener paramOnSelectListener) {
    Objects.requireNonNull(paramOnSelectListener);
    try {
      IHdmiControlService iHdmiControlService = this.mService;
      IHdmiControlCallback.Stub stub = new IHdmiControlCallback.Stub() {
          public void onComplete(int param1Int) {
            Binder.withCleanCallingIdentity(new _$$Lambda$HdmiSwitchClient$3$Cqxvec1NmkC6VlEdX5OEOabobXY(executor, listener, param1Int));
          }
        };
      super(this, paramExecutor, paramOnSelectListener);
      iHdmiControlService.portSelect(paramInt, stub);
      return;
    } catch (RemoteException remoteException) {
      Log.e("HdmiSwitchClient", "failed to select port: ", (Throwable)remoteException);
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public static interface OnSelectListener {
    void onSelect(int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiSwitchClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */