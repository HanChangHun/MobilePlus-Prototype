package android.animation;

class SeekState {
  private long mPlayTime = -1L;
  
  private boolean mSeekingInReverse = false;
  
  private SeekState() {}
  
  long getPlayTime() {
    return this.mPlayTime;
  }
  
  long getPlayTimeNormalized() {
    return AnimatorSet.access$300(AnimatorSet.this) ? (AnimatorSet.this.getTotalDuration() - AnimatorSet.access$200(AnimatorSet.this) - this.mPlayTime) : this.mPlayTime;
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
  
  void setPlayTime(long paramLong, boolean paramBoolean) {
    if (AnimatorSet.this.getTotalDuration() != -1L)
      this.mPlayTime = Math.min(paramLong, AnimatorSet.this.getTotalDuration() - AnimatorSet.access$200(AnimatorSet.this)); 
    this.mPlayTime = Math.max(0L, this.mPlayTime);
    this.mSeekingInReverse = paramBoolean;
  }
  
  void updateSeekDirection(boolean paramBoolean) {
    if (!paramBoolean || AnimatorSet.this.getTotalDuration() != -1L) {
      if (this.mPlayTime >= 0L && paramBoolean != this.mSeekingInReverse) {
        this.mPlayTime = AnimatorSet.this.getTotalDuration() - AnimatorSet.access$200(AnimatorSet.this) - this.mPlayTime;
        this.mSeekingInReverse = paramBoolean;
      } 
      return;
    } 
    throw new UnsupportedOperationException("Error: Cannot reverse infinite animator set");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/animation/AnimatorSet$SeekState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */