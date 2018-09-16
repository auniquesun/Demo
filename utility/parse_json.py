import json

'''
My Solution
'''
def parse_json_me(string):

	start = string.find('{')
	end = string.find('}')

	string = string[start:end+1]	# 截取 '{...}'
	
	# 找到 '{' 之后的第一个 字母字符
	for idx, char in enumerate(string):
		if char.isalpha() == True:
			string = string[:idx] + '"' + string[idx:]	# '"' 加在第一个 字母字符之前
			break

	idx_quote = string.rfind(':')	# 从后往前找；标记冒号的位置

	while idx_quote != -1:
		if string[idx_quote-1] != '"':
			string = string[:idx_quote] + '"' + string[idx_quote:]	# '"' 加在冒号之前
		
		tmp = string[:idx_quote]	# 从后往前找；标记逗号的位置
		idx_colon = tmp.rfind(',')
		if idx_colon != -1:
			if tmp[idx_colon+1] != '"':
				string = string[:idx_colon + 1] + '"' + string[idx_colon+1:]	# '"' 加在逗号之后
			idx_quote = tmp.rfind(':')
		else:
			break

	print(string)
	return json.loads(string)

'''
General Solution
'''
def parse_json_general(string):
    '''
    把一个类json字符串转换成json字符串
    '''
    if len(string) == 0:
        raise Exception('argument value {} error'.format(string))

    pattern = r"([a-zA-Z_][a-zA-Z_0-9]*)\s*\:"
    repl = lambda match: '"{}":'.format(match.group(1))
    json_str = string[string.find('{'):string.find('}')+1]
    json_str = re.sub(pattern, repl, json_str)
    
    return json.loads(json_str)

print(parse_json_general('{  aopr:56,tip:35,la:["jif"]}'))