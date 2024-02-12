import math


def main(y, z, x):
    # first part
    divisor_1_1 = 30 * (92 * y ** 2 + x / 50) ** 3
    divisor_1_2 = (z ** 3 / 13 - y) ** 6
    divisor_1 = divisor_1_1 - divisor_1_2
    divider_1_1 = math.log(z ** 3 + z / 20 + 37 * z ** 2)
    divider_1_2 = 35 * (math.tan(95 - x ** 3 - 37 * y)) ** 6
    divider_1 = divider_1_1 + divider_1_2
    first_part = divisor_1 / divider_1
    # second part
    divisor_2_1 = (19 * z ** 3 + x / 69) ** 4
    divisor_2_2 = 15 * (y ** 2 - 69) ** 3
    divisor_2 = divisor_2_1 + divisor_2_2
    divider_2_1 = 73 * (z ** 3 - y ** 2) ** 7
    divider_2_2 = x ** 4
    divider_2 = divider_2_1 + divider_2_2
    second_part = divisor_2 / divider_2
    # final result
    res = math.sqrt(first_part) + second_part
    return res


print(main(0.82, -0.2, 0.47))
