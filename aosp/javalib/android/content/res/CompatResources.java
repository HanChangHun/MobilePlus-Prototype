package android.content.res;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

public class CompatResources extends Resources {
  private WeakReference<Context> mContext = new WeakReference<>(null);
  
  public CompatResources(ClassLoader paramClassLoader) {
    super(paramClassLoader);
  }
  
  private Resources.Theme getTheme() {
    Context context = this.mContext.get();
    if (context != null) {
      Resources.Theme theme = context.getTheme();
    } else {
      context = null;
    } 
    return (Resources.Theme)context;
  }
  
  public int getColor(int paramInt) throws Resources.NotFoundException {
    return getColor(paramInt, getTheme());
  }
  
  public ColorStateList getColorStateList(int paramInt) throws Resources.NotFoundException {
    return getColorStateList(paramInt, getTheme());
  }
  
  public Drawable getDrawable(int paramInt) throws Resources.NotFoundException {
    return getDrawable(paramInt, getTheme());
  }
  
  public Drawable getDrawableForDensity(int paramInt1, int paramInt2) throws Resources.NotFoundException {
    return getDrawableForDensity(paramInt1, paramInt2, getTheme());
  }
  
  public void setContext(Context paramContext) {
    this.mContext = new WeakReference<>(paramContext);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/CompatResources.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */