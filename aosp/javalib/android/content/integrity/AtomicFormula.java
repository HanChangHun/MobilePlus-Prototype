package android.content.integrity;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class AtomicFormula extends IntegrityFormula {
  public static final int APP_CERTIFICATE = 1;
  
  public static final int EQ = 0;
  
  public static final int GT = 1;
  
  public static final int GTE = 2;
  
  public static final int INSTALLER_CERTIFICATE = 3;
  
  public static final int INSTALLER_NAME = 2;
  
  public static final int PACKAGE_NAME = 0;
  
  public static final int PRE_INSTALLED = 5;
  
  public static final int STAMP_CERTIFICATE_HASH = 7;
  
  public static final int STAMP_TRUSTED = 6;
  
  public static final int VERSION_CODE = 4;
  
  private final int mKey;
  
  public AtomicFormula(int paramInt) {
    Preconditions.checkArgument(isValidKey(paramInt), String.format("Unknown key: %d", new Object[] { Integer.valueOf(paramInt) }));
    this.mKey = paramInt;
  }
  
  private static boolean isValidKey(int paramInt) {
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 0) {
      bool2 = bool1;
      if (paramInt != 1) {
        bool2 = bool1;
        if (paramInt != 4) {
          bool2 = bool1;
          if (paramInt != 2) {
            bool2 = bool1;
            if (paramInt != 3) {
              bool2 = bool1;
              if (paramInt != 5) {
                bool2 = bool1;
                if (paramInt != 6)
                  if (paramInt == 7) {
                    bool2 = bool1;
                  } else {
                    bool2 = false;
                  }  
              } 
            } 
          } 
        } 
      } 
    } 
    return bool2;
  }
  
  static String keyToString(int paramInt) {
    StringBuilder stringBuilder;
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown key ");
        stringBuilder.append(paramInt);
        throw new IllegalArgumentException(stringBuilder.toString());
      case 7:
        return "STAMP_CERTIFICATE_HASH";
      case 6:
        return "STAMP_TRUSTED";
      case 5:
        return "PRE_INSTALLED";
      case 4:
        return "VERSION_CODE";
      case 3:
        return "INSTALLER_CERTIFICATE";
      case 2:
        return "INSTALLER_NAME";
      case 1:
        return "APP_CERTIFICATE";
      case 0:
        break;
    } 
    return "PACKAGE_NAME";
  }
  
  static String operatorToString(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt == 2)
          return "GTE"; 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown operator ");
        stringBuilder.append(paramInt);
        throw new IllegalArgumentException(stringBuilder.toString());
      } 
      return "GT";
    } 
    return "EQ";
  }
  
  public int getKey() {
    return this.mKey;
  }
  
  public static final class BooleanAtomicFormula extends AtomicFormula implements Parcelable {
    public static final Parcelable.Creator<BooleanAtomicFormula> CREATOR = new Parcelable.Creator<BooleanAtomicFormula>() {
        public AtomicFormula.BooleanAtomicFormula createFromParcel(Parcel param2Parcel) {
          return new AtomicFormula.BooleanAtomicFormula(param2Parcel);
        }
        
        public AtomicFormula.BooleanAtomicFormula[] newArray(int param2Int) {
          return new AtomicFormula.BooleanAtomicFormula[param2Int];
        }
      };
    
    private final Boolean mValue;
    
    public BooleanAtomicFormula(int param1Int) {
      super(param1Int);
      boolean bool;
      if (param1Int == 5 || param1Int == 6) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, String.format("Key %s cannot be used with BooleanAtomicFormula", new Object[] { keyToString(param1Int) }));
      this.mValue = null;
    }
    
    public BooleanAtomicFormula(int param1Int, boolean param1Boolean) {
      super(param1Int);
      boolean bool;
      if (param1Int == 5 || param1Int == 6) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, String.format("Key %s cannot be used with BooleanAtomicFormula", new Object[] { keyToString(param1Int) }));
      this.mValue = Boolean.valueOf(param1Boolean);
    }
    
    BooleanAtomicFormula(Parcel param1Parcel) {
      super(param1Parcel.readInt());
      boolean bool;
      if (param1Parcel.readByte() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mValue = Boolean.valueOf(bool);
    }
    
    private static boolean getBooleanMetadataValue(AppInstallMetadata param1AppInstallMetadata, int param1Int) {
      StringBuilder stringBuilder;
      if (param1Int != 5) {
        if (param1Int == 6)
          return param1AppInstallMetadata.isStampTrusted(); 
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected key in BooleanAtomicFormula: ");
        stringBuilder.append(param1Int);
        throw new IllegalStateException(stringBuilder.toString());
      } 
      return stringBuilder.isPreInstalled();
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      if (getKey() != param1Object.getKey() || this.mValue != ((BooleanAtomicFormula)param1Object).mValue)
        bool = false; 
      return bool;
    }
    
    public int getTag() {
      return 3;
    }
    
    public Boolean getValue() {
      return this.mValue;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(getKey()), this.mValue });
    }
    
    public boolean isAppCertificateFormula() {
      return false;
    }
    
    public boolean isInstallerFormula() {
      return false;
    }
    
    public boolean matches(AppInstallMetadata param1AppInstallMetadata) {
      Boolean bool = this.mValue;
      boolean bool1 = false;
      if (bool == null)
        return false; 
      if (getBooleanMetadataValue(param1AppInstallMetadata, getKey()) == this.mValue.booleanValue())
        bool1 = true; 
      return bool1;
    }
    
    public String toString() {
      return (this.mValue == null) ? String.format("(%s)", new Object[] { keyToString(getKey()) }) : String.format("(%s %s %s)", new Object[] { keyToString(getKey()), operatorToString(0), this.mValue });
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      if (this.mValue != null) {
        param1Parcel.writeInt(getKey());
        param1Parcel.writeByte((byte)this.mValue.booleanValue());
        return;
      } 
      throw new IllegalStateException("Cannot write an empty BooleanAtomicFormula.");
    }
  }
  
  class null implements Parcelable.Creator<BooleanAtomicFormula> {
    public AtomicFormula.BooleanAtomicFormula createFromParcel(Parcel param1Parcel) {
      return new AtomicFormula.BooleanAtomicFormula(param1Parcel);
    }
    
    public AtomicFormula.BooleanAtomicFormula[] newArray(int param1Int) {
      return new AtomicFormula.BooleanAtomicFormula[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Key {}
  
  public static final class LongAtomicFormula extends AtomicFormula implements Parcelable {
    public static final Parcelable.Creator<LongAtomicFormula> CREATOR = new Parcelable.Creator<LongAtomicFormula>() {
        public AtomicFormula.LongAtomicFormula createFromParcel(Parcel param2Parcel) {
          return new AtomicFormula.LongAtomicFormula(param2Parcel);
        }
        
        public AtomicFormula.LongAtomicFormula[] newArray(int param2Int) {
          return new AtomicFormula.LongAtomicFormula[param2Int];
        }
      };
    
    private final Integer mOperator;
    
    private final Long mValue;
    
    public LongAtomicFormula(int param1Int) {
      super(param1Int);
      boolean bool;
      if (param1Int == 4) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, String.format("Key %s cannot be used with LongAtomicFormula", new Object[] { keyToString(param1Int) }));
      this.mValue = null;
      this.mOperator = null;
    }
    
    public LongAtomicFormula(int param1Int1, int param1Int2, long param1Long) {
      super(param1Int1);
      boolean bool;
      if (param1Int1 == 4) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, String.format("Key %s cannot be used with LongAtomicFormula", new Object[] { keyToString(param1Int1) }));
      Preconditions.checkArgument(isValidOperator(param1Int2), String.format("Unknown operator: %d", new Object[] { Integer.valueOf(param1Int2) }));
      this.mOperator = Integer.valueOf(param1Int2);
      this.mValue = Long.valueOf(param1Long);
    }
    
    LongAtomicFormula(Parcel param1Parcel) {
      super(param1Parcel.readInt());
      this.mValue = Long.valueOf(param1Parcel.readLong());
      this.mOperator = Integer.valueOf(param1Parcel.readInt());
    }
    
    private static long getLongMetadataValue(AppInstallMetadata param1AppInstallMetadata, int param1Int) {
      if (param1Int == 4)
        return param1AppInstallMetadata.getVersionCode(); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unexpected key in IntAtomicFormula");
      stringBuilder.append(param1Int);
      throw new IllegalStateException(stringBuilder.toString());
    }
    
    private static boolean isValidOperator(int param1Int) {
      boolean bool1 = true;
      boolean bool2 = bool1;
      if (param1Int != 0) {
        bool2 = bool1;
        if (param1Int != 1)
          if (param1Int == 2) {
            bool2 = bool1;
          } else {
            bool2 = false;
          }  
      } 
      return bool2;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      if (getKey() != param1Object.getKey() || this.mValue != ((LongAtomicFormula)param1Object).mValue || this.mOperator != ((LongAtomicFormula)param1Object).mOperator)
        bool = false; 
      return bool;
    }
    
    public Integer getOperator() {
      return this.mOperator;
    }
    
    public int getTag() {
      return 2;
    }
    
    public Long getValue() {
      return this.mValue;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(getKey()), this.mOperator, this.mValue });
    }
    
    public boolean isAppCertificateFormula() {
      return false;
    }
    
    public boolean isInstallerFormula() {
      return false;
    }
    
    public boolean matches(AppInstallMetadata param1AppInstallMetadata) {
      Long long_ = this.mValue;
      boolean bool1 = false;
      boolean bool2 = false;
      boolean bool3 = false;
      if (long_ == null || this.mOperator == null)
        return false; 
      long l = getLongMetadataValue(param1AppInstallMetadata, getKey());
      int i = this.mOperator.intValue();
      if (i != 0) {
        if (i != 1) {
          if (i == 2) {
            if (l >= this.mValue.longValue())
              bool3 = true; 
            return bool3;
          } 
          throw new IllegalArgumentException(String.format("Unexpected operator %d", new Object[] { this.mOperator }));
        } 
        bool3 = bool1;
        if (l > this.mValue.longValue())
          bool3 = true; 
        return bool3;
      } 
      bool3 = bool2;
      if (l == this.mValue.longValue())
        bool3 = true; 
      return bool3;
    }
    
    public String toString() {
      return (this.mValue == null || this.mOperator == null) ? String.format("(%s)", new Object[] { keyToString(getKey()) }) : String.format("(%s %s %s)", new Object[] { keyToString(getKey()), operatorToString(this.mOperator.intValue()), this.mValue });
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      if (this.mValue != null && this.mOperator != null) {
        param1Parcel.writeInt(getKey());
        param1Parcel.writeLong(this.mValue.longValue());
        param1Parcel.writeInt(this.mOperator.intValue());
        return;
      } 
      throw new IllegalStateException("Cannot write an empty LongAtomicFormula.");
    }
  }
  
  class null implements Parcelable.Creator<LongAtomicFormula> {
    public AtomicFormula.LongAtomicFormula createFromParcel(Parcel param1Parcel) {
      return new AtomicFormula.LongAtomicFormula(param1Parcel);
    }
    
    public AtomicFormula.LongAtomicFormula[] newArray(int param1Int) {
      return new AtomicFormula.LongAtomicFormula[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Operator {}
  
  public static final class StringAtomicFormula extends AtomicFormula implements Parcelable {
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
    
    public StringAtomicFormula(int param1Int) {
      super(param1Int);
      boolean bool;
      if (param1Int == 0 || param1Int == 1 || param1Int == 3 || param1Int == 2 || param1Int == 7) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, String.format("Key %s cannot be used with StringAtomicFormula", new Object[] { keyToString(param1Int) }));
      this.mValue = null;
      this.mIsHashedValue = null;
    }
    
    public StringAtomicFormula(int param1Int, String param1String) {
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
    
    public StringAtomicFormula(int param1Int, String param1String, boolean param1Boolean) {
      super(param1Int);
      boolean bool;
      if (param1Int == 0 || param1Int == 1 || param1Int == 3 || param1Int == 2 || param1Int == 7) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, String.format("Key %s cannot be used with StringAtomicFormula", new Object[] { keyToString(param1Int) }));
      this.mValue = param1String;
      this.mIsHashedValue = Boolean.valueOf(param1Boolean);
    }
    
    StringAtomicFormula(Parcel param1Parcel) {
      super(param1Parcel.readInt());
      boolean bool;
      this.mValue = param1Parcel.readStringNoHelper();
      if (param1Parcel.readByte() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mIsHashedValue = Boolean.valueOf(bool);
    }
    
    private static List<String> getMetadataValue(AppInstallMetadata param1AppInstallMetadata, int param1Int) {
      StringBuilder stringBuilder;
      if (param1Int != 0) {
        if (param1Int != 1) {
          if (param1Int != 2) {
            if (param1Int != 3) {
              if (param1Int == 7)
                return Collections.singletonList(param1AppInstallMetadata.getStampCertificateHash()); 
              stringBuilder = new StringBuilder();
              stringBuilder.append("Unexpected key in StringAtomicFormula: ");
              stringBuilder.append(param1Int);
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
    
    private static String hash(String param1String) {
      try {
        return IntegrityUtils.getHexDigest(MessageDigest.getInstance("SHA-256").digest(param1String.getBytes(StandardCharsets.UTF_8)));
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
        throw new RuntimeException("SHA-256 algorithm not found", noSuchAlgorithmException);
      } 
    }
    
    private static String hashValue(int param1Int, String param1String) {
      return (param1String.length() > 32 && (param1Int == 0 || param1Int == 2)) ? hash(param1String) : param1String;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (this == param1Object)
        return true; 
      if (param1Object == null || getClass() != param1Object.getClass())
        return false; 
      param1Object = param1Object;
      if (getKey() != param1Object.getKey() || !Objects.equals(this.mValue, ((StringAtomicFormula)param1Object).mValue))
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
    
    public boolean matches(AppInstallMetadata param1AppInstallMetadata) {
      return (this.mValue == null || this.mIsHashedValue == null) ? false : getMetadataValue(param1AppInstallMetadata, getKey()).contains(this.mValue);
    }
    
    public String toString() {
      return (this.mValue == null || this.mIsHashedValue == null) ? String.format("(%s)", new Object[] { keyToString(getKey()) }) : String.format("(%s %s %s)", new Object[] { keyToString(getKey()), operatorToString(0), this.mValue });
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      if (this.mValue != null && this.mIsHashedValue != null) {
        param1Parcel.writeInt(getKey());
        param1Parcel.writeStringNoHelper(this.mValue);
        param1Parcel.writeByte((byte)this.mIsHashedValue.booleanValue());
        return;
      } 
      throw new IllegalStateException("Cannot write an empty StringAtomicFormula.");
    }
  }
  
  class null implements Parcelable.Creator<StringAtomicFormula> {
    public AtomicFormula.StringAtomicFormula createFromParcel(Parcel param1Parcel) {
      return new AtomicFormula.StringAtomicFormula(param1Parcel);
    }
    
    public AtomicFormula.StringAtomicFormula[] newArray(int param1Int) {
      return new AtomicFormula.StringAtomicFormula[param1Int];
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/AtomicFormula.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */