try:
    print('try ...')
    i = int('a')
    print('i = ', i)
    r = 10 / 0
    print('r = ', r)

    #断言, n 不为0 不报错； n为0 出现错误,这样的情况,后面的程序一定会出错
    assert n != 0, 'n is zero'

except ValueError as v:
    print('except:', v)
except ZeroDivisionError as e:
    print('except:', e)
finally:
    print('finally....')
print('END')

import logging
logging.error('msg %s' % 'zeroDivisionError')

#启动pdb进行单步调试, python -m pdb filename.py
#l 查看代码
#n 单步骤执行代码
#p 变量名 查看变量值
#q 退出调试

import pdb
#此行进入调试模式, c继续运行, p查看变量
pdb.set_trace()
