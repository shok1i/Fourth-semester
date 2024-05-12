def reverse_string(s):
    return s[::-1]


def spliting(binary_string):
    while len(binary_string) <= 40:
        binary_string = '0' + binary_string
    reversed_binary_string = reverse_string(binary_string)
    arr = [
        reverse_string(reversed_binary_string[0]),
        reverse_string(reversed_binary_string[1:8]),
        "0000000000",
        reverse_string(reversed_binary_string[18:28]),
        reverse_string(reversed_binary_string[28:35]),
        reverse_string(reversed_binary_string[35:])
    ]
    # print(arr)
    return arr


def output(split_binary):
    output_str = \
        (
            split_binary[1] +
            split_binary[3] +
            split_binary[0] +
            split_binary[2] +
            split_binary[4] +
            split_binary[5]
        )
    # output_str = output_str.strip('0')
    # print(output_str)

    decimal_num = int(output_str, 2)
    return hex(decimal_num)


def main(input_string):
    split_binary = spliting(bin(input_string)[2:])
    hex_res = str(output(split_binary))
    return hex_res


print(main(811043052545),  "  class=", main(811043052545).__class__)
