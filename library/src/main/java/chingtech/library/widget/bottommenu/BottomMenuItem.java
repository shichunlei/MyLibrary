package chingtech.library.widget.bottommenu;

import android.graphics.drawable.Drawable;

/**
 * Created by Joe on 16/12/10
 */
public class BottomMenuItem {

    private String   title;
    private Drawable drawable;

    public BottomMenuItem(String title, Drawable drawable) {
        this.title = title;
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
