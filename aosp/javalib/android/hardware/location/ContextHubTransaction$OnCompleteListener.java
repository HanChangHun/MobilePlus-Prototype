package android.hardware.location;

@FunctionalInterface
public interface OnCompleteListener<L> {
  void onComplete(ContextHubTransaction<L> paramContextHubTransaction, ContextHubTransaction.Response<L> paramResponse);
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/ContextHubTransaction$OnCompleteListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */