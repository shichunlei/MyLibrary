package chingtech.library.bean;

public class ItemBean extends SelectedBean {

    private String value;

    public ItemBean(String value) {
        this.value = value;
    }

    public ItemBean(String value, boolean isSelected) {
        this.value = value;
        setSelected(isSelected);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
