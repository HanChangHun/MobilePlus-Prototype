package android.hardware;

import com.android.internal.app.IAppOpsCallback;
import java.lang.ref.WeakReference;

class IAppOpsCallbackWrapper extends IAppOpsCallback.Stub {
  private final WeakReference<Camera> mWeakCamera;
  
  IAppOpsCallbackWrapper(Camera paramCamera) {
    this.mWeakCamera = new WeakReference<>(paramCamera);
  }
  
  public void opChanged(int paramInt1, int paramInt2, String paramString) {
    if (paramInt1 == 28) {
      Camera camera = this.mWeakCamera.get();
      if (camera != null)
        Camera.access$1500(camera); 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/Camera$IAppOpsCallbackWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */