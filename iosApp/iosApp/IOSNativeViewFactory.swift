//
//  IOSNativeViewFactory.swift
//  iosApp
//
//  Created by Puvi on 28/05/25.
//
import SwiftUI
import ComposeApp

class IOSNativeViewFactory:  NativeViewFactory {
    
    static var shared = IOSNativeViewFactory()
    
    func createButtonView(label: String, onClick: @escaping () -> Void) -> UIViewController {
        let view=SimpleIOSButton(label:label,action: onClick)
        return UIHostingController(rootView: view)
    }

}
