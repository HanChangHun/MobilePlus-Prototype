package android.app;

import android.os.RemoteException;
import android.view.IOnKeyguardExitResult;

class null extends IOnKeyguardExitResult.Stub {
  public void onKeyguardExitResult(boolean paramBoolean) throws RemoteException {
    KeyguardManager.OnKeyguardExitResult onKeyguardExitResult = callback;
    if (onKeyguardExitResult != null)
      onKeyguardExitResult.onKeyguardExitResult(paramBoolean); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/KeyguardManager$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */