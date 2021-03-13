package android.content.res;

import android.graphics.drawable.Drawable;

class DrawableCache extends ThemedResourceCache<Drawable.ConstantState> {
  public Drawable getInstance(long paramLong, Resources paramResources, Resources.Theme paramTheme) {
    Drawable.ConstantState constantState = get(paramLong, paramTheme);
    return (constantState != null) ? constantState.newDrawable(paramResources, paramTheme) : null;
  }
  
  public boolean shouldInvalidateEntry(Drawable.ConstantState paramConstantState, int paramInt) {
    return Configuration.needNewResources(paramInt, paramConstantState.getChangingConfigurations());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/DrawableCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */