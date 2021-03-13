package android.content.integrity;

import com.android.internal.util.Preconditions;

public class IntegrityUtils {
  private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
  
  private static char decToHex(int paramInt) {
    if (paramInt >= 0) {
      char[] arrayOfChar = HEX_CHARS;
      if (paramInt < arrayOfChar.length)
        return arrayOfChar[paramInt]; 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid dec value to be converted to hex digit ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public static byte[] getBytesFromHexDigest(String paramString) {
    boolean bool;
    if (paramString.length() % 2 == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid hex encoding ");
    stringBuilder.append(paramString);
    stringBuilder.append(": must have even length");
    Preconditions.checkArgument(bool, stringBuilder.toString());
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    for (byte b = 0; b < arrayOfByte.length; b++) {
      char c1 = paramString.charAt(b * 2);
      char c2 = paramString.charAt(b * 2 + 1);
      arrayOfByte[b] = (byte)(byte)(hexToDec(c1) << 4 | hexToDec(c2));
    } 
    return arrayOfByte;
  }
  
  public static String getHexDigest(byte[] paramArrayOfbyte) {
    char[] arrayOfChar = new char[paramArrayOfbyte.length * 2];
    for (byte b = 0; b < paramArrayOfbyte.length; b++) {
      byte b1 = paramArrayOfbyte[b];
      byte b2 = paramArrayOfbyte[b];
      arrayOfChar[b * 2] = decToHex(b1 >>> 4 & 0xF);
      arrayOfChar[b * 2 + 1] = decToHex(b2 & 0xF);
    } 
    return new String(arrayOfChar);
  }
  
  private static int hexToDec(int paramInt) {
    if (paramInt >= 48 && paramInt <= 57)
      return paramInt - 48; 
    if (paramInt >= 97 && paramInt <= 102)
      return paramInt - 97 + 10; 
    if (paramInt >= 65 && paramInt <= 70)
      return paramInt - 65 + 10; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid hex char ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/IntegrityUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */