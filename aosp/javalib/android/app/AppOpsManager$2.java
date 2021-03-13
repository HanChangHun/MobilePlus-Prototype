package android.app;

import com.android.internal.app.IAppOpsCallback;

class null extends IAppOpsCallback.Stub {
  public void opChanged(int paramInt1, int paramInt2, String paramString) {
    AppOpsManager.OnOpChangedListener onOpChangedListener = callback;
    if (onOpChangedListener instanceof AppOpsManager.OnOpChangedInternalListener)
      ((AppOpsManager.OnOpChangedInternalListener)onOpChangedListener).onOpChanged(paramInt1, paramString); 
    if (AppOpsManager.access$600()[paramInt1] != null)
      callback.onOpChanged(AppOpsManager.access$600()[paramInt1], paramString); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */