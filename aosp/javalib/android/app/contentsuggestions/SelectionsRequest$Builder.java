package android.app.contentsuggestions;

import android.annotation.SystemApi;
import android.graphics.Point;
import android.os.Bundle;

@SystemApi
public final class Builder {
  private Bundle mExtras;
  
  private Point mInterestPoint;
  
  private final int mTaskId;
  
  public Builder(int paramInt) {
    this.mTaskId = paramInt;
  }
  
  public SelectionsRequest build() {
    return new SelectionsRequest(this.mTaskId, this.mInterestPoint, this.mExtras, null);
  }
  
  public Builder setExtras(Bundle paramBundle) {
    this.mExtras = paramBundle;
    return this;
  }
  
  public Builder setInterestPoint(Point paramPoint) {
    this.mInterestPoint = paramPoint;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/SelectionsRequest$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */