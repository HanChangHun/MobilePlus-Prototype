package android.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;
import android.widget.LinearLayout;

public class SearchBar extends LinearLayout {
  public SearchBar(Context paramContext) {
    super(paramContext);
  }
  
  public SearchBar(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public ActionMode startActionModeForChild(View paramView, ActionMode.Callback paramCallback, int paramInt) {
    return (paramInt != 0) ? super.startActionModeForChild(paramView, paramCallback, paramInt) : null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SearchDialog$SearchBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */