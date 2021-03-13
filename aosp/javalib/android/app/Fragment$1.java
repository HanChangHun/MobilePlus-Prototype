package android.app;

class null extends FragmentContainer {
  public <T extends android.view.View> T onFindViewById(int paramInt) {
    if (Fragment.this.mView != null)
      return (T)Fragment.this.mView.findViewById(paramInt); 
    throw new IllegalStateException("Fragment does not have a view");
  }
  
  public boolean onHasView() {
    boolean bool;
    if (Fragment.this.mView != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Fragment$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */