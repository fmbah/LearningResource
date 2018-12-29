package web.regular_expressions_syntax;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import org.junit.Test;
import sun.misc.Regexp;

/**
 * @ClassName TestRegularExpression
 * @Description 未使用java util 提供的正则表达式
 * @Author root
 * @Date 18-12-27 下午3:40
 * @Version 1.0
 **/
public class TestRegularExpression {

    @Test
    public void testBackslash() {
        RegularExpression expression = new RegularExpression("ab");//匹配ab字符
        System.out.println(expression.matches("ab"));

        RegularExpression expression1 = new RegularExpression("a\\b");//匹配a字符, 表达式需要转义
        System.out.println(expression1.matches("a") + "===" + expression1.matches("ab"));

    }

    @Test
    public void testShift6() {
        RegularExpression expression = new RegularExpression("^A");//匹配开头元素为A
        System.out.println(expression.matches("Abac"));

        String pattern = "^A";
        System.out.println("Abac".matches(pattern));//实现机制不同, 匹配结果不同

        //jquery执行, node执行
        //var pattern = /^A/
        //pattern.test('Abac')

        //py3执行
        //import re
        //p = re.compile('^A')
        //p.match('Abac')
    }

    @Test
    public void testShift4() {
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
    }

    @Test
    public void testShift8() {
        RegularExpression expression = new RegularExpression("bo*");
        System.out.println(expression.matches("A ghost booooed") + "===" + expression.matches("A bird warbled") + "===" + expression.matches("A goat grunted"));

        String pattern = "bo*";
        System.out.println("A ghost booooed".matches(pattern));//结果依然不同

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

    }
    @Test
    public void testShift11() {
        RegularExpression expression = new RegularExpression("bo+");
        System.out.println(expression.matches("A ghost booooed") + "===" + expression.matches("A bird warbled") + "===" + expression.matches("A goat grunted"));

        String pattern = "bo+";
        System.out.println("A ghost booooed".matches(pattern));//结果依然不同

        //jquery执行, node执行
        //var pattern = /bo+/
        //pattern.test('A ghost booooed')
        //pattern.test('A bird warbled')
        //pattern.test('A goat grunted')

        //py3执行
        //import re
        //p = re.compile('bo+')
        //p.match('A ghost booooed')
        //p.match('A bird warbled')
        //p.match('A goat grunted')

    }

    @Test
    public void testShift12() {
        RegularExpression expression = new RegularExpression("e?le?");
        System.out.println(expression.matches("angel") + "==" + expression.matches("angle") + "===" + expression.matches("oslo"));
    }

    @Test
    public void testShift13() {
        RegularExpression expression = new RegularExpression(".n");
        System.out.println(expression.matches("nay") + "==" + expression.matches("an") + "===" + expression.matches("on"));
    }

    @Test
    public void testShift14n() {
        RegularExpression expression = new RegularExpression("apple(,)\\sorange\\1");
        System.out.println(expression.matches("apple, orange, cherry, peach."));
    }

    @Test
    public void testType0() {
        RegularExpression expression = new RegularExpression("foo{1,2}");
        System.out.println(expression.matches("123foofoofoof"));
        RegularExpression expression1 = new RegularExpression("(?:foo){1,2}");
        System.out.println(expression1.matches("foo"));
    }

    @Test
    public void testType1() {
        RegularExpression expression = new RegularExpression("Jack(?=Sprat)");
        System.out.println(expression.matches("JackSprat111"));
        RegularExpression expression1 = new RegularExpression("Jack(?=Sprat|Frost)");
        System.out.println(expression1.matches("Jack1111") + "==" + expression1.matches("JackFrost1111") + "==" + expression1.matches("JackSprat111"));
    }

    @Test
    public void testType2() {
        RegularExpression expression = new RegularExpression("\\d+(?!\\.)");
        System.out.println(expression.matches("3.141"));

        //jquery exec
        //var pattern = /\d+(?!\.)/
        //pattern.test('3.141')
        //py3 exec
        //import re
        //re.search('\\d+(?!\\.)', '3.141')
        //<_sre.SRE_Match object; span=(2, 5), match='141'>
    }

    @Test
    public void testType3() {
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
    }

    @Test
    public void testType4() {
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
    }


    @Test
    public void testType4_1() {
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
    }

    @Test
    public void testType4_2() {
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
    }

    @Test
    public void testType5() {
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
    }

    @Test
    public void testType6() {
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
    }

    @Test
    public void testType7() {
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
    }

    @Test
    public void testType7_1() {
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
    }

    @Test
    public void testType8() {
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

    }

    @Test
    public void testType9() {
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
    }

    @Test
    public void testType10() {
        RegularExpression expression = new RegularExpression("\\s");
        System.out.println(expression.matches("a b") + "==" + expression.matches("ab"));//匹配成功的是空格

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
    }

    @Test
    public void testType11() {
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
    }

    @Test
    public void testType12() {
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
    }

    @Test
    public void testType13() {
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
    }

    @Test
    public void testType14() {
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
    }

    @Test
    public void demo() {
        RegularExpression expression = new RegularExpression("^[^0]\\d*$");//匹配正整数
        System.out.println(expression.matches("01") + "==" + expression.matches("ab") + "==" + expression.matches("10sdfa") + "==" + expression.matches("109"));



    }
}
