package android.app;

import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class Person implements Parcelable {
  public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
      public Person createFromParcel(Parcel param1Parcel) {
        return new Person(param1Parcel);
      }
      
      public Person[] newArray(int param1Int) {
        return new Person[param1Int];
      }
    };
  
  private Icon mIcon;
  
  private boolean mIsBot;
  
  private boolean mIsImportant;
  
  private String mKey;
  
  private CharSequence mName;
  
  private String mUri;
  
  private Person(Builder paramBuilder) {
    this.mName = paramBuilder.mName;
    this.mIcon = paramBuilder.mIcon;
    this.mUri = paramBuilder.mUri;
    this.mKey = paramBuilder.mKey;
    this.mIsBot = paramBuilder.mIsBot;
    this.mIsImportant = paramBuilder.mIsImportant;
  }
  
  private Person(Parcel paramParcel) {
    this.mName = paramParcel.readCharSequence();
    if (paramParcel.readInt() != 0)
      this.mIcon = (Icon)Icon.CREATOR.createFromParcel(paramParcel); 
    this.mUri = paramParcel.readString();
    this.mKey = paramParcel.readString();
    this.mIsImportant = paramParcel.readBoolean();
    this.mIsBot = paramParcel.readBoolean();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: instanceof android/app/Person
    //   4: istore_2
    //   5: iconst_0
    //   6: istore_3
    //   7: iload_2
    //   8: ifeq -> 134
    //   11: aload_1
    //   12: checkcast android/app/Person
    //   15: astore #4
    //   17: aload_0
    //   18: getfield mName : Ljava/lang/CharSequence;
    //   21: aload #4
    //   23: getfield mName : Ljava/lang/CharSequence;
    //   26: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   29: ifeq -> 132
    //   32: aload_0
    //   33: getfield mIcon : Landroid/graphics/drawable/Icon;
    //   36: astore #5
    //   38: aload #5
    //   40: ifnonnull -> 54
    //   43: aload #4
    //   45: getfield mIcon : Landroid/graphics/drawable/Icon;
    //   48: ifnonnull -> 132
    //   51: goto -> 73
    //   54: aload #4
    //   56: getfield mIcon : Landroid/graphics/drawable/Icon;
    //   59: astore_1
    //   60: aload_1
    //   61: ifnull -> 132
    //   64: aload #5
    //   66: aload_1
    //   67: invokevirtual sameAs : (Landroid/graphics/drawable/Icon;)Z
    //   70: ifeq -> 132
    //   73: aload_0
    //   74: getfield mUri : Ljava/lang/String;
    //   77: aload #4
    //   79: getfield mUri : Ljava/lang/String;
    //   82: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   85: ifeq -> 132
    //   88: aload_0
    //   89: getfield mKey : Ljava/lang/String;
    //   92: aload #4
    //   94: getfield mKey : Ljava/lang/String;
    //   97: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   100: ifeq -> 132
    //   103: aload_0
    //   104: getfield mIsBot : Z
    //   107: aload #4
    //   109: getfield mIsBot : Z
    //   112: if_icmpne -> 132
    //   115: aload_0
    //   116: getfield mIsImportant : Z
    //   119: aload #4
    //   121: getfield mIsImportant : Z
    //   124: if_icmpne -> 132
    //   127: iconst_1
    //   128: istore_3
    //   129: goto -> 132
    //   132: iload_3
    //   133: ireturn
    //   134: iconst_0
    //   135: ireturn
  }
  
  public Icon getIcon() {
    return this.mIcon;
  }
  
  public Uri getIconUri() {
    Icon icon = this.mIcon;
    return (icon != null && (icon.getType() == 4 || this.mIcon.getType() == 6)) ? this.mIcon.getUri() : null;
  }
  
  public String getKey() {
    return this.mKey;
  }
  
  public CharSequence getName() {
    return this.mName;
  }
  
  public String getUri() {
    return this.mUri;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.mName, this.mIcon, this.mUri, this.mKey, Boolean.valueOf(this.mIsBot), Boolean.valueOf(this.mIsImportant) });
  }
  
  public boolean isBot() {
    return this.mIsBot;
  }
  
  public boolean isImportant() {
    return this.mIsImportant;
  }
  
  public String resolveToLegacyUri() {
    String str = this.mUri;
    if (str != null)
      return str; 
    if (this.mName != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("name:");
      stringBuilder.append(this.mName);
      return stringBuilder.toString();
    } 
    return "";
  }
  
  public Builder toBuilder() {
    return new Builder(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeCharSequence(this.mName);
    if (this.mIcon != null) {
      paramParcel.writeInt(1);
      this.mIcon.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeString(this.mUri);
    paramParcel.writeString(this.mKey);
    paramParcel.writeBoolean(this.mIsImportant);
    paramParcel.writeBoolean(this.mIsBot);
  }
  
  public static class Builder {
    private Icon mIcon;
    
    private boolean mIsBot;
    
    private boolean mIsImportant;
    
    private String mKey;
    
    private CharSequence mName;
    
    private String mUri;
    
    public Builder() {}
    
    private Builder(Person param1Person) {
      this.mName = param1Person.mName;
      this.mIcon = param1Person.mIcon;
      this.mUri = param1Person.mUri;
      this.mKey = param1Person.mKey;
      this.mIsBot = param1Person.mIsBot;
      this.mIsImportant = param1Person.mIsImportant;
    }
    
    public Person build() {
      return new Person(this);
    }
    
    public Builder setBot(boolean param1Boolean) {
      this.mIsBot = param1Boolean;
      return this;
    }
    
    public Builder setIcon(Icon param1Icon) {
      this.mIcon = param1Icon;
      return this;
    }
    
    public Builder setImportant(boolean param1Boolean) {
      this.mIsImportant = param1Boolean;
      return this;
    }
    
    public Builder setKey(String param1String) {
      this.mKey = param1String;
      return this;
    }
    
    public Builder setName(CharSequence param1CharSequence) {
      this.mName = param1CharSequence;
      return this;
    }
    
    public Builder setUri(String param1String) {
      this.mUri = param1String;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Person.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */