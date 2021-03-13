package android.database;

import android.app.compat.CompatChanges;
import android.net.Uri;
import android.os.Handler;
import android.os.Process;
import android.os.UserHandle;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public abstract class ContentObserver {
  private static final long ADD_CONTENT_OBSERVER_FLAGS = 150939131L;
  
  Handler mHandler;
  
  private final Object mLock = new Object();
  
  private Transport mTransport;
  
  public ContentObserver(Handler paramHandler) {
    this.mHandler = paramHandler;
  }
  
  public boolean deliverSelfNotifications() {
    return false;
  }
  
  @Deprecated
  public final void dispatchChange(boolean paramBoolean) {
    dispatchChange(paramBoolean, null);
  }
  
  public final void dispatchChange(boolean paramBoolean, Uri paramUri) {
    dispatchChange(paramBoolean, paramUri, 0);
  }
  
  public final void dispatchChange(boolean paramBoolean, Uri paramUri, int paramInt) {
    dispatchChange(paramBoolean, Arrays.asList(new Uri[] { paramUri }, ), paramInt);
  }
  
  public final void dispatchChange(boolean paramBoolean, Collection<Uri> paramCollection, int paramInt) {
    dispatchChange(paramBoolean, paramCollection, paramInt, UserHandle.getCallingUserId());
  }
  
  public final void dispatchChange(boolean paramBoolean, Collection<Uri> paramCollection, int paramInt1, int paramInt2) {
    Handler handler = this.mHandler;
    if (handler == null) {
      onChange(paramBoolean, paramCollection, paramInt1, paramInt2);
    } else {
      handler.post(new _$$Lambda$ContentObserver$MgqiYb2qvgLhoXTioYXq9MvvpNk(this, paramBoolean, paramCollection, paramInt1, paramInt2));
    } 
  }
  
  public IContentObserver getContentObserver() {
    synchronized (this.mLock) {
      if (this.mTransport == null) {
        Transport transport = new Transport();
        this(this);
        this.mTransport = transport;
      } 
      return this.mTransport;
    } 
  }
  
  public void onChange(boolean paramBoolean) {}
  
  public void onChange(boolean paramBoolean, Uri paramUri) {
    onChange(paramBoolean);
  }
  
  public void onChange(boolean paramBoolean, Uri paramUri, int paramInt) {
    onChange(paramBoolean, paramUri);
  }
  
  public void onChange(boolean paramBoolean, Collection<Uri> paramCollection, int paramInt) {
    Iterator<Uri> iterator = paramCollection.iterator();
    while (iterator.hasNext())
      onChange(paramBoolean, iterator.next(), paramInt); 
  }
  
  public void onChange(boolean paramBoolean, Collection<Uri> paramCollection, int paramInt1, int paramInt2) {
    if (!CompatChanges.isChangeEnabled(150939131L) || Process.myUid() == 1000) {
      onChange(paramBoolean, paramCollection, paramInt2);
      return;
    } 
    onChange(paramBoolean, paramCollection, paramInt1);
  }
  
  public IContentObserver releaseContentObserver() {
    synchronized (this.mLock) {
      Transport transport = this.mTransport;
      if (transport != null) {
        transport.releaseContentObserver();
        this.mTransport = null;
      } 
      return transport;
    } 
  }
  
  private static final class Transport extends IContentObserver.Stub {
    private ContentObserver mContentObserver;
    
    public Transport(ContentObserver param1ContentObserver) {
      this.mContentObserver = param1ContentObserver;
    }
    
    public void onChange(boolean param1Boolean, Uri param1Uri, int param1Int) {
      onChangeEtc(param1Boolean, new Uri[] { param1Uri }, 0, param1Int);
    }
    
    public void onChangeEtc(boolean param1Boolean, Uri[] param1ArrayOfUri, int param1Int1, int param1Int2) {
      ContentObserver contentObserver = this.mContentObserver;
      if (contentObserver != null)
        contentObserver.dispatchChange(param1Boolean, Arrays.asList(param1ArrayOfUri), param1Int1, param1Int2); 
    }
    
    public void releaseContentObserver() {
      this.mContentObserver = null;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/ContentObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */