//
//  SimpleIOSButtong.swift
//  iosApp
//
//  Created by Puvi on 28/05/25.
//
import SwiftUI


struct SimpleIOSButton :View{
    var label: String
    var action: () -> Void
    
    var body: some View {
        Button(action:action){
           Text(label)
        }
    }
}
