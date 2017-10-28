package com.chingtech.sample.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import chingtech.library.base.fragment.BaseFragment;
import chingtech.library.utils.LogUtils;
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
public class PhotoFragment extends BaseFragment
        implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private List<LocalMedia> selectList = new ArrayList<>();

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private GridImageAdapter adapter;
    private int maxSelectNum = 9;
    @BindView(R.id.tv_select_num)
    TextView tv_select_num;

    @BindView(R.id.rgb_crop)
    RadioGroup rgb_crop;
    @BindView(R.id.rgb_style)
    RadioGroup rgb_style;
    @BindView(R.id.rgb_photo_mode)
    RadioGroup rgb_photo_mode;
    @BindView(R.id.rgb_compress)
    RadioGroup rgb_compress;

    private int aspect_ratio_x, aspect_ratio_y;

    @BindView(R.id.cb_voice)
    CheckBox cb_voice;
    @BindView(R.id.cb_choose_mode)
    CheckBox cb_choose_mode;
    @BindView(R.id.cb_isCamera)
    CheckBox cb_isCamera;
    @BindView(R.id.cb_isGif)
    CheckBox cb_isGif;
    @BindView(R.id.cb_preview_img)
    CheckBox cb_preview_img;
    @BindView(R.id.cb_preview_video)
    CheckBox cb_preview_video;
    @BindView(R.id.cb_crop)
    CheckBox cb_crop;
    @BindView(R.id.cb_compress)
    CheckBox cb_compress;
    @BindView(R.id.cb_mode)
    CheckBox cb_mode;
    @BindView(R.id.cb_hide)
    CheckBox cb_hide;
    @BindView(R.id.cb_preview_audio)
    CheckBox cb_preview_audio;
    @BindView(R.id.cb_crop_circular)
    CheckBox cb_crop_circular;
    @BindView(R.id.cb_styleCrop)
    CheckBox cb_styleCrop;
    @BindView(R.id.cb_showCropGrid)
    CheckBox cb_showCropGrid;
    @BindView(R.id.cb_showCropFrame)
    CheckBox cb_showCropFrame;

    private int compressMode = PictureConfig.SYSTEM_COMPRESS_MODE;
    private int themeId;
    private int chooseMode = PictureMimeType.ofAll();

    private int x = 0, y = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.content_photo;
    }

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
                    LogUtils.i("TAG", "onActivityResult:" + selectList.size());
                    break;
            }
        }
    }

    @OnClick({R.id.minus, R.id.plus})
    public void onClick(View v) {
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
