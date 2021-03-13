package android.app;

import android.graphics.Matrix;
import android.view.GhostView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

class GhostViewListeners implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
  private ViewGroup mDecor;
  
  private Matrix mMatrix = new Matrix();
  
  private View mParent;
  
  private View mView;
  
  private ViewTreeObserver mViewTreeObserver;
  
  public GhostViewListeners(View paramView1, View paramView2, ViewGroup paramViewGroup) {
    this.mView = paramView1;
    this.mParent = paramView2;
    this.mDecor = paramViewGroup;
    this.mViewTreeObserver = paramView2.getViewTreeObserver();
  }
  
  public View getView() {
    return this.mView;
  }
  
  public boolean onPreDraw() {
    GhostView ghostView = GhostView.getGhost(this.mView);
    if (ghostView == null || !this.mView.isAttachedToWindow()) {
      removeListener();
      return true;
    } 
    GhostView.calculateMatrix(this.mView, this.mDecor, this.mMatrix);
    ghostView.setMatrix(this.mMatrix);
    return true;
  }
  
  public void onViewAttachedToWindow(View paramView) {
    this.mViewTreeObserver = paramView.getViewTreeObserver();
  }
  
  public void onViewDetachedFromWindow(View paramView) {
    removeListener();
  }
  
  public void removeListener() {
    if (this.mViewTreeObserver.isAlive()) {
      this.mViewTreeObserver.removeOnPreDrawListener(this);
    } else {
      this.mParent.getViewTreeObserver().removeOnPreDrawListener(this);
    } 
    this.mParent.removeOnAttachStateChangeListener(this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityTransitionCoordinator$GhostViewListeners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */