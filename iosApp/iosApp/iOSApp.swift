import SwiftUI
import shared

// swiftlint:disable type_name
@main
struct iOSApp: App {

    init() {
        KoinKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
            ContentView(viewModel: KmpNativeCoroutinesTest())
		}
	}
}
