package android.app;

import android.content.Intent;

public final class ActivityResult {
  private final int mResultCode;
  
  private final Intent mResultData;
  
  public ActivityResult(int paramInt, Intent paramIntent) {
    this.mResultCode = paramInt;
    this.mResultData = paramIntent;
  }
  
  public int getResultCode() {
    return this.mResultCode;
  }
  
  public Intent getResultData() {
    return this.mResultData;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Instrumentation$ActivityResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */