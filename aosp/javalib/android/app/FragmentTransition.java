package android.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.view.OneShotPreDrawListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class FragmentTransition {
  private static final int[] INVERSE_OPS = new int[] { 0, 3, 0, 1, 5, 4, 7, 6, 9, 8 };
  
  private static void addSharedElementsWithMatchingNames(ArrayList<View> paramArrayList, ArrayMap<String, View> paramArrayMap, Collection<String> paramCollection) {
    for (int i = paramArrayMap.size() - 1; i >= 0; i--) {
      View view = (View)paramArrayMap.valueAt(i);
      if (view != null && paramCollection.contains(view.getTransitionName()))
        paramArrayList.add(view); 
    } 
  }
  
  public static void addTargets(Transition paramTransition, ArrayList<View> paramArrayList) {
    TransitionSet transitionSet;
    if (paramTransition == null)
      return; 
    if (paramTransition instanceof TransitionSet) {
      transitionSet = (TransitionSet)paramTransition;
      int i = transitionSet.getTransitionCount();
      for (byte b = 0; b < i; b++)
        addTargets(transitionSet.getTransitionAt(b), paramArrayList); 
    } else if (!hasSimpleTarget((Transition)transitionSet) && isNullOrEmpty(transitionSet.getTargets())) {
      int i = paramArrayList.size();
      for (byte b = 0; b < i; b++)
        transitionSet.addTarget(paramArrayList.get(b)); 
    } 
  }
  
  private static void addToFirstInLastOut(BackStackRecord paramBackStackRecord, BackStackRecord.Op paramOp, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: aload_1
    //   1: getfield fragment : Landroid/app/Fragment;
    //   4: astore #5
    //   6: aload #5
    //   8: ifnonnull -> 12
    //   11: return
    //   12: aload #5
    //   14: getfield mContainerId : I
    //   17: istore #6
    //   19: iload #6
    //   21: ifne -> 25
    //   24: return
    //   25: iload_3
    //   26: ifeq -> 42
    //   29: getstatic android/app/FragmentTransition.INVERSE_OPS : [I
    //   32: aload_1
    //   33: getfield cmd : I
    //   36: iaload
    //   37: istore #7
    //   39: goto -> 48
    //   42: aload_1
    //   43: getfield cmd : I
    //   46: istore #7
    //   48: iconst_0
    //   49: istore #8
    //   51: iconst_0
    //   52: istore #9
    //   54: iconst_0
    //   55: istore #10
    //   57: iconst_0
    //   58: istore #11
    //   60: iconst_0
    //   61: istore #12
    //   63: iconst_0
    //   64: istore #13
    //   66: iload #7
    //   68: iconst_1
    //   69: if_icmpeq -> 384
    //   72: iload #7
    //   74: iconst_3
    //   75: if_icmpeq -> 279
    //   78: iload #7
    //   80: iconst_4
    //   81: if_icmpeq -> 185
    //   84: iload #7
    //   86: iconst_5
    //   87: if_icmpeq -> 119
    //   90: iload #7
    //   92: bipush #6
    //   94: if_icmpeq -> 279
    //   97: iload #7
    //   99: bipush #7
    //   101: if_icmpeq -> 384
    //   104: iconst_0
    //   105: istore #14
    //   107: iconst_0
    //   108: istore #7
    //   110: iconst_0
    //   111: istore #8
    //   113: iconst_0
    //   114: istore #9
    //   116: goto -> 435
    //   119: iload #4
    //   121: ifeq -> 166
    //   124: iload #13
    //   126: istore #14
    //   128: aload #5
    //   130: getfield mHiddenChanged : Z
    //   133: ifeq -> 163
    //   136: iload #13
    //   138: istore #14
    //   140: aload #5
    //   142: getfield mHidden : Z
    //   145: ifne -> 163
    //   148: iload #13
    //   150: istore #14
    //   152: aload #5
    //   154: getfield mAdded : Z
    //   157: ifeq -> 163
    //   160: iconst_1
    //   161: istore #14
    //   163: goto -> 173
    //   166: aload #5
    //   168: getfield mHidden : Z
    //   171: istore #14
    //   173: iconst_0
    //   174: istore #7
    //   176: iconst_0
    //   177: istore #8
    //   179: iconst_1
    //   180: istore #9
    //   182: goto -> 435
    //   185: iload #4
    //   187: ifeq -> 232
    //   190: iload #8
    //   192: istore #7
    //   194: aload #5
    //   196: getfield mHiddenChanged : Z
    //   199: ifeq -> 229
    //   202: iload #8
    //   204: istore #7
    //   206: aload #5
    //   208: getfield mAdded : Z
    //   211: ifeq -> 229
    //   214: iload #8
    //   216: istore #7
    //   218: aload #5
    //   220: getfield mHidden : Z
    //   223: ifeq -> 229
    //   226: iconst_1
    //   227: istore #7
    //   229: goto -> 259
    //   232: iload #9
    //   234: istore #7
    //   236: aload #5
    //   238: getfield mAdded : Z
    //   241: ifeq -> 259
    //   244: iload #9
    //   246: istore #7
    //   248: aload #5
    //   250: getfield mHidden : Z
    //   253: ifne -> 259
    //   256: iconst_1
    //   257: istore #7
    //   259: iconst_0
    //   260: istore #14
    //   262: iconst_1
    //   263: istore #11
    //   265: iload #7
    //   267: istore #8
    //   269: iconst_0
    //   270: istore #9
    //   272: iload #11
    //   274: istore #7
    //   276: goto -> 435
    //   279: iload #4
    //   281: ifeq -> 337
    //   284: aload #5
    //   286: getfield mAdded : Z
    //   289: ifne -> 330
    //   292: aload #5
    //   294: getfield mView : Landroid/view/View;
    //   297: ifnull -> 330
    //   300: aload #5
    //   302: getfield mView : Landroid/view/View;
    //   305: invokevirtual getVisibility : ()I
    //   308: ifne -> 330
    //   311: aload #5
    //   313: getfield mView : Landroid/view/View;
    //   316: invokevirtual getTransitionAlpha : ()F
    //   319: fconst_0
    //   320: fcmpl
    //   321: ifle -> 330
    //   324: iconst_1
    //   325: istore #7
    //   327: goto -> 334
    //   330: iload #10
    //   332: istore #7
    //   334: goto -> 364
    //   337: iload #11
    //   339: istore #7
    //   341: aload #5
    //   343: getfield mAdded : Z
    //   346: ifeq -> 364
    //   349: iload #11
    //   351: istore #7
    //   353: aload #5
    //   355: getfield mHidden : Z
    //   358: ifne -> 364
    //   361: iconst_1
    //   362: istore #7
    //   364: iconst_0
    //   365: istore #14
    //   367: iconst_1
    //   368: istore #11
    //   370: iload #7
    //   372: istore #8
    //   374: iconst_0
    //   375: istore #9
    //   377: iload #11
    //   379: istore #7
    //   381: goto -> 435
    //   384: iload #4
    //   386: ifeq -> 399
    //   389: aload #5
    //   391: getfield mIsNewlyAdded : Z
    //   394: istore #14
    //   396: goto -> 426
    //   399: iload #12
    //   401: istore #14
    //   403: aload #5
    //   405: getfield mAdded : Z
    //   408: ifne -> 426
    //   411: iload #12
    //   413: istore #14
    //   415: aload #5
    //   417: getfield mHidden : Z
    //   420: ifne -> 426
    //   423: iconst_1
    //   424: istore #14
    //   426: iconst_0
    //   427: istore #7
    //   429: iconst_0
    //   430: istore #8
    //   432: iconst_1
    //   433: istore #9
    //   435: aload_2
    //   436: iload #6
    //   438: invokevirtual get : (I)Ljava/lang/Object;
    //   441: checkcast android/app/FragmentTransition$FragmentContainerTransition
    //   444: astore_1
    //   445: iload #14
    //   447: ifeq -> 477
    //   450: aload_1
    //   451: aload_2
    //   452: iload #6
    //   454: invokestatic ensureContainer : (Landroid/app/FragmentTransition$FragmentContainerTransition;Landroid/util/SparseArray;I)Landroid/app/FragmentTransition$FragmentContainerTransition;
    //   457: astore_1
    //   458: aload_1
    //   459: aload #5
    //   461: putfield lastIn : Landroid/app/Fragment;
    //   464: aload_1
    //   465: iload_3
    //   466: putfield lastInIsPop : Z
    //   469: aload_1
    //   470: aload_0
    //   471: putfield lastInTransaction : Landroid/app/BackStackRecord;
    //   474: goto -> 477
    //   477: iload #4
    //   479: ifne -> 582
    //   482: iload #9
    //   484: ifeq -> 582
    //   487: aload_1
    //   488: ifnull -> 505
    //   491: aload_1
    //   492: getfield firstOut : Landroid/app/Fragment;
    //   495: aload #5
    //   497: if_acmpne -> 505
    //   500: aload_1
    //   501: aconst_null
    //   502: putfield firstOut : Landroid/app/Fragment;
    //   505: aload_0
    //   506: getfield mManager : Landroid/app/FragmentManagerImpl;
    //   509: astore #15
    //   511: aload #5
    //   513: getfield mState : I
    //   516: iconst_1
    //   517: if_icmpge -> 579
    //   520: aload #15
    //   522: getfield mCurState : I
    //   525: iconst_1
    //   526: if_icmplt -> 579
    //   529: aload #15
    //   531: getfield mHost : Landroid/app/FragmentHostCallback;
    //   534: invokevirtual getContext : ()Landroid/content/Context;
    //   537: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   540: getfield targetSdkVersion : I
    //   543: bipush #24
    //   545: if_icmplt -> 576
    //   548: aload_0
    //   549: getfield mReorderingAllowed : Z
    //   552: ifne -> 576
    //   555: aload #15
    //   557: aload #5
    //   559: invokevirtual makeActive : (Landroid/app/Fragment;)V
    //   562: aload #15
    //   564: aload #5
    //   566: iconst_1
    //   567: iconst_0
    //   568: iconst_0
    //   569: iconst_0
    //   570: invokevirtual moveToState : (Landroid/app/Fragment;IIIZ)V
    //   573: goto -> 582
    //   576: goto -> 582
    //   579: goto -> 582
    //   582: iload #8
    //   584: ifeq -> 631
    //   587: aload_1
    //   588: astore #15
    //   590: aload #15
    //   592: ifnull -> 603
    //   595: aload #15
    //   597: getfield firstOut : Landroid/app/Fragment;
    //   600: ifnonnull -> 631
    //   603: aload #15
    //   605: aload_2
    //   606: iload #6
    //   608: invokestatic ensureContainer : (Landroid/app/FragmentTransition$FragmentContainerTransition;Landroid/util/SparseArray;I)Landroid/app/FragmentTransition$FragmentContainerTransition;
    //   611: astore_1
    //   612: aload_1
    //   613: aload #5
    //   615: putfield firstOut : Landroid/app/Fragment;
    //   618: aload_1
    //   619: iload_3
    //   620: putfield firstOutIsPop : Z
    //   623: aload_1
    //   624: aload_0
    //   625: putfield firstOutTransaction : Landroid/app/BackStackRecord;
    //   628: goto -> 631
    //   631: iload #4
    //   633: ifne -> 659
    //   636: iload #7
    //   638: ifeq -> 659
    //   641: aload_1
    //   642: ifnull -> 659
    //   645: aload_1
    //   646: getfield lastIn : Landroid/app/Fragment;
    //   649: aload #5
    //   651: if_acmpne -> 659
    //   654: aload_1
    //   655: aconst_null
    //   656: putfield lastIn : Landroid/app/Fragment;
    //   659: return
  }
  
  private static void bfsAddViewChildren(List<View> paramList, View paramView) {
    int i = paramList.size();
    if (containedBeforeIndex(paramList, paramView, i))
      return; 
    paramList.add(paramView);
    for (int j = i; j < paramList.size(); j++) {
      paramView = paramList.get(j);
      if (paramView instanceof ViewGroup) {
        ViewGroup viewGroup = (ViewGroup)paramView;
        int k = viewGroup.getChildCount();
        for (byte b = 0; b < k; b++) {
          paramView = viewGroup.getChildAt(b);
          if (!containedBeforeIndex(paramList, paramView, i))
            paramList.add(paramView); 
        } 
      } 
    } 
  }
  
  public static void calculateFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean) {
    int i = paramBackStackRecord.mOps.size();
    for (byte b = 0; b < i; b++)
      addToFirstInLastOut(paramBackStackRecord, paramBackStackRecord.mOps.get(b), paramSparseArray, false, paramBoolean); 
  }
  
  private static ArrayMap<String, String> calculateNameOverrides(int paramInt1, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt2, int paramInt3) {
    ArrayMap<String, String> arrayMap = new ArrayMap();
    while (--paramInt3 >= paramInt2) {
      BackStackRecord backStackRecord = paramArrayList.get(paramInt3);
      if (backStackRecord.interactsWith(paramInt1)) {
        boolean bool = ((Boolean)paramArrayList1.get(paramInt3)).booleanValue();
        if (backStackRecord.mSharedElementSourceNames != null) {
          ArrayList<String> arrayList1;
          ArrayList<String> arrayList2;
          int i = backStackRecord.mSharedElementSourceNames.size();
          if (bool) {
            arrayList1 = backStackRecord.mSharedElementSourceNames;
            arrayList2 = backStackRecord.mSharedElementTargetNames;
          } else {
            arrayList2 = backStackRecord.mSharedElementSourceNames;
            arrayList1 = backStackRecord.mSharedElementTargetNames;
          } 
          for (byte b = 0; b < i; b++) {
            String str2 = arrayList2.get(b);
            String str3 = arrayList1.get(b);
            String str1 = (String)arrayMap.remove(str3);
            if (str1 != null) {
              arrayMap.put(str2, str1);
            } else {
              arrayMap.put(str2, str3);
            } 
          } 
        } 
      } 
      paramInt3--;
    } 
    return arrayMap;
  }
  
  public static void calculatePopFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean) {
    if (!paramBackStackRecord.mManager.mContainer.onHasView())
      return; 
    for (int i = paramBackStackRecord.mOps.size() - 1; i >= 0; i--)
      addToFirstInLastOut(paramBackStackRecord, paramBackStackRecord.mOps.get(i), paramSparseArray, true, paramBoolean); 
  }
  
  private static void callSharedElementStartEnd(Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean1, ArrayMap<String, View> paramArrayMap, boolean paramBoolean2) {
    SharedElementCallback sharedElementCallback;
    if (paramBoolean1) {
      sharedElementCallback = paramFragment2.getEnterTransitionCallback();
    } else {
      sharedElementCallback = sharedElementCallback.getEnterTransitionCallback();
    } 
    if (sharedElementCallback != null) {
      int i;
      ArrayList<View> arrayList1 = new ArrayList();
      ArrayList<String> arrayList = new ArrayList();
      if (paramArrayMap == null) {
        i = 0;
      } else {
        i = paramArrayMap.size();
      } 
      for (byte b = 0; b < i; b++) {
        arrayList.add((String)paramArrayMap.keyAt(b));
        arrayList1.add((View)paramArrayMap.valueAt(b));
      } 
      if (paramBoolean2) {
        sharedElementCallback.onSharedElementStart(arrayList, arrayList1, null);
      } else {
        sharedElementCallback.onSharedElementEnd(arrayList, arrayList1, null);
      } 
    } 
  }
  
  private static ArrayMap<String, View> captureInSharedElements(ArrayMap<String, String> paramArrayMap, TransitionSet paramTransitionSet, FragmentContainerTransition paramFragmentContainerTransition) {
    ArrayList<String> arrayList;
    SharedElementCallback sharedElementCallback;
    Fragment fragment = paramFragmentContainerTransition.lastIn;
    View view = fragment.getView();
    if (paramArrayMap.isEmpty() || paramTransitionSet == null || view == null) {
      paramArrayMap.clear();
      return null;
    } 
    ArrayMap<String, View> arrayMap = new ArrayMap();
    view.findNamedViews((Map)arrayMap);
    BackStackRecord backStackRecord = paramFragmentContainerTransition.lastInTransaction;
    if (paramFragmentContainerTransition.lastInIsPop) {
      sharedElementCallback = fragment.getExitTransitionCallback();
      arrayList = backStackRecord.mSharedElementSourceNames;
    } else {
      sharedElementCallback = fragment.getEnterTransitionCallback();
      arrayList = ((BackStackRecord)arrayList).mSharedElementTargetNames;
    } 
    if (arrayList != null)
      arrayMap.retainAll(arrayList); 
    if (arrayList != null && sharedElementCallback != null) {
      sharedElementCallback.onMapSharedElements(arrayList, (Map<String, View>)arrayMap);
      for (int i = arrayList.size() - 1; i >= 0; i--) {
        String str1;
        String str2 = arrayList.get(i);
        View view1 = (View)arrayMap.get(str2);
        if (view1 == null) {
          str1 = findKeyForValue(paramArrayMap, str2);
          if (str1 != null)
            paramArrayMap.remove(str1); 
        } else if (!str2.equals(str1.getTransitionName())) {
          str2 = findKeyForValue(paramArrayMap, str2);
          if (str2 != null)
            paramArrayMap.put(str2, str1.getTransitionName()); 
        } 
      } 
    } else {
      retainValues(paramArrayMap, arrayMap);
    } 
    return arrayMap;
  }
  
  private static ArrayMap<String, View> captureOutSharedElements(ArrayMap<String, String> paramArrayMap, TransitionSet paramTransitionSet, FragmentContainerTransition paramFragmentContainerTransition) {
    ArrayList<String> arrayList;
    SharedElementCallback sharedElementCallback;
    if (paramArrayMap.isEmpty() || paramTransitionSet == null) {
      paramArrayMap.clear();
      return null;
    } 
    Fragment fragment = paramFragmentContainerTransition.firstOut;
    ArrayMap<String, View> arrayMap = new ArrayMap();
    fragment.getView().findNamedViews((Map)arrayMap);
    BackStackRecord backStackRecord = paramFragmentContainerTransition.firstOutTransaction;
    if (paramFragmentContainerTransition.firstOutIsPop) {
      sharedElementCallback = fragment.getEnterTransitionCallback();
      arrayList = backStackRecord.mSharedElementTargetNames;
    } else {
      sharedElementCallback = fragment.getExitTransitionCallback();
      arrayList = ((BackStackRecord)arrayList).mSharedElementSourceNames;
    } 
    arrayMap.retainAll(arrayList);
    if (sharedElementCallback != null) {
      sharedElementCallback.onMapSharedElements(arrayList, (Map<String, View>)arrayMap);
      for (int i = arrayList.size() - 1; i >= 0; i--) {
        String str = arrayList.get(i);
        View view = (View)arrayMap.get(str);
        if (view == null) {
          paramArrayMap.remove(str);
        } else if (!str.equals(view.getTransitionName())) {
          str = (String)paramArrayMap.remove(str);
          paramArrayMap.put(view.getTransitionName(), str);
        } 
      } 
    } else {
      paramArrayMap.retainAll(arrayMap.keySet());
    } 
    return arrayMap;
  }
  
  private static Transition cloneTransition(Transition paramTransition) {
    Transition transition = paramTransition;
    if (paramTransition != null)
      transition = paramTransition.clone(); 
    return transition;
  }
  
  private static ArrayList<View> configureEnteringExitingViews(Transition paramTransition, Fragment paramFragment, ArrayList<View> paramArrayList, View paramView) {
    ArrayList<View> arrayList = null;
    if (paramTransition != null) {
      ArrayList<View> arrayList1 = new ArrayList();
      View view = paramFragment.getView();
      if (view != null)
        view.captureTransitioningViews(arrayList1); 
      if (paramArrayList != null)
        arrayList1.removeAll(paramArrayList); 
      arrayList = arrayList1;
      if (!arrayList1.isEmpty()) {
        arrayList1.add(paramView);
        addTargets(paramTransition, arrayList1);
        arrayList = arrayList1;
      } 
    } 
    return arrayList;
  }
  
  private static TransitionSet configureSharedElementsOrdered(ViewGroup paramViewGroup, View paramView, ArrayMap<String, String> paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2, Transition paramTransition1, Transition paramTransition2) {
    TransitionSet transitionSet;
    Fragment fragment1 = paramFragmentContainerTransition.lastIn;
    Fragment fragment2 = paramFragmentContainerTransition.firstOut;
    if (fragment1 == null || fragment2 == null)
      return null; 
    boolean bool = paramFragmentContainerTransition.lastInIsPop;
    if (paramArrayMap.isEmpty()) {
      transitionSet = null;
    } else {
      transitionSet = getSharedElementTransition(fragment1, fragment2, bool);
    } 
    ArrayMap<String, View> arrayMap = captureOutSharedElements(paramArrayMap, transitionSet, paramFragmentContainerTransition);
    if (paramArrayMap.isEmpty()) {
      transitionSet = null;
    } else {
      paramArrayList1.addAll(arrayMap.values());
    } 
    if (paramTransition1 == null && paramTransition2 == null && transitionSet == null)
      return null; 
    callSharedElementStartEnd(fragment1, fragment2, bool, arrayMap, true);
    if (transitionSet != null) {
      final Rect inEpicenter = new Rect();
      setSharedElementTargets(transitionSet, paramView, paramArrayList1);
      setOutEpicenter(transitionSet, paramTransition2, arrayMap, paramFragmentContainerTransition.firstOutIsPop, paramFragmentContainerTransition.firstOutTransaction);
      if (paramTransition1 != null)
        paramTransition1.setEpicenterCallback(new Transition.EpicenterCallback() {
              public Rect onGetEpicenter(Transition param1Transition) {
                return inEpicenter.isEmpty() ? null : inEpicenter;
              }
            }); 
      Rect rect1 = rect2;
    } else {
      paramTransition2 = null;
    } 
    OneShotPreDrawListener.add((View)paramViewGroup, new _$$Lambda$FragmentTransition$Ip0LktADPhG_3ouNBXgzufWpFfY(paramArrayMap, transitionSet, paramFragmentContainerTransition, paramArrayList2, paramView, fragment1, fragment2, bool, paramArrayList1, paramTransition1, (Rect)paramTransition2));
    return transitionSet;
  }
  
  private static TransitionSet configureSharedElementsReordered(ViewGroup paramViewGroup, View paramView, ArrayMap<String, String> paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2, Transition paramTransition1, Transition paramTransition2) {
    TransitionSet transitionSet1;
    TransitionSet transitionSet2;
    Fragment fragment1 = paramFragmentContainerTransition.lastIn;
    Fragment fragment2 = paramFragmentContainerTransition.firstOut;
    if (fragment1 != null)
      fragment1.getView().setVisibility(0); 
    if (fragment1 == null || fragment2 == null)
      return null; 
    boolean bool = paramFragmentContainerTransition.lastInIsPop;
    if (paramArrayMap.isEmpty()) {
      transitionSet2 = null;
    } else {
      transitionSet2 = getSharedElementTransition(fragment1, fragment2, bool);
    } 
    ArrayMap<String, View> arrayMap1 = captureOutSharedElements(paramArrayMap, transitionSet2, paramFragmentContainerTransition);
    ArrayMap<String, View> arrayMap2 = captureInSharedElements(paramArrayMap, transitionSet2, paramFragmentContainerTransition);
    if (paramArrayMap.isEmpty()) {
      if (arrayMap1 != null)
        arrayMap1.clear(); 
      if (arrayMap2 != null)
        arrayMap2.clear(); 
      paramArrayMap = null;
    } else {
      addSharedElementsWithMatchingNames(paramArrayList1, arrayMap1, paramArrayMap.keySet());
      addSharedElementsWithMatchingNames(paramArrayList2, arrayMap2, paramArrayMap.values());
      transitionSet1 = transitionSet2;
    } 
    if (paramTransition1 == null && paramTransition2 == null && transitionSet1 == null)
      return null; 
    callSharedElementStartEnd(fragment1, fragment2, bool, arrayMap1, true);
    if (transitionSet1 != null) {
      paramArrayList2.add(paramView);
      setSharedElementTargets(transitionSet1, paramView, paramArrayList1);
      setOutEpicenter(transitionSet1, paramTransition2, arrayMap1, paramFragmentContainerTransition.firstOutIsPop, paramFragmentContainerTransition.firstOutTransaction);
      final Rect epicenter = new Rect();
      View view = getInEpicenterView(arrayMap2, paramFragmentContainerTransition, paramTransition1, bool);
      if (view != null)
        paramTransition1.setEpicenterCallback(new Transition.EpicenterCallback() {
              public Rect onGetEpicenter(Transition param1Transition) {
                return epicenter;
              }
            }); 
    } else {
      paramFragmentContainerTransition = null;
      paramView = null;
    } 
    OneShotPreDrawListener.add((View)paramViewGroup, new _$$Lambda$FragmentTransition$jurn0WXuKw3bRQ_2d5zCWdeZWuI(fragment1, fragment2, bool, arrayMap2, (View)paramFragmentContainerTransition, (Rect)paramView));
    return transitionSet1;
  }
  
  private static void configureTransitionsOrdered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap) {
    if (paramFragmentManagerImpl.mContainer.onHasView()) {
      ViewGroup viewGroup = paramFragmentManagerImpl.mContainer.<ViewGroup>onFindViewById(paramInt);
    } else {
      paramFragmentManagerImpl = null;
    } 
    if (paramFragmentManagerImpl == null)
      return; 
    Fragment fragment1 = paramFragmentContainerTransition.lastIn;
    Fragment fragment2 = paramFragmentContainerTransition.firstOut;
    boolean bool1 = paramFragmentContainerTransition.lastInIsPop;
    boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
    Transition transition2 = getEnterTransition(fragment1, bool1);
    Transition transition3 = getExitTransition(fragment2, bool2);
    ArrayList<View> arrayList2 = new ArrayList();
    ArrayList<View> arrayList3 = new ArrayList();
    TransitionSet transitionSet = configureSharedElementsOrdered((ViewGroup)paramFragmentManagerImpl, paramView, paramArrayMap, paramFragmentContainerTransition, arrayList2, arrayList3, transition2, transition3);
    if (transition2 == null && transitionSet == null && transition3 == null)
      return; 
    ArrayList<View> arrayList1 = configureEnteringExitingViews(transition3, fragment2, arrayList2, paramView);
    if (arrayList1 == null || arrayList1.isEmpty())
      transition3 = null; 
    if (transition2 != null)
      transition2.addTarget(paramView); 
    Transition transition1 = mergeTransitions(transition2, transition3, (Transition)transitionSet, fragment1, paramFragmentContainerTransition.lastInIsPop);
    if (transition1 != null) {
      transition1.setNameOverrides(paramArrayMap);
      ArrayList<View> arrayList = new ArrayList();
      scheduleRemoveTargets(transition1, transition2, arrayList, transition3, arrayList1, transitionSet, arrayList3);
      scheduleTargetChange((ViewGroup)paramFragmentManagerImpl, fragment1, paramView, arrayList3, transition2, arrayList, transition3, arrayList1);
      TransitionManager.beginDelayedTransition((ViewGroup)paramFragmentManagerImpl, transition1);
    } 
  }
  
  private static void configureTransitionsReordered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap) {
    if (paramFragmentManagerImpl.mContainer.onHasView()) {
      ViewGroup viewGroup = paramFragmentManagerImpl.mContainer.<ViewGroup>onFindViewById(paramInt);
    } else {
      paramFragmentManagerImpl = null;
    } 
    if (paramFragmentManagerImpl == null)
      return; 
    Fragment fragment1 = paramFragmentContainerTransition.lastIn;
    Fragment fragment2 = paramFragmentContainerTransition.firstOut;
    boolean bool1 = paramFragmentContainerTransition.lastInIsPop;
    boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
    ArrayList<View> arrayList2 = new ArrayList();
    ArrayList<View> arrayList3 = new ArrayList();
    Transition transition3 = getEnterTransition(fragment1, bool1);
    Transition transition4 = getExitTransition(fragment2, bool2);
    TransitionSet transitionSet = configureSharedElementsReordered((ViewGroup)paramFragmentManagerImpl, paramView, paramArrayMap, paramFragmentContainerTransition, arrayList3, arrayList2, transition3, transition4);
    if (transition3 == null && transitionSet == null && transition4 == null)
      return; 
    Transition transition1 = transition4;
    ArrayList<View> arrayList4 = configureEnteringExitingViews(transition1, fragment2, arrayList3, paramView);
    ArrayList<View> arrayList1 = configureEnteringExitingViews(transition3, fragment1, arrayList2, paramView);
    setViewVisibility(arrayList1, 4);
    Transition transition2 = mergeTransitions(transition3, transition1, (Transition)transitionSet, fragment1, bool1);
    if (transition2 != null) {
      replaceHide(transition1, fragment2, arrayList4);
      transition2.setNameOverrides(paramArrayMap);
      scheduleRemoveTargets(transition2, transition3, arrayList1, transition1, arrayList4, transitionSet, arrayList2);
      TransitionManager.beginDelayedTransition((ViewGroup)paramFragmentManagerImpl, transition2);
      setViewVisibility(arrayList1, 0);
      if (transitionSet != null) {
        transitionSet.getTargets().clear();
        transitionSet.getTargets().addAll(arrayList2);
        replaceTargets((Transition)transitionSet, arrayList3, arrayList2);
      } 
    } 
  }
  
  private static boolean containedBeforeIndex(List<View> paramList, View paramView, int paramInt) {
    for (byte b = 0; b < paramInt; b++) {
      if (paramList.get(b) == paramView)
        return true; 
    } 
    return false;
  }
  
  private static FragmentContainerTransition ensureContainer(FragmentContainerTransition paramFragmentContainerTransition, SparseArray<FragmentContainerTransition> paramSparseArray, int paramInt) {
    FragmentContainerTransition fragmentContainerTransition = paramFragmentContainerTransition;
    if (paramFragmentContainerTransition == null) {
      fragmentContainerTransition = new FragmentContainerTransition();
      paramSparseArray.put(paramInt, fragmentContainerTransition);
    } 
    return fragmentContainerTransition;
  }
  
  private static String findKeyForValue(ArrayMap<String, String> paramArrayMap, String paramString) {
    int i = paramArrayMap.size();
    for (byte b = 0; b < i; b++) {
      if (paramString.equals(paramArrayMap.valueAt(b)))
        return (String)paramArrayMap.keyAt(b); 
    } 
    return null;
  }
  
  private static Transition getEnterTransition(Fragment paramFragment, boolean paramBoolean) {
    Transition transition;
    if (paramFragment == null)
      return null; 
    if (paramBoolean) {
      transition = paramFragment.getReenterTransition();
    } else {
      transition = transition.getEnterTransition();
    } 
    return cloneTransition(transition);
  }
  
  private static Transition getExitTransition(Fragment paramFragment, boolean paramBoolean) {
    Transition transition;
    if (paramFragment == null)
      return null; 
    if (paramBoolean) {
      transition = paramFragment.getReturnTransition();
    } else {
      transition = transition.getExitTransition();
    } 
    return cloneTransition(transition);
  }
  
  private static View getInEpicenterView(ArrayMap<String, View> paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, Transition paramTransition, boolean paramBoolean) {
    BackStackRecord backStackRecord = paramFragmentContainerTransition.lastInTransaction;
    if (paramTransition != null && paramArrayMap != null && backStackRecord.mSharedElementSourceNames != null && !backStackRecord.mSharedElementSourceNames.isEmpty()) {
      String str;
      if (paramBoolean) {
        str = backStackRecord.mSharedElementSourceNames.get(0);
      } else {
        str = ((BackStackRecord)str).mSharedElementTargetNames.get(0);
      } 
      return (View)paramArrayMap.get(str);
    } 
    return null;
  }
  
  private static TransitionSet getSharedElementTransition(Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean) {
    if (paramFragment1 == null || paramFragment2 == null)
      return null; 
    if (paramBoolean) {
      transition = paramFragment2.getSharedElementReturnTransition();
    } else {
      transition = transition.getSharedElementEnterTransition();
    } 
    Transition transition = cloneTransition(transition);
    if (transition == null)
      return null; 
    TransitionSet transitionSet = new TransitionSet();
    transitionSet.addTransition(transition);
    return transitionSet;
  }
  
  private static boolean hasSimpleTarget(Transition paramTransition) {
    return (!isNullOrEmpty(paramTransition.getTargetIds()) || !isNullOrEmpty(paramTransition.getTargetNames()) || !isNullOrEmpty(paramTransition.getTargetTypes()));
  }
  
  private static boolean isNullOrEmpty(List paramList) {
    return (paramList == null || paramList.isEmpty());
  }
  
  private static Transition mergeTransitions(Transition paramTransition1, Transition paramTransition2, Transition paramTransition3, Fragment paramFragment, boolean paramBoolean) {
    TransitionSet transitionSet;
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramTransition1 != null) {
      bool2 = bool1;
      if (paramTransition2 != null) {
        bool2 = bool1;
        if (paramFragment != null) {
          if (paramBoolean) {
            paramBoolean = paramFragment.getAllowReturnTransitionOverlap();
          } else {
            paramBoolean = paramFragment.getAllowEnterTransitionOverlap();
          } 
          bool2 = paramBoolean;
        } 
      } 
    } 
    if (bool2) {
      TransitionSet transitionSet1 = new TransitionSet();
      if (paramTransition1 != null)
        transitionSet1.addTransition(paramTransition1); 
      if (paramTransition2 != null)
        transitionSet1.addTransition(paramTransition2); 
      if (paramTransition3 != null)
        transitionSet1.addTransition(paramTransition3); 
      transitionSet = transitionSet1;
    } else {
      Transition transition;
      paramFragment = null;
      if (transitionSet != null && paramTransition1 != null) {
        transitionSet = (new TransitionSet()).addTransition((Transition)transitionSet).addTransition(paramTransition1).setOrdering(1);
      } else if (transitionSet == null) {
        Fragment fragment = paramFragment;
        if (paramTransition1 != null)
          transition = paramTransition1; 
      } 
      if (paramTransition3 != null) {
        TransitionSet transitionSet1 = new TransitionSet();
        if (transition != null)
          transitionSet1.addTransition(transition); 
        transitionSet1.addTransition(paramTransition3);
        transitionSet = transitionSet1;
      } 
    } 
    return (Transition)transitionSet;
  }
  
  private static void replaceHide(Transition paramTransition, Fragment paramFragment, final ArrayList<View> exitingViews) {
    if (paramFragment != null && paramTransition != null && paramFragment.mAdded && paramFragment.mHidden && paramFragment.mHiddenChanged) {
      paramFragment.setHideReplaced(true);
      final View fragmentView = paramFragment.getView();
      OneShotPreDrawListener.add((View)paramFragment.mContainer, new _$$Lambda$FragmentTransition$PZ32bJ_FSMpbzYzBl8x73NJPidQ(exitingViews));
      paramTransition.addListener((Transition.TransitionListener)new TransitionListenerAdapter() {
            public void onTransitionEnd(Transition param1Transition) {
              param1Transition.removeListener((Transition.TransitionListener)this);
              fragmentView.setVisibility(8);
              FragmentTransition.setViewVisibility(exitingViews, 0);
            }
          });
    } 
  }
  
  public static void replaceTargets(Transition paramTransition, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2) {
    TransitionSet transitionSet;
    if (paramTransition instanceof TransitionSet) {
      transitionSet = (TransitionSet)paramTransition;
      int i = transitionSet.getTransitionCount();
      for (byte b = 0; b < i; b++)
        replaceTargets(transitionSet.getTransitionAt(b), paramArrayList1, paramArrayList2); 
    } else if (!hasSimpleTarget((Transition)transitionSet)) {
      List list = transitionSet.getTargets();
      if (list != null && list.size() == paramArrayList1.size() && list.containsAll(paramArrayList1)) {
        if (paramArrayList2 == null) {
          i = 0;
        } else {
          i = paramArrayList2.size();
        } 
        for (byte b = 0; b < i; b++)
          transitionSet.addTarget(paramArrayList2.get(b)); 
        for (int i = paramArrayList1.size() - 1; i >= 0; i--)
          transitionSet.removeTarget(paramArrayList1.get(i)); 
      } 
    } 
  }
  
  private static void retainValues(ArrayMap<String, String> paramArrayMap, ArrayMap<String, View> paramArrayMap1) {
    for (int i = paramArrayMap.size() - 1; i >= 0; i--) {
      if (!paramArrayMap1.containsKey(paramArrayMap.valueAt(i)))
        paramArrayMap.removeAt(i); 
    } 
  }
  
  private static void scheduleRemoveTargets(Transition paramTransition1, final Transition enterTransition, final ArrayList<View> enteringViews, final Transition exitTransition, final ArrayList<View> exitingViews, final TransitionSet sharedElementTransition, final ArrayList<View> sharedElementsIn) {
    paramTransition1.addListener((Transition.TransitionListener)new TransitionListenerAdapter() {
          public void onTransitionEnd(Transition param1Transition) {
            param1Transition.removeListener((Transition.TransitionListener)this);
          }
          
          public void onTransitionStart(Transition param1Transition) {
            param1Transition = enterTransition;
            if (param1Transition != null)
              FragmentTransition.replaceTargets(param1Transition, enteringViews, null); 
            param1Transition = exitTransition;
            if (param1Transition != null)
              FragmentTransition.replaceTargets(param1Transition, exitingViews, null); 
            TransitionSet transitionSet = sharedElementTransition;
            if (transitionSet != null)
              FragmentTransition.replaceTargets((Transition)transitionSet, sharedElementsIn, null); 
          }
        });
  }
  
  private static void scheduleTargetChange(ViewGroup paramViewGroup, Fragment paramFragment, View paramView, ArrayList<View> paramArrayList1, Transition paramTransition1, ArrayList<View> paramArrayList2, Transition paramTransition2, ArrayList<View> paramArrayList3) {
    OneShotPreDrawListener.add((View)paramViewGroup, new _$$Lambda$FragmentTransition$8Ei4ls5jlZcfRvuLcweFAxtFBFs(paramTransition1, paramView, paramFragment, paramArrayList1, paramArrayList2, paramArrayList3, paramTransition2));
  }
  
  private static void setEpicenter(Transition paramTransition, View paramView) {
    if (paramView != null) {
      final Rect epicenter = new Rect();
      paramView.getBoundsOnScreen(rect);
      paramTransition.setEpicenterCallback(new Transition.EpicenterCallback() {
            public Rect onGetEpicenter(Transition param1Transition) {
              return epicenter;
            }
          });
    } 
  }
  
  private static void setOutEpicenter(TransitionSet paramTransitionSet, Transition paramTransition, ArrayMap<String, View> paramArrayMap, boolean paramBoolean, BackStackRecord paramBackStackRecord) {
    if (paramBackStackRecord.mSharedElementSourceNames != null && !paramBackStackRecord.mSharedElementSourceNames.isEmpty()) {
      String str;
      if (paramBoolean) {
        str = paramBackStackRecord.mSharedElementTargetNames.get(0);
      } else {
        str = ((BackStackRecord)str).mSharedElementSourceNames.get(0);
      } 
      View view = (View)paramArrayMap.get(str);
      setEpicenter((Transition)paramTransitionSet, view);
      if (paramTransition != null)
        setEpicenter(paramTransition, view); 
    } 
  }
  
  private static void setSharedElementTargets(TransitionSet paramTransitionSet, View paramView, ArrayList<View> paramArrayList) {
    List<View> list = paramTransitionSet.getTargets();
    list.clear();
    int i = paramArrayList.size();
    for (byte b = 0; b < i; b++)
      bfsAddViewChildren(list, paramArrayList.get(b)); 
    list.add(paramView);
    paramArrayList.add(paramView);
    addTargets((Transition)paramTransitionSet, paramArrayList);
  }
  
  private static void setViewVisibility(ArrayList<View> paramArrayList, int paramInt) {
    if (paramArrayList == null)
      return; 
    for (int i = paramArrayList.size() - 1; i >= 0; i--)
      ((View)paramArrayList.get(i)).setVisibility(paramInt); 
  }
  
  static void startTransitions(FragmentManagerImpl paramFragmentManagerImpl, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, boolean paramBoolean) {
    if (paramFragmentManagerImpl.mCurState < 1)
      return; 
    SparseArray<FragmentContainerTransition> sparseArray = new SparseArray();
    int i;
    for (i = paramInt1; i < paramInt2; i++) {
      BackStackRecord backStackRecord = paramArrayList.get(i);
      if (((Boolean)paramArrayList1.get(i)).booleanValue()) {
        calculatePopFragments(backStackRecord, sparseArray, paramBoolean);
      } else {
        calculateFragments(backStackRecord, sparseArray, paramBoolean);
      } 
    } 
    if (sparseArray.size() != 0) {
      View view = new View(paramFragmentManagerImpl.mHost.getContext());
      int j = sparseArray.size();
      for (i = 0; i < j; i++) {
        int k = sparseArray.keyAt(i);
        ArrayMap<String, String> arrayMap = calculateNameOverrides(k, paramArrayList, paramArrayList1, paramInt1, paramInt2);
        FragmentContainerTransition fragmentContainerTransition = (FragmentContainerTransition)sparseArray.valueAt(i);
        if (paramBoolean) {
          configureTransitionsReordered(paramFragmentManagerImpl, k, fragmentContainerTransition, view, arrayMap);
        } else {
          configureTransitionsOrdered(paramFragmentManagerImpl, k, fragmentContainerTransition, view, arrayMap);
        } 
      } 
    } 
  }
  
  public static class FragmentContainerTransition {
    public Fragment firstOut;
    
    public boolean firstOutIsPop;
    
    public BackStackRecord firstOutTransaction;
    
    public Fragment lastIn;
    
    public boolean lastInIsPop;
    
    public BackStackRecord lastInTransaction;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/FragmentTransition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */