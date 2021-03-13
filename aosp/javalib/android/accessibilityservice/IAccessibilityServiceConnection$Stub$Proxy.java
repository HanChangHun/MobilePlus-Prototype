package android.accessibilityservice;

import android.content.pm.ParceledListSlice;
import android.graphics.Region;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import android.view.accessibility.IAccessibilityInteractionConnectionCallback;
import java.util.List;

class Proxy implements IAccessibilityServiceConnection {
  public static IAccessibilityServiceConnection sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void disableSelf() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceConnection.Stub.getDefaultImpl().disableSelf();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void dispatchGesture(int paramInt1, ParceledListSlice paramParceledListSlice, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt1);
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceConnection.Stub.getDefaultImpl().dispatchGesture(paramInt1, paramParceledListSlice, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String[] findAccessibilityNodeInfoByAccessibilityId(int paramInt1, long paramLong1, int paramInt2, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, int paramInt3, long paramLong2, Bundle paramBundle) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      try {
        parcel1.writeInt(paramInt1);
        parcel1.writeLong(paramLong1);
        try {
          IBinder iBinder;
          parcel1.writeInt(paramInt2);
          if (paramIAccessibilityInteractionConnectionCallback != null) {
            iBinder = paramIAccessibilityInteractionConnectionCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeInt(paramInt3);
          parcel1.writeLong(paramLong2);
          if (paramBundle != null) {
            parcel1.writeInt(1);
            paramBundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().findAccessibilityNodeInfoByAccessibilityId(paramInt1, paramLong1, paramInt2, paramIAccessibilityInteractionConnectionCallback, paramInt3, paramLong2, paramBundle);
            parcel2.recycle();
            parcel1.recycle();
            return arrayOfString1;
          } 
          parcel2.readException();
          String[] arrayOfString = parcel2.createStringArray();
          parcel2.recycle();
          parcel1.recycle();
          return arrayOfString;
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIAccessibilityInteractionConnectionCallback;
  }
  
  public String[] findAccessibilityNodeInfosByText(int paramInt1, long paramLong1, String paramString, int paramInt2, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeLong(paramLong1);
          try {
            IBinder iBinder;
            parcel1.writeString(paramString);
            parcel1.writeInt(paramInt2);
            if (paramIAccessibilityInteractionConnectionCallback != null) {
              iBinder = paramIAccessibilityInteractionConnectionCallback.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            parcel1.writeLong(paramLong2);
            if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
              String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().findAccessibilityNodeInfosByText(paramInt1, paramLong1, paramString, paramInt2, paramIAccessibilityInteractionConnectionCallback, paramLong2);
              parcel2.recycle();
              parcel1.recycle();
              return arrayOfString1;
            } 
            parcel2.readException();
            String[] arrayOfString = parcel2.createStringArray();
            parcel2.recycle();
            parcel1.recycle();
            return arrayOfString;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString;
  }
  
  public String[] findAccessibilityNodeInfosByViewId(int paramInt1, long paramLong1, String paramString, int paramInt2, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeLong(paramLong1);
          try {
            IBinder iBinder;
            parcel1.writeString(paramString);
            parcel1.writeInt(paramInt2);
            if (paramIAccessibilityInteractionConnectionCallback != null) {
              iBinder = paramIAccessibilityInteractionConnectionCallback.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            parcel1.writeLong(paramLong2);
            if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
              String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().findAccessibilityNodeInfosByViewId(paramInt1, paramLong1, paramString, paramInt2, paramIAccessibilityInteractionConnectionCallback, paramLong2);
              parcel2.recycle();
              parcel1.recycle();
              return arrayOfString1;
            } 
            parcel2.readException();
            String[] arrayOfString = parcel2.createStringArray();
            parcel2.recycle();
            parcel1.recycle();
            return arrayOfString;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramString;
  }
  
  public String[] findFocus(int paramInt1, long paramLong1, int paramInt2, int paramInt3, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeLong(paramLong1);
          try {
            IBinder iBinder;
            parcel1.writeInt(paramInt2);
            parcel1.writeInt(paramInt3);
            if (paramIAccessibilityInteractionConnectionCallback != null) {
              iBinder = paramIAccessibilityInteractionConnectionCallback.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            parcel1.writeLong(paramLong2);
            if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
              String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().findFocus(paramInt1, paramLong1, paramInt2, paramInt3, paramIAccessibilityInteractionConnectionCallback, paramLong2);
              parcel2.recycle();
              parcel1.recycle();
              return arrayOfString1;
            } 
            parcel2.readException();
            String[] arrayOfString = parcel2.createStringArray();
            parcel2.recycle();
            parcel1.recycle();
            return arrayOfString;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIAccessibilityInteractionConnectionCallback;
  }
  
  public String[] focusSearch(int paramInt1, long paramLong1, int paramInt2, int paramInt3, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeLong(paramLong1);
          try {
            IBinder iBinder;
            parcel1.writeInt(paramInt2);
            parcel1.writeInt(paramInt3);
            if (paramIAccessibilityInteractionConnectionCallback != null) {
              iBinder = paramIAccessibilityInteractionConnectionCallback.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            parcel1.writeLong(paramLong2);
            if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
              String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().focusSearch(paramInt1, paramLong1, paramInt2, paramInt3, paramIAccessibilityInteractionConnectionCallback, paramLong2);
              parcel2.recycle();
              parcel1.recycle();
              return arrayOfString1;
            } 
            parcel2.readException();
            String[] arrayOfString = parcel2.createStringArray();
            parcel2.recycle();
            parcel1.recycle();
            return arrayOfString;
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIAccessibilityInteractionConnectionCallback;
  }
  
  public String getInterfaceDescriptor() {
    return "android.accessibilityservice.IAccessibilityServiceConnection";
  }
  
  public float getMagnificationCenterX(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
        return IAccessibilityServiceConnection.Stub.getDefaultImpl().getMagnificationCenterX(paramInt); 
      parcel2.readException();
      return parcel2.readFloat();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public float getMagnificationCenterY(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
        return IAccessibilityServiceConnection.Stub.getDefaultImpl().getMagnificationCenterY(paramInt); 
      parcel2.readException();
      return parcel2.readFloat();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public Region getMagnificationRegion(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      Region region;
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        region = IAccessibilityServiceConnection.Stub.getDefaultImpl().getMagnificationRegion(paramInt);
        return region;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        region = (Region)Region.CREATOR.createFromParcel(parcel2);
      } else {
        region = null;
      } 
      return region;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public float getMagnificationScale(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
        return IAccessibilityServiceConnection.Stub.getDefaultImpl().getMagnificationScale(paramInt); 
      parcel2.readException();
      return parcel2.readFloat();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder getOverlayWindowToken(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
        return IAccessibilityServiceConnection.Stub.getDefaultImpl().getOverlayWindowToken(paramInt); 
      parcel2.readException();
      return parcel2.readStrongBinder();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public AccessibilityServiceInfo getServiceInfo() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      AccessibilityServiceInfo accessibilityServiceInfo;
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        accessibilityServiceInfo = IAccessibilityServiceConnection.Stub.getDefaultImpl().getServiceInfo();
        return accessibilityServiceInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        accessibilityServiceInfo = (AccessibilityServiceInfo)AccessibilityServiceInfo.CREATOR.createFromParcel(parcel2);
      } else {
        accessibilityServiceInfo = null;
      } 
      return accessibilityServiceInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getSoftKeyboardShowMode() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
        return IAccessibilityServiceConnection.Stub.getDefaultImpl().getSoftKeyboardShowMode(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<AccessibilityNodeInfo.AccessibilityAction> getSystemActions() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
        return IAccessibilityServiceConnection.Stub.getDefaultImpl().getSystemActions(); 
      parcel2.readException();
      return parcel2.createTypedArrayList(AccessibilityNodeInfo.AccessibilityAction.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public AccessibilityWindowInfo getWindow(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      AccessibilityWindowInfo accessibilityWindowInfo;
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        accessibilityWindowInfo = IAccessibilityServiceConnection.Stub.getDefaultImpl().getWindow(paramInt);
        return accessibilityWindowInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        accessibilityWindowInfo = (AccessibilityWindowInfo)AccessibilityWindowInfo.CREATOR.createFromParcel(parcel2);
      } else {
        accessibilityWindowInfo = null;
      } 
      return accessibilityWindowInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getWindowIdForLeashToken(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
        return IAccessibilityServiceConnection.Stub.getDefaultImpl().getWindowIdForLeashToken(paramIBinder); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public AccessibilityWindowInfo.WindowListSparseArray getWindows() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      AccessibilityWindowInfo.WindowListSparseArray windowListSparseArray;
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        windowListSparseArray = IAccessibilityServiceConnection.Stub.getDefaultImpl().getWindows();
        return windowListSparseArray;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        windowListSparseArray = (AccessibilityWindowInfo.WindowListSparseArray)AccessibilityWindowInfo.WindowListSparseArray.CREATOR.createFromParcel(parcel2);
      } else {
        windowListSparseArray = null;
      } 
      return windowListSparseArray;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isAccessibilityButtonAvailable() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(26, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().isAccessibilityButtonAvailable();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isFingerprintGestureDetectionAvailable() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(29, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().isFingerprintGestureDetectionAvailable();
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean performAccessibilityAction(int paramInt1, long paramLong1, int paramInt2, Bundle paramBundle, int paramInt3, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      try {
        parcel1.writeInt(paramInt1);
        parcel1.writeLong(paramLong1);
        try {
          IBinder iBinder;
          parcel1.writeInt(paramInt2);
          boolean bool = true;
          if (paramBundle != null) {
            parcel1.writeInt(1);
            paramBundle.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(paramInt3);
          if (paramIAccessibilityInteractionConnectionCallback != null) {
            iBinder = paramIAccessibilityInteractionConnectionCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          parcel1.writeLong(paramLong2);
          if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().performAccessibilityAction(paramInt1, paramLong1, paramInt2, paramBundle, paramInt3, paramIAccessibilityInteractionConnectionCallback, paramLong2);
            parcel2.recycle();
            parcel1.recycle();
            return bool;
          } 
          parcel2.readException();
          paramInt1 = parcel2.readInt();
          if (paramInt1 == 0)
            bool = false; 
          parcel2.recycle();
          parcel1.recycle();
          return bool;
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramBundle;
  }
  
  public boolean performGlobalAction(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(11, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().performGlobalAction(paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean resetMagnification(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      boolean bool1 = true;
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        paramBoolean = IAccessibilityServiceConnection.Stub.getDefaultImpl().resetMagnification(paramInt, paramBoolean);
        return paramBoolean;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0) {
        paramBoolean = bool1;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sendGesture(int paramInt, ParceledListSlice paramParceledListSlice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceConnection.Stub.getDefaultImpl().sendGesture(paramInt, paramParceledListSlice);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setGestureDetectionPassthroughRegion(int paramInt, Region paramRegion) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      if (paramRegion != null) {
        parcel1.writeInt(1);
        paramRegion.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceConnection.Stub.getDefaultImpl().setGestureDetectionPassthroughRegion(paramInt, paramRegion);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setMagnificationCallbackEnabled(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceConnection.Stub.getDefaultImpl().setMagnificationCallbackEnabled(paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setMagnificationScaleAndCenter(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean) throws RemoteException {
    Exception exception;
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      try {
        parcel1.writeInt(paramInt);
        try {
          parcel1.writeFloat(paramFloat1);
          try {
            parcel1.writeFloat(paramFloat2);
            try {
              boolean bool2;
              parcel1.writeFloat(paramFloat3);
              boolean bool1 = true;
              if (paramBoolean) {
                bool2 = true;
              } else {
                bool2 = false;
              } 
              parcel1.writeInt(bool2);
              try {
                if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                  paramBoolean = IAccessibilityServiceConnection.Stub.getDefaultImpl().setMagnificationScaleAndCenter(paramInt, paramFloat1, paramFloat2, paramFloat3, paramBoolean);
                  parcel2.recycle();
                  parcel1.recycle();
                  return paramBoolean;
                } 
                parcel2.readException();
                paramInt = parcel2.readInt();
                if (paramInt != 0) {
                  paramBoolean = bool1;
                } else {
                  paramBoolean = false;
                } 
                parcel2.recycle();
                parcel1.recycle();
                return paramBoolean;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw exception;
  }
  
  public void setOnKeyEventResult(boolean paramBoolean, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(14, parcel, null, 1) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceConnection.Stub.getDefaultImpl().setOnKeyEventResult(paramBoolean, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void setServiceInfo(AccessibilityServiceInfo paramAccessibilityServiceInfo) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      if (paramAccessibilityServiceInfo != null) {
        parcel1.writeInt(1);
        paramAccessibilityServiceInfo.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceConnection.Stub.getDefaultImpl().setServiceInfo(paramAccessibilityServiceInfo);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setSoftKeyboardCallbackEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceConnection.Stub.getDefaultImpl().setSoftKeyboardCallbackEnabled(paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setSoftKeyboardShowMode(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(22, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().setSoftKeyboardShowMode(paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setTouchExplorationPassthroughRegion(int paramInt, Region paramRegion) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      if (paramRegion != null) {
        parcel1.writeInt(1);
        paramRegion.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceConnection.Stub.getDefaultImpl().setTouchExplorationPassthroughRegion(paramInt, paramRegion);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean switchToInputMethod(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(25, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().switchToInputMethod(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void takeScreenshot(int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
      parcel1.writeInt(paramInt);
      if (paramRemoteCallback != null) {
        parcel1.writeInt(1);
        paramRemoteCallback.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceConnection.Stub.getDefaultImpl().takeScreenshot(paramInt, paramRemoteCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/IAccessibilityServiceConnection$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */