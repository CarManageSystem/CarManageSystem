<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'DisplayMap.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="js/OpenLayers.js"></script>
    <script type="text/javascript" src="js/jquery-1.10.1.js"></script>
    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    
    <style>
    body {font-family:Microsoft YaHei;}
    </style>
    
    <script type="text/javascript">
    /**
     * 地图设置相关openlayer
     */
        var road,spaceLeble,space,map,outline,spaceVector,device,errordev,deviceVector;
    	var occupy,selectedFeature,spaceControl,path,layer,deviceControl;
        var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
        var Feature = OpenLayers.Feature.Vector;
        var Geometry = OpenLayers.Geometry;
        var mapBaseUrl="http://localhost:8006/cgi-bin/mapserv.exe?MAP=D:/";
        var mapName,areaName;
        var mapServerUrl;
        renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;
        function init(){
        	initMap();
        	
        	//startWebSocket();
        	//getPieData();
    		/* setTimeout(function(){
    			getPieData();			
    		}, 5000); */
        }
        
        
    	function initMap(){
    		//var obj = document.getElementById("areaName"); //定位id
    		//var index = obj.selectedIndex; // 选中索引
    		//mapName = obj.options[index].value; // 选中值
    		//areaName=obj.options[index].text;
    		mapName = "gzpark";
    		mapServerUrl=mapBaseUrl+mapName+".map";
    		OpenLayers.ProxyHost = "cgi/proxy.cgi?url=";
    		
     		OpenLayers.Lang.setCode("zh-CN");
     		//var param={
     		//		mapName:mapName
     		//};
    		
    			//var extend=result.extend;
    			//var scales=result.scales;
    			map = new OpenLayers.Map({ 
    		  		div: "map",
    		  		//maxExtent: new OpenLayers.Bounds(extend[0],extend[1],extend[2],extend[3]),
    		  		maxExtent: new OpenLayers.Bounds(10,-60,80,0),
    		  		maxResolution: "auto",
    		  		units: 'dd',
    		  		projection: "EPSG:4326",
    		  		//minScale: 8000000,
    		  		//maxScale: 2000000,
    		  		//scales:[ scales[0],scales[1],scales[2]],
    		  		scales:[ 40000000,25000000,10000000],
    		 		// width: 2048,
    		 		// heigh: 2048		
    	 		});
    	 		spaceVector = new OpenLayers.Layer.Vector("spaceVector", {
    	        	protocol: new OpenLayers.Protocol.WFS({
    	           		url: mapServerUrl, //mapserver地图服务器的url，加上mapfile文件的路径
    	        		featureType: "space", //layer的名称
    	                srsName: "EPSG:4326", //layer的坐标系
    	                geometryName: "the_geom", //geometry字段的名称
    	                featurePrefix: "ms"
    	        	}),
    	        	strategies: [new OpenLayers.Strategy.BBOX()],
    	          	styleMap: new OpenLayers.StyleMap(
    	                {'default':{
    	                	strokeOpacity: 0,                   
    	                    fillColor: "#0000FF",
    	                    fillOpacity: 0,
    	                    pointRadius: 6,
    	                    pointerEvents: "visiblePainted", 
    	                    //label : "{licence}",
    	                },
    	                'select':{
    	                	strokeColor:"#0000FF",
    	                	strokeOpacity:0.5 ,                   
    	                    fillOpacity: 0,
    	                    pointRadius: 6,
    	                    pointerEvents: "visiblePainted",                  
    	                },
    	                'find':{
    	                	strokeOpacity: 1,
    	                	strokeColor: "#FF0000",                   
    	                    fillColor: "#646464",
    	                    fillOpacity: 0,
    	                    pointRadius: 6,
    	                    pointerEvents: "visiblePainted",                  
    	                },                 
    				}),
    	    	});
    	 
    	 
    			deviceVector = new OpenLayers.Layer.Vector("deviceVector", {
    		     	protocol: new OpenLayers.Protocol.WFS({
    		        url: mapServerUrl, //mapserver地图服务器的url，加上mapfile文件的路径
    		     		   featureType: "device", //layer的名称
    		               srsName: "EPSG:4326", //layer的坐标系
    		               geometryName: "the_geom", //geometry字段的名称
    		               featurePrefix: "ms"
    		     	}),
    		     	strategies: [new OpenLayers.Strategy.BBOX()],
    		      	styleMap: new OpenLayers.StyleMap(
    		             {'default':{
    		             	strokeOpacity: 0,                   
    		                fillColor: "#0000FF",
    		                fillOpacity: 0,
    		                pointRadius: 6,
    		                pointerEvents: "visiblePainted", 
    		                //label : "{licence}",
    		             },
    		             'select':{
    		             	strokeColor:"#0000FF",
    		             	strokeOpacity:0.5 ,                   
    		                fillOpacity: 0,
    		                pointRadius: 6,
    		                pointerEvents: "visiblePainted",                  
    		             }                     
    		        }),
    		 	});
    	         
    	            
    		 	outline=new OpenLayers.Layer.WMS("outline",mapServerUrl, {transparent: "true",layers: 'outline'},{ singleTile: true,isBaseLayer: true});
    		    space = new OpenLayers.Layer.WMS( "space",mapServerUrl, {transparent: "true",layers: 'space'},{ singleTile: true,isBaseLayer: false});
    			spaceLeble = new OpenLayers.Layer.WMS( "lable",mapServerUrl, {transparent: "true",layers: 'spaceId'},{ singleTile: true,isBaseLayer: false});
    			device=new OpenLayers.Layer.WMS( "device",mapServerUrl, {transparent: "true",layers: 'device'},{ singleTile: true,isBaseLayer: false});
    			/* errordev=new OpenLayers.Layer.WMS( "errordevice",mapServerUrl, {transparent: "true",layers: 'error_device'},{ singleTile: true,isBaseLayer: false}); */
    			
    			map.addLayer(outline);
    			map.addLayer(space);  
    		 	map.addLayer(spaceLeble);
    		 	map.addLayer(device);
    		 	/* map.addLayer(errordev); */
    		 	map.addLayer(deviceVector);
    		 	map.addLayer(spaceVector);

    		 	map.setCenter(new OpenLayers.LonLat(175, 35), 0.1);
    		  	map.addControl( new OpenLayers.Control.LayerSwitcher() ); 
    		  	setTimeout("bind()", 3000);
    	}



    	
    	function refresh2(){
    		space.redraw(true);
    		//getPieData();
    	}

    	
    	function bind(){  
            //控件SelectFeature主要用于操作(鼠标移入、移出、单击、双击)矢量图层(OpenLayers.Layer.Vector)的要素
            
    		spaceControl=new OpenLayers.Control.SelectFeature([deviceVector,spaceVector],{toggle:true});
    		
    		map.addControl(spaceControl);
    		
    		spaceControl.activate();
    		
    		spaceControl.onSelect = onSelectFeature;
    		
    		spaceControl.onUnselect = onFeatureUnselect;
    		
    	}
    	
        //构造弹出窗口
    	function onSelectFeature(currentFeature){ 
    		var feature=currentFeature;
    		selectedFeature=currentFeature;
    		
    		if(currentFeature.attributes.maptype=="space"){	
    			var para={
    				spaceId:currentFeature.attributes.spaceid
    			};
    			$.post("getcarportinfo.action",para,function(result){
    	     	    var state = result.carportState;
        			var carLicense = result.carLicense;
        			var property = null;
        			if(result.carportProperty==0){
        				property = "固定";
        			}else{
        				property = "临时";
        			}
        			if(state==0){//空闲
        				var html = "<div>"+property+"车位</div>"+"<div>暂未被占用</div>";
        				popup = new OpenLayers.Popup.FramedCloud("chicken", 
        					   		feature.geometry.getBounds().getCenterLonLat(),
        					   		null,
        					   		html,
        					   	 	null, 
        					    	true, 
        					    	onPopupClose);
        				feature.popup = popup;
        				map.addPopup(popup);
        			}else{//占用
        				var html = "<div>"+property+"车位</div>"+"<div>正在使用中</div>"+"<div>车牌："+carLicense+"</div>";
        				popup = new OpenLayers.Popup.FramedCloud("chicken", 
        				            feature.geometry.getBounds().getCenterLonLat(),
        				            null,
        				            html,
        				            null, 
        				            true, 
        				            onPopupClose);
        				 feature.popup = popup;
        				 map.addPopup(popup);
        			}
        			});				
    		}else if(currentFeature.attributes.maptype=="device"){
    			var html = "<div>设备</div>"+"<div>正在使用中</div>";
				popup = new OpenLayers.Popup.FramedCloud("chicken", 
				            feature.geometry.getBounds().getCenterLonLat(),
				            null,
				            html,
				            null, 
				            true, 
				            onPopupClose);
				 feature.popup = popup;
				 map.addPopup(popup);
    		}
    	}
            

            //关闭弹出窗口的函数
    	function onPopupClose (evt) {
    	     spaceControl.unselect(selectedFeature);
    	}
             //销毁弹出窗口的函数
    	 function onFeatureUnselect (feature) 
    	 {
    		 if(feature.popup!=null){
    		     map.removePopup(feature.popup);
    		     feature.popup.destroy();
    		     feature.popup = null;
    		 }
    	 }
    	 


    	
    	setInterval("refresh2()", 30000); 
    </script>

  </head>
  
  <body onload="init()">
  
  <iframe src='page/top.jsp' width=100% height="90px" scrolling=no></iframe>
  
  <div style="margin-top:0px;height:15px;font-size:0.9em;">
    <ol class="breadcrumb" style="padding:0px 15px;background:#ffffff">
      <li style="margin-left:60px"><a href="house.action"><b>首页</b></a></li>
      <li class="active"><b>车场管理</b></li>
    </ol>
  </div>
  
  <div class="container" style="margin-top:20px;border:3px solid #d3d3d3;border-radius:5px;">
	<div class="row" style="height:500px;">
	  <div id="map" class="col-xs-8" style="height:100%;border-right:3px solid #d3d3d3">
	  </div>
	  <div class="col-xs-4" style="height:100%;overflow:auto;">
	    
	    <table class="table table-striped table-hover" style="margin-top:20px;margin-bottom:10px;font-size:1em;text-align:center;border:2px solid #f8f8f8;border-radius:5px;">
        <thead>
          <tr>
            <td>车位编号</td>
            <td>车位状态</td>
            <td>车位类型</td>
          </tr>
        </thead>
        <tbody> 
          <c:forEach items="${list}" var="carport">
          <tr>
            <td>${ carport.carportId }</td>
            <c:if test="${carport.carportState == 1}">
   			  <td>占用</td>
			</c:if>
			<c:if test="${carport.carportState == 0}">
   			  <td>空闲</td>
			</c:if>
            <c:if test="${carport.carportProperty == 1}">
   			  <td>临时</td>
			</c:if>
			<c:if test="${carport.carportProperty == 0}">
   			  <td>固定</td>
			</c:if>
          </tr>
          </c:forEach>
        </tbody> 	
	    </table>
	  </div>
	</div>
  </div>
  
 <iframe src='page/down.jsp' width=100% style="position:fixed;bottom:10px;height:20px" scrolling=no frameborder=0></iframe>
 </body>
</html>
