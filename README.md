wkb
===
## 1. Android开发是否需要JRE或者JDK
  需要运行在虚拟机中,JVM,  android编译过程, 运行虚拟机环境.

## 2. Android SDK中的tools和platform-tools目录有什么区别?
    

## 3. 什么是Android视图?什么是Intent?

## 4. 应用的图标文件保存在什么地方?在什么地方设置?

## 5. 应用需要的mp3文件应该放在什么地方?

## 6. Activity的第一个生命周期回调函数是什么?最后一个回调函数椒什么?
  GC process
## 7. 那个类可用于记录app的日志消息?方法有那些?区别是什么?
  logcat,做了签名后, 发布就没有log输出;

## 8. 我们可以命名的资源种类有那些?
  layout，
## 9. R.java是什么? 
  运行过程， 调用SDK中的工具去自动生成；
## 10. 如果两个基于文件的资源只有扩展名不同,结果会怎样?
  
## 11. 什么是原始资源和xml资源,他们和asset有什么不同?

## 12. Xml资源能否本地话?asset呢？
  
## 13. 能否预先定义控件id,如果能，为什么?
  id数量是有限的，超过之后可以放在asset里面；
  DexClassLibrary， activity需要在manifest中定义，fragment do not need to be defined in manifest file;
  android sandbox; 
## 14. 什么是资源配置修饰符?
  
## 15. AssetManager类有什么用??

## 16. Adb工具的作用是什么?
  bridge between pc and phone, adb principle, 
## 17. ContentProvider数据库保存在那里?
  sqlite, local file, webapi
## 18. 有什么好的方法浏览ContentProvider数据库?

## 19. ContentProvider的authority属性是什么?是否可以缩短?
  unique name
## 20. 如何通过游标遍历查询结果?
  
## 21. ContentValues类有什么作用?
  key-value type for insert
## 22. ContentResolver类有什么作用?
  key-value type for query
## 23. 如何使用URIMather,他的作用是什么?
  interface for UI and database
## 24. 如何使用Intent调用Activity
  
## 25. 什么是显式Intent和隐式Intent
  Intent是一种在不同组件之间传递的请求消息，是应用程序发出的请求和意图。作为一个完整的消息传递机制，Intent不仅需要发送端，还需要接收端。
  对于明确指出了目标组件名称的Intent，我们称之为显式Intent。
  对于没有明确指出目标组件名称的Intent，则称之为隐式Intent。(launch browser to open a website)
## 26. 如何通过Intent将数据发送到接受组件
  Bundle(); hashtable
## 27. Action.MAIN的意思是什么?
  标识Activity为一个程序的开始
## 28. 如果在Intent过滤器中不指定任何操作,是否意味着活动能够响应所有操作
  must specify control
## 29. 如果在Intent过滤器中不指定任何数据,将配备那些类型的Intent?
  
## 30. 如何调用能向调用方法返回结果的Activity?

## 31. 调用Activity最快的方法是什么?
  activity process
## 32. Action_pick和action_get_content有什么区别?

## 33. 怎么新建用户界面?怎么用代码控制用户界面的元素?
  MV(layout)C(activity)
## 34. 用户界面控件怎么使用样式?
  customize control
## 35. 怎么使用主题?
  use default theme, 主题可以应用到整个应用Application范围或者某个活动Activity范围中(high priority)。
## 36. 那五种布局管理器?他们分被适合使用在什么典型场景?
  ViewStub
## 37. Android:layout_weight作用是什么?
  布局权重
## 38. Android:gravity和android:layout_gravity 

## 39. Fragment父类是什么?为什么Fragment必须附加到一个Activity才能使用?

## 40. FragmentManager用途以及特性

## 41. Fragment和Activity之前有什么相同点和不同点?
  lifecycle and relation
## 42. Fragment之间怎么通讯?
  通过activity来通讯，Bundle-〉activity-〉



