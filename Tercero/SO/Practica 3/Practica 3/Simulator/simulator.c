
#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

#define EN_RUTA 0   // autobús en ruta
#define EN_PARADA 1 // autobús en la parada

int N_PARADAS = 5; //número de paradas de la ruta
int MAX_USUARIOS = 40; // capacidad del autobús
int USUARIOS = 4; // numero de usuarios

int estado = EN_RUTA; // estado inicial
int parada_actual = 0; // parada en la que se encuentra el autobus
int n_ocupantes = 0; // ocupantes que tiene el autobús

/* personas que desean subir en cada parada */
//int *esperando_parada[N_PARADAS]; //= {0,0,...0};
int *esperando_parada;
/* personas que desean bajar en cada parada */
//int *esperando_bajar[N_PARADAS]; //= {0,0,...0};
int *esperando_bajar;

/** Otras definiciones globales (comunicación y sincronización) **/
/** COMPLETAR CON DEFINICIÓN DE VARIABLES NECESARIAS ***/

pthread_mutex_t mutex; //Cerrojo

pthread_t *th_usuario, th_autobus; //Hilos

//Variables condicion
pthread_cond_t parado;
pthread_cond_t subir;
pthread_cond_t bajar;


/* FUNCIONES A IMPLEMENTAR */

/* Ajustar el estado y bloquear al autobús hasta que no haya pasajeros que
quieran bajar y/o subir la parada actual. Después se pone en marcha */
void Autobus_En_Parada() {

	pthread_mutex_lock(&mutex); //Bloqueamos el cerrojo al entrar en la region critica

	estado = EN_PARADA;
	printf("===	El autobus esta en la parada: [%d]	===\n", parada_actual);

	//Esperamos a que suba y baje la gente que toca en esta parada
	while(esperando_parada[parada_actual] > 0  || esperando_bajar[parada_actual] > 0){
		pthread_cond_broadcast(&parado); //Avisamos a los usuarios que estan en la parada y autobus de que pueden hacer sus cosas
		//!!DEJAMOS SALIR ANTES DE ENTRAR!!
		if(esperando_bajar[parada_actual] > 0){ 
			pthread_cond_wait(&bajar, &mutex);	//Esperamos a que termine de salir la gente
		}
		//Entramos....
		if(esperando_parada[parada_actual] > 0){
			pthread_cond_wait(&subir,&mutex);	//Esperamos a que termine de entrar la gente
		}
	}
	pthread_mutex_unlock(&mutex);	//Desbloqueamos el cerrojo
}


/* Establecer un Retardo que simule el trayecto y actualizar numero de parada */
void Conducir_Hasta_Siguiente_Parada() {

	pthread_mutex_lock(&mutex); //Bloqueamos el cerrojo al entrar en la region critica
	
	estado = EN_RUTA;
	printf("===	Autobus en trayecto, Siguiente parada : [%d]	===\n", (++parada_actual % N_PARADAS) );
	pthread_mutex_unlock(&mutex);

	sleep(rand()%5);	//Simulamos un trayecto

	pthread_mutex_lock(&mutex); //Bloqueamos el cerrojo al entrar en la region critica
	parada_actual = ++parada_actual % N_PARADAS; //Actualizamos la parada en la que estamos
	estado = EN_PARADA; //Actualizamos el estado a parada
	printf("===	Autobus estacionado, parada actual: [%d]	===\n", parada_actual);
	pthread_mutex_unlock(&mutex);
}


/* El usuario indicará que quiere subir en la parada ’origen’, esperará a que
el autobús se pare en dicha parada y subirá. El id_usuario puede utilizarse para
proporcionar información de depuración */
void Subir_Autobus(int id_usuario, int origen) {

	pthread_mutex_lock(&mutex); //Bloqueamos el cerrojo al entrar en la region critica
	esperando_parada[origen]++; //Personas que hay esperando en la parada actual para SUBIR

	printf("El pasajero [%d], quiere SUBIR en la parada [%d] \n", id_usuario, origen);

	while(parada_actual != origen || estado != EN_PARADA){
		//Esperamos a recibir la señal condicional por broadcast de que el autobus esta en la parada de origen y parado
		pthread_cond_wait(&parado, &mutex); 
	}

	//EL AUTOBUS HA LLEGADO, A VER SI ME PUEDO SUBIR

	//Si el autobus no está lleno
	if(n_ocupantes < MAX_USUARIOS-1){
		n_ocupantes++;
		esperando_parada[origen]--;
		printf("El pasajero [%d] se acaba de SUBIR al autobus en la parada [%d] [N.pasajeros = %d]\n", id_usuario, origen,n_ocupantes);
	}else{	//Si esta lleno
		printf("El pasajero [%d] NO HA PODIDO SUBIR al autobus en la parada [%d] porque estaba al completo [N.pasajeros = %d]\n", id_usuario, origen, n_ocupantes);
	}
	if(esperando_parada[origen] == 0 || n_ocupantes == MAX_USUARIOS) pthread_cond_signal(&subir);	//Avisamos de que ha terminado de entrar la gente
	pthread_mutex_unlock(&mutex);
}


/* El usuario indicará que quiere bajar en la parada ’destino’, esperará a que
el autobús se pare en dicha parada y bajará. El id_usuario puede utilizarse para
proporcionar información de depuración */
void Bajar_Autobus(int id_usuario, int destino){

	pthread_mutex_lock(&mutex); //Bloqueamos el cerrojo al entrar en la region critica
	esperando_bajar[destino]++; //Personas que se quieren BAJAR en la parada actual

	printf("El pasajero [%d], quiere BAJAR en la parada [%d] \n",id_usuario,destino);

	while(parada_actual != destino || estado != EN_PARADA){
		//Esperamos a recibir la señal condicional por broadcast de que el autobus esta en la parada de destino y parado
		pthread_cond_wait(&parado, &mutex);
	}

	//EL AUTOBUS HA LLEGADO! ME BAJO AQUI

	n_ocupantes--; //Un pasajero menos
	esperando_bajar[destino]--;	//Una persona que se quiere bajar menos
	printf("El pasajero [%d] se acaba de BAJAR del autobus en la parada [%d] [N.pasajeros = %d]\n", id_usuario, destino, n_ocupantes);
	
	if(esperando_bajar[destino] == 0) pthread_cond_signal(&bajar);	//Avisamos de que ha terminado de salir la gente
	pthread_mutex_unlock(&mutex);

}


void * thread_autobus(void * args) {

	while (1) {
	// esperar a que los viajeros suban y bajen
	Autobus_En_Parada();

	// conducir hasta siguiente parada
	Conducir_Hasta_Siguiente_Parada();
	}
}


void * thread_usuario(void * arg) {

	int id_usuario = *((int*) arg);
	int a, b;

	// obtener el id del usario

	while (1) {
		a=rand() % N_PARADAS;

		do{
			b=rand() % N_PARADAS;
		}while(a>b);

		printf("Pasajero [%d] ira de [%d] ---> [%d]\n",id_usuario, a, b);
		Usuario(id_usuario,a,b);
	}
}


void Usuario(int id_usuario, int origen, int destino) {

	// Esperar a que el autobus esté en parada origen para subir
	Subir_Autobus(id_usuario, origen);

	// Bajarme en estación destino
	Bajar_Autobus(id_usuario, destino);
}


int main(int argc, char *argv[]) {
	int i;

	// Opcional: obtener de los argumentos del programa la capacidad del
	// autobus, el numero de usuarios y el numero de paradas
	
	if(argc == 4){
		//Parseamos informacion del comando STRING -> INT
		N_PARADAS = atoi(argv[3]); 
		MAX_USUARIOS = atoi(argv[1]); 
		USUARIOS = atoi(argv[2]); 
	}else if(argc == 2 || argc == 3){	//Argumentos mal
		exit(1);
	}
	
	printf("===	El bus ha salido! Siguiente parada: [%d] ===\n", (++parada_actual % N_PARADAS));

	esperando_parada = (int*) malloc(sizeof(int)*N_PARADAS);
	esperando_bajar = (int*) malloc(sizeof(int)*N_PARADAS);
	th_usuario = (pthread_t*) malloc(sizeof(pthread_t)*USUARIOS);
	int id[USUARIOS];

	// Definición de variables locales a main
	pthread_mutex_init(&mutex, NULL);
	pthread_cond_init(&parado, NULL);
	pthread_cond_init(&subir, NULL);
	pthread_cond_init(&bajar, NULL);

	//Autobus:
	pthread_create(&th_autobus, NULL, thread_autobus, NULL);

	//Pasajeros:
	for(i=0; i < USUARIOS; i++){
		id[i] = i;	//Asignamos un id a cada usuario
		//Creamos su hilo
		pthread_create(&th_usuario[i], NULL, thread_usuario, (void*)&id[i] );
	}
	
	//Esperamos a que el autobus termine
	pthread_join(th_autobus, NULL);

	//Esperar a que terminen los pasajeros
	for(i=0; i < USUARIOS; i++){
		pthread_join(&th_usuario, NULL);
	}
	
	pthread_mutex_destroy(&mutex);
	pthread_cond_destroy(&parado);
	pthread_cond_destroy(&subir);
	pthread_cond_destroy(&bajar);
	return 0;
}
