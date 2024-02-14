def main(n, m, y, a, b, x):
    first = 0
    for c in range(1, m + 1):
        for j in range(1, n + 1):
            first += c ** 6 / 63 - 32 * j ** 3 - y ** 5
    second = 0
    for k in range(1, b + 1):
        for c in range(1, m + 1):
            for i in range(1, a + 1):
                second += (c ** 2 + k ** 3) ** 5 + x ** 7 / 97 + i ** 3
    return first - second


print("{:e}".format(main(5, 7, -0.78, 8, 3, 0.26)))
