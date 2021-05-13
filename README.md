# TodoPractice
Ez a Todo app vue js.-ben készült a todomvc vue js. tutorial felhasználásával. Ez csak a gyakorlást szolgálja.

Végső alkalmazás eléréséhez a todosapira és vuefrontendre van szükségünk.

### Előkászületek

- Az alkamzás elindításához szükség van a Node.js-re.
- Java 8
- Visual Studio Code ajánlot
- Intellij ajánlott


Az elinditáshoz telepíteni kell az alkalmazást, amelyet az `npm install` parancsal tehetünk meg.

Telepítés után az `npm run serve` paracsal indítható.

Az alkalmazás a http://localhost:8081 indul el.

Az alkamazás backendje egy spring boot applikáció, melynek a build toolja a gradle(todosapi).

A frontend pedig egy vue.js alkalmazás(vuefrontend).

Az alkamazás használ még egy MySQL servert, amely a perzisztenciáért felelős.