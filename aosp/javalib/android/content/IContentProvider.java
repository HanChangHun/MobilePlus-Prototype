package android.content;

import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface IContentProvider extends IInterface {
  public static final int APPLY_BATCH_TRANSACTION = 20;
  
  public static final int BULK_INSERT_TRANSACTION = 13;
  
  public static final int CALL_TRANSACTION = 21;
  
  public static final int CANONICALIZE_ASYNC_TRANSACTION = 30;
  
  public static final int CANONICALIZE_TRANSACTION = 25;
  
  public static final int CHECK_URI_PERMISSION_TRANSACTION = 28;
  
  public static final int CREATE_CANCELATION_SIGNAL_TRANSACTION = 24;
  
  public static final int DELETE_TRANSACTION = 4;
  
  public static final int GET_STREAM_TYPES_TRANSACTION = 22;
  
  public static final int GET_TYPE_ASYNC_TRANSACTION = 29;
  
  public static final int GET_TYPE_TRANSACTION = 2;
  
  public static final int INSERT_TRANSACTION = 3;
  
  public static final int OPEN_ASSET_FILE_TRANSACTION = 15;
  
  public static final int OPEN_FILE_TRANSACTION = 14;
  
  public static final int OPEN_TYPED_ASSET_FILE_TRANSACTION = 23;
  
  public static final int QUERY_TRANSACTION = 1;
  
  public static final int REFRESH_TRANSACTION = 27;
  
  public static final int UNCANONICALIZE_TRANSACTION = 26;
  
  public static final int UPDATE_TRANSACTION = 10;
  
  public static final String descriptor = "android.content.IContentProvider";
  
  ContentProviderResult[] applyBatch(String paramString1, String paramString2, String paramString3, ArrayList<ContentProviderOperation> paramArrayList) throws RemoteException, OperationApplicationException;
  
  @Deprecated
  default int bulkInsert(String paramString, Uri paramUri, ContentValues[] paramArrayOfContentValues) throws RemoteException {
    return bulkInsert(paramString, null, paramUri, paramArrayOfContentValues);
  }
  
  int bulkInsert(String paramString1, String paramString2, Uri paramUri, ContentValues[] paramArrayOfContentValues) throws RemoteException;
  
  @Deprecated
  default Bundle call(String paramString1, String paramString2, String paramString3, Bundle paramBundle) throws RemoteException {
    return call(paramString1, null, "unknown", paramString2, paramString3, paramBundle);
  }
  
  Bundle call(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Bundle paramBundle) throws RemoteException;
  
  Uri canonicalize(String paramString1, String paramString2, Uri paramUri) throws RemoteException;
  
  void canonicalizeAsync(String paramString1, String paramString2, Uri paramUri, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  int checkUriPermission(String paramString1, String paramString2, Uri paramUri, int paramInt1, int paramInt2) throws RemoteException;
  
  ICancellationSignal createCancellationSignal() throws RemoteException;
  
  @Deprecated
  default int delete(String paramString1, Uri paramUri, String paramString2, String[] paramArrayOfString) throws RemoteException {
    return delete(paramString1, (String)null, paramUri, ContentResolver.createSqlQueryBundle(paramString2, paramArrayOfString));
  }
  
  int delete(String paramString1, String paramString2, Uri paramUri, Bundle paramBundle) throws RemoteException;
  
  String[] getStreamTypes(Uri paramUri, String paramString) throws RemoteException;
  
  String getType(Uri paramUri) throws RemoteException;
  
  void getTypeAsync(Uri paramUri, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  @Deprecated
  default Uri insert(String paramString, Uri paramUri, ContentValues paramContentValues) throws RemoteException {
    return insert(paramString, null, paramUri, paramContentValues, null);
  }
  
  Uri insert(String paramString1, String paramString2, Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) throws RemoteException;
  
  AssetFileDescriptor openAssetFile(String paramString1, String paramString2, Uri paramUri, String paramString3, ICancellationSignal paramICancellationSignal) throws RemoteException, FileNotFoundException;
  
  ParcelFileDescriptor openFile(String paramString1, String paramString2, Uri paramUri, String paramString3, ICancellationSignal paramICancellationSignal, IBinder paramIBinder) throws RemoteException, FileNotFoundException;
  
  AssetFileDescriptor openTypedAssetFile(String paramString1, String paramString2, Uri paramUri, String paramString3, Bundle paramBundle, ICancellationSignal paramICancellationSignal) throws RemoteException, FileNotFoundException;
  
  Cursor query(String paramString1, String paramString2, Uri paramUri, String[] paramArrayOfString, Bundle paramBundle, ICancellationSignal paramICancellationSignal) throws RemoteException;
  
  boolean refresh(String paramString1, String paramString2, Uri paramUri, Bundle paramBundle, ICancellationSignal paramICancellationSignal) throws RemoteException;
  
  Uri uncanonicalize(String paramString1, String paramString2, Uri paramUri) throws RemoteException;
  
  @Deprecated
  default int update(String paramString1, Uri paramUri, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString) throws RemoteException {
    return update(paramString1, (String)null, paramUri, paramContentValues, ContentResolver.createSqlQueryBundle(paramString2, paramArrayOfString));
  }
  
  int update(String paramString1, String paramString2, Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) throws RemoteException;
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IContentProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */