package com.sy.acauto.component;

import com.sy.acauto.bean.TrieNode;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class WordSet {

    private int nodeVal = 0;

    private boolean isLinkFail = false;

    TrieNode root = new TrieNode((char) 0, null, nodeVal ++);

    public void addTerm(String term) {
        TrieNode now = root;
        for (int i = 0; i < term.length(); i++) {
            char word = term.charAt(i);
            // 如果没有该子节点, 则插入
            if (!now.subExist(word)) {
                TrieNode thisNode = new TrieNode(word, root, nodeVal ++);
                now.addSubNode(word, thisNode);
                if (i == term.length() - 1) {
                    thisNode.setTerm(term);
                }
                thisNode.setParentNode(now);
                now = thisNode;
            } else {
                // 如果该子节点存在, 则继续往下处理
                now = now.getSubNode(word);
            }
        }

        // 标记为需要重建失配跳转指针
        isLinkFail = false;
    }

    public void linkFail() {
        if (Objects.isNull(root.getSubNodes())) {
            return;
        }
        LinkedList<TrieNode> queue = new LinkedList<>(root.getSubNodes().values());

        while (!queue.isEmpty()) {
            TrieNode thisNode = queue.poll();
            TrieNode fatherFail = thisNode.getFatherFail();
            if (!Objects.isNull(fatherFail)) {
                TrieNode fatherFailSub = fatherFail.getSubNode(thisNode.getWord());
                if (Objects.isNull(fatherFailSub)) {
                    thisNode.setFailNode(root);
                } else {
                    thisNode.setFailNode(fatherFailSub);
                }
            } else {
                thisNode.setFailNode(root);
            }
            // 加入子节点
            if (!Objects.isNull(thisNode.getSubNodes())) {
                queue.addAll(thisNode.getSubNodes().values());
            }
        }
        isLinkFail = true;
    }

    public List<String> query(String sentence) {
        if (!isLinkFail) {
            linkFail();
        }
        List<String> rs = new ArrayList<>();

        TrieNode matchNode = root;
        for (int i = 0; i < sentence.length(); ) {
            if (!StringUtils.isEmpty(matchNode.getTerm())) {
                rs.add(matchNode.getTerm());
            }
            char word = sentence.charAt(i);
            TrieNode SubMatchNode = matchNode.getSubNode(word);
            if (Objects.isNull(SubMatchNode)) {
                if (Objects.equals(matchNode, root)) {
                    i ++;
                } else {
                    matchNode = matchNode.getFailNode();
                }
            } else {
                matchNode = SubMatchNode;
                i++;
            }
        }
        return rs;
    }

    public String mark(String sentence) {
        if (!isLinkFail) {
            linkFail();
        }
        StringBuilder rs = new StringBuilder();

        TrieNode matchNode = root;
        for (int i = 0; i < sentence.length(); ) {
            if (!StringUtils.isEmpty(matchNode.getTerm())) {
                String term = matchNode.getTerm();
                rs.delete(rs.length() - term.length(), rs.length());
                rs.append("  [");
                rs.append(term);
                rs.append("]  ");
            }
            char word = sentence.charAt(i);
            TrieNode SubMatchNode = matchNode.getSubNode(word);
            if (Objects.isNull(SubMatchNode)) {
                if (Objects.equals(matchNode, root)) {
                    rs.append(word);
                    i ++;
                } else {
                    matchNode = matchNode.getFailNode();
                }
            } else {
                matchNode = SubMatchNode;
                rs.append(word);
                i++;
            }
        }
        return rs.toString();
    }


    // dfs遍历树
    public void dfsTree(TrieNode now) {
        System.out.println(now.toString());
        if (!Objects.isNull(now.getSubNodes())) {
            for (TrieNode node : now.getSubNodes().values()) {
                dfsTree(node);
            }
        }
    }

    // 打印这棵树
    public void showYourSelf() {
        dfsTree(root);
    }


    @PostConstruct
    public void init() {
        // 初始化
    }

    @PreDestroy
    public void destory() {
        // 持久化到磁盘?
    }

}
