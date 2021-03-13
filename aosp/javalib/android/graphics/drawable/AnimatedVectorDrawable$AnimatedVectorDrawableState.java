package android.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.res.Resources;
import android.util.ArrayMap;
import android.util.Log;
import java.util.ArrayList;

class AnimatedVectorDrawableState extends Drawable.ConstantState {
  ArrayList<Animator> mAnimators;
  
  int mChangingConfigurations;
  
  ArrayList<PendingAnimator> mPendingAnims;
  
  private final boolean mShouldIgnoreInvalidAnim = AnimatedVectorDrawable.access$400();
  
  ArrayMap<Animator, String> mTargetNameMap;
  
  VectorDrawable mVectorDrawable;
  
  public AnimatedVectorDrawableState(AnimatedVectorDrawableState paramAnimatedVectorDrawableState, Drawable.Callback paramCallback, Resources paramResources) {
    if (paramAnimatedVectorDrawableState != null) {
      this.mChangingConfigurations = paramAnimatedVectorDrawableState.mChangingConfigurations;
      VectorDrawable vectorDrawable = paramAnimatedVectorDrawableState.mVectorDrawable;
      if (vectorDrawable != null) {
        Drawable.ConstantState constantState = vectorDrawable.getConstantState();
        if (paramResources != null) {
          this.mVectorDrawable = (VectorDrawable)constantState.newDrawable(paramResources);
        } else {
          this.mVectorDrawable = (VectorDrawable)constantState.newDrawable();
        } 
        VectorDrawable vectorDrawable1 = (VectorDrawable)this.mVectorDrawable.mutate();
        this.mVectorDrawable = vectorDrawable1;
        vectorDrawable1.setCallback(paramCallback);
        this.mVectorDrawable.setLayoutDirection(paramAnimatedVectorDrawableState.mVectorDrawable.getLayoutDirection());
        this.mVectorDrawable.setBounds(paramAnimatedVectorDrawableState.mVectorDrawable.getBounds());
        this.mVectorDrawable.setAllowCaching(false);
      } 
      if (paramAnimatedVectorDrawableState.mAnimators != null)
        this.mAnimators = new ArrayList<>(paramAnimatedVectorDrawableState.mAnimators); 
      if (paramAnimatedVectorDrawableState.mTargetNameMap != null)
        this.mTargetNameMap = new ArrayMap(paramAnimatedVectorDrawableState.mTargetNameMap); 
      if (paramAnimatedVectorDrawableState.mPendingAnims != null)
        this.mPendingAnims = new ArrayList<>(paramAnimatedVectorDrawableState.mPendingAnims); 
    } else {
      this.mVectorDrawable = new VectorDrawable();
    } 
  }
  
  private Animator prepareLocalAnimator(int paramInt) {
    StringBuilder stringBuilder;
    Animator animator1 = this.mAnimators.get(paramInt);
    Animator animator2 = animator1.clone();
    String str = (String)this.mTargetNameMap.get(animator1);
    Object object = this.mVectorDrawable.getTargetByName(str);
    if (!this.mShouldIgnoreInvalidAnim)
      if (object != null) {
        if (!(object instanceof VectorDrawable.VectorDrawableState) && !(object instanceof VectorDrawable.VObject)) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Target should be either VGroup, VPath, or ConstantState, ");
          stringBuilder.append(object.getClass());
          stringBuilder.append(" is not supported");
          throw new UnsupportedOperationException(stringBuilder.toString());
        } 
      } else {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Target with the name \"");
        stringBuilder.append(str);
        stringBuilder.append("\" cannot be found in the VectorDrawable to be animated.");
        throw new IllegalStateException(stringBuilder.toString());
      }  
    stringBuilder.setTarget(object);
    return (Animator)stringBuilder;
  }
  
  public void addPendingAnimator(int paramInt, float paramFloat, String paramString) {
    if (this.mPendingAnims == null)
      this.mPendingAnims = new ArrayList<>(1); 
    this.mPendingAnims.add(new PendingAnimator(paramInt, paramFloat, paramString));
  }
  
  public void addTargetAnimator(String paramString, Animator paramAnimator) {
    if (this.mAnimators == null) {
      this.mAnimators = new ArrayList<>(1);
      this.mTargetNameMap = new ArrayMap(1);
    } 
    this.mAnimators.add(paramAnimator);
    this.mTargetNameMap.put(paramAnimator, paramString);
  }
  
  public boolean canApplyTheme() {
    VectorDrawable vectorDrawable = this.mVectorDrawable;
    return ((vectorDrawable != null && vectorDrawable.canApplyTheme()) || this.mPendingAnims != null || super.canApplyTheme());
  }
  
  public int getChangingConfigurations() {
    return this.mChangingConfigurations;
  }
  
  public void inflatePendingAnimators(Resources paramResources, Resources.Theme paramTheme) {
    ArrayList<PendingAnimator> arrayList = this.mPendingAnims;
    if (arrayList != null) {
      this.mPendingAnims = null;
      byte b = 0;
      int i = arrayList.size();
      while (b < i) {
        PendingAnimator pendingAnimator = arrayList.get(b);
        Animator animator = pendingAnimator.newInstance(paramResources, paramTheme);
        AnimatedVectorDrawable.access$600(animator, pendingAnimator.target, this.mVectorDrawable, this.mShouldIgnoreInvalidAnim);
        addTargetAnimator(pendingAnimator.target, animator);
        b++;
      } 
    } 
  }
  
  public Drawable newDrawable() {
    return new AnimatedVectorDrawable(this, null, null);
  }
  
  public Drawable newDrawable(Resources paramResources) {
    return new AnimatedVectorDrawable(this, paramResources, null);
  }
  
  public void prepareLocalAnimators(AnimatorSet paramAnimatorSet, Resources paramResources) {
    int i;
    if (this.mPendingAnims != null) {
      if (paramResources != null) {
        inflatePendingAnimators(paramResources, null);
      } else {
        Log.e("AnimatedVectorDrawable", "Failed to load animators. Either the AnimatedVectorDrawable must be created using a Resources object or applyTheme() must be called with a non-null Theme object.");
      } 
      this.mPendingAnims = null;
    } 
    ArrayList<Animator> arrayList = this.mAnimators;
    if (arrayList == null) {
      i = 0;
    } else {
      i = arrayList.size();
    } 
    if (i > 0) {
      AnimatorSet.Builder builder = paramAnimatorSet.play(prepareLocalAnimator(0));
      for (byte b = 1; b < i; b++)
        builder.with(prepareLocalAnimator(b)); 
    } 
  }
  
  private static class PendingAnimator {
    public final int animResId;
    
    public final float pathErrorScale;
    
    public final String target;
    
    public PendingAnimator(int param2Int, float param2Float, String param2String) {
      this.animResId = param2Int;
      this.pathErrorScale = param2Float;
      this.target = param2String;
    }
    
    public Animator newInstance(Resources param2Resources, Resources.Theme param2Theme) {
      return AnimatorInflater.loadAnimator(param2Resources, param2Theme, this.animResId, this.pathErrorScale);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/AnimatedVectorDrawable$AnimatedVectorDrawableState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */