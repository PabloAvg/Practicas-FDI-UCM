
/**
 * Clase que representa a las cartas del juego
 * @param {String} sprite nombre de la carta (concide con los nombres
 * ya registrado de los sprites).
 */
var MemoryGameCard = function(sprite){

    this.myNombre = sprite;

    /**
     * 0 = Boca abajo (Back)
     * 1 = Boca arriba
     * 2 = Solved (resuelta)
     */
    this.status_carta = 0;

    /**
     * Da la vuelta a la carta, cambiando el estado de la misma.
     */
    this.flip = function(){

        if(this.status_carta == 0){
            this.status_carta = 1;
        }else if(this.status_carta == 1){
            this.status_carta = 0;
        }
        //Solved = Solved
    }

    /**
     * Marca una carta como encontrada, cambiando el estado de la misma.
     */
    this.found = function(){
        this.status_carta = 2;
    }

    /**
     * Compara dos cartas, devolviendotruesi ambas representan la misma carta.
     * @param {MemoryCardGame} otherCard carta con la que quieres comparar
     */
    this.compareTo = function(otherCard){
        return (this.myNombre == otherCard.myNombre);
    }

    /**
     * Dibuja la carta de acuerdo al estado en el que se encuentra.
     * Recibe como parámetros el servidor gráfico y la posición en
     * la que se encuentra enel array de cartas del juego 
     * (necesario para dibujar una carta).
     * @param {myCustomGraphicServer} gs custom grafic server asosciado
     * @param {int} pos posicion en el tablero
     */
    this.draw = function(gs, pos){

        //Si esta boca abajo, el status será 0 -> back
        if(this.status_carta == 0){
            gs.draw("back", pos);
        }else{
            //Pintamos la propia carta
            gs.draw(this.myNombre, pos);
        }
       
    }

}


/**
 * Clase representativa del juego de cartas
 * @param {myCustomGraphicServer} gs servidor gráfico
 */
var MemoryGame = function(gs){
    
    this.myCustomGraphicServer = gs;
    this.cartas = new Array();  //Array de las cartas del juego (2 de cada tipo)
    this.status= "Juego de memoria Spectrum" //Mensaje que se motrará en la parte superior de la pantalla
    this.card_selected = 0;
    this.first_selected = -1;
    this.second_selected = -1;
    this.parejas_encontradas = 0;

     /**
     * Inicializa el juego creando las cartas 
     * (recuerda que son 2 de cadatipo de carta),
     * desordenándolas y comenzando el bucle de juego.
     */
    this.initGame = function(){
        
        //Cargamos las cartas
        this.cartas[0] = new MemoryGameCard("perico");
        this.cartas[1] = new MemoryGameCard("perico");
        this.cartas[2] = new MemoryGameCard("mortadelo");
        this.cartas[3] = new MemoryGameCard("mortadelo");
        this.cartas[4] = new MemoryGameCard("fernandomartin");
        this.cartas[5] = new MemoryGameCard("fernandomartin");
        this.cartas[6] = new MemoryGameCard("sabotaje");
        this.cartas[7] = new MemoryGameCard("sabotaje");
        this.cartas[8] = new MemoryGameCard("phantomas");
        this.cartas[9] = new MemoryGameCard("phantomas");
        this.cartas[10] = new MemoryGameCard("poogaboo");
        this.cartas[11] = new MemoryGameCard("poogaboo");
        this.cartas[12] = new MemoryGameCard("sevilla");
        this.cartas[13] = new MemoryGameCard("sevilla");
        this.cartas[14] = new MemoryGameCard("abadia");
        this.cartas[15] = new MemoryGameCard("abadia");

        //Barajamos las cartas para que queden descolocadas dentro
        //del array
        this.cartas.sort( () => .5 - Math.random());
        this.loop();
        //this.draw();
    }

    /**
     * Dibuja el juego, esto es: (1) escribe el status con el 
     * estado actual del juego y (2) pide a cada una de las 
     * cartas del tablero que se dibujen.
     * 
     * + status:
     * - Juego memoria Spectrum
     * - Inténtalo de nuevo
     * - ¡Pareja encontrada!
     * - ¡Has ganado!
     */
    this.draw = function(){
        this.myCustomGraphicServer.drawMessage(this.status);   //Comunicamos el estado del juego
        for (var i = 0; i < 16; i++) {
            this.cartas[i].draw(this.myCustomGraphicServer,i);  //Llamamos a la funcion draw de cada carta
        }
    }

    /**
     * Es el bucle del juego. En este caso es muy sencillo:
     * llamamos al método draw cada 16ms (equivalente a unos 60fps).
     * Esto se realizará con la función setInterval de Javascript.
     */
    this.loop = function(){
        var that = this; 
        setInterval(function(){that.draw()}, 16); 
    }

    /* Método para voltear cartas y, en el caso de haberse volteado dos, comprobar si se ha encontrado la pareja.
    * @param {*} cardId Posición de la carta seleccionada {0...15}
    */
   this.onClick = function(cardId){

       if(  this.card_selected == 0                     //No hay mas cartas seleccionadas
            && this.cartas[cardId].status_carta != 1   //No estas tu misma levantada
            && this.cartas[cardId].status_carta != 2   //No estas solucionada ya
            ){ 
                this.card_selected = 1; //Ya hay 1 carta seleccionada
                this.first_selected = cardId; //Guardo la primera carta
                this.cartas[cardId].flip();  //La volteo
       }
      else if(
           this.card_selected == 1                      //Hay otra carta volteada
           && this.cartas[cardId].status_carta != 1    //No estas tu misma levantada
           && this.cartas[cardId].status_carta != 2    //No estas solucionada ya
       ){
            this.second_selected = cardId; //Guardo la segunda carta
            this.cartas[cardId].flip(); //La doy la vuelta

             
            //Para que se presente bien el juego y no selecciones
            //una segunda pareja hasta que acabe el setTimeout
            
            this.card_selected = -1; 

            //TODO Parte de duda: 
            carta1aux = this.first_selected;
            carta2aux = this.second_selected;
            var that = this;

            if(!this.cartas[carta1aux].compareTo(this.cartas[carta2aux])){ //Fallas en las cartas
				that.status = "Inténtalo de nuevo"; 
				setTimeout(function() {
					that.cartas[carta1aux].flip();
					that.cartas[carta2aux].flip();
                    that.card_selected = 0;
				},1000);
            }
           else{
                that.status = "¡Pareja encontrada!";
                that.cartas[carta1aux].found();
                that.cartas[carta2aux].found();
                that.card_selected = 0; 
                this.parejas_encontradas += 1;
           }
       }
         if(this.parejas_encontradas == 8){
             this.status = "¡Has ganado!";
        }
   }
   
}



