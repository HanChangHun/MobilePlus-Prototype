package android.app.timezone;

final class Utils {
  static <T> T validateConditionalNull(boolean paramBoolean, String paramString, T paramT) {
    return paramBoolean ? validateNotNull(paramString, paramT) : validateNull(paramString, paramT);
  }
  
  static <T> T validateNotNull(String paramString, T paramT) {
    if (paramT != null)
      return paramT; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(" == null");
    throw new NullPointerException(stringBuilder.toString());
  }
  
  static <T> T validateNull(String paramString, T paramT) {
    if (paramT == null)
      return null; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append(" != null");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  static String validateRulesVersion(String paramString1, String paramString2) {
    validateNotNull(paramString1, paramString2);
    if (!paramString2.isEmpty())
      return paramString2; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString1);
    stringBuilder.append(" must not be empty");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  static int validateVersion(String paramString, int paramInt) {
    if (paramInt >= 0 && paramInt <= 999)
      return paramInt; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Invalid ");
    stringBuilder.append(paramString);
    stringBuilder.append(" version=");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */