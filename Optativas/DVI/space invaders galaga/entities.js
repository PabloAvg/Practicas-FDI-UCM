

///////////////////////////////
// SPRITE
///////////////////////////////
var Sprite = function() { }

Sprite.prototype.setup = function(sprite,props) {
    this.sprite = sprite;
    this.merge(props);
    this.frame = this.frame || 0;
    this.w = SpriteSheet.map[sprite].w;
    this.h = SpriteSheet.map[sprite].h;
}
Sprite.prototype.merge = function(props) {
    if(props) {
        for (var prop in props) {
            this[prop] = props[prop];
        }
    }
}

Sprite.prototype.draw = function(ctx) {
    SpriteSheet.draw(ctx,this.sprite,this.x,this.y,this.frame);
}

Sprite.prototype.hit = function(damage) {
    this.board.remove(this);
}

var OBJECT_PLAYER = 1,
    OBJECT_PLAYER_PROJECTILE = 2,
    OBJECT_ENEMY = 4,
    OBJECT_ENEMY_PROJECTILE = 8,
    OBJECT_POWERUP = 16;

///////////////////////////////
// JUGADOR
///////////////////////////////

var PlayerShip = function() {
    this.setup('ship_player', { vx: 0, frame: 0, reloadTime: 0.25, maxVel: 200 });

    this.w = SpriteSheet.map['ship_player'].w;
    this.h = SpriteSheet.map['ship_player'].h;
    this.x = Game.width/2 - this.w / 2;
    this.y = Game.height - 10 - this.h;
    this.vx = 0;
    this.maxVel = 200;

    this.reloadTime = 0.25; // un cuarto de segundo
    this.reload = this.reloadTime;

    
    this.step = function(dt) {
        if(Game.keys['left']) { this.vx = -this.maxVel; }
        else if(Game.keys['right']) { this.vx = this.maxVel; }
        else { this.vx = 0; }
        this.x += this.vx * dt;
        if(this.x < 0) { this.x = 0; }
        else if(this.x > Game.width - this.w) {
            this.x = Game.width - this.w
        }

        this.reload-=dt;
        if(Game.keys['fire'] && this.reload < 0) {
            Game.keys['fire'] = false;
            this.reload = this.reloadTime;

            this.board.add(new PlayerMissile(this.x,this.y+this.h/2));
            this.board.add(new PlayerMissile(this.x+this.w,this.y+this.h/2));
        }

    };
}

PlayerShip.prototype = new Sprite();
PlayerShip.prototype.type = OBJECT_PLAYER

///////////////////////////////
//PLAYER MISSILE
///////////////////////////////

//OK
var PlayerMissile = function(x,y) {
    this.setup('player_missile',{ vy: -700, damage: 10 });
    this.x = x - this.w/2;
    this.y = y - this.h;
};  

PlayerMissile.prototype = new Sprite();
PlayerMissile.prototype.type = OBJECT_PLAYER_PROJECTILE;

PlayerMissile.prototype.step = function(dt) {
    this.y += this.vy * dt;
    var collision = this.board.collide(this,OBJECT_ENEMY);
    if(collision) {
        collision.hit(this.damage);
        this.board.remove(this);
    } else if(this.y < -this.h) {
        this.board.remove(this);
    }
};

///////////////////////////////
//ENEMIGOS
///////////////////////////////

var Enemy = function(blueprint,override) {
    this.merge(this.baseParameters);
    this.setup(blueprint.sprite,blueprint);
    this.merge(override);
}

Enemy.prototype = new Sprite();
Enemy.prototype.type = OBJECT_ENEMY;

Enemy.prototype.baseParameters = { A: 0, B: 0, C: 0, D: 0, E: 0, F: 0, G: 0, H: 0, t: 0 }; 

Enemy.prototype.step = function(dt) {
    this.t += dt;
    this.vx = this.A + this.B * Math.sin(this.C * this.t + this.D);
    this.vy = this.E + this.F * Math.sin(this.G * this.t + this.H);
    this.x += this.vx * dt;
    this.y += this.vy * dt;
    if(this.y > Game.height || this.x < -this.w || this.x > Game.width) {
        this.board.remove(this);
    }
}

Enemy.prototype.hit = function(damage) {
    this.health -= damage;
    if(this.health <= 0)
    this.board.remove(this);
}
    

// NOS TOCA PÁGINA 25, Colisiones de los enemigos con el jugador