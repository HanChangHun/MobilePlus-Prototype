package android.app;

import android.content.Context;
import android.os.Bundle;

@Deprecated
public abstract class FragmentContainer {
  public Fragment instantiate(Context paramContext, String paramString, Bundle paramBundle) {
    return Fragment.instantiate(paramContext, paramString, paramBundle);
  }
  
  public abstract <T extends android.view.View> T onFindViewById(int paramInt);
  
  public abstract boolean onHasView();
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */