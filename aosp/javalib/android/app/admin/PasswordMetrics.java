package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.android.internal.widget.LockscreenCredential;
import com.android.internal.widget.PasswordValidationError;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public final class PasswordMetrics implements Parcelable {
  private static final int CHAR_DIGIT = 2;
  
  private static final int CHAR_LOWER_CASE = 0;
  
  private static final int CHAR_SYMBOL = 3;
  
  private static final int CHAR_UPPER_CASE = 1;
  
  public static final Parcelable.Creator<PasswordMetrics> CREATOR = new Parcelable.Creator<PasswordMetrics>() {
      public PasswordMetrics createFromParcel(Parcel param1Parcel) {
        return new PasswordMetrics(param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt());
      }
      
      public PasswordMetrics[] newArray(int param1Int) {
        return new PasswordMetrics[param1Int];
      }
    };
  
  public static final int MAX_ALLOWED_SEQUENCE = 3;
  
  private static final String TAG = "PasswordMetrics";
  
  public int credType;
  
  public int length = 0;
  
  public int letters = 0;
  
  public int lowerCase = 0;
  
  public int nonLetter = 0;
  
  public int nonNumeric = 0;
  
  public int numeric = 0;
  
  public int seqLength = Integer.MAX_VALUE;
  
  public int symbols = 0;
  
  public int upperCase = 0;
  
  public PasswordMetrics(int paramInt) {
    this.credType = paramInt;
  }
  
  public PasswordMetrics(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10) {
    this.credType = paramInt1;
    this.length = paramInt2;
    this.letters = paramInt3;
    this.upperCase = paramInt4;
    this.lowerCase = paramInt5;
    this.numeric = paramInt6;
    this.symbols = paramInt7;
    this.nonLetter = paramInt8;
    this.nonNumeric = paramInt9;
    this.seqLength = paramInt10;
  }
  
  private PasswordMetrics(PasswordMetrics paramPasswordMetrics) {
    this(paramPasswordMetrics.credType, paramPasswordMetrics.length, paramPasswordMetrics.letters, paramPasswordMetrics.upperCase, paramPasswordMetrics.lowerCase, paramPasswordMetrics.numeric, paramPasswordMetrics.symbols, paramPasswordMetrics.nonLetter, paramPasswordMetrics.nonNumeric, paramPasswordMetrics.seqLength);
  }
  
  public static PasswordMetrics applyComplexity(PasswordMetrics paramPasswordMetrics, boolean paramBoolean, int paramInt) {
    return applyComplexity(paramPasswordMetrics, paramBoolean, ComplexityBucket.forComplexity(paramInt));
  }
  
  private static PasswordMetrics applyComplexity(PasswordMetrics paramPasswordMetrics, boolean paramBoolean, ComplexityBucket paramComplexityBucket) {
    paramPasswordMetrics = new PasswordMetrics(paramPasswordMetrics);
    if (!paramComplexityBucket.canHaveSequence())
      paramPasswordMetrics.seqLength = Math.min(paramPasswordMetrics.seqLength, 3); 
    paramPasswordMetrics.length = Math.max(paramPasswordMetrics.length, paramComplexityBucket.getMinimumLength(paramBoolean ^ true));
    if (!paramBoolean && !paramComplexityBucket.allowsNumericPassword())
      paramPasswordMetrics.nonNumeric = Math.max(paramPasswordMetrics.nonNumeric, 1); 
    return paramPasswordMetrics;
  }
  
  private static int categoryChar(char paramChar) {
    return ('a' <= paramChar && paramChar <= 'z') ? 0 : (('A' <= paramChar && paramChar <= 'Z') ? 1 : (('0' <= paramChar && paramChar <= '9') ? 2 : 3));
  }
  
  private static void comparePasswordMetrics(PasswordMetrics paramPasswordMetrics1, PasswordMetrics paramPasswordMetrics2, ArrayList<PasswordValidationError> paramArrayList) {
    int i = paramPasswordMetrics2.length;
    int j = paramPasswordMetrics1.length;
    if (i < j)
      paramArrayList.add(new PasswordValidationError(3, j)); 
    i = paramPasswordMetrics2.letters;
    j = paramPasswordMetrics1.letters;
    if (i < j)
      paramArrayList.add(new PasswordValidationError(6, j)); 
    i = paramPasswordMetrics2.upperCase;
    j = paramPasswordMetrics1.upperCase;
    if (i < j)
      paramArrayList.add(new PasswordValidationError(7, j)); 
    i = paramPasswordMetrics2.lowerCase;
    j = paramPasswordMetrics1.lowerCase;
    if (i < j)
      paramArrayList.add(new PasswordValidationError(8, j)); 
    j = paramPasswordMetrics2.numeric;
    i = paramPasswordMetrics1.numeric;
    if (j < i)
      paramArrayList.add(new PasswordValidationError(9, i)); 
    j = paramPasswordMetrics2.symbols;
    i = paramPasswordMetrics1.symbols;
    if (j < i)
      paramArrayList.add(new PasswordValidationError(10, i)); 
    i = paramPasswordMetrics2.nonLetter;
    j = paramPasswordMetrics1.nonLetter;
    if (i < j)
      paramArrayList.add(new PasswordValidationError(11, j)); 
    i = paramPasswordMetrics2.nonNumeric;
    j = paramPasswordMetrics1.nonNumeric;
    if (i < j)
      paramArrayList.add(new PasswordValidationError(12, j)); 
    if (paramPasswordMetrics2.seqLength > paramPasswordMetrics1.seqLength)
      paramArrayList.add(new PasswordValidationError(5, 0)); 
  }
  
  public static int complexityLevelToMinQuality(int paramInt) {
    return (paramInt != 65536) ? ((paramInt != 196608 && paramInt != 327680) ? 0 : 196608) : 65536;
  }
  
  public static PasswordMetrics computeForCredential(LockscreenCredential paramLockscreenCredential) {
    if (paramLockscreenCredential.isPassword() || paramLockscreenCredential.isPin())
      return computeForPassword(paramLockscreenCredential.getCredential()); 
    if (paramLockscreenCredential.isPattern())
      return new PasswordMetrics(1); 
    if (paramLockscreenCredential.isNone())
      return new PasswordMetrics(-1); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown credential type ");
    stringBuilder.append(paramLockscreenCredential.getType());
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static PasswordMetrics computeForPassword(byte[] paramArrayOfbyte) {
    byte b1 = 0;
    byte b2 = 0;
    byte b3 = 0;
    byte b4 = 0;
    byte b5 = 0;
    byte b6 = 0;
    byte b7 = 0;
    int i = paramArrayOfbyte.length;
    int j = paramArrayOfbyte.length;
    for (byte b8 = 0; b8 < j; b8++) {
      int k = categoryChar((char)paramArrayOfbyte[b8]);
      if (k != 0) {
        if (k != 1) {
          if (k != 2) {
            if (k == 3) {
              b5++;
              b6++;
              b7++;
            } 
          } else {
            b4++;
            b6++;
          } 
        } else {
          b1++;
          b2++;
          b7++;
        } 
      } else {
        b1++;
        b3++;
        b7++;
      } 
    } 
    return new PasswordMetrics(4, i, b1, b2, b3, b4, b5, b6, b7, maxLengthSequence(paramArrayOfbyte));
  }
  
  private static boolean hasInvalidCharacters(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    for (byte b = 0; b < i; b++) {
      char c = (char)paramArrayOfbyte[b];
      if (c < ' ' || c > '')
        return true; 
    } 
    return false;
  }
  
  private static int maxDiffCategory(int paramInt) {
    return (paramInt != 0 && paramInt != 1) ? ((paramInt != 2) ? 0 : 10) : 1;
  }
  
  public static int maxLengthSequence(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte.length == 0)
      return 0; 
    char c = (char)paramArrayOfbyte[0];
    int i = categoryChar(c);
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    byte b = 1;
    int i1 = c;
    while (b < paramArrayOfbyte.length) {
      int i4;
      c = (char)paramArrayOfbyte[b];
      int i2 = categoryChar(c);
      int i3 = c - i1;
      if (i2 != i || Math.abs(i3) > maxDiffCategory(i)) {
        m = Math.max(m, b - n);
        i4 = b;
        n = 0;
        i = i2;
      } else {
        i1 = m;
        i4 = n;
        if (k) {
          i1 = m;
          i4 = n;
          if (i3 != j) {
            i1 = Math.max(m, b - n);
            i4 = b - 1;
          } 
        } 
        j = i3;
        n = 1;
        m = i1;
      } 
      i1 = c;
      b++;
      k = n;
      n = i4;
    } 
    return Math.max(m, paramArrayOfbyte.length - n);
  }
  
  public static PasswordMetrics merge(List<PasswordMetrics> paramList) {
    PasswordMetrics passwordMetrics = new PasswordMetrics(-1);
    Iterator<PasswordMetrics> iterator = paramList.iterator();
    while (iterator.hasNext())
      passwordMetrics.maxWith(iterator.next()); 
    return passwordMetrics;
  }
  
  private void removeOverlapping() {
    int i = this.upperCase + this.lowerCase;
    int j = this.numeric + this.symbols;
    int k = Math.max(this.letters, i);
    int m = this.symbols + k;
    int n = Math.max(this.nonLetter, j);
    int i1 = Math.max(this.nonNumeric, m);
    n = Math.max(k + n, this.numeric + i1);
    if (i >= this.letters)
      this.letters = 0; 
    if (j >= this.nonLetter)
      this.nonLetter = 0; 
    if (m >= this.nonNumeric)
      this.nonNumeric = 0; 
    if (n >= this.length)
      this.length = 0; 
  }
  
  public static int sanitizeComplexityLevel(int paramInt) {
    if (paramInt != 0 && paramInt != 65536 && paramInt != 196608 && paramInt != 327680) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid password complexity used: ");
      stringBuilder.append(paramInt);
      Log.w("PasswordMetrics", stringBuilder.toString());
      return 0;
    } 
    return paramInt;
  }
  
  private boolean satisfiesBucket(ComplexityBucket paramComplexityBucket) {
    null = paramComplexityBucket.allowsCredType(this.credType);
    boolean bool = false;
    if (!null)
      return false; 
    if (this.credType != 4)
      return true; 
    if (paramComplexityBucket.canHaveSequence() || this.seqLength <= 3) {
      int i = this.length;
      if (this.nonNumeric > 0) {
        null = true;
      } else {
        null = false;
      } 
      if (i >= paramComplexityBucket.getMinimumLength(null))
        return true; 
    } 
    return bool;
  }
  
  public static List<PasswordValidationError> validatePassword(PasswordMetrics paramPasswordMetrics, int paramInt, boolean paramBoolean, byte[] paramArrayOfbyte) {
    return hasInvalidCharacters(paramArrayOfbyte) ? Collections.singletonList(new PasswordValidationError(2, 0)) : validatePasswordMetrics(paramPasswordMetrics, paramInt, paramBoolean, computeForPassword(paramArrayOfbyte));
  }
  
  public static List<PasswordValidationError> validatePasswordMetrics(PasswordMetrics paramPasswordMetrics1, int paramInt, boolean paramBoolean, PasswordMetrics paramPasswordMetrics2) {
    ComplexityBucket complexityBucket = ComplexityBucket.forComplexity(paramInt);
    paramInt = paramPasswordMetrics2.credType;
    if (paramInt < paramPasswordMetrics1.credType || !complexityBucket.allowsCredType(paramInt))
      return Collections.singletonList(new PasswordValidationError(1, 0)); 
    if (paramPasswordMetrics2.credType != 4)
      return Collections.emptyList(); 
    if (paramBoolean && paramPasswordMetrics2.nonNumeric > 0)
      return Collections.singletonList(new PasswordValidationError(2, 0)); 
    ArrayList<PasswordValidationError> arrayList = new ArrayList();
    if (paramPasswordMetrics2.length > 16)
      arrayList.add(new PasswordValidationError(4, 16)); 
    paramPasswordMetrics1 = applyComplexity(paramPasswordMetrics1, paramBoolean, complexityBucket);
    paramPasswordMetrics1.length = Math.min(16, Math.max(paramPasswordMetrics1.length, 4));
    paramPasswordMetrics1.removeOverlapping();
    comparePasswordMetrics(paramPasswordMetrics1, paramPasswordMetrics2, arrayList);
    return arrayList;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public int determineComplexity() {
    for (ComplexityBucket complexityBucket : ComplexityBucket.values()) {
      if (satisfiesBucket(complexityBucket))
        return complexityBucket.mComplexityLevel; 
    } 
    throw new IllegalStateException("Failed to figure out complexity for a given metrics");
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (this.credType != ((PasswordMetrics)paramObject).credType || this.length != ((PasswordMetrics)paramObject).length || this.letters != ((PasswordMetrics)paramObject).letters || this.upperCase != ((PasswordMetrics)paramObject).upperCase || this.lowerCase != ((PasswordMetrics)paramObject).lowerCase || this.numeric != ((PasswordMetrics)paramObject).numeric || this.symbols != ((PasswordMetrics)paramObject).symbols || this.nonLetter != ((PasswordMetrics)paramObject).nonLetter || this.nonNumeric != ((PasswordMetrics)paramObject).nonNumeric || this.seqLength != ((PasswordMetrics)paramObject).seqLength)
      bool = false; 
    return bool;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.credType), Integer.valueOf(this.length), Integer.valueOf(this.letters), Integer.valueOf(this.upperCase), Integer.valueOf(this.lowerCase), Integer.valueOf(this.numeric), Integer.valueOf(this.symbols), Integer.valueOf(this.nonLetter), Integer.valueOf(this.nonNumeric), Integer.valueOf(this.seqLength) });
  }
  
  public void maxWith(PasswordMetrics paramPasswordMetrics) {
    int i = Math.max(this.credType, paramPasswordMetrics.credType);
    this.credType = i;
    if (i != 4)
      return; 
    this.length = Math.max(this.length, paramPasswordMetrics.length);
    this.letters = Math.max(this.letters, paramPasswordMetrics.letters);
    this.upperCase = Math.max(this.upperCase, paramPasswordMetrics.upperCase);
    this.lowerCase = Math.max(this.lowerCase, paramPasswordMetrics.lowerCase);
    this.numeric = Math.max(this.numeric, paramPasswordMetrics.numeric);
    this.symbols = Math.max(this.symbols, paramPasswordMetrics.symbols);
    this.nonLetter = Math.max(this.nonLetter, paramPasswordMetrics.nonLetter);
    this.nonNumeric = Math.max(this.nonNumeric, paramPasswordMetrics.nonNumeric);
    this.seqLength = Math.min(this.seqLength, paramPasswordMetrics.seqLength);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.credType);
    paramParcel.writeInt(this.length);
    paramParcel.writeInt(this.letters);
    paramParcel.writeInt(this.upperCase);
    paramParcel.writeInt(this.lowerCase);
    paramParcel.writeInt(this.numeric);
    paramParcel.writeInt(this.symbols);
    paramParcel.writeInt(this.nonLetter);
    paramParcel.writeInt(this.nonNumeric);
    paramParcel.writeInt(this.seqLength);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface CharacterCatagory {}
  
  private enum ComplexityBucket {
    BUCKET_HIGH(327680) {
      boolean allowsCredType(int param2Int) {
        boolean bool;
        if (param2Int == 4) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      boolean allowsNumericPassword() {
        return false;
      }
      
      boolean canHaveSequence() {
        return false;
      }
      
      int getMinimumLength(boolean param2Boolean) {
        byte b;
        if (param2Boolean) {
          b = 6;
        } else {
          b = 8;
        } 
        return b;
      }
    },
    BUCKET_LOW(327680),
    BUCKET_MEDIUM(196608) {
      boolean allowsCredType(int param2Int) {
        boolean bool;
        if (param2Int == 4) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      boolean allowsNumericPassword() {
        return false;
      }
      
      boolean canHaveSequence() {
        return false;
      }
      
      int getMinimumLength(boolean param2Boolean) {
        return 4;
      }
    },
    BUCKET_NONE(196608);
    
    int mComplexityLevel;
    
    static {
      null  = new null("BUCKET_NONE", 3, 0);
      BUCKET_NONE = ;
      $VALUES = new ComplexityBucket[] { BUCKET_HIGH, BUCKET_MEDIUM, BUCKET_LOW,  };
    }
    
    ComplexityBucket(int param1Int1) {
      this.mComplexityLevel = param1Int1;
    }
    
    static ComplexityBucket forComplexity(int param1Int) {
      for (ComplexityBucket complexityBucket : values()) {
        if (complexityBucket.mComplexityLevel == param1Int)
          return complexityBucket; 
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Invalid complexity level: ");
      stringBuilder.append(param1Int);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    abstract boolean allowsCredType(int param1Int);
    
    abstract boolean allowsNumericPassword();
    
    abstract boolean canHaveSequence();
    
    abstract int getMinimumLength(boolean param1Boolean);
  }
  
  enum null {
    boolean allowsCredType(int param1Int) {
      boolean bool;
      if (param1Int == 4) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean allowsNumericPassword() {
      return false;
    }
    
    boolean canHaveSequence() {
      return false;
    }
    
    int getMinimumLength(boolean param1Boolean) {
      byte b;
      if (param1Boolean) {
        b = 6;
      } else {
        b = 8;
      } 
      return b;
    }
  }
  
  enum null {
    boolean allowsCredType(int param1Int) {
      boolean bool;
      if (param1Int == 4) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean allowsNumericPassword() {
      return false;
    }
    
    boolean canHaveSequence() {
      return false;
    }
    
    int getMinimumLength(boolean param1Boolean) {
      return 4;
    }
  }
  
  enum null {
    boolean allowsCredType(int param1Int) {
      boolean bool;
      if (param1Int != -1) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    boolean allowsNumericPassword() {
      return true;
    }
    
    boolean canHaveSequence() {
      return true;
    }
    
    int getMinimumLength(boolean param1Boolean) {
      return 0;
    }
  }
  
  enum null {
    boolean allowsCredType(int param1Int) {
      return true;
    }
    
    boolean allowsNumericPassword() {
      return true;
    }
    
    boolean canHaveSequence() {
      return true;
    }
    
    int getMinimumLength(boolean param1Boolean) {
      return 0;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/PasswordMetrics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */