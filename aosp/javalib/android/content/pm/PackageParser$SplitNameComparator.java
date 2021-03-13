package android.content.pm;

import java.util.Comparator;

class SplitNameComparator implements Comparator<String> {
  private SplitNameComparator() {}
  
  public int compare(String paramString1, String paramString2) {
    return (paramString1 == null) ? -1 : ((paramString2 == null) ? 1 : paramString1.compareTo(paramString2));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParser$SplitNameComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */