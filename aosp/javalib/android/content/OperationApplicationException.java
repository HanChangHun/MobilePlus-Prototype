package android.content;

public class OperationApplicationException extends Exception {
  private final int mNumSuccessfulYieldPoints = 0;
  
  public OperationApplicationException() {}
  
  public OperationApplicationException(int paramInt) {}
  
  public OperationApplicationException(String paramString) {
    super(paramString);
  }
  
  public OperationApplicationException(String paramString, int paramInt) {
    super(paramString);
  }
  
  public OperationApplicationException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
  
  public OperationApplicationException(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public int getNumSuccessfulYieldPoints() {
    return this.mNumSuccessfulYieldPoints;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/OperationApplicationException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */