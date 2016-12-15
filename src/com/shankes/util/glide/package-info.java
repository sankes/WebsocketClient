/**
 * @author shankes
 *
 * @data 2016-12-15
 */
package com.shankes.util.glide;

//Glide.with(this)// 1.)设置绑定生命周期
// // 2.)简单的加载图片实例
// .load(urlGif)
// // 3.)设置动态GIF加载方式
// // .asBitmap()// 显示gif静态图片
// .asGif()// 显示gif动态图片
// // 4.)设置加载中以及加载失败图片
// .placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher)//
// 以及加载失败图片
// // 5.)设置跳过内存缓存
// .skipMemoryCache(true)
// // 6.)设置下载优先级
// .priority(Priority.NORMAL)
// // 7.)设置缓存策略
// // 策略解说：
// // all:缓存源资源和转换后的资源
// // none:不作任何磁盘缓存
// // source:缓存源资源
// // result：缓存转换后的资源
// .diskCacheStrategy(DiskCacheStrategy.ALL)
// // 8.)设置加载动画,api也提供了几个常用的动画：比如crossFade()
// .animate(R.anim.item_alpha_in)
// // 9.)设置缩略图支持,先加载缩略图 再加载全图
// .thumbnail(0.1f)
// // 10.)设置加载尺寸
// .override(800, 800)
// // 11.)设置动态转换
// .centerCrop()
// // api提供了比如：centerCrop()、fitCenter()等函数
// // 也可以通过自定义Transformation，举例说明：比如一个人圆角转化器
// // .transform(new GlideRoundTransform(this))// 动态转换(圆角转化器)
// // 12.)设置监听请求接口
// // .listener(new RequestListener<String, GlideDrawable>() {
// // @Override
// // public boolean onException(Exception e, String model,
// // Target<GlideDrawable> target,
// // boolean isFirstResource) {
// // return false;
// // }
// //
// // @Override
// // public boolean onResourceReady(GlideDrawable resource, String
// // model, Target<GlideDrawable> target,
// // boolean isFromMemoryCache, boolean isFirstResource) {
// // // imageView.setImageDrawable(resource);
// // return false;
// // }
// // })
// // 13.)设置要加载的内容
// // .into(new SimpleTarget<GlideDrawable>() {
// // // 项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排，该如何实现目标下
// // @Override
// // public void onResourceReady(GlideDrawable resource,
// // GlideAnimation<? super GlideDrawable> glideAnimation) {
// // mImgGlideTest.setImageDrawable(resource);
// // }
// // })
// // 14.)加载到ImageView容器中
// .into(mImgGlideTest);
// 16.)缓存的动态清理
// Glide.get(this).clearDiskCache();// 清理磁盘缓存 需要在子线程中执行
// Glide.get(this).clearMemory();// 清理内存缓存 可以在UI主线程中进行
