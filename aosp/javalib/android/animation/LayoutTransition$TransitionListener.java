package android.animation;

import android.view.View;
import android.view.ViewGroup;

public interface TransitionListener {
  void endTransition(LayoutTransition paramLayoutTransition, ViewGroup paramViewGroup, View paramView, int paramInt);
  
  void startTransition(LayoutTransition paramLayoutTransition, ViewGroup paramViewGroup, View paramView, int paramInt);
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/LayoutTransition$TransitionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */