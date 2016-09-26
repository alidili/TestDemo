package com.yang.testdemo.logic;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑管理
 * Created by yangle on 2016/3/28.
 */
public class Controller {

    private static Controller controller;
    private static List<Activity> activityList;
    private static Activity currentActivity;
    private static Context context;

    /**
     * 获取Controller实例
     *
     * @return Controller实例
     */
    public static Controller defaultController() {
        return controller == null ? controller = new Controller() : controller;
    }

    /**
     * 构造方法
     */
    public Controller() {
        activityList = new ArrayList<>();
        createAsyncObject();
    }

    /**
     * 初始化对象
     */
    private void createAsyncObject() {
    }

    /**
     * 添加activity到集合中
     *
     * @param activity 当前activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 记录当前Activity
     *
     * @param currentActivity 当前activity
     */
    public static void setCurrentActivity(Activity currentActivity) {
        Controller.currentActivity = currentActivity;
    }

    /**
     * 获取当前activity
     *
     * @return 当前activity
     */
    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    /**
     * 设置当前上下文
     *
     * @param context 当前上下文
     */
    public static void setContext(Context context) {
        Controller.context = context;
    }

    /**
     * 获取当前上下文
     *
     * @return 当前上下文
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 退出当前应用，关闭所有activity
     */
    public static void exitApp() {
        try {
            for (Activity activity : activityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
