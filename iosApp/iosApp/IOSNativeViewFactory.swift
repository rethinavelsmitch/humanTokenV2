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
    
    func showDatePicker(selectedDate: String, onDismiss: @escaping () -> Void, onDateSelected: @escaping (String) -> Void) -> UIViewController {
        
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

           return datePickerVC


//       let view=DatePickerWrapper(initialDateString:selectedDate,onDateSelected:onDateSelected,onDismiss:onDismiss)
  //     return UIHostingController(rootView: view)
    }


}



