package chingtech.library.bean;

import chingtech.library.interfaces.OnSheetItemClickListener;

public class SheetItem {

    private String name;
    private OnSheetItemClickListener itemClickListener;
    private int color;

    public SheetItem(String name, int color, OnSheetItemClickListener itemClickListener) {
        this.name = name;
        this.color = color;
        this.itemClickListener = itemClickListener;
    }

    public SheetItem(String name, OnSheetItemClickListener itemClickListener) {
        this.name = name;
        this.itemClickListener = itemClickListener;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OnSheetItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(OnSheetItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
