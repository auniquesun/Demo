## summary
1. 概率无向图模型是由无向图表示的联合概率分布，无向图上节点的连接关系表示联合概率随机变量集合之间的条件独立性，即马尔可夫性。因此，概率无向图模型也成为马尔可夫随机场。<br />
概率无向图模型或者马尔可夫随机场的联合概率分布可以分解为无向图上 **最大团的正值函数乘积** 的形式。<br />
2. 条件随机场是给定输入随机变量 _X_ 条件下，输出变量 _Y_ 的条件概率分布模型，其形式为参数化的对数线性模型。条件随机场的 *最大特点* 是 **假设输出变量之间的联合概率分布构成概率无向图模型，即马尔可夫随机场。条** 件随机场是判别模型。<br />
3. **线性链条件随机场是定义在观测序列与标记序列上的条件随机场。** 线性链条件随机场一般表示为给定观测序列条件下标记序列的条件概率分布，由参数化的对数线性模型表示。模型包含特征及相应的权值，特征是定义在线性链的边与节点上的。<br />
4. 线性链条件随机场的概率通常是利用 **前向-后向** 算法。<br />
5. 条件随机场的学习方法通常是 **极大似然估计或正则化的极大似然估计**，即在给定训练数据的条件下，通过极大化训练数据的对数似然函数以估计模型参数。具体的算法有 **改进的迭代尺度算法、梯度下降法，拟牛顿法** <br />
6. 线性链条件随机场的一个重要应用是标注，**维特比算法是给定观测序列求条件概率最大的标记序列的方法** <br />