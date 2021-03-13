package android.hardware.hdmi;

import android.os.RemoteException;

class null implements Runnable {
  public void run() {
    if (HdmiAudioSystemClient.access$000(HdmiAudioSystemClient.this)) {
      try {
        HdmiAudioSystemClient.this.mService.reportAudioStatus(HdmiAudioSystemClient.this.getDeviceType(), HdmiAudioSystemClient.access$100(HdmiAudioSystemClient.this), HdmiAudioSystemClient.access$200(HdmiAudioSystemClient.this), HdmiAudioSystemClient.access$300(HdmiAudioSystemClient.this));
        HdmiAudioSystemClient.access$400(HdmiAudioSystemClient.this).postDelayed(this, 500L);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiAudioSystemClient$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */