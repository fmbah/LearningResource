import os
import os.path

binaryStr = None
try:
    home = os.environ['HOME']
    filePath = os.path.join(home, 'hello.java')
    f = open(filePath, 'r')
    # print('try....', f.read())
    # binaryStr = open("/root/图片/2018-09-20 08-57-09 的屏幕截图.png", 'rb')
    # print(binaryStr.read())
finally:
    if f:
        f.close()
    if binaryStr:
        binaryStr.close()
    # print("finally run...")

with open(filePath, 'r') as f:
    # print('with open....', f.read())
    for line in f.readlines():
        # print(line.strip())
        print()

with open('/root/writeHello.txt', 'w') as f:
    f.write('Hello World!')


from io import StringIO
f = StringIO()
f.write("Hello")
f.write(" ")
f.write("World")
print(f.getvalue())

f = StringIO('Hello!\nHi!\nGoodBye!')
while True:
    s = f.readline()
    if s == '':
        break
    print(s.strip())

from io import BytesIO
f = BytesIO()
f.write('中文'.encode('utf-8'))
print(f.getvalue())


import os
print(os.name, os.uname())
print(os.environ.get('PATH'))
print(os.path.abspath('.'))
# os.mkdir('/root/writeHello')
# os.rmdir('/root/writeHello')
# os.rename('/root/writeHello.txt', '/root/writeHello.py')
# os.remove('/root/writeHello.py')
# os.remove('/root/writeHello.txt')
print(len([x for x in os.listdir('.') if os.path.isfile(x) and os.path.splitext(x)[1]=='.py']))


import pickle
d = {'name':'Bob'}
f = open('dumps.txt', 'wb')
pickle.dump(d, f)
f.close()
f = open('dumps.txt', 'rb')
d = pickle.load(f)
f.close()
print(d)

import json
d = {'name':'Bob', 'age': 8, 'score': 80.0, 'isNone': None, 'isTrue': True, '[]': list([1, 2, 3]), 'dict': {'k1':'v1'}}
json_str = json.dumps(d)
print(json_str)
json_py = json.loads(json_str)
print(json_py)

class Student:
    def __init__(self, name, age, sex):
        self.name = name
        self.age = age
        self.sex = sex

s = Student('小红', 11, '女')
json_s = json.dumps(s, default=lambda obj: obj.__dict__)
print(json_s)
json_s_py = json.loads(json_s)
print(json_s_py)

from turtle import *

# 设置笔刷宽度:
width(4)
# 前进:
forward(200)
# 右转90度:
right(90)
# 笔刷颜色:
pencolor('red')
forward(100)
right(90)
pencolor('green')
forward(200)
right(90)
pencolor('blue')
forward(100)
right(90)
# 调用done()使得窗口等待被关闭，否则将立刻关闭窗口:
# done()

