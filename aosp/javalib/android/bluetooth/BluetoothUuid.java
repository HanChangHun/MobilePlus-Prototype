package android.bluetooth;

import android.annotation.SystemApi;
import android.os.ParcelUuid;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

@SystemApi
public final class BluetoothUuid {
  @SystemApi
  public static final ParcelUuid A2DP_SINK = ParcelUuid.fromString("0000110B-0000-1000-8000-00805F9B34FB");
  
  @SystemApi
  public static final ParcelUuid A2DP_SOURCE = ParcelUuid.fromString("0000110A-0000-1000-8000-00805F9B34FB");
  
  @SystemApi
  public static final ParcelUuid ADV_AUDIO_DIST = ParcelUuid.fromString("0000110D-0000-1000-8000-00805F9B34FB");
  
  @SystemApi
  public static final ParcelUuid AVRCP_CONTROLLER;
  
  @SystemApi
  public static final ParcelUuid AVRCP_TARGET;
  
  @SystemApi
  public static final ParcelUuid BASE_UUID;
  
  @SystemApi
  public static final ParcelUuid BNEP;
  
  @SystemApi
  public static final ParcelUuid HEARING_AID;
  
  @SystemApi
  public static final ParcelUuid HFP;
  
  @SystemApi
  public static final ParcelUuid HFP_AG;
  
  @SystemApi
  public static final ParcelUuid HID;
  
  @SystemApi
  public static final ParcelUuid HOGP;
  
  @SystemApi
  public static final ParcelUuid HSP = ParcelUuid.fromString("00001108-0000-1000-8000-00805F9B34FB");
  
  @SystemApi
  public static final ParcelUuid HSP_AG = ParcelUuid.fromString("00001112-0000-1000-8000-00805F9B34FB");
  
  @SystemApi
  public static final ParcelUuid MAP;
  
  @SystemApi
  public static final ParcelUuid MAS;
  
  @SystemApi
  public static final ParcelUuid MNS;
  
  @SystemApi
  public static final ParcelUuid NAP;
  
  @SystemApi
  public static final ParcelUuid OBEX_OBJECT_PUSH;
  
  @SystemApi
  public static final ParcelUuid PANU;
  
  @SystemApi
  public static final ParcelUuid PBAP_PCE;
  
  @SystemApi
  public static final ParcelUuid PBAP_PSE;
  
  @SystemApi
  public static final ParcelUuid SAP;
  
  @SystemApi
  public static final int UUID_BYTES_128_BIT = 16;
  
  @SystemApi
  public static final int UUID_BYTES_16_BIT = 2;
  
  @SystemApi
  public static final int UUID_BYTES_32_BIT = 4;
  
  static {
    HFP = ParcelUuid.fromString("0000111E-0000-1000-8000-00805F9B34FB");
    HFP_AG = ParcelUuid.fromString("0000111F-0000-1000-8000-00805F9B34FB");
    AVRCP_CONTROLLER = ParcelUuid.fromString("0000110E-0000-1000-8000-00805F9B34FB");
    AVRCP_TARGET = ParcelUuid.fromString("0000110C-0000-1000-8000-00805F9B34FB");
    OBEX_OBJECT_PUSH = ParcelUuid.fromString("00001105-0000-1000-8000-00805f9b34fb");
    HID = ParcelUuid.fromString("00001124-0000-1000-8000-00805f9b34fb");
    HOGP = ParcelUuid.fromString("00001812-0000-1000-8000-00805f9b34fb");
    PANU = ParcelUuid.fromString("00001115-0000-1000-8000-00805F9B34FB");
    NAP = ParcelUuid.fromString("00001116-0000-1000-8000-00805F9B34FB");
    BNEP = ParcelUuid.fromString("0000000f-0000-1000-8000-00805F9B34FB");
    PBAP_PCE = ParcelUuid.fromString("0000112e-0000-1000-8000-00805F9B34FB");
    PBAP_PSE = ParcelUuid.fromString("0000112f-0000-1000-8000-00805F9B34FB");
    MAP = ParcelUuid.fromString("00001134-0000-1000-8000-00805F9B34FB");
    MNS = ParcelUuid.fromString("00001133-0000-1000-8000-00805F9B34FB");
    MAS = ParcelUuid.fromString("00001132-0000-1000-8000-00805F9B34FB");
    SAP = ParcelUuid.fromString("0000112D-0000-1000-8000-00805F9B34FB");
    HEARING_AID = ParcelUuid.fromString("0000FDF0-0000-1000-8000-00805f9b34fb");
    BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
  }
  
  @SystemApi
  public static boolean containsAnyUuid(ParcelUuid[] paramArrayOfParcelUuid1, ParcelUuid[] paramArrayOfParcelUuid2) {
    boolean bool1 = true;
    boolean bool2 = true;
    if (paramArrayOfParcelUuid1 == null && paramArrayOfParcelUuid2 == null)
      return true; 
    if (paramArrayOfParcelUuid1 == null) {
      if (paramArrayOfParcelUuid2.length != 0)
        bool2 = false; 
      return bool2;
    } 
    if (paramArrayOfParcelUuid2 == null) {
      if (paramArrayOfParcelUuid1.length == 0) {
        bool2 = bool1;
      } else {
        bool2 = false;
      } 
      return bool2;
    } 
    HashSet hashSet = new HashSet(Arrays.asList((Object[])paramArrayOfParcelUuid1));
    int i = paramArrayOfParcelUuid2.length;
    for (byte b = 0; b < i; b++) {
      if (hashSet.contains(paramArrayOfParcelUuid2[b]))
        return true; 
    } 
    return false;
  }
  
  private static int getServiceIdentifierFromParcelUuid(ParcelUuid paramParcelUuid) {
    return (int)((paramParcelUuid.getUuid().getMostSignificantBits() & 0xFFFFFFFF00000000L) >>> 32L);
  }
  
  public static boolean is16BitUuid(ParcelUuid paramParcelUuid) {
    UUID uUID = paramParcelUuid.getUuid();
    long l1 = uUID.getLeastSignificantBits();
    long l2 = BASE_UUID.getUuid().getLeastSignificantBits();
    boolean bool = false;
    if (l1 != l2)
      return false; 
    if ((uUID.getMostSignificantBits() & 0xFFFF0000FFFFFFFFL) == 4096L)
      bool = true; 
    return bool;
  }
  
  public static boolean is32BitUuid(ParcelUuid paramParcelUuid) {
    UUID uUID = paramParcelUuid.getUuid();
    long l1 = uUID.getLeastSignificantBits();
    long l2 = BASE_UUID.getUuid().getLeastSignificantBits();
    boolean bool = false;
    if (l1 != l2)
      return false; 
    if (is16BitUuid(paramParcelUuid))
      return false; 
    if ((uUID.getMostSignificantBits() & 0xFFFFFFFFL) == 4096L)
      bool = true; 
    return bool;
  }
  
  @SystemApi
  public static ParcelUuid parseUuidFrom(byte[] paramArrayOfbyte) {
    // Byte code:
    //   0: aload_0
    //   1: ifnull -> 224
    //   4: aload_0
    //   5: arraylength
    //   6: istore_1
    //   7: iload_1
    //   8: iconst_2
    //   9: if_icmpeq -> 59
    //   12: iload_1
    //   13: iconst_4
    //   14: if_icmpeq -> 59
    //   17: iload_1
    //   18: bipush #16
    //   20: if_icmpne -> 26
    //   23: goto -> 59
    //   26: new java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial <init> : ()V
    //   33: astore_0
    //   34: aload_0
    //   35: ldc 'uuidBytes length invalid - '
    //   37: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: pop
    //   41: aload_0
    //   42: iload_1
    //   43: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: new java/lang/IllegalArgumentException
    //   50: dup
    //   51: aload_0
    //   52: invokevirtual toString : ()Ljava/lang/String;
    //   55: invokespecial <init> : (Ljava/lang/String;)V
    //   58: athrow
    //   59: iload_1
    //   60: bipush #16
    //   62: if_icmpne -> 102
    //   65: aload_0
    //   66: invokestatic wrap : ([B)Ljava/nio/ByteBuffer;
    //   69: getstatic java/nio/ByteOrder.LITTLE_ENDIAN : Ljava/nio/ByteOrder;
    //   72: invokevirtual order : (Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;
    //   75: astore_0
    //   76: new android/os/ParcelUuid
    //   79: dup
    //   80: new java/util/UUID
    //   83: dup
    //   84: aload_0
    //   85: bipush #8
    //   87: invokevirtual getLong : (I)J
    //   90: aload_0
    //   91: iconst_0
    //   92: invokevirtual getLong : (I)J
    //   95: invokespecial <init> : (JJ)V
    //   98: invokespecial <init> : (Ljava/util/UUID;)V
    //   101: areturn
    //   102: iload_1
    //   103: iconst_2
    //   104: if_icmpne -> 131
    //   107: aload_0
    //   108: iconst_0
    //   109: baload
    //   110: sipush #255
    //   113: iand
    //   114: i2l
    //   115: aload_0
    //   116: iconst_1
    //   117: baload
    //   118: sipush #255
    //   121: iand
    //   122: bipush #8
    //   124: ishl
    //   125: i2l
    //   126: ladd
    //   127: lstore_2
    //   128: goto -> 186
    //   131: aload_0
    //   132: iconst_0
    //   133: baload
    //   134: sipush #255
    //   137: iand
    //   138: i2l
    //   139: lstore_2
    //   140: aload_0
    //   141: iconst_1
    //   142: baload
    //   143: sipush #255
    //   146: iand
    //   147: bipush #8
    //   149: ishl
    //   150: i2l
    //   151: lstore #4
    //   153: aload_0
    //   154: iconst_2
    //   155: baload
    //   156: sipush #255
    //   159: iand
    //   160: bipush #16
    //   162: ishl
    //   163: i2l
    //   164: lstore #6
    //   166: aload_0
    //   167: iconst_3
    //   168: baload
    //   169: sipush #255
    //   172: iand
    //   173: bipush #24
    //   175: ishl
    //   176: i2l
    //   177: lload_2
    //   178: lload #4
    //   180: ladd
    //   181: lload #6
    //   183: ladd
    //   184: ladd
    //   185: lstore_2
    //   186: new android/os/ParcelUuid
    //   189: dup
    //   190: new java/util/UUID
    //   193: dup
    //   194: getstatic android/bluetooth/BluetoothUuid.BASE_UUID : Landroid/os/ParcelUuid;
    //   197: invokevirtual getUuid : ()Ljava/util/UUID;
    //   200: invokevirtual getMostSignificantBits : ()J
    //   203: lload_2
    //   204: bipush #32
    //   206: lshl
    //   207: ladd
    //   208: getstatic android/bluetooth/BluetoothUuid.BASE_UUID : Landroid/os/ParcelUuid;
    //   211: invokevirtual getUuid : ()Ljava/util/UUID;
    //   214: invokevirtual getLeastSignificantBits : ()J
    //   217: invokespecial <init> : (JJ)V
    //   220: invokespecial <init> : (Ljava/util/UUID;)V
    //   223: areturn
    //   224: new java/lang/IllegalArgumentException
    //   227: dup
    //   228: ldc 'uuidBytes cannot be null'
    //   230: invokespecial <init> : (Ljava/lang/String;)V
    //   233: athrow
  }
  
  public static byte[] uuidToBytes(ParcelUuid paramParcelUuid) {
    if (paramParcelUuid != null) {
      if (is16BitUuid(paramParcelUuid)) {
        int i = getServiceIdentifierFromParcelUuid(paramParcelUuid);
        return new byte[] { (byte)(i & 0xFF), (byte)((0xFF00 & i) >> 8) };
      } 
      if (is32BitUuid(paramParcelUuid)) {
        int i = getServiceIdentifierFromParcelUuid(paramParcelUuid);
        return new byte[] { (byte)(i & 0xFF), (byte)((0xFF00 & i) >> 8), (byte)((0xFF0000 & i) >> 16), (byte)((0xFF000000 & i) >> 24) };
      } 
      long l1 = paramParcelUuid.getUuid().getMostSignificantBits();
      long l2 = paramParcelUuid.getUuid().getLeastSignificantBits();
      byte[] arrayOfByte = new byte[16];
      ByteBuffer byteBuffer = ByteBuffer.wrap(arrayOfByte).order(ByteOrder.LITTLE_ENDIAN);
      byteBuffer.putLong(8, l1);
      byteBuffer.putLong(0, l2);
      return arrayOfByte;
    } 
    throw new IllegalArgumentException("uuid cannot be null");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothUuid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */