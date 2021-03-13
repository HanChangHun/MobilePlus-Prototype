package android.hardware.display;

final class DisplayManagerCallback extends IDisplayManagerCallback.Stub {
  private DisplayManagerCallback() {}
  
  public void onDisplayEvent(int paramInt1, int paramInt2) {
    DisplayManagerGlobal.access$200(DisplayManagerGlobal.this, paramInt1, paramInt2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/DisplayManagerGlobal$DisplayManagerCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */