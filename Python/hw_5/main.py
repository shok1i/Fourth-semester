import math


def main(y, x):
    res = 0
    for i in range(len(y)):
        f_p = 1 - 89 * x[math.floor(i / 4)]
        s_p = y[len(y) - 1 - i] ** 3
        res += (f_p - s_p) ** 4
    return res


print("{:e}".format(main([-0.62, 0.04], [0.27, -0.54])))
