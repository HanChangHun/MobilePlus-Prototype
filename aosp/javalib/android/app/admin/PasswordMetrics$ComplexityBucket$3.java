package android.app.admin;

enum null {
  boolean allowsCredType(int paramInt) {
    boolean bool;
    if (paramInt != -1) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  boolean allowsNumericPassword() {
    return true;
  }
  
  boolean canHaveSequence() {
    return true;
  }
  
  int getMinimumLength(boolean paramBoolean) {
    return 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/PasswordMetrics$ComplexityBucket$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */