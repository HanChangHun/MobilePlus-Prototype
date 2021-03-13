package android.graphics;

public enum Result {
  FREEZE_END, FREEZE_START, NORMAL;
  
  static {
    FREEZE_START = new Result("FREEZE_START", 1);
    Result result = new Result("FREEZE_END", 2);
    FREEZE_END = result;
    $VALUES = new Result[] { NORMAL, FREEZE_START, result };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Interpolator$Result.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */