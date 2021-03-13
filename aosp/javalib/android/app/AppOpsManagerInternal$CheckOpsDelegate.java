package android.app;

import com.android.internal.util.function.HeptFunction;
import com.android.internal.util.function.QuadFunction;

public interface CheckOpsDelegate {
  int checkAudioOperation(int paramInt1, int paramInt2, int paramInt3, String paramString, QuadFunction<Integer, Integer, Integer, String, Integer> paramQuadFunction);
  
  int checkOperation(int paramInt1, int paramInt2, String paramString, boolean paramBoolean, QuadFunction<Integer, Integer, String, Boolean, Integer> paramQuadFunction);
  
  int noteOperation(int paramInt1, int paramInt2, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, HeptFunction<Integer, Integer, String, String, Boolean, String, Boolean, Integer> paramHeptFunction);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManagerInternal$CheckOpsDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */