package com.chingtech.sample.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import com.chingtech.sample.R;
import com.chingtech.sample.WrapContentHeightViewPager;
import com.chingtech.sample.bean.Card;

import static chingtech.library.utils.ScreenUtils.dp2px;

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
 * MyLibrary
 * Package com.chingtech.sample.view
 * Description:
 * Created by 师春雷
 * Created at 17/12/16 上午9:50
 */
public class SubmitCreditCardActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.text_card_number)
    TextView  textCardNumber;
    @BindView(R.id.text_expired_date)
    TextView  textExpiredDate;
    @BindView(R.id.text_card_holder)
    TextView  textCardHolder;
    @BindView(R.id.text_cvv_code)
    TextView  textCvvCode;
    @BindView(R.id.icon_help_blue)
    ImageView iconHelpBlue;
    @BindView(R.id.card_blue)
    CardView  cardBlue;

    @BindView(R.id.icon_help_gray)
    ImageView iconHelpGray;
    @BindView(R.id.card_gray)
    CardView  cardGray;

    @BindView(R.id.input_edit_card_number)
    TextInputEditText inputEditCardNumber;
    @BindView(R.id.input_edit_expired_date)
    TextInputEditText inputEditExpiredDate;
    @BindView(R.id.input_edit_card_holder)
    TextInputEditText inputEditCardHolder;
    @BindView(R.id.input_edit_cvv_code)
    TextInputEditText inputEditCvvCode;
    @BindView(R.id.input_layout_cvv_code)
    TextInputLayout   inputLayoutCvvCode;

    @BindView(R.id.view_pager)
    WrapContentHeightViewPager viewPager;

    @BindView(R.id.progress_circle)
    ProgressBar progressCircle;
    @BindView(R.id.icon_lock)
    ImageView   iconLock;
    @BindView(R.id.progress_horizontal)
    ProgressBar progressHorizontal;
    @BindView(R.id.label_secure_submission)
    TextView    labelSecureSubmission;

    @BindView(R.id.layout)
    RelativeLayout layout;

    private boolean showingGray = true;
    private AnimatorSet inSet;
    private AnimatorSet outSet;
    private Card        card;

    @Override
    protected void init() {
        card = new Card();

        View.OnClickListener onHelpClickListener = v -> showToast(
                "The CVV Number (\"Card Verification Value\") is a 3 or 4 digit number on your credit and debit cards");

        iconHelpGray.setOnClickListener(onHelpClickListener);
        iconHelpBlue.setOnClickListener(onHelpClickListener);

        inputEditCardNumber.addTextChangedListener(new TextWatcher() {

            private boolean lock;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    flipToBlue();
                }
                textCardNumber.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (lock || s.length() > 16) {
                    return;
                }
                lock = true;
                for (int i = 4; i < s.length(); i += 5) {
                    if (s.toString().charAt(i) != ' ') {
                        s.insert(i, " ");
                    }
                }
                lock = false;
            }
        });

        inputEditExpiredDate.addTextChangedListener(new TextWatcher() {

            private boolean lock;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textExpiredDate.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (lock || s.length() > 4) {
                    return;
                }
                lock = true;
                if (s.length() > 2 && s.toString().charAt(2) != '/') {
                    s.insert(2, "/");
                }
                lock = false;
            }
        });

        inputEditCardHolder.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textCardHolder.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputEditCvvCode.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textCvvCode.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Display display = getWindowManager().getDefaultDisplay();
        Point   size    = new Point();
        display.getSize(size);
        int width  = size.x;
        int height = size.y;

        PagerAdapter adapter = new MyPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(width / 4, 0, width / 4, 0);
        viewPager.setPageMargin(width / 14);
        viewPager.setPagingEnabled(false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                    int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        updateProgressBar(25);
                        inputEditCardNumber.setFocusableInTouchMode(true);
                        inputEditExpiredDate.setFocusable(false);
                        inputEditCardHolder.setFocusable(false);
                        inputEditCvvCode.setFocusable(false);
                        inputEditCardNumber.requestFocus();
                        return;
                    case 1:
                        updateProgressBar(50);
                        inputEditCardNumber.setFocusable(false);
                        inputEditExpiredDate.setFocusableInTouchMode(true);
                        inputEditCardHolder.setFocusable(false);
                        inputEditCvvCode.setFocusable(false);
                        inputEditExpiredDate.requestFocus();
                        return;
                    case 2:
                        updateProgressBar(75);
                        inputEditCardNumber.setFocusable(false);
                        inputEditExpiredDate.setFocusable(false);
                        inputEditCardHolder.setFocusableInTouchMode(true);
                        inputEditCvvCode.setFocusable(false);
                        inputEditCardHolder.requestFocus();
                        return;
                    case 3:
                        updateProgressBar(100);
                        inputEditCardNumber.setFocusable(false);
                        inputEditExpiredDate.setFocusable(false);
                        inputEditCardHolder.setFocusable(false);
                        inputEditCvvCode.setFocusableInTouchMode(true);
                        inputEditCvvCode.requestFocus();
                        return;
                    case 4:
                        inputEditCardNumber.setFocusable(false);
                        inputEditExpiredDate.setFocusable(false);
                        inputEditCardHolder.setFocusable(false);
                        inputEditCvvCode.setFocusable(false);
                        return;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        TextView.OnEditorActionListener onEditorActionListener = (v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                handled = true;
            }
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                submit();
                handled = true;
            }
            return handled;
        };

        inputEditCardNumber.setOnEditorActionListener(onEditorActionListener);
        inputEditExpiredDate.setOnEditorActionListener(onEditorActionListener);
        inputEditCardHolder.setOnEditorActionListener(onEditorActionListener);
        inputEditCvvCode.setOnEditorActionListener(onEditorActionListener);

        inputEditCardNumber.requestFocus();

        inSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.card_flip_in);
        outSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.card_flip_out);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_credit_card;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
    }

    @Override
    protected View injectTarget() {
        return layout;
    }

    @Override
    protected void loadData() {
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int resId = 0;
            switch (position) {
                case 0:
                    resId = R.id.input_layout_card_number;
                    break;
                case 1:
                    resId = R.id.input_layout_expired_date;
                    break;
                case 2:
                    resId = R.id.input_layout_card_holder;
                    break;
                case 3:
                    resId = R.id.input_layout_cvv_code;
                    break;
                case 4:
                    resId = R.id.space;
                    break;

            }
            return findViewById(resId);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }


        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    private void updateProgressBar(int progress) {
        ObjectAnimator animation = ObjectAnimator.ofInt(progressHorizontal, "progress", progress);
        animation.setDuration(300);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }

    private void submit() {
        viewPager.setCurrentItem(4);
        card.setCardNumber(inputEditCardNumber.getText().toString());
        card.setExpiredDate(inputEditExpiredDate.getText().toString());
        card.setCardHolder(inputEditCardHolder.getText().toString());
        card.setCvvCode(inputEditCvvCode.getText().toString());
        Toast.makeText(SubmitCreditCardActivity.this, card.toString(), Toast.LENGTH_LONG).show();

        new Handler().postDelayed(() -> {
            inputLayoutCvvCode.setVisibility(View.INVISIBLE);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                                                 | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
            labelSecureSubmission.setVisibility(View.VISIBLE);
            hideKeyboard(inputEditCvvCode);
            progressCircle.setVisibility(View.VISIBLE);
        }, 300);
    }

    private void reset() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                                             | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        inputLayoutCvvCode.setVisibility(View.VISIBLE);
        progressCircle.setVisibility(View.GONE);
        labelSecureSubmission.setVisibility(View.GONE);
        flipToGray();
        viewPager.setCurrentItem(0);
        inputEditCardNumber.setText("");
        inputEditExpiredDate.setText("");
        inputEditCardHolder.setText("");
        inputEditCvvCode.setText("");
        inputEditCardNumber.requestFocus();
        showKeyboard(inputEditCardNumber);
    }

    private void flipToGray() {
        if (!showingGray && !outSet.isRunning() && !inSet.isRunning()) {
            showingGray = true;

            cardBlue.setCardElevation(0);
            cardGray.setCardElevation(0);

            outSet.setTarget(cardBlue);
            outSet.start();

            inSet.setTarget(cardGray);
            inSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    cardGray.setCardElevation(dp2px(12));
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            inSet.start();
        }
    }

    private void flipToBlue() {
        if (showingGray && !outSet.isRunning() && !inSet.isRunning()) {
            showingGray = false;

            cardGray.setCardElevation(0);
            cardBlue.setCardElevation(0);

            outSet.setTarget(cardGray);
            outSet.start();

            inSet.setTarget(cardBlue);
            inSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    cardBlue.setCardElevation(dp2px(12));
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            inSet.start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu._menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                reset();
                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
