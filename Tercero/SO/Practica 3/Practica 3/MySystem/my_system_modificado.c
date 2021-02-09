#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <errno.h>

int main(int argc, char* argv[])
{
	if (argc!=2){
		fprintf(stderr, "Usage: %s <command>\n", argv[0]);
		exit(1);
	}

    int status;
    pid_t childPid;
    
    childPid = fork();
    
    if(childPid == 0){   //Parte del hijo
        execl("/bin/bash", "/bin/bash", "-c", argv[1], (char*) NULL);
        exit(1);
    }
    else if(childPid == -1){
        status = -1; //Error, el fork ha fallado
    }
    else{   //Parte del padre
        while(waitpid(childPid, &status, 0) == -1){
            if(errno != EINTR){
                status = -1;     
                break;           
            }
        }    
    }
	return status;
}
