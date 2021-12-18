/*
 * Copyright (c) 2005, 2016, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package sun.awt.X11;


import sun.awt.UNIXToolkit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

import java.awt.Desktop.Action;
import java.awt.peer.DesktopPeer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Concrete implementation of the interface {@code DesktopPeer} for
 * the Gnome desktop on Linux and Unix platforms.
 * This modification is a naive hack to enable launch/open support for Snap/Flatpak packages
 *
 * @see DesktopPeer
 */
public class XDesktopPeer implements DesktopPeer {

    private static boolean XDGOpenExists;
    private static boolean initExecuted = false;
    private static final List<Action> supportedActions
            = new ArrayList<>(Arrays.asList(Action.OPEN, Action.MAIL, Action.BROWSE));
            
    XDesktopPeer() {
        XDGOpenExists = new File("/bin/xdg-open").exists();
        initExecuted = true;
    }

    static boolean isDesktopSupported() {
        if (initExecuted) {
            return XDGOpenExists;
        } else {
            XDGOpenExists = new File("/bin/xdg-open").exists();
            initExecuted = true;
            return XDGOpenExists;
        }
    }

    public boolean isSupported(Action type) {
        return supportedActions.contains(type);
    }

    public void open(File file) throws IOException {
        try {
            launch(file.toURI());
        } catch (MalformedURLException e) {
            throw new IOException(file.toString());
        }
    }

    public void edit(File file) throws IOException {
        throw new UnsupportedOperationException("The current platform " +
            "doesn't support the EDIT action.");
    }

    public void print(File file) throws IOException {
        throw new UnsupportedOperationException("The current platform " +
            "doesn't support the PRINT action.");
    }

    public void mail(URI uri) throws IOException {
        launch(uri);
    }

    public void browse(URI uri) throws IOException {
        launch(uri);
    }

    private void launch(URI uri) throws IOException {
        String[] launchCommand = new String[2];
        launchCommand[0] = "xdg-open";
        launchCommand[1] = uri.toString();
        Runtime.getRuntime().exec(launchCommand);
    }
}
