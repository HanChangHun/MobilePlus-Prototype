package android.app;

@Deprecated
public interface BackStackEntry {
  CharSequence getBreadCrumbShortTitle();
  
  int getBreadCrumbShortTitleRes();
  
  CharSequence getBreadCrumbTitle();
  
  int getBreadCrumbTitleRes();
  
  int getId();
  
  String getName();
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentManager$BackStackEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */