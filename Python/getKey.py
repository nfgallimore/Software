def strxor(a, b):     # xor two strings of different lengths
    if len(a) > len(b):
        return "".join([chr(ord(x) ^ ord(y)) for (x, y) in zip(a[:len(b)], b)])
    else:
        return "".join([chr(ord(x) ^ ord(y)) for (x, y) in zip(a, b[:len(a)])])

ciphertext = strxor(strxor("6c73d5240a948c86981bc294814d".decode('hex'), "attack at dawn"), "attack at dusk").encode('hex')
print(ciphertext)
