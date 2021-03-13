package android.content;

import android.os.Bundle;

class StringResultListener extends ContentResolver.ResultListener<String> {
  private StringResultListener() {}
  
  protected String getResultFromBundle(Bundle paramBundle) {
    return paramBundle.getString("result");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentResolver$StringResultListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */