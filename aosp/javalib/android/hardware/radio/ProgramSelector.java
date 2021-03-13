package android.hardware.radio;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

@SystemApi
public final class ProgramSelector implements Parcelable {
  public static final Parcelable.Creator<ProgramSelector> CREATOR = new Parcelable.Creator<ProgramSelector>() {
      public ProgramSelector createFromParcel(Parcel param1Parcel) {
        return new ProgramSelector(param1Parcel);
      }
      
      public ProgramSelector[] newArray(int param1Int) {
        return new ProgramSelector[param1Int];
      }
    };
  
  public static final int IDENTIFIER_TYPE_AMFM_FREQUENCY = 1;
  
  public static final int IDENTIFIER_TYPE_DAB_ENSEMBLE = 6;
  
  public static final int IDENTIFIER_TYPE_DAB_FREQUENCY = 8;
  
  public static final int IDENTIFIER_TYPE_DAB_SCID = 7;
  
  public static final int IDENTIFIER_TYPE_DAB_SIDECC = 5;
  
  public static final int IDENTIFIER_TYPE_DAB_SID_EXT = 5;
  
  public static final int IDENTIFIER_TYPE_DRMO_FREQUENCY = 10;
  
  @Deprecated
  public static final int IDENTIFIER_TYPE_DRMO_MODULATION = 11;
  
  public static final int IDENTIFIER_TYPE_DRMO_SERVICE_ID = 9;
  
  public static final int IDENTIFIER_TYPE_HD_STATION_ID_EXT = 3;
  
  public static final int IDENTIFIER_TYPE_HD_STATION_NAME = 10004;
  
  @Deprecated
  public static final int IDENTIFIER_TYPE_HD_SUBCHANNEL = 4;
  
  public static final int IDENTIFIER_TYPE_INVALID = 0;
  
  public static final int IDENTIFIER_TYPE_RDS_PI = 2;
  
  public static final int IDENTIFIER_TYPE_SXM_CHANNEL = 13;
  
  public static final int IDENTIFIER_TYPE_SXM_SERVICE_ID = 12;
  
  public static final int IDENTIFIER_TYPE_VENDOR_END = 1999;
  
  @Deprecated
  public static final int IDENTIFIER_TYPE_VENDOR_PRIMARY_END = 1999;
  
  @Deprecated
  public static final int IDENTIFIER_TYPE_VENDOR_PRIMARY_START = 1000;
  
  public static final int IDENTIFIER_TYPE_VENDOR_START = 1000;
  
  @Deprecated
  public static final int PROGRAM_TYPE_AM = 1;
  
  @Deprecated
  public static final int PROGRAM_TYPE_AM_HD = 3;
  
  @Deprecated
  public static final int PROGRAM_TYPE_DAB = 5;
  
  @Deprecated
  public static final int PROGRAM_TYPE_DRMO = 6;
  
  @Deprecated
  public static final int PROGRAM_TYPE_FM = 2;
  
  @Deprecated
  public static final int PROGRAM_TYPE_FM_HD = 4;
  
  @Deprecated
  public static final int PROGRAM_TYPE_INVALID = 0;
  
  @Deprecated
  public static final int PROGRAM_TYPE_SXM = 7;
  
  @Deprecated
  public static final int PROGRAM_TYPE_VENDOR_END = 1999;
  
  @Deprecated
  public static final int PROGRAM_TYPE_VENDOR_START = 1000;
  
  private final Identifier mPrimaryId;
  
  private final int mProgramType;
  
  private final Identifier[] mSecondaryIds;
  
  private final long[] mVendorIds;
  
  public ProgramSelector(int paramInt, Identifier paramIdentifier, Identifier[] paramArrayOfIdentifier, long[] paramArrayOflong) {
    Identifier[] arrayOfIdentifier = paramArrayOfIdentifier;
    if (paramArrayOfIdentifier == null)
      arrayOfIdentifier = new Identifier[0]; 
    long[] arrayOfLong = paramArrayOflong;
    if (paramArrayOflong == null)
      arrayOfLong = new long[0]; 
    if (!Stream.<Identifier>of(arrayOfIdentifier).anyMatch((Predicate)_$$Lambda$ProgramSelector$pP_Cu6h7_REdNveY60TFDS4pIKk.INSTANCE)) {
      this.mProgramType = paramInt;
      Objects.requireNonNull(paramIdentifier);
      this.mPrimaryId = paramIdentifier;
      this.mSecondaryIds = arrayOfIdentifier;
      this.mVendorIds = arrayOfLong;
      return;
    } 
    throw new IllegalArgumentException("secondaryIds list must not contain nulls");
  }
  
  private ProgramSelector(Parcel paramParcel) {
    this.mProgramType = paramParcel.readInt();
    this.mPrimaryId = (Identifier)paramParcel.readTypedObject(Identifier.CREATOR);
    Identifier[] arrayOfIdentifier = (Identifier[])paramParcel.createTypedArray(Identifier.CREATOR);
    this.mSecondaryIds = arrayOfIdentifier;
    if (!Stream.<Identifier>of(arrayOfIdentifier).anyMatch((Predicate)_$$Lambda$ProgramSelector$nFx6NE_itx7YUkyrPxAq5zDeJdQ.INSTANCE)) {
      this.mVendorIds = paramParcel.createLongArray();
      return;
    } 
    throw new IllegalArgumentException("secondaryIds list must not contain nulls");
  }
  
  public static ProgramSelector createAmFmSelector(int paramInt1, int paramInt2) {
    return createAmFmSelector(paramInt1, paramInt2, 0);
  }
  
  public static ProgramSelector createAmFmSelector(int paramInt1, int paramInt2, int paramInt3) {
    boolean bool;
    byte b = 2;
    int i = paramInt1;
    if (paramInt1 == -1)
      if (paramInt2 < 50000) {
        if (paramInt3 <= 0) {
          paramInt1 = 0;
        } else {
          paramInt1 = 3;
        } 
        i = paramInt1;
      } else {
        if (paramInt3 <= 0) {
          paramInt1 = 1;
        } else {
          paramInt1 = 2;
        } 
        i = paramInt1;
      }  
    if (i == 0 || i == 3) {
      bool = true;
    } else {
      bool = false;
    } 
    if (i == 3 || i == 2) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    } 
    if (bool || paramInt1 != 0 || i == 1) {
      if (paramInt3 >= 0 && paramInt3 <= 8) {
        if (paramInt3 <= 0 || paramInt1 != 0) {
          if (isValidAmFmFrequency(bool, paramInt2)) {
            paramInt1 = b;
            if (bool)
              paramInt1 = 1; 
            Identifier identifier = new Identifier(1, paramInt2);
            Identifier[] arrayOfIdentifier = null;
            if (paramInt3 > 0)
              arrayOfIdentifier = new Identifier[] { new Identifier(4, (paramInt3 - 1)) }; 
            return new ProgramSelector(paramInt1, identifier, arrayOfIdentifier, null);
          } 
          StringBuilder stringBuilder2 = new StringBuilder();
          stringBuilder2.append("Provided value is not a valid AM/FM frequency: ");
          stringBuilder2.append(paramInt2);
          throw new IllegalArgumentException(stringBuilder2.toString());
        } 
        throw new IllegalArgumentException("Subchannels are not supported for non-HD radio");
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Invalid subchannel: ");
      stringBuilder1.append(paramInt3);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown band: ");
    stringBuilder.append(i);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private static boolean isValidAmFmFrequency(boolean paramBoolean, int paramInt) {
    boolean bool1 = true;
    boolean bool2 = true;
    if (paramBoolean) {
      if (paramInt > 150 && paramInt <= 30000) {
        paramBoolean = bool2;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } 
    if (paramInt > 60000 && paramInt < 110000) {
      paramBoolean = bool1;
    } else {
      paramBoolean = false;
    } 
    return paramBoolean;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof ProgramSelector))
      return false; 
    paramObject = paramObject;
    return this.mPrimaryId.equals(paramObject.getPrimaryId());
  }
  
  public Identifier[] getAllIds(int paramInt) {
    ArrayList<Identifier> arrayList = new ArrayList();
    if (this.mPrimaryId.getType() == paramInt)
      arrayList.add(this.mPrimaryId); 
    for (Identifier identifier : this.mSecondaryIds) {
      if (identifier.getType() == paramInt)
        arrayList.add(identifier); 
    } 
    return arrayList.<Identifier>toArray(new Identifier[arrayList.size()]);
  }
  
  public long getFirstId(int paramInt) {
    if (this.mPrimaryId.getType() == paramInt)
      return this.mPrimaryId.getValue(); 
    for (Identifier identifier : this.mSecondaryIds) {
      if (identifier.getType() == paramInt)
        return identifier.getValue(); 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Identifier ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" not found");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Identifier getPrimaryId() {
    return this.mPrimaryId;
  }
  
  @Deprecated
  public int getProgramType() {
    return this.mProgramType;
  }
  
  public Identifier[] getSecondaryIds() {
    return this.mSecondaryIds;
  }
  
  @Deprecated
  public long[] getVendorIds() {
    return this.mVendorIds;
  }
  
  public int hashCode() {
    return this.mPrimaryId.hashCode();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("ProgramSelector(type=");
    stringBuilder.append(this.mProgramType);
    stringBuilder.append(", primary=");
    stringBuilder = stringBuilder.append(this.mPrimaryId);
    if (this.mSecondaryIds.length > 0) {
      stringBuilder.append(", secondary=");
      stringBuilder.append(this.mSecondaryIds);
    } 
    if (this.mVendorIds.length > 0) {
      stringBuilder.append(", vendor=");
      stringBuilder.append(this.mVendorIds);
    } 
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  public ProgramSelector withSecondaryPreferred(Identifier paramIdentifier) {
    int i = paramIdentifier.getType();
    Identifier[] arrayOfIdentifier = (Identifier[])Stream.concat(Arrays.stream((Object[])this.mSecondaryIds).filter(new _$$Lambda$ProgramSelector$TWK8H6GGx8Rt5rbA87tKag_pCqw(i)), Stream.of(paramIdentifier)).toArray((IntFunction)_$$Lambda$ProgramSelector$kEsOH_p_eN5KvKLjoDTGZXYtuP4.INSTANCE);
    return new ProgramSelector(this.mProgramType, this.mPrimaryId, arrayOfIdentifier, this.mVendorIds);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mProgramType);
    paramParcel.writeTypedObject(this.mPrimaryId, 0);
    paramParcel.writeTypedArray((Parcelable[])this.mSecondaryIds, 0);
    paramParcel.writeLongArray(this.mVendorIds);
  }
  
  public static final class Identifier implements Parcelable {
    public static final Parcelable.Creator<Identifier> CREATOR = new Parcelable.Creator<Identifier>() {
        public ProgramSelector.Identifier createFromParcel(Parcel param2Parcel) {
          return new ProgramSelector.Identifier(param2Parcel);
        }
        
        public ProgramSelector.Identifier[] newArray(int param2Int) {
          return new ProgramSelector.Identifier[param2Int];
        }
      };
    
    private final int mType;
    
    private final long mValue;
    
    public Identifier(int param1Int, long param1Long) {
      int i = param1Int;
      if (param1Int == 10004)
        i = 4; 
      this.mType = i;
      this.mValue = param1Long;
    }
    
    private Identifier(Parcel param1Parcel) {
      this.mType = param1Parcel.readInt();
      this.mValue = param1Parcel.readLong();
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool = true;
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof Identifier))
        return false; 
      param1Object = param1Object;
      if (param1Object.getType() != this.mType || param1Object.getValue() != this.mValue)
        bool = false; 
      return bool;
    }
    
    public int getType() {
      return (this.mType == 4 && this.mValue > 10L) ? 10004 : this.mType;
    }
    
    public long getValue() {
      return this.mValue;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(this.mType), Long.valueOf(this.mValue) });
    }
    
    public boolean isCategoryType() {
      boolean bool;
      int i = this.mType;
      if ((i >= 1000 && i <= 1999) || this.mType == 6) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Identifier(");
      stringBuilder.append(this.mType);
      stringBuilder.append(", ");
      stringBuilder.append(this.mValue);
      stringBuilder.append(")");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.mType);
      param1Parcel.writeLong(this.mValue);
    }
  }
  
  class null implements Parcelable.Creator<Identifier> {
    public ProgramSelector.Identifier createFromParcel(Parcel param1Parcel) {
      return new ProgramSelector.Identifier(param1Parcel);
    }
    
    public ProgramSelector.Identifier[] newArray(int param1Int) {
      return new ProgramSelector.Identifier[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface IdentifierType {}
  
  @Deprecated
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ProgramType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ProgramSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */