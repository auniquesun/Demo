# -*- encoding: utf-8 -*-

import random
import json

def choose_line(lines):
    while True:
        chosen_line = random.choice(lines)
        if 'abstract' in chosen_line and 'title' in chosen_line:
            return chosen_line

files = ['dblp.v10/dblp-ref/dblp-ref-0.json', 
         'dblp.v10/dblp-ref/dblp-ref-1.json', 
         'dblp.v10/dblp-ref/dblp-ref-2.json', 
         'dblp.v10/dblp-ref/dblp-ref-3.json']

chosen_papers = []

for i in range(len(files)):
    with open(files[i]) as rf:
        lines = rf.readlines()   # 读取一个文件的所有行
        paper = {}
        j = 0
        while j < 25:
            j += 1
            chosen_line = choose_line(lines)
            print('='*10)
            print(chosen_line)
            print()
            data = json.loads(chosen_line)
            paper['abstract'] = data['abstract']
            paper['title'] = data['title']
            # paper['id'] = data['id']
            # paper['authors'] = data['authors']
            # paper['n_citation'] = data['n_citation']
            # paper['references'] = data['references']
            # paper['venue'] = data['venue']
            # paper['year'] = data['year']
            chosen_papers.append(paper)
    
            with open('chosen_paper.txt', 'a') as wf:   # 以追加的方式写入文件
                wf.write(str(paper) + '\n')

print('len(chosen_papers):', len(chosen_papers))
print(chosen_papers[0])