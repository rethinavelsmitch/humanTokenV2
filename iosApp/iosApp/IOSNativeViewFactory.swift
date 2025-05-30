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
    
    func showDatePicker(
        selectedDate: String,
        onDismiss: @escaping () -> Void,
        onDateSelected: @escaping (String) -> Void
    ) {
        let datePickerVC = UIHostingController(rootView: DatePickerWrapper(
            initialDateString: selectedDate,
            onDateSelected: onDateSelected,
            onDismiss: onDismiss
        ))
        
        datePickerVC.modalPresentationStyle = .pageSheet

        if let sheet = datePickerVC.sheetPresentationController {
            sheet.detents = [.medium()]
            sheet.prefersGrabberVisible = true
        }

        // ✅ Present the sheet on top of the current visible VC
        DispatchQueue.main.async {
            if let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene,
               let rootVC = windowScene.windows.first(where: { $0.isKeyWindow })?.rootViewController {
                
                var topVC = rootVC
                while let presented = topVC.presentedViewController {
                    topVC = presented
                }

                topVC.present(datePickerVC, animated: true)
            }
        }
    }

    func showAlertDialog(primaryText: String, secondaryText: String, onDismiss: @escaping () -> Void) {
        let alert = UIAlertController(
            title: primaryText,
            message: secondaryText,
            preferredStyle: .alert
        )

        let okAction = UIAlertAction(title: "OK", style: .default) { _ in
            print("OK tapped")
            onDismiss()
        }

        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel) { _ in
            print("Cancel tapped")
            onDismiss()
        }

        alert.addAction(okAction)
        alert.addAction(cancelAction)

        DispatchQueue.main.async {
            if let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene,
               let rootVC = windowScene.windows.first(where: { $0.isKeyWindow })?.rootViewController {

                var topVC = rootVC
                while let presented = topVC.presentedViewController {
                    topVC = presented
                }

                topVC.present(alert, animated: true)
            }
        }
    }

}



