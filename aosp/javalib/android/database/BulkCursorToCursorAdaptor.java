package android.database;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

public final class BulkCursorToCursorAdaptor extends AbstractWindowedCursor {
  private static final String TAG = "BulkCursor";
  
  private IBulkCursor mBulkCursor;
  
  private String[] mColumns;
  
  private int mCount;
  
  private AbstractCursor.SelfContentObserver mObserverBridge = new AbstractCursor.SelfContentObserver(this);
  
  private boolean mWantsAllOnMoveCalls;
  
  private void throwIfCursorIsClosed() {
    if (this.mBulkCursor != null)
      return; 
    throw new StaleDataException("Attempted to access a cursor after it has been closed.");
  }
  
  public void close() {
    super.close();
    IBulkCursor iBulkCursor = this.mBulkCursor;
    if (iBulkCursor != null)
      try {
        iBulkCursor.close();
        this.mBulkCursor = null;
      } catch (RemoteException remoteException) {
        Log.w("BulkCursor", "Remote process exception when closing");
        this.mBulkCursor = null;
      } finally {} 
  }
  
  public void deactivate() {
    super.deactivate();
    IBulkCursor iBulkCursor = this.mBulkCursor;
    if (iBulkCursor != null)
      try {
        iBulkCursor.deactivate();
      } catch (RemoteException remoteException) {
        Log.w("BulkCursor", "Remote process exception when deactivating");
      }  
  }
  
  public String[] getColumnNames() {
    throwIfCursorIsClosed();
    return this.mColumns;
  }
  
  public int getCount() {
    throwIfCursorIsClosed();
    return this.mCount;
  }
  
  public Bundle getExtras() {
    throwIfCursorIsClosed();
    try {
      return this.mBulkCursor.getExtras();
    } catch (RemoteException remoteException) {
      throw new RuntimeException(remoteException);
    } 
  }
  
  public IContentObserver getObserver() {
    return this.mObserverBridge.getContentObserver();
  }
  
  public void initialize(BulkCursorDescriptor paramBulkCursorDescriptor) {
    this.mBulkCursor = paramBulkCursorDescriptor.cursor;
    this.mColumns = paramBulkCursorDescriptor.columnNames;
    this.mWantsAllOnMoveCalls = paramBulkCursorDescriptor.wantsAllOnMoveCalls;
    this.mCount = paramBulkCursorDescriptor.count;
    if (paramBulkCursorDescriptor.window != null)
      setWindow(paramBulkCursorDescriptor.window); 
  }
  
  public boolean onMove(int paramInt1, int paramInt2) {
    throwIfCursorIsClosed();
    try {
      if (this.mWindow == null || paramInt2 < this.mWindow.getStartPosition() || paramInt2 >= this.mWindow.getStartPosition() + this.mWindow.getNumRows()) {
        setWindow(this.mBulkCursor.getWindow(paramInt2));
      } else if (this.mWantsAllOnMoveCalls) {
        this.mBulkCursor.onMove(paramInt2);
      } 
      return !(this.mWindow == null);
    } catch (RemoteException remoteException) {
      Log.e("BulkCursor", "Unable to get window because the remote process is dead");
      return false;
    } 
  }
  
  public boolean requery() {
    throwIfCursorIsClosed();
    try {
      int i = this.mBulkCursor.requery(getObserver());
      this.mCount = i;
      if (i != -1) {
        this.mPos = -1;
        closeWindow();
        super.requery();
        return true;
      } 
      deactivate();
      return false;
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to requery because the remote process exception ");
      stringBuilder.append(exception.getMessage());
      Log.e("BulkCursor", stringBuilder.toString());
      deactivate();
      return false;
    } 
  }
  
  public Bundle respond(Bundle paramBundle) {
    throwIfCursorIsClosed();
    try {
      return this.mBulkCursor.respond(paramBundle);
    } catch (RemoteException remoteException) {
      Log.w("BulkCursor", "respond() threw RemoteException, returning an empty bundle.", (Throwable)remoteException);
      return Bundle.EMPTY;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/BulkCursorToCursorAdaptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */