# PetStore App

A Java console application for managing a pet store inventory. Supports adding, deleting, displaying, saving, and loading animal records (Fish and Birds) with persistent file storage.

---

## Project Structure

```
src/
└── petstore/
    ├── app/
    │   ├── PetStoreApp.java   # Main application class and menu logic
    │   └── Input.java         # Console input utility (validation helpers)
    └── inventory/
        ├── Animal.java        # Base class for all animals
        ├── Fish.java          # Fish subclass
        ├── Bird.java          # Bird subclass
        ├── WaterType.java     # Enum: SALT, FRESH, BOTH
        └── NestType.java      # Enum: BORROW, CUP, DOME
```

---

<img src="https://github.com/Starksood/petstoreapp4/blob/main/Screenshot%202026-04-05%20at%2018.27.47.png">

---

## Requirements

- Java 11 or higher
- No external dependencies

---

## Compiling

From the project root:

```bash
javac -d out -sourcepath src \
  src/petstore/app/PetStoreApp.java \
  src/petstore/app/Input.java \
  src/petstore/inventory/Animal.java \
  src/petstore/inventory/Bird.java \
  src/petstore/inventory/Fish.java \
  src/petstore/inventory/NestType.java \
  src/petstore/inventory/WaterType.java
```

---

## Running

```bash
java -cp out petstore.app.PetStoreApp
```

---

## Features

| Option | Description |
|--------|-------------|
| 1 | Add a new Fish or Bird to the inventory |
| 2 | Delete an animal by its ID |
| 3 | Display all Fish and Bird inventory |
| 4 | Save inventory to `PetStoreData.txt` |
| 5 | Load inventory from `PetStoreData.txt` |
| 0 | Exit the application |

---

## Data Model

### Animal (base)
| Field | Type | Description |
|-------|------|-------------|
| id | int | Auto-incremented unique ID |
| name | String | Animal name |
| dateDOB | LocalDate | Date of birth (MM-DD-YYYY) |
| description | String | Optional description |

### Fish (extends Animal)
| Field | Type | Description |
|-------|------|-------------|
| finCount | int | Number of fins |
| waterType | WaterType | `SALT`, `FRESH`, or `BOTH` |

### Bird (extends Animal)
| Field | Type | Description |
|-------|------|-------------|
| wingSpan | int | Wingspan in cm |
| nestType | NestType | `BORROW`, `CUP`, or `DOME` |

---

## Example Testing Output

### Application Startup

```
==================================================
Welcome to the PetStore App
==================================================
--------------------------------------------------
Main Menu
--------------------------------------------------
0 = End Program
1 = Add Animal
2 = Delete Animal
3 = Display Inventory
4 = Save Inventory
5 = Load Inventory
--------------------------------------------------
Menu Choice:
```

---

### Adding a Fish (Option 1)

```
Menu Choice: 1
--------------------------------------------------
Add Inventory
--------------------------------------------------
Please enter the following inventory information:
Name: Nemo
Date DOB (MM-DD-YYYY): 03-15-2022
Description or press enter to continue: Clownfish
Type 1=Fish, 2=Bird: 1
finCount: 6
WaterType 1=Salt, 2=Fresh, 3=Both: 1
Successful Add: Animal Id = 1, Name = Nemo, Date of Birth =3-15-2022
Press enter to continue...
```

---

### Adding a Bird (Option 1)

```
Menu Choice: 1
--------------------------------------------------
Add Inventory
--------------------------------------------------
Please enter the following inventory information:
Name: Tweety
Date DOB (MM-DD-YYYY): 06-01-2021
Description or press enter to continue: Yellow canary
Type 1=Fish, 2=Bird: 2
Wing Span: 25
NestType 1=Burrow, 2=Cup, 3=Dome: 2
Successful Add: Animal Id = 2, Name = Tweety, Date of Birth =6-1-2021
Press enter to continue...
```

---

### Displaying Inventory (Option 3)

```
Fish Inventory
--------------------------------------------------
ID  Name           Date Rec'd finCount          WaterType
--- --------------- ---------- --------------- ----------
1   Nemo            3-15-2022  6               SALT

Bird Inventory
--------------------------------------------------
ID  Name           Date Rec'd WingSpan      NestType
--- --------------- ---------- --------------- ----------
2   Tweety          6-1-2021   25              CUP

Press enter to continue...
```

---

### Deleting an Animal (Option 2)

```
Menu Choice: 2
Delete Inventory
--------------------------------------------------
Please enter the inventory id: 1
1
Successful Delete: Animal Id = 1, Name = Nemo, Date of Birth =3-15-2022
Press enter to continue...
```

Attempting to delete a non-existent ID:

```
Please enter the inventory id: 99
99
ERROR: Inventory ID:99 NOT found!
```

---

### Saving Inventory (Option 4)

```
Menu Choice: 4
Saving data! Please wait...
2 Inventory records successfully written to PetStoreData.txt
Please any key to continue...
```

The saved `PetStoreData.txt` file uses pipe-delimited format:

```
Fish|1|Nemo|3-15-2022|Clownfish|6|SALT
Bird|2|Tweety|6-1-2021|Yellow canary|25|CUP
```

---

### Loading Inventory (Option 5)

```
Menu Choice: 5
Loading data! Please wait...
2 Inventory records successfully loaded from PetStoreData.txt
Please any key to continue...
```

---

### Input Validation

Invalid menu choice:

```
Menu Choice: 9
Invalid input! Please enter a number between (0 - 5):
```

Invalid date format:

```
Date DOB (MM-DD-YYYY): 2022/03/15
Invalid input! Please enter a valid date (MM-D-YYYY):
```

Invalid integer input:

```
finCount: abc
Invalid input! Please enter a number:
```

---

### Exiting (Option 0)

```
Menu Choice: 0
```

The program closes the scanner and exits cleanly.

---

## Persistence

Inventory is saved to and loaded from `PetStoreData.txt` in the project root directory. The file uses a pipe-delimited (`|`) format:

```
<Type>|<id>|<name>|<dateDOB>|<description>|<finCount or wingSpan>|<WaterType or NestType>
```

The animal ID counter is restored from the last loaded record to prevent ID collisions after a reload.

---

## Authors

- Sanyam Sood
- Version 1.1 — March 29th, 2026
