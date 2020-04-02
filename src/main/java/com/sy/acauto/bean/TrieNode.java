package com.sy.acauto.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class TrieNode {

    public TrieNode(char word, TrieNode parentNode) {
        this.word = word;
        this.parentNode = parentNode;
    }

    public TrieNode(char word, TrieNode parentNode, int nodeVal) {
        this.word = word;
        this.parentNode = parentNode;
        this.nodeVal = nodeVal;
    }

    // 字
    private char word;

    private int nodeVal;

    private String term;

    // 子节点
    private volatile Map<Character, TrieNode> subNodes;

    private TrieNode parentNode;

    // 失配跳转
    private volatile TrieNode failNode;

    public void addSubNode(char word, TrieNode node) {
        if (Objects.isNull(subNodes)) {
            synchronized (this) {
                if (Objects.isNull(subNodes)) {
                    subNodes = new HashMap<>();
                }
            }
        }
        subNodes.put(word, node);
    }

    public boolean subExist(char word) {
        if (Objects.isNull(subNodes)) {
            return false;
        }
        return !Objects.isNull(subNodes.get(word));
    }

    public TrieNode getSubNode(char word) {
        if (!Objects.isNull(subNodes)) {
            return subNodes.get(word);
        }
        return null;
    }

    // 获取父亲的失败跳转节点
    public TrieNode getFatherFail() {
        if (!Objects.isNull(getParentNode())) {
            return getParentNode().getFailNode();
        }
        return null;
    }

    public String toString() {
        return "[" + word + " " + nodeVal + " "+ getFailNode() + "]";
    }

}
