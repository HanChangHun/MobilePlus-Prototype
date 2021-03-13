package android.hardware.hdmi;

class null extends IHdmiVendorCommandListener.Stub {
  public void onControlStateChanged(boolean paramBoolean, int paramInt) {
    listener.onControlStateChanged(paramBoolean, paramInt);
  }
  
  public void onReceived(int paramInt1, int paramInt2, byte[] paramArrayOfbyte, boolean paramBoolean) {
    listener.onReceived(paramInt1, paramInt2, paramArrayOfbyte, paramBoolean);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiClient$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */