package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.os.RemoteException;
import android.util.Log;
import java.util.Collections;
import java.util.List;
import libcore.util.EmptyArray;

@SystemApi
public final class HdmiTvClient extends HdmiClient {
  private static final String TAG = "HdmiTvClient";
  
  public static final int VENDOR_DATA_SIZE = 16;
  
  HdmiTvClient(IHdmiControlService paramIHdmiControlService) {
    super(paramIHdmiControlService);
  }
  
  private void checkTimerRecordingSourceType(int paramInt) {
    if (paramInt == 1 || paramInt == 2 || paramInt == 3)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid source type:");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  static HdmiTvClient create(IHdmiControlService paramIHdmiControlService) {
    return new HdmiTvClient(paramIHdmiControlService);
  }
  
  private static IHdmiControlCallback getCallbackWrapper(final SelectCallback callback) {
    return new IHdmiControlCallback.Stub() {
        public void onComplete(int param1Int) {
          callback.onComplete(param1Int);
        }
      };
  }
  
  private static IHdmiInputChangeListener getListenerWrapper(final InputChangeListener listener) {
    return new IHdmiInputChangeListener.Stub() {
        public void onChanged(HdmiDeviceInfo param1HdmiDeviceInfo) {
          listener.onChanged(param1HdmiDeviceInfo);
        }
      };
  }
  
  private IHdmiMhlVendorCommandListener getListenerWrapper(final HdmiMhlVendorCommandListener listener) {
    return new IHdmiMhlVendorCommandListener.Stub() {
        public void onReceived(int param1Int1, int param1Int2, int param1Int3, byte[] param1ArrayOfbyte) {
          listener.onReceived(param1Int1, param1Int2, param1Int3, param1ArrayOfbyte);
        }
      };
  }
  
  private static IHdmiRecordListener getListenerWrapper(final HdmiRecordListener callback) {
    return new IHdmiRecordListener.Stub() {
        public byte[] getOneTouchRecordSource(int param1Int) {
          HdmiRecordSources.RecordSource recordSource = callback.onOneTouchRecordSourceRequested(param1Int);
          if (recordSource == null)
            return EmptyArray.BYTE; 
          byte[] arrayOfByte = new byte[recordSource.getDataSize(true)];
          recordSource.toByteArray(true, arrayOfByte, 0);
          return arrayOfByte;
        }
        
        public void onClearTimerRecordingResult(int param1Int1, int param1Int2) {
          callback.onClearTimerRecordingResult(param1Int1, param1Int2);
        }
        
        public void onOneTouchRecordResult(int param1Int1, int param1Int2) {
          callback.onOneTouchRecordResult(param1Int1, param1Int2);
        }
        
        public void onTimerRecordingResult(int param1Int1, int param1Int2) {
          callback.onTimerRecordingResult(param1Int1, HdmiRecordListener.TimerStatusData.parseFrom(param1Int2));
        }
      };
  }
  
  public void clearTimerRecording(int paramInt1, int paramInt2, HdmiTimerRecordSources.TimerRecordSource paramTimerRecordSource) {
    if (paramTimerRecordSource != null) {
      checkTimerRecordingSourceType(paramInt2);
      try {
        byte[] arrayOfByte = new byte[paramTimerRecordSource.getDataSize()];
        paramTimerRecordSource.toByteArray(arrayOfByte, 0);
        this.mService.clearTimerRecording(paramInt1, paramInt2, arrayOfByte);
      } catch (RemoteException remoteException) {
        Log.e("HdmiTvClient", "failed to start record: ", (Throwable)remoteException);
      } 
      return;
    } 
    throw new IllegalArgumentException("source must not be null.");
  }
  
  public void deviceSelect(int paramInt, SelectCallback paramSelectCallback) {
    if (paramSelectCallback != null) {
      try {
        this.mService.deviceSelect(paramInt, getCallbackWrapper(paramSelectCallback));
      } catch (RemoteException remoteException) {
        Log.e("HdmiTvClient", "failed to select device: ", (Throwable)remoteException);
      } 
      return;
    } 
    throw new IllegalArgumentException("callback must not be null.");
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
    return 0;
  }
  
  public void portSelect(int paramInt, SelectCallback paramSelectCallback) {
    if (paramSelectCallback != null) {
      try {
        this.mService.portSelect(paramInt, getCallbackWrapper(paramSelectCallback));
      } catch (RemoteException remoteException) {
        Log.e("HdmiTvClient", "failed to select port: ", (Throwable)remoteException);
      } 
      return;
    } 
    throw new IllegalArgumentException("Callback must not be null");
  }
  
  public void sendMhlVendorCommand(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte != null && paramArrayOfbyte.length == 16) {
      if (paramInt2 >= 0 && paramInt2 < 16) {
        if (paramInt3 >= 0 && paramInt2 + paramInt3 <= 16) {
          try {
            this.mService.sendMhlVendorCommand(paramInt1, paramInt2, paramInt3, paramArrayOfbyte);
          } catch (RemoteException remoteException) {
            Log.e("HdmiTvClient", "failed to send vendor command: ", (Throwable)remoteException);
          } 
          return;
        } 
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Invalid length:");
        stringBuilder1.append(paramInt3);
        throw new IllegalArgumentException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid offset:");
      stringBuilder.append(paramInt2);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    throw new IllegalArgumentException("Invalid vendor command data.");
  }
  
  public void sendStandby(int paramInt) {
    try {
      this.mService.sendStandby(getDeviceType(), paramInt);
    } catch (RemoteException remoteException) {
      Log.e("HdmiTvClient", "sendStandby threw exception ", (Throwable)remoteException);
    } 
  }
  
  public void setHdmiMhlVendorCommandListener(HdmiMhlVendorCommandListener paramHdmiMhlVendorCommandListener) {
    if (paramHdmiMhlVendorCommandListener != null) {
      try {
        this.mService.addHdmiMhlVendorCommandListener(getListenerWrapper(paramHdmiMhlVendorCommandListener));
      } catch (RemoteException remoteException) {
        Log.e("HdmiTvClient", "failed to set hdmi mhl vendor command listener: ", (Throwable)remoteException);
      } 
      return;
    } 
    throw new IllegalArgumentException("listener must not be null.");
  }
  
  public void setInputChangeListener(InputChangeListener paramInputChangeListener) {
    if (paramInputChangeListener != null) {
      try {
        this.mService.setInputChangeListener(getListenerWrapper(paramInputChangeListener));
      } catch (RemoteException remoteException) {
        Log.e("TAG", "Failed to set InputChangeListener:", (Throwable)remoteException);
      } 
      return;
    } 
    throw new IllegalArgumentException("listener must not be null.");
  }
  
  public void setRecordListener(HdmiRecordListener paramHdmiRecordListener) {
    if (paramHdmiRecordListener != null) {
      try {
        this.mService.setHdmiRecordListener(getListenerWrapper(paramHdmiRecordListener));
      } catch (RemoteException remoteException) {
        Log.e("HdmiTvClient", "failed to set record listener.", (Throwable)remoteException);
      } 
      return;
    } 
    throw new IllegalArgumentException("listener must not be null.");
  }
  
  public void setSystemAudioMode(boolean paramBoolean, SelectCallback paramSelectCallback) {
    try {
      this.mService.setSystemAudioMode(paramBoolean, getCallbackWrapper(paramSelectCallback));
    } catch (RemoteException remoteException) {
      Log.e("HdmiTvClient", "failed to set system audio mode:", (Throwable)remoteException);
    } 
  }
  
  public void setSystemAudioMute(boolean paramBoolean) {
    try {
      this.mService.setSystemAudioMute(paramBoolean);
    } catch (RemoteException remoteException) {
      Log.e("HdmiTvClient", "failed to set mute: ", (Throwable)remoteException);
    } 
  }
  
  public void setSystemAudioVolume(int paramInt1, int paramInt2, int paramInt3) {
    try {
      this.mService.setSystemAudioVolume(paramInt1, paramInt2, paramInt3);
    } catch (RemoteException remoteException) {
      Log.e("HdmiTvClient", "failed to set volume: ", (Throwable)remoteException);
    } 
  }
  
  public void startOneTouchRecord(int paramInt, HdmiRecordSources.RecordSource paramRecordSource) {
    if (paramRecordSource != null) {
      try {
        byte[] arrayOfByte = new byte[paramRecordSource.getDataSize(true)];
        paramRecordSource.toByteArray(true, arrayOfByte, 0);
        this.mService.startOneTouchRecord(paramInt, arrayOfByte);
      } catch (RemoteException remoteException) {
        Log.e("HdmiTvClient", "failed to start record: ", (Throwable)remoteException);
      } 
      return;
    } 
    throw new IllegalArgumentException("source must not be null.");
  }
  
  public void startTimerRecording(int paramInt1, int paramInt2, HdmiTimerRecordSources.TimerRecordSource paramTimerRecordSource) {
    if (paramTimerRecordSource != null) {
      checkTimerRecordingSourceType(paramInt2);
      try {
        byte[] arrayOfByte = new byte[paramTimerRecordSource.getDataSize()];
        paramTimerRecordSource.toByteArray(arrayOfByte, 0);
        this.mService.startTimerRecording(paramInt1, paramInt2, arrayOfByte);
      } catch (RemoteException remoteException) {
        Log.e("HdmiTvClient", "failed to start record: ", (Throwable)remoteException);
      } 
      return;
    } 
    throw new IllegalArgumentException("source must not be null.");
  }
  
  public void stopOneTouchRecord(int paramInt) {
    try {
      this.mService.stopOneTouchRecord(paramInt);
    } catch (RemoteException remoteException) {
      Log.e("HdmiTvClient", "failed to stop record: ", (Throwable)remoteException);
    } 
  }
  
  public static interface HdmiMhlVendorCommandListener {
    void onReceived(int param1Int1, int param1Int2, int param1Int3, byte[] param1ArrayOfbyte);
  }
  
  public static interface InputChangeListener {
    void onChanged(HdmiDeviceInfo param1HdmiDeviceInfo);
  }
  
  public static interface SelectCallback {
    void onComplete(int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiTvClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */