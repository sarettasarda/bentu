import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinBridge.shared.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}