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

public interface IGnssNavigationMessageCallback extends IBase {
  public static final String kInterfaceName = "android.hardware.gnss@1.0::IGnssNavigationMessageCallback";
  
  static IGnssNavigationMessageCallback asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@1.0::IGnssNavigationMessageCallback");
    if (iHwInterface != null && iHwInterface instanceof IGnssNavigationMessageCallback)
      return (IGnssNavigationMessageCallback)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@1.0::IGnssNavigationMessageCallback");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IGnssNavigationMessageCallback castFrom(IHwInterface paramIHwInterface) {
    IGnssNavigationMessageCallback iGnssNavigationMessageCallback;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iGnssNavigationMessageCallback = asInterface(paramIHwInterface.asBinder());
    } 
    return iGnssNavigationMessageCallback;
  }
  
  static IGnssNavigationMessageCallback getService() throws RemoteException {
    return getService("default");
  }
  
  static IGnssNavigationMessageCallback getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IGnssNavigationMessageCallback", paramString));
  }
  
  static IGnssNavigationMessageCallback getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IGnssNavigationMessageCallback", paramString, paramBoolean));
  }
  
  static IGnssNavigationMessageCallback getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  void gnssNavigationMessageCb(GnssNavigationMessage paramGnssNavigationMessage) throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class GnssNavigationMessage {
    public ArrayList<Byte> data = new ArrayList<>();
    
    public short messageId = (short)0;
    
    public short status;
    
    public short submessageId = (short)0;
    
    public short svid = (short)0;
    
    public short type = (short)0;
    
    public static final ArrayList<GnssNavigationMessage> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<GnssNavigationMessage> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 32), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        GnssNavigationMessage gnssNavigationMessage = new GnssNavigationMessage();
        gnssNavigationMessage.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 32));
        arrayList.add(gnssNavigationMessage);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<GnssNavigationMessage> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 32);
      for (byte b = 0; b < i; b++)
        ((GnssNavigationMessage)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 32)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != GnssNavigationMessage.class)
        return false; 
      param1Object = param1Object;
      return (this.svid != ((GnssNavigationMessage)param1Object).svid) ? false : ((this.type != ((GnssNavigationMessage)param1Object).type) ? false : (!HidlSupport.deepEquals(Short.valueOf(this.status), Short.valueOf(((GnssNavigationMessage)param1Object).status)) ? false : ((this.messageId != ((GnssNavigationMessage)param1Object).messageId) ? false : ((this.submessageId != ((GnssNavigationMessage)param1Object).submessageId) ? false : (!!HidlSupport.deepEquals(this.data, ((GnssNavigationMessage)param1Object).data))))));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.svid))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.type))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.status))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.messageId))), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.submessageId))), Integer.valueOf(HidlSupport.deepHashCode(this.data)) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.svid = param1HwBlob.getInt16(param1Long + 0L);
      this.type = param1HwBlob.getInt16(param1Long + 2L);
      this.status = param1HwBlob.getInt16(param1Long + 4L);
      this.messageId = param1HwBlob.getInt16(param1Long + 6L);
      this.submessageId = param1HwBlob.getInt16(param1Long + 8L);
      int i = param1HwBlob.getInt32(param1Long + 16L + 8L);
      HwBlob hwBlob = param1HwParcel.readEmbeddedBuffer((i * 1), param1HwBlob.handle(), param1Long + 16L + 0L, true);
      this.data.clear();
      for (byte b = 0; b < i; b++) {
        byte b1 = hwBlob.getInt8((b * 1));
        this.data.add(Byte.valueOf(b1));
      } 
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(32L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".svid = ");
      stringBuilder.append(this.svid);
      stringBuilder.append(", .type = ");
      stringBuilder.append(IGnssNavigationMessageCallback.GnssNavigationMessageType.toString(this.type));
      stringBuilder.append(", .status = ");
      stringBuilder.append(IGnssNavigationMessageCallback.NavigationMessageStatus.dumpBitfield(this.status));
      stringBuilder.append(", .messageId = ");
      stringBuilder.append(this.messageId);
      stringBuilder.append(", .submessageId = ");
      stringBuilder.append(this.submessageId);
      stringBuilder.append(", .data = ");
      stringBuilder.append(this.data);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putInt16(param1Long + 0L, this.svid);
      param1HwBlob.putInt16(2L + param1Long, this.type);
      param1HwBlob.putInt16(4L + param1Long, this.status);
      param1HwBlob.putInt16(6L + param1Long, this.messageId);
      param1HwBlob.putInt16(param1Long + 8L, this.submessageId);
      int i = this.data.size();
      param1HwBlob.putInt32(param1Long + 16L + 8L, i);
      param1HwBlob.putBool(param1Long + 16L + 12L, false);
      HwBlob hwBlob = new HwBlob(i * 1);
      for (byte b = 0; b < i; b++)
        hwBlob.putInt8((b * 1), ((Byte)this.data.get(b)).byteValue()); 
      param1HwBlob.putBlob(16L + param1Long + 0L, hwBlob);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(32);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class GnssNavigationMessageType {
    public static final short BDS_D1 = 1281;
    
    public static final short BDS_D2 = 1282;
    
    public static final short GAL_F = 1538;
    
    public static final short GAL_I = 1537;
    
    public static final short GLO_L1CA = 769;
    
    public static final short GPS_CNAV2 = 260;
    
    public static final short GPS_L1CA = 257;
    
    public static final short GPS_L2CNAV = 258;
    
    public static final short GPS_L5CNAV = 259;
    
    public static final short UNKNOWN = 0;
    
    public static final String dumpBitfield(short param1Short) {
      ArrayList<String> arrayList = new ArrayList();
      short s1 = 0;
      arrayList.add("UNKNOWN");
      if ((param1Short & 0x101) == 257) {
        arrayList.add("GPS_L1CA");
        s1 = (short)(Character.MIN_VALUE | 0x101);
      } 
      short s2 = s1;
      if ((param1Short & 0x102) == 258) {
        arrayList.add("GPS_L2CNAV");
        s2 = (short)(s1 | 0x102);
      } 
      s1 = s2;
      if ((param1Short & 0x103) == 259) {
        arrayList.add("GPS_L5CNAV");
        s1 = (short)(s2 | 0x103);
      } 
      s2 = s1;
      if ((param1Short & 0x104) == 260) {
        arrayList.add("GPS_CNAV2");
        s2 = (short)(s1 | 0x104);
      } 
      s1 = s2;
      if ((param1Short & 0x301) == 769) {
        arrayList.add("GLO_L1CA");
        s1 = (short)(s2 | 0x301);
      } 
      s2 = s1;
      if ((param1Short & 0x501) == 1281) {
        arrayList.add("BDS_D1");
        s2 = (short)(s1 | 0x501);
      } 
      s1 = s2;
      if ((param1Short & 0x502) == 1282) {
        arrayList.add("BDS_D2");
        s1 = (short)(s2 | 0x502);
      } 
      s2 = s1;
      if ((param1Short & 0x601) == 1537) {
        arrayList.add("GAL_I");
        s2 = (short)(s1 | 0x601);
      } 
      s1 = s2;
      if ((param1Short & 0x602) == 1538) {
        arrayList.add("GAL_F");
        s1 = (short)(s2 | 0x602);
      } 
      if (param1Short != s1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Short.toUnsignedInt((short)(s1 & param1Short))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(short param1Short) {
      if (param1Short == 0)
        return "UNKNOWN"; 
      if (param1Short == 257)
        return "GPS_L1CA"; 
      if (param1Short == 258)
        return "GPS_L2CNAV"; 
      if (param1Short == 259)
        return "GPS_L5CNAV"; 
      if (param1Short == 260)
        return "GPS_CNAV2"; 
      if (param1Short == 769)
        return "GLO_L1CA"; 
      if (param1Short == 1281)
        return "BDS_D1"; 
      if (param1Short == 1282)
        return "BDS_D2"; 
      if (param1Short == 1537)
        return "GAL_I"; 
      if (param1Short == 1538)
        return "GAL_F"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(param1Short)));
      return stringBuilder.toString();
    }
  }
  
  public static final class NavigationMessageStatus {
    public static final short PARITY_PASSED = 1;
    
    public static final short PARITY_REBUILT = 2;
    
    public static final short UNKNOWN = 0;
    
    public static final String dumpBitfield(short param1Short) {
      ArrayList<String> arrayList = new ArrayList();
      short s1 = 0;
      if ((param1Short & 0x1) == 1) {
        arrayList.add("PARITY_PASSED");
        s1 = (short)(false | true);
      } 
      short s2 = s1;
      if ((param1Short & 0x2) == 2) {
        arrayList.add("PARITY_REBUILT");
        s2 = (short)(s1 | 0x2);
      } 
      arrayList.add("UNKNOWN");
      if (param1Short != s2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Short.toUnsignedInt((short)(s2 & param1Short))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(short param1Short) {
      if (param1Short == 1)
        return "PARITY_PASSED"; 
      if (param1Short == 2)
        return "PARITY_REBUILT"; 
      if (param1Short == 0)
        return "UNKNOWN"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(param1Short)));
      return stringBuilder.toString();
    }
  }
  
  public static final class Proxy implements IGnssNavigationMessageCallback {
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
    
    public void gnssNavigationMessageCb(IGnssNavigationMessageCallback.GnssNavigationMessage param1GnssNavigationMessage) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssNavigationMessageCallback");
      param1GnssNavigationMessage.writeToParcel(null);
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
        return "[class or subclass of android.hardware.gnss@1.0::IGnssNavigationMessageCallback]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IGnssNavigationMessageCallback {
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
                78, 113, 105, -111, -99, 36, -5, -27, 87, 62, 
                91, -51, 104, 61, 11, -41, -85, -11, 83, -92, 
                -26, -61, 76, 65, -7, -33, -63, -31, 32, 80, 
                -37, 7 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@1.0::IGnssNavigationMessageCallback", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@1.0::IGnssNavigationMessageCallback";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      DebugInfo debugInfo;
      HwBlob hwBlob1;
      String str;
      ArrayList<String> arrayList;
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
          str.enforceInterface("android.hidl.base@1.0::IBase");
          arrayList = interfaceChain();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeStringVector(arrayList);
          param1HwParcel2.send();
        case 1:
          break;
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssNavigationMessageCallback");
      IGnssNavigationMessageCallback.GnssNavigationMessage gnssNavigationMessage = new IGnssNavigationMessageCallback.GnssNavigationMessage();
      gnssNavigationMessage.readFromParcel((HwParcel)arrayList);
      gnssNavigationMessageCb(gnssNavigationMessage);
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@1.0::IGnssNavigationMessageCallback".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssNavigationMessageCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */