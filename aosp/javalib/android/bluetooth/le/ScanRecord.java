package android.bluetooth.le;

import android.bluetooth.BluetoothUuid;
import android.os.ParcelUuid;
import android.util.SparseArray;
import java.util.List;
import java.util.Map;

public final class ScanRecord {
  private static final int DATA_TYPE_FLAGS = 1;
  
  private static final int DATA_TYPE_LOCAL_NAME_COMPLETE = 9;
  
  private static final int DATA_TYPE_LOCAL_NAME_SHORT = 8;
  
  private static final int DATA_TYPE_MANUFACTURER_SPECIFIC_DATA = 255;
  
  private static final int DATA_TYPE_SERVICE_DATA_128_BIT = 33;
  
  private static final int DATA_TYPE_SERVICE_DATA_16_BIT = 22;
  
  private static final int DATA_TYPE_SERVICE_DATA_32_BIT = 32;
  
  private static final int DATA_TYPE_SERVICE_SOLICITATION_UUIDS_128_BIT = 21;
  
  private static final int DATA_TYPE_SERVICE_SOLICITATION_UUIDS_16_BIT = 20;
  
  private static final int DATA_TYPE_SERVICE_SOLICITATION_UUIDS_32_BIT = 31;
  
  private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_COMPLETE = 7;
  
  private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_PARTIAL = 6;
  
  private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_COMPLETE = 3;
  
  private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_PARTIAL = 2;
  
  private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_COMPLETE = 5;
  
  private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_PARTIAL = 4;
  
  private static final int DATA_TYPE_TX_POWER_LEVEL = 10;
  
  private static final String TAG = "ScanRecord";
  
  private final int mAdvertiseFlags;
  
  private final byte[] mBytes;
  
  private final String mDeviceName;
  
  private final SparseArray<byte[]> mManufacturerSpecificData;
  
  private final Map<ParcelUuid, byte[]> mServiceData;
  
  private final List<ParcelUuid> mServiceSolicitationUuids;
  
  private final List<ParcelUuid> mServiceUuids;
  
  private final int mTxPowerLevel;
  
  private ScanRecord(List<ParcelUuid> paramList1, List<ParcelUuid> paramList2, SparseArray<byte[]> paramSparseArray, Map<ParcelUuid, byte[]> paramMap, int paramInt1, int paramInt2, String paramString, byte[] paramArrayOfbyte) {
    this.mServiceSolicitationUuids = paramList2;
    this.mServiceUuids = paramList1;
    this.mManufacturerSpecificData = paramSparseArray;
    this.mServiceData = paramMap;
    this.mDeviceName = paramString;
    this.mAdvertiseFlags = paramInt1;
    this.mTxPowerLevel = paramInt2;
    this.mBytes = paramArrayOfbyte;
  }
  
  private static byte[] extractBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfbyte, paramInt1, arrayOfByte, 0, paramInt2);
    return arrayOfByte;
  }
  
  public static ScanRecord parseFromBytes(byte[] paramArrayOfbyte) {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: new java/util/ArrayList
    //   9: dup
    //   10: invokespecial <init> : ()V
    //   13: astore_1
    //   14: new java/util/ArrayList
    //   17: dup
    //   18: invokespecial <init> : ()V
    //   21: astore_2
    //   22: new android/util/SparseArray
    //   25: dup
    //   26: invokespecial <init> : ()V
    //   29: astore_3
    //   30: new android/util/ArrayMap
    //   33: dup
    //   34: invokespecial <init> : ()V
    //   37: astore #4
    //   39: iconst_m1
    //   40: istore #5
    //   42: aconst_null
    //   43: astore #6
    //   45: ldc -2147483648
    //   47: istore #7
    //   49: iconst_0
    //   50: istore #8
    //   52: aload_0
    //   53: arraylength
    //   54: istore #9
    //   56: iload #8
    //   58: iload #9
    //   60: if_icmpge -> 479
    //   63: iload #8
    //   65: iconst_1
    //   66: iadd
    //   67: istore #10
    //   69: aload_0
    //   70: iload #8
    //   72: baload
    //   73: istore #8
    //   75: iload #8
    //   77: sipush #255
    //   80: iand
    //   81: istore #8
    //   83: iload #8
    //   85: ifne -> 91
    //   88: goto -> 479
    //   91: iload #8
    //   93: iconst_1
    //   94: isub
    //   95: istore #11
    //   97: iload #10
    //   99: iconst_1
    //   100: iadd
    //   101: istore #9
    //   103: aload_0
    //   104: iload #10
    //   106: baload
    //   107: sipush #255
    //   110: iand
    //   111: istore #10
    //   113: iload #10
    //   115: sipush #255
    //   118: if_icmpeq -> 426
    //   121: iload #10
    //   123: tableswitch default -> 176, 1 -> 413, 2 -> 399, 3 -> 399, 4 -> 385, 5 -> 385, 6 -> 370, 7 -> 370, 8 -> 350, 9 -> 350, 10 -> 341
    //   176: iload #10
    //   178: tableswitch default -> 204, 20 -> 327, 21 -> 312, 22 -> 249
    //   204: iload #10
    //   206: tableswitch default -> 232, 31 -> 235, 32 -> 249, 33 -> 249
    //   232: goto -> 464
    //   235: aload_0
    //   236: iload #9
    //   238: iload #11
    //   240: iconst_4
    //   241: aload_2
    //   242: invokestatic parseServiceSolicitationUuid : ([BIIILjava/util/List;)I
    //   245: pop
    //   246: goto -> 464
    //   249: iconst_2
    //   250: istore #8
    //   252: iload #10
    //   254: bipush #32
    //   256: if_icmpne -> 265
    //   259: iconst_4
    //   260: istore #8
    //   262: goto -> 276
    //   265: iload #10
    //   267: bipush #33
    //   269: if_icmpne -> 276
    //   272: bipush #16
    //   274: istore #8
    //   276: aload #4
    //   278: aload_0
    //   279: iload #9
    //   281: iload #8
    //   283: invokestatic extractBytes : ([BII)[B
    //   286: invokestatic parseUuidFrom : ([B)Landroid/os/ParcelUuid;
    //   289: aload_0
    //   290: iload #9
    //   292: iload #8
    //   294: iadd
    //   295: iload #11
    //   297: iload #8
    //   299: isub
    //   300: invokestatic extractBytes : ([BII)[B
    //   303: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   308: pop
    //   309: goto -> 464
    //   312: aload_0
    //   313: iload #9
    //   315: iload #11
    //   317: bipush #16
    //   319: aload_2
    //   320: invokestatic parseServiceSolicitationUuid : ([BIIILjava/util/List;)I
    //   323: pop
    //   324: goto -> 464
    //   327: aload_0
    //   328: iload #9
    //   330: iload #11
    //   332: iconst_2
    //   333: aload_2
    //   334: invokestatic parseServiceSolicitationUuid : ([BIIILjava/util/List;)I
    //   337: pop
    //   338: goto -> 464
    //   341: aload_0
    //   342: iload #9
    //   344: baload
    //   345: istore #7
    //   347: goto -> 464
    //   350: new java/lang/String
    //   353: dup
    //   354: aload_0
    //   355: iload #9
    //   357: iload #11
    //   359: invokestatic extractBytes : ([BII)[B
    //   362: invokespecial <init> : ([B)V
    //   365: astore #6
    //   367: goto -> 464
    //   370: aload_0
    //   371: iload #9
    //   373: iload #11
    //   375: bipush #16
    //   377: aload_1
    //   378: invokestatic parseServiceUuid : ([BIIILjava/util/List;)I
    //   381: pop
    //   382: goto -> 464
    //   385: aload_0
    //   386: iload #9
    //   388: iload #11
    //   390: iconst_4
    //   391: aload_1
    //   392: invokestatic parseServiceUuid : ([BIIILjava/util/List;)I
    //   395: pop
    //   396: goto -> 464
    //   399: aload_0
    //   400: iload #9
    //   402: iload #11
    //   404: iconst_2
    //   405: aload_1
    //   406: invokestatic parseServiceUuid : ([BIIILjava/util/List;)I
    //   409: pop
    //   410: goto -> 464
    //   413: sipush #255
    //   416: aload_0
    //   417: iload #9
    //   419: baload
    //   420: iand
    //   421: istore #5
    //   423: goto -> 464
    //   426: aload_3
    //   427: aload_0
    //   428: iload #9
    //   430: iconst_1
    //   431: iadd
    //   432: baload
    //   433: sipush #255
    //   436: iand
    //   437: bipush #8
    //   439: ishl
    //   440: sipush #255
    //   443: aload_0
    //   444: iload #9
    //   446: baload
    //   447: iand
    //   448: iadd
    //   449: aload_0
    //   450: iload #9
    //   452: iconst_2
    //   453: iadd
    //   454: iload #11
    //   456: iconst_2
    //   457: isub
    //   458: invokestatic extractBytes : ([BII)[B
    //   461: invokevirtual put : (ILjava/lang/Object;)V
    //   464: iload #9
    //   466: iload #11
    //   468: iadd
    //   469: istore #8
    //   471: goto -> 52
    //   474: astore #6
    //   476: goto -> 533
    //   479: aload_1
    //   480: invokeinterface isEmpty : ()Z
    //   485: istore #12
    //   487: iload #12
    //   489: ifeq -> 497
    //   492: aconst_null
    //   493: astore_1
    //   494: goto -> 497
    //   497: new android/bluetooth/le/ScanRecord
    //   500: dup
    //   501: aload_1
    //   502: aload_2
    //   503: aload_3
    //   504: aload #4
    //   506: iload #5
    //   508: iload #7
    //   510: aload #6
    //   512: aload_0
    //   513: invokespecial <init> : (Ljava/util/List;Ljava/util/List;Landroid/util/SparseArray;Ljava/util/Map;IILjava/lang/String;[B)V
    //   516: astore #6
    //   518: aload #6
    //   520: areturn
    //   521: astore #6
    //   523: goto -> 533
    //   526: astore #6
    //   528: goto -> 533
    //   531: astore #6
    //   533: new java/lang/StringBuilder
    //   536: dup
    //   537: invokespecial <init> : ()V
    //   540: astore #6
    //   542: aload #6
    //   544: ldc 'unable to parse scan record: '
    //   546: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   549: pop
    //   550: aload #6
    //   552: aload_0
    //   553: invokestatic toString : ([B)Ljava/lang/String;
    //   556: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   559: pop
    //   560: ldc 'ScanRecord'
    //   562: aload #6
    //   564: invokevirtual toString : ()Ljava/lang/String;
    //   567: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   570: pop
    //   571: new android/bluetooth/le/ScanRecord
    //   574: dup
    //   575: aconst_null
    //   576: aconst_null
    //   577: aconst_null
    //   578: aconst_null
    //   579: iconst_m1
    //   580: ldc -2147483648
    //   582: aconst_null
    //   583: aload_0
    //   584: invokespecial <init> : (Ljava/util/List;Ljava/util/List;Landroid/util/SparseArray;Ljava/util/Map;IILjava/lang/String;[B)V
    //   587: areturn
    // Exception table:
    //   from	to	target	type
    //   52	56	531	java/lang/Exception
    //   235	246	474	java/lang/Exception
    //   276	309	474	java/lang/Exception
    //   312	324	474	java/lang/Exception
    //   327	338	474	java/lang/Exception
    //   350	367	474	java/lang/Exception
    //   370	382	474	java/lang/Exception
    //   385	396	474	java/lang/Exception
    //   399	410	474	java/lang/Exception
    //   426	464	474	java/lang/Exception
    //   479	487	526	java/lang/Exception
    //   497	518	521	java/lang/Exception
  }
  
  private static int parseServiceSolicitationUuid(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, List<ParcelUuid> paramList) {
    while (paramInt2 > 0) {
      paramList.add(BluetoothUuid.parseUuidFrom(extractBytes(paramArrayOfbyte, paramInt1, paramInt3)));
      paramInt2 -= paramInt3;
      paramInt1 += paramInt3;
    } 
    return paramInt1;
  }
  
  private static int parseServiceUuid(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, List<ParcelUuid> paramList) {
    while (paramInt2 > 0) {
      paramList.add(BluetoothUuid.parseUuidFrom(extractBytes(paramArrayOfbyte, paramInt1, paramInt3)));
      paramInt2 -= paramInt3;
      paramInt1 += paramInt3;
    } 
    return paramInt1;
  }
  
  public int getAdvertiseFlags() {
    return this.mAdvertiseFlags;
  }
  
  public byte[] getBytes() {
    return this.mBytes;
  }
  
  public String getDeviceName() {
    return this.mDeviceName;
  }
  
  public SparseArray<byte[]> getManufacturerSpecificData() {
    return this.mManufacturerSpecificData;
  }
  
  public byte[] getManufacturerSpecificData(int paramInt) {
    SparseArray<byte[]> sparseArray = this.mManufacturerSpecificData;
    return (sparseArray == null) ? null : (byte[])sparseArray.get(paramInt);
  }
  
  public Map<ParcelUuid, byte[]> getServiceData() {
    return this.mServiceData;
  }
  
  public byte[] getServiceData(ParcelUuid paramParcelUuid) {
    if (paramParcelUuid != null) {
      Map<ParcelUuid, byte[]> map = this.mServiceData;
      if (map != null)
        return map.get(paramParcelUuid); 
    } 
    return null;
  }
  
  public List<ParcelUuid> getServiceSolicitationUuids() {
    return this.mServiceSolicitationUuids;
  }
  
  public List<ParcelUuid> getServiceUuids() {
    return this.mServiceUuids;
  }
  
  public int getTxPowerLevel() {
    return this.mTxPowerLevel;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ScanRecord [mAdvertiseFlags=");
    stringBuilder.append(this.mAdvertiseFlags);
    stringBuilder.append(", mServiceUuids=");
    stringBuilder.append(this.mServiceUuids);
    stringBuilder.append(", mServiceSolicitationUuids=");
    stringBuilder.append(this.mServiceSolicitationUuids);
    stringBuilder.append(", mManufacturerSpecificData=");
    stringBuilder.append(BluetoothLeUtils.toString(this.mManufacturerSpecificData));
    stringBuilder.append(", mServiceData=");
    stringBuilder.append(BluetoothLeUtils.toString(this.mServiceData));
    stringBuilder.append(", mTxPowerLevel=");
    stringBuilder.append(this.mTxPowerLevel);
    stringBuilder.append(", mDeviceName=");
    stringBuilder.append(this.mDeviceName);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/ScanRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */