# ctblur
基于stackOverFlow提供的算法基础，实现了在Android平台上应用的动态毛玻璃效果。语言：kotlin、java<br>
This is a experimental project. A blur library for create a new mask to window. Thanks for "robinxdroid/BlurView"

# using ctblur(java版)
1.Download zip file([zip](https://github.com/MrshaoGx/ctblur/blob/master/ctblur.zip))<br>
2.Copy ctblur.jar file and folders into your project directory<br>
```java
//创建CTBlurData对象
CTBlurData data=new CTBlurData();

//设置CTBlurData的所有参数，具体参数解释见CTBlurData类说明
//then you can set the CTBlurData properties right here
 data.contextWrapper=new ContextWrapper(this.getActivity())
 data.rootView        //The layer that you want to blur
 data.viewsToBlurOnto //List<View>  The list of view that you wanna blur to nullable
 data.blurRadius = 1  //the radius ,note:when you using boxblur ,the radius must be odd
 
 // ALGORITHM_GAUSSAINFASTBLUR
 // ALGORITHM_BOXBLUR
 data.blurAlgorithm=CTBlurUtils.getIBlurAlgorithm(CTBlurUtils.ALGORITHM_GAUSSAINFASTBLUR,mBlurData.contextWrapper) 
  //创建CTBlur对象
 CTBlur ctblur=new CTBlur(data);
 ctblur.updateview(); //模糊操作，that's it
```
# note 
1.I suggest that downSampleSize keep in  '4 < downSampleSize < 15' ,make sure your don't have to solve some performance problem because of it brought.<br>
2. you can change the BLUR_INTERCEPTOR to ajust the animation effects which is a variable in CTBlur object.
