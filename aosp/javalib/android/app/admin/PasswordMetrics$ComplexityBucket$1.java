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
    byte b;
    if (paramBoolean) {
      b = 6;
    } else {
      b = 8;
    } 
    return b;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/PasswordMetrics$ComplexityBucket$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */