package com.smart.org.model;

import java.sql.Date;

/**
 * Created by dys on 2017/1/5.
 */
public class User {
    private Long id;//ID
    private String caseName;//案件名称
    private String involeCaseUnitIds;//涉案单位IDS
    private String involeCaseUnitNames;//涉案单位
    private String involeCasePersonIds;//涉案人员IDS
    private String involeCasePersonNameS;//涉案人员
    private String wfqkzy;//违法情况摘要
    private Long pIndex;//发案区域
    private String pName;//区域名称
    private Long pRovince;//
    private Long city;//
    private Date faDate;//发案时间
    private Long wfMoney;//违法金额
    private Date ccDate;//查处时间
    private String cfxx;//处罚信息
    private String xzajh;//行政案件号
    private Date cfDate;//处罚时间
    private String cfTypes;//处罚种类：种类ID以逗号分隔
    private String cfyj;//处罚依据
    private String cfjds;//处罚决定书
    private String cfResult;//处罚结果
    private Long caseState;//案件状态0：默认行政处罚1：建议移送
    private Long orderValue;//
    private Long isDel;//
    private Date addDate;//
    private Long addUserId;//
    private Date updateDate;//
    private Long updateUserId;//
    private Date delDate;//
    private Long delUserId;//
    private Long deptId;//
    private String deptName;//
    private String caseSn;//
    private Long ysajlrId;//移送案件录入ID
    private Long isSync;//是否同步
    private Long illegalIncome;//违法所得
    private Long hyType;//行业类型
    private Long isPublic;//是否公开
    private Long appId;//抽取第三方数据ID
    private Long sysId;//数据来源的系统0表示两法平台  1表示工商局系统  部分未定义
    private Long isSyncl;//
    private Long isGd;//是否归档 1 已归档 0 未归档
    private Long toCx;//是否已同步至安徽诚信平台 1已同步 0 未同步
    private Long isSdCase;//是否是双打案件:0双打案件，1非双打案件
    private Long isJmwlCase;//是否涉及假冒伪劣:0否，1是

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getInvoleCaseUnitIds() {
        return involeCaseUnitIds;
    }

    public void setInvoleCaseUnitIds(String involeCaseUnitIds) {
        this.involeCaseUnitIds = involeCaseUnitIds;
    }

    public String getInvoleCaseUnitNames() {
        return involeCaseUnitNames;
    }

    public void setInvoleCaseUnitNames(String involeCaseUnitNames) {
        this.involeCaseUnitNames = involeCaseUnitNames;
    }

    public String getInvoleCasePersonIds() {
        return involeCasePersonIds;
    }

    public void setInvoleCasePersonIds(String involeCasePersonIds) {
        this.involeCasePersonIds = involeCasePersonIds;
    }

    public String getInvoleCasePersonNameS() {
        return involeCasePersonNameS;
    }

    public void setInvoleCasePersonNameS(String involeCasePersonNameS) {
        this.involeCasePersonNameS = involeCasePersonNameS;
    }

    public String getWfqkzy() {
        return wfqkzy;
    }

    public void setWfqkzy(String wfqkzy) {
        this.wfqkzy = wfqkzy;
    }

    public Long getpIndex() {
        return pIndex;
    }

    public void setpIndex(Long pIndex) {
        this.pIndex = pIndex;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Long getpRovince() {
        return pRovince;
    }

    public void setpRovince(Long pRovince) {
        this.pRovince = pRovince;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Date getFaDate() {
        return faDate;
    }

    public void setFaDate(Date faDate) {
        this.faDate = faDate;
    }

    public Long getWfMoney() {
        return wfMoney;
    }

    public void setWfMoney(Long wfMoney) {
        this.wfMoney = wfMoney;
    }

    public Date getCcDate() {
        return ccDate;
    }

    public void setCcDate(Date ccDate) {
        this.ccDate = ccDate;
    }

    public String getCfxx() {
        return cfxx;
    }

    public void setCfxx(String cfxx) {
        this.cfxx = cfxx;
    }

    public String getXzajh() {
        return xzajh;
    }

    public void setXzajh(String xzajh) {
        this.xzajh = xzajh;
    }

    public Date getCfDate() {
        return cfDate;
    }

    public void setCfDate(Date cfDate) {
        this.cfDate = cfDate;
    }

    public String getCfTypes() {
        return cfTypes;
    }

    public void setCfTypes(String cfTypes) {
        this.cfTypes = cfTypes;
    }

    public String getCfyj() {
        return cfyj;
    }

    public void setCfyj(String cfyj) {
        this.cfyj = cfyj;
    }

    public String getCfjds() {
        return cfjds;
    }

    public void setCfjds(String cfjds) {
        this.cfjds = cfjds;
    }

    public String getCfResult() {
        return cfResult;
    }

    public void setCfResult(String cfResult) {
        this.cfResult = cfResult;
    }

    public Long getCaseState() {
        return caseState;
    }

    public void setCaseState(Long caseState) {
        this.caseState = caseState;
    }

    public Long getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Long orderValue) {
        this.orderValue = orderValue;
    }

    public Long getIsDel() {
        return isDel;
    }

    public void setIsDel(Long isDel) {
        this.isDel = isDel;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Long getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Long addUserId) {
        this.addUserId = addUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }

    public Long getDelUserId() {
        return delUserId;
    }

    public void setDelUserId(Long delUserId) {
        this.delUserId = delUserId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCaseSn() {
        return caseSn;
    }

    public void setCaseSn(String caseSn) {
        this.caseSn = caseSn;
    }

    public Long getYsajlrId() {
        return ysajlrId;
    }

    public void setYsajlrId(Long ysajlrId) {
        this.ysajlrId = ysajlrId;
    }

    public Long getIsSync() {
        return isSync;
    }

    public void setIsSync(Long isSync) {
        this.isSync = isSync;
    }

    public Long getIllegalIncome() {
        return illegalIncome;
    }

    public void setIllegalIncome(Long illegalIncome) {
        this.illegalIncome = illegalIncome;
    }

    public Long getHyType() {
        return hyType;
    }

    public void setHyType(Long hyType) {
        this.hyType = hyType;
    }

    public Long getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Long isPublic) {
        this.isPublic = isPublic;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getSysId() {
        return sysId;
    }

    public void setSysId(Long sysId) {
        this.sysId = sysId;
    }

    public Long getIsSyncl() {
        return isSyncl;
    }

    public void setIsSyncl(Long isSyncl) {
        this.isSyncl = isSyncl;
    }

    public Long getIsGd() {
        return isGd;
    }

    public void setIsGd(Long isGd) {
        this.isGd = isGd;
    }

    public Long getToCx() {
        return toCx;
    }

    public void setToCx(Long toCx) {
        this.toCx = toCx;
    }

    public Long getIsSdCase() {
        return isSdCase;
    }

    public void setIsSdCase(Long isSdCase) {
        this.isSdCase = isSdCase;
    }

    public Long getIsJmwlCase() {
        return isJmwlCase;
    }

    public void setIsJmwlCase(Long isJmwlCase) {
        this.isJmwlCase = isJmwlCase;
    }
}
