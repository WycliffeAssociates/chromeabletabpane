/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wycliffeassociates.controls;

import com.jfoenix.skins.JFXTabPaneSkin;
import com.sun.javafx.css.converters.BooleanConverter;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableBooleanProperty;
import javafx.css.Styleable;
import javafx.css.StyleableBooleanProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import org.wycliffeassociates.skins.ChromeableTabPaneSkin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * JFXTabPane is the material design implementation of a tab pane.
 *
 * TODO: REWORK SWITCH ANIMATION
 * @author Shadi Shaheen
 * @version 1.0
 * @since 2016-03-09
 */
public class ChromeableTabPane extends TabPane {
    /**
     * Initialize the style class to 'jfx-tab-pane'.
     * <p>
     * This is the selector class from which CSS can be used to style
     * this control.
     */
    private static final String DEFAULT_STYLE_CLASS = "jfx-tab-pane";
    private static final String USER_AGENT_STYLESHEET = ChromeableTabPane.class.getResource("/css/controls/jfx-tab-pane.css").toExternalForm();

    /**
     * {@inheritDoc}
     */
    public ChromeableTabPane() {
        initialize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new ChromeableTabPaneSkin(this);
    }

    private void initialize() {
        this.getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUserAgentStylesheet() {
        return USER_AGENT_STYLESHEET;
    }

    /**
     * propagate any mouse events on the tab pane to its parent
     */
    public void propagateMouseEventsToParent() {
        this.addEventHandler(MouseEvent.ANY, e -> {
            e.consume();
            this.getParent().fireEvent(e);
        });
    }

    /**
     * disable animation on validation
     */
    private StyleableBooleanProperty disableAnimation = new SimpleStyleableBooleanProperty(ChromeableTabPane.StyleableProperties.DISABLE_ANIMATION,
            ChromeableTabPane.this,
            "disableAnimation",
            false);

    public final StyleableBooleanProperty disableAnimationProperty() {
        return this.disableAnimation;
    }

    public final Boolean isDisableAnimation() {
        return disableAnimation != null && this.disableAnimationProperty().get();
    }

    public final void setDisableAnimation(final Boolean disabled) {
        this.disableAnimationProperty().set(disabled);
    }

    private static class StyleableProperties {
        private static final CssMetaData<ChromeableTabPane, Boolean> DISABLE_ANIMATION =
                new CssMetaData<ChromeableTabPane, Boolean>("-jfx-disable-animation",
                        BooleanConverter.getInstance(), false) {
                    @Override
                    public boolean isSettable(ChromeableTabPane control) {
                        return control.disableAnimation == null || !control.disableAnimation.isBound();
                    }

                    @Override
                    public StyleableBooleanProperty getStyleableProperty(ChromeableTabPane control) {
                        return control.disableAnimationProperty();
                    }
                };

        private static final List<CssMetaData<? extends Styleable, ?>> CHILD_STYLEABLES;

        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(
                    TabPane.getClassCssMetaData());
            Collections.addAll(styleables, DISABLE_ANIMATION);
            CHILD_STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    // inherit the styleable properties from parent
    private List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        return getClassCssMetaData();
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return ChromeableTabPane.StyleableProperties.CHILD_STYLEABLES;
    }

}