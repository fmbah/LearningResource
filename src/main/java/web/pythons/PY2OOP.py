class Student(object):
    overkey = 'overvalue'
    def __init__(self, name, age, sex):
        self.name = name
        self.age = age
        self.sex = sex
    def getDescription(self):
        #利用str的format格式化字符串
        #利用生成器推导式去获取key和self中key对应的值的集合
        return ",".join("{}={}".format(key,getattr(self,key)) for key in self.__dict__.keys())
    #重写__str__定义对象的打印内容
    def __str__(self):
        return "{}->({})".format(self.__class__.__name__,self.getDescription())
    def get__privateVar(self):
        return self.__privateVar
    def set__privateVar(self, privateValue):
        self.__privateVar = privateValue

student = Student('fmbah', 26, '1')

print('OOP run....')
student.name = 'fmbah'
student.set__privateVar('private value')
print(student.name, student.age, student.sex, student.get__privateVar())
print(student)
print('\n' * 3)
class Animal(object):
    def run(self):
        if isinstance(self, Dog):
            print('dog run....')
        elif isinstance(self, Cat):
            print('cat run....')
        else:
            print('animal run....')
class Dog(Animal):
    pass
class Cat(Animal):
    pass

dog = Dog()
dog.run()
cat = Cat()
cat.run()
print(isinstance(dog, Dog))
print(isinstance(dog, Animal))

#多态
def run_twice(tmp):
    tmp.run()

run_twice(dog)
run_twice(cat)

print('\n' * 3)
print(type(123), type('123'), type("123"), type(True), type([1,2]), type((1,[1])), type({'k1':'v1'}), type(lambda x: x))
import types
print(types.FunctionType == type(lambda x: x))
print(types.MappingProxyType == type(lambda x: x))
print(dir(dog))

s1 = Student('张三', 18, '男')
#实例优先级高于类
s1.overkey = 'overValue1'
print(Student.overkey, s1, s1.overkey)

#限制类中属性key值
class TupleBean(object):
    __slots__ = ('k1', 'k2', 'intKey')
    def set_intKey(self, value):
        if not isinstance(value, int):
            raise ValueError('must be an integer!')
        if value < 0 or value > 100:
            raise ValueError('must between 0 ~ 100')
        self.intKey = value
    def get_intKey(self):
        return self.intKey

tupleBean = TupleBean()
tupleBean.k2 = 'V3'
tupleBean.set_intKey(3)
print(tupleBean.get_intKey())

class AnnotationBean(object):
    __slots__ = ('_score')
    @property
    def score(self):
        return self._score
    @score.setter
    def score(self, value):
        if not isinstance(value, int):
            raise ValueError('must be an integer!')
        if value < 0 or value > 100:
            raise ValueError('must between 0 ~ 100')
        self._score = value
    @property
    def readonlyscore(self):
        return self._score + 100


annotationBean = AnnotationBean()
annotationBean.score = 60
print(annotationBean.score, annotationBean.readonlyscore)

#MixIn 多继承:通过组合模拟更多的情况
class Runnable(object):
    print('run....')
class Flyable(object):
    print('fly....')

class Bat(Runnable, Flyable):
    print('bat....')
#蝙蝠继承了两个类的属性(更容易理解的方式是组合了两个类的功能)
bat = Bat()

class Fib(object):
    def __init__(self):
        self.a, self.b = 0, 1
    def __iter__(self):
        return self
    def __next__(self):
        self.a, self.b = self.b, self.a + self.b
        if self.a > 100:
            raise StopIteration()
        return self.a
    def __getitem__(self, item):
        if isinstance(item, int):
            a, b = 1, 1
            for x in range(item):
                a, b = b, a + b
            return a
        if isinstance(item, slice):
            start = item.start
            stop = item.stop
            if start is None:
                start = 0
            a, b = 1, 1
            L = []
            for x in range(stop):
                if x >= start:
                    L.append(a)
                a, b = b, a + b
            return L
    def __getattr__(self, item):
        if item == 'age':
            return lambda: 19
    def __call__(self, *args, **kwargs):
        print('call invoke......')
for n in Fib():
    print(n)
f = Fib()
f()
print(f[10], f[0:5], f.age(), callable(min), callable(max), callable([1, 2]))

from enum import Enum
Month = Enum('Month', ('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'))
for name, member in Month.__members__.items():
    print(name, "==>", member, ",", member.value)
class Weekend(Enum):
    Sun = 0
    Mon = 1
    Tue = 2
    Wed = 3
    Thu = 4
    Fri = 5
    Sat = 6
print(Weekend.Sun, Weekend['Sun'], Weekend.Thu.value)
#动态语言(运行时动态编译),静态语言(编译时已定义)
print(type(Fib), type(f))
# class Hello(object):
#     def hello(self, name='world'):
#         print('hello %s' % name)
def fn(self, name='world'):
    print('hello %s' % name)
def fn1(self, name='world1'):
    print('hello %s' % name)
Hello = type('Hello', (object,), dict(hello=fn, hello1=fn1))
h = Hello()
h.hello()
h.hello1()
#metaclass



