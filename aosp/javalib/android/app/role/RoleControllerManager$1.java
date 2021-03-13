package android.app.role;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.android.internal.infra.ServiceConnector;
import java.util.function.Function;

class null extends ServiceConnector.Impl<IRoleController> {
  null(Context paramContext, Intent paramIntent, int paramInt1, int paramInt2, Function paramFunction) {
    super(paramContext, paramIntent, paramInt1, paramInt2, paramFunction);
  }
  
  protected Handler getJobHandler() {
    return handler;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/RoleControllerManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */