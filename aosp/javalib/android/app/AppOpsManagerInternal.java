package android.app;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.android.internal.app.IAppOpsCallback;
import com.android.internal.util.function.HeptFunction;
import com.android.internal.util.function.QuadFunction;

public abstract class AppOpsManagerInternal {
  public abstract void setDeviceAndProfileOwners(SparseIntArray paramSparseIntArray);
  
  public abstract void setModeFromPermissionPolicy(int paramInt1, int paramInt2, String paramString, int paramInt3, IAppOpsCallback paramIAppOpsCallback);
  
  public abstract void setUidModeFromPermissionPolicy(int paramInt1, int paramInt2, int paramInt3, IAppOpsCallback paramIAppOpsCallback);
  
  public abstract void updateAppWidgetVisibility(SparseArray<String> paramSparseArray, boolean paramBoolean);
  
  public static interface CheckOpsDelegate {
    int checkAudioOperation(int param1Int1, int param1Int2, int param1Int3, String param1String, QuadFunction<Integer, Integer, Integer, String, Integer> param1QuadFunction);
    
    int checkOperation(int param1Int1, int param1Int2, String param1String, boolean param1Boolean, QuadFunction<Integer, Integer, String, Boolean, Integer> param1QuadFunction);
    
    int noteOperation(int param1Int1, int param1Int2, String param1String1, String param1String2, boolean param1Boolean1, String param1String3, boolean param1Boolean2, HeptFunction<Integer, Integer, String, String, Boolean, String, Boolean, Integer> param1HeptFunction);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManagerInternal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */