# RuneScape Snap
This repository contains the build file and wrappers necessary to run RuneScape and Oldschool RuneScape on Linux systems in a Snap container format.
RuneScape 3 does provide official Linux support, but is limited in it's supported distributions.
Old School RuneScape does not have any Linux specific installer, but is a Java based game and can be run with a Java runtime environment.
This Snap package resolves these dependencies issues allowing for the games to run on any Linux compatible system, with the Snap benefits of sandboxing and confinement.


## Install Instructions
Ensure snapd is installed. Snap is installed by default on most Ubuntu derivatives. [Instructions can be found here](https://docs.snapcraft.io/installing-snapd/6735)

At a terminal, run

`sudo snap install runescape`

## Build Instructions
The snap can be built on any Linux system with snapd installed.

`sudo snap install snapcraft --classic`

`snapcraft`

`sudo snap install runescape_release.snap --dangerous`

The dangerous flag is required to install any snap package that lacks signature verification, such as those built locally. Sandboxing is unaffected.

## General Info

The snap does not contain Jagex code, and will dynamically fetch this from the Jagex servers on first run.

## Usage Instructions

Once installed, desktop icons should be available in your desktops standard start menu. You can also launch the game via the terminal. 

`runescape.rs3`

`runescape.osrs`

`runescape.reset`

In the event environmental variables need to be passed to the game to modify it's behaviour in any capacity, users can place a bash script in the snaps directory. E.G, ~/snap/runescape/current/ called 

`osrs-user-config`
 
`rs3-user-config`

which will be automatically loaded prior to the game if present. This allows for adding environmental variables to the games without having to mess with any root owned files or desktop entries.
Particularly, exports (such as Mesa debug environmental variables) and parameters for the game client, can be added, for example.

`echo "export PULSE_LATENCY_MSEC=100" > ~/snap/runescape/current/rs3-user-config`
