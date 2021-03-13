package android.app;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

@Deprecated
public class ListFragment extends Fragment {
  ListAdapter mAdapter;
  
  CharSequence mEmptyText;
  
  View mEmptyView;
  
  private final Handler mHandler = new Handler();
  
  ListView mList;
  
  View mListContainer;
  
  boolean mListShown;
  
  private final AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
        ListFragment.this.onListItemClick((ListView)param1AdapterView, param1View, param1Int, param1Long);
      }
    };
  
  View mProgressContainer;
  
  private final Runnable mRequestFocus = new Runnable() {
      public void run() {
        ListFragment.this.mList.focusableViewAvailable((View)ListFragment.this.mList);
      }
    };
  
  TextView mStandardEmptyView;
  
  private void ensureList() {
    if (this.mList != null)
      return; 
    View view = getView();
    if (view != null) {
      if (view instanceof ListView) {
        this.mList = (ListView)view;
      } else {
        TextView textView = (TextView)view.findViewById(16909093);
        this.mStandardEmptyView = textView;
        if (textView == null) {
          this.mEmptyView = view.findViewById(16908292);
        } else {
          textView.setVisibility(8);
        } 
        this.mProgressContainer = view.findViewById(16909321);
        this.mListContainer = view.findViewById(16909126);
        View view1 = view.findViewById(16908298);
        if (view1 instanceof ListView) {
          ListView listView = (ListView)view1;
          this.mList = listView;
          if (listView != null) {
            view1 = this.mEmptyView;
            if (view1 != null) {
              listView.setEmptyView(view1);
            } else {
              CharSequence charSequence = this.mEmptyText;
              if (charSequence != null) {
                this.mStandardEmptyView.setText(charSequence);
                this.mList.setEmptyView((View)this.mStandardEmptyView);
              } 
            } 
          } else {
            throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
          } 
        } else {
          throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
        } 
      } 
      this.mListShown = true;
      this.mList.setOnItemClickListener(this.mOnClickListener);
      if (this.mAdapter != null) {
        ListAdapter listAdapter = this.mAdapter;
        this.mAdapter = null;
        setListAdapter(listAdapter);
      } else if (this.mProgressContainer != null) {
        setListShown(false, false);
      } 
      this.mHandler.post(this.mRequestFocus);
      return;
    } 
    throw new IllegalStateException("Content view not yet created");
  }
  
  private void setListShown(boolean paramBoolean1, boolean paramBoolean2) {
    ensureList();
    View view = this.mProgressContainer;
    if (view != null) {
      if (this.mListShown == paramBoolean1)
        return; 
      this.mListShown = paramBoolean1;
      if (paramBoolean1) {
        if (paramBoolean2) {
          view.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
          this.mListContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
        } else {
          view.clearAnimation();
          this.mListContainer.clearAnimation();
        } 
        this.mProgressContainer.setVisibility(8);
        this.mListContainer.setVisibility(0);
      } else {
        if (paramBoolean2) {
          view.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
          this.mListContainer.startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
        } else {
          view.clearAnimation();
          this.mListContainer.clearAnimation();
        } 
        this.mProgressContainer.setVisibility(0);
        this.mListContainer.setVisibility(8);
      } 
      return;
    } 
    throw new IllegalStateException("Can't be used with a custom content view");
  }
  
  public ListAdapter getListAdapter() {
    return this.mAdapter;
  }
  
  public ListView getListView() {
    ensureList();
    return this.mList;
  }
  
  public long getSelectedItemId() {
    ensureList();
    return this.mList.getSelectedItemId();
  }
  
  public int getSelectedItemPosition() {
    ensureList();
    return this.mList.getSelectedItemPosition();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    return paramLayoutInflater.inflate(17367060, paramViewGroup, false);
  }
  
  public void onDestroyView() {
    this.mHandler.removeCallbacks(this.mRequestFocus);
    this.mList = null;
    this.mListShown = false;
    this.mListContainer = null;
    this.mProgressContainer = null;
    this.mEmptyView = null;
    this.mStandardEmptyView = null;
    super.onDestroyView();
  }
  
  public void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong) {}
  
  public void onViewCreated(View paramView, Bundle paramBundle) {
    super.onViewCreated(paramView, paramBundle);
    ensureList();
  }
  
  public void setEmptyText(CharSequence paramCharSequence) {
    ensureList();
    TextView textView = this.mStandardEmptyView;
    if (textView != null) {
      textView.setText(paramCharSequence);
      if (this.mEmptyText == null)
        this.mList.setEmptyView((View)this.mStandardEmptyView); 
      this.mEmptyText = paramCharSequence;
      return;
    } 
    throw new IllegalStateException("Can't be used with a custom content view");
  }
  
  public void setListAdapter(ListAdapter paramListAdapter) {
    boolean bool2;
    ListAdapter listAdapter = this.mAdapter;
    boolean bool1 = false;
    if (listAdapter != null) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mAdapter = paramListAdapter;
    ListView listView = this.mList;
    if (listView != null) {
      listView.setAdapter(paramListAdapter);
      if (!this.mListShown && !bool2) {
        if (getView().getWindowToken() != null)
          bool1 = true; 
        setListShown(true, bool1);
      } 
    } 
  }
  
  public void setListShown(boolean paramBoolean) {
    setListShown(paramBoolean, true);
  }
  
  public void setListShownNoAnimation(boolean paramBoolean) {
    setListShown(paramBoolean, false);
  }
  
  public void setSelection(int paramInt) {
    ensureList();
    this.mList.setSelection(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ListFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */