## summary
1. 提升方法是将弱学习算法提升为强学习算法的统计学习方法。它通过反复修改训练数据的权值分布，构建一系列基本分类器，并将这些弱分类器线性组合，构成强分类器。代表性的提升方法是AdaBoost
2. AdaBoost算法的特点是通过迭代每次学习一个基本分类器，每次迭代中，提高那些在前一轮中被分类器误分类数据的权值，同时降低那些被正确分类的数据的权值，最后，AdaBoost将基本分类器的线性组合作为强分类器，其中给分类误差率小的分类器大的权值，给分类误差率大的分类器小的权值
3. AdaBoost的训练误差分析表明，每次迭代可以减少它在训练数据上的分类误差率，说明它作为提升方法的有效性
4. AdaBoost算法的的一个解释是它是前向分布算法的一种实现。在这个方法里，模型是加法模型，损失函数是指数损失，算法是前向分布算法
5. 提升树是以分类树或回归数为基本分类器的提升方法，提升树被认为是统计学习方法中最有效的方法之一