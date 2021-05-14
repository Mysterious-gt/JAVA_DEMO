package cn.sunyog.baidu;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/13 2:35 下午
 * @Desc: 百度网盘测试程序
 */
public class DuPanTest {
    @Test
    public void testPing(){
        String url="http://openapi.baidu.com/oauth/2.0/authorize";
        Map<String,Object> param=new HashMap<>();
        param.put("response_type","code");
        param.put("client_id","VeTSPS7WYRaYYShYHLOfuopET0sHs2X4");//appKey
        param.put("redirect_uri","oob");
        param.put("scope","basic,netdisk");
        param.put("display","page");//默认，页面授权

        String res = HttpUtil.get(url, param);
        System.out.println(res);
/**
        http://openapi.baidu.com/oauth/2.0/authorize?response_type=code&client_id=VeTSPS7WYRaYYShYHLOfuopET0sHs2X4&redirect_uri=oob&scope=basic,netdisk&display=page

        https://openapi.baidu.com/oauth/2.0/token?grant_type=authorization_code&code=6114d3b84d6eb8f2c5bea4f102d798a4&client_id=VeTSPS7WYRaYYShYHLOfuopET0sHs2X4&client_secret=3Dr5L5tuXlKYdGVqgD8anlD5GiHIsGCY&redirect_uri=oob

        https://openapi.baidu.com/oauth/2.0/token?grant_type=refresh_token&refresh_token=122.fd9ade213474bdd9f4258ad467a759e8.YBWxHHWh2XVEI_o-6Yig3R_5-qhMwoKEKcap3rA.qe-pcw&client_id=VeTSPS7WYRaYYShYHLOfuopET0sHs2X4&client_secret=3Dr5L5tuXlKYdGVqgD8anlD5GiHIsGCY
 */
    }

    @Test
    public void testFileList(){
//        String url="https://pan.baidu.com/rest/2.0/xpan/nas?method=uinfo";
//        String url="https://pan.baidu.com/api/quota";
        String url="https://pan.baidu.com/rest/2.0/xpan/file?method=list";
        Map<String,Object> param=new HashMap<>();
        param.put("access_token","121.9d96de6dc839f1cba75a5648154a4ada.Y_5QQ6ordiwSYgiOKBoSzX3elCJFxM9h2lc5Ar8.m3CrmA");
        param.put("dir","/Document/book/new");

        String str = HttpUtil.get(url, param);
        JSONObject jsonObj = JSONUtil.parseObj(str);
        Object list = jsonObj.get("list");
        JSONArray jsonList = JSONUtil.parseArray(list);
        for (Object item : jsonList) {
            System.out.println(item);
        }
    }

    @Test
    public void getFileDetail(){
        String url="https://pan.baidu.com/rest/2.0/xpan/multimedia?method=filemetas&dlink=1&fsids=[574739812086119]";
        Map<String,Object> map=new HashMap<>();
        map.put("access_token","121.9d96de6dc839f1cba75a5648154a4ada.Y_5QQ6ordiwSYgiOKBoSzX3elCJFxM9h2lc5Ar8.m3CrmA");
        String result = HttpUtil.get(url, map);
        JSONObject jsonObj = JSONUtil.parseObj(result);
        Object listStr = jsonObj.get("list");
        JSONArray list = JSONUtil.parseArray(listStr);
        for (Object item : list) {
            JSONObject json = JSONUtil.parseObj(item);
            for (Map.Entry<String, Object> entry : json.entrySet()) {
                System.out.println(entry.getKey()+"\t"+entry.getValue());
            }
        }

    }

    @Test
    public void testDownload() throws IOException {
        Map<String,Object> param=new HashMap<>();
        param.put("access_token","121.9d96de6dc839f1cba75a5648154a4ada.Y_5QQ6ordiwSYgiOKBoSzX3elCJFxM9h2lc5Ar8.m3CrmA");
        String dlink="https://d.pcs.baidu.com/file/e539c5027td994c0a60239eecaf1dab6?fid=4173969690-250528" +
                "-574739812086119&rt=pr&sign=FDtAERV-DCb740ccc5511e5e8fedcff06b081203-U%2Buv5RY9gAMOT66dVtvcBv6tj4w" +
                "%3D&expires=8h&chkbd=0&chkv=2&dp-logid=1588378371428488363&dp-callid=0&dstime=1620893590&r=334346028" +
                "&origin_appid=24160948&file_type=0";

        HttpResponse response = HttpRequest.get(dlink).header("User-Agent", "pan.baidu.com").form(param).execute();
        System.out.println(response.body());
    }
}
