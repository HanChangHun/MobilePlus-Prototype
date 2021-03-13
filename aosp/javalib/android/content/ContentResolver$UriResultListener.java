package android.content;

import android.net.Uri;
import android.os.Bundle;

class UriResultListener extends ContentResolver.ResultListener<Uri> {
  private UriResultListener() {}
  
  protected Uri getResultFromBundle(Bundle paramBundle) {
    return (Uri)paramBundle.getParcelable("result");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentResolver$UriResultListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */