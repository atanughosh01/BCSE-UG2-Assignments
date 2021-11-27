""" Filter out the odd squares using map, filter, list. """

import math
try:
    limit = int(input('\nEnter upto which number odd squares are to be filtered out : '))
except:
    print('Error ! Please Enter Numeric Value')
    quit()

num_list = [i for i in range(limit + 1)]
# print(num_list)

def isPerfectSquare(n):
    if (n > 0):
        sr = math.sqrt(n)
        return (int(sr) == sr)
    return False

square_list = list(map(lambda f: float(f), filter(lambda x: isPerfectSquare(x), num_list)))
# print(square_list)

odd_square_list = list(map(lambda z: int(z), filter(lambda y: True if y % 2 != 0 else False, square_list)))
print('\nList of odd squares is :', odd_square_list)
