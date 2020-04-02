package com.sy.acauto.service;

import com.sy.acauto.component.WordSet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ACAutoService {

    Map<String, WordSet> wordSetMap = new ConcurrentHashMap<>();

    public void buildWordSet(String name, List<String> words) {

        WordSet wordSet = wordSetMap.get(name);
        if (Objects.isNull(wordSet)) {
            wordSet = new WordSet();
            wordSetMap.put(name, wordSet);
        }

        for (String word : words) {
            wordSet.addTerm(word);
        }

        // 重建失配跳转索引
        wordSet.linkFail();

    }

    // 标记
    public String mark(String wordSetName, String targetSentence) throws Exception {
        WordSet wordSet = wordSetMap.get(wordSetName);

        if (Objects.isNull(wordSet)) {
            throw new Exception("词库不存在!");
        }
        return wordSet.mark(targetSentence);
    }


}
