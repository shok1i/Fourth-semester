def checker(trace, check):
    flag = True
    for i in range(len(check)):
        if trace[0] != check[i]:
            flag = True
        else:
            flag = False
            break
    return flag


def main(input_data):
    output_data_hue_t = []
    output_data_hue_a = []
    output_data_hue_percent = []
    output_data_hue_name = []
    output_data_hue_fafa = []
    check = []
    flag = True
    for trace in input_data:
        flag = checker(trace, check)
        if flag:
            email = trace[0]
            hue_t = (email.split('!')[0]
                     .replace('0', 'false')
                     .replace('1', 'true'))
            output_data_hue_t.append(hue_t)
            hue_a = email.split('@')[1]
            output_data_hue_a.append(hue_a)
            percent = float(trace[1])
            percent = int(percent * 100)
            perper = str(percent) + "%"
            output_data_hue_percent.append(perper)
            name = trace[2].split(',')[0]
            output_data_hue_name.append(name)
            check.append(email)
    output_data_hue_fafa.append(output_data_hue_t)
    output_data_hue_fafa.append(output_data_hue_percent)
    output_data_hue_fafa.append(output_data_hue_a)
    output_data_hue_fafa.append(output_data_hue_name)
    return output_data_hue_fafa


input_table = [['1!dutli91@mail.ru', '0.3', 'Дутли, М.Д.'], ['1!nafko43@yahoo.com', '0.3', 'Нафко, В.Г.'],
               ['1!nafko43@yahoo.com', '0.3', 'Нафко, В.Г.'], ['1!bimev42@mail.ru', '0.6', 'Бимев, Т.З.']]
autism = main(input_table)
print(autism)
