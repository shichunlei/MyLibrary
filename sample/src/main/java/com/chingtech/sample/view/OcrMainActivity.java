package com.chingtech.sample.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.LogUtils;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.AlertDialog;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.chingtech.sample.R;
import com.chingtech.sample.service.RecognizeService;

import static com.chingtech.sample.FileUtil.getSaveFile;

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
 * Created at 17/12/2 下午1:40
 */
public class OcrMainActivity extends BaseActivity {

    private static final int REQUEST_CODE_GENERAL          = 105;
    private static final int REQUEST_CODE_GENERAL_BASIC    = 106;
    private static final int REQUEST_CODE_ACCURATE_BASIC   = 107;
    private static final int REQUEST_CODE_ACCURATE         = 108;
    private static final int REQUEST_CODE_GENERAL_ENHANCED = 109;
    private static final int REQUEST_CODE_GENERAL_WEBIMAGE = 110;
    private static final int REQUEST_CODE_BANKCARD         = 111;
    private static final int REQUEST_CODE_VEHICLE_LICENSE  = 120;
    private static final int REQUEST_CODE_DRIVING_LICENSE  = 121;
    private static final int REQUEST_CODE_LICENSE_PLATE    = 122;
    private static final int REQUEST_CODE_BUSINESS_LICENSE = 123;
    private static final int REQUEST_CODE_RECEIPT          = 124;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar  toolbar;

    @BindView(R.id.scroll_content)
    NestedScrollView scrollView;

    private boolean hasGotToken = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_ocr;
    }

    @Override
    protected void init() {
        // 请选择您的初始化方式
        initAccessToken();
        // initAccessTokenWithAkSk();
    }

    private void initAccessTokenWithAkSk() {
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
                LogUtils.i("TAG", "onResult: " + token);
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                showDialog(context, "AK，SK方式获取token失败" + error.getMessage());
            }
        }, this, "你的AK", "你的SK");
    }

    private void initAccessToken() {
        OCR.getInstance().initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                LogUtils.i("TAG", "onResult: " + token);
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                showDialog(context, "licence方式获取token失败" + error.getMessage());
            }
        }, this);
    }

    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            showDialog(this, "token还未成功获取");
        }
        return hasGotToken;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("文字识别");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return scrollView;
    }

    @Override
    protected void loadData() {
    }

    private void infoPopText(final String result) {
        showDialog(this, result);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initAccessToken();
        } else {
            showDialog(this, "需要android.permission.READ_PHONE_STATE");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 识别成功回调，通用文字识别（含位置信息）
        if (requestCode == REQUEST_CODE_GENERAL && resultCode == Activity.RESULT_OK) {
            RecognizeService.recGeneral(getSaveFile(getApplicationContext()).getAbsolutePath(),
                                        result -> infoPopText(result));
        }

        // 识别成功回调，通用文字识别（含位置信息高精度版）
        if (requestCode == REQUEST_CODE_ACCURATE && resultCode == Activity.RESULT_OK) {
            RecognizeService.recAccurate(getSaveFile(getApplicationContext()).getAbsolutePath(),
                                         result -> infoPopText(result));
        }

        // 识别成功回调，通用文字识别
        if (requestCode == REQUEST_CODE_GENERAL_BASIC && resultCode == Activity.RESULT_OK) {
            RecognizeService.recGeneralBasic(getSaveFile(getApplicationContext()).getAbsolutePath(),
                                             result -> infoPopText(result));
        }

        // 识别成功回调，通用文字识别（高精度版）
        if (requestCode == REQUEST_CODE_ACCURATE_BASIC && resultCode == Activity.RESULT_OK) {
            RecognizeService.recAccurateBasic(
                    getSaveFile(getApplicationContext()).getAbsolutePath(),
                    result -> infoPopText(result));
        }

        // 识别成功回调，通用文字识别（含生僻字版）
        if (requestCode == REQUEST_CODE_GENERAL_ENHANCED && resultCode == Activity.RESULT_OK) {
            RecognizeService.recGeneralEnhanced(
                    getSaveFile(getApplicationContext()).getAbsolutePath(),
                    result -> infoPopText(result));
        }

        // 识别成功回调，网络图片文字识别
        if (requestCode == REQUEST_CODE_GENERAL_WEBIMAGE && resultCode == Activity.RESULT_OK) {
            RecognizeService.recWebimage(getSaveFile(getApplicationContext()).getAbsolutePath(),
                                         result -> infoPopText(result));
        }

        // 识别成功回调，银行卡识别
        if (requestCode == REQUEST_CODE_BANKCARD && resultCode == Activity.RESULT_OK) {
            RecognizeService.recBankCard(getSaveFile(getApplicationContext()).getAbsolutePath(),
                                         result -> infoPopText(result));
        }

        // 识别成功回调，行驶证识别
        if (requestCode == REQUEST_CODE_VEHICLE_LICENSE && resultCode == Activity.RESULT_OK) {
            RecognizeService.recVehicleLicense(
                    getSaveFile(getApplicationContext()).getAbsolutePath(),
                    result -> infoPopText(result));
        }

        // 识别成功回调，驾驶证识别
        if (requestCode == REQUEST_CODE_DRIVING_LICENSE && resultCode == Activity.RESULT_OK) {
            RecognizeService.recDrivingLicense(
                    getSaveFile(getApplicationContext()).getAbsolutePath(),
                    result -> infoPopText(result));
        }

        // 识别成功回调，车牌识别
        if (requestCode == REQUEST_CODE_LICENSE_PLATE && resultCode == Activity.RESULT_OK) {
            RecognizeService.recLicensePlate(getSaveFile(getApplicationContext()).getAbsolutePath(),
                                             result -> infoPopText(result));
        }

        // 识别成功回调，营业执照识别
        if (requestCode == REQUEST_CODE_BUSINESS_LICENSE && resultCode == Activity.RESULT_OK) {
            RecognizeService.recBusinessLicense(
                    getSaveFile(getApplicationContext()).getAbsolutePath(),
                    result -> infoPopText(result));
        }

        // 识别成功回调，通用票据识别
        if (requestCode == REQUEST_CODE_RECEIPT && resultCode == Activity.RESULT_OK) {
            RecognizeService.recReceipt(getSaveFile(getApplicationContext()).getAbsolutePath(),
                                        result -> infoPopText(result));
        }
    }

    @OnClick({R.id.general_basic_button, R.id.accurate_basic_button, R.id.general_button,
            R.id.accurate_button, R.id.general_enhance_button, R.id.general_webimage_button,
            R.id.idcard_button, R.id.bankcard_button, R.id.driving_license_button,
            R.id.vehicle_license_button, R.id.license_plate_button, R.id.business_license_button,
            R.id.receipt_button})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(CameraActivity.KEY_OUTPUT_FILE_PATH,
                         getSaveFile(getApplication()).getAbsolutePath());
        switch (view.getId()) {
            case R.id.general_basic_button:
                if (!checkTokenStatus()) {
                    return;
                }
                bundle.putString(CameraActivity.KEY_CONTENT_TYPE,
                                 CameraActivity.CONTENT_TYPE_GENERAL);
                openActivity(CameraActivity.class, bundle, REQUEST_CODE_GENERAL_BASIC);
                break;
            case R.id.accurate_basic_button:
                if (!checkTokenStatus()) {
                    return;
                }
                bundle.putString(CameraActivity.KEY_CONTENT_TYPE,
                                 CameraActivity.CONTENT_TYPE_GENERAL);
                openActivity(CameraActivity.class, bundle, REQUEST_CODE_ACCURATE_BASIC);
                break;

            case R.id.general_button:
                if (!checkTokenStatus()) {
                    return;
                }
                bundle.putString(CameraActivity.KEY_CONTENT_TYPE,
                                 CameraActivity.CONTENT_TYPE_GENERAL);
                openActivity(CameraActivity.class, bundle, REQUEST_CODE_GENERAL);
                break;

            case R.id.accurate_button:
                if (!checkTokenStatus()) {
                    return;
                }
                bundle.putString(CameraActivity.KEY_CONTENT_TYPE,
                                 CameraActivity.CONTENT_TYPE_GENERAL);
                openActivity(CameraActivity.class, bundle, REQUEST_CODE_ACCURATE);
                break;
            case R.id.general_enhance_button:
                if (!checkTokenStatus()) {
                    return;
                }
                bundle.putString(CameraActivity.KEY_CONTENT_TYPE,
                                 CameraActivity.CONTENT_TYPE_GENERAL);
                openActivity(CameraActivity.class, bundle, REQUEST_CODE_GENERAL_ENHANCED);
                break;

            case R.id.general_webimage_button:
                if (!checkTokenStatus()) {
                    return;
                }
                bundle.putString(CameraActivity.KEY_CONTENT_TYPE,
                                 CameraActivity.CONTENT_TYPE_GENERAL);
                openActivity(CameraActivity.class, bundle, REQUEST_CODE_GENERAL_WEBIMAGE);
                break;
            case R.id.idcard_button:
                if (!checkTokenStatus()) {
                    return;
                }
                openActivity(IDCardActivity.class, false);
                break;
            case R.id.bankcard_button:
                if (!checkTokenStatus()) {
                    return;
                }
                bundle.putString(CameraActivity.KEY_CONTENT_TYPE,
                                 CameraActivity.CONTENT_TYPE_BANK_CARD);
                openActivity(CameraActivity.class, bundle, REQUEST_CODE_BANKCARD);
                break;
            case R.id.driving_license_button:
                if (!checkTokenStatus()) {
                    return;
                }
                bundle.putString(CameraActivity.KEY_CONTENT_TYPE,
                                 CameraActivity.CONTENT_TYPE_GENERAL);
                openActivity(CameraActivity.class, bundle, REQUEST_CODE_DRIVING_LICENSE);
                break;
            case R.id.vehicle_license_button:
                if (!checkTokenStatus()) {
                    return;
                }
                bundle.putString(CameraActivity.KEY_CONTENT_TYPE,
                                 CameraActivity.CONTENT_TYPE_GENERAL);
                openActivity(CameraActivity.class, bundle, REQUEST_CODE_VEHICLE_LICENSE);
                break;
            case R.id.license_plate_button:
                if (!checkTokenStatus()) {
                    return;
                }
                bundle.putString(CameraActivity.KEY_CONTENT_TYPE,
                                 CameraActivity.CONTENT_TYPE_GENERAL);
                openActivity(CameraActivity.class, bundle, REQUEST_CODE_LICENSE_PLATE);
                break;
            case R.id.business_license_button:
                if (!checkTokenStatus()) {
                    return;
                }
                bundle.putString(CameraActivity.KEY_CONTENT_TYPE,
                                 CameraActivity.CONTENT_TYPE_GENERAL);
                openActivity(CameraActivity.class, bundle, REQUEST_CODE_BUSINESS_LICENSE);
                break;
            case R.id.receipt_button:
                if (!checkTokenStatus()) {
                    return;
                }
                bundle.putString(CameraActivity.KEY_CONTENT_TYPE,
                                 CameraActivity.CONTENT_TYPE_GENERAL);
                openActivity(CameraActivity.class, bundle, REQUEST_CODE_RECEIPT);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放内存资源
        OCR.getInstance().release();
    }

    public static void showDialog(Context context, String result) {
        new AlertDialog(context).builder()
                                .setTitle("提示")
                                .setMsg(result)
                                .setNegativeButton("", null)
                                .setPositiveButton("", view -> {

                                })
                                .show();
    }
}
