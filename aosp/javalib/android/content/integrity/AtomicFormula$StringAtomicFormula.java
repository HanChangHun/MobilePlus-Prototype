package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class StringAtomicFormula extends AtomicFormula implements Parcelable {
  public static final Parcelable.Creator<StringAtomicFormula> CREATOR = new Parcelable.Creator<StringAtomicFormula>() {
      public AtomicFormula.StringAtomicFormula createFromParcel(Parcel param2Parcel) {
        return new AtomicFormula.StringAtomicFormula(param2Parcel);
      }
      
      public AtomicFormula.StringAtomicFormula[] newArray(int param2Int) {
        return new AtomicFormula.StringAtomicFormula[param2Int];
      }
    };
  
  private final Boolean mIsHashedValue;
  
  private final String mValue;
  
  public StringAtomicFormula(int paramInt) {
    super(paramInt);
    boolean bool;
    if (paramInt == 0 || paramInt == 1 || paramInt == 3 || paramInt == 2 || paramInt == 7) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, String.format("Key %s cannot be used with StringAtomicFormula", new Object[] { keyToString(paramInt) }));
    this.mValue = null;
    this.mIsHashedValue = null;
  }
  
  public StringAtomicFormula(int paramInt, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: invokespecial <init> : (I)V
    //   5: iconst_0
    //   6: istore_3
    //   7: iload_1
    //   8: ifeq -> 41
    //   11: iload_1
    //   12: iconst_1
    //   13: if_icmpeq -> 41
    //   16: iload_1
    //   17: iconst_3
    //   18: if_icmpeq -> 41
    //   21: iload_1
    //   22: iconst_2
    //   23: if_icmpeq -> 41
    //   26: iload_1
    //   27: bipush #7
    //   29: if_icmpne -> 35
    //   32: goto -> 41
    //   35: iconst_0
    //   36: istore #4
    //   38: goto -> 44
    //   41: iconst_1
    //   42: istore #4
    //   44: iload #4
    //   46: ldc 'Key %s cannot be used with StringAtomicFormula'
    //   48: iconst_1
    //   49: anewarray java/lang/Object
    //   52: dup
    //   53: iconst_0
    //   54: iload_1
    //   55: invokestatic keyToString : (I)Ljava/lang/String;
    //   58: aastore
    //   59: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   62: invokestatic checkArgument : (ZLjava/lang/Object;)V
    //   65: iload_1
    //   66: aload_2
    //   67: invokestatic hashValue : (ILjava/lang/String;)Ljava/lang/String;
    //   70: astore #5
    //   72: aload_0
    //   73: aload #5
    //   75: putfield mValue : Ljava/lang/String;
    //   78: iload_1
    //   79: iconst_1
    //   80: if_icmpeq -> 106
    //   83: iload_1
    //   84: iconst_3
    //   85: if_icmpeq -> 106
    //   88: iload_1
    //   89: bipush #7
    //   91: if_icmpeq -> 106
    //   94: iload_3
    //   95: istore #4
    //   97: aload #5
    //   99: aload_2
    //   100: invokevirtual equals : (Ljava/lang/Object;)Z
    //   103: ifne -> 109
    //   106: iconst_1
    //   107: istore #4
    //   109: aload_0
    //   110: iload #4
    //   112: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   115: putfield mIsHashedValue : Ljava/lang/Boolean;
    //   118: return
  }
  
  public StringAtomicFormula(int paramInt, String paramString, boolean paramBoolean) {
    super(paramInt);
    boolean bool;
    if (paramInt == 0 || paramInt == 1 || paramInt == 3 || paramInt == 2 || paramInt == 7) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, String.format("Key %s cannot be used with StringAtomicFormula", new Object[] { keyToString(paramInt) }));
    this.mValue = paramString;
    this.mIsHashedValue = Boolean.valueOf(paramBoolean);
  }
  
  StringAtomicFormula(Parcel paramParcel) {
    super(paramParcel.readInt());
    boolean bool;
    this.mValue = paramParcel.readStringNoHelper();
    if (paramParcel.readByte() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mIsHashedValue = Boolean.valueOf(bool);
  }
  
  private static List<String> getMetadataValue(AppInstallMetadata paramAppInstallMetadata, int paramInt) {
    StringBuilder stringBuilder;
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt == 7)
              return Collections.singletonList(paramAppInstallMetadata.getStampCertificateHash()); 
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected key in StringAtomicFormula: ");
            stringBuilder.append(paramInt);
            throw new IllegalStateException(stringBuilder.toString());
          } 
          return stringBuilder.getInstallerCertificates();
        } 
        return Collections.singletonList(stringBuilder.getInstallerName());
      } 
      return stringBuilder.getAppCertificates();
    } 
    return Collections.singletonList(stringBuilder.getPackageName());
  }
  
  private static String hash(String paramString) {
    try {
      return IntegrityUtils.getHexDigest(MessageDigest.getInstance("SHA-256").digest(paramString.getBytes(StandardCharsets.UTF_8)));
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      throw new RuntimeException("SHA-256 algorithm not found", noSuchAlgorithmException);
    } 
  }
  
  private static String hashValue(int paramInt, String paramString) {
    return (paramString.length() > 32 && (paramInt == 0 || paramInt == 2)) ? hash(paramString) : paramString;
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
    if (getKey() != paramObject.getKey() || !Objects.equals(this.mValue, ((StringAtomicFormula)paramObject).mValue))
      bool = false; 
    return bool;
  }
  
  public Boolean getIsHashedValue() {
    return this.mIsHashedValue;
  }
  
  public int getTag() {
    return 1;
  }
  
  public String getValue() {
    return this.mValue;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(getKey()), this.mValue });
  }
  
  public boolean isAppCertificateFormula() {
    int i = getKey();
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  public boolean isInstallerFormula() {
    return (getKey() == 2 || getKey() == 3);
  }
  
  public boolean matches(AppInstallMetadata paramAppInstallMetadata) {
    return (this.mValue == null || this.mIsHashedValue == null) ? false : getMetadataValue(paramAppInstallMetadata, getKey()).contains(this.mValue);
  }
  
  public String toString() {
    return (this.mValue == null || this.mIsHashedValue == null) ? String.format("(%s)", new Object[] { keyToString(getKey()) }) : String.format("(%s %s %s)", new Object[] { keyToString(getKey()), operatorToString(0), this.mValue });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.mValue != null && this.mIsHashedValue != null) {
      paramParcel.writeInt(getKey());
      paramParcel.writeStringNoHelper(this.mValue);
      paramParcel.writeByte((byte)this.mIsHashedValue.booleanValue());
      return;
    } 
    throw new IllegalStateException("Cannot write an empty StringAtomicFormula.");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/AtomicFormula$StringAtomicFormula.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */