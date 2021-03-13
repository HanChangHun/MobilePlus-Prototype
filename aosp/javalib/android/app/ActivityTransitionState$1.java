package android.app;

class null implements Runnable {
  public void run() {
    if (ActivityTransitionState.access$000(ActivityTransitionState.this) == null || ActivityTransitionState.access$000(ActivityTransitionState.this).isWaitingForRemoteExit()) {
      ActivityTransitionState.access$100(ActivityTransitionState.this);
      ActivityTransitionState.access$200(ActivityTransitionState.this);
      return;
    } 
    if (ActivityTransitionState.access$000(ActivityTransitionState.this).isReturning())
      ActivityTransitionState.access$000(ActivityTransitionState.this).runAfterTransitionsComplete(new _$$Lambda$ActivityTransitionState$1$fqoR3vg9Harhjtb0p87HpKinLK8(this)); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityTransitionState$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */