package android.app.admin;

enum null {
  boolean allowsCredType(int paramInt) {
    boolean bool;
    if (paramInt == 4) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  boolean allowsNumericPassword() {
    return false;
  }
  
  boolean canHaveSequence() {
    return false;
  }
  
  int getMinimumLength(boolean paramBoolean) {
    return 4;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/PasswordMetrics$ComplexityBucket$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */