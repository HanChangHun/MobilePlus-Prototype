package android.hardware.display;

import android.content.pm.ParceledListSlice;
import android.graphics.Point;
import android.media.projection.IMediaProjection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.DisplayInfo;
import android.view.Surface;

public abstract class Stub extends Binder implements IDisplayManager {
  private static final String DESCRIPTOR = "android.hardware.display.IDisplayManager";
  
  static final int TRANSACTION_connectWifiDisplay = 7;
  
  static final int TRANSACTION_createVirtualDisplay = 15;
  
  static final int TRANSACTION_disconnectWifiDisplay = 8;
  
  static final int TRANSACTION_forgetWifiDisplay = 10;
  
  static final int TRANSACTION_getAmbientBrightnessStats = 22;
  
  static final int TRANSACTION_getBrightnessConfigurationForUser = 24;
  
  static final int TRANSACTION_getBrightnessEvents = 21;
  
  static final int TRANSACTION_getDefaultBrightnessConfiguration = 25;
  
  static final int TRANSACTION_getDisplayIds = 2;
  
  static final int TRANSACTION_getDisplayInfo = 1;
  
  static final int TRANSACTION_getMinimumBrightnessCurve = 29;
  
  static final int TRANSACTION_getPreferredWideGamutColorSpaceId = 30;
  
  static final int TRANSACTION_getStableDisplaySize = 20;
  
  static final int TRANSACTION_getWifiDisplayStatus = 13;
  
  static final int TRANSACTION_isMinimalPostProcessingRequested = 26;
  
  static final int TRANSACTION_isUidPresentOnDisplay = 3;
  
  static final int TRANSACTION_pauseWifiDisplay = 11;
  
  static final int TRANSACTION_registerCallback = 4;
  
  static final int TRANSACTION_releaseVirtualDisplay = 18;
  
  static final int TRANSACTION_renameWifiDisplay = 9;
  
  static final int TRANSACTION_requestColorMode = 14;
  
  static final int TRANSACTION_resizeVirtualDisplay = 16;
  
  static final int TRANSACTION_resumeWifiDisplay = 12;
  
  static final int TRANSACTION_setBrightnessConfigurationForUser = 23;
  
  static final int TRANSACTION_setTemporaryAutoBrightnessAdjustment = 28;
  
  static final int TRANSACTION_setTemporaryBrightness = 27;
  
  static final int TRANSACTION_setVirtualDisplayState = 19;
  
  static final int TRANSACTION_setVirtualDisplaySurface = 17;
  
  static final int TRANSACTION_startWifiDisplayScan = 5;
  
  static final int TRANSACTION_stopWifiDisplayScan = 6;
  
  public Stub() {
    attachInterface(this, "android.hardware.display.IDisplayManager");
  }
  
  public static IDisplayManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.display.IDisplayManager");
    return (iInterface != null && iInterface instanceof IDisplayManager) ? (IDisplayManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IDisplayManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 30:
        return "getPreferredWideGamutColorSpaceId";
      case 29:
        return "getMinimumBrightnessCurve";
      case 28:
        return "setTemporaryAutoBrightnessAdjustment";
      case 27:
        return "setTemporaryBrightness";
      case 26:
        return "isMinimalPostProcessingRequested";
      case 25:
        return "getDefaultBrightnessConfiguration";
      case 24:
        return "getBrightnessConfigurationForUser";
      case 23:
        return "setBrightnessConfigurationForUser";
      case 22:
        return "getAmbientBrightnessStats";
      case 21:
        return "getBrightnessEvents";
      case 20:
        return "getStableDisplaySize";
      case 19:
        return "setVirtualDisplayState";
      case 18:
        return "releaseVirtualDisplay";
      case 17:
        return "setVirtualDisplaySurface";
      case 16:
        return "resizeVirtualDisplay";
      case 15:
        return "createVirtualDisplay";
      case 14:
        return "requestColorMode";
      case 13:
        return "getWifiDisplayStatus";
      case 12:
        return "resumeWifiDisplay";
      case 11:
        return "pauseWifiDisplay";
      case 10:
        return "forgetWifiDisplay";
      case 9:
        return "renameWifiDisplay";
      case 8:
        return "disconnectWifiDisplay";
      case 7:
        return "connectWifiDisplay";
      case 6:
        return "stopWifiDisplayScan";
      case 5:
        return "startWifiDisplayScan";
      case 4:
        return "registerCallback";
      case 3:
        return "isUidPresentOnDisplay";
      case 2:
        return "getDisplayIds";
      case 1:
        break;
    } 
    return "getDisplayInfo";
  }
  
  public static boolean setDefaultImpl(IDisplayManager paramIDisplayManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIDisplayManager != null) {
        Proxy.sDefaultImpl = paramIDisplayManager;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1598968902) {
      boolean bool2;
      int i;
      boolean bool1;
      Curve curve;
      BrightnessConfiguration brightnessConfiguration1;
      ParceledListSlice parceledListSlice;
      Point point;
      WifiDisplayStatus wifiDisplayStatus;
      int[] arrayOfInt;
      BrightnessConfiguration brightnessConfiguration2;
      IVirtualDisplayCallback iVirtualDisplayCallback;
      boolean bool = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 30:
          paramParcel1.enforceInterface("android.hardware.display.IDisplayManager");
          paramInt1 = getPreferredWideGamutColorSpaceId();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 29:
          paramParcel1.enforceInterface("android.hardware.display.IDisplayManager");
          curve = getMinimumBrightnessCurve();
          paramParcel2.writeNoException();
          if (curve != null) {
            paramParcel2.writeInt(1);
            curve.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 28:
          curve.enforceInterface("android.hardware.display.IDisplayManager");
          setTemporaryAutoBrightnessAdjustment(curve.readFloat());
          paramParcel2.writeNoException();
          return true;
        case 27:
          curve.enforceInterface("android.hardware.display.IDisplayManager");
          setTemporaryBrightness(curve.readFloat());
          paramParcel2.writeNoException();
          return true;
        case 26:
          curve.enforceInterface("android.hardware.display.IDisplayManager");
          bool2 = isMinimalPostProcessingRequested(curve.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 25:
          curve.enforceInterface("android.hardware.display.IDisplayManager");
          brightnessConfiguration1 = getDefaultBrightnessConfiguration();
          paramParcel2.writeNoException();
          if (brightnessConfiguration1 != null) {
            paramParcel2.writeInt(1);
            brightnessConfiguration1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 24:
          brightnessConfiguration1.enforceInterface("android.hardware.display.IDisplayManager");
          brightnessConfiguration1 = getBrightnessConfigurationForUser(brightnessConfiguration1.readInt());
          paramParcel2.writeNoException();
          if (brightnessConfiguration1 != null) {
            paramParcel2.writeInt(1);
            brightnessConfiguration1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 23:
          brightnessConfiguration1.enforceInterface("android.hardware.display.IDisplayManager");
          if (brightnessConfiguration1.readInt() != 0) {
            brightnessConfiguration2 = (BrightnessConfiguration)BrightnessConfiguration.CREATOR.createFromParcel((Parcel)brightnessConfiguration1);
          } else {
            brightnessConfiguration2 = null;
          } 
          setBrightnessConfigurationForUser(brightnessConfiguration2, brightnessConfiguration1.readInt(), brightnessConfiguration1.readString());
          paramParcel2.writeNoException();
          return true;
        case 22:
          brightnessConfiguration1.enforceInterface("android.hardware.display.IDisplayManager");
          parceledListSlice = getAmbientBrightnessStats();
          paramParcel2.writeNoException();
          if (parceledListSlice != null) {
            paramParcel2.writeInt(1);
            parceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 21:
          parceledListSlice.enforceInterface("android.hardware.display.IDisplayManager");
          parceledListSlice = getBrightnessEvents(parceledListSlice.readString());
          paramParcel2.writeNoException();
          if (parceledListSlice != null) {
            paramParcel2.writeInt(1);
            parceledListSlice.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 20:
          parceledListSlice.enforceInterface("android.hardware.display.IDisplayManager");
          point = getStableDisplaySize();
          paramParcel2.writeNoException();
          if (point != null) {
            paramParcel2.writeInt(1);
            point.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 19:
          point.enforceInterface("android.hardware.display.IDisplayManager");
          iVirtualDisplayCallback = IVirtualDisplayCallback.Stub.asInterface(point.readStrongBinder());
          if (point.readInt() != 0)
            bool = true; 
          setVirtualDisplayState(iVirtualDisplayCallback, bool);
          paramParcel2.writeNoException();
          return true;
        case 18:
          point.enforceInterface("android.hardware.display.IDisplayManager");
          releaseVirtualDisplay(IVirtualDisplayCallback.Stub.asInterface(point.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 17:
          point.enforceInterface("android.hardware.display.IDisplayManager");
          iVirtualDisplayCallback = IVirtualDisplayCallback.Stub.asInterface(point.readStrongBinder());
          if (point.readInt() != 0) {
            Surface surface = (Surface)Surface.CREATOR.createFromParcel((Parcel)point);
          } else {
            point = null;
          } 
          setVirtualDisplaySurface(iVirtualDisplayCallback, (Surface)point);
          paramParcel2.writeNoException();
          return true;
        case 16:
          point.enforceInterface("android.hardware.display.IDisplayManager");
          resizeVirtualDisplay(IVirtualDisplayCallback.Stub.asInterface(point.readStrongBinder()), point.readInt(), point.readInt(), point.readInt());
          paramParcel2.writeNoException();
          return true;
        case 15:
          point.enforceInterface("android.hardware.display.IDisplayManager");
          if (point.readInt() != 0) {
            VirtualDisplayConfig virtualDisplayConfig = (VirtualDisplayConfig)VirtualDisplayConfig.CREATOR.createFromParcel((Parcel)point);
          } else {
            iVirtualDisplayCallback = null;
          } 
          i = createVirtualDisplay((VirtualDisplayConfig)iVirtualDisplayCallback, IVirtualDisplayCallback.Stub.asInterface(point.readStrongBinder()), IMediaProjection.Stub.asInterface(point.readStrongBinder()), point.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 14:
          point.enforceInterface("android.hardware.display.IDisplayManager");
          requestColorMode(point.readInt(), point.readInt());
          paramParcel2.writeNoException();
          return true;
        case 13:
          point.enforceInterface("android.hardware.display.IDisplayManager");
          wifiDisplayStatus = getWifiDisplayStatus();
          paramParcel2.writeNoException();
          if (wifiDisplayStatus != null) {
            paramParcel2.writeInt(1);
            wifiDisplayStatus.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 12:
          wifiDisplayStatus.enforceInterface("android.hardware.display.IDisplayManager");
          resumeWifiDisplay();
          paramParcel2.writeNoException();
          return true;
        case 11:
          wifiDisplayStatus.enforceInterface("android.hardware.display.IDisplayManager");
          pauseWifiDisplay();
          paramParcel2.writeNoException();
          return true;
        case 10:
          wifiDisplayStatus.enforceInterface("android.hardware.display.IDisplayManager");
          forgetWifiDisplay(wifiDisplayStatus.readString());
          paramParcel2.writeNoException();
          return true;
        case 9:
          wifiDisplayStatus.enforceInterface("android.hardware.display.IDisplayManager");
          renameWifiDisplay(wifiDisplayStatus.readString(), wifiDisplayStatus.readString());
          paramParcel2.writeNoException();
          return true;
        case 8:
          wifiDisplayStatus.enforceInterface("android.hardware.display.IDisplayManager");
          disconnectWifiDisplay();
          paramParcel2.writeNoException();
          return true;
        case 7:
          wifiDisplayStatus.enforceInterface("android.hardware.display.IDisplayManager");
          connectWifiDisplay(wifiDisplayStatus.readString());
          paramParcel2.writeNoException();
          return true;
        case 6:
          wifiDisplayStatus.enforceInterface("android.hardware.display.IDisplayManager");
          stopWifiDisplayScan();
          paramParcel2.writeNoException();
          return true;
        case 5:
          wifiDisplayStatus.enforceInterface("android.hardware.display.IDisplayManager");
          startWifiDisplayScan();
          paramParcel2.writeNoException();
          return true;
        case 4:
          wifiDisplayStatus.enforceInterface("android.hardware.display.IDisplayManager");
          registerCallback(IDisplayManagerCallback.Stub.asInterface(wifiDisplayStatus.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 3:
          wifiDisplayStatus.enforceInterface("android.hardware.display.IDisplayManager");
          bool1 = isUidPresentOnDisplay(wifiDisplayStatus.readInt(), wifiDisplayStatus.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 2:
          wifiDisplayStatus.enforceInterface("android.hardware.display.IDisplayManager");
          arrayOfInt = getDisplayIds();
          paramParcel2.writeNoException();
          paramParcel2.writeIntArray(arrayOfInt);
          return true;
        case 1:
          break;
      } 
      arrayOfInt.enforceInterface("android.hardware.display.IDisplayManager");
      DisplayInfo displayInfo = getDisplayInfo(arrayOfInt.readInt());
      paramParcel2.writeNoException();
      if (displayInfo != null) {
        paramParcel2.writeInt(1);
        displayInfo.writeToParcel(paramParcel2, 1);
      } else {
        paramParcel2.writeInt(0);
      } 
      return true;
    } 
    paramParcel2.writeString("android.hardware.display.IDisplayManager");
    return true;
  }
  
  private static class Proxy implements IDisplayManager {
    public static IDisplayManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void connectWifiDisplay(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().connectWifiDisplay(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int createVirtualDisplay(VirtualDisplayConfig param2VirtualDisplayConfig, IVirtualDisplayCallback param2IVirtualDisplayCallback, IMediaProjection param2IMediaProjection, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (param2VirtualDisplayConfig != null) {
          parcel1.writeInt(1);
          param2VirtualDisplayConfig.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        IBinder iBinder1 = null;
        if (param2IVirtualDisplayCallback != null) {
          iBinder2 = param2IVirtualDisplayCallback.asBinder();
        } else {
          iBinder2 = null;
        } 
        parcel1.writeStrongBinder(iBinder2);
        IBinder iBinder2 = iBinder1;
        if (param2IMediaProjection != null)
          iBinder2 = param2IMediaProjection.asBinder(); 
        parcel1.writeStrongBinder(iBinder2);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null)
          return IDisplayManager.Stub.getDefaultImpl().createVirtualDisplay(param2VirtualDisplayConfig, param2IVirtualDisplayCallback, param2IMediaProjection, param2String); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void disconnectWifiDisplay() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().disconnectWifiDisplay();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void forgetWifiDisplay(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().forgetWifiDisplay(param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getAmbientBrightnessStats() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        ParceledListSlice parceledListSlice;
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          parceledListSlice = IDisplayManager.Stub.getDefaultImpl().getAmbientBrightnessStats();
          return parceledListSlice;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          parceledListSlice = null;
        } 
        return parceledListSlice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public BrightnessConfiguration getBrightnessConfigurationForUser(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        BrightnessConfiguration brightnessConfiguration;
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          brightnessConfiguration = IDisplayManager.Stub.getDefaultImpl().getBrightnessConfigurationForUser(param2Int);
          return brightnessConfiguration;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          brightnessConfiguration = (BrightnessConfiguration)BrightnessConfiguration.CREATOR.createFromParcel(parcel2);
        } else {
          brightnessConfiguration = null;
        } 
        return brightnessConfiguration;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParceledListSlice getBrightnessEvents(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null)
          return IDisplayManager.Stub.getDefaultImpl().getBrightnessEvents(param2String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParceledListSlice parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
        } else {
          param2String = null;
        } 
        return (ParceledListSlice)param2String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public BrightnessConfiguration getDefaultBrightnessConfiguration() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        BrightnessConfiguration brightnessConfiguration;
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          brightnessConfiguration = IDisplayManager.Stub.getDefaultImpl().getDefaultBrightnessConfiguration();
          return brightnessConfiguration;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          brightnessConfiguration = (BrightnessConfiguration)BrightnessConfiguration.CREATOR.createFromParcel(parcel2);
        } else {
          brightnessConfiguration = null;
        } 
        return brightnessConfiguration;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int[] getDisplayIds() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null)
          return IDisplayManager.Stub.getDefaultImpl().getDisplayIds(); 
        parcel2.readException();
        return parcel2.createIntArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public DisplayInfo getDisplayInfo(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        DisplayInfo displayInfo;
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          displayInfo = IDisplayManager.Stub.getDefaultImpl().getDisplayInfo(param2Int);
          return displayInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          displayInfo = (DisplayInfo)DisplayInfo.CREATOR.createFromParcel(parcel2);
        } else {
          displayInfo = null;
        } 
        return displayInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.display.IDisplayManager";
    }
    
    public Curve getMinimumBrightnessCurve() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Curve curve;
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          curve = IDisplayManager.Stub.getDefaultImpl().getMinimumBrightnessCurve();
          return curve;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          curve = (Curve)Curve.CREATOR.createFromParcel(parcel2);
        } else {
          curve = null;
        } 
        return curve;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPreferredWideGamutColorSpaceId() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null)
          return IDisplayManager.Stub.getDefaultImpl().getPreferredWideGamutColorSpaceId(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public Point getStableDisplaySize() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        Point point;
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          point = IDisplayManager.Stub.getDefaultImpl().getStableDisplaySize();
          return point;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          point = (Point)Point.CREATOR.createFromParcel(parcel2);
        } else {
          point = null;
        } 
        return point;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public WifiDisplayStatus getWifiDisplayStatus() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        WifiDisplayStatus wifiDisplayStatus;
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          wifiDisplayStatus = IDisplayManager.Stub.getDefaultImpl().getWifiDisplayStatus();
          return wifiDisplayStatus;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          wifiDisplayStatus = (WifiDisplayStatus)WifiDisplayStatus.CREATOR.createFromParcel(parcel2);
        } else {
          wifiDisplayStatus = null;
        } 
        return wifiDisplayStatus;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isMinimalPostProcessingRequested(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(26, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          bool = IDisplayManager.Stub.getDefaultImpl().isMinimalPostProcessingRequested(param2Int);
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
    
    public boolean isUidPresentOnDisplay(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(3, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          bool = IDisplayManager.Stub.getDefaultImpl().isUidPresentOnDisplay(param2Int1, param2Int2);
          return bool;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        if (param2Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void pauseWifiDisplay() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().pauseWifiDisplay();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerCallback(IDisplayManagerCallback param2IDisplayManagerCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (param2IDisplayManagerCallback != null) {
          iBinder = param2IDisplayManagerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().registerCallback(param2IDisplayManagerCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void releaseVirtualDisplay(IVirtualDisplayCallback param2IVirtualDisplayCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (param2IVirtualDisplayCallback != null) {
          iBinder = param2IVirtualDisplayCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().releaseVirtualDisplay(param2IVirtualDisplayCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void renameWifiDisplay(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().renameWifiDisplay(param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestColorMode(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().requestColorMode(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resizeVirtualDisplay(IVirtualDisplayCallback param2IVirtualDisplayCallback, int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (param2IVirtualDisplayCallback != null) {
          iBinder = param2IVirtualDisplayCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().resizeVirtualDisplay(param2IVirtualDisplayCallback, param2Int1, param2Int2, param2Int3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void resumeWifiDisplay() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().resumeWifiDisplay();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setBrightnessConfigurationForUser(BrightnessConfiguration param2BrightnessConfiguration, int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (param2BrightnessConfiguration != null) {
          parcel1.writeInt(1);
          param2BrightnessConfiguration.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().setBrightnessConfigurationForUser(param2BrightnessConfiguration, param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setTemporaryAutoBrightnessAdjustment(float param2Float) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        parcel1.writeFloat(param2Float);
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().setTemporaryAutoBrightnessAdjustment(param2Float);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setTemporaryBrightness(float param2Float) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        parcel1.writeFloat(param2Float);
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().setTemporaryBrightness(param2Float);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setVirtualDisplayState(IVirtualDisplayCallback param2IVirtualDisplayCallback, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        boolean bool;
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (param2IVirtualDisplayCallback != null) {
          iBinder = param2IVirtualDisplayCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().setVirtualDisplayState(param2IVirtualDisplayCallback, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setVirtualDisplaySurface(IVirtualDisplayCallback param2IVirtualDisplayCallback, Surface param2Surface) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (param2IVirtualDisplayCallback != null) {
          iBinder = param2IVirtualDisplayCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2Surface != null) {
          parcel1.writeInt(1);
          param2Surface.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().setVirtualDisplaySurface(param2IVirtualDisplayCallback, param2Surface);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startWifiDisplayScan() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().startWifiDisplayScan();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stopWifiDisplayScan() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.display.IDisplayManager");
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IDisplayManager.Stub.getDefaultImpl() != null) {
          IDisplayManager.Stub.getDefaultImpl().stopWifiDisplayScan();
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IDisplayManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */