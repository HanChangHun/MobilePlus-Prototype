package android.app;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.util.Pair;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Request {
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
  
  public Request(Uri paramUri) {
    if (paramUri != null) {
      String str = paramUri.getScheme();
      if (str != null && (str.equals("http") || str.equals("https"))) {
        this.mUri = paramUri;
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can only download HTTP/HTTPS URIs: ");
      stringBuilder.append(paramUri);
      throw new IllegalArgumentException(stringBuilder.toString());
    } 
    throw null;
  }
  
  Request(String paramString) {
    this.mUri = Uri.parse(paramString);
  }
  
  private void encodeHttpHeaders(ContentValues paramContentValues) {
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
      paramContentValues.put(stringBuilder.toString(), str);
      b++;
    } 
  }
  
  private void putIfNonNull(ContentValues paramContentValues, String paramString, Object paramObject) {
    if (paramObject != null)
      paramContentValues.put(paramString, paramObject.toString()); 
  }
  
  private void setDestinationFromBase(File paramFile, String paramString) {
    if (paramString != null) {
      this.mDestinationUri = Uri.withAppendedPath(Uri.fromFile(paramFile), paramString);
      return;
    } 
    throw new NullPointerException("subPath cannot be null");
  }
  
  public Request addRequestHeader(String paramString1, String paramString2) {
    if (paramString1 != null) {
      if (!paramString1.contains(":")) {
        String str = paramString2;
        if (paramString2 == null)
          str = ""; 
        this.mRequestHeaders.add(Pair.create(paramString1, str));
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
  
  public Request setAllowedNetworkTypes(int paramInt) {
    this.mAllowedNetworkTypes = paramInt;
    return this;
  }
  
  public Request setAllowedOverMetered(boolean paramBoolean) {
    this.mMeteredAllowed = paramBoolean;
    return this;
  }
  
  public Request setAllowedOverRoaming(boolean paramBoolean) {
    this.mRoamingAllowed = paramBoolean;
    return this;
  }
  
  public Request setDescription(CharSequence paramCharSequence) {
    this.mDescription = paramCharSequence;
    return this;
  }
  
  public Request setDestinationInExternalFilesDir(Context paramContext, String paramString1, String paramString2) {
    File file = paramContext.getExternalFilesDir(paramString1);
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
      setDestinationFromBase(file, paramString2);
      return this;
    } 
    throw new IllegalStateException("Failed to get external storage files directory");
  }
  
  public Request setDestinationInExternalPublicDir(String paramString1, String paramString2) {
    File file = Environment.getExternalStoragePublicDirectory(paramString1);
    if (file != null) {
      Application application = AppGlobals.getInitialApplication();
      if ((application.getApplicationInfo()).targetSdkVersion >= 29 || !Environment.isExternalStorageLegacy())
        try {
          ContentProviderClient contentProviderClient = application.getContentResolver().acquireContentProviderClient("downloads");
          try {
            Bundle bundle = new Bundle();
            this();
            bundle.putString("dir_type", paramString1);
            contentProviderClient.call("create_external_public_dir", null, bundle);
            if (contentProviderClient != null)
              contentProviderClient.close(); 
            return this;
          } finally {
            if (contentProviderClient != null)
              try {
                contentProviderClient.close();
              } finally {
                paramString2 = null;
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
      setDestinationFromBase(file, paramString2);
      return this;
    } 
    throw new IllegalStateException("Failed to get external storage public directory");
  }
  
  public Request setDestinationUri(Uri paramUri) {
    this.mDestinationUri = paramUri;
    return this;
  }
  
  public Request setMimeType(String paramString) {
    this.mMimeType = paramString;
    return this;
  }
  
  public Request setNotificationVisibility(int paramInt) {
    this.mNotificationVisibility = paramInt;
    return this;
  }
  
  public Request setRequiresCharging(boolean paramBoolean) {
    if (paramBoolean) {
      this.mFlags |= 0x1;
    } else {
      this.mFlags &= 0xFFFFFFFE;
    } 
    return this;
  }
  
  public Request setRequiresDeviceIdle(boolean paramBoolean) {
    if (paramBoolean) {
      this.mFlags |= 0x2;
    } else {
      this.mFlags &= 0xFFFFFFFD;
    } 
    return this;
  }
  
  @Deprecated
  public Request setShowRunningNotification(boolean paramBoolean) {
    Request request;
    if (paramBoolean) {
      request = setNotificationVisibility(0);
    } else {
      request = setNotificationVisibility(2);
    } 
    return request;
  }
  
  public Request setTitle(CharSequence paramCharSequence) {
    this.mTitle = paramCharSequence;
    return this;
  }
  
  @Deprecated
  public Request setVisibleInDownloadsUi(boolean paramBoolean) {
    this.mIsVisibleInDownloadsUi = paramBoolean;
    return this;
  }
  
  ContentValues toContentValues(String paramString) {
    ContentValues contentValues = new ContentValues();
    contentValues.put("uri", this.mUri.toString());
    contentValues.put("is_public_api", Boolean.valueOf(true));
    contentValues.put("notificationpackage", paramString);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/DownloadManager$Request.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */