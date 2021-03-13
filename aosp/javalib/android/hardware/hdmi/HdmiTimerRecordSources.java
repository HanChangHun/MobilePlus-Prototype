package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.util.Log;

@SystemApi
public class HdmiTimerRecordSources {
  private static final int EXTERNAL_SOURCE_SPECIFIER_EXTERNAL_PHYSICAL_ADDRESS = 5;
  
  private static final int EXTERNAL_SOURCE_SPECIFIER_EXTERNAL_PLUG = 4;
  
  public static final int RECORDING_SEQUENCE_REPEAT_FRIDAY = 32;
  
  private static final int RECORDING_SEQUENCE_REPEAT_MASK = 127;
  
  public static final int RECORDING_SEQUENCE_REPEAT_MONDAY = 2;
  
  public static final int RECORDING_SEQUENCE_REPEAT_ONCE_ONLY = 0;
  
  public static final int RECORDING_SEQUENCE_REPEAT_SATUREDAY = 64;
  
  public static final int RECORDING_SEQUENCE_REPEAT_SUNDAY = 1;
  
  public static final int RECORDING_SEQUENCE_REPEAT_THURSDAY = 16;
  
  public static final int RECORDING_SEQUENCE_REPEAT_TUESDAY = 4;
  
  public static final int RECORDING_SEQUENCE_REPEAT_WEDNESDAY = 8;
  
  private static final String TAG = "HdmiTimerRecordingSources";
  
  private static void checkDurationValue(int paramInt1, int paramInt2) {
    if (paramInt1 >= 0 && paramInt1 <= 99) {
      if (paramInt2 >= 0 && paramInt2 <= 59)
        return; 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("minute should be in rage of [0, 59]:");
      stringBuilder1.append(paramInt2);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Hour should be in rage of [0, 99]:");
    stringBuilder.append(paramInt1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static void checkTimeValue(int paramInt1, int paramInt2) {
    if (paramInt1 >= 0 && paramInt1 <= 23) {
      if (paramInt2 >= 0 && paramInt2 <= 59)
        return; 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Minute should be in rage of [0, 59]:");
      stringBuilder1.append(paramInt2);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Hour should be in rage of [0, 23]:");
    stringBuilder.append(paramInt1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  @SystemApi
  public static boolean checkTimerRecordSource(int paramInt, byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length - 7;
    boolean bool1 = true;
    boolean bool2 = true;
    boolean bool3 = true;
    boolean bool4 = true;
    if (paramInt != 1) {
      if (paramInt != 2) {
        if (paramInt != 3)
          return false; 
        paramInt = paramArrayOfbyte[7];
        if (paramInt == 4) {
          if (2 != i)
            bool4 = false; 
          return bool4;
        } 
        if (paramInt == 5) {
          if (3 == i) {
            bool4 = bool1;
          } else {
            bool4 = false;
          } 
          return bool4;
        } 
        return false;
      } 
      if (4 == i) {
        bool4 = bool2;
      } else {
        bool4 = false;
      } 
      return bool4;
    } 
    if (7 == i) {
      bool4 = bool3;
    } else {
      bool4 = false;
    } 
    return bool4;
  }
  
  private static void checkTimerRecordSourceInputs(TimerInfo paramTimerInfo, HdmiRecordSources.RecordSource paramRecordSource) {
    if (paramTimerInfo != null) {
      if (paramRecordSource != null)
        return; 
      Log.w("HdmiTimerRecordingSources", "source should not be null.");
      throw new IllegalArgumentException("source should not be null.");
    } 
    Log.w("HdmiTimerRecordingSources", "TimerInfo should not be null.");
    throw new IllegalArgumentException("TimerInfo should not be null.");
  }
  
  public static Duration durationOf(int paramInt1, int paramInt2) {
    checkDurationValue(paramInt1, paramInt2);
    return new Duration(paramInt1, paramInt2);
  }
  
  public static TimerRecordSource ofAnalogueSource(TimerInfo paramTimerInfo, HdmiRecordSources.AnalogueServiceSource paramAnalogueServiceSource) {
    checkTimerRecordSourceInputs(paramTimerInfo, paramAnalogueServiceSource);
    return new TimerRecordSource(paramTimerInfo, paramAnalogueServiceSource);
  }
  
  public static TimerRecordSource ofDigitalSource(TimerInfo paramTimerInfo, HdmiRecordSources.DigitalServiceSource paramDigitalServiceSource) {
    checkTimerRecordSourceInputs(paramTimerInfo, paramDigitalServiceSource);
    return new TimerRecordSource(paramTimerInfo, paramDigitalServiceSource);
  }
  
  public static TimerRecordSource ofExternalPhysicalAddress(TimerInfo paramTimerInfo, HdmiRecordSources.ExternalPhysicalAddress paramExternalPhysicalAddress) {
    checkTimerRecordSourceInputs(paramTimerInfo, paramExternalPhysicalAddress);
    return new TimerRecordSource(paramTimerInfo, new ExternalSourceDecorator(paramExternalPhysicalAddress, 5));
  }
  
  public static TimerRecordSource ofExternalPlug(TimerInfo paramTimerInfo, HdmiRecordSources.ExternalPlugData paramExternalPlugData) {
    checkTimerRecordSourceInputs(paramTimerInfo, paramExternalPlugData);
    return new TimerRecordSource(paramTimerInfo, new ExternalSourceDecorator(paramExternalPlugData, 4));
  }
  
  public static Time timeOf(int paramInt1, int paramInt2) {
    checkTimeValue(paramInt1, paramInt2);
    return new Time(paramInt1, paramInt2);
  }
  
  public static TimerInfo timerInfoOf(int paramInt1, int paramInt2, Time paramTime, Duration paramDuration, int paramInt3) {
    if (paramInt1 >= 0 && paramInt1 <= 31) {
      if (paramInt2 >= 1 && paramInt2 <= 12) {
        checkTimeValue(paramTime.mHour, paramTime.mMinute);
        checkDurationValue(paramDuration.mHour, paramDuration.mMinute);
        if (paramInt3 == 0 || (paramInt3 & 0xFFFFFF80) == 0)
          return new TimerInfo(paramInt1, paramInt2, paramTime, paramDuration, paramInt3); 
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Invalid reecording sequence value:");
        stringBuilder2.append(paramInt3);
        throw new IllegalArgumentException(stringBuilder2.toString());
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Month of year should be in range of [1, 12]:");
      stringBuilder1.append(paramInt2);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Day of month should be in range of [0, 31]:");
    stringBuilder.append(paramInt1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  @SystemApi
  public static final class Duration extends TimeUnit {
    private Duration(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
  }
  
  private static class ExternalSourceDecorator extends HdmiRecordSources.RecordSource {
    private final int mExternalSourceSpecifier;
    
    private final HdmiRecordSources.RecordSource mRecordSource;
    
    private ExternalSourceDecorator(HdmiRecordSources.RecordSource param1RecordSource, int param1Int) {
      super(param1RecordSource.mSourceType, param1RecordSource.getDataSize(false) + 1);
      this.mRecordSource = param1RecordSource;
      this.mExternalSourceSpecifier = param1Int;
    }
    
    int extraParamToByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      param1ArrayOfbyte[param1Int] = (byte)(byte)this.mExternalSourceSpecifier;
      this.mRecordSource.toByteArray(false, param1ArrayOfbyte, param1Int + 1);
      return getDataSize(false);
    }
  }
  
  @SystemApi
  public static final class Time extends TimeUnit {
    private Time(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
  }
  
  static class TimeUnit {
    final int mHour;
    
    final int mMinute;
    
    TimeUnit(int param1Int1, int param1Int2) {
      this.mHour = param1Int1;
      this.mMinute = param1Int2;
    }
    
    static byte toBcdByte(int param1Int) {
      return (byte)(param1Int / 10 % 10 << 4 | param1Int % 10);
    }
    
    int toByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      param1ArrayOfbyte[param1Int] = toBcdByte(this.mHour);
      param1ArrayOfbyte[param1Int + 1] = toBcdByte(this.mMinute);
      return 2;
    }
  }
  
  @SystemApi
  public static final class TimerInfo {
    private static final int BASIC_INFO_SIZE = 7;
    
    private static final int DAY_OF_MONTH_SIZE = 1;
    
    private static final int DURATION_SIZE = 2;
    
    private static final int MONTH_OF_YEAR_SIZE = 1;
    
    private static final int RECORDING_SEQUENCE_SIZE = 1;
    
    private static final int START_TIME_SIZE = 2;
    
    private final int mDayOfMonth;
    
    private final HdmiTimerRecordSources.Duration mDuration;
    
    private final int mMonthOfYear;
    
    private final int mRecordingSequence;
    
    private final HdmiTimerRecordSources.Time mStartTime;
    
    private TimerInfo(int param1Int1, int param1Int2, HdmiTimerRecordSources.Time param1Time, HdmiTimerRecordSources.Duration param1Duration, int param1Int3) {
      this.mDayOfMonth = param1Int1;
      this.mMonthOfYear = param1Int2;
      this.mStartTime = param1Time;
      this.mDuration = param1Duration;
      this.mRecordingSequence = param1Int3;
    }
    
    int getDataSize() {
      return 7;
    }
    
    int toByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      param1ArrayOfbyte[param1Int] = (byte)(byte)this.mDayOfMonth;
      param1ArrayOfbyte[++param1Int] = (byte)(byte)this.mMonthOfYear;
      param1Int = ++param1Int + this.mStartTime.toByteArray(param1ArrayOfbyte, param1Int);
      param1ArrayOfbyte[param1Int + this.mDuration.toByteArray(param1ArrayOfbyte, param1Int)] = (byte)(byte)this.mRecordingSequence;
      return getDataSize();
    }
  }
  
  @SystemApi
  public static final class TimerRecordSource {
    private final HdmiRecordSources.RecordSource mRecordSource;
    
    private final HdmiTimerRecordSources.TimerInfo mTimerInfo;
    
    private TimerRecordSource(HdmiTimerRecordSources.TimerInfo param1TimerInfo, HdmiRecordSources.RecordSource param1RecordSource) {
      this.mTimerInfo = param1TimerInfo;
      this.mRecordSource = param1RecordSource;
    }
    
    int getDataSize() {
      return this.mTimerInfo.getDataSize() + this.mRecordSource.getDataSize(false);
    }
    
    int toByteArray(byte[] param1ArrayOfbyte, int param1Int) {
      int i = this.mTimerInfo.toByteArray(param1ArrayOfbyte, param1Int);
      this.mRecordSource.toByteArray(false, param1ArrayOfbyte, param1Int + i);
      return getDataSize();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/HdmiTimerRecordSources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */