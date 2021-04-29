This folder contains a patch to Java to use xdg-open instead of attempting to use GTK functions for opening URI's.
Importantly, in Snap/Flatpak, xdg-open is a wrapper around xdg-desktop-portal functionality to cross the sandbox boundary.

The contents are to be considered [GPL2 with the classpath exception](http://openjdk.java.net/legal/gplv2+ce.html), as they derive from upstream [Java](https://openjdk.java.net/) sourcecode.

Being lazy and considering Java Bytecode compatibility, the class is precompiled to speed up the Snapcraft build system.

The classes simply replace the functionality built into the JVM by default. Patching modules like this means I don't have to build the entire JVM.
This solution is much better than the previous LD_PRELOAD shenanigans that still required bundling gvfs.

The source is also shipped alongside the built snap to aid with the GPL requirements.
