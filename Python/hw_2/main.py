def main(x):
    if x < 18:
        return (x**3 // 68 - 21)**3
    elif x >= 30:
        return 41 * x ** 2
    return (33 * x ** 3 - 0.01) ** 2


print("-----------------------")
print(main( 28))
print("{:e}".format(main( 28)))

print("-----------------------")
print(main(-18))
print("{:e}".format(main(-18)))
