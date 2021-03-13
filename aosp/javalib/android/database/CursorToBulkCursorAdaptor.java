package android.database;

import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public final class CursorToBulkCursorAdaptor extends BulkCursorNative implements IBinder.DeathRecipient {
  private static final String TAG = "Cursor";
  
  private CrossProcessCursor mCursor;
  
  private CursorWindow mFilledWindow;
  
  private final Object mLock = new Object();
  
  private ContentObserverProxy mObserver;
  
  private final String mProviderName;
  
  public CursorToBulkCursorAdaptor(Cursor paramCursor, IContentObserver paramIContentObserver, String paramString) {
    if (paramCursor instanceof CrossProcessCursor) {
      this.mCursor = (CrossProcessCursor)paramCursor;
    } else {
      this.mCursor = new CrossProcessCursorWrapper(paramCursor);
    } 
    this.mProviderName = paramString;
    synchronized (this.mLock) {
      createAndRegisterObserverProxyLocked(paramIContentObserver);
      return;
    } 
  }
  
  private void closeFilledWindowLocked() {
    CursorWindow cursorWindow = this.mFilledWindow;
    if (cursorWindow != null) {
      cursorWindow.close();
      this.mFilledWindow = null;
    } 
  }
  
  private void createAndRegisterObserverProxyLocked(IContentObserver paramIContentObserver) {
    if (this.mObserver == null) {
      ContentObserverProxy contentObserverProxy = new ContentObserverProxy(paramIContentObserver, this);
      this.mObserver = contentObserverProxy;
      this.mCursor.registerContentObserver(contentObserverProxy);
      return;
    } 
    throw new IllegalStateException("an observer is already registered");
  }
  
  private void disposeLocked() {
    if (this.mCursor != null) {
      unregisterObserverProxyLocked();
      this.mCursor.close();
      this.mCursor = null;
    } 
    closeFilledWindowLocked();
  }
  
  private void throwIfCursorIsClosed() {
    if (this.mCursor != null)
      return; 
    throw new StaleDataException("Attempted to access a cursor after it has been closed.");
  }
  
  private void unregisterObserverProxyLocked() {
    ContentObserverProxy contentObserverProxy = this.mObserver;
    if (contentObserverProxy != null) {
      this.mCursor.unregisterContentObserver(contentObserverProxy);
      this.mObserver.unlinkToDeath(this);
      this.mObserver = null;
    } 
  }
  
  public void binderDied() {
    synchronized (this.mLock) {
      disposeLocked();
      return;
    } 
  }
  
  public void close() {
    synchronized (this.mLock) {
      disposeLocked();
      return;
    } 
  }
  
  public void deactivate() {
    synchronized (this.mLock) {
      if (this.mCursor != null) {
        unregisterObserverProxyLocked();
        this.mCursor.deactivate();
      } 
      closeFilledWindowLocked();
      return;
    } 
  }
  
  public BulkCursorDescriptor getBulkCursorDescriptor() {
    synchronized (this.mLock) {
      throwIfCursorIsClosed();
      BulkCursorDescriptor bulkCursorDescriptor = new BulkCursorDescriptor();
      this();
      bulkCursorDescriptor.cursor = this;
      bulkCursorDescriptor.columnNames = this.mCursor.getColumnNames();
      bulkCursorDescriptor.wantsAllOnMoveCalls = this.mCursor.getWantsAllOnMoveCalls();
      bulkCursorDescriptor.count = this.mCursor.getCount();
      bulkCursorDescriptor.window = this.mCursor.getWindow();
      if (bulkCursorDescriptor.window != null)
        bulkCursorDescriptor.window.acquireReference(); 
      return bulkCursorDescriptor;
    } 
  }
  
  public Bundle getExtras() {
    synchronized (this.mLock) {
      throwIfCursorIsClosed();
      return this.mCursor.getExtras();
    } 
  }
  
  public CursorWindow getWindow(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLock : Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: invokespecial throwIfCursorIsClosed : ()V
    //   11: aload_0
    //   12: getfield mCursor : Landroid/database/CrossProcessCursor;
    //   15: iload_1
    //   16: invokeinterface moveToPosition : (I)Z
    //   21: ifne -> 32
    //   24: aload_0
    //   25: invokespecial closeFilledWindowLocked : ()V
    //   28: aload_2
    //   29: monitorexit
    //   30: aconst_null
    //   31: areturn
    //   32: aload_0
    //   33: getfield mCursor : Landroid/database/CrossProcessCursor;
    //   36: invokeinterface getWindow : ()Landroid/database/CursorWindow;
    //   41: astore_3
    //   42: aload_3
    //   43: ifnull -> 53
    //   46: aload_0
    //   47: invokespecial closeFilledWindowLocked : ()V
    //   50: goto -> 130
    //   53: aload_0
    //   54: getfield mFilledWindow : Landroid/database/CursorWindow;
    //   57: astore #4
    //   59: aload #4
    //   61: ifnonnull -> 84
    //   64: new android/database/CursorWindow
    //   67: astore_3
    //   68: aload_3
    //   69: aload_0
    //   70: getfield mProviderName : Ljava/lang/String;
    //   73: invokespecial <init> : (Ljava/lang/String;)V
    //   76: aload_0
    //   77: aload_3
    //   78: putfield mFilledWindow : Landroid/database/CursorWindow;
    //   81: goto -> 119
    //   84: iload_1
    //   85: aload #4
    //   87: invokevirtual getStartPosition : ()I
    //   90: if_icmplt -> 111
    //   93: aload #4
    //   95: astore_3
    //   96: iload_1
    //   97: aload #4
    //   99: invokevirtual getStartPosition : ()I
    //   102: aload #4
    //   104: invokevirtual getNumRows : ()I
    //   107: iadd
    //   108: if_icmplt -> 119
    //   111: aload #4
    //   113: invokevirtual clear : ()V
    //   116: aload #4
    //   118: astore_3
    //   119: aload_0
    //   120: getfield mCursor : Landroid/database/CrossProcessCursor;
    //   123: iload_1
    //   124: aload_3
    //   125: invokeinterface fillWindow : (ILandroid/database/CursorWindow;)V
    //   130: aload_3
    //   131: ifnull -> 138
    //   134: aload_3
    //   135: invokevirtual acquireReference : ()V
    //   138: aload_2
    //   139: monitorexit
    //   140: aload_3
    //   141: areturn
    //   142: astore_3
    //   143: aload_2
    //   144: monitorexit
    //   145: aload_3
    //   146: athrow
    // Exception table:
    //   from	to	target	type
    //   7	30	142	finally
    //   32	42	142	finally
    //   46	50	142	finally
    //   53	59	142	finally
    //   64	81	142	finally
    //   84	93	142	finally
    //   96	111	142	finally
    //   111	116	142	finally
    //   119	130	142	finally
    //   134	138	142	finally
    //   138	140	142	finally
    //   143	145	142	finally
  }
  
  public void onMove(int paramInt) {
    synchronized (this.mLock) {
      throwIfCursorIsClosed();
      this.mCursor.onMove(this.mCursor.getPosition(), paramInt);
      return;
    } 
  }
  
  public int requery(IContentObserver paramIContentObserver) {
    synchronized (this.mLock) {
      throwIfCursorIsClosed();
      closeFilledWindowLocked();
      try {
        boolean bool = this.mCursor.requery();
        if (!bool)
          return -1; 
        unregisterObserverProxyLocked();
        createAndRegisterObserverProxyLocked(paramIContentObserver);
        return this.mCursor.getCount();
      } catch (IllegalStateException illegalStateException1) {
        IllegalStateException illegalStateException2 = new IllegalStateException();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(this.mProviderName);
        stringBuilder.append(" Requery misuse db, mCursor isClosed:");
        stringBuilder.append(this.mCursor.isClosed());
        this(stringBuilder.toString(), illegalStateException1);
        throw illegalStateException2;
      } 
    } 
  }
  
  public Bundle respond(Bundle paramBundle) {
    synchronized (this.mLock) {
      throwIfCursorIsClosed();
      paramBundle = this.mCursor.respond(paramBundle);
      return paramBundle;
    } 
  }
  
  private static final class ContentObserverProxy extends ContentObserver {
    protected IContentObserver mRemote;
    
    public ContentObserverProxy(IContentObserver param1IContentObserver, IBinder.DeathRecipient param1DeathRecipient) {
      super(null);
      this.mRemote = param1IContentObserver;
      try {
        param1IContentObserver.asBinder().linkToDeath(param1DeathRecipient, 0);
      } catch (RemoteException remoteException) {}
    }
    
    public boolean deliverSelfNotifications() {
      return false;
    }
    
    public void onChange(boolean param1Boolean, Collection<Uri> param1Collection, int param1Int1, int param1Int2) {
      ArrayList arrayList = new ArrayList();
      Objects.requireNonNull(arrayList);
      param1Collection.forEach(new _$$Lambda$vluurRwe4T6AeGPc4y6_YLz_9cI(arrayList));
      Uri[] arrayOfUri = (Uri[])arrayList.toArray((Object[])new Uri[arrayList.size()]);
      try {
        this.mRemote.onChangeEtc(param1Boolean, arrayOfUri, param1Int1, param1Int2);
      } catch (RemoteException remoteException) {}
    }
    
    public boolean unlinkToDeath(IBinder.DeathRecipient param1DeathRecipient) {
      return this.mRemote.asBinder().unlinkToDeath(param1DeathRecipient, 0);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/CursorToBulkCursorAdaptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */