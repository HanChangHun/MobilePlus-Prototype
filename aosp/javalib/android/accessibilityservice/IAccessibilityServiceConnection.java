package android.accessibilityservice;

import android.content.pm.ParceledListSlice;
import android.graphics.Region;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import android.view.accessibility.IAccessibilityInteractionConnectionCallback;
import java.util.List;

public interface IAccessibilityServiceConnection extends IInterface {
  void disableSelf() throws RemoteException;
  
  void dispatchGesture(int paramInt1, ParceledListSlice paramParceledListSlice, int paramInt2) throws RemoteException;
  
  String[] findAccessibilityNodeInfoByAccessibilityId(int paramInt1, long paramLong1, int paramInt2, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, int paramInt3, long paramLong2, Bundle paramBundle) throws RemoteException;
  
  String[] findAccessibilityNodeInfosByText(int paramInt1, long paramLong1, String paramString, int paramInt2, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException;
  
  String[] findAccessibilityNodeInfosByViewId(int paramInt1, long paramLong1, String paramString, int paramInt2, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException;
  
  String[] findFocus(int paramInt1, long paramLong1, int paramInt2, int paramInt3, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException;
  
  String[] focusSearch(int paramInt1, long paramLong1, int paramInt2, int paramInt3, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException;
  
  float getMagnificationCenterX(int paramInt) throws RemoteException;
  
  float getMagnificationCenterY(int paramInt) throws RemoteException;
  
  Region getMagnificationRegion(int paramInt) throws RemoteException;
  
  float getMagnificationScale(int paramInt) throws RemoteException;
  
  IBinder getOverlayWindowToken(int paramInt) throws RemoteException;
  
  AccessibilityServiceInfo getServiceInfo() throws RemoteException;
  
  int getSoftKeyboardShowMode() throws RemoteException;
  
  List<AccessibilityNodeInfo.AccessibilityAction> getSystemActions() throws RemoteException;
  
  AccessibilityWindowInfo getWindow(int paramInt) throws RemoteException;
  
  int getWindowIdForLeashToken(IBinder paramIBinder) throws RemoteException;
  
  AccessibilityWindowInfo.WindowListSparseArray getWindows() throws RemoteException;
  
  boolean isAccessibilityButtonAvailable() throws RemoteException;
  
  boolean isFingerprintGestureDetectionAvailable() throws RemoteException;
  
  boolean performAccessibilityAction(int paramInt1, long paramLong1, int paramInt2, Bundle paramBundle, int paramInt3, IAccessibilityInteractionConnectionCallback paramIAccessibilityInteractionConnectionCallback, long paramLong2) throws RemoteException;
  
  boolean performGlobalAction(int paramInt) throws RemoteException;
  
  boolean resetMagnification(int paramInt, boolean paramBoolean) throws RemoteException;
  
  void sendGesture(int paramInt, ParceledListSlice paramParceledListSlice) throws RemoteException;
  
  void setGestureDetectionPassthroughRegion(int paramInt, Region paramRegion) throws RemoteException;
  
  void setMagnificationCallbackEnabled(int paramInt, boolean paramBoolean) throws RemoteException;
  
  boolean setMagnificationScaleAndCenter(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean) throws RemoteException;
  
  void setOnKeyEventResult(boolean paramBoolean, int paramInt) throws RemoteException;
  
  void setServiceInfo(AccessibilityServiceInfo paramAccessibilityServiceInfo) throws RemoteException;
  
  void setSoftKeyboardCallbackEnabled(boolean paramBoolean) throws RemoteException;
  
  boolean setSoftKeyboardShowMode(int paramInt) throws RemoteException;
  
  void setTouchExplorationPassthroughRegion(int paramInt, Region paramRegion) throws RemoteException;
  
  boolean switchToInputMethod(String paramString) throws RemoteException;
  
  void takeScreenshot(int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException;
  
  public static class Default implements IAccessibilityServiceConnection {
    public IBinder asBinder() {
      return null;
    }
    
    public void disableSelf() throws RemoteException {}
    
    public void dispatchGesture(int param1Int1, ParceledListSlice param1ParceledListSlice, int param1Int2) throws RemoteException {}
    
    public String[] findAccessibilityNodeInfoByAccessibilityId(int param1Int1, long param1Long1, int param1Int2, IAccessibilityInteractionConnectionCallback param1IAccessibilityInteractionConnectionCallback, int param1Int3, long param1Long2, Bundle param1Bundle) throws RemoteException {
      return null;
    }
    
    public String[] findAccessibilityNodeInfosByText(int param1Int1, long param1Long1, String param1String, int param1Int2, IAccessibilityInteractionConnectionCallback param1IAccessibilityInteractionConnectionCallback, long param1Long2) throws RemoteException {
      return null;
    }
    
    public String[] findAccessibilityNodeInfosByViewId(int param1Int1, long param1Long1, String param1String, int param1Int2, IAccessibilityInteractionConnectionCallback param1IAccessibilityInteractionConnectionCallback, long param1Long2) throws RemoteException {
      return null;
    }
    
    public String[] findFocus(int param1Int1, long param1Long1, int param1Int2, int param1Int3, IAccessibilityInteractionConnectionCallback param1IAccessibilityInteractionConnectionCallback, long param1Long2) throws RemoteException {
      return null;
    }
    
    public String[] focusSearch(int param1Int1, long param1Long1, int param1Int2, int param1Int3, IAccessibilityInteractionConnectionCallback param1IAccessibilityInteractionConnectionCallback, long param1Long2) throws RemoteException {
      return null;
    }
    
    public float getMagnificationCenterX(int param1Int) throws RemoteException {
      return 0.0F;
    }
    
    public float getMagnificationCenterY(int param1Int) throws RemoteException {
      return 0.0F;
    }
    
    public Region getMagnificationRegion(int param1Int) throws RemoteException {
      return null;
    }
    
    public float getMagnificationScale(int param1Int) throws RemoteException {
      return 0.0F;
    }
    
    public IBinder getOverlayWindowToken(int param1Int) throws RemoteException {
      return null;
    }
    
    public AccessibilityServiceInfo getServiceInfo() throws RemoteException {
      return null;
    }
    
    public int getSoftKeyboardShowMode() throws RemoteException {
      return 0;
    }
    
    public List<AccessibilityNodeInfo.AccessibilityAction> getSystemActions() throws RemoteException {
      return null;
    }
    
    public AccessibilityWindowInfo getWindow(int param1Int) throws RemoteException {
      return null;
    }
    
    public int getWindowIdForLeashToken(IBinder param1IBinder) throws RemoteException {
      return 0;
    }
    
    public AccessibilityWindowInfo.WindowListSparseArray getWindows() throws RemoteException {
      return null;
    }
    
    public boolean isAccessibilityButtonAvailable() throws RemoteException {
      return false;
    }
    
    public boolean isFingerprintGestureDetectionAvailable() throws RemoteException {
      return false;
    }
    
    public boolean performAccessibilityAction(int param1Int1, long param1Long1, int param1Int2, Bundle param1Bundle, int param1Int3, IAccessibilityInteractionConnectionCallback param1IAccessibilityInteractionConnectionCallback, long param1Long2) throws RemoteException {
      return false;
    }
    
    public boolean performGlobalAction(int param1Int) throws RemoteException {
      return false;
    }
    
    public boolean resetMagnification(int param1Int, boolean param1Boolean) throws RemoteException {
      return false;
    }
    
    public void sendGesture(int param1Int, ParceledListSlice param1ParceledListSlice) throws RemoteException {}
    
    public void setGestureDetectionPassthroughRegion(int param1Int, Region param1Region) throws RemoteException {}
    
    public void setMagnificationCallbackEnabled(int param1Int, boolean param1Boolean) throws RemoteException {}
    
    public boolean setMagnificationScaleAndCenter(int param1Int, float param1Float1, float param1Float2, float param1Float3, boolean param1Boolean) throws RemoteException {
      return false;
    }
    
    public void setOnKeyEventResult(boolean param1Boolean, int param1Int) throws RemoteException {}
    
    public void setServiceInfo(AccessibilityServiceInfo param1AccessibilityServiceInfo) throws RemoteException {}
    
    public void setSoftKeyboardCallbackEnabled(boolean param1Boolean) throws RemoteException {}
    
    public boolean setSoftKeyboardShowMode(int param1Int) throws RemoteException {
      return false;
    }
    
    public void setTouchExplorationPassthroughRegion(int param1Int, Region param1Region) throws RemoteException {}
    
    public boolean switchToInputMethod(String param1String) throws RemoteException {
      return false;
    }
    
    public void takeScreenshot(int param1Int, RemoteCallback param1RemoteCallback) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IAccessibilityServiceConnection {
    private static final String DESCRIPTOR = "android.accessibilityservice.IAccessibilityServiceConnection";
    
    static final int TRANSACTION_disableSelf = 13;
    
    static final int TRANSACTION_dispatchGesture = 28;
    
    static final int TRANSACTION_findAccessibilityNodeInfoByAccessibilityId = 2;
    
    static final int TRANSACTION_findAccessibilityNodeInfosByText = 3;
    
    static final int TRANSACTION_findAccessibilityNodeInfosByViewId = 4;
    
    static final int TRANSACTION_findFocus = 5;
    
    static final int TRANSACTION_focusSearch = 6;
    
    static final int TRANSACTION_getMagnificationCenterX = 16;
    
    static final int TRANSACTION_getMagnificationCenterY = 17;
    
    static final int TRANSACTION_getMagnificationRegion = 18;
    
    static final int TRANSACTION_getMagnificationScale = 15;
    
    static final int TRANSACTION_getOverlayWindowToken = 30;
    
    static final int TRANSACTION_getServiceInfo = 10;
    
    static final int TRANSACTION_getSoftKeyboardShowMode = 23;
    
    static final int TRANSACTION_getSystemActions = 12;
    
    static final int TRANSACTION_getWindow = 8;
    
    static final int TRANSACTION_getWindowIdForLeashToken = 31;
    
    static final int TRANSACTION_getWindows = 9;
    
    static final int TRANSACTION_isAccessibilityButtonAvailable = 26;
    
    static final int TRANSACTION_isFingerprintGestureDetectionAvailable = 29;
    
    static final int TRANSACTION_performAccessibilityAction = 7;
    
    static final int TRANSACTION_performGlobalAction = 11;
    
    static final int TRANSACTION_resetMagnification = 19;
    
    static final int TRANSACTION_sendGesture = 27;
    
    static final int TRANSACTION_setGestureDetectionPassthroughRegion = 33;
    
    static final int TRANSACTION_setMagnificationCallbackEnabled = 21;
    
    static final int TRANSACTION_setMagnificationScaleAndCenter = 20;
    
    static final int TRANSACTION_setOnKeyEventResult = 14;
    
    static final int TRANSACTION_setServiceInfo = 1;
    
    static final int TRANSACTION_setSoftKeyboardCallbackEnabled = 24;
    
    static final int TRANSACTION_setSoftKeyboardShowMode = 22;
    
    static final int TRANSACTION_setTouchExplorationPassthroughRegion = 34;
    
    static final int TRANSACTION_switchToInputMethod = 25;
    
    static final int TRANSACTION_takeScreenshot = 32;
    
    public Stub() {
      attachInterface(this, "android.accessibilityservice.IAccessibilityServiceConnection");
    }
    
    public static IAccessibilityServiceConnection asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.accessibilityservice.IAccessibilityServiceConnection");
      return (iInterface != null && iInterface instanceof IAccessibilityServiceConnection) ? (IAccessibilityServiceConnection)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAccessibilityServiceConnection getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 34:
          return "setTouchExplorationPassthroughRegion";
        case 33:
          return "setGestureDetectionPassthroughRegion";
        case 32:
          return "takeScreenshot";
        case 31:
          return "getWindowIdForLeashToken";
        case 30:
          return "getOverlayWindowToken";
        case 29:
          return "isFingerprintGestureDetectionAvailable";
        case 28:
          return "dispatchGesture";
        case 27:
          return "sendGesture";
        case 26:
          return "isAccessibilityButtonAvailable";
        case 25:
          return "switchToInputMethod";
        case 24:
          return "setSoftKeyboardCallbackEnabled";
        case 23:
          return "getSoftKeyboardShowMode";
        case 22:
          return "setSoftKeyboardShowMode";
        case 21:
          return "setMagnificationCallbackEnabled";
        case 20:
          return "setMagnificationScaleAndCenter";
        case 19:
          return "resetMagnification";
        case 18:
          return "getMagnificationRegion";
        case 17:
          return "getMagnificationCenterY";
        case 16:
          return "getMagnificationCenterX";
        case 15:
          return "getMagnificationScale";
        case 14:
          return "setOnKeyEventResult";
        case 13:
          return "disableSelf";
        case 12:
          return "getSystemActions";
        case 11:
          return "performGlobalAction";
        case 10:
          return "getServiceInfo";
        case 9:
          return "getWindows";
        case 8:
          return "getWindow";
        case 7:
          return "performAccessibilityAction";
        case 6:
          return "focusSearch";
        case 5:
          return "findFocus";
        case 4:
          return "findAccessibilityNodeInfosByViewId";
        case 3:
          return "findAccessibilityNodeInfosByText";
        case 2:
          return "findAccessibilityNodeInfoByAccessibilityId";
        case 1:
          break;
      } 
      return "setServiceInfo";
    }
    
    public static boolean setDefaultImpl(IAccessibilityServiceConnection param1IAccessibilityServiceConnection) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAccessibilityServiceConnection != null) {
          Proxy.sDefaultImpl = param1IAccessibilityServiceConnection;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1598968902) {
        boolean bool6;
        int i1;
        boolean bool5;
        int n;
        boolean bool4;
        int m;
        boolean bool3;
        int k;
        boolean bool2;
        int j;
        boolean bool1;
        int i;
        IBinder iBinder;
        Region region;
        List<AccessibilityNodeInfo.AccessibilityAction> list;
        AccessibilityServiceInfo accessibilityServiceInfo;
        AccessibilityWindowInfo.WindowListSparseArray windowListSparseArray;
        AccessibilityWindowInfo accessibilityWindowInfo;
        String[] arrayOfString;
        ParceledListSlice parceledListSlice;
        IAccessibilityInteractionConnectionCallback iAccessibilityInteractionConnectionCallback;
        float f1;
        float f2;
        float f3;
        long l1;
        long l2;
        int i2;
        boolean bool7 = false;
        boolean bool8 = false;
        boolean bool9 = false;
        boolean bool10 = false;
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 34:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              Region region1 = (Region)Region.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            setTouchExplorationPassthroughRegion(param1Int1, (Region)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 33:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              Region region1 = (Region)Region.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            setGestureDetectionPassthroughRegion(param1Int1, (Region)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 32:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              RemoteCallback remoteCallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            takeScreenshot(param1Int1, (RemoteCallback)param1Parcel1);
            param1Parcel2.writeNoException();
            return true;
          case 31:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            param1Int1 = getWindowIdForLeashToken(param1Parcel1.readStrongBinder());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(param1Int1);
            return true;
          case 30:
            param1Parcel1.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            iBinder = getOverlayWindowToken(param1Parcel1.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeStrongBinder(iBinder);
            return true;
          case 29:
            iBinder.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            bool6 = isFingerprintGestureDetectionAvailable();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool6);
            return true;
          case 28:
            iBinder.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            i1 = iBinder.readInt();
            if (iBinder.readInt() != 0) {
              parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel((Parcel)iBinder);
            } else {
              parceledListSlice = null;
            } 
            dispatchGesture(i1, parceledListSlice, iBinder.readInt());
            param1Parcel2.writeNoException();
            return true;
          case 27:
            iBinder.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            i1 = iBinder.readInt();
            if (iBinder.readInt() != 0) {
              ParceledListSlice parceledListSlice1 = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel((Parcel)iBinder);
            } else {
              iBinder = null;
            } 
            sendGesture(i1, (ParceledListSlice)iBinder);
            param1Parcel2.writeNoException();
            return true;
          case 26:
            iBinder.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            bool5 = isAccessibilityButtonAvailable();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool5);
            return true;
          case 25:
            iBinder.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            bool5 = switchToInputMethod(iBinder.readString());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool5);
            return true;
          case 24:
            iBinder.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            if (iBinder.readInt() != 0)
              bool10 = true; 
            setSoftKeyboardCallbackEnabled(bool10);
            param1Parcel2.writeNoException();
            return true;
          case 23:
            iBinder.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            n = getSoftKeyboardShowMode();
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(n);
            return true;
          case 22:
            iBinder.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            bool4 = setSoftKeyboardShowMode(iBinder.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool4);
            return true;
          case 21:
            iBinder.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            m = iBinder.readInt();
            bool10 = bool7;
            if (iBinder.readInt() != 0)
              bool10 = true; 
            setMagnificationCallbackEnabled(m, bool10);
            param1Parcel2.writeNoException();
            return true;
          case 20:
            iBinder.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            m = iBinder.readInt();
            f1 = iBinder.readFloat();
            f2 = iBinder.readFloat();
            f3 = iBinder.readFloat();
            if (iBinder.readInt() != 0) {
              bool10 = true;
            } else {
              bool10 = false;
            } 
            bool3 = setMagnificationScaleAndCenter(m, f1, f2, f3, bool10);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool3);
            return true;
          case 19:
            iBinder.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            k = iBinder.readInt();
            bool10 = bool8;
            if (iBinder.readInt() != 0)
              bool10 = true; 
            bool2 = resetMagnification(k, bool10);
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 18:
            iBinder.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            region = getMagnificationRegion(iBinder.readInt());
            param1Parcel2.writeNoException();
            if (region != null) {
              param1Parcel2.writeInt(1);
              region.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 17:
            region.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            f3 = getMagnificationCenterY(region.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeFloat(f3);
            return true;
          case 16:
            region.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            f3 = getMagnificationCenterX(region.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeFloat(f3);
            return true;
          case 15:
            region.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            f3 = getMagnificationScale(region.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeFloat(f3);
            return true;
          case 14:
            region.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            bool10 = bool9;
            if (region.readInt() != 0)
              bool10 = true; 
            setOnKeyEventResult(bool10, region.readInt());
            return true;
          case 13:
            region.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            disableSelf();
            param1Parcel2.writeNoException();
            return true;
          case 12:
            region.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            list = getSystemActions();
            param1Parcel2.writeNoException();
            param1Parcel2.writeTypedList(list);
            return true;
          case 11:
            list.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            bool2 = performGlobalAction(list.readInt());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool2);
            return true;
          case 10:
            list.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            accessibilityServiceInfo = getServiceInfo();
            param1Parcel2.writeNoException();
            if (accessibilityServiceInfo != null) {
              param1Parcel2.writeInt(1);
              accessibilityServiceInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 9:
            accessibilityServiceInfo.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            windowListSparseArray = getWindows();
            param1Parcel2.writeNoException();
            if (windowListSparseArray != null) {
              param1Parcel2.writeInt(1);
              windowListSparseArray.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 8:
            windowListSparseArray.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            accessibilityWindowInfo = getWindow(windowListSparseArray.readInt());
            param1Parcel2.writeNoException();
            if (accessibilityWindowInfo != null) {
              param1Parcel2.writeInt(1);
              accessibilityWindowInfo.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          case 7:
            accessibilityWindowInfo.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            j = accessibilityWindowInfo.readInt();
            l1 = accessibilityWindowInfo.readLong();
            param1Int2 = accessibilityWindowInfo.readInt();
            if (accessibilityWindowInfo.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)accessibilityWindowInfo);
            } else {
              parceledListSlice = null;
            } 
            bool1 = performAccessibilityAction(j, l1, param1Int2, (Bundle)parceledListSlice, accessibilityWindowInfo.readInt(), IAccessibilityInteractionConnectionCallback.Stub.asInterface(accessibilityWindowInfo.readStrongBinder()), accessibilityWindowInfo.readLong());
            param1Parcel2.writeNoException();
            param1Parcel2.writeInt(bool1);
            return true;
          case 6:
            accessibilityWindowInfo.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            arrayOfString = focusSearch(accessibilityWindowInfo.readInt(), accessibilityWindowInfo.readLong(), accessibilityWindowInfo.readInt(), accessibilityWindowInfo.readInt(), IAccessibilityInteractionConnectionCallback.Stub.asInterface(accessibilityWindowInfo.readStrongBinder()), accessibilityWindowInfo.readLong());
            param1Parcel2.writeNoException();
            param1Parcel2.writeStringArray(arrayOfString);
            return true;
          case 5:
            arrayOfString.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            arrayOfString = findFocus(arrayOfString.readInt(), arrayOfString.readLong(), arrayOfString.readInt(), arrayOfString.readInt(), IAccessibilityInteractionConnectionCallback.Stub.asInterface(arrayOfString.readStrongBinder()), arrayOfString.readLong());
            param1Parcel2.writeNoException();
            param1Parcel2.writeStringArray(arrayOfString);
            return true;
          case 4:
            arrayOfString.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            arrayOfString = findAccessibilityNodeInfosByViewId(arrayOfString.readInt(), arrayOfString.readLong(), arrayOfString.readString(), arrayOfString.readInt(), IAccessibilityInteractionConnectionCallback.Stub.asInterface(arrayOfString.readStrongBinder()), arrayOfString.readLong());
            param1Parcel2.writeNoException();
            param1Parcel2.writeStringArray(arrayOfString);
            return true;
          case 3:
            arrayOfString.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            arrayOfString = findAccessibilityNodeInfosByText(arrayOfString.readInt(), arrayOfString.readLong(), arrayOfString.readString(), arrayOfString.readInt(), IAccessibilityInteractionConnectionCallback.Stub.asInterface(arrayOfString.readStrongBinder()), arrayOfString.readLong());
            param1Parcel2.writeNoException();
            param1Parcel2.writeStringArray(arrayOfString);
            return true;
          case 2:
            arrayOfString.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
            i = arrayOfString.readInt();
            l2 = arrayOfString.readLong();
            i2 = arrayOfString.readInt();
            iAccessibilityInteractionConnectionCallback = IAccessibilityInteractionConnectionCallback.Stub.asInterface(arrayOfString.readStrongBinder());
            param1Int2 = arrayOfString.readInt();
            l1 = arrayOfString.readLong();
            if (arrayOfString.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel((Parcel)arrayOfString);
            } else {
              arrayOfString = null;
            } 
            arrayOfString = findAccessibilityNodeInfoByAccessibilityId(i, l2, i2, iAccessibilityInteractionConnectionCallback, param1Int2, l1, (Bundle)arrayOfString);
            param1Parcel2.writeNoException();
            param1Parcel2.writeStringArray(arrayOfString);
            return true;
          case 1:
            break;
        } 
        arrayOfString.enforceInterface("android.accessibilityservice.IAccessibilityServiceConnection");
        if (arrayOfString.readInt() != 0) {
          AccessibilityServiceInfo accessibilityServiceInfo1 = (AccessibilityServiceInfo)AccessibilityServiceInfo.CREATOR.createFromParcel((Parcel)arrayOfString);
        } else {
          arrayOfString = null;
        } 
        setServiceInfo((AccessibilityServiceInfo)arrayOfString);
        param1Parcel2.writeNoException();
        return true;
      } 
      param1Parcel2.writeString("android.accessibilityservice.IAccessibilityServiceConnection");
      return true;
    }
    
    private static class Proxy implements IAccessibilityServiceConnection {
      public static IAccessibilityServiceConnection sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
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
      
      public void dispatchGesture(int param2Int1, ParceledListSlice param2ParceledListSlice, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int1);
          if (param2ParceledListSlice != null) {
            parcel1.writeInt(1);
            param2ParceledListSlice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            IAccessibilityServiceConnection.Stub.getDefaultImpl().dispatchGesture(param2Int1, param2ParceledListSlice, param2Int2);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String[] findAccessibilityNodeInfoByAccessibilityId(int param2Int1, long param2Long1, int param2Int2, IAccessibilityInteractionConnectionCallback param2IAccessibilityInteractionConnectionCallback, int param2Int3, long param2Long2, Bundle param2Bundle) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          try {
            parcel1.writeInt(param2Int1);
            parcel1.writeLong(param2Long1);
            try {
              IBinder iBinder;
              parcel1.writeInt(param2Int2);
              if (param2IAccessibilityInteractionConnectionCallback != null) {
                iBinder = param2IAccessibilityInteractionConnectionCallback.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              parcel1.writeInt(param2Int3);
              parcel1.writeLong(param2Long2);
              if (param2Bundle != null) {
                parcel1.writeInt(1);
                param2Bundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().findAccessibilityNodeInfoByAccessibilityId(param2Int1, param2Long1, param2Int2, param2IAccessibilityInteractionConnectionCallback, param2Int3, param2Long2, param2Bundle);
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
        throw param2IAccessibilityInteractionConnectionCallback;
      }
      
      public String[] findAccessibilityNodeInfosByText(int param2Int1, long param2Long1, String param2String, int param2Int2, IAccessibilityInteractionConnectionCallback param2IAccessibilityInteractionConnectionCallback, long param2Long2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          try {
            parcel1.writeInt(param2Int1);
            try {
              parcel1.writeLong(param2Long1);
              try {
                IBinder iBinder;
                parcel1.writeString(param2String);
                parcel1.writeInt(param2Int2);
                if (param2IAccessibilityInteractionConnectionCallback != null) {
                  iBinder = param2IAccessibilityInteractionConnectionCallback.asBinder();
                } else {
                  iBinder = null;
                } 
                parcel1.writeStrongBinder(iBinder);
                parcel1.writeLong(param2Long2);
                if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                  String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().findAccessibilityNodeInfosByText(param2Int1, param2Long1, param2String, param2Int2, param2IAccessibilityInteractionConnectionCallback, param2Long2);
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
        throw param2String;
      }
      
      public String[] findAccessibilityNodeInfosByViewId(int param2Int1, long param2Long1, String param2String, int param2Int2, IAccessibilityInteractionConnectionCallback param2IAccessibilityInteractionConnectionCallback, long param2Long2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          try {
            parcel1.writeInt(param2Int1);
            try {
              parcel1.writeLong(param2Long1);
              try {
                IBinder iBinder;
                parcel1.writeString(param2String);
                parcel1.writeInt(param2Int2);
                if (param2IAccessibilityInteractionConnectionCallback != null) {
                  iBinder = param2IAccessibilityInteractionConnectionCallback.asBinder();
                } else {
                  iBinder = null;
                } 
                parcel1.writeStrongBinder(iBinder);
                parcel1.writeLong(param2Long2);
                if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                  String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().findAccessibilityNodeInfosByViewId(param2Int1, param2Long1, param2String, param2Int2, param2IAccessibilityInteractionConnectionCallback, param2Long2);
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
        throw param2String;
      }
      
      public String[] findFocus(int param2Int1, long param2Long1, int param2Int2, int param2Int3, IAccessibilityInteractionConnectionCallback param2IAccessibilityInteractionConnectionCallback, long param2Long2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          try {
            parcel1.writeInt(param2Int1);
            try {
              parcel1.writeLong(param2Long1);
              try {
                IBinder iBinder;
                parcel1.writeInt(param2Int2);
                parcel1.writeInt(param2Int3);
                if (param2IAccessibilityInteractionConnectionCallback != null) {
                  iBinder = param2IAccessibilityInteractionConnectionCallback.asBinder();
                } else {
                  iBinder = null;
                } 
                parcel1.writeStrongBinder(iBinder);
                parcel1.writeLong(param2Long2);
                if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                  String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().findFocus(param2Int1, param2Long1, param2Int2, param2Int3, param2IAccessibilityInteractionConnectionCallback, param2Long2);
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
        throw param2IAccessibilityInteractionConnectionCallback;
      }
      
      public String[] focusSearch(int param2Int1, long param2Long1, int param2Int2, int param2Int3, IAccessibilityInteractionConnectionCallback param2IAccessibilityInteractionConnectionCallback, long param2Long2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          try {
            parcel1.writeInt(param2Int1);
            try {
              parcel1.writeLong(param2Long1);
              try {
                IBinder iBinder;
                parcel1.writeInt(param2Int2);
                parcel1.writeInt(param2Int3);
                if (param2IAccessibilityInteractionConnectionCallback != null) {
                  iBinder = param2IAccessibilityInteractionConnectionCallback.asBinder();
                } else {
                  iBinder = null;
                } 
                parcel1.writeStrongBinder(iBinder);
                parcel1.writeLong(param2Long2);
                if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                  String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().focusSearch(param2Int1, param2Long1, param2Int2, param2Int3, param2IAccessibilityInteractionConnectionCallback, param2Long2);
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
        throw param2IAccessibilityInteractionConnectionCallback;
      }
      
      public String getInterfaceDescriptor() {
        return "android.accessibilityservice.IAccessibilityServiceConnection";
      }
      
      public float getMagnificationCenterX(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
            return IAccessibilityServiceConnection.Stub.getDefaultImpl().getMagnificationCenterX(param2Int); 
          parcel2.readException();
          return parcel2.readFloat();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public float getMagnificationCenterY(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
            return IAccessibilityServiceConnection.Stub.getDefaultImpl().getMagnificationCenterY(param2Int); 
          parcel2.readException();
          return parcel2.readFloat();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public Region getMagnificationRegion(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          Region region;
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            region = IAccessibilityServiceConnection.Stub.getDefaultImpl().getMagnificationRegion(param2Int);
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
      
      public float getMagnificationScale(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
            return IAccessibilityServiceConnection.Stub.getDefaultImpl().getMagnificationScale(param2Int); 
          parcel2.readException();
          return parcel2.readFloat();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public IBinder getOverlayWindowToken(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
            return IAccessibilityServiceConnection.Stub.getDefaultImpl().getOverlayWindowToken(param2Int); 
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
      
      public AccessibilityWindowInfo getWindow(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          AccessibilityWindowInfo accessibilityWindowInfo;
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            accessibilityWindowInfo = IAccessibilityServiceConnection.Stub.getDefaultImpl().getWindow(param2Int);
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
      
      public int getWindowIdForLeashToken(IBinder param2IBinder) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
            return IAccessibilityServiceConnection.Stub.getDefaultImpl().getWindowIdForLeashToken(param2IBinder); 
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
      
      public boolean performAccessibilityAction(int param2Int1, long param2Long1, int param2Int2, Bundle param2Bundle, int param2Int3, IAccessibilityInteractionConnectionCallback param2IAccessibilityInteractionConnectionCallback, long param2Long2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          try {
            parcel1.writeInt(param2Int1);
            parcel1.writeLong(param2Long1);
            try {
              IBinder iBinder;
              parcel1.writeInt(param2Int2);
              boolean bool = true;
              if (param2Bundle != null) {
                parcel1.writeInt(1);
                param2Bundle.writeToParcel(parcel1, 0);
              } else {
                parcel1.writeInt(0);
              } 
              parcel1.writeInt(param2Int3);
              if (param2IAccessibilityInteractionConnectionCallback != null) {
                iBinder = param2IAccessibilityInteractionConnectionCallback.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              parcel1.writeLong(param2Long2);
              if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().performAccessibilityAction(param2Int1, param2Long1, param2Int2, param2Bundle, param2Int3, param2IAccessibilityInteractionConnectionCallback, param2Long2);
                parcel2.recycle();
                parcel1.recycle();
                return bool;
              } 
              parcel2.readException();
              param2Int1 = parcel2.readInt();
              if (param2Int1 == 0)
                bool = false; 
              parcel2.recycle();
              parcel1.recycle();
              return bool;
            } finally {}
          } finally {}
        } finally {}
        parcel2.recycle();
        parcel1.recycle();
        throw param2Bundle;
      }
      
      public boolean performGlobalAction(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(11, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().performGlobalAction(param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean resetMagnification(int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool2;
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          boolean bool1 = true;
          if (param2Boolean) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            param2Boolean = IAccessibilityServiceConnection.Stub.getDefaultImpl().resetMagnification(param2Int, param2Boolean);
            return param2Boolean;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0) {
            param2Boolean = bool1;
          } else {
            param2Boolean = false;
          } 
          return param2Boolean;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void sendGesture(int param2Int, ParceledListSlice param2ParceledListSlice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          if (param2ParceledListSlice != null) {
            parcel1.writeInt(1);
            param2ParceledListSlice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            IAccessibilityServiceConnection.Stub.getDefaultImpl().sendGesture(param2Int, param2ParceledListSlice);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setGestureDetectionPassthroughRegion(int param2Int, Region param2Region) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          if (param2Region != null) {
            parcel1.writeInt(1);
            param2Region.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            IAccessibilityServiceConnection.Stub.getDefaultImpl().setGestureDetectionPassthroughRegion(param2Int, param2Region);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setMagnificationCallbackEnabled(int param2Int, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            IAccessibilityServiceConnection.Stub.getDefaultImpl().setMagnificationCallbackEnabled(param2Int, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean setMagnificationScaleAndCenter(int param2Int, float param2Float1, float param2Float2, float param2Float3, boolean param2Boolean) throws RemoteException {
        Exception exception;
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          try {
            parcel1.writeInt(param2Int);
            try {
              parcel1.writeFloat(param2Float1);
              try {
                parcel1.writeFloat(param2Float2);
                try {
                  boolean bool2;
                  parcel1.writeFloat(param2Float3);
                  boolean bool1 = true;
                  if (param2Boolean) {
                    bool2 = true;
                  } else {
                    bool2 = false;
                  } 
                  parcel1.writeInt(bool2);
                  try {
                    if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                      param2Boolean = IAccessibilityServiceConnection.Stub.getDefaultImpl().setMagnificationScaleAndCenter(param2Int, param2Float1, param2Float2, param2Float3, param2Boolean);
                      parcel2.recycle();
                      parcel1.recycle();
                      return param2Boolean;
                    } 
                    parcel2.readException();
                    param2Int = parcel2.readInt();
                    if (param2Int != 0) {
                      param2Boolean = bool1;
                    } else {
                      param2Boolean = false;
                    } 
                    parcel2.recycle();
                    parcel1.recycle();
                    return param2Boolean;
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
      
      public void setOnKeyEventResult(boolean param2Boolean, int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(14, parcel, null, 1) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            IAccessibilityServiceConnection.Stub.getDefaultImpl().setOnKeyEventResult(param2Boolean, param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void setServiceInfo(AccessibilityServiceInfo param2AccessibilityServiceInfo) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          if (param2AccessibilityServiceInfo != null) {
            parcel1.writeInt(1);
            param2AccessibilityServiceInfo.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            IAccessibilityServiceConnection.Stub.getDefaultImpl().setServiceInfo(param2AccessibilityServiceInfo);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setSoftKeyboardCallbackEnabled(boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            IAccessibilityServiceConnection.Stub.getDefaultImpl().setSoftKeyboardCallbackEnabled(param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean setSoftKeyboardShowMode(int param2Int) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(22, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().setSoftKeyboardShowMode(param2Int);
            return bool;
          } 
          parcel2.readException();
          param2Int = parcel2.readInt();
          if (param2Int != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void setTouchExplorationPassthroughRegion(int param2Int, Region param2Region) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          if (param2Region != null) {
            parcel1.writeInt(1);
            param2Region.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            IAccessibilityServiceConnection.Stub.getDefaultImpl().setTouchExplorationPassthroughRegion(param2Int, param2Region);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public boolean switchToInputMethod(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeString(param2String);
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(25, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().switchToInputMethod(param2String);
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
      
      public void takeScreenshot(int param2Int, RemoteCallback param2RemoteCallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
          parcel1.writeInt(param2Int);
          if (param2RemoteCallback != null) {
            parcel1.writeInt(1);
            param2RemoteCallback.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
            IAccessibilityServiceConnection.Stub.getDefaultImpl().takeScreenshot(param2Int, param2RemoteCallback);
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
  }
  
  private static class Proxy implements IAccessibilityServiceConnection {
    public static IAccessibilityServiceConnection sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
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
    
    public void dispatchGesture(int param1Int1, ParceledListSlice param1ParceledListSlice, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int1);
        if (param1ParceledListSlice != null) {
          parcel1.writeInt(1);
          param1ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceConnection.Stub.getDefaultImpl().dispatchGesture(param1Int1, param1ParceledListSlice, param1Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String[] findAccessibilityNodeInfoByAccessibilityId(int param1Int1, long param1Long1, int param1Int2, IAccessibilityInteractionConnectionCallback param1IAccessibilityInteractionConnectionCallback, int param1Int3, long param1Long2, Bundle param1Bundle) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        try {
          parcel1.writeInt(param1Int1);
          parcel1.writeLong(param1Long1);
          try {
            IBinder iBinder;
            parcel1.writeInt(param1Int2);
            if (param1IAccessibilityInteractionConnectionCallback != null) {
              iBinder = param1IAccessibilityInteractionConnectionCallback.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            parcel1.writeInt(param1Int3);
            parcel1.writeLong(param1Long2);
            if (param1Bundle != null) {
              parcel1.writeInt(1);
              param1Bundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
              String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().findAccessibilityNodeInfoByAccessibilityId(param1Int1, param1Long1, param1Int2, param1IAccessibilityInteractionConnectionCallback, param1Int3, param1Long2, param1Bundle);
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
      throw param1IAccessibilityInteractionConnectionCallback;
    }
    
    public String[] findAccessibilityNodeInfosByText(int param1Int1, long param1Long1, String param1String, int param1Int2, IAccessibilityInteractionConnectionCallback param1IAccessibilityInteractionConnectionCallback, long param1Long2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        try {
          parcel1.writeInt(param1Int1);
          try {
            parcel1.writeLong(param1Long1);
            try {
              IBinder iBinder;
              parcel1.writeString(param1String);
              parcel1.writeInt(param1Int2);
              if (param1IAccessibilityInteractionConnectionCallback != null) {
                iBinder = param1IAccessibilityInteractionConnectionCallback.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              parcel1.writeLong(param1Long2);
              if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().findAccessibilityNodeInfosByText(param1Int1, param1Long1, param1String, param1Int2, param1IAccessibilityInteractionConnectionCallback, param1Long2);
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
      throw param1String;
    }
    
    public String[] findAccessibilityNodeInfosByViewId(int param1Int1, long param1Long1, String param1String, int param1Int2, IAccessibilityInteractionConnectionCallback param1IAccessibilityInteractionConnectionCallback, long param1Long2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        try {
          parcel1.writeInt(param1Int1);
          try {
            parcel1.writeLong(param1Long1);
            try {
              IBinder iBinder;
              parcel1.writeString(param1String);
              parcel1.writeInt(param1Int2);
              if (param1IAccessibilityInteractionConnectionCallback != null) {
                iBinder = param1IAccessibilityInteractionConnectionCallback.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              parcel1.writeLong(param1Long2);
              if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().findAccessibilityNodeInfosByViewId(param1Int1, param1Long1, param1String, param1Int2, param1IAccessibilityInteractionConnectionCallback, param1Long2);
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
      throw param1String;
    }
    
    public String[] findFocus(int param1Int1, long param1Long1, int param1Int2, int param1Int3, IAccessibilityInteractionConnectionCallback param1IAccessibilityInteractionConnectionCallback, long param1Long2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        try {
          parcel1.writeInt(param1Int1);
          try {
            parcel1.writeLong(param1Long1);
            try {
              IBinder iBinder;
              parcel1.writeInt(param1Int2);
              parcel1.writeInt(param1Int3);
              if (param1IAccessibilityInteractionConnectionCallback != null) {
                iBinder = param1IAccessibilityInteractionConnectionCallback.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              parcel1.writeLong(param1Long2);
              if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().findFocus(param1Int1, param1Long1, param1Int2, param1Int3, param1IAccessibilityInteractionConnectionCallback, param1Long2);
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
      throw param1IAccessibilityInteractionConnectionCallback;
    }
    
    public String[] focusSearch(int param1Int1, long param1Long1, int param1Int2, int param1Int3, IAccessibilityInteractionConnectionCallback param1IAccessibilityInteractionConnectionCallback, long param1Long2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        try {
          parcel1.writeInt(param1Int1);
          try {
            parcel1.writeLong(param1Long1);
            try {
              IBinder iBinder;
              parcel1.writeInt(param1Int2);
              parcel1.writeInt(param1Int3);
              if (param1IAccessibilityInteractionConnectionCallback != null) {
                iBinder = param1IAccessibilityInteractionConnectionCallback.asBinder();
              } else {
                iBinder = null;
              } 
              parcel1.writeStrongBinder(iBinder);
              parcel1.writeLong(param1Long2);
              if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                String[] arrayOfString1 = IAccessibilityServiceConnection.Stub.getDefaultImpl().focusSearch(param1Int1, param1Long1, param1Int2, param1Int3, param1IAccessibilityInteractionConnectionCallback, param1Long2);
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
      throw param1IAccessibilityInteractionConnectionCallback;
    }
    
    public String getInterfaceDescriptor() {
      return "android.accessibilityservice.IAccessibilityServiceConnection";
    }
    
    public float getMagnificationCenterX(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
          return IAccessibilityServiceConnection.Stub.getDefaultImpl().getMagnificationCenterX(param1Int); 
        parcel2.readException();
        return parcel2.readFloat();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public float getMagnificationCenterY(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
          return IAccessibilityServiceConnection.Stub.getDefaultImpl().getMagnificationCenterY(param1Int); 
        parcel2.readException();
        return parcel2.readFloat();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Region getMagnificationRegion(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Region region;
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          region = IAccessibilityServiceConnection.Stub.getDefaultImpl().getMagnificationRegion(param1Int);
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
    
    public float getMagnificationScale(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
          return IAccessibilityServiceConnection.Stub.getDefaultImpl().getMagnificationScale(param1Int); 
        parcel2.readException();
        return parcel2.readFloat();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder getOverlayWindowToken(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
          return IAccessibilityServiceConnection.Stub.getDefaultImpl().getOverlayWindowToken(param1Int); 
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
    
    public AccessibilityWindowInfo getWindow(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        AccessibilityWindowInfo accessibilityWindowInfo;
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          accessibilityWindowInfo = IAccessibilityServiceConnection.Stub.getDefaultImpl().getWindow(param1Int);
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
    
    public int getWindowIdForLeashToken(IBinder param1IBinder) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null)
          return IAccessibilityServiceConnection.Stub.getDefaultImpl().getWindowIdForLeashToken(param1IBinder); 
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
    
    public boolean performAccessibilityAction(int param1Int1, long param1Long1, int param1Int2, Bundle param1Bundle, int param1Int3, IAccessibilityInteractionConnectionCallback param1IAccessibilityInteractionConnectionCallback, long param1Long2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        try {
          parcel1.writeInt(param1Int1);
          parcel1.writeLong(param1Long1);
          try {
            IBinder iBinder;
            parcel1.writeInt(param1Int2);
            boolean bool = true;
            if (param1Bundle != null) {
              parcel1.writeInt(1);
              param1Bundle.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            parcel1.writeInt(param1Int3);
            if (param1IAccessibilityInteractionConnectionCallback != null) {
              iBinder = param1IAccessibilityInteractionConnectionCallback.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            parcel1.writeLong(param1Long2);
            if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
              bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().performAccessibilityAction(param1Int1, param1Long1, param1Int2, param1Bundle, param1Int3, param1IAccessibilityInteractionConnectionCallback, param1Long2);
              parcel2.recycle();
              parcel1.recycle();
              return bool;
            } 
            parcel2.readException();
            param1Int1 = parcel2.readInt();
            if (param1Int1 == 0)
              bool = false; 
            parcel2.recycle();
            parcel1.recycle();
            return bool;
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param1Bundle;
    }
    
    public boolean performGlobalAction(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(11, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().performGlobalAction(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean resetMagnification(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        boolean bool1 = true;
        if (param1Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          param1Boolean = IAccessibilityServiceConnection.Stub.getDefaultImpl().resetMagnification(param1Int, param1Boolean);
          return param1Boolean;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0) {
          param1Boolean = bool1;
        } else {
          param1Boolean = false;
        } 
        return param1Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sendGesture(int param1Int, ParceledListSlice param1ParceledListSlice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        if (param1ParceledListSlice != null) {
          parcel1.writeInt(1);
          param1ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceConnection.Stub.getDefaultImpl().sendGesture(param1Int, param1ParceledListSlice);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setGestureDetectionPassthroughRegion(int param1Int, Region param1Region) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        if (param1Region != null) {
          parcel1.writeInt(1);
          param1Region.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceConnection.Stub.getDefaultImpl().setGestureDetectionPassthroughRegion(param1Int, param1Region);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setMagnificationCallbackEnabled(int param1Int, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceConnection.Stub.getDefaultImpl().setMagnificationCallbackEnabled(param1Int, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setMagnificationScaleAndCenter(int param1Int, float param1Float1, float param1Float2, float param1Float3, boolean param1Boolean) throws RemoteException {
      Exception exception;
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        try {
          parcel1.writeInt(param1Int);
          try {
            parcel1.writeFloat(param1Float1);
            try {
              parcel1.writeFloat(param1Float2);
              try {
                boolean bool2;
                parcel1.writeFloat(param1Float3);
                boolean bool1 = true;
                if (param1Boolean) {
                  bool2 = true;
                } else {
                  bool2 = false;
                } 
                parcel1.writeInt(bool2);
                try {
                  if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
                    param1Boolean = IAccessibilityServiceConnection.Stub.getDefaultImpl().setMagnificationScaleAndCenter(param1Int, param1Float1, param1Float2, param1Float3, param1Boolean);
                    parcel2.recycle();
                    parcel1.recycle();
                    return param1Boolean;
                  } 
                  parcel2.readException();
                  param1Int = parcel2.readInt();
                  if (param1Int != 0) {
                    param1Boolean = bool1;
                  } else {
                    param1Boolean = false;
                  } 
                  parcel2.recycle();
                  parcel1.recycle();
                  return param1Boolean;
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
    
    public void setOnKeyEventResult(boolean param1Boolean, int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(14, parcel, null, 1) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceConnection.Stub.getDefaultImpl().setOnKeyEventResult(param1Boolean, param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setServiceInfo(AccessibilityServiceInfo param1AccessibilityServiceInfo) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        if (param1AccessibilityServiceInfo != null) {
          parcel1.writeInt(1);
          param1AccessibilityServiceInfo.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceConnection.Stub.getDefaultImpl().setServiceInfo(param1AccessibilityServiceInfo);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setSoftKeyboardCallbackEnabled(boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceConnection.Stub.getDefaultImpl().setSoftKeyboardCallbackEnabled(param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setSoftKeyboardShowMode(int param1Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(22, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().setSoftKeyboardShowMode(param1Int);
          return bool;
        } 
        parcel2.readException();
        param1Int = parcel2.readInt();
        if (param1Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setTouchExplorationPassthroughRegion(int param1Int, Region param1Region) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        if (param1Region != null) {
          parcel1.writeInt(1);
          param1Region.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceConnection.Stub.getDefaultImpl().setTouchExplorationPassthroughRegion(param1Int, param1Region);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean switchToInputMethod(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeString(param1String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(25, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          bool = IAccessibilityServiceConnection.Stub.getDefaultImpl().switchToInputMethod(param1String);
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
    
    public void takeScreenshot(int param1Int, RemoteCallback param1RemoteCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.accessibilityservice.IAccessibilityServiceConnection");
        parcel1.writeInt(param1Int);
        if (param1RemoteCallback != null) {
          parcel1.writeInt(1);
          param1RemoteCallback.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IAccessibilityServiceConnection.Stub.getDefaultImpl() != null) {
          IAccessibilityServiceConnection.Stub.getDefaultImpl().takeScreenshot(param1Int, param1RemoteCallback);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/IAccessibilityServiceConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */