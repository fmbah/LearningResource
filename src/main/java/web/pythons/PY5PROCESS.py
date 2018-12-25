import os
print('Process (%s) started...' % os.getpid())
#Only works on Unix/Linux/Mac
pid = os.fork()
if pid == 0:
    print("i'm child process (%s) and my parent is (%s)" % (os.getpid(), os.getppid()))
else:
    print('i (%s) just created a child process (%s)' % (os.getpid(), pid))


from multiprocessing import Process
#子进程要执行的代码
def run_proc(name):
    print('Run child process %s (%s)...' % (name, os.getpid()))

from multiprocessing import Pool
import time, random

def long_time_task(name):
    print('Run task %s (%s)...' % (name, os.getpid()))
    start = time.time()
    time.sleep(random.random() * 3)
    end = time.time()
    print('Task %s runs %0.2f seconds.' % (name, (end - start)))
if __name__ == '__main__':
    print('Parent process %s.' % os.getpid())
    # p = Process(target=run_proc, args=('test', ))
    # print('Child process will start.')
    # p.start()
    # p.join()
    # print('Child process end..')
    p = Pool(4)
    for i in range(5):
        p.apply_async(long_time_task, args=(i, ))
    print('Waiting for all subprocesses done...')
    p.close()
    p.join()
    print('All subprocesses done.')

import subprocess
print('$ nslookup www.python.org')
r = subprocess.call(['nslookup', 'www.python.org'])
print('Exit code:', r)


