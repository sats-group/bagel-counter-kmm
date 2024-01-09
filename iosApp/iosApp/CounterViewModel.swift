import Foundation
import SignalRClient

class CounterViewModel: ObservableObject {
    @MainActor @Published var counterValue = 0
    
    @MainActor var isDecrementEnabled: Bool {
        counterValue > 0
    }
    
    @MainActor var isIncrementEnabled: Bool {
        counterValue < 8
    }

    private var hubConnection: HubConnection

    init() {
        self.hubConnection = HubConnectionBuilder(
            url: URL(string: "https://sats-test-no-bagel.azurewebsites.net/api")!
        )
        .withLogging(minLogLevel: .info)
        .build()

        self.hubConnection.on(
            method: "UpdatedBagelCount",
            callback: { (newValue: Int) in
                DispatchQueue.main.async {
                    self.counterValue = newValue
                }
            }
        )

        self.hubConnection.start()
    }

    func incrementCounter() {
        self.hubConnection.invoke(
            method: "IncrementBagelCount",
            arguments: [],
            invocationDidComplete: {
                guard let error = $0 else { return }
                print(error)
            }
        )
    }

    func decrementCounter() {
        self.hubConnection.invoke(
            method: "DecrementBagelCount", 
            arguments: [],
            invocationDidComplete: {
                guard let error = $0 else { return }
                print(error)
            }
        )
    }
}
