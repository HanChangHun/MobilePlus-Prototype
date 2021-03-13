package android.database;

public class SQLException extends RuntimeException {
  public SQLException() {}
  
  public SQLException(String paramString) {
    super(paramString);
  }
  
  public SQLException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/SQLException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */