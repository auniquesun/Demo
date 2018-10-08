# -*- encoding: utf-8 -*-

import re
import io
import jieba
import pandas as pd
import numpy as np
from gensim.models import word2vec
from gensim.models import Word2Vec


def tokenize():
    print('='*10, 'tokenizing', '='*10)
    # first try
    #with io.open('chosen_paper.txt','r',encoding='utf-8') as content:
    # second try
    with io.open('paper_100K.txt','r',encoding='utf-8') as content:
        for line in content:
            tokens = jieba.cut(line)
            # first try
            #with io.open('vocabulary.txt', 'a', encoding='utf-8') as output:
            # second try
            with io.open('vocabulary_100K.txt', 'a', encoding='utf-8') as output:
                output.write(' '.join(tokens))
    print('='*10, 'tokenizing done', '='*10)

def train():
    print('='*10, 'training', '='*10)

    context = 20          # Context window size
    downsampling = 1e-3   # Downsample setting for frequent words
    # first try
    #num_features = 20    # Word vector dimensionality
    #min_word_count = 2   # Minimum word count
    #num_workers = 16       # Number of threads to run in parallel
    # vocabulary.txt 共包含1100篇文章
    #sentences = word2vec.Text8Corpus("vocabulary.txt")

    # second try
    num_features = 50    # Word vector dimensionality
    min_word_count = 10  # Minimum word count
    num_workers = 32       # Number of threads to run in parallel
    # 共包含10000篇文章
    sentences = word2vec.Text8Corpus("paper_100K.txt")


    model = word2vec.Word2Vec(sentences, workers=num_workers, \
            				  size=num_features, min_count = min_word_count, \
            				  window = context, sg = 1, sample = downsampling)

    print('='*10, 'training done', '='*10)

    model.init_sims(replace=True)
    model.save("vocabulary_100K.model")
    print('='*10, 'saving model done', '='*10)
    # 可以在加载模型之后使用另外的句子来进一步训练模型
    # model = gensim.models.Word2Vec.load('/tmp/mymodel')
    # model.train(more_sentences)

def get_train_data():
    # first try
    #model = Word2Vec.load('vocabulary.model')
    # second try
    model = Word2Vec.load('vocabulary_100K.model')
    print('neural:', model['neural'])
    
    X_train, Y_train = [], []

    with open('label.csv') as f:
        for index, line in enumerate(f):  # 按行读取csv文件
            print('第', index, '行:', line)
            col_list = line.split(',')
            problem = col_list[0]
            method = col_list[1]

            print('>'*3, 'problem:', problem)
            problem_words = problem.split(' ')
            x_problem = np.zeros((1, 50))
            y_problem = 0
            for word in problem_words:
                try:
                    word = re.search(r'\w+', word).group(0).lower() # 转换成小写
                    # print('word:', word)
                    # print(model[word])
                    print('='*20)
                    # print('type:', type(model[word]))
                    # print('+-'*10, model[word].shape())
                    x_problem += model[word]    # 对每个句子中单词的embedding的求和
                except Exception as err:
                    print(str(err))


            print('<'*3, 'method:', method)
            method_words = method.split(' ')
            x_method = np.zeros((1, 50))
            y_method = 1
            for word in method_words:
                try:
                    word = re.search(r'\w+', word).group(0).lower() # 转换成小写
                    print('word:', word)
                    print(model[word])
                    x_method += model[word]
                except Exception as err:
                    print(str(err))
            if index != 0:
                X_train.append([np.sum(x_problem)])   # 把embedding每一维数值求和，再把这个和放入一个list；
                                                    # 这样做的目的是为满足svm对数据格式的要求
                X_train.append([np.sum(x_method)])
                Y_train.append(y_problem)
                Y_train.append(y_method)

    print('Y_train:', Y_train)
    return X_train, Y_train

if __name__ == '__main__':
    print('='*10, 'starting', '='*10, '\n')
    #tokenize()
    train()
    print('='*10, 'ending', '='*10, '\n')
