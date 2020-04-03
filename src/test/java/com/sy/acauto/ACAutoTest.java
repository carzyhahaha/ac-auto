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
        wordSet.addTerm("我的后缀abcde");
        wordSet.addTerm("后缀abcde是我的前缀");
        wordSet.addTerm("理论上时间复杂度");
        wordSet.addTerm("最坏情况判断 n(目标串长度) + m(失配次数, 因为只有适配时候才会重复处理当前字符)");
        wordSet.addTerm("目标串长度");
        wordSet.addTerm("最长模式串)");
        wordSet.addTerm("时间复杂度为O(n)");

        wordSet.linkFail();
//        wordSet.showYourSelf();

        System.out.println(wordSet.query("我的后缀abcde是我的前缀,理论上时间复杂度是O(n+m)n为目标串长度, m为最长模式串所以时间复杂度为O(n)"));



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
