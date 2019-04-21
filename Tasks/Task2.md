# Task2

## 当没有请求的时候，REST service的CPU、内存占用情况
没有请求的时候CPU显示占用就在0%和1%之间浮动；内存占用为224M，偶尔会变小一下下，但是不常见。

## 当有请求的时候，REST service的CPU、内存占用情况

并行请求数量 | CPU占用情况 | 内存占用情况
-|-|-
10 | 短时间上升到100%，之后掉到13%到15%附近 | 几乎不变
100 | 短时间上升到100%，之后掉到15%附近，次高上升到25%过 | 几乎不变
1000 | 短时间上升到100%，之后掉到20%左右 | 几乎不变
5000 | 短时间上升到100%，维持较长一段时间后下降到50%，之后升到80%，然后电脑开始卡顿 | 几乎不变
10000 | 100%不动了 | 几乎不变
 