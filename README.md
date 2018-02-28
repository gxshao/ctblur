# ctblur
This is a experimental project. A blur library for create a new mask to window. Thanks for "robinxdroid/BlurView"

# using ctblur(java)
1.Download zip file([zip](https://github.com/MrshaoGx/ctblur/blob/master/ctblur.zip))<br>
2.Copy ctblur.jar file and folders into your project directory<br>
```java
CTBlurData data=new CTBlurData();


//then you can set the CTBlurData properties right here
 data.contextWrapper=new ContextWrapper(this.getActivity())
 data.rootView   //The layer that you want to blur
 data.viewsToBlurOnto //List<View>  The list of view that you wanna blur to nullable
 data.blurRadius = 1  //the radius ,note:when you using boxblur ,the radius must be odd
 data.blurAlgorithm=CTBlurUtils.getIBlurAlgorithm(1,mBlurData.contextWrapper)  // 0:GaussianFastBlur  1:BoxBlur

CTBlur ctblur=new CTBlur(data);

ctblur.updateview(); //that's it
```
