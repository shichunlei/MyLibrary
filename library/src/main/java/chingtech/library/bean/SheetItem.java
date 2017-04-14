package chingtech.library.bean;

import chingtech.library.interfaces.OnSheetItemClickListener;
import chingtech.library.utils.ENUM;

public class SheetItem {

    private String name;
    private OnSheetItemClickListener itemClickListener;
    private ENUM.SheetItemColor color;

    public SheetItem(String name, ENUM.SheetItemColor color, OnSheetItemClickListener itemClickListener) {
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

    public ENUM.SheetItemColor getColor() {
        return color;
    }

    public void setColor(ENUM.SheetItemColor color) {
        this.color = color;
    }
}
