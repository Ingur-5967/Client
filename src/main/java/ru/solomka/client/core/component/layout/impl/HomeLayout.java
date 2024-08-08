package ru.solomka.client.core.component.layout.impl;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ru.solomka.client.core.component.ResourceConstant;
import ru.solomka.client.core.component.entity.SceneEntry;
import ru.solomka.client.core.component.item.constant.ContextMenuEntry;
import ru.solomka.client.core.component.item.constant.ElementBarEntry;
import ru.solomka.client.core.component.item.impl.pane.LinkedPane;
import ru.solomka.client.core.component.layout.Layout;
import ru.solomka.client.core.component.option.CssContext;
import ru.solomka.client.core.component.option.CssProperties;

public class HomeLayout implements Layout {


    @Override
    public void loadLayout(Pane region, SceneEntry entry) {

        AnchorPane container = new AnchorPane();

        LinkedPane elementBarEntry = new ElementBarEntry(
                region,
                new CssContext[]{new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(ResourceConstant.Color.BACKGROUND_BAR.getColor())), new CssContext(CssProperties.BORDER_RADIUS.getProperty(0))}
        ).build();

        LinkedPane contextMenuEntry = new ContextMenuEntry(region,28, 10).build();

        // TODO: delete this section of code later refactoring

//        int fixedX = (int) (region.getPrefWidth()/2 - (int) elementBarEntry.getPrefWidth()*(region.getPrefWidth()/elementBarEntry.getPrefWidth())/3.6);
//
//        container.setLayoutX(fixedX);
//        container.setPrefSize(region.getPrefWidth() - elementBarEntry.getPrefWidth()*1.1, region.getPrefHeight());
//
//        AnchorPane contentContainer = new AnchorPane();
//        contentContainer.setPrefSize(container.getPrefWidth(), 350);
//
//        HorizontalStackNode informationContent = new HorizontalStackNode(
//                contentContainer, 400,
//                Arrays.asList(
//
//                        new SceneElement("entry1", () -> {
//                            VerticalStackNode serverContainer = new VerticalStackNode(
//                                    contentContainer, 35, 55,
//                                    Arrays.asList(
//                                            new SceneElement("ServerBoxTitle", () -> {
//                                                Label fLabel = new Label("Сервера");
//                                                fLabel.setStyle("-fx-font-size: 20; -fx-text-fill: white; -fx-font: small-caps bold 24px/1 sans-serif");
//                                                return fLabel;
//                                            }, contentContainer),
//                                            new SceneElement("ServerContainer", () -> {
//                                                AnchorPane contentProvider = new AnchorPane();
//                                                contentProvider.setPrefSize(240, 250);
//                                                contentProvider.setStyle(new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(ResourceConstant.Color.BACKGROUND_BAR.getColor())).getCss());
//                                                return contentProvider;
//                                            }, contentContainer),
//                                            new SceneElement("SocialContainer", () -> {
//                                                AnchorPane socialContainer = new AnchorPane();
//
//                                                socialContainer.setStyle(new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(ResourceConstant.Color.BACKGROUND_BAR.getColor())).getCss());
//                                                socialContainer.setPrefSize(240, 80);
//
//                                                LabelStackNode content = new LabelStackNode(
//                                                        socialContainer,
//                                                        new Pair<>("Супер Промокоды!", new CssContext[]{
//                                                                new CssContext(CssProperties.BACKGROUND_COLOR.getProperty("white")), new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white")),
//                                                                new CssContext(CssProperties.FONT_SIZE.getProperty(4))
//                                                        }),
//                                                        new Pair<>("Новые возможности появляются тут :)",  new CssContext[]{
//                                                                new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(ResourceConstant.Color.SUB_TEXT_BAR.getColor())), new CssContext(CssProperties.TEXT_FILL_COLOR.getProperty("white")),
//                                                                new CssContext(CssProperties.FONT_SIZE.getProperty(4))
//                                                        }), 4
//                                                );
//
//
//                                                socialContainer.getChildren().add(content);
//
//                                                return socialContainer;
//                                            }, contentContainer)
//                                    ).toArray(SceneElement[]::new)
//                            );
//
//                            serverContainer.getComponentParent().getChildren().getLast().setLayoutY(serverContainer.getComponentParent().getChildren().getLast().getLayoutY() + 300);
//
//                            return serverContainer.getComponentParent();
//                        }, contentContainer),
//
//                        new SceneElement("entry2", () -> {
//                            VerticalStackNode newsContainer = new VerticalStackNode(
//                                    contentContainer, 50, 20,
//                                    Arrays.asList(
//                                            new SceneElement("NewsBoxTitle", () -> {
//                                                Label fLabel = new Label("Последние новости");
//                                                fLabel.setStyle("-fx-font-size: 20; -fx-text-fill: white; -fx-font: small-caps bold 24px/1 sans-serif");
//                                                return fLabel;
//                                            }, contentContainer),
//                                            new SceneElement("NewsContainer", () -> {
//                                                AnchorPane contentProvider = new AnchorPane();
//                                                contentProvider.setPrefSize(300, 400);
//                                                contentProvider.setStyle(new CssContext(CssProperties.BACKGROUND_COLOR.getProperty(ResourceConstant.Color.BACKGROUND_BAR.getColor())).getCss());
//                                                return contentProvider;
//                                            }, contentContainer)
//                                    ).toArray(SceneElement[]::new)
//                            );
//
//                            return newsContainer.getComponentParent();
//                        }, contentContainer)
//                ).toArray(SceneElement[]::new)
//        );
//
//        informationContent.setLocationLayout((int) informationContent.getComponentParent().getLayoutX(), (int) (informationContent.getComponentParent().getLayoutY() + 65));


        region.getChildren().addAll(elementBarEntry.getItem(), contextMenuEntry.getItem());
        region.getChildren().add(container);
    }
}