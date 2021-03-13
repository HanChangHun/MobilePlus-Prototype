package android.app.timezonedetector;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ShellCommand;
import android.text.TextUtils;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class TelephonyTimeZoneSuggestion implements Parcelable {
  public static final Parcelable.Creator<TelephonyTimeZoneSuggestion> CREATOR = new Parcelable.Creator<TelephonyTimeZoneSuggestion>() {
      public TelephonyTimeZoneSuggestion createFromParcel(Parcel param1Parcel) {
        return TelephonyTimeZoneSuggestion.createFromParcel(param1Parcel);
      }
      
      public TelephonyTimeZoneSuggestion[] newArray(int param1Int) {
        return new TelephonyTimeZoneSuggestion[param1Int];
      }
    };
  
  public static final int MATCH_TYPE_EMULATOR_ZONE_ID = 4;
  
  public static final int MATCH_TYPE_NA = 0;
  
  public static final int MATCH_TYPE_NETWORK_COUNTRY_AND_OFFSET = 3;
  
  public static final int MATCH_TYPE_NETWORK_COUNTRY_ONLY = 2;
  
  public static final int MATCH_TYPE_TEST_NETWORK_OFFSET_ONLY = 5;
  
  public static final int QUALITY_MULTIPLE_ZONES_WITH_DIFFERENT_OFFSETS = 3;
  
  public static final int QUALITY_MULTIPLE_ZONES_WITH_SAME_OFFSET = 2;
  
  public static final int QUALITY_NA = 0;
  
  public static final int QUALITY_SINGLE_ZONE = 1;
  
  private List<String> mDebugInfo;
  
  private final int mMatchType;
  
  private final int mQuality;
  
  private final int mSlotIndex;
  
  private final String mZoneId;
  
  private TelephonyTimeZoneSuggestion(Builder paramBuilder) {
    this.mSlotIndex = paramBuilder.mSlotIndex;
    this.mZoneId = paramBuilder.mZoneId;
    this.mMatchType = paramBuilder.mMatchType;
    this.mQuality = paramBuilder.mQuality;
    if (paramBuilder.mDebugInfo != null) {
      ArrayList arrayList = new ArrayList(paramBuilder.mDebugInfo);
    } else {
      paramBuilder = null;
    } 
    this.mDebugInfo = (List<String>)paramBuilder;
  }
  
  public static TelephonyTimeZoneSuggestion createEmptySuggestion(int paramInt, String paramString) {
    return (new Builder(paramInt)).addDebugInfo(paramString).build();
  }
  
  private static TelephonyTimeZoneSuggestion createFromParcel(Parcel paramParcel) {
    TelephonyTimeZoneSuggestion telephonyTimeZoneSuggestion = (new Builder(paramParcel.readInt())).setZoneId(paramParcel.readString()).setMatchType(paramParcel.readInt()).setQuality(paramParcel.readInt()).build();
    ArrayList<String> arrayList = paramParcel.readArrayList(TelephonyTimeZoneSuggestion.class.getClassLoader());
    if (arrayList != null)
      telephonyTimeZoneSuggestion.addDebugInfo(arrayList); 
    return telephonyTimeZoneSuggestion;
  }
  
  public static TelephonyTimeZoneSuggestion parseCommandLineArg(ShellCommand paramShellCommand) throws IllegalArgumentException {
    Integer integer1 = null;
    String str = null;
    Integer integer2 = null;
    Integer integer3 = null;
    while (true) {
      String str1 = paramShellCommand.getNextArg();
      if (str1 != null) {
        StringBuilder stringBuilder;
        byte b = -1;
        switch (str1.hashCode()) {
          case 2069298417:
            if (str1.equals("--slot_index"))
              b = 0; 
            break;
          case 2037196639:
            if (str1.equals("--quality"))
              b = 2; 
            break;
          case 1274807534:
            if (str1.equals("--zone_id"))
              b = 1; 
            break;
          case -174375148:
            if (str1.equals("--match_type"))
              b = 3; 
            break;
        } 
        if (b != 0) {
          if (b != 1) {
            if (b != 2) {
              if (b == 3) {
                integer3 = Integer.valueOf(parseMatchTypeCommandLineArg(paramShellCommand.getNextArgRequired()));
                continue;
              } 
              stringBuilder = new StringBuilder();
              stringBuilder.append("Unknown option: ");
              stringBuilder.append(str1);
              throw new IllegalArgumentException(stringBuilder.toString());
            } 
            integer2 = Integer.valueOf(parseQualityCommandLineArg(stringBuilder.getNextArgRequired()));
            continue;
          } 
          str = stringBuilder.getNextArgRequired();
          continue;
        } 
        integer1 = Integer.valueOf(Integer.parseInt(stringBuilder.getNextArgRequired()));
        continue;
      } 
      if (integer1 != null) {
        Builder builder = new Builder(integer1.intValue());
        if (!TextUtils.isEmpty(str) && !"_".equals(str))
          builder.setZoneId(str); 
        if (integer2 != null)
          builder.setQuality(integer2.intValue()); 
        if (integer3 != null)
          builder.setMatchType(integer3.intValue()); 
        builder.addDebugInfo("Command line injection");
        return builder.build();
      } 
      throw new IllegalArgumentException("No slotIndex specified.");
    } 
  }
  
  private static int parseMatchTypeCommandLineArg(String paramString) {
    byte b;
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 1336193813:
        if (paramString.equals("emulator")) {
          b = 0;
          break;
        } 
      case 957831062:
        if (paramString.equals("country")) {
          b = 2;
          break;
        } 
      case 556438401:
        if (paramString.equals("test_network")) {
          b = 3;
          break;
        } 
      case -1592694013:
        if (paramString.equals("country_with_offset")) {
          b = 1;
          break;
        } 
    } 
    if (b != 0) {
      if (b != 1) {
        if (b != 2) {
          if (b == 3)
            return 5; 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unrecognized match_type: ");
          stringBuilder.append(paramString);
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
        return 2;
      } 
      return 3;
    } 
    return 4;
  }
  
  private static int parseQualityCommandLineArg(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual hashCode : ()I
    //   4: istore_1
    //   5: iload_1
    //   6: ldc -902265784
    //   8: if_icmpeq -> 54
    //   11: iload_1
    //   12: ldc -650306251
    //   14: if_icmpeq -> 40
    //   17: iload_1
    //   18: ldc 1611832522
    //   20: if_icmpeq -> 26
    //   23: goto -> 68
    //   26: aload_0
    //   27: ldc 'multiple_different'
    //   29: invokevirtual equals : (Ljava/lang/Object;)Z
    //   32: ifeq -> 23
    //   35: iconst_2
    //   36: istore_1
    //   37: goto -> 70
    //   40: aload_0
    //   41: ldc 'multiple_same'
    //   43: invokevirtual equals : (Ljava/lang/Object;)Z
    //   46: ifeq -> 23
    //   49: iconst_1
    //   50: istore_1
    //   51: goto -> 70
    //   54: aload_0
    //   55: ldc 'single'
    //   57: invokevirtual equals : (Ljava/lang/Object;)Z
    //   60: ifeq -> 23
    //   63: iconst_0
    //   64: istore_1
    //   65: goto -> 70
    //   68: iconst_m1
    //   69: istore_1
    //   70: iload_1
    //   71: ifeq -> 121
    //   74: iload_1
    //   75: iconst_1
    //   76: if_icmpeq -> 119
    //   79: iload_1
    //   80: iconst_2
    //   81: if_icmpne -> 86
    //   84: iconst_3
    //   85: ireturn
    //   86: new java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial <init> : ()V
    //   93: astore_2
    //   94: aload_2
    //   95: ldc 'Unrecognized quality: '
    //   97: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: pop
    //   101: aload_2
    //   102: aload_0
    //   103: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: pop
    //   107: new java/lang/IllegalArgumentException
    //   110: dup
    //   111: aload_2
    //   112: invokevirtual toString : ()Ljava/lang/String;
    //   115: invokespecial <init> : (Ljava/lang/String;)V
    //   118: athrow
    //   119: iconst_2
    //   120: ireturn
    //   121: iconst_1
    //   122: ireturn
  }
  
  public static void printCommandLineOpts(PrintWriter paramPrintWriter) {
    paramPrintWriter.println("Telephony suggestion options:");
    paramPrintWriter.println("  --slot_index <number>");
    paramPrintWriter.println("  To withdraw a previous suggestion:");
    paramPrintWriter.println("    [--zone_id \"_\"]");
    paramPrintWriter.println("  To make a new suggestion:");
    paramPrintWriter.println("    --zone_id <Olson ID>");
    paramPrintWriter.println("    --quality <single|multiple_same|multiple_different>");
    paramPrintWriter.println("    --match_type <emulator|country_with_offset|country|test_network>");
    paramPrintWriter.println();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("See ");
    stringBuilder.append(TelephonyTimeZoneSuggestion.class.getName());
    stringBuilder.append(" for more information");
    paramPrintWriter.println(stringBuilder.toString());
  }
  
  public void addDebugInfo(String paramString) {
    if (this.mDebugInfo == null)
      this.mDebugInfo = new ArrayList<>(); 
    this.mDebugInfo.add(paramString);
  }
  
  public void addDebugInfo(List<String> paramList) {
    if (this.mDebugInfo == null)
      this.mDebugInfo = new ArrayList<>(paramList.size()); 
    this.mDebugInfo.addAll(paramList);
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
    if (this.mSlotIndex != ((TelephonyTimeZoneSuggestion)paramObject).mSlotIndex || this.mMatchType != ((TelephonyTimeZoneSuggestion)paramObject).mMatchType || this.mQuality != ((TelephonyTimeZoneSuggestion)paramObject).mQuality || !Objects.equals(this.mZoneId, ((TelephonyTimeZoneSuggestion)paramObject).mZoneId))
      bool = false; 
    return bool;
  }
  
  public List<String> getDebugInfo() {
    List<String> list = this.mDebugInfo;
    if (list == null) {
      list = Collections.emptyList();
    } else {
      list = Collections.unmodifiableList(list);
    } 
    return list;
  }
  
  public int getMatchType() {
    return this.mMatchType;
  }
  
  public int getQuality() {
    return this.mQuality;
  }
  
  public int getSlotIndex() {
    return this.mSlotIndex;
  }
  
  public String getZoneId() {
    return this.mZoneId;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.mSlotIndex), this.mZoneId, Integer.valueOf(this.mMatchType), Integer.valueOf(this.mQuality) });
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("TelephonyTimeZoneSuggestion{mSlotIndex=");
    stringBuilder.append(this.mSlotIndex);
    stringBuilder.append(", mZoneId='");
    stringBuilder.append(this.mZoneId);
    stringBuilder.append('\'');
    stringBuilder.append(", mMatchType=");
    stringBuilder.append(this.mMatchType);
    stringBuilder.append(", mQuality=");
    stringBuilder.append(this.mQuality);
    stringBuilder.append(", mDebugInfo=");
    stringBuilder.append(this.mDebugInfo);
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mSlotIndex);
    paramParcel.writeString(this.mZoneId);
    paramParcel.writeInt(this.mMatchType);
    paramParcel.writeInt(this.mQuality);
    paramParcel.writeList(this.mDebugInfo);
  }
  
  public static final class Builder {
    private List<String> mDebugInfo;
    
    private int mMatchType;
    
    private int mQuality;
    
    private final int mSlotIndex;
    
    private String mZoneId;
    
    public Builder(int param1Int) {
      this.mSlotIndex = param1Int;
    }
    
    public Builder addDebugInfo(String param1String) {
      if (this.mDebugInfo == null)
        this.mDebugInfo = new ArrayList<>(); 
      this.mDebugInfo.add(param1String);
      return this;
    }
    
    public TelephonyTimeZoneSuggestion build() {
      validate();
      return new TelephonyTimeZoneSuggestion(this);
    }
    
    public Builder setMatchType(int param1Int) {
      this.mMatchType = param1Int;
      return this;
    }
    
    public Builder setQuality(int param1Int) {
      this.mQuality = param1Int;
      return this;
    }
    
    public Builder setZoneId(String param1String) {
      this.mZoneId = param1String;
      return this;
    }
    
    void validate() {
      int i = this.mQuality;
      int j = this.mMatchType;
      if (this.mZoneId == null) {
        if (i != 0 || j != 0) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Invalid quality or match type for null zone ID. quality=");
          stringBuilder.append(i);
          stringBuilder.append(", matchType=");
          stringBuilder.append(j);
          throw new RuntimeException(stringBuilder.toString());
        } 
      } else {
        boolean bool2;
        boolean bool1 = false;
        if (i == 1 || i == 2 || i == 3) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        if (j == 2 || j == 3 || j == 4 || j == 5)
          bool1 = true; 
        if (!bool2 || !bool1) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Invalid quality or match type with zone ID. quality=");
          stringBuilder.append(i);
          stringBuilder.append(", matchType=");
          stringBuilder.append(j);
          throw new RuntimeException(stringBuilder.toString());
        } 
      } 
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface MatchType {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Quality {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezonedetector/TelephonyTimeZoneSuggestion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */