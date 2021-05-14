package cn.sunyog.ran;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/6 2:51 下午
 * @Desc:
 */
public class AllMember {
    public static void main(String[] args) {
        FileReader reader = new FileReader("file/yaanli.json");
        FileWriter writer = new FileWriter("file/yaanli.csv");
        writer.append("姓名,身份证号,年龄,类别,性别,手机号,地址\n");
        String str = reader.readString();
        JSONObject jsonObj = JSONUtil.parseObj(str);
        Object data = jsonObj.get("data");
        JSONArray arr = JSONUtil.parseArray(data);
        for (Object item : arr) {
            JSONObject itemObj = JSONUtil.parseObj(item);
            //姓名，身份证号
            Object name = itemObj.get("name");
            Object cardno = itemObj.get("cardno");
            Object age = itemObj.get("age");
            Object classification = itemObj.get("classification");
            Object sex = itemObj.get("sex");
            Object mobile = itemObj.get("mobile");
            Object addr = itemObj.get("addr");
            StringBuffer sb=new StringBuffer("").append(name).append(",").append(cardno).append(",")
                    .append(age).append(",").append(classification).append(",").append(sex).append(",")
                    .append(mobile).append(",").append(addr).append("\n");
            writer.append(sb.toString());
        }
    }
}
