package android.app;

import java.util.List;

@Deprecated
public class FragmentManagerNonConfig {
  private final List<FragmentManagerNonConfig> mChildNonConfigs;
  
  private final List<Fragment> mFragments;
  
  FragmentManagerNonConfig(List<Fragment> paramList, List<FragmentManagerNonConfig> paramList1) {
    this.mFragments = paramList;
    this.mChildNonConfigs = paramList1;
  }
  
  List<FragmentManagerNonConfig> getChildNonConfigs() {
    return this.mChildNonConfigs;
  }
  
  List<Fragment> getFragments() {
    return this.mFragments;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentManagerNonConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */