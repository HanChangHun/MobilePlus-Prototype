package android.accessibilityservice;

import android.graphics.Region;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

class Proxy implements IAccessibilityServiceClient {
  public static IAccessibilityServiceClient sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void clearAccessibilityCache() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      if (!this.mRemote.transact(5, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().clearAccessibilityCache();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.accessibilityservice.IAccessibilityServiceClient";
  }
  
  public void init(IAccessibilityServiceConnection paramIAccessibilityServiceConnection, int paramInt, IBinder paramIBinder) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      if (paramIAccessibilityServiceConnection != null) {
        iBinder = paramIAccessibilityServiceConnection.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      parcel.writeInt(paramInt);
      parcel.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(1, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().init(paramIAccessibilityServiceConnection, paramInt, paramIBinder);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAccessibilityButtonAvailabilityChanged(boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(13, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().onAccessibilityButtonAvailabilityChanged(paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAccessibilityButtonClicked(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(12, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().onAccessibilityButtonClicked(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      boolean bool = false;
      if (paramAccessibilityEvent != null) {
        parcel.writeInt(1);
        paramAccessibilityEvent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBoolean)
        bool = true; 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(2, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().onAccessibilityEvent(paramAccessibilityEvent, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onFingerprintCapturingGesturesChanged(boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(10, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().onFingerprintCapturingGesturesChanged(paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onFingerprintGesture(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(11, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().onFingerprintGesture(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onGesture(AccessibilityGestureEvent paramAccessibilityGestureEvent) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      if (paramAccessibilityGestureEvent != null) {
        parcel.writeInt(1);
        paramAccessibilityGestureEvent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(4, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().onGesture(paramAccessibilityGestureEvent);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onInterrupt() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      if (!this.mRemote.transact(3, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().onInterrupt();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onKeyEvent(KeyEvent paramKeyEvent, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      if (paramKeyEvent != null) {
        parcel.writeInt(1);
        paramKeyEvent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(6, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().onKeyEvent(paramKeyEvent, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onMagnificationChanged(int paramInt, Region paramRegion, float paramFloat1, float paramFloat2, float paramFloat3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      parcel.writeInt(paramInt);
      if (paramRegion != null) {
        parcel.writeInt(1);
        paramRegion.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeFloat(paramFloat1);
      parcel.writeFloat(paramFloat2);
      parcel.writeFloat(paramFloat3);
      if (!this.mRemote.transact(7, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().onMagnificationChanged(paramInt, paramRegion, paramFloat1, paramFloat2, paramFloat3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPerformGestureResult(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      parcel.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(9, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().onPerformGestureResult(paramInt, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSoftKeyboardShowModeChanged(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().onSoftKeyboardShowModeChanged(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSystemActionsChanged() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceClient");
      if (!this.mRemote.transact(14, parcel, null, 1) && IAccessibilityServiceClient.Stub.getDefaultImpl() != null) {
        IAccessibilityServiceClient.Stub.getDefaultImpl().onSystemActionsChanged();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/IAccessibilityServiceClient$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */