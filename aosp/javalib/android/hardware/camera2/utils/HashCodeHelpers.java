package android.hardware.camera2.utils;

public final class HashCodeHelpers {
  public static int hashCode(float... paramVarArgs) {
    byte b = 0;
    if (paramVarArgs == null)
      return 0; 
    int i = 1;
    int j = paramVarArgs.length;
    while (b < j) {
      i = (i << 5) - i ^ Float.floatToIntBits(paramVarArgs[b]);
      b++;
    } 
    return i;
  }
  
  public static int hashCode(int... paramVarArgs) {
    byte b = 0;
    if (paramVarArgs == null)
      return 0; 
    int i = 1;
    int j = paramVarArgs.length;
    while (b < j) {
      i = (i << 5) - i ^ paramVarArgs[b];
      b++;
    } 
    return i;
  }
  
  public static <T> int hashCodeGeneric(T... paramVarArgs) {
    if (paramVarArgs == null)
      return 0; 
    int i = 1;
    int j = paramVarArgs.length;
    for (byte b = 0; b < j; b++) {
      int k;
      T t = paramVarArgs[b];
      if (t == null) {
        k = 0;
      } else {
        k = t.hashCode();
      } 
      i = (i << 5) - i ^ k;
    } 
    return i;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/HashCodeHelpers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */