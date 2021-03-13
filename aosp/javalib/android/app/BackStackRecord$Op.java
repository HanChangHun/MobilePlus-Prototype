package android.app;

final class Op {
  int cmd;
  
  int enterAnim;
  
  int exitAnim;
  
  Fragment fragment;
  
  int popEnterAnim;
  
  int popExitAnim;
  
  Op() {}
  
  Op(int paramInt, Fragment paramFragment) {
    this.cmd = paramInt;
    this.fragment = paramFragment;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/BackStackRecord$Op.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */