package android.app;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

@Deprecated
public class ListActivity extends Activity {
  protected ListAdapter mAdapter;
  
  private boolean mFinishedStart = false;
  
  private Handler mHandler = new Handler();
  
  protected ListView mList;
  
  private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
        ListActivity.this.onListItemClick((ListView)param1AdapterView, param1View, param1Int, param1Long);
      }
    };
  
  private Runnable mRequestFocus = new Runnable() {
      public void run() {
        ListActivity.this.mList.focusableViewAvailable((View)ListActivity.this.mList);
      }
    };
  
  private void ensureList() {
    if (this.mList != null)
      return; 
    setContentView(17367186);
  }
  
  public ListAdapter getListAdapter() {
    return this.mAdapter;
  }
  
  public ListView getListView() {
    ensureList();
    return this.mList;
  }
  
  public long getSelectedItemId() {
    return this.mList.getSelectedItemId();
  }
  
  public int getSelectedItemPosition() {
    return this.mList.getSelectedItemPosition();
  }
  
  public void onContentChanged() {
    super.onContentChanged();
    View view = (View)findViewById(16908292);
    ListView listView = findViewById(16908298);
    this.mList = listView;
    if (listView != null) {
      if (view != null)
        listView.setEmptyView(view); 
      this.mList.setOnItemClickListener(this.mOnClickListener);
      if (this.mFinishedStart)
        setListAdapter(this.mAdapter); 
      this.mHandler.post(this.mRequestFocus);
      this.mFinishedStart = true;
      return;
    } 
    throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
  }
  
  protected void onDestroy() {
    this.mHandler.removeCallbacks(this.mRequestFocus);
    super.onDestroy();
  }
  
  protected void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong) {}
  
  protected void onRestoreInstanceState(Bundle paramBundle) {
    ensureList();
    super.onRestoreInstanceState(paramBundle);
  }
  
  public void setListAdapter(ListAdapter paramListAdapter) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureList : ()V
    //   6: aload_0
    //   7: aload_1
    //   8: putfield mAdapter : Landroid/widget/ListAdapter;
    //   11: aload_0
    //   12: getfield mList : Landroid/widget/ListView;
    //   15: aload_1
    //   16: invokevirtual setAdapter : (Landroid/widget/ListAdapter;)V
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	22	finally
    //   23	25	22	finally
  }
  
  public void setSelection(int paramInt) {
    this.mList.setSelection(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ListActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */