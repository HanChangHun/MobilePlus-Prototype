package android.graphics;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class DecodeException extends IOException {
  public static final int SOURCE_EXCEPTION = 1;
  
  public static final int SOURCE_INCOMPLETE = 2;
  
  public static final int SOURCE_MALFORMED_DATA = 3;
  
  final int mError;
  
  final ImageDecoder.Source mSource;
  
  DecodeException(int paramInt, String paramString, Throwable paramThrowable, ImageDecoder.Source paramSource) {
    super(stringBuilder.toString(), paramThrowable);
    this.mError = paramInt;
    this.mSource = paramSource;
  }
  
  DecodeException(int paramInt, Throwable paramThrowable, ImageDecoder.Source paramSource) {
    super(errorMessage(paramInt, paramThrowable), paramThrowable);
    this.mError = paramInt;
    this.mSource = paramSource;
  }
  
  private static String errorMessage(int paramInt, Throwable paramThrowable) {
    if (paramInt != 1)
      return (paramInt != 2) ? ((paramInt != 3) ? "" : "Input contained an error.") : "Input was incomplete."; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Exception in input: ");
    stringBuilder.append(paramThrowable);
    return stringBuilder.toString();
  }
  
  public int getError() {
    return this.mError;
  }
  
  public ImageDecoder.Source getSource() {
    return this.mSource;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Error {}
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ImageDecoder$DecodeException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */