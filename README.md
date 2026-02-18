# CamShop OCR

An Android app that lets you snap a photo of any product and instantly search for it online — no typing required. Powered by [Firebase ML Kit Text Recognition](https://firebase.google.com/docs/ml-kit/recognize-text).

## Screenshots

<p align="center">
  <img src="/images/1.png" width="160"/>
  <img src="/images/2.png" width="160"/>
  <img src="/images/3.png" width="160"/>
  <img src="/images/4.png" width="160"/>
  <img src="/images/5.png" width="160"/>
</p>

## How It Works

1. **Capture** — Take a photo of a product label or text using your device camera
2. **Detect** — Firebase ML Kit's on-device OCR extracts text from the image
3. **Search** — Tap the search button to find the product on Flipkart instantly

## Tech Stack

| Component | Technology |
|-----------|------------|
| Language | Kotlin |
| Min SDK | 16 (Android 4.1) |
| Target SDK | 28 (Android 9.0) |
| OCR Engine | Firebase ML Vision 17.0.0 |
| UI | Android Support Library, ConstraintLayout |
| Utilities | Anko Commons |

## Getting Started

### Prerequisites

- Android Studio 3.2+
- A Firebase project with ML Kit enabled

### Firebase Setup

1. Go to the [Firebase Console](https://console.firebase.google.com/) and create a new project (or use an existing one)
2. Add an Android app with the package name `com.tobibur.camshop`
3. Download the `google-services.json` file
4. Place it in the `app/` directory

### Build & Run

```bash
# Clone the repository
git clone https://github.com/Tobibur/CamShop-ocr.git
cd CamShop-ocr

# Build the debug APK
./gradlew assembleDebug

# Install on a connected device
./gradlew installDebug
```

> **Note:** A physical device with a camera is recommended. The emulator camera may not produce good OCR results.

## Project Structure

```
app/src/main/java/com/tobibur/camshop/
├── MainActivity.kt              # Entry point, hosts MainFragment
├── ResultActivity.kt            # Displays detected text + Flipkart search
└── ui/main/
    └── MainFragment.kt          # Camera capture + Firebase ML Kit OCR
```

## Roadmap

- [x] Camera capture
- [x] Text detection via Firebase ML Kit
- [x] Product search on Flipkart
- [ ] Improved UI/UX
- [ ] Support for multiple shopping websites

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

Copyright (c) 2018 Tobibur Rahman
