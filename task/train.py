# import keras
# import keras.backend as K
from get_embeddings import get_train_data
from sklearn import svm


def model():
	embeddings = keras.layers.Input(shape=(20,), name='embeddings')
	rnn_embeddings = keras.layers.GRU(16, activation='relu')(embeddings)
	output = keras.layers.Dense(1, activation='sigmoid')(rnn_embeddings)

	model = keras.models.Model(inputs = [embeddings], outputs = output)


	return model

def train():
	# model = model()
	# print(model.summary())

	# X,y = get_train_data()
	# optimizer = keras.optimizers.Adam(lr=0.001)

	# rnn_model.compile(optimizer=optimizer,
 #                  loss=keras.losses.binary_crossentropy,
 #                  metrics=["accuracy"])

	# X_train, X_dev = X[:90], X[90:]
	# y_train, y_dev = y[:90], y[90:]
	# rnn_model.fit(X_train, y_train,
 #                 batch_size=10,
 #                 epochs=3,
 #                 validation_data=(X_dev, y_dev))

	X,y = get_train_data()	# 总共有两百条数据
	X_train, X_dev = X[:180], X[180:]	# 前180条用作训练
	# X_train, X_dev = X[:], X[180:]
	y_train, y_dev = y[:180], y[180:]		# 后20条用作预测

	clf = svm.SVC()
	print('%'*10, 'start training', '%'*10)
	res = clf.fit(X_train, y_train)
	print('res:', res)

	print('%'*10, 'end training', '%'*10)
	preds = clf.predict(X_dev)
	print('预测结果:')
	for index, pred in enumerate(preds):
		flag = ''
		if index % 2 == pred:
			flag = 'right'
		else:
			flag = 'wrong'
		print(index, ':', pred, '==>', flag)

if __name__ == '__main__':
	train()