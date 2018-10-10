# Disk usage calculator

The idea is to create a micro service which allows a set of directories as
input where the micro service will be executing folder traversal in parallel
in order to calculate disk usage.

Usage: `java -jar <name-of-jar>.jar <path-a> <path-b> ... <path-n>`

First impression is that it is faster than then `du` command. On
the Openshift OT environment (master1 node) it was executed and
compared:

Method | Result
-------|-------
du | 62s
calc | 40s