package com.chingtech.sample.view.baidu_tts.control;

import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import java.util.Map;

/**
 * 合成引擎的初始化参数
 * <p>
 * Created by fujiayi on 2017/9/13.
 */

public class InitConfig {
    /**
     * appId appKey 和 secretKey。注意如果需要离线合成功能,请在您申请的应用中填写包名。
     * 本demo的包名是com.baidu.tts.sample，定义在build.gradle中。
     */
    private String appId;

    private String appKey;

    private String secretKey;

    /**
     * 纯在线或者离在线融合
     */
    private TtsMode ttsMode;

    /**
     * 离线发音，离线男声或者女声
     */
    private String offlineVoice;

    /**
     * 初始化的其它参数，用于setParam
     */
    private Map<String, String> params;

    /**
     * 合成引擎的回调
     */
    private SpeechSynthesizerListener listener;

    public InitConfig(String appId, String appKey, String secretKey, TtsMode ttsMode, String offlineVoice,
                      Map<String, String> params, SpeechSynthesizerListener listener) {
        this.appId = appId;
        this.appKey = appKey;
        this.secretKey = secretKey;
        this.ttsMode = ttsMode;
        this.offlineVoice = offlineVoice;
        this.params = params;
        this.listener = listener;
    }

    public SpeechSynthesizerListener getListener() {
        return listener;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getOfflineVoice() {
        return offlineVoice;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public TtsMode getTtsMode() {
        return ttsMode;
    }
}
