package android.content;

import android.net.Uri;
import java.util.List;

public class ContentUris {
  public static Uri.Builder appendId(Uri.Builder paramBuilder, long paramLong) {
    return paramBuilder.appendEncodedPath(String.valueOf(paramLong));
  }
  
  public static long parseId(Uri paramUri) {
    long l;
    String str = paramUri.getLastPathSegment();
    if (str == null) {
      l = -1L;
    } else {
      l = Long.parseLong(str);
    } 
    return l;
  }
  
  public static Uri removeId(Uri paramUri) {
    String str = paramUri.getLastPathSegment();
    if (str != null) {
      Long.parseLong(str);
      List<String> list = paramUri.getPathSegments();
      Uri.Builder builder = paramUri.buildUpon();
      builder.path(null);
      for (byte b = 0; b < list.size() - 1; b++)
        builder.appendPath(list.get(b)); 
      return builder.build();
    } 
    throw new IllegalArgumentException("No path segments to remove");
  }
  
  public static Uri withAppendedId(Uri paramUri, long paramLong) {
    return appendId(paramUri.buildUpon(), paramLong).build();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentUris.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */