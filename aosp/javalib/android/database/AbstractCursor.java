package android.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractCursor implements CrossProcessCursor {
  private static final String TAG = "Cursor";
  
  @Deprecated
  protected boolean mClosed;
  
  private final ContentObservable mContentObservable = new ContentObservable();
  
  @Deprecated
  protected ContentResolver mContentResolver;
  
  protected Long mCurrentRowID;
  
  private final DataSetObservable mDataSetObservable = new DataSetObservable();
  
  private Bundle mExtras = Bundle.EMPTY;
  
  private Uri mNotifyUri;
  
  private List<Uri> mNotifyUris;
  
  @Deprecated
  protected int mPos = -1;
  
  protected int mRowIdColumnIndex;
  
  private ContentObserver mSelfObserver;
  
  private final Object mSelfObserverLock = new Object();
  
  private boolean mSelfObserverRegistered;
  
  protected HashMap<Long, Map<String, Object>> mUpdatedRows;
  
  protected void checkPosition() {
    if (-1 != this.mPos && getCount() != this.mPos)
      return; 
    throw new CursorIndexOutOfBoundsException(this.mPos, getCount());
  }
  
  public void close() {
    this.mClosed = true;
    this.mContentObservable.unregisterAll();
    onDeactivateOrClose();
  }
  
  public void copyStringToBuffer(int paramInt, CharArrayBuffer paramCharArrayBuffer) {
    String str = getString(paramInt);
    if (str != null) {
      char[] arrayOfChar = paramCharArrayBuffer.data;
      if (arrayOfChar == null || arrayOfChar.length < str.length()) {
        paramCharArrayBuffer.data = str.toCharArray();
      } else {
        str.getChars(0, str.length(), arrayOfChar, 0);
      } 
      paramCharArrayBuffer.sizeCopied = str.length();
    } else {
      paramCharArrayBuffer.sizeCopied = 0;
    } 
  }
  
  public void deactivate() {
    onDeactivateOrClose();
  }
  
  public void fillWindow(int paramInt, CursorWindow paramCursorWindow) {
    DatabaseUtils.cursorFillWindow(this, paramInt, paramCursorWindow);
  }
  
  protected void finalize() {
    ContentObserver contentObserver = this.mSelfObserver;
    if (contentObserver != null && this.mSelfObserverRegistered == true)
      this.mContentResolver.unregisterContentObserver(contentObserver); 
    try {
      if (!this.mClosed)
        close(); 
    } catch (Exception exception) {}
  }
  
  public byte[] getBlob(int paramInt) {
    throw new UnsupportedOperationException("getBlob is not supported");
  }
  
  public int getColumnCount() {
    return (getColumnNames()).length;
  }
  
  public int getColumnIndex(String paramString) {
    int i = paramString.lastIndexOf('.');
    String str = paramString;
    if (i != -1) {
      Exception exception = new Exception();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("requesting column name with table name -- ");
      stringBuilder.append(paramString);
      Log.e("Cursor", stringBuilder.toString(), exception);
      str = paramString.substring(i + 1);
    } 
    String[] arrayOfString = getColumnNames();
    int j = arrayOfString.length;
    for (i = 0; i < j; i++) {
      if (arrayOfString[i].equalsIgnoreCase(str))
        return i; 
    } 
    return -1;
  }
  
  public int getColumnIndexOrThrow(String paramString) {
    int i = getColumnIndex(paramString);
    if (i >= 0)
      return i; 
    String str = "";
    try {
      String str1 = Arrays.toString((Object[])getColumnNames());
      str = str1;
    } catch (Exception exception) {
      Log.d("Cursor", "Cannot collect column names for debug purposes", exception);
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("column '");
    stringBuilder.append(paramString);
    stringBuilder.append("' does not exist. Available columns: ");
    stringBuilder.append(str);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public String getColumnName(int paramInt) {
    return getColumnNames()[paramInt];
  }
  
  public abstract String[] getColumnNames();
  
  public abstract int getCount();
  
  public abstract double getDouble(int paramInt);
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public abstract float getFloat(int paramInt);
  
  public abstract int getInt(int paramInt);
  
  public abstract long getLong(int paramInt);
  
  public Uri getNotificationUri() {
    synchronized (this.mSelfObserverLock) {
      return this.mNotifyUri;
    } 
  }
  
  public List<Uri> getNotificationUris() {
    synchronized (this.mSelfObserverLock) {
      return this.mNotifyUris;
    } 
  }
  
  public final int getPosition() {
    return this.mPos;
  }
  
  public abstract short getShort(int paramInt);
  
  public abstract String getString(int paramInt);
  
  public int getType(int paramInt) {
    return 3;
  }
  
  @Deprecated
  protected Object getUpdatedField(int paramInt) {
    return null;
  }
  
  public boolean getWantsAllOnMoveCalls() {
    return false;
  }
  
  public CursorWindow getWindow() {
    return null;
  }
  
  public final boolean isAfterLast() {
    int i = getCount();
    boolean bool = true;
    if (i == 0)
      return true; 
    if (this.mPos != getCount())
      bool = false; 
    return bool;
  }
  
  public final boolean isBeforeFirst() {
    int i = getCount();
    boolean bool = true;
    if (i == 0)
      return true; 
    if (this.mPos != -1)
      bool = false; 
    return bool;
  }
  
  public boolean isClosed() {
    return this.mClosed;
  }
  
  @Deprecated
  protected boolean isFieldUpdated(int paramInt) {
    return false;
  }
  
  public final boolean isFirst() {
    boolean bool;
    if (this.mPos == 0 && getCount() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public final boolean isLast() {
    boolean bool;
    int i = getCount();
    if (this.mPos == i - 1 && i != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public abstract boolean isNull(int paramInt);
  
  public final boolean move(int paramInt) {
    return moveToPosition(this.mPos + paramInt);
  }
  
  public final boolean moveToFirst() {
    return moveToPosition(0);
  }
  
  public final boolean moveToLast() {
    return moveToPosition(getCount() - 1);
  }
  
  public final boolean moveToNext() {
    return moveToPosition(this.mPos + 1);
  }
  
  public final boolean moveToPosition(int paramInt) {
    int i = getCount();
    if (paramInt >= i) {
      this.mPos = i;
      return false;
    } 
    if (paramInt < 0) {
      this.mPos = -1;
      return false;
    } 
    i = this.mPos;
    if (paramInt == i)
      return true; 
    boolean bool = onMove(i, paramInt);
    if (!bool) {
      this.mPos = -1;
    } else {
      this.mPos = paramInt;
    } 
    return bool;
  }
  
  public final boolean moveToPrevious() {
    return moveToPosition(this.mPos - 1);
  }
  
  protected void onChange(boolean paramBoolean) {
    synchronized (this.mSelfObserverLock) {
      this.mContentObservable.dispatchChange(paramBoolean, (Uri)null);
      if (this.mNotifyUris != null && paramBoolean) {
        int i = this.mNotifyUris.size();
        for (byte b = 0; b < i; b++) {
          Uri uri = this.mNotifyUris.get(b);
          this.mContentResolver.notifyChange(uri, this.mSelfObserver);
        } 
      } 
      return;
    } 
  }
  
  protected void onDeactivateOrClose() {
    ContentObserver contentObserver = this.mSelfObserver;
    if (contentObserver != null) {
      this.mContentResolver.unregisterContentObserver(contentObserver);
      this.mSelfObserverRegistered = false;
    } 
    this.mDataSetObservable.notifyInvalidated();
  }
  
  public boolean onMove(int paramInt1, int paramInt2) {
    return true;
  }
  
  public void registerContentObserver(ContentObserver paramContentObserver) {
    this.mContentObservable.registerObserver(paramContentObserver);
  }
  
  public void registerDataSetObserver(DataSetObserver paramDataSetObserver) {
    this.mDataSetObservable.registerObserver(paramDataSetObserver);
  }
  
  public boolean requery() {
    if (this.mSelfObserver != null && !this.mSelfObserverRegistered) {
      int i = this.mNotifyUris.size();
      for (byte b = 0; b < i; b++) {
        Uri uri = this.mNotifyUris.get(b);
        this.mContentResolver.registerContentObserver(uri, true, this.mSelfObserver);
      } 
      this.mSelfObserverRegistered = true;
    } 
    this.mDataSetObservable.notifyChanged();
    return true;
  }
  
  public Bundle respond(Bundle paramBundle) {
    return Bundle.EMPTY;
  }
  
  public void setExtras(Bundle paramBundle) {
    if (paramBundle == null)
      paramBundle = Bundle.EMPTY; 
    this.mExtras = paramBundle;
  }
  
  public void setNotificationUri(ContentResolver paramContentResolver, Uri paramUri) {
    setNotificationUris(paramContentResolver, Arrays.asList(new Uri[] { paramUri }));
  }
  
  public void setNotificationUris(ContentResolver paramContentResolver, List<Uri> paramList) {
    Objects.requireNonNull(paramContentResolver);
    Objects.requireNonNull(paramList);
    setNotificationUris(paramContentResolver, paramList, paramContentResolver.getUserId(), true);
  }
  
  public void setNotificationUris(ContentResolver paramContentResolver, List<Uri> paramList, int paramInt, boolean paramBoolean) {
    synchronized (this.mSelfObserverLock) {
      this.mNotifyUris = paramList;
      this.mNotifyUri = paramList.get(0);
      this.mContentResolver = paramContentResolver;
      if (this.mSelfObserver != null) {
        paramContentResolver.unregisterContentObserver(this.mSelfObserver);
        this.mSelfObserverRegistered = false;
      } 
      if (paramBoolean) {
        SelfContentObserver selfContentObserver = new SelfContentObserver();
        this(this);
        this.mSelfObserver = selfContentObserver;
        int i = this.mNotifyUris.size();
        for (byte b = 0; b < i; b++) {
          Uri uri = this.mNotifyUris.get(b);
          this.mContentResolver.registerContentObserver(uri, true, this.mSelfObserver, paramInt);
        } 
        this.mSelfObserverRegistered = true;
      } 
      return;
    } 
  }
  
  public void unregisterContentObserver(ContentObserver paramContentObserver) {
    if (!this.mClosed)
      this.mContentObservable.unregisterObserver(paramContentObserver); 
  }
  
  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver) {
    this.mDataSetObservable.unregisterObserver(paramDataSetObserver);
  }
  
  protected static class SelfContentObserver extends ContentObserver {
    WeakReference<AbstractCursor> mCursor;
    
    public SelfContentObserver(AbstractCursor param1AbstractCursor) {
      super(null);
      this.mCursor = new WeakReference<>(param1AbstractCursor);
    }
    
    public boolean deliverSelfNotifications() {
      return false;
    }
    
    public void onChange(boolean param1Boolean) {
      AbstractCursor abstractCursor = this.mCursor.get();
      if (abstractCursor != null)
        abstractCursor.onChange(false); 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/AbstractCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */