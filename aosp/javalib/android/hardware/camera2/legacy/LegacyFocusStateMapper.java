package android.hardware.camera2.legacy;

import android.hardware.Camera;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.utils.ParamsUtils;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.Objects;

public class LegacyFocusStateMapper {
  private static final boolean DEBUG = false;
  
  private static String TAG = "LegacyFocusStateMapper";
  
  private String mAfModePrevious = null;
  
  private int mAfRun = 0;
  
  private int mAfState = 0;
  
  private int mAfStatePrevious = 0;
  
  private final Camera mCamera;
  
  private final Object mLock = new Object();
  
  public LegacyFocusStateMapper(Camera paramCamera) {
    this.mCamera = (Camera)Preconditions.checkNotNull(paramCamera, "camera must not be null");
  }
  
  private static String afStateToString(int paramInt) {
    StringBuilder stringBuilder;
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("UNKNOWN(");
        stringBuilder.append(paramInt);
        stringBuilder.append(")");
        return stringBuilder.toString();
      case 6:
        return "PASSIVE_UNFOCUSED";
      case 5:
        return "NOT_FOCUSED_LOCKED";
      case 4:
        return "FOCUSED_LOCKED";
      case 3:
        return "ACTIVE_SCAN";
      case 2:
        return "PASSIVE_FOCUSED";
      case 1:
        return "PASSIVE_SCAN";
      case 0:
        break;
    } 
    return "INACTIVE";
  }
  
  public void mapResultTriggers(CameraMetadataNative paramCameraMetadataNative) {
    Preconditions.checkNotNull(paramCameraMetadataNative, "result must not be null");
    synchronized (this.mLock) {
      int i = this.mAfState;
      paramCameraMetadataNative.set(CaptureResult.CONTROL_AF_STATE, Integer.valueOf(i));
      this.mAfStatePrevious = i;
      return;
    } 
  }
  
  public void processRequestTriggers(CaptureRequest paramCaptureRequest, Camera.Parameters paramParameters) {
    Preconditions.checkNotNull(paramCaptureRequest, "captureRequest must not be null");
    CaptureRequest.Key key = CaptureRequest.CONTROL_AF_TRIGGER;
    final int currentAfRun = 0;
    int j = ((Integer)ParamsUtils.getOrDefault(paramCaptureRequest, key, Integer.valueOf(0))).intValue();
    null = paramParameters.getFocusMode();
    if (!Objects.equals(this.mAfModePrevious, null))
      synchronized (this.mLock) {
        this.mAfRun++;
        this.mAfState = 0;
        this.mCamera.cancelAutoFocus();
      }  
    this.mAfModePrevious = null;
    synchronized (this.mLock) {
      final int currentAfRun = this.mAfRun;
      null = new Camera.AutoFocusMoveCallback() {
          public void onAutoFocusMoving(boolean param1Boolean, Camera param1Camera) {
            synchronized (LegacyFocusStateMapper.this.mLock) {
              byte b;
              int i = LegacyFocusStateMapper.this.mAfRun;
              if (currentAfRun != i) {
                String str1 = LegacyFocusStateMapper.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("onAutoFocusMoving - ignoring move callbacks from old af run");
                stringBuilder.append(currentAfRun);
                Log.d(str1, stringBuilder.toString());
                return;
              } 
              if (param1Boolean) {
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
                str = LegacyFocusStateMapper.TAG;
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
        };
      switch (null.hashCode()) {
        default:
          k = -1;
          break;
        case 910005312:
          if (null.equals("continuous-picture")) {
            k = 2;
            break;
          } 
        case 103652300:
          if (null.equals("macro")) {
            k = 1;
            break;
          } 
        case 3005871:
          if (null.equals("auto")) {
            k = 0;
            break;
          } 
        case -194628547:
          if (null.equals("continuous-video")) {
            k = 3;
            break;
          } 
      } 
      if (k == 0 || k == 1 || k == 2 || k == 3)
        this.mCamera.setAutoFocusMoveCallback((Camera.AutoFocusMoveCallback)null); 
      if (j != 0)
        if (j != 1) {
          if (j != 2) {
            null = TAG;
            null = new StringBuilder();
            null.append("processRequestTriggers - ignoring unknown control.afTrigger = ");
            null.append(j);
            Log.w(null, null.toString());
          } else {
            synchronized (this.mLock) {
              synchronized (this.mLock) {
                this.mAfRun++;
                this.mAfState = 0;
                this.mCamera.cancelAutoFocus();
              } 
            } 
          } 
        } else {
          switch (null.hashCode()) {
            default:
              k = -1;
              break;
            case 910005312:
              if (null.equals("continuous-picture")) {
                k = 2;
                break;
              } 
            case 103652300:
              if (null.equals("macro")) {
                k = 1;
                break;
              } 
            case 3005871:
              if (null.equals("auto")) {
                k = i;
                break;
              } 
            case -194628547:
              if (null.equals("continuous-video")) {
                k = 3;
                break;
              } 
          } 
          if (k != 0 && k != 1) {
            if (k != 2 && k != 3) {
              k = 0;
            } else {
              k = 1;
            } 
          } else {
            k = 3;
          } 
          synchronized (this.mLock) {
            i = this.mAfRun + 1;
            this.mAfRun = i;
            this.mAfState = k;
            if (k != 0)
              this.mCamera.autoFocus(new Camera.AutoFocusCallback() {
                    public void onAutoFocus(boolean param1Boolean, Camera param1Camera) {
                      synchronized (LegacyFocusStateMapper.this.mLock) {
                        int i = LegacyFocusStateMapper.this.mAfRun;
                        int j = currentAfRun;
                        byte b = 0;
                        if (i != j) {
                          Log.d(LegacyFocusStateMapper.TAG, String.format("onAutoFocus - ignoring AF callback (old run %d, new run %d)", new Object[] { Integer.valueOf(this.val$currentAfRun), Integer.valueOf(i) }));
                          return;
                        } 
                        if (param1Boolean) {
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
                          String str1 = LegacyFocusStateMapper.TAG;
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
                  }); 
          } 
        }  
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/LegacyFocusStateMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */