package android.content;

import android.util.AndroidException;

public class SendIntentException extends AndroidException {
  public SendIntentException() {}
  
  public SendIntentException(Exception paramException) {
    super(paramException);
  }
  
  public SendIntentException(String paramString) {
    super(paramString);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IntentSender$SendIntentException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */