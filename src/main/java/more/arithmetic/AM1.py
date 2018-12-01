def reverse(x):
    print('reverse num: %s' % x)
    max = pow(2, 31) - 1
    min = pow(-2, 31)
    print(max)
    print(min)
    isMinus = False
    if (x < 0):
        isMinus = True
        x = x * -1

    tmp = 0
    while(True):
        if(x == 0):
            break
        remainder = x % 10
        #如果此时tmp*10 + remainder超过了最大值,那么则返回0==>反推下面的公式
        if (max // 10 < tmp or (max // 10 == tmp and remainder > max % 10)):
            tmp = 0
            break
        if (isMinus):
            tmp1 = tmp
            ##如果此时tmp1*10 + remainder比最小值小,那么则返回0===>反推下面公式
            if (min // 10 > tmp1 or (min // 10 == tmp1 and remainder > min * -1 % 10)):
                tmp = 0
                break

        tmp = tmp * 10 + remainder
        x = x // 10

    if (isMinus):
        tmp = tmp * -1

    print(tmp)
    return tmp

##执行用时: 52 ms, 在Reverse Integer的Python提交中击败了12.36% 的用户
if __name__ == '__main__':
    print(reverse(-123))


