""" Write first seven Fibonacci numbers using generator next() function / yield
in python. Trace and memorize the function. """


def fib_gen(limit):
    a, b = 0, 1                 # Initialize first two base Fibonacci Numbers
    i = 1                       # Initialize the iteration variable
    yield a
    yield b
    while(i != limit):          # One by one yield next Fibonacci Number
        a, b = b, a+b
        i += 1
        yield b

try:
    n = int(input('Enter upto which Fibonacci number you want to print : '))
except:
    print('Error ! Please enter numeric value')
    quit()

fib_num = fib_gen(n-1)          # Create a generator object 'fib_num'
print('1st', n, 'Fibonacci numbers are : ', end=" ")

try:
    while(True):                # Iterating over the generator object using next
        print(next(fib_num), ' ', end=" ")
except StopIteration:
    quit()
    
