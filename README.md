# Projekt: hausarbeit\_i143\_2024\_i22c\_c7

## Projektübersicht

Projekt `hausarbeit_i143_2024_i22c_c7` ist eine Java-basierte Anwendung. Sie ist für zelluläre Automaten entwickelt und wird auf GitLab gehostet. Das Projekt nutzt Spring Boot für das Framework und verwendet Maven für das Abhängigkeitsmanagement.

## Erste Schritte

### Voraussetzungen

Bevor Sie beginnen, stellen Sie sicher, dass Sie Folgendes installiert haben:

- [ Java Development Kit (JDK) 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) oder höher
- [IntelliJ IDEA](https://www.jetbrains.com/de-de/idea/) (empfohlene IDE für dieses Projekt)
- [Apache Maven](https://maven.apache.org/download.cgi) (für die mvn commands)

### Projekt in IntelliJ IDEA importieren

1. Öffnen Sie IntelliJ IDEA.
2. Wählen Sie `File` -> `New` -> `Project from Version Control` oder `Get from VCS`.
3. Wählen Sie Git und geben Sie die Repository-URL ein: `https://gitlab2.nordakademie.de/LarsNicht-I22/hausarbeit_i143_2024_i22c_c7.git`.
5. Klicken Sie auf `Clone`.
4. Loggen Sie sich mit Ihren Daten ein.
5. IntelliJ IDEA erkennt das Maven-Projekt automatisch und importiert es.

## Anwendung ausführen

Um die Anwendung auszuführen, haben Sie mehrere Möglichkeiten:

### Ausführen mit Maven

Verwenden Sie den folgenden Befehl, um die Anwendung direkt über die Befehlszeile auszuführen:

```sh
mvn spring-boot:run
```

### Ausführen mit IntelliJ IDEA

1. Navigieren Sie zu `src/main/java/de/nordakademie/zellulaere_automaten/ZellulaereAutomatenApplication.java`.
2. Rechtsklicken Sie auf die `ZellulaereAutomatenApplication`-Klasse.
3. Wählen Sie `Run 'ZellulaereAutomatenApplication.main()'`.

## Tests ausführen

Um die Tests auszuführen, haben Sie mehrere Möglichkeiten:

### Ausführen mit Maven

```sh
mvn test
```

### Ausführen mit IntelliJ IDEA

1. Navigieren Sie zu `src/test/java`.
2. Rechtsklicken Sie auf den `test`-Ordner.
3. Wählen Sie `Run 'All Tests'`.


## Projekt als .jar bauen

### Ausführen mit Maven

Um das Projekt als .jar Datei zu bauen, öffnen Sie das Terminal in IntelliJ IDEA und führen Sie den folgenden Befehl aus:

```sh
mvn clean install
```

Dieser Befehl kompiliert das Projekt als .jar Datei in den 'Target' Ordner und führt alle Tests aus.

### Ausführen mit IntelliJ IDEA

1. Wählen Sie `View` -> `Tool Windows` -> `Maven`.
2. Im Maven-Fenster, erweitern Sie das Projekt und navigieren Sie zu `Lifecycle`.
3. Doppelklicken Sie auf `install`, um den Build-Prozess zu starten.

### Ausführen der .jar Datei

1. Öffnen Sie die `Eingabeaufforderung` (CMD).
2. Navigieren Sie zum Speicherort der `.jar` Datei.
3. Geben Sie den folgenden Befehl ein (Wenn die .jar eine andere Version als 1.0 hat, muss der Command entsprechend dem neuen Namen angepasst werden).

```sh
java -jar zellulaere_automaten-1.0.jar
```

## Autoren und Lizenz

### Autoren

- Daria Stolarczyk
- Jannick Gottschalk
- Lars Nicht
- Viktoria Melnyk

## Lizenz

Dieses Projekt ist unter der MIT-Lizenz lizenziert. Weitere Informationen finden Sie in der `LICENSE`-Datei.

