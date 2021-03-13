package android.animation;

import java.util.ArrayList;
import java.util.Collection;

class Node implements Cloneable {
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
  
  public Node(Animator paramAnimator) {
    this.mAnimation = paramAnimator;
  }
  
  void addChild(Node paramNode) {
    if (this.mChildNodes == null)
      this.mChildNodes = new ArrayList<>(); 
    if (!this.mChildNodes.contains(paramNode)) {
      this.mChildNodes.add(paramNode);
      paramNode.addParent(this);
    } 
  }
  
  public void addParent(Node paramNode) {
    if (this.mParents == null)
      this.mParents = new ArrayList<>(); 
    if (!this.mParents.contains(paramNode)) {
      this.mParents.add(paramNode);
      paramNode.addChild(this);
    } 
  }
  
  public void addParents(ArrayList<Node> paramArrayList) {
    if (paramArrayList == null)
      return; 
    int i = paramArrayList.size();
    for (byte b = 0; b < i; b++)
      addParent(paramArrayList.get(b)); 
  }
  
  public void addSibling(Node paramNode) {
    if (this.mSiblings == null)
      this.mSiblings = new ArrayList<>(); 
    if (!this.mSiblings.contains(paramNode)) {
      this.mSiblings.add(paramNode);
      paramNode.addSibling(this);
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


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimatorSet$Node.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */