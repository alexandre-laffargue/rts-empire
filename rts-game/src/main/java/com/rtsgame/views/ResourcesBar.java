package com.rtsgame.views;

import com.rtsgame.models.resources.Resource;
import com.rtsgame.models.resources.ResourceType;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ResourcesBar extends VBox {
    private Label[] resourceLabels;
    private Label daysLabel;

    public ResourcesBar() {
        setPadding(new Insets(10));
        setSpacing(5);

        resourceLabels = new Label[ResourceType.values().length];
        for (int i = 0; i < ResourceType.values().length; i++) {
            resourceLabels[i] = new Label();
            getChildren().add(resourceLabels[i]);
        }
        daysLabel = new Label("Day: 0");
        getChildren().add(daysLabel);
    }

    public void updateResources(Resource[] resources, int currentDay) {
        for (int i = 0; i < resources.length; i++) {
            resourceLabels[i].setText(resources[i].toString());
        }
        daysLabel.setText("Day: " + currentDay);
    }
}