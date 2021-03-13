package android.app;

import android.graphics.Rect;
import android.util.Rational;
import java.util.ArrayList;
import java.util.List;

public class Builder {
  private Rational mAspectRatio;
  
  private Rect mSourceRectHint;
  
  private List<RemoteAction> mUserActions;
  
  public PictureInPictureParams build() {
    return new PictureInPictureParams(this.mAspectRatio, this.mUserActions, this.mSourceRectHint);
  }
  
  public Builder setActions(List<RemoteAction> paramList) {
    if (this.mUserActions != null)
      this.mUserActions = null; 
    if (paramList != null)
      this.mUserActions = new ArrayList<>(paramList); 
    return this;
  }
  
  public Builder setAspectRatio(Rational paramRational) {
    this.mAspectRatio = paramRational;
    return this;
  }
  
  public Builder setSourceRectHint(Rect paramRect) {
    if (paramRect == null) {
      this.mSourceRectHint = null;
    } else {
      this.mSourceRectHint = new Rect(paramRect);
    } 
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PictureInPictureParams$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */