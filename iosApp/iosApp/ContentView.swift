import SwiftUI
import shared
import Combine
import KMPNativeCoroutinesCombine

struct ContentView: View {
    @ObservedObject private(set) var viewModel: KmpNativeCoroutinesTest

    var body: some View {
        Text(viewModel.text)
    }
}

extension ContentView {
    class ViewModel: ObservableObject {
        @Published var text = "Loading..."
        init() {
            GreetingHelper().getColorModels { greeting, error in
                DispatchQueue.main.async {
                    if let greeting = greeting {
                        self.text = greeting[0].name
                    } else {
                        self.text = error?.localizedDescription ?? "error"
                    }
                }
            }
        }
    }
}

class KmpNativeCoroutinesTest: ObservableObject {
    let helper: GreetingHelper = GreetingHelper()
    @Published private(set) var text: String = "Loading models"
    private var cancellables = Set<AnyCancellable>()

    init() {
        let future = createFuture(for: helper.getColorModelsNative())
        createFuture(for: helper.getColorModelsNative())
            // Update the UI on the main thread
            .receive(on: DispatchQueue.main)
            .sink { [weak self] completion in
                if case let .failure(error) = completion {
                    self?.text = error.localizedDescription
                }
            } receiveValue: { [weak self] word in
                self?.text = word.description
            }.store(in: &cancellables)

    }
}
