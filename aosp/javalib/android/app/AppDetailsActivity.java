package android.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class AppDetailsActivity extends Activity {
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    intent.setData(Uri.fromParts("package", getPackageName(), null));
    startActivity(intent);
    finish();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppDetailsActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */