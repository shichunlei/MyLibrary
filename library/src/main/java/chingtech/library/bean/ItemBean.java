package chingtech.library.bean;

public class ItemBean {

    private boolean isSelected;

    private String value;

    public ItemBean(String value, boolean isSelected) {
        this.value = value;
        setSelected(isSelected);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
