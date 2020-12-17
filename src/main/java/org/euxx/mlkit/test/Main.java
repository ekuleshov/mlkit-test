package org.euxx.mlkit.test;

import apple.NSObject;
import apple.foundation.NSBundle;
import apple.foundation.NSDictionary;
import apple.foundation.NSError;
import apple.uikit.ITargetAction;
import apple.uikit.UIApplication;
import apple.uikit.UIBarButtonItem;
import apple.uikit.UIButton;
import apple.uikit.UIColor;
import apple.uikit.UIImage;
import apple.uikit.UINavigationController;
import apple.uikit.UIScreen;
import apple.uikit.UIWindow;
import apple.uikit.c.UIKit;
import apple.uikit.enums.UIBarButtonItemStyle;
import apple.uikit.enums.UIBarStyle;
import apple.uikit.protocol.UIApplicationDelegate;

import org.euxx.mlkit.test.ui.AppViewController;
import org.moe.natj.general.Pointer;
import org.moe.natj.general.ann.RegisterOnStartup;
import org.moe.natj.objc.ann.Selector;

import static apple.foundation.c.Foundation.NSLocalizedString;

@RegisterOnStartup
public class Main extends NSObject implements UIApplicationDelegate {

    public static void main(String[] args) {
        try {
            UIKit.UIApplicationMain(0, null, null, Main.class.getName());
        } catch(Throwable ex) {
            ex.printStackTrace();
        }
    }

    @Selector("alloc")
    public static native Main alloc();

    protected Main(Pointer peer) {
        super(peer);
    }

    private UIWindow window;
    private AppViewController main;

    @Override
    public boolean applicationDidFinishLaunchingWithOptions(UIApplication application, NSDictionary launchOptions) {
        Thread.setDefaultUncaughtExceptionHandler(new MainExceptionHandler());

        main = AppViewController.alloc();
        main.initWithNibNameBundle("InfoController", NSBundle.mainBundle());
        main.setTitle("ML Kit Test");

        UINavigationController navigationController = UINavigationController.alloc().init();
        navigationController.navigationBar().setBarStyle(UIBarStyle.Black);
        navigationController.navigationBar().setTintColor(UIColor.whiteColor());
        navigationController.navigationBar().setBarTintColor(UIColor.lightGrayColor());
        navigationController.navigationBar().setShadowImage(UIImage.alloc().init());
        navigationController.navigationBar().setTranslucent(false);

        navigationController.initWithRootViewController(main);

        window = UIWindow.alloc().initWithFrame(UIScreen.mainScreen().bounds());
        window.setRootViewController(navigationController);
        window.makeKeyAndVisible();

        return true;
    }

    @Override
    public void setWindow(UIWindow value) {
        window = value;
    }

    @Override
    public UIWindow window() {
        return window;
    }

    static class MainExceptionHandler implements Thread.UncaughtExceptionHandler {
        private final Thread.UncaughtExceptionHandler defaultHandler;

        public MainExceptionHandler() {
            this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            e.printStackTrace();
            defaultHandler.uncaughtException(t, e);
        }
    }
}
