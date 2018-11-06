# 反射

## 步骤

1. 类加载器获取对象
2. 获取类的默认构造器并通过它实例化
3. 通过反射方法设置属性

## 加载器工作机制

1. 装载

   > 查找和导入class文件

2. 链接

   > 执行校验准备和解析步骤，其中解析步骤是可选择的
   >
   > 步骤
   >
   > 1. 校验：检查载入class文件数据额的正确性
   > 2. 准备：给类的静态变量分配存储空间
   > 3. 解析：将符号引用转为直接引用

3. 初始化： 

   > 对类的静态变量。静态代码块执行初始化工作



# Bean

##Bean的基本配置

< bean    id="Foo"          class="com.smart.Foo">

| bean 名称 | bean的类名    |
| --------- | ------------- |
| Foo       | com.smart.Foo |

> spring不允许出现两个相同的id的<bean>，但却可以出现两个相同 name 的<bean>。如果通过 getBean(beanName)获取 bean 时，将返回最近的一个，前面的都会被覆盖

## JavaBean关于属性命名的特殊规范

一般情况下，java的属性变量名都以小写字母开头，如 `maxSpeed，brand`，但也存在特殊的情况。考虑到一些特定意义，如`USA`,`XML`等，JavaBean 允许以大写字母开头的属性变量名，不过必须满足**变量名的前两个字母要么全部大写，要么全部小写**，因此`maxSpeed` ,`IDCard`,`IDCode`都是合法的。`iDCard` ` iDCode`是不合法的。

> 在属性注入 Set（）Get（）方法时，会报没有Set()方法的错误，其实是命名不规范导致的



## bean之间的关系

### 继承

``` java
<bean id="abstractCar" class="com.xxx.Car"
    p:brand="宝马" p:price="10000.00" p:color="red" abstract="true"/>
    
<bean id="car1" p:brand="奔驰" p:color="white" parent="abstractCar"/>
<bean id="car2" p:brand="宝马" p:color="black" parent="abstractCar"/>
```



## 依赖

``` java
<bean id="">
```























































