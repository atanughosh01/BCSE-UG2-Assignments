""" Print first 10 odd and even numbers using iterators and compress. You can use duck typing """

# importing necessary packages
import itertools

low = input('Enter Lower Limit : ')
up = input('Enter Upper Limit : ')
try:
    lower = int(low)
    upper = int(up)
except:
    print('Error ! Please enter numeric value')
    quit()

num_range = range(lower, upper+1)
num_list = list(num_range)
odd_list = list()
even_list = list()

for i in num_range:
    if i % 2 == 0:
        odd_list.append(False)
        even_list.append(True)
    else:
        odd_list.append(True)
        even_list.append(False)

odd_num_list = list(itertools.compress(num_list, odd_list))
even_num_list = list(itertools.compress(num_list, even_list))

print('Odd numbers in the range', (lower, upper), 'are : ', end=" ")
for j in range(len(odd_num_list)):
    print(odd_num_list[j], end=" ")

print('\nEven numbers in the range', (lower, upper), 'are : ', end=" ")
for k in range(len(even_num_list)):
    print(even_num_list[k], end=" ")
