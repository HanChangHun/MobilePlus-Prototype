package android.drm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Deprecated
public class DrmManagerClient implements AutoCloseable {
  private static final int ACTION_PROCESS_DRM_INFO = 1002;
  
  private static final int ACTION_REMOVE_ALL_RIGHTS = 1001;
  
  public static final int ERROR_NONE = 0;
  
  public static final int ERROR_UNKNOWN = -2000;
  
  public static final int INVALID_SESSION = -1;
  
  private static final String TAG = "DrmManagerClient";
  
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  private final AtomicBoolean mClosed = new AtomicBoolean();
  
  private Context mContext;
  
  private EventHandler mEventHandler;
  
  HandlerThread mEventThread;
  
  private InfoHandler mInfoHandler;
  
  HandlerThread mInfoThread;
  
  private long mNativeContext;
  
  private OnErrorListener mOnErrorListener;
  
  private OnEventListener mOnEventListener;
  
  private OnInfoListener mOnInfoListener;
  
  private int mUniqueId;
  
  static {
    System.loadLibrary("drmframework_jni");
  }
  
  public DrmManagerClient(Context paramContext) {
    this.mContext = paramContext;
    createEventThreads();
    this.mUniqueId = _initialize();
    this.mCloseGuard.open("release");
  }
  
  private native DrmInfo _acquireDrmInfo(int paramInt, DrmInfoRequest paramDrmInfoRequest);
  
  private native boolean _canHandle(int paramInt, String paramString1, String paramString2);
  
  private native int _checkRightsStatus(int paramInt1, String paramString, int paramInt2);
  
  private native DrmConvertedStatus _closeConvertSession(int paramInt1, int paramInt2);
  
  private native DrmConvertedStatus _convertData(int paramInt1, int paramInt2, byte[] paramArrayOfbyte);
  
  private native DrmSupportInfo[] _getAllSupportInfo(int paramInt);
  
  private native ContentValues _getConstraints(int paramInt1, String paramString, int paramInt2);
  
  private native int _getDrmObjectType(int paramInt, String paramString1, String paramString2);
  
  private native ContentValues _getMetadata(int paramInt, String paramString);
  
  private native String _getOriginalMimeType(int paramInt, String paramString, FileDescriptor paramFileDescriptor);
  
  private native int _initialize();
  
  private native void _installDrmEngine(int paramInt, String paramString);
  
  private native int _openConvertSession(int paramInt, String paramString);
  
  private native DrmInfoStatus _processDrmInfo(int paramInt, DrmInfo paramDrmInfo);
  
  private native void _release(int paramInt);
  
  private native int _removeAllRights(int paramInt);
  
  private native int _removeRights(int paramInt, String paramString);
  
  private native int _saveRights(int paramInt, DrmRights paramDrmRights, String paramString1, String paramString2);
  
  private native void _setListeners(int paramInt, Object paramObject);
  
  private String convertUriToPath(Uri paramUri) {
    Cursor cursor;
    String str = null;
    if (paramUri != null) {
      str = paramUri.getScheme();
      if (str == null || str.equals("") || str.equals("file"))
        return paramUri.getPath(); 
      if (str.equals("http") || str.equals("https"))
        return paramUri.toString(); 
      if (str.equals("content")) {
        Cursor cursor1 = null;
        str = null;
        try {
          Cursor cursor2 = this.mContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
          if (cursor2 != null) {
            Cursor cursor3 = cursor2;
            cursor1 = cursor2;
            if (cursor2.getCount() != 0) {
              cursor3 = cursor2;
              cursor1 = cursor2;
              if (cursor2.moveToFirst()) {
                cursor3 = cursor2;
                cursor1 = cursor2;
                String str2 = cursor2.getString(cursor2.getColumnIndexOrThrow("_data"));
                String str1 = str2;
                if (cursor2 != null)
                  cursor2.close(); 
                return str1;
              } 
            } 
          } 
          cursor = cursor2;
          cursor1 = cursor2;
          IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
          cursor = cursor2;
          cursor1 = cursor2;
          this("Given Uri could not be found in media store");
          cursor = cursor2;
          cursor1 = cursor2;
          throw illegalArgumentException;
        } catch (SQLiteException sQLiteException) {
          cursor = cursor1;
          IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
          cursor = cursor1;
          this("Given Uri is not formatted in a way so that it can be found in media store.");
          cursor = cursor1;
          throw illegalArgumentException;
        } finally {}
        if (cursor != null)
          cursor.close(); 
        throw paramUri;
      } 
      throw new IllegalArgumentException("Given Uri scheme is not supported");
    } 
    return (String)cursor;
  }
  
  private void createEventThreads() {
    if (this.mEventHandler == null && this.mInfoHandler == null) {
      HandlerThread handlerThread = new HandlerThread("DrmManagerClient.InfoHandler");
      this.mInfoThread = handlerThread;
      handlerThread.start();
      this.mInfoHandler = new InfoHandler(this.mInfoThread.getLooper());
      handlerThread = new HandlerThread("DrmManagerClient.EventHandler");
      this.mEventThread = handlerThread;
      handlerThread.start();
      this.mEventHandler = new EventHandler(this.mEventThread.getLooper());
    } 
  }
  
  private void createListeners() {
    _setListeners(this.mUniqueId, new WeakReference<>(this));
  }
  
  private int getErrorType(int paramInt) {
    byte b = -1;
    if (paramInt != 1 && paramInt != 2 && paramInt != 3) {
      paramInt = b;
    } else {
      paramInt = 2006;
    } 
    return paramInt;
  }
  
  private int getEventType(int paramInt) {
    byte b = -1;
    if (paramInt != 1 && paramInt != 2 && paramInt != 3) {
      paramInt = b;
    } else {
      paramInt = 1002;
    } 
    return paramInt;
  }
  
  public static void notify(Object paramObject, int paramInt1, int paramInt2, String paramString) {
    paramObject = ((WeakReference<DrmManagerClient>)paramObject).get();
    if (paramObject != null) {
      InfoHandler infoHandler = ((DrmManagerClient)paramObject).mInfoHandler;
      if (infoHandler != null) {
        Message message = infoHandler.obtainMessage(1, paramInt1, paramInt2, paramString);
        ((DrmManagerClient)paramObject).mInfoHandler.sendMessage(message);
      } 
    } 
  }
  
  public DrmInfo acquireDrmInfo(DrmInfoRequest paramDrmInfoRequest) {
    if (paramDrmInfoRequest != null && paramDrmInfoRequest.isValid())
      return _acquireDrmInfo(this.mUniqueId, paramDrmInfoRequest); 
    throw new IllegalArgumentException("Given drmInfoRequest is invalid/null");
  }
  
  public int acquireRights(DrmInfoRequest paramDrmInfoRequest) {
    DrmInfo drmInfo = acquireDrmInfo(paramDrmInfoRequest);
    return (drmInfo == null) ? -2000 : processDrmInfo(drmInfo);
  }
  
  public boolean canHandle(Uri paramUri, String paramString) {
    if ((paramUri != null && Uri.EMPTY != paramUri) || (paramString != null && !paramString.equals("")))
      return canHandle(convertUriToPath(paramUri), paramString); 
    throw new IllegalArgumentException("Uri or the mimetype should be non null");
  }
  
  public boolean canHandle(String paramString1, String paramString2) {
    if ((paramString1 != null && !paramString1.equals("")) || (paramString2 != null && !paramString2.equals("")))
      return _canHandle(this.mUniqueId, paramString1, paramString2); 
    throw new IllegalArgumentException("Path or the mimetype should be non null");
  }
  
  public int checkRightsStatus(Uri paramUri) {
    if (paramUri != null && Uri.EMPTY != paramUri)
      return checkRightsStatus(convertUriToPath(paramUri)); 
    throw new IllegalArgumentException("Given uri is not valid");
  }
  
  public int checkRightsStatus(Uri paramUri, int paramInt) {
    if (paramUri != null && Uri.EMPTY != paramUri)
      return checkRightsStatus(convertUriToPath(paramUri), paramInt); 
    throw new IllegalArgumentException("Given uri is not valid");
  }
  
  public int checkRightsStatus(String paramString) {
    return checkRightsStatus(paramString, 0);
  }
  
  public int checkRightsStatus(String paramString, int paramInt) {
    if (paramString != null && !paramString.equals("") && DrmStore.Action.isValid(paramInt))
      return _checkRightsStatus(this.mUniqueId, paramString, paramInt); 
    throw new IllegalArgumentException("Given path or action is not valid");
  }
  
  public void close() {
    this.mCloseGuard.close();
    if (this.mClosed.compareAndSet(false, true)) {
      if (this.mEventHandler != null) {
        this.mEventThread.quit();
        this.mEventThread = null;
      } 
      if (this.mInfoHandler != null) {
        this.mInfoThread.quit();
        this.mInfoThread = null;
      } 
      this.mEventHandler = null;
      this.mInfoHandler = null;
      this.mOnEventListener = null;
      this.mOnInfoListener = null;
      this.mOnErrorListener = null;
      _release(this.mUniqueId);
    } 
  }
  
  public DrmConvertedStatus closeConvertSession(int paramInt) {
    return _closeConvertSession(this.mUniqueId, paramInt);
  }
  
  public DrmConvertedStatus convertData(int paramInt, byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte != null && paramArrayOfbyte.length > 0)
      return _convertData(this.mUniqueId, paramInt, paramArrayOfbyte); 
    throw new IllegalArgumentException("Given inputData should be non null");
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mCloseGuard != null)
        this.mCloseGuard.warnIfOpen(); 
      close();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public String[] getAvailableDrmEngines() {
    DrmSupportInfo[] arrayOfDrmSupportInfo = _getAllSupportInfo(this.mUniqueId);
    ArrayList<String> arrayList = new ArrayList();
    for (byte b = 0; b < arrayOfDrmSupportInfo.length; b++)
      arrayList.add(arrayOfDrmSupportInfo[b].getDescriprition()); 
    return arrayList.<String>toArray(new String[arrayList.size()]);
  }
  
  public Collection<DrmSupportInfo> getAvailableDrmSupportInfo() {
    return Arrays.asList(_getAllSupportInfo(this.mUniqueId));
  }
  
  public ContentValues getConstraints(Uri paramUri, int paramInt) {
    if (paramUri != null && Uri.EMPTY != paramUri)
      return getConstraints(convertUriToPath(paramUri), paramInt); 
    throw new IllegalArgumentException("Uri should be non null");
  }
  
  public ContentValues getConstraints(String paramString, int paramInt) {
    if (paramString != null && !paramString.equals("") && DrmStore.Action.isValid(paramInt))
      return _getConstraints(this.mUniqueId, paramString, paramInt); 
    throw new IllegalArgumentException("Given usage or path is invalid/null");
  }
  
  public int getDrmObjectType(Uri paramUri, String paramString) {
    if ((paramUri != null && Uri.EMPTY != paramUri) || (paramString != null && !paramString.equals(""))) {
      String str1;
      String str2 = "";
      try {
        str1 = convertUriToPath(paramUri);
      } catch (Exception exception) {
        Log.w("DrmManagerClient", "Given Uri could not be found in media store");
        str1 = str2;
      } 
      return getDrmObjectType(str1, paramString);
    } 
    throw new IllegalArgumentException("Uri or the mimetype should be non null");
  }
  
  public int getDrmObjectType(String paramString1, String paramString2) {
    if ((paramString1 != null && !paramString1.equals("")) || (paramString2 != null && !paramString2.equals("")))
      return _getDrmObjectType(this.mUniqueId, paramString1, paramString2); 
    throw new IllegalArgumentException("Path or the mimetype should be non null");
  }
  
  public ContentValues getMetadata(Uri paramUri) {
    if (paramUri != null && Uri.EMPTY != paramUri)
      return getMetadata(convertUriToPath(paramUri)); 
    throw new IllegalArgumentException("Uri should be non null");
  }
  
  public ContentValues getMetadata(String paramString) {
    if (paramString != null && !paramString.equals(""))
      return _getMetadata(this.mUniqueId, paramString); 
    throw new IllegalArgumentException("Given path is invalid/null");
  }
  
  public String getOriginalMimeType(Uri paramUri) {
    if (paramUri != null && Uri.EMPTY != paramUri)
      return getOriginalMimeType(convertUriToPath(paramUri)); 
    throw new IllegalArgumentException("Given uri is not valid");
  }
  
  public String getOriginalMimeType(String paramString) {
    if (paramString != null && !paramString.equals("")) {
      IOException iOException2;
      FileInputStream fileInputStream1 = null;
      FileInputStream fileInputStream2 = null;
      IOException iOException1 = null;
      FileInputStream fileInputStream3 = null;
      FileInputStream fileInputStream4 = null;
      FileInputStream fileInputStream5 = null;
      FileDescriptor fileDescriptor = null;
      FileInputStream fileInputStream6 = fileInputStream3;
      FileInputStream fileInputStream7 = fileInputStream4;
      try {
        File file = new File();
        fileInputStream6 = fileInputStream3;
        fileInputStream7 = fileInputStream4;
        this(paramString);
        fileInputStream6 = fileInputStream3;
        fileInputStream7 = fileInputStream4;
        if (file.exists()) {
          fileInputStream6 = fileInputStream3;
          fileInputStream7 = fileInputStream4;
          fileInputStream5 = new FileInputStream();
          fileInputStream6 = fileInputStream3;
          fileInputStream7 = fileInputStream4;
          this(file);
          fileInputStream6 = fileInputStream5;
          fileInputStream7 = fileInputStream5;
          fileDescriptor = fileInputStream5.getFD();
        } 
        fileInputStream6 = fileInputStream5;
        fileInputStream7 = fileInputStream5;
        paramString = _getOriginalMimeType(this.mUniqueId, paramString, fileDescriptor);
        String str = paramString;
      } catch (IOException iOException) {
        fileInputStream6 = fileInputStream2;
        if (fileInputStream7 != null) {
          fileInputStream6 = fileInputStream1;
          fileInputStream7.close();
          iOException = iOException1;
        } else {
          return (String)fileInputStream6;
        } 
      } finally {
        if (iOException2 != null)
          try {
            iOException2.close();
          } catch (IOException iOException) {} 
      } 
      return (String)iOException2;
    } 
    throw new IllegalArgumentException("Given path should be non null");
  }
  
  public void installDrmEngine(String paramString) {
    if (paramString != null && !paramString.equals("")) {
      _installDrmEngine(this.mUniqueId, paramString);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Given engineFilePath: ");
    stringBuilder.append(paramString);
    stringBuilder.append("is not valid");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public int openConvertSession(String paramString) {
    if (paramString != null && !paramString.equals(""))
      return _openConvertSession(this.mUniqueId, paramString); 
    throw new IllegalArgumentException("Path or the mimeType should be non null");
  }
  
  public int processDrmInfo(DrmInfo paramDrmInfo) {
    if (paramDrmInfo != null && paramDrmInfo.isValid()) {
      short s = -2000;
      EventHandler eventHandler = this.mEventHandler;
      if (eventHandler != null) {
        Message message = eventHandler.obtainMessage(1002, paramDrmInfo);
        if (this.mEventHandler.sendMessage(message)) {
          s = 0;
        } else {
          s = -2000;
        } 
      } 
      return s;
    } 
    throw new IllegalArgumentException("Given drmInfo is invalid/null");
  }
  
  @Deprecated
  public void release() {
    close();
  }
  
  public int removeAllRights() {
    short s = -2000;
    EventHandler eventHandler = this.mEventHandler;
    if (eventHandler != null) {
      Message message = eventHandler.obtainMessage(1001);
      if (this.mEventHandler.sendMessage(message)) {
        s = 0;
      } else {
        s = -2000;
      } 
    } 
    return s;
  }
  
  public int removeRights(Uri paramUri) {
    if (paramUri != null && Uri.EMPTY != paramUri)
      return removeRights(convertUriToPath(paramUri)); 
    throw new IllegalArgumentException("Given uri is not valid");
  }
  
  public int removeRights(String paramString) {
    if (paramString != null && !paramString.equals(""))
      return _removeRights(this.mUniqueId, paramString); 
    throw new IllegalArgumentException("Given path should be non null");
  }
  
  public int saveRights(DrmRights paramDrmRights, String paramString1, String paramString2) throws IOException {
    if (paramDrmRights != null && paramDrmRights.isValid()) {
      if (paramString1 != null && !paramString1.equals(""))
        DrmUtils.writeToFile(paramString1, paramDrmRights.getData()); 
      return _saveRights(this.mUniqueId, paramDrmRights, paramString1, paramString2);
    } 
    throw new IllegalArgumentException("Given drmRights or contentPath is not valid");
  }
  
  public void setOnErrorListener(OnErrorListener paramOnErrorListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield mOnErrorListener : Landroid/drm/DrmManagerClient$OnErrorListener;
    //   7: aload_1
    //   8: ifnull -> 15
    //   11: aload_0
    //   12: invokespecial createListeners : ()V
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	18	finally
    //   11	15	18	finally
  }
  
  public void setOnEventListener(OnEventListener paramOnEventListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield mOnEventListener : Landroid/drm/DrmManagerClient$OnEventListener;
    //   7: aload_1
    //   8: ifnull -> 15
    //   11: aload_0
    //   12: invokespecial createListeners : ()V
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	18	finally
    //   11	15	18	finally
  }
  
  public void setOnInfoListener(OnInfoListener paramOnInfoListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield mOnInfoListener : Landroid/drm/DrmManagerClient$OnInfoListener;
    //   7: aload_1
    //   8: ifnull -> 15
    //   11: aload_0
    //   12: invokespecial createListeners : ()V
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	18	finally
    //   11	15	18	finally
  }
  
  private class EventHandler extends Handler {
    public EventHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      DrmEvent drmEvent1;
      DrmErrorEvent drmErrorEvent;
      DrmEvent drmEvent2 = null;
      StringBuilder stringBuilder = null;
      HashMap<Object, Object> hashMap = new HashMap<>();
      int i = param1Message.what;
      if (i != 1001) {
        if (i != 1002) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("Unknown message type ");
          stringBuilder.append(param1Message.what);
          Log.e("DrmManagerClient", stringBuilder.toString());
          return;
        } 
        DrmInfo drmInfo = (DrmInfo)param1Message.obj;
        DrmManagerClient drmManagerClient = DrmManagerClient.this;
        DrmInfoStatus drmInfoStatus = drmManagerClient._processDrmInfo(drmManagerClient.mUniqueId, drmInfo);
        hashMap.put("drm_info_status_object", drmInfoStatus);
        hashMap.put("drm_info_object", drmInfo);
        if (drmInfoStatus != null && 1 == drmInfoStatus.statusCode) {
          drmEvent1 = new DrmEvent(DrmManagerClient.this.mUniqueId, DrmManagerClient.this.getEventType(drmInfoStatus.infoType), null, (HashMap)hashMap);
        } else {
          if (drmInfoStatus != null) {
            i = drmInfoStatus.infoType;
          } else {
            i = drmEvent1.getInfoType();
          } 
          drmErrorEvent = new DrmErrorEvent(DrmManagerClient.this.mUniqueId, DrmManagerClient.this.getErrorType(i), null, (HashMap)hashMap);
          drmEvent1 = drmEvent2;
        } 
      } else {
        DrmManagerClient drmManagerClient = DrmManagerClient.this;
        if (drmManagerClient._removeAllRights(drmManagerClient.mUniqueId) == 0) {
          drmEvent1 = new DrmEvent(DrmManagerClient.this.mUniqueId, 1001, null);
        } else {
          drmErrorEvent = new DrmErrorEvent(DrmManagerClient.this.mUniqueId, 2007, null);
          drmEvent1 = drmEvent2;
        } 
      } 
      if (DrmManagerClient.this.mOnEventListener != null && drmEvent1 != null)
        DrmManagerClient.this.mOnEventListener.onEvent(DrmManagerClient.this, drmEvent1); 
      if (DrmManagerClient.this.mOnErrorListener != null && drmErrorEvent != null)
        DrmManagerClient.this.mOnErrorListener.onError(DrmManagerClient.this, drmErrorEvent); 
    }
  }
  
  private class InfoHandler extends Handler {
    public static final int INFO_EVENT_TYPE = 1;
    
    public InfoHandler(Looper param1Looper) {
      super(param1Looper);
    }
    
    public void handleMessage(Message param1Message) {
      DrmInfoEvent drmInfoEvent;
      DrmErrorEvent drmErrorEvent;
      String str2 = null;
      StringBuilder stringBuilder = null;
      if (param1Message.what != 1) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown message type ");
        stringBuilder.append(param1Message.what);
        Log.e("DrmManagerClient", stringBuilder.toString());
        return;
      } 
      int i = param1Message.arg1;
      int j = param1Message.arg2;
      String str1 = param1Message.obj.toString();
      switch (j) {
        default:
          drmErrorEvent = new DrmErrorEvent(i, j, str1);
          str1 = str2;
          break;
        case 2:
          try {
            DrmUtils.removeFile(str1);
          } catch (IOException iOException) {
            iOException.printStackTrace();
          } 
          drmInfoEvent = new DrmInfoEvent(i, j, str1);
          break;
        case 1:
        case 3:
        case 4:
        case 5:
        case 6:
          drmInfoEvent = new DrmInfoEvent(i, j, (String)drmInfoEvent);
          break;
      } 
      if (DrmManagerClient.this.mOnInfoListener != null && drmInfoEvent != null)
        DrmManagerClient.this.mOnInfoListener.onInfo(DrmManagerClient.this, drmInfoEvent); 
      if (DrmManagerClient.this.mOnErrorListener != null && drmErrorEvent != null)
        DrmManagerClient.this.mOnErrorListener.onError(DrmManagerClient.this, drmErrorEvent); 
    }
  }
  
  public static interface OnErrorListener {
    void onError(DrmManagerClient param1DrmManagerClient, DrmErrorEvent param1DrmErrorEvent);
  }
  
  public static interface OnEventListener {
    void onEvent(DrmManagerClient param1DrmManagerClient, DrmEvent param1DrmEvent);
  }
  
  public static interface OnInfoListener {
    void onInfo(DrmManagerClient param1DrmManagerClient, DrmInfoEvent param1DrmInfoEvent);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/drm/DrmManagerClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */