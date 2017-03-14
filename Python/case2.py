# The following example is pretty much the exact use-case of a dictionary,
# but is included for its simplicity. Note that you can include statements
# in each suite.
v = 'ten'
for case in switch(v):
    if case('one'):
        print 1
        break
    if case('two'):
        print 2
        break
    if case('ten'):
        print 10
        break
    if case('eleven'):
        print 11
        break
    if case(): # default, could also just omit condition or 'if True'
        print "something else!"
        # No need to break here, it'll stop anyway

# break is used here to look as much like the real thing as possible, but
# elif is generally just as good and more concise.

# Empty suites are considered syntax errors, so intentional fall-throughs
# should contain 'pass'
c = 'z'
for case in switch(c):
    if case('a'): pass # only necessary if the rest of the suite is empty
    if case('b'): pass
    # ...
    if case('y'): pass
    if case('z'):
        print "c is lowercase!"
        break
    if case('A'): pass
    # ...
    if case('Z'):
        print "c is uppercase!"
        break
    if case(): # default
        print "I dunno what c was!"

# As suggested by Pierre Quentel, you can even expand upon the
# functionality of the classic 'case' statement by matching multiple
# cases in a single shot. This greatly benefits operations such as the
# uppercase/lowercase example above:
import string
c = 'A'
for case in switch(c):
    if case(*string.lowercase): # note the * for unpacking as arguments
        print "c is lowercase!"
        break
    if case(*string.uppercase):
        print "c is uppercase!"
        break
    if case('!', '?', '.'): # normal argument passing style also applies
        print "c is a sentence terminator!"
        break
    if case(): # default
        print "I dunno what c was!"
