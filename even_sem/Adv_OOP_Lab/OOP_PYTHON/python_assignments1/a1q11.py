""" Let's find all Pythagorean triples whose short sides are numbers smaller than 10.
use filter and comprehension """

from math import sqrt
try:
    limit = int(input('\nMax size of shorter sides : '))
except:
    print('Error ! Please Enter Numeric Value')
    quit()

triplet_list = [[(x, y, sqrt(x*x + y*y)) for x in range(limit)]for y in range(limit)]
print('Pythagorean triplet list :', triplet_list)
