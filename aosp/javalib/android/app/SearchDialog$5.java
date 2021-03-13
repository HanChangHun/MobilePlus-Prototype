package android.app;

import android.widget.SearchView;

class null implements SearchView.OnSuggestionListener {
  public boolean onSuggestionClick(int paramInt) {
    SearchDialog.this.dismiss();
    return false;
  }
  
  public boolean onSuggestionSelect(int paramInt) {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SearchDialog$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */