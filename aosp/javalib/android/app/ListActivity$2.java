package android.app;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

class null implements AdapterView.OnItemClickListener {
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
    ListActivity.this.onListItemClick((ListView)paramAdapterView, paramView, paramInt, paramLong);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ListActivity$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */