package android.app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class ActivityAdapter extends BaseAdapter implements Filterable {
  private final Object lock = new Object();
  
  protected List<LauncherActivity.ListItem> mActivitiesList;
  
  private Filter mFilter;
  
  protected final LauncherActivity.IconResizer mIconResizer;
  
  protected final LayoutInflater mInflater;
  
  private ArrayList<LauncherActivity.ListItem> mOriginalValues;
  
  private final boolean mShowIcons;
  
  public ActivityAdapter(LauncherActivity.IconResizer paramIconResizer) {
    this.mIconResizer = paramIconResizer;
    this.mInflater = (LayoutInflater)paramLauncherActivity.getSystemService("layout_inflater");
    this.mShowIcons = paramLauncherActivity.onEvaluateShowIcons();
    this.mActivitiesList = paramLauncherActivity.makeListItems();
  }
  
  private void bindView(View paramView, LauncherActivity.ListItem paramListItem) {
    TextView textView = (TextView)paramView;
    textView.setText(paramListItem.label);
    if (this.mShowIcons) {
      if (paramListItem.icon == null)
        paramListItem.icon = this.mIconResizer.createIconThumbnail(paramListItem.resolveInfo.loadIcon(LauncherActivity.this.getPackageManager())); 
      textView.setCompoundDrawablesRelativeWithIntrinsicBounds(paramListItem.icon, null, null, null);
    } 
  }
  
  public int getCount() {
    boolean bool;
    List<LauncherActivity.ListItem> list = this.mActivitiesList;
    if (list != null) {
      bool = list.size();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Filter getFilter() {
    if (this.mFilter == null)
      this.mFilter = new ArrayFilter(); 
    return this.mFilter;
  }
  
  public Object getItem(int paramInt) {
    return Integer.valueOf(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    if (paramView == null)
      paramView = this.mInflater.inflate(17367080, paramViewGroup, false); 
    bindView(paramView, this.mActivitiesList.get(paramInt));
    return paramView;
  }
  
  public Intent intentForPosition(int paramInt) {
    if (this.mActivitiesList == null)
      return null; 
    Intent intent = new Intent(LauncherActivity.this.mIntent);
    LauncherActivity.ListItem listItem = this.mActivitiesList.get(paramInt);
    intent.setClassName(listItem.packageName, listItem.className);
    if (listItem.extras != null)
      intent.putExtras(listItem.extras); 
    return intent;
  }
  
  public LauncherActivity.ListItem itemForPosition(int paramInt) {
    List<LauncherActivity.ListItem> list = this.mActivitiesList;
    return (list == null) ? null : list.get(paramInt);
  }
  
  private class ArrayFilter extends Filter {
    private ArrayFilter() {}
    
    protected Filter.FilterResults performFiltering(CharSequence param2CharSequence) {
      Filter.FilterResults filterResults = new Filter.FilterResults();
      if (LauncherActivity.ActivityAdapter.this.mOriginalValues == null)
        synchronized (LauncherActivity.ActivityAdapter.this.lock) {
          LauncherActivity.ActivityAdapter activityAdapter = LauncherActivity.ActivityAdapter.this;
          ArrayList arrayList = new ArrayList();
          this((Collection)LauncherActivity.ActivityAdapter.this.mActivitiesList);
          LauncherActivity.ActivityAdapter.access$102(activityAdapter, arrayList);
        }  
      if (param2CharSequence == null || param2CharSequence.length() == 0)
        synchronized (LauncherActivity.ActivityAdapter.this.lock) {
          ArrayList arrayList = new ArrayList();
          this((Collection)LauncherActivity.ActivityAdapter.this.mOriginalValues);
          filterResults.values = arrayList;
          filterResults.count = arrayList.size();
          return filterResults;
        }  
      String str = param2CharSequence.toString().toLowerCase();
      ArrayList<LauncherActivity.ListItem> arrayList2 = LauncherActivity.ActivityAdapter.this.mOriginalValues;
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
    
    protected void publishResults(CharSequence param2CharSequence, Filter.FilterResults param2FilterResults) {
      LauncherActivity.ActivityAdapter.this.mActivitiesList = (List<LauncherActivity.ListItem>)param2FilterResults.values;
      if (param2FilterResults.count > 0) {
        LauncherActivity.ActivityAdapter.this.notifyDataSetChanged();
      } else {
        LauncherActivity.ActivityAdapter.this.notifyDataSetInvalidated();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LauncherActivity$ActivityAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */