package android.app;

import android.annotation.SystemApi;
import android.util.Pair;

@SystemApi
public final class DisableInfo {
  private boolean mClock;
  
  private boolean mNavigateHome;
  
  private boolean mNotificationIcons;
  
  private boolean mNotificationPeeking;
  
  private boolean mRecents;
  
  private boolean mSearch;
  
  private boolean mStatusBarExpansion;
  
  private boolean mSystemIcons;
  
  public DisableInfo() {}
  
  public DisableInfo(int paramInt1, int paramInt2) {
    boolean bool2;
    boolean bool1 = true;
    if ((0x10000 & paramInt1) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mStatusBarExpansion = bool2;
    if ((0x200000 & paramInt1) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mNavigateHome = bool2;
    if ((0x40000 & paramInt1) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mNotificationPeeking = bool2;
    if ((0x1000000 & paramInt1) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mRecents = bool2;
    if ((0x2000000 & paramInt1) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mSearch = bool2;
    if ((0x100000 & paramInt1) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mSystemIcons = bool2;
    if ((0x800000 & paramInt1) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mClock = bool2;
    if ((0x20000 & paramInt1) != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.mNotificationIcons = bool2;
  }
  
  public boolean areAllComponentsDisabled() {
    boolean bool;
    if (this.mStatusBarExpansion && this.mNavigateHome && this.mNotificationPeeking && this.mRecents && this.mSearch && this.mSystemIcons && this.mClock && this.mNotificationIcons) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  @SystemApi
  public boolean areAllComponentsEnabled() {
    boolean bool;
    if (!this.mStatusBarExpansion && !this.mNavigateHome && !this.mNotificationPeeking && !this.mRecents && !this.mSearch && !this.mSystemIcons && !this.mClock && !this.mNotificationIcons) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean areNotificationIconsDisabled() {
    return this.mNotificationIcons;
  }
  
  public boolean areSystemIconsDisabled() {
    return this.mSystemIcons;
  }
  
  public boolean isClockDisabled() {
    return this.mClock;
  }
  
  @SystemApi
  public boolean isNavigateToHomeDisabled() {
    return this.mNavigateHome;
  }
  
  @SystemApi
  public boolean isNotificationPeekingDisabled() {
    return this.mNotificationPeeking;
  }
  
  @SystemApi
  public boolean isRecentsDisabled() {
    return this.mRecents;
  }
  
  @SystemApi
  public boolean isSearchDisabled() {
    return this.mSearch;
  }
  
  @SystemApi
  public boolean isStatusBarExpansionDisabled() {
    return this.mStatusBarExpansion;
  }
  
  public void setClockDisabled(boolean paramBoolean) {
    this.mClock = paramBoolean;
  }
  
  public void setDisableAll() {
    this.mStatusBarExpansion = true;
    this.mNavigateHome = true;
    this.mNotificationPeeking = true;
    this.mRecents = true;
    this.mSearch = true;
    this.mSystemIcons = true;
    this.mClock = true;
    this.mNotificationIcons = true;
  }
  
  public void setEnableAll() {
    this.mStatusBarExpansion = false;
    this.mNavigateHome = false;
    this.mNotificationPeeking = false;
    this.mRecents = false;
    this.mSearch = false;
    this.mSystemIcons = false;
    this.mClock = false;
    this.mNotificationIcons = false;
  }
  
  public void setNagivationHomeDisabled(boolean paramBoolean) {
    this.mNavigateHome = paramBoolean;
  }
  
  public void setNotificationIconsDisabled(boolean paramBoolean) {
    this.mNotificationIcons = paramBoolean;
  }
  
  public void setNotificationPeekingDisabled(boolean paramBoolean) {
    this.mNotificationPeeking = paramBoolean;
  }
  
  public void setRecentsDisabled(boolean paramBoolean) {
    this.mRecents = paramBoolean;
  }
  
  public void setSearchDisabled(boolean paramBoolean) {
    this.mSearch = paramBoolean;
  }
  
  public void setStatusBarExpansionDisabled(boolean paramBoolean) {
    this.mStatusBarExpansion = paramBoolean;
  }
  
  public void setSystemIconsDisabled(boolean paramBoolean) {
    this.mSystemIcons = paramBoolean;
  }
  
  public Pair<Integer, Integer> toFlags() {
    int i = 0;
    if (this.mStatusBarExpansion)
      i = 0x0 | 0x10000; 
    int j = i;
    if (this.mNavigateHome)
      j = i | 0x200000; 
    i = j;
    if (this.mNotificationPeeking)
      i = j | 0x40000; 
    j = i;
    if (this.mRecents)
      j = i | 0x1000000; 
    i = j;
    if (this.mSearch)
      i = j | 0x2000000; 
    j = i;
    if (this.mSystemIcons)
      j = i | 0x100000; 
    i = j;
    if (this.mClock)
      i = j | 0x800000; 
    j = i;
    if (this.mNotificationIcons)
      j = i | 0x20000; 
    return new Pair(Integer.valueOf(j), Integer.valueOf(0));
  }
  
  public String toString() {
    String str2;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DisableInfo: ");
    stringBuilder.append(" mStatusBarExpansion=");
    boolean bool = this.mStatusBarExpansion;
    String str1 = "disabled";
    if (bool) {
      str2 = "disabled";
    } else {
      str2 = "enabled";
    } 
    stringBuilder.append(str2);
    stringBuilder.append(" mNavigateHome=");
    if (this.mNavigateHome) {
      str2 = "disabled";
    } else {
      str2 = "enabled";
    } 
    stringBuilder.append(str2);
    stringBuilder.append(" mNotificationPeeking=");
    if (this.mNotificationPeeking) {
      str2 = "disabled";
    } else {
      str2 = "enabled";
    } 
    stringBuilder.append(str2);
    stringBuilder.append(" mRecents=");
    if (this.mRecents) {
      str2 = "disabled";
    } else {
      str2 = "enabled";
    } 
    stringBuilder.append(str2);
    stringBuilder.append(" mSearch=");
    if (this.mSearch) {
      str2 = "disabled";
    } else {
      str2 = "enabled";
    } 
    stringBuilder.append(str2);
    stringBuilder.append(" mSystemIcons=");
    if (this.mSystemIcons) {
      str2 = "disabled";
    } else {
      str2 = "enabled";
    } 
    stringBuilder.append(str2);
    stringBuilder.append(" mClock=");
    if (this.mClock) {
      str2 = "disabled";
    } else {
      str2 = "enabled";
    } 
    stringBuilder.append(str2);
    stringBuilder.append(" mNotificationIcons=");
    if (this.mNotificationIcons) {
      str2 = str1;
    } else {
      str2 = "enabled";
    } 
    stringBuilder.append(str2);
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/StatusBarManager$DisableInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */