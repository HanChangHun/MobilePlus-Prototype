package android.accounts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class AccountArrayAdapter extends ArrayAdapter<ChooseAccountActivity.AccountInfo> {
  private ChooseAccountActivity.AccountInfo[] mInfos;
  
  private LayoutInflater mLayoutInflater;
  
  public AccountArrayAdapter(Context paramContext, int paramInt, ChooseAccountActivity.AccountInfo[] paramArrayOfAccountInfo) {
    super(paramContext, paramInt, (Object[])paramArrayOfAccountInfo);
    this.mInfos = paramArrayOfAccountInfo;
    this.mLayoutInflater = (LayoutInflater)paramContext.getSystemService("layout_inflater");
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    ChooseAccountActivity.ViewHolder viewHolder1;
    ChooseAccountActivity.ViewHolder viewHolder2;
    if (paramView == null) {
      View view = this.mLayoutInflater.inflate(17367120, null);
      viewHolder1 = new ChooseAccountActivity.ViewHolder(null);
      viewHolder1.text = (TextView)view.findViewById(16908694);
      viewHolder1.icon = (ImageView)view.findViewById(16908693);
      view.setTag(viewHolder1);
    } else {
      ChooseAccountActivity.ViewHolder viewHolder = (ChooseAccountActivity.ViewHolder)viewHolder1.getTag();
      viewHolder2 = viewHolder1;
      viewHolder1 = viewHolder;
    } 
    viewHolder1.text.setText((this.mInfos[paramInt]).name);
    viewHolder1.icon.setImageDrawable((this.mInfos[paramInt]).drawable);
    return (View)viewHolder2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accounts/ChooseAccountActivity$AccountArrayAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */