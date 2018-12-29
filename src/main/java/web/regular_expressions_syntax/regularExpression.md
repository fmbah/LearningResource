### 正则表达式语法

1. 字符'\'放在非特殊字符前,表明'\'后的字符不能按照字面意思理解；
    * ab: 匹配ab字符
    * a\b: 匹配a字符,\b表示匹配边界
    ````
            RegularExpression expression = new RegularExpression("ab");//匹配ab字符
            System.out.println(expression.matches("ab"));
    
            RegularExpression expression1 = new RegularExpression("a\\b");//匹配a字符, 表达式需要转义
            System.out.println(expression1.matches("a") + "===" + expression1.matches("ab"));
    ````
2. 字符'^'对于目标字符开头进行匹配,如果开启了多行匹配标识,对于换行后的首字符也会进行匹配
    ````
            RegularExpression expression = new RegularExpression("^A");//匹配开头元素为A
            System.out.println(expression.matches("Abac"));
    
            String pattern = "^A";
            System.out.println("Abac".matches(pattern));//匹配结果不一致,可以使用上面的工具类
    
            //jquery执行, node执行
            //var pattern = /^A/
            //pattern.test('Abac')
    
            //py3执行
            //import re
            //p = re.compile('^A')
            //p.match('Abac')
    ````
3. 字符'$'对于目标字符结尾进行匹配,如果开启了多行匹配标识,对于换行后的尾字符也会进行匹配
    ````
            RegularExpression expression = new RegularExpression("t$");
            System.out.println(expression.matches("eat") + "==" + expression.matches("eater"));
    
            String pattern = "t$";
            System.out.println("eat".matches(pattern) + "===" + "eater".matches(pattern));//实现机制不同, 匹配结果不同
    
            //jquery执行, node执行
            //var pattern = /t$/
            //pattern.test('eat')
            //pattern.test('eater')
    
            //py3执行
            //import re
            //re.search('t$', 'eat')
            //re.search('t$', 'eater')
    ````
4. 字符'*'匹配前面表达式0次或者更多次
    ````
            RegularExpression expression = new RegularExpression("bo*");
            System.out.println(expression.matches("A ghost booooed") + "===" + expression.matches("A bird warbled") + "===" + expression.matches("A goat grunted"));
    
            String pattern = "bo*";
            System.out.println("A ghost booooed".matches(pattern));//fijian结果依然不同
    
            //jquery执行, node执行
            //var pattern = /bo*/
            //pattern.test('A ghost booooed')
            //pattern.test('A bird warbled')
            //pattern.test('A goat grunted')
    
            //py3执行
            //import re
            //p = re.compile('bo*')
            //p.match('A ghost booooed')
            //p.match('A bird warbled')
            //p.match('A goat grunted')
    ````
5. 字符'+'匹配前面表达式1次或者更多次,与字符'*'大致相同
6. 字符'?'匹配前面的表达式0次或1次
    ````
            RegularExpression expression = new RegularExpression("e?le?");
            System.out.println(expression.matches("angel") + "==" + expression.matches("angle") + "===" + expression.matches("oslo"));
    ````
7. 字符'.'（小数点）匹配除换行字符之外的任何单个字符
    ````
            RegularExpression expression = new RegularExpression(".n");
            System.out.println(expression.matches("nay") + "==" + expression.matches("an") + "===" + expression.matches("on"));
    ````
8. 字符'\n' 如果n是正整数，则返回对最后一个子字符串的引用，该子字符串匹配正则表达式中的n个括号（计算左括号）
    ````
            RegularExpression expression = new RegularExpression("apple(,)\\sorange\\1");
            System.out.println(expression.matches("apple, orange, cherry, peach."));
    ````
9. 类型(?:x) 
10. 类型x(?=y|z) 匹配字符串x后跟着y或者z
    ````
            RegularExpression expression = new RegularExpression("Jack(?=Sprat)");
            System.out.println(expression.matches("JackSprat111"));
            RegularExpression expression1 = new RegularExpression("Jack(?=Sprat|Frost)");
            System.out.println(expression1.matches("Jack1111") + "==" + expression1.matches("JackFrost1111") + "==" + expression1.matches("JackSprat111"));
    ````
11. 类型x(?!y) 匹配字符串x后面字符串不能是y
    ````
            RegularExpression expression = new RegularExpression("\\d+(?!\\.)");
            System.out.println(expression.matches("3.141"));
    
            //jquery exec
            //var pattern = /\d+(?!\.)/
            //pattern.test('3.141')
            //py3 exec
            //import re
            //re.search('\\d+(?!\\.)', '3.141')
            //<_sre.SRE_Match object; span=(2, 5), match='141'>
    ````
12. 类型x|y 目标字符串匹配x,如果没有匹配成功,再去匹配y
    ````
            RegularExpression expression = new RegularExpression("green|red");
            System.out.println(expression.matches("green apple") + "==" + expression.matches("red apple") + "==" + expression.matches("black apple"));
    
            RegularExpression expression1 = new RegularExpression("a*|b");
            System.out.println(expression1.matches("dkdkdkj"));
            //jquery exec
            //var pattern = /green|red/
            //        > pattern.test('green apple')
            //        true
            //                > pattern.test('red apple')
            //        true
            //                > pattern.test('black apple')
            //        false
                    //py3 exec
            //        >>> p = re.compile('green|red')
            //                >>> p.match('green apple')
            //                <_sre.SRE_Match object; span=(0, 5), match='green'>
            //>>> p.match('red apple')
            //                <_sre.SRE_Match object; span=(0, 3), match='red'>
            //>>> p.match('black apple')
            //                >>> print(p.match('black apple'))
            //        None
    ````
13. 字符串'{n}' 精确匹配前面表达式出现的次数,n必须为正整数
    ````
            RegularExpression expression = new RegularExpression("a{2}");
            System.out.println(expression.matches("candy") + "==" + expression.matches("caandy") + "==" + expression.matches("caaandy"));
    
            //jquery exec
            //        > var pattern = /a{2}/
            //        undefined
            //                > pattern
            //                /a{2}/
            //> pattern.test('candy')
            //        false
            //                > pattern.test('caandy')
            //        true
            //                > pattern.test('caaandy')
            //        true
            //py3 exec
            //        >>> re.search('a{2}','candy')
            //                >>> re.search('a{2}','caandy')
            //                <_sre.SRE_Match object; span=(1, 3), match='aa'>
            //>>> re.search('a{2}','caaandy')
            //                <_sre.SRE_Match object; span=(1, 3), match='aa'>
    ````
14. 字符串'{n,}' 匹配前面表达式至少出现的次数,n必须为正整数
    ````
            RegularExpression expression = new RegularExpression("a{2,}");
            System.out.println(expression.matches("candy") + "==" + expression.matches("caandy") + "==" + expression.matches("caaandy"));
    
            //jquery exec
            //        > var pattern = /a{2,}/
            //        undefined
            //                > pattern
            //                /a{2,}/
            //> pattern.test('candy')
            //        false
            //                > pattern.test('caandy')
            //        true
            //                > pattern.test('caaandy')
            //        true
            //py3 exec
            //        >>> re.search('a{2,}','candy')
            //                >>> re.search('a{2,}','caandy')
            //                <_sre.SRE_Match object; span=(1, 3), match='aa'>
            //>>> re.search('a{2,}','caaandy')
            //                <_sre.SRE_Match object; span=(1, 3), match='aa'>
    ````
15. 字符串'{n,m}' 匹配前面表达式至少出现n次, 最多出现m次, n < m, n/m必须为正整数
    ````
            RegularExpression expression = new RegularExpression("a{2,3}");
            System.out.println(expression. matches("jaaaaj") + "==" + expression.matches("jaj"));
    
            //jquery exec
            //var pattern = /a{2,3}/
            //pattern.test('jaaaaj')
            //true
            //pattern.test('jaj')
            //false
            //py3
            //import re
            //re.search('a{2,3}','jaaaaj')
            //<_sre.SRE_Match object; span=(1, 4), match='aaa'>
    ````
16. 字符串'[xyz]' 匹配字符集,目标字符匹配表达式任何一个字符,包括转义符号,以'.' '*'这样的字符,不需要做转义
    ````
                RegularExpression expression = new RegularExpression("[a-d]");
        //        RegularExpression expression = new RegularExpression("[abcd]");
                System.out.println(expression.matches("brisket") + "==" + expression.matches("city"));
        
                RegularExpression expression1 = new RegularExpression("[a-z.]+");
                System.out.println(expression1.matches("test.i.ng"));
        
                RegularExpression expression2 = new RegularExpression("[\\w.]+");
                System.out.println(expression1.matches("test.i.ng"));
        
                //jquery exec
                //> var pattern = /[a-d]/
                //undefined
                //> pattern.test('ll')
                //false
                //> pattern.test('and')
                //true
                //> var pattern = /[a-z.]+/
                //undefined
                //> pattern.test('1')
                //false
                //> pattern.test('a.jsl.')
                //true
                //> pattern.test('a.jsl.11')
                //true
                //> var pattern = /[\w.]+/
                //undefined
                //> pattern.test('111')
                //true
                //> pattern.test('abac.sjsl')
                //true
                //> pattern.test('abac.sjsKSKS')
                //true
        
                //py3 exec
                //>>> p
                //re.compile('[a-d]a')
                //>>> p.match('aa')
                //<_sre.SRE_Match object; span=(0, 2), match='aa'>
                //>>> p.match('aab')
                //<_sre.SRE_Match object; span=(0, 2), match='aa'>
                //>>> p.match('ab')
                //>>> p = re.compile('[a-z.]+')
                //>>> p
                //re.compile('[a-z.]+')
                //>>> p.match('a')
                //<_sre.SRE_Match object; span=(0, 1), match='a'>
                //>>> p.match('a.')
                //<_sre.SRE_Match object; span=(0, 2), match='a.'>
                //>>> p.match('a.alal')
                //<_sre.SRE_Match object; span=(0, 6), match='a.alal'>
                //>>> p.match('111')
                //>>> p = re.compile('[\w.]+')
                //>>> p.match('11')
                //<_sre.SRE_Match object; span=(0, 2), match='11'>
                //>>> p.match('11aaa')
                //<_sre.SRE_Match object; span=(0, 5), match='11aaa'>
    ````
17. 字符串'[^xyz]', 目标字符串不包含匹配内容的任何一个字符
    ````
                RegularExpression expression = new RegularExpression("[^a-d]");
                System.out.println(expression.matches("abcd") + "==" + expression.matches("abcde"));
        
                //jquery exec
                //> var pattern = /[^a-d]/
                //undefined
                //> pattern
                ///[^a-d]/
                //> pattern.test('abcd')
                //false
                //> pattern.test('abcde')
                //true
        
                //py3
                //>>> print(re.search('[^a-d]','abcd'))
                //None
                //>>> print(re.search('[^a-d]','abcde'))
                //<_sre.SRE_Match object; span=(4, 5), match='e'>
    ````
18. 字符'\d' 匹配目标字符串为0-9之间的数字(等同'[0-9]')
    ````
            RegularExpression expression = new RegularExpression("\\d");
            System.out.println(expression.matches("1") + "==" + expression.matches("a"));
    
            //jquery exec
            //> var pattern = /\d/
            //undefined
            //> pattern
            ///\d/
            //> pattern.test('a')
            //false
            //> pattern.test('1')
            //true
    
            //py3 exec
            //>>> p = re.compile('\d')
            //>>> p
            //re.compile('\\d')
            //>>> p.match('a')
            //>>> print(p.match('a'))
            //None
            //>>> print(p.match('1'))
            //<_sre.SRE_Match object; span=(0, 1), match='1'>
    ````
19. 字符'\D' 匹配目标字符串为非0-9之间的数字(等同'[^0-9]')
    ````
                RegularExpression expression = new RegularExpression("\\D");
                System.out.println(expression.matches("1") + "==" + expression.matches("a"));
        
                //jquery exec
                //> var pattern = /\D/
                //undefined
                //> pattern
                ///\D/
                //> pattern.test('a')
                //true
                //> pattern.test('1')
                //false
        
                //py3 exec
                //>>> p = re.compile('\D')
                //>>> p
                //re.compile('\\D')
                //>>> p.match('A')
                //<_sre.SRE_Match object; span=(0, 1), match='A'>
                //>>> p.match('9')
                //>>> print(p.match('9'))
                //None
    ````
20. 字符'\b' 表示字符之间的看不见的东西...... \b 就是用在你匹配整个单词的时候。 如果不是整个单词就不匹配。 你想匹配 I 的话，你知道，很多单词里都有I的，但我只想匹配I，就是“我”，这个时候用 \bI\b
    ````
                RegularExpression expression = new RegularExpression("\\bI\\b");
                System.out.println(expression.matches("I am Fmbah") + "==" + expression.matches("I'm Fmbah") + "==" + expression.matches("I am"));
        
                //jquery exec
                //> var pattern = /\bI\b/
                //undefined
                //> pattern
                ///\bI\b/
                //> pattern.test('I am Fmbah')
                //true
                //> pattern.test('I'm Fmbah')
                //...
                //> pattern.test("I'm Fmbah")
                //true
                //> pattern.test("HereIam")
                //false
        
                //py3 exec
                //>>> import re
                //>>> re.compile('\bI\b')
                //re.compile('\x08I\x08')
                //>>> p = re.compile('\bI\b')
                //>>> p
                //re.compile('\x08I\x08')
                //>>> p.match('I')
                //>>> p.match('I am Fmbah')
                //>>> p = re.compile('\\bI\\b')
                //>>> p
                //re.compile('\\bI\\b')
                //>>> p.match('I am Fmbah')
                //<_sre.SRE_Match object; span=(0, 1), match='I'>
                //>>> p.match('I')
                //<_sre.SRE_Match object; span=(0, 1), match='I'>
                //>>> p.match("HereIam")
                //>>> print(p.match("HereIam"))
                //None
    ````
22. 字符'\n' 匹配回车
    ````
            RegularExpression expression = new RegularExpression("\\n");
            System.out.println(expression.matches("a\nb"));
    
            //jquery exec
            //> var pattern = /\n/
            //undefined
            //> pattern
            ///\n/
            //> pattern.test('a\nb')
            //true
            //> pattern.test('ab')
            //false
    
            //py3
            //>>> p = re.compile('\n')
            //>>> p.match('\n')
            //<_sre.SRE_Match object; span=(0, 1), match='\n'>
            //>>> p.match('a\nb')
            //>>> p.match('a\n')
            //>>> p.match('\n')
            //<_sre.SRE_Match object; span=(0, 1), match='\n'>
            //>>> re.search('\n','a\nb')
            //<_sre.SRE_Match object; span=(1, 2), match='\n'>
    ````
23. 字符'\s' 匹配space空格
    ````
                RegularExpression expression = new RegularExpression("\\s");
                System.out.println(expression.matches("a b") + "==" + expression.matches("ab"));
        
                //jquery exec
                //> var pattern = /\s/
                //undefined
                //> pattern
                ///\s/
                //> pattern.test('ab')
                //false
                //> pattern.test('a b')
                //true
        
                //py3 exec
                //>>> re.search('\s', 'ab')
                //>>> re.search('\s', 'a b')
                //<_sre.SRE_Match object; span=(1, 2), match=' '>
    ````
24. 字符'\S' 匹配空白以外的字符
    ````
                RegularExpression expression = new RegularExpression("\\S");
                System.out.println(expression.matches("foo bar") + "==" + expression.matches("123") + "==" + expression.matches(" "));//匹配除了空格之外的内容
                //jquery exec
                //> var pattern = /\S/
                //undefined
                //> pattern
                ///\S/
                //> pattern.test(' ')
                //false
                //> pattern.test('a b')
                //true
                //> pattern.test('ab')
                //true
        
                //py3 exec
                //>>> re.search('\S', ' ')
                //>>> re.search('\S', 'a b')
                //<_sre.SRE_Match object; span=(0, 1), match='a'>
                //>>> re.search('\S', 'ab')
                //<_sre.SRE_Match object; span=(0, 1), match='a'>
    ````
25. 字符'\t' 匹配一个tab
    ````
            RegularExpression expression = new RegularExpression("\\t");
            System.out.println(expression.matches("a\tb") + "==" + expression.matches("ab"));
    
            //jquery exec
            //> var pattern = /\t/
            //undefined
            //> pattern
            ///\t/
            //> pattern.test('a\tb')
            //true
            //> pattern.test('ab')
            //false
    
            //py3 exec
            //>>> re.search('\t', 'a\tb')
            //<_sre.SRE_Match object; span=(1, 2), match='\t'>
            //>>> re.search('\t','ab')
    ````
26. 字符'\w' 匹配任何字母数字下划线字符
    ````
                RegularExpression expression = new RegularExpression("\\w");
                System.out.println(expression.matches("_") + "==" + expression.matches("&") + "==" + expression.matches("a") + "==" + expression.matches("1"));
        
                //jquery exec
                //> var pattern = /\w/
                //undefined
                //> pattern
                ///\w/
                //> pattern.test('_')
                //true
                //> pattern.test('&')
                //false
                //> pattern.test('a')
                //true
                //> pattern.test('1')
                //tru
        
                //py3 exec
                //>>> re.search('\w','_')
                //<_sre.SRE_Match object; span=(0, 1), match='_'>
                //>>> re.search('\w','&')
                //>>> re.search('\w','a')
                //<_sre.SRE_Match object; span=(0, 1), match='a'>
                //>>> re.search('\w','1')
                //<_sre.SRE_Match object; span=(0, 1), match='1'>
    ````
27. 字符'\W' 与'\w'相反, 匹配任何非文字字符, 包括下划线
    ````
            RegularExpression expression = new RegularExpression("\\W");
            System.out.println(expression.matches("_") + "==" + expression.matches("&"));
    
            //jquery exec
            //> var pattern = /\W/
            //undefined
            //> pattern.test('_')
            //false
            //> pattern.test('&')
            //true
    
            //py3 exec
            //>>> re.search('\W', '_')
            //>>> re.search('\W', '&')
            //<_sre.SRE_Match object; span=(0, 1), match='&'>
    ````
### 参照(maybe Scientific net)
* [Regular Expressions](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions)
* [菜鸟工具](https://c.runoob.com/front-end/854)
* [python3官网](https://docs.python.org/3/howto/regex.html)
* [百度知道](https://zhidao.baidu.com/question/58688915.html)