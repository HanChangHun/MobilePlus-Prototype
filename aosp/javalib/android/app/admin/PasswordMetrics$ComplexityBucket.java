package android.app.admin;

enum ComplexityBucket {
  BUCKET_HIGH(327680) {
    boolean allowsCredType(int param2Int) {
      boolean bool;
      if (param2Int == 4) {
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
    
    int getMinimumLength(boolean param2Boolean) {
      byte b;
      if (param2Boolean) {
        b = 6;
      } else {
        b = 8;
      } 
      return b;
    }
  },
  BUCKET_LOW(327680),
  BUCKET_MEDIUM(196608) {
    boolean allowsCredType(int param2Int) {
      boolean bool;
      if (param2Int == 4) {
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
    
    int getMinimumLength(boolean param2Boolean) {
      return 4;
    }
  },
  BUCKET_NONE(196608);
  
  int mComplexityLevel;
  
  static {
    BUCKET_LOW = new null("BUCKET_LOW", 2, 65536);
    null  = new null("BUCKET_NONE", 3, 0);
    BUCKET_NONE = ;
    $VALUES = new ComplexityBucket[] { BUCKET_HIGH, BUCKET_MEDIUM, BUCKET_LOW,  };
  }
  
  ComplexityBucket(int paramInt1) {
    this.mComplexityLevel = paramInt1;
  }
  
  static ComplexityBucket forComplexity(int paramInt) {
    for (ComplexityBucket complexityBucket : values()) {
      if (complexityBucket.mComplexityLevel == paramInt)
        return complexityBucket; 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid complexity level: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  abstract boolean allowsCredType(int paramInt);
  
  abstract boolean allowsNumericPassword();
  
  abstract boolean canHaveSequence();
  
  abstract int getMinimumLength(boolean paramBoolean);
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/PasswordMetrics$ComplexityBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */