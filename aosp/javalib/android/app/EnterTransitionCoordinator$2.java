package android.app;

import android.view.ViewGroup;

class null implements Runnable {
  int mAnimations;
  
  public void run() {
    int i = this.mAnimations;
    this.mAnimations = i + 1;
    if (i < 2) {
      ViewGroup viewGroup = EnterTransitionCoordinator.this.getDecor();
      if (viewGroup != null)
        viewGroup.postOnAnimation(this); 
    } else if (EnterTransitionCoordinator.this.mResultReceiver != null) {
      EnterTransitionCoordinator.this.mResultReceiver.send(101, null);
      EnterTransitionCoordinator.this.mResultReceiver = null;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/EnterTransitionCoordinator$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */