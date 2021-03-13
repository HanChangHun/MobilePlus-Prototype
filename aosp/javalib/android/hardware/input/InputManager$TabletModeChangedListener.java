package android.hardware.input;

final class TabletModeChangedListener extends ITabletModeChangedListener.Stub {
  private TabletModeChangedListener() {}
  
  public void onTabletModeChanged(long paramLong, boolean paramBoolean) {
    InputManager.access$300(InputManager.this, paramLong, paramBoolean);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/InputManager$TabletModeChangedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */