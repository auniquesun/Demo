# -*- encoding: utf-8 -*-

import random
import json

def choose_line(lines):
    while True:
        chosen_line = random.choice(lines)
        if 'abstract' in chosen_line and 'title' in chosen_line:
            return chosen_line

filename = 'paper_100K.txt'

chosen_papers = []

print('choose paper start')
with open(filename) as rf:
    lines = rf.readlines()   # 读取一个文件的所有行
    j = 0
    # first try
    while j < 100:           # 每个file中选25篇paper
        try:
            j += 1
            # 选出的chosen_line仅包含abstract和title
            chosen_line = choose_line(lines)
            print('='*10)
            print(chosen_line)
            print()
            chosen_papers.append(chosen_line)
            print('paper(',j,'):','选择成功')
        except:
            print('paper(',j,'):','选择失败')


with open('new_labelling_paper_100.txt', 'w') as wf:   # 以追加的方式写入文件
    for paper in chosen_papers:
        wf.write('%s\n' % paper)    # 最后 new_labelling_paper_100.txt 文件会有200行，100行paper，100行空行(写入了换行符)
print('choose paper done')

print('len(chosen_papers):', len(chosen_papers))
print(chosen_papers[0])
