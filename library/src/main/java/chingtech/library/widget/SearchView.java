package chingtech.library.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import chingtech.library.R;
import chingtech.library.utils.AnimatorUitls;

/**
 * 揭露式搜索框
 *
 * @author 2017/6/15 16:54 / changliugang
 */
public class SearchView extends FrameLayout {

    private Context mContext;

    private boolean mIsSearchOpen;// 搜索框是否开启的标识
    // 动画持续时间
    private int duration = AnimatorUitls.ANIMATION_DURATION;
    // 是否清除了焦点的标志
    private boolean mClearingFocus;

    //搜索框输入EditText
    private EditText       mSearchContentEditText;
    // 搜索界面根布局
    private FrameLayout    mSearchLayout;
    // 顶部条，和ToolBar重合
    private RelativeLayout mSearchBarLayout;
    // 建议搜索列表
    private ListView       mSuggestionListView;
    // 返回按钮
    private ImageButton    mBackBtn;
    // 清除按钮
    private ImageButton    mClearBtn;
    // 半透明蒙版层
    private View           mTransparentView;

    // 搜索关键字
    private CharSequence mTheQueryText;
    // 上次搜索的关键字
    private CharSequence mLastQueryText;


    private OnQueryTextListener mOnQueryTextListener;

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        mOnQueryTextListener = onQueryTextListener;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public SearchView(@NonNull Context context) {
        this(context, null);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs,
            @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.search_view, this, true);
        mSearchLayout = (FrameLayout) rootView.findViewById(R.id.search_layout);
        mSearchBarLayout = (RelativeLayout) mSearchLayout.findViewById(R.id.search_bar_layout_rl);
        mSearchContentEditText = (EditText) mSearchLayout.findViewById(R.id.search_edit_text_et);
        mSuggestionListView = (ListView) mSearchLayout.findViewById(R.id.search_suggestion_list_lv);
        mBackBtn = (ImageButton) mSearchLayout.findViewById(R.id.search_home_up_icon_ib);
        mClearBtn = (ImageButton) mSearchLayout.findViewById(R.id.search_clear_icon_ib);
        mTransparentView = mSearchLayout.findViewById(R.id.search_transparent_view);

        mTransparentView.setOnClickListener(mOnClickListener);
        mClearBtn.setOnClickListener(mOnClickListener);
        mBackBtn.setOnClickListener(mOnClickListener);
        mSearchContentEditText.setOnClickListener(mOnClickListener);

        initSearchView();
        mSuggestionListView.setVisibility(GONE);
    }

    /**
     * 配置SearchView的搜索相关操作
     */
    private void initSearchView() {
        /**
         *需要注意的是 setOnEditorActionListener这个方法，并不是在我们点击EditText的时候触发，也
         *不是在我们对EditText进行编辑时触发，而是在我们编辑完之后点击软键盘上的回车键才会触发。
         */
        mSearchContentEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                submitQuery();
                return true;
            }
        });

        mSearchContentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTheQueryText = s;
                doItWhenTextChanged(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSearchContentEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showKeyboard(v);
                }
            }
        });

    }

    /**
     * 显示软键盘
     *
     * @param view 相关控件
     */
    public void showKeyboard(View view) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1 && view.hasFocus()) {
            view.clearFocus();
        }
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) view.getContext()
                                                          .getSystemService(
                                                                  Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    /**
     * 隐藏软键盘
     *
     * @param view 相关控件
     */
    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                                                          .getSystemService(
                                                                  Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 查询关键字有变化时的操作
     *
     * @param newText 新的查询关键字
     */
    private void doItWhenTextChanged(CharSequence newText) {
        CharSequence text = mSearchContentEditText.getText();
        mTheQueryText = text;
        boolean hasQueryText = !TextUtils.isEmpty(text);
        // 搜索文字有变化时，发现其不为空，显示清除按钮，为空，隐藏清除按钮
        if (hasQueryText) {
            mClearBtn.setVisibility(VISIBLE);
        } else {
            mClearBtn.setVisibility(GONE);
        }
        // 回调onQueryTextChange，并刷新查询关键字变量
        if (mOnQueryTextListener != null && !TextUtils.equals(newText, mLastQueryText)) {
            mOnQueryTextListener.onQueryTextChange(newText.toString());
        }
        mLastQueryText = newText;
    }

    /**
     * 提交查询
     */
    private void submitQuery() {
        CharSequence text = mSearchContentEditText.getText();
        // 非空，长度大于0
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            if (mOnQueryTextListener == null || !mOnQueryTextListener.onQueryTextSubmit(
                    text.toString())) {
                mSearchContentEditText.setText(null);
                hideSearch();
            }
        }
    }

    /**
     * 搜索文字监听
     */
    public interface OnQueryTextListener {

        boolean onQueryTextSubmit(String queryText);

        boolean onQueryTextChange(String newText);
    }

    /**
     * 点击事件
     */
    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.search_clear_icon_ib) {
                mSearchContentEditText.setText(null);
            } else if (i == R.id.search_home_up_icon_ib) {
                hideSearch();
            } else if (i == R.id.search_transparent_view) {
                // showSuggestions
            } else if (i == R.id.search_edit_text_et) {
                // hideSearch();
            }
        }
    };

    /**
     * Search是否
     *
     * @return
     */
    public boolean isSearchOpen() {
        return mIsSearchOpen;
    }

    public void showSearch() {
        showSearch(true);
    }

    /**
     * 显示搜索栏
     *
     * @param animate
     */
    public void showSearch(boolean animate) {
        // 如果SearchView此时打开着，不进行后续操作
        if (isSearchOpen()) {
            return;
        }
        // 清空EditText并设置焦点
        mSearchContentEditText.setText(null);
        mSearchContentEditText.requestFocus();

        if (animate) {
            mSearchLayout.setVisibility(VISIBLE);
            showWithAnimation();
        } else {
            mSearchLayout.setVisibility(VISIBLE);
        }
        mIsSearchOpen = true;
    }

    /**
     * 显示动画
     */
    private void showWithAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AnimatorUitls.revealViewToggle(mSearchBarLayout, true);
        } else {
            AnimatorUitls.fadeInView(mSearchLayout);
        }
    }

    /**
     * 隐藏搜索栏
     */
    public void hideSearch() {
        if (!isSearchOpen()) {
            return;
        }

        mSearchContentEditText.setText(null);
        clearFocus();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AnimatorUitls.revealViewToggle(mSearchBarLayout, false);
        } else {
            AnimatorUitls.fadeOutView(mSearchLayout);
        }

        mIsSearchOpen = false;
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        // Don't accept focus if in the middle of clearing focus
        // 正在清除焦点时，不接受焦点
        if (mClearingFocus) {
            return false;
        }
        // Check if SearchView is focusable.
        // 检查SearchView是否可以设置焦点
        if (!isFocusable()) {
            return false;
        }
        return mSearchContentEditText.requestFocus(direction, previouslyFocusedRect);
    }

    @Override
    public void clearFocus() {
        mClearingFocus = true;
        hideKeyboard(this);
        super.clearFocus();
        mSearchContentEditText.clearFocus();
        mClearingFocus = false;
    }

    @Override
    public void setBackground(Drawable background) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mSearchBarLayout.setBackground(background);
        } else {
            mSearchBarLayout.setBackgroundDrawable(background);
        }
    }
}
