package android.app;

import android.annotation.SystemApi;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.provider.Downloads;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.Pair;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DownloadManager {
  public static final String ACTION_DOWNLOAD_COMPLETE = "android.intent.action.DOWNLOAD_COMPLETE";
  
  @SystemApi
  public static final String ACTION_DOWNLOAD_COMPLETED = "android.intent.action.DOWNLOAD_COMPLETED";
  
  public static final String ACTION_NOTIFICATION_CLICKED = "android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED";
  
  public static final String ACTION_VIEW_DOWNLOADS = "android.intent.action.VIEW_DOWNLOADS";
  
  public static final String COLUMN_ALLOW_WRITE = "allow_write";
  
  public static final String COLUMN_BYTES_DOWNLOADED_SO_FAR = "bytes_so_far";
  
  public static final String COLUMN_DESCRIPTION = "description";
  
  public static final String COLUMN_DESTINATION = "destination";
  
  public static final String COLUMN_FILE_NAME_HINT = "hint";
  
  public static final String COLUMN_ID = "_id";
  
  public static final String COLUMN_LAST_MODIFIED_TIMESTAMP = "last_modified_timestamp";
  
  @Deprecated
  public static final String COLUMN_LOCAL_FILENAME = "local_filename";
  
  public static final String COLUMN_LOCAL_URI = "local_uri";
  
  public static final String COLUMN_MEDIAPROVIDER_URI = "mediaprovider_uri";
  
  public static final String COLUMN_MEDIASTORE_URI = "mediastore_uri";
  
  public static final String COLUMN_MEDIA_TYPE = "media_type";
  
  public static final String COLUMN_REASON = "reason";
  
  public static final String COLUMN_STATUS = "status";
  
  public static final String COLUMN_TITLE = "title";
  
  public static final String COLUMN_TOTAL_SIZE_BYTES = "total_size";
  
  public static final String COLUMN_URI = "uri";
  
  public static final int ERROR_BLOCKED = 1010;
  
  public static final int ERROR_CANNOT_RESUME = 1008;
  
  public static final int ERROR_DEVICE_NOT_FOUND = 1007;
  
  public static final int ERROR_FILE_ALREADY_EXISTS = 1009;
  
  public static final int ERROR_FILE_ERROR = 1001;
  
  public static final int ERROR_HTTP_DATA_ERROR = 1004;
  
  public static final int ERROR_INSUFFICIENT_SPACE = 1006;
  
  public static final int ERROR_TOO_MANY_REDIRECTS = 1005;
  
  public static final int ERROR_UNHANDLED_HTTP_CODE = 1002;
  
  public static final int ERROR_UNKNOWN = 1000;
  
  public static final String EXTRA_DOWNLOAD_ID = "extra_download_id";
  
  public static final String EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS = "extra_click_download_ids";
  
  public static final String INTENT_EXTRAS_SORT_BY_SIZE = "android.app.DownloadManager.extra_sortBySize";
  
  private static final String NON_DOWNLOADMANAGER_DOWNLOAD = "non-dwnldmngr-download-dont-retry2download";
  
  public static final int PAUSED_QUEUED_FOR_WIFI = 3;
  
  public static final int PAUSED_UNKNOWN = 4;
  
  public static final int PAUSED_WAITING_FOR_NETWORK = 2;
  
  public static final int PAUSED_WAITING_TO_RETRY = 1;
  
  public static final int STATUS_FAILED = 16;
  
  public static final int STATUS_PAUSED = 4;
  
  public static final int STATUS_PENDING = 1;
  
  public static final int STATUS_RUNNING = 2;
  
  public static final int STATUS_SUCCESSFUL = 8;
  
  public static final String[] UNDERLYING_COLUMNS = new String[] { 
      "_id", "local_filename", "mediaprovider_uri", "destination", "title", "description", "uri", "status", "hint", "media_type", 
      "total_size", "last_modified_timestamp", "bytes_so_far", "allow_write", "local_uri", "reason" };
  
  private boolean mAccessFilename;
  
  private Uri mBaseUri;
  
  private final String mPackageName;
  
  private final ContentResolver mResolver;
  
  public DownloadManager(Context paramContext) {
    boolean bool;
    this.mBaseUri = Downloads.Impl.CONTENT_URI;
    this.mResolver = paramContext.getContentResolver();
    this.mPackageName = paramContext.getPackageName();
    if ((paramContext.getApplicationInfo()).targetSdkVersion < 24) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mAccessFilename = bool;
  }
  
  private static String extractDisplayName(String paramString) {
    if (paramString == null)
      return null; 
    if (paramString.indexOf('/') == -1)
      return paramString; 
    String str = paramString;
    if (paramString.endsWith("/"))
      str = paramString.substring(0, paramString.length() - 1); 
    return str.substring(str.lastIndexOf('/') + 1);
  }
  
  private static String extractFileExtension(String paramString) {
    if (paramString == null)
      return null; 
    paramString = extractDisplayName(paramString);
    int i = paramString.lastIndexOf('.');
    return (i == -1) ? null : paramString.substring(i + 1);
  }
  
  public static long getActiveNetworkWarningBytes(Context paramContext) {
    return -1L;
  }
  
  public static Long getMaxBytesOverMobile(Context paramContext) {
    try {
      long l = Settings.Global.getLong(paramContext.getContentResolver(), "download_manager_max_bytes_over_mobile");
      return Long.valueOf(l);
    } catch (android.provider.Settings.SettingNotFoundException settingNotFoundException) {
      return null;
    } 
  }
  
  public static Long getRecommendedMaxBytesOverMobile(Context paramContext) {
    try {
      long l = Settings.Global.getLong(paramContext.getContentResolver(), "download_manager_recommended_max_bytes_over_mobile");
      return Long.valueOf(l);
    } catch (android.provider.Settings.SettingNotFoundException settingNotFoundException) {
      return null;
    } 
  }
  
  static String[] getWhereArgsForIds(long[] paramArrayOflong) {
    return getWhereArgsForIds(paramArrayOflong, new String[paramArrayOflong.length]);
  }
  
  static String[] getWhereArgsForIds(long[] paramArrayOflong, String[] paramArrayOfString) {
    for (byte b = 0; b < paramArrayOflong.length; b++)
      paramArrayOfString[b] = Long.toString(paramArrayOflong[b]); 
    return paramArrayOfString;
  }
  
  static String getWhereClauseForIds(long[] paramArrayOflong) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("(");
    for (byte b = 0; b < paramArrayOflong.length; b++) {
      if (b > 0)
        stringBuilder.append("OR "); 
      stringBuilder.append("_id");
      stringBuilder.append(" = ? ");
    } 
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public static boolean isActiveNetworkExpensive(Context paramContext) {
    return false;
  }
  
  private static String resolveMimeType(File paramFile) {
    String str = extractFileExtension(paramFile.getPath());
    if (str == null)
      return "application/octet-stream"; 
    str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.toLowerCase(Locale.ROOT));
    return (str == null) ? "application/octet-stream" : str;
  }
  
  private static void validateArgumentIsNonEmpty(String paramString1, String paramString2) {
    if (!TextUtils.isEmpty(paramString2))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString1);
    stringBuilder.append(" can't be null");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  @Deprecated
  public long addCompletedDownload(String paramString1, String paramString2, boolean paramBoolean1, String paramString3, String paramString4, long paramLong, boolean paramBoolean2) {
    return addCompletedDownload(paramString1, paramString2, paramBoolean1, paramString3, paramString4, paramLong, paramBoolean2, false, null, null);
  }
  
  @Deprecated
  public long addCompletedDownload(String paramString1, String paramString2, boolean paramBoolean1, String paramString3, String paramString4, long paramLong, boolean paramBoolean2, Uri paramUri1, Uri paramUri2) {
    return addCompletedDownload(paramString1, paramString2, paramBoolean1, paramString3, paramString4, paramLong, paramBoolean2, false, paramUri1, paramUri2);
  }
  
  @Deprecated
  public long addCompletedDownload(String paramString1, String paramString2, boolean paramBoolean1, String paramString3, String paramString4, long paramLong, boolean paramBoolean2, boolean paramBoolean3) {
    return addCompletedDownload(paramString1, paramString2, paramBoolean1, paramString3, paramString4, paramLong, paramBoolean2, paramBoolean3, null, null);
  }
  
  @Deprecated
  public long addCompletedDownload(String paramString1, String paramString2, boolean paramBoolean1, String paramString3, String paramString4, long paramLong, boolean paramBoolean2, boolean paramBoolean3, Uri paramUri1, Uri paramUri2) {
    validateArgumentIsNonEmpty("title", paramString1);
    validateArgumentIsNonEmpty("description", paramString2);
    validateArgumentIsNonEmpty("path", paramString4);
    validateArgumentIsNonEmpty("mimeType", paramString3);
    if (paramLong >= 0L) {
      Request request;
      byte b2;
      if (paramUri1 != null) {
        request = new Request(paramUri1);
      } else {
        request = new Request("non-dwnldmngr-download-dont-retry2download");
      } 
      request.setTitle(paramString1).setDescription(paramString2).setMimeType(paramString3);
      if (paramUri2 != null)
        request.addRequestHeader("Referer", paramUri2.toString()); 
      ContentValues contentValues = request.toContentValues(null);
      contentValues.put("destination", Integer.valueOf(6));
      contentValues.put("_data", paramString4);
      contentValues.put("mimetype", resolveMimeType(new File(paramString4)));
      contentValues.put("status", Integer.valueOf(200));
      contentValues.put("total_bytes", Long.valueOf(paramLong));
      byte b1 = 2;
      if (paramBoolean1) {
        b2 = 0;
      } else {
        b2 = 2;
      } 
      contentValues.put("scanned", Integer.valueOf(b2));
      if (paramBoolean2) {
        b2 = 3;
      } else {
        b2 = b1;
      } 
      contentValues.put("visibility", Integer.valueOf(b2));
      contentValues.put("allow_write", Integer.valueOf(paramBoolean3));
      Uri uri = this.mResolver.insert(Downloads.Impl.CONTENT_URI, contentValues);
      return (uri == null) ? -1L : Long.parseLong(uri.getLastPathSegment());
    } 
    throw new IllegalArgumentException(" invalid value for param: totalBytes");
  }
  
  public long enqueue(Request paramRequest) {
    ContentValues contentValues = paramRequest.toContentValues(this.mPackageName);
    return Long.parseLong(this.mResolver.insert(Downloads.Impl.CONTENT_URI, contentValues).getLastPathSegment());
  }
  
  public void forceDownload(long... paramVarArgs) {
    ContentValues contentValues = new ContentValues();
    contentValues.put("status", Integer.valueOf(190));
    contentValues.put("control", Integer.valueOf(0));
    contentValues.put("bypass_recommended_size_limit", Integer.valueOf(1));
    this.mResolver.update(this.mBaseUri, contentValues, getWhereClauseForIds(paramVarArgs), getWhereArgsForIds(paramVarArgs));
  }
  
  public Uri getDownloadUri(long paramLong) {
    return ContentUris.withAppendedId(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, paramLong);
  }
  
  public String getMimeTypeForDownloadedFile(long paramLong) {
    null = (new Query()).setFilterById(new long[] { paramLong });
    Cursor cursor = null;
    try {
      Cursor cursor1 = query(null);
      if (cursor1 == null)
        return null; 
      cursor = cursor1;
      if (cursor1.moveToFirst()) {
        cursor = cursor1;
        return cursor1.getString(cursor1.getColumnIndexOrThrow("media_type"));
      } 
      return null;
    } finally {
      if (cursor != null)
        cursor.close(); 
    } 
  }
  
  public Uri getUriForDownloadedFile(long paramLong) {
    null = (new Query()).setFilterById(new long[] { paramLong });
    Cursor cursor = null;
    try {
      Cursor cursor1 = query(null);
      if (cursor1 == null)
        return null; 
      cursor = cursor1;
      if (cursor1.moveToFirst()) {
        cursor = cursor1;
        if (8 == cursor1.getInt(cursor1.getColumnIndexOrThrow("status"))) {
          cursor = cursor1;
          return ContentUris.withAppendedId(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, paramLong);
        } 
      } 
      return null;
    } finally {
      if (cursor != null)
        cursor.close(); 
    } 
  }
  
  public int markRowDeleted(long... paramVarArgs) {
    if (paramVarArgs != null && paramVarArgs.length != 0)
      return this.mResolver.delete(this.mBaseUri, getWhereClauseForIds(paramVarArgs), getWhereArgsForIds(paramVarArgs)); 
    throw new IllegalArgumentException("input param 'ids' can't be null");
  }
  
  @SystemApi
  public void onMediaStoreDownloadsDeleted(LongSparseArray<String> paramLongSparseArray) {
    try {
      ContentProviderClient contentProviderClient = this.mResolver.acquireUnstableContentProviderClient(this.mBaseUri);
      try {
        Bundle bundle = new Bundle();
        this();
        long[] arrayOfLong = new long[paramLongSparseArray.size()];
        String[] arrayOfString = new String[paramLongSparseArray.size()];
        for (int i = paramLongSparseArray.size() - 1; i >= 0; i--) {
          arrayOfLong[i] = paramLongSparseArray.keyAt(i);
          arrayOfString[i] = (String)paramLongSparseArray.valueAt(i);
        } 
        bundle.putLongArray("ids", arrayOfLong);
        bundle.putStringArray("mime_types", arrayOfString);
        contentProviderClient.call("mediastore_downloads_deleted", null, bundle);
      } finally {
        if (contentProviderClient != null)
          try {
            contentProviderClient.close();
          } finally {
            contentProviderClient = null;
          }  
      } 
    } catch (RemoteException remoteException) {}
  }
  
  public ParcelFileDescriptor openDownloadedFile(long paramLong) throws FileNotFoundException {
    return this.mResolver.openFileDescriptor(getDownloadUri(paramLong), "r");
  }
  
  public Cursor query(Query paramQuery) {
    return query(paramQuery, UNDERLYING_COLUMNS);
  }
  
  public Cursor query(Query paramQuery, String[] paramArrayOfString) {
    Cursor cursor = paramQuery.runQuery(this.mResolver, paramArrayOfString, this.mBaseUri);
    return (Cursor)((cursor == null) ? null : new CursorTranslator(cursor, this.mBaseUri, this.mAccessFilename));
  }
  
  public int remove(long... paramVarArgs) {
    return markRowDeleted(paramVarArgs);
  }
  
  public boolean rename(Context paramContext, long paramLong, String paramString) {
    IllegalStateException illegalStateException;
    if (FileUtils.isValidFatFilename(paramString)) {
      Query query = new Query();
      boolean bool = true;
      Cursor cursor = query(query.setFilterById(new long[] { paramLong }));
      if (cursor != null) {
        long[] arrayOfLong;
        try {
          if (cursor.moveToFirst()) {
            if (cursor.getInt(cursor.getColumnIndexOrThrow("status")) == 8) {
              String str = cursor.getString(cursor.getColumnIndexOrThrow("local_filename"));
              if (str != null) {
                File file = new File();
                this(str);
                boolean bool1 = file.exists();
                if (bool1) {
                  if (cursor != null)
                    cursor.close(); 
                  File file2 = new File(str);
                  File file1 = new File(file2.getParentFile(), paramString);
                  if (!file1.exists()) {
                    ContentValues contentValues;
                    if (file2.renameTo(file1)) {
                      MediaStore.scanFile(this.mResolver, file2);
                      MediaStore.scanFile(this.mResolver, file1);
                      contentValues = new ContentValues();
                      contentValues.put("title", paramString);
                      contentValues.put("_data", file1.toString());
                      contentValues.putNull("mediaprovider_uri");
                      arrayOfLong = new long[1];
                      arrayOfLong[0] = paramLong;
                      return bool;
                    } 
                    StringBuilder stringBuilder7 = new StringBuilder();
                    stringBuilder7.append("Failed to rename file from ");
                    stringBuilder7.append(contentValues);
                    stringBuilder7.append(" to ");
                    stringBuilder7.append(arrayOfLong);
                    throw new IllegalStateException(stringBuilder7.toString());
                  } 
                  StringBuilder stringBuilder6 = new StringBuilder();
                  stringBuilder6.append("File already exists: ");
                  stringBuilder6.append(arrayOfLong);
                  throw new IllegalStateException(stringBuilder6.toString());
                } 
                IllegalStateException illegalStateException4 = new IllegalStateException();
                StringBuilder stringBuilder5 = new StringBuilder();
                this();
                stringBuilder5.append("Downloaded file doesn't exist anymore: ");
                stringBuilder5.append(DatabaseUtils.dumpCurrentRowToString((Cursor)arrayOfLong));
                this(stringBuilder5.toString());
                throw illegalStateException4;
              } 
              IllegalStateException illegalStateException3 = new IllegalStateException();
              StringBuilder stringBuilder4 = new StringBuilder();
              this();
              stringBuilder4.append("Download doesn't have a valid file path: ");
              stringBuilder4.append(DatabaseUtils.dumpCurrentRowToString((Cursor)arrayOfLong));
              this(stringBuilder4.toString());
              throw illegalStateException3;
            } 
            IllegalStateException illegalStateException2 = new IllegalStateException();
            StringBuilder stringBuilder3 = new StringBuilder();
            this();
            stringBuilder3.append("Download is not completed yet: ");
            stringBuilder3.append(DatabaseUtils.dumpCurrentRowToString((Cursor)arrayOfLong));
            this(stringBuilder3.toString());
            throw illegalStateException2;
          } 
          IllegalStateException illegalStateException1 = new IllegalStateException();
          StringBuilder stringBuilder2 = new StringBuilder();
          this();
          stringBuilder2.append("Missing download id=");
          stringBuilder2.append(paramLong);
          this(stringBuilder2.toString());
          throw illegalStateException1;
        } finally {
          if (arrayOfLong != null)
            try {
              arrayOfLong.close();
            } finally {
              arrayOfLong = null;
            }  
        } 
      } 
      illegalStateException = new IllegalStateException();
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      stringBuilder1.append("Missing cursor for download id=");
      stringBuilder1.append(paramLong);
      this(stringBuilder1.toString());
      throw illegalStateException;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append((String)illegalStateException);
    stringBuilder.append(" is not a valid filename");
    throw new SecurityException(stringBuilder.toString());
  }
  
  public void restartDownload(long... paramVarArgs) {
    ContentValues contentValues;
    Cursor cursor = query((new Query()).setFilterById(paramVarArgs));
    try {
      cursor.moveToFirst();
      while (true) {
        StringBuilder stringBuilder;
        boolean bool = cursor.isAfterLast();
        if (!bool) {
          int i = cursor.getInt(cursor.getColumnIndex("status"));
          if (i == 8 || i == 16) {
            cursor.moveToNext();
            continue;
          } 
          IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
          stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Cannot restart incomplete download: ");
          stringBuilder.append(cursor.getLong(cursor.getColumnIndex("_id")));
          this(stringBuilder.toString());
          throw illegalArgumentException;
        } 
        cursor.close();
        contentValues = new ContentValues();
        contentValues.put("current_bytes", Integer.valueOf(0));
        contentValues.put("total_bytes", Integer.valueOf(-1));
        contentValues.putNull("_data");
        contentValues.put("status", Integer.valueOf(190));
        contentValues.put("numfailed", Integer.valueOf(0));
        return;
      } 
    } finally {
      contentValues.close();
    } 
  }
  
  public void setAccessAllDownloads(boolean paramBoolean) {
    if (paramBoolean) {
      this.mBaseUri = Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI;
    } else {
      this.mBaseUri = Downloads.Impl.CONTENT_URI;
    } 
  }
  
  public void setAccessFilename(boolean paramBoolean) {
    this.mAccessFilename = paramBoolean;
  }
  
  private static class CursorTranslator extends CursorWrapper {
    private final boolean mAccessFilename;
    
    private final Uri mBaseUri;
    
    public CursorTranslator(Cursor param1Cursor, Uri param1Uri, boolean param1Boolean) {
      super(param1Cursor);
      this.mBaseUri = param1Uri;
      this.mAccessFilename = param1Boolean;
    }
    
    private long getErrorCode(int param1Int) {
      if ((400 <= param1Int && param1Int < 488) || (500 <= param1Int && param1Int < 600))
        return param1Int; 
      if (param1Int != 198) {
        if (param1Int != 199) {
          if (param1Int != 488) {
            if (param1Int != 489) {
              if (param1Int != 497) {
                switch (param1Int) {
                  default:
                    return 1000L;
                  case 495:
                    return 1004L;
                  case 493:
                  case 494:
                    return 1002L;
                  case 492:
                    break;
                } 
                return 1001L;
              } 
              return 1005L;
            } 
            return 1008L;
          } 
          return 1009L;
        } 
        return 1007L;
      } 
      return 1006L;
    }
    
    private String getLocalUri() {
      long l = getLong(getColumnIndex("destination"));
      if (l == 4L || l == 0L || l == 6L) {
        String str = super.getString(getColumnIndex("local_filename"));
        return (str == null) ? null : Uri.fromFile(new File(str)).toString();
      } 
      l = getLong(getColumnIndex("_id"));
      return ContentUris.withAppendedId(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, l).toString();
    }
    
    private long getPausedReason(int param1Int) {
      switch (param1Int) {
        default:
          return 4L;
        case 196:
          return 3L;
        case 195:
          return 2L;
        case 194:
          break;
      } 
      return 1L;
    }
    
    private long getReason(int param1Int) {
      int i = translateStatus(param1Int);
      return (i != 4) ? ((i != 16) ? 0L : getErrorCode(param1Int)) : getPausedReason(param1Int);
    }
    
    private int translateStatus(int param1Int) {
      if (param1Int != 190) {
        if (param1Int != 200) {
          switch (param1Int) {
            default:
              return 16;
            case 193:
            case 194:
            case 195:
            case 196:
              return 4;
            case 192:
              break;
          } 
          return 2;
        } 
        return 8;
      } 
      return 1;
    }
    
    public int getInt(int param1Int) {
      return (int)getLong(param1Int);
    }
    
    public long getLong(int param1Int) {
      return getColumnName(param1Int).equals("reason") ? getReason(super.getInt(getColumnIndex("status"))) : (getColumnName(param1Int).equals("status") ? translateStatus(super.getInt(getColumnIndex("status"))) : super.getLong(param1Int));
    }
    
    public String getString(int param1Int) {
      // Byte code:
      //   0: aload_0
      //   1: iload_1
      //   2: invokevirtual getColumnName : (I)Ljava/lang/String;
      //   5: astore_2
      //   6: aload_2
      //   7: invokevirtual hashCode : ()I
      //   10: istore_3
      //   11: iload_3
      //   12: ldc -1204869480
      //   14: if_icmpeq -> 40
      //   17: iload_3
      //   18: ldc 22072411
      //   20: if_icmpeq -> 26
      //   23: goto -> 54
      //   26: aload_2
      //   27: ldc 'local_filename'
      //   29: invokevirtual equals : (Ljava/lang/Object;)Z
      //   32: ifeq -> 23
      //   35: iconst_1
      //   36: istore_3
      //   37: goto -> 56
      //   40: aload_2
      //   41: ldc 'local_uri'
      //   43: invokevirtual equals : (Ljava/lang/Object;)Z
      //   46: ifeq -> 23
      //   49: iconst_0
      //   50: istore_3
      //   51: goto -> 56
      //   54: iconst_m1
      //   55: istore_3
      //   56: iload_3
      //   57: ifeq -> 91
      //   60: iload_3
      //   61: iconst_1
      //   62: if_icmpeq -> 68
      //   65: goto -> 75
      //   68: aload_0
      //   69: getfield mAccessFilename : Z
      //   72: ifeq -> 81
      //   75: aload_0
      //   76: iload_1
      //   77: invokespecial getString : (I)Ljava/lang/String;
      //   80: areturn
      //   81: new java/lang/SecurityException
      //   84: dup
      //   85: ldc 'COLUMN_LOCAL_FILENAME is deprecated; use ContentResolver.openFileDescriptor() instead'
      //   87: invokespecial <init> : (Ljava/lang/String;)V
      //   90: athrow
      //   91: aload_0
      //   92: invokespecial getLocalUri : ()Ljava/lang/String;
      //   95: areturn
    }
  }
  
  public static class Query {
    public static final int ORDER_ASCENDING = 1;
    
    public static final int ORDER_DESCENDING = 2;
    
    private String mFilterString = null;
    
    private long[] mIds = null;
    
    private boolean mOnlyIncludeVisibleInDownloadsUi = false;
    
    private String mOrderByColumn = "lastmod";
    
    private int mOrderDirection = 2;
    
    private Integer mStatusFlags = null;
    
    private String joinStrings(String param1String, Iterable<String> param1Iterable) {
      StringBuilder stringBuilder = new StringBuilder();
      boolean bool = true;
      for (String str : param1Iterable) {
        if (!bool)
          stringBuilder.append(param1String); 
        stringBuilder.append(str);
        bool = false;
      } 
      return stringBuilder.toString();
    }
    
    private String statusClause(String param1String, int param1Int) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("status");
      stringBuilder.append(param1String);
      stringBuilder.append("'");
      stringBuilder.append(param1Int);
      stringBuilder.append("'");
      return stringBuilder.toString();
    }
    
    public Query orderBy(String param1String, int param1Int) {
      if (param1Int == 1 || param1Int == 2) {
        if (param1String.equals("last_modified_timestamp")) {
          this.mOrderByColumn = "lastmod";
        } else {
          if (param1String.equals("total_size")) {
            this.mOrderByColumn = "total_bytes";
            this.mOrderDirection = param1Int;
            return this;
          } 
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Cannot order by ");
          stringBuilder1.append(param1String);
          throw new IllegalArgumentException(stringBuilder1.toString());
        } 
        this.mOrderDirection = param1Int;
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid direction: ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    Cursor runQuery(ContentResolver param1ContentResolver, String[] param1ArrayOfString, Uri param1Uri) {
      String str1;
      int i;
      ArrayList<String> arrayList = new ArrayList();
      long[] arrayOfLong = this.mIds;
      if (arrayOfLong == null) {
        i = 0;
      } else {
        i = arrayOfLong.length;
      } 
      if (this.mFilterString != null)
        i++; 
      String[] arrayOfString = new String[i];
      if (i > 0) {
        long[] arrayOfLong1 = this.mIds;
        if (arrayOfLong1 != null) {
          arrayList.add(DownloadManager.getWhereClauseForIds(arrayOfLong1));
          DownloadManager.getWhereArgsForIds(this.mIds, arrayOfString);
        } 
        if (this.mFilterString != null) {
          arrayList.add("title LIKE ?");
          i = arrayOfString.length;
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("%");
          stringBuilder1.append(this.mFilterString);
          stringBuilder1.append("%");
          arrayOfString[i - 1] = stringBuilder1.toString();
        } 
      } 
      if (this.mStatusFlags != null) {
        ArrayList<String> arrayList1 = new ArrayList();
        if ((this.mStatusFlags.intValue() & 0x1) != 0)
          arrayList1.add(statusClause("=", 190)); 
        if ((this.mStatusFlags.intValue() & 0x2) != 0)
          arrayList1.add(statusClause("=", 192)); 
        if ((this.mStatusFlags.intValue() & 0x4) != 0) {
          arrayList1.add(statusClause("=", 193));
          arrayList1.add(statusClause("=", 194));
          arrayList1.add(statusClause("=", 195));
          arrayList1.add(statusClause("=", 196));
        } 
        if ((this.mStatusFlags.intValue() & 0x8) != 0)
          arrayList1.add(statusClause("=", 200)); 
        if ((this.mStatusFlags.intValue() & 0x10) != 0) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("(");
          stringBuilder1.append(statusClause(">=", 400));
          stringBuilder1.append(" AND ");
          stringBuilder1.append(statusClause("<", 600));
          stringBuilder1.append(")");
          arrayList1.add(stringBuilder1.toString());
        } 
        arrayList.add(joinStrings(" OR ", arrayList1));
      } 
      if (this.mOnlyIncludeVisibleInDownloadsUi)
        arrayList.add("is_visible_in_downloads_ui != '0'"); 
      arrayList.add("deleted != '1'");
      String str2 = joinStrings(" AND ", arrayList);
      if (this.mOrderDirection == 1) {
        str1 = "ASC";
      } else {
        str1 = "DESC";
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.mOrderByColumn);
      stringBuilder.append(" ");
      stringBuilder.append(str1);
      return param1ContentResolver.query(param1Uri, param1ArrayOfString, str2, arrayOfString, stringBuilder.toString());
    }
    
    public Query setFilterById(long... param1VarArgs) {
      this.mIds = param1VarArgs;
      return this;
    }
    
    public Query setFilterByStatus(int param1Int) {
      this.mStatusFlags = Integer.valueOf(param1Int);
      return this;
    }
    
    public Query setFilterByString(String param1String) {
      this.mFilterString = param1String;
      return this;
    }
    
    public Query setOnlyIncludeVisibleInDownloadsUi(boolean param1Boolean) {
      this.mOnlyIncludeVisibleInDownloadsUi = param1Boolean;
      return this;
    }
  }
  
  public static class Request {
    @Deprecated
    public static final int NETWORK_BLUETOOTH = 4;
    
    public static final int NETWORK_MOBILE = 1;
    
    public static final int NETWORK_WIFI = 2;
    
    private static final int SCANNABLE_VALUE_NO = 2;
    
    private static final int SCANNABLE_VALUE_YES = 0;
    
    public static final int VISIBILITY_HIDDEN = 2;
    
    public static final int VISIBILITY_VISIBLE = 0;
    
    public static final int VISIBILITY_VISIBLE_NOTIFY_COMPLETED = 1;
    
    public static final int VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION = 3;
    
    private int mAllowedNetworkTypes = -1;
    
    private CharSequence mDescription;
    
    private Uri mDestinationUri;
    
    private int mFlags = 0;
    
    private boolean mIsVisibleInDownloadsUi = true;
    
    private boolean mMeteredAllowed = true;
    
    private String mMimeType;
    
    private int mNotificationVisibility = 0;
    
    private List<Pair<String, String>> mRequestHeaders = new ArrayList<>();
    
    private boolean mRoamingAllowed = true;
    
    private boolean mScannable = false;
    
    private CharSequence mTitle;
    
    private Uri mUri;
    
    public Request(Uri param1Uri) {
      if (param1Uri != null) {
        String str = param1Uri.getScheme();
        if (str != null && (str.equals("http") || str.equals("https"))) {
          this.mUri = param1Uri;
          return;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Can only download HTTP/HTTPS URIs: ");
        stringBuilder.append(param1Uri);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      throw null;
    }
    
    Request(String param1String) {
      this.mUri = Uri.parse(param1String);
    }
    
    private void encodeHttpHeaders(ContentValues param1ContentValues) {
      byte b = 0;
      for (Pair<String, String> pair : this.mRequestHeaders) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((String)pair.first);
        stringBuilder.append(": ");
        stringBuilder.append((String)pair.second);
        String str = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("http_header_");
        stringBuilder.append(b);
        param1ContentValues.put(stringBuilder.toString(), str);
        b++;
      } 
    }
    
    private void putIfNonNull(ContentValues param1ContentValues, String param1String, Object param1Object) {
      if (param1Object != null)
        param1ContentValues.put(param1String, param1Object.toString()); 
    }
    
    private void setDestinationFromBase(File param1File, String param1String) {
      if (param1String != null) {
        this.mDestinationUri = Uri.withAppendedPath(Uri.fromFile(param1File), param1String);
        return;
      } 
      throw new NullPointerException("subPath cannot be null");
    }
    
    public Request addRequestHeader(String param1String1, String param1String2) {
      if (param1String1 != null) {
        if (!param1String1.contains(":")) {
          String str = param1String2;
          if (param1String2 == null)
            str = ""; 
          this.mRequestHeaders.add(Pair.create(param1String1, str));
          return this;
        } 
        throw new IllegalArgumentException("header may not contain ':'");
      } 
      throw new NullPointerException("header cannot be null");
    }
    
    @Deprecated
    public void allowScanningByMediaScanner() {
      this.mScannable = true;
    }
    
    public Request setAllowedNetworkTypes(int param1Int) {
      this.mAllowedNetworkTypes = param1Int;
      return this;
    }
    
    public Request setAllowedOverMetered(boolean param1Boolean) {
      this.mMeteredAllowed = param1Boolean;
      return this;
    }
    
    public Request setAllowedOverRoaming(boolean param1Boolean) {
      this.mRoamingAllowed = param1Boolean;
      return this;
    }
    
    public Request setDescription(CharSequence param1CharSequence) {
      this.mDescription = param1CharSequence;
      return this;
    }
    
    public Request setDestinationInExternalFilesDir(Context param1Context, String param1String1, String param1String2) {
      File file = param1Context.getExternalFilesDir(param1String1);
      if (file != null) {
        if (file.exists()) {
          if (!file.isDirectory()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append(" already exists and is not a directory");
            throw new IllegalStateException(stringBuilder.toString());
          } 
        } else if (!file.mkdirs()) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unable to create directory: ");
          stringBuilder.append(file.getAbsolutePath());
          throw new IllegalStateException(stringBuilder.toString());
        } 
        setDestinationFromBase(file, param1String2);
        return this;
      } 
      throw new IllegalStateException("Failed to get external storage files directory");
    }
    
    public Request setDestinationInExternalPublicDir(String param1String1, String param1String2) {
      File file = Environment.getExternalStoragePublicDirectory(param1String1);
      if (file != null) {
        Application application = AppGlobals.getInitialApplication();
        if ((application.getApplicationInfo()).targetSdkVersion >= 29 || !Environment.isExternalStorageLegacy())
          try {
            ContentProviderClient contentProviderClient = application.getContentResolver().acquireContentProviderClient("downloads");
            try {
              Bundle bundle = new Bundle();
              this();
              bundle.putString("dir_type", param1String1);
              contentProviderClient.call("create_external_public_dir", null, bundle);
              if (contentProviderClient != null)
                contentProviderClient.close(); 
              return this;
            } finally {
              if (contentProviderClient != null)
                try {
                  contentProviderClient.close();
                } finally {
                  param1String2 = null;
                }  
            } 
          } catch (RemoteException remoteException) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to create directory: ");
            stringBuilder.append(file.getAbsolutePath());
            throw new IllegalStateException(stringBuilder.toString());
          }  
        if (file.exists()) {
          if (!file.isDirectory()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append(" already exists and is not a directory");
            throw new IllegalStateException(stringBuilder.toString());
          } 
        } else if (!file.mkdirs()) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unable to create directory: ");
          stringBuilder.append(file.getAbsolutePath());
          throw new IllegalStateException(stringBuilder.toString());
        } 
        setDestinationFromBase(file, param1String2);
        return this;
      } 
      throw new IllegalStateException("Failed to get external storage public directory");
    }
    
    public Request setDestinationUri(Uri param1Uri) {
      this.mDestinationUri = param1Uri;
      return this;
    }
    
    public Request setMimeType(String param1String) {
      this.mMimeType = param1String;
      return this;
    }
    
    public Request setNotificationVisibility(int param1Int) {
      this.mNotificationVisibility = param1Int;
      return this;
    }
    
    public Request setRequiresCharging(boolean param1Boolean) {
      if (param1Boolean) {
        this.mFlags |= 0x1;
      } else {
        this.mFlags &= 0xFFFFFFFE;
      } 
      return this;
    }
    
    public Request setRequiresDeviceIdle(boolean param1Boolean) {
      if (param1Boolean) {
        this.mFlags |= 0x2;
      } else {
        this.mFlags &= 0xFFFFFFFD;
      } 
      return this;
    }
    
    @Deprecated
    public Request setShowRunningNotification(boolean param1Boolean) {
      Request request;
      if (param1Boolean) {
        request = setNotificationVisibility(0);
      } else {
        request = setNotificationVisibility(2);
      } 
      return request;
    }
    
    public Request setTitle(CharSequence param1CharSequence) {
      this.mTitle = param1CharSequence;
      return this;
    }
    
    @Deprecated
    public Request setVisibleInDownloadsUi(boolean param1Boolean) {
      this.mIsVisibleInDownloadsUi = param1Boolean;
      return this;
    }
    
    ContentValues toContentValues(String param1String) {
      ContentValues contentValues = new ContentValues();
      contentValues.put("uri", this.mUri.toString());
      contentValues.put("is_public_api", Boolean.valueOf(true));
      contentValues.put("notificationpackage", param1String);
      Uri uri = this.mDestinationUri;
      byte b = 2;
      if (uri != null) {
        contentValues.put("destination", Integer.valueOf(4));
        contentValues.put("hint", this.mDestinationUri.toString());
      } else {
        contentValues.put("destination", Integer.valueOf(2));
      } 
      if (this.mScannable)
        b = 0; 
      contentValues.put("scanned", Integer.valueOf(b));
      if (!this.mRequestHeaders.isEmpty())
        encodeHttpHeaders(contentValues); 
      putIfNonNull(contentValues, "title", this.mTitle);
      putIfNonNull(contentValues, "description", this.mDescription);
      putIfNonNull(contentValues, "mimetype", this.mMimeType);
      contentValues.put("visibility", Integer.valueOf(this.mNotificationVisibility));
      contentValues.put("allowed_network_types", Integer.valueOf(this.mAllowedNetworkTypes));
      contentValues.put("allow_roaming", Boolean.valueOf(this.mRoamingAllowed));
      contentValues.put("allow_metered", Boolean.valueOf(this.mMeteredAllowed));
      contentValues.put("flags", Integer.valueOf(this.mFlags));
      contentValues.put("is_visible_in_downloads_ui", Boolean.valueOf(this.mIsVisibleInDownloadsUi));
      return contentValues;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/DownloadManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */