package android.content.pm;

import java.text.Collator;
import java.util.Comparator;

public class DisplayNameComparator implements Comparator<ResolveInfo> {
  private final Collator mCollator;
  
  private PackageManager mPM;
  
  public DisplayNameComparator(PackageManager paramPackageManager) {
    Collator collator = Collator.getInstance();
    this.mCollator = collator;
    this.mPM = paramPackageManager;
    collator.setStrength(0);
  }
  
  public final int compare(ResolveInfo paramResolveInfo1, ResolveInfo paramResolveInfo2) {
    if (paramResolveInfo1.targetUserId != -2)
      return 1; 
    if (paramResolveInfo2.targetUserId != -2)
      return -1; 
    CharSequence charSequence2 = paramResolveInfo1.loadLabel(this.mPM);
    CharSequence charSequence3 = charSequence2;
    if (charSequence2 == null)
      charSequence3 = paramResolveInfo1.activityInfo.name; 
    charSequence2 = paramResolveInfo2.loadLabel(this.mPM);
    CharSequence charSequence1 = charSequence2;
    if (charSequence2 == null)
      charSequence1 = paramResolveInfo2.activityInfo.name; 
    return this.mCollator.compare(charSequence3.toString(), charSequence1.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ResolveInfo$DisplayNameComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */