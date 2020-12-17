package org.euxx.mlkit.test.ui;

import com.google.mlkit.mlkittextrecognition.MLKText;
import com.google.mlkit.mlkittextrecognition.MLKTextRecognizer;
import com.google.mlkit.mlkitvision.MLKVisionImage;

import org.moe.natj.general.Pointer;
import org.moe.natj.general.ann.Owned;
import org.moe.natj.general.ann.RegisterOnStartup;
import org.moe.natj.objc.ObjCRuntime;
import org.moe.natj.objc.ann.IBOutlet;
import org.moe.natj.objc.ann.ObjCClassName;
import org.moe.natj.objc.ann.Property;
import org.moe.natj.objc.ann.Selector;

import apple.NSObject;
import apple.c.Globals;
import apple.coremedia.opaque.CMSampleBufferRef;
import apple.foundation.NSArray;
import apple.foundation.NSError;
import apple.foundation.NSMutableArray;
import apple.foundation.NSMutableDictionary;
import apple.uikit.NSLayoutConstraint;
import apple.uikit.UIButton;
import apple.uikit.UIColor;
import apple.uikit.UIImage;
import apple.uikit.UILabel;
import apple.uikit.UITextView;
import apple.uikit.UIViewController;

@org.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("AppViewController")
@RegisterOnStartup
public class AppViewController extends UIViewController
    implements MLKTextRecognizer.Block_processImageCompletion {

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

        UIImage img = UIImage.imageNamed("image_has_text.jpg");

        MLKVisionImage image = MLKVisionImage.alloc().initWithImage(img);
        image.setOrientation(img.imageOrientation());

        MLKTextRecognizer recognizer = MLKTextRecognizer.alloc().init();
        recognizer.processImageCompletion(image, this);
    }

    @Override
    public void call_processImageCompletion(MLKText text, NSError error) {
        String msg = text.text()
            + "\n\n"
            + error.userInfo().toString();
        System.err.println(msg);
        update(msg);
    }

    private void update(String msg) {
        Globals.dispatch_sync(Globals.dispatch_get_main_queue(),
            new Globals.Block_dispatch_sync() {
                @Override
                public void call_dispatch_sync() {
                    textView().setText(msg);
                }
        });
    }
}
