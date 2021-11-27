inp = input('Enter Elements of Collection : ')
l = inp.split()

for i in range(len(l)):
    l[i] = int(l[i])

print("User's list of numbers : ", l)

l1 = sorted(l)
l2 = sorted(l, reverse=True)
# print(l1)
# print(l2)

n = int(input('Enter N : '))

print('List of smallest', n, 'numbers :', l1[:n])
print('List of largest', n, 'numbers :', l2[:n])
