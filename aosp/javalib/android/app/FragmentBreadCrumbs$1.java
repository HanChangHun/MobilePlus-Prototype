package android.app;

import android.view.View;

class null implements View.OnClickListener {
  public void onClick(View paramView) {
    if (paramView.getTag() instanceof FragmentManager.BackStackEntry) {
      FragmentManager.BackStackEntry backStackEntry = (FragmentManager.BackStackEntry)paramView.getTag();
      if (backStackEntry == FragmentBreadCrumbs.this.mParentEntry) {
        if (FragmentBreadCrumbs.access$000(FragmentBreadCrumbs.this) != null)
          FragmentBreadCrumbs.access$000(FragmentBreadCrumbs.this).onClick(paramView); 
      } else {
        if (FragmentBreadCrumbs.access$100(FragmentBreadCrumbs.this) != null) {
          FragmentManager.BackStackEntry backStackEntry1;
          FragmentBreadCrumbs.OnBreadCrumbClickListener onBreadCrumbClickListener = FragmentBreadCrumbs.access$100(FragmentBreadCrumbs.this);
          if (backStackEntry == FragmentBreadCrumbs.this.mTopEntry) {
            paramView = null;
          } else {
            backStackEntry1 = backStackEntry;
          } 
          if (onBreadCrumbClickListener.onBreadCrumbClick(backStackEntry1, 0))
            return; 
        } 
        if (backStackEntry == FragmentBreadCrumbs.this.mTopEntry) {
          FragmentBreadCrumbs.this.mActivity.getFragmentManager().popBackStack();
        } else {
          FragmentBreadCrumbs.this.mActivity.getFragmentManager().popBackStack(backStackEntry.getId(), 0);
        } 
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentBreadCrumbs$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */