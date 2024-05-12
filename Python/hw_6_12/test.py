def transform_table(input_table):
    # Удаление пустых столбцов
    input_table = [list(filter(lambda x: x is not None, col)) for col in zip(*input_table) if
                   any(cell is not None for cell in col)]

    # Удаление пустых строк
    input_table = [row for row in input_table if any(cell is not None for cell in row)]

    # Разбиение одного из столбцов по разделителю "|"
    for row in input_table:
        split_data = row[2].split('|')
        if len(split_data) == 2:
            row[2] = split_data
        else:
            row[2] = [split_data[0], '0']

    # Преобразование содержимого ячеек
    for row in input_table:
        row[0] = ' '.join(row[0].split()[::-1])
        row[1] = row[1].replace(' ', '-')
        row[2][1] = str(int(float(row[2][1]) * 100)) + '%'

    # Транспонирование таблицы
    output_table = [list(row) for row in zip(*input_table)]

    return output_table


# Пример использования
input_table = [
    ['Зеночяк Владислав', None, '806 197-6185|0.03'],
    ['Гикегук Николай', None, '334 358-8254|0.17'],
    ['Лутевман Амир', None, '715 854-4205|0.75']
]

output_table = transform_table(input_table)
print(output_table)