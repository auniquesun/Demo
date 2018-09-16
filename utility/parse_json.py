import json

def parse_json(string):

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

# parse_json('{aopr:56,tip:35,la:["jif"]}')
parse_json('{  aopr:56,tip:35,la:["jif"]}')