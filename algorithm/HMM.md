## Summary
1. 隐马尔可夫模型是关于时序的概率模型，描述一个由隐藏的马尔科夫链随机生成不可观测的状态的序列，再由各个状态随机生成一个观测而产生观测的序列的过程.
    隐马尔可夫模型由初始状态概率向量_π_，状态转移概率矩阵_A_和观测概率矩阵_B_决定。因此，隐马尔可夫模型可以写成 λ = (_A_,_B_,_π_).<br />
    隐马尔可夫模型是一个生成模型，表示状态序列和观测序列的联合分布，但是状态序列是隐藏的，不可观测，由此而得名.<br />
    隐马尔可夫模型可以用于标注，这时状态对应着标记，标注问题是给定观测序列预测其对应的标记序列
<br />
2. 概率计算问题，给定模型 _λ_ = (_A_,_B_,_π_) 和观测序列 _O_ = (_o1_,_o2_,...,_oT_)，计算在模型 _λ_ 下观测序列O出现的概率_P_(_O_|_λ_). 前向-后向算法是通过递归的计算前-后向概率高效地进行隐马尔可夫模型的概率计算.
<br />
3. 学习问题，已知观测序列 _O_ = (_o1_,_o2_,...,_oT_)，估计模型 _λ_ = (_A_,_B_,_π_) 参数，使得在该模型下观测序列概率 _P_(_O_|_λ_) 最大. 即用极大似然估计的方法估计参数. Baum-Welch 算法，也就是EM算法可以高效的对 隐马尔可夫模型进行训练，这是一种无极监督学习方法
<br />
4. 预测问题，已知模型  _λ_ = (_A_,_B_,_π_) 和观测序列 _O_ = (_o1_,_o2_,...,_oT_)，求对给定观测序列条件概率 _P_(_O_|_λ_) 最大的状态序列 _I_(_i1_,_i2_,...,_iT_)，viterbi algorithm 运用动态规划的思想高效的求解最优路径，即概率达到最大的状态序列
<br />