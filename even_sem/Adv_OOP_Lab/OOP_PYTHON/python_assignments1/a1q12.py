""" Enumerate the sequence of all lowercase ASCII letters,
starting from 1, using enumerate. """

l = [chr(x) for x in range(ord('a'), ord('z')+1)]
seq_list = list(enumerate(l, 1))
print(seq_list)
