# Task1

## ‘F12’在Chrome浏览器中的用途

在Chrome浏览器中按下F12会在当前页面开启开发者工具，  
该工具会显示当前页面的ELements（元素）、Console（控制台）、Sources（源代码）等等内容，  
方便开发者开发调试网页。  
PS：macOS下开启开发者工具的快捷键是option+command+i

## 分析交通大学首页的载入
  首页会根据当前浏览器显示区域的宽度选择不同的body  
  宽度大于某一个值时，选择class为desktop的body；宽度小于该值时，页面刷新，此时选择class为mobile的body  
  选取完body之后是一个巨大的main-container包含了整个页面  
  内部依次是：  
  id为header、main的两个div  
  在class为desktop的body中：  
  header的布局以宽屏+鼠标指针操作为优化对象，显示了尽可能多的内容，  
  main中的news板块为横向左右分布  
  在class为mobile的body中：  
  header的布局以窄屏+手指点按操作为优化对象，将导航栏隐藏到了右侧，同时导航栏从横排变为表格形式排布，单个选项由窄长的长方形变为方正的长方形  
  main中的new板块为上下分布
  其余内容（比如main）在两种body下基本保持一致
  
## 与复旦大学的首页比较
  复旦大学的首页在载入的时候会先判断客户端的类型而非显示器宽度  
  对于ie浏览器有特殊处理，非ie浏览器则在一堆设备中依次筛选（但实际操作下来发现手机上访问到的还是桌面版网页，英文版页面无此问题）  
  剩下的内容大体上和交通大学首页中class为desktop的body内容相似  
  不过其中div的id用的都是div1、div2之类的名称，可读性有点低，另外好像这样子也不方便搜索引擎爬信息  
  
## 优化方案
  感觉交通大学的首页做的挺好的，不过我记得在一本书上看到html5的新特性里有写  
  分块可以直接写header、nav、main、footer而不用先开一个div再给一个class  
  这样似乎也有利于外部从网页源码上更快检索出信息？
