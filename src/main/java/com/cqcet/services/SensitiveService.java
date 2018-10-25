package com.cqcet.services;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by �Ǹ�˭ on 2018/10/25.
 */
@Service
public class SensitiveService implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveService.class);

    /**
     * Ĭ�����д��滻��
     */
    private static final String DEFAULT_REPLACEMENT = "***";


    private class TrieNode {

        /**
         * true �ؼ��ʵ��ս� �� false ����
         */
        private boolean end = false;

        /**
         * key��һ���ַ���value�Ƕ�Ӧ�Ľڵ�
         */
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        /**
         * ��ָ��λ����ӽڵ���
         */
        void addSubNode(Character key, TrieNode node) {
            subNodes.put(key, node);
        }

        /**
         * ��ȡ�¸��ڵ�
         */
        TrieNode getSubNode(Character key) {
            return subNodes.get(key);
        }

        boolean isKeywordEnd() {
            return end;
        }

        void setKeywordEnd(boolean end) {
            this.end = end;
        }

        public int getSubNodeCount() {
            return subNodes.size();
        }


    }


    /**
     * ���ڵ�
     */
    private TrieNode rootNode = new TrieNode();


    /**
     * �ж��Ƿ���һ������
     */
    private boolean isSymbol(char c) {
        int ic = (int) c;
        // 0x2E80-0x9FFF �������ַ�Χ
        return !CharUtils.isAsciiAlphanumeric(c) && (ic < 0x2E80 || ic > 0x9FFF);
    }


    /**
     * �������д�
     */
    public String filter(String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }
        String replacement = DEFAULT_REPLACEMENT;
        StringBuilder result = new StringBuilder();

        TrieNode tempNode = rootNode;
        int begin = 0; // �ع���
        int position = 0; // ��ǰ�Ƚϵ�λ��

        while (position < text.length()) {
            char c = text.charAt(position);
            // �ո�ֱ������
            if (isSymbol(c)) {
                if (tempNode == rootNode) {
                    result.append(c);
                    ++begin;
                }
                ++position;
                continue;
            }

            tempNode = tempNode.getSubNode(c);

            // ��ǰλ�õ�ƥ�����
            if (tempNode == null) {
                // ��begin��ʼ���ַ������������д�
                result.append(text.charAt(begin));
                // ������һ���ַ���ʼ����
                position = begin + 1;
                begin = position;
                // �ص�����ʼ�ڵ�
                tempNode = rootNode;
            } else if (tempNode.isKeywordEnd()) {
                // �������дʣ� ��begin��position��λ����replacement�滻��
                result.append(replacement);
                position = position + 1;
                begin = position;
                tempNode = rootNode;
            } else {
                ++position;
            }
        }

        result.append(text.substring(begin));

        return result.toString();
    }

    private void addWord(String lineTxt) {
        TrieNode tempNode = rootNode;
        // ѭ��ÿ���ֽ�
        for (int i = 0; i < lineTxt.length(); ++i) {
            Character c = lineTxt.charAt(i);
            // ���˿ո�
            if (isSymbol(c)) {
                continue;
            }
            TrieNode node = tempNode.getSubNode(c);

            if (node == null) { // û��ʼ��
                node = new TrieNode();
                tempNode.addSubNode(c, node);
            }

            tempNode = node;

            if (i == lineTxt.length() - 1) {
                // �ؼ��ʽ����� ���ý�����־
                tempNode.setKeywordEnd(true);
            }
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        rootNode = new TrieNode();

        try {
            InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("SensitiveWords.txt");
            InputStreamReader read = new InputStreamReader(is,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                lineTxt = lineTxt.trim();
                addWord(lineTxt);
            }
            read.close();
        } catch (Exception e) {
            logger.error("��ȡ���д��ļ�ʧ��" + e.getMessage());
        }
    }

    public static void main(String[] argv) {
        SensitiveService s = new SensitiveService();
        s.addWord("ɫ��");
        s.addWord("��ɫ");
        System.out.print(s.filter("���Xɫ**��XX"));
    }
}
