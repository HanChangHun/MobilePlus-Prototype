package android.content.pm;

public class PackageParserException extends Exception {
  public final int error;
  
  public PackageParserException(int paramInt, String paramString) {
    super(paramString);
    this.error = paramInt;
  }
  
  public PackageParserException(int paramInt, String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
    this.error = paramInt;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$PackageParserException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */