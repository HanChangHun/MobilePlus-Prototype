package android.app.admin;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

public final class SystemUpdatePolicy implements Parcelable {
  public static final Parcelable.Creator<SystemUpdatePolicy> CREATOR = new Parcelable.Creator<SystemUpdatePolicy>() {
      public SystemUpdatePolicy createFromParcel(Parcel param1Parcel) {
        SystemUpdatePolicy systemUpdatePolicy = new SystemUpdatePolicy();
        SystemUpdatePolicy.access$202(systemUpdatePolicy, param1Parcel.readInt());
        SystemUpdatePolicy.access$302(systemUpdatePolicy, param1Parcel.readInt());
        SystemUpdatePolicy.access$402(systemUpdatePolicy, param1Parcel.readInt());
        int i = param1Parcel.readInt();
        systemUpdatePolicy.mFreezePeriods.ensureCapacity(i);
        for (byte b = 0; b < i; b++) {
          MonthDay monthDay1 = MonthDay.of(param1Parcel.readInt(), param1Parcel.readInt());
          MonthDay monthDay2 = MonthDay.of(param1Parcel.readInt(), param1Parcel.readInt());
          systemUpdatePolicy.mFreezePeriods.add(new FreezePeriod(monthDay1, monthDay2));
        } 
        return systemUpdatePolicy;
      }
      
      public SystemUpdatePolicy[] newArray(int param1Int) {
        return new SystemUpdatePolicy[param1Int];
      }
    };
  
  static final int FREEZE_PERIOD_MAX_LENGTH = 90;
  
  static final int FREEZE_PERIOD_MIN_SEPARATION = 60;
  
  private static final String KEY_FREEZE_END = "end";
  
  private static final String KEY_FREEZE_START = "start";
  
  private static final String KEY_FREEZE_TAG = "freeze";
  
  private static final String KEY_INSTALL_WINDOW_END = "install_window_end";
  
  private static final String KEY_INSTALL_WINDOW_START = "install_window_start";
  
  private static final String KEY_POLICY_TYPE = "policy_type";
  
  private static final String TAG = "SystemUpdatePolicy";
  
  public static final int TYPE_INSTALL_AUTOMATIC = 1;
  
  public static final int TYPE_INSTALL_WINDOWED = 2;
  
  @SystemApi
  public static final int TYPE_PAUSE = 4;
  
  public static final int TYPE_POSTPONE = 3;
  
  private static final int TYPE_UNKNOWN = -1;
  
  private static final int WINDOW_BOUNDARY = 1440;
  
  private final ArrayList<FreezePeriod> mFreezePeriods = new ArrayList<>();
  
  private int mMaintenanceWindowEnd;
  
  private int mMaintenanceWindowStart;
  
  private int mPolicyType = -1;
  
  private SystemUpdatePolicy() {}
  
  public static SystemUpdatePolicy createAutomaticInstallPolicy() {
    SystemUpdatePolicy systemUpdatePolicy = new SystemUpdatePolicy();
    systemUpdatePolicy.mPolicyType = 1;
    return systemUpdatePolicy;
  }
  
  public static SystemUpdatePolicy createPostponeInstallPolicy() {
    SystemUpdatePolicy systemUpdatePolicy = new SystemUpdatePolicy();
    systemUpdatePolicy.mPolicyType = 3;
    return systemUpdatePolicy;
  }
  
  public static SystemUpdatePolicy createWindowedInstallPolicy(int paramInt1, int paramInt2) {
    if (paramInt1 >= 0 && paramInt1 < 1440 && paramInt2 >= 0 && paramInt2 < 1440) {
      SystemUpdatePolicy systemUpdatePolicy = new SystemUpdatePolicy();
      systemUpdatePolicy.mPolicyType = 2;
      systemUpdatePolicy.mMaintenanceWindowStart = paramInt1;
      systemUpdatePolicy.mMaintenanceWindowEnd = paramInt2;
      return systemUpdatePolicy;
    } 
    throw new IllegalArgumentException("startTime and endTime must be inside [0, 1440)");
  }
  
  private static long dateToMillis(LocalDate paramLocalDate) {
    return LocalDateTime.of(paramLocalDate, LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
  }
  
  private InstallationOption getInstallationOptionRegardlessFreezeAt(long paramLong) {
    int i = this.mPolicyType;
    if (i == 1 || i == 3)
      return new InstallationOption(this.mPolicyType, Long.MAX_VALUE); 
    if (i == 2) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(paramLong);
      long l1 = TimeUnit.HOURS.toMillis(calendar.get(11)) + TimeUnit.MINUTES.toMillis(calendar.get(12)) + TimeUnit.SECONDS.toMillis(calendar.get(13)) + calendar.get(14);
      paramLong = TimeUnit.MINUTES.toMillis(this.mMaintenanceWindowStart);
      long l2 = TimeUnit.MINUTES.toMillis(this.mMaintenanceWindowEnd);
      long l3 = TimeUnit.DAYS.toMillis(1L);
      return ((paramLong <= l1 && l1 <= l2) || (paramLong > l2 && (paramLong <= l1 || l1 <= l2))) ? new InstallationOption(1, (l2 - l1 + l3) % l3) : new InstallationOption(4, (paramLong - l1 + l3) % l3);
    } 
    throw new RuntimeException("Unknown policy type");
  }
  
  private static LocalDate millisToDate(long paramLong) {
    return Instant.ofEpochMilli(paramLong).atZone(ZoneId.systemDefault()).toLocalDate();
  }
  
  public static SystemUpdatePolicy restoreFromXml(XmlPullParser paramXmlPullParser) {
    try {
      SystemUpdatePolicy systemUpdatePolicy = new SystemUpdatePolicy();
      this();
      String str = paramXmlPullParser.getAttributeValue(null, "policy_type");
      if (str != null) {
        systemUpdatePolicy.mPolicyType = Integer.parseInt(str);
        str = paramXmlPullParser.getAttributeValue(null, "install_window_start");
        if (str != null)
          systemUpdatePolicy.mMaintenanceWindowStart = Integer.parseInt(str); 
        str = paramXmlPullParser.getAttributeValue(null, "install_window_end");
        if (str != null)
          systemUpdatePolicy.mMaintenanceWindowEnd = Integer.parseInt(str); 
        int i = paramXmlPullParser.getDepth();
        while (true) {
          int j = paramXmlPullParser.next();
          if (j != 1 && (j != 3 || paramXmlPullParser.getDepth() > i)) {
            if (j == 3 || j == 4 || !paramXmlPullParser.getName().equals("freeze"))
              continue; 
            ArrayList<FreezePeriod> arrayList = systemUpdatePolicy.mFreezePeriods;
            FreezePeriod freezePeriod = new FreezePeriod();
            this(MonthDay.parse(paramXmlPullParser.getAttributeValue(null, "start")), MonthDay.parse(paramXmlPullParser.getAttributeValue(null, "end")));
            arrayList.add(freezePeriod);
            continue;
          } 
          break;
        } 
        return systemUpdatePolicy;
      } 
    } catch (NumberFormatException|org.xmlpull.v1.XmlPullParserException|IOException numberFormatException) {
      Log.w("SystemUpdatePolicy", "Load xml failed", numberFormatException);
    } 
    return null;
  }
  
  private static LocalDate roundUpLeapDay(LocalDate paramLocalDate) {
    return (paramLocalDate.isLeapYear() && paramLocalDate.getMonthValue() == 2 && paramLocalDate.getDayOfMonth() == 28) ? paramLocalDate.plusDays(1L) : paramLocalDate;
  }
  
  private long timeUntilNextFreezePeriod(long paramLong) {
    LocalDate localDate3;
    List<FreezePeriod> list = FreezePeriod.canonicalizePeriods(this.mFreezePeriods);
    LocalDate localDate1 = millisToDate(paramLong);
    FreezePeriod freezePeriod = null;
    Iterator<FreezePeriod> iterator = list.iterator();
    while (true) {
      FreezePeriod freezePeriod1 = freezePeriod;
      if (iterator.hasNext()) {
        freezePeriod1 = iterator.next();
        if (freezePeriod1.after(localDate1)) {
          localDate3 = (LocalDate)(freezePeriod1.toCurrentOrFutureRealDates(localDate1)).first;
          break;
        } 
        if (!localDate3.contains(localDate1))
          continue; 
        throw new IllegalArgumentException("Given date is inside a freeze period");
      } 
      break;
    } 
    LocalDate localDate2 = localDate3;
    if (localDate3 == null)
      localDate2 = (LocalDate)(((FreezePeriod)list.get(0)).toCurrentOrFutureRealDates(localDate1)).first; 
    return dateToMillis(localDate2) - paramLong;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Pair<LocalDate, LocalDate> getCurrentFreezePeriod(LocalDate paramLocalDate) {
    for (FreezePeriod freezePeriod : this.mFreezePeriods) {
      if (freezePeriod.contains(paramLocalDate))
        return freezePeriod.toCurrentOrFutureRealDates(paramLocalDate); 
    } 
    return null;
  }
  
  public List<FreezePeriod> getFreezePeriods() {
    return Collections.unmodifiableList(this.mFreezePeriods);
  }
  
  public int getInstallWindowEnd() {
    return (this.mPolicyType == 2) ? this.mMaintenanceWindowEnd : -1;
  }
  
  public int getInstallWindowStart() {
    return (this.mPolicyType == 2) ? this.mMaintenanceWindowStart : -1;
  }
  
  @SystemApi
  public InstallationOption getInstallationOptionAt(long paramLong) {
    Pair<LocalDate, LocalDate> pair = getCurrentFreezePeriod(millisToDate(paramLong));
    if (pair != null)
      return new InstallationOption(4, dateToMillis(roundUpLeapDay((LocalDate)pair.second).plusDays(1L)) - paramLong); 
    InstallationOption installationOption = getInstallationOptionRegardlessFreezeAt(paramLong);
    if (this.mFreezePeriods.size() > 0)
      installationOption.limitEffectiveTime(timeUntilNextFreezePeriod(paramLong)); 
    return installationOption;
  }
  
  public int getPolicyType() {
    return this.mPolicyType;
  }
  
  public boolean isValid() {
    try {
      validateType();
      validateFreezePeriods();
      return true;
    } catch (IllegalArgumentException illegalArgumentException) {
      return false;
    } 
  }
  
  public void saveToXml(XmlSerializer paramXmlSerializer) throws IOException {
    paramXmlSerializer.attribute(null, "policy_type", Integer.toString(this.mPolicyType));
    paramXmlSerializer.attribute(null, "install_window_start", Integer.toString(this.mMaintenanceWindowStart));
    paramXmlSerializer.attribute(null, "install_window_end", Integer.toString(this.mMaintenanceWindowEnd));
    for (byte b = 0; b < this.mFreezePeriods.size(); b++) {
      FreezePeriod freezePeriod = this.mFreezePeriods.get(b);
      paramXmlSerializer.startTag(null, "freeze");
      paramXmlSerializer.attribute(null, "start", freezePeriod.getStart().toString());
      paramXmlSerializer.attribute(null, "end", freezePeriod.getEnd().toString());
      paramXmlSerializer.endTag(null, "freeze");
    } 
  }
  
  public SystemUpdatePolicy setFreezePeriods(List<FreezePeriod> paramList) {
    FreezePeriod.validatePeriods(paramList);
    this.mFreezePeriods.clear();
    this.mFreezePeriods.addAll(paramList);
    return this;
  }
  
  public String toString() {
    return String.format("SystemUpdatePolicy (type: %d, windowStart: %d, windowEnd: %d, freezes: [%s])", new Object[] { Integer.valueOf(this.mPolicyType), Integer.valueOf(this.mMaintenanceWindowStart), Integer.valueOf(this.mMaintenanceWindowEnd), this.mFreezePeriods.stream().map((Function)_$$Lambda$SystemUpdatePolicy$cfrSWvZcAu30PIPvKA2LGQbmTew.INSTANCE).collect(Collectors.joining(",")) });
  }
  
  public void validateAgainstPreviousFreezePeriod(LocalDate paramLocalDate1, LocalDate paramLocalDate2, LocalDate paramLocalDate3) {
    FreezePeriod.validateAgainstPreviousFreezePeriod(this.mFreezePeriods, paramLocalDate1, paramLocalDate2, paramLocalDate3);
  }
  
  public void validateFreezePeriods() {
    FreezePeriod.validatePeriods(this.mFreezePeriods);
  }
  
  public void validateType() {
    int i = this.mPolicyType;
    if (i == 1 || i == 3)
      return; 
    if (i == 2) {
      i = this.mMaintenanceWindowStart;
      if (i >= 0 && i < 1440) {
        i = this.mMaintenanceWindowEnd;
        if (i >= 0 && i < 1440)
          return; 
      } 
      throw new IllegalArgumentException("Invalid maintenance window");
    } 
    throw new IllegalArgumentException("Invalid system update policy type.");
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mPolicyType);
    paramParcel.writeInt(this.mMaintenanceWindowStart);
    paramParcel.writeInt(this.mMaintenanceWindowEnd);
    int i = this.mFreezePeriods.size();
    paramParcel.writeInt(i);
    for (paramInt = 0; paramInt < i; paramInt++) {
      FreezePeriod freezePeriod = this.mFreezePeriods.get(paramInt);
      paramParcel.writeInt(freezePeriod.getStart().getMonthValue());
      paramParcel.writeInt(freezePeriod.getStart().getDayOfMonth());
      paramParcel.writeInt(freezePeriod.getEnd().getMonthValue());
      paramParcel.writeInt(freezePeriod.getEnd().getDayOfMonth());
    } 
  }
  
  @SystemApi
  public static class InstallationOption {
    private long mEffectiveTime;
    
    private final int mType;
    
    InstallationOption(int param1Int, long param1Long) {
      this.mType = param1Int;
      this.mEffectiveTime = param1Long;
    }
    
    public long getEffectiveTime() {
      return this.mEffectiveTime;
    }
    
    public int getType() {
      return this.mType;
    }
    
    protected void limitEffectiveTime(long param1Long) {
      this.mEffectiveTime = Long.min(this.mEffectiveTime, param1Long);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    static @interface InstallationOptionType {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface InstallationOptionType {}
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface SystemUpdatePolicyType {}
  
  public static final class ValidationFailedException extends IllegalArgumentException implements Parcelable {
    public static final Parcelable.Creator<ValidationFailedException> CREATOR = new Parcelable.Creator<ValidationFailedException>() {
        public SystemUpdatePolicy.ValidationFailedException createFromParcel(Parcel param2Parcel) {
          return new SystemUpdatePolicy.ValidationFailedException(param2Parcel.readInt(), param2Parcel.readString());
        }
        
        public SystemUpdatePolicy.ValidationFailedException[] newArray(int param2Int) {
          return new SystemUpdatePolicy.ValidationFailedException[param2Int];
        }
      };
    
    public static final int ERROR_COMBINED_FREEZE_PERIOD_TOO_CLOSE = 6;
    
    public static final int ERROR_COMBINED_FREEZE_PERIOD_TOO_LONG = 5;
    
    public static final int ERROR_DUPLICATE_OR_OVERLAP = 2;
    
    public static final int ERROR_NEW_FREEZE_PERIOD_TOO_CLOSE = 4;
    
    public static final int ERROR_NEW_FREEZE_PERIOD_TOO_LONG = 3;
    
    public static final int ERROR_NONE = 0;
    
    public static final int ERROR_UNKNOWN = 1;
    
    private final int mErrorCode;
    
    private ValidationFailedException(int param1Int, String param1String) {
      super(param1String);
      this.mErrorCode = param1Int;
    }
    
    public static ValidationFailedException combinedPeriodTooClose(String param1String) {
      return new ValidationFailedException(6, param1String);
    }
    
    public static ValidationFailedException combinedPeriodTooLong(String param1String) {
      return new ValidationFailedException(5, param1String);
    }
    
    public static ValidationFailedException duplicateOrOverlapPeriods() {
      return new ValidationFailedException(2, "Found duplicate or overlapping periods");
    }
    
    public static ValidationFailedException freezePeriodTooClose(String param1String) {
      return new ValidationFailedException(4, param1String);
    }
    
    public static ValidationFailedException freezePeriodTooLong(String param1String) {
      return new ValidationFailedException(3, param1String);
    }
    
    public int describeContents() {
      return 0;
    }
    
    public int getErrorCode() {
      return this.mErrorCode;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.mErrorCode);
      param1Parcel.writeString(getMessage());
    }
    
    @Retention(RetentionPolicy.SOURCE)
    static @interface ValidationFailureType {}
  }
  
  class null implements Parcelable.Creator<ValidationFailedException> {
    public SystemUpdatePolicy.ValidationFailedException createFromParcel(Parcel param1Parcel) {
      return new SystemUpdatePolicy.ValidationFailedException(param1Parcel.readInt(), param1Parcel.readString());
    }
    
    public SystemUpdatePolicy.ValidationFailedException[] newArray(int param1Int) {
      return new SystemUpdatePolicy.ValidationFailedException[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface ValidationFailureType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/SystemUpdatePolicy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */