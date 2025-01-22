# Persistenz-und-Datenbank-Anbindung
**Name:** Josef Joseph<br>
**Matrikelnummer:** 219650<br>

Installationsanleitung:<br>
Um die ToDo-App zu installieren, benötigst du Android Studio mit den folgenden Einstellungen: compileSdk = 35, minSdk = 26 und targetSdk = 34. 
Du solltest außerdem ein Android-Gerät oder einen Emulator zur Verfügung haben, auf dem die App getestet werden kann 
(die App wurde auf einem Pixel 8 Pro mit API 29 getestet).
Zuerst musst du den Quellcode des Projekts herunterladen oder das Repository von GitHub klonen: https://github.com/simonkllr/ToDoApp-DB.git.
Achte darauf, dass alle erforderlichen Abhängigkeiten in der build.gradle-Datei korrekt definiert sind. Danach synchronisiere das Projekt mit Gradle,
indem du in Android Studio den Menüpunkt File > Sync Project with Gradle Files auswählst.
Sobald das Projekt synchronisiert ist, wählst du ein Zielgerät aus (entweder einen Emulator oder ein physisches Android-Gerät) und
kannst die App durch Klicken auf den "Run"-Button in Android Studio starten.

Funktionsbeschreibung:<br>
Das Aufgabenmanagement ermöglicht es, neue Aufgaben hinzuzufügen. Hierfür wird ein Dialog geöffnet, in dem du den Namen, die Beschreibung,
die Priorität und die Deadline der Aufgabe angeben kannst. Bestehende Aufgaben können bearbeitet werden, oder dauerhaft gelöscht werden.
Für die Entwicklung wurde die Programmiersprache Kotlin verwendet. Die App setzt auf moderne Technologien wie Jetpack Compose für die Benutzeroberfläche und
SQLite zur Speicherung der Aufgaben. Das Design orientiert sich an Material Design 3. Die Architektur der App folgt dem MVC-Modell (Model-View-Controller).
Das Model umfasst die ToDo-Datenklasse sowie den ToDoDbHelper zur Verwaltung der SQLite-Datenbank. Die View besteht aus den UI-Komponenten wie ToDoScreen und
DashboardScreen, während der Controller durch den ToDoController die Datenbankoperationen übernimmt. Das Build-Management erfolgt über Gradle.

bekannte Probleme:<br>
-konnte DateTimePicker irgendwie nicht umsetzen. Egal was ich versuchte, es tauchte einfach nicht auf.<br>
-Bei der priority wollte ich so implementieren das man zwischen Low, Medium und High entscheiden können würde, aber auch das tauchte nicht auf.<br>
-Eine weitere Activity für erledigte Aufgaben habe ich nicht implementiert, weil mir die Zeit gefehlt hat. Bin mit lernen beschäftigt.<br>
