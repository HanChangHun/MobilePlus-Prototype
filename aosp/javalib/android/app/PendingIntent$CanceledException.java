package android.app;

import android.util.AndroidException;

public class CanceledException extends AndroidException {
  public CanceledException() {}
  
  public CanceledException(Exception paramException) {
    super(paramException);
  }
  
  public CanceledException(String paramString) {
    super(paramString);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/PendingIntent$CanceledException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */