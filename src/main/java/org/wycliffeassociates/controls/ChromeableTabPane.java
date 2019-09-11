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

import com.jfoenix.controls.JFXTabPane;
import javafx.scene.Node;
import org.wycliffeassociates.skins.ChromeableTabPaneSkin;
import javafx.scene.control.Skin;
import java.util.Objects;

/**
 * ChromeableTabPane is a modification of the JFoenix Material design TabPane that allows for
 * inserting a node to fit in the tab pane header.
 * <p>
 * Based on the JFXTabPane from github.com/jfoenixadmin/jfoenix by Shadi Shaheen
 */
public class ChromeableTabPane extends JFXTabPane {
    /**
     * Initialize the style class to 'jfx-tab-pane'.
     * <p>
     * This is the selector class from which CSS can be used to style
     * this control.
     */
    private static final String DEFAULT_STYLE_CLASS = "jfx-tab-pane";
    private static final String STYLESHEET = "css/controls/otter-tab-pane.css";

    private Node chrome = null;

    private double headerScalingFactor = 1.0;

    /**
     * {@inheritDoc}
     */
    public ChromeableTabPane() {
        initialize();
    }

    /**
     * {@inheritDoc}
     *
     * @param headerScalingFactor a scaling factor to reduce the width of the TabPane Header
     */
    public ChromeableTabPane(double headerScalingFactor) {
        this.headerScalingFactor = Math.min(Math.max(headerScalingFactor, 0.0), 1.0);
        initialize();
    }

    /**
     * {@inheritDoc}
     *
     * @param headerScalingFactor a scaling factor to reduce the width of the TabPane Header
     */
    public ChromeableTabPane(Node chrome, double headerScalingFactor) {
        this.headerScalingFactor = Math.min(Math.max(headerScalingFactor, 0.0), 1.0);
        this.chrome = chrome;
        initialize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new ChromeableTabPaneSkin(this, chrome, headerScalingFactor);
    }

    @Override
    public String getUserAgentStylesheet() {
        return Objects.requireNonNull(
                Thread.currentThread().getContextClassLoader().getResource(STYLESHEET)
        ).toExternalForm();
    }

    private void initialize() {
        this.getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }
}
