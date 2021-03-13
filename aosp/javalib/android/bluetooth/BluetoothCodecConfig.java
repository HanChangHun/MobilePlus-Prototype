package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

public final class BluetoothCodecConfig implements Parcelable {
  public static final int BITS_PER_SAMPLE_16 = 1;
  
  public static final int BITS_PER_SAMPLE_24 = 2;
  
  public static final int BITS_PER_SAMPLE_32 = 4;
  
  public static final int BITS_PER_SAMPLE_NONE = 0;
  
  public static final int CHANNEL_MODE_MONO = 1;
  
  public static final int CHANNEL_MODE_NONE = 0;
  
  public static final int CHANNEL_MODE_STEREO = 2;
  
  public static final int CODEC_PRIORITY_DEFAULT = 0;
  
  public static final int CODEC_PRIORITY_DISABLED = -1;
  
  public static final int CODEC_PRIORITY_HIGHEST = 1000000;
  
  public static final Parcelable.Creator<BluetoothCodecConfig> CREATOR = new Parcelable.Creator<BluetoothCodecConfig>() {
      public BluetoothCodecConfig createFromParcel(Parcel param1Parcel) {
        return new BluetoothCodecConfig(param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readLong(), param1Parcel.readLong(), param1Parcel.readLong(), param1Parcel.readLong());
      }
      
      public BluetoothCodecConfig[] newArray(int param1Int) {
        return new BluetoothCodecConfig[param1Int];
      }
    };
  
  public static final int SAMPLE_RATE_176400 = 16;
  
  public static final int SAMPLE_RATE_192000 = 32;
  
  public static final int SAMPLE_RATE_44100 = 1;
  
  public static final int SAMPLE_RATE_48000 = 2;
  
  public static final int SAMPLE_RATE_88200 = 4;
  
  public static final int SAMPLE_RATE_96000 = 8;
  
  public static final int SAMPLE_RATE_NONE = 0;
  
  public static final int SOURCE_CODEC_TYPE_AAC = 1;
  
  public static final int SOURCE_CODEC_TYPE_APTX = 2;
  
  public static final int SOURCE_CODEC_TYPE_APTX_HD = 3;
  
  public static final int SOURCE_CODEC_TYPE_INVALID = 1000000;
  
  public static final int SOURCE_CODEC_TYPE_LDAC = 4;
  
  public static final int SOURCE_CODEC_TYPE_MAX = 5;
  
  public static final int SOURCE_CODEC_TYPE_SBC = 0;
  
  private final int mBitsPerSample;
  
  private final int mChannelMode;
  
  private int mCodecPriority;
  
  private final long mCodecSpecific1;
  
  private final long mCodecSpecific2;
  
  private final long mCodecSpecific3;
  
  private final long mCodecSpecific4;
  
  private final int mCodecType;
  
  private final int mSampleRate;
  
  public BluetoothCodecConfig(int paramInt) {
    this.mCodecType = paramInt;
    this.mCodecPriority = 0;
    this.mSampleRate = 0;
    this.mBitsPerSample = 0;
    this.mChannelMode = 0;
    this.mCodecSpecific1 = 0L;
    this.mCodecSpecific2 = 0L;
    this.mCodecSpecific3 = 0L;
    this.mCodecSpecific4 = 0L;
  }
  
  public BluetoothCodecConfig(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2, long paramLong3, long paramLong4) {
    this.mCodecType = paramInt1;
    this.mCodecPriority = paramInt2;
    this.mSampleRate = paramInt3;
    this.mBitsPerSample = paramInt4;
    this.mChannelMode = paramInt5;
    this.mCodecSpecific1 = paramLong1;
    this.mCodecSpecific2 = paramLong2;
    this.mCodecSpecific3 = paramLong3;
    this.mCodecSpecific4 = paramLong4;
  }
  
  private static String appendCapabilityToString(String paramString1, String paramString2) {
    if (paramString1 == null)
      return paramString2; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString1);
    stringBuilder.append("|");
    stringBuilder.append(paramString2);
    return stringBuilder.toString();
  }
  
  private static boolean hasSingleBit(int paramInt) {
    return (paramInt == 0 || (paramInt - 1 & paramInt) == 0);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof BluetoothCodecConfig;
    boolean bool1 = false;
    if (bool) {
      paramObject = paramObject;
      bool = bool1;
      if (((BluetoothCodecConfig)paramObject).mCodecType == this.mCodecType) {
        bool = bool1;
        if (((BluetoothCodecConfig)paramObject).mCodecPriority == this.mCodecPriority) {
          bool = bool1;
          if (((BluetoothCodecConfig)paramObject).mSampleRate == this.mSampleRate) {
            bool = bool1;
            if (((BluetoothCodecConfig)paramObject).mBitsPerSample == this.mBitsPerSample) {
              bool = bool1;
              if (((BluetoothCodecConfig)paramObject).mChannelMode == this.mChannelMode) {
                bool = bool1;
                if (((BluetoothCodecConfig)paramObject).mCodecSpecific1 == this.mCodecSpecific1) {
                  bool = bool1;
                  if (((BluetoothCodecConfig)paramObject).mCodecSpecific2 == this.mCodecSpecific2) {
                    bool = bool1;
                    if (((BluetoothCodecConfig)paramObject).mCodecSpecific3 == this.mCodecSpecific3) {
                      bool = bool1;
                      if (((BluetoothCodecConfig)paramObject).mCodecSpecific4 == this.mCodecSpecific4)
                        bool = true; 
                    } 
                  } 
                } 
              } 
            } 
          } 
        } 
      } 
      return bool;
    } 
    return false;
  }
  
  public int getBitsPerSample() {
    return this.mBitsPerSample;
  }
  
  public int getChannelMode() {
    return this.mChannelMode;
  }
  
  public String getCodecName() {
    int i = this.mCodecType;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              if (i != 1000000) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("UNKNOWN CODEC(");
                stringBuilder.append(this.mCodecType);
                stringBuilder.append(")");
                return stringBuilder.toString();
              } 
              return "INVALID CODEC";
            } 
            return "LDAC";
          } 
          return "aptX HD";
        } 
        return "aptX";
      } 
      return "AAC";
    } 
    return "SBC";
  }
  
  public int getCodecPriority() {
    return this.mCodecPriority;
  }
  
  public long getCodecSpecific1() {
    return this.mCodecSpecific1;
  }
  
  public long getCodecSpecific2() {
    return this.mCodecSpecific2;
  }
  
  public long getCodecSpecific3() {
    return this.mCodecSpecific3;
  }
  
  public long getCodecSpecific4() {
    return this.mCodecSpecific4;
  }
  
  public int getCodecType() {
    return this.mCodecType;
  }
  
  public int getSampleRate() {
    return this.mSampleRate;
  }
  
  public boolean hasSingleBitsPerSample() {
    return hasSingleBit(this.mBitsPerSample);
  }
  
  public boolean hasSingleChannelMode() {
    return hasSingleBit(this.mChannelMode);
  }
  
  public boolean hasSingleSampleRate() {
    return hasSingleBit(this.mSampleRate);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.mCodecType), Integer.valueOf(this.mCodecPriority), Integer.valueOf(this.mSampleRate), Integer.valueOf(this.mBitsPerSample), Integer.valueOf(this.mChannelMode), Long.valueOf(this.mCodecSpecific1), Long.valueOf(this.mCodecSpecific2), Long.valueOf(this.mCodecSpecific3), Long.valueOf(this.mCodecSpecific4) });
  }
  
  public boolean isMandatoryCodec() {
    boolean bool;
    if (this.mCodecType == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isValid() {
    boolean bool;
    if (this.mSampleRate != 0 && this.mBitsPerSample != 0 && this.mChannelMode != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean sameAudioFeedingParameters(BluetoothCodecConfig paramBluetoothCodecConfig) {
    boolean bool;
    if (paramBluetoothCodecConfig != null && paramBluetoothCodecConfig.mSampleRate == this.mSampleRate && paramBluetoothCodecConfig.mBitsPerSample == this.mBitsPerSample && paramBluetoothCodecConfig.mChannelMode == this.mChannelMode) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean sameCodecSpecificParameters(BluetoothCodecConfig paramBluetoothCodecConfig) {
    return (paramBluetoothCodecConfig == null && this.mCodecType != paramBluetoothCodecConfig.mCodecType) ? false : (!(this.mCodecType == 4 && this.mCodecSpecific1 != paramBluetoothCodecConfig.mCodecSpecific1));
  }
  
  public void setCodecPriority(int paramInt) {
    this.mCodecPriority = paramInt;
  }
  
  public boolean similarCodecFeedingParameters(BluetoothCodecConfig paramBluetoothCodecConfig) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 123
    //   4: aload_0
    //   5: getfield mCodecType : I
    //   8: aload_1
    //   9: getfield mCodecType : I
    //   12: if_icmpeq -> 18
    //   15: goto -> 123
    //   18: aload_1
    //   19: getfield mSampleRate : I
    //   22: istore_2
    //   23: aload_0
    //   24: getfield mSampleRate : I
    //   27: ifeq -> 36
    //   30: iload_2
    //   31: istore_3
    //   32: iload_2
    //   33: ifne -> 41
    //   36: aload_0
    //   37: getfield mSampleRate : I
    //   40: istore_3
    //   41: aload_1
    //   42: getfield mBitsPerSample : I
    //   45: istore_2
    //   46: aload_0
    //   47: getfield mBitsPerSample : I
    //   50: ifeq -> 63
    //   53: iload_2
    //   54: ifne -> 60
    //   57: goto -> 63
    //   60: goto -> 68
    //   63: aload_0
    //   64: getfield mBitsPerSample : I
    //   67: istore_2
    //   68: aload_1
    //   69: getfield mChannelMode : I
    //   72: istore #4
    //   74: aload_0
    //   75: getfield mChannelMode : I
    //   78: ifeq -> 92
    //   81: iload #4
    //   83: ifne -> 89
    //   86: goto -> 92
    //   89: goto -> 98
    //   92: aload_0
    //   93: getfield mChannelMode : I
    //   96: istore #4
    //   98: aload_0
    //   99: new android/bluetooth/BluetoothCodecConfig
    //   102: dup
    //   103: aload_0
    //   104: getfield mCodecType : I
    //   107: iconst_0
    //   108: iload_3
    //   109: iload_2
    //   110: iload #4
    //   112: lconst_0
    //   113: lconst_0
    //   114: lconst_0
    //   115: lconst_0
    //   116: invokespecial <init> : (IIIIIJJJJ)V
    //   119: invokevirtual sameAudioFeedingParameters : (Landroid/bluetooth/BluetoothCodecConfig;)Z
    //   122: ireturn
    //   123: iconst_0
    //   124: ireturn
  }
  
  public String toString() {
    String str1 = null;
    if (this.mSampleRate == 0)
      str1 = appendCapabilityToString(null, "NONE"); 
    String str2 = str1;
    if ((this.mSampleRate & 0x1) != 0)
      str2 = appendCapabilityToString(str1, "44100"); 
    str1 = str2;
    if ((this.mSampleRate & 0x2) != 0)
      str1 = appendCapabilityToString(str2, "48000"); 
    str2 = str1;
    if ((this.mSampleRate & 0x4) != 0)
      str2 = appendCapabilityToString(str1, "88200"); 
    str1 = str2;
    if ((this.mSampleRate & 0x8) != 0)
      str1 = appendCapabilityToString(str2, "96000"); 
    str2 = str1;
    if ((this.mSampleRate & 0x10) != 0)
      str2 = appendCapabilityToString(str1, "176400"); 
    String str3 = str2;
    if ((this.mSampleRate & 0x20) != 0)
      str3 = appendCapabilityToString(str2, "192000"); 
    str2 = null;
    if (this.mBitsPerSample == 0)
      str2 = appendCapabilityToString(null, "NONE"); 
    str1 = str2;
    if ((this.mBitsPerSample & 0x1) != 0)
      str1 = appendCapabilityToString(str2, "16"); 
    str2 = str1;
    if ((this.mBitsPerSample & 0x2) != 0)
      str2 = appendCapabilityToString(str1, "24"); 
    String str4 = str2;
    if ((this.mBitsPerSample & 0x4) != 0)
      str4 = appendCapabilityToString(str2, "32"); 
    str1 = null;
    if (this.mChannelMode == 0)
      str1 = appendCapabilityToString(null, "NONE"); 
    str2 = str1;
    if ((this.mChannelMode & 0x1) != 0)
      str2 = appendCapabilityToString(str1, "MONO"); 
    str1 = str2;
    if ((this.mChannelMode & 0x2) != 0)
      str1 = appendCapabilityToString(str2, "STEREO"); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{codecName:");
    stringBuilder.append(getCodecName());
    stringBuilder.append(",mCodecType:");
    stringBuilder.append(this.mCodecType);
    stringBuilder.append(",mCodecPriority:");
    stringBuilder.append(this.mCodecPriority);
    stringBuilder.append(",mSampleRate:");
    stringBuilder.append(String.format("0x%x", new Object[] { Integer.valueOf(this.mSampleRate) }));
    stringBuilder.append("(");
    stringBuilder.append(str3);
    stringBuilder.append("),mBitsPerSample:");
    stringBuilder.append(String.format("0x%x", new Object[] { Integer.valueOf(this.mBitsPerSample) }));
    stringBuilder.append("(");
    stringBuilder.append(str4);
    stringBuilder.append("),mChannelMode:");
    stringBuilder.append(String.format("0x%x", new Object[] { Integer.valueOf(this.mChannelMode) }));
    stringBuilder.append("(");
    stringBuilder.append(str1);
    stringBuilder.append("),mCodecSpecific1:");
    stringBuilder.append(this.mCodecSpecific1);
    stringBuilder.append(",mCodecSpecific2:");
    stringBuilder.append(this.mCodecSpecific2);
    stringBuilder.append(",mCodecSpecific3:");
    stringBuilder.append(this.mCodecSpecific3);
    stringBuilder.append(",mCodecSpecific4:");
    stringBuilder.append(this.mCodecSpecific4);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mCodecType);
    paramParcel.writeInt(this.mCodecPriority);
    paramParcel.writeInt(this.mSampleRate);
    paramParcel.writeInt(this.mBitsPerSample);
    paramParcel.writeInt(this.mChannelMode);
    paramParcel.writeLong(this.mCodecSpecific1);
    paramParcel.writeLong(this.mCodecSpecific2);
    paramParcel.writeLong(this.mCodecSpecific3);
    paramParcel.writeLong(this.mCodecSpecific4);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface BitsPerSample {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ChannelMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface CodecPriority {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SampleRate {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SourceCodecType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/BluetoothCodecConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */