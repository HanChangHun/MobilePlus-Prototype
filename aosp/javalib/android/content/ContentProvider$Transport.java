package android.content;

import android.app.AppOpsManager;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.ParcelableException;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.Trace;
import android.util.Pair;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

class Transport extends ContentProviderNative {
  volatile AppOpsManager mAppOpsManager = null;
  
  volatile ContentInterface mInterface = ContentProvider.this;
  
  volatile int mReadOp = -1;
  
  volatile int mWriteOp = -1;
  
  private void enforceFilePermission(String paramString1, String paramString2, Uri paramUri, String paramString3, IBinder paramIBinder) throws FileNotFoundException, SecurityException {
    if (paramString3 != null && paramString3.indexOf('w') != -1) {
      if (enforceWritePermission(paramString1, paramString2, paramUri, paramIBinder) != 0)
        throw new FileNotFoundException("App op not allowed"); 
    } else if (enforceReadPermission(paramString1, paramString2, paramUri, paramIBinder) != 0) {
      throw new FileNotFoundException("App op not allowed");
    } 
  }
  
  private int enforceReadPermission(String paramString1, String paramString2, Uri paramUri, IBinder paramIBinder) throws SecurityException {
    int i = ContentProvider.this.enforceReadPermissionInner(paramUri, paramString1, paramString2, paramIBinder);
    return (i != 0) ? i : noteProxyOp(paramString1, paramString2, this.mReadOp);
  }
  
  private int enforceWritePermission(String paramString1, String paramString2, Uri paramUri, IBinder paramIBinder) throws SecurityException {
    int i = ContentProvider.this.enforceWritePermissionInner(paramUri, paramString1, paramString2, paramIBinder);
    return (i != 0) ? i : noteProxyOp(paramString1, paramString2, this.mWriteOp);
  }
  
  private int noteProxyOp(String paramString1, String paramString2, int paramInt) {
    if (paramInt != -1) {
      paramInt = this.mAppOpsManager.noteProxyOp(paramInt, paramString1, Binder.getCallingUid(), paramString2, null);
      if (paramInt == 3)
        paramInt = 1; 
      return paramInt;
    } 
    return 0;
  }
  
  public ContentProviderResult[] applyBatch(String paramString1, String paramString2, String paramString3, ArrayList<ContentProviderOperation> paramArrayList) throws OperationApplicationException {
    ContentProvider.access$200(ContentProvider.this, paramString3);
    int i = paramArrayList.size();
    int[] arrayOfInt = new int[i];
    byte b = 0;
    while (b < i) {
      ContentProviderOperation contentProviderOperation1 = paramArrayList.get(b);
      Uri uri1 = contentProviderOperation1.getUri();
      arrayOfInt[b] = ContentProvider.getUserIdFromUri(uri1);
      uri1 = ContentProvider.this.validateIncomingUri(uri1);
      Uri uri2 = ContentProvider.access$000(ContentProvider.this, uri1);
      ContentProviderOperation contentProviderOperation2 = contentProviderOperation1;
      if (!Objects.equals(contentProviderOperation1.getUri(), uri2)) {
        contentProviderOperation2 = new ContentProviderOperation(contentProviderOperation1, uri2);
        paramArrayList.set(b, contentProviderOperation2);
      } 
      if (!contentProviderOperation2.isReadOperation() || enforceReadPermission(paramString1, paramString2, uri2, null) == 0) {
        if (!contentProviderOperation2.isWriteOperation() || enforceWritePermission(paramString1, paramString2, uri2, null) == 0) {
          b++;
          continue;
        } 
        throw new OperationApplicationException("App op not allowed", 0);
      } 
      throw new OperationApplicationException("App op not allowed", 0);
    } 
    Trace.traceBegin(1048576L, "applyBatch");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
    try {
      ContentProviderResult[] arrayOfContentProviderResult = this.mInterface.applyBatch(paramString3, paramArrayList);
      if (arrayOfContentProviderResult != null)
        for (b = 0; b < arrayOfContentProviderResult.length; b++) {
          if (arrayOfInt[b] != -2)
            arrayOfContentProviderResult[b] = new ContentProviderResult(arrayOfContentProviderResult[b], arrayOfInt[b]); 
        }  
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return arrayOfContentProviderResult;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
  
  public int bulkInsert(String paramString1, String paramString2, Uri paramUri, ContentValues[] paramArrayOfContentValues) {
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    paramUri = ContentProvider.access$000(ContentProvider.this, paramUri);
    if (enforceWritePermission(paramString1, paramString2, paramUri, null) != 0)
      return 0; 
    Trace.traceBegin(1048576L, "bulkInsert");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
    try {
      int i = this.mInterface.bulkInsert(paramUri, paramArrayOfContentValues);
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return i;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
  
  public Bundle call(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Bundle paramBundle) {
    ContentProvider.access$200(ContentProvider.this, paramString3);
    Bundle.setDefusable(paramBundle, true);
    Trace.traceBegin(1048576L, "call");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
    try {
      Bundle bundle = this.mInterface.call(paramString3, paramString4, paramString5, paramBundle);
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return bundle;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
  
  public Uri canonicalize(String paramString1, String paramString2, Uri paramUri) {
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    int i = ContentProvider.getUserIdFromUri(paramUri);
    paramUri = ContentProvider.getUriWithoutUserId(paramUri);
    if (enforceReadPermission(paramString1, paramString2, paramUri, null) != 0)
      return null; 
    Trace.traceBegin(1048576L, "canonicalize");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
    try {
      Uri uri = ContentProvider.maybeAddUserId(this.mInterface.canonicalize(paramUri), i);
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return uri;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
  
  public void canonicalizeAsync(String paramString1, String paramString2, Uri paramUri, RemoteCallback paramRemoteCallback) {
    Bundle bundle = new Bundle();
    try {
      bundle.putParcelable("result", (Parcelable)canonicalize(paramString1, paramString2, paramUri));
    } catch (Exception exception) {
      bundle.putParcelable("error", (Parcelable)new ParcelableException(exception));
    } 
    paramRemoteCallback.sendResult(bundle);
  }
  
  public int checkUriPermission(String paramString1, String paramString2, Uri paramUri, int paramInt1, int paramInt2) {
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    paramUri = ContentProvider.access$000(ContentProvider.this, paramUri);
    Trace.traceBegin(1048576L, "checkUriPermission");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
    try {
      paramInt1 = this.mInterface.checkUriPermission(paramUri, paramInt1, paramInt2);
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return paramInt1;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
  
  public ICancellationSignal createCancellationSignal() {
    return CancellationSignal.createTransport();
  }
  
  public int delete(String paramString1, String paramString2, Uri paramUri, Bundle paramBundle) {
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    paramUri = ContentProvider.access$000(ContentProvider.this, paramUri);
    if (enforceWritePermission(paramString1, paramString2, paramUri, null) != 0)
      return 0; 
    Trace.traceBegin(1048576L, "delete");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
    try {
      int i = this.mInterface.delete(paramUri, paramBundle);
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return i;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
  
  ContentProvider getContentProvider() {
    return ContentProvider.this;
  }
  
  public String getProviderName() {
    return getContentProvider().getClass().getName();
  }
  
  public String[] getStreamTypes(Uri paramUri, String paramString) {
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    paramUri = ContentProvider.access$000(ContentProvider.this, paramUri);
    Trace.traceBegin(1048576L, "getStreamTypes");
    try {
      String[] arrayOfString = this.mInterface.getStreamTypes(paramUri, paramString);
      Trace.traceEnd(1048576L);
      return arrayOfString;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    Trace.traceEnd(1048576L);
    throw paramUri;
  }
  
  public String getType(Uri paramUri) {
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    paramUri = ContentProvider.access$000(ContentProvider.this, paramUri);
    Trace.traceBegin(1048576L, "getType");
    try {
      String str = this.mInterface.getType(paramUri);
      Trace.traceEnd(1048576L);
      return str;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    Trace.traceEnd(1048576L);
    throw paramUri;
  }
  
  public void getTypeAsync(Uri paramUri, RemoteCallback paramRemoteCallback) {
    Bundle bundle = new Bundle();
    try {
      bundle.putString("result", getType(paramUri));
    } catch (Exception exception) {
      bundle.putParcelable("error", (Parcelable)new ParcelableException(exception));
    } 
    paramRemoteCallback.sendResult(bundle);
  }
  
  public Uri insert(String paramString1, String paramString2, Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) {
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    int i = ContentProvider.getUserIdFromUri(paramUri);
    paramUri = ContentProvider.access$000(ContentProvider.this, paramUri);
    if (enforceWritePermission(paramString1, paramString2, paramUri, null) != 0) {
      pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
      try {
        return ContentProvider.this.rejectInsert(paramUri, paramContentValues);
      } finally {
        ContentProvider.access$100(ContentProvider.this, pair);
      } 
    } 
    Trace.traceBegin(1048576L, "insert");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(pair, paramString2));
    try {
      Uri uri = ContentProvider.maybeAddUserId(this.mInterface.insert(paramUri, paramContentValues, paramBundle), i);
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return uri;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
  
  public AssetFileDescriptor openAssetFile(String paramString1, String paramString2, Uri paramUri, String paramString3, ICancellationSignal paramICancellationSignal) throws FileNotFoundException {
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    paramUri = ContentProvider.access$000(ContentProvider.this, paramUri);
    enforceFilePermission(paramString1, paramString2, paramUri, paramString3, null);
    Trace.traceBegin(1048576L, "openAssetFile");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
    try {
      AssetFileDescriptor assetFileDescriptor = this.mInterface.openAssetFile(paramUri, paramString3, CancellationSignal.fromTransport(paramICancellationSignal));
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return assetFileDescriptor;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
  
  public ParcelFileDescriptor openFile(String paramString1, String paramString2, Uri paramUri, String paramString3, ICancellationSignal paramICancellationSignal, IBinder paramIBinder) throws FileNotFoundException {
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    paramUri = ContentProvider.access$000(ContentProvider.this, paramUri);
    enforceFilePermission(paramString1, paramString2, paramUri, paramString3, paramIBinder);
    Trace.traceBegin(1048576L, "openFile");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
    try {
      ParcelFileDescriptor parcelFileDescriptor = this.mInterface.openFile(paramUri, paramString3, CancellationSignal.fromTransport(paramICancellationSignal));
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return parcelFileDescriptor;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
  
  public AssetFileDescriptor openTypedAssetFile(String paramString1, String paramString2, Uri paramUri, String paramString3, Bundle paramBundle, ICancellationSignal paramICancellationSignal) throws FileNotFoundException {
    Bundle.setDefusable(paramBundle, true);
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    paramUri = ContentProvider.access$000(ContentProvider.this, paramUri);
    enforceFilePermission(paramString1, paramString2, paramUri, "r", null);
    Trace.traceBegin(1048576L, "openTypedAssetFile");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
    try {
      AssetFileDescriptor assetFileDescriptor = this.mInterface.openTypedAssetFile(paramUri, paramString3, paramBundle, CancellationSignal.fromTransport(paramICancellationSignal));
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return assetFileDescriptor;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
  
  public Cursor query(String paramString1, String paramString2, Uri paramUri, String[] paramArrayOfString, Bundle paramBundle, ICancellationSignal paramICancellationSignal) {
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    paramUri = ContentProvider.access$000(ContentProvider.this, paramUri);
    if (enforceReadPermission(paramString1, paramString2, paramUri, null) != 0) {
      if (paramArrayOfString != null)
        return (Cursor)new MatrixCursor(paramArrayOfString, 0); 
      pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
      try {
        Cursor cursor = this.mInterface.query(paramUri, paramArrayOfString, paramBundle, CancellationSignal.fromTransport(paramICancellationSignal));
        ContentProvider.access$100(ContentProvider.this, pair);
        return (Cursor)((cursor == null) ? null : new MatrixCursor(cursor.getColumnNames(), 0));
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowAsRuntimeException();
      } finally {}
      ContentProvider.access$100(ContentProvider.this, pair);
      throw paramString2;
    } 
    Trace.traceBegin(1048576L, "query");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(pair, paramString2));
    try {
      Cursor cursor = this.mInterface.query(paramUri, paramArrayOfString, paramBundle, CancellationSignal.fromTransport(paramICancellationSignal));
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return cursor;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
  
  public boolean refresh(String paramString1, String paramString2, Uri paramUri, Bundle paramBundle, ICancellationSignal paramICancellationSignal) throws RemoteException {
    paramUri = ContentProvider.getUriWithoutUserId(ContentProvider.this.validateIncomingUri(paramUri));
    if (enforceReadPermission(paramString1, paramString2, paramUri, null) != 0)
      return false; 
    Trace.traceBegin(1048576L, "refresh");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
    try {
      return this.mInterface.refresh(paramUri, paramBundle, CancellationSignal.fromTransport(paramICancellationSignal));
    } finally {
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
    } 
  }
  
  public Uri uncanonicalize(String paramString1, String paramString2, Uri paramUri) {
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    int i = ContentProvider.getUserIdFromUri(paramUri);
    paramUri = ContentProvider.getUriWithoutUserId(paramUri);
    if (enforceReadPermission(paramString1, paramString2, paramUri, null) != 0)
      return null; 
    Trace.traceBegin(1048576L, "uncanonicalize");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
    try {
      Uri uri = ContentProvider.maybeAddUserId(this.mInterface.uncanonicalize(paramUri), i);
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return uri;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
  
  public int update(String paramString1, String paramString2, Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) {
    paramUri = ContentProvider.this.validateIncomingUri(paramUri);
    paramUri = ContentProvider.access$000(ContentProvider.this, paramUri);
    if (enforceWritePermission(paramString1, paramString2, paramUri, null) != 0)
      return 0; 
    Trace.traceBegin(1048576L, "update");
    Pair pair = ContentProvider.access$100(ContentProvider.this, new Pair(paramString1, paramString2));
    try {
      int i = this.mInterface.update(paramUri, paramContentValues, paramBundle);
      ContentProvider.access$100(ContentProvider.this, pair);
      Trace.traceEnd(1048576L);
      return i;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowAsRuntimeException();
    } finally {}
    ContentProvider.access$100(ContentProvider.this, pair);
    Trace.traceEnd(1048576L);
    throw paramString2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProvider$Transport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */