package android.content;

import android.util.Log;

class NotRespondingRunnable implements Runnable {
  private NotRespondingRunnable() {}
  
  public void run() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Detected provider not responding: ");
    stringBuilder.append(ContentProviderClient.access$100(ContentProviderClient.this));
    Log.w("ContentProviderClient", stringBuilder.toString());
    ContentProviderClient.access$200(ContentProviderClient.this).appNotRespondingViaProvider(ContentProviderClient.access$100(ContentProviderClient.this));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProviderClient$NotRespondingRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */