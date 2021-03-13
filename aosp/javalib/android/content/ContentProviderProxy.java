package android.content;

import android.content.res.AssetFileDescriptor;
import android.database.BulkCursorDescriptor;
import android.database.BulkCursorToCursorAdaptor;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ICancellationSignal;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteCallback;
import android.os.RemoteException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

final class ContentProviderProxy implements IContentProvider {
  private IBinder mRemote;
  
  public ContentProviderProxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public ContentProviderResult[] applyBatch(String paramString1, String paramString2, String paramString3, ArrayList<ContentProviderOperation> paramArrayList) throws RemoteException, OperationApplicationException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      parcel1.writeInt(paramArrayList.size());
      Iterator<ContentProviderOperation> iterator = paramArrayList.iterator();
      while (iterator.hasNext())
        ((ContentProviderOperation)iterator.next()).writeToParcel(parcel1, 0); 
      this.mRemote.transact(20, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionWithOperationApplicationExceptionFromParcel(parcel2);
      return (ContentProviderResult[])parcel2.createTypedArray(ContentProviderResult.CREATOR);
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public int bulkInsert(String paramString1, String paramString2, Uri paramUri, ContentValues[] paramArrayOfContentValues) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      paramUri.writeToParcel(parcel1, 0);
      parcel1.writeTypedArray((Parcelable[])paramArrayOfContentValues, 0);
      this.mRemote.transact(13, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return parcel2.readInt();
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public Bundle call(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      parcel1.writeString(paramString3);
      parcel1.writeString(paramString4);
      parcel1.writeString(paramString5);
      parcel1.writeBundle(paramBundle);
      this.mRemote.transact(21, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return parcel2.readBundle();
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public Uri canonicalize(String paramString1, String paramString2, Uri paramUri) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      paramUri.writeToParcel(parcel1, 0);
      this.mRemote.transact(25, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return (Uri)Uri.CREATOR.createFromParcel(parcel2);
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public void canonicalizeAsync(String paramString1, String paramString2, Uri paramUri, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.IContentProvider");
      parcel.writeString(paramString1);
      parcel.writeString(paramString2);
      paramUri.writeToParcel(parcel, 0);
      paramRemoteCallback.writeToParcel(parcel, 0);
      this.mRemote.transact(30, parcel, null, 1);
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public int checkUriPermission(String paramString1, String paramString2, Uri paramUri, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      paramUri.writeToParcel(parcel1, 0);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      this.mRemote.transact(28, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      paramInt1 = parcel2.readInt();
      return paramInt1;
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public ICancellationSignal createCancellationSignal() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      this.mRemote.transact(24, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return ICancellationSignal.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public int delete(String paramString1, String paramString2, Uri paramUri, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      paramUri.writeToParcel(parcel1, 0);
      parcel1.writeBundle(paramBundle);
      this.mRemote.transact(4, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return parcel2.readInt();
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public String[] getStreamTypes(Uri paramUri, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      paramUri.writeToParcel(parcel1, 0);
      parcel1.writeString(paramString);
      this.mRemote.transact(22, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return parcel2.createStringArray();
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public String getType(Uri paramUri) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      paramUri.writeToParcel(parcel1, 0);
      this.mRemote.transact(2, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return parcel2.readString();
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public void getTypeAsync(Uri paramUri, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.IContentProvider");
      paramUri.writeToParcel(parcel, 0);
      paramRemoteCallback.writeToParcel(parcel, 0);
      this.mRemote.transact(29, parcel, null, 1);
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public Uri insert(String paramString1, String paramString2, Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      paramUri.writeToParcel(parcel1, 0);
      paramContentValues.writeToParcel(parcel1, 0);
      parcel1.writeBundle(paramBundle);
      this.mRemote.transact(3, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return (Uri)Uri.CREATOR.createFromParcel(parcel2);
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public AssetFileDescriptor openAssetFile(String paramString1, String paramString2, Uri paramUri, String paramString3, ICancellationSignal paramICancellationSignal) throws RemoteException, FileNotFoundException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      AssetFileDescriptor assetFileDescriptor;
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      paramUri.writeToParcel(parcel1, 0);
      parcel1.writeString(paramString3);
      paramString2 = null;
      if (paramICancellationSignal != null) {
        IBinder iBinder = paramICancellationSignal.asBinder();
      } else {
        paramString1 = null;
      } 
      parcel1.writeStrongBinder((IBinder)paramString1);
      this.mRemote.transact(15, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionWithFileNotFoundExceptionFromParcel(parcel2);
      paramString1 = paramString2;
      if (parcel2.readInt() != 0)
        assetFileDescriptor = (AssetFileDescriptor)AssetFileDescriptor.CREATOR.createFromParcel(parcel2); 
      return assetFileDescriptor;
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public ParcelFileDescriptor openFile(String paramString1, String paramString2, Uri paramUri, String paramString3, ICancellationSignal paramICancellationSignal, IBinder paramIBinder) throws RemoteException, FileNotFoundException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParcelFileDescriptor parcelFileDescriptor;
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      paramUri.writeToParcel(parcel1, 0);
      parcel1.writeString(paramString3);
      paramString2 = null;
      if (paramICancellationSignal != null) {
        IBinder iBinder = paramICancellationSignal.asBinder();
      } else {
        paramString1 = null;
      } 
      parcel1.writeStrongBinder((IBinder)paramString1);
      parcel1.writeStrongBinder(paramIBinder);
      this.mRemote.transact(14, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionWithFileNotFoundExceptionFromParcel(parcel2);
      paramString1 = paramString2;
      if (parcel2.readInt() != 0)
        parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2); 
      return parcelFileDescriptor;
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public AssetFileDescriptor openTypedAssetFile(String paramString1, String paramString2, Uri paramUri, String paramString3, Bundle paramBundle, ICancellationSignal paramICancellationSignal) throws RemoteException, FileNotFoundException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      AssetFileDescriptor assetFileDescriptor;
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      paramUri.writeToParcel(parcel1, 0);
      parcel1.writeString(paramString3);
      parcel1.writeBundle(paramBundle);
      paramString2 = null;
      if (paramICancellationSignal != null) {
        IBinder iBinder = paramICancellationSignal.asBinder();
      } else {
        paramString1 = null;
      } 
      parcel1.writeStrongBinder((IBinder)paramString1);
      this.mRemote.transact(23, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionWithFileNotFoundExceptionFromParcel(parcel2);
      paramString1 = paramString2;
      if (parcel2.readInt() != 0)
        assetFileDescriptor = (AssetFileDescriptor)AssetFileDescriptor.CREATOR.createFromParcel(parcel2); 
      return assetFileDescriptor;
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public Cursor query(String paramString1, String paramString2, Uri paramUri, String[] paramArrayOfString, Bundle paramBundle, ICancellationSignal paramICancellationSignal) throws RemoteException {
    BulkCursorToCursorAdaptor bulkCursorToCursorAdaptor = new BulkCursorToCursorAdaptor();
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      paramUri.writeToParcel(parcel1, 0);
      int i = 0;
      if (paramArrayOfString != null)
        i = paramArrayOfString.length; 
      parcel1.writeInt(i);
      for (byte b = 0; b < i; b++)
        parcel1.writeString(paramArrayOfString[b]); 
      parcel1.writeBundle(paramBundle);
      parcel1.writeStrongBinder(bulkCursorToCursorAdaptor.getObserver().asBinder());
      paramString2 = null;
      if (paramICancellationSignal != null) {
        IBinder iBinder = paramICancellationSignal.asBinder();
      } else {
        paramString1 = null;
      } 
      parcel1.writeStrongBinder((IBinder)paramString1);
      this.mRemote.transact(1, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      if (parcel2.readInt() != 0) {
        IBinder iBinder1;
        BulkCursorDescriptor bulkCursorDescriptor = (BulkCursorDescriptor)BulkCursorDescriptor.CREATOR.createFromParcel(parcel2);
        IBinder iBinder2 = this.mRemote;
        paramString1 = paramString2;
        if (bulkCursorDescriptor.cursor != null)
          iBinder1 = bulkCursorDescriptor.cursor.asBinder(); 
        Binder.copyAllowBlocking(iBinder2, iBinder1);
        bulkCursorToCursorAdaptor.initialize(bulkCursorDescriptor);
        BulkCursorToCursorAdaptor bulkCursorToCursorAdaptor1 = bulkCursorToCursorAdaptor;
      } else {
        bulkCursorToCursorAdaptor.close();
        paramString1 = null;
      } 
      parcel1.recycle();
      parcel2.recycle();
      return (Cursor)paramString1;
    } catch (RemoteException remoteException) {
      bulkCursorToCursorAdaptor.close();
      throw remoteException;
    } catch (RuntimeException runtimeException) {
      bulkCursorToCursorAdaptor.close();
      throw runtimeException;
    } finally {}
    parcel1.recycle();
    parcel2.recycle();
    throw paramString1;
  }
  
  public boolean refresh(String paramString1, String paramString2, Uri paramUri, Bundle paramBundle, ICancellationSignal paramICancellationSignal) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      boolean bool = false;
      paramUri.writeToParcel(parcel1, 0);
      parcel1.writeBundle(paramBundle);
      if (paramICancellationSignal != null) {
        IBinder iBinder = paramICancellationSignal.asBinder();
      } else {
        paramString1 = null;
      } 
      parcel1.writeStrongBinder((IBinder)paramString1);
      this.mRemote.transact(27, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      int i = parcel2.readInt();
      if (i == 0)
        bool = true; 
      return bool;
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public Uri uncanonicalize(String paramString1, String paramString2, Uri paramUri) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      paramUri.writeToParcel(parcel1, 0);
      this.mRemote.transact(26, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return (Uri)Uri.CREATOR.createFromParcel(parcel2);
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
  
  public int update(String paramString1, String paramString2, Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.IContentProvider");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      paramUri.writeToParcel(parcel1, 0);
      paramContentValues.writeToParcel(parcel1, 0);
      parcel1.writeBundle(paramBundle);
      this.mRemote.transact(10, parcel1, parcel2, 0);
      DatabaseUtils.readExceptionFromParcel(parcel2);
      return parcel2.readInt();
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProviderProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */