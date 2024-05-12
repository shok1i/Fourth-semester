def main(data_string):
    result = []
    # print("data_string (before): ", data_string)
    data_string = data_string.replace(' ', '')
    data_string = data_string.replace(',', '')
    data_string = data_string.replace('"', '')
    data_string = data_string.replace('\n', '')
    # print("data_string (after): ", data_string)
    block_start = data_string.find('<<')
    block_end = data_string.find('>>')
    while block_start != -1 and block_end != -1:
        block_string = data_string[block_start + 2:block_end]
        # print(block_string)
        label_start = block_string.find('@')
        label_end = block_string.find(':')
        label_string = block_string[label_start + 1:label_end]
        # print(label_string)
        values_start = block_string.find('(')
        values_end = block_string.find(')')
        values_string = block_string[values_start + 1:values_end]
        # print(values_string)
        values_arr = values_string.split('#')
        meh = []
        for value in values_arr:
            if value != '':
                meh.append(int(value))
        # print(values_arr)
        pair = (label_string, meh)
        result.append(pair)
        block_start = data_string.find('<<', block_end)
        block_end = data_string.find('>>', block_end + 1)
    # print(result)
    return result


stringgg = main('{{<< def @"anma":array( #-3661 ,#2634 ) >>, <<def @"mave_330" :\narray( #-7664 , #-4404 ) >>, << def @"rarabi_987": array(#-7016 ,\n#3342 )>>, << def @"raorer_727" : array(#4657 , #-1547) >>, }}')

print(stringgg)