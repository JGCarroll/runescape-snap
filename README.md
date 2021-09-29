# RuneScape Snap

[![Get it from the Snap Store](https://snapcraft.io/static/images/badges/en/snap-store-black.svg)](https://snapcraft.io/runescape)

This repository contains the build file and wrappers necessary to run RuneScape and Oldschool RuneScape on Linux systems in a Snap container format.
RuneScape 3 does provide official Linux support, but is limited in it's supported distributions.
Old School RuneScape does not have any Linux specific installer, but is a Java based game and can be run with a Java runtime environment.
This Snap package resolves these dependencies issues allowing for the games to run on any Linux compatible system, with the Snap benefits of sandboxing and confinement.

## Install Instructions
Ensure snapd is installed.
Snap is installed by default on most Ubuntu derivatives.
[Instructions can be found here](https://docs.snapcraft.io/installing-snapd/6735)

At a terminal, run

```bash
sudo snap install runescape
```

Some systems may have graphical installers for Snap packages pre-setup.
You might also want to consider `sudo snap install snap-store` to get the official Snap GUI installer.

## Usage Instructions

Once installed, desktop icons should be available in your desktops standard start menu.
You can also launch the game via the terminal. 

`runescape.rs3` or `snap run runescape.rs3`

`runescape.osrs` or `snap run runescape.osrs`

`runescape.reset` or `snap run runescape.reset`

In the event environmental variables need to be passed to the game to modify it's behaviour in any capacity, users can place a bash script in the snaps directory, e.g., `~/snap/runescape/current/` called 

`osrs-user-config`
 
`rs3-user-config`

These scripts will be automatically loaded prior to the game if present.
This allows for adding environmental variables to the games without having to mess with any root owned files or desktop entries.
Particularly, exports (such as Mesa debug environmental variables) and parameters for the game client can be added.
For example:

`echo "export PULSE_LATENCY_MSEC=100" > ~/snap/runescape/current/rs3-user-config`

Users may pass the -h parameter ( e.g., `runescape.rs3 -h` ) to the launch scripts to see common overrides.
These parameters will be preferred over any conflicting entries in the user-config files.

## Build Instructions
The snap can be built on any Linux system with snapd installed.

For machines with virtual machine support, instructions should be roughly as follows.

```bash
sudo snap install snapcraft --classic
sudo snap install multipass
snapcraft
sudo snap install runescape_release.snap --dangerous
```

The dangerous flag is required to install any snap package that lacks signature verification, such as those built locally.
It does not disable the sandboxing, and is dangerous in the sense the author is unknown (I.E, not me) rather than the snap has any specific dangerous properties.
It is impossible to generate valid signatures for snaps without owning the package name on the Snap Store itself, so any unofficial builds will always require this flag, or require their package name be changed.

If your machine does not have VM support, try building with the LXD backend using the instructions [here](https://snapcraft.io/docs/build-on-lxd), follow the building with bases information. 

## General Info

### Is this a third party client?
This snap is not a third party client, I do not have any interest in modifying any of the games features or providing any features that modify any gameplay mechanics or interactions, regardless of their implementation.

The game engines are unmodified from Jagex, they are simply run in a consistent environment, and that environment itself may have some tweaks to encourage the engines to work better, such as setting RAM limits in OSRS, setting default cache locations to not pollute $HOME, consistent OS libraries (Mesa, Java, etc), and various others.

It is a third party package, in the sense that Jagex are not publishing this snap.

### How do I get in touch / report an issue?
Please use the Github issues functionality.
If it's a problem that exists in the snap package and doesn't exist in the official package, I'm interested in knowing about it.

Please don't assume I might know about problems already.
**I've been on an extended break from the game and so at any given point it's possible this package doesn't work, and I'd need the communities help to alert me to it!**

Historically, I used to check the RuneScape forums and subreddits rather vigorously to look out for issues.
Unfortunately, this actually consumes a lot of time since it's looking for needles in haystacks, and in the ideal case, the needles don't exist.
So unfortunately I've decided to stop doing it, please use Github issues :)!

### OSRS C++ client
I'd like to package the newer C++ version of OSRS in the future.
For now, it's impossible to package the Steam version because it would involve having to communicate with the Steam client for authentication.
Jagex have commited to distributing a release outside of Steam, for Windows, which I can attempt to wrap in Proton/Wine when it releases.
I have no idea to whether Jagex will release a Linux native version.
If they do, I'll attempt to snap that and replace the Java client with it.

In general, I'll keep an eye out on what can be done as events unfold.

### Can this project be trusted?

* The main scripts powering the snap can be found at `/snap/runescape/current/bin/` and are written in Bash, so what they do is plainly visible in every installation.

* The downloads from Jagex are cryptographically verified by the snap. 
  The snap itself is cryptographically verified by snapd itself, unless installed with the --dangerous flag.

* The exact versions of installed packages are usually listed in `/snap/runescape/current/snap/manifest.yaml`, so they be inspected.

* The production builds are usually done by Githubs own runners and can be viewed [here](https://github.com/MrCarroll/runescape-snap/actions/).

* Sandboxing is used where possible to isolate what this snap and the games can do.
  In the majority of scenarios, they can't even inspect your $HOME folder, though exact sandboxing depends on Linux distribution / patches.
  You can view what is allowed and control it via the snap CLI tools.

### Why are the store reviews so bad?
Reviews in the majority of Linux app stores (Gnome Software/Discover/etc) are powered by the [ODRS service](https://odrs.gnome.org/).
ODRS merges reviews from multiple package formats across multiple distributions.

Unfortunately, this means a significant amount of alternate RuneScape packages share the same comments and ratings as this snap.
Many of these comments are older than the Snap itself.
The RSU launcher, Debians' packages, the upstream official package from Jagex, and presumably various other packages are treated in this manner, with all their reviews being merged and shared.

It's impossible to leave responses to ask for more information or comment on any criticisms or bug reports submitted as a review.
Because of this, I simply don't look at them because I cannot tell which package they relate to or respond at all.

### Copyright

The snap does not contain Jagex code, and will dynamically fetch this from the Jagex servers on first run.
This solves a lot of issues regarding the EULA of the games; I simply don't distribute them, but instead the environment they run in. 

On the offchance you're a Jagex employee reading this and wish to get in touch, my email address can be found on my Github profile.