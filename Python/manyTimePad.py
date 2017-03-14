# Punctuation including space and numbers is 20- 3F or 01xxxxx
# A-Z is 41-5A ie.  0x40 or   010xxxxx
# a-z is 61-7A      0x20 or   011xxxxx

special     = 0x00
punctuation = 0x20
upper       = 0x40
lower       = 0x60

names = [ "spec ","punct","upper","lower" ]
result = [ special,punctuation,upper,lower ]

for i in range(0, len(names) ):
   print names[i]," ",
   for j in range (0, len(names) ):
      print " ", names[j], (result[i] ^ result[j]) >> 5,
   print
