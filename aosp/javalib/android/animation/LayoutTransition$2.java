package android.animation;

import android.view.View;
import android.view.ViewGroup;

class null implements View.OnLayoutChangeListener {
  public void onLayoutChange(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
    anim.setupEndValues();
    Animator animator = anim;
    if (animator instanceof ValueAnimator) {
      paramInt1 = 0;
      PropertyValuesHolder[] arrayOfPropertyValuesHolder = ((ValueAnimator)animator).getValues();
      for (paramInt2 = 0; paramInt2 < arrayOfPropertyValuesHolder.length; paramInt2++) {
        KeyframeSet keyframeSet;
        PropertyValuesHolder propertyValuesHolder = arrayOfPropertyValuesHolder[paramInt2];
        if (propertyValuesHolder.mKeyframes instanceof KeyframeSet) {
          keyframeSet = (KeyframeSet)propertyValuesHolder.mKeyframes;
          if (keyframeSet.mFirstKeyframe == null || keyframeSet.mLastKeyframe == null || !keyframeSet.mFirstKeyframe.getValue().equals(keyframeSet.mLastKeyframe.getValue()))
            paramInt1 = 1; 
        } else if (!((PropertyValuesHolder)keyframeSet).mKeyframes.getValue(0.0F).equals(((PropertyValuesHolder)keyframeSet).mKeyframes.getValue(1.0F))) {
          paramInt1 = 1;
        } 
      } 
      if (paramInt1 == 0)
        return; 
    } 
    long l = 0L;
    paramInt1 = changeReason;
    if (paramInt1 != 2) {
      if (paramInt1 != 3) {
        if (paramInt1 == 4) {
          long l1 = LayoutTransition.access$1000(LayoutTransition.this) + LayoutTransition.access$200(LayoutTransition.this);
          LayoutTransition layoutTransition = LayoutTransition.this;
          LayoutTransition.access$214(layoutTransition, LayoutTransition.access$1100(layoutTransition));
          l = l1;
          if (LayoutTransition.access$1200(LayoutTransition.this) != LayoutTransition.access$1300()) {
            anim.setInterpolator(LayoutTransition.access$1200(LayoutTransition.this));
            l = l1;
          } 
        } 
      } else {
        long l1 = LayoutTransition.access$600(LayoutTransition.this) + LayoutTransition.access$200(LayoutTransition.this);
        LayoutTransition layoutTransition = LayoutTransition.this;
        LayoutTransition.access$214(layoutTransition, LayoutTransition.access$700(layoutTransition));
        l = l1;
        if (LayoutTransition.access$800(LayoutTransition.this) != LayoutTransition.access$900()) {
          anim.setInterpolator(LayoutTransition.access$800(LayoutTransition.this));
          l = l1;
        } 
      } 
    } else {
      long l1 = LayoutTransition.access$100(LayoutTransition.this) + LayoutTransition.access$200(LayoutTransition.this);
      LayoutTransition layoutTransition = LayoutTransition.this;
      LayoutTransition.access$214(layoutTransition, LayoutTransition.access$300(layoutTransition));
      l = l1;
      if (LayoutTransition.access$400(LayoutTransition.this) != LayoutTransition.access$500()) {
        anim.setInterpolator(LayoutTransition.access$400(LayoutTransition.this));
        l = l1;
      } 
    } 
    anim.setStartDelay(l);
    anim.setDuration(duration);
    animator = (Animator)LayoutTransition.access$1400(LayoutTransition.this).get(child);
    if (animator != null)
      animator.cancel(); 
    if ((Animator)LayoutTransition.access$000(LayoutTransition.this).get(child) != null)
      LayoutTransition.access$000(LayoutTransition.this).remove(child); 
    LayoutTransition.access$1400(LayoutTransition.this).put(child, anim);
    parent.requestTransitionStart(LayoutTransition.this);
    child.removeOnLayoutChangeListener(this);
    LayoutTransition.access$1500(LayoutTransition.this).remove(child);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/LayoutTransition$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */