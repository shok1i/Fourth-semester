import math


def xor_booleans(a, b):
    return a != b


def main(Z):
    # print("Z", Z)
    big_o = {c for c in Z if (c > 68)}
    # print("big_o", big_o)
    big_f = {math.ceil(o / 8) + o for o in big_o
             if xor_booleans((o > -58), (o <= 25))}
    # print("big_f", big_f)
    big_m = {math.floor(c / 8) - c for c in Z if 36 < c <= 75}
    # print("big_m", big_m)
    big_f = set(map(abs, big_f))
    res = len(big_m) + len(big_f) - sum(big_f)
    return res


print("Test-1")
print(main({-96, 33, 1, 65, 40, 9, 77, -7, 59, 60}))
print("Test-2")
print(main({68, -27, 50, 19, -11, 86, -39, -71, -2, 31}))
print("Test-3")
print(main({-31, 3, 36, -29, 52, -73, -40, 93, 94, 31}))
print("Test-4")
print(main({-96, 40, 41, -15, 18, 83, 20, -41, 58, 94}))

