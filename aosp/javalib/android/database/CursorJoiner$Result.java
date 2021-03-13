package android.database;

public enum Result {
  BOTH, LEFT, RIGHT;
  
  static {
    LEFT = new Result("LEFT", 1);
    Result result = new Result("BOTH", 2);
    BOTH = result;
    $VALUES = new Result[] { RIGHT, LEFT, result };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/CursorJoiner$Result.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */