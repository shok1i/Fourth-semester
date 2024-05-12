def main(path):
    return start(path)


def start(path):
    if path[0] == "XOJO":
        return second_UP(path)
    return second_DOWN(path)


def second_UP(path):
    if path[3] == 'CMAKE':
        return third(path)
    return 6


def third(path):
    if path[1] == 2006:
        if path[4] == 'GRACE':
            return 0
        if path[4] == 'ORG':
            return 1
        return 2

    if path[2] == 'HTML':
        return 3
    if path[2] == 'SWIFT':
        return 4
    return 5


def second_DOWN(path):
    if path[1] == 2006:
        return 7
    return third_second(path)


def third_second(path):
    if path[4] == 'GRACE':
        return 8
    if path[4] == 'ORG':
        return four(path)
    return 12


def four(path):
    if path[2] == 'HTML':
        return 9
    if path[2] == 'SWIFT':
        return 10
    return 11


print(main(['SCSS', 2011, 'PIKE', 'CMAKE', 'GRACE']))
print(main(['SCSS', 2006, 'SWIFT', 'CMAKE', 'GRACE']))
print(main(['XOJO', 2006, 'SWIFT', 'SAS', 'GRACE']))
print(main(['XOJO', 2006, 'SWIFT', 'CMAKE', 'ORG']))
print(main(['SCSS', 2011, 'PIKE', 'SAS', 'ORG']))
