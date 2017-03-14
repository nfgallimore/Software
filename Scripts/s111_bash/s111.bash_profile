# Author: Robert Jellinek <robert.jellinek@gmail.com>
# Date:   6/29/2011
#
# ~libs111/s111.bash_profile provides the aliases, path, and prompt format
# for bash users (which seems to be the default for new nice.harvard.edu 
# accounts)

alias javac='/nfs/home/l/i/libs1/pub/local/i386/jdk1.6.0_14/bin/javac -J-Xms64m -J-Xmx128m -classpath .:/home/c/s/cscie119/utils/java'
alias java='/nfs/home/l/i/libs1/pub/local/i386/jdk1.6.0_14/bin/java -Xms64m -Xmx128m -classpath .:/home/c/s/cscie119/utils/java'
alias javadoc='/nfs/home/l/i/libs1/pub/local/i386/jdk1.6.0_14/bin/javadoc -J-Xms64m -J-Xmx128m -classpath .:/home/c/s/cscie119/utils/java'
alias jdb='/nfs/home/l/i/libs1/pub/local/i386/jdk1.6.0_14/bin/jdb -J-Xms64m -J-Xmx128m -classpath .:/home/c/s/cscie119/utils/java'
alias ls='ls --color'
alias rm='rm -i'

export PATH=~libs111/pub/bin:$PATH

export PS1="\h: \w % "
