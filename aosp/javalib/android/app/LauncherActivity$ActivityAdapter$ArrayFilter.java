package android.app;

import android.widget.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class ArrayFilter extends Filter {
  private ArrayFilter() {}
  
  protected Filter.FilterResults performFiltering(CharSequence paramCharSequence) {
    Filter.FilterResults filterResults = new Filter.FilterResults();
    if (LauncherActivity.ActivityAdapter.access$100(LauncherActivity.ActivityAdapter.this) == null)
      synchronized (LauncherActivity.ActivityAdapter.access$200(LauncherActivity.ActivityAdapter.this)) {
        LauncherActivity.ActivityAdapter activityAdapter = LauncherActivity.ActivityAdapter.this;
        ArrayList arrayList = new ArrayList();
        this((Collection)LauncherActivity.ActivityAdapter.this.mActivitiesList);
        LauncherActivity.ActivityAdapter.access$102(activityAdapter, arrayList);
      }  
    if (paramCharSequence == null || paramCharSequence.length() == 0)
      synchronized (LauncherActivity.ActivityAdapter.access$200(LauncherActivity.ActivityAdapter.this)) {
        ArrayList arrayList = new ArrayList();
        this(LauncherActivity.ActivityAdapter.access$100(LauncherActivity.ActivityAdapter.this));
        filterResults.values = arrayList;
        filterResults.count = arrayList.size();
        return filterResults;
      }  
    String str = paramCharSequence.toString().toLowerCase();
    ArrayList<LauncherActivity.ListItem> arrayList2 = LauncherActivity.ActivityAdapter.access$100(LauncherActivity.ActivityAdapter.this);
    int i = arrayList2.size();
    ArrayList<LauncherActivity.ListItem> arrayList1 = new ArrayList(i);
    for (byte b = 0; b < i; b++) {
      LauncherActivity.ListItem listItem = arrayList2.get(b);
      String[] arrayOfString = listItem.label.toString().toLowerCase().split(" ");
      int j = arrayOfString.length;
      for (byte b1 = 0; b1 < j; b1++) {
        if (arrayOfString[b1].startsWith(str)) {
          arrayList1.add(listItem);
          break;
        } 
      } 
    } 
    filterResults.values = arrayList1;
    filterResults.count = arrayList1.size();
    return filterResults;
  }
  
  protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults) {
    LauncherActivity.ActivityAdapter.this.mActivitiesList = (List<LauncherActivity.ListItem>)paramFilterResults.values;
    if (paramFilterResults.count > 0) {
      LauncherActivity.ActivityAdapter.this.notifyDataSetChanged();
    } else {
      LauncherActivity.ActivityAdapter.this.notifyDataSetInvalidated();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LauncherActivity$ActivityAdapter$ArrayFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */