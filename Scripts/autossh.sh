#!/bin/bash
## autossh script


HOST="virtualtheologies13@virtualtheologies.com";
PATH="$PATH:/usr/local/bin/autossh";

ENC="3des -D 1080"
FLAGS="-M 20000 -f -N -p 22 -g -c $ENC"

echo "Connecting to: $HOST"


$PATH -M 20000 -f -N -p 22 -g -c 3des -D 1080 $HOST