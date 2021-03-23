var startGame = function() {
    Game.setBoard(0,new TitleScreen("Alien Invasion", "Press fire to start playing",
    playGame));
}

var playGame = function() {
    var board = new GameBoard();
    board.add(new PlayerShip());
    board.add(new Enemy(enemies.basic));
    board.add(new Enemy(enemies.basic, { x: 200 }));
    Game.setBoard(0,board);
};
    
var sprites = { 
    ship_player: {sx:0, sy:0, w:37, h:40, frames:1},
    ship_purple_enemy: {sx:37, sy:0, w:42, h:43, frames:1},
    ship_orange_enemy: {sx:79, sy:0, w:37, h:43, frames:1},
    ship_grey_enemy: {sx:116, sy:0, w:42, h:43, frames:1},
    ship_green_enemy: {sx:158, sy:0, w:33, h:32, frames:1},
    player_missile: {sx:0, sy:42, w:7, h:20, frames:1},
    enemy_missile: {sx:9, sy:42, w:3, h:20, frames:1},
    explosion: {sx:0, sy:70, w:64, h:46, frames:12},
};

var enemies = {
    basic: { x: 100, y: -50, sprite: 'ship_purple_enemy',
    B: 100, C:3, E: 100, health: 20 }
};

// Indica que se llame al método de inicialización una vez
// se haya terminado de cargar la página HTML
// y este después de realizar la inicialización llamará a
// startGame
window.addEventListener("load", function() {
    Game.initialize("game",sprites,startGame);
});

/*var callback = function(){
        SpriteSheet.draw(ctx, "ship_player", 0,0,0);
        SpriteSheet.draw(ctx, "ship_purple_enemy", 0,50,0);
        SpriteSheet.draw(ctx, "ship_orange_enemy", 0,100,0);
        SpriteSheet.draw(ctx, "ship_grey_enemy", 0,150,0);
        SpriteSheet.draw(ctx, "ship_green_enemy", 0,200,0);
        SpriteSheet.draw(ctx, "player_missile", 0,250,0);
        SpriteSheet.draw(ctx, "enemy_missile", 0,300,0);
        var i = 11;
        timer = setInterval(function() {
            SpriteSheet.draw(ctx, "explosion", 0,350,i)
            i -= 1;
            if(i == -1){
                clearInterval(timer);
            }
          }, 16);
    }*/