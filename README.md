# Projektdokumentation: hausarbeit\_i143\_2024\_i22c\_c7

## Projektübersicht

Projekt `hausarbeit_i143_2024_i22c_c7` ist eine Java-basierte Anwendung. Sie ist für zelluläre Automaten entwickelt und wird auf GitLab gehostet. Das Projekt nutzt Spring Boot für das Framework und verwendet Maven für das Abhängigkeitsmanagement.

## Erste Schritte

Um mit diesem Projekt zu beginnen, folgen Sie den unten stehenden Anweisungen.

### Voraussetzungen

Bevor Sie beginnen, stellen Sie sicher, dass Sie Folgendes installiert haben:

- Java Development Kit (JDK) 21 oder höher
- IntelliJ IDEA (empfohlene IDE für dieses Projekt)

### Repository klonen

Klonen Sie zuerst das Repository von GitLab mit den folgenden Befehlen:

```sh
cd existing_repo
git remote add origin https://gitlab2.nordakademie.de/LarsNicht-I22/hausarbeit_i143_2024_i22c_c7.git
git branch -M main
git push -uf origin main
```

### Projekt in IntelliJ IDEA importieren

1. Öffnen Sie IntelliJ IDEA.
2. Wählen Sie `File` -> `New` -> `Project from Version Control`.
3. Wählen Sie Git und geben Sie die Repository-URL ein: `https://gitlab2.nordakademie.de/LarsNicht-I22/hausarbeit_i143_2024_i22c_c7.git`.
4. Klicken Sie auf `Clone`.
5. IntelliJ IDEA erkennt das Maven-Projekt automatisch und importiert es.

### Projekt bauen

Um das Projekt zu bauen, öffnen Sie das Terminal in IntelliJ IDEA und führen Sie den folgenden Befehl aus:

```sh
mvn clean install
```

Dieser Befehl kompiliert das Projekt und führt alle Tests aus.

## Anwendung ausführen

Um die Anwendung auszuführen, haben Sie mehrere Möglichkeiten:

### Ausführen mit Maven

Verwenden Sie den folgenden Befehl, um die Anwendung direkt über die Befehlszeile auszuführen:

```sh
mvn spring-boot:run
```

### Ausführen mit IntelliJ IDEA

1. Navigieren Sie zu `src/main/java/de/nordakademie/zellulaere_automaten/Application.java`.
2. Rechtsklicken Sie auf die `Application`-Klasse.
3. Wählen Sie `Run 'Application.main()'`.

## Verwendung

### Befehlszeile

Sie können die Anwendung auch über die Befehlszeile mit folgendem Befehl starten:

```sh
mvn spring-boot:run
```

### Beispielverwendung

TODO

## Tests

Um die Tests auszuführen, verwenden Sie den folgenden Befehl:

```sh
mvn test
```

## Autoren und Anerkennung

### Autoren

- Daria Stolarczyk - I22
- Jannick Gottschalk - I22
- Lars Nicht - I22
- Viktoria Melnyk - I22

## Lizenz

Dieses Projekt ist unter der MIT-Lizenz lizenziert. Weitere Informationen finden Sie in der `LICENSE`-Datei.

