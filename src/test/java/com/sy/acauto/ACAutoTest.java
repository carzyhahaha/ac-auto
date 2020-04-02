package com.sy.acauto;

import com.sy.acauto.component.WordSet;
import com.sy.acauto.service.ACAutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ACAutoTest {

    @Test
    public void test() {

        WordSet wordSet = new WordSet();
        wordSet.addTerm("指针");
        wordSet.addTerm("指向");
        wordSet.addTerm("是不是");
        wordSet.addTerm("前缀");
        wordSet.addTerm("相同");


        wordSet.linkFail();
//        wordSet.showYourSelf();

        System.out.println(wordSet.query("ac自动机,就是在tire树的基础上,增加一个fail指针,如果当前点匹配失败,则将指针转" +
                        "移到fail指针指向的地方,这样就不用回溯,而可以路匹配下去了.(当前模式串后缀和fail指针指向的模式串部分前缀相同," +
                        "如abce和bcd,我们找到c发现下一个要找的不是e,就跳到bcd中的c处,看看此处的下一个字符(d)是不是应该找的那一个)"));


        System.out.println(wordSet.mark("ac自动机,就是在tire树的基础上,增加一个fail指针,如果当前点匹配失败,则将指针转" +
                "移到fail指针指向的地方,这样就不用回溯,而可以路匹配下去了.(当前模式串后缀和fail指针指向的模式串部分前缀相同," +
                "如abce和bcd,我们找到c发现下一个要找的不是e,就跳到bcd中的c处,看看此处的下一个字符(d)是不是应该找的那一个)"));
    }

    @Autowired
    ACAutoService acAutoService;

    @Test
    public void test2() throws Exception {
        List<String> words = new ArrayList<>();


        acAutoService.buildWordSet("", words);
        System.out.println();


    }


}
