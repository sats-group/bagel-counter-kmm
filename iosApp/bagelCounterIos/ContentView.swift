import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var viewModel = CounterViewModel()
    
    var body: some View {
        ZStack {
            Color.black.ignoresSafeArea()
            VStack(alignment: .center, spacing: 0) {
                Image("Bagel").resizable().frame(width: 120, height: 120)
                Text("Bagel counter")
                    .foregroundColor(.white).padding(.top, 24)
                    .font(.system(size: 42))
                Spacer()
                    .frame(height: 56)
                HStack(alignment: .center, spacing: 0) {
                    let counterText = if let counterValue = viewModel.counterValue {
                        "\(counterValue)"
                    } else {
                        "   "
                    }
                    Text(counterText)
                        .foregroundColor(.white).padding(.top, 24)
                        .font(.system(size: 57).bold())
                    Spacer()
                        .frame(width: 50)
                    Divider()
                        .frame(width: 2)
                        .overlay(Color(hex: "#C4C4C4"))
                    Spacer()
                        .frame(width: 46)
                    Button(
                        action: {
                            viewModel.decrementCounter()
                        }
                    ) {
                        Image("ArrowDown")
                            .resizable()
                            .foregroundColor(
                                .white.opacity(viewModel.isDecrementEnabled ? 1 : 0.3)
                            )
                            .frame(width: 32, height: 16)
                            .frame(width: 78, height: 78, alignment: .center)
                    }
                    .disabled(!viewModel.isDecrementEnabled)
                    Spacer()
                        .frame(width: 17)
                    Button(
                        action: {
                            viewModel.incrementCounter()
                        }
                    ) {
                        Image("ArrowUp")
                            .resizable()
                            .foregroundColor(
                                .white.opacity(viewModel.isIncrementEnabled ? 1 : 0.3)
                            )
                            .frame(width: 32, height: 16)
                            .frame(width: 78, height: 78, alignment: .center)
                    }
                    .disabled(!viewModel.isIncrementEnabled)
                }.frame(height: 125)
            }
        }.onAppear(perform: { viewModel.connect() })
    }
}

extension Color {
    init(hex: String) {
        var cleanHexCode = hex.trimmingCharacters(in: .whitespacesAndNewlines)
        cleanHexCode = cleanHexCode.replacingOccurrences(of: "#", with: "")
        var rgb: UInt64 = 0
        Scanner(string: cleanHexCode).scanHexInt64(&rgb)
        let redValue = Double((rgb >> 16) & 0xFF) / 255.0
        let greenValue = Double((rgb >> 8) & 0xFF) / 255.0
        let blueValue = Double(rgb & 0xFF) / 255.0
        self.init(red: redValue, green: greenValue, blue: blueValue)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
