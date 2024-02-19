def main(n):
    if n == 0:
        return -0.22
    elif n == 1:
        return 0.98
    return 1 - main(n - 1) - 40 * (main(n - 2) ** 3 - main(n - 2) ** 2) ** 2


print("{:e}".format(main(8)))
