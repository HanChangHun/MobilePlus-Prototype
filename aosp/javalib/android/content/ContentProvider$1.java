package android.content;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;

class null extends AsyncTask<Object, Object, Object> {
  protected Object doInBackground(Object... paramVarArgs) {
    func.writeDataToPipe(fds[1], uri, mimeType, opts, args);
    try {
      fds[1].close();
    } catch (IOException iOException) {
      Log.w("ContentProvider", "Failure closing pipe", iOException);
    } 
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProvider$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */