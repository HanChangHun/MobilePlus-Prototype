package android.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.internal.R;

@Deprecated
public class FragmentBreadCrumbs extends ViewGroup implements FragmentManager.OnBackStackChangedListener {
  private static final int DEFAULT_GRAVITY = 8388627;
  
  Activity mActivity;
  
  LinearLayout mContainer;
  
  private int mGravity;
  
  LayoutInflater mInflater;
  
  private int mLayoutResId;
  
  int mMaxVisible = -1;
  
  private OnBreadCrumbClickListener mOnBreadCrumbClickListener;
  
  private View.OnClickListener mOnClickListener = new View.OnClickListener() {
      public void onClick(View param1View) {
        if (param1View.getTag() instanceof FragmentManager.BackStackEntry) {
          FragmentManager.BackStackEntry backStackEntry = (FragmentManager.BackStackEntry)param1View.getTag();
          if (backStackEntry == FragmentBreadCrumbs.this.mParentEntry) {
            if (FragmentBreadCrumbs.this.mParentClickListener != null)
              FragmentBreadCrumbs.this.mParentClickListener.onClick(param1View); 
          } else {
            if (FragmentBreadCrumbs.this.mOnBreadCrumbClickListener != null) {
              FragmentManager.BackStackEntry backStackEntry1;
              FragmentBreadCrumbs.OnBreadCrumbClickListener onBreadCrumbClickListener = FragmentBreadCrumbs.this.mOnBreadCrumbClickListener;
              if (backStackEntry == FragmentBreadCrumbs.this.mTopEntry) {
                param1View = null;
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
    };
  
  private View.OnClickListener mParentClickListener;
  
  BackStackRecord mParentEntry;
  
  private int mTextColor;
  
  BackStackRecord mTopEntry;
  
  public FragmentBreadCrumbs(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public FragmentBreadCrumbs(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 17956935);
  }
  
  public FragmentBreadCrumbs(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  public FragmentBreadCrumbs(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.FragmentBreadCrumbs, paramInt1, paramInt2);
    this.mGravity = typedArray.getInt(0, 8388627);
    this.mLayoutResId = typedArray.getResourceId(2, 17367163);
    this.mTextColor = typedArray.getColor(1, 0);
    typedArray.recycle();
  }
  
  private BackStackRecord createBackStackEntry(CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    if (paramCharSequence1 == null)
      return null; 
    BackStackRecord backStackRecord = new BackStackRecord((FragmentManagerImpl)this.mActivity.getFragmentManager());
    backStackRecord.setBreadCrumbTitle(paramCharSequence1);
    backStackRecord.setBreadCrumbShortTitle(paramCharSequence2);
    return backStackRecord;
  }
  
  private FragmentManager.BackStackEntry getPreEntry(int paramInt) {
    BackStackRecord backStackRecord = this.mParentEntry;
    if (backStackRecord != null) {
      if (paramInt != 0)
        backStackRecord = this.mTopEntry; 
      return backStackRecord;
    } 
    return this.mTopEntry;
  }
  
  private int getPreEntryCount() {
    byte b2;
    BackStackRecord backStackRecord = this.mTopEntry;
    byte b1 = 1;
    if (backStackRecord != null) {
      b2 = 1;
    } else {
      b2 = 0;
    } 
    if (this.mParentEntry == null)
      b1 = 0; 
    return b2 + b1;
  }
  
  public void onBackStackChanged() {
    updateCrumbs();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (getChildCount() == 0)
      return; 
    View view = getChildAt(0);
    int i = this.mPaddingTop;
    int j = this.mPaddingTop;
    paramInt4 = view.getMeasuredHeight();
    int k = this.mPaddingBottom;
    paramInt1 = getLayoutDirection();
    paramInt1 = Gravity.getAbsoluteGravity(this.mGravity & 0x800007, paramInt1);
    if (paramInt1 != 1) {
      if (paramInt1 != 5) {
        paramInt2 = this.mPaddingLeft;
        paramInt1 = view.getMeasuredWidth() + paramInt2;
      } else {
        paramInt1 = this.mRight - this.mLeft - this.mPaddingRight;
        paramInt2 = paramInt1 - view.getMeasuredWidth();
      } 
    } else {
      paramInt2 = this.mPaddingLeft + (this.mRight - this.mLeft - view.getMeasuredWidth()) / 2;
      paramInt1 = view.getMeasuredWidth() + paramInt2;
    } 
    paramInt3 = paramInt2;
    if (paramInt2 < this.mPaddingLeft)
      paramInt3 = this.mPaddingLeft; 
    paramInt2 = paramInt1;
    if (paramInt1 > this.mRight - this.mLeft - this.mPaddingRight)
      paramInt2 = this.mRight - this.mLeft - this.mPaddingRight; 
    view.layout(paramInt3, i, paramInt2, j + paramInt4 - k);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    int i = getChildCount();
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    while (n < i) {
      View view = getChildAt(n);
      int i2 = j;
      int i3 = k;
      int i4 = m;
      if (view.getVisibility() != 8) {
        measureChild(view, paramInt1, paramInt2);
        i3 = Math.max(k, view.getMeasuredWidth());
        i2 = Math.max(j, view.getMeasuredHeight());
        i4 = combineMeasuredStates(m, view.getMeasuredState());
      } 
      n++;
      j = i2;
      k = i3;
      m = i4;
    } 
    n = this.mPaddingLeft;
    int i1 = this.mPaddingRight;
    j = Math.max(j + this.mPaddingTop + this.mPaddingBottom, getSuggestedMinimumHeight());
    setMeasuredDimension(resolveSizeAndState(Math.max(k + n + i1, getSuggestedMinimumWidth()), paramInt1, m), resolveSizeAndState(j, paramInt2, m << 16));
  }
  
  public void setActivity(Activity paramActivity) {
    this.mActivity = paramActivity;
    LayoutInflater layoutInflater = (LayoutInflater)paramActivity.getSystemService("layout_inflater");
    this.mInflater = layoutInflater;
    LinearLayout linearLayout = (LinearLayout)layoutInflater.inflate(17367165, this, false);
    this.mContainer = linearLayout;
    addView((View)linearLayout);
    paramActivity.getFragmentManager().addOnBackStackChangedListener(this);
    updateCrumbs();
    setLayoutTransition(new LayoutTransition());
  }
  
  public void setMaxVisible(int paramInt) {
    if (paramInt >= 1) {
      this.mMaxVisible = paramInt;
      return;
    } 
    throw new IllegalArgumentException("visibleCrumbs must be greater than zero");
  }
  
  public void setOnBreadCrumbClickListener(OnBreadCrumbClickListener paramOnBreadCrumbClickListener) {
    this.mOnBreadCrumbClickListener = paramOnBreadCrumbClickListener;
  }
  
  public void setParentTitle(CharSequence paramCharSequence1, CharSequence paramCharSequence2, View.OnClickListener paramOnClickListener) {
    this.mParentEntry = createBackStackEntry(paramCharSequence1, paramCharSequence2);
    this.mParentClickListener = paramOnClickListener;
    updateCrumbs();
  }
  
  public void setTitle(CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    this.mTopEntry = createBackStackEntry(paramCharSequence1, paramCharSequence2);
    updateCrumbs();
  }
  
  void updateCrumbs() {
    FragmentManager fragmentManager = this.mActivity.getFragmentManager();
    int i = fragmentManager.getBackStackEntryCount();
    int j = getPreEntryCount();
    int k = this.mContainer.getChildCount();
    int m = 0;
    while (m < i + j) {
      FragmentManager.BackStackEntry backStackEntry;
      if (m < j) {
        backStackEntry = getPreEntry(m);
      } else {
        backStackEntry = fragmentManager.getBackStackEntryAt(m - j);
      } 
      int n = k;
      if (m < k) {
        n = k;
        if (this.mContainer.getChildAt(m).getTag() != backStackEntry) {
          for (n = m; n < k; n++)
            this.mContainer.removeViewAt(m); 
          n = m;
        } 
      } 
      if (m >= n) {
        View view = this.mInflater.inflate(this.mLayoutResId, this, false);
        TextView textView = (TextView)view.findViewById(16908310);
        textView.setText(backStackEntry.getBreadCrumbTitle());
        textView.setTag(backStackEntry);
        textView.setTextColor(this.mTextColor);
        if (m == 0)
          view.findViewById(16909121).setVisibility(8); 
        this.mContainer.addView(view);
        textView.setOnClickListener(this.mOnClickListener);
      } 
      m++;
      k = n;
    } 
    for (m = this.mContainer.getChildCount(); m > i + j; m--)
      this.mContainer.removeViewAt(m - 1); 
    for (k = 0; k < m; k++) {
      boolean bool;
      View view1 = this.mContainer.getChildAt(k);
      View view2 = view1.findViewById(16908310);
      if (k < m - 1) {
        bool = true;
      } else {
        bool = false;
      } 
      view2.setEnabled(bool);
      int n = this.mMaxVisible;
      if (n > 0) {
        if (k < m - n) {
          n = 8;
        } else {
          n = 0;
        } 
        view1.setVisibility(n);
        view2 = view1.findViewById(16909121);
        if (k > m - this.mMaxVisible && k != 0) {
          n = 0;
        } else {
          n = 8;
        } 
        view2.setVisibility(n);
      } 
    } 
  }
  
  @Deprecated
  public static interface OnBreadCrumbClickListener {
    boolean onBreadCrumbClick(FragmentManager.BackStackEntry param1BackStackEntry, int param1Int);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentBreadCrumbs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */