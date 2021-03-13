package android.content;

import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ContentInterface {
  ContentProviderResult[] applyBatch(String paramString, ArrayList<ContentProviderOperation> paramArrayList) throws RemoteException, OperationApplicationException;
  
  int bulkInsert(Uri paramUri, ContentValues[] paramArrayOfContentValues) throws RemoteException;
  
  Bundle call(String paramString1, String paramString2, String paramString3, Bundle paramBundle) throws RemoteException;
  
  Uri canonicalize(Uri paramUri) throws RemoteException;
  
  int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2) throws RemoteException;
  
  int delete(Uri paramUri, Bundle paramBundle) throws RemoteException;
  
  String[] getStreamTypes(Uri paramUri, String paramString) throws RemoteException;
  
  String getType(Uri paramUri) throws RemoteException;
  
  Uri insert(Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) throws RemoteException;
  
  AssetFileDescriptor openAssetFile(Uri paramUri, String paramString, CancellationSignal paramCancellationSignal) throws RemoteException, FileNotFoundException;
  
  ParcelFileDescriptor openFile(Uri paramUri, String paramString, CancellationSignal paramCancellationSignal) throws RemoteException, FileNotFoundException;
  
  AssetFileDescriptor openTypedAssetFile(Uri paramUri, String paramString, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws RemoteException, FileNotFoundException;
  
  Cursor query(Uri paramUri, String[] paramArrayOfString, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws RemoteException;
  
  boolean refresh(Uri paramUri, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws RemoteException;
  
  Uri uncanonicalize(Uri paramUri) throws RemoteException;
  
  int update(Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) throws RemoteException;
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentInterface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */