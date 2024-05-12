import math


def getFFFFFFFFFFFFFFFFFu(boboboboboboboboboboboboboobobo):
    F = []
    for o in boboboboboboboboboboboboboobobo:
        if (o > -58) ^ (o <= 25):
            F.append(math.floor(o / 8) + o)
    # print(F)
    return F


def main(C):
    boboboboboboboboboboboboboobobo = []
    for c in C:
        if not (c <= 68):
            boboboboboboboboboboboboboobobo.append(c)
    #     print(boboboboboboboboboboboboboobobo)

    F = getFFFFFFFFFFFFFFFFFu(boboboboboboboboboboboboboobobo)

    M = []
    for c in C:
        if (36 < c < 75):
            M.append(math.ceil(c / 8) - c)
    #     print(M)

    res_1 = len(M) * len(F)

    # res_1 = 0
    # for f in F:
    #     for m in M:
    #         res_1 += f * m

    res_2 = 0
    for f in F:
        res_2 += abs(f)

    res = res_1 - res_2

    return res


print("Test-1")
print(main({-96, 33, 1, 65, 40, 9, 77, -7, 59, 60}))
print("Test-2")
print(main({68, -27, 50, 19, -11, 86, -39, -71, -2, 31}))
print("Test-3")
print(main({-96, 40, 41, -15, 18, 83, 20, -41, 58, 94}))
