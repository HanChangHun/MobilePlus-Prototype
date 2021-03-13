package android.animation;

import android.app.ActivityThread;
import android.app.Application;
import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.util.ArrayMap;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class AnimatorSet extends Animator implements AnimationHandler.AnimationFrameCallback {
  private static final String TAG = "AnimatorSet";
  
  private boolean mChildrenInitialized;
  
  private ValueAnimator mDelayAnim;
  
  private boolean mDependencyDirty;
  
  private AnimatorListenerAdapter mDummyListener;
  
  private long mDuration;
  
  private final boolean mEndCanBeCalled;
  
  private ArrayList<AnimationEvent> mEvents;
  
  private long mFirstFrame;
  
  private TimeInterpolator mInterpolator;
  
  private int mLastEventId;
  
  private long mLastFrameTime;
  
  private ArrayMap<Animator, Node> mNodeMap;
  
  private ArrayList<Node> mNodes;
  
  private long mPauseTime;
  
  private ArrayList<Node> mPlayingSet;
  
  private boolean mReversing;
  
  private Node mRootNode;
  
  private SeekState mSeekState;
  
  private boolean mSelfPulse;
  
  private final boolean mShouldIgnoreEndWithoutStart;
  
  private final boolean mShouldResetValuesAtStart;
  
  private long mStartDelay;
  
  private boolean mStarted;
  
  private long mTotalDuration;
  
  public AnimatorSet() {
    boolean bool2;
    this.mPlayingSet = new ArrayList<>();
    this.mNodeMap = new ArrayMap();
    this.mEvents = new ArrayList<>();
    this.mNodes = new ArrayList<>();
    boolean bool1 = false;
    this.mDependencyDirty = false;
    this.mStarted = false;
    this.mStartDelay = 0L;
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F }).setDuration(0L);
    this.mDelayAnim = valueAnimator;
    this.mRootNode = new Node(valueAnimator);
    this.mDuration = -1L;
    this.mInterpolator = null;
    this.mTotalDuration = 0L;
    this.mLastFrameTime = -1L;
    this.mFirstFrame = -1L;
    this.mLastEventId = -1;
    this.mReversing = false;
    this.mSelfPulse = true;
    this.mSeekState = new SeekState();
    this.mChildrenInitialized = false;
    this.mPauseTime = -1L;
    this.mDummyListener = new AnimatorListenerAdapter() {
        public void onAnimationEnd(Animator param1Animator) {
          if (AnimatorSet.this.mNodeMap.get(param1Animator) != null) {
            ((AnimatorSet.Node)AnimatorSet.this.mNodeMap.get(param1Animator)).mEnded = true;
            return;
          } 
          throw new AndroidRuntimeException("Error: animation ended is not in the node map");
        }
      };
    this.mNodeMap.put(this.mDelayAnim, this.mRootNode);
    this.mNodes.add(this.mRootNode);
    Application application = ActivityThread.currentApplication();
    if (application == null || application.getApplicationInfo() == null) {
      this.mShouldIgnoreEndWithoutStart = true;
      bool2 = true;
    } else {
      if ((application.getApplicationInfo()).targetSdkVersion < 24) {
        this.mShouldIgnoreEndWithoutStart = true;
      } else {
        this.mShouldIgnoreEndWithoutStart = false;
      } 
      if ((application.getApplicationInfo()).targetSdkVersion < 26) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
    } 
    if (!bool2) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    this.mShouldResetValuesAtStart = bool3;
    boolean bool3 = bool1;
    if (!bool2)
      bool3 = true; 
    this.mEndCanBeCalled = bool3;
  }
  
  private void addAnimationCallback(long paramLong) {
    if (!this.mSelfPulse)
      return; 
    AnimationHandler.getInstance().addAnimationFrameCallback(this, paramLong);
  }
  
  private void addDummyListener() {
    for (byte b = 1; b < this.mNodes.size(); b++)
      ((Node)this.mNodes.get(b)).mAnimation.addListener(this.mDummyListener); 
  }
  
  private void createDependencyGraph() {
    if (!this.mDependencyDirty) {
      boolean bool2;
      boolean bool1 = false;
      byte b1 = 0;
      while (true) {
        bool2 = bool1;
        if (b1 < this.mNodes.size()) {
          Animator animator = ((Node)this.mNodes.get(b1)).mAnimation;
          if (((Node)this.mNodes.get(b1)).mTotalDuration != animator.getTotalDuration()) {
            bool2 = true;
            break;
          } 
          b1++;
          continue;
        } 
        break;
      } 
      if (!bool2)
        return; 
    } 
    this.mDependencyDirty = false;
    int i = this.mNodes.size();
    byte b;
    for (b = 0; b < i; b++)
      ((Node)this.mNodes.get(b)).mParentsAdded = false; 
    for (b = 0; b < i; b++) {
      Node node = this.mNodes.get(b);
      if (!node.mParentsAdded) {
        node.mParentsAdded = true;
        if (node.mSiblings != null) {
          findSiblings(node, node.mSiblings);
          node.mSiblings.remove(node);
          int j = node.mSiblings.size();
          byte b1;
          for (b1 = 0; b1 < j; b1++)
            node.addParents(((Node)node.mSiblings.get(b1)).mParents); 
          for (b1 = 0; b1 < j; b1++) {
            Node node1 = node.mSiblings.get(b1);
            node1.addParents(node.mParents);
            node1.mParentsAdded = true;
          } 
        } 
      } 
    } 
    for (b = 0; b < i; b++) {
      Node node = this.mNodes.get(b);
      if (node != this.mRootNode && node.mParents == null)
        node.addParent(this.mRootNode); 
    } 
    ArrayList<Node> arrayList1 = new ArrayList(this.mNodes.size());
    this.mRootNode.mStartTime = 0L;
    this.mRootNode.mEndTime = this.mDelayAnim.getDuration();
    updatePlayTime(this.mRootNode, arrayList1);
    sortAnimationEvents();
    ArrayList<AnimationEvent> arrayList = this.mEvents;
    this.mTotalDuration = ((AnimationEvent)arrayList.get(arrayList.size() - 1)).getTime();
  }
  
  private void endAnimation() {
    this.mStarted = false;
    this.mLastFrameTime = -1L;
    this.mFirstFrame = -1L;
    this.mLastEventId = -1;
    this.mPaused = false;
    this.mPauseTime = -1L;
    this.mSeekState.reset();
    this.mPlayingSet.clear();
    removeAnimationCallback();
    if (this.mListeners != null) {
      ArrayList<Animator.AnimatorListener> arrayList = (ArrayList)this.mListeners.clone();
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        ((Animator.AnimatorListener)arrayList.get(b)).onAnimationEnd(this, this.mReversing); 
    } 
    removeDummyListener();
    this.mSelfPulse = true;
    this.mReversing = false;
  }
  
  private int findLatestEventIdForTime(long paramLong) {
    int k;
    int i = this.mEvents.size();
    int j = this.mLastEventId;
    if (this.mReversing) {
      long l = getTotalDuration();
      k = this.mLastEventId;
      int m = k;
      if (k == -1)
        m = i; 
      this.mLastEventId = m;
      while (--m >= 0) {
        if (((AnimationEvent)this.mEvents.get(m)).getTime() >= l - paramLong)
          j = m; 
        m--;
      } 
      k = j;
    } else {
      int m = this.mLastEventId + 1;
      while (true) {
        k = j;
        if (m < i) {
          AnimationEvent animationEvent = this.mEvents.get(m);
          k = j;
          if (animationEvent.getTime() != -1L) {
            k = j;
            if (animationEvent.getTime() <= paramLong)
              k = m; 
          } 
          m++;
          j = k;
          continue;
        } 
        break;
      } 
    } 
    return k;
  }
  
  private void findSiblings(Node paramNode, ArrayList<Node> paramArrayList) {
    if (!paramArrayList.contains(paramNode)) {
      paramArrayList.add(paramNode);
      if (paramNode.mSiblings == null)
        return; 
      for (byte b = 0; b < paramNode.mSiblings.size(); b++)
        findSiblings(paramNode.mSiblings.get(b), paramArrayList); 
    } 
  }
  
  private void forceToEnd() {
    if (this.mEndCanBeCalled) {
      end();
      return;
    } 
    if (this.mReversing) {
      handleAnimationEvents(this.mLastEventId, 0, getTotalDuration());
    } else {
      long l1 = getTotalDuration();
      long l2 = l1;
      if (l1 == -1L)
        l2 = 2147483647L; 
      handleAnimationEvents(this.mLastEventId, this.mEvents.size() - 1, l2);
    } 
    this.mPlayingSet.clear();
    endAnimation();
  }
  
  private Node getNodeForAnimation(Animator paramAnimator) {
    Node node1 = (Node)this.mNodeMap.get(paramAnimator);
    Node node2 = node1;
    if (node1 == null) {
      node2 = new Node(paramAnimator);
      this.mNodeMap.put(paramAnimator, node2);
      this.mNodes.add(node2);
    } 
    return node2;
  }
  
  private long getPlayTimeForNode(long paramLong, Node paramNode) {
    return getPlayTimeForNode(paramLong, paramNode, this.mReversing);
  }
  
  private long getPlayTimeForNode(long paramLong, Node paramNode, boolean paramBoolean) {
    if (paramBoolean) {
      long l = getTotalDuration();
      return paramNode.mEndTime - l - paramLong;
    } 
    return paramLong - paramNode.mStartTime;
  }
  
  private void handleAnimationEvents(int paramInt1, int paramInt2, long paramLong) {
    if (this.mReversing) {
      if (paramInt1 == -1)
        paramInt1 = this.mEvents.size(); 
      while (--paramInt1 >= paramInt2) {
        AnimationEvent animationEvent = this.mEvents.get(paramInt1);
        Node node = animationEvent.mNode;
        if (animationEvent.mEvent == 2) {
          if (node.mAnimation.isStarted())
            node.mAnimation.cancel(); 
          node.mEnded = false;
          this.mPlayingSet.add(animationEvent.mNode);
          node.mAnimation.startWithoutPulsing(true);
          pulseFrame(node, 0L);
        } else if (animationEvent.mEvent == 1 && !node.mEnded) {
          pulseFrame(node, getPlayTimeForNode(paramLong, node));
        } 
        paramInt1--;
      } 
    } else {
      while (++paramInt1 <= paramInt2) {
        AnimationEvent animationEvent = this.mEvents.get(paramInt1);
        Node node = animationEvent.mNode;
        if (animationEvent.mEvent == 0) {
          this.mPlayingSet.add(animationEvent.mNode);
          if (node.mAnimation.isStarted())
            node.mAnimation.cancel(); 
          node.mEnded = false;
          node.mAnimation.startWithoutPulsing(false);
          pulseFrame(node, 0L);
        } else if (animationEvent.mEvent == 2 && !node.mEnded) {
          pulseFrame(node, getPlayTimeForNode(paramLong, node));
        } 
        paramInt1++;
      } 
    } 
  }
  
  private void initAnimation() {
    if (this.mInterpolator != null)
      for (byte b = 0; b < this.mNodes.size(); b++)
        ((Node)this.mNodes.get(b)).mAnimation.setInterpolator(this.mInterpolator);  
    updateAnimatorsDuration();
    createDependencyGraph();
  }
  
  private void initChildren() {
    if (!isInitialized()) {
      this.mChildrenInitialized = true;
      skipToEndValue(false);
    } 
  }
  
  private static boolean isEmptySet(AnimatorSet paramAnimatorSet) {
    if (paramAnimatorSet.getStartDelay() > 0L)
      return false; 
    for (byte b = 0; b < paramAnimatorSet.getChildAnimations().size(); b++) {
      Animator animator = paramAnimatorSet.getChildAnimations().get(b);
      if (!(animator instanceof AnimatorSet))
        return false; 
      if (!isEmptySet((AnimatorSet)animator))
        return false; 
    } 
    return true;
  }
  
  private void printChildCount() {
    ArrayList<Node> arrayList = new ArrayList(this.mNodes.size());
    arrayList.add(this.mRootNode);
    Log.d("AnimatorSet", "Current tree: ");
    byte b = 0;
    while (b < arrayList.size()) {
      int i = arrayList.size();
      StringBuilder stringBuilder = new StringBuilder();
      while (b < i) {
        Node node = arrayList.get(b);
        int j = 0;
        int k = 0;
        if (node.mChildNodes != null) {
          byte b1 = 0;
          while (true) {
            j = k;
            if (b1 < node.mChildNodes.size()) {
              Node node1 = node.mChildNodes.get(b1);
              j = k;
              if (node1.mLatestParent == node) {
                j = k + 1;
                arrayList.add(node1);
              } 
              b1++;
              k = j;
              continue;
            } 
            break;
          } 
        } 
        stringBuilder.append(" ");
        stringBuilder.append(j);
        b++;
      } 
      Log.d("AnimatorSet", stringBuilder.toString());
    } 
  }
  
  private void pulseFrame(Node paramNode, long paramLong) {
    if (!paramNode.mEnded) {
      float f = ValueAnimator.getDurationScale();
      if (f == 0.0F)
        f = 1.0F; 
      paramNode.mEnded = paramNode.mAnimation.pulseAnimationFrame((long)((float)paramLong * f));
    } 
  }
  
  private void removeAnimationCallback() {
    if (!this.mSelfPulse)
      return; 
    AnimationHandler.getInstance().removeCallback(this);
  }
  
  private void removeDummyListener() {
    for (byte b = 1; b < this.mNodes.size(); b++)
      ((Node)this.mNodes.get(b)).mAnimation.removeListener(this.mDummyListener); 
  }
  
  private void skipToStartValue(boolean paramBoolean) {
    skipToEndValue(paramBoolean ^ true);
  }
  
  private void sortAnimationEvents() {
    this.mEvents.clear();
    int i;
    for (i = 1; i < this.mNodes.size(); i++) {
      Node node = this.mNodes.get(i);
      this.mEvents.add(new AnimationEvent(node, 0));
      this.mEvents.add(new AnimationEvent(node, 1));
      this.mEvents.add(new AnimationEvent(node, 2));
    } 
    this.mEvents.sort(new Comparator<AnimationEvent>() {
          public int compare(AnimatorSet.AnimationEvent param1AnimationEvent1, AnimatorSet.AnimationEvent param1AnimationEvent2) {
            long l1 = param1AnimationEvent1.getTime();
            long l2 = param1AnimationEvent2.getTime();
            return (l1 == l2) ? ((param1AnimationEvent2.mEvent + param1AnimationEvent1.mEvent == 1) ? (param1AnimationEvent1.mEvent - param1AnimationEvent2.mEvent) : (param1AnimationEvent2.mEvent - param1AnimationEvent1.mEvent)) : ((l2 == -1L) ? -1 : ((l1 == -1L) ? 1 : (int)(l1 - l2)));
          }
        });
    int j = this.mEvents.size();
    for (i = 0; i < j; i++) {
      AnimationEvent animationEvent = this.mEvents.get(i);
      if (animationEvent.mEvent == 2) {
        boolean bool;
        if (animationEvent.mNode.mStartTime == animationEvent.mNode.mEndTime) {
          bool = true;
        } else if (animationEvent.mNode.mEndTime == animationEvent.mNode.mStartTime + animationEvent.mNode.mAnimation.getStartDelay()) {
          bool = false;
        } else {
          i++;
          continue;
        } 
        int k = j;
        int m = j;
        int n = i + 1;
        while (n < j && (k >= j || m >= j)) {
          int i1 = k;
          int i2 = m;
          if (((AnimationEvent)this.mEvents.get(n)).mNode == animationEvent.mNode)
            if (((AnimationEvent)this.mEvents.get(n)).mEvent == 0) {
              i1 = n;
              i2 = m;
            } else {
              i1 = k;
              i2 = m;
              if (((AnimationEvent)this.mEvents.get(n)).mEvent == 1) {
                i2 = n;
                i1 = k;
              } 
            }  
          n++;
          k = i1;
          m = i2;
        } 
        if (!bool || k != this.mEvents.size()) {
          if (m != this.mEvents.size()) {
            n = i;
            if (bool) {
              animationEvent = this.mEvents.remove(k);
              this.mEvents.add(i, animationEvent);
              n = i + 1;
            } 
            animationEvent = this.mEvents.remove(m);
            this.mEvents.add(n, animationEvent);
            i = n + 2;
            continue;
          } 
          throw new UnsupportedOperationException("Something went wrong, no startdelay end is found after stop for an animation");
        } 
        throw new UnsupportedOperationException("Something went wrong, no start isfound after stop for an animation that has the same start and endtime.");
      } 
    } 
    if (this.mEvents.isEmpty() || ((AnimationEvent)this.mEvents.get(0)).mEvent == 0) {
      this.mEvents.add(0, new AnimationEvent(this.mRootNode, 0));
      this.mEvents.add(1, new AnimationEvent(this.mRootNode, 1));
      this.mEvents.add(2, new AnimationEvent(this.mRootNode, 2));
      ArrayList<AnimationEvent> arrayList = this.mEvents;
      if (((AnimationEvent)arrayList.get(arrayList.size() - 1)).mEvent != 0) {
        arrayList = this.mEvents;
        if (((AnimationEvent)arrayList.get(arrayList.size() - 1)).mEvent != 1)
          return; 
      } 
      throw new UnsupportedOperationException("Something went wrong, the last event is not an end event");
    } 
    throw new UnsupportedOperationException("Sorting went bad, the start event should always be at index 0");
  }
  
  private void start(boolean paramBoolean1, boolean paramBoolean2) {
    if (Looper.myLooper() != null) {
      this.mStarted = true;
      this.mSelfPulse = paramBoolean2;
      this.mPaused = false;
      this.mPauseTime = -1L;
      int i = this.mNodes.size();
      byte b;
      for (b = 0; b < i; b++) {
        Node node = this.mNodes.get(b);
        node.mEnded = false;
        node.mAnimation.setAllowRunningAsynchronously(false);
      } 
      initAnimation();
      if (!paramBoolean1 || canReverse()) {
        this.mReversing = paramBoolean1;
        paramBoolean2 = isEmptySet(this);
        if (!paramBoolean2)
          startAnimation(); 
        if (this.mListeners != null) {
          ArrayList<Animator.AnimatorListener> arrayList = (ArrayList)this.mListeners.clone();
          i = arrayList.size();
          for (b = 0; b < i; b++)
            ((Animator.AnimatorListener)arrayList.get(b)).onAnimationStart(this, paramBoolean1); 
        } 
        if (paramBoolean2)
          end(); 
        return;
      } 
      throw new UnsupportedOperationException("Cannot reverse infinite AnimatorSet");
    } 
    throw new AndroidRuntimeException("Animators may only be run on Looper threads");
  }
  
  private void startAnimation() {
    addDummyListener();
    addAnimationCallback(0L);
    if (this.mSeekState.getPlayTimeNormalized() == 0L && this.mReversing)
      this.mSeekState.reset(); 
    if (this.mShouldResetValuesAtStart)
      if (isInitialized()) {
        skipToEndValue(this.mReversing ^ true);
      } else if (this.mReversing) {
        initChildren();
        skipToEndValue(this.mReversing ^ true);
      } else {
        for (int i = this.mEvents.size() - 1; i >= 0; i--) {
          if (((AnimationEvent)this.mEvents.get(i)).mEvent == 1) {
            Animator animator = ((AnimationEvent)this.mEvents.get(i)).mNode.mAnimation;
            if (animator.isInitialized())
              animator.skipToEndValue(true); 
          } 
        } 
      }  
    if (this.mReversing || this.mStartDelay == 0L || this.mSeekState.isActive()) {
      long l;
      if (this.mSeekState.isActive()) {
        this.mSeekState.updateSeekDirection(this.mReversing);
        l = this.mSeekState.getPlayTime();
      } else {
        l = 0L;
      } 
      int j = findLatestEventIdForTime(l);
      handleAnimationEvents(-1, j, l);
      for (int i = this.mPlayingSet.size() - 1; i >= 0; i--) {
        if (((Node)this.mPlayingSet.get(i)).mEnded)
          this.mPlayingSet.remove(i); 
      } 
      this.mLastEventId = j;
    } 
  }
  
  private void updateAnimatorsDuration() {
    if (this.mDuration >= 0L) {
      int i = this.mNodes.size();
      for (byte b = 0; b < i; b++)
        ((Node)this.mNodes.get(b)).mAnimation.setDuration(this.mDuration); 
    } 
    this.mDelayAnim.setDuration(this.mStartDelay);
  }
  
  private void updatePlayTime(Node paramNode, ArrayList<Node> paramArrayList) {
    if (paramNode.mChildNodes == null) {
      if (paramNode == this.mRootNode)
        for (byte b1 = 0; b1 < this.mNodes.size(); b1++) {
          paramNode = this.mNodes.get(b1);
          if (paramNode != this.mRootNode) {
            paramNode.mStartTime = -1L;
            paramNode.mEndTime = -1L;
          } 
        }  
      return;
    } 
    paramArrayList.add(paramNode);
    int i = paramNode.mChildNodes.size();
    for (byte b = 0; b < i; b++) {
      StringBuilder stringBuilder;
      Node node = paramNode.mChildNodes.get(b);
      node.mTotalDuration = node.mAnimation.getTotalDuration();
      int j = paramArrayList.indexOf(node);
      if (j >= 0) {
        while (j < paramArrayList.size()) {
          ((Node)paramArrayList.get(j)).mLatestParent = null;
          ((Node)paramArrayList.get(j)).mStartTime = -1L;
          ((Node)paramArrayList.get(j)).mEndTime = -1L;
          j++;
        } 
        node.mStartTime = -1L;
        node.mEndTime = -1L;
        node.mLatestParent = null;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Cycle found in AnimatorSet: ");
        stringBuilder.append(this);
        Log.w("AnimatorSet", stringBuilder.toString());
      } else {
        if (((Node)stringBuilder).mStartTime != -1L)
          if (paramNode.mEndTime == -1L) {
            ((Node)stringBuilder).mLatestParent = paramNode;
            ((Node)stringBuilder).mStartTime = -1L;
            ((Node)stringBuilder).mEndTime = -1L;
          } else {
            long l;
            if (paramNode.mEndTime >= ((Node)stringBuilder).mStartTime) {
              ((Node)stringBuilder).mLatestParent = paramNode;
              ((Node)stringBuilder).mStartTime = paramNode.mEndTime;
            } 
            if (((Node)stringBuilder).mTotalDuration == -1L) {
              l = -1L;
            } else {
              l = ((Node)stringBuilder).mStartTime + ((Node)stringBuilder).mTotalDuration;
            } 
            ((Node)stringBuilder).mEndTime = l;
          }  
        updatePlayTime((Node)stringBuilder, paramArrayList);
      } 
    } 
    paramArrayList.remove(paramNode);
  }
  
  void animateBasedOnPlayTime(long paramLong1, long paramLong2, boolean paramBoolean) {
    long l = paramLong1;
    if (l >= 0L && paramLong2 >= 0L) {
      if (paramBoolean) {
        if (getTotalDuration() != -1L) {
          long l1 = getTotalDuration() - this.mStartDelay;
          paramLong1 = l1 - Math.min(l, l1);
          l = l1 - paramLong2;
          paramBoolean = false;
          paramLong2 = paramLong1;
          paramLong1 = l;
        } else {
          throw new UnsupportedOperationException("Cannot reverse AnimatorSet with infinite duration");
        } 
      } else {
        paramLong1 = paramLong2;
        paramLong2 = l;
      } 
      ArrayList<Node> arrayList = new ArrayList();
      byte b;
      for (b = 0; b < this.mEvents.size(); b++) {
        AnimationEvent animationEvent = this.mEvents.get(b);
        if (animationEvent.getTime() > paramLong2 || animationEvent.getTime() == -1L)
          break; 
        if (animationEvent.mEvent == 1 && (animationEvent.mNode.mEndTime == -1L || animationEvent.mNode.mEndTime > paramLong2))
          arrayList.add(animationEvent.mNode); 
        if (animationEvent.mEvent == 2)
          animationEvent.mNode.mAnimation.skipToEndValue(false); 
      } 
      for (b = 0; b < arrayList.size(); b++) {
        Node node = arrayList.get(b);
        l = getPlayTimeForNode(paramLong2, node, paramBoolean);
        if (!paramBoolean)
          l -= node.mAnimation.getStartDelay(); 
        node.mAnimation.animateBasedOnPlayTime(l, paramLong1, paramBoolean);
      } 
      for (b = 0; b < this.mEvents.size(); b++) {
        AnimationEvent animationEvent = this.mEvents.get(b);
        if (animationEvent.getTime() > paramLong2 && animationEvent.mEvent == 1)
          animationEvent.mNode.mAnimation.skipToEndValue(true); 
      } 
      return;
    } 
    throw new UnsupportedOperationException("Error: Play time should never be negative.");
  }
  
  public boolean canReverse() {
    boolean bool;
    if (getTotalDuration() != -1L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void cancel() {
    if (Looper.myLooper() != null) {
      if (isStarted()) {
        if (this.mListeners != null) {
          ArrayList<Animator.AnimatorListener> arrayList1 = (ArrayList)this.mListeners.clone();
          int j = arrayList1.size();
          for (byte b1 = 0; b1 < j; b1++)
            ((Animator.AnimatorListener)arrayList1.get(b1)).onAnimationCancel(this); 
        } 
        ArrayList<Node> arrayList = new ArrayList<>(this.mPlayingSet);
        int i = arrayList.size();
        for (byte b = 0; b < i; b++)
          ((Node)arrayList.get(b)).mAnimation.cancel(); 
        this.mPlayingSet.clear();
        endAnimation();
      } 
      return;
    } 
    throw new AndroidRuntimeException("Animators may only be run on Looper threads");
  }
  
  public AnimatorSet clone() {
    final AnimatorSet anim = (AnimatorSet)super.clone();
    int i = this.mNodes.size();
    animatorSet.mStarted = false;
    animatorSet.mLastFrameTime = -1L;
    animatorSet.mFirstFrame = -1L;
    animatorSet.mLastEventId = -1;
    animatorSet.mPaused = false;
    animatorSet.mPauseTime = -1L;
    animatorSet.mSeekState = new SeekState();
    animatorSet.mSelfPulse = true;
    animatorSet.mPlayingSet = new ArrayList<>();
    animatorSet.mNodeMap = new ArrayMap();
    animatorSet.mNodes = new ArrayList<>(i);
    animatorSet.mEvents = new ArrayList<>();
    animatorSet.mDummyListener = new AnimatorListenerAdapter() {
        public void onAnimationEnd(Animator param1Animator) {
          if (anim.mNodeMap.get(param1Animator) != null) {
            ((AnimatorSet.Node)anim.mNodeMap.get(param1Animator)).mEnded = true;
            return;
          } 
          throw new AndroidRuntimeException("Error: animation ended is not in the node map");
        }
      };
    animatorSet.mReversing = false;
    animatorSet.mDependencyDirty = true;
    HashMap<Object, Object> hashMap = new HashMap<>(i);
    byte b;
    for (b = 0; b < i; b++) {
      Node node1 = this.mNodes.get(b);
      Node node2 = node1.clone();
      node2.mAnimation.removeListener(this.mDummyListener);
      hashMap.put(node1, node2);
      animatorSet.mNodes.add(node2);
      animatorSet.mNodeMap.put(node2.mAnimation, node2);
    } 
    Node node = (Node)hashMap.get(this.mRootNode);
    animatorSet.mRootNode = node;
    animatorSet.mDelayAnim = (ValueAnimator)node.mAnimation;
    for (b = 0; b < i; b++) {
      int j;
      Node node1 = this.mNodes.get(b);
      Node node2 = (Node)hashMap.get(node1);
      if (node1.mLatestParent == null) {
        node = null;
      } else {
        node = (Node)hashMap.get(node1.mLatestParent);
      } 
      node2.mLatestParent = node;
      if (node1.mChildNodes == null) {
        j = 0;
      } else {
        j = node1.mChildNodes.size();
      } 
      byte b1;
      for (b1 = 0; b1 < j; b1++)
        node2.mChildNodes.set(b1, (Node)hashMap.get(node1.mChildNodes.get(b1))); 
      if (node1.mSiblings == null) {
        j = 0;
      } else {
        j = node1.mSiblings.size();
      } 
      for (b1 = 0; b1 < j; b1++)
        node2.mSiblings.set(b1, (Node)hashMap.get(node1.mSiblings.get(b1))); 
      if (node1.mParents == null) {
        j = 0;
      } else {
        j = node1.mParents.size();
      } 
      for (b1 = 0; b1 < j; b1++)
        node2.mParents.set(b1, (Node)hashMap.get(node1.mParents.get(b1))); 
    } 
    return animatorSet;
  }
  
  public void commitAnimationFrame(long paramLong) {}
  
  public boolean doAnimationFrame(long paramLong) {
    float f = ValueAnimator.getDurationScale();
    if (f == 0.0F) {
      forceToEnd();
      return true;
    } 
    if (this.mFirstFrame < 0L)
      this.mFirstFrame = paramLong; 
    if (this.mPaused) {
      this.mPauseTime = paramLong;
      removeAnimationCallback();
      return false;
    } 
    long l = this.mPauseTime;
    if (l > 0L) {
      this.mFirstFrame += paramLong - l;
      this.mPauseTime = -1L;
    } 
    if (this.mSeekState.isActive()) {
      this.mSeekState.updateSeekDirection(this.mReversing);
      if (this.mReversing) {
        this.mFirstFrame = (long)((float)paramLong - (float)this.mSeekState.getPlayTime() * f);
      } else {
        this.mFirstFrame = (long)((float)paramLong - (float)(this.mSeekState.getPlayTime() + this.mStartDelay) * f);
      } 
      this.mSeekState.reset();
    } 
    if (!this.mReversing && (float)paramLong < (float)this.mFirstFrame + (float)this.mStartDelay * f)
      return false; 
    l = (long)((float)(paramLong - this.mFirstFrame) / f);
    this.mLastFrameTime = paramLong;
    int i = findLatestEventIdForTime(l);
    handleAnimationEvents(this.mLastEventId, i, l);
    this.mLastEventId = i;
    for (i = 0; i < this.mPlayingSet.size(); i++) {
      Node node = this.mPlayingSet.get(i);
      if (!node.mEnded)
        pulseFrame(node, getPlayTimeForNode(l, node)); 
    } 
    for (i = this.mPlayingSet.size() - 1; i >= 0; i--) {
      if (((Node)this.mPlayingSet.get(i)).mEnded)
        this.mPlayingSet.remove(i); 
    } 
    boolean bool = false;
    if (this.mReversing) {
      if (this.mPlayingSet.size() == 1 && this.mPlayingSet.get(0) == this.mRootNode) {
        i = 1;
      } else {
        i = bool;
        if (this.mPlayingSet.isEmpty()) {
          i = bool;
          if (this.mLastEventId < 3)
            i = 1; 
        } 
      } 
    } else if (this.mPlayingSet.isEmpty() && this.mLastEventId == this.mEvents.size() - 1) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i != 0) {
      endAnimation();
      return true;
    } 
    return false;
  }
  
  public void end() {
    if (Looper.myLooper() != null) {
      if (this.mShouldIgnoreEndWithoutStart && !isStarted())
        return; 
      if (isStarted()) {
        if (this.mReversing) {
          int i = this.mLastEventId;
          int j = i;
          if (i == -1)
            j = this.mEvents.size(); 
          this.mLastEventId = j;
          while (true) {
            j = this.mLastEventId;
            if (j > 0) {
              this.mLastEventId = --j;
              AnimationEvent animationEvent = this.mEvents.get(j);
              Animator animator = animationEvent.mNode.mAnimation;
              if (((Node)this.mNodeMap.get(animator)).mEnded)
                continue; 
              if (animationEvent.mEvent == 2) {
                animator.reverse();
                continue;
              } 
              if (animationEvent.mEvent == 1 && animator.isStarted())
                animator.end(); 
              continue;
            } 
            break;
          } 
        } else {
          while (this.mLastEventId < this.mEvents.size() - 1) {
            int i = this.mLastEventId + 1;
            this.mLastEventId = i;
            AnimationEvent animationEvent = this.mEvents.get(i);
            Animator animator = animationEvent.mNode.mAnimation;
            if (((Node)this.mNodeMap.get(animator)).mEnded)
              continue; 
            if (animationEvent.mEvent == 0) {
              animator.start();
              continue;
            } 
            if (animationEvent.mEvent == 2 && animator.isStarted())
              animator.end(); 
          } 
        } 
        this.mPlayingSet.clear();
      } 
      endAnimation();
      return;
    } 
    throw new AndroidRuntimeException("Animators may only be run on Looper threads");
  }
  
  public int getChangingConfigurations() {
    int i = super.getChangingConfigurations();
    int j = this.mNodes.size();
    for (byte b = 0; b < j; b++)
      i |= ((Node)this.mNodes.get(b)).mAnimation.getChangingConfigurations(); 
    return i;
  }
  
  public ArrayList<Animator> getChildAnimations() {
    ArrayList<Animator> arrayList = new ArrayList();
    int i = this.mNodes.size();
    for (byte b = 0; b < i; b++) {
      Node node = this.mNodes.get(b);
      if (node != this.mRootNode)
        arrayList.add(node.mAnimation); 
    } 
    return arrayList;
  }
  
  public long getCurrentPlayTime() {
    if (this.mSeekState.isActive())
      return this.mSeekState.getPlayTime(); 
    if (this.mLastFrameTime == -1L)
      return 0L; 
    float f = ValueAnimator.getDurationScale();
    if (f == 0.0F)
      f = 1.0F; 
    return this.mReversing ? (long)((float)(this.mLastFrameTime - this.mFirstFrame) / f) : (long)((float)(this.mLastFrameTime - this.mFirstFrame - this.mStartDelay) / f);
  }
  
  public long getDuration() {
    return this.mDuration;
  }
  
  public TimeInterpolator getInterpolator() {
    return this.mInterpolator;
  }
  
  public long getStartDelay() {
    return this.mStartDelay;
  }
  
  public long getTotalDuration() {
    updateAnimatorsDuration();
    createDependencyGraph();
    return this.mTotalDuration;
  }
  
  boolean isInitialized() {
    boolean bool2;
    if (this.mChildrenInitialized)
      return true; 
    boolean bool1 = true;
    byte b = 0;
    while (true) {
      bool2 = bool1;
      if (b < this.mNodes.size()) {
        if (!((Node)this.mNodes.get(b)).mAnimation.isInitialized()) {
          bool2 = false;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    this.mChildrenInitialized = bool2;
    return bool2;
  }
  
  public boolean isRunning() {
    boolean bool;
    if (this.mStartDelay == 0L)
      return this.mStarted; 
    if (this.mLastFrameTime > 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isStarted() {
    return this.mStarted;
  }
  
  public void pause() {
    if (Looper.myLooper() != null) {
      boolean bool = this.mPaused;
      super.pause();
      if (!bool && this.mPaused)
        this.mPauseTime = -1L; 
      return;
    } 
    throw new AndroidRuntimeException("Animators may only be run on Looper threads");
  }
  
  public Builder play(Animator paramAnimator) {
    return (paramAnimator != null) ? new Builder(paramAnimator) : null;
  }
  
  public void playSequentially(List<Animator> paramList) {
    if (paramList != null && paramList.size() > 0)
      if (paramList.size() == 1) {
        play(paramList.get(0));
      } else {
        for (byte b = 0; b < paramList.size() - 1; b++)
          play(paramList.get(b)).before(paramList.get(b + 1)); 
      }  
  }
  
  public void playSequentially(Animator... paramVarArgs) {
    if (paramVarArgs != null)
      if (paramVarArgs.length == 1) {
        play(paramVarArgs[0]);
      } else {
        for (byte b = 0; b < paramVarArgs.length - 1; b++)
          play(paramVarArgs[b]).before(paramVarArgs[b + 1]); 
      }  
  }
  
  public void playTogether(Collection<Animator> paramCollection) {
    if (paramCollection != null && paramCollection.size() > 0) {
      Collection collection = null;
      Iterator<Animator> iterator = paramCollection.iterator();
      paramCollection = collection;
      while (iterator.hasNext()) {
        Builder builder;
        Animator animator = iterator.next();
        if (paramCollection == null) {
          builder = play(animator);
          continue;
        } 
        builder.with(animator);
      } 
    } 
  }
  
  public void playTogether(Animator... paramVarArgs) {
    if (paramVarArgs != null) {
      Builder builder = play(paramVarArgs[0]);
      for (byte b = 1; b < paramVarArgs.length; b++)
        builder.with(paramVarArgs[b]); 
    } 
  }
  
  boolean pulseAnimationFrame(long paramLong) {
    return doAnimationFrame(paramLong);
  }
  
  public void resume() {
    if (Looper.myLooper() != null) {
      boolean bool = this.mPaused;
      super.resume();
      if (bool && !this.mPaused && this.mPauseTime >= 0L)
        addAnimationCallback(0L); 
      return;
    } 
    throw new AndroidRuntimeException("Animators may only be run on Looper threads");
  }
  
  public void reverse() {
    start(true, true);
  }
  
  public void setCurrentPlayTime(long paramLong) {
    if (!this.mReversing || getTotalDuration() != -1L) {
      if ((getTotalDuration() == -1L || paramLong <= getTotalDuration() - this.mStartDelay) && paramLong >= 0L) {
        initAnimation();
        if (!isStarted() || isPaused()) {
          if (!this.mReversing) {
            if (!this.mSeekState.isActive()) {
              findLatestEventIdForTime(0L);
              initChildren();
              this.mSeekState.setPlayTime(0L, this.mReversing);
            } 
            animateBasedOnPlayTime(paramLong, 0L, this.mReversing);
            this.mSeekState.setPlayTime(paramLong, this.mReversing);
            return;
          } 
          throw new UnsupportedOperationException("Error: Something went wrong. mReversing should not be set when AnimatorSet is not started.");
        } 
        this.mSeekState.setPlayTime(paramLong, this.mReversing);
        return;
      } 
      throw new UnsupportedOperationException("Error: Play time should always be in between0 and duration.");
    } 
    throw new UnsupportedOperationException("Error: Cannot seek in reverse in an infinite AnimatorSet");
  }
  
  public AnimatorSet setDuration(long paramLong) {
    if (paramLong >= 0L) {
      this.mDependencyDirty = true;
      this.mDuration = paramLong;
      return this;
    } 
    throw new IllegalArgumentException("duration must be a value of zero or greater");
  }
  
  public void setInterpolator(TimeInterpolator paramTimeInterpolator) {
    this.mInterpolator = paramTimeInterpolator;
  }
  
  public void setStartDelay(long paramLong) {
    long l1 = paramLong;
    if (paramLong < 0L) {
      Log.w("AnimatorSet", "Start delay should always be non-negative");
      l1 = 0L;
    } 
    long l2 = l1 - this.mStartDelay;
    if (l2 == 0L)
      return; 
    this.mStartDelay = l1;
    if (!this.mDependencyDirty) {
      int i = this.mNodes.size();
      byte b = 0;
      while (true) {
        l1 = -1L;
        if (b < i) {
          Node node = this.mNodes.get(b);
          if (node == this.mRootNode) {
            node.mEndTime = this.mStartDelay;
          } else {
            if (node.mStartTime == -1L) {
              paramLong = -1L;
            } else {
              paramLong = node.mStartTime + l2;
            } 
            node.mStartTime = paramLong;
            if (node.mEndTime == -1L) {
              paramLong = l1;
            } else {
              paramLong = node.mEndTime + l2;
            } 
            node.mEndTime = paramLong;
          } 
          b++;
          continue;
        } 
        paramLong = this.mTotalDuration;
        if (paramLong != -1L)
          this.mTotalDuration = paramLong + l2; 
        break;
      } 
    } 
  }
  
  public void setTarget(Object paramObject) {
    int i = this.mNodes.size();
    for (byte b = 0; b < i; b++) {
      Animator animator = ((Node)this.mNodes.get(b)).mAnimation;
      if (animator instanceof AnimatorSet) {
        ((AnimatorSet)animator).setTarget(paramObject);
      } else if (animator instanceof ObjectAnimator) {
        ((ObjectAnimator)animator).setTarget(paramObject);
      } 
    } 
  }
  
  public void setupEndValues() {
    int i = this.mNodes.size();
    for (byte b = 0; b < i; b++) {
      Node node = this.mNodes.get(b);
      if (node != this.mRootNode)
        node.mAnimation.setupEndValues(); 
    } 
  }
  
  public void setupStartValues() {
    int i = this.mNodes.size();
    for (byte b = 0; b < i; b++) {
      Node node = this.mNodes.get(b);
      if (node != this.mRootNode)
        node.mAnimation.setupStartValues(); 
    } 
  }
  
  public boolean shouldPlayTogether() {
    updateAnimatorsDuration();
    createDependencyGraph();
    ArrayList<Node> arrayList = this.mRootNode.mChildNodes;
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (arrayList != null)
      if (this.mRootNode.mChildNodes.size() == this.mNodes.size() - 1) {
        bool2 = bool1;
      } else {
        bool2 = false;
      }  
    return bool2;
  }
  
  void skipToEndValue(boolean paramBoolean) {
    if (isInitialized()) {
      initAnimation();
      if (paramBoolean) {
        for (int i = this.mEvents.size() - 1; i >= 0; i--) {
          if (((AnimationEvent)this.mEvents.get(i)).mEvent == 1)
            ((AnimationEvent)this.mEvents.get(i)).mNode.mAnimation.skipToEndValue(true); 
        } 
      } else {
        for (byte b = 0; b < this.mEvents.size(); b++) {
          if (((AnimationEvent)this.mEvents.get(b)).mEvent == 2)
            ((AnimationEvent)this.mEvents.get(b)).mNode.mAnimation.skipToEndValue(false); 
        } 
      } 
      return;
    } 
    throw new UnsupportedOperationException("Children must be initialized.");
  }
  
  public void start() {
    start(false, true);
  }
  
  void startWithoutPulsing(boolean paramBoolean) {
    start(paramBoolean, false);
  }
  
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("AnimatorSet@");
    stringBuilder1.append(Integer.toHexString(hashCode()));
    stringBuilder1.append("{");
    String str = stringBuilder1.toString();
    int i = this.mNodes.size();
    for (byte b = 0; b < i; b++) {
      Node node = this.mNodes.get(b);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append("\n    ");
      stringBuilder.append(node.mAnimation.toString());
      str = stringBuilder.toString();
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(str);
    stringBuilder2.append("\n}");
    return stringBuilder2.toString();
  }
  
  private static class AnimationEvent {
    static final int ANIMATION_DELAY_ENDED = 1;
    
    static final int ANIMATION_END = 2;
    
    static final int ANIMATION_START = 0;
    
    final int mEvent;
    
    final AnimatorSet.Node mNode;
    
    AnimationEvent(AnimatorSet.Node param1Node, int param1Int) {
      this.mNode = param1Node;
      this.mEvent = param1Int;
    }
    
    long getTime() {
      int i = this.mEvent;
      if (i == 0)
        return this.mNode.mStartTime; 
      if (i == 1) {
        long l1 = this.mNode.mStartTime;
        long l2 = -1L;
        if (l1 != -1L) {
          l2 = this.mNode.mStartTime;
          l2 = this.mNode.mAnimation.getStartDelay() + l2;
        } 
        return l2;
      } 
      return this.mNode.mEndTime;
    }
    
    public String toString() {
      String str;
      int i = this.mEvent;
      if (i == 0) {
        str = "start";
      } else if (i == 1) {
        str = "delay ended";
      } else {
        str = "end";
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(" ");
      stringBuilder.append(this.mNode.mAnimation.toString());
      return stringBuilder.toString();
    }
  }
  
  public class Builder {
    private AnimatorSet.Node mCurrentNode;
    
    Builder(Animator param1Animator) {
      AnimatorSet.access$402(AnimatorSet.this, true);
      this.mCurrentNode = AnimatorSet.this.getNodeForAnimation(param1Animator);
    }
    
    public Builder after(long param1Long) {
      ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
      valueAnimator.setDuration(param1Long);
      after(valueAnimator);
      return this;
    }
    
    public Builder after(Animator param1Animator) {
      AnimatorSet.Node node = AnimatorSet.this.getNodeForAnimation(param1Animator);
      this.mCurrentNode.addParent(node);
      return this;
    }
    
    public Builder before(Animator param1Animator) {
      AnimatorSet.Node node = AnimatorSet.this.getNodeForAnimation(param1Animator);
      this.mCurrentNode.addChild(node);
      return this;
    }
    
    public Builder with(Animator param1Animator) {
      AnimatorSet.Node node = AnimatorSet.this.getNodeForAnimation(param1Animator);
      this.mCurrentNode.addSibling(node);
      return this;
    }
  }
  
  private static class Node implements Cloneable {
    Animator mAnimation;
    
    ArrayList<Node> mChildNodes = null;
    
    long mEndTime = 0L;
    
    boolean mEnded = false;
    
    Node mLatestParent = null;
    
    ArrayList<Node> mParents;
    
    boolean mParentsAdded = false;
    
    ArrayList<Node> mSiblings;
    
    long mStartTime = 0L;
    
    long mTotalDuration = 0L;
    
    public Node(Animator param1Animator) {
      this.mAnimation = param1Animator;
    }
    
    void addChild(Node param1Node) {
      if (this.mChildNodes == null)
        this.mChildNodes = new ArrayList<>(); 
      if (!this.mChildNodes.contains(param1Node)) {
        this.mChildNodes.add(param1Node);
        param1Node.addParent(this);
      } 
    }
    
    public void addParent(Node param1Node) {
      if (this.mParents == null)
        this.mParents = new ArrayList<>(); 
      if (!this.mParents.contains(param1Node)) {
        this.mParents.add(param1Node);
        param1Node.addChild(this);
      } 
    }
    
    public void addParents(ArrayList<Node> param1ArrayList) {
      if (param1ArrayList == null)
        return; 
      int i = param1ArrayList.size();
      for (byte b = 0; b < i; b++)
        addParent(param1ArrayList.get(b)); 
    }
    
    public void addSibling(Node param1Node) {
      if (this.mSiblings == null)
        this.mSiblings = new ArrayList<>(); 
      if (!this.mSiblings.contains(param1Node)) {
        this.mSiblings.add(param1Node);
        param1Node.addSibling(this);
      } 
    }
    
    public Node clone() {
      try {
        Node node = (Node)super.clone();
        node.mAnimation = this.mAnimation.clone();
        if (this.mChildNodes != null) {
          ArrayList<Node> arrayList = new ArrayList();
          this((Collection)this.mChildNodes);
          node.mChildNodes = arrayList;
        } 
        if (this.mSiblings != null) {
          ArrayList<Node> arrayList = new ArrayList();
          this((Collection)this.mSiblings);
          node.mSiblings = arrayList;
        } 
        if (this.mParents != null) {
          ArrayList<Node> arrayList = new ArrayList();
          this((Collection)this.mParents);
          node.mParents = arrayList;
        } 
        node.mEnded = false;
        return node;
      } catch (CloneNotSupportedException cloneNotSupportedException) {
        throw new AssertionError();
      } 
    }
  }
  
  private class SeekState {
    private long mPlayTime = -1L;
    
    private boolean mSeekingInReverse = false;
    
    private SeekState() {}
    
    long getPlayTime() {
      return this.mPlayTime;
    }
    
    long getPlayTimeNormalized() {
      return AnimatorSet.this.mReversing ? (AnimatorSet.this.getTotalDuration() - AnimatorSet.this.mStartDelay - this.mPlayTime) : this.mPlayTime;
    }
    
    boolean isActive() {
      boolean bool;
      if (this.mPlayTime != -1L) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    void reset() {
      this.mPlayTime = -1L;
      this.mSeekingInReverse = false;
    }
    
    void setPlayTime(long param1Long, boolean param1Boolean) {
      if (AnimatorSet.this.getTotalDuration() != -1L)
        this.mPlayTime = Math.min(param1Long, AnimatorSet.this.getTotalDuration() - AnimatorSet.this.mStartDelay); 
      this.mPlayTime = Math.max(0L, this.mPlayTime);
      this.mSeekingInReverse = param1Boolean;
    }
    
    void updateSeekDirection(boolean param1Boolean) {
      if (!param1Boolean || AnimatorSet.this.getTotalDuration() != -1L) {
        if (this.mPlayTime >= 0L && param1Boolean != this.mSeekingInReverse) {
          this.mPlayTime = AnimatorSet.this.getTotalDuration() - AnimatorSet.this.mStartDelay - this.mPlayTime;
          this.mSeekingInReverse = param1Boolean;
        } 
        return;
      } 
      throw new UnsupportedOperationException("Error: Cannot reverse infinite animator set");
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimatorSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */