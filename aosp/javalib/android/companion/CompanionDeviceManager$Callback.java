package android.companion;

import android.content.IntentSender;

public abstract class Callback {
  public abstract void onDeviceFound(IntentSender paramIntentSender);
  
  public abstract void onFailure(CharSequence paramCharSequence);
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/CompanionDeviceManager$Callback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */