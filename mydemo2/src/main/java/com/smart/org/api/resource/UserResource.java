package com.smart.org.api.resource;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.smart.org.model.User;
import com.smart.org.service.UserService;
import com.smart.org.util.HttpRequest;
import com.smart.org.util.JudgeRequest;
import org.apache.commons.httpclient.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dys on 2017/1/5.
 */
@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    private UserService userService;
//    HttpClient client = new HttpClient();
    //申请的apikey,secretkey
    public static String apikey="ZGlnaXRhbGNoaW5hMTAwMA==";
    public static String secretkey= "5923732ac18bc9a3de2c9c71e3c521f3c18bc9a3de2c9c71";

    public static final String ENCODE="utf-8";

    private Gson gson;

    /*
     * 行政处罚详情,调用的是两法平台的数据库取数据
     */
    //  @RequestMapping(value="/getAllUsers",method= RequestMethod.GET)
    public List getUsers(String caseName) {
        List<User> list = userService.getUserInfo(caseName);
        List lists = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            Long lid= list.get(i).getId();
            caseName = list.get(i).getCaseName();
            Date faDate = list.get(i).getFaDate();
//            String pName = list.get(i).getpName();
            Long wfMoney = list.get(i).getWfMoney();
//            String deptName = list.get(i).getDeptName();
//            String wfqkzy = list.get(i).getWfqkzy();
//            Long caseState = list.get(i).getCaseState();
//            String xzajh = list.get(i).getXzajh();
//            Long illegalIncome = list.get(i).getIllegalIncome();
//            Date ccDate = list.get(i).getCcDate();
//            Date cfDate = list.get(i).getCfDate();
//            Long hyType = list.get(i).getHyType();
//            Long isJmwlCase = list.get(i).getIsJmwlCase();
//            Long sysId = list.get(i).getSysId();
//            String cfTypes = list.get(i).getCfTypes();
//            String cfyj = list.get(i).getCfyj();
//            String cfjds = list.get(i).getCfjds();
//            int indexOf = caseName.indexOf("公司");//截断到公司处，分割成两个字段
//            String compName = caseName.substring(0, indexOf + 2);//公司名称
//            caseName = caseName.substring(indexOf + 2);//案件名称
            map.put("id", lid);
//            map.put("compName", compName);
            map.put("caseName", caseName);
//            map.put("wfqkzy", wfqkzy);
            map.put("faDate", faDate);
//            map.put("pName", pName);
            map.put("wfMoney", wfMoney);
//            map.put("deptName", deptName);
//            map.put("caseState", caseState);
//            map.put("xzajh", xzajh);
//            map.put("illegalIncome", illegalIncome);
//            map.put("ccDate", ccDate);
//            map.put("cfDate", cfDate);
//            map.put("hyType", hyType);
//            map.put("cfTypes", cfTypes);
//            if(isJmwlCase==1){
//                map.put("isJmwlCase","是");
//            }else{
//                map.put("isJmwlCase","否");
//            }
//            if(sysId==0){
//                map.put("sysId","两法平台");
//            }else{
//                map.put("sysId","工商局系统");
//            }
//            map.put("cfyj", cfyj);
//            map.put("cfjds", cfjds);
            lists.add(map);
        }
        return lists;
    }

    public Map getczcfinfo(Long id){
        User user= userService.getczcfinfo(id);
        Map<String, Object> map = new HashMap<String, Object>();
        String caseName = user.getCaseName();
        Date faDate = user.getFaDate();
        String pName = user.getpName();
        Long wfMoney = user.getWfMoney();
        String deptName = user.getDeptName();
        String wfqkzy = user.getWfqkzy();
        Long caseState = user.getCaseState();
        String xzajh = user.getXzajh();
        Long illegalIncome = user.getIllegalIncome();
        Date ccDate = user.getCcDate();
        Date cfDate = user.getCfDate();
        Long hyType = user.getHyType();
        Long isJmwlCase = user.getIsJmwlCase();
        Long sysId = user.getSysId();
        String cfTypes = user.getCfTypes();
        String cfyj = user.getCfyj();
        String cfjds = user.getCfjds();
        int indexOf = caseName.indexOf("公司");//截断到公司处，分割成两个字段
        String compName = caseName.substring(0, indexOf + 2);//公司名称
        caseName = caseName.substring(indexOf + 2);//案件名称
        map.put("compName", compName);
        map.put("caseName", caseName);
        map.put("wfqkzy", wfqkzy);
        map.put("faDate", faDate);
        map.put("pName", pName);
        map.put("wfMoney", wfMoney);
        map.put("deptName", deptName);
        map.put("caseState", caseState);
        map.put("xzajh", xzajh);
        map.put("illegalIncome", illegalIncome);
        map.put("ccDate", ccDate);
        map.put("cfDate", cfDate);
        map.put("hyType", hyType);
        map.put("cfTypes", cfTypes);
        if(isJmwlCase==1){
            map.put("isJmwlCase","是");
        }else{
            map.put("isJmwlCase","否");
        }
        if(sysId==0){
            map.put("sysId","两法平台");
        }else{
            map.put("sysId","工商局系统");
        }
        map.put("cfyj", cfyj);
        map.put("cfjds", cfjds);
        return map;
    }

    /*
      * 公司查询
      */
    public List company(String keyword) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/search";
        String urlParams = "keyword=" + keyword;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
//        System.out.println("1111111111..." + mapdata);
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> paraMap = new HashMap<String, Object>();
            Map resultMap = (Map) list.get(i);
            paraMap.put("id", resultMap.get("_id"));
            paraMap.put("mc", resultMap.get("名称"));
            paraMap.put("cym", resultMap.get("曾用名"));
            paraMap.put("hzrq", resultMap.get("核准日期"));
            paraMap.put("zch", resultMap.get("注册号"));
            paraMap.put("djjg", resultMap.get("登记机关"));
            paraMap.put("djzt", resultMap.get("登记状态"));
            paraMap.put("tishxxdm", resultMap.get("统一社会信用代码"));
            lists.add(paraMap);
        }
         }
        else{
            lists = null;
        }

        return lists;
//        System.out.println("1111111111..." + lists);

    }

    /*
     * 公司查询分页
     */
    public List companys(String keyword, String from) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/search";
        String urlParams = "keyword=" + keyword + "&from=" + from + "&size=" + 10;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> paraMap = new HashMap<String, Object>();
            Map resultMap = (Map) list.get(i);
            paraMap.put("id", resultMap.get("_id"));
            paraMap.put("mc", resultMap.get("名称"));
            paraMap.put("cym", resultMap.get("曾用名"));
            paraMap.put("hzrq", resultMap.get("核准日期"));
            paraMap.put("zch", resultMap.get("注册号"));
            paraMap.put("djjg", resultMap.get("登记机关"));
            paraMap.put("djzt", resultMap.get("登记状态"));
            paraMap.put("tishxxdm", resultMap.get("统一社会信用代码"));
            lists.add(paraMap);
        }
        }
        else{
            lists = null;
        }

        return lists;
//        System.out.println("1111111111..." + lists);

    }

    /*
     * 公司详情
     */
    public List companyInfo(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/companyinfo";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);

        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            Map<String, Object> paraMap = new HashMap<String, Object>();
            Map mapdata = (Map) JSON.get("data");

            paraMap.put("zs", mapdata.get("住所"));
            paraMap.put("mc", mapdata.get("名称"));
            paraMap.put("clrq", mapdata.get("成立日期"));
            paraMap.put("cym", mapdata.get("曾用名"));
            paraMap.put("hzrq", mapdata.get("核准日期"));
            paraMap.put("fddbr", mapdata.get("法定代表人"));
            paraMap.put("zch", mapdata.get("注册号"));
            paraMap.put("zczb", mapdata.get("注册资本"));
            paraMap.put("djjg", mapdata.get("登记机关"));
            paraMap.put("djzt", mapdata.get("登记状态"));
            paraMap.put("ss", mapdata.get("省市"));
            paraMap.put("lx", mapdata.get("类型"));
            paraMap.put("jyzt", mapdata.get("经营状态"));
            paraMap.put("jyfw", mapdata.get("经营范围"));
            paraMap.put("yyqxz", mapdata.get("营业期限自"));
            paraMap.put("yyqxzs", mapdata.get("营业期限至"));
            paraMap.put("dxrq", mapdata.get("注吊销日期"));
            paraMap.put("tishxxdm", mapdata.get("统一社会信用代码"));
            lists.add(paraMap);
        } else {
            lists = null;
        }
        return lists;

    }

    /*
 * 公司主要成员
 */
    public List member(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/member";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        System.out.println("member..." + mapdata);
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("xm", resultMap.get("姓名"));
                paraMap.put("xh", resultMap.get("序号"));
                paraMap.put("zw", resultMap.get("职务"));
                lists.add(paraMap);
            }
        }
        else{
            lists = null;
        }
//        System.out.println("member..." + lists);
        return lists;
//        System.out.println("1111111111..." + lists);

    }

    /*
       * 公司股东信息
       */
    public List investorInfo(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/investorInfo";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);

        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            Map mapdata = (Map) JSON.get("data");
            List list = (List) mapdata.get("hits");
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("czxq", resultMap.get("出资详情"));
                paraMap.put("cgbl", resultMap.get("证照/证件号码"));
                paraMap.put("gd", resultMap.get("股东"));
                paraMap.put("gdlx", resultMap.get("股东类型"));
                paraMap.put("gdmc", resultMap.get("股东名称"));
                paraMap.put("gdmc", resultMap.get("股东名称"));
                paraMap.put("rjczbz", resultMap.get("认缴出资币种"));
                lists.add(paraMap);
            }
        } else {
            lists = null;
        }
        return lists;
//        System.out.println("1111111111..." + lists);

    }

    /*
         * 对外投资
         */
    public List investment(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/investment";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map<String, Object> paraMap = new HashMap<String, Object>();
        Map mapdata = (Map) JSON.get("data");
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map resultMap = (Map) list.get(i);
                paraMap.put("mc", resultMap.get("名称"));
                paraMap.put("cym", resultMap.get("曾用名"));
                paraMap.put("hzrq", resultMap.get("核准日期"));
                paraMap.put("zch", resultMap.get("注册号"));
                paraMap.put("djzt", resultMap.get("登记状态"));
                paraMap.put("tyshxydm", resultMap.get("统一社会信用代码"));
                lists.add(paraMap);
            }
        }
        else {
            lists = null;
        }
        return lists;
//        System.out.println("1111111111..." + lists);

    }

    /*
         * 年报
         */
    public List yearReport(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/yearReport";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
//        DVO dvo=gson.fromJson(json,new TypeToken<DVO>() {}.getType());
//        List<GuDongInfo> guDongInfos=dvo.getData().getGuDongInfos();

        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        //       System.out.println("yearReport..." + mapdata);
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            if (list.size() > 0) {
                for (int i = 0; i < 1; i++) {
                    Map<String, Object> paraMap = new HashMap<String, Object>();
                    Map resultMap = (Map) list.get(0);
                    paraMap.put("id", resultMap.get("_id"));
                    String fbsj = (String) resultMap.get("发布日期");
                    int indexOf = fbsj.indexOf("T");
                    String fbsjs = fbsj.substring(0, indexOf);//公司名称
                    paraMap.put("fbsj", fbsjs);
                    paraMap.put("mc", resultMap.get("修改记录"));
                    paraMap.put("fddbrxm", resultMap.get("分支机构登记信息"));
                    Map jbxx = (Map) resultMap.get("基本信息");
                    if (jbxx != null || jbxx.size() > 0) {
                        jbxx.put("qyjyzt", jbxx.get("企业经营状态"));
                        jbxx.put("shywz", jbxx.get("是否有网站或网店"));
                        jbxx.put("sfzr", jbxx.get("有限责任公司本年度是否发生股东股权转让"));
                        jbxx.put("qymc", jbxx.get("企业名称"));
                        jbxx.put("lxdh", jbxx.get("企业联系电话"));
                        jbxx.put("zch", jbxx.get("注册号/统一社会信用代码"));
                        jbxx.put("yzbm", jbxx.get("邮政编码"));
                        jbxx.put("qytxdz", jbxx.get("企业通信地址"));
                        jbxx.put("dzyx", jbxx.get("电子邮箱"));
                        paraMap.put("jbxx", jbxx);
                    } else {
                        jbxx.put("qyjyzt", null);
                        jbxx.put("shywz", null);
                        jbxx.put("sfzr", null);
                        jbxx.put("qymc", null);
                        jbxx.put("lxdh", null);
                        jbxx.put("zch", null);
                        jbxx.put("yzbm", null);
                        jbxx.put("qytxdz", null);
                        jbxx.put("dzyx", null);

                    }
                    List gdczxx = (List) resultMap.get("股东及出资信息");
                    if (null == gdczxx || gdczxx.size() == 0) {
//                         List listg = new ArrayList();
                        for (int j = 0; j < gdczxx.size(); j++) {
                            Map maps = (Map) gdczxx.get(i);
                            Map<String, Object> mapg = new HashMap<String, Object>();
                            mapg.put("sjczfs", maps.get("实缴出资方式"));
                            mapg.put("sjczsj", maps.get("实缴出资时间"));
                            mapg.put("sjcze", maps.get("实缴出资额（万元）"));
                            mapg.put("gd", maps.get("股东"));
                            mapg.put("rjczfs", maps.get("认缴出资方式"));
                            mapg.put("rjczsj", maps.get("认缴出资时间"));
                            mapg.put("rjcze", maps.get("认缴出资额（万元）"));
                            //      listg.add( mapg);
                            paraMap.put("gdczxx", mapg);
                        }
                    } else {
                        for (int j = 0; j < gdczxx.size(); j++) {
//                            Map maps = (Map) gdczxx.get(i);
                            Map<String, Object> mapg = new HashMap<String, Object>();
                            mapg.put("sjczfs", null);
                            mapg.put("sjczsj", null);
                            mapg.put("sjcze", null);
                            mapg.put("gd", null);
                            mapg.put("rjczfs", null);
                            mapg.put("rjczsj", null);
                            mapg.put("rjcze", null);
                        }
                    }
                    //          System.out.println("yearReport1111133335555" + lists);

                    //paraMap.put("gdczxx", listg);
                    Map zczkxx = (Map) resultMap.get("资产状况信息");
                    if (jbxx != null || jbxx.size() > 0) {
                        zczkxx.put("jlr", zczkxx.get("净利润"));
                        zczkxx.put("lrzh", zczkxx.get("利润总额"));
                        zczkxx.put("syqyhj", zczkxx.get("所有者权益合计"));
                        zczkxx.put("nsze", zczkxx.get("纳税总额"));
                        zczkxx.put("yyzsr", zczkxx.get("营业总收入中主营业务收入"));
                        zczkxx.put("fzze", zczkxx.get("负债总额"));
                        zczkxx.put("zcze", zczkxx.get("资产总额"));
                        zczkxx.put("xsze", zczkxx.get("销售总额"));
                        paraMap.put("zczkxx", zczkxx);
                        //        System.out.println("yearReport11111" + lists);
                        lists.add(paraMap);
                    }else {
                        zczkxx.put("jlr", null);
                        zczkxx.put("lrzh", null);
                        zczkxx.put("syqyhj", null);
                        zczkxx.put("nsze", null);
                        zczkxx.put("yyzsr", null);
                        zczkxx.put("fzze", null);
                        zczkxx.put("zcze", null);
                        zczkxx.put("xsze", null);
                    }
                }
             }
            } else {
                lists = null;
            }
            return lists;
        }


    /*
           * 工商变更
           */
    public List businesscChange(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/businesscChange";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
//        System.out.println("1111111111..." + mapdata);
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("变更日期");
                int indexOf = fbsj.indexOf("T");
                String bgrq = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("bgrq", bgrq);
                paraMap.put("bgsx", resultMap.get("变更事项"));
                paraMap.put("bgh", resultMap.get("变更后"));
                paraMap.put("bgq", resultMap.get("变更前"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
    //    System.out.println("businesscChange..." + mapdata);
        return lists;
    }

    /*
              * 动产抵押
              */
    public List chattelmortgage(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/chattelmortgage";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
//        System.out.println("1111111111..." + mapdata);
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("登记日期");
                int indexOf = fbsj.indexOf("T");
                String djrq = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("djrq", djrq);
                paraMap.put("jyywlx", resultMap.get("交易业务类型"));
                paraMap.put("zwr", resultMap.get("债务人"));
                paraMap.put("zqse", resultMap.get("债权数额"));
                paraMap.put("zqzl", resultMap.get("债权种类"));
                paraMap.put("bz", resultMap.get("备注"));
                paraMap.put("lxqx", resultMap.get("履行期限"));
                paraMap.put("dsr", resultMap.get("当事人"));
                paraMap.put("dyqrmc", resultMap.get("抵押权人名称"));
                paraMap.put("dywgk", resultMap.get("抵押物概况"));
                paraMap.put("dbfw", resultMap.get("担保范围"));
                paraMap.put("zxyy", resultMap.get("注销原因"));
                paraMap.put("zxrq", resultMap.get("注销日期"));
                paraMap.put("zxjg", resultMap.get("登记机关"));
                paraMap.put("djzl", resultMap.get("登记种类"));
                paraMap.put("djbh", resultMap.get("登记编号"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
      //  System.out.println("chattelmortgage..." + lists);
        return lists;


    }

    /*
              * 版权出质
              */
    public List stockvend(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/stockvend";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
//        System.out.println("1111111111..." + mapdata);
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("登记日期");
                int indexOf = fbsj.indexOf("T");
                String djrq = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("djrq", djrq);
                paraMap.put("djbh", resultMap.get("登记编号"));
                paraMap.put("zqr", resultMap.get("质权人"));
                paraMap.put("zxyy", resultMap.get("注销原因"));
                paraMap.put("zt", resultMap.get("状态"));
                paraMap.put("czr", resultMap.get("出质人"));
                paraMap.put("czbd", resultMap.get("出质标的"));
                paraMap.put("czgqse", resultMap.get("出质股权数额"));
                paraMap.put("bz", resultMap.get("备注"));
                paraMap.put("zxrq", resultMap.get("注销日期"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
     //   System.out.println("2222222222..." + lists);
        return lists;


    }

    /*
                  * 行政处罚
                  */
    public List punish(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/punish";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);

        Map mapdata = (Map) JSON.get("data");
        System.out.println("行政处罚===" + mapdata);
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                paraMap.put("xzcfjdwsh", resultMap.get("行政处罚决定书文号"));
                paraMap.put("zccfjddrq", resultMap.get("作出处罚决定的日期"));
                paraMap.put("ly", resultMap.get("来源"));
                paraMap.put("mc", resultMap.get("名称"));
                paraMap.put("zw", resultMap.get("正文"));
                paraMap.put("dsr", resultMap.get("当事人"));
                paraMap.put("cfbm", resultMap.get("处罚部门"));
                paraMap.put("xzcfjd", resultMap.get("行政处罚决定"));
                paraMap.put("fddbr", resultMap.get("法定代表人（主要负责人）姓名"));
                paraMap.put("zywfwgss", resultMap.get("主要违法违规事实（案由）"));
                paraMap.put("blwzsmybjpjyw", resultMap.get("办理无真实贸易背景票据业务"));
                paraMap.put("xzcfyj", resultMap.get("行政处罚依据"));
                paraMap.put("zccfjddjg", resultMap.get("作出处罚决定的机关名称"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
      //  System.out.println("punish..." + lists);
        return lists;


    }

    /*
              * 欠税记录
              */
    public List taxarrears(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/taxarrears";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("发布时间");
                int indexOf = fbsj.indexOf("T");
                String fbsjs = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("fbsj", fbsjs);
                paraMap.put("mc", resultMap.get("名称"));
                paraMap.put("fddbrxm", resultMap.get("法定代表人姓名"));
                paraMap.put("sf", resultMap.get("省份"));
                paraMap.put("swjg", resultMap.get("税务机关"));
                paraMap.put("jydz", resultMap.get("经营地址"));
                paraMap.put("sbh", resultMap.get("识别号"));
                paraMap.put("je", resultMap.get("金额"));
                paraMap.put("gjswjg", resultMap.get("高级税务机关"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
        System.out.println("taxarrears..." + lists);
        return lists;
//        System.out.println("taxarrears..." + lists);

    }

    /*
           * 税务非正常户
           */
    public List taxabnormal(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/taxabnormal";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            System.out.println("OKOK" + json);
            Map mapdata = (Map) JSON.get("data");
            List list = (List) mapdata.get("hits");
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("认定时间");
                int indexOf = fbsj.indexOf("T");
                String rdsj = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("rdsj", rdsj);
                paraMap.put("qymc", resultMap.get("企业名称"));
                paraMap.put("fbq", resultMap.get("发布期"));
                paraMap.put("fddbr", resultMap.get("法定代表人"));
                paraMap.put("swjg", resultMap.get("税务机关"));
                paraMap.put("jydz", resultMap.get("经营地址"));
                paraMap.put("sbh", resultMap.get("识别号"));
                lists.add(paraMap);
            }
        } else {
            lists = null;
        }
        return lists;
//        System.out.println("taxabnormal..." + lists);

    }

    /*
           * 经营异常
           */
    public List abnormalbusiness(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/abnormalbusiness";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            List list = (List) mapdata.get("hits");
//        List lists=new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("列入日期");
                int indexOf = fbsj.indexOf("T");
                String lrrq = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("lrrq", lrrq);
                paraMap.put("zcjdjg", resultMap.get("作出决定机关"));
                paraMap.put("ycyy", resultMap.get("异常原因"));
                paraMap.put("ycrq", resultMap.get("移出原因"));
                lists.add(paraMap);
            }
        } else {
            lists = null;
        }
        return lists;
//        System.out.println("1111111111..." + lists);

    }

    /*
       * 开庭公告
       */
    public List court(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/court";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("开庭时间");
                int indexOf = fbsj.indexOf("T");
                String ktsj = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("ktsj", ktsj);
                paraMap.put("entities", resultMap.get("entities"));
                paraMap.put("ay", resultMap.get("案由"));
                paraMap.put("dsr", resultMap.get("当事人"));
                paraMap.put("fy", resultMap.get("法院"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }

        return lists;
//        System.out.println("1111111111..." + lists);

    }

    /*
      * 裁判文书
      */
    public List writ(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/writ";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);

        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            Map mapdata = (Map) JSON.get("data");
            List list = (List) mapdata.get("hits");
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("判决时间");
                int indexOf = fbsj.indexOf("T");
                String pjsj = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("pjsj", pjsj);
                paraMap.put("entities", resultMap.get("entities"));
                paraMap.put("dsr", resultMap.get("当事人"));
                paraMap.put("bt", resultMap.get("标题"));
                paraMap.put("ah", resultMap.get("案号"));
                paraMap.put("ay", resultMap.get("案由"));
                paraMap.put("fy", resultMap.get("法院"));
                paraMap.put("dl", resultMap.get("段落"));
                lists.add(paraMap);
            }
        } else {
            lists = null;
        }
        return lists;
//        System.out.println("1111111111..." + lists);

    }

    /*
     * 司法协助
     */
    public List judicial(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/judicial";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);

        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            Map mapdata = (Map) JSON.get("data");
            List list = (List) mapdata.get("hits");
//        List lists = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                paraMap.put("gsrq", resultMap.get("公示日期"));
                paraMap.put("zxsx", resultMap.get("执行事项"));
                paraMap.put("zxfy", resultMap.get("执行法院"));
                paraMap.put("zxcdswh", resultMap.get("执行裁定书文号"));
                paraMap.put("zxtzswh", resultMap.get("执行通知书文号"));
                paraMap.put("xxdjqxz", resultMap.get("续行冻结期限自"));
                paraMap.put("xxdjqxzs", resultMap.get("续行冻结期限至"));
                paraMap.put("gqszqymc", resultMap.get("股权所在企业名称"));
                paraMap.put("bzxr", resultMap.get("被执行人"));
                paraMap.put("bzxrcy", resultMap.get("被执行人持有股权、其它投资权益的数额"));
                lists.add(paraMap);
            }
        }else {
            lists=null;
        }
        return lists;
//        System.out.println("1111111111..." + lists);

    }

    /*
  * 执行记录
  */
    public List execute(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/execute";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("立案时间");
                int indexOf = fbsj.indexOf("T");
                String lasj = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("lasj", lasj);
                paraMap.put("entities", resultMap.get("entities"));
                paraMap.put("zxbd", resultMap.get("执行标的"));
                paraMap.put("zxfy", resultMap.get("执行法院"));
                paraMap.put("ajzt", resultMap.get("案件状态"));
                paraMap.put("ah", resultMap.get("案号"));
                paraMap.put("bzxr", resultMap.get("被执行人姓名/名称"));
                lists.add(paraMap);
            }
        }
        else
        {
            lists=null;
        }
        return lists;
//        System.out.println("1111111111..." + lists);

    }

    /*
* 失信被执行人
*/
    public List badfaith(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/badfaith";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("立案时间");
                int indexOf = fbsj.indexOf("T");
                String lasj = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("lasj", lasj);
                paraMap.put("entities", resultMap.get("entities"));
                paraMap.put("zczxyjdw", resultMap.get("做出执行依据单位"));
                paraMap.put("sxbzxrxwjtqx", resultMap.get("失信被执行人行为具体情形"));
                paraMap.put("ylx", resultMap.get("已履行"));
                paraMap.put("nl", resultMap.get("年龄"));
                paraMap.put("xb", resultMap.get("性别"));
                paraMap.put("zxyjwh", resultMap.get("执行依据文号"));
                paraMap.put("zxfy", resultMap.get("执行法院"));
                paraMap.put("wlv", resultMap.get("未履行"));
                paraMap.put("fddbrhz", resultMap.get("法定代表人或者负责人姓名"));
                paraMap.put("sxflwsqddyw", resultMap.get("生效法律文书确定的义务"));
                paraMap.put("sf", resultMap.get("省份"));
                paraMap.put("bzxrxm", resultMap.get("被执行人姓名/名称"));
                paraMap.put("bzxrlxqk", resultMap.get("被执行人的履行情况"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
        return lists;
//        System.out.println("1111111111..." + lists);

    }

    /*
        * 涉诉公告
        */
    public List advice(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/advice";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("公告时间");
                int indexOf = fbsj.indexOf("T");
                String ggsj = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("ggsj", ggsj);
                paraMap.put("entities", resultMap.get("entities"));
                paraMap.put("ggr", resultMap.get("公告人"));
                paraMap.put("gglx", resultMap.get("公告类型"));
                paraMap.put("dsr", resultMap.get("当事人"));
                paraMap.put("zw", resultMap.get("正文"));
                paraMap.put("xgr", resultMap.get("相关人"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
        return lists;

    }

    /*
    * 专利
    */
    public List patent(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/patent";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        System.out.println("patent..." + mapdata);
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                paraMap.put("entities", resultMap.get("entities"));
                paraMap.put("zldl", resultMap.get("专利代理机构"));
                paraMap.put("zllx", resultMap.get("专利类型"));
                paraMap.put("dlr", resultMap.get("代理人"));
                paraMap.put("fmr", resultMap.get("发明人"));
                paraMap.put("fmmc", resultMap.get("发明（设计）名称"));
                paraMap.put("gjfl", resultMap.get("国际分类"));
                paraMap.put("dz", resultMap.get("地址"));
                paraMap.put("zy", resultMap.get("摘要"));
                paraMap.put("sqr", resultMap.get("申请人"));
                paraMap.put("sqgbh", resultMap.get("申请公布号"));
                paraMap.put("sqh", resultMap.get("申请号"));
                String sqr = (String) resultMap.get("申请日");
                int indexOfs = sqr.indexOf("T");
                String sqrs = sqr.substring(0, indexOfs);//公司名称
                paraMap.put("sqrs", sqrs);
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
     //   System.out.println("patent..." + lists);
        return lists;
//        System.out.println("patent..." + lists);

    }

    /*
           * 著作权
           */
    public List copyright(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/copyright";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        System.out.println("copyright..." + mapdata);
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("公告日期");
                int indexOf = fbsj.indexOf("T");
                String ggrq = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("ggrq", ggrq);
                paraMap.put("zpm", resultMap.get("作品名称"));
                paraMap.put("flh", resultMap.get("分类号"));
                paraMap.put("gglx", resultMap.get("公告类型"));
                paraMap.put("bbh", resultMap.get("版本号"));
                paraMap.put("djh", resultMap.get("登记号"));
                paraMap.put("zzqr", resultMap.get("著作权人"));
                String djrq = (String) resultMap.get("登记日期");
                int indexOfs = djrq.indexOf("T");
                String djrqs = djrq.substring(0, indexOfs);//公司名称
                paraMap.put("djrq", djrqs);
                paraMap.put("zzqr", resultMap.get("软件全称"));
                paraMap.put("zzqr", resultMap.get("软件简称"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
    //    System.out.println("copyright..." + lists);
        return lists;
//        System.out.println("copyright..." + lists);

    }

    /*
         * ICP 备案信息
         */
    public List icp(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/icp";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        System.out.println("icp..." + mapdata);
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("审核通过时间");
                int indexOf = fbsj.indexOf("T");
                String shtgsj = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("shtgsj", shtgsj);
                paraMap.put("zbdwmc", resultMap.get("主办单位名称"));
                paraMap.put("zbdwxz", resultMap.get("主办单位性质"));
                paraMap.put("ba", resultMap.get("备案/许可证号"));
                paraMap.put("wzmc", resultMap.get("网站名称"));
                paraMap.put("wzym", resultMap.get("网站域名"));
                paraMap.put("wzbh", resultMap.get("网站备案/许可证号"));
                paraMap.put("fzrxm", resultMap.get("负责人姓名"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
    //    System.out.println("icp..." + lists);
        return lists;
//        System.out.println("icp..." + lists);

    }

    /*
        *招投标
        */
    public List bid(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/bid";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                String fbsj = (String) resultMap.get("发布时间");
                int indexOf = fbsj.indexOf("T");
                String fbsjs = fbsj.substring(0, indexOf);//公司名称
                paraMap.put("fbsjs", fbsjs);
                paraMap.put("gglx", resultMap.get("公告类型"));
                paraMap.put("dq", resultMap.get("地区"));
                paraMap.put("bt", resultMap.get("标题"));
                paraMap.put("zw", resultMap.get("正文"));
                paraMap.put("fbdw", resultMap.get("发布单位"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
        return lists;
//        System.out.println("bid..." + lists);

    }


    /*
       *土地信息
       */
    public List soil(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/soil";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        System.out.println("soil..." + mapdata);
        List list = (List) mapdata.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                paraMap.put("gsq", resultMap.get("公示期"));
                paraMap.put("entities", resultMap.get("entities"));
                paraMap.put("gglb", resultMap.get("公告类别"));
                paraMap.put("crnx", resultMap.get("出让年限"));
                paraMap.put("dwdz", resultMap.get("单位地址"));
                paraMap.put("srdw", resultMap.get("受让单位"));
                paraMap.put("tdyt", resultMap.get("土地用途"));
                paraMap.put("tdmj", resultMap.get("土地面积(公顷)"));
                paraMap.put("dkwz", resultMap.get("地块位置"));
                paraMap.put("bz", resultMap.get("备注"));
                paraMap.put("cjjg", resultMap.get("成交价格(万元)"));
                paraMap.put("bt", resultMap.get("标题"));
                paraMap.put("dzyj", resultMap.get("电子邮件"));
                paraMap.put("lxr", resultMap.get("联系人"));
                paraMap.put("lxdw", resultMap.get("联系单位"));
                paraMap.put("lxdh", resultMap.get("联系电话"));
                paraMap.put("xzq", resultMap.get("行政区"));
                paraMap.put("yzbm", resultMap.get("邮政编码"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
      //  System.out.println("soil..." + lists);
        return lists;
    }

    /*
           *事件检索
           */
    public List datasearch(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/datasearch";
        int begin = 1451606400;
        Date date = new Date();
        long end = date.getTime() / 1000;
        String urlParams = "id=" + id + "&begin=" + begin + "&end=" + end;
    //    System.out.println("参数888888：：：" + urlParams);
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
        Map maps = (Map) mapdata.get("event");
        List list = (List) maps.get("hits");
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                Map resultMap = (Map) list.get(i);
                paraMap.put("id", resultMap.get("_id"));
                paraMap.put("author", resultMap.get("author"));
                paraMap.put("category", resultMap.get("category"));
                paraMap.put("title", resultMap.get("title"));
                Integer post_times = (Integer) resultMap.get("post_time");
                Integer event_times = (Integer) resultMap.get("event_time");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Long time = new Long(post_times);
                Long times = new Long(event_times);
                String post_time = format.format(time);
                String event_time = format.format(times);
                paraMap.put("post_time", post_time);
                paraMap.put("event_time", event_time);
                paraMap.put("url", resultMap.get("url"));
                paraMap.put("content", resultMap.get("content"));
                lists.add(paraMap);
            }
        }
        else{
            lists=null;
        }
        System.out.println("新闻..." + lists);
        return lists;
//        System.out.println("1111111111..." + lists);

    }

    /*
          *问答
          */
    public List news(String id) throws Exception {
        String address = "http://apis.scity.cn/fengbao/riskstromdata/news";
        String urlParams = "id=" + id;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet(address, urlParams);
        JSONObject JSON = JSONObject.parseObject(json);
        Map mapdata = (Map) JSON.get("data");
//        System.out.println("1111111111..." + mapdata);
        List lists = new ArrayList();
        if (JudgeRequest.OkRequest(JSON)) {
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("id", mapdata.get("_id"));
            paraMap.put("fbsj", mapdata.get("post_time"));
            paraMap.put("bt", mapdata.get("title"));
            paraMap.put("ljdz", mapdata.get("url"));
            paraMap.put("ly", mapdata.get("author"));
            paraMap.put("zw", mapdata.get("content"));
            lists.add(paraMap);
        }else {
            lists=null;
        }
        //System.out.println("2222222222222222..." + lists);
        return lists;


    }

    /*
        *药品流通管理
        */
    public Map yaopin(String name,String reportName) throws Exception {
        String address = "http://api.internetware.cn/ahypltglxt/";
        String urlParams = "iw-apikey=123&iw-cmd=list&reportName="+reportName+"&year=2015&page=1&name=" + name;
        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json = commonGet(address, urlParams);
        String json = HttpRequest.sendGet2(address, urlParams);
//        System.out.println("yaopin--"+json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        Map map = new HashMap();
        map.put("binfo", jsonObject.getJSONObject("data").get("list"));
        return map;
//        System.out.println("==mapdata"+mapdata);
//        List list=(List) mapdata.get("list");
//        map.put("list",JSON.getJSONObject("data").get("list"));
//        System.out.println("list!==="+list);
//        List lists = new ArrayList();
//        if(list.size()>0) {
//            Map mapurl = (Map) list.get(0);
//            String url = (String) mapurl.get("url");
////            String url=JSON.getJSONObject("data").getJSONObject("list").get("url").toString();
////            List lists = new ArrayList();
////            Map listxqthree = qyyaopinthree(url);
//             Map map=new HashMap();
//             map.put("listxqthree",qyyaopinthree(url));
//             System.out.println("--listxqthree"+map);
//            if(listxqthree.size()==0){
//                map.put("listxqthree",null);
//            }else {
//                map.put("listxqthree",listxqthree);
//            }

//            Map listxqfine = qyyaopinsfine(url);
//            if(listxqfine.size()==0){
//                System.out.println("url222222..." + lists);
//                return lists;
//            }else {
//                lists.add(listxqfine);
//            }

//        lists.add(listxqfine);
//            for (int i = 0; i < list.size(); i++) {
//                Map<String, Object> paraMap = new HashMap<String, Object>();
//                Map resultMap = (Map) list.get(0);
//                paraMap.put("name", resultMap.get("name"));
//                paraMap.put("reportName", resultMap.get("reportName"));
//                paraMap.put("reportType", resultMap.get("reportType"));
//                paraMap.put("state", resultMap.get("state"));
//                paraMap.put("date", resultMap.get("date"));
////                lists.add(paraMap);
//            }
//            System.out.println("url000000..." + lists);
//            return lists;
//        }else{
//            System.out.println("url111111..." + lists);
////            return lists;
//        }

    }

    /*
        *药品流通管理详情yplt3
        */
    public Map qyyaopinthree(String url) throws Exception {

        String address = "http://api.internetware.cn/ahypltglxt/";
        String urlParams = "iw-apikey=123&iw-cmd=yplt3&url=" + url;
//        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json= commonGet(address,urlParams);
        String json = HttpRequest.sendGet2(address, urlParams);
//        System.out.println("ytl3"+json);
        JSONObject JSON = JSONObject.parseObject(json);

        Map map = new HashMap();
        map.put("data", JSON.get("data"));
//        return JSON.getJSONObject("data");
        map.put("storesTotal", JSON.getJSONObject("data").get("storesTotal"));
        map.put("medicalInsTotal", JSON.getJSONObject("data").get("medicalInsTotal"));
        map.put("operatingArea", JSON.getJSONObject("data").get("operatingArea"));
        map.put("salesTotal", JSON.getJSONObject("data").get("salesTotal"));
        map.put("hospitalPre", JSON.getJSONObject("data").get("hospitalPre"));
        map.put("prescriptionDrug", JSON.getJSONObject("data").get("prescriptionDrug"));
        map.put("overCounterDrugs", JSON.getJSONObject("data").get("overCounterDrugs"));
        map.put("nonDrug", JSON.getJSONObject("data").get("nonDrug"));
        map.put("healthFoods", JSON.getJSONObject("data").get("healthFoods"));
        map.put("medicalInstruments", JSON.getJSONObject("data").get("medicalInstruments"));
        map.put("cosmetics", JSON.getJSONObject("data").get("cosmetics"));
        map.put("other", JSON.getJSONObject("data").get("other"));
        map.put("prescriptionsTotal", JSON.getJSONObject("data").get("prescriptionsTotal"));
        map.put("hospitalPrescription", JSON.getJSONObject("data").get("hospitalPrescription"));

        return map;
    }

    /*
        *药品流通管理详情yplt5
        */
    public Map qyyaopinsfine(String url) throws Exception {
        String address = "http://api.internetware.cn/ahypltglxt/";
        String urlParams = "iw-apikey=123&iw-cmd=yplt5&url=" + url;
//        String urlParams ="url="+url;
//        urlParams = new URI(urlParams, false, "UTF-8").toString();
//        String json= commonGet(address,urlParams);
        String json = HttpRequest.sendGet2(address, urlParams);
//        System.out.println("ytl3"+json);
        JSONObject JSON = JSONObject.parseObject(json);
        Map map = new HashMap();
        map.put("data2", JSON.get("data"));
//        return JSON.getJSONObject("data");
        map.put("independentLegal", JSON.getJSONObject("data").get("independentLegal"));
        map.put("tranWeb", JSON.getJSONObject("data").get("tranWeb"));
        map.put("electricityBusiness", JSON.getJSONObject("data").get("electricityBusiness"));
        map.put("fixedAssets", JSON.getJSONObject("data").get("fixedAssets"));
        map.put("salesAmount", JSON.getJSONObject("data").get("salesAmount"));
        map.put("mobSalesAmount", JSON.getJSONObject("data").get("mobSalesAmount"));
        map.put("thirdSalesAmount", JSON.getJSONObject("data").get("thirdSalesAmount"));
        map.put("ordersTotal", JSON.getJSONObject("data").get("ordersTotal"));
        map.put("transactionOrder", JSON.getJSONObject("data").get("transactionOrder"));
        map.put("outbound", JSON.getJSONObject("data").get("outbound"));
        map.put("averagePrice", JSON.getJSONObject("data").get("averagePrice"));
        map.put("averageNum", JSON.getJSONObject("data").get("averageNum"));
        map.put("activeUsers", JSON.getJSONObject("data").get("activeUsers"));
        map.put("UV", JSON.getJSONObject("data").get("UV"));

        map.put("PV", JSON.getJSONObject("data").get("PV"));
        map.put("IP", JSON.getJSONObject("data").get("IP"));
        map.put("siteConversionRate", JSON.getJSONObject("data").get("siteConversionRate"));
        map.put("orderConversionRate", JSON.getJSONObject("data").get("orderConversionRate"));
        map.put("repeatPurchaseRate", JSON.getJSONObject("data").get("repeatPurchaseRate"));
        map.put("registeredNum", JSON.getJSONObject("data").get("registeredNum"));//20
        map.put("enterpriseUsers", JSON.getJSONObject("data").get("enterpriseUsers"));
        map.put("individualUser", JSON.getJSONObject("data").get("individualUser"));
        map.put("prescription", JSON.getJSONObject("data").get("prescription"));
        map.put("overCounterDrugs", JSON.getJSONObject("data").get("overCounterDrugs"));
        map.put("chinesePatentMed", JSON.getJSONObject("data").get("chinesePatentMed"));//25
        map.put("chineseMedicine", JSON.getJSONObject("data").get("chineseMedicine"));
        map.put("westernMedicine", JSON.getJSONObject("data").get("westernMedicine"));
        map.put("medicalInstruments", JSON.getJSONObject("data").get("medicalInstruments"));
        map.put("hygienicMaterials", JSON.getJSONObject("data").get("hygienicMaterials"));
        map.put("healthFood", JSON.getJSONObject("data").get("healthFood"));
        map.put("maternal", JSON.getJSONObject("data").get("maternal"));
        map.put("cosmetics", JSON.getJSONObject("data").get("cosmetics"));
        map.put("adultProducts", JSON.getJSONObject("data").get("adultProducts"));
        map.put("other", JSON.getJSONObject("data").get("other"));
        map.put("B2B", JSON.getJSONObject("data").get("B2B"));
        map.put("B2C", JSON.getJSONObject("data").get("B2C"));
        map.put("C2C", JSON.getJSONObject("data").get("C2C"));
        map.put("O2O", JSON.getJSONObject("data").get("O2O"));
        map.put("B2A", JSON.getJSONObject("data").get("B2A"));
        map.put("dailyOrder", JSON.getJSONObject("data").get("dailyOrder"));
        map.put("dailyOutbound", JSON.getJSONObject("data").get("dailyOutbound"));
        map.put("outgoErrorRate", JSON.getJSONObject("data").get("outgoErrorRate"));
        map.put("ontimeRate", JSON.getJSONObject("data").get("ontimeRate"));
        map.put("returnRate", JSON.getJSONObject("data").get("returnRate"));
        map.put("complaintRate", JSON.getJSONObject("data").get("complaintRate"));
        map.put("grossProfit", JSON.getJSONObject("data").get("grossProfit"));
        map.put("expenseRatio", JSON.getJSONObject("data").get("expenseRatio"));
        map.put("logisticsCostRate", JSON.getJSONObject("data").get("logisticsCostRate"));

        return map;


    }


    /**
     * 公共的get请求
     *
     * @param address
     * @param urlParams
     */
//    public String commonGet(String address, String urlParams) throws Exception {
//        GetMethod get = null;
//        if (urlParams != null && !"".equals(urlParams)) {
//            get = new GetMethod(address + "?" + urlParams);
//        } else {
//            get = new GetMethod(address);
//        }
//
//        try {
//            HttpClient client = new HttpClient();
//            get.setRequestHeader("apikey", apikey);
//            get.setRequestHeader("sign", signGetParam(apikey, secretkey, urlParams));
//
//            client.executeMethod(get);
//            System.out.println("Access System authenticate, Status: "
//                    + get.getStatusCode());
//            System.out.println("Access System authenticate, Response: "
//                    + get.getResponseBodyAsString());
//        } catch (final Exception e) {
//            e.printStackTrace();
//            // 调用异常, 返回异常报文
//        } finally {
//            get.releaseConnection();
//        }
//        return get.getResponseBodyAsString();
//    }
//
//    /**
//     * 把参数排序,然后按算法加密
//     *
//     * @param apikey
//     * @param secretkey
//     * @param param
//     * @return
//     * @throws Exception
//     */
//    public String signGetParam(String apikey, String secretkey, String param) throws Exception {
//        String pf = "";
//        if (param != null && !param.equals("")) {
//            String[] ps = param.split("&");
//            Arrays.sort(ps, String.CASE_INSENSITIVE_ORDER);
//            for (String p : ps) {
//                pf += p + "&";
//            }
//            pf = pf.substring(0, pf.length() - 1);
//        }
//
//        return signRequest(apikey, pf, secretkey);
//    }
//
//
//    public String signPostParam(String apikey, String secretkey, Map<String, Object> param) throws Exception {
//        String pf = "";
//        if (param != null) {
//            Set<String> keySet = param.keySet();
//            for (String key : keySet) {
//                pf += key + "=" + param.get(key) + "&";
//            }
//            pf = pf.substring(0, pf.length() - 1);
//        }
//
//        return signRequest(apikey, pf, secretkey);
//    }
//
//
//    public static String signRequest(String appid, String srcText, String appkey) throws Exception {
//
//        // 对报文进行BASE64编码，避免中文处理问题
//        String base64Text = new String(org.apache.commons.codec.binary.Base64.encodeBase64((appid + srcText)
//                .getBytes("utf-8"), false));
//        // MD5摘要，生成固定长度字符串用于加密
//        String destText = MD5Util.md5Digest(base64Text);
//        AlgorithmData data = new AlgorithmData();
//        data.setDataMing(destText);
//        data.setKey(appkey);
//        // 3DES加密
//        Algorithm3DES.encryptMode(data);
//        return data.getDataMi();
//    }
}
