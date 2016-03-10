# CarManageSystem
BUPT. Teacher Sun.Created By XC

在开发过程中，任何成员想到的需要大家都注意的地方，都可以写在此文档中，并在群内加以通知！

合作愉快~

开发说明：
一. 类、方法、变量命名规则统一方案  （“顾名思义”原则）
1.所有命名方式按单词首字母大写排开，所有类型第一个字母必须大写，对象及方法第一个字母小写，
	如： 工程名--CarManageSystem
	    类 对象--AppLoginDao loginDao;   注意大小写
2.所有命名尽可能与数据库字段名保持一致，或类似
	如： 数据库字段名-- car_is_owner   定义对应变量 boolean isOwner；
3.所有命名尽可能按 所实现的功能或所用的对象 的英文词句 “组合命名”，保证“顾名思义”原则
	如： 功能-- 使用动态验证码登陆
	    方法名-- loginWithDynamicMessage(String p,String m)
	    功能-- 发送手机验证码信息         
	    方法名-- sendMessageWith(String phoneString,int verifyCode)
