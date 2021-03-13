package android.content;

import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import java.util.Map;
import java.util.Observable;

public class ContentQueryMap extends Observable {
  private String[] mColumnNames;
  
  private ContentObserver mContentObserver;
  
  private volatile Cursor mCursor;
  
  private boolean mDirty = false;
  
  private Handler mHandlerForUpdateNotifications = null;
  
  private boolean mKeepUpdated = false;
  
  private int mKeyColumn;
  
  private Map<String, ContentValues> mValues = null;
  
  public ContentQueryMap(Cursor paramCursor, String paramString, boolean paramBoolean, Handler paramHandler) {
    this.mCursor = paramCursor;
    this.mColumnNames = this.mCursor.getColumnNames();
    this.mKeyColumn = this.mCursor.getColumnIndexOrThrow(paramString);
    this.mHandlerForUpdateNotifications = paramHandler;
    setKeepUpdated(paramBoolean);
    if (!paramBoolean)
      readCursorIntoCache(paramCursor); 
  }
  
  private void readCursorIntoCache(Cursor paramCursor) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mValues : Ljava/util/Map;
    //   6: ifnull -> 22
    //   9: aload_0
    //   10: getfield mValues : Ljava/util/Map;
    //   13: invokeinterface size : ()I
    //   18: istore_2
    //   19: goto -> 24
    //   22: iconst_0
    //   23: istore_2
    //   24: new java/util/HashMap
    //   27: astore_3
    //   28: aload_3
    //   29: iload_2
    //   30: invokespecial <init> : (I)V
    //   33: aload_0
    //   34: aload_3
    //   35: putfield mValues : Ljava/util/Map;
    //   38: aload_1
    //   39: invokeinterface moveToNext : ()Z
    //   44: ifeq -> 121
    //   47: new android/content/ContentValues
    //   50: astore_3
    //   51: aload_3
    //   52: invokespecial <init> : ()V
    //   55: iconst_0
    //   56: istore_2
    //   57: iload_2
    //   58: aload_0
    //   59: getfield mColumnNames : [Ljava/lang/String;
    //   62: arraylength
    //   63: if_icmpge -> 97
    //   66: iload_2
    //   67: aload_0
    //   68: getfield mKeyColumn : I
    //   71: if_icmpeq -> 91
    //   74: aload_3
    //   75: aload_0
    //   76: getfield mColumnNames : [Ljava/lang/String;
    //   79: iload_2
    //   80: aaload
    //   81: aload_1
    //   82: iload_2
    //   83: invokeinterface getString : (I)Ljava/lang/String;
    //   88: invokevirtual put : (Ljava/lang/String;Ljava/lang/String;)V
    //   91: iinc #2, 1
    //   94: goto -> 57
    //   97: aload_0
    //   98: getfield mValues : Ljava/util/Map;
    //   101: aload_1
    //   102: aload_0
    //   103: getfield mKeyColumn : I
    //   106: invokeinterface getString : (I)Ljava/lang/String;
    //   111: aload_3
    //   112: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   117: pop
    //   118: goto -> 38
    //   121: aload_0
    //   122: monitorexit
    //   123: return
    //   124: astore_1
    //   125: aload_0
    //   126: monitorexit
    //   127: aload_1
    //   128: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	124	finally
    //   24	38	124	finally
    //   38	55	124	finally
    //   57	91	124	finally
    //   97	118	124	finally
  }
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mContentObserver : Landroid/database/ContentObserver;
    //   6: ifnull -> 27
    //   9: aload_0
    //   10: getfield mCursor : Landroid/database/Cursor;
    //   13: aload_0
    //   14: getfield mContentObserver : Landroid/database/ContentObserver;
    //   17: invokeinterface unregisterContentObserver : (Landroid/database/ContentObserver;)V
    //   22: aload_0
    //   23: aconst_null
    //   24: putfield mContentObserver : Landroid/database/ContentObserver;
    //   27: aload_0
    //   28: getfield mCursor : Landroid/database/Cursor;
    //   31: invokeinterface close : ()V
    //   36: aload_0
    //   37: aconst_null
    //   38: putfield mCursor : Landroid/database/Cursor;
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Exception table:
    //   from	to	target	type
    //   2	27	44	finally
    //   27	41	44	finally
  }
  
  protected void finalize() throws Throwable {
    if (this.mCursor != null)
      close(); 
    super.finalize();
  }
  
  public Map<String, ContentValues> getRows() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mDirty : Z
    //   6: ifeq -> 13
    //   9: aload_0
    //   10: invokevirtual requery : ()V
    //   13: aload_0
    //   14: getfield mValues : Ljava/util/Map;
    //   17: astore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: areturn
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	22	finally
    //   13	18	22	finally
  }
  
  public ContentValues getValues(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mDirty : Z
    //   6: ifeq -> 13
    //   9: aload_0
    //   10: invokevirtual requery : ()V
    //   13: aload_0
    //   14: getfield mValues : Ljava/util/Map;
    //   17: aload_1
    //   18: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   23: checkcast android/content/ContentValues
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: areturn
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	31	finally
    //   13	27	31	finally
  }
  
  public void requery() {
    Cursor cursor = this.mCursor;
    if (cursor == null)
      return; 
    this.mDirty = false;
    if (!cursor.requery())
      return; 
    readCursorIntoCache(cursor);
    setChanged();
    notifyObservers();
  }
  
  public void setKeepUpdated(boolean paramBoolean) {
    if (paramBoolean == this.mKeepUpdated)
      return; 
    this.mKeepUpdated = paramBoolean;
    if (!paramBoolean) {
      this.mCursor.unregisterContentObserver(this.mContentObserver);
      this.mContentObserver = null;
    } else {
      if (this.mHandlerForUpdateNotifications == null)
        this.mHandlerForUpdateNotifications = new Handler(); 
      if (this.mContentObserver == null)
        this.mContentObserver = new ContentObserver(this.mHandlerForUpdateNotifications) {
            public void onChange(boolean param1Boolean) {
              if (ContentQueryMap.this.countObservers() != 0) {
                ContentQueryMap.this.requery();
              } else {
                ContentQueryMap.access$002(ContentQueryMap.this, true);
              } 
            }
          }; 
      this.mCursor.registerContentObserver(this.mContentObserver);
      this.mDirty = true;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentQueryMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */