""" Write a prime generator program using only primes and using python loops """

low_lim = input('Enter the lower limit : ')
high_lim = input('Enter the upper limit : ')
try:
    lower = int(low_lim)
    upper = int(high_lim)
except:
    print('Error ! Please Enter Numeric Value')
    quit()

print("Prime numbers between", lower, "and", upper, "are : ", end=" ")

for num in range(lower, upper + 1):
    if num > 1:
        for i in range(2, num):
            if (num % i) == 0:
                break
        else:
            print(num, ' ', end=" ")
