package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.util.Log;

class null implements Camera.AutoFocusCallback {
  public void onAutoFocus(boolean paramBoolean, Camera paramCamera) {
    synchronized (LegacyFocusStateMapper.access$000(LegacyFocusStateMapper.this)) {
      int i = LegacyFocusStateMapper.access$100(LegacyFocusStateMapper.this);
      int j = currentAfRun;
      byte b = 0;
      if (i != j) {
        Log.d(LegacyFocusStateMapper.access$200(), String.format("onAutoFocus - ignoring AF callback (old run %d, new run %d)", new Object[] { Integer.valueOf(this.val$currentAfRun), Integer.valueOf(i) }));
        return;
      } 
      if (paramBoolean) {
        j = 4;
      } else {
        j = 5;
      } 
      String str = afMode;
      switch (str.hashCode()) {
        default:
          b = -1;
          break;
        case 910005312:
          if (str.equals("continuous-picture")) {
            b = 1;
            break;
          } 
        case 103652300:
          if (str.equals("macro")) {
            b = 3;
            break;
          } 
        case 3005871:
          if (str.equals("auto"))
            break; 
        case -194628547:
          if (str.equals("continuous-video")) {
            b = 2;
            break;
          } 
      } 
      if (b != 0 && b != 1 && b != 2 && b != 3) {
        String str1 = LegacyFocusStateMapper.access$200();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("onAutoFocus - got unexpected onAutoFocus in mode ");
        stringBuilder.append(afMode);
        Log.w(str1, stringBuilder.toString());
      } 
      LegacyFocusStateMapper.access$302(LegacyFocusStateMapper.this, j);
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyFocusStateMapper$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */