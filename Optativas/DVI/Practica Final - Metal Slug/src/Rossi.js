function add_Rossi(Q) {

	// ANIMACIONES DEL TORSO

	Q.animations("rossi_torso", {
		chest_stand_right: {
			frames: [0,1,2,3],
			rate: 1 / 7,
			flip: false,
			loop: true
		},

		chest_stand_left: {
			frames: [0,1,2,3],
			rate: 1 / 7,
			flip: "x",
			loop: true
		},

		before_chest_shoot_gun_right: {
			frames: [0,1,2,3,4],
			rate: 1 / 20,
			flip: false,
			loop: false,
			trigger: "gunShooting"
		},
		after_chest_shoot_gun_right: {
			frames: [5,6,7,8,9],
			rate: 1 / 20,
			flip: false,
			loop: false
		},
		before_chest_shoot_gun_left: {
			frames: [0,1,2,3,4],
			rate: 1 / 20,
			flip: "x",
			loop: false,
			trigger: "gunShooting"
		},
		after_chest_shoot_gun_left: {
			frames: [5,6,7,8,9],
			rate: 1 / 20,
			flip: "x",
			loop: false
		},
	});

	// ANIMACIONES DE LAS PIERNAS

	Q.animations("rossi_legs", {
		legs_stand_right: {
			frames: [0,1,2,3],
			rate: 1 / 7,
			flip: false,
			loop: true
		},
		legs_stand_left: {
			frames: [0,1,2,3],
			rate: 1 / 7,
			flip: "x",
			loop: true
		},
		walk_right: {
			frames: [4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22],
			rate: 1 / 15,
			flip: false,
			loop: true
		},
		walk_left: {
			frames: [4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22],
			rate: 1 / 15,
			flip: "x",
			loop: true
		},
		jump_right: {
			frames: [4,5,6,7,8,9,10,11,12,13,14,15],
			rate: 1 / 15,
			flip: false,
			loop: true
		},
		jump_left: {
			frames: [4,5,6,7,8,9,10,11,12,13,14,15],
			rate: 1 / 15,
			flip: "x",
			loop: true
		}
	});

	const directions = {
		up: 0,
		right: 1,
		down: 2,
		left: 3
	};

	// PIERNAS DE ROSSI

	Q.Sprite.extend("RossiLegs", {

		init: function(p) {
			this._super(p,{
				sheet: "piernas_quietas",
				sprite: "rossi_legs",
				x: 50,
				y: 200,
				frame: 0,
				scale: 1,
				move: true,
				type: Q.SPRITE_DEFAULT,
				direction: directions.right
			});
			this.add("2d, platformerControls, animation, tween");
			this.p.jumpSpeed = -350;
		},

		step: function(dt){
			//ANDANDO
			if(this.p.vx > 0){
				this.p.sheet = "movimiento"
				this.play("walk_right");
				this.lookback = false;
			}
			else if(this.p.vx < 0)
			{
				this.p.sheet = "movimiento"
				this.play("walk_left");
				this.lookback = true;
			}
			
			//SALTANDO
			if(this.p.vy < 0) {
				this.p.sheet = "salto"
				if(this.lookback){this.play("jump_left");}
				else{this.play("jump_right");}
			} else{ this.p.salto = false;}

			if(this.p.vx == 0 && this.p.vy == 0){
				
				this.p.sheet = "piernas_quietas"

				if(this.lookback) this.play(`legs_stand_left`)
				else this.play(`legs_stand_right`)
			}
		},

		die: function() {
			
			if(Q.state.get("lives") >= 0){
				Q.state.dec("lives", 1);
			}
            if (Q.state.get("lives") < 0) {
				Q.audio.stop();
                //Q.audio.play("music_die.mp3"); 
				this.p.move = false;
				this.stage.unfollow();
				this.del('2d, platformerControls');
				//this.play("morir");
				this.animate({y: this.p.y-100}, 0.4, Q.Easing.Linear, {callback: this.disappear});
                Q.stageScene("endMenu", 2, { label: "You lose!" });
            }
    
        },

		disappear: function(){
			this.animate({y: this.p.y+800}, 1, Q.Easing.Linear, {callback: this.Dead });
		},

		Dead: function(){
			this.destroy();
		}
	});


	// TORSO DE ROSSI

	Q.Sprite.extend("RossiChest", {
		init: function(p) {
			this._super(p,{
				sheet: "normal",
				sprite: "rossi_torso",
				x: 50,
				y: 100,
				frame: 0,
				scale: 1,
				type: Q.SPRITE_NONE,
				projectileSpeed: 500,
			});
			this.add("animation, tween, platformerControls");
			Q.input.on("fire", this, "attackAction");
			Q.input.on("right", this, function(){ this.p.direction = directions.right});
			Q.input.on("left", this, function(){ this.p.direction = directions.left});
			this.on("gunShooting", this, "gunShooting");
		},
		step: function(dt){
			let legs = Q("RossiLegs");
			if(legs.length > 0){
				legs = legs.items[0];

				if(legs.lookback) {
					this.p.y = legs.p.y;
					this.p.x = legs.p.x-3;
					this.play("chest_stand_left")
				}
				else {
					this.p.y = legs.p.y;
					this.p.x = legs.p.x+3;
					this.play("chest_stand_right")
				}
			}
		},
		attackAction: function(){
			this.p.sheet = "disparo";
			this.size(true);

			let legs = Q("RossiLegs");
			legs = legs.items[0];

			if(legs.lookback) {
				this.p.y = legs.p.y;
				this.p.x = legs.p.x-3;
				this.play("before_chest_shoot_gun_left",200)
			}
			else {
				this.p.y = legs.p.y;
				this.p.x = legs.p.x+3;
				this.play("before_chest_shoot_gun_right",200)
			}

		},

		gunShooting: function(){
			let offset = 0;
			let speed = 0;

			let legs = Q("RossiLegs");
			legs = legs.items[0];
			
			if (!legs.lookback){
				offset = legs.p.w;
				speed = this.p.projectileSpeed;
				this.play("after_chest_shoot_gun_right",200)
			}
			else{
				offset = legs.p.w * -1;
				speed = -this.p.projectileSpeed;
				this.play("after_chest_shoot_gun_left",200)
			}
			this.stage.insert(new Q.gunProjectile({
				x: this.p.x + offset,
				y: this.p.y - 2,
				vx: speed
			}));
			
		}
	})


	// PROYECTIL DE PISTOLA

	Q.Sprite.extend("gunProjectile", {
        init: function(p) {
            this._super(p, {
                asset: "enemy_bullet.png",
                x: p.x,
                y: p.y,
                vx: p.vx,
                gravity: 0
            });
            this.add("2d");
            this.on("hit", function(collision){
                if(collision.obj.isA("RifleSoldier")){
                    collision.obj.takeDamage(1);
                    this.destroy();    
                }
                if (!collision.obj.isA("gunProjectile") && 
                !collision.obj.isA("TileLayer") && 
                !collision.obj.isA("testProjectile") &&
                !collision.obj.isA("Prisoner")){
                    Q.state.inc("score", 100);
                }

                this.destroy();

                /*else if (!collision.obj.isA("testProjectile")){
                    this.destroy();    
                }
                else{
                    let legs = Q("RossiLegs");
                    legs = legs.items[0];

                    let chest = Q("RossiChest");
                    chest = chest.items[0];

                    if (!legs.lookback){
                        speed = chest.p.projectileSpeed;
                    }
                    else{
                        speed = -chest.p.projectileSpeed;
                    }
                }*/
            });
        }
    })

}

