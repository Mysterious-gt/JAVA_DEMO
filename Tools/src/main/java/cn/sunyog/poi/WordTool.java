package cn.sunyog.poi;

import java.io.*;
import java.util.*;

import cn.sunyog.entity.Person;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import cn.sunyog.ToolsApplication;
import cn.sunyog.echart.BarChartTool;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @Author: MysteriousGT
 * @Date: 2021/3/29 10:50 上午
 * @Desc:
 */
public class WordTool {
    public static void getDocx() {
        try {
            String path = "/Users/jerrylee/Desktop/poi-word.docx";
            FileInputStream in = new FileInputStream(new File(path));
            XWPFDocument document = new XWPFDocument(in);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            System.out.println(extractor.getText());
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeDocx(){
        try {
            String path = "/Users/jerrylee/Desktop/poi-word.docx";
            File file = new File(path);
            FileInputStream in = new FileInputStream(file);
            XWPFDocument document = new XWPFDocument(in);
            //新增段落
            XWPFParagraph para = document.createParagraph();
            XWPFRun run = para.createRun();
            run.setText("我是新增的段落");
            run.setBold(true);
            //新增表格
            XWPFTable table = document.createTable(3, 3);
            List<XWPFTableRow> rows = table.getRows();
            for (XWPFTableRow row : rows) {
                List<XWPFTableCell> cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    cell.setText("测试表格输入");
                }
            }

            FileOutputStream out = new FileOutputStream(file);
            document.write(out);
            //打印结果
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            System.out.println(extractor.getText());
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void templatePree(){
        try {
            Map<String,Object> map=new HashMap<>(8);
            JFreeChart barChart = BarChartTool.getBarChart();
            ByteArrayOutputStream outArr = new ByteArrayOutputStream();
            ChartUtils.writeChartAsJPEG(outArr, barChart, 600, 400);
            byte[] bytes = outArr.toByteArray();
            String base64 = Base64.getEncoder().encodeToString(bytes);
            map.put("picture",base64);
            map.put("para","模板段落修改");
            map.put("personList",getPersonList());

            Configuration config = new Configuration();
            config.setDefaultEncoding("utf-8");
            config.setClassForTemplateLoading(ToolsApplication.class,"/file");
            Template template = config.getTemplate("word-template.ftl");
            FileOutputStream out = new FileOutputStream(new File("/Users/jerrylee/Desktop/word-template.docx"));
            template.process(map,new OutputStreamWriter(out));
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static List<Person> getPersonList() {
        LinkedList<Person> res=new LinkedList<>();
        res.push(new Person("张三",12,"男"));
        res.push(new Person("李四",22,"女"));
        res.push(new Person("老王",33,"男"));
        return res;
    }

    public static void getDoc() {
        try {
            String path = "/Users/jerrylee/Desktop/poi-word.doc";
            FileInputStream in = new FileInputStream(path);
            WordExtractor extractor = new WordExtractor(in);
            String text = extractor.getText();
            System.out.println(text);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
