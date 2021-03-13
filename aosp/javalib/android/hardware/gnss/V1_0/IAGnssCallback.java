package android.hardware.gnss.V1_0;

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

public interface IAGnssCallback extends IBase {
  public static final String kInterfaceName = "android.hardware.gnss@1.0::IAGnssCallback";
  
  static IAGnssCallback asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@1.0::IAGnssCallback");
    if (iHwInterface != null && iHwInterface instanceof IAGnssCallback)
      return (IAGnssCallback)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@1.0::IAGnssCallback");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IAGnssCallback castFrom(IHwInterface paramIHwInterface) {
    IAGnssCallback iAGnssCallback;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iAGnssCallback = asInterface(paramIHwInterface.asBinder());
    } 
    return iAGnssCallback;
  }
  
  static IAGnssCallback getService() throws RemoteException {
    return getService("default");
  }
  
  static IAGnssCallback getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IAGnssCallback", paramString));
  }
  
  static IAGnssCallback getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IAGnssCallback", paramString, paramBoolean));
  }
  
  static IAGnssCallback getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  void agnssStatusIpV4Cb(AGnssStatusIpV4 paramAGnssStatusIpV4) throws RemoteException;
  
  void agnssStatusIpV6Cb(AGnssStatusIpV6 paramAGnssStatusIpV6) throws RemoteException;
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class AGnssStatusIpV4 {
    public int ipV4Addr = 0;
    
    public byte status = (byte)0;
    
    public byte type = (byte)0;
    
    public static final ArrayList<AGnssStatusIpV4> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<AGnssStatusIpV4> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 8), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        AGnssStatusIpV4 aGnssStatusIpV4 = new AGnssStatusIpV4();
        aGnssStatusIpV4.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 8));
        arrayList.add(aGnssStatusIpV4);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<AGnssStatusIpV4> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 8);
      for (byte b = 0; b < i; b++)
        ((AGnssStatusIpV4)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 8)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != AGnssStatusIpV4.class)
        return false; 
      param1Object = param1Object;
      return (this.type != ((AGnssStatusIpV4)param1Object).type) ? false : ((this.status != ((AGnssStatusIpV4)param1Object).status) ? false : (!(this.ipV4Addr != ((AGnssStatusIpV4)param1Object).ipV4Addr)));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.ipV4Addr))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.type = param1HwBlob.getInt8(0L + param1Long);
      this.status = param1HwBlob.getInt8(1L + param1Long);
      this.ipV4Addr = param1HwBlob.getInt32(4L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(8L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".type = ");
      stringBuilder.append(IAGnssCallback.AGnssType.toString(this.type));
      stringBuilder.append(", .status = ");
      stringBuilder.append(IAGnssCallback.AGnssStatusValue.toString(this.status));
      stringBuilder.append(", .ipV4Addr = ");
      stringBuilder.append(this.ipV4Addr);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putInt8(0L + param1Long, this.type);
      param1HwBlob.putInt8(1L + param1Long, this.status);
      param1HwBlob.putInt32(4L + param1Long, this.ipV4Addr);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(8);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class AGnssStatusIpV6 {
    public byte[] ipV6Addr = new byte[16];
    
    public byte status = (byte)0;
    
    public byte type = (byte)0;
    
    public static final ArrayList<AGnssStatusIpV6> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<AGnssStatusIpV6> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 18), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        AGnssStatusIpV6 aGnssStatusIpV6 = new AGnssStatusIpV6();
        aGnssStatusIpV6.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 18));
        arrayList.add(aGnssStatusIpV6);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<AGnssStatusIpV6> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 18);
      for (byte b = 0; b < i; b++)
        ((AGnssStatusIpV6)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 18)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != AGnssStatusIpV6.class)
        return false; 
      param1Object = param1Object;
      return (this.type != ((AGnssStatusIpV6)param1Object).type) ? false : ((this.status != ((AGnssStatusIpV6)param1Object).status) ? false : (!!HidlSupport.deepEquals(this.ipV6Addr, ((AGnssStatusIpV6)param1Object).ipV6Addr)));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(this.ipV6Addr)) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.type = param1HwBlob.getInt8(0L + param1Long);
      this.status = param1HwBlob.getInt8(1L + param1Long);
      param1HwBlob.copyToInt8Array(2L + param1Long, this.ipV6Addr, 16);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(18L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".type = ");
      stringBuilder.append(IAGnssCallback.AGnssType.toString(this.type));
      stringBuilder.append(", .status = ");
      stringBuilder.append(IAGnssCallback.AGnssStatusValue.toString(this.status));
      stringBuilder.append(", .ipV6Addr = ");
      stringBuilder.append(Arrays.toString(this.ipV6Addr));
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putInt8(0L + param1Long, this.type);
      param1HwBlob.putInt8(1L + param1Long, this.status);
      byte[] arrayOfByte = this.ipV6Addr;
      if (arrayOfByte != null && arrayOfByte.length == 16) {
        param1HwBlob.putInt8Array(2L + param1Long, arrayOfByte);
        return;
      } 
      throw new IllegalArgumentException("Array element is not of the expected length");
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(18);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class AGnssStatusValue {
    public static final byte AGNSS_DATA_CONNECTED = 3;
    
    public static final byte AGNSS_DATA_CONN_DONE = 4;
    
    public static final byte AGNSS_DATA_CONN_FAILED = 5;
    
    public static final byte RELEASE_AGNSS_DATA_CONN = 2;
    
    public static final byte REQUEST_AGNSS_DATA_CONN = 1;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("REQUEST_AGNSS_DATA_CONN");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("RELEASE_AGNSS_DATA_CONN");
        b2 = (byte)(b1 | 0x2);
      } 
      b1 = b2;
      if ((param1Byte & 0x3) == 3) {
        arrayList.add("AGNSS_DATA_CONNECTED");
        b1 = (byte)(b2 | 0x3);
      } 
      b2 = b1;
      if ((param1Byte & 0x4) == 4) {
        arrayList.add("AGNSS_DATA_CONN_DONE");
        b2 = (byte)(b1 | 0x4);
      } 
      b1 = b2;
      if ((param1Byte & 0x5) == 5) {
        arrayList.add("AGNSS_DATA_CONN_FAILED");
        b1 = (byte)(b2 | 0x5);
      } 
      if (param1Byte != b1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b1 & param1Byte))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(byte param1Byte) {
      if (param1Byte == 1)
        return "REQUEST_AGNSS_DATA_CONN"; 
      if (param1Byte == 2)
        return "RELEASE_AGNSS_DATA_CONN"; 
      if (param1Byte == 3)
        return "AGNSS_DATA_CONNECTED"; 
      if (param1Byte == 4)
        return "AGNSS_DATA_CONN_DONE"; 
      if (param1Byte == 5)
        return "AGNSS_DATA_CONN_FAILED"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class AGnssType {
    public static final byte TYPE_C2K = 2;
    
    public static final byte TYPE_SUPL = 1;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("TYPE_SUPL");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("TYPE_C2K");
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
      if (param1Byte == 1)
        return "TYPE_SUPL"; 
      if (param1Byte == 2)
        return "TYPE_C2K"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class Proxy implements IAGnssCallback {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public void agnssStatusIpV4Cb(IAGnssCallback.AGnssStatusIpV4 param1AGnssStatusIpV4) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IAGnssCallback");
      param1AGnssStatusIpV4.writeToParcel(null);
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
    
    public void agnssStatusIpV6Cb(IAGnssCallback.AGnssStatusIpV6 param1AGnssStatusIpV6) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IAGnssCallback");
      param1AGnssStatusIpV6.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(2, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
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
        HwBlob hwBlob1 = hwParcel.readBuffer(16L);
        int i = hwBlob1.getInt32(8L);
        HwBlob hwBlob2 = hwParcel.readEmbeddedBuffer((i * 32), hwBlob1.handle(), 0L, true);
        arrayList.clear();
        for (byte b = 0; b < i; b++) {
          byte[] arrayOfByte = new byte[32];
          hwBlob2.copyToInt8Array((b * 32), arrayOfByte, 32);
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
    
    public boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) throws RemoteException {
      return this.mRemote.linkToDeath(param1DeathRecipient, param1Long);
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
        return "[class or subclass of android.hardware.gnss@1.0::IAGnssCallback]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IAGnssCallback {
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
                126, -55, -81, -37, -106, 75, -5, -125, 105, -122, 
                105, 19, -54, -16, 24, -14, 99, 101, -110, -120, 
                91, -53, 85, -118, 101, -34, 44, 84, 54, -85, 
                79, 96 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@1.0::IAGnssCallback", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@1.0::IAGnssCallback";
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
          ArrayList<byte[]> arrayList1;
          String str;
          HwBlob hwBlob1;
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
              arrayList1.enforceInterface("android.hidl.base@1.0::IBase");
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
        arrayList.enforceInterface("android.hardware.gnss@1.0::IAGnssCallback");
        IAGnssCallback.AGnssStatusIpV6 aGnssStatusIpV6 = new IAGnssCallback.AGnssStatusIpV6();
        aGnssStatusIpV6.readFromParcel((HwParcel)arrayList);
        agnssStatusIpV6Cb(aGnssStatusIpV6);
        param1HwParcel2.writeStatus(0);
        param1HwParcel2.send();
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IAGnssCallback");
      IAGnssCallback.AGnssStatusIpV4 aGnssStatusIpV4 = new IAGnssCallback.AGnssStatusIpV4();
      aGnssStatusIpV4.readFromParcel((HwParcel)arrayList);
      agnssStatusIpV4Cb(aGnssStatusIpV4);
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@1.0::IAGnssCallback".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IAGnssCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */