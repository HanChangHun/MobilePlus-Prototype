package android.app.admin;

import android.content.pm.IPackageDataObserver;
import java.util.concurrent.Executor;

class null extends IPackageDataObserver.Stub {
  public void onRemoveCompleted(String paramString, boolean paramBoolean) {
    executor.execute(new _$$Lambda$DevicePolicyManager$1$k6Rmp3Fg9FFATYRU5Z7rHDXGemA(listener, paramString, paramBoolean));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DevicePolicyManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */