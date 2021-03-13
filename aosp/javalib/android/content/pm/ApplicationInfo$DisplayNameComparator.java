package android.content.pm;

import java.text.Collator;
import java.util.Comparator;

public class DisplayNameComparator implements Comparator<ApplicationInfo> {
  private PackageManager mPM;
  
  private final Collator sCollator = Collator.getInstance();
  
  public DisplayNameComparator(PackageManager paramPackageManager) {
    this.mPM = paramPackageManager;
  }
  
  public final int compare(ApplicationInfo paramApplicationInfo1, ApplicationInfo paramApplicationInfo2) {
    CharSequence charSequence2 = this.mPM.getApplicationLabel(paramApplicationInfo1);
    CharSequence charSequence3 = charSequence2;
    if (charSequence2 == null)
      charSequence3 = paramApplicationInfo1.packageName; 
    charSequence2 = this.mPM.getApplicationLabel(paramApplicationInfo2);
    CharSequence charSequence1 = charSequence2;
    if (charSequence2 == null)
      charSequence1 = paramApplicationInfo2.packageName; 
    return this.sCollator.compare(charSequence3.toString(), charSequence1.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ApplicationInfo$DisplayNameComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */