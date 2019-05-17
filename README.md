# RuneScape Snap
This repository contains the build file and wrappers necessary to run RuneScape and Oldschool Runescape on Linux systems.
Whilst Jagex provides a native Runescape 3 installer, it does not run on any system other than Ubuntu 14.04 or Debian 8 due to dependency hell.
Oldschool Runescape does not have any Linux specific installer, but is a Java based game and can be run with a JRE.
This snap package resolves these dependencies issues allowing for the games to run on any Linux compatible system, with the snap benefits of sandboxing and confinement.

## Install Instructions
Ensure snap is installed. Snap is installed by default on most Ubuntu deritivatives. [Instructions can be found here](https://docs.snapcraft.io/installing-snapd/6735)

At a terminal, run

**sudo snap install rslauncher**

## Build Instructions
The snap can be built on any Linux system with snapd installed.

**sudo snap install snapcraft --classic**

**snapcraft**

**sudo snap install rslauncher_1_amd64.snap --dangerous**

The dangerous flag is required to install any snap package that lacks signature verification, such as those built locally. This flag does not turn off the snap sandboxing, unlike --classic.

## General Info

The snap does not contain Jagex code, and will dynamically fetch this from the Runescape servers on first run.
The snap uses Ubuntu 16.04 as a base due to less configuration changes required to get the upstream game to work. Ubuntu 18.04, supported until 2028, is also compatible but not utilised whilst 16.04 retains upstream support.

## Usage instructions

Once installed, desktop icons should be available in your desktops standard start menu. You can also launch the game via the terminal. 

**rslauncher.rs3**

**rslauncher.osrs**

**rslauncher.reset** to clear the user files for both games
