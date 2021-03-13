package android.app;

import android.content.pm.InstantAppResolveInfo;
import android.os.Bundle;
import android.os.IRemoteCallback;
import android.os.RemoteException;
import java.util.List;

public final class InstantAppResolutionCallback {
  private final IRemoteCallback mCallback;
  
  private final int mSequence;
  
  public InstantAppResolutionCallback(int paramInt, IRemoteCallback paramIRemoteCallback) {
    this.mCallback = paramIRemoteCallback;
    this.mSequence = paramInt;
  }
  
  public void onInstantAppResolveInfo(List<InstantAppResolveInfo> paramList) {
    Bundle bundle = new Bundle();
    bundle.putParcelableList("android.app.extra.RESOLVE_INFO", paramList);
    bundle.putInt("android.app.extra.SEQUENCE", this.mSequence);
    try {
      this.mCallback.sendResult(bundle);
    } catch (RemoteException remoteException) {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/InstantAppResolverService$InstantAppResolutionCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */