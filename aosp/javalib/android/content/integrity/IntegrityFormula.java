package android.content.integrity;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

@SystemApi
public abstract class IntegrityFormula {
  public static final int BOOLEAN_ATOMIC_FORMULA_TAG = 3;
  
  public static final int COMPOUND_FORMULA_TAG = 0;
  
  public static final int INSTALLER_ALLOWED_BY_MANIFEST_FORMULA_TAG = 4;
  
  public static final int LONG_ATOMIC_FORMULA_TAG = 2;
  
  public static final int STRING_ATOMIC_FORMULA_TAG = 1;
  
  public static IntegrityFormula all(IntegrityFormula... paramVarArgs) {
    return new CompoundFormula(0, Arrays.asList(paramVarArgs));
  }
  
  public static IntegrityFormula any(IntegrityFormula... paramVarArgs) {
    return new CompoundFormula(1, Arrays.asList(paramVarArgs));
  }
  
  public static IntegrityFormula not(IntegrityFormula paramIntegrityFormula) {
    return new CompoundFormula(2, Arrays.asList(new IntegrityFormula[] { paramIntegrityFormula }));
  }
  
  public static IntegrityFormula readFromParcel(Parcel paramParcel) {
    StringBuilder stringBuilder;
    int i = paramParcel.readInt();
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i == 4)
              return (IntegrityFormula)InstallerAllowedByManifestFormula.CREATOR.createFromParcel(paramParcel); 
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unknown formula tag ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
          } 
          return (IntegrityFormula)AtomicFormula.BooleanAtomicFormula.CREATOR.createFromParcel((Parcel)stringBuilder);
        } 
        return (IntegrityFormula)AtomicFormula.LongAtomicFormula.CREATOR.createFromParcel((Parcel)stringBuilder);
      } 
      return (IntegrityFormula)AtomicFormula.StringAtomicFormula.CREATOR.createFromParcel((Parcel)stringBuilder);
    } 
    return (IntegrityFormula)CompoundFormula.CREATOR.createFromParcel((Parcel)stringBuilder);
  }
  
  public static void writeToParcel(IntegrityFormula paramIntegrityFormula, Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(paramIntegrityFormula.getTag());
    ((Parcelable)paramIntegrityFormula).writeToParcel(paramParcel, paramInt);
  }
  
  public abstract int getTag();
  
  public abstract boolean isAppCertificateFormula();
  
  public abstract boolean isInstallerFormula();
  
  public abstract boolean matches(AppInstallMetadata paramAppInstallMetadata);
  
  public static final class Application {
    public static IntegrityFormula certificatesContain(String param1String) {
      return new AtomicFormula.StringAtomicFormula(1, param1String);
    }
    
    public static IntegrityFormula isPreInstalled() {
      return new AtomicFormula.BooleanAtomicFormula(5, true);
    }
    
    public static IntegrityFormula packageNameEquals(String param1String) {
      return new AtomicFormula.StringAtomicFormula(0, param1String);
    }
    
    public static IntegrityFormula versionCodeEquals(long param1Long) {
      return new AtomicFormula.LongAtomicFormula(4, 0, param1Long);
    }
    
    public static IntegrityFormula versionCodeGreaterThan(long param1Long) {
      return new AtomicFormula.LongAtomicFormula(4, 1, param1Long);
    }
    
    public static IntegrityFormula versionCodeGreaterThanOrEqualTo(long param1Long) {
      return new AtomicFormula.LongAtomicFormula(4, 2, param1Long);
    }
  }
  
  public static final class Installer {
    public static IntegrityFormula certificatesContain(String param1String) {
      return new AtomicFormula.StringAtomicFormula(3, param1String);
    }
    
    public static IntegrityFormula notAllowedByManifest() {
      return IntegrityFormula.not(new InstallerAllowedByManifestFormula());
    }
    
    public static IntegrityFormula packageNameEquals(String param1String) {
      return new AtomicFormula.StringAtomicFormula(2, param1String);
    }
  }
  
  public static final class SourceStamp {
    public static IntegrityFormula notTrusted() {
      return new AtomicFormula.BooleanAtomicFormula(6, false);
    }
    
    public static IntegrityFormula stampCertificateHashEquals(String param1String) {
      return new AtomicFormula.StringAtomicFormula(7, param1String);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  static @interface Tag {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/IntegrityFormula.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */