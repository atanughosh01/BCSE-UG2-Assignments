""" Write a regular expression to validate a phone number. """

# importing necessary packages
import re
# function to check valid phone number
def check_valid(s):
    # 1) Either begins with 0- or 91- or nothing
    # 2) Then contains 6 or 7 or 8 or 9
    # 3) Then contains 9 random digits
    pattern = re.compile("(0-|91-)?[6-9][0-9]{9}$")
    return pattern.match(s)

num = input('Enter Phone Number : ')
if check_valid(num):
    print("Number is Valid")
else:
    print("Number is Invalid")
