package android.content;

import java.util.ArrayList;

public final class MimeTypeFilter {
  public static String matches(String paramString, String[] paramArrayOfString) {
    if (paramString == null)
      return null; 
    String[] arrayOfString = paramString.split("/");
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str = paramArrayOfString[b];
      if (mimeTypeAgainstFilter(arrayOfString, str.split("/")))
        return str; 
    } 
    return null;
  }
  
  public static String matches(String[] paramArrayOfString, String paramString) {
    if (paramArrayOfString == null)
      return null; 
    String[] arrayOfString = paramString.split("/");
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str = paramArrayOfString[b];
      if (mimeTypeAgainstFilter(str.split("/"), arrayOfString))
        return str; 
    } 
    return null;
  }
  
  public static boolean matches(String paramString1, String paramString2) {
    return (paramString1 == null) ? false : mimeTypeAgainstFilter(paramString1.split("/"), paramString2.split("/"));
  }
  
  public static String[] matchesMany(String[] paramArrayOfString, String paramString) {
    byte b = 0;
    if (paramArrayOfString == null)
      return new String[0]; 
    ArrayList<String> arrayList = new ArrayList();
    String[] arrayOfString = paramString.split("/");
    int i = paramArrayOfString.length;
    while (b < i) {
      String str = paramArrayOfString[b];
      if (mimeTypeAgainstFilter(str.split("/"), arrayOfString))
        arrayList.add(str); 
      b++;
    } 
    return arrayList.<String>toArray(new String[arrayList.size()]);
  }
  
  private static boolean mimeTypeAgainstFilter(String[] paramArrayOfString1, String[] paramArrayOfString2) {
    if (paramArrayOfString2.length == 2) {
      if (!paramArrayOfString2[0].isEmpty() && !paramArrayOfString2[1].isEmpty())
        return (paramArrayOfString1.length != 2) ? false : ((!"*".equals(paramArrayOfString2[0]) && !paramArrayOfString2[0].equals(paramArrayOfString1[0])) ? false : (!(!"*".equals(paramArrayOfString2[1]) && !paramArrayOfString2[1].equals(paramArrayOfString1[1])))); 
      throw new IllegalArgumentException("Ill-formatted MIME type filter. Type or subtype empty.");
    } 
    throw new IllegalArgumentException("Ill-formatted MIME type filter. Must be type/subtype.");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/MimeTypeFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */