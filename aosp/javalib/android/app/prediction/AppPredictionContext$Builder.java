package android.app.prediction;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Bundle;

@SystemApi
public final class Builder {
  private Bundle mExtras;
  
  private final String mPackageName;
  
  private int mPredictedTargetCount;
  
  private String mUiSurface;
  
  @SystemApi
  public Builder(Context paramContext) {
    this.mPackageName = paramContext.getPackageName();
  }
  
  public AppPredictionContext build() {
    return new AppPredictionContext(this.mUiSurface, this.mPredictedTargetCount, this.mPackageName, this.mExtras, null);
  }
  
  public Builder setExtras(Bundle paramBundle) {
    this.mExtras = paramBundle;
    return this;
  }
  
  public Builder setPredictedTargetCount(int paramInt) {
    this.mPredictedTargetCount = paramInt;
    return this;
  }
  
  public Builder setUiSurface(String paramString) {
    this.mUiSurface = paramString;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppPredictionContext$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */