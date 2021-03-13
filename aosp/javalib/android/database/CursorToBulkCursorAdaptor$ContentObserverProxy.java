package android.database;

import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

final class ContentObserverProxy extends ContentObserver {
  protected IContentObserver mRemote;
  
  public ContentObserverProxy(IContentObserver paramIContentObserver, IBinder.DeathRecipient paramDeathRecipient) {
    super(null);
    this.mRemote = paramIContentObserver;
    try {
      paramIContentObserver.asBinder().linkToDeath(paramDeathRecipient, 0);
    } catch (RemoteException remoteException) {}
  }
  
  public boolean deliverSelfNotifications() {
    return false;
  }
  
  public void onChange(boolean paramBoolean, Collection<Uri> paramCollection, int paramInt1, int paramInt2) {
    ArrayList arrayList = new ArrayList();
    Objects.requireNonNull(arrayList);
    paramCollection.forEach(new _$$Lambda$vluurRwe4T6AeGPc4y6_YLz_9cI(arrayList));
    Uri[] arrayOfUri = (Uri[])arrayList.toArray((Object[])new Uri[arrayList.size()]);
    try {
      this.mRemote.onChangeEtc(paramBoolean, arrayOfUri, paramInt1, paramInt2);
    } catch (RemoteException remoteException) {}
  }
  
  public boolean unlinkToDeath(IBinder.DeathRecipient paramDeathRecipient) {
    return this.mRemote.asBinder().unlinkToDeath(paramDeathRecipient, 0);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/CursorToBulkCursorAdaptor$ContentObserverProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */