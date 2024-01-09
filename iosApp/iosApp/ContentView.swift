import SwiftUI
import shared
import AVFoundation

struct ContentView: View {
    @ObservedObject var viewModel = CounterViewModel()
    
    private var bagelInPlayer: AVAudioPlayer?
    private var bagelOutPlayer: AVAudioPlayer?
    
    init() {
        bagelInPlayer = setupAudioPlayer(with: "BagelIn")
        bagelOutPlayer = setupAudioPlayer(with: "BagelOut")
    }
    
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
                    Text("\(viewModel.counterValue)")
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
                            bagelOutPlayer?.play()
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
                            bagelInPlayer?.play()
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
        }
    }
    
    private func setupAudioPlayer(with resourceName: String) -> AVAudioPlayer? {
        guard let url = Bundle.main.url(forResource: resourceName, withExtension: "wav") else {
            print("Error: Unable to find \(resourceName).wav")
            return nil
        }
        return try? AVAudioPlayer(contentsOf: url)
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
