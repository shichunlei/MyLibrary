package com.chingtech.sample.view;

import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;

import android.view.View;
import android.widget.CheckBox;

import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import com.chingtech.sample.R;
import com.larswerkman.lobsterpicker.OnColorListener;
import com.larswerkman.lobsterpicker.sliders.LobsterShadeSlider;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import chingtech.library.widget.WaveLoadingView;

/**
 * MyLibrary
 * Package com.chingtech.library.view
 * Description:
 * Created by 师春雷
 * Created at 2017/5/27 13:54
 */
public class WaveLoadView extends BaseActivity {

    @BindView(R.id.waveLoadingView)
    WaveLoadingView mWaveLoadingView;

    private int checkedItem = 0;

    @Override
    protected void init() {

        // Sets the length of the animation, default is 1000.
        mWaveLoadingView.setAnimDuration(3000);

        // Shape Type
        findViewById(R.id.tv_shape).setOnClickListener(
                view -> new AlertDialog.Builder(WaveLoadView.this).setTitle("Shape Type")
                                                                  .setSingleChoiceItems(
                                                                          new String[] {"CIRCLE",
                                                                                  "TRIANGLE",
                                                                                  "SQUARE",
                                                                                  "RECTANGLE"},
                                                                          checkedItem,
                                                                          (dialog, which) -> {
                                                                              checkedItem = which;
                                                                              switch (which) {
                                                                                  case 0:
                                                                                      mWaveLoadingView
                                                                                              .setShapeType(
                                                                                                      WaveLoadingView.ShapeType.CIRCLE);
                                                                                      dialog.dismiss();
                                                                                      break;
                                                                                  case 1:
                                                                                      mWaveLoadingView
                                                                                              .setShapeType(
                                                                                                      WaveLoadingView.ShapeType.TRIANGLE);
                                                                                      dialog.dismiss();
                                                                                      break;
                                                                                  case 2:
                                                                                      mWaveLoadingView
                                                                                              .setShapeType(
                                                                                                      WaveLoadingView.ShapeType.SQUARE);
                                                                                      dialog.dismiss();
                                                                                      break;
                                                                                  case 3:
                                                                                      mWaveLoadingView
                                                                                              .setShapeType(
                                                                                                      WaveLoadingView.ShapeType.RECTANGLE);
                                                                                      dialog.dismiss();
                                                                                      break;
                                                                                  default:
                                                                                      dialog.dismiss();
                                                                                      break;
                                                                              }
                                                                          })
                                                                  .show());

        // Animator
        ((CheckBox) findViewById(R.id.cb_animator_cancel_and_start)).setOnCheckedChangeListener(
                (compoundButton, b) -> {
                    if (b) {
                        mWaveLoadingView.cancelAnimation();
                    } else {
                        mWaveLoadingView.startAnimation();
                    }
                });

        ((CheckBox) findViewById(R.id.cb_animator_pause_and_resume)).setOnCheckedChangeListener(
                (compoundButton, b) -> {
                    if (b) {
                        mWaveLoadingView.pauseAnimation();
                    } else {
                        mWaveLoadingView.resumeAnimation();
                    }
                });

        // Top Title
        ((CheckBox) findViewById(R.id.cb_title_top)).setOnCheckedChangeListener(
                (compoundButton, b) -> {
                    if (b) {
                        mWaveLoadingView.setTopTitle("Top Title");
                    } else {
                        mWaveLoadingView.setTopTitle("");
                    }
                });
        // Center Title
        ((CheckBox) findViewById(R.id.cb_title_center)).setOnCheckedChangeListener(
                (compoundButton, b) -> {
                    if (b) {
                        mWaveLoadingView.setCenterTitle("Center Title");
                    } else {
                        mWaveLoadingView.setCenterTitle("");
                    }
                });
        // Bottom Title
        ((CheckBox) findViewById(R.id.cb_title_bottom)).setOnCheckedChangeListener(
                (compoundButton, b) -> {
                    if (b) {
                        mWaveLoadingView.setBottomTitle("Bottom Title");
                    } else {
                        mWaveLoadingView.setBottomTitle("");
                    }
                });

        // Progress
        ((DiscreteSeekBar) findViewById(R.id.seekbar_progress)).setOnProgressChangeListener(
                new DiscreteSeekBar.OnProgressChangeListener() {
                    @Override
                    public void onProgressChanged(DiscreteSeekBar seekBar, int value,
                            boolean fromUser) {
                        mWaveLoadingView.setProgressValue(value);
                    }

                    @Override
                    public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

                    }
                });

        // Border
        ((DiscreteSeekBar) findViewById(R.id.seekbar_border_width)).setOnProgressChangeListener(
                new DiscreteSeekBar.OnProgressChangeListener() {
                    @Override
                    public void onProgressChanged(DiscreteSeekBar seekBar, int value,
                            boolean fromUser) {
                        mWaveLoadingView.setBorderWidth(value);
                    }

                    @Override
                    public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                    }
                });

        // Amplitude
        ((DiscreteSeekBar) findViewById(R.id.seek_bar_amplitude)).setOnProgressChangeListener(
                new DiscreteSeekBar.OnProgressChangeListener() {
                    @Override
                    public void onProgressChanged(DiscreteSeekBar seekBar, int value,
                            boolean fromUser) {
                        mWaveLoadingView.setAmplitudeRatio(value);
                    }

                    @Override
                    public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                    }
                });

        // Wave Color
        ((LobsterShadeSlider) findViewById(R.id.shadeslider_wave_color)).addOnColorListener(
                new OnColorListener() {
                    @Override
                    public void onColorChanged(@ColorInt int color) {
                        mWaveLoadingView.setWaveColor(color);
                    }

                    @Override
                    public void onColorSelected(@ColorInt int color) {
                    }
                });
        //Wave Background Color
        ((LobsterShadeSlider) findViewById(
                R.id.shadeslider_wave_background_color)).addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(@ColorInt int color) {
                mWaveLoadingView.setWaveBgColor(color);
            }

            @Override
            public void onColorSelected(@ColorInt int color) {
            }
        });

        // Border Color
        ((LobsterShadeSlider) findViewById(R.id.shadeslider_border_color)).addOnColorListener(
                new OnColorListener() {
                    @Override
                    public void onColorChanged(@ColorInt int color) {
                        mWaveLoadingView.setBorderColor(color);
                    }

                    @Override
                    public void onColorSelected(@ColorInt int color) {
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.wave_load_view;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void loadData() {

    }
}
