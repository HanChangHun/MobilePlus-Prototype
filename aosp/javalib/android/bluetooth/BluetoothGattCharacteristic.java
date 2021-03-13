package android.bluetooth;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BluetoothGattCharacteristic implements Parcelable {
  public static final Parcelable.Creator<BluetoothGattCharacteristic> CREATOR = new Parcelable.Creator<BluetoothGattCharacteristic>() {
      public BluetoothGattCharacteristic createFromParcel(Parcel param1Parcel) {
        return new BluetoothGattCharacteristic(param1Parcel);
      }
      
      public BluetoothGattCharacteristic[] newArray(int param1Int) {
        return new BluetoothGattCharacteristic[param1Int];
      }
    };
  
  public static final int FORMAT_FLOAT = 52;
  
  public static final int FORMAT_SFLOAT = 50;
  
  public static final int FORMAT_SINT16 = 34;
  
  public static final int FORMAT_SINT32 = 36;
  
  public static final int FORMAT_SINT8 = 33;
  
  public static final int FORMAT_UINT16 = 18;
  
  public static final int FORMAT_UINT32 = 20;
  
  public static final int FORMAT_UINT8 = 17;
  
  public static final int PERMISSION_READ = 1;
  
  public static final int PERMISSION_READ_ENCRYPTED = 2;
  
  public static final int PERMISSION_READ_ENCRYPTED_MITM = 4;
  
  public static final int PERMISSION_WRITE = 16;
  
  public static final int PERMISSION_WRITE_ENCRYPTED = 32;
  
  public static final int PERMISSION_WRITE_ENCRYPTED_MITM = 64;
  
  public static final int PERMISSION_WRITE_SIGNED = 128;
  
  public static final int PERMISSION_WRITE_SIGNED_MITM = 256;
  
  public static final int PROPERTY_BROADCAST = 1;
  
  public static final int PROPERTY_EXTENDED_PROPS = 128;
  
  public static final int PROPERTY_INDICATE = 32;
  
  public static final int PROPERTY_NOTIFY = 16;
  
  public static final int PROPERTY_READ = 2;
  
  public static final int PROPERTY_SIGNED_WRITE = 64;
  
  public static final int PROPERTY_WRITE = 8;
  
  public static final int PROPERTY_WRITE_NO_RESPONSE = 4;
  
  public static final int WRITE_TYPE_DEFAULT = 2;
  
  public static final int WRITE_TYPE_NO_RESPONSE = 1;
  
  public static final int WRITE_TYPE_SIGNED = 4;
  
  protected List<BluetoothGattDescriptor> mDescriptors;
  
  protected int mInstance;
  
  protected int mKeySize = 16;
  
  protected int mPermissions;
  
  protected int mProperties;
  
  protected BluetoothGattService mService;
  
  protected UUID mUuid;
  
  protected byte[] mValue;
  
  protected int mWriteType;
  
  BluetoothGattCharacteristic(BluetoothGattService paramBluetoothGattService, UUID paramUUID, int paramInt1, int paramInt2, int paramInt3) {
    initCharacteristic(paramBluetoothGattService, paramUUID, paramInt1, paramInt2, paramInt3);
  }
  
  private BluetoothGattCharacteristic(Parcel paramParcel) {
    this.mUuid = ((ParcelUuid)paramParcel.readParcelable(null)).getUuid();
    this.mInstance = paramParcel.readInt();
    this.mProperties = paramParcel.readInt();
    this.mPermissions = paramParcel.readInt();
    this.mKeySize = paramParcel.readInt();
    this.mWriteType = paramParcel.readInt();
    this.mDescriptors = new ArrayList<>();
    ArrayList arrayList = paramParcel.createTypedArrayList(BluetoothGattDescriptor.CREATOR);
    if (arrayList != null)
      for (BluetoothGattDescriptor bluetoothGattDescriptor : arrayList) {
        bluetoothGattDescriptor.setCharacteristic(this);
        this.mDescriptors.add(bluetoothGattDescriptor);
      }  
  }
  
  public BluetoothGattCharacteristic(UUID paramUUID, int paramInt1, int paramInt2) {
    initCharacteristic(null, paramUUID, 0, paramInt1, paramInt2);
  }
  
  public BluetoothGattCharacteristic(UUID paramUUID, int paramInt1, int paramInt2, int paramInt3) {
    initCharacteristic(null, paramUUID, paramInt1, paramInt2, paramInt3);
  }
  
  private float bytesToFloat(byte paramByte1, byte paramByte2) {
    int i = unsignedToSigned(unsignedByteToInt(paramByte1) + ((unsignedByteToInt(paramByte2) & 0xF) << 8), 12);
    int j = unsignedToSigned(unsignedByteToInt(paramByte2) >> 4, 4);
    return (float)(i * Math.pow(10.0D, j));
  }
  
  private float bytesToFloat(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4) {
    return (float)(unsignedToSigned(unsignedByteToInt(paramByte1) + (unsignedByteToInt(paramByte2) << 8) + (unsignedByteToInt(paramByte3) << 16), 24) * Math.pow(10.0D, paramByte4));
  }
  
  private int getTypeLen(int paramInt) {
    return paramInt & 0xF;
  }
  
  private void initCharacteristic(BluetoothGattService paramBluetoothGattService, UUID paramUUID, int paramInt1, int paramInt2, int paramInt3) {
    this.mUuid = paramUUID;
    this.mInstance = paramInt1;
    this.mProperties = paramInt2;
    this.mPermissions = paramInt3;
    this.mService = paramBluetoothGattService;
    this.mValue = null;
    this.mDescriptors = new ArrayList<>();
    if ((this.mProperties & 0x4) != 0) {
      this.mWriteType = 1;
    } else {
      this.mWriteType = 2;
    } 
  }
  
  private int intToSignedBits(int paramInt1, int paramInt2) {
    int i = paramInt1;
    if (paramInt1 < 0)
      i = (1 << paramInt2 - 1) + (paramInt1 & (1 << paramInt2 - 1) - 1); 
    return i;
  }
  
  private int unsignedByteToInt(byte paramByte) {
    return paramByte & 0xFF;
  }
  
  private int unsignedBytesToInt(byte paramByte1, byte paramByte2) {
    return unsignedByteToInt(paramByte1) + (unsignedByteToInt(paramByte2) << 8);
  }
  
  private int unsignedBytesToInt(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4) {
    return unsignedByteToInt(paramByte1) + (unsignedByteToInt(paramByte2) << 8) + (unsignedByteToInt(paramByte3) << 16) + (unsignedByteToInt(paramByte4) << 24);
  }
  
  private int unsignedToSigned(int paramInt1, int paramInt2) {
    int i = paramInt1;
    if ((1 << paramInt2 - 1 & paramInt1) != 0)
      i = ((1 << paramInt2 - 1) - (paramInt1 & (1 << paramInt2 - 1) - 1)) * -1; 
    return i;
  }
  
  public boolean addDescriptor(BluetoothGattDescriptor paramBluetoothGattDescriptor) {
    this.mDescriptors.add(paramBluetoothGattDescriptor);
    paramBluetoothGattDescriptor.setCharacteristic(this);
    return true;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public BluetoothGattDescriptor getDescriptor(UUID paramUUID) {
    for (BluetoothGattDescriptor bluetoothGattDescriptor : this.mDescriptors) {
      if (bluetoothGattDescriptor.getUuid().equals(paramUUID))
        return bluetoothGattDescriptor; 
    } 
    return null;
  }
  
  BluetoothGattDescriptor getDescriptor(UUID paramUUID, int paramInt) {
    for (BluetoothGattDescriptor bluetoothGattDescriptor : this.mDescriptors) {
      if (bluetoothGattDescriptor.getUuid().equals(paramUUID) && bluetoothGattDescriptor.getInstanceId() == paramInt)
        return bluetoothGattDescriptor; 
    } 
    return null;
  }
  
  public List<BluetoothGattDescriptor> getDescriptors() {
    return this.mDescriptors;
  }
  
  public Float getFloatValue(int paramInt1, int paramInt2) {
    int i = getTypeLen(paramInt1);
    byte[] arrayOfByte = this.mValue;
    return (i + paramInt2 > arrayOfByte.length) ? null : ((paramInt1 != 50) ? ((paramInt1 != 52) ? null : Float.valueOf(bytesToFloat(arrayOfByte[paramInt2], arrayOfByte[paramInt2 + 1], arrayOfByte[paramInt2 + 2], arrayOfByte[paramInt2 + 3]))) : Float.valueOf(bytesToFloat(arrayOfByte[paramInt2], arrayOfByte[paramInt2 + 1])));
  }
  
  public int getInstanceId() {
    return this.mInstance;
  }
  
  public Integer getIntValue(int paramInt1, int paramInt2) {
    int i = getTypeLen(paramInt1);
    byte[] arrayOfByte = this.mValue;
    return (i + paramInt2 > arrayOfByte.length) ? null : ((paramInt1 != 17) ? ((paramInt1 != 18) ? ((paramInt1 != 20) ? ((paramInt1 != 36) ? ((paramInt1 != 33) ? ((paramInt1 != 34) ? null : Integer.valueOf(unsignedToSigned(unsignedBytesToInt(arrayOfByte[paramInt2], arrayOfByte[paramInt2 + 1]), 16))) : Integer.valueOf(unsignedToSigned(unsignedByteToInt(arrayOfByte[paramInt2]), 8))) : Integer.valueOf(unsignedToSigned(unsignedBytesToInt(arrayOfByte[paramInt2], arrayOfByte[paramInt2 + 1], arrayOfByte[paramInt2 + 2], arrayOfByte[paramInt2 + 3]), 32))) : Integer.valueOf(unsignedBytesToInt(arrayOfByte[paramInt2], arrayOfByte[paramInt2 + 1], arrayOfByte[paramInt2 + 2], arrayOfByte[paramInt2 + 3]))) : Integer.valueOf(unsignedBytesToInt(arrayOfByte[paramInt2], arrayOfByte[paramInt2 + 1]))) : Integer.valueOf(unsignedByteToInt(arrayOfByte[paramInt2])));
  }
  
  public int getKeySize() {
    return this.mKeySize;
  }
  
  public int getPermissions() {
    return this.mPermissions;
  }
  
  public int getProperties() {
    return this.mProperties;
  }
  
  public BluetoothGattService getService() {
    return this.mService;
  }
  
  public String getStringValue(int paramInt) {
    byte[] arrayOfByte = this.mValue;
    if (arrayOfByte == null || paramInt > arrayOfByte.length)
      return null; 
    arrayOfByte = new byte[arrayOfByte.length - paramInt];
    byte b = 0;
    while (true) {
      byte[] arrayOfByte1 = this.mValue;
      if (b != arrayOfByte1.length - paramInt) {
        arrayOfByte[b] = (byte)arrayOfByte1[paramInt + b];
        b++;
        continue;
      } 
      return new String(arrayOfByte);
    } 
  }
  
  public UUID getUuid() {
    return this.mUuid;
  }
  
  public byte[] getValue() {
    return this.mValue;
  }
  
  public int getWriteType() {
    return this.mWriteType;
  }
  
  public void setInstanceId(int paramInt) {
    this.mInstance = paramInt;
  }
  
  public void setKeySize(int paramInt) {
    this.mKeySize = paramInt;
  }
  
  void setService(BluetoothGattService paramBluetoothGattService) {
    this.mService = paramBluetoothGattService;
  }
  
  public boolean setValue(int paramInt1, int paramInt2, int paramInt3) {
    // Byte code:
    //   0: aload_0
    //   1: iload_2
    //   2: invokespecial getTypeLen : (I)I
    //   5: iload_3
    //   6: iadd
    //   7: istore #4
    //   9: aload_0
    //   10: getfield mValue : [B
    //   13: ifnonnull -> 24
    //   16: aload_0
    //   17: iload #4
    //   19: newarray byte
    //   21: putfield mValue : [B
    //   24: iload #4
    //   26: aload_0
    //   27: getfield mValue : [B
    //   30: arraylength
    //   31: if_icmple -> 36
    //   34: iconst_0
    //   35: ireturn
    //   36: iload_1
    //   37: istore #4
    //   39: iload_2
    //   40: bipush #17
    //   42: if_icmpeq -> 230
    //   45: iload_1
    //   46: istore #4
    //   48: iload_2
    //   49: bipush #18
    //   51: if_icmpeq -> 192
    //   54: iload_1
    //   55: istore #4
    //   57: iload_2
    //   58: bipush #20
    //   60: if_icmpeq -> 116
    //   63: iload_2
    //   64: bipush #36
    //   66: if_icmpeq -> 107
    //   69: iload_2
    //   70: bipush #33
    //   72: if_icmpeq -> 95
    //   75: iload_2
    //   76: bipush #34
    //   78: if_icmpeq -> 83
    //   81: iconst_0
    //   82: ireturn
    //   83: aload_0
    //   84: iload_1
    //   85: bipush #16
    //   87: invokespecial intToSignedBits : (II)I
    //   90: istore #4
    //   92: goto -> 192
    //   95: aload_0
    //   96: iload_1
    //   97: bipush #8
    //   99: invokespecial intToSignedBits : (II)I
    //   102: istore #4
    //   104: goto -> 230
    //   107: aload_0
    //   108: iload_1
    //   109: bipush #32
    //   111: invokespecial intToSignedBits : (II)I
    //   114: istore #4
    //   116: aload_0
    //   117: getfield mValue : [B
    //   120: astore #5
    //   122: iload_3
    //   123: iconst_1
    //   124: iadd
    //   125: istore_1
    //   126: aload #5
    //   128: iload_3
    //   129: iload #4
    //   131: sipush #255
    //   134: iand
    //   135: i2b
    //   136: i2b
    //   137: bastore
    //   138: iload_1
    //   139: iconst_1
    //   140: iadd
    //   141: istore_2
    //   142: aload #5
    //   144: iload_1
    //   145: iload #4
    //   147: bipush #8
    //   149: ishr
    //   150: sipush #255
    //   153: iand
    //   154: i2b
    //   155: i2b
    //   156: bastore
    //   157: aload #5
    //   159: iload_2
    //   160: iload #4
    //   162: bipush #16
    //   164: ishr
    //   165: sipush #255
    //   168: iand
    //   169: i2b
    //   170: i2b
    //   171: bastore
    //   172: aload #5
    //   174: iload_2
    //   175: iconst_1
    //   176: iadd
    //   177: iload #4
    //   179: bipush #24
    //   181: ishr
    //   182: sipush #255
    //   185: iand
    //   186: i2b
    //   187: i2b
    //   188: bastore
    //   189: goto -> 244
    //   192: aload_0
    //   193: getfield mValue : [B
    //   196: astore #5
    //   198: aload #5
    //   200: iload_3
    //   201: iload #4
    //   203: sipush #255
    //   206: iand
    //   207: i2b
    //   208: i2b
    //   209: bastore
    //   210: aload #5
    //   212: iload_3
    //   213: iconst_1
    //   214: iadd
    //   215: iload #4
    //   217: bipush #8
    //   219: ishr
    //   220: sipush #255
    //   223: iand
    //   224: i2b
    //   225: i2b
    //   226: bastore
    //   227: goto -> 244
    //   230: aload_0
    //   231: getfield mValue : [B
    //   234: iload_3
    //   235: iload #4
    //   237: sipush #255
    //   240: iand
    //   241: i2b
    //   242: i2b
    //   243: bastore
    //   244: iconst_1
    //   245: ireturn
  }
  
  public boolean setValue(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = getTypeLen(paramInt3) + paramInt4;
    if (this.mValue == null)
      this.mValue = new byte[i]; 
    if (i > this.mValue.length)
      return false; 
    if (paramInt3 != 50) {
      if (paramInt3 != 52)
        return false; 
      paramInt1 = intToSignedBits(paramInt1, 24);
      paramInt2 = intToSignedBits(paramInt2, 8);
      byte[] arrayOfByte = this.mValue;
      i = paramInt4 + 1;
      arrayOfByte[paramInt4] = (byte)(byte)(paramInt1 & 0xFF);
      paramInt3 = i + 1;
      arrayOfByte[i] = (byte)(byte)(paramInt1 >> 8 & 0xFF);
      paramInt4 = paramInt3 + 1;
      arrayOfByte[paramInt3] = (byte)(byte)(paramInt1 >> 16 & 0xFF);
      arrayOfByte[paramInt4] = (byte)(byte)(arrayOfByte[paramInt4] + (byte)(paramInt2 & 0xFF));
    } else {
      paramInt1 = intToSignedBits(paramInt1, 12);
      paramInt2 = intToSignedBits(paramInt2, 4);
      byte[] arrayOfByte = this.mValue;
      paramInt3 = paramInt4 + 1;
      arrayOfByte[paramInt4] = (byte)(byte)(paramInt1 & 0xFF);
      arrayOfByte[paramInt3] = (byte)(byte)(paramInt1 >> 8 & 0xF);
      arrayOfByte[paramInt3] = (byte)(byte)(arrayOfByte[paramInt3] + (byte)((paramInt2 & 0xF) << 4));
    } 
    return true;
  }
  
  public boolean setValue(String paramString) {
    this.mValue = paramString.getBytes();
    return true;
  }
  
  public boolean setValue(byte[] paramArrayOfbyte) {
    this.mValue = paramArrayOfbyte;
    return true;
  }
  
  public void setWriteType(int paramInt) {
    this.mWriteType = paramInt;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeParcelable((Parcelable)new ParcelUuid(this.mUuid), 0);
    paramParcel.writeInt(this.mInstance);
    paramParcel.writeInt(this.mProperties);
    paramParcel.writeInt(this.mPermissions);
    paramParcel.writeInt(this.mKeySize);
    paramParcel.writeInt(this.mWriteType);
    paramParcel.writeTypedList(this.mDescriptors);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothGattCharacteristic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */