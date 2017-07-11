package chingtech.library.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changhai qiu
 *         Email:qstumn@163.com
 */
public class RadarData {

    private String       mLabel;
    private List<Float>  mValue;
    private int          mColor;
    private List<String> mValueText;
    private int          mVauleTextColor;
    private int          mValueTextSize;
    /** 是否显示数值 */
    private boolean      mValueTextEnable;

    public RadarData(List<Float> value) {
        this(value, 0xFFE84E40);
    }

    public RadarData(List<Float> value, int color) {
        this("data", value, color);
    }

    public RadarData(List<Float> value, int color, boolean mValueTextEnable, int mVauleTextColor) {
        this("data", value, color, mValueTextEnable, mVauleTextColor);
    }

    public RadarData(String label, List<Float> value, int color) {
        this(label, value, color, true);
    }

    public RadarData(List<Float> value, boolean mValueTextEnable) {
        this("data", value, 0xFFE84E40, mValueTextEnable);
    }

    public RadarData(String label, List<Float> value, boolean mValueTextEnable) {
        this(label, value, 0xFFE84E40, mValueTextEnable);
    }

    public RadarData(String label, List<Float> value, int color, boolean mValueTextEnable) {
        this(label, value, color, mValueTextEnable, 0xFF333333);
    }

    public RadarData(String label, List<Float> value, int color, boolean mValueTextEnable,
            int mVauleTextColor) {
        this.mLabel = label;
        this.mValue = value;
        this.mColor = color;
        initValueText();
        this.mVauleTextColor = mVauleTextColor;
        mValueTextSize = 10;
        this.mValueTextEnable = mValueTextEnable;
    }

    public List<Float> getValue() {
        return mValue;
    }

    public void setValue(List<Float> value) {
        this.mValue = value;
        initValueText();
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String mLabel) {
        this.mLabel = mLabel;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

    public int getVauleTextColor() {
        return mVauleTextColor;
    }

    public void setVauleTextColor(int mVauleTextColor) {
        this.mVauleTextColor = mVauleTextColor;
    }

    public int getValueTextSize() {
        return mValueTextSize;
    }

    public void setValueTextSize(int mValueTextSize) {
        this.mValueTextSize = mValueTextSize;
    }

    public boolean isValueTextEnable() {
        return mValueTextEnable;
    }

    public void setValueTextEnable(boolean mValueTextEnable) {
        this.mValueTextEnable = mValueTextEnable;
    }

    public List<String> getValueText() {
        return mValueText;
    }

    public void setValueText(List<String> mValueText) {
        this.mValueText = mValueText;
    }

    private void initValueText() {
        mValueText = new ArrayList<>();
        for (int i = 0; i < mValue.size(); i++) {
            mValueText.add(mValue.get(i).toString());
        }
    }
}
