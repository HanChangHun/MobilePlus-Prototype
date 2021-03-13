package android.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.res.Resources;

class PendingAnimator {
  public final int animResId;
  
  public final float pathErrorScale;
  
  public final String target;
  
  public PendingAnimator(int paramInt, float paramFloat, String paramString) {
    this.animResId = paramInt;
    this.pathErrorScale = paramFloat;
    this.target = paramString;
  }
  
  public Animator newInstance(Resources paramResources, Resources.Theme paramTheme) {
    return AnimatorInflater.loadAnimator(paramResources, paramTheme, this.animResId, this.pathErrorScale);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedVectorDrawable$AnimatedVectorDrawableState$PendingAnimator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */