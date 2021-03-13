package android.content.pm;

import java.text.Collator;
import java.util.Comparator;

public class DisplayNameComparator implements Comparator<PackageItemInfo> {
  private PackageManager mPM;
  
  private final Collator sCollator = Collator.getInstance();
  
  public DisplayNameComparator(PackageManager paramPackageManager) {
    this.mPM = paramPackageManager;
  }
  
  public final int compare(PackageItemInfo paramPackageItemInfo1, PackageItemInfo paramPackageItemInfo2) {
    CharSequence charSequence2 = paramPackageItemInfo1.loadLabel(this.mPM);
    CharSequence charSequence3 = charSequence2;
    if (charSequence2 == null)
      charSequence3 = paramPackageItemInfo1.name; 
    charSequence2 = paramPackageItemInfo2.loadLabel(this.mPM);
    CharSequence charSequence1 = charSequence2;
    if (charSequence2 == null)
      charSequence1 = paramPackageItemInfo2.name; 
    return this.sCollator.compare(charSequence3.toString(), charSequence1.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageItemInfo$DisplayNameComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */