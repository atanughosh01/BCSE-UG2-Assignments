"""   Write a discount coupon code using dictionary in Python with
different rate coupons for each day of the week. """

coupons = dict(mon=5, tue=10, wed=15, thu=20, fri=25, sat=30, sun=40)
print('Coupons : ', coupons)
inp = input('Enter Day of Week : ')
day = inp.strip().lower()[:3]
print('Day Entered :', day)
for coupon in coupons:
    if day == coupon:
        print('Discount on ', inp, 'in % is', coupons[day])
        break
else:
    print('Enter a valid Day')
