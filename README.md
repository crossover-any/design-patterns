# design-patterns
# 设计模式

## 意义

- 代码重用型（即相同功能的代码，不用多次编写）
- 可读性（即编程规范性，便于其他程序员的阅读和理解）
- 可扩展性（即当需要增加新的功能时，非常的方便，称为可维护）
- 可靠性（即当我们增加新的功能后，对原来的功能没有影响）
- 高内聚，低耦合

## 七大原则

- 开闭原则

- 里氏替换原则
- 依赖倒置原则 
- 单一职责原则
- 接口隔离原则
- 合成复用原则

开闭原则：对扩展开放，对修改关闭。

单一职责：一个类只负责一个功能领域中的相应职责。

里氏替换：所有引用基类的地方必须能透明的使用其子类的对象。

依赖倒置：依赖于抽象，不能依赖于具体实现。

接口隔离：类之间的关系应该建立在最小的接口上。

合成复用：尽量使用合成、聚合的方式，而不是使用继承。

迪米特：一个软件实体应当尽可能少的与其它实体类发生相互作用。



开闭原则

```java
//某系统后台需要监测雨雾数据展示图标，如柱状图、折线图等，在未来需要支持图标的着色操作。在开始设计的时侯，代码可能是这样子的。
public class BarChart {

	public void draw(){
		System.out.println("Draw bar chart...");
	}
}

public class LineChart {

	public void draw(){
		System.out.println("Draw line chart...");
	}
}

public class App {

	public void drawChart(String type){
		if (type.equalsIgnoreCase("line")){
			new LineChart().draw();
		}else if (type.equalsIgnoreCase("bar")){
			new BarChart().draw();
		}
	}
}
-----------------------------------------
    //这样子在初期是能满足业务需求的，但是如果后面需要支持其他的类图则需要重新重新编写一个类，并且需要增加if分支的判断，是十分不合理的。基于此，需要引入一个抽象的Char类AbstractChart，App类在画图的时侯总是把相关的操作委托到据图的AbstractChart的派生类实例，这样的话App类的代码就不用修改。
    public abstract class AbstractChart {

	public abstract void draw();
}

public class BarChart extends AbstractChart{

	@Override
	public void draw() {
		System.out.println("Draw bar chart...");
	}
}

public class LineChart extends AbstractChart {

	@Override
	public void draw() {
		System.out.println("Draw line chart...");
	}
}

public class App {

	public void drawChart(AbstractChart chart){
		chart.draw();
	}
}
```



单一职责

需求描述：在一个项目系统代码编写的时侯，由于历史原因和人为的不规范，导致项目没有分层，一个Service类的伪代码是这样的

```java
public class Service {
	
	public UserDTO findUser(String name){
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_user WHERE name = ?");
		preparedStatement.setObject(1, name);
        User user = //处理结果
        UserDTO dto = new UserDTO();
        //entity值拷贝到dto
        return dto;
	}
}
```

问题分析：这里出现了一个问题，Service做了太多的东西，包括数据库连接的管理，Sql的执行这些业务层不应该接触到的逻辑，更可怕的是，例如到时候如果数据库换成了Oracle，这个方法将会大改，因此，拆分除新的DataBaseUtils类用于执行查询和查询结果封装，改造后Service的伪代码如下

```java
public class Service {

    private Dao dao;
	
	public UserDTO findUser(String name){
       User user =  dao.findUserByName(name);
       UserDTO dto = new UserDTO();
        //entity值拷贝到dto
       return dto;
    }
}


public class Dao{

    public User findUserByName(String name){
       Connection connection = DataBaseUtils.getConnnection();
       PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM t_user WHERE name = ?");
		preparedStatement.setObject(1, name);
        User user = //处理结果
        return user;
    }
}
```



里氏替换

里氏替换是实现开闭原则的基础，它告诉我们在设计程序的时侯可能使用基类进行对象的定义和引用，在运行时再决定积累的具体子类型。举个简单的例子，假设一种会呼吸的动物作为父类，子类猪和鸟也有自身的呼吸方式：

```java
public abstract class Animal {

	protected abstract void breathe();
}

public class Bird extends Animal {

	@Override
	public void breathe() {
		System.out.println("Bird breathes...");
	}
}

public class Pig extends Animal {

	@Override
	public void breathe() {
		System.out.println("Pig breathes...");
	}
}

public class App {

	public static void main(String[] args) throws Exception {
		Animal bird = new Bird();
		bird.breathe();
		Animal pig = new Pig();
		pig.breathe();
	}
}
```

依赖倒转

需求描述：组装一台电脑

```java
//电脑
public class Computer{
    private HuaShuoMainBoard huaShuoMainBoard;
    private JinShiDunMemory jinShiDunMemory;
    private XiJieHardDisk xiJieHardDisk;

    public HuaShuoMainBoard getHuaShuoMainBoard() {
        return huaShuoMainBoard;
    }
    public void setHuaShuoMainBoard(HuaShuoMainBoard huaShuoMainBoard) {
        this.huaShuoMainBoard = huaShuoMainBoard;
    }
    public JinShiDunMemory getJinShiDunMemory() {
        return jinShiDunMemory;
    }
    public void setJinShiDunMemory(JinShiDunMemory jinShiDunMemory) {
        this.jinShiDunMemory = jinShiDunMemory;
    }
    public XiJieHardDisk getXiJieHardDisk() {
        return xiJieHardDisk;
    }
    public void setXiJieHardDisk(XiJieHardDisk xiJieHardDisk) {
        this.xiJieHardDisk = xiJieHardDisk;
    }
}

public class MainClass {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.setHuaShuoMainBoard(new HuaSuoMainBoard());
        computer.setJinShiDunMemory(new JinShiDunMemory());
        computer.setXiJieHardDisk(new XiJieHardDisk());
        
        computer.setHuaShuoMainBoard(new WeiXingMainBoard());//报错，无法安装
    }
}
```

问题描述：可以看到如果我们此类里面的属性和方法均是具体细节实现，计算机则只能选择固定的配置，这明显是不合理的，如果我们把里面的具体实现换成抽象，那么就可以自由组装电脑。