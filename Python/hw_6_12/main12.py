import struct


def main(data):
    # Проверить сигнатуру
    if data[0:3] != b'HSA':
        raise ValueError('Неверная сигнатура')
    # Разобрать структуру B
    fmt_B = '<H I I'
    b_offset = 4
    b = struct.unpack_from(fmt_B, data, b_offset)
    # Разобрать структуру A
    fmt_A = '<I I q'
    a_offset = struct.calcsize(fmt_B) + 4
    a = struct.unpack_from(fmt_A, data, a_offset)
    # Разобрать структуру A
    fmt_A2 = '<' + 'i' * a[0]
    a2_offset = a[1]
    a2 = struct.unpack_from(fmt_A2, data, a2_offset)
    # Разобрать структуру C1
    fmt_C = '<d H i'
    c_offset = b[0]  # b_offset + struct.calcsize(fmt_B)
    c = struct.unpack_from(fmt_C, data, c_offset)
    # Разобрать структуру D
    fmt_D = '<' + 'I B f' * 5
    d_offset = b[0] + struct.calcsize(fmt_C)
    d = struct.unpack_from(fmt_D, data, d_offset)
    # Разобрать структуру C2
    fmt_C2 = '<I' + 'I' * 3
    c2_offset = (b[0] +
                 struct.calcsize(fmt_C) +
                 struct.calcsize(fmt_D))
    c2 = struct.unpack_from(fmt_C2, data, c2_offset)
    # Разобрать структуру C2
    fmt_B2 = '<' + 'Q' * b[1]
    b2_offset = b[2]  # b_offset + struct.calcsize(fmt_B)
    b2 = struct.unpack_from(fmt_B2, data, b2_offset)
    result = {
        'A1': {
            'B1': {
                'C1': c[0],
                'C2': c[1],
                'C3': c[2],
                'C4': [
                    {'D1': d[0], 'D2': d[1], 'D3': d[2]},
                    {'D1': d[3], 'D2': d[4], 'D3': d[5]},
                    {'D1': d[6], 'D2': d[7], 'D3': d[8]},
                    {'D1': d[9], 'D2': d[10], 'D3': d[11]},
                    {'D1': d[12], 'D2': d[13], 'D3': d[14]},
                ],
                'C5': c2[0],
                'C6': list(c2[1:])
            },
            'B2': list(b2)
        },
        'A2': list(a2),
        'A3': a[2]
    }
    # Составить результат разбора
    return result


# Пример 2
data1 = (b'HSA\xcc\x1e\x00\x03\x00\x00\x00i\x00\x00\x00\x05\x00\x00\x00\x81\x00'
         b'\x00\x00\xeb\xb8 \x96\xcb\\\xcaT\x84\x16\xdc\x9f\x8f\x03\xec?\x83/\x01[\xc4:'
         b'>\xe5\xa5\x1e\xb9\x13\xeb\xa1\xbe\xbd\\0\xe996\xd3\x99\xbe\x95\xb6I(\xf3s'
         b"nv?)z\xeab\xc0'!\x18\xbf\x98g\xe4\x8eP\xc1|\x8e<x\x8b\x1cL4\x84\xa7"
         b'\x9e6\x94\xaf\xa9W,\x9avX\xb7\x80.\x08\xe6\xe6G\x13\xe3\xa7Gm\xf32'
         b"\xba\xa2\xa4\xe6\xda\xd2oI\xe4\x04-H\xa5\xe3\x16\xab\x06\x0b\x1f]U'\x15K"
         b'\xf3\x16\xb4\xa4\x8f')
example1 = main(data1)

print(str(example1))

data2 = (b'HSA\xcc\x1e\x00\x02\x00\x00\x00i\x00\x00\x00\x04\x00\x00\x00y\x00'
         b'\x00\x00O\xbc\xbc\x1b-k\xf2\x8eP\xfe\xd0\x95mY\xbd\xbf\xb6\xe2'
         b'\xcb\xf9\x9a\xf6,l\xca\xa1S\xd4A&\xbf\xf6|\xac\xa5\x8e\xe2\xab=\xbf=\xdb'
         b'\x8d\xbb{\xc9 \x7f?z\xc3\xb2\x8d\xb8\xfa\\`\xbe\xfdC\x11\x9f\xd6Lx,'
         b'\xbff_\x08\xd7\xc4p\xb5\xeb\x0f\x08%p3\xe3\xc29\xfc\x90\xbb|\xf3\xd3\x8a'
         b'*\xc7\\[\x9a\x82\x96\xa8\x8b\x82\x1f&\xb17\xb4\xf6\x91\xcdF\x88R\xb0\xcci'
         b'\x00')
example2 = main(data2)

print(example2)
