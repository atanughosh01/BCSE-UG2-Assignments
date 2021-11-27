""" Create a list of all the numbers up to N=50 which are multiples of
five using anonymous function """

try:
    num = int(input('\nEnter the number whose multiples are to be added to list : '))
    limit = int(input('\nEnter upto which number the multiples are to be added to list : '))
except:
    print('Error ! Please Enter Numeric Value(s)')
    quit()

num_list = [i for i in range(limit + 1)]
multiply_list = list(filter(lambda x: (x % num == 0), num_list))
print('\nThe list of multiples of', num, 'upto', limit, 'is :', multiply_list)
