# ideal-lamp-row
Project 2 IKT205 - Tic Tac Toe

This application is a school assignment. With this application you can create a multiplayer game or join one.

This application is a tic tac toe app using a custom API provided by the teacher. The application communicates with the API. You can create multiplayer games and join multiplayer games. When a new game is created it does a post request to the api which returns a game with a gameId. When you join a game with a gameId, it does a another post request and gets the game in return. The games poll every 5 seconds to get updates and check if the other player in the game has don their turn. When a player puts a piece down, it does another post request and updates the game state. When a player gets three in a row it says player <name> won.

Faults with the app/ to improve:
- It is not possible to create a game, press the back button and join another. It will crash after some time. If you want to create a game and then join it you need to create it, stop the virtual device, and join a game using that gameId.
- Need to implement point system for when a player gets tree in a row.
- No win conditions. Need to make it says to restart if no one won the game.
- stability and ui

<img src="https://user-images.githubusercontent.com/75445926/118253521-4e896800-b4aa-11eb-9c32-be54d80791b6.png" width="400" height="700">
<img src="https://user-images.githubusercontent.com/75445926/118253554-5b0dc080-b4aa-11eb-9fd0-28d1c11dce4b.png" width="400" height="700">
<img src="https://user-images.githubusercontent.com/75445926/118253580-63fe9200-b4aa-11eb-92e9-576dc556b5dc.png" width="400" height="700">
