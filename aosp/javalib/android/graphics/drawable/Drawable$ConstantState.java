package android.graphics.drawable;

import android.content.res.Resources;

public abstract class ConstantState {
  public boolean canApplyTheme() {
    return false;
  }
  
  public abstract int getChangingConfigurations();
  
  public abstract Drawable newDrawable();
  
  public Drawable newDrawable(Resources paramResources) {
    return newDrawable();
  }
  
  public Drawable newDrawable(Resources paramResources, Resources.Theme paramTheme) {
    return newDrawable(paramResources);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/drawable/Drawable$ConstantState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */