package org.euxx.mlkit.test.ui;

import org.moe.natj.general.Pointer;
import org.moe.natj.general.ann.Owned;
import org.moe.natj.general.ann.RegisterOnStartup;
import org.moe.natj.objc.ObjCRuntime;
import org.moe.natj.objc.ann.IBOutlet;
import org.moe.natj.objc.ann.ObjCClassName;
import org.moe.natj.objc.ann.Property;
import org.moe.natj.objc.ann.Selector;

import apple.NSObject;
import apple.foundation.NSArray;
import apple.foundation.NSMutableArray;
import apple.foundation.NSMutableDictionary;
import apple.uikit.NSLayoutConstraint;
import apple.uikit.UIButton;
import apple.uikit.UIColor;
import apple.uikit.UILabel;
import apple.uikit.UITextView;
import apple.uikit.UIViewController;

@org.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("AppViewController")
@RegisterOnStartup
public class AppViewController extends UIViewController {

    @Owned
    @Selector("alloc")
    public static native AppViewController alloc();

    @Selector("init")
    public native AppViewController init();

    protected AppViewController(Pointer peer) {
        super(peer);
    }

    @Selector("textView")
    @Property
    @IBOutlet
    public native UITextView textView();

    @Selector("setTextView:")
    public native void setTextView(UITextView value);


    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        textView().setText("Hi there!");
    }
}
