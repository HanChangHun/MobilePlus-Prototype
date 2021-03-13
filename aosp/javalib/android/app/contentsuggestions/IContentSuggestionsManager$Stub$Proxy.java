package android.app.contentsuggestions;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.os.IResultReceiver;

class Proxy implements IContentSuggestionsManager {
  public static IContentSuggestionsManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void classifyContentSelections(int paramInt, ClassificationsRequest paramClassificationsRequest, IClassificationsCallback paramIClassificationsCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
      parcel.writeInt(paramInt);
      if (paramClassificationsRequest != null) {
        parcel.writeInt(1);
        paramClassificationsRequest.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramIClassificationsCallback != null) {
        iBinder = paramIClassificationsCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(4, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
        IContentSuggestionsManager.Stub.getDefaultImpl().classifyContentSelections(paramInt, paramClassificationsRequest, paramIClassificationsCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.contentsuggestions.IContentSuggestionsManager";
  }
  
  public void isEnabled(int paramInt, IResultReceiver paramIResultReceiver) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
      parcel.writeInt(paramInt);
      if (paramIResultReceiver != null) {
        iBinder = paramIResultReceiver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(6, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
        IContentSuggestionsManager.Stub.getDefaultImpl().isEnabled(paramInt, paramIResultReceiver);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void notifyInteraction(int paramInt, String paramString, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
      parcel.writeInt(paramInt);
      parcel.writeString(paramString);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(5, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
        IContentSuggestionsManager.Stub.getDefaultImpl().notifyInteraction(paramInt, paramString, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void provideContextBitmap(int paramInt, Bitmap paramBitmap, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
      parcel.writeInt(paramInt);
      if (paramBitmap != null) {
        parcel.writeInt(1);
        paramBitmap.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
        IContentSuggestionsManager.Stub.getDefaultImpl().provideContextBitmap(paramInt, paramBitmap, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void provideContextImage(int paramInt1, int paramInt2, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (paramBundle != null) {
        parcel.writeInt(1);
        paramBundle.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
        IContentSuggestionsManager.Stub.getDefaultImpl().provideContextImage(paramInt1, paramInt2, paramBundle);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void suggestContentSelections(int paramInt, SelectionsRequest paramSelectionsRequest, ISelectionsCallback paramISelectionsCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
      parcel.writeInt(paramInt);
      if (paramSelectionsRequest != null) {
        parcel.writeInt(1);
        paramSelectionsRequest.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramISelectionsCallback != null) {
        iBinder = paramISelectionsCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(3, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
        IContentSuggestionsManager.Stub.getDefaultImpl().suggestContentSelections(paramInt, paramSelectionsRequest, paramISelectionsCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/IContentSuggestionsManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */