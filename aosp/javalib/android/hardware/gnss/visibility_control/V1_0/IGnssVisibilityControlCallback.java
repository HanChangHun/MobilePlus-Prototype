package android.hardware.gnss.visibility_control.V1_0;

import android.internal.hidl.base.V1_0.DebugInfo;
import android.internal.hidl.base.V1_0.IBase;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public interface IGnssVisibilityControlCallback extends IBase {
  public static final String kInterfaceName = "android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback";
  
  static IGnssVisibilityControlCallback asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback");
    if (iHwInterface != null && iHwInterface instanceof IGnssVisibilityControlCallback)
      return (IGnssVisibilityControlCallback)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IGnssVisibilityControlCallback castFrom(IHwInterface paramIHwInterface) {
    IGnssVisibilityControlCallback iGnssVisibilityControlCallback;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iGnssVisibilityControlCallback = asInterface(paramIHwInterface.asBinder());
    } 
    return iGnssVisibilityControlCallback;
  }
  
  static IGnssVisibilityControlCallback getService() throws RemoteException {
    return getService("default");
  }
  
  static IGnssVisibilityControlCallback getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback", paramString));
  }
  
  static IGnssVisibilityControlCallback getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback", paramString, paramBoolean));
  }
  
  static IGnssVisibilityControlCallback getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean isInEmergencySession() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void nfwNotifyCb(NfwNotification paramNfwNotification) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class NfwNotification {
    public boolean inEmergencyMode = false;
    
    public boolean isCachedLocation = false;
    
    public String otherProtocolStackName = new String();
    
    public byte protocolStack = (byte)0;
    
    public String proxyAppPackageName = new String();
    
    public byte requestor = (byte)0;
    
    public String requestorId = new String();
    
    public byte responseType = (byte)0;
    
    public static final ArrayList<NfwNotification> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<NfwNotification> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 72), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        NfwNotification nfwNotification = new NfwNotification();
        nfwNotification.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 72));
        arrayList.add(nfwNotification);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<NfwNotification> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 72);
      for (byte b = 0; b < i; b++)
        ((NfwNotification)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 72)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != NfwNotification.class)
        return false; 
      param1Object = param1Object;
      return !HidlSupport.deepEquals(this.proxyAppPackageName, ((NfwNotification)param1Object).proxyAppPackageName) ? false : ((this.protocolStack != ((NfwNotification)param1Object).protocolStack) ? false : (!HidlSupport.deepEquals(this.otherProtocolStackName, ((NfwNotification)param1Object).otherProtocolStackName) ? false : ((this.requestor != ((NfwNotification)param1Object).requestor) ? false : (!HidlSupport.deepEquals(this.requestorId, ((NfwNotification)param1Object).requestorId) ? false : ((this.responseType != ((NfwNotification)param1Object).responseType) ? false : ((this.inEmergencyMode != ((NfwNotification)param1Object).inEmergencyMode) ? false : (!(this.isCachedLocation != ((NfwNotification)param1Object).isCachedLocation))))))));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.proxyAppPackageName)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.protocolStack))), Integer.valueOf(HidlSupport.deepHashCode(this.otherProtocolStackName)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.requestor))), Integer.valueOf(HidlSupport.deepHashCode(this.requestorId)), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.responseType))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.inEmergencyMode))), Integer.valueOf(HidlSupport.deepHashCode(Boolean.valueOf(this.isCachedLocation))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      String str = param1HwBlob.getString(param1Long + 0L);
      this.proxyAppPackageName = str;
      param1HwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), param1HwBlob.handle(), param1Long + 0L + 0L, false);
      this.protocolStack = param1HwBlob.getInt8(param1Long + 16L);
      str = param1HwBlob.getString(param1Long + 24L);
      this.otherProtocolStackName = str;
      param1HwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), param1HwBlob.handle(), param1Long + 24L + 0L, false);
      this.requestor = param1HwBlob.getInt8(param1Long + 40L);
      str = param1HwBlob.getString(param1Long + 48L);
      this.requestorId = str;
      param1HwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), param1HwBlob.handle(), param1Long + 48L + 0L, false);
      this.responseType = param1HwBlob.getInt8(param1Long + 64L);
      this.inEmergencyMode = param1HwBlob.getBool(param1Long + 65L);
      this.isCachedLocation = param1HwBlob.getBool(param1Long + 66L);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(72L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".proxyAppPackageName = ");
      stringBuilder.append(this.proxyAppPackageName);
      stringBuilder.append(", .protocolStack = ");
      stringBuilder.append(IGnssVisibilityControlCallback.NfwProtocolStack.toString(this.protocolStack));
      stringBuilder.append(", .otherProtocolStackName = ");
      stringBuilder.append(this.otherProtocolStackName);
      stringBuilder.append(", .requestor = ");
      stringBuilder.append(IGnssVisibilityControlCallback.NfwRequestor.toString(this.requestor));
      stringBuilder.append(", .requestorId = ");
      stringBuilder.append(this.requestorId);
      stringBuilder.append(", .responseType = ");
      stringBuilder.append(IGnssVisibilityControlCallback.NfwResponseType.toString(this.responseType));
      stringBuilder.append(", .inEmergencyMode = ");
      stringBuilder.append(this.inEmergencyMode);
      stringBuilder.append(", .isCachedLocation = ");
      stringBuilder.append(this.isCachedLocation);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putString(0L + param1Long, this.proxyAppPackageName);
      param1HwBlob.putInt8(16L + param1Long, this.protocolStack);
      param1HwBlob.putString(24L + param1Long, this.otherProtocolStackName);
      param1HwBlob.putInt8(40L + param1Long, this.requestor);
      param1HwBlob.putString(48L + param1Long, this.requestorId);
      param1HwBlob.putInt8(64L + param1Long, this.responseType);
      param1HwBlob.putBool(65L + param1Long, this.inEmergencyMode);
      param1HwBlob.putBool(66L + param1Long, this.isCachedLocation);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(72);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class NfwProtocolStack {
    public static final byte CTRL_PLANE = 0;
    
    public static final byte IMS = 10;
    
    public static final byte OTHER_PROTOCOL_STACK = 100;
    
    public static final byte SIM = 11;
    
    public static final byte SUPL = 1;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      arrayList.add("CTRL_PLANE");
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("SUPL");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0xA) == 10) {
        arrayList.add("IMS");
        b2 = (byte)(b1 | 0xA);
      } 
      b1 = b2;
      if ((param1Byte & 0xB) == 11) {
        arrayList.add("SIM");
        b1 = (byte)(b2 | 0xB);
      } 
      b2 = b1;
      if ((param1Byte & 0x64) == 100) {
        arrayList.add("OTHER_PROTOCOL_STACK");
        b2 = (byte)(b1 | 0x64);
      } 
      if (param1Byte != b2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b2 & param1Byte))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(byte param1Byte) {
      if (param1Byte == 0)
        return "CTRL_PLANE"; 
      if (param1Byte == 1)
        return "SUPL"; 
      if (param1Byte == 10)
        return "IMS"; 
      if (param1Byte == 11)
        return "SIM"; 
      if (param1Byte == 100)
        return "OTHER_PROTOCOL_STACK"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class NfwRequestor {
    public static final byte AUTOMOBILE_CLIENT = 20;
    
    public static final byte CARRIER = 0;
    
    public static final byte GNSS_CHIPSET_VENDOR = 12;
    
    public static final byte MODEM_CHIPSET_VENDOR = 11;
    
    public static final byte OEM = 10;
    
    public static final byte OTHER_CHIPSET_VENDOR = 13;
    
    public static final byte OTHER_REQUESTOR = 100;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      arrayList.add("CARRIER");
      if ((param1Byte & 0xA) == 10) {
        arrayList.add("OEM");
        b1 = (byte)(0x0 | 0xA);
      } 
      byte b2 = b1;
      if ((param1Byte & 0xB) == 11) {
        arrayList.add("MODEM_CHIPSET_VENDOR");
        b2 = (byte)(b1 | 0xB);
      } 
      b1 = b2;
      if ((param1Byte & 0xC) == 12) {
        arrayList.add("GNSS_CHIPSET_VENDOR");
        b1 = (byte)(b2 | 0xC);
      } 
      b2 = b1;
      if ((param1Byte & 0xD) == 13) {
        arrayList.add("OTHER_CHIPSET_VENDOR");
        b2 = (byte)(b1 | 0xD);
      } 
      b1 = b2;
      if ((param1Byte & 0x14) == 20) {
        arrayList.add("AUTOMOBILE_CLIENT");
        b1 = (byte)(b2 | 0x14);
      } 
      b2 = b1;
      if ((param1Byte & 0x64) == 100) {
        arrayList.add("OTHER_REQUESTOR");
        b2 = (byte)(b1 | 0x64);
      } 
      if (param1Byte != b2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b2 & param1Byte))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(byte param1Byte) {
      if (param1Byte == 0)
        return "CARRIER"; 
      if (param1Byte == 10)
        return "OEM"; 
      if (param1Byte == 11)
        return "MODEM_CHIPSET_VENDOR"; 
      if (param1Byte == 12)
        return "GNSS_CHIPSET_VENDOR"; 
      if (param1Byte == 13)
        return "OTHER_CHIPSET_VENDOR"; 
      if (param1Byte == 20)
        return "AUTOMOBILE_CLIENT"; 
      if (param1Byte == 100)
        return "OTHER_REQUESTOR"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class NfwResponseType {
    public static final byte ACCEPTED_LOCATION_PROVIDED = 2;
    
    public static final byte ACCEPTED_NO_LOCATION_PROVIDED = 1;
    
    public static final byte REJECTED = 0;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      arrayList.add("REJECTED");
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("ACCEPTED_NO_LOCATION_PROVIDED");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("ACCEPTED_LOCATION_PROVIDED");
        b2 = (byte)(b1 | 0x2);
      } 
      if (param1Byte != b2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b2 & param1Byte))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(byte param1Byte) {
      if (param1Byte == 0)
        return "REJECTED"; 
      if (param1Byte == 1)
        return "ACCEPTED_NO_LOCATION_PROVIDED"; 
      if (param1Byte == 2)
        return "ACCEPTED_LOCATION_PROVIDED"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class Proxy implements IGnssVisibilityControlCallback {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public IHwBinder asBinder() {
      return this.mRemote;
    }
    
    public void debug(NativeHandle param1NativeHandle, ArrayList<String> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hidl.base@1.0::IBase");
      hwParcel2.writeNativeHandle(param1NativeHandle);
      hwParcel2.writeStringVector(param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(256131655, hwParcel2, hwParcel1, 0);
        hwParcel1.verifySuccess();
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public final boolean equals(Object param1Object) {
      return HidlSupport.interfacesEqual((IHwInterface)this, param1Object);
    }
    
    public DebugInfo getDebugInfo() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(257049926, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        DebugInfo debugInfo = new DebugInfo();
        this();
        debugInfo.readFromParcel(hwParcel);
        return debugInfo;
      } finally {
        hwParcel.release();
      } 
    }
    
    public ArrayList<byte[]> getHashChain() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256398152, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        ArrayList<byte[]> arrayList = new ArrayList();
        this();
        HwBlob hwBlob = hwParcel.readBuffer(16L);
        int i = hwBlob.getInt32(8L);
        hwBlob = hwParcel.readEmbeddedBuffer((i * 32), hwBlob.handle(), 0L, true);
        arrayList.clear();
        for (byte b = 0; b < i; b++) {
          byte[] arrayOfByte = new byte[32];
          hwBlob.copyToInt8Array((b * 32), arrayOfByte, 32);
          arrayList.add(arrayOfByte);
        } 
        return arrayList;
      } finally {
        hwParcel.release();
      } 
    }
    
    public final int hashCode() {
      return asBinder().hashCode();
    }
    
    public ArrayList<String> interfaceChain() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256067662, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readStringVector();
      } finally {
        hwParcel.release();
      } 
    }
    
    public String interfaceDescriptor() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256136003, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readString();
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean isInEmergencySession() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(2, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return hwParcel.readBool();
      } finally {
        hwParcel.release();
      } 
    }
    
    public boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) throws RemoteException {
      return this.mRemote.linkToDeath(param1DeathRecipient, param1Long);
    }
    
    public void nfwNotifyCb(IGnssVisibilityControlCallback.NfwNotification param1NfwNotification) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback");
      param1NfwNotification.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(1, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void notifySyspropsChanged() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(257120595, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void ping() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256921159, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setHALInstrumentation() throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hidl.base@1.0::IBase");
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(256462420, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public String toString() {
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(interfaceDescriptor());
        stringBuilder.append("@Proxy");
        return stringBuilder.toString();
      } catch (RemoteException remoteException) {
        return "[class or subclass of android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IGnssVisibilityControlCallback {
    public IHwBinder asBinder() {
      return (IHwBinder)this;
    }
    
    public void debug(NativeHandle param1NativeHandle, ArrayList<String> param1ArrayList) {}
    
    public final DebugInfo getDebugInfo() {
      DebugInfo debugInfo = new DebugInfo();
      debugInfo.pid = HidlSupport.getPidIfSharable();
      debugInfo.ptr = 0L;
      debugInfo.arch = 0;
      return debugInfo;
    }
    
    public final ArrayList<byte[]> getHashChain() {
      return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
                51, -90, -78, 12, 67, -81, 0, -3, -5, 48, 
                93, -8, -111, -68, 89, 17, -64, 109, -102, -111, 
                48, -71, 18, 117, -106, 73, -109, 46, 90, 74, 
                110, 109 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      ArrayList<String> arrayList;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          DebugInfo debugInfo;
          HwBlob hwBlob1;
          String str;
          ArrayList<byte[]> arrayList1;
          HwBlob hwBlob2;
          switch (param1Int1) {
            default:
              return;
            case 257120595:
              param1HwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
              notifySyspropsChanged();
            case 257049926:
              param1HwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
              debugInfo = getDebugInfo();
              param1HwParcel2.writeStatus(0);
              debugInfo.writeToParcel(param1HwParcel2);
              param1HwParcel2.send();
            case 256921159:
              debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
              ping();
              param1HwParcel2.writeStatus(0);
              param1HwParcel2.send();
            case 256462420:
              debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
              setHALInstrumentation();
            case 256398152:
              debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
              arrayList1 = getHashChain();
              param1HwParcel2.writeStatus(0);
              hwBlob1 = new HwBlob(16);
              param1Int2 = arrayList1.size();
              hwBlob1.putInt32(8L, param1Int2);
              hwBlob1.putBool(12L, false);
              hwBlob2 = new HwBlob(param1Int2 * 32);
              param1Int1 = 0;
              while (param1Int1 < param1Int2) {
                long l = (param1Int1 * 32);
                byte[] arrayOfByte = arrayList1.get(param1Int1);
                if (arrayOfByte != null && arrayOfByte.length == 32) {
                  hwBlob2.putInt8Array(l, arrayOfByte);
                  param1Int1++;
                  continue;
                } 
                throw new IllegalArgumentException("Array element is not of the expected length");
              } 
              hwBlob1.putBlob(0L, hwBlob2);
              param1HwParcel2.writeBuffer(hwBlob1);
              param1HwParcel2.send();
            case 256136003:
              hwBlob1.enforceInterface("android.hidl.base@1.0::IBase");
              str = interfaceDescriptor();
              param1HwParcel2.writeStatus(0);
              param1HwParcel2.writeString(str);
              param1HwParcel2.send();
            case 256131655:
              str.enforceInterface("android.hidl.base@1.0::IBase");
              debug(str.readNativeHandle(), str.readStringVector());
              param1HwParcel2.writeStatus(0);
              param1HwParcel2.send();
            case 256067662:
              break;
          } 
          str.enforceInterface("android.hidl.base@1.0::IBase");
          arrayList = interfaceChain();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeStringVector(arrayList);
          param1HwParcel2.send();
        } 
        arrayList.enforceInterface("android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback");
        boolean bool = isInEmergencySession();
        param1HwParcel2.writeStatus(0);
        param1HwParcel2.writeBool(bool);
        param1HwParcel2.send();
      } 
      arrayList.enforceInterface("android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback");
      IGnssVisibilityControlCallback.NfwNotification nfwNotification = new IGnssVisibilityControlCallback.NfwNotification();
      nfwNotification.readFromParcel((HwParcel)arrayList);
      nfwNotifyCb(nfwNotification);
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss.visibility_control@1.0::IGnssVisibilityControlCallback".equals(param1String) ? this : null);
    }
    
    public void registerAsService(String param1String) throws RemoteException {
      registerService(param1String);
    }
    
    public final void setHALInstrumentation() {}
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(interfaceDescriptor());
      stringBuilder.append("@Stub");
      return stringBuilder.toString();
    }
    
    public final boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) {
      return true;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/visibility_control/V1_0/IGnssVisibilityControlCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */