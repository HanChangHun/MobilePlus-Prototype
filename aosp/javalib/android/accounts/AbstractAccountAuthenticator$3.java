package android.accounts;

import android.os.Bundle;

class null implements Runnable {
  public void run() {
    Bundle bundle1 = new Bundle();
    bundle1.putString("android.accounts.AbstractAccountAuthenticato.KEY_AUTH_TOKEN_TYPE", authTokenType);
    bundle1.putStringArray("android.accounts.AbstractAccountAuthenticator.KEY_REQUIRED_FEATURES", requiredFeatures);
    bundle1.putBundle("android.accounts.AbstractAccountAuthenticator.KEY_OPTIONS", options);
    Bundle bundle2 = new Bundle();
    bundle2.putBundle("accountSessionBundle", bundle1);
    response.onResult(bundle2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/AbstractAccountAuthenticator$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */