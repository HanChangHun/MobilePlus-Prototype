package android.content.pm;

import android.os.UserHandle;
import java.util.List;

public interface ShortcutChangeCallback {
  default void onShortcutsAddedOrUpdated(String paramString, List<ShortcutInfo> paramList, UserHandle paramUserHandle) {}
  
  default void onShortcutsRemoved(String paramString, List<ShortcutInfo> paramList, UserHandle paramUserHandle) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherApps$ShortcutChangeCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */