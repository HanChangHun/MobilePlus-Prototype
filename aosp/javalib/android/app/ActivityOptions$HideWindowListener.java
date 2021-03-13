package android.app;

import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.view.View;
import android.view.Window;
import java.util.ArrayList;

class HideWindowListener extends TransitionListenerAdapter implements ExitTransitionCoordinator.HideSharedElementsCallback {
  private final ExitTransitionCoordinator mExit;
  
  private boolean mSharedElementHidden;
  
  private ArrayList<View> mSharedElements;
  
  private boolean mTransitionEnded;
  
  private final boolean mWaitingForTransition;
  
  private final Window mWindow;
  
  public HideWindowListener(Window paramWindow, ExitTransitionCoordinator paramExitTransitionCoordinator) {
    this.mWindow = paramWindow;
    this.mExit = paramExitTransitionCoordinator;
    this.mSharedElements = new ArrayList<>(paramExitTransitionCoordinator.mSharedElements);
    Transition transition = this.mWindow.getExitTransition();
    if (transition != null) {
      transition.addListener((Transition.TransitionListener)this);
      this.mWaitingForTransition = true;
    } else {
      this.mWaitingForTransition = false;
    } 
    View view = this.mWindow.getDecorView();
    if (view != null)
      if (view.getTag(16908897) == null) {
        view.setTagInternal(16908897, paramExitTransitionCoordinator);
      } else {
        throw new IllegalStateException("Cannot start a transition while one is running");
      }  
  }
  
  private void hideWhenDone() {
    if (this.mSharedElementHidden && (!this.mWaitingForTransition || this.mTransitionEnded)) {
      this.mExit.resetViews();
      int i = this.mSharedElements.size();
      for (byte b = 0; b < i; b++)
        ((View)this.mSharedElements.get(b)).requestLayout(); 
      View view = this.mWindow.getDecorView();
      if (view != null) {
        view.setTagInternal(16908897, null);
        view.setVisibility(8);
      } 
    } 
  }
  
  public void hideSharedElements() {
    this.mSharedElementHidden = true;
    hideWhenDone();
  }
  
  public void onTransitionEnd(Transition paramTransition) {
    this.mTransitionEnded = true;
    hideWhenDone();
    paramTransition.removeListener((Transition.TransitionListener)this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityOptions$HideWindowListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */