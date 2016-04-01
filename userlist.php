<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel app\models\CountrySearch */
/* @var $dataProvider yii\data\ActiveDataProvider */
$this->title = '用户列表';
$this->params['index'] = 11;
define('ROOT_DIR',Yii::getAlias('@web').'/');
?>
<style>
 .buttons{
    width:85px;
    margin-right: 10px;
 }
</style>
<!-- <input type='button' value='添加用户' onclick="window.location.href='useradd'" class=' btn btn-warning'> -->
<div id="user_list" class="inner">
     <script src="<?=ROOT_DIR?>js/jquery.dataTables.min.js"  type="text/javascript" ></script>
     <script src="<?=ROOT_DIR?>js/dataTables.bootstrap.js"  type="text/javascript" ></script>
    <h1 class="tablehead">用户管理</h1>
    <div  class="div-table">
        <input type='button' value='添加用户' onclick="window.location.href='useradd'" class=' btn btn-warning' style='margin-bottom: 20px;'>
        <table class="table table-bordered table-striped table-hover" id="user">
          <thead>
            <tr>
                <th style="text-align: center">用户名</th>
                <th style="text-align: center">姓名</th>
                <th style="text-align: center">电话</th>
                <th style="text-align: center">邮箱</th>
                <th style="text-align: center">角色</th>
                <!-- <th>所属部门</th>
                <th>负责区域</th>  -->              
                <th style="text-align: center">操作</th>
            </tr>
          </thead>
          <tbody id="tab">
          </tbody>
        </table>    
    </div>
</div>

<script>
//alert("ss");
//



$(document).ready(function() {
$.ajax({
    url:"<?=ROOT_URL?>user/list",
    type:"GET",
    dataType : 'json',    
   //async : false,  //同步
    success : function(jsonStr){
        //alert(jsonStr);
     //if(jsonStr==null){
        var appendStr = "";
        $.each(jsonStr, function(index, content){
            var role = "";
            if(content.role_id==0){
                role="管理员";
            }
            if(content.role_id==1){
                role="运营";
            }
            if(content.role_id==2){
                role="客服运营";
            }
            if(content.role_id==3){
                role="销售";
            }
            if(content.role_id==4){
                role="检测";
            }

            appendStr+="<tr >";
            appendStr+="<td>"+content.user_id+"</td>";
            appendStr+="<td>"+content.user_name+"</td>";
            appendStr+="<td>"+content.user_tel+"</td>";
            appendStr+="<td>"+content.user_email+"</td>";
            appendStr+="<td>"+role+"</td>";
            //appendStr+="<td>"+content.dep_id+"</td>";
            //appendStr+="<td>"+content.user_area+"</td>";
            appendStr+="<td><input type='button' value='修改' onclick=\"update('"+content.user_id+"')\" class='buttons btn btn-warning'>&nbsp";
            if(content.user_id!= 'admin')
            appendStr+="<input type='button' value='删除'  onclick=\"del('"+content.user_id+"')\" class='del buttons btn btn-warning'></td>";
            appendStr+="</tr>"; 
            });
        $("#tab").append(appendStr);

        $("#user").dataTable({
            //"lengthChange": false,
            "dom": 'frtip',

            "sPaginationType": "full_numbers",
            "oLanguage": {
                "sInfo": "共_TOTAL_条数据，展示_START_到_END_条数据",
                "sInfoEmpty": "无数据",
                "sInfoFiltered": "(从_MAX_条数据中的查询)",
                "sLengthMenu": "每页显示 _MENU_ 条数据",
                "sSearch":  "查找 _INPUT_ ",
                "sZeroRecords": " ",
                "oPaginate":{
                    "sPrevious":"上一页",
                    "sNext":"下一页",
                    "sLast":"尾页",
                    "sFirst":"首页"
                },
            },
            "iDisplayLength":10,
            // "aaSorting": [[3,'desc']]                  
        });
        $("#user_filter").css("text-align","left");
        $("#user_info").css({"width":"50%","float":"left"});

    },
    error:function(){
       // alert("error");
    }
});
});

$(document).ready(function() {
    
});


function del(userid){
    $.messager.confirm("删除确认", "确定删除用户 "+userid+" 吗", function() { 
        $.ajax({
            url:"<?=ROOT_URL?>user/delete",
            type:"post",
            data:{user_id:userid},
            success:function(){
                window.location.reload();
            },
            error:function(){
                //alert("error");
            }
        });
    })
}
function update(userid){
    location.href = "userupdate?userid="+userid;
}

</script>