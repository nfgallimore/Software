#!/bin/sh
alias ROOT="/Applications/World\ of\ Warcraft"
cd "$ROOT"

alias ADDONS="/Applications/World\ of\ Warcraft/Interface"

# credentials
ACCOUNT="CYBERUKE"
REALM="Sargeras"
CHARACTER="Darkrizen"

#cd "$ADDONS" && mkdir testdir

# creates a backup
# cp -r Development Development.bak

# WTF="Development/WTF"
# mv $WTF/ACCOUNT $WTF/$ACCOUNT
# WTF=$WTF/$ACCOUNT

# mv $WTF/CHARACTER $WTF/$CHARACTER
# $WTF = "$WTF/Account/$CHARACTER"

# mv $ROOT/Development/WTF $ROOT/WTF

# cd "$ROOT"

r=$(pwd)
case "$ROOT/Development/Interface" in
    /*) p=r;;
    *) p="";;
    esac
cd "$ADDONS" && cp -r . "$p/$ADDONS"
cd "$r"