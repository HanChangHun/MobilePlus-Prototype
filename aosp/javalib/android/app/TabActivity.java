package android.app;

import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

@Deprecated
public class TabActivity extends ActivityGroup {
  private String mDefaultTab = null;
  
  private int mDefaultTabIndex = -1;
  
  private TabHost mTabHost;
  
  private void ensureTabHost() {
    if (this.mTabHost == null)
      setContentView(17367318); 
  }
  
  public TabHost getTabHost() {
    ensureTabHost();
    return this.mTabHost;
  }
  
  public TabWidget getTabWidget() {
    return this.mTabHost.getTabWidget();
  }
  
  protected void onChildTitleChanged(Activity paramActivity, CharSequence paramCharSequence) {
    if (getLocalActivityManager().getCurrentActivity() == paramActivity) {
      View view = this.mTabHost.getCurrentTabView();
      if (view != null && view instanceof TextView)
        ((TextView)view).setText(paramCharSequence); 
    } 
  }
  
  public void onContentChanged() {
    super.onContentChanged();
    TabHost tabHost = findViewById(16908306);
    this.mTabHost = tabHost;
    if (tabHost != null) {
      tabHost.setup(getLocalActivityManager());
      return;
    } 
    throw new RuntimeException("Your content must have a TabHost whose id attribute is 'android.R.id.tabhost'");
  }
  
  protected void onPostCreate(Bundle paramBundle) {
    super.onPostCreate(paramBundle);
    ensureTabHost();
    if (this.mTabHost.getCurrentTab() == -1)
      this.mTabHost.setCurrentTab(0); 
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle) {
    super.onRestoreInstanceState(paramBundle);
    ensureTabHost();
    String str = paramBundle.getString("currentTab");
    if (str != null)
      this.mTabHost.setCurrentTabByTag(str); 
    if (this.mTabHost.getCurrentTab() < 0) {
      str = this.mDefaultTab;
      if (str != null) {
        this.mTabHost.setCurrentTabByTag(str);
      } else {
        int i = this.mDefaultTabIndex;
        if (i >= 0)
          this.mTabHost.setCurrentTab(i); 
      } 
    } 
  }
  
  protected void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    String str = this.mTabHost.getCurrentTabTag();
    if (str != null)
      paramBundle.putString("currentTab", str); 
  }
  
  public void setDefaultTab(int paramInt) {
    this.mDefaultTab = null;
    this.mDefaultTabIndex = paramInt;
  }
  
  public void setDefaultTab(String paramString) {
    this.mDefaultTab = paramString;
    this.mDefaultTabIndex = -1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/TabActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */