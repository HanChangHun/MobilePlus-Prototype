package android.graphics.drawable;

import android.content.res.Resources;

final class ClipState extends DrawableWrapper.DrawableWrapperState {
  int mGravity = 3;
  
  int mOrientation = 1;
  
  private int[] mThemeAttrs;
  
  ClipState(ClipState paramClipState, Resources paramResources) {
    super(paramClipState, paramResources);
    if (paramClipState != null) {
      this.mOrientation = paramClipState.mOrientation;
      this.mGravity = paramClipState.mGravity;
    } 
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new ClipDrawable(this, paramResources, null);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/ClipDrawable$ClipState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */