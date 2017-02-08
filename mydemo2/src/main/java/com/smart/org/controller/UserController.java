package com.smart.org.controller;

import com.alibaba.fastjson.JSONObject;
import com.smart.org.api.resource.UserResource;
import com.smart.org.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by dys on 2017/1/5.
 */
@Controller
public class UserController {
    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserResource userResource;


    /*
        * 首页,即是搜索页面
        */
    @RequestMapping("/index")
    public String shouye(ModelMap map) {

        return "public/enterprise-searchs";
    }


    /*
        * 基本信息
        */
    @RequestMapping("/base")
    public String base(@RequestParam String id, ModelMap map) throws Exception {
        String shuju = id;
        int indexOf = shuju.indexOf("?");
        String ids = shuju.substring(0, indexOf);//id
        String caseName = shuju.substring(indexOf + 1);
//        List listanjian = userResource.getUsers(caseName);
//        map.put("listanjian",listanjian);
        List listcomapny = userResource.companyInfo(ids);
        map.put("listcomapny", listcomapny);
        List listmember = userResource.member(ids);
        map.put("listmember", listmember);
        List listinvestorInfo = userResource.investorInfo(ids);
        map.put("listinvestorInfo", listinvestorInfo);
        List listyearReport = userResource.yearReport(ids);
        map.put("listyearReport", listyearReport);
        List listpunish = userResource.punish(ids);
        map.put("listpunish", listpunish);
        return "public/base-info";
    }

    /*
           * 经营状况
           */
    @RequestMapping("/jingyin")
    public String jingyin(@RequestParam String id, ModelMap map) throws Exception {
        String shuju = id;
        int indexOf = shuju.indexOf("?");
        String ids = shuju.substring(0, indexOf);//id
        String caseName = shuju.substring(indexOf + 1);
//        String caseName = URLDecoder.decode(shuju.substring(indexOf+1), "UTF-8");
        List listanjian = userResource.getUsers(caseName);
        map.put("listanjian", listanjian);
//        System.out.println("listanjian:::" + listanjian.size());
        List listcomapny = userResource.companyInfo(ids);
        map.put("listcomapny", listcomapny);
        List listbusines = userResource.businesscChange(ids);
        map.put("listbusines", listbusines);
        List listchatte = userResource.chattelmortgage(ids);
        map.put("listchatte", listchatte);
        List liststockvend = userResource.stockvend(ids);
        map.put("liststockvend", liststockvend);
        List listpunish = userResource.punish(ids);
        map.put("listpunish", listpunish);
        List listtaxarrears = userResource.taxarrears(ids);
        map.put("listtaxarrears", listtaxarrears);
        List listabnormal = userResource.abnormalbusiness(ids);
        map.put("listabnormal", listabnormal);
        List listyearReport = userResource.yearReport(ids);
        map.put("listyearReport", listyearReport);

        //ytl3
        Map ytl3 = userResource.yaopin(caseName, "1004");


//        JSONObject ob = (JSONObject)m.get("binfo");
//        ob = (JSONObject) m.get("binfo").get(0);
        List lm = (List) ytl3.get("binfo");
        if (lm.size() > 0) {
//        JSONArray jsonArray = JSONArray.fromObject(jaStr);
            for (int i = 0; i < lm.size(); i++) {
                String jsonstr = lm.get(i).toString();
                JSONObject jsonObject = JSONObject.parseObject(jsonstr);
                map.put("binfo", ytl3.get("binfo"));
                Map mapa = userResource.qyyaopinthree(jsonObject.get("url").toString());
                map.put("storesTotal", mapa.get("storesTotal"));
                map.put("medicalInsTotal", mapa.get("medicalInsTotal"));
                map.put("operatingArea", mapa.get("operatingArea"));
                map.put("salesTotal", mapa.get("salesTotal"));
                map.put("hospitalPre", mapa.get("hospitalPre"));
                map.put("prescriptionDrug", mapa.get("prescriptionDrug"));
                map.put("overCounterDrugs", mapa.get("overCounterDrugs"));
                map.put("nonDrug", mapa.get("nonDrug"));
                map.put("healthFoods", mapa.get("healthFoods"));
                map.put("medicalInstruments", mapa.get("medicalInstruments"));
                map.put("cosmetics", mapa.get("cosmetics"));
                map.put("other", mapa.get("other"));
                map.put("prescriptionsTotal", mapa.get("prescriptionsTotal"));
                map.put("hospitalPrescription", mapa.get("hospitalPrescription"));
                map.put("data", mapa.get("data"));
//            Map mapa2 = userResource.qyyaopinsfine(jsonObject.get("url").toString());
//            map.put("data2",mapa2.get("data2"));
//            map.put("independentLegal",mapa2.get("independentLegal"));
//            System.out.println("dd"+mapa2.get("independentLegal"));
            }
        } else {
            map.put("binfo", null);
            map.put("storesTotal", null);
            map.put("medicalInsTotal", null);
            map.put("operatingArea", null);
            map.put("salesTotal", null);
            map.put("hospitalPre", null);
            map.put("prescriptionDrug", null);
            map.put("overCounterDrugs", null);
            map.put("nonDrug", null);
            map.put("healthFoods", null);
            map.put("medicalInstruments", null);
            map.put("cosmetics", null);
            map.put("other", null);
            map.put("prescriptionsTotal", null);
            map.put("hospitalPrescription", null);
            map.put("data", null);
        }
//        System.out.println("$$$"+mapa.get("storesTotal"));
//        System.out.println("药品##"+yaopin);


        //ytl5  电子商务报表数据
        Map ytl5 = userResource.yaopin(caseName, "3006");
        List lm2 = (List) ytl5.get("binfo");
        if (lm2.size() > 0) {
//        JSONArray jsonArray = JSONArray.fromObject(jaStr);
            for (int i = 0; i < lm2.size(); i++) {
                String jsonstr = lm2.get(i).toString();
                JSONObject jsonObject5 = JSONObject.parseObject(jsonstr);
                map.put("binfo5", ytl5.get("binfo"));
                Map mapa5 = userResource.qyyaopinsfine(jsonObject5.get("url").toString());
                map.put("data2", mapa5.get("data2"));
                map.put("independentLegal", mapa5.get("independentLegal"));
                //yongsheng
                map.put("tranWeb", mapa5.get("tranWeb"));
                map.put("electricityBusiness", mapa5.get("electricityBusiness"));
                map.put("fixedAssets", mapa5.get("fixedAssets"));
                map.put("salesAmount", mapa5.get("salesAmount"));
                map.put("mobSalesAmount", mapa5.get("mobSalesAmount"));
                map.put("thirdSalesAmount", mapa5.get("thirdSalesAmount"));
                map.put("ordersTotal", mapa5.get("ordersTotal"));
                map.put("transactionOrder", mapa5.get("transactionOrder"));
                map.put("outbound", mapa5.get("outbound"));
                map.put("averagePrice", mapa5.get("averagePrice"));
                map.put("averageNum", mapa5.get("averageNum"));
                map.put("activeUsers", mapa5.get("activeUsers"));
                map.put("UV", mapa5.get("UV"));
                map.put("PV", mapa5.get("PV"));
                map.put("IP", mapa5.get("IP"));
                map.put("siteConversionRate", mapa5.get("siteConversionRate"));
                map.put("orderConversionRate", mapa5.get("orderConversionRate"));
                map.put("repeatPurchaseRate", mapa5.get("repeatPurchaseRate"));
                map.put("registeredNum", mapa5.get("registeredNum"));
                map.put("enterpriseUsers", mapa5.get("enterpriseUsers"));
                map.put("individualUser", mapa5.get("individualUser"));
                map.put("prescription", mapa5.get("prescription"));
                map.put("overCounterDrugs5", mapa5.get("overCounterDrugs"));
                map.put("chinesePatentMed", mapa5.get("chinesePatentMed"));
                map.put("chineseMedicine", mapa5.get("chineseMedicine"));
                map.put("westernMedicine", mapa5.get("westernMedicine"));
                map.put("medicalInstruments5", mapa5.get("medicalInstruments"));
                map.put("hygienicMaterials", mapa5.get("hygienicMaterials"));
                map.put("healthFood", mapa5.get("healthFood"));
                map.put("maternal", mapa5.get("maternal"));
                map.put("cosmetics5", mapa5.get("cosmetics"));
                map.put("adultProducts", mapa5.get("adultProducts"));
                map.put("other5", mapa5.get("other"));
                map.put("B2B", mapa5.get("B2B"));
                map.put("B2C", mapa5.get("B2C"));
                map.put("C2C", mapa5.get("C2C"));
                map.put("O2O", mapa5.get("O2O"));
                map.put("B2A", mapa5.get("B2A"));
                map.put("dailyOrder", mapa5.get("dailyOrder"));
                map.put("dailyOutbound", mapa5.get("dailyOutbound"));
                map.put("outgoErrorRate", mapa5.get("outgoErrorRate"));
                map.put("ontimeRate", mapa5.get("ontimeRate"));
                map.put("returnRate", mapa5.get("returnRate"));
                map.put("complaintRate", mapa5.get("complaintRate"));
                map.put("grossProfit", mapa5.get("grossProfit"));
                map.put("expenseRatio", mapa5.get("expenseRatio"));
                map.put("logisticsCostRate", mapa5.get("logisticsCostRate"));
                System.out.println("dd" + mapa5.get("independentLegal"));
            }
        } else {
            map.put("data2", null);
            map.put("independentLegal", null);
            map.put("tranWeb", null);
            map.put("electricityBusiness", null);
            map.put("fixedAssets", null);
            map.put("salesAmount", null);
            map.put("mobSalesAmount", null);
            map.put("thirdSalesAmount", null);
            map.put("ordersTotal", null);
            map.put("transactionOrder", null);
            map.put("outbound", null);
            map.put("averagePrice", null);
            map.put("averageNum", null);
            map.put("activeUsers", null);
            map.put("UV", null);
            map.put("PV", null);
            map.put("IP", null);
            map.put("siteConversionRate", null);
            map.put("orderConversionRate", null);
            map.put("repeatPurchaseRate", null);
            map.put("registeredNum", null);
            map.put("enterpriseUsers", null);
            map.put("individualUser", null);
            map.put("prescription", null);
            map.put("overCounterDrugs5", null);
            map.put("chinesePatentMed", null);
            map.put("chineseMedicine", null);
            map.put("westernMedicine", null);
            map.put("medicalInstruments5", null);
            map.put("hygienicMaterials", null);
            map.put("healthFood", null);
            map.put("maternal", null);
            map.put("cosmetics5", null);
            map.put("adultProducts", null);
            map.put("other5", null);
            map.put("B2B", null);
            map.put("B2C", null);
            map.put("C2C", null);
            map.put("O2O", null);
            map.put("B2A", null);
            map.put("dailyOrder", null);
            map.put("dailyOutbound", null);
            map.put("outgoErrorRate", null);
            map.put("ontimeRate", null);
            map.put("returnRate", null);
            map.put("complaintRate", null);
            map.put("grossProfit", null);
            map.put("expenseRatio", null);
            map.put("logisticsCostRate", null);
        }


        return "public/jingyZk";
    }

    /*
           * 企业年报
           */
    @RequestMapping("/qiyenb")
    public String qiyenb(@RequestParam String id, ModelMap map) throws Exception {
        String shuju = id;
        int indexOf = shuju.indexOf("?");
        String ids = shuju.substring(0, indexOf);//id
        String caseName = shuju.substring(indexOf + 1);
        List listanjian = userResource.getUsers(caseName);
        map.put("listanjian", listanjian);
//        List listyearReport = userResource.yearReport(ids);
//        map.put("listyearReport", listyearReport);
        List listcomapny = userResource.companyInfo(ids);
        map.put("listcomapny", listcomapny);
        List listpunish = userResource.punish(ids);
        map.put("listpunish", listpunish);
        return "public/qiyeNianB";

    }

    /*/*
              * 公司查询
              */
//    @RequestMapping(value = "/company")
//    public String company(@RequestParam String keyword, ModelMap map) throws Exception {
//
//        List lists = userResource.company(keyword);
//        map.put("lists", lists);
//        return "public/enterprise-search :: lists";
//    }

    /*
             * 公司查询分页
             */
    @RequestMapping(value = "/companys")
    public String companys(@RequestParam(required = false, defaultValue = "1") long page,
                           @RequestParam String keyword, ModelMap map) throws Exception {
//        String shuju = keyword;
//        int indexOf = shuju.indexOf("?");
//        String ids = shuju.substring(0, indexOf);//id
//        System.out.println("---ids"+ids);
//        String from = shuju.substring(indexOf + 1);
//        System.out.println("keyword--"+keyword);

        List lists = userResource.companys(keyword,Long.toString(page));
        map.put("lists", lists);
        return "public/enterprise-search";
    }

    /*
   * 法律诉讼
   */
    @RequestMapping("/law")
    public String falv(@RequestParam String id, ModelMap map) throws Exception {
        String shuju = id;
        int indexOf = shuju.indexOf("?");
        String ids = shuju.substring(0, indexOf);//id
        String caseName = shuju.substring(indexOf + 1);
        List listanjian = userResource.getUsers(caseName);
        map.put("listanjian", listanjian);
        List listcomapny = userResource.companyInfo(ids);
        map.put("listcomapny", listcomapny);
        List listcourt = userResource.court(ids);
        map.put("listcourt", listcourt);
        List listwrit = userResource.writ(ids);
        map.put("listwrit", listwrit);
        List listjudicial = userResource.judicial(ids);
        map.put("listjudicial", listjudicial);
        List listexecute = userResource.execute(ids);
        map.put("listexecute", listexecute);
        List listbadfaith = userResource.badfaith(ids);
        map.put("listbadfaith", listbadfaith);
        List listadvice = userResource.advice(ids);
        map.put("listadvice", listadvice);
        List listyearReport = userResource.yearReport(ids);
        map.put("listyearReport", listyearReport);
        List listpunish = userResource.punish(ids);
        map.put("listpunish", listpunish);
        return "public/law";
    }

    /*
     * 知识产权
     */
    @RequestMapping("/knowledge")
    public String knowledge(@RequestParam String id, ModelMap map) throws Exception {
        String shuju = id;
        int indexOf = shuju.indexOf("?");
        String ids = shuju.substring(0, indexOf);//id
        String caseName = shuju.substring(indexOf + 1);
        List listanjian = userResource.getUsers(caseName);
        map.put("listanjian", listanjian);
        List listcomapny = userResource.companyInfo(ids);
        map.put("listcomapny", listcomapny);
        List listpatent = userResource.patent(ids);
        map.put("listpatent", listpatent);
        List listcopyright = userResource.copyright(ids);
        map.put("listcopyright", listcopyright);
        List listicp = userResource.icp(ids);
        map.put("listicp", listicp);
        List listbid = userResource.bid(ids);
        map.put("listbid", listbid);
        List listsoil = userResource.soil(ids);
        map.put("listsoil", listsoil);
        List listyearReport = userResource.yearReport(ids);
        map.put("listyearReport", listyearReport);
        List listpunish = userResource.punish(ids);
        map.put("listpunish", listpunish);
        return "public/knowledge";
    }

    /*
           * 新闻事件
           */
    @RequestMapping("/news")
    public String news(@RequestParam String id, ModelMap map) throws Exception {
        String shuju = id;
        int indexOf = shuju.indexOf("?");
        String ids = shuju.substring(0, indexOf);//id
        String caseName = shuju.substring(indexOf + 1);
        List listanjian = userResource.getUsers(caseName);
        map.put("listanjian", listanjian);
        List listcomapny = userResource.companyInfo(ids);
        map.put("listcomapny", listcomapny);
        List listnews = userResource.datasearch(ids);
        map.put("listnews", listnews);
        List listyearReport = userResource.yearReport(ids);
        map.put("listyearReport", listyearReport);
        List listpunish = userResource.punish(ids);
        map.put("listpunish", listpunish);
        return "public/news";
    }

    /*
         * 关联
         */
    @RequestMapping("/releave")
    public String releave(@RequestParam String id, ModelMap map) throws Exception {
        String shuju = id;
        int indexOf = shuju.indexOf("?");
        String ids = shuju.substring(0, indexOf);//id
        String caseName = shuju.substring(indexOf + 1);
        List listanjian = userResource.getUsers(caseName);
        map.put("listanjian", listanjian);
        List listcomapny = userResource.companyInfo(ids);
        map.put("listcomapny", listcomapny);
        List listyearReport = userResource.yearReport(ids);
        map.put("listyearReport", listyearReport);
        List listpunish = userResource.punish(ids);
        map.put("listpunish", listpunish);
        return "public/releave";
    }

    /*
           *药品流通管理
           */
//    @RequestMapping("/yaopin")
//    public String yaopin(@RequestParam String name,ModelMap map) throws Exception {
//        userResource.yaopin(name,map);
//       map.put("listyaopin",listyaopin);
//        System.out.print("...." + listyaopin);
//        return "public/index";
//    }

    /*
   * 公司详情
   */
//    @RequestMapping(value = "/companyInfo")
//    public String companyInfo(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.companyInfo(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
   * 公司主要成员
   */
//    @RequestMapping(value = "/member")
//    public String member(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.member(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 公司股东信息
*/
//    @RequestMapping(value = "/investorInfo")
//    public String investorInfo(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.investorInfo(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 对外投资
*/
//    @RequestMapping(value = "/investment")
//    public String investment(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.investment(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 年报
*/
//    @RequestMapping(value = "/yearReport")
//    public String yearReport(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.yearReport(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
    * 工商变更
    */
//    @RequestMapping(value = "/businesscChange")
//    public String businesscChange(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.businesscChange(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
   * 动产抵押
   */
//    @RequestMapping(value = "/chattelmortgage")
//    public String chattelmortgage(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.chattelmortgage(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
 * 版权出质
 */
//    @RequestMapping(value = "/stockvend")
//    public String stockvend(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.stockvend(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 行政处罚
*/
//    @RequestMapping(value = "/punish")
//    public String punish(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.punish(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 欠税记录
*/
//    @RequestMapping(value = "/taxarrears")
//    public String taxarrears(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.taxarrears(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 税务非正常户
*/
//    @RequestMapping(value = "/taxabnormal")
//    public String taxabnormal(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.taxabnormal(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 经营异常
*/
//    @RequestMapping(value = "/abnormalbusiness")
//    public String abnormalbusiness(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.abnormalbusiness(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 开庭公告
*/
//    @RequestMapping(value = "/court")
//    public String court(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.court(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 裁判文书
*/
//    @RequestMapping(value = "/writ")
//    public String writ(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.writ(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 司法协助
*/
//    @RequestMapping(value = "/judicial")
//    public String judicial(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.judicial(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 执行记录
*/
//    @RequestMapping(value = "/execute")
//    public String execute(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.execute(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 失信被执行人
*/
//    @RequestMapping(value = "/badfaith")
//    public String badfaith(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.badfaith(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 涉诉公告
*/
//    @RequestMapping(value = "/advice")
//    public String advice(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.advice(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 专利
*/
//    @RequestMapping(value = "/patent")
//    public String patent(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.patent(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* 著作权
*/
//    @RequestMapping(value = "/copyright")
//    public String copyright(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.copyright(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
* ICP 备案信息
*/
//    @RequestMapping(value = "/icp")
//    public String icp(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.icp(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
*   招投标
*/
//    @RequestMapping(value = "/bid")
//    public String bid(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.bid(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
*   土地信息
*/
//    @RequestMapping(value = "/soil")
//    public String soil(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.soil(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
*   事件检索
*/
//    @RequestMapping(value = "/datasearch")
//    public String datasearch(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.datasearch(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
    /*
*   时间检索
*/
//    @RequestMapping(value = "/news")
//    public String news(@RequestParam  String id, ModelMap map) throws Exception {
//        System.out.print("llll");
//        List lists = userResource.news(id);
//        map.put("lists",lists);
//        return "public/index";
//    }
}
