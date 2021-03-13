package android.graphics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class UnflattenHelper {
  private static final Pattern FLATTENED_PATTERN = Pattern.compile("(-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+)");
  
  static Matcher getMatcher(String paramString) {
    return FLATTENED_PATTERN.matcher(paramString);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Rect$UnflattenHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */