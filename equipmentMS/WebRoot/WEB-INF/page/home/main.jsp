<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
  </head>
  
  <body style="margin: 0px; padding: 0px; width: 100%; height: 100%;">
  	<div style="width: 100%; height: 100%; border: 0px solid #00f; overflow: hidden;">
  	    <div class="title" style="text-align: center; color:#666; font-size: 25px; margin-top: 10px;">系统使用注意事项</div>
  	    <div class="content" style="color:#666; font-size: 18px; margin: 5px; line-height: 150%;">
  	        (一)系统管理：该模块需注意的地方有<br>
  	            <span style="margin-left: 28px;"></span>1.权限，本系统的一切操作都涉及权限，执行任何系统操作均需要有对应的权限才可进行，负责将无法完成对应操作。权限结构采用账号-角色-权限模式分布，即同
  	            一个账号最多只能拥有一个角色，不同角色可以拥有不同的权限，系统角色有系统管理员来创建并维护。<br>
  	            <span style="margin-left: 28px;"></span>2.系统安全，为了提高系统安全，本系统不提供账号注册功能，所有账号均由系统管理员创建，具体创建账号的步骤请参照系统手册，本系统最高权限账号为admin
  	            账号，初始密码为administrator，请系统管理者在首次登录系统后后更改该账号密码，以确保系统安全。<br>
  	            <span style="margin-left: 28px;"></span>3.账号，新创建的账号不能登录系统，必须由账号所有者进行账号激活，并由管理员为其分配角色后才可登录系统。<br>
  	        (二)设备管理：该模块需注意的地方有<br>
  	            <span style="margin-left: 28px;"></span>1.设备类型，该设备类型不局限于设备，而是指整个系统中所有物品的类型即也包括备品备件的类型，类型名称具有唯一性即不能出现重复，具体类型的实际意义为
  	            一种具体的物品(如：型号为POSTEK C168/200s的打印机即为一个类型)，具体的设备区分依靠设备编号进行区分。<br>
  	            <span style="margin-left: 28px;"></span>2.增加设备，设备编号不能出现重复，设备编号，设备名称，设备类型，设备标识，使用状态，购买价格为必输内容，切格式要符合要求。<br>
  	            <span style="margin-left: 28px;"></span>3.设备类型，使用标识，使用状态，文档管理中的文档类型和文档格式的名称均具有唯一性即不能出现重复。<br>
  	            <span style="margin-left: 28px;"></span>4.关联备品，注意同一件备品同时最多只能与一个设备进行关联，同一设备同时可以有多个备品。<br>
  	            <span style="margin-left: 28px;"></span>5.设备编辑，设备编号及购买金额不能修改<br>
  	        (三)备品备件：该模块需注意的地方有<br>
  	            <span style="margin-left: 28px;"></span>1.增加备品，备品编号不能出现重复，备品编号，备品名称，备品类型，计量单位，单价为必输内容，切格式要符合要求。<br>
  	            <span style="margin-left: 28px;"></span>2.备品编辑，备品编号及购买金额不能修改。<br>
  	            <span style="margin-left: 28px;"></span>3.出入库，出库仅针对在库备品，入库则仅针对无库存信息的备品。<br>
  	        (四)文档管理：该模块需注意的地方有<br>
  	            <span style="margin-left: 28px;"></span>1.文档格式，请勿进行删改操作，尤其是IMAGE类型，进行删改操作后可能导致设备关联图片无法展示。<br>
  	    </div>
    </div>
  </body>
</html>
