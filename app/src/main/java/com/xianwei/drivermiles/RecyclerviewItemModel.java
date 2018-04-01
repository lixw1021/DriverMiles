package com.xianwei.drivermiles;

/**
 * Created by xianwei li on 3/31/2018.
 */

public class RecyclerviewItemModel {

    private int imageSourceId;
    private String itemName;
    private String subItemName;

    public RecyclerviewItemModel(int imageSourceId, String itemName, String subItemName) {
        this.imageSourceId = imageSourceId;
        this.itemName = itemName;
        this.subItemName = subItemName;
    }

    public int getImageSourceId() {
        return imageSourceId;
    }

    public void setImageSourceId(int imageSourceId) {
        this.imageSourceId = imageSourceId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSubItemName() {
        return subItemName;
    }

    public void setSubItemName(String subItemName) {
        this.subItemName = subItemName;
    }
}
