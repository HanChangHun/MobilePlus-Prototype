package android.companion;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.OneTimeUseBuilder;
import android.text.TextUtils;
import com.android.internal.util.BitUtils;
import com.android.internal.util.ObjectUtils;
import com.android.internal.util.Preconditions;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;
import libcore.util.HexEncoding;

public final class BluetoothLeDeviceFilter implements DeviceFilter<ScanResult> {
  public static final Parcelable.Creator<BluetoothLeDeviceFilter> CREATOR = new Parcelable.Creator<BluetoothLeDeviceFilter>() {
      public BluetoothLeDeviceFilter createFromParcel(Parcel param1Parcel) {
        BluetoothLeDeviceFilter.Builder builder = (new BluetoothLeDeviceFilter.Builder()).setNamePattern(BluetoothDeviceFilterUtils.patternFromString(param1Parcel.readString())).setScanFilter((ScanFilter)param1Parcel.readParcelable(null));
        byte[] arrayOfByte1 = param1Parcel.createByteArray();
        byte[] arrayOfByte2 = param1Parcel.createByteArray();
        if (arrayOfByte1 != null)
          builder.setRawDataFilter(arrayOfByte1, arrayOfByte2); 
        String str2 = param1Parcel.readString();
        String str1 = param1Parcel.readString();
        int i = param1Parcel.readInt();
        int j = param1Parcel.readInt();
        int k = param1Parcel.readInt();
        int m = param1Parcel.readInt();
        boolean bool = param1Parcel.readBoolean();
        if (str2 != null)
          if (i >= 0) {
            ByteOrder byteOrder;
            if (bool) {
              byteOrder = ByteOrder.LITTLE_ENDIAN;
            } else {
              byteOrder = ByteOrder.BIG_ENDIAN;
            } 
            builder.setRenameFromBytes(str2, str1, i, j, byteOrder);
          } else {
            builder.setRenameFromName(str2, str1, k, m);
          }  
        return builder.build();
      }
      
      public BluetoothLeDeviceFilter[] newArray(int param1Int) {
        return new BluetoothLeDeviceFilter[param1Int];
      }
    };
  
  private static final boolean DEBUG = false;
  
  private static final String LOG_TAG = "BluetoothLeDeviceFilter";
  
  private static final int RENAME_PREFIX_LENGTH_LIMIT = 10;
  
  private final Pattern mNamePattern;
  
  private final byte[] mRawDataFilter;
  
  private final byte[] mRawDataFilterMask;
  
  private final int mRenameBytesFrom;
  
  private final int mRenameBytesLength;
  
  private final boolean mRenameBytesReverseOrder;
  
  private final int mRenameNameFrom;
  
  private final int mRenameNameLength;
  
  private final String mRenamePrefix;
  
  private final String mRenameSuffix;
  
  private final ScanFilter mScanFilter;
  
  private BluetoothLeDeviceFilter(Pattern paramPattern, ScanFilter paramScanFilter, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
    this.mNamePattern = paramPattern;
    this.mScanFilter = (ScanFilter)ObjectUtils.firstNotNull(paramScanFilter, ScanFilter.EMPTY);
    this.mRawDataFilter = paramArrayOfbyte1;
    this.mRawDataFilterMask = paramArrayOfbyte2;
    this.mRenamePrefix = paramString1;
    this.mRenameSuffix = paramString2;
    this.mRenameBytesFrom = paramInt1;
    this.mRenameBytesLength = paramInt2;
    this.mRenameNameFrom = paramInt3;
    this.mRenameNameLength = paramInt4;
    this.mRenameBytesReverseOrder = paramBoolean;
  }
  
  public static int getRenamePrefixLengthLimit() {
    return 10;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.mRenameBytesFrom != ((BluetoothLeDeviceFilter)paramObject).mRenameBytesFrom || this.mRenameBytesLength != ((BluetoothLeDeviceFilter)paramObject).mRenameBytesLength || this.mRenameNameFrom != ((BluetoothLeDeviceFilter)paramObject).mRenameNameFrom || this.mRenameNameLength != ((BluetoothLeDeviceFilter)paramObject).mRenameNameLength || this.mRenameBytesReverseOrder != ((BluetoothLeDeviceFilter)paramObject).mRenameBytesReverseOrder || !Objects.equals(this.mNamePattern, ((BluetoothLeDeviceFilter)paramObject).mNamePattern) || !Objects.equals(this.mScanFilter, ((BluetoothLeDeviceFilter)paramObject).mScanFilter) || !Arrays.equals(this.mRawDataFilter, ((BluetoothLeDeviceFilter)paramObject).mRawDataFilter) || !Arrays.equals(this.mRawDataFilterMask, ((BluetoothLeDeviceFilter)paramObject).mRawDataFilterMask) || !Objects.equals(this.mRenamePrefix, ((BluetoothLeDeviceFilter)paramObject).mRenamePrefix) || !Objects.equals(this.mRenameSuffix, ((BluetoothLeDeviceFilter)paramObject).mRenameSuffix))
      bool = false; 
    return bool;
  }
  
  public String getDeviceDisplayName(ScanResult paramScanResult) {
    byte[] arrayOfByte;
    if (this.mRenameBytesFrom < 0 && this.mRenameNameFrom < 0)
      return BluetoothDeviceFilterUtils.getDeviceDisplayNameInternal(paramScanResult.getDevice()); 
    StringBuilder stringBuilder = new StringBuilder(TextUtils.emptyIfNull(this.mRenamePrefix));
    if (this.mRenameBytesFrom >= 0) {
      int k;
      byte b;
      arrayOfByte = paramScanResult.getScanRecord().getBytes();
      int i = this.mRenameBytesFrom;
      int j = this.mRenameBytesFrom + this.mRenameBytesLength - 1;
      if (this.mRenameBytesReverseOrder) {
        k = j;
      } else {
        k = i;
      } 
      if (this.mRenameBytesReverseOrder) {
        b = -1;
      } else {
        b = 1;
      } 
      while (i <= k && k <= j) {
        stringBuilder.append(HexEncoding.encodeToString(arrayOfByte[k], true));
        k += b;
      } 
    } else {
      String str = BluetoothDeviceFilterUtils.getDeviceDisplayNameInternal(arrayOfByte.getDevice());
      int i = this.mRenameNameFrom;
      stringBuilder.append(str.substring(i, this.mRenameNameLength + i));
    } 
    stringBuilder.append(TextUtils.emptyIfNull(this.mRenameSuffix));
    return stringBuilder.toString();
  }
  
  public int getMediumType() {
    return 1;
  }
  
  public Pattern getNamePattern() {
    return this.mNamePattern;
  }
  
  public byte[] getRawDataFilter() {
    return this.mRawDataFilter;
  }
  
  public byte[] getRawDataFilterMask() {
    return this.mRawDataFilterMask;
  }
  
  public int getRenameBytesFrom() {
    return this.mRenameBytesFrom;
  }
  
  public int getRenameBytesLength() {
    return this.mRenameBytesLength;
  }
  
  public String getRenamePrefix() {
    return this.mRenamePrefix;
  }
  
  public String getRenameSuffix() {
    return this.mRenameSuffix;
  }
  
  public ScanFilter getScanFilter() {
    return this.mScanFilter;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { 
          this.mNamePattern, this.mScanFilter, this.mRawDataFilter, this.mRawDataFilterMask, this.mRenamePrefix, this.mRenameSuffix, Integer.valueOf(this.mRenameBytesFrom), Integer.valueOf(this.mRenameBytesLength), Integer.valueOf(this.mRenameNameFrom), Integer.valueOf(this.mRenameNameLength), 
          Boolean.valueOf(this.mRenameBytesReverseOrder) });
  }
  
  public boolean isRenameBytesReverseOrder() {
    return this.mRenameBytesReverseOrder;
  }
  
  public boolean matches(ScanResult paramScanResult) {
    boolean bool;
    BluetoothDevice bluetoothDevice = paramScanResult.getDevice();
    if (getScanFilter().matches(paramScanResult) && BluetoothDeviceFilterUtils.matchesName(getNamePattern(), bluetoothDevice) && (this.mRawDataFilter == null || BitUtils.maskedEquals(paramScanResult.getScanRecord().getBytes(), this.mRawDataFilter, this.mRawDataFilterMask))) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("BluetoothLEDeviceFilter{mNamePattern=");
    stringBuilder.append(this.mNamePattern);
    stringBuilder.append(", mScanFilter=");
    stringBuilder.append(this.mScanFilter);
    stringBuilder.append(", mRawDataFilter=");
    stringBuilder.append(Arrays.toString(this.mRawDataFilter));
    stringBuilder.append(", mRawDataFilterMask=");
    stringBuilder.append(Arrays.toString(this.mRawDataFilterMask));
    stringBuilder.append(", mRenamePrefix='");
    stringBuilder.append(this.mRenamePrefix);
    stringBuilder.append('\'');
    stringBuilder.append(", mRenameSuffix='");
    stringBuilder.append(this.mRenameSuffix);
    stringBuilder.append('\'');
    stringBuilder.append(", mRenameBytesFrom=");
    stringBuilder.append(this.mRenameBytesFrom);
    stringBuilder.append(", mRenameBytesLength=");
    stringBuilder.append(this.mRenameBytesLength);
    stringBuilder.append(", mRenameNameFrom=");
    stringBuilder.append(this.mRenameNameFrom);
    stringBuilder.append(", mRenameNameLength=");
    stringBuilder.append(this.mRenameNameLength);
    stringBuilder.append(", mRenameBytesReverseOrder=");
    stringBuilder.append(this.mRenameBytesReverseOrder);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(BluetoothDeviceFilterUtils.patternToString(getNamePattern()));
    paramParcel.writeParcelable((Parcelable)this.mScanFilter, paramInt);
    paramParcel.writeByteArray(this.mRawDataFilter);
    paramParcel.writeByteArray(this.mRawDataFilterMask);
    paramParcel.writeString(this.mRenamePrefix);
    paramParcel.writeString(this.mRenameSuffix);
    paramParcel.writeInt(this.mRenameBytesFrom);
    paramParcel.writeInt(this.mRenameBytesLength);
    paramParcel.writeInt(this.mRenameNameFrom);
    paramParcel.writeInt(this.mRenameNameLength);
    paramParcel.writeBoolean(this.mRenameBytesReverseOrder);
  }
  
  public static final class Builder extends OneTimeUseBuilder<BluetoothLeDeviceFilter> {
    private Pattern mNamePattern;
    
    private byte[] mRawDataFilter;
    
    private byte[] mRawDataFilterMask;
    
    private int mRenameBytesFrom = -1;
    
    private int mRenameBytesLength;
    
    private boolean mRenameBytesReverseOrder = false;
    
    private int mRenameNameFrom = -1;
    
    private int mRenameNameLength;
    
    private String mRenamePrefix;
    
    private String mRenameSuffix;
    
    private ScanFilter mScanFilter;
    
    private void checkRangeNotEmpty(int param1Int) {
      boolean bool;
      if (param1Int > 0) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, "Range must be non-empty");
    }
    
    private void checkRenameNotSet() {
      boolean bool;
      if (this.mRenamePrefix == null) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool, "Renaming rule can only be set once");
    }
    
    private Builder setRename(String param1String1, String param1String2) {
      boolean bool;
      checkNotUsed();
      if (TextUtils.length(param1String1) <= BluetoothLeDeviceFilter.getRenamePrefixLengthLimit()) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, "Prefix is too long");
      this.mRenamePrefix = param1String1;
      this.mRenameSuffix = param1String2;
      return this;
    }
    
    public BluetoothLeDeviceFilter build() {
      markUsed();
      return new BluetoothLeDeviceFilter(this.mNamePattern, this.mScanFilter, this.mRawDataFilter, this.mRawDataFilterMask, this.mRenamePrefix, this.mRenameSuffix, this.mRenameBytesFrom, this.mRenameBytesLength, this.mRenameNameFrom, this.mRenameNameLength, this.mRenameBytesReverseOrder);
    }
    
    public Builder setNamePattern(Pattern param1Pattern) {
      checkNotUsed();
      this.mNamePattern = param1Pattern;
      return this;
    }
    
    public Builder setRawDataFilter(byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2) {
      checkNotUsed();
      Objects.requireNonNull(param1ArrayOfbyte1);
      if (param1ArrayOfbyte2 == null || param1ArrayOfbyte1.length == param1ArrayOfbyte2.length) {
        boolean bool1 = true;
        Preconditions.checkArgument(bool1, "Mask and filter should be the same length");
        this.mRawDataFilter = param1ArrayOfbyte1;
        this.mRawDataFilterMask = param1ArrayOfbyte2;
        return this;
      } 
      boolean bool = false;
      Preconditions.checkArgument(bool, "Mask and filter should be the same length");
      this.mRawDataFilter = param1ArrayOfbyte1;
      this.mRawDataFilterMask = param1ArrayOfbyte2;
      return this;
    }
    
    public Builder setRenameFromBytes(String param1String1, String param1String2, int param1Int1, int param1Int2, ByteOrder param1ByteOrder) {
      boolean bool;
      checkRenameNotSet();
      checkRangeNotEmpty(param1Int2);
      this.mRenameBytesFrom = param1Int1;
      this.mRenameBytesLength = param1Int2;
      if (param1ByteOrder == ByteOrder.LITTLE_ENDIAN) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mRenameBytesReverseOrder = bool;
      return setRename(param1String1, param1String2);
    }
    
    public Builder setRenameFromName(String param1String1, String param1String2, int param1Int1, int param1Int2) {
      checkRenameNotSet();
      checkRangeNotEmpty(param1Int2);
      this.mRenameNameFrom = param1Int1;
      this.mRenameNameLength = param1Int2;
      this.mRenameBytesReverseOrder = false;
      return setRename(param1String1, param1String2);
    }
    
    public Builder setScanFilter(ScanFilter param1ScanFilter) {
      checkNotUsed();
      this.mScanFilter = param1ScanFilter;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/BluetoothLeDeviceFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */