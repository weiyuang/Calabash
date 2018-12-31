
# Java大作业 葫芦娃大战妖精 实验报告
姓名：魏煜昂  
学号：161220132

## 项目简介
![](pic/show.gif)

葫芦娃大战妖精是一个简单的游戏程序。用户通过选择葫芦娃的布阵，和另一测的妖精进行战斗。
敌对的生物体距离一定程度时两者进行战斗，失败的一方留下尸体。
最终有一方全军覆没时结束战斗。
同时战斗过程中也可以播放生动活泼的背景音乐。

## 开发环境
* Windows 10
* Java 1.8.0_192
* IntelliJ IDEA 2018.2.3

## 用户操作
程序的操作十分简单方便，用户只需要选择给定的葫芦娃阵营即可。至于哪一方获胜，全靠天意随机。
1. 在菜单中选择想要产生的阵列
2. 待屏幕上显示出双方阵势后，点击一下屏幕
3. 随心所欲拖动鼠标，进行快速的移动和战斗
4. 接下来就是听天由命了，战斗中阵亡的一方会留下尸体，在一定时间轮回后自动清空
5. 在界面的最下方有小标签，显示游戏的开始结束状态
6. 结束以后，换一个队形继续拖动鼠标玩耍吧
7. 点击文件，背景音乐可以在战斗的同时播放背景音乐

## 项目结构
### 文件目录
* java/creature:存放所有生物类，两个阵营的类
* java/gui：图形界面的生成和事件监听控制
* resources：fxml文件和需要用到的图片，音乐文件

### 数据结构
* 位置Position类  
存储一个生物体的位置，用x，y坐标来表示。
```
public class Position {
    int x;
    int y;
    ……
}

```
* 基础生物Creature类  
所有生物的基类，有名字、能力、图片、阵营、生死状态等属性，初始化为“空生物体”
生物有移动函数move，负责在整个战斗界面上进行移动和战斗的判断
```
public class Creature {
    Position position = new Position();
    String name;
    int power;
    Image image;
    public int camp;
    protected boolean state;
    public Creature() {
        name = null;
        camp = EMPTY;
        state = false;
        power = 0.5;
    }
    ……
    void move(){……}
}
```
* 其他生物类  （包括尸体）  
继承`Creature`类，额外增加了一些属性，如战斗力，图形文件等。  
一共有Calabash，Grandpa，Toad，Scorpion和Snake这些生物体。

* HeroList类  
存储所有葫芦娃和爷爷的一个类，主要负责所有己方生物的存储和队形排列。
```
public class HeroList{
    List<Creature> List =new ArrayList<Creature>();//列表存储所有己方生物
    ……//其他属性
    void setQueue(int index){……}//根据不同的队形站队
    ……//其他方法
}
```
* MonsterList类  
和HeroList类似，替换为敌方生物。
* battleMap数组  
是一个Creature类型的二维数组，用来存放整张战斗地图。  
```
Creature[][] battleMap;
void initbattleMap(){
        battleMap = new Creature[HEIGHT][WIDTH];
        for (int i=0;i<HEIGHT;i++)
            for (int j=0;j<WIDTH;j++)
                battleMap[i][j]= new Creature();
    }
```


### 运行流程
1. 自动生成一个运行界面，进行必要的初始化
2. 用户选择阵型后，双方阵营分别创建各自的生物体，放置到HeroList和MonsterList中
3. 按照阵营，把所有生物依次放置在battleMap上
4. 开始运行，每个生物体进行移动并战斗，不断重绘画布
5. 检测到结束时，停止战斗

## 一些算法
### 移动和战斗  
每一个生物体Creature都带有move方法，若这个生物不是空生物，也不是尸体，那么就可以进行移动。
简单给出代码框架：
```
public void move() {
        if (this.camp == MONSTER || this.camp == HERO) {//判断是否可以移动
            Random random = new Random();
            int desX = getX(), desY = getY();
            int diretion = random.nextInt(4);//生成随机数，判断向哪个方向移动
            if (direction == ？)
                ……// 判断方向，生成desX,desY坐标，表示预计目的地
            if (desX < WIDTH && desX > 0 && desY < HEIGHT && desY > 0) {//判断目的地是否在框架内
                int preX=getX();//记录下preX，preY坐标，表示现在正处在的位置
                int preY=getY();
                if (this.camp == HERO) {//如果是己方人物
                    if (目的地空)
                        ……//直接行走，放置到目的地上，原来的位置生成空Creature
                    else if (目的地为对方生物) {
                        Fight()//对两个生物进行战斗力的对比，失败者在原地创建出一个尸体Body对象，战胜者不动
                    }
                }
                else if (this.camp == MONSTER)//如果是敌方人物，同理
                    ……
            }
        }
    }
}
```
关于战斗力对比，在初始化人物的时候就已经给出各自的能力和获胜概率
* 葫芦娃聪明机灵，碰到敌人时获胜的概率达60%
* 爷爷年老体弱，碰到敌人只有50%概率获胜
* 蛇精和蝎子精诡计多端，碰到敌人有60%概率获胜
* 蛤蟆精碰到敌人也有50%概率获胜
* 在战胜一个生物体以后，战胜方积累了格斗经验，之后获胜概率会额外增加5%

### 检查结束
判断是否结束，可以分别检查是否还有己方生物或敌方生物的剩余。如果两者中有一个没有剩余，那么就结束战斗
```
boolean checkFinish() {
    boolean heroflag = false;
    boolean monsterflag = false;
    for (int i = 0; i < HEIGHT; i++)
        for (int j = 0; j < WIDTH; j++)
            if (battleMap[i][j].camp == MONSTER)
                monsterflag = true;
    for (int i = 0; i < HEIGHT; i++)
        for (int j = 0; j < WIDTH; j++)
            if (battleMap[i][j].camp == HERO)
                 heroflag = true;
    if (!monsterflag || !heroflag)
        return true;
    else 
        return false;
}
```
## 图形界面GUI
### Gui.Main
是整个程序的入口，生成主界面，加载背景图片，加载FXML文件
### Gui.Controller
* fight方法
处理所有的外部事件。检测到鼠标点击，将状态设置为：战斗中。
检测到鼠标移动，并且在战斗状态时，就可以对每个生物体进行移动。
```
void fight(){
    battleground.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            ……//从准备状态切换为战斗状态
        }
    });
    battleground.setOnMouseMoved(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (status == 1) {//如果在战斗状态
                for (int i = 0; i < HEIGHT; i++) {//移动，战斗
                    for (int j = 0; j < WIDTH; j++)
                        battleMap[i][j].move();
                }
                ……//检测程序执行轮数，实时清空过期的尸体
                paintAll();
            }
            if (checkFinish()) {//检测是否结束，切换状态
                status = 2;
                label.setText("end");
            }
        }
    });
}
```
其中，drawAll()方法绘制所有的图形。检测该位置是否有生物或者实体，在界面上放置图片即可。尸体会在上面的函数中判断轮数并清空。

##小结
* 通过一个学期的学习，初步了解了Java程序开发需要遵循的一些原则和方法，也尽可能地在程序中有所体现。
* 代码中对继承的使用比较明显，Creature是其他所有生物的基类。
这样的方法在实现对整个界面的控制上会有比较显著的作用。
* 对面向对象有了更加深刻的认识。由于之前编写代码很多内容没有考虑到，因此对代码要进行多次的迭代和封装。
* 掌握了Maven的使用方法，方便项目管理。
* 对注解等内容的使用，也增加了代码的可读性。