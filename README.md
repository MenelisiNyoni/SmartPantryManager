# Smart Pantry Manager

A Java Android Application that suggests recipes based strictly on leftover ingredients to reduce food waste. Built as part of the Mobile App Development 700 practical assignment.

## Features
- **Pantry Management:** Full CRUD functionality to add, view, edit, and delete pantry items.
- **Strict-Matching Engine:** Suggests recipes only when 100% of required ingredients are present in the pantry in required quantities.
- **Pre-loaded Recipe Collection:** Seeded SQLite database containing 15 default recipes.
- **Settings:** Persistent preferences for expiration alerts using `SharedPreferences`.

## Database
This project uses **SQLite (`SQLiteOpenHelper`)** for local, zero-latency on-device data persistence.

## How to Run
1. Clone this repository:
   `git clone https://github.com/MenelisiNyoni/SmartPantryManager.git`
2. Open the project in **Android Studio**.
3. Build and run on an Android Emulator or connected physical device (API Level 21+).