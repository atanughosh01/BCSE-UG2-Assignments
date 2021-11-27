""" Write a code which yields all terms of the geometric progression
a, aq, aq2, aq3, ....   When the progression produces a term that is
greater than 100,000, the generator stops (with a return statement).
Compute total time and time within the loop """

import time
a, q = [int(x) for x in input('Enter value of a and q separated by whitespace : ').split()]
t1 = time.time()
print('The GP is : ', end=" ")
while(a<100000):
    print(a, ' ', end=" ")
    a *= q
t2 = time.time()
print('\nTime taken within the loop (in s) :', (t2-t1))
t3 = time.time()
print('Total time taken (in s) :', (t3-t1))
