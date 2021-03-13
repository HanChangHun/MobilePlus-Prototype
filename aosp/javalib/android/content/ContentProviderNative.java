package android.content;

import android.content.res.AssetFileDescriptor;
import android.database.BulkCursorDescriptor;
import android.database.Cursor;
import android.database.CursorToBulkCursorAdaptor;
import android.database.DatabaseUtils;
import android.database.IContentObserver;
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
import java.util.ArrayList;

public abstract class ContentProviderNative extends Binder implements IContentProvider {
  public ContentProviderNative() {
    attachInterface(this, "android.content.IContentProvider");
  }
  
  public static IContentProvider asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IContentProvider iContentProvider = (IContentProvider)paramIBinder.queryLocalInterface("android.content.IContentProvider");
    return (iContentProvider != null) ? iContentProvider : new ContentProviderProxy(paramIBinder);
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public abstract String getProviderName();
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    String str1;
    String[] arrayOfString;
    boolean bool = false;
    if (paramInt1 != 1) {
      Uri uri1;
      if (paramInt1 != 2) {
        ParcelFileDescriptor parcelFileDescriptor;
        if (paramInt1 != 3) {
          if (paramInt1 != 4) {
            if (paramInt1 != 10) {
              Uri uri2;
              ICancellationSignal iCancellationSignal;
              AssetFileDescriptor assetFileDescriptor2;
              String[] arrayOfString1;
              Bundle bundle1;
              ContentProviderResult[] arrayOfContentProviderResult;
              AssetFileDescriptor assetFileDescriptor1;
              String str5;
              Uri uri3;
              ArrayList<ContentProviderOperation> arrayList;
              String str4;
              Uri uri5;
              String str7;
              Uri uri4;
              String str6;
              Bundle bundle2;
              String str8;
              Uri uri6;
              boolean bool1;
              String str9;
              switch (paramInt1) {
                default:
                  switch (paramInt1) {
                    default:
                      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
                    case 30:
                      try {
                        paramParcel1.enforceInterface("android.content.IContentProvider");
                        canonicalizeAsync(paramParcel1.readString(), paramParcel1.readString(), (Uri)Uri.CREATOR.createFromParcel(paramParcel1), (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(paramParcel1));
                        return true;
                      } catch (Exception exception) {
                        DatabaseUtils.writeExceptionToParcel(paramParcel2, exception);
                        return true;
                      } 
                    case 29:
                      exception.enforceInterface("android.content.IContentProvider");
                      getTypeAsync((Uri)Uri.CREATOR.createFromParcel((Parcel)exception), (RemoteCallback)RemoteCallback.CREATOR.createFromParcel((Parcel)exception));
                      return true;
                    case 28:
                      exception.enforceInterface("android.content.IContentProvider");
                      paramInt1 = checkUriPermission(exception.readString(), exception.readString(), (Uri)Uri.CREATOR.createFromParcel((Parcel)exception), exception.readInt(), exception.readInt());
                      paramParcel2.writeNoException();
                      paramParcel2.writeInt(paramInt1);
                      return true;
                    case 27:
                      exception.enforceInterface("android.content.IContentProvider");
                      str5 = exception.readString();
                      arrayOfString = (String[])exception.readString();
                      uri5 = (Uri)Uri.CREATOR.createFromParcel((Parcel)exception);
                      bundle2 = exception.readBundle();
                      bool1 = refresh(str5, (String)arrayOfString, uri5, bundle2, ICancellationSignal.Stub.asInterface(exception.readStrongBinder()));
                      paramParcel2.writeNoException();
                      if (bool1) {
                        paramInt1 = bool;
                      } else {
                        paramInt1 = -1;
                      } 
                      paramParcel2.writeInt(paramInt1);
                      return true;
                    case 26:
                      exception.enforceInterface("android.content.IContentProvider");
                      uri2 = uncanonicalize(exception.readString(), exception.readString(), (Uri)Uri.CREATOR.createFromParcel((Parcel)exception));
                      paramParcel2.writeNoException();
                      Uri.writeToParcel(paramParcel2, uri2);
                      return true;
                    case 25:
                      uri2.enforceInterface("android.content.IContentProvider");
                      uri2 = canonicalize(uri2.readString(), uri2.readString(), (Uri)Uri.CREATOR.createFromParcel((Parcel)uri2));
                      paramParcel2.writeNoException();
                      Uri.writeToParcel(paramParcel2, uri2);
                      return true;
                    case 24:
                      uri2.enforceInterface("android.content.IContentProvider");
                      iCancellationSignal = createCancellationSignal();
                      paramParcel2.writeNoException();
                      paramParcel2.writeStrongBinder(iCancellationSignal.asBinder());
                      return true;
                    case 23:
                      iCancellationSignal.enforceInterface("android.content.IContentProvider");
                      str9 = iCancellationSignal.readString();
                      str7 = iCancellationSignal.readString();
                      uri3 = (Uri)Uri.CREATOR.createFromParcel((Parcel)iCancellationSignal);
                      arrayOfString = (String[])iCancellationSignal.readString();
                      bundle2 = iCancellationSignal.readBundle();
                      assetFileDescriptor2 = openTypedAssetFile(str9, str7, uri3, (String)arrayOfString, bundle2, ICancellationSignal.Stub.asInterface(iCancellationSignal.readStrongBinder()));
                      paramParcel2.writeNoException();
                      if (assetFileDescriptor2 != null) {
                        paramParcel2.writeInt(1);
                        assetFileDescriptor2.writeToParcel(paramParcel2, 1);
                      } else {
                        paramParcel2.writeInt(0);
                      } 
                      return true;
                    case 22:
                      assetFileDescriptor2.enforceInterface("android.content.IContentProvider");
                      arrayOfString1 = getStreamTypes((Uri)Uri.CREATOR.createFromParcel((Parcel)assetFileDescriptor2), assetFileDescriptor2.readString());
                      paramParcel2.writeNoException();
                      paramParcel2.writeStringArray(arrayOfString1);
                      return true;
                    case 21:
                      arrayOfString1.enforceInterface("android.content.IContentProvider");
                      bundle1 = call(arrayOfString1.readString(), arrayOfString1.readString(), arrayOfString1.readString(), arrayOfString1.readString(), arrayOfString1.readString(), arrayOfString1.readBundle());
                      paramParcel2.writeNoException();
                      paramParcel2.writeBundle(bundle1);
                      return true;
                    case 20:
                      break;
                  } 
                  bundle1.enforceInterface("android.content.IContentProvider");
                  arrayOfString = (String[])bundle1.readString();
                  str8 = bundle1.readString();
                  str7 = bundle1.readString();
                  paramInt2 = bundle1.readInt();
                  arrayList = new ArrayList();
                  this(paramInt2);
                  for (paramInt1 = 0; paramInt1 < paramInt2; paramInt1++)
                    arrayList.add(paramInt1, (ContentProviderOperation)ContentProviderOperation.CREATOR.createFromParcel((Parcel)bundle1)); 
                  arrayOfContentProviderResult = applyBatch((String)arrayOfString, str8, str7, arrayList);
                  paramParcel2.writeNoException();
                  paramParcel2.writeTypedArray((Parcelable[])arrayOfContentProviderResult, 0);
                  return true;
                case 15:
                  arrayOfContentProviderResult.enforceInterface("android.content.IContentProvider");
                  str4 = arrayOfContentProviderResult.readString();
                  arrayOfString = (String[])arrayOfContentProviderResult.readString();
                  uri4 = (Uri)Uri.CREATOR.createFromParcel((Parcel)arrayOfContentProviderResult);
                  str8 = arrayOfContentProviderResult.readString();
                  assetFileDescriptor1 = openAssetFile(str4, (String)arrayOfString, uri4, str8, ICancellationSignal.Stub.asInterface(arrayOfContentProviderResult.readStrongBinder()));
                  paramParcel2.writeNoException();
                  if (assetFileDescriptor1 != null) {
                    paramParcel2.writeInt(1);
                    assetFileDescriptor1.writeToParcel(paramParcel2, 1);
                  } else {
                    paramParcel2.writeInt(0);
                  } 
                  return true;
                case 14:
                  assetFileDescriptor1.enforceInterface("android.content.IContentProvider");
                  str4 = assetFileDescriptor1.readString();
                  str6 = assetFileDescriptor1.readString();
                  uri6 = (Uri)Uri.CREATOR.createFromParcel((Parcel)assetFileDescriptor1);
                  arrayOfString = (String[])assetFileDescriptor1.readString();
                  parcelFileDescriptor = openFile(str4, str6, uri6, (String)arrayOfString, ICancellationSignal.Stub.asInterface(assetFileDescriptor1.readStrongBinder()), assetFileDescriptor1.readStrongBinder());
                  paramParcel2.writeNoException();
                  if (parcelFileDescriptor != null) {
                    paramParcel2.writeInt(1);
                    parcelFileDescriptor.writeToParcel(paramParcel2, 1);
                  } else {
                    paramParcel2.writeInt(0);
                  } 
                  return true;
                case 13:
                  break;
              } 
              parcelFileDescriptor.enforceInterface("android.content.IContentProvider");
              paramInt1 = bulkInsert(parcelFileDescriptor.readString(), parcelFileDescriptor.readString(), (Uri)Uri.CREATOR.createFromParcel((Parcel)parcelFileDescriptor), (ContentValues[])parcelFileDescriptor.createTypedArray(ContentValues.CREATOR));
              paramParcel2.writeNoException();
              paramParcel2.writeInt(paramInt1);
              return true;
            } 
            parcelFileDescriptor.enforceInterface("android.content.IContentProvider");
            paramInt1 = update(parcelFileDescriptor.readString(), parcelFileDescriptor.readString(), (Uri)Uri.CREATOR.createFromParcel((Parcel)parcelFileDescriptor), (ContentValues)ContentValues.CREATOR.createFromParcel((Parcel)parcelFileDescriptor), parcelFileDescriptor.readBundle());
            paramParcel2.writeNoException();
            paramParcel2.writeInt(paramInt1);
            return true;
          } 
          parcelFileDescriptor.enforceInterface("android.content.IContentProvider");
          paramInt1 = delete(parcelFileDescriptor.readString(), parcelFileDescriptor.readString(), (Uri)Uri.CREATOR.createFromParcel((Parcel)parcelFileDescriptor), parcelFileDescriptor.readBundle());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        } 
        parcelFileDescriptor.enforceInterface("android.content.IContentProvider");
        uri1 = insert(parcelFileDescriptor.readString(), parcelFileDescriptor.readString(), (Uri)Uri.CREATOR.createFromParcel((Parcel)parcelFileDescriptor), (ContentValues)ContentValues.CREATOR.createFromParcel((Parcel)parcelFileDescriptor), parcelFileDescriptor.readBundle());
        paramParcel2.writeNoException();
        Uri.writeToParcel(paramParcel2, uri1);
        return true;
      } 
      uri1.enforceInterface("android.content.IContentProvider");
      str1 = getType((Uri)Uri.CREATOR.createFromParcel((Parcel)uri1));
      paramParcel2.writeNoException();
      paramParcel2.writeString(str1);
      return true;
    } 
    str1.enforceInterface("android.content.IContentProvider");
    String str3 = str1.readString();
    String str2 = str1.readString();
    Uri uri = (Uri)Uri.CREATOR.createFromParcel((Parcel)str1);
    paramInt2 = str1.readInt();
    if (paramInt2 > 0) {
      arrayOfString = new String[paramInt2];
      for (paramInt1 = 0; paramInt1 < paramInt2; paramInt1++)
        arrayOfString[paramInt1] = str1.readString(); 
    } else {
      arrayOfString = null;
    } 
    Bundle bundle = str1.readBundle();
    IContentObserver iContentObserver = IContentObserver.Stub.asInterface(str1.readStrongBinder());
    Cursor cursor = query(str3, str2, uri, arrayOfString, bundle, ICancellationSignal.Stub.asInterface(str1.readStrongBinder()));
    if (cursor != null) {
      CursorToBulkCursorAdaptor cursorToBulkCursorAdaptor;
      String str;
      str3 = null;
      Cursor cursor1 = cursor;
      str1 = str3;
      try {
        CursorToBulkCursorAdaptor cursorToBulkCursorAdaptor1 = new CursorToBulkCursorAdaptor();
        cursor1 = cursor;
        str1 = str3;
        this(cursor, iContentObserver, getProviderName());
        str3 = null;
        str = str3;
        cursorToBulkCursorAdaptor = cursorToBulkCursorAdaptor1;
        BulkCursorDescriptor bulkCursorDescriptor = cursorToBulkCursorAdaptor1.getBulkCursorDescriptor();
        cursorToBulkCursorAdaptor1 = null;
        str = str3;
        cursorToBulkCursorAdaptor = cursorToBulkCursorAdaptor1;
        paramParcel2.writeNoException();
        str = str3;
        cursorToBulkCursorAdaptor = cursorToBulkCursorAdaptor1;
        paramParcel2.writeInt(1);
        str = str3;
        cursorToBulkCursorAdaptor = cursorToBulkCursorAdaptor1;
        bulkCursorDescriptor.writeToParcel(paramParcel2, 1);
      } finally {
        if (cursorToBulkCursorAdaptor != null)
          cursorToBulkCursorAdaptor.close(); 
        if (str != null)
          str.close(); 
      } 
    } else {
      paramParcel2.writeNoException();
      paramParcel2.writeInt(0);
    } 
    return true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProviderNative.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */