package com.rtsgame.views;


public class BuildingInfo {
    private String iconPath;
    private String tooltipText;

    public BuildingInfo(String iconPath, String tooltipText) {
        this.iconPath = iconPath;
        this.tooltipText = tooltipText;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getTooltipText() {
        return tooltipText;
    }
}
