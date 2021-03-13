package android.animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.Map;

final class CleanupCallback implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
  final Map<View, View.OnLayoutChangeListener> layoutChangeListenerMap;
  
  final ViewGroup parent;
  
  CleanupCallback(Map<View, View.OnLayoutChangeListener> paramMap, ViewGroup paramViewGroup) {
    this.layoutChangeListenerMap = paramMap;
    this.parent = paramViewGroup;
  }
  
  private void cleanup() {
    this.parent.getViewTreeObserver().removeOnPreDrawListener(this);
    this.parent.removeOnAttachStateChangeListener(this);
    if (this.layoutChangeListenerMap.size() > 0) {
      for (View view : this.layoutChangeListenerMap.keySet())
        view.removeOnLayoutChangeListener(this.layoutChangeListenerMap.get(view)); 
      this.layoutChangeListenerMap.clear();
    } 
  }
  
  public boolean onPreDraw() {
    cleanup();
    return true;
  }
  
  public void onViewAttachedToWindow(View paramView) {}
  
  public void onViewDetachedFromWindow(View paramView) {
    cleanup();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/LayoutTransition$CleanupCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */