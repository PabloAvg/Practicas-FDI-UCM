var canvas = document.getElementById('game');
var ctx = canvas.getContext && canvas.getContext('2d');
if(!ctx) {
    // No 2d context available, let the user know
    alert('Please upgrade your browser');
    } else {
        startGame();
}

function startGame() {
    ctx.fillStyle = "#FFFF00";
    ctx.fillRect(50,100,380,400);
    ctx.fillStyle = "rgba(0,0,128,0.5)";
    ctx.fillRect(25,50,380,400);
}

var img = new Image();

img.onload = function() {
    ctx.drawImage(img,100,100);
}

img.src = 'images/sprites.png';

ctx.drawImage(img,0,0,40,40,100,100,40,40);

SpriteSheet.load({
    ship: {sx:0, sy:0, w:38, h:43, frames:3}
    }, function(){
    SpriteSheet.draw(ctx, "ship", 0,0);
    SpriteSheet.draw(ctx, "ship", 100,50);
    SpriteSheet.draw(ctx, "ship", 150,100,1);
});

var SpriteSheet = new function() {
    this.map = { };
    this.load = function(spriteData, callback) {
    this.map = spriteData;
    this.image = new Image();
    this.image.onload = callback;
    this.image.src = 'images/sprites.png';
    };
    this.draw = function(ctx,sprite,x,y,frame) {
    var s = this.map[sprite];
    if(!frame) frame = 0;
    ctx.drawImage(this.image,
    s.sx + frame * s.w,
    s.sy,
    s.w, s.h,
    x, y,
    s.w, s.h);
    };
    }