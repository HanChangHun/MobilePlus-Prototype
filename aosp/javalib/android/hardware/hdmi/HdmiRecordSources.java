package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.util.Log;

@SystemApi
public final class HdmiRecordSources {
  public static final int ANALOGUE_BROADCAST_TYPE_CABLE = 0;
  
  public static final int ANALOGUE_BROADCAST_TYPE_SATELLITE = 1;
  
  public static final int ANALOGUE_BROADCAST_TYPE_TERRESTRIAL = 2;
  
  public static final int BROADCAST_SYSTEM_NTSC_M = 3;
  
  public static final int BROADCAST_SYSTEM_PAL_BG = 0;
  
  public static final int BROADCAST_SYSTEM_PAL_DK = 8;
  
  public static final int BROADCAST_SYSTEM_PAL_I = 4;
  
  public static final int BROADCAST_SYSTEM_PAL_M = 2;
  
  public static final int BROADCAST_SYSTEM_PAL_OTHER_SYSTEM = 31;
  
  public static final int BROADCAST_SYSTEM_SECAM_BG = 6;
  
  public static final int BROADCAST_SYSTEM_SECAM_DK = 5;
  
  public static final int BROADCAST_SYSTEM_SECAM_L = 7;
  
  public static final int BROADCAST_SYSTEM_SECAM_LP = 1;
  
  private static final int CHANNEL_NUMBER_FORMAT_1_PART = 1;
  
  private static final int CHANNEL_NUMBER_FORMAT_2_PART = 2;
  
  public static final int DIGITAL_BROADCAST_TYPE_ARIB = 0;
  
  public static final int DIGITAL_BROADCAST_TYPE_ARIB_BS = 8;
  
  public static final int DIGITAL_BROADCAST_TYPE_ARIB_CS = 9;
  
  public static final int DIGITAL_BROADCAST_TYPE_ARIB_T = 10;
  
  public static final int DIGITAL_BROADCAST_TYPE_ATSC = 1;
  
  public static final int DIGITAL_BROADCAST_TYPE_ATSC_CABLE = 16;
  
  public static final int DIGITAL_BROADCAST_TYPE_ATSC_SATELLITE = 17;
  
  public static final int DIGITAL_BROADCAST_TYPE_ATSC_TERRESTRIAL = 18;
  
  public static final int DIGITAL_BROADCAST_TYPE_DVB = 2;
  
  public static final int DIGITAL_BROADCAST_TYPE_DVB_C = 24;
  
  public static final int DIGITAL_BROADCAST_TYPE_DVB_S = 25;
  
  public static final int DIGITAL_BROADCAST_TYPE_DVB_S2 = 26;
  
  public static final int DIGITAL_BROADCAST_TYPE_DVB_T = 27;
  
  private static final int RECORD_SOURCE_TYPE_ANALOGUE_SERVICE = 3;
  
  private static final int RECORD_SOURCE_TYPE_DIGITAL_SERVICE = 2;
  
  private static final int RECORD_SOURCE_TYPE_EXTERNAL_PHYSICAL_ADDRESS = 5;
  
  private static final int RECORD_SOURCE_TYPE_EXTERNAL_PLUG = 4;
  
  private static final int RECORD_SOURCE_TYPE_OWN_SOURCE = 1;
  
  private static final String TAG = "HdmiRecordSources";
  
  @SystemApi
  public static boolean checkRecordSource(byte[] paramArrayOfbyte) {
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool5 = false;
    if (paramArrayOfbyte == null || paramArrayOfbyte.length == 0)
      return false; 
    byte b = paramArrayOfbyte[0];
    int i = paramArrayOfbyte.length - 1;
    if (b != 1) {
      if (b != 2) {
        if (b != 3) {
          if (b != 4) {
            if (b != 5)
              return false; 
            if (i == 2)
              bool5 = true; 
            return bool5;
          } 
          bool5 = bool1;
          if (i == 1)
            bool5 = true; 
          return bool5;
        } 
        bool5 = bool2;
        if (i == 4)
          bool5 = true; 
        return bool5;
      } 
      bool5 = bool3;
      if (i == 7)
        bool5 = true; 
      return bool5;
    } 
    bool5 = bool4;
    if (i == 0)
      bool5 = true; 
    return bool5;
  }
  
  public static AnalogueServiceSource ofAnalogue(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt1 >= 0 && paramInt1 <= 2) {
      if (paramInt2 >= 0 && paramInt2 <= 65535) {
        if (paramInt3 >= 0 && paramInt3 <= 31)
          return new AnalogueServiceSource(paramInt1, paramInt2, paramInt3); 
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Invalid Broadcast system:");
        stringBuilder2.append(paramInt3);
        Log.w("HdmiRecordSources", stringBuilder2.toString());
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Invalid Broadcast system:");
        stringBuilder2.append(paramInt3);
        throw new IllegalArgumentException(stringBuilder2.toString());
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Invalid frequency value[0x0000-0xFFFF]:");
      stringBuilder1.append(paramInt2);
      Log.w("HdmiRecordSources", stringBuilder1.toString());
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Invalid frequency value[0x0000-0xFFFF]:");
      stringBuilder1.append(paramInt2);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid Broadcast type:");
    stringBuilder.append(paramInt1);
    Log.w("HdmiRecordSources", stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid Broadcast type:");
    stringBuilder.append(paramInt1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static DigitalServiceSource ofArib(int paramInt, AribData paramAribData) {
    if (paramAribData != null) {
      StringBuilder stringBuilder;
      if (paramInt != 0)
        switch (paramInt) {
          default:
            stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid ARIB type:");
            stringBuilder.append(paramInt);
            Log.w("HdmiRecordSources", stringBuilder.toString());
            throw new IllegalArgumentException("type should not be null.");
          case 8:
          case 9:
          case 10:
            break;
        }  
      return new DigitalServiceSource(0, paramInt, (DigitalServiceIdentification)stringBuilder);
    } 
    throw new IllegalArgumentException("data should not be null.");
  }
  
  public static DigitalServiceSource ofAtsc(int paramInt, AtscData paramAtscData) {
    if (paramAtscData != null) {
      StringBuilder stringBuilder;
      if (paramInt != 1)
        switch (paramInt) {
          default:
            stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid ATSC type:");
            stringBuilder.append(paramInt);
            Log.w("HdmiRecordSources", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid ATSC type:");
            stringBuilder.append(paramInt);
            throw new IllegalArgumentException(stringBuilder.toString());
          case 16:
          case 17:
          case 18:
            break;
        }  
      return new DigitalServiceSource(0, paramInt, (DigitalServiceIdentification)stringBuilder);
    } 
    throw new IllegalArgumentException("data should not be null.");
  }
  
  public static DigitalServiceSource ofDigitalChannelId(int paramInt, DigitalChannelData paramDigitalChannelData) {
    if (paramDigitalChannelData != null) {
      StringBuilder stringBuilder;
      if (paramInt != 0 && paramInt != 1 && paramInt != 2)
        switch (paramInt) {
          default:
            switch (paramInt) {
              default:
                switch (paramInt) {
                  default:
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Invalid broadcast type:");
                    stringBuilder.append(paramInt);
                    Log.w("HdmiRecordSources", stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Invalid broadcast system value:");
                    stringBuilder.append(paramInt);
                    throw new IllegalArgumentException(stringBuilder.toString());
                  case 24:
                  case 25:
                  case 26:
                  case 27:
                    break;
                } 
                break;
              case 16:
              case 17:
              case 18:
                break;
            } 
            break;
          case 8:
          case 9:
          case 10:
            break;
        }  
      return new DigitalServiceSource(1, paramInt, (DigitalServiceIdentification)stringBuilder);
    } 
    throw new IllegalArgumentException("data should not be null.");
  }
  
  public static DigitalServiceSource ofDvb(int paramInt, DvbData paramDvbData) {
    if (paramDvbData != null) {
      StringBuilder stringBuilder;
      if (paramInt != 2)
        switch (paramInt) {
          default:
            stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid DVB type:");
            stringBuilder.append(paramInt);
            Log.w("HdmiRecordSources", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid DVB type:");
            stringBuilder.append(paramInt);
            throw new IllegalArgumentException(stringBuilder.toString());
          case 24:
          case 25:
          case 26:
          case 27:
            break;
        }  
      return new DigitalServiceSource(0, paramInt, (DigitalServiceIdentification)stringBuilder);
    } 
    throw new IllegalArgumentException("data should not be null.");
  }
  
  public static ExternalPhysicalAddress ofExternalPhysicalAddress(int paramInt) {
    if ((0xFFFF0000 & paramInt) == 0)
      return new ExternalPhysicalAddress(paramInt); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid physical address:");
    stringBuilder.append(paramInt);
    Log.w("HdmiRecordSources", stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid physical address:");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static ExternalPlugData ofExternalPlug(int paramInt) {
    if (paramInt >= 1 && paramInt <= 255)
      return new ExternalPlugData(paramInt); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid plug number[1-255]");
    stringBuilder.append(paramInt);
    Log.w("HdmiRecordSources", stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid plug number[1-255]");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static OwnSource ofOwnSource() {
    return new OwnSource();
  }
  
  private static int shortToByteArray(short paramShort, byte[] paramArrayOfbyte, int paramInt) {
    paramArrayOfbyte[paramInt] = (byte)(byte)(paramShort >>> 8 & 0xFF);
    paramArrayOfbyte[paramInt + 1] = (byte)(byte)(paramShort & 0xFF);
    return 2;
  }
  
  private static int threeFieldsToSixBytes(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfbyte, int paramInt4) {
    shortToByteArray((short)paramInt1, paramArrayOfbyte, paramInt4);
    shortToByteArray((short)paramInt2, paramArrayOfbyte, paramInt4 + 2);
    shortToByteArray((short)paramInt3, paramArrayOfbyte, paramInt4 + 4);
    return 6;
  }
  
  @SystemApi
  public static final class AnalogueServiceSource extends RecordSource {
    static final int EXTRA_DATA_SIZE = 4;
    
    private final int mBroadcastSystem;
    
    private final int mBroadcastType;
    
    private final int mFrequency;
    
    private AnalogueServiceSource(int param1Int1, int param1Int2, int param1Int3) {
      super(3, 4);
      this.mBroadcastType = param1Int1;
      this.mFrequency = param1Int2;
      this.mBroadcastSystem = param1Int3;
    }
    
    int extraParamToByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      param1ArrayOfbyte[param1Int] = (byte)(byte)this.mBroadcastType;
      HdmiRecordSources.shortToByteArray((short)this.mFrequency, param1ArrayOfbyte, param1Int + 1);
      param1ArrayOfbyte[param1Int + 3] = (byte)(byte)this.mBroadcastSystem;
      return 4;
    }
  }
  
  public static final class AribData implements DigitalServiceIdentification {
    private final int mOriginalNetworkId;
    
    private final int mServiceId;
    
    private final int mTransportStreamId;
    
    public AribData(int param1Int1, int param1Int2, int param1Int3) {
      this.mTransportStreamId = param1Int1;
      this.mServiceId = param1Int2;
      this.mOriginalNetworkId = param1Int3;
    }
    
    public int toByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      return HdmiRecordSources.threeFieldsToSixBytes(this.mTransportStreamId, this.mServiceId, this.mOriginalNetworkId, param1ArrayOfbyte, param1Int);
    }
  }
  
  public static final class AtscData implements DigitalServiceIdentification {
    private final int mProgramNumber;
    
    private final int mTransportStreamId;
    
    public AtscData(int param1Int1, int param1Int2) {
      this.mTransportStreamId = param1Int1;
      this.mProgramNumber = param1Int2;
    }
    
    public int toByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      return HdmiRecordSources.threeFieldsToSixBytes(this.mTransportStreamId, this.mProgramNumber, 0, param1ArrayOfbyte, param1Int);
    }
  }
  
  private static final class ChannelIdentifier {
    private final int mChannelNumberFormat;
    
    private final int mMajorChannelNumber;
    
    private final int mMinorChannelNumber;
    
    private ChannelIdentifier(int param1Int1, int param1Int2, int param1Int3) {
      this.mChannelNumberFormat = param1Int1;
      this.mMajorChannelNumber = param1Int2;
      this.mMinorChannelNumber = param1Int3;
    }
    
    private int toByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      int i = this.mChannelNumberFormat;
      int j = this.mMajorChannelNumber;
      param1ArrayOfbyte[param1Int] = (byte)(byte)(i << 2 | j >>> 8 & 0x3);
      param1ArrayOfbyte[param1Int + 1] = (byte)(byte)(j & 0xFF);
      HdmiRecordSources.shortToByteArray((short)this.mMinorChannelNumber, param1ArrayOfbyte, param1Int + 2);
      return 4;
    }
  }
  
  public static final class DigitalChannelData implements DigitalServiceIdentification {
    private final HdmiRecordSources.ChannelIdentifier mChannelIdentifier;
    
    private DigitalChannelData(HdmiRecordSources.ChannelIdentifier param1ChannelIdentifier) {
      this.mChannelIdentifier = param1ChannelIdentifier;
    }
    
    public static DigitalChannelData ofOneNumber(int param1Int) {
      return new DigitalChannelData(new HdmiRecordSources.ChannelIdentifier(1, 0, param1Int));
    }
    
    public static DigitalChannelData ofTwoNumbers(int param1Int1, int param1Int2) {
      return new DigitalChannelData(new HdmiRecordSources.ChannelIdentifier(2, param1Int1, param1Int2));
    }
    
    public int toByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      this.mChannelIdentifier.toByteArray(param1ArrayOfbyte, param1Int);
      param1ArrayOfbyte[param1Int + 4] = (byte)0;
      param1ArrayOfbyte[param1Int + 5] = (byte)0;
      return 6;
    }
  }
  
  private static interface DigitalServiceIdentification {
    int toByteArray(byte[] param1ArrayOfbyte, int param1Int);
  }
  
  @SystemApi
  public static final class DigitalServiceSource extends RecordSource {
    private static final int DIGITAL_SERVICE_IDENTIFIED_BY_CHANNEL = 1;
    
    private static final int DIGITAL_SERVICE_IDENTIFIED_BY_DIGITAL_ID = 0;
    
    static final int EXTRA_DATA_SIZE = 7;
    
    private final int mBroadcastSystem;
    
    private final HdmiRecordSources.DigitalServiceIdentification mIdentification;
    
    private final int mIdentificationMethod;
    
    private DigitalServiceSource(int param1Int1, int param1Int2, HdmiRecordSources.DigitalServiceIdentification param1DigitalServiceIdentification) {
      super(2, 7);
      this.mIdentificationMethod = param1Int1;
      this.mBroadcastSystem = param1Int2;
      this.mIdentification = param1DigitalServiceIdentification;
    }
    
    int extraParamToByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      param1ArrayOfbyte[param1Int] = (byte)(byte)(this.mIdentificationMethod << 7 | this.mBroadcastSystem & 0x7F);
      this.mIdentification.toByteArray(param1ArrayOfbyte, param1Int + 1);
      return 7;
    }
  }
  
  public static final class DvbData implements DigitalServiceIdentification {
    private final int mOriginalNetworkId;
    
    private final int mServiceId;
    
    private final int mTransportStreamId;
    
    public DvbData(int param1Int1, int param1Int2, int param1Int3) {
      this.mTransportStreamId = param1Int1;
      this.mServiceId = param1Int2;
      this.mOriginalNetworkId = param1Int3;
    }
    
    public int toByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      return HdmiRecordSources.threeFieldsToSixBytes(this.mTransportStreamId, this.mServiceId, this.mOriginalNetworkId, param1ArrayOfbyte, param1Int);
    }
  }
  
  @SystemApi
  public static final class ExternalPhysicalAddress extends RecordSource {
    static final int EXTRA_DATA_SIZE = 2;
    
    private final int mPhysicalAddress;
    
    private ExternalPhysicalAddress(int param1Int) {
      super(5, 2);
      this.mPhysicalAddress = param1Int;
    }
    
    int extraParamToByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      HdmiRecordSources.shortToByteArray((short)this.mPhysicalAddress, param1ArrayOfbyte, param1Int);
      return 2;
    }
  }
  
  @SystemApi
  public static final class ExternalPlugData extends RecordSource {
    static final int EXTRA_DATA_SIZE = 1;
    
    private final int mPlugNumber;
    
    private ExternalPlugData(int param1Int) {
      super(4, 1);
      this.mPlugNumber = param1Int;
    }
    
    int extraParamToByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      param1ArrayOfbyte[param1Int] = (byte)(byte)this.mPlugNumber;
      return 1;
    }
  }
  
  @SystemApi
  public static final class OwnSource extends RecordSource {
    private static final int EXTRA_DATA_SIZE = 0;
    
    private OwnSource() {
      super(1, 0);
    }
    
    int extraParamToByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      return 0;
    }
  }
  
  @SystemApi
  public static abstract class RecordSource {
    final int mExtraDataSize;
    
    final int mSourceType;
    
    RecordSource(int param1Int1, int param1Int2) {
      this.mSourceType = param1Int1;
      this.mExtraDataSize = param1Int2;
    }
    
    abstract int extraParamToByteArray(byte[] param1ArrayOfbyte, int param1Int);
    
    final int getDataSize(boolean param1Boolean) {
      int i = this.mExtraDataSize;
      int j = i;
      if (param1Boolean)
        j = i + 1; 
      return j;
    }
    
    final int toByteArray(boolean param1Boolean, byte[] param1ArrayOfbyte, int param1Int) {
      int i = param1Int;
      if (param1Boolean) {
        param1ArrayOfbyte[param1Int] = (byte)(byte)this.mSourceType;
        i = param1Int + 1;
      } 
      extraParamToByteArray(param1ArrayOfbyte, i);
      return getDataSize(param1Boolean);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiRecordSources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */