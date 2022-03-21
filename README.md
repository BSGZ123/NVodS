# NVodS 感谢多个开源项目，如谷粒学院
是基于Java 8/11/13、SpringBoot 2.2.6.RELEASE、SpringCloud Hoxton.SR2、Spring Cloud Alibaba 2.2.0.RELEASE、MyBatis Plus 3.1.0
等技术构建的一个在线视频的点播平台。
是个学习项目同样也是毕业设计

##关于项目开发学习中遇到的问题
###主要问题1：AbstractMethodError: com.alibaba.cloud.sentinel.feign.SentinelContractHolder.parseAndValidateMetadat...
在之前使用的Spring Cloud和Spring Cloud Alibaba版本之间出现接口名不一致的问题，在 Hoxton.SR1 中，fegin.context 接口的定义为 parseAndValidatateMetadata，
而在 Hoxton.SR3 中，fegin.context 接口的定义为 parseAndValidateMetadata，就是之前版本中定义的方法名拼写错误，所以在 Hoxton.SR1 后面的版本更正过来。

解决办法：要错一起错，要对一起对  要么都选择SpringCloud Hoxton.SR2以后 Spring Cloud Alibaba 2.2.0以后，要么都选择以前的版本。

###主要问题2：在使用openfeign访问调用远程方法时，出现了循坏依赖的问题
当时搞得我头皮发麻，把依赖注入的几个service接口看了好几遍，想了好久都没想出来到底哪儿形成注入闭环了，后来是在不行就顺手搜了下openfeign 循环依赖
关键字，结果发现依然是Spring Cloud和Spring Cloud Alibaba之间不兼容导致的原因，学习的教程有些老了，就想着升升级，也到spring.io官网瞅了两眼依赖要求，
只是没想到这么迷，以后直接用新版本重新写这个项目。。。。。

###细碎的问题：
####粗心啦！键盘一敲快，字打错啦，然后好几天才发现
####controller层的@PostMapping注解，一不小心将两个方法注解参数写一样了啦
####等等各种各样小错误。。。

BsKPLu
Test
