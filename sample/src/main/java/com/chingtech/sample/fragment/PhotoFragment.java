package com.chingtech.sample.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import chingtech.library.base.fragment.BaseFragment;
import chingtech.library.widget.StateView;
import com.chingtech.sample.R;
import com.chingtech.sample.adapter.GridImageAdapter;
import com.chingtech.sample.FullyGridLayoutManager;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

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
 * Created at 17/8/18 上午11:39
 */
@ContentView(R.layout.content_photo)
public class PhotoFragment extends BaseFragment
        implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private List<LocalMedia> selectList = new ArrayList<>();

    @ViewInject(R.id.recycler)
    private RecyclerView     recyclerView;
    private GridImageAdapter adapter;
    private int maxSelectNum = 9;
    @ViewInject(R.id.tv_select_num)
    private TextView tv_select_num;

    @ViewInject(R.id.rgb_crop)
    private RadioGroup rgb_crop;
    @ViewInject(R.id.rgb_style)
    private RadioGroup rgb_style;
    @ViewInject(R.id.rgb_photo_mode)
    private RadioGroup rgb_photo_mode;
    @ViewInject(R.id.rgb_compress)
    private RadioGroup rgb_compress;

    private int aspect_ratio_x, aspect_ratio_y;

    @ViewInject(R.id.cb_voice)
    private CheckBox cb_voice;
    @ViewInject(R.id.cb_choose_mode)
    private CheckBox cb_choose_mode;
    @ViewInject(R.id.cb_isCamera)
    private CheckBox cb_isCamera;
    @ViewInject(R.id.cb_isGif)
    private CheckBox cb_isGif;
    @ViewInject(R.id.cb_preview_img)
    private CheckBox cb_preview_img;
    @ViewInject(R.id.cb_preview_video)
    private CheckBox cb_preview_video;
    @ViewInject(R.id.cb_crop)
    private CheckBox cb_crop;
    @ViewInject(R.id.cb_compress)
    private CheckBox cb_compress;
    @ViewInject(R.id.cb_mode)
    private CheckBox cb_mode;
    @ViewInject(R.id.cb_hide)
    private CheckBox cb_hide;
    @ViewInject(R.id.cb_preview_audio)
    private CheckBox cb_preview_audio;
    @ViewInject(R.id.cb_crop_circular)
    private CheckBox cb_crop_circular;
    @ViewInject(R.id.cb_styleCrop)
    private CheckBox cb_styleCrop;
    @ViewInject(R.id.cb_showCropGrid)
    private CheckBox cb_showCropGrid;
    @ViewInject(R.id.cb_showCropFrame)
    private CheckBox cb_showCropFrame;

    private int compressMode = PictureConfig.SYSTEM_COMPRESS_MODE;
    private int themeId;
    private int chooseMode = PictureMimeType.ofAll();

    @Override
    public void init() {
        mStateView.showEmpty();
        mStateView.showLoading();
        new Handler().postDelayed(() -> mStateView.showRetry(), 3000);

        mStateView.setOnRetryClickListener(() -> {
            new Handler().postDelayed(() -> mStateView.showContent(), 3000);
        });

        themeId = R.style.picture_default_style;

        rgb_crop.setOnCheckedChangeListener(this);
        rgb_compress.setOnCheckedChangeListener(this);
        rgb_style.setOnCheckedChangeListener(this);
        rgb_photo_mode.setOnCheckedChangeListener(this);

        cb_crop.setOnCheckedChangeListener(this);
        cb_crop_circular.setOnCheckedChangeListener(this);
        cb_compress.setOnCheckedChangeListener(this);

        FullyGridLayoutManager manager = new FullyGridLayoutManager(getActivity(), 4,
                                                                    GridLayoutManager.VERTICAL,
                                                                    false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(getActivity(), onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((position, v) -> {
            LocalMedia media       = selectList.get(position);
            String     pictureType = media.getPictureType();
            int        mediaType   = PictureMimeType.pictureToVideo(pictureType);
            switch (mediaType) {
                case 1:
                    // 预览图片
                    PictureSelector.create(PhotoFragment.this)
                                   .externalPicturePreview(position, selectList);
                    break;
                case 2:
                    // 预览视频
                    PictureSelector.create(PhotoFragment.this)
                                   .externalPictureVideo(media.getPath());
                    break;
                case 3:
                    // 预览音频
                    PictureSelector.create(PhotoFragment.this)
                                   .externalPictureAudio(media.getPath());
                    break;
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener
            = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = cb_mode.isChecked();
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(PhotoFragment.this)
                               .openGallery(chooseMode)
                               .theme(themeId)
                               .maxSelectNum(maxSelectNum)
                               .minSelectNum(1)
                               .selectionMode(cb_choose_mode.isChecked() ? PictureConfig.MULTIPLE :
                                                      PictureConfig.SINGLE)
                               .previewImage(cb_preview_img.isChecked())
                               .previewVideo(cb_preview_video.isChecked())
                               .enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
                               .compressGrade(Luban.THIRD_GEAR)
                               .isCamera(cb_isCamera.isChecked())
                               .enableCrop(cb_crop.isChecked())
                               .compress(cb_compress.isChecked())
                               .compressMode(compressMode)
                               .glideOverride(160, 160)
                               .previewEggs(true)
                               .withAspectRatio(aspect_ratio_x, aspect_ratio_y)
                               .hideBottomControls(cb_hide.isChecked() ? false : true)
                               .isGif(cb_isGif.isChecked())
                               .freeStyleCropEnabled(cb_styleCrop.isChecked())
                               .circleDimmedLayer(cb_crop_circular.isChecked())
                               .showCropFrame(cb_showCropFrame.isChecked())
                               .showCropGrid(cb_showCropGrid.isChecked())
                               .openClickSound(cb_voice.isChecked())
                               .selectionMedia(selectList)
                               .forResult(PictureConfig.CHOOSE_REQUEST);
            } else {
                // 单独拍照
                PictureSelector.create(PhotoFragment.this)
                               .openCamera(chooseMode)
                               .theme(themeId)
                               .maxSelectNum(maxSelectNum)
                               .minSelectNum(1)
                               .selectionMode(cb_choose_mode.isChecked() ? PictureConfig.MULTIPLE :
                                                      PictureConfig.SINGLE)
                               .previewImage(cb_preview_img.isChecked())
                               .previewVideo(cb_preview_video.isChecked())
                               .enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
                               .compressGrade(Luban.THIRD_GEAR)
                               .isCamera(cb_isCamera.isChecked())
                               .enableCrop(cb_crop.isChecked())
                               .compress(cb_compress.isChecked())
                               .compressMode(compressMode)
                               .glideOverride(160, 160)
                               .withAspectRatio(aspect_ratio_x, aspect_ratio_y)
                               .hideBottomControls(cb_hide.isChecked() ? false : true)
                               .isGif(cb_isGif.isChecked())
                               .freeStyleCropEnabled(cb_styleCrop.isChecked())
                               .circleDimmedLayer(cb_crop_circular.isChecked())
                               .showCropFrame(cb_showCropFrame.isChecked())
                               .showCropGrid(cb_showCropGrid.isChecked())
                               .openClickSound(cb_voice.isChecked())
                               .selectionMedia(selectList)
                               .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    selectList = PictureSelector.obtainMultipleResult(data);
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    Log.i("TAG", "onActivityResult:" + selectList.size());
                    break;
            }
        }
    }

    @Event({R.id.minus, R.id.plus})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.minus:
                if (maxSelectNum > 1) {
                    maxSelectNum--;
                }
                tv_select_num.setText(maxSelectNum + "");
                adapter.setSelectMax(maxSelectNum);
                break;
            case R.id.plus:
                maxSelectNum++;
                tv_select_num.setText(maxSelectNum + "");
                adapter.setSelectMax(maxSelectNum);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_all:
                chooseMode = PictureMimeType.ofAll();
                cb_preview_img.setChecked(true);
                cb_preview_video.setChecked(true);
                cb_isGif.setChecked(false);
                cb_isGif.setVisibility(View.GONE);
                cb_preview_video.setChecked(true);
                cb_preview_img.setChecked(true);
                cb_preview_video.setVisibility(View.VISIBLE);
                cb_preview_img.setVisibility(View.VISIBLE);
                cb_preview_audio.setVisibility(View.GONE);
                break;
            case R.id.rb_image:
                chooseMode = PictureMimeType.ofImage();
                cb_preview_img.setChecked(true);
                cb_preview_video.setChecked(false);
                cb_isGif.setChecked(false);
                cb_isGif.setVisibility(View.VISIBLE);
                cb_preview_video.setChecked(false);
                cb_preview_video.setVisibility(View.GONE);
                cb_preview_img.setChecked(true);
                cb_preview_img.setVisibility(View.VISIBLE);
                cb_preview_audio.setVisibility(View.GONE);
                break;
            case R.id.rb_video:
                chooseMode = PictureMimeType.ofVideo();
                cb_preview_img.setChecked(false);
                cb_preview_video.setChecked(true);
                cb_isGif.setChecked(false);
                cb_isGif.setVisibility(View.GONE);
                cb_preview_video.setChecked(true);
                cb_preview_video.setVisibility(View.VISIBLE);
                cb_preview_img.setVisibility(View.GONE);
                cb_preview_audio.setVisibility(View.GONE);
                cb_preview_img.setChecked(false);
                break;
            case R.id.rb_audio:
                chooseMode = PictureMimeType.ofAudio();
                cb_preview_audio.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_crop_default:
                aspect_ratio_x = 0;
                aspect_ratio_y = 0;
                break;
            case R.id.rb_crop_1to1:
                aspect_ratio_x = 1;
                aspect_ratio_y = 1;
                break;
            case R.id.rb_crop_3to4:
                aspect_ratio_x = 3;
                aspect_ratio_y = 4;
                break;
            case R.id.rb_crop_3to2:
                aspect_ratio_x = 3;
                aspect_ratio_y = 2;
                break;
            case R.id.rb_crop_16to9:
                aspect_ratio_x = 16;
                aspect_ratio_y = 9;
                break;
            case R.id.rb_compress_system:
                compressMode = PictureConfig.SYSTEM_COMPRESS_MODE;
                break;
            case R.id.rb_compress_luban:
                compressMode = PictureConfig.LUBAN_COMPRESS_MODE;
                break;
            case R.id.rb_default_style:
                themeId = R.style.picture_default_style;
                break;
            case R.id.rb_white_style:
                themeId = R.style.picture_white_style;
                break;
            case R.id.rb_num_style:
                themeId = R.style.picture_QQ_style;
                break;
            case R.id.rb_sina_style:
                themeId = R.style.picture_Sina_style;
                break;
            case R.id.rb_custom_style:
                themeId = R.style.picture_custom_style;
                break;
        }
    }

    private int x = 0, y = 0;

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_crop:
                rgb_crop.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                cb_hide.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                cb_crop_circular.setVisibility(isChecked ? View.VISIBLE : View.GONE);

                cb_styleCrop.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                cb_showCropFrame.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                cb_showCropGrid.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                break;
            case R.id.cb_crop_circular:
                if (isChecked) {
                    x = aspect_ratio_x;
                    y = aspect_ratio_y;
                    aspect_ratio_x = 1;
                    aspect_ratio_y = 1;
                } else {
                    aspect_ratio_x = x;
                    aspect_ratio_y = y;
                }
                rgb_crop.setVisibility(isChecked ? View.GONE : View.VISIBLE);
                if (isChecked) {
                    cb_showCropFrame.setChecked(false);
                    cb_showCropGrid.setChecked(false);
                } else {
                    cb_showCropFrame.setChecked(true);
                    cb_showCropGrid.setChecked(true);
                }
                break;
            case R.id.cb_compress:
                rgb_compress.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                break;
        }
    }
}
