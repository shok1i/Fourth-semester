def main(y, x):
    res = 0
    for i in range(len(y)):
        res += (1 - 89 * x[i % 4] - y[len(y) + 1 - i] ** 3) ** 4
    return res

# print("{:e}".format(main([-0.62, 0.04], [0.27, -0.54])))
# HZ
