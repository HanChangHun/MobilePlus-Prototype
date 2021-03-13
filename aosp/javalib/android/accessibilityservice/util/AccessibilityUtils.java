package android.accessibilityservice.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public final class AccessibilityUtils {
  private static final String ANCHOR_TAG = "a";
  
  private static final String IMG_PREFIX = "R.drawable.";
  
  private static final List<String> UNSUPPORTED_TAG_LIST = new ArrayList<>(Collections.singletonList("a"));
  
  public static String getFilteredHtmlText(String paramString) {
    for (String str1 : UNSUPPORTED_TAG_LIST) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("(?i)<");
      stringBuilder1.append(str1);
      stringBuilder1.append("(\\s+|>)");
      String str2 = stringBuilder1.toString();
      StringBuilder stringBuilder2 = new StringBuilder();
      stringBuilder2.append("(?i)</");
      stringBuilder2.append(str1);
      stringBuilder2.append("\\s*>");
      str1 = stringBuilder2.toString();
      paramString = Pattern.compile(str2).matcher(paramString).replaceAll("<invalidtag ");
      paramString = Pattern.compile(str1).matcher(paramString).replaceAll("</invalidtag>");
    } 
    return Pattern.compile("(?i)<img\\s+(?!src\\s*=\\s*\"(?-i)R.drawable.)").matcher(paramString).replaceAll("<invalidtag ");
  }
  
  private static int getScreenHeightPixels(Context paramContext) {
    Resources resources = paramContext.getResources();
    return Math.round(TypedValue.applyDimension(1, (resources.getConfiguration()).screenHeightDp, resources.getDisplayMetrics()));
  }
  
  private static int getScreenWidthPixels(Context paramContext) {
    Resources resources = paramContext.getResources();
    return Math.round(TypedValue.applyDimension(1, (resources.getConfiguration()).screenWidthDp, resources.getDisplayMetrics()));
  }
  
  public static Drawable loadSafeAnimatedImage(Context paramContext, ApplicationInfo paramApplicationInfo, int paramInt) {
    Context context = null;
    if (paramInt == 0)
      return null; 
    Drawable drawable = paramContext.getPackageManager().getDrawable(paramApplicationInfo.packageName, paramInt, paramApplicationInfo);
    if (drawable == null)
      return null; 
    int i = drawable.getIntrinsicWidth();
    paramInt = getScreenWidthPixels(paramContext);
    boolean bool = true;
    if (i > paramInt) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    if (drawable.getIntrinsicHeight() <= getScreenHeightPixels(paramContext))
      bool = false; 
    return (Drawable)((paramInt != 0 || bool) ? context : drawable);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/util/AccessibilityUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */