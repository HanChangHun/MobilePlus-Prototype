package android.app.prediction;

import android.annotation.SystemApi;

@SystemApi
public final class Builder {
  private int mAction;
  
  private String mLocation;
  
  private AppTarget mTarget;
  
  public Builder(AppTarget paramAppTarget, int paramInt) {
    this.mTarget = paramAppTarget;
    this.mAction = paramInt;
  }
  
  public AppTargetEvent build() {
    return new AppTargetEvent(this.mTarget, this.mLocation, this.mAction, null);
  }
  
  public Builder setLaunchLocation(String paramString) {
    this.mLocation = paramString;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppTargetEvent$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */