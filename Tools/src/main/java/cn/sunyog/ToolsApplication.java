package cn.sunyog;

import cn.sunyog.echart.BarChartTool;
import cn.sunyog.poi.WordTool;

/**
 * @Author: MysteriousGT
 * @Date: 2021/3/29 10:23 上午
 * @Desc: 工具启动类
 */
public class ToolsApplication {
    public static void main(String[] args) {
        BarChartTool.pringChart(BarChartTool.getBarChart());
//        WordTool.getDocx();
//        WordTool.getDoc();
//        WordTool.writeDocx();
//        WordTool.templatePree();
    }
}
