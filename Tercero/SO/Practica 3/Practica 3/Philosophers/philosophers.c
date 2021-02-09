#include <stdio.h> 
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>

#define NR_PHILOSOPHERS 30

pthread_t philosophers[NR_PHILOSOPHERS];
pthread_mutex_t forks[NR_PHILOSOPHERS];


void init()
{
    int i;
    for(i=0; i<NR_PHILOSOPHERS; i++)
        pthread_mutex_init(&forks[i],NULL);
    
}

void think(int i) {
    printf("Philosopher %d thinking... \n" , i);
    sleep(random() % 2);
    printf("Philosopher %d stopped thinking!!! \n" , i);

}

void eat(int i) {
    printf("Philosopher %d eating... \n" , i);
    sleep(random() % 3);
    printf("Philosopher %d is not eating anymore!!! \n" , i);

}

void toSleep(int i) {
    printf("Philosopher %d sleeping... \n" , i);
    sleep(random() % 2);
    printf("Philosopher %d is awake!!! \n" , i);
    
}

//Esquema propuesto por markel, que sentido tiene el if??
void coger(int right, int left){

    if(right < left){
        //Primero derecha y luego izquierda
        pthread_mutex_lock(&forks[right]);  
        pthread_mutex_lock(&forks[left]); 
    }else{
        //Primero izquierda y luego derecha
        pthread_mutex_lock(&forks[left]);  
        pthread_mutex_lock(&forks[right]); 
    }

}

void* philosopher(void* i)
{
    int nPhilosopher = (int)i;
    int right = nPhilosopher;
    int left = (nPhilosopher - 1 == -1) ? NR_PHILOSOPHERS - 1 : (nPhilosopher - 1);
    while(1)
    {
        
        think(nPhilosopher);

        // TRY TO GRAB BOTH FORKS (right and left)
        //Solucion con interbloqueo e inanicion (tenemos que hacer la de monitor???)
        //pthread_mutex_lock(&forks[right]); 
        //pthread_mutex_lock(&forks[left]); 

        coger(right, left); 

        eat(nPhilosopher);

        // PUT FORKS BACK ON THE TABLE podriamos hacer una funcion externa para esto pero no merece la pena
        pthread_mutex_unlock(&forks[right]);
        pthread_mutex_unlock(&forks[left]);

        toSleep(nPhilosopher);
   }

}

int main()
{
    init();
    unsigned long i;
    for(i=0; i<NR_PHILOSOPHERS; i++)
        pthread_create(&philosophers[i], NULL, philosopher, (void*)i);
    
    for(i=0; i<NR_PHILOSOPHERS; i++)
        pthread_join(philosophers[i],NULL);
    return 0;
} 
