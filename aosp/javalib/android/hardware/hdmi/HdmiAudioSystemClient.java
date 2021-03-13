package android.hardware.hdmi;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;

public final class HdmiAudioSystemClient extends HdmiClient {
  private static final int REPORT_AUDIO_STATUS_INTERVAL_MS = 500;
  
  private static final String TAG = "HdmiAudioSystemClient";
  
  private boolean mCanSendAudioStatus = true;
  
  private final Handler mHandler;
  
  private boolean mLastIsMute;
  
  private int mLastMaxVolume;
  
  private int mLastVolume;
  
  private boolean mPendingReportAudioStatus;
  
  public HdmiAudioSystemClient(IHdmiControlService paramIHdmiControlService) {
    this(paramIHdmiControlService, null);
  }
  
  public HdmiAudioSystemClient(IHdmiControlService paramIHdmiControlService, Handler paramHandler) {
    super(paramIHdmiControlService);
    if (paramHandler == null)
      paramHandler = new Handler(Looper.getMainLooper()); 
    this.mHandler = paramHandler;
  }
  
  public int getDeviceType() {
    return 5;
  }
  
  public void sendReportAudioStatusCecCommand(boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2) {
    if (paramBoolean1) {
      try {
        this.mService.reportAudioStatus(getDeviceType(), paramInt1, paramInt2, paramBoolean2);
      } catch (RemoteException remoteException) {}
      return;
    } 
    this.mLastVolume = paramInt1;
    this.mLastMaxVolume = paramInt2;
    this.mLastIsMute = paramBoolean2;
    if (this.mCanSendAudioStatus) {
      try {
        this.mService.reportAudioStatus(getDeviceType(), paramInt1, paramInt2, paramBoolean2);
        this.mCanSendAudioStatus = false;
        Handler handler = this.mHandler;
        Runnable runnable = new Runnable() {
            public void run() {
              if (HdmiAudioSystemClient.this.mPendingReportAudioStatus) {
                try {
                  HdmiAudioSystemClient.this.mService.reportAudioStatus(HdmiAudioSystemClient.this.getDeviceType(), HdmiAudioSystemClient.this.mLastVolume, HdmiAudioSystemClient.this.mLastMaxVolume, HdmiAudioSystemClient.this.mLastIsMute);
                  HdmiAudioSystemClient.this.mHandler.postDelayed(this, 500L);
                } catch (RemoteException remoteException) {
                  HdmiAudioSystemClient.access$502(HdmiAudioSystemClient.this, true);
                } finally {
                  Exception exception;
                } 
                HdmiAudioSystemClient.access$002(HdmiAudioSystemClient.this, false);
              } else {
                HdmiAudioSystemClient.access$502(HdmiAudioSystemClient.this, true);
              } 
            }
          };
        super(this);
        handler.postDelayed(runnable, 500L);
      } catch (RemoteException remoteException) {}
    } else {
      this.mPendingReportAudioStatus = true;
    } 
  }
  
  public void setSystemAudioMode(boolean paramBoolean, SetSystemAudioModeCallback paramSetSystemAudioModeCallback) {}
  
  public void setSystemAudioModeOnForAudioOnlySource() {
    try {
      this.mService.setSystemAudioModeOnForAudioOnlySource();
    } catch (RemoteException remoteException) {
      Log.d("HdmiAudioSystemClient", "Failed to set System Audio Mode on for Audio Only source");
    } 
  }
  
  public static interface SetSystemAudioModeCallback {
    void onComplete(int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiAudioSystemClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */