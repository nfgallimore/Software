#!/bin/sh
#
# by Patrick van der Leer <pat.vdleer@gmail.com>
# released under GPL, version 2 or later

PATH=/sbin:/bin:/usr/sbin:/usr/bin
DAEMON="/usr/bin/autossh"
DESC="Autossh job"
PIDFOLDER="/var/run/autossh"
PIDFOLDERSSH="$PIDFOLDER/ssh"
REMOTE_USER="virtualtheologies13"
REMOTE_ADDR="virtualtheologies.com"
LOGFILE="/var/log/autossh.log"

if [ ! -d $PIDFOLDER ] ; then
    mkdir -p $PIDFOLDER
fi

if [ ! -d $PIDFOLDERSSH ] ; then
    mkdir -p $PIDFOLDERSSH
fi

test -f $DAEMON || exit 0

. /lib/lsb/init-functions

PIDFILE="$PIDFOLDER/$REMOTE_USER-$REMOTE_ADDR.pid"
PIDFILESSH="$PIDFOLDERSSH/$REMOTE_USER-$REMOTE_ADDR.pid"

is_running() {
    if [ -f $PIDFILE ]; then
        PID=`cat $PIDFILE`
        if [ -n "$PID" ]; then
            return 0
        else
            return 1
        fi
    else
        return 1
    fi
}

start_autossh() {
    if ! is_running; then
        echo "Starting $DESC"
        export AUTOSSH_FIRST_POLL=10
        export AUTOSSH_POLL=60
        export AUTOSSH_PIDFILE=$PIDFILESSH
        start-stop-daemon --start --make-pidfile --pidfile $PIDFILE --exec $DAEMON -- -M 29000 -i /root/.ssh/id_rsa -X -C -R 2222:localhost:22 $REMOTE_USER@$REMOTE_ADDR >> $LOGFILE 2>&1 &
        sleep 1;
        if ! is_running; then
            echo "$DESC: running @ pid $PID"
        else
            echo 'Something went wrong';
        fi
    else
        echo "$DESC: already running (pid $PID)"
    fi
}

stop_autossh() {
    if is_running; then
        echo "Stopping $DESC"
        start-stop-daemon --stop --pidfile $PIDFILE --signal 15
        if [ -f $PIDSSHFILE ]; then
            PIDSSH=`cat $PIDFILESSH`
            kill $PIDSSH
            rm -f $PIDFILESSH
        fi
    else
        echo "$DESC: not running"
    fi
    [ -f $PIDFILE ] && rm -f $PIDFILE
}

case "$1" in
    start)
        start_autossh
    ;;
    stop)
        stop_autossh
    ;;
    force-reload|restart)
        stop_autossh
        start_autossh
    ;;
    status)
        if is_running; then
            echo "$DESC: running (pid $PID)"
            exit 0
        else
            echo "$DESC: not running"
            [ -f $PIDFILE ] && exit 1 || exit 3
        fi
    ;;
    log)
        if [ -f $LOGIFLE ]; then
            tail $LOGFILE
        else
            echo "log file '$LOGFILE' does't exist"
        fi
    ;;
    *)
        echo "Usage: $0 {start|stop|restart|force-reload|status|log}"
        exit 3
    ;;
esac

exit 0