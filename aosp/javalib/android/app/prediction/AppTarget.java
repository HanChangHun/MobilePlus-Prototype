package android.app.prediction;

import android.annotation.SystemApi;
import android.content.pm.ShortcutInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import java.util.Objects;

@SystemApi
public final class AppTarget implements Parcelable {
  public static final Parcelable.Creator<AppTarget> CREATOR = new Parcelable.Creator<AppTarget>() {
      public AppTarget createFromParcel(Parcel param1Parcel) {
        return new AppTarget(param1Parcel);
      }
      
      public AppTarget[] newArray(int param1Int) {
        return new AppTarget[param1Int];
      }
    };
  
  private final String mClassName;
  
  private final AppTargetId mId;
  
  private final String mPackageName;
  
  private final int mRank;
  
  private final ShortcutInfo mShortcutInfo;
  
  private final UserHandle mUser;
  
  @Deprecated
  public AppTarget(AppTargetId paramAppTargetId, ShortcutInfo paramShortcutInfo, String paramString) {
    this.mId = paramAppTargetId;
    Objects.requireNonNull(paramShortcutInfo);
    ShortcutInfo shortcutInfo = paramShortcutInfo;
    this.mShortcutInfo = shortcutInfo;
    this.mPackageName = shortcutInfo.getPackage();
    this.mUser = this.mShortcutInfo.getUserHandle();
    this.mClassName = paramString;
    this.mRank = 0;
  }
  
  private AppTarget(AppTargetId paramAppTargetId, String paramString1, UserHandle paramUserHandle, ShortcutInfo paramShortcutInfo, String paramString2, int paramInt) {
    this.mId = paramAppTargetId;
    this.mShortcutInfo = paramShortcutInfo;
    this.mPackageName = paramString1;
    this.mClassName = paramString2;
    this.mUser = paramUserHandle;
    this.mRank = paramInt;
  }
  
  @Deprecated
  public AppTarget(AppTargetId paramAppTargetId, String paramString1, String paramString2, UserHandle paramUserHandle) {
    this.mId = paramAppTargetId;
    this.mShortcutInfo = null;
    Objects.requireNonNull(paramString1);
    this.mPackageName = paramString1;
    this.mClassName = paramString2;
    Objects.requireNonNull(paramUserHandle);
    this.mUser = paramUserHandle;
    this.mRank = 0;
  }
  
  private AppTarget(Parcel paramParcel) {
    this.mId = (AppTargetId)paramParcel.readTypedObject(AppTargetId.CREATOR);
    ShortcutInfo shortcutInfo = (ShortcutInfo)paramParcel.readTypedObject(ShortcutInfo.CREATOR);
    this.mShortcutInfo = shortcutInfo;
    if (shortcutInfo == null) {
      this.mPackageName = paramParcel.readString();
      this.mUser = UserHandle.of(paramParcel.readInt());
    } else {
      this.mPackageName = shortcutInfo.getPackage();
      this.mUser = this.mShortcutInfo.getUserHandle();
    } 
    this.mClassName = paramParcel.readString();
    this.mRank = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getClass : ()Ljava/lang/Class;
    //   4: astore_2
    //   5: aload_1
    //   6: ifnull -> 17
    //   9: aload_1
    //   10: invokevirtual getClass : ()Ljava/lang/Class;
    //   13: astore_3
    //   14: goto -> 19
    //   17: aconst_null
    //   18: astore_3
    //   19: aload_2
    //   20: aload_3
    //   21: invokevirtual equals : (Ljava/lang/Object;)Z
    //   24: istore #4
    //   26: iconst_0
    //   27: istore #5
    //   29: iload #4
    //   31: ifne -> 36
    //   34: iconst_0
    //   35: ireturn
    //   36: aload_1
    //   37: checkcast android/app/prediction/AppTarget
    //   40: astore_1
    //   41: aload_0
    //   42: getfield mClassName : Ljava/lang/String;
    //   45: ifnonnull -> 55
    //   48: aload_1
    //   49: getfield mClassName : Ljava/lang/String;
    //   52: ifnull -> 75
    //   55: aload_0
    //   56: getfield mClassName : Ljava/lang/String;
    //   59: astore_3
    //   60: aload_3
    //   61: ifnull -> 81
    //   64: aload_3
    //   65: aload_1
    //   66: getfield mClassName : Ljava/lang/String;
    //   69: invokevirtual equals : (Ljava/lang/Object;)Z
    //   72: ifeq -> 81
    //   75: iconst_1
    //   76: istore #6
    //   78: goto -> 84
    //   81: iconst_0
    //   82: istore #6
    //   84: aload_0
    //   85: getfield mShortcutInfo : Landroid/content/pm/ShortcutInfo;
    //   88: ifnonnull -> 98
    //   91: aload_1
    //   92: getfield mShortcutInfo : Landroid/content/pm/ShortcutInfo;
    //   95: ifnull -> 128
    //   98: aload_0
    //   99: getfield mShortcutInfo : Landroid/content/pm/ShortcutInfo;
    //   102: astore_3
    //   103: aload_3
    //   104: ifnull -> 134
    //   107: aload_1
    //   108: getfield mShortcutInfo : Landroid/content/pm/ShortcutInfo;
    //   111: ifnull -> 134
    //   114: aload_3
    //   115: invokevirtual getId : ()Ljava/lang/String;
    //   118: aload_1
    //   119: getfield mShortcutInfo : Landroid/content/pm/ShortcutInfo;
    //   122: invokevirtual getId : ()Ljava/lang/String;
    //   125: if_acmpne -> 134
    //   128: iconst_1
    //   129: istore #7
    //   131: goto -> 137
    //   134: iconst_0
    //   135: istore #7
    //   137: aload_0
    //   138: getfield mId : Landroid/app/prediction/AppTargetId;
    //   141: aload_1
    //   142: getfield mId : Landroid/app/prediction/AppTargetId;
    //   145: invokevirtual equals : (Ljava/lang/Object;)Z
    //   148: ifeq -> 206
    //   151: aload_0
    //   152: getfield mPackageName : Ljava/lang/String;
    //   155: aload_1
    //   156: getfield mPackageName : Ljava/lang/String;
    //   159: invokevirtual equals : (Ljava/lang/Object;)Z
    //   162: ifeq -> 206
    //   165: iload #6
    //   167: ifeq -> 206
    //   170: aload_0
    //   171: getfield mUser : Landroid/os/UserHandle;
    //   174: aload_1
    //   175: getfield mUser : Landroid/os/UserHandle;
    //   178: invokevirtual equals : (Ljava/lang/Object;)Z
    //   181: ifeq -> 206
    //   184: iload #7
    //   186: ifeq -> 206
    //   189: aload_0
    //   190: getfield mRank : I
    //   193: aload_1
    //   194: getfield mRank : I
    //   197: if_icmpne -> 206
    //   200: iconst_1
    //   201: istore #5
    //   203: goto -> 206
    //   206: iload #5
    //   208: ireturn
  }
  
  public String getClassName() {
    return this.mClassName;
  }
  
  public AppTargetId getId() {
    return this.mId;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public int getRank() {
    return this.mRank;
  }
  
  public ShortcutInfo getShortcutInfo() {
    return this.mShortcutInfo;
  }
  
  public UserHandle getUser() {
    return this.mUser;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeTypedObject(this.mId, paramInt);
    paramParcel.writeTypedObject((Parcelable)this.mShortcutInfo, paramInt);
    if (this.mShortcutInfo == null) {
      paramParcel.writeString(this.mPackageName);
      paramParcel.writeInt(this.mUser.getIdentifier());
    } 
    paramParcel.writeString(this.mClassName);
    paramParcel.writeInt(this.mRank);
  }
  
  @SystemApi
  public static final class Builder {
    private String mClassName;
    
    private final AppTargetId mId;
    
    private String mPackageName;
    
    private int mRank;
    
    private ShortcutInfo mShortcutInfo;
    
    private UserHandle mUser;
    
    @SystemApi
    @Deprecated
    public Builder(AppTargetId param1AppTargetId) {
      this.mId = param1AppTargetId;
    }
    
    @SystemApi
    public Builder(AppTargetId param1AppTargetId, ShortcutInfo param1ShortcutInfo) {
      Objects.requireNonNull(param1AppTargetId);
      this.mId = param1AppTargetId;
      Objects.requireNonNull(param1ShortcutInfo);
      this.mShortcutInfo = param1ShortcutInfo;
      this.mPackageName = param1ShortcutInfo.getPackage();
      this.mUser = param1ShortcutInfo.getUserHandle();
    }
    
    @SystemApi
    public Builder(AppTargetId param1AppTargetId, String param1String, UserHandle param1UserHandle) {
      Objects.requireNonNull(param1AppTargetId);
      this.mId = param1AppTargetId;
      Objects.requireNonNull(param1String);
      this.mPackageName = param1String;
      Objects.requireNonNull(param1UserHandle);
      this.mUser = param1UserHandle;
    }
    
    public AppTarget build() {
      if (this.mPackageName != null)
        return new AppTarget(this.mId, this.mPackageName, this.mUser, this.mShortcutInfo, this.mClassName, this.mRank); 
      throw new IllegalStateException("No target is set");
    }
    
    public Builder setClassName(String param1String) {
      Objects.requireNonNull(param1String);
      this.mClassName = param1String;
      return this;
    }
    
    public Builder setRank(int param1Int) {
      if (param1Int >= 0) {
        this.mRank = param1Int;
        return this;
      } 
      throw new IllegalArgumentException("rank cannot be a negative value");
    }
    
    @Deprecated
    public Builder setTarget(ShortcutInfo param1ShortcutInfo) {
      setTarget(param1ShortcutInfo.getPackage(), param1ShortcutInfo.getUserHandle());
      Objects.requireNonNull(param1ShortcutInfo);
      this.mShortcutInfo = param1ShortcutInfo;
      return this;
    }
    
    @Deprecated
    public Builder setTarget(String param1String, UserHandle param1UserHandle) {
      if (this.mPackageName == null) {
        Objects.requireNonNull(param1String);
        this.mPackageName = param1String;
        Objects.requireNonNull(param1UserHandle);
        this.mUser = param1UserHandle;
        return this;
      } 
      throw new IllegalArgumentException("Target is already set");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/prediction/AppTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */