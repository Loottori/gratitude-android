# Grateful – Android

A calm daily gratitude journal for Android, built with Kotlin and Jetpack Compose.
Every day, write down a few things you're grateful for, read a Bible verse and an
encouraging thought fetched from the web, and watch your streak grow.

This is the Android version of my web app [Grateful](https://loottori.github.io/gratitude-app/).

## Screenshots

| Today | History | Verses | Thoughts |
|---|---|---|---|
| <img src="C:\Projektit\Mobiiliohjelmointi\Grateful\screenshots\today.png" width="200"/> | <img src="C:\Projektit\Mobiiliohjelmointi\Grateful\screenshots\history.png" width="200"/> | <img src="C:\Projektit\Mobiiliohjelmointi\Grateful\screenshots\verses.png" width="200"/> | <img src="C:\Projektit\Mobiiliohjelmointi\Grateful\screenshots\thoughts.png" width="200"/> |

## Features

- **Daily gratitude journal** – write as many things as you like, one line at a time
- **Streak tracking** – a small flower opens one petal for each day in a row (up to six)
- **Past entries** – every saved day, newest first
- **Verse and thought of the day** – fetched from a REST API, the same item for the whole day
- **Adaptive navigation** – bottom bar on phones, side rail on wide screens and in landscape
- **Error handling** – loading indicator, error message and a "Try again" button when offline
- **Local storage** – journal entries are saved on the device, no account needed
- **Soft pastel design** – colors, fonts and the leaf logo match the web version

## REST API

The app fetches its daily content with two GET requests to JSON files that I maintain
myself and host on GitHub Pages:

| Data | URL |
|---|---|
| Bible verses | https://loottori.github.io/gratitude-data/verses.json |
| Encouraging thoughts | https://loottori.github.io/gratitude-data/thoughts.json |

Example item:

```json
{ "id": 1, "text": "This is the day which the LORD hath made; we will rejoice and be glad in it.", "reference": "Psalm 118:24" }
```

New verses and thoughts can be added by editing the JSON files – no app update needed.

## Tech stack

- **Kotlin** and **Jetpack Compose** (Material 3)
- **Retrofit** + **Gson** for the REST API
- **ViewModel**, **StateFlow** and **coroutines** (MVVM architecture)
- **NavigationSuiteScaffold** for adaptive navigation
- Minimum Android version: 8.0 (API 26)

## Project structure

```
app/src/main/java/com/loottori/grateful/
├── MainActivity.kt          # App entry point and adaptive navigation
├── data/
│   ├── local/               # EntryStorage: saves journal entries as JSON on the device
│   ├── model/               # Verse, Thought, JournalEntry
│   ├── remote/              # Retrofit: ApiService, RetrofitClient
│   └── repository/          # ContentRepository (REST API), JournalRepository (journal)
└── ui/
    ├── components/          # Shared UI: cards, streak flower, header, background glow
    ├── today/               # Gratitude journal (home screen)
    ├── history/             # Past entries
    ├── verses/              # Bible verses from the REST API
    ├── thoughts/            # Encouraging thoughts from the REST API
    └── theme/               # Colors, fonts and shapes
```

## Running the project


## Running the project

1. Clone the repository and open it in Android Studio.
2. Let Gradle sync finish.
3. Run the app on an emulator or a device with internet access.

## Credits

- Bible verses: King James Version (public domain)
- Fonts: [Fraunces](https://fonts.google.com/specimen/Fraunces) and
  [Instrument Sans](https://fonts.google.com/specimen/Instrument+Sans), SIL Open Font License
- Design based on my own web app [Grateful](https://github.com/loottori/gratitude-app)

Made as a project for a mobile programming course.