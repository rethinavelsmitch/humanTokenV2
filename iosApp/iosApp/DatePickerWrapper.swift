//
//  DatePickerWrapper.swift
//  iosApp
//
//  Created by Puvi on 28/05/25.
//

import SwiftUI


struct DatePickerWrapper: View {
    @Environment(\.dismiss) private var dismiss
    
    let onDateSelected: (String) -> Void
    let onDismiss: () -> Void
    
    @State private var date: Date
    
    init(initialDateString: String,
         onDateSelected: @escaping (String) -> Void,
         onDismiss: @escaping () -> Void) {
        
        self.onDateSelected = onDateSelected
        self.onDismiss = onDismiss
        
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"
        _date = State(initialValue: formatter.date(from: initialDateString) ?? Date())
    }
    
    var body: some View {
        DatePicker(
            "",
            selection: $date,
            displayedComponents: .date
        )
        .datePickerStyle(.wheel)
        .labelsHidden()
        .padding()
        .onChange(of: date) { newDate in
            let formatter = DateFormatter()
            formatter.dateFormat = "dd/MM/yyyy" // Your desired output format
            let formattedDate = formatter.string(from: newDate)
            onDateSelected(formattedDate)
        }
        .onDisappear {
            onDismiss()
        }
    }
}

