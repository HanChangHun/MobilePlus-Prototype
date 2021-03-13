package android.app;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

@Deprecated
public class ExpandableListActivity extends Activity implements View.OnCreateContextMenuListener, ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupCollapseListener, ExpandableListView.OnGroupExpandListener {
  ExpandableListAdapter mAdapter;
  
  boolean mFinishedStart = false;
  
  ExpandableListView mList;
  
  private void ensureList() {
    if (this.mList != null)
      return; 
    setContentView(17367041);
  }
  
  public ExpandableListAdapter getExpandableListAdapter() {
    return this.mAdapter;
  }
  
  public ExpandableListView getExpandableListView() {
    ensureList();
    return this.mList;
  }
  
  public long getSelectedId() {
    return this.mList.getSelectedId();
  }
  
  public long getSelectedPosition() {
    return this.mList.getSelectedPosition();
  }
  
  public boolean onChildClick(ExpandableListView paramExpandableListView, View paramView, int paramInt1, int paramInt2, long paramLong) {
    return false;
  }
  
  public void onContentChanged() {
    super.onContentChanged();
    View view = (View)findViewById(16908292);
    ExpandableListView expandableListView = findViewById(16908298);
    this.mList = expandableListView;
    if (expandableListView != null) {
      if (view != null)
        expandableListView.setEmptyView(view); 
      this.mList.setOnChildClickListener(this);
      this.mList.setOnGroupExpandListener(this);
      this.mList.setOnGroupCollapseListener(this);
      if (this.mFinishedStart)
        setListAdapter(this.mAdapter); 
      this.mFinishedStart = true;
      return;
    } 
    throw new RuntimeException("Your content must have a ExpandableListView whose id attribute is 'android.R.id.list'");
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo) {}
  
  public void onGroupCollapse(int paramInt) {}
  
  public void onGroupExpand(int paramInt) {}
  
  protected void onRestoreInstanceState(Bundle paramBundle) {
    ensureList();
    super.onRestoreInstanceState(paramBundle);
  }
  
  public void setListAdapter(ExpandableListAdapter paramExpandableListAdapter) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial ensureList : ()V
    //   6: aload_0
    //   7: aload_1
    //   8: putfield mAdapter : Landroid/widget/ExpandableListAdapter;
    //   11: aload_0
    //   12: getfield mList : Landroid/widget/ExpandableListView;
    //   15: aload_1
    //   16: invokevirtual setAdapter : (Landroid/widget/ExpandableListAdapter;)V
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
  
  public boolean setSelectedChild(int paramInt1, int paramInt2, boolean paramBoolean) {
    return this.mList.setSelectedChild(paramInt1, paramInt2, paramBoolean);
  }
  
  public void setSelectedGroup(int paramInt) {
    this.mList.setSelectedGroup(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ExpandableListActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */