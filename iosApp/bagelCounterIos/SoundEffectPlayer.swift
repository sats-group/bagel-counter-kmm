import Foundation
import SignalRClient
import AVFoundation

class SoundEffectPlayer {
    private var bagelInPlayer: AVAudioPlayer?
    private var bagelOutPlayer: AVAudioPlayer?
    
    init() {
        bagelInPlayer = setupAudioPlayer(with: "BagelIn")
        bagelOutPlayer = setupAudioPlayer(with: "BagelOut")
    }
    
    @MainActor
    func bagelIn() {
        bagelInPlayer?.play()
    }
    
    @MainActor
    func bagelOut() {
        bagelOutPlayer?.play()
    }
    
    private func setupAudioPlayer(with resourceName: String) -> AVAudioPlayer? {
        guard let url = Bundle.main.url(forResource: resourceName, withExtension: "wav") else {
            print("Error: Unable to find \(resourceName).wav")
            return nil
        }
        return try? AVAudioPlayer(contentsOf: url)
    }
}
