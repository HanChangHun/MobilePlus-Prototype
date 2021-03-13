package android.hardware.hdmi;

import android.annotation.SystemApi;

@SystemApi
public interface VendorCommandListener {
  void onControlStateChanged(boolean paramBoolean, int paramInt);
  
  void onReceived(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, boolean paramBoolean);
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiControlManager$VendorCommandListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */