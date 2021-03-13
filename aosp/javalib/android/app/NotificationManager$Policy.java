package android.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.proto.ProtoOutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

public class Policy implements Parcelable {
  public static final int[] ALL_PRIORITY_CATEGORIES = new int[] { 32, 64, 128, 1, 2, 4, 8, 16, 256 };
  
  private static final int[] ALL_SUPPRESSED_EFFECTS = new int[] { 1, 2, 4, 8, 16, 32, 64, 128, 256 };
  
  public static final int CONVERSATION_SENDERS_ANYONE = 1;
  
  public static final int CONVERSATION_SENDERS_IMPORTANT = 2;
  
  public static final int CONVERSATION_SENDERS_NONE = 3;
  
  public static final int CONVERSATION_SENDERS_UNSET = -1;
  
  public static final Parcelable.Creator<Policy> CREATOR = new Parcelable.Creator<Policy>() {
      public NotificationManager.Policy createFromParcel(Parcel param2Parcel) {
        return new NotificationManager.Policy(param2Parcel);
      }
      
      public NotificationManager.Policy[] newArray(int param2Int) {
        return new NotificationManager.Policy[param2Int];
      }
    };
  
  public static final int PRIORITY_CATEGORY_ALARMS = 32;
  
  public static final int PRIORITY_CATEGORY_CALLS = 8;
  
  public static final int PRIORITY_CATEGORY_CONVERSATIONS = 256;
  
  public static final int PRIORITY_CATEGORY_EVENTS = 2;
  
  public static final int PRIORITY_CATEGORY_MEDIA = 64;
  
  public static final int PRIORITY_CATEGORY_MESSAGES = 4;
  
  public static final int PRIORITY_CATEGORY_REMINDERS = 1;
  
  public static final int PRIORITY_CATEGORY_REPEAT_CALLERS = 16;
  
  public static final int PRIORITY_CATEGORY_SYSTEM = 128;
  
  public static final int PRIORITY_SENDERS_ANY = 0;
  
  public static final int PRIORITY_SENDERS_CONTACTS = 1;
  
  public static final int PRIORITY_SENDERS_STARRED = 2;
  
  public static final int STATE_CHANNELS_BYPASSING_DND = 1;
  
  public static final int STATE_UNSET = -1;
  
  public static final int SUPPRESSED_EFFECTS_UNSET = -1;
  
  public static final int SUPPRESSED_EFFECT_AMBIENT = 128;
  
  public static final int SUPPRESSED_EFFECT_BADGE = 64;
  
  public static final int SUPPRESSED_EFFECT_FULL_SCREEN_INTENT = 4;
  
  public static final int SUPPRESSED_EFFECT_LIGHTS = 8;
  
  public static final int SUPPRESSED_EFFECT_NOTIFICATION_LIST = 256;
  
  public static final int SUPPRESSED_EFFECT_PEEK = 16;
  
  @Deprecated
  public static final int SUPPRESSED_EFFECT_SCREEN_OFF = 1;
  
  @Deprecated
  public static final int SUPPRESSED_EFFECT_SCREEN_ON = 2;
  
  public static final int SUPPRESSED_EFFECT_STATUS_BAR = 32;
  
  public final int priorityCallSenders;
  
  public final int priorityCategories;
  
  public final int priorityConversationSenders;
  
  public final int priorityMessageSenders;
  
  public final int state;
  
  public final int suppressedVisualEffects;
  
  public Policy(int paramInt1, int paramInt2, int paramInt3) {
    this(paramInt1, paramInt2, paramInt3, -1, -1, -1);
  }
  
  public Policy(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this(paramInt1, paramInt2, paramInt3, paramInt4, -1, -1);
  }
  
  public Policy(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    this(paramInt1, paramInt2, paramInt3, paramInt4, -1, paramInt5);
  }
  
  public Policy(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    this.priorityCategories = paramInt1;
    this.priorityCallSenders = paramInt2;
    this.priorityMessageSenders = paramInt3;
    this.suppressedVisualEffects = paramInt4;
    this.state = paramInt5;
    this.priorityConversationSenders = paramInt6;
  }
  
  public Policy(Parcel paramParcel) {
    this(paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt());
  }
  
  public static boolean areAllVisualEffectsSuppressed(int paramInt) {
    byte b = 0;
    while (true) {
      int[] arrayOfInt = ALL_SUPPRESSED_EFFECTS;
      if (b < arrayOfInt.length) {
        if ((paramInt & arrayOfInt[b]) == 0)
          return false; 
        b++;
        continue;
      } 
      return true;
    } 
  }
  
  private static void bitwiseToProtoEnum(ProtoOutputStream paramProtoOutputStream, long paramLong, int paramInt) {
    byte b = 1;
    while (paramInt > 0) {
      if ((paramInt & 0x1) == 1)
        paramProtoOutputStream.write(paramLong, b); 
      b++;
      paramInt >>>= 1;
    } 
  }
  
  public static String conversationSendersToString(int paramInt) {
    if (paramInt != -1) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("invalidConversationType{");
            stringBuilder.append(paramInt);
            stringBuilder.append("}");
            return stringBuilder.toString();
          } 
          return "none";
        } 
        return "important";
      } 
      return "anyone";
    } 
    return "unset";
  }
  
  private static String effectToString(int paramInt) {
    if (paramInt != -1) {
      if (paramInt != 4) {
        if (paramInt != 8) {
          if (paramInt != 16) {
            if (paramInt != 32) {
              if (paramInt != 64) {
                if (paramInt != 128) {
                  if (paramInt != 256) {
                    if (paramInt != 1) {
                      if (paramInt != 2) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("UNKNOWN_");
                        stringBuilder.append(paramInt);
                        return stringBuilder.toString();
                      } 
                      return "SUPPRESSED_EFFECT_SCREEN_ON";
                    } 
                    return "SUPPRESSED_EFFECT_SCREEN_OFF";
                  } 
                  return "SUPPRESSED_EFFECT_NOTIFICATION_LIST";
                } 
                return "SUPPRESSED_EFFECT_AMBIENT";
              } 
              return "SUPPRESSED_EFFECT_BADGE";
            } 
            return "SUPPRESSED_EFFECT_STATUS_BAR";
          } 
          return "SUPPRESSED_EFFECT_PEEK";
        } 
        return "SUPPRESSED_EFFECT_LIGHTS";
      } 
      return "SUPPRESSED_EFFECT_FULL_SCREEN_INTENT";
    } 
    return "SUPPRESSED_EFFECTS_UNSET";
  }
  
  public static int getAllSuppressedVisualEffects() {
    int i = 0;
    byte b = 0;
    while (true) {
      int[] arrayOfInt = ALL_SUPPRESSED_EFFECTS;
      if (b < arrayOfInt.length) {
        i |= arrayOfInt[b];
        b++;
        continue;
      } 
      return i;
    } 
  }
  
  public static String priorityCategoriesToString(int paramInt) {
    if (paramInt == 0)
      return ""; 
    StringBuilder stringBuilder = new StringBuilder();
    int i = 0;
    int j = paramInt;
    paramInt = i;
    while (true) {
      int[] arrayOfInt = ALL_PRIORITY_CATEGORIES;
      if (paramInt < arrayOfInt.length) {
        i = arrayOfInt[paramInt];
        if ((j & i) != 0) {
          if (stringBuilder.length() > 0)
            stringBuilder.append(','); 
          stringBuilder.append(priorityCategoryToString(i));
        } 
        j &= i;
        paramInt++;
        continue;
      } 
      if (j != 0) {
        if (stringBuilder.length() > 0)
          stringBuilder.append(','); 
        stringBuilder.append("PRIORITY_CATEGORY_UNKNOWN_");
        stringBuilder.append(j);
      } 
      return stringBuilder.toString();
    } 
  }
  
  private static String priorityCategoryToString(int paramInt) {
    if (paramInt != 1) {
      if (paramInt != 2) {
        if (paramInt != 4) {
          if (paramInt != 8) {
            if (paramInt != 16) {
              if (paramInt != 32) {
                if (paramInt != 64) {
                  if (paramInt != 128) {
                    if (paramInt != 256) {
                      StringBuilder stringBuilder = new StringBuilder();
                      stringBuilder.append("PRIORITY_CATEGORY_UNKNOWN_");
                      stringBuilder.append(paramInt);
                      return stringBuilder.toString();
                    } 
                    return "PRIORITY_CATEGORY_CONVERSATIONS";
                  } 
                  return "PRIORITY_CATEGORY_SYSTEM";
                } 
                return "PRIORITY_CATEGORY_MEDIA";
              } 
              return "PRIORITY_CATEGORY_ALARMS";
            } 
            return "PRIORITY_CATEGORY_REPEAT_CALLERS";
          } 
          return "PRIORITY_CATEGORY_CALLS";
        } 
        return "PRIORITY_CATEGORY_MESSAGES";
      } 
      return "PRIORITY_CATEGORY_EVENTS";
    } 
    return "PRIORITY_CATEGORY_REMINDERS";
  }
  
  public static String prioritySendersToString(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("PRIORITY_SENDERS_UNKNOWN_");
          stringBuilder.append(paramInt);
          return stringBuilder.toString();
        } 
        return "PRIORITY_SENDERS_STARRED";
      } 
      return "PRIORITY_SENDERS_CONTACTS";
    } 
    return "PRIORITY_SENDERS_ANY";
  }
  
  public static String suppressedEffectsToString(int paramInt) {
    if (paramInt <= 0)
      return ""; 
    StringBuilder stringBuilder = new StringBuilder();
    int i = 0;
    int j = paramInt;
    paramInt = i;
    while (true) {
      int[] arrayOfInt = ALL_SUPPRESSED_EFFECTS;
      if (paramInt < arrayOfInt.length) {
        i = arrayOfInt[paramInt];
        if ((j & i) != 0) {
          if (stringBuilder.length() > 0)
            stringBuilder.append(','); 
          stringBuilder.append(effectToString(i));
        } 
        j &= i;
        paramInt++;
        continue;
      } 
      if (j != 0) {
        if (stringBuilder.length() > 0)
          stringBuilder.append(','); 
        stringBuilder.append("UNKNOWN_");
        stringBuilder.append(j);
      } 
      return stringBuilder.toString();
    } 
  }
  
  private boolean suppressedVisualEffectsEqual(int paramInt1, int paramInt2) {
    boolean bool = true;
    if (paramInt1 == paramInt2)
      return true; 
    int i = paramInt1;
    if ((paramInt1 & 0x2) != 0)
      i = paramInt1 | 0x10; 
    paramInt1 = i;
    if ((i & 0x1) != 0)
      paramInt1 = i | 0x4 | 0x8 | 0x80; 
    i = paramInt2;
    if ((paramInt2 & 0x2) != 0)
      i = paramInt2 | 0x10; 
    paramInt2 = i;
    if ((i & 0x1) != 0)
      paramInt2 = i | 0x4 | 0x8 | 0x80; 
    if ((paramInt1 & 0x2) != (paramInt2 & 0x2)) {
      if ((paramInt1 & 0x2) != 0) {
        i = paramInt2;
      } else {
        i = paramInt1;
      } 
      if ((i & 0x10) == 0)
        return false; 
    } 
    if ((paramInt1 & 0x1) != (paramInt2 & 0x1)) {
      if ((paramInt1 & 0x1) != 0) {
        i = paramInt2;
      } else {
        i = paramInt1;
      } 
      if ((i & 0x4) == 0 || (i & 0x8) == 0 || (i & 0x80) == 0)
        return false; 
    } 
    if ((paramInt1 & 0xFFFFFFFD & 0xFFFFFFFE) != (paramInt2 & 0xFFFFFFFD & 0xFFFFFFFE))
      bool = false; 
    return bool;
  }
  
  private static int toggleEffects(int paramInt, int[] paramArrayOfint, boolean paramBoolean) {
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      int i = paramArrayOfint[b];
      if (paramBoolean) {
        paramInt |= i;
      } else {
        paramInt &= i;
      } 
    } 
    return paramInt;
  }
  
  public boolean allowAlarms() {
    boolean bool;
    if ((this.priorityCategories & 0x20) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean allowCalls() {
    boolean bool;
    if ((this.priorityCategories & 0x8) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int allowCallsFrom() {
    return this.priorityCallSenders;
  }
  
  public boolean allowConversations() {
    boolean bool;
    if ((this.priorityCategories & 0x100) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int allowConversationsFrom() {
    return this.priorityConversationSenders;
  }
  
  public boolean allowEvents() {
    boolean bool;
    if ((this.priorityCategories & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean allowMedia() {
    boolean bool;
    if ((this.priorityCategories & 0x40) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean allowMessages() {
    boolean bool;
    if ((this.priorityCategories & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int allowMessagesFrom() {
    return this.priorityMessageSenders;
  }
  
  public boolean allowReminders() {
    int i = this.priorityCategories;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public boolean allowRepeatCallers() {
    boolean bool;
    if ((this.priorityCategories & 0x10) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean allowSystem() {
    boolean bool;
    if ((this.priorityCategories & 0x80) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Policy copy() {
    Parcel parcel = Parcel.obtain();
    try {
      writeToParcel(parcel, 0);
      parcel.setDataPosition(0);
      return new Policy(parcel);
    } finally {
      parcel.recycle();
    } 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    bitwiseToProtoEnum(paramProtoOutputStream, 2259152797697L, this.priorityCategories);
    paramProtoOutputStream.write(1159641169922L, this.priorityCallSenders);
    paramProtoOutputStream.write(1159641169923L, this.priorityMessageSenders);
    bitwiseToProtoEnum(paramProtoOutputStream, 2259152797700L, this.suppressedVisualEffects);
    paramProtoOutputStream.end(paramLong);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof Policy;
    boolean bool1 = false;
    if (!bool)
      return false; 
    if (paramObject == this)
      return true; 
    paramObject = paramObject;
    if (((Policy)paramObject).priorityCategories == this.priorityCategories && ((Policy)paramObject).priorityCallSenders == this.priorityCallSenders && ((Policy)paramObject).priorityMessageSenders == this.priorityMessageSenders && suppressedVisualEffectsEqual(this.suppressedVisualEffects, ((Policy)paramObject).suppressedVisualEffects) && ((Policy)paramObject).state == this.state && ((Policy)paramObject).priorityConversationSenders == this.priorityConversationSenders)
      bool1 = true; 
    return bool1;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.priorityCategories), Integer.valueOf(this.priorityCallSenders), Integer.valueOf(this.priorityMessageSenders), Integer.valueOf(this.suppressedVisualEffects), Integer.valueOf(this.state), Integer.valueOf(this.priorityConversationSenders) });
  }
  
  public boolean showAmbient() {
    boolean bool;
    if ((this.suppressedVisualEffects & 0x80) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean showBadges() {
    boolean bool;
    if ((this.suppressedVisualEffects & 0x40) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean showFullScreenIntents() {
    boolean bool;
    if ((this.suppressedVisualEffects & 0x4) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean showInNotificationList() {
    boolean bool;
    if ((this.suppressedVisualEffects & 0x100) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean showLights() {
    boolean bool;
    if ((this.suppressedVisualEffects & 0x8) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean showPeeking() {
    boolean bool;
    if ((this.suppressedVisualEffects & 0x10) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean showStatusBarIcons() {
    boolean bool;
    if ((this.suppressedVisualEffects & 0x20) == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("NotificationManager.Policy[priorityCategories=");
    stringBuilder.append(priorityCategoriesToString(this.priorityCategories));
    stringBuilder.append(",priorityCallSenders=");
    stringBuilder.append(prioritySendersToString(this.priorityCallSenders));
    stringBuilder.append(",priorityMessageSenders=");
    stringBuilder.append(prioritySendersToString(this.priorityMessageSenders));
    stringBuilder.append(",priorityConvSenders=");
    stringBuilder.append(conversationSendersToString(this.priorityConversationSenders));
    stringBuilder.append(",suppressedVisualEffects=");
    stringBuilder.append(suppressedEffectsToString(this.suppressedVisualEffects));
    stringBuilder.append(",areChannelsBypassingDnd=");
    if ((this.state & 0x1) != 0) {
      str = "true";
    } else {
      str = "false";
    } 
    stringBuilder.append(str);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.priorityCategories);
    paramParcel.writeInt(this.priorityCallSenders);
    paramParcel.writeInt(this.priorityMessageSenders);
    paramParcel.writeInt(this.suppressedVisualEffects);
    paramParcel.writeInt(this.state);
    paramParcel.writeInt(this.priorityConversationSenders);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ConversationSenders {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PrioritySenders {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/NotificationManager$Policy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */