package chingtech.library.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import chingtech.library.R;
import java.io.IOException;
import java.net.URL;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * Health-Old
 * Package com.chingtech.library.utils
 * Description:
 * Created by 师春雷
 * Created at 17/11/7 上午10:25
 */
public class DownloadImageTask extends AsyncTask<String, Void, Drawable> {

    private ViewGroup viewGroup;
    private Context   context;

    public DownloadImageTask(Context context, ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        this.context = context;
    }

    protected Drawable doInBackground(String... urls) {
        return loadImageFromNetwork(urls[0]);
    }

    protected void onPostExecute(Drawable result) {
        if (null != result) {
            viewGroup.setBackground(result);
        } else {
            viewGroup.setBackground(ContextCompat.getDrawable(context, R.drawable.retry));
        }
    }

    public Drawable loadImageFromNetwork(String imageUrl) {
        Drawable drawable = null;
        try {
            // 可以在这里通过第二个参数(文件名)来判断，是否本地有此图片
            drawable = Drawable.createFromStream(new URL(imageUrl).openStream(), null);
        } catch (IOException e) {
            e.getMessage();
        }
        return drawable;
    }
}
