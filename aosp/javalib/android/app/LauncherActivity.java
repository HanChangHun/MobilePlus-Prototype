package android.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Deprecated
public abstract class LauncherActivity extends ListActivity {
  IconResizer mIconResizer;
  
  Intent mIntent;
  
  PackageManager mPackageManager;
  
  private void updateAlertTitle() {
    TextView textView = findViewById(16908739);
    if (textView != null)
      textView.setText(getTitle()); 
  }
  
  private void updateButtonText() {
    Button button = findViewById(16908313);
    if (button != null)
      button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
              LauncherActivity.this.finish();
            }
          }); 
  }
  
  protected Intent getTargetIntent() {
    return new Intent();
  }
  
  protected Intent intentForPosition(int paramInt) {
    return ((ActivityAdapter)this.mAdapter).intentForPosition(paramInt);
  }
  
  protected ListItem itemForPosition(int paramInt) {
    return ((ActivityAdapter)this.mAdapter).itemForPosition(paramInt);
  }
  
  public List<ListItem> makeListItems() {
    List<ResolveInfo> list = onQueryPackageManager(this.mIntent);
    onSortResultList(list);
    ArrayList<ListItem> arrayList = new ArrayList(list.size());
    int i = list.size();
    for (byte b = 0; b < i; b++) {
      ResolveInfo resolveInfo = list.get(b);
      arrayList.add(new ListItem(this.mPackageManager, resolveInfo, null));
    } 
    return arrayList;
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    PackageManager packageManager = getPackageManager();
    this.mPackageManager = packageManager;
    if (!packageManager.hasSystemFeature("android.hardware.type.watch")) {
      requestWindowFeature(5);
      setProgressBarIndeterminateVisibility(true);
    } 
    onSetContentView();
    this.mIconResizer = new IconResizer();
    Intent intent = new Intent(getTargetIntent());
    this.mIntent = intent;
    intent.setComponent(null);
    this.mAdapter = (ListAdapter)new ActivityAdapter(this.mIconResizer);
    setListAdapter(this.mAdapter);
    getListView().setTextFilterEnabled(true);
    updateAlertTitle();
    updateButtonText();
    if (!this.mPackageManager.hasSystemFeature("android.hardware.type.watch"))
      setProgressBarIndeterminateVisibility(false); 
  }
  
  protected boolean onEvaluateShowIcons() {
    return true;
  }
  
  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong) {
    startActivity(intentForPosition(paramInt));
  }
  
  protected List<ResolveInfo> onQueryPackageManager(Intent paramIntent) {
    return this.mPackageManager.queryIntentActivities(paramIntent, 0);
  }
  
  protected void onSetContentView() {
    setContentView(17367079);
  }
  
  protected void onSortResultList(List<ResolveInfo> paramList) {
    Collections.sort(paramList, (Comparator<? super ResolveInfo>)new ResolveInfo.DisplayNameComparator(this.mPackageManager));
  }
  
  public void setTitle(int paramInt) {
    super.setTitle(paramInt);
    updateAlertTitle();
  }
  
  public void setTitle(CharSequence paramCharSequence) {
    super.setTitle(paramCharSequence);
    updateAlertTitle();
  }
  
  private class ActivityAdapter extends BaseAdapter implements Filterable {
    private final Object lock = new Object();
    
    protected List<LauncherActivity.ListItem> mActivitiesList;
    
    private Filter mFilter;
    
    protected final LauncherActivity.IconResizer mIconResizer;
    
    protected final LayoutInflater mInflater;
    
    private ArrayList<LauncherActivity.ListItem> mOriginalValues;
    
    private final boolean mShowIcons;
    
    public ActivityAdapter(LauncherActivity.IconResizer param1IconResizer) {
      this.mIconResizer = param1IconResizer;
      this.mInflater = (LayoutInflater)LauncherActivity.this.getSystemService("layout_inflater");
      this.mShowIcons = LauncherActivity.this.onEvaluateShowIcons();
      this.mActivitiesList = LauncherActivity.this.makeListItems();
    }
    
    private void bindView(View param1View, LauncherActivity.ListItem param1ListItem) {
      TextView textView = (TextView)param1View;
      textView.setText(param1ListItem.label);
      if (this.mShowIcons) {
        if (param1ListItem.icon == null)
          param1ListItem.icon = this.mIconResizer.createIconThumbnail(param1ListItem.resolveInfo.loadIcon(LauncherActivity.this.getPackageManager())); 
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(param1ListItem.icon, null, null, null);
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
    
    public Object getItem(int param1Int) {
      return Integer.valueOf(param1Int);
    }
    
    public long getItemId(int param1Int) {
      return param1Int;
    }
    
    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      if (param1View == null)
        param1View = this.mInflater.inflate(17367080, param1ViewGroup, false); 
      bindView(param1View, this.mActivitiesList.get(param1Int));
      return param1View;
    }
    
    public Intent intentForPosition(int param1Int) {
      if (this.mActivitiesList == null)
        return null; 
      Intent intent = new Intent(LauncherActivity.this.mIntent);
      LauncherActivity.ListItem listItem = this.mActivitiesList.get(param1Int);
      intent.setClassName(listItem.packageName, listItem.className);
      if (listItem.extras != null)
        intent.putExtras(listItem.extras); 
      return intent;
    }
    
    public LauncherActivity.ListItem itemForPosition(int param1Int) {
      List<LauncherActivity.ListItem> list = this.mActivitiesList;
      return (list == null) ? null : list.get(param1Int);
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
  
  private class ArrayFilter extends Filter {
    private ArrayFilter() {}
    
    protected Filter.FilterResults performFiltering(CharSequence param1CharSequence) {
      Filter.FilterResults filterResults = new Filter.FilterResults();
      if (LauncherActivity.ActivityAdapter.this.mOriginalValues == null)
        synchronized (LauncherActivity.ActivityAdapter.this.lock) {
          LauncherActivity.ActivityAdapter activityAdapter = LauncherActivity.ActivityAdapter.this;
          ArrayList arrayList = new ArrayList();
          this((Collection)LauncherActivity.ActivityAdapter.this.mActivitiesList);
          LauncherActivity.ActivityAdapter.access$102(activityAdapter, arrayList);
        }  
      if (param1CharSequence == null || param1CharSequence.length() == 0)
        synchronized (LauncherActivity.ActivityAdapter.this.lock) {
          ArrayList arrayList = new ArrayList();
          this((Collection)LauncherActivity.ActivityAdapter.this.mOriginalValues);
          filterResults.values = arrayList;
          filterResults.count = arrayList.size();
          return filterResults;
        }  
      String str = param1CharSequence.toString().toLowerCase();
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
    
    protected void publishResults(CharSequence param1CharSequence, Filter.FilterResults param1FilterResults) {
      LauncherActivity.ActivityAdapter.this.mActivitiesList = (List<LauncherActivity.ListItem>)param1FilterResults.values;
      if (param1FilterResults.count > 0) {
        LauncherActivity.ActivityAdapter.this.notifyDataSetChanged();
      } else {
        LauncherActivity.ActivityAdapter.this.notifyDataSetInvalidated();
      } 
    }
  }
  
  public class IconResizer {
    private Canvas mCanvas;
    
    private int mIconHeight = -1;
    
    private int mIconWidth = -1;
    
    private final Rect mOldBounds = new Rect();
    
    public IconResizer() {
      Canvas canvas = new Canvas();
      this.mCanvas = canvas;
      canvas.setDrawFilter((DrawFilter)new PaintFlagsDrawFilter(4, 2));
      int i = (int)LauncherActivity.this.getResources().getDimension(17104896);
      this.mIconHeight = i;
      this.mIconWidth = i;
    }
    
    public Drawable createIconThumbnail(Drawable param1Drawable) {
      BitmapDrawable bitmapDrawable;
      int i = this.mIconWidth;
      int j = this.mIconHeight;
      int k = param1Drawable.getIntrinsicWidth();
      int m = param1Drawable.getIntrinsicHeight();
      if (param1Drawable instanceof PaintDrawable) {
        PaintDrawable paintDrawable = (PaintDrawable)param1Drawable;
        paintDrawable.setIntrinsicWidth(i);
        paintDrawable.setIntrinsicHeight(j);
      } 
      Drawable drawable = param1Drawable;
      if (i > 0) {
        drawable = param1Drawable;
        if (j > 0) {
          if (i < k || j < m) {
            Bitmap.Config config;
            int n;
            float f = k / m;
            if (k > m) {
              n = (int)(i / f);
            } else {
              n = j;
              if (m > k) {
                i = (int)(j * f);
                n = j;
              } 
            } 
            if (param1Drawable.getOpacity() != -1) {
              config = Bitmap.Config.ARGB_8888;
            } else {
              config = Bitmap.Config.RGB_565;
            } 
            Bitmap bitmap = Bitmap.createBitmap(this.mIconWidth, this.mIconHeight, config);
            Canvas canvas = this.mCanvas;
            canvas.setBitmap(bitmap);
            this.mOldBounds.set(param1Drawable.getBounds());
            k = (this.mIconWidth - i) / 2;
            j = (this.mIconHeight - n) / 2;
            param1Drawable.setBounds(k, j, k + i, j + n);
            param1Drawable.draw(canvas);
            param1Drawable.setBounds(this.mOldBounds);
            bitmapDrawable = new BitmapDrawable(LauncherActivity.this.getResources(), bitmap);
            canvas.setBitmap(null);
            return (Drawable)bitmapDrawable;
          } 
          drawable = param1Drawable;
          if (k < i) {
            drawable = param1Drawable;
            if (m < j) {
              Bitmap.Config config = Bitmap.Config.ARGB_8888;
              Bitmap bitmap = Bitmap.createBitmap(this.mIconWidth, this.mIconHeight, config);
              Canvas canvas = this.mCanvas;
              canvas.setBitmap(bitmap);
              this.mOldBounds.set(param1Drawable.getBounds());
              int n = (i - k) / 2;
              i = (j - m) / 2;
              param1Drawable.setBounds(n, i, n + k, i + m);
              param1Drawable.draw(canvas);
              param1Drawable.setBounds(this.mOldBounds);
              bitmapDrawable = new BitmapDrawable(LauncherActivity.this.getResources(), bitmap);
              canvas.setBitmap(null);
            } 
          } 
        } 
      } 
      return (Drawable)bitmapDrawable;
    }
  }
  
  public static class ListItem {
    public String className;
    
    public Bundle extras;
    
    public Drawable icon;
    
    public CharSequence label;
    
    public String packageName;
    
    public ResolveInfo resolveInfo;
    
    public ListItem() {}
    
    ListItem(PackageManager param1PackageManager, ResolveInfo param1ResolveInfo, LauncherActivity.IconResizer param1IconResizer) {
      ServiceInfo serviceInfo;
      this.resolveInfo = param1ResolveInfo;
      this.label = param1ResolveInfo.loadLabel(param1PackageManager);
      ActivityInfo activityInfo1 = param1ResolveInfo.activityInfo;
      ActivityInfo activityInfo2 = activityInfo1;
      if (activityInfo1 == null)
        serviceInfo = param1ResolveInfo.serviceInfo; 
      if (this.label == null && serviceInfo != null)
        this.label = param1ResolveInfo.activityInfo.name; 
      if (param1IconResizer != null)
        this.icon = param1IconResizer.createIconThumbnail(param1ResolveInfo.loadIcon(param1PackageManager)); 
      this.packageName = ((ComponentInfo)serviceInfo).applicationInfo.packageName;
      this.className = ((ComponentInfo)serviceInfo).name;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/LauncherActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */