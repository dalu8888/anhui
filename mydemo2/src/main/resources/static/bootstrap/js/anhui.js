/**
 * Created by Administrator on 2017/1/11.
 */
var Url="http://localhost:8080/api/users/";

function init (){
    var id=  GetQueryString("id");
    getCompanyInfoList(id);
}
function GetQueryString() {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

function getCompanyInfoList(id){
    var id=id;
    $.ajax({
        type: "get",
        url: Url+"companyInfo",
        data: {"id":id},
        dataType:"json",
        success: function(res) {
            if (res.msg == "success") {

             //   $("#gs").html(res.mc);
            }
        },
        error: function(res) {

        }
    });
}