package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.util.Log;

class null implements Camera.AutoFocusMoveCallback {
  public void onAutoFocusMoving(boolean paramBoolean, Camera paramCamera) {
    synchronized (LegacyFocusStateMapper.access$000(LegacyFocusStateMapper.this)) {
      byte b;
      int i = LegacyFocusStateMapper.access$100(LegacyFocusStateMapper.this);
      if (currentAfRun != i) {
        String str1 = LegacyFocusStateMapper.access$200();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("onAutoFocusMoving - ignoring move callbacks from old af run");
        stringBuilder.append(currentAfRun);
        Log.d(str1, stringBuilder.toString());
        return;
      } 
      if (paramBoolean) {
        b = 1;
      } else {
        b = 2;
      } 
      String str = afMode;
      i = -1;
      int j = str.hashCode();
      if (j != -194628547) {
        if (j == 910005312 && str.equals("continuous-picture"))
          i = 0; 
      } else if (str.equals("continuous-video")) {
        i = 1;
      } 
      if (i != 0 && i != 1) {
        str = LegacyFocusStateMapper.access$200();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("onAutoFocus - got unexpected onAutoFocus in mode ");
        stringBuilder.append(afMode);
        Log.w(str, stringBuilder.toString());
      } 
      LegacyFocusStateMapper.access$302(LegacyFocusStateMapper.this, b);
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyFocusStateMapper$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */