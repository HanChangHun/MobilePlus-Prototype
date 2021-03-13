package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

@Deprecated
public class KeyguardLock {
  private final String mTag;
  
  private final IBinder mToken = (IBinder)new Binder();
  
  KeyguardLock(String paramString) {
    this.mTag = paramString;
  }
  
  public void disableKeyguard() {
    try {
      KeyguardManager.access$100(KeyguardManager.this).disableKeyguard(this.mToken, this.mTag, KeyguardManager.access$000(KeyguardManager.this).getUserId());
    } catch (RemoteException remoteException) {}
  }
  
  public void reenableKeyguard() {
    try {
      KeyguardManager.access$100(KeyguardManager.this).reenableKeyguard(this.mToken, KeyguardManager.access$000(KeyguardManager.this).getUserId());
    } catch (RemoteException remoteException) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/KeyguardManager$KeyguardLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */