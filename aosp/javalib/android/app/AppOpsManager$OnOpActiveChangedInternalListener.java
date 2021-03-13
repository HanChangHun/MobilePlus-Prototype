package android.app;

public interface OnOpActiveChangedInternalListener extends AppOpsManager.OnOpActiveChangedListener {
  default void onOpActiveChanged(int paramInt1, int paramInt2, String paramString, boolean paramBoolean) {}
  
  default void onOpActiveChanged(String paramString1, int paramInt, String paramString2, boolean paramBoolean) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$OnOpActiveChangedInternalListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */