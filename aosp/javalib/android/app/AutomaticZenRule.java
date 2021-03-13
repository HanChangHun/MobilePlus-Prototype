package android.app;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.service.notification.ZenPolicy;
import java.util.Objects;

public final class AutomaticZenRule implements Parcelable {
  public static final Parcelable.Creator<AutomaticZenRule> CREATOR = new Parcelable.Creator<AutomaticZenRule>() {
      public AutomaticZenRule createFromParcel(Parcel param1Parcel) {
        return new AutomaticZenRule(param1Parcel);
      }
      
      public AutomaticZenRule[] newArray(int param1Int) {
        return new AutomaticZenRule[param1Int];
      }
    };
  
  private static final int DISABLED = 0;
  
  private static final int ENABLED = 1;
  
  private Uri conditionId;
  
  private ComponentName configurationActivity;
  
  private long creationTime;
  
  private boolean enabled;
  
  private int interruptionFilter;
  
  private boolean mModified;
  
  private ZenPolicy mZenPolicy;
  
  private String name;
  
  private ComponentName owner;
  
  public AutomaticZenRule(Parcel paramParcel) {
    boolean bool1 = false;
    this.enabled = false;
    this.mModified = false;
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.enabled = bool2;
    if (paramParcel.readInt() == 1)
      this.name = paramParcel.readString(); 
    this.interruptionFilter = paramParcel.readInt();
    this.conditionId = (Uri)paramParcel.readParcelable(null);
    this.owner = (ComponentName)paramParcel.readParcelable(null);
    this.configurationActivity = (ComponentName)paramParcel.readParcelable(null);
    this.creationTime = paramParcel.readLong();
    this.mZenPolicy = (ZenPolicy)paramParcel.readParcelable(null);
    boolean bool2 = bool1;
    if (paramParcel.readInt() == 1)
      bool2 = true; 
    this.mModified = bool2;
  }
  
  public AutomaticZenRule(String paramString, ComponentName paramComponentName1, ComponentName paramComponentName2, Uri paramUri, ZenPolicy paramZenPolicy, int paramInt, boolean paramBoolean) {
    this.enabled = false;
    this.mModified = false;
    this.name = paramString;
    this.owner = paramComponentName1;
    this.configurationActivity = paramComponentName2;
    this.conditionId = paramUri;
    this.interruptionFilter = paramInt;
    this.enabled = paramBoolean;
    this.mZenPolicy = paramZenPolicy;
  }
  
  public AutomaticZenRule(String paramString, ComponentName paramComponentName1, ComponentName paramComponentName2, Uri paramUri, ZenPolicy paramZenPolicy, int paramInt, boolean paramBoolean, long paramLong) {
    this(paramString, paramComponentName1, paramComponentName2, paramUri, paramZenPolicy, paramInt, paramBoolean);
    this.creationTime = paramLong;
  }
  
  @Deprecated
  public AutomaticZenRule(String paramString, ComponentName paramComponentName, Uri paramUri, int paramInt, boolean paramBoolean) {
    this(paramString, paramComponentName, null, paramUri, null, paramInt, paramBoolean);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof AutomaticZenRule;
    boolean bool1 = false;
    if (!bool)
      return false; 
    if (paramObject == this)
      return true; 
    paramObject = paramObject;
    if (((AutomaticZenRule)paramObject).enabled == this.enabled && ((AutomaticZenRule)paramObject).mModified == this.mModified && Objects.equals(((AutomaticZenRule)paramObject).name, this.name) && ((AutomaticZenRule)paramObject).interruptionFilter == this.interruptionFilter && Objects.equals(((AutomaticZenRule)paramObject).conditionId, this.conditionId) && Objects.equals(((AutomaticZenRule)paramObject).owner, this.owner) && Objects.equals(((AutomaticZenRule)paramObject).mZenPolicy, this.mZenPolicy) && Objects.equals(((AutomaticZenRule)paramObject).configurationActivity, this.configurationActivity) && ((AutomaticZenRule)paramObject).creationTime == this.creationTime)
      bool1 = true; 
    return bool1;
  }
  
  public Uri getConditionId() {
    return this.conditionId;
  }
  
  public ComponentName getConfigurationActivity() {
    return this.configurationActivity;
  }
  
  public long getCreationTime() {
    return this.creationTime;
  }
  
  public int getInterruptionFilter() {
    return this.interruptionFilter;
  }
  
  public String getName() {
    return this.name;
  }
  
  public ComponentName getOwner() {
    return this.owner;
  }
  
  public ZenPolicy getZenPolicy() {
    ZenPolicy zenPolicy = this.mZenPolicy;
    if (zenPolicy == null) {
      zenPolicy = null;
    } else {
      zenPolicy = zenPolicy.copy();
    } 
    return zenPolicy;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Boolean.valueOf(this.enabled), this.name, Integer.valueOf(this.interruptionFilter), this.conditionId, this.owner, this.configurationActivity, this.mZenPolicy, Boolean.valueOf(this.mModified), Long.valueOf(this.creationTime) });
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public boolean isModified() {
    return this.mModified;
  }
  
  public void setConditionId(Uri paramUri) {
    this.conditionId = paramUri;
  }
  
  public void setConfigurationActivity(ComponentName paramComponentName) {
    this.configurationActivity = paramComponentName;
  }
  
  public void setEnabled(boolean paramBoolean) {
    this.enabled = paramBoolean;
  }
  
  public void setInterruptionFilter(int paramInt) {
    this.interruptionFilter = paramInt;
  }
  
  public void setModified(boolean paramBoolean) {
    this.mModified = paramBoolean;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
  
  public void setZenPolicy(ZenPolicy paramZenPolicy) {
    if (paramZenPolicy == null) {
      paramZenPolicy = null;
    } else {
      paramZenPolicy = paramZenPolicy.copy();
    } 
    this.mZenPolicy = paramZenPolicy;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(AutomaticZenRule.class.getSimpleName());
    stringBuilder.append('[');
    stringBuilder.append("enabled=");
    stringBuilder.append(this.enabled);
    stringBuilder.append(",name=");
    stringBuilder.append(this.name);
    stringBuilder.append(",interruptionFilter=");
    stringBuilder.append(this.interruptionFilter);
    stringBuilder.append(",conditionId=");
    stringBuilder.append(this.conditionId);
    stringBuilder.append(",owner=");
    stringBuilder.append(this.owner);
    stringBuilder.append(",configActivity=");
    stringBuilder.append(this.configurationActivity);
    stringBuilder.append(",creationTime=");
    stringBuilder.append(this.creationTime);
    stringBuilder.append(",mZenPolicy=");
    stringBuilder.append(this.mZenPolicy);
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.enabled);
    if (this.name != null) {
      paramParcel.writeInt(1);
      paramParcel.writeString(this.name);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.interruptionFilter);
    paramParcel.writeParcelable((Parcelable)this.conditionId, 0);
    paramParcel.writeParcelable((Parcelable)this.owner, 0);
    paramParcel.writeParcelable((Parcelable)this.configurationActivity, 0);
    paramParcel.writeLong(this.creationTime);
    paramParcel.writeParcelable((Parcelable)this.mZenPolicy, 0);
    paramParcel.writeInt(this.mModified);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AutomaticZenRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */