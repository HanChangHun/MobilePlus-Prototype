package android.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.DebugUtils;
import android.util.Log;
import android.util.LogWriter;
import android.util.Pair;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.R;
import com.android.internal.util.FastPrintWriter;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class FragmentManagerImpl extends FragmentManager implements LayoutInflater.Factory2 {
  static boolean DEBUG = false;
  
  static final String TAG = "FragmentManager";
  
  static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
  
  static final String TARGET_STATE_TAG = "android:target_state";
  
  static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
  
  static final String VIEW_STATE_TAG = "android:view_state";
  
  SparseArray<Fragment> mActive;
  
  final ArrayList<Fragment> mAdded = new ArrayList<>();
  
  boolean mAllowOldReentrantBehavior;
  
  ArrayList<Integer> mAvailBackStackIndices;
  
  ArrayList<BackStackRecord> mBackStack;
  
  ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
  
  ArrayList<BackStackRecord> mBackStackIndices;
  
  FragmentContainer mContainer;
  
  ArrayList<Fragment> mCreatedMenus;
  
  int mCurState = 0;
  
  boolean mDestroyed;
  
  Runnable mExecCommit = new Runnable() {
      public void run() {
        FragmentManagerImpl.this.execPendingActions();
      }
    };
  
  boolean mExecutingActions;
  
  boolean mHavePendingDeferredStart;
  
  FragmentHostCallback<?> mHost;
  
  final CopyOnWriteArrayList<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> mLifecycleCallbacks = new CopyOnWriteArrayList<>();
  
  boolean mNeedMenuInvalidate;
  
  int mNextFragmentIndex = 0;
  
  String mNoTransactionsBecause;
  
  Fragment mParent;
  
  ArrayList<OpGenerator> mPendingActions;
  
  ArrayList<StartEnterTransitionListener> mPostponedTransactions;
  
  Fragment mPrimaryNav;
  
  FragmentManagerNonConfig mSavedNonConfig;
  
  SparseArray<Parcelable> mStateArray = null;
  
  Bundle mStateBundle = null;
  
  boolean mStateSaved;
  
  ArrayList<Fragment> mTmpAddedFragments;
  
  ArrayList<Boolean> mTmpIsPop;
  
  ArrayList<BackStackRecord> mTmpRecords;
  
  private void addAddedFragments(ArraySet<Fragment> paramArraySet) {
    int i = this.mCurState;
    if (i < 1)
      return; 
    int j = Math.min(i, 4);
    int k = this.mAdded.size();
    for (i = 0; i < k; i++) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment.mState < j) {
        moveToState(fragment, j, fragment.getNextAnim(), fragment.getNextTransition(), false);
        if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded)
          paramArraySet.add(fragment); 
      } 
    } 
  }
  
  private void burpActive() {
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null)
      for (int i = sparseArray.size() - 1; i >= 0; i--) {
        if (this.mActive.valueAt(i) == null) {
          sparseArray = this.mActive;
          sparseArray.delete(sparseArray.keyAt(i));
        } 
      }  
  }
  
  private void checkStateLoss() {
    if (!this.mStateSaved) {
      if (this.mNoTransactionsBecause == null)
        return; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Can not perform this action inside of ");
      stringBuilder.append(this.mNoTransactionsBecause);
      throw new IllegalStateException(stringBuilder.toString());
    } 
    throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
  }
  
  private void cleanupExec() {
    this.mExecutingActions = false;
    this.mTmpIsPop.clear();
    this.mTmpRecords.clear();
  }
  
  private void completeExecute(BackStackRecord paramBackStackRecord, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    if (paramBoolean1) {
      paramBackStackRecord.executePopOps(paramBoolean3);
    } else {
      paramBackStackRecord.executeOps();
    } 
    ArrayList<BackStackRecord> arrayList = new ArrayList(1);
    ArrayList<Boolean> arrayList1 = new ArrayList(1);
    arrayList.add(paramBackStackRecord);
    arrayList1.add(Boolean.valueOf(paramBoolean1));
    if (paramBoolean2)
      FragmentTransition.startTransitions(this, arrayList, arrayList1, 0, 1, true); 
    if (paramBoolean3)
      moveToState(this.mCurState, true); 
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null) {
      int i = sparseArray.size();
      for (byte b = 0; b < i; b++) {
        Fragment fragment = (Fragment)this.mActive.valueAt(b);
        if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && paramBackStackRecord.interactsWith(fragment.mContainerId))
          fragment.mIsNewlyAdded = false; 
      } 
    } 
  }
  
  private void dispatchMoveToState(int paramInt) {
    if (this.mAllowOldReentrantBehavior) {
      moveToState(paramInt, false);
    } else {
      try {
        this.mExecutingActions = true;
        moveToState(paramInt, false);
        this.mExecutingActions = false;
        return;
      } finally {
        this.mExecutingActions = false;
      } 
    } 
    execPendingActions();
  }
  
  private void endAnimatingAwayFragments() {
    int i;
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray == null) {
      i = 0;
    } else {
      i = sparseArray.size();
    } 
    for (byte b = 0; b < i; b++) {
      Fragment fragment = (Fragment)this.mActive.valueAt(b);
      if (fragment != null && fragment.getAnimatingAway() != null)
        fragment.getAnimatingAway().end(); 
    } 
  }
  
  private void ensureExecReady(boolean paramBoolean) {
    if (!this.mExecutingActions) {
      if (Looper.myLooper() == this.mHost.getHandler().getLooper()) {
        if (!paramBoolean)
          checkStateLoss(); 
        if (this.mTmpRecords == null) {
          this.mTmpRecords = new ArrayList<>();
          this.mTmpIsPop = new ArrayList<>();
        } 
        this.mExecutingActions = true;
        try {
          executePostponedTransaction(null, null);
          return;
        } finally {
          this.mExecutingActions = false;
        } 
      } 
      throw new IllegalStateException("Must be called from main thread of fragment host");
    } 
    throw new IllegalStateException("FragmentManager is already executing transactions");
  }
  
  private static void executeOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2) {
    while (paramInt1 < paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(paramInt1);
      boolean bool = ((Boolean)paramArrayList1.get(paramInt1)).booleanValue();
      boolean bool1 = true;
      if (bool) {
        backStackRecord.bumpBackStackNesting(-1);
        if (paramInt1 != paramInt2 - 1)
          bool1 = false; 
        backStackRecord.executePopOps(bool1);
      } else {
        backStackRecord.bumpBackStackNesting(1);
        backStackRecord.executeOps();
      } 
      paramInt1++;
    } 
  }
  
  private void executeOpsTogether(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2) {
    boolean bool = ((BackStackRecord)paramArrayList.get(paramInt1)).mReorderingAllowed;
    ArrayList<Fragment> arrayList = this.mTmpAddedFragments;
    if (arrayList == null) {
      this.mTmpAddedFragments = new ArrayList<>();
    } else {
      arrayList.clear();
    } 
    this.mTmpAddedFragments.addAll(this.mAdded);
    Fragment fragment = getPrimaryNavigationFragment();
    int i = paramInt1;
    boolean bool1 = false;
    while (true) {
      boolean bool2 = true;
      if (i < paramInt2) {
        BackStackRecord backStackRecord = paramArrayList.get(i);
        if (!((Boolean)paramArrayList1.get(i)).booleanValue()) {
          fragment = backStackRecord.expandOps(this.mTmpAddedFragments, fragment);
        } else {
          backStackRecord.trackAddedFragmentsInPop(this.mTmpAddedFragments);
        } 
        boolean bool3 = bool2;
        if (!bool1)
          if (backStackRecord.mAddToBackStack) {
            bool3 = bool2;
          } else {
            bool3 = false;
          }  
        i++;
        bool1 = bool3;
        continue;
      } 
      this.mTmpAddedFragments.clear();
      if (!bool)
        FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, paramInt1, paramInt2, false); 
      executeOps(paramArrayList, paramArrayList1, paramInt1, paramInt2);
      int j = paramInt2;
      if (bool) {
        ArraySet<Fragment> arraySet = new ArraySet();
        addAddedFragments(arraySet);
        j = postponePostponableTransactions(paramArrayList, paramArrayList1, paramInt1, paramInt2, arraySet);
        makeRemovedFragmentsInvisible(arraySet);
      } 
      if (j != paramInt1 && bool) {
        FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, paramInt1, j, true);
        moveToState(this.mCurState, true);
      } 
      while (paramInt1 < paramInt2) {
        BackStackRecord backStackRecord = paramArrayList.get(paramInt1);
        if (((Boolean)paramArrayList1.get(paramInt1)).booleanValue() && backStackRecord.mIndex >= 0) {
          freeBackStackIndex(backStackRecord.mIndex);
          backStackRecord.mIndex = -1;
        } 
        backStackRecord.runOnCommitRunnables();
        paramInt1++;
      } 
      if (bool1)
        reportBackStackChanged(); 
      return;
    } 
  }
  
  private void executePostponedTransaction(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   4: astore_3
    //   5: aload_3
    //   6: ifnonnull -> 15
    //   9: iconst_0
    //   10: istore #4
    //   12: goto -> 21
    //   15: aload_3
    //   16: invokevirtual size : ()I
    //   19: istore #4
    //   21: iconst_0
    //   22: istore #5
    //   24: iload #5
    //   26: iload #4
    //   28: if_icmpge -> 232
    //   31: aload_0
    //   32: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   35: iload #5
    //   37: invokevirtual get : (I)Ljava/lang/Object;
    //   40: checkcast android/app/FragmentManagerImpl$StartEnterTransitionListener
    //   43: astore_3
    //   44: aload_1
    //   45: ifnull -> 101
    //   48: aload_3
    //   49: invokestatic access$000 : (Landroid/app/FragmentManagerImpl$StartEnterTransitionListener;)Z
    //   52: ifne -> 101
    //   55: aload_1
    //   56: aload_3
    //   57: invokestatic access$100 : (Landroid/app/FragmentManagerImpl$StartEnterTransitionListener;)Landroid/app/BackStackRecord;
    //   60: invokevirtual indexOf : (Ljava/lang/Object;)I
    //   63: istore #6
    //   65: iload #6
    //   67: iconst_m1
    //   68: if_icmpeq -> 101
    //   71: aload_2
    //   72: iload #6
    //   74: invokevirtual get : (I)Ljava/lang/Object;
    //   77: checkcast java/lang/Boolean
    //   80: invokevirtual booleanValue : ()Z
    //   83: ifeq -> 101
    //   86: aload_3
    //   87: invokevirtual cancelTransaction : ()V
    //   90: iload #4
    //   92: istore #6
    //   94: iload #5
    //   96: istore #7
    //   98: goto -> 219
    //   101: aload_3
    //   102: invokevirtual isReady : ()Z
    //   105: ifne -> 144
    //   108: iload #4
    //   110: istore #6
    //   112: iload #5
    //   114: istore #7
    //   116: aload_1
    //   117: ifnull -> 219
    //   120: iload #4
    //   122: istore #6
    //   124: iload #5
    //   126: istore #7
    //   128: aload_3
    //   129: invokestatic access$100 : (Landroid/app/FragmentManagerImpl$StartEnterTransitionListener;)Landroid/app/BackStackRecord;
    //   132: aload_1
    //   133: iconst_0
    //   134: aload_1
    //   135: invokevirtual size : ()I
    //   138: invokevirtual interactsWith : (Ljava/util/ArrayList;II)Z
    //   141: ifeq -> 219
    //   144: aload_0
    //   145: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   148: iload #5
    //   150: invokevirtual remove : (I)Ljava/lang/Object;
    //   153: pop
    //   154: iload #5
    //   156: iconst_1
    //   157: isub
    //   158: istore #7
    //   160: iload #4
    //   162: iconst_1
    //   163: isub
    //   164: istore #6
    //   166: aload_1
    //   167: ifnull -> 215
    //   170: aload_3
    //   171: invokestatic access$000 : (Landroid/app/FragmentManagerImpl$StartEnterTransitionListener;)Z
    //   174: ifne -> 215
    //   177: aload_1
    //   178: aload_3
    //   179: invokestatic access$100 : (Landroid/app/FragmentManagerImpl$StartEnterTransitionListener;)Landroid/app/BackStackRecord;
    //   182: invokevirtual indexOf : (Ljava/lang/Object;)I
    //   185: istore #5
    //   187: iload #5
    //   189: iconst_m1
    //   190: if_icmpeq -> 215
    //   193: aload_2
    //   194: iload #5
    //   196: invokevirtual get : (I)Ljava/lang/Object;
    //   199: checkcast java/lang/Boolean
    //   202: invokevirtual booleanValue : ()Z
    //   205: ifeq -> 215
    //   208: aload_3
    //   209: invokevirtual cancelTransaction : ()V
    //   212: goto -> 219
    //   215: aload_3
    //   216: invokevirtual completeTransaction : ()V
    //   219: iload #7
    //   221: iconst_1
    //   222: iadd
    //   223: istore #5
    //   225: iload #6
    //   227: istore #4
    //   229: goto -> 24
    //   232: return
  }
  
  private Fragment findFragmentUnder(Fragment paramFragment) {
    ViewGroup viewGroup = paramFragment.mContainer;
    View view = paramFragment.mView;
    if (viewGroup == null || view == null)
      return null; 
    for (int i = this.mAdded.indexOf(paramFragment) - 1; i >= 0; i--) {
      paramFragment = this.mAdded.get(i);
      if (paramFragment.mContainer == viewGroup && paramFragment.mView != null)
        return paramFragment; 
    } 
    return null;
  }
  
  private void forcePostponedTransactions() {
    if (this.mPostponedTransactions != null)
      while (!this.mPostponedTransactions.isEmpty())
        ((StartEnterTransitionListener)this.mPostponedTransactions.remove(0)).completeTransaction();  
  }
  
  private boolean generateOpsForPendingActions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield mPendingActions : Ljava/util/ArrayList;
    //   8: ifnull -> 96
    //   11: aload_0
    //   12: getfield mPendingActions : Ljava/util/ArrayList;
    //   15: invokevirtual size : ()I
    //   18: ifne -> 24
    //   21: goto -> 96
    //   24: aload_0
    //   25: getfield mPendingActions : Ljava/util/ArrayList;
    //   28: invokevirtual size : ()I
    //   31: istore #4
    //   33: iconst_0
    //   34: istore #5
    //   36: iload #5
    //   38: iload #4
    //   40: if_icmpge -> 71
    //   43: iload_3
    //   44: aload_0
    //   45: getfield mPendingActions : Ljava/util/ArrayList;
    //   48: iload #5
    //   50: invokevirtual get : (I)Ljava/lang/Object;
    //   53: checkcast android/app/FragmentManagerImpl$OpGenerator
    //   56: aload_1
    //   57: aload_2
    //   58: invokeinterface generateOps : (Ljava/util/ArrayList;Ljava/util/ArrayList;)Z
    //   63: ior
    //   64: istore_3
    //   65: iinc #5, 1
    //   68: goto -> 36
    //   71: aload_0
    //   72: getfield mPendingActions : Ljava/util/ArrayList;
    //   75: invokevirtual clear : ()V
    //   78: aload_0
    //   79: getfield mHost : Landroid/app/FragmentHostCallback;
    //   82: invokevirtual getHandler : ()Landroid/os/Handler;
    //   85: aload_0
    //   86: getfield mExecCommit : Ljava/lang/Runnable;
    //   89: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
    //   92: aload_0
    //   93: monitorexit
    //   94: iload_3
    //   95: ireturn
    //   96: aload_0
    //   97: monitorexit
    //   98: iconst_0
    //   99: ireturn
    //   100: astore_1
    //   101: aload_0
    //   102: monitorexit
    //   103: aload_1
    //   104: athrow
    // Exception table:
    //   from	to	target	type
    //   4	21	100	finally
    //   24	33	100	finally
    //   43	65	100	finally
    //   71	94	100	finally
    //   96	98	100	finally
    //   101	103	100	finally
  }
  
  private void makeRemovedFragmentsInvisible(ArraySet<Fragment> paramArraySet) {
    int i = paramArraySet.size();
    for (byte b = 0; b < i; b++) {
      Fragment fragment = (Fragment)paramArraySet.valueAt(b);
      if (!fragment.mAdded)
        fragment.getView().setTransitionAlpha(0.0F); 
    } 
  }
  
  static boolean modifiesAlpha(Animator paramAnimator) {
    PropertyValuesHolder[] arrayOfPropertyValuesHolder;
    if (paramAnimator == null)
      return false; 
    if (paramAnimator instanceof ValueAnimator) {
      arrayOfPropertyValuesHolder = ((ValueAnimator)paramAnimator).getValues();
      for (byte b = 0; b < arrayOfPropertyValuesHolder.length; b++) {
        if ("alpha".equals(arrayOfPropertyValuesHolder[b].getPropertyName()))
          return true; 
      } 
    } else if (arrayOfPropertyValuesHolder instanceof AnimatorSet) {
      ArrayList<Animator> arrayList = ((AnimatorSet)arrayOfPropertyValuesHolder).getChildAnimations();
      for (byte b = 0; b < arrayList.size(); b++) {
        if (modifiesAlpha(arrayList.get(b)))
          return true; 
      } 
    } 
    return false;
  }
  
  private boolean popBackStackImmediate(String paramString, int paramInt1, int paramInt2) {
    execPendingActions();
    ensureExecReady(true);
    Fragment fragment = this.mPrimaryNav;
    if (fragment != null && paramInt1 < 0 && paramString == null) {
      FragmentManagerImpl fragmentManagerImpl = fragment.mChildFragmentManager;
      if (fragmentManagerImpl != null && fragmentManagerImpl.popBackStackImmediate())
        return true; 
    } 
    boolean bool = popBackStackState(this.mTmpRecords, this.mTmpIsPop, paramString, paramInt1, paramInt2);
    if (bool) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
    return bool;
  }
  
  private int postponePostponableTransactions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, ArraySet<Fragment> paramArraySet) {
    int i = paramInt2;
    int j = paramInt2 - 1;
    while (j >= paramInt1) {
      boolean bool1;
      BackStackRecord backStackRecord = paramArrayList.get(j);
      boolean bool = ((Boolean)paramArrayList1.get(j)).booleanValue();
      if (backStackRecord.isPostponed() && !backStackRecord.interactsWith(paramArrayList, j + 1, paramInt2)) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      int k = i;
      if (bool1) {
        if (this.mPostponedTransactions == null)
          this.mPostponedTransactions = new ArrayList<>(); 
        StartEnterTransitionListener startEnterTransitionListener = new StartEnterTransitionListener(backStackRecord, bool);
        this.mPostponedTransactions.add(startEnterTransitionListener);
        backStackRecord.setOnStartPostponedListener(startEnterTransitionListener);
        if (bool) {
          backStackRecord.executeOps();
        } else {
          backStackRecord.executePopOps(false);
        } 
        k = i - 1;
        if (j != k) {
          paramArrayList.remove(j);
          paramArrayList.add(k, backStackRecord);
        } 
        addAddedFragments(paramArraySet);
      } 
      j--;
      i = k;
    } 
    return i;
  }
  
  private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1) {
    if (paramArrayList == null || paramArrayList.isEmpty())
      return; 
    if (paramArrayList1 != null && paramArrayList.size() == paramArrayList1.size()) {
      executePostponedTransaction(paramArrayList, paramArrayList1);
      int i = paramArrayList.size();
      int j = 0;
      int k = 0;
      while (k < i) {
        int m = j;
        int n = k;
        if (!((BackStackRecord)paramArrayList.get(k)).mReorderingAllowed) {
          if (j != k)
            executeOpsTogether(paramArrayList, paramArrayList1, j, k); 
          n = k + 1;
          j = n;
          if (((Boolean)paramArrayList1.get(k)).booleanValue())
            while (true) {
              j = n;
              if (n < i) {
                j = n;
                if (((Boolean)paramArrayList1.get(n)).booleanValue()) {
                  j = n;
                  if (!((BackStackRecord)paramArrayList.get(n)).mReorderingAllowed) {
                    n++;
                    continue;
                  } 
                } 
              } 
              break;
            }  
          executeOpsTogether(paramArrayList, paramArrayList1, k, j);
          m = j;
          n = j - 1;
        } 
        k = n + 1;
        j = m;
      } 
      if (j != i)
        executeOpsTogether(paramArrayList, paramArrayList1, j, i); 
      return;
    } 
    throw new IllegalStateException("Internal error with the back stack records");
  }
  
  public static int reverseTransit(int paramInt) {
    boolean bool = false;
    if (paramInt != 4097) {
      if (paramInt != 4099) {
        if (paramInt != 8194) {
          paramInt = bool;
        } else {
          paramInt = 4097;
        } 
      } else {
        paramInt = 4099;
      } 
    } else {
      paramInt = 8194;
    } 
    return paramInt;
  }
  
  private void scheduleCommit() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   6: astore_1
    //   7: iconst_0
    //   8: istore_2
    //   9: aload_1
    //   10: ifnull -> 28
    //   13: aload_0
    //   14: getfield mPostponedTransactions : Ljava/util/ArrayList;
    //   17: invokevirtual isEmpty : ()Z
    //   20: ifne -> 28
    //   23: iconst_1
    //   24: istore_3
    //   25: goto -> 30
    //   28: iconst_0
    //   29: istore_3
    //   30: iload_2
    //   31: istore #4
    //   33: aload_0
    //   34: getfield mPendingActions : Ljava/util/ArrayList;
    //   37: ifnull -> 57
    //   40: iload_2
    //   41: istore #4
    //   43: aload_0
    //   44: getfield mPendingActions : Ljava/util/ArrayList;
    //   47: invokevirtual size : ()I
    //   50: iconst_1
    //   51: if_icmpne -> 57
    //   54: iconst_1
    //   55: istore #4
    //   57: iload_3
    //   58: ifne -> 66
    //   61: iload #4
    //   63: ifeq -> 95
    //   66: aload_0
    //   67: getfield mHost : Landroid/app/FragmentHostCallback;
    //   70: invokevirtual getHandler : ()Landroid/os/Handler;
    //   73: aload_0
    //   74: getfield mExecCommit : Ljava/lang/Runnable;
    //   77: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
    //   80: aload_0
    //   81: getfield mHost : Landroid/app/FragmentHostCallback;
    //   84: invokevirtual getHandler : ()Landroid/os/Handler;
    //   87: aload_0
    //   88: getfield mExecCommit : Ljava/lang/Runnable;
    //   91: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   94: pop
    //   95: aload_0
    //   96: monitorexit
    //   97: return
    //   98: astore_1
    //   99: aload_0
    //   100: monitorexit
    //   101: aload_1
    //   102: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	98	finally
    //   13	23	98	finally
    //   33	40	98	finally
    //   43	54	98	finally
    //   66	95	98	finally
    //   95	97	98	finally
    //   99	101	98	finally
  }
  
  private void setHWLayerAnimListenerIfAlpha(View paramView, Animator paramAnimator) {
    if (paramView == null || paramAnimator == null)
      return; 
    if (shouldRunOnHWLayer(paramView, paramAnimator))
      paramAnimator.addListener(new AnimateOnHWLayerIfNeededListener(paramView)); 
  }
  
  private static void setRetaining(FragmentManagerNonConfig paramFragmentManagerNonConfig) {
    if (paramFragmentManagerNonConfig == null)
      return; 
    List<Fragment> list1 = paramFragmentManagerNonConfig.getFragments();
    if (list1 != null) {
      Iterator<Fragment> iterator = list1.iterator();
      while (iterator.hasNext())
        ((Fragment)iterator.next()).mRetaining = true; 
    } 
    List<FragmentManagerNonConfig> list = paramFragmentManagerNonConfig.getChildNonConfigs();
    if (list != null) {
      Iterator<FragmentManagerNonConfig> iterator = list.iterator();
      while (iterator.hasNext())
        setRetaining(iterator.next()); 
    } 
  }
  
  static boolean shouldRunOnHWLayer(View paramView, Animator paramAnimator) {
    boolean bool = false;
    if (paramView == null || paramAnimator == null)
      return false; 
    if (paramView.getLayerType() == 0 && paramView.hasOverlappingRendering() && modifiesAlpha(paramAnimator))
      bool = true; 
    return bool;
  }
  
  private void throwException(RuntimeException paramRuntimeException) {
    Log.e("FragmentManager", paramRuntimeException.getMessage());
    FastPrintWriter fastPrintWriter = new FastPrintWriter((Writer)new LogWriter(6, "FragmentManager"), false, 1024);
    if (this.mHost != null) {
      Log.e("FragmentManager", "Activity state:");
      try {
        this.mHost.onDump("  ", null, (PrintWriter)fastPrintWriter, new String[0]);
      } catch (Exception exception) {
        fastPrintWriter.flush();
        Log.e("FragmentManager", "Failed dumping state", exception);
      } 
    } else {
      Log.e("FragmentManager", "Fragment manager state:");
      try {
        dump("  ", null, (PrintWriter)fastPrintWriter, new String[0]);
      } catch (Exception exception) {
        fastPrintWriter.flush();
        Log.e("FragmentManager", "Failed dumping state", exception);
      } 
    } 
    fastPrintWriter.flush();
    throw paramRuntimeException;
  }
  
  public static int transitToStyleIndex(int paramInt, boolean paramBoolean) {
    byte b = -1;
    if (paramInt != 4097) {
      if (paramInt != 4099) {
        if (paramInt != 8194) {
          paramInt = b;
        } else if (paramBoolean) {
          paramInt = 2;
        } else {
          paramInt = 3;
        } 
      } else if (paramBoolean) {
        paramInt = 4;
      } else {
        paramInt = 5;
      } 
    } else if (paramBoolean) {
      paramInt = 0;
    } else {
      paramInt = 1;
    } 
    return paramInt;
  }
  
  void addBackStackState(BackStackRecord paramBackStackRecord) {
    if (this.mBackStack == null)
      this.mBackStack = new ArrayList<>(); 
    this.mBackStack.add(paramBackStackRecord);
  }
  
  public void addFragment(Fragment paramFragment, boolean paramBoolean) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("add: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    makeActive(paramFragment);
    if (!paramFragment.mDetached)
      if (!this.mAdded.contains(paramFragment)) {
        synchronized (this.mAdded) {
          this.mAdded.add(paramFragment);
          paramFragment.mAdded = true;
          paramFragment.mRemoving = false;
          if (paramFragment.mView == null)
            paramFragment.mHiddenChanged = false; 
          if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
            this.mNeedMenuInvalidate = true; 
          if (paramBoolean)
            moveToState(paramFragment); 
        } 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Fragment already added: ");
        stringBuilder.append(paramFragment);
        throw new IllegalStateException(stringBuilder.toString());
      }  
  }
  
  public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener) {
    if (this.mBackStackChangeListeners == null)
      this.mBackStackChangeListeners = new ArrayList<>(); 
    this.mBackStackChangeListeners.add(paramOnBackStackChangedListener);
  }
  
  public int allocBackStackIndex(BackStackRecord paramBackStackRecord) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   6: ifnull -> 111
    //   9: aload_0
    //   10: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   13: invokevirtual size : ()I
    //   16: ifgt -> 22
    //   19: goto -> 111
    //   22: aload_0
    //   23: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   26: aload_0
    //   27: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   30: invokevirtual size : ()I
    //   33: iconst_1
    //   34: isub
    //   35: invokevirtual remove : (I)Ljava/lang/Object;
    //   38: checkcast java/lang/Integer
    //   41: invokevirtual intValue : ()I
    //   44: istore_2
    //   45: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   48: ifeq -> 97
    //   51: new java/lang/StringBuilder
    //   54: astore_3
    //   55: aload_3
    //   56: invokespecial <init> : ()V
    //   59: aload_3
    //   60: ldc_w 'Adding back stack index '
    //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: aload_3
    //   68: iload_2
    //   69: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   72: pop
    //   73: aload_3
    //   74: ldc_w ' with '
    //   77: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: pop
    //   81: aload_3
    //   82: aload_1
    //   83: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: ldc 'FragmentManager'
    //   89: aload_3
    //   90: invokevirtual toString : ()Ljava/lang/String;
    //   93: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   96: pop
    //   97: aload_0
    //   98: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   101: iload_2
    //   102: aload_1
    //   103: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   106: pop
    //   107: aload_0
    //   108: monitorexit
    //   109: iload_2
    //   110: ireturn
    //   111: aload_0
    //   112: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   115: ifnonnull -> 131
    //   118: new java/util/ArrayList
    //   121: astore_3
    //   122: aload_3
    //   123: invokespecial <init> : ()V
    //   126: aload_0
    //   127: aload_3
    //   128: putfield mBackStackIndices : Ljava/util/ArrayList;
    //   131: aload_0
    //   132: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   135: invokevirtual size : ()I
    //   138: istore_2
    //   139: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   142: ifeq -> 191
    //   145: new java/lang/StringBuilder
    //   148: astore_3
    //   149: aload_3
    //   150: invokespecial <init> : ()V
    //   153: aload_3
    //   154: ldc_w 'Setting back stack index '
    //   157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: pop
    //   161: aload_3
    //   162: iload_2
    //   163: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload_3
    //   168: ldc_w ' to '
    //   171: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: pop
    //   175: aload_3
    //   176: aload_1
    //   177: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   180: pop
    //   181: ldc 'FragmentManager'
    //   183: aload_3
    //   184: invokevirtual toString : ()Ljava/lang/String;
    //   187: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   190: pop
    //   191: aload_0
    //   192: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   195: aload_1
    //   196: invokevirtual add : (Ljava/lang/Object;)Z
    //   199: pop
    //   200: aload_0
    //   201: monitorexit
    //   202: iload_2
    //   203: ireturn
    //   204: astore_1
    //   205: aload_0
    //   206: monitorexit
    //   207: aload_1
    //   208: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	204	finally
    //   22	97	204	finally
    //   97	109	204	finally
    //   111	131	204	finally
    //   131	191	204	finally
    //   191	202	204	finally
    //   205	207	204	finally
  }
  
  public void attachController(FragmentHostCallback<?> paramFragmentHostCallback, FragmentContainer paramFragmentContainer, Fragment paramFragment) {
    if (this.mHost == null) {
      boolean bool;
      this.mHost = paramFragmentHostCallback;
      this.mContainer = paramFragmentContainer;
      this.mParent = paramFragment;
      if (getTargetSdk() <= 25) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mAllowOldReentrantBehavior = bool;
      return;
    } 
    throw new IllegalStateException("Already attached");
  }
  
  public void attachFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("attach: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (paramFragment.mDetached) {
      paramFragment.mDetached = false;
      if (!paramFragment.mAdded)
        if (!this.mAdded.contains(paramFragment)) {
          if (DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("add from attach: ");
            stringBuilder.append(paramFragment);
            Log.v("FragmentManager", stringBuilder.toString());
          } 
          synchronized (this.mAdded) {
            this.mAdded.add(paramFragment);
            paramFragment.mAdded = true;
            if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
              this.mNeedMenuInvalidate = true; 
          } 
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Fragment already added: ");
          stringBuilder.append(paramFragment);
          throw new IllegalStateException(stringBuilder.toString());
        }  
    } 
  }
  
  public FragmentTransaction beginTransaction() {
    return new BackStackRecord(this);
  }
  
  void completeShowHideFragment(Fragment paramFragment) {
    if (paramFragment.mView != null) {
      Animator animator = loadAnimator(paramFragment, paramFragment.getNextTransition(), paramFragment.mHidden ^ true, paramFragment.getNextTransitionStyle());
      if (animator != null) {
        animator.setTarget(paramFragment.mView);
        if (paramFragment.mHidden) {
          if (paramFragment.isHideReplaced()) {
            paramFragment.setHideReplaced(false);
          } else {
            final ViewGroup container = paramFragment.mContainer;
            final View animatingView = paramFragment.mView;
            if (viewGroup != null)
              viewGroup.startViewTransition(view); 
            animator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter() {
                  public void onAnimationEnd(Animator param1Animator) {
                    ViewGroup viewGroup = container;
                    if (viewGroup != null)
                      viewGroup.endViewTransition(animatingView); 
                    param1Animator.removeListener((Animator.AnimatorListener)this);
                    animatingView.setVisibility(8);
                  }
                });
          } 
        } else {
          paramFragment.mView.setVisibility(0);
        } 
        setHWLayerAnimListenerIfAlpha(paramFragment.mView, animator);
        animator.start();
      } else {
        boolean bool;
        if (paramFragment.mHidden && !paramFragment.isHideReplaced()) {
          bool = true;
        } else {
          bool = false;
        } 
        paramFragment.mView.setVisibility(bool);
        if (paramFragment.isHideReplaced())
          paramFragment.setHideReplaced(false); 
      } 
    } 
    if (paramFragment.mAdded && paramFragment.mHasMenu && paramFragment.mMenuVisible)
      this.mNeedMenuInvalidate = true; 
    paramFragment.mHiddenChanged = false;
    paramFragment.onHiddenChanged(paramFragment.mHidden);
  }
  
  public void detachFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("detach: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (!paramFragment.mDetached) {
      paramFragment.mDetached = true;
      if (paramFragment.mAdded) {
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("remove from detach: ");
          stringBuilder.append(paramFragment);
          Log.v("FragmentManager", stringBuilder.toString());
        } 
        synchronized (this.mAdded) {
          this.mAdded.remove(paramFragment);
          if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
            this.mNeedMenuInvalidate = true; 
          paramFragment.mAdded = false;
        } 
      } 
    } 
  }
  
  public void dispatchActivityCreated() {
    this.mStateSaved = false;
    dispatchMoveToState(2);
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration) {
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.performConfigurationChanged(paramConfiguration); 
    } 
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem) {
    if (this.mCurState < 1)
      return false; 
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null && fragment.performContextItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  public void dispatchCreate() {
    this.mStateSaved = false;
    dispatchMoveToState(1);
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {
    if (this.mCurState < 1)
      return false; 
    boolean bool = false;
    ArrayList<Fragment> arrayList = null;
    byte b = 0;
    while (b < this.mAdded.size()) {
      Fragment fragment = this.mAdded.get(b);
      boolean bool1 = bool;
      ArrayList<Fragment> arrayList1 = arrayList;
      if (fragment != null) {
        bool1 = bool;
        arrayList1 = arrayList;
        if (fragment.performCreateOptionsMenu(paramMenu, paramMenuInflater)) {
          bool1 = true;
          arrayList1 = arrayList;
          if (arrayList == null)
            arrayList1 = new ArrayList(); 
          arrayList1.add(fragment);
        } 
      } 
      b++;
      bool = bool1;
      arrayList = arrayList1;
    } 
    if (this.mCreatedMenus != null)
      for (b = 0; b < this.mCreatedMenus.size(); b++) {
        Fragment fragment = this.mCreatedMenus.get(b);
        if (arrayList == null || !arrayList.contains(fragment))
          fragment.onDestroyOptionsMenu(); 
      }  
    this.mCreatedMenus = arrayList;
    return bool;
  }
  
  public void dispatchDestroy() {
    this.mDestroyed = true;
    execPendingActions();
    dispatchMoveToState(0);
    this.mHost = null;
    this.mContainer = null;
    this.mParent = null;
  }
  
  public void dispatchDestroyView() {
    dispatchMoveToState(1);
  }
  
  public void dispatchLowMemory() {
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.performLowMemory(); 
    } 
  }
  
  @Deprecated
  public void dispatchMultiWindowModeChanged(boolean paramBoolean) {
    for (int i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performMultiWindowModeChanged(paramBoolean); 
    } 
  }
  
  public void dispatchMultiWindowModeChanged(boolean paramBoolean, Configuration paramConfiguration) {
    for (int i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performMultiWindowModeChanged(paramBoolean, paramConfiguration); 
    } 
  }
  
  void dispatchOnFragmentActivityCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentActivityCreated(paramFragment, paramBundle, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentActivityCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentAttached(Fragment paramFragment, Context paramContext, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentAttached(paramFragment, paramContext, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentAttached(this, paramFragment, paramContext); 
    } 
  }
  
  void dispatchOnFragmentCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentCreated(paramFragment, paramBundle, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentDestroyed(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentDestroyed(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentDestroyed(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentDetached(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentDetached(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentDetached(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentPaused(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPaused(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPaused(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentPreAttached(Fragment paramFragment, Context paramContext, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPreAttached(paramFragment, paramContext, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPreAttached(this, paramFragment, paramContext); 
    } 
  }
  
  void dispatchOnFragmentPreCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentPreCreated(paramFragment, paramBundle, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPreCreated(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentResumed(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentResumed(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentResumed(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentSaveInstanceState(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentSaveInstanceState(paramFragment, paramBundle, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentSaveInstanceState(this, paramFragment, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentStarted(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentStarted(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentStarted(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentStopped(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentStopped(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentStopped(this, paramFragment); 
    } 
  }
  
  void dispatchOnFragmentViewCreated(Fragment paramFragment, View paramView, Bundle paramBundle, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentViewCreated(paramFragment, paramView, paramBundle, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentViewCreated(this, paramFragment, paramView, paramBundle); 
    } 
  }
  
  void dispatchOnFragmentViewDestroyed(Fragment paramFragment, boolean paramBoolean) {
    Fragment fragment = this.mParent;
    if (fragment != null) {
      FragmentManager fragmentManager = fragment.getFragmentManager();
      if (fragmentManager instanceof FragmentManagerImpl)
        ((FragmentManagerImpl)fragmentManager).dispatchOnFragmentViewDestroyed(paramFragment, true); 
    } 
    for (Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean> pair : this.mLifecycleCallbacks) {
      if (!paramBoolean || ((Boolean)pair.second).booleanValue())
        ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentViewDestroyed(this, paramFragment); 
    } 
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem) {
    if (this.mCurState < 1)
      return false; 
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null && fragment.performOptionsItemSelected(paramMenuItem))
        return true; 
    } 
    return false;
  }
  
  public void dispatchOptionsMenuClosed(Menu paramMenu) {
    if (this.mCurState < 1)
      return; 
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.performOptionsMenuClosed(paramMenu); 
    } 
  }
  
  public void dispatchPause() {
    dispatchMoveToState(4);
  }
  
  @Deprecated
  public void dispatchPictureInPictureModeChanged(boolean paramBoolean) {
    for (int i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performPictureInPictureModeChanged(paramBoolean); 
    } 
  }
  
  public void dispatchPictureInPictureModeChanged(boolean paramBoolean, Configuration paramConfiguration) {
    for (int i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null)
        fragment.performPictureInPictureModeChanged(paramBoolean, paramConfiguration); 
    } 
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu) {
    if (this.mCurState < 1)
      return false; 
    boolean bool = false;
    byte b = 0;
    while (b < this.mAdded.size()) {
      Fragment fragment = this.mAdded.get(b);
      boolean bool1 = bool;
      if (fragment != null) {
        bool1 = bool;
        if (fragment.performPrepareOptionsMenu(paramMenu))
          bool1 = true; 
      } 
      b++;
      bool = bool1;
    } 
    return bool;
  }
  
  public void dispatchResume() {
    this.mStateSaved = false;
    dispatchMoveToState(5);
  }
  
  public void dispatchStart() {
    this.mStateSaved = false;
    dispatchMoveToState(4);
  }
  
  public void dispatchStop() {
    dispatchMoveToState(3);
  }
  
  public void dispatchTrimMemory(int paramInt) {
    for (byte b = 0; b < this.mAdded.size(); b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.performTrimMemory(paramInt); 
    } 
  }
  
  void doPendingDeferredStart() {
    if (this.mHavePendingDeferredStart) {
      boolean bool = false;
      byte b = 0;
      while (b < this.mActive.size()) {
        Fragment fragment = (Fragment)this.mActive.valueAt(b);
        boolean bool1 = bool;
        if (fragment != null) {
          bool1 = bool;
          if (fragment.mLoaderManager != null)
            bool1 = bool | fragment.mLoaderManager.hasRunningLoaders(); 
        } 
        b++;
        bool = bool1;
      } 
      if (!bool) {
        this.mHavePendingDeferredStart = false;
        startPendingDeferredFragments();
      } 
    } 
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #5
    //   9: aload #5
    //   11: aload_1
    //   12: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: pop
    //   16: aload #5
    //   18: ldc_w '    '
    //   21: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: aload #5
    //   27: invokevirtual toString : ()Ljava/lang/String;
    //   30: astore #5
    //   32: aload_0
    //   33: getfield mActive : Landroid/util/SparseArray;
    //   36: astore #6
    //   38: aload #6
    //   40: ifnull -> 162
    //   43: aload #6
    //   45: invokevirtual size : ()I
    //   48: istore #7
    //   50: iload #7
    //   52: ifle -> 162
    //   55: aload_3
    //   56: aload_1
    //   57: invokevirtual print : (Ljava/lang/String;)V
    //   60: aload_3
    //   61: ldc_w 'Active Fragments in '
    //   64: invokevirtual print : (Ljava/lang/String;)V
    //   67: aload_3
    //   68: aload_0
    //   69: invokestatic identityHashCode : (Ljava/lang/Object;)I
    //   72: invokestatic toHexString : (I)Ljava/lang/String;
    //   75: invokevirtual print : (Ljava/lang/String;)V
    //   78: aload_3
    //   79: ldc_w ':'
    //   82: invokevirtual println : (Ljava/lang/String;)V
    //   85: iconst_0
    //   86: istore #8
    //   88: iload #8
    //   90: iload #7
    //   92: if_icmpge -> 162
    //   95: aload_0
    //   96: getfield mActive : Landroid/util/SparseArray;
    //   99: iload #8
    //   101: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   104: checkcast android/app/Fragment
    //   107: astore #6
    //   109: aload_3
    //   110: aload_1
    //   111: invokevirtual print : (Ljava/lang/String;)V
    //   114: aload_3
    //   115: ldc_w '  #'
    //   118: invokevirtual print : (Ljava/lang/String;)V
    //   121: aload_3
    //   122: iload #8
    //   124: invokevirtual print : (I)V
    //   127: aload_3
    //   128: ldc_w ': '
    //   131: invokevirtual print : (Ljava/lang/String;)V
    //   134: aload_3
    //   135: aload #6
    //   137: invokevirtual println : (Ljava/lang/Object;)V
    //   140: aload #6
    //   142: ifnull -> 156
    //   145: aload #6
    //   147: aload #5
    //   149: aload_2
    //   150: aload_3
    //   151: aload #4
    //   153: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   156: iinc #8, 1
    //   159: goto -> 88
    //   162: aload_0
    //   163: getfield mAdded : Ljava/util/ArrayList;
    //   166: invokevirtual size : ()I
    //   169: istore #7
    //   171: iload #7
    //   173: ifle -> 252
    //   176: aload_3
    //   177: aload_1
    //   178: invokevirtual print : (Ljava/lang/String;)V
    //   181: aload_3
    //   182: ldc_w 'Added Fragments:'
    //   185: invokevirtual println : (Ljava/lang/String;)V
    //   188: iconst_0
    //   189: istore #8
    //   191: iload #8
    //   193: iload #7
    //   195: if_icmpge -> 252
    //   198: aload_0
    //   199: getfield mAdded : Ljava/util/ArrayList;
    //   202: iload #8
    //   204: invokevirtual get : (I)Ljava/lang/Object;
    //   207: checkcast android/app/Fragment
    //   210: astore #6
    //   212: aload_3
    //   213: aload_1
    //   214: invokevirtual print : (Ljava/lang/String;)V
    //   217: aload_3
    //   218: ldc_w '  #'
    //   221: invokevirtual print : (Ljava/lang/String;)V
    //   224: aload_3
    //   225: iload #8
    //   227: invokevirtual print : (I)V
    //   230: aload_3
    //   231: ldc_w ': '
    //   234: invokevirtual print : (Ljava/lang/String;)V
    //   237: aload_3
    //   238: aload #6
    //   240: invokevirtual toString : ()Ljava/lang/String;
    //   243: invokevirtual println : (Ljava/lang/String;)V
    //   246: iinc #8, 1
    //   249: goto -> 191
    //   252: aload_0
    //   253: getfield mCreatedMenus : Ljava/util/ArrayList;
    //   256: astore #6
    //   258: aload #6
    //   260: ifnull -> 351
    //   263: aload #6
    //   265: invokevirtual size : ()I
    //   268: istore #7
    //   270: iload #7
    //   272: ifle -> 351
    //   275: aload_3
    //   276: aload_1
    //   277: invokevirtual print : (Ljava/lang/String;)V
    //   280: aload_3
    //   281: ldc_w 'Fragments Created Menus:'
    //   284: invokevirtual println : (Ljava/lang/String;)V
    //   287: iconst_0
    //   288: istore #8
    //   290: iload #8
    //   292: iload #7
    //   294: if_icmpge -> 351
    //   297: aload_0
    //   298: getfield mCreatedMenus : Ljava/util/ArrayList;
    //   301: iload #8
    //   303: invokevirtual get : (I)Ljava/lang/Object;
    //   306: checkcast android/app/Fragment
    //   309: astore #6
    //   311: aload_3
    //   312: aload_1
    //   313: invokevirtual print : (Ljava/lang/String;)V
    //   316: aload_3
    //   317: ldc_w '  #'
    //   320: invokevirtual print : (Ljava/lang/String;)V
    //   323: aload_3
    //   324: iload #8
    //   326: invokevirtual print : (I)V
    //   329: aload_3
    //   330: ldc_w ': '
    //   333: invokevirtual print : (Ljava/lang/String;)V
    //   336: aload_3
    //   337: aload #6
    //   339: invokevirtual toString : ()Ljava/lang/String;
    //   342: invokevirtual println : (Ljava/lang/String;)V
    //   345: iinc #8, 1
    //   348: goto -> 290
    //   351: aload_0
    //   352: getfield mBackStack : Ljava/util/ArrayList;
    //   355: astore #6
    //   357: aload #6
    //   359: ifnull -> 461
    //   362: aload #6
    //   364: invokevirtual size : ()I
    //   367: istore #7
    //   369: iload #7
    //   371: ifle -> 461
    //   374: aload_3
    //   375: aload_1
    //   376: invokevirtual print : (Ljava/lang/String;)V
    //   379: aload_3
    //   380: ldc_w 'Back Stack:'
    //   383: invokevirtual println : (Ljava/lang/String;)V
    //   386: iconst_0
    //   387: istore #8
    //   389: iload #8
    //   391: iload #7
    //   393: if_icmpge -> 461
    //   396: aload_0
    //   397: getfield mBackStack : Ljava/util/ArrayList;
    //   400: iload #8
    //   402: invokevirtual get : (I)Ljava/lang/Object;
    //   405: checkcast android/app/BackStackRecord
    //   408: astore #6
    //   410: aload_3
    //   411: aload_1
    //   412: invokevirtual print : (Ljava/lang/String;)V
    //   415: aload_3
    //   416: ldc_w '  #'
    //   419: invokevirtual print : (Ljava/lang/String;)V
    //   422: aload_3
    //   423: iload #8
    //   425: invokevirtual print : (I)V
    //   428: aload_3
    //   429: ldc_w ': '
    //   432: invokevirtual print : (Ljava/lang/String;)V
    //   435: aload_3
    //   436: aload #6
    //   438: invokevirtual toString : ()Ljava/lang/String;
    //   441: invokevirtual println : (Ljava/lang/String;)V
    //   444: aload #6
    //   446: aload #5
    //   448: aload_2
    //   449: aload_3
    //   450: aload #4
    //   452: invokevirtual dump : (Ljava/lang/String;Ljava/io/FileDescriptor;Ljava/io/PrintWriter;[Ljava/lang/String;)V
    //   455: iinc #8, 1
    //   458: goto -> 389
    //   461: aload_0
    //   462: monitorenter
    //   463: aload_0
    //   464: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   467: ifnull -> 555
    //   470: aload_0
    //   471: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   474: invokevirtual size : ()I
    //   477: istore #7
    //   479: iload #7
    //   481: ifle -> 555
    //   484: aload_3
    //   485: aload_1
    //   486: invokevirtual print : (Ljava/lang/String;)V
    //   489: aload_3
    //   490: ldc_w 'Back Stack Indices:'
    //   493: invokevirtual println : (Ljava/lang/String;)V
    //   496: iconst_0
    //   497: istore #8
    //   499: iload #8
    //   501: iload #7
    //   503: if_icmpge -> 555
    //   506: aload_0
    //   507: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   510: iload #8
    //   512: invokevirtual get : (I)Ljava/lang/Object;
    //   515: checkcast android/app/BackStackRecord
    //   518: astore_2
    //   519: aload_3
    //   520: aload_1
    //   521: invokevirtual print : (Ljava/lang/String;)V
    //   524: aload_3
    //   525: ldc_w '  #'
    //   528: invokevirtual print : (Ljava/lang/String;)V
    //   531: aload_3
    //   532: iload #8
    //   534: invokevirtual print : (I)V
    //   537: aload_3
    //   538: ldc_w ': '
    //   541: invokevirtual print : (Ljava/lang/String;)V
    //   544: aload_3
    //   545: aload_2
    //   546: invokevirtual println : (Ljava/lang/Object;)V
    //   549: iinc #8, 1
    //   552: goto -> 499
    //   555: aload_0
    //   556: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   559: ifnull -> 598
    //   562: aload_0
    //   563: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   566: invokevirtual size : ()I
    //   569: ifle -> 598
    //   572: aload_3
    //   573: aload_1
    //   574: invokevirtual print : (Ljava/lang/String;)V
    //   577: aload_3
    //   578: ldc_w 'mAvailBackStackIndices: '
    //   581: invokevirtual print : (Ljava/lang/String;)V
    //   584: aload_3
    //   585: aload_0
    //   586: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   589: invokevirtual toArray : ()[Ljava/lang/Object;
    //   592: invokestatic toString : ([Ljava/lang/Object;)Ljava/lang/String;
    //   595: invokevirtual println : (Ljava/lang/String;)V
    //   598: aload_0
    //   599: monitorexit
    //   600: aload_0
    //   601: getfield mPendingActions : Ljava/util/ArrayList;
    //   604: astore_2
    //   605: aload_2
    //   606: ifnull -> 691
    //   609: aload_2
    //   610: invokevirtual size : ()I
    //   613: istore #7
    //   615: iload #7
    //   617: ifle -> 691
    //   620: aload_3
    //   621: aload_1
    //   622: invokevirtual print : (Ljava/lang/String;)V
    //   625: aload_3
    //   626: ldc_w 'Pending Actions:'
    //   629: invokevirtual println : (Ljava/lang/String;)V
    //   632: iconst_0
    //   633: istore #8
    //   635: iload #8
    //   637: iload #7
    //   639: if_icmpge -> 691
    //   642: aload_0
    //   643: getfield mPendingActions : Ljava/util/ArrayList;
    //   646: iload #8
    //   648: invokevirtual get : (I)Ljava/lang/Object;
    //   651: checkcast android/app/FragmentManagerImpl$OpGenerator
    //   654: astore_2
    //   655: aload_3
    //   656: aload_1
    //   657: invokevirtual print : (Ljava/lang/String;)V
    //   660: aload_3
    //   661: ldc_w '  #'
    //   664: invokevirtual print : (Ljava/lang/String;)V
    //   667: aload_3
    //   668: iload #8
    //   670: invokevirtual print : (I)V
    //   673: aload_3
    //   674: ldc_w ': '
    //   677: invokevirtual print : (Ljava/lang/String;)V
    //   680: aload_3
    //   681: aload_2
    //   682: invokevirtual println : (Ljava/lang/Object;)V
    //   685: iinc #8, 1
    //   688: goto -> 635
    //   691: aload_3
    //   692: aload_1
    //   693: invokevirtual print : (Ljava/lang/String;)V
    //   696: aload_3
    //   697: ldc_w 'FragmentManager misc state:'
    //   700: invokevirtual println : (Ljava/lang/String;)V
    //   703: aload_3
    //   704: aload_1
    //   705: invokevirtual print : (Ljava/lang/String;)V
    //   708: aload_3
    //   709: ldc_w '  mHost='
    //   712: invokevirtual print : (Ljava/lang/String;)V
    //   715: aload_3
    //   716: aload_0
    //   717: getfield mHost : Landroid/app/FragmentHostCallback;
    //   720: invokevirtual println : (Ljava/lang/Object;)V
    //   723: aload_3
    //   724: aload_1
    //   725: invokevirtual print : (Ljava/lang/String;)V
    //   728: aload_3
    //   729: ldc_w '  mContainer='
    //   732: invokevirtual print : (Ljava/lang/String;)V
    //   735: aload_3
    //   736: aload_0
    //   737: getfield mContainer : Landroid/app/FragmentContainer;
    //   740: invokevirtual println : (Ljava/lang/Object;)V
    //   743: aload_0
    //   744: getfield mParent : Landroid/app/Fragment;
    //   747: ifnull -> 770
    //   750: aload_3
    //   751: aload_1
    //   752: invokevirtual print : (Ljava/lang/String;)V
    //   755: aload_3
    //   756: ldc_w '  mParent='
    //   759: invokevirtual print : (Ljava/lang/String;)V
    //   762: aload_3
    //   763: aload_0
    //   764: getfield mParent : Landroid/app/Fragment;
    //   767: invokevirtual println : (Ljava/lang/Object;)V
    //   770: aload_3
    //   771: aload_1
    //   772: invokevirtual print : (Ljava/lang/String;)V
    //   775: aload_3
    //   776: ldc_w '  mCurState='
    //   779: invokevirtual print : (Ljava/lang/String;)V
    //   782: aload_3
    //   783: aload_0
    //   784: getfield mCurState : I
    //   787: invokevirtual print : (I)V
    //   790: aload_3
    //   791: ldc_w ' mStateSaved='
    //   794: invokevirtual print : (Ljava/lang/String;)V
    //   797: aload_3
    //   798: aload_0
    //   799: getfield mStateSaved : Z
    //   802: invokevirtual print : (Z)V
    //   805: aload_3
    //   806: ldc_w ' mDestroyed='
    //   809: invokevirtual print : (Ljava/lang/String;)V
    //   812: aload_3
    //   813: aload_0
    //   814: getfield mDestroyed : Z
    //   817: invokevirtual println : (Z)V
    //   820: aload_0
    //   821: getfield mNeedMenuInvalidate : Z
    //   824: ifeq -> 847
    //   827: aload_3
    //   828: aload_1
    //   829: invokevirtual print : (Ljava/lang/String;)V
    //   832: aload_3
    //   833: ldc_w '  mNeedMenuInvalidate='
    //   836: invokevirtual print : (Ljava/lang/String;)V
    //   839: aload_3
    //   840: aload_0
    //   841: getfield mNeedMenuInvalidate : Z
    //   844: invokevirtual println : (Z)V
    //   847: aload_0
    //   848: getfield mNoTransactionsBecause : Ljava/lang/String;
    //   851: ifnull -> 874
    //   854: aload_3
    //   855: aload_1
    //   856: invokevirtual print : (Ljava/lang/String;)V
    //   859: aload_3
    //   860: ldc_w '  mNoTransactionsBecause='
    //   863: invokevirtual print : (Ljava/lang/String;)V
    //   866: aload_3
    //   867: aload_0
    //   868: getfield mNoTransactionsBecause : Ljava/lang/String;
    //   871: invokevirtual println : (Ljava/lang/String;)V
    //   874: return
    //   875: astore_1
    //   876: aload_0
    //   877: monitorexit
    //   878: aload_1
    //   879: athrow
    // Exception table:
    //   from	to	target	type
    //   463	479	875	finally
    //   484	496	875	finally
    //   506	549	875	finally
    //   555	598	875	finally
    //   598	600	875	finally
    //   876	878	875	finally
  }
  
  public void enqueueAction(OpGenerator paramOpGenerator, boolean paramBoolean) {
    // Byte code:
    //   0: iload_2
    //   1: ifne -> 8
    //   4: aload_0
    //   5: invokespecial checkStateLoss : ()V
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: getfield mDestroyed : Z
    //   14: ifne -> 63
    //   17: aload_0
    //   18: getfield mHost : Landroid/app/FragmentHostCallback;
    //   21: ifnonnull -> 27
    //   24: goto -> 63
    //   27: aload_0
    //   28: getfield mPendingActions : Ljava/util/ArrayList;
    //   31: ifnonnull -> 47
    //   34: new java/util/ArrayList
    //   37: astore_3
    //   38: aload_3
    //   39: invokespecial <init> : ()V
    //   42: aload_0
    //   43: aload_3
    //   44: putfield mPendingActions : Ljava/util/ArrayList;
    //   47: aload_0
    //   48: getfield mPendingActions : Ljava/util/ArrayList;
    //   51: aload_1
    //   52: invokevirtual add : (Ljava/lang/Object;)Z
    //   55: pop
    //   56: aload_0
    //   57: invokespecial scheduleCommit : ()V
    //   60: aload_0
    //   61: monitorexit
    //   62: return
    //   63: iload_2
    //   64: ifeq -> 70
    //   67: aload_0
    //   68: monitorexit
    //   69: return
    //   70: new java/lang/IllegalStateException
    //   73: astore_1
    //   74: aload_1
    //   75: ldc_w 'Activity has been destroyed'
    //   78: invokespecial <init> : (Ljava/lang/String;)V
    //   81: aload_1
    //   82: athrow
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    // Exception table:
    //   from	to	target	type
    //   10	24	83	finally
    //   27	47	83	finally
    //   47	62	83	finally
    //   67	69	83	finally
    //   70	83	83	finally
    //   84	86	83	finally
  }
  
  void ensureInflatedFragmentView(Fragment paramFragment) {
    if (paramFragment.mFromLayout && !paramFragment.mPerformedCreateView) {
      paramFragment.mView = paramFragment.performCreateView(paramFragment.performGetLayoutInflater(paramFragment.mSavedFragmentState), null, paramFragment.mSavedFragmentState);
      if (paramFragment.mView != null) {
        paramFragment.mView.setSaveFromParentEnabled(false);
        if (paramFragment.mHidden)
          paramFragment.mView.setVisibility(8); 
        paramFragment.onViewCreated(paramFragment.mView, paramFragment.mSavedFragmentState);
        dispatchOnFragmentViewCreated(paramFragment, paramFragment.mView, paramFragment.mSavedFragmentState, false);
      } 
    } 
  }
  
  public boolean execPendingActions() {
    ensureExecReady(true);
    boolean bool = false;
    while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
        cleanupExec();
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
    return bool;
  }
  
  public void execSingleAction(OpGenerator paramOpGenerator, boolean paramBoolean) {
    if (paramBoolean && (this.mHost == null || this.mDestroyed))
      return; 
    ensureExecReady(paramBoolean);
    if (paramOpGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
      this.mExecutingActions = true;
      try {
        removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
      } finally {
        cleanupExec();
      } 
    } 
    doPendingDeferredStart();
    burpActive();
  }
  
  public boolean executePendingTransactions() {
    boolean bool = execPendingActions();
    forcePostponedTransactions();
    return bool;
  }
  
  public Fragment findFragmentById(int paramInt) {
    int i;
    for (i = this.mAdded.size() - 1; i >= 0; i--) {
      Fragment fragment = this.mAdded.get(i);
      if (fragment != null && fragment.mFragmentId == paramInt)
        return fragment; 
    } 
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null)
      for (i = sparseArray.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null && fragment.mFragmentId == paramInt)
          return fragment; 
      }  
    return null;
  }
  
  public Fragment findFragmentByTag(String paramString) {
    if (paramString != null)
      for (int i = this.mAdded.size() - 1; i >= 0; i--) {
        Fragment fragment = this.mAdded.get(i);
        if (fragment != null && paramString.equals(fragment.mTag))
          return fragment; 
      }  
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null && paramString != null)
      for (int i = sparseArray.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null && paramString.equals(fragment.mTag))
          return fragment; 
      }  
    return null;
  }
  
  public Fragment findFragmentByWho(String paramString) {
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray != null && paramString != null)
      for (int i = sparseArray.size() - 1; i >= 0; i--) {
        Fragment fragment = (Fragment)this.mActive.valueAt(i);
        if (fragment != null) {
          fragment = fragment.findFragmentByWho(paramString);
          if (fragment != null)
            return fragment; 
        } 
      }  
    return null;
  }
  
  public void freeBackStackIndex(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   6: iload_1
    //   7: aconst_null
    //   8: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   11: pop
    //   12: aload_0
    //   13: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   16: ifnonnull -> 32
    //   19: new java/util/ArrayList
    //   22: astore_2
    //   23: aload_2
    //   24: invokespecial <init> : ()V
    //   27: aload_0
    //   28: aload_2
    //   29: putfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   32: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   35: ifeq -> 70
    //   38: new java/lang/StringBuilder
    //   41: astore_2
    //   42: aload_2
    //   43: invokespecial <init> : ()V
    //   46: aload_2
    //   47: ldc_w 'Freeing back stack index '
    //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: aload_2
    //   55: iload_1
    //   56: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: ldc 'FragmentManager'
    //   62: aload_2
    //   63: invokevirtual toString : ()Ljava/lang/String;
    //   66: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   69: pop
    //   70: aload_0
    //   71: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   74: iload_1
    //   75: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   78: invokevirtual add : (Ljava/lang/Object;)Z
    //   81: pop
    //   82: aload_0
    //   83: monitorexit
    //   84: return
    //   85: astore_2
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_2
    //   89: athrow
    // Exception table:
    //   from	to	target	type
    //   2	32	85	finally
    //   32	70	85	finally
    //   70	84	85	finally
    //   86	88	85	finally
  }
  
  public FragmentManager.BackStackEntry getBackStackEntryAt(int paramInt) {
    return this.mBackStack.get(paramInt);
  }
  
  public int getBackStackEntryCount() {
    boolean bool;
    ArrayList<BackStackRecord> arrayList = this.mBackStack;
    if (arrayList != null) {
      bool = arrayList.size();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public Fragment getFragment(Bundle paramBundle, String paramString) {
    int i = paramBundle.getInt(paramString, -1);
    if (i == -1)
      return null; 
    Fragment fragment = (Fragment)this.mActive.get(i);
    if (fragment == null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Fragment no longer exists for key ");
      stringBuilder.append(paramString);
      stringBuilder.append(": index ");
      stringBuilder.append(i);
      throwException(new IllegalStateException(stringBuilder.toString()));
    } 
    return fragment;
  }
  
  public List<Fragment> getFragments() {
    if (this.mAdded.isEmpty())
      return Collections.EMPTY_LIST; 
    synchronized (this.mAdded) {
      return (List)this.mAdded.clone();
    } 
  }
  
  LayoutInflater.Factory2 getLayoutInflaterFactory() {
    return this;
  }
  
  public Fragment getPrimaryNavigationFragment() {
    return this.mPrimaryNav;
  }
  
  int getTargetSdk() {
    FragmentHostCallback<?> fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null) {
      Context context = fragmentHostCallback.getContext();
      if (context != null) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo != null)
          return applicationInfo.targetSdkVersion; 
      } 
    } 
    return 0;
  }
  
  public void hideFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("hide: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (!paramFragment.mHidden) {
      paramFragment.mHidden = true;
      paramFragment.mHiddenChanged = true ^ paramFragment.mHiddenChanged;
    } 
  }
  
  public void invalidateOptionsMenu() {
    FragmentHostCallback<?> fragmentHostCallback = this.mHost;
    if (fragmentHostCallback != null && this.mCurState == 5) {
      fragmentHostCallback.onInvalidateOptionsMenu();
    } else {
      this.mNeedMenuInvalidate = true;
    } 
  }
  
  public boolean isDestroyed() {
    return this.mDestroyed;
  }
  
  boolean isStateAtLeast(int paramInt) {
    boolean bool;
    if (this.mCurState >= paramInt) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isStateSaved() {
    return this.mStateSaved;
  }
  
  Animator loadAnimator(Fragment paramFragment, int paramInt1, boolean paramBoolean, int paramInt2) {
    Animator animator = paramFragment.onCreateAnimator(paramInt1, paramBoolean, paramFragment.getNextAnim());
    if (animator != null)
      return animator; 
    if (paramFragment.getNextAnim() != 0) {
      Animator animator1 = AnimatorInflater.loadAnimator(this.mHost.getContext(), paramFragment.getNextAnim());
      if (animator1 != null)
        return animator1; 
    } 
    if (paramInt1 == 0)
      return null; 
    int i = transitToStyleIndex(paramInt1, paramBoolean);
    if (i < 0)
      return null; 
    paramInt1 = paramInt2;
    if (paramInt2 == 0) {
      paramInt1 = paramInt2;
      if (this.mHost.onHasWindowAnimations())
        paramInt1 = this.mHost.onGetWindowAnimations(); 
    } 
    if (paramInt1 == 0)
      return null; 
    TypedArray typedArray = this.mHost.getContext().obtainStyledAttributes(paramInt1, R.styleable.FragmentAnimation);
    paramInt1 = typedArray.getResourceId(i, 0);
    typedArray.recycle();
    return (paramInt1 == 0) ? null : AnimatorInflater.loadAnimator(this.mHost.getContext(), paramInt1);
  }
  
  void makeActive(Fragment paramFragment) {
    if (paramFragment.mIndex >= 0)
      return; 
    int i = this.mNextFragmentIndex;
    this.mNextFragmentIndex = i + 1;
    paramFragment.setIndex(i, this.mParent);
    if (this.mActive == null)
      this.mActive = new SparseArray(); 
    this.mActive.put(paramFragment.mIndex, paramFragment);
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Allocated fragment index ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
  }
  
  void makeInactive(Fragment paramFragment) {
    if (paramFragment.mIndex < 0)
      return; 
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Freeing fragment index ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    this.mActive.put(paramFragment.mIndex, null);
    this.mHost.inactivateFragment(paramFragment.mWho);
    paramFragment.initState();
  }
  
  void moveFragmentToExpectedState(Fragment paramFragment) {
    if (paramFragment == null)
      return; 
    int i = this.mCurState;
    int j = i;
    if (paramFragment.mRemoving)
      if (paramFragment.isInBackStack()) {
        j = Math.min(i, 1);
      } else {
        j = Math.min(i, 0);
      }  
    moveToState(paramFragment, j, paramFragment.getNextTransition(), paramFragment.getNextTransitionStyle(), false);
    if (paramFragment.mView != null) {
      Fragment fragment = findFragmentUnder(paramFragment);
      if (fragment != null) {
        View view = fragment.mView;
        ViewGroup viewGroup = paramFragment.mContainer;
        j = viewGroup.indexOfChild(view);
        i = viewGroup.indexOfChild(paramFragment.mView);
        if (i < j) {
          viewGroup.removeViewAt(i);
          viewGroup.addView(paramFragment.mView, j);
        } 
      } 
      if (paramFragment.mIsNewlyAdded && paramFragment.mContainer != null) {
        paramFragment.mView.setTransitionAlpha(1.0F);
        paramFragment.mIsNewlyAdded = false;
        Animator animator = loadAnimator(paramFragment, paramFragment.getNextTransition(), true, paramFragment.getNextTransitionStyle());
        if (animator != null) {
          animator.setTarget(paramFragment.mView);
          setHWLayerAnimListenerIfAlpha(paramFragment.mView, animator);
          animator.start();
        } 
      } 
    } 
    if (paramFragment.mHiddenChanged)
      completeShowHideFragment(paramFragment); 
  }
  
  void moveToState(int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mHost : Landroid/app/FragmentHostCallback;
    //   4: ifnonnull -> 25
    //   7: iload_1
    //   8: ifne -> 14
    //   11: goto -> 25
    //   14: new java/lang/IllegalStateException
    //   17: dup
    //   18: ldc_w 'No activity'
    //   21: invokespecial <init> : (Ljava/lang/String;)V
    //   24: athrow
    //   25: iload_2
    //   26: ifne -> 38
    //   29: aload_0
    //   30: getfield mCurState : I
    //   33: iload_1
    //   34: if_icmpne -> 38
    //   37: return
    //   38: aload_0
    //   39: iload_1
    //   40: putfield mCurState : I
    //   43: aload_0
    //   44: getfield mActive : Landroid/util/SparseArray;
    //   47: ifnull -> 276
    //   50: iconst_0
    //   51: istore_1
    //   52: aload_0
    //   53: getfield mAdded : Ljava/util/ArrayList;
    //   56: invokevirtual size : ()I
    //   59: istore_3
    //   60: iconst_0
    //   61: istore #4
    //   63: iload #4
    //   65: iload_3
    //   66: if_icmpge -> 121
    //   69: aload_0
    //   70: getfield mAdded : Ljava/util/ArrayList;
    //   73: iload #4
    //   75: invokevirtual get : (I)Ljava/lang/Object;
    //   78: checkcast android/app/Fragment
    //   81: astore #5
    //   83: aload_0
    //   84: aload #5
    //   86: invokevirtual moveFragmentToExpectedState : (Landroid/app/Fragment;)V
    //   89: iload_1
    //   90: istore #6
    //   92: aload #5
    //   94: getfield mLoaderManager : Landroid/app/LoaderManagerImpl;
    //   97: ifnull -> 112
    //   100: iload_1
    //   101: aload #5
    //   103: getfield mLoaderManager : Landroid/app/LoaderManagerImpl;
    //   106: invokevirtual hasRunningLoaders : ()Z
    //   109: ior
    //   110: istore #6
    //   112: iinc #4, 1
    //   115: iload #6
    //   117: istore_1
    //   118: goto -> 63
    //   121: aload_0
    //   122: getfield mActive : Landroid/util/SparseArray;
    //   125: invokevirtual size : ()I
    //   128: istore_3
    //   129: iconst_0
    //   130: istore #4
    //   132: iload_1
    //   133: istore #6
    //   135: iload #4
    //   137: iload_3
    //   138: if_icmpge -> 231
    //   141: aload_0
    //   142: getfield mActive : Landroid/util/SparseArray;
    //   145: iload #4
    //   147: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   150: checkcast android/app/Fragment
    //   153: astore #5
    //   155: iload #6
    //   157: istore_1
    //   158: aload #5
    //   160: ifnull -> 222
    //   163: aload #5
    //   165: getfield mRemoving : Z
    //   168: ifne -> 182
    //   171: iload #6
    //   173: istore_1
    //   174: aload #5
    //   176: getfield mDetached : Z
    //   179: ifeq -> 222
    //   182: iload #6
    //   184: istore_1
    //   185: aload #5
    //   187: getfield mIsNewlyAdded : Z
    //   190: ifne -> 222
    //   193: aload_0
    //   194: aload #5
    //   196: invokevirtual moveFragmentToExpectedState : (Landroid/app/Fragment;)V
    //   199: iload #6
    //   201: istore_1
    //   202: aload #5
    //   204: getfield mLoaderManager : Landroid/app/LoaderManagerImpl;
    //   207: ifnull -> 222
    //   210: iload #6
    //   212: aload #5
    //   214: getfield mLoaderManager : Landroid/app/LoaderManagerImpl;
    //   217: invokevirtual hasRunningLoaders : ()Z
    //   220: ior
    //   221: istore_1
    //   222: iinc #4, 1
    //   225: iload_1
    //   226: istore #6
    //   228: goto -> 135
    //   231: iload #6
    //   233: ifne -> 240
    //   236: aload_0
    //   237: invokevirtual startPendingDeferredFragments : ()V
    //   240: aload_0
    //   241: getfield mNeedMenuInvalidate : Z
    //   244: ifeq -> 276
    //   247: aload_0
    //   248: getfield mHost : Landroid/app/FragmentHostCallback;
    //   251: astore #5
    //   253: aload #5
    //   255: ifnull -> 276
    //   258: aload_0
    //   259: getfield mCurState : I
    //   262: iconst_5
    //   263: if_icmpne -> 276
    //   266: aload #5
    //   268: invokevirtual onInvalidateOptionsMenu : ()V
    //   271: aload_0
    //   272: iconst_0
    //   273: putfield mNeedMenuInvalidate : Z
    //   276: return
  }
  
  void moveToState(Fragment paramFragment) {
    moveToState(paramFragment, this.mCurState, 0, 0, false);
  }
  
  void moveToState(Fragment paramFragment, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    // Byte code:
    //   0: aload_1
    //   1: getfield mAdded : Z
    //   4: istore #6
    //   6: iconst_1
    //   7: istore #7
    //   9: iload #6
    //   11: ifeq -> 27
    //   14: aload_1
    //   15: getfield mDetached : Z
    //   18: ifeq -> 24
    //   21: goto -> 27
    //   24: goto -> 41
    //   27: iload_2
    //   28: istore #8
    //   30: iload #8
    //   32: istore_2
    //   33: iload #8
    //   35: iconst_1
    //   36: if_icmple -> 41
    //   39: iconst_1
    //   40: istore_2
    //   41: iload_2
    //   42: istore #8
    //   44: aload_1
    //   45: getfield mRemoving : Z
    //   48: ifeq -> 88
    //   51: iload_2
    //   52: istore #8
    //   54: iload_2
    //   55: aload_1
    //   56: getfield mState : I
    //   59: if_icmple -> 88
    //   62: aload_1
    //   63: getfield mState : I
    //   66: ifne -> 82
    //   69: aload_1
    //   70: invokevirtual isInBackStack : ()Z
    //   73: ifeq -> 82
    //   76: iconst_1
    //   77: istore #8
    //   79: goto -> 88
    //   82: aload_1
    //   83: getfield mState : I
    //   86: istore #8
    //   88: iload #8
    //   90: istore_2
    //   91: aload_1
    //   92: getfield mDeferStart : Z
    //   95: ifeq -> 120
    //   98: iload #8
    //   100: istore_2
    //   101: aload_1
    //   102: getfield mState : I
    //   105: iconst_4
    //   106: if_icmpge -> 120
    //   109: iload #8
    //   111: istore_2
    //   112: iload #8
    //   114: iconst_3
    //   115: if_icmple -> 120
    //   118: iconst_3
    //   119: istore_2
    //   120: aload_1
    //   121: getfield mState : I
    //   124: iload_2
    //   125: if_icmpgt -> 1306
    //   128: aload_1
    //   129: getfield mFromLayout : Z
    //   132: ifeq -> 143
    //   135: aload_1
    //   136: getfield mInLayout : Z
    //   139: ifne -> 143
    //   142: return
    //   143: aload_1
    //   144: invokevirtual getAnimatingAway : ()Landroid/animation/Animator;
    //   147: ifnull -> 167
    //   150: aload_1
    //   151: aconst_null
    //   152: invokevirtual setAnimatingAway : (Landroid/animation/Animator;)V
    //   155: aload_0
    //   156: aload_1
    //   157: aload_1
    //   158: invokevirtual getStateAfterAnimating : ()I
    //   161: iconst_0
    //   162: iconst_0
    //   163: iconst_1
    //   164: invokevirtual moveToState : (Landroid/app/Fragment;IIIZ)V
    //   167: aload_1
    //   168: getfield mState : I
    //   171: istore #8
    //   173: iload #8
    //   175: ifeq -> 213
    //   178: iload #8
    //   180: iconst_1
    //   181: if_icmpeq -> 712
    //   184: iload_2
    //   185: istore #4
    //   187: iload #8
    //   189: iconst_2
    //   190: if_icmpeq -> 1147
    //   193: iload_2
    //   194: istore_3
    //   195: iload #8
    //   197: iconst_3
    //   198: if_icmpeq -> 1164
    //   201: iload_2
    //   202: istore #4
    //   204: iload #8
    //   206: iconst_4
    //   207: if_icmpeq -> 1227
    //   210: goto -> 1301
    //   213: iload_2
    //   214: ifle -> 712
    //   217: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   220: ifeq -> 259
    //   223: new java/lang/StringBuilder
    //   226: dup
    //   227: invokespecial <init> : ()V
    //   230: astore #9
    //   232: aload #9
    //   234: ldc_w 'moveto CREATED: '
    //   237: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   240: pop
    //   241: aload #9
    //   243: aload_1
    //   244: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   247: pop
    //   248: ldc 'FragmentManager'
    //   250: aload #9
    //   252: invokevirtual toString : ()Ljava/lang/String;
    //   255: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   258: pop
    //   259: iload_2
    //   260: istore_3
    //   261: aload_1
    //   262: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   265: ifnull -> 353
    //   268: aload_1
    //   269: aload_1
    //   270: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   273: ldc 'android:view_state'
    //   275: invokevirtual getSparseParcelableArray : (Ljava/lang/String;)Landroid/util/SparseArray;
    //   278: putfield mSavedViewState : Landroid/util/SparseArray;
    //   281: aload_1
    //   282: aload_0
    //   283: aload_1
    //   284: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   287: ldc 'android:target_state'
    //   289: invokevirtual getFragment : (Landroid/os/Bundle;Ljava/lang/String;)Landroid/app/Fragment;
    //   292: putfield mTarget : Landroid/app/Fragment;
    //   295: aload_1
    //   296: getfield mTarget : Landroid/app/Fragment;
    //   299: ifnull -> 316
    //   302: aload_1
    //   303: aload_1
    //   304: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   307: ldc 'android:target_req_state'
    //   309: iconst_0
    //   310: invokevirtual getInt : (Ljava/lang/String;I)I
    //   313: putfield mTargetRequestCode : I
    //   316: aload_1
    //   317: aload_1
    //   318: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   321: ldc 'android:user_visible_hint'
    //   323: iconst_1
    //   324: invokevirtual getBoolean : (Ljava/lang/String;Z)Z
    //   327: putfield mUserVisibleHint : Z
    //   330: iload_2
    //   331: istore_3
    //   332: aload_1
    //   333: getfield mUserVisibleHint : Z
    //   336: ifne -> 353
    //   339: aload_1
    //   340: iconst_1
    //   341: putfield mDeferStart : Z
    //   344: iload_2
    //   345: istore_3
    //   346: iload_2
    //   347: iconst_3
    //   348: if_icmple -> 353
    //   351: iconst_3
    //   352: istore_3
    //   353: aload_1
    //   354: aload_0
    //   355: getfield mHost : Landroid/app/FragmentHostCallback;
    //   358: putfield mHost : Landroid/app/FragmentHostCallback;
    //   361: aload_1
    //   362: aload_0
    //   363: getfield mParent : Landroid/app/Fragment;
    //   366: putfield mParentFragment : Landroid/app/Fragment;
    //   369: aload_0
    //   370: getfield mParent : Landroid/app/Fragment;
    //   373: astore #9
    //   375: aload #9
    //   377: ifnull -> 390
    //   380: aload #9
    //   382: getfield mChildFragmentManager : Landroid/app/FragmentManagerImpl;
    //   385: astore #9
    //   387: goto -> 399
    //   390: aload_0
    //   391: getfield mHost : Landroid/app/FragmentHostCallback;
    //   394: invokevirtual getFragmentManagerImpl : ()Landroid/app/FragmentManagerImpl;
    //   397: astore #9
    //   399: aload_1
    //   400: aload #9
    //   402: putfield mFragmentManager : Landroid/app/FragmentManagerImpl;
    //   405: aload_1
    //   406: getfield mTarget : Landroid/app/Fragment;
    //   409: ifnull -> 528
    //   412: aload_0
    //   413: getfield mActive : Landroid/util/SparseArray;
    //   416: aload_1
    //   417: getfield mTarget : Landroid/app/Fragment;
    //   420: getfield mIndex : I
    //   423: invokevirtual get : (I)Ljava/lang/Object;
    //   426: aload_1
    //   427: getfield mTarget : Landroid/app/Fragment;
    //   430: if_acmpne -> 462
    //   433: aload_1
    //   434: getfield mTarget : Landroid/app/Fragment;
    //   437: getfield mState : I
    //   440: iconst_1
    //   441: if_icmpge -> 459
    //   444: aload_0
    //   445: aload_1
    //   446: getfield mTarget : Landroid/app/Fragment;
    //   449: iconst_1
    //   450: iconst_0
    //   451: iconst_0
    //   452: iconst_1
    //   453: invokevirtual moveToState : (Landroid/app/Fragment;IIIZ)V
    //   456: goto -> 528
    //   459: goto -> 528
    //   462: new java/lang/StringBuilder
    //   465: dup
    //   466: invokespecial <init> : ()V
    //   469: astore #9
    //   471: aload #9
    //   473: ldc_w 'Fragment '
    //   476: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   479: pop
    //   480: aload #9
    //   482: aload_1
    //   483: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   486: pop
    //   487: aload #9
    //   489: ldc_w ' declared target fragment '
    //   492: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   495: pop
    //   496: aload #9
    //   498: aload_1
    //   499: getfield mTarget : Landroid/app/Fragment;
    //   502: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   505: pop
    //   506: aload #9
    //   508: ldc_w ' that does not belong to this FragmentManager!'
    //   511: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   514: pop
    //   515: new java/lang/IllegalStateException
    //   518: dup
    //   519: aload #9
    //   521: invokevirtual toString : ()Ljava/lang/String;
    //   524: invokespecial <init> : (Ljava/lang/String;)V
    //   527: athrow
    //   528: aload_0
    //   529: aload_1
    //   530: aload_0
    //   531: getfield mHost : Landroid/app/FragmentHostCallback;
    //   534: invokevirtual getContext : ()Landroid/content/Context;
    //   537: iconst_0
    //   538: invokevirtual dispatchOnFragmentPreAttached : (Landroid/app/Fragment;Landroid/content/Context;Z)V
    //   541: aload_1
    //   542: iconst_0
    //   543: putfield mCalled : Z
    //   546: aload_1
    //   547: aload_0
    //   548: getfield mHost : Landroid/app/FragmentHostCallback;
    //   551: invokevirtual getContext : ()Landroid/content/Context;
    //   554: invokevirtual onAttach : (Landroid/content/Context;)V
    //   557: aload_1
    //   558: getfield mCalled : Z
    //   561: ifeq -> 665
    //   564: aload_1
    //   565: getfield mParentFragment : Landroid/app/Fragment;
    //   568: ifnonnull -> 582
    //   571: aload_0
    //   572: getfield mHost : Landroid/app/FragmentHostCallback;
    //   575: aload_1
    //   576: invokevirtual onAttachFragment : (Landroid/app/Fragment;)V
    //   579: goto -> 590
    //   582: aload_1
    //   583: getfield mParentFragment : Landroid/app/Fragment;
    //   586: aload_1
    //   587: invokevirtual onAttachFragment : (Landroid/app/Fragment;)V
    //   590: aload_0
    //   591: aload_1
    //   592: aload_0
    //   593: getfield mHost : Landroid/app/FragmentHostCallback;
    //   596: invokevirtual getContext : ()Landroid/content/Context;
    //   599: iconst_0
    //   600: invokevirtual dispatchOnFragmentAttached : (Landroid/app/Fragment;Landroid/content/Context;Z)V
    //   603: aload_1
    //   604: getfield mIsCreated : Z
    //   607: ifne -> 641
    //   610: aload_0
    //   611: aload_1
    //   612: aload_1
    //   613: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   616: iconst_0
    //   617: invokevirtual dispatchOnFragmentPreCreated : (Landroid/app/Fragment;Landroid/os/Bundle;Z)V
    //   620: aload_1
    //   621: aload_1
    //   622: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   625: invokevirtual performCreate : (Landroid/os/Bundle;)V
    //   628: aload_0
    //   629: aload_1
    //   630: aload_1
    //   631: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   634: iconst_0
    //   635: invokevirtual dispatchOnFragmentCreated : (Landroid/app/Fragment;Landroid/os/Bundle;Z)V
    //   638: goto -> 655
    //   641: aload_1
    //   642: aload_1
    //   643: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   646: iconst_1
    //   647: invokevirtual restoreChildFragmentState : (Landroid/os/Bundle;Z)V
    //   650: aload_1
    //   651: iconst_1
    //   652: putfield mState : I
    //   655: aload_1
    //   656: iconst_0
    //   657: putfield mRetaining : Z
    //   660: iload_3
    //   661: istore_2
    //   662: goto -> 712
    //   665: new java/lang/StringBuilder
    //   668: dup
    //   669: invokespecial <init> : ()V
    //   672: astore #9
    //   674: aload #9
    //   676: ldc_w 'Fragment '
    //   679: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   682: pop
    //   683: aload #9
    //   685: aload_1
    //   686: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   689: pop
    //   690: aload #9
    //   692: ldc_w ' did not call through to super.onAttach()'
    //   695: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   698: pop
    //   699: new android/util/SuperNotCalledException
    //   702: dup
    //   703: aload #9
    //   705: invokevirtual toString : ()Ljava/lang/String;
    //   708: invokespecial <init> : (Ljava/lang/String;)V
    //   711: athrow
    //   712: aload_0
    //   713: aload_1
    //   714: invokevirtual ensureInflatedFragmentView : (Landroid/app/Fragment;)V
    //   717: iload_2
    //   718: iconst_1
    //   719: if_icmple -> 1144
    //   722: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   725: ifeq -> 764
    //   728: new java/lang/StringBuilder
    //   731: dup
    //   732: invokespecial <init> : ()V
    //   735: astore #9
    //   737: aload #9
    //   739: ldc_w 'moveto ACTIVITY_CREATED: '
    //   742: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   745: pop
    //   746: aload #9
    //   748: aload_1
    //   749: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   752: pop
    //   753: ldc 'FragmentManager'
    //   755: aload #9
    //   757: invokevirtual toString : ()Ljava/lang/String;
    //   760: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   763: pop
    //   764: aload_1
    //   765: getfield mFromLayout : Z
    //   768: ifne -> 1106
    //   771: aconst_null
    //   772: astore #9
    //   774: aload_1
    //   775: getfield mContainerId : I
    //   778: ifeq -> 974
    //   781: aload_1
    //   782: getfield mContainerId : I
    //   785: iconst_m1
    //   786: if_icmpne -> 839
    //   789: new java/lang/StringBuilder
    //   792: dup
    //   793: invokespecial <init> : ()V
    //   796: astore #9
    //   798: aload #9
    //   800: ldc_w 'Cannot create fragment '
    //   803: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   806: pop
    //   807: aload #9
    //   809: aload_1
    //   810: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   813: pop
    //   814: aload #9
    //   816: ldc_w ' for a container view with no id'
    //   819: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   822: pop
    //   823: aload_0
    //   824: new java/lang/IllegalArgumentException
    //   827: dup
    //   828: aload #9
    //   830: invokevirtual toString : ()Ljava/lang/String;
    //   833: invokespecial <init> : (Ljava/lang/String;)V
    //   836: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   839: aload_0
    //   840: getfield mContainer : Landroid/app/FragmentContainer;
    //   843: aload_1
    //   844: getfield mContainerId : I
    //   847: invokevirtual onFindViewById : (I)Landroid/view/View;
    //   850: checkcast android/view/ViewGroup
    //   853: astore #10
    //   855: aload #10
    //   857: ifnonnull -> 970
    //   860: aload_1
    //   861: getfield mRestored : Z
    //   864: ifne -> 970
    //   867: aload_1
    //   868: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   871: aload_1
    //   872: getfield mContainerId : I
    //   875: invokevirtual getResourceName : (I)Ljava/lang/String;
    //   878: astore #9
    //   880: goto -> 890
    //   883: astore #9
    //   885: ldc_w 'unknown'
    //   888: astore #9
    //   890: new java/lang/StringBuilder
    //   893: dup
    //   894: invokespecial <init> : ()V
    //   897: astore #11
    //   899: aload #11
    //   901: ldc_w 'No view found for id 0x'
    //   904: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   907: pop
    //   908: aload #11
    //   910: aload_1
    //   911: getfield mContainerId : I
    //   914: invokestatic toHexString : (I)Ljava/lang/String;
    //   917: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   920: pop
    //   921: aload #11
    //   923: ldc_w ' ('
    //   926: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   929: pop
    //   930: aload #11
    //   932: aload #9
    //   934: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   937: pop
    //   938: aload #11
    //   940: ldc_w ') for fragment '
    //   943: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   946: pop
    //   947: aload #11
    //   949: aload_1
    //   950: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   953: pop
    //   954: aload_0
    //   955: new java/lang/IllegalArgumentException
    //   958: dup
    //   959: aload #11
    //   961: invokevirtual toString : ()Ljava/lang/String;
    //   964: invokespecial <init> : (Ljava/lang/String;)V
    //   967: invokespecial throwException : (Ljava/lang/RuntimeException;)V
    //   970: aload #10
    //   972: astore #9
    //   974: aload_1
    //   975: aload #9
    //   977: putfield mContainer : Landroid/view/ViewGroup;
    //   980: aload_1
    //   981: aload_1
    //   982: aload_1
    //   983: aload_1
    //   984: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   987: invokevirtual performGetLayoutInflater : (Landroid/os/Bundle;)Landroid/view/LayoutInflater;
    //   990: aload #9
    //   992: aload_1
    //   993: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   996: invokevirtual performCreateView : (Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    //   999: putfield mView : Landroid/view/View;
    //   1002: aload_1
    //   1003: getfield mView : Landroid/view/View;
    //   1006: ifnull -> 1106
    //   1009: aload_1
    //   1010: getfield mView : Landroid/view/View;
    //   1013: iconst_0
    //   1014: invokevirtual setSaveFromParentEnabled : (Z)V
    //   1017: aload #9
    //   1019: ifnull -> 1031
    //   1022: aload #9
    //   1024: aload_1
    //   1025: getfield mView : Landroid/view/View;
    //   1028: invokevirtual addView : (Landroid/view/View;)V
    //   1031: aload_1
    //   1032: getfield mHidden : Z
    //   1035: ifeq -> 1047
    //   1038: aload_1
    //   1039: getfield mView : Landroid/view/View;
    //   1042: bipush #8
    //   1044: invokevirtual setVisibility : (I)V
    //   1047: aload_1
    //   1048: aload_1
    //   1049: getfield mView : Landroid/view/View;
    //   1052: aload_1
    //   1053: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1056: invokevirtual onViewCreated : (Landroid/view/View;Landroid/os/Bundle;)V
    //   1059: aload_0
    //   1060: aload_1
    //   1061: aload_1
    //   1062: getfield mView : Landroid/view/View;
    //   1065: aload_1
    //   1066: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1069: iconst_0
    //   1070: invokevirtual dispatchOnFragmentViewCreated : (Landroid/app/Fragment;Landroid/view/View;Landroid/os/Bundle;Z)V
    //   1073: aload_1
    //   1074: getfield mView : Landroid/view/View;
    //   1077: invokevirtual getVisibility : ()I
    //   1080: ifne -> 1097
    //   1083: aload_1
    //   1084: getfield mContainer : Landroid/view/ViewGroup;
    //   1087: ifnull -> 1097
    //   1090: iload #7
    //   1092: istore #5
    //   1094: goto -> 1100
    //   1097: iconst_0
    //   1098: istore #5
    //   1100: aload_1
    //   1101: iload #5
    //   1103: putfield mIsNewlyAdded : Z
    //   1106: aload_1
    //   1107: aload_1
    //   1108: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1111: invokevirtual performActivityCreated : (Landroid/os/Bundle;)V
    //   1114: aload_0
    //   1115: aload_1
    //   1116: aload_1
    //   1117: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1120: iconst_0
    //   1121: invokevirtual dispatchOnFragmentActivityCreated : (Landroid/app/Fragment;Landroid/os/Bundle;Z)V
    //   1124: aload_1
    //   1125: getfield mView : Landroid/view/View;
    //   1128: ifnull -> 1139
    //   1131: aload_1
    //   1132: aload_1
    //   1133: getfield mSavedFragmentState : Landroid/os/Bundle;
    //   1136: invokevirtual restoreViewState : (Landroid/os/Bundle;)V
    //   1139: aload_1
    //   1140: aconst_null
    //   1141: putfield mSavedFragmentState : Landroid/os/Bundle;
    //   1144: iload_2
    //   1145: istore #4
    //   1147: iload #4
    //   1149: istore_3
    //   1150: iload #4
    //   1152: iconst_2
    //   1153: if_icmple -> 1164
    //   1156: aload_1
    //   1157: iconst_3
    //   1158: putfield mState : I
    //   1161: iload #4
    //   1163: istore_3
    //   1164: iload_3
    //   1165: istore #4
    //   1167: iload_3
    //   1168: iconst_3
    //   1169: if_icmple -> 1227
    //   1172: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   1175: ifeq -> 1214
    //   1178: new java/lang/StringBuilder
    //   1181: dup
    //   1182: invokespecial <init> : ()V
    //   1185: astore #9
    //   1187: aload #9
    //   1189: ldc_w 'moveto STARTED: '
    //   1192: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1195: pop
    //   1196: aload #9
    //   1198: aload_1
    //   1199: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1202: pop
    //   1203: ldc 'FragmentManager'
    //   1205: aload #9
    //   1207: invokevirtual toString : ()Ljava/lang/String;
    //   1210: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1213: pop
    //   1214: aload_1
    //   1215: invokevirtual performStart : ()V
    //   1218: aload_0
    //   1219: aload_1
    //   1220: iconst_0
    //   1221: invokevirtual dispatchOnFragmentStarted : (Landroid/app/Fragment;Z)V
    //   1224: iload_3
    //   1225: istore #4
    //   1227: iload #4
    //   1229: istore_2
    //   1230: iload #4
    //   1232: iconst_4
    //   1233: if_icmple -> 1301
    //   1236: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   1239: ifeq -> 1278
    //   1242: new java/lang/StringBuilder
    //   1245: dup
    //   1246: invokespecial <init> : ()V
    //   1249: astore #9
    //   1251: aload #9
    //   1253: ldc_w 'moveto RESUMED: '
    //   1256: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1259: pop
    //   1260: aload #9
    //   1262: aload_1
    //   1263: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1266: pop
    //   1267: ldc 'FragmentManager'
    //   1269: aload #9
    //   1271: invokevirtual toString : ()Ljava/lang/String;
    //   1274: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1277: pop
    //   1278: aload_1
    //   1279: invokevirtual performResume : ()V
    //   1282: aload_0
    //   1283: aload_1
    //   1284: iconst_0
    //   1285: invokevirtual dispatchOnFragmentResumed : (Landroid/app/Fragment;Z)V
    //   1288: aload_1
    //   1289: aconst_null
    //   1290: putfield mSavedFragmentState : Landroid/os/Bundle;
    //   1293: aload_1
    //   1294: aconst_null
    //   1295: putfield mSavedViewState : Landroid/util/SparseArray;
    //   1298: iload #4
    //   1300: istore_2
    //   1301: iload_2
    //   1302: istore_3
    //   1303: goto -> 1954
    //   1306: aload_1
    //   1307: getfield mState : I
    //   1310: iload_2
    //   1311: if_icmple -> 1952
    //   1314: aload_1
    //   1315: getfield mState : I
    //   1318: istore #8
    //   1320: iload #8
    //   1322: iconst_1
    //   1323: if_icmpeq -> 1777
    //   1326: iload #8
    //   1328: iconst_2
    //   1329: if_icmpeq -> 1469
    //   1332: iload #8
    //   1334: iconst_3
    //   1335: if_icmpeq -> 1469
    //   1338: iload #8
    //   1340: iconst_4
    //   1341: if_icmpeq -> 1412
    //   1344: iload #8
    //   1346: iconst_5
    //   1347: if_icmpeq -> 1355
    //   1350: iload_2
    //   1351: istore_3
    //   1352: goto -> 1954
    //   1355: iload_2
    //   1356: iconst_5
    //   1357: if_icmpge -> 1412
    //   1360: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   1363: ifeq -> 1402
    //   1366: new java/lang/StringBuilder
    //   1369: dup
    //   1370: invokespecial <init> : ()V
    //   1373: astore #9
    //   1375: aload #9
    //   1377: ldc_w 'movefrom RESUMED: '
    //   1380: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1383: pop
    //   1384: aload #9
    //   1386: aload_1
    //   1387: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1390: pop
    //   1391: ldc 'FragmentManager'
    //   1393: aload #9
    //   1395: invokevirtual toString : ()Ljava/lang/String;
    //   1398: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1401: pop
    //   1402: aload_1
    //   1403: invokevirtual performPause : ()V
    //   1406: aload_0
    //   1407: aload_1
    //   1408: iconst_0
    //   1409: invokevirtual dispatchOnFragmentPaused : (Landroid/app/Fragment;Z)V
    //   1412: iload_2
    //   1413: iconst_4
    //   1414: if_icmpge -> 1469
    //   1417: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   1420: ifeq -> 1459
    //   1423: new java/lang/StringBuilder
    //   1426: dup
    //   1427: invokespecial <init> : ()V
    //   1430: astore #9
    //   1432: aload #9
    //   1434: ldc_w 'movefrom STARTED: '
    //   1437: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1440: pop
    //   1441: aload #9
    //   1443: aload_1
    //   1444: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1447: pop
    //   1448: ldc 'FragmentManager'
    //   1450: aload #9
    //   1452: invokevirtual toString : ()Ljava/lang/String;
    //   1455: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1458: pop
    //   1459: aload_1
    //   1460: invokevirtual performStop : ()V
    //   1463: aload_0
    //   1464: aload_1
    //   1465: iconst_0
    //   1466: invokevirtual dispatchOnFragmentStopped : (Landroid/app/Fragment;Z)V
    //   1469: iload_2
    //   1470: iconst_2
    //   1471: if_icmpge -> 1774
    //   1474: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   1477: ifeq -> 1516
    //   1480: new java/lang/StringBuilder
    //   1483: dup
    //   1484: invokespecial <init> : ()V
    //   1487: astore #9
    //   1489: aload #9
    //   1491: ldc_w 'movefrom ACTIVITY_CREATED: '
    //   1494: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1497: pop
    //   1498: aload #9
    //   1500: aload_1
    //   1501: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1504: pop
    //   1505: ldc 'FragmentManager'
    //   1507: aload #9
    //   1509: invokevirtual toString : ()Ljava/lang/String;
    //   1512: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1515: pop
    //   1516: aload_1
    //   1517: getfield mView : Landroid/view/View;
    //   1520: ifnull -> 1546
    //   1523: aload_0
    //   1524: getfield mHost : Landroid/app/FragmentHostCallback;
    //   1527: aload_1
    //   1528: invokevirtual onShouldSaveFragmentState : (Landroid/app/Fragment;)Z
    //   1531: ifeq -> 1546
    //   1534: aload_1
    //   1535: getfield mSavedViewState : Landroid/util/SparseArray;
    //   1538: ifnonnull -> 1546
    //   1541: aload_0
    //   1542: aload_1
    //   1543: invokevirtual saveFragmentViewState : (Landroid/app/Fragment;)V
    //   1546: aload_1
    //   1547: invokevirtual performDestroyView : ()V
    //   1550: aload_0
    //   1551: aload_1
    //   1552: iconst_0
    //   1553: invokevirtual dispatchOnFragmentViewDestroyed : (Landroid/app/Fragment;Z)V
    //   1556: aload_1
    //   1557: getfield mView : Landroid/view/View;
    //   1560: ifnull -> 1756
    //   1563: aload_1
    //   1564: getfield mContainer : Landroid/view/ViewGroup;
    //   1567: ifnull -> 1756
    //   1570: aload_0
    //   1571: invokevirtual getTargetSdk : ()I
    //   1574: bipush #26
    //   1576: if_icmplt -> 1597
    //   1579: aload_1
    //   1580: getfield mView : Landroid/view/View;
    //   1583: invokevirtual clearAnimation : ()V
    //   1586: aload_1
    //   1587: getfield mContainer : Landroid/view/ViewGroup;
    //   1590: aload_1
    //   1591: getfield mView : Landroid/view/View;
    //   1594: invokevirtual endViewTransition : (Landroid/view/View;)V
    //   1597: aload_0
    //   1598: getfield mCurState : I
    //   1601: ifle -> 1653
    //   1604: aload_0
    //   1605: getfield mDestroyed : Z
    //   1608: ifne -> 1653
    //   1611: aload_1
    //   1612: getfield mView : Landroid/view/View;
    //   1615: invokevirtual getVisibility : ()I
    //   1618: ifne -> 1650
    //   1621: aload_1
    //   1622: getfield mView : Landroid/view/View;
    //   1625: invokevirtual getTransitionAlpha : ()F
    //   1628: fconst_0
    //   1629: fcmpl
    //   1630: ifle -> 1647
    //   1633: aload_0
    //   1634: aload_1
    //   1635: iload_3
    //   1636: iconst_0
    //   1637: iload #4
    //   1639: invokevirtual loadAnimator : (Landroid/app/Fragment;IZI)Landroid/animation/Animator;
    //   1642: astore #9
    //   1644: goto -> 1656
    //   1647: goto -> 1653
    //   1650: goto -> 1653
    //   1653: aconst_null
    //   1654: astore #9
    //   1656: aload_1
    //   1657: getfield mView : Landroid/view/View;
    //   1660: fconst_1
    //   1661: invokevirtual setTransitionAlpha : (F)V
    //   1664: aload #9
    //   1666: ifnull -> 1742
    //   1669: aload_1
    //   1670: getfield mContainer : Landroid/view/ViewGroup;
    //   1673: astore #10
    //   1675: aload_1
    //   1676: getfield mView : Landroid/view/View;
    //   1679: astore #11
    //   1681: aload #10
    //   1683: aload #11
    //   1685: invokevirtual startViewTransition : (Landroid/view/View;)V
    //   1688: aload_1
    //   1689: aload #9
    //   1691: invokevirtual setAnimatingAway : (Landroid/animation/Animator;)V
    //   1694: aload_1
    //   1695: iload_2
    //   1696: invokevirtual setStateAfterAnimating : (I)V
    //   1699: aload #9
    //   1701: new android/app/FragmentManagerImpl$2
    //   1704: dup
    //   1705: aload_0
    //   1706: aload #10
    //   1708: aload #11
    //   1710: aload_1
    //   1711: aload_1
    //   1712: invokespecial <init> : (Landroid/app/FragmentManagerImpl;Landroid/view/ViewGroup;Landroid/view/View;Landroid/app/Fragment;Landroid/app/Fragment;)V
    //   1715: invokevirtual addListener : (Landroid/animation/Animator$AnimatorListener;)V
    //   1718: aload #9
    //   1720: aload_1
    //   1721: getfield mView : Landroid/view/View;
    //   1724: invokevirtual setTarget : (Ljava/lang/Object;)V
    //   1727: aload_0
    //   1728: aload_1
    //   1729: getfield mView : Landroid/view/View;
    //   1732: aload #9
    //   1734: invokespecial setHWLayerAnimListenerIfAlpha : (Landroid/view/View;Landroid/animation/Animator;)V
    //   1737: aload #9
    //   1739: invokevirtual start : ()V
    //   1742: aload_1
    //   1743: getfield mContainer : Landroid/view/ViewGroup;
    //   1746: aload_1
    //   1747: getfield mView : Landroid/view/View;
    //   1750: invokevirtual removeView : (Landroid/view/View;)V
    //   1753: goto -> 1756
    //   1756: aload_1
    //   1757: aconst_null
    //   1758: putfield mContainer : Landroid/view/ViewGroup;
    //   1761: aload_1
    //   1762: aconst_null
    //   1763: putfield mView : Landroid/view/View;
    //   1766: aload_1
    //   1767: iconst_0
    //   1768: putfield mInLayout : Z
    //   1771: goto -> 1777
    //   1774: goto -> 1777
    //   1777: iload_2
    //   1778: istore_3
    //   1779: iload_2
    //   1780: iconst_1
    //   1781: if_icmpge -> 1954
    //   1784: aload_0
    //   1785: getfield mDestroyed : Z
    //   1788: ifeq -> 1814
    //   1791: aload_1
    //   1792: invokevirtual getAnimatingAway : ()Landroid/animation/Animator;
    //   1795: ifnull -> 1814
    //   1798: aload_1
    //   1799: invokevirtual getAnimatingAway : ()Landroid/animation/Animator;
    //   1802: astore #9
    //   1804: aload_1
    //   1805: aconst_null
    //   1806: invokevirtual setAnimatingAway : (Landroid/animation/Animator;)V
    //   1809: aload #9
    //   1811: invokevirtual cancel : ()V
    //   1814: aload_1
    //   1815: invokevirtual getAnimatingAway : ()Landroid/animation/Animator;
    //   1818: ifnull -> 1831
    //   1821: aload_1
    //   1822: iload_2
    //   1823: invokevirtual setStateAfterAnimating : (I)V
    //   1826: iconst_1
    //   1827: istore_3
    //   1828: goto -> 1954
    //   1831: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   1834: ifeq -> 1873
    //   1837: new java/lang/StringBuilder
    //   1840: dup
    //   1841: invokespecial <init> : ()V
    //   1844: astore #9
    //   1846: aload #9
    //   1848: ldc_w 'movefrom CREATED: '
    //   1851: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1854: pop
    //   1855: aload #9
    //   1857: aload_1
    //   1858: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1861: pop
    //   1862: ldc 'FragmentManager'
    //   1864: aload #9
    //   1866: invokevirtual toString : ()Ljava/lang/String;
    //   1869: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   1872: pop
    //   1873: aload_1
    //   1874: getfield mRetaining : Z
    //   1877: ifne -> 1893
    //   1880: aload_1
    //   1881: invokevirtual performDestroy : ()V
    //   1884: aload_0
    //   1885: aload_1
    //   1886: iconst_0
    //   1887: invokevirtual dispatchOnFragmentDestroyed : (Landroid/app/Fragment;Z)V
    //   1890: goto -> 1898
    //   1893: aload_1
    //   1894: iconst_0
    //   1895: putfield mState : I
    //   1898: aload_1
    //   1899: invokevirtual performDetach : ()V
    //   1902: aload_0
    //   1903: aload_1
    //   1904: iconst_0
    //   1905: invokevirtual dispatchOnFragmentDetached : (Landroid/app/Fragment;Z)V
    //   1908: iload_2
    //   1909: istore_3
    //   1910: iload #5
    //   1912: ifne -> 1954
    //   1915: aload_1
    //   1916: getfield mRetaining : Z
    //   1919: ifne -> 1932
    //   1922: aload_0
    //   1923: aload_1
    //   1924: invokevirtual makeInactive : (Landroid/app/Fragment;)V
    //   1927: iload_2
    //   1928: istore_3
    //   1929: goto -> 1954
    //   1932: aload_1
    //   1933: aconst_null
    //   1934: putfield mHost : Landroid/app/FragmentHostCallback;
    //   1937: aload_1
    //   1938: aconst_null
    //   1939: putfield mParentFragment : Landroid/app/Fragment;
    //   1942: aload_1
    //   1943: aconst_null
    //   1944: putfield mFragmentManager : Landroid/app/FragmentManagerImpl;
    //   1947: iload_2
    //   1948: istore_3
    //   1949: goto -> 1954
    //   1952: iload_2
    //   1953: istore_3
    //   1954: aload_1
    //   1955: getfield mState : I
    //   1958: iload_3
    //   1959: if_icmpeq -> 2038
    //   1962: new java/lang/StringBuilder
    //   1965: dup
    //   1966: invokespecial <init> : ()V
    //   1969: astore #9
    //   1971: aload #9
    //   1973: ldc_w 'moveToState: Fragment state for '
    //   1976: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1979: pop
    //   1980: aload #9
    //   1982: aload_1
    //   1983: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1986: pop
    //   1987: aload #9
    //   1989: ldc_w ' not updated inline; expected state '
    //   1992: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1995: pop
    //   1996: aload #9
    //   1998: iload_3
    //   1999: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   2002: pop
    //   2003: aload #9
    //   2005: ldc_w ' found '
    //   2008: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2011: pop
    //   2012: aload #9
    //   2014: aload_1
    //   2015: getfield mState : I
    //   2018: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   2021: pop
    //   2022: ldc 'FragmentManager'
    //   2024: aload #9
    //   2026: invokevirtual toString : ()Ljava/lang/String;
    //   2029: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   2032: pop
    //   2033: aload_1
    //   2034: iload_3
    //   2035: putfield mState : I
    //   2038: return
    // Exception table:
    //   from	to	target	type
    //   867	880	883	android/content/res/Resources$NotFoundException
  }
  
  public void noteStateNotSaved() {
    this.mSavedNonConfig = null;
    this.mStateSaved = false;
    int i = this.mAdded.size();
    for (byte b = 0; b < i; b++) {
      Fragment fragment = this.mAdded.get(b);
      if (fragment != null)
        fragment.noteStateNotSaved(); 
    } 
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    if (!"fragment".equals(paramString))
      return null; 
    String str1 = paramAttributeSet.getAttributeValue(null, "class");
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Fragment);
    int i = 0;
    if (str1 == null)
      str1 = typedArray.getString(0); 
    int j = typedArray.getResourceId(1, -1);
    String str2 = typedArray.getString(2);
    typedArray.recycle();
    if (paramView != null)
      i = paramView.getId(); 
    if (i != -1 || j != -1 || str2 != null) {
      Fragment fragment2;
      if (j != -1) {
        Fragment fragment = findFragmentById(j);
      } else {
        paramView = null;
      } 
      View view = paramView;
      if (paramView == null) {
        view = paramView;
        if (str2 != null)
          fragment2 = findFragmentByTag(str2); 
      } 
      Fragment fragment1 = fragment2;
      if (fragment2 == null) {
        fragment1 = fragment2;
        if (i != -1)
          fragment1 = findFragmentById(i); 
      } 
      if (DEBUG) {
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("onCreateView: id=0x");
        stringBuilder2.append(Integer.toHexString(j));
        stringBuilder2.append(" fname=");
        stringBuilder2.append(str1);
        stringBuilder2.append(" existing=");
        stringBuilder2.append(fragment1);
        Log.v("FragmentManager", stringBuilder2.toString());
      } 
      if (fragment1 == null) {
        int k;
        fragment1 = this.mContainer.instantiate(paramContext, str1, null);
        fragment1.mFromLayout = true;
        if (j != 0) {
          k = j;
        } else {
          k = i;
        } 
        fragment1.mFragmentId = k;
        fragment1.mContainerId = i;
        fragment1.mTag = str2;
        fragment1.mInLayout = true;
        fragment1.mFragmentManager = this;
        fragment1.mHost = this.mHost;
        fragment1.onInflate(this.mHost.getContext(), paramAttributeSet, fragment1.mSavedFragmentState);
        addFragment(fragment1, true);
      } else if (!fragment1.mInLayout) {
        fragment1.mInLayout = true;
        fragment1.mHost = this.mHost;
        if (!fragment1.mRetaining)
          fragment1.onInflate(this.mHost.getContext(), paramAttributeSet, fragment1.mSavedFragmentState); 
      } else {
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append(paramAttributeSet.getPositionDescription());
        stringBuilder1.append(": Duplicate id 0x");
        stringBuilder1.append(Integer.toHexString(j));
        stringBuilder1.append(", tag ");
        stringBuilder1.append(str2);
        stringBuilder1.append(", or parent id 0x");
        stringBuilder1.append(Integer.toHexString(i));
        stringBuilder1.append(" with another fragment for ");
        stringBuilder1.append(str1);
        throw new IllegalArgumentException(stringBuilder1.toString());
      } 
      if (this.mCurState < 1 && ((Fragment)stringBuilder1).mFromLayout) {
        moveToState((Fragment)stringBuilder1, 1, 0, 0, false);
      } else {
        moveToState((Fragment)stringBuilder1);
      } 
      if (((Fragment)stringBuilder1).mView != null) {
        if (j != 0)
          ((Fragment)stringBuilder1).mView.setId(j); 
        if (((Fragment)stringBuilder1).mView.getTag() == null)
          ((Fragment)stringBuilder1).mView.setTag(str2); 
        return ((Fragment)stringBuilder1).mView;
      } 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Fragment ");
      stringBuilder1.append(str1);
      stringBuilder1.append(" did not create a view.");
      throw new IllegalStateException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramAttributeSet.getPositionDescription());
    stringBuilder.append(": Must specify unique android:id, android:tag, or have a parent with an id for ");
    stringBuilder.append(str1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet) {
    return null;
  }
  
  public void performPendingDeferredStart(Fragment paramFragment) {
    if (paramFragment.mDeferStart) {
      if (this.mExecutingActions) {
        this.mHavePendingDeferredStart = true;
        return;
      } 
      paramFragment.mDeferStart = false;
      moveToState(paramFragment, this.mCurState, 0, 0, false);
    } 
  }
  
  public void popBackStack() {
    enqueueAction(new PopBackStackState(null, -1, 0), false);
  }
  
  public void popBackStack(int paramInt1, int paramInt2) {
    if (paramInt1 >= 0) {
      enqueueAction(new PopBackStackState(null, paramInt1, paramInt2), false);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Bad id: ");
    stringBuilder.append(paramInt1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void popBackStack(String paramString, int paramInt) {
    enqueueAction(new PopBackStackState(paramString, -1, paramInt), false);
  }
  
  public boolean popBackStackImmediate() {
    checkStateLoss();
    return popBackStackImmediate(null, -1, 0);
  }
  
  public boolean popBackStackImmediate(int paramInt1, int paramInt2) {
    checkStateLoss();
    if (paramInt1 >= 0)
      return popBackStackImmediate(null, paramInt1, paramInt2); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Bad id: ");
    stringBuilder.append(paramInt1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean popBackStackImmediate(String paramString, int paramInt) {
    checkStateLoss();
    return popBackStackImmediate(paramString, -1, paramInt);
  }
  
  boolean popBackStackState(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, String paramString, int paramInt1, int paramInt2) {
    ArrayList<BackStackRecord> arrayList = this.mBackStack;
    if (arrayList == null)
      return false; 
    if (paramString == null && paramInt1 < 0 && (paramInt2 & 0x1) == 0) {
      paramInt1 = arrayList.size() - 1;
      if (paramInt1 < 0)
        return false; 
      paramArrayList.add(this.mBackStack.remove(paramInt1));
      paramArrayList1.add(Boolean.valueOf(true));
    } else {
      int i = -1;
      if (paramString != null || paramInt1 >= 0) {
        int j;
        for (j = this.mBackStack.size() - 1; j >= 0; j--) {
          BackStackRecord backStackRecord = this.mBackStack.get(j);
          if ((paramString != null && paramString.equals(backStackRecord.getName())) || (paramInt1 >= 0 && paramInt1 == backStackRecord.mIndex))
            break; 
        } 
        if (j < 0)
          return false; 
        i = j;
        if ((paramInt2 & 0x1) != 0)
          for (paramInt2 = j - 1;; paramInt2--) {
            i = paramInt2;
            if (paramInt2 >= 0) {
              BackStackRecord backStackRecord = this.mBackStack.get(paramInt2);
              if (paramString == null || !paramString.equals(backStackRecord.getName())) {
                i = paramInt2;
                if (paramInt1 >= 0) {
                  i = paramInt2;
                  if (paramInt1 == backStackRecord.mIndex)
                    continue; 
                } 
                break;
              } 
              continue;
            } 
            break;
          }  
      } 
      if (i == this.mBackStack.size() - 1)
        return false; 
      for (paramInt1 = this.mBackStack.size() - 1; paramInt1 > i; paramInt1--) {
        paramArrayList.add(this.mBackStack.remove(paramInt1));
        paramArrayList1.add(Boolean.valueOf(true));
      } 
    } 
    return true;
  }
  
  public void putFragment(Bundle paramBundle, String paramString, Fragment paramFragment) {
    if (paramFragment.mIndex < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Fragment ");
      stringBuilder.append(paramFragment);
      stringBuilder.append(" is not currently in the FragmentManager");
      throwException(new IllegalStateException(stringBuilder.toString()));
    } 
    paramBundle.putInt(paramString, paramFragment.mIndex);
  }
  
  public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks, boolean paramBoolean) {
    this.mLifecycleCallbacks.add(new Pair(paramFragmentLifecycleCallbacks, Boolean.valueOf(paramBoolean)));
  }
  
  public void removeFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("remove: ");
      stringBuilder.append(paramFragment);
      stringBuilder.append(" nesting=");
      stringBuilder.append(paramFragment.mBackStackNesting);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    boolean bool = paramFragment.isInBackStack();
    if (!paramFragment.mDetached || (bool ^ true) != 0)
      synchronized (this.mAdded) {
        this.mAdded.remove(paramFragment);
        if (paramFragment.mHasMenu && paramFragment.mMenuVisible)
          this.mNeedMenuInvalidate = true; 
        paramFragment.mAdded = false;
        paramFragment.mRemoving = true;
        return;
      }  
  }
  
  public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener) {
    ArrayList<FragmentManager.OnBackStackChangedListener> arrayList = this.mBackStackChangeListeners;
    if (arrayList != null)
      arrayList.remove(paramOnBackStackChangedListener); 
  }
  
  void reportBackStackChanged() {
    if (this.mBackStackChangeListeners != null)
      for (byte b = 0; b < this.mBackStackChangeListeners.size(); b++)
        ((FragmentManager.OnBackStackChangedListener)this.mBackStackChangeListeners.get(b)).onBackStackChanged();  
  }
  
  void restoreAllState(Parcelable paramParcelable, FragmentManagerNonConfig paramFragmentManagerNonConfig) {
    Fragment fragment;
    if (paramParcelable == null)
      return; 
    FragmentManagerState fragmentManagerState = (FragmentManagerState)paramParcelable;
    if (fragmentManagerState.mActive == null)
      return; 
    paramParcelable = null;
    if (paramFragmentManagerNonConfig != null) {
      byte b1;
      List<Fragment> list = paramFragmentManagerNonConfig.getFragments();
      List<FragmentManagerNonConfig> list1 = paramFragmentManagerNonConfig.getChildNonConfigs();
      if (list != null) {
        b1 = list.size();
      } else {
        b1 = 0;
      } 
      byte b2 = 0;
      while (true) {
        List<FragmentManagerNonConfig> list2 = list1;
        if (b2 < b1) {
          fragment = list.get(b2);
          if (DEBUG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("restoreAllState: re-attaching retained ");
            stringBuilder.append(fragment);
            Log.v("FragmentManager", stringBuilder.toString());
          } 
          byte b;
          for (b = 0; b < fragmentManagerState.mActive.length && (fragmentManagerState.mActive[b]).mIndex != fragment.mIndex; b++);
          if (b == fragmentManagerState.mActive.length) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Could not find active fragment with index ");
            stringBuilder.append(fragment.mIndex);
            throwException(new IllegalStateException(stringBuilder.toString()));
          } 
          FragmentState fragmentState = fragmentManagerState.mActive[b];
          fragmentState.mInstance = fragment;
          fragment.mSavedViewState = null;
          fragment.mBackStackNesting = 0;
          fragment.mInLayout = false;
          fragment.mAdded = false;
          fragment.mTarget = null;
          if (fragmentState.mSavedFragmentState != null) {
            fragmentState.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
            fragment.mSavedViewState = fragmentState.mSavedFragmentState.getSparseParcelableArray("android:view_state");
            fragment.mSavedFragmentState = fragmentState.mSavedFragmentState;
          } 
          b2++;
          continue;
        } 
        break;
      } 
    } 
    this.mActive = new SparseArray(fragmentManagerState.mActive.length);
    int i;
    for (i = 0; i < fragmentManagerState.mActive.length; i++) {
      FragmentState fragmentState = fragmentManagerState.mActive[i];
      if (fragmentState != null) {
        FragmentManagerNonConfig fragmentManagerNonConfig1 = null;
        FragmentManagerNonConfig fragmentManagerNonConfig2 = fragmentManagerNonConfig1;
        if (fragment != null) {
          fragmentManagerNonConfig2 = fragmentManagerNonConfig1;
          if (i < fragment.size())
            fragmentManagerNonConfig2 = fragment.get(i); 
        } 
        Fragment fragment1 = fragmentState.instantiate(this.mHost, this.mContainer, this.mParent, fragmentManagerNonConfig2);
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("restoreAllState: active #");
          stringBuilder.append(i);
          stringBuilder.append(": ");
          stringBuilder.append(fragment1);
          Log.v("FragmentManager", stringBuilder.toString());
        } 
        this.mActive.put(fragment1.mIndex, fragment1);
        fragmentState.mInstance = null;
      } 
    } 
    if (paramFragmentManagerNonConfig != null) {
      List<Fragment> list = paramFragmentManagerNonConfig.getFragments();
      if (list != null) {
        i = list.size();
      } else {
        i = 0;
      } 
      for (byte b = 0; b < i; b++) {
        fragment = list.get(b);
        if (fragment.mTargetIndex >= 0) {
          fragment.mTarget = (Fragment)this.mActive.get(fragment.mTargetIndex);
          if (fragment.mTarget == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Re-attaching retained fragment ");
            stringBuilder.append(fragment);
            stringBuilder.append(" target no longer exists: ");
            stringBuilder.append(fragment.mTargetIndex);
            Log.w("FragmentManager", stringBuilder.toString());
            fragment.mTarget = null;
          } 
        } 
      } 
    } 
    this.mAdded.clear();
    if (fragmentManagerState.mAdded != null) {
      i = 0;
      while (i < fragmentManagerState.mAdded.length) {
        fragment = (Fragment)this.mActive.get(fragmentManagerState.mAdded[i]);
        if (fragment == null) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("No instantiated fragment for index #");
          stringBuilder.append(fragmentManagerState.mAdded[i]);
          throwException(new IllegalStateException(stringBuilder.toString()));
        } 
        fragment.mAdded = true;
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("restoreAllState: added #");
          stringBuilder.append(i);
          stringBuilder.append(": ");
          stringBuilder.append(fragment);
          Log.v("FragmentManager", stringBuilder.toString());
        } 
        if (!this.mAdded.contains(fragment)) {
          synchronized (this.mAdded) {
            this.mAdded.add(fragment);
            i++;
          } 
          continue;
        } 
        throw new IllegalStateException("Already added!");
      } 
    } 
    if (fragmentManagerState.mBackStack != null) {
      this.mBackStack = new ArrayList<>(fragmentManagerState.mBackStack.length);
      for (i = 0; i < fragmentManagerState.mBackStack.length; i++) {
        BackStackRecord backStackRecord = fragmentManagerState.mBackStack[i].instantiate(this);
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("restoreAllState: back stack #");
          stringBuilder.append(i);
          stringBuilder.append(" (index ");
          stringBuilder.append(backStackRecord.mIndex);
          stringBuilder.append("): ");
          stringBuilder.append(backStackRecord);
          Log.v("FragmentManager", stringBuilder.toString());
          FastPrintWriter fastPrintWriter = new FastPrintWriter((Writer)new LogWriter(2, "FragmentManager"), false, 1024);
          backStackRecord.dump("  ", (PrintWriter)fastPrintWriter, false);
          fastPrintWriter.flush();
        } 
        this.mBackStack.add(backStackRecord);
        if (backStackRecord.mIndex >= 0)
          setBackStackIndex(backStackRecord.mIndex, backStackRecord); 
      } 
    } else {
      this.mBackStack = null;
    } 
    if (fragmentManagerState.mPrimaryNavActiveIndex >= 0)
      this.mPrimaryNav = (Fragment)this.mActive.get(fragmentManagerState.mPrimaryNavActiveIndex); 
    this.mNextFragmentIndex = fragmentManagerState.mNextFragmentIndex;
  }
  
  FragmentManagerNonConfig retainNonConfig() {
    setRetaining(this.mSavedNonConfig);
    return this.mSavedNonConfig;
  }
  
  Parcelable saveAllState() {
    StringBuilder stringBuilder1;
    StringBuilder stringBuilder2;
    forcePostponedTransactions();
    endAnimatingAwayFragments();
    execPendingActions();
    this.mStateSaved = true;
    this.mSavedNonConfig = null;
    SparseArray<Fragment> sparseArray = this.mActive;
    if (sparseArray == null || sparseArray.size() <= 0)
      return null; 
    int i = this.mActive.size();
    FragmentState[] arrayOfFragmentState = new FragmentState[i];
    int j = 0;
    byte b;
    for (b = 0; b < i; b++) {
      Fragment fragment1 = (Fragment)this.mActive.valueAt(b);
      if (fragment1 != null) {
        if (fragment1.mIndex < 0) {
          stringBuilder2 = new StringBuilder();
          stringBuilder2.append("Failure saving state: active ");
          stringBuilder2.append(fragment1);
          stringBuilder2.append(" has cleared index: ");
          stringBuilder2.append(fragment1.mIndex);
          throwException(new IllegalStateException(stringBuilder2.toString()));
        } 
        byte b1 = 1;
        FragmentState fragmentState = new FragmentState(fragment1);
        arrayOfFragmentState[b] = fragmentState;
        if (fragment1.mState > 0 && fragmentState.mSavedFragmentState == null) {
          fragmentState.mSavedFragmentState = saveFragmentBasicState(fragment1);
          if (fragment1.mTarget != null) {
            if (fragment1.mTarget.mIndex < 0) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Failure saving state: ");
              stringBuilder.append(fragment1);
              stringBuilder.append(" has target not in fragment manager: ");
              stringBuilder.append(fragment1.mTarget);
              throwException(new IllegalStateException(stringBuilder.toString()));
            } 
            if (fragmentState.mSavedFragmentState == null)
              fragmentState.mSavedFragmentState = new Bundle(); 
            putFragment(fragmentState.mSavedFragmentState, "android:target_state", fragment1.mTarget);
            if (fragment1.mTargetRequestCode != 0)
              fragmentState.mSavedFragmentState.putInt("android:target_req_state", fragment1.mTargetRequestCode); 
          } 
        } else {
          fragmentState.mSavedFragmentState = fragment1.mSavedFragmentState;
        } 
        j = b1;
        if (DEBUG) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Saved state of ");
          stringBuilder.append(fragment1);
          stringBuilder.append(": ");
          stringBuilder.append(fragmentState.mSavedFragmentState);
          Log.v("FragmentManager", stringBuilder.toString());
          j = b1;
        } 
      } 
    } 
    if (!j) {
      if (DEBUG)
        Log.v("FragmentManager", "saveAllState: no fragments!"); 
      return null;
    } 
    sparseArray = null;
    BackStackState[] arrayOfBackStackState2 = null;
    j = this.mAdded.size();
    if (j > 0) {
      int[] arrayOfInt = new int[j];
      b = 0;
      while (true) {
        int[] arrayOfInt1 = arrayOfInt;
        if (b < j) {
          arrayOfInt[b] = ((Fragment)this.mAdded.get(b)).mIndex;
          if (arrayOfInt[b] < 0) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Failure saving state: active ");
            stringBuilder1.append(this.mAdded.get(b));
            stringBuilder1.append(" has cleared index: ");
            stringBuilder1.append(arrayOfInt[b]);
            throwException(new IllegalStateException(stringBuilder1.toString()));
          } 
          if (DEBUG) {
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("saveAllState: adding fragment #");
            stringBuilder1.append(b);
            stringBuilder1.append(": ");
            stringBuilder1.append(this.mAdded.get(b));
            Log.v("FragmentManager", stringBuilder1.toString());
          } 
          b++;
          continue;
        } 
        break;
      } 
    } 
    ArrayList<BackStackRecord> arrayList = this.mBackStack;
    BackStackState[] arrayOfBackStackState1 = arrayOfBackStackState2;
    if (arrayList != null) {
      j = arrayList.size();
      arrayOfBackStackState1 = arrayOfBackStackState2;
      if (j > 0) {
        arrayOfBackStackState2 = new BackStackState[j];
        b = 0;
        while (true) {
          arrayOfBackStackState1 = arrayOfBackStackState2;
          if (b < j) {
            arrayOfBackStackState2[b] = new BackStackState(this, this.mBackStack.get(b));
            if (DEBUG) {
              stringBuilder2 = new StringBuilder();
              stringBuilder2.append("saveAllState: adding back stack #");
              stringBuilder2.append(b);
              stringBuilder2.append(": ");
              stringBuilder2.append(this.mBackStack.get(b));
              Log.v("FragmentManager", stringBuilder2.toString());
            } 
            b++;
            continue;
          } 
          break;
        } 
      } 
    } 
    FragmentManagerState fragmentManagerState = new FragmentManagerState();
    fragmentManagerState.mActive = arrayOfFragmentState;
    fragmentManagerState.mAdded = (int[])stringBuilder1;
    fragmentManagerState.mBackStack = (BackStackState[])stringBuilder2;
    fragmentManagerState.mNextFragmentIndex = this.mNextFragmentIndex;
    Fragment fragment = this.mPrimaryNav;
    if (fragment != null)
      fragmentManagerState.mPrimaryNavActiveIndex = fragment.mIndex; 
    saveNonConfig();
    return fragmentManagerState;
  }
  
  Bundle saveFragmentBasicState(Fragment paramFragment) {
    Bundle bundle1 = null;
    if (this.mStateBundle == null)
      this.mStateBundle = new Bundle(); 
    paramFragment.performSaveInstanceState(this.mStateBundle);
    dispatchOnFragmentSaveInstanceState(paramFragment, this.mStateBundle, false);
    if (!this.mStateBundle.isEmpty()) {
      bundle1 = this.mStateBundle;
      this.mStateBundle = null;
    } 
    if (paramFragment.mView != null)
      saveFragmentViewState(paramFragment); 
    Bundle bundle2 = bundle1;
    if (paramFragment.mSavedViewState != null) {
      bundle2 = bundle1;
      if (bundle1 == null)
        bundle2 = new Bundle(); 
      bundle2.putSparseParcelableArray("android:view_state", paramFragment.mSavedViewState);
    } 
    bundle1 = bundle2;
    if (!paramFragment.mUserVisibleHint) {
      bundle1 = bundle2;
      if (bundle2 == null)
        bundle1 = new Bundle(); 
      bundle1.putBoolean("android:user_visible_hint", paramFragment.mUserVisibleHint);
    } 
    return bundle1;
  }
  
  public Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment) {
    if (paramFragment.mIndex < 0) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Fragment ");
      stringBuilder.append(paramFragment);
      stringBuilder.append(" is not currently in the FragmentManager");
      throwException(new IllegalStateException(stringBuilder.toString()));
    } 
    int i = paramFragment.mState;
    Fragment fragment = null;
    if (i > 0) {
      Fragment.SavedState savedState;
      Bundle bundle = saveFragmentBasicState(paramFragment);
      paramFragment = fragment;
      if (bundle != null)
        savedState = new Fragment.SavedState(bundle); 
      return savedState;
    } 
    return null;
  }
  
  void saveFragmentViewState(Fragment paramFragment) {
    if (paramFragment.mView == null)
      return; 
    SparseArray<Parcelable> sparseArray = this.mStateArray;
    if (sparseArray == null) {
      this.mStateArray = new SparseArray();
    } else {
      sparseArray.clear();
    } 
    paramFragment.mView.saveHierarchyState(this.mStateArray);
    if (this.mStateArray.size() > 0) {
      paramFragment.mSavedViewState = this.mStateArray;
      this.mStateArray = null;
    } 
  }
  
  void saveNonConfig() {
    ArrayList<Fragment> arrayList1 = null;
    ArrayList<Fragment> arrayList2 = null;
    ArrayList<Fragment> arrayList3 = null;
    ArrayList<Fragment> arrayList4 = null;
    if (this.mActive != null) {
      byte b = 0;
      while (true) {
        arrayList1 = arrayList2;
        arrayList3 = arrayList4;
        if (b < this.mActive.size()) {
          Fragment fragment = (Fragment)this.mActive.valueAt(b);
          ArrayList<Fragment> arrayList = arrayList2;
          arrayList3 = arrayList4;
          if (fragment != null) {
            FragmentManagerNonConfig fragmentManagerNonConfig;
            arrayList1 = arrayList2;
            if (fragment.mRetainInstance) {
              byte b1;
              arrayList3 = arrayList2;
              if (arrayList2 == null)
                arrayList3 = new ArrayList(); 
              arrayList3.add(fragment);
              if (fragment.mTarget != null) {
                b1 = fragment.mTarget.mIndex;
              } else {
                b1 = -1;
              } 
              fragment.mTargetIndex = b1;
              arrayList1 = arrayList3;
              if (DEBUG) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("retainNonConfig: keeping retained ");
                stringBuilder.append(fragment);
                Log.v("FragmentManager", stringBuilder.toString());
                arrayList1 = arrayList3;
              } 
            } 
            if (fragment.mChildFragmentManager != null) {
              fragment.mChildFragmentManager.saveNonConfig();
              fragmentManagerNonConfig = fragment.mChildFragmentManager.mSavedNonConfig;
            } else {
              fragmentManagerNonConfig = ((Fragment)fragmentManagerNonConfig).mChildNonConfig;
            } 
            arrayList2 = arrayList4;
            if (arrayList4 == null) {
              arrayList2 = arrayList4;
              if (fragmentManagerNonConfig != null) {
                arrayList4 = new ArrayList<>(this.mActive.size());
                byte b1 = 0;
                while (true) {
                  arrayList2 = arrayList4;
                  if (b1 < b) {
                    arrayList4.add(null);
                    b1++;
                    continue;
                  } 
                  break;
                } 
              } 
            } 
            arrayList = arrayList1;
            arrayList3 = arrayList2;
            if (arrayList2 != null) {
              arrayList2.add(fragmentManagerNonConfig);
              arrayList3 = arrayList2;
              arrayList = arrayList1;
            } 
          } 
          b++;
          arrayList2 = arrayList;
          arrayList4 = arrayList3;
          continue;
        } 
        break;
      } 
    } 
    if (arrayList1 == null && arrayList3 == null) {
      this.mSavedNonConfig = null;
    } else {
      this.mSavedNonConfig = new FragmentManagerNonConfig(arrayList1, (List)arrayList3);
    } 
  }
  
  public void setBackStackIndex(int paramInt, BackStackRecord paramBackStackRecord) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   6: ifnonnull -> 22
    //   9: new java/util/ArrayList
    //   12: astore_3
    //   13: aload_3
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: aload_3
    //   19: putfield mBackStackIndices : Ljava/util/ArrayList;
    //   22: aload_0
    //   23: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   26: invokevirtual size : ()I
    //   29: istore #4
    //   31: iload #4
    //   33: istore #5
    //   35: iload_1
    //   36: iload #4
    //   38: if_icmpge -> 106
    //   41: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   44: ifeq -> 93
    //   47: new java/lang/StringBuilder
    //   50: astore_3
    //   51: aload_3
    //   52: invokespecial <init> : ()V
    //   55: aload_3
    //   56: ldc_w 'Setting back stack index '
    //   59: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: aload_3
    //   64: iload_1
    //   65: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: aload_3
    //   70: ldc_w ' to '
    //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: aload_3
    //   78: aload_2
    //   79: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   82: pop
    //   83: ldc 'FragmentManager'
    //   85: aload_3
    //   86: invokevirtual toString : ()Ljava/lang/String;
    //   89: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   92: pop
    //   93: aload_0
    //   94: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   97: iload_1
    //   98: aload_2
    //   99: invokevirtual set : (ILjava/lang/Object;)Ljava/lang/Object;
    //   102: pop
    //   103: goto -> 260
    //   106: iload #5
    //   108: iload_1
    //   109: if_icmpge -> 199
    //   112: aload_0
    //   113: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   116: aconst_null
    //   117: invokevirtual add : (Ljava/lang/Object;)Z
    //   120: pop
    //   121: aload_0
    //   122: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   125: ifnonnull -> 141
    //   128: new java/util/ArrayList
    //   131: astore_3
    //   132: aload_3
    //   133: invokespecial <init> : ()V
    //   136: aload_0
    //   137: aload_3
    //   138: putfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   141: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   144: ifeq -> 180
    //   147: new java/lang/StringBuilder
    //   150: astore_3
    //   151: aload_3
    //   152: invokespecial <init> : ()V
    //   155: aload_3
    //   156: ldc_w 'Adding available back stack index '
    //   159: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: aload_3
    //   164: iload #5
    //   166: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: ldc 'FragmentManager'
    //   172: aload_3
    //   173: invokevirtual toString : ()Ljava/lang/String;
    //   176: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   179: pop
    //   180: aload_0
    //   181: getfield mAvailBackStackIndices : Ljava/util/ArrayList;
    //   184: iload #5
    //   186: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   189: invokevirtual add : (Ljava/lang/Object;)Z
    //   192: pop
    //   193: iinc #5, 1
    //   196: goto -> 106
    //   199: getstatic android/app/FragmentManagerImpl.DEBUG : Z
    //   202: ifeq -> 251
    //   205: new java/lang/StringBuilder
    //   208: astore_3
    //   209: aload_3
    //   210: invokespecial <init> : ()V
    //   213: aload_3
    //   214: ldc_w 'Adding back stack index '
    //   217: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: pop
    //   221: aload_3
    //   222: iload_1
    //   223: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   226: pop
    //   227: aload_3
    //   228: ldc_w ' with '
    //   231: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: pop
    //   235: aload_3
    //   236: aload_2
    //   237: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   240: pop
    //   241: ldc 'FragmentManager'
    //   243: aload_3
    //   244: invokevirtual toString : ()Ljava/lang/String;
    //   247: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   250: pop
    //   251: aload_0
    //   252: getfield mBackStackIndices : Ljava/util/ArrayList;
    //   255: aload_2
    //   256: invokevirtual add : (Ljava/lang/Object;)Z
    //   259: pop
    //   260: aload_0
    //   261: monitorexit
    //   262: return
    //   263: astore_2
    //   264: aload_0
    //   265: monitorexit
    //   266: aload_2
    //   267: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	263	finally
    //   22	31	263	finally
    //   41	93	263	finally
    //   93	103	263	finally
    //   112	141	263	finally
    //   141	180	263	finally
    //   180	193	263	finally
    //   199	251	263	finally
    //   251	260	263	finally
    //   260	262	263	finally
    //   264	266	263	finally
  }
  
  public void setPrimaryNavigationFragment(Fragment paramFragment) {
    if (paramFragment == null || (this.mActive.get(paramFragment.mIndex) == paramFragment && (paramFragment.mHost == null || paramFragment.getFragmentManager() == this))) {
      this.mPrimaryNav = paramFragment;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Fragment ");
    stringBuilder.append(paramFragment);
    stringBuilder.append(" is not an active fragment of FragmentManager ");
    stringBuilder.append(this);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void showFragment(Fragment paramFragment) {
    if (DEBUG) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("show: ");
      stringBuilder.append(paramFragment);
      Log.v("FragmentManager", stringBuilder.toString());
    } 
    if (paramFragment.mHidden) {
      paramFragment.mHidden = false;
      paramFragment.mHiddenChanged ^= 0x1;
    } 
  }
  
  void startPendingDeferredFragments() {
    if (this.mActive == null)
      return; 
    for (byte b = 0; b < this.mActive.size(); b++) {
      Fragment fragment = (Fragment)this.mActive.valueAt(b);
      if (fragment != null)
        performPendingDeferredStart(fragment); 
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("FragmentManager{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" in ");
    Fragment fragment = this.mParent;
    if (fragment != null) {
      DebugUtils.buildShortClassTag(fragment, stringBuilder);
    } else {
      DebugUtils.buildShortClassTag(this.mHost, stringBuilder);
    } 
    stringBuilder.append("}}");
    return stringBuilder.toString();
  }
  
  public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: iconst_0
    //   8: istore_3
    //   9: aload_0
    //   10: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   13: invokevirtual size : ()I
    //   16: istore #4
    //   18: iload_3
    //   19: iload #4
    //   21: if_icmpge -> 60
    //   24: aload_0
    //   25: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   28: iload_3
    //   29: invokevirtual get : (I)Ljava/lang/Object;
    //   32: checkcast android/util/Pair
    //   35: getfield first : Ljava/lang/Object;
    //   38: aload_1
    //   39: if_acmpne -> 54
    //   42: aload_0
    //   43: getfield mLifecycleCallbacks : Ljava/util/concurrent/CopyOnWriteArrayList;
    //   46: iload_3
    //   47: invokevirtual remove : (I)Ljava/lang/Object;
    //   50: pop
    //   51: goto -> 60
    //   54: iinc #3, 1
    //   57: goto -> 18
    //   60: aload_2
    //   61: monitorexit
    //   62: return
    //   63: astore_1
    //   64: aload_2
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    // Exception table:
    //   from	to	target	type
    //   9	18	63	finally
    //   24	51	63	finally
    //   60	62	63	finally
    //   64	66	63	finally
  }
  
  static class AnimateOnHWLayerIfNeededListener implements Animator.AnimatorListener {
    private boolean mShouldRunOnHWLayer = false;
    
    private View mView;
    
    public AnimateOnHWLayerIfNeededListener(View param1View) {
      if (param1View == null)
        return; 
      this.mView = param1View;
    }
    
    public void onAnimationCancel(Animator param1Animator) {}
    
    public void onAnimationEnd(Animator param1Animator) {
      if (this.mShouldRunOnHWLayer)
        this.mView.setLayerType(0, null); 
      this.mView = null;
      param1Animator.removeListener(this);
    }
    
    public void onAnimationRepeat(Animator param1Animator) {}
    
    public void onAnimationStart(Animator param1Animator) {
      boolean bool = FragmentManagerImpl.shouldRunOnHWLayer(this.mView, param1Animator);
      this.mShouldRunOnHWLayer = bool;
      if (bool)
        this.mView.setLayerType(2, null); 
    }
  }
  
  static interface OpGenerator {
    boolean generateOps(ArrayList<BackStackRecord> param1ArrayList, ArrayList<Boolean> param1ArrayList1);
  }
  
  private class PopBackStackState implements OpGenerator {
    final int mFlags;
    
    final int mId;
    
    final String mName;
    
    public PopBackStackState(String param1String, int param1Int1, int param1Int2) {
      this.mName = param1String;
      this.mId = param1Int1;
      this.mFlags = param1Int2;
    }
    
    public boolean generateOps(ArrayList<BackStackRecord> param1ArrayList, ArrayList<Boolean> param1ArrayList1) {
      if (FragmentManagerImpl.this.mPrimaryNav != null && this.mId < 0 && this.mName == null) {
        FragmentManagerImpl fragmentManagerImpl = FragmentManagerImpl.this.mPrimaryNav.mChildFragmentManager;
        if (fragmentManagerImpl != null && fragmentManagerImpl.popBackStackImmediate())
          return false; 
      } 
      return FragmentManagerImpl.this.popBackStackState(param1ArrayList, param1ArrayList1, this.mName, this.mId, this.mFlags);
    }
  }
  
  static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
    private final boolean mIsBack;
    
    private int mNumPostponed;
    
    private final BackStackRecord mRecord;
    
    public StartEnterTransitionListener(BackStackRecord param1BackStackRecord, boolean param1Boolean) {
      this.mIsBack = param1Boolean;
      this.mRecord = param1BackStackRecord;
    }
    
    public void cancelTransaction() {
      this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, false, false);
    }
    
    public void completeTransaction() {
      int i = this.mNumPostponed;
      boolean bool = false;
      if (i > 0) {
        i = 1;
      } else {
        i = 0;
      } 
      FragmentManagerImpl fragmentManagerImpl = this.mRecord.mManager;
      int j = fragmentManagerImpl.mAdded.size();
      for (byte b = 0; b < j; b++) {
        Fragment fragment = fragmentManagerImpl.mAdded.get(b);
        fragment.setOnStartEnterTransitionListener(null);
        if (i != 0 && fragment.isPostponed())
          fragment.startPostponedEnterTransition(); 
      } 
      fragmentManagerImpl = this.mRecord.mManager;
      BackStackRecord backStackRecord = this.mRecord;
      boolean bool1 = this.mIsBack;
      if (i == 0)
        bool = true; 
      fragmentManagerImpl.completeExecute(backStackRecord, bool1, bool, true);
    }
    
    public boolean isReady() {
      boolean bool;
      if (this.mNumPostponed == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void onStartEnterTransition() {
      int i = this.mNumPostponed - 1;
      this.mNumPostponed = i;
      if (i != 0)
        return; 
      this.mRecord.mManager.scheduleCommit();
    }
    
    public void startListening() {
      this.mNumPostponed++;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */