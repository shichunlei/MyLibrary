package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.OnClick;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.LogUtils;
import chingtech.library.utils.StatusBarHelper;
import com.chingtech.sample.FullyGridLayoutManager;
import com.chingtech.sample.R;
import com.chingtech.sample.adapter.GridImageAdapter;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
public class PhotoActivity extends BaseActivity
        implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

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

    @Override
    protected void init() {
        themeId = R.style.picture_default_style;

        rgb_crop.setOnCheckedChangeListener(this);
        rgb_compress.setOnCheckedChangeListener(this);
        rgb_style.setOnCheckedChangeListener(this);
        rgb_photo_mode.setOnCheckedChangeListener(this);

        cb_crop.setOnCheckedChangeListener(this);
        cb_crop_circular.setOnCheckedChangeListener(this);
        cb_compress.setOnCheckedChangeListener(this);

        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4,
                                                                    GridLayoutManager.VERTICAL,
                                                                    false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((position, v) -> {
            if (selectList.size() > 0) {
                LocalMedia media       = selectList.get(position);
                String     pictureType = media.getPictureType();
                int        mediaType   = PictureMimeType.pictureToVideo(pictureType);
                switch (mediaType) {
                    case 1:
                        // 预览图片 可自定长按保存路径
                        PictureSelector.create(PhotoActivity.this)
                                       .externalPicturePreview(position, selectList);
                        break;
                    case 2:
                        // 预览视频
                        PictureSelector.create(PhotoActivity.this)
                                       .externalPictureVideo(media.getPath());
                        break;
                    case 3:
                        // 预览音频
                        PictureSelector.create(PhotoActivity.this)
                                       .externalPictureAudio(media.getPath());
                        break;
                }
            }
        });

        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                   .subscribe(new Observer<Boolean>() {
                       @Override
                       public void onSubscribe(Disposable d) {
                       }

                       @Override
                       public void onNext(Boolean aBoolean) {
                           if (aBoolean) {
                               PictureFileUtils.deleteCacheDirFile(PhotoActivity.this);
                           } else {
                               showToast(getString(R.string.picture_jurisdiction));
                           }
                       }

                       @Override
                       public void onError(Throwable e) {
                       }

                       @Override
                       public void onComplete() {
                       }
                   });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("图片选择");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void loadData() {

    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener
            = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = cb_mode.isChecked();
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(PhotoActivity.this)
                               .openGallery(
                                       chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                               .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                               .maxSelectNum(maxSelectNum)// 最大图片选择数量
                               .minSelectNum(1)// 最小选择数量
                               .imageSpanCount(4)// 每行显示个数
                               .selectionMode(cb_choose_mode.isChecked() ? PictureConfig.MULTIPLE :
                                                      PictureConfig.SINGLE)// 多选 or 单选
                               .previewImage(cb_preview_img.isChecked())// 是否可预览图片
                               .previewVideo(cb_preview_video.isChecked())// 是否可预览视频
                               .enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
                               .compressGrade(
                                       Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                               .isCamera(cb_isCamera.isChecked())// 是否显示拍照按钮
                               //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                               .enableCrop(cb_crop.isChecked())// 是否裁剪
                               .compress(cb_compress.isChecked())// 是否压缩
                               .compressMode(
                                       compressMode)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                               //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                               .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                               .withAspectRatio(aspect_ratio_x,
                                                aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                               .hideBottomControls(
                                       cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
                               .isGif(cb_isGif.isChecked())// 是否显示gif图片
                               .freeStyleCropEnabled(cb_styleCrop.isChecked())// 裁剪框是否可拖拽
                               .circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
                               .showCropFrame(
                                       cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                               .showCropGrid(
                                       cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                               .openClickSound(cb_voice.isChecked())// 是否开启点击声音
                               //                               .selectionMedia(selectList)// 是否传入已选图片
                               //.videoMaxSecond(15)
                               //.videoMinSecond(10)
                               //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                               //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                               //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                               //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                               //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                               //.rotateEnabled() // 裁剪是否可旋转图片
                               //.scaleEnabled()// 裁剪是否可放大缩小图片
                               //.videoQuality()// 视频录制质量 0 or 1
                               //.videoSecond()//显示多少秒以内的视频or音频也可适用
                               //.recordVideoSecond()//录制视频秒数 默认60s
                               .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            } else {
                // 单独拍照
                PictureSelector.create(PhotoActivity.this)
                               .openCamera(chooseMode)// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                               .theme(themeId)// 主题样式设置 具体参考 values/styles
                               .maxSelectNum(maxSelectNum)// 最大图片选择数量
                               .minSelectNum(1)// 最小选择数量
                               .selectionMode(cb_choose_mode.isChecked() ? PictureConfig.MULTIPLE :
                                                      PictureConfig.SINGLE)// 多选 or 单选
                               .previewImage(cb_preview_img.isChecked())// 是否可预览图片
                               .previewVideo(cb_preview_video.isChecked())// 是否可预览视频
                               .enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
                               .compressGrade(
                                       Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                               .isCamera(cb_isCamera.isChecked())// 是否显示拍照按钮
                               .enableCrop(cb_crop.isChecked())// 是否裁剪
                               .compress(cb_compress.isChecked())// 是否压缩
                               .compressMode(
                                       compressMode)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                               .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                               .withAspectRatio(aspect_ratio_x,
                                                aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                               .hideBottomControls(
                                       cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
                               .isGif(cb_isGif.isChecked())// 是否显示gif图片
                               .freeStyleCropEnabled(cb_styleCrop.isChecked())// 裁剪框是否可拖拽
                               .circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
                               .showCropFrame(
                                       cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                               .showCropGrid(
                                       cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                               .openClickSound(cb_voice.isChecked())// 是否开启点击声音
                               .selectionMedia(selectList)// 是否传入已选图片
                               .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                               //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                               //.cropCompressQuality(90)// 裁剪压缩质量 默认为100
                               //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                               //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                               //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                               //.rotateEnabled() // 裁剪是否可旋转图片
                               //.scaleEnabled()// 裁剪是否可放大缩小图片
                               //.videoQuality()// 视频录制质量 0 or 1
                               //.videoSecond()////显示多少秒以内的视频or音频也可适用
                               .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    LogUtils.i("TAG", "onActivityResult:" + selectList.toString());
                    break;
            }
        }
    }

    @OnClick({R.id.minus, R.id.plus})
    public void onEvent(View v) {
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
                cb_preview_video.setChecked(true);
                cb_preview_img.setChecked(true);
                cb_preview_video.setVisibility(View.VISIBLE);
                cb_preview_img.setVisibility(View.VISIBLE);
                cb_compress.setVisibility(View.VISIBLE);
                cb_crop.setVisibility(View.VISIBLE);
                cb_isGif.setVisibility(View.VISIBLE);
                cb_preview_audio.setVisibility(View.GONE);
                break;
            case R.id.rb_image:
                chooseMode = PictureMimeType.ofImage();
                cb_preview_img.setChecked(true);
                cb_preview_video.setChecked(false);
                cb_isGif.setChecked(false);
                cb_preview_video.setChecked(false);
                cb_preview_video.setVisibility(View.GONE);
                cb_preview_img.setChecked(true);
                cb_preview_audio.setVisibility(View.GONE);
                cb_preview_img.setVisibility(View.VISIBLE);
                cb_compress.setVisibility(View.VISIBLE);
                cb_crop.setVisibility(View.VISIBLE);
                cb_isGif.setVisibility(View.VISIBLE);
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
                cb_preview_img.setChecked(false);
                cb_compress.setVisibility(View.GONE);
                cb_preview_audio.setVisibility(View.GONE);
                cb_crop.setVisibility(View.GONE);
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
