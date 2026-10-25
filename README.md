# Challenge Mod (Fabric 1.21 / 1.21.1)

Multiplayer-Challenge-Mod mit Simple-Voice-Chat-Anbindung.

## Befehle (nur Operatoren, Permission-Level 2)

| Befehl | Wirkung |
|---|---|
| `/blind <spieler>` | Alle Bloecke schwarz mit weissen Konturen (nur beim Spieler), HUD unveraendert, dazu Blindness + Darkness (unendlich) und kleiner Sichtradius |
| `/unblind <spieler>` | Macht `/blind` rueckgaengig |
| `/mute <spieler>` | Spieler wird im Voicechat stumm geschaltet (hoert aber weiter) |
| `/unmute <spieler>` | Macht `/mute` rueckgaengig |
| `/deaf <spieler>` | Spieler hoert im Voicechat nichts (kann aber sprechen) |
| `/undeaf <spieler>` | Macht `/deaf` rueckgaengig |
| `/challange start` | Startet einen Timer in der Action-Bar ueber der Hotbar aller Spieler |
| `/challange stop` | Stoppt den Timer |
| `/challange status` | Zeigt, ob das Voicechat-Plugin geladen ist, wer Blind/Mute/Deaf ist und wie viele Pakete blockiert wurden |
| `/blindradius [1-20]` | Sichtradius bei Blind in Bloecken (Standard 2.5, Vanilla-Blindness 5). Ohne Zahl: aktuellen Wert anzeigen |

`/challenge ...` (korrekte Schreibweise) funktioniert ebenfalls.

Zusaetzlich zeigt ein kleines HUD oben links dem Spieler selbst seine Rollen (z.B. "Rolle: Blind, Mute").

Hinter dem Namen ueber dem Kopf steht die Rolle, z.B. `Steve (Blind, Mute)`.
Das laeuft ueber Scoreboard-Teams mit Namen `cc_*` - die Tags erscheinen auch in Tab-Liste und Chat.

## Installation

Die **eine JAR** kommt in den `mods`-Ordner von **Server UND allen Spielern**.
Ausserdem noetig: Fabric Loader, **Fabric API** und **Simple Voice Chat** - **auf dem Server UND bei allen Spielern**
(gleiche Version). Mute/Deaf laufen ueber ein Plugin im Simple Voice Chat auf dem Server.
Mit `/challange status` pruefst du, ob das Plugin geladen wurde.

## JAR bauen

### Variante A: ohne Installation (GitHub)
1. Neues Repository auf github.com anlegen und den kompletten Inhalt dieses Ordners hochladen.
2. Reiter **Actions** oeffnen, der Workflow "Build JAR" laeuft automatisch (ca. 3 Min.).
3. Fertigen Lauf anklicken, unten unter **Artifacts** `challengemod-jar` herunterladen, ZIP entpacken - fertig.

### Variante B: lokal
JDK 21 installieren, dann im Projektordner `./gradlew build` (Windows: `gradlew.bat build`).
Die JAR liegt danach in `build/libs/challengemod-1.2.0.jar`.

## Hinweise
- Zustand (wer ist blind/mute/deaf) liegt im Speicher und geht bei einem Server-Neustart verloren.
- Blind-Texturen: Beim Umschalten laedt der Client kurz die Ressourcen neu (ein Moment Ladebildschirm).
  Betroffen sind alle Block-Texturen, also auch Block-Icons im Inventar; normale Items, HUD und Hotbar nicht.
- Ist ein Spieler schon in einem Scoreboard-Team, wird er fuer die Dauer der Rolle in das Rollen-Team verschoben
  und danach zurueckgesetzt.
