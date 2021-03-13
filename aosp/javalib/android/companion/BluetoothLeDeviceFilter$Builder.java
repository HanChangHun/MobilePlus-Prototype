package android.companion;

import android.bluetooth.le.ScanFilter;
import android.provider.OneTimeUseBuilder;
import android.text.TextUtils;
import com.android.internal.util.Preconditions;
import java.nio.ByteOrder;
import java.util.Objects;
import java.util.regex.Pattern;

public final class Builder extends OneTimeUseBuilder<BluetoothLeDeviceFilter> {
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
  
  private void checkRangeNotEmpty(int paramInt) {
    boolean bool;
    if (paramInt > 0) {
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
  
  private Builder setRename(String paramString1, String paramString2) {
    boolean bool;
    checkNotUsed();
    if (TextUtils.length(paramString1) <= BluetoothLeDeviceFilter.getRenamePrefixLengthLimit()) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "Prefix is too long");
    this.mRenamePrefix = paramString1;
    this.mRenameSuffix = paramString2;
    return this;
  }
  
  public BluetoothLeDeviceFilter build() {
    markUsed();
    return new BluetoothLeDeviceFilter(this.mNamePattern, this.mScanFilter, this.mRawDataFilter, this.mRawDataFilterMask, this.mRenamePrefix, this.mRenameSuffix, this.mRenameBytesFrom, this.mRenameBytesLength, this.mRenameNameFrom, this.mRenameNameLength, this.mRenameBytesReverseOrder, null);
  }
  
  public Builder setNamePattern(Pattern paramPattern) {
    checkNotUsed();
    this.mNamePattern = paramPattern;
    return this;
  }
  
  public Builder setRawDataFilter(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    checkNotUsed();
    Objects.requireNonNull(paramArrayOfbyte1);
    if (paramArrayOfbyte2 == null || paramArrayOfbyte1.length == paramArrayOfbyte2.length) {
      boolean bool1 = true;
      Preconditions.checkArgument(bool1, "Mask and filter should be the same length");
      this.mRawDataFilter = paramArrayOfbyte1;
      this.mRawDataFilterMask = paramArrayOfbyte2;
      return this;
    } 
    boolean bool = false;
    Preconditions.checkArgument(bool, "Mask and filter should be the same length");
    this.mRawDataFilter = paramArrayOfbyte1;
    this.mRawDataFilterMask = paramArrayOfbyte2;
    return this;
  }
  
  public Builder setRenameFromBytes(String paramString1, String paramString2, int paramInt1, int paramInt2, ByteOrder paramByteOrder) {
    boolean bool;
    checkRenameNotSet();
    checkRangeNotEmpty(paramInt2);
    this.mRenameBytesFrom = paramInt1;
    this.mRenameBytesLength = paramInt2;
    if (paramByteOrder == ByteOrder.LITTLE_ENDIAN) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mRenameBytesReverseOrder = bool;
    return setRename(paramString1, paramString2);
  }
  
  public Builder setRenameFromName(String paramString1, String paramString2, int paramInt1, int paramInt2) {
    checkRenameNotSet();
    checkRangeNotEmpty(paramInt2);
    this.mRenameNameFrom = paramInt1;
    this.mRenameNameLength = paramInt2;
    this.mRenameBytesReverseOrder = false;
    return setRename(paramString1, paramString2);
  }
  
  public Builder setScanFilter(ScanFilter paramScanFilter) {
    checkNotUsed();
    this.mScanFilter = paramScanFilter;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/BluetoothLeDeviceFilter$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */