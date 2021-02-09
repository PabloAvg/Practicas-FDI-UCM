#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <linux/limits.h>
#include "mytar.h"
#define MAX_RUTA 64

extern char *use;

/** Copy nBytes bytes from the origin file to the destination file.
 *
 * origin: pointer to the FILE descriptor associated with the origin file
 * destination:  pointer to the FILE descriptor associated with the destination file
 * nBytes: number of bytes to copy
 *
 * Returns the number of bytes actually copied or -1 if an error occured.
 */
int copynFile(FILE * origin, FILE * destination, int nBytes){
    int c, n=0;
    while((n < nBytes) && (c = getc(origin)) != EOF){
        putc((unsigned char) c, destination);
        n++;
        putc(c, stdout);
    }
    return n;
}


/** Loads a string from a file.
 *
 * file: pointer to the FILE descriptor 
 * 
 * The loadstr() function must allocate memory from the heap to store 
 * the contents of the string read from the FILE. 
 * Once the string has been properly built in memory, the function returns
 * the starting address of the string (pointer returned by malloc()) 
 * 
 * Returns: !=NULL if success, NULL if error
 */
char* loadstr(FILE * file) {
	// Complete the function
	int i = 0, caracter;
	char *str;

	if ((str = (char *) malloc(MAX_RUTA * sizeof(char))) == NULL)
	{
		perror("No se puede añadir el nombre del fichero a la memoria");
		return NULL;
	}

	do {
		caracter = getc(file);
		str[i]= (char)caracter;
		i++;

	} while ((caracter != EOF) && ((char)caracter != '\0') && (i < MAX_RUTA));

	str[i] = '\0';

	return str;
}

/** Read tarball header and store it in memory.
 *
 * tarFile: pointer to the tarball's FILE descriptor 
 * nFiles: output parameter. Used to return the number 
 * of files stored in the tarball archive (first 4 bytes of the header).
 *
 * On success it returns the starting memory address of an array that stores 
 * the (name,size) pairs read from the tar file. Upon failure, the function returns NULL.
 */
stHeaderEntry* readHeader(FILE * tarFile, int *nFiles) {

    // Complete the function
    stHeaderEntry* array=NULL; //Puntero a Array de estructura [char* name, unsigned int size]
    int nr_files=0;

    /*... Read the number of files (N) from tarfile and store it in nr_files ...*/
    fread(&nr_files,sizeof(unsigned int),1,tarFile);

    /* Allocate memory for the array */
    array=malloc(sizeof(stHeaderEntry)*nr_files);

    /*... Read the (pathname,size) pairs from tarFile and store them in the array ...*/
    int i =0;
   	for (; i < nr_files; i++){	//Damos nr_files vueltas
   			//    vvvv- leo y asigno el nombre con loadstr !!!!
			if ((array[i].name = loadstr(tarFile)) == NULL){	//Si algo va mal en al cargar el nombre
				for (int j = 0; j < i; j++){
					free(array[j].name);	//Libero la memoria de la cabecera
				}
				fclose(tarFile);
				return NULL;
			}
			fread(&array[i].size, sizeof(unsigned int), 1, tarFile); //Leo el tamaño
		}

    /* Store the number of files in the output parameter */
    (*nFiles)=nr_files;

    return array;
}


/** Creates a tarball archive 
 *
 * nfiles: number of files to be stored in the tarball
 * filenames: array with the path names of the files to be included in the tarball
 * tarname: name of the tarball archive
 * 
 * On success, it returns EXIT_SUCCESS; upon error it returns EXIT_FAILURE. 
 * (macros defined in stdlib.h).
 *
 * HINTS: First reserve room in the file to store the tarball header.
 * Move the file's position indicator to the data section (skip the header)
 * and dump the contents of the source files (one by one) in the tarball archive. 
 * At the same time, build the representation of the tarball header in memory.
 * Finally, rewind the file's position indicator, write the number of files as well as 
 * the (file name,file size) pairs in the tar archive.
 *
 * Important reminder: to calculate the room needed for the header, a simple sizeof 
 * of stHeaderEntry will not work. Bear in mind that, on disk, file names found in (name,size) 
 * pairs occupy strlen(name)+1 bytes.
 *
 */
int createTar(int nFiles, char *fileNames[], char tarName[]) {

	stHeaderEntry* array = NULL; //array para la cabecera

	FILE * tar;	// tar filepointer!
	if((tar = fopen(tarName, "w")) == NULL){ //Abrimos el archivo
			perror("Error al abrir el archivo\n");
			return EXIT_FAILURE;
	}

	array = malloc(sizeof(stHeaderEntry)*nFiles); //Reservamos memoria para la cabecera

	int i,tam, bytes=0;
	for(i=0; i < nFiles; i++){
			tam = strlen(fileNames[i])+1;		//Tamaño del archivo i
			bytes = bytes + tam;				//Bytes acumulados por los nombres de los txt
			array[i].name = malloc(tam);		//reservamos la memoria necesaria para guardar el nombre
			strcpy(array[i].name, fileNames[i]);//Introducimos el nombre en el array de cabeceras
	}

	//Calculamos el tamaño de la cabecera
	int cabecera = sizeof(char) * bytes + sizeof(int) + nFiles * sizeof(unsigned int);

	if(fseek(tar, cabecera, SEEK_SET) < 0){	//Nos situamos en la region de datos con el filepointer (tar)
			perror("Error al situarse en la region de datos!\n");
			return EXIT_FAILURE;
	}

	FILE * inputFile; 	//Filepointer al archivo del que queremos extraer los datos
	for(i=0; i < nFiles; i++){
			if((inputFile = fopen(fileNames[i], "r")) == NULL){	//Abrimos el archivo i de la lista de archivos
				perror("Error de lectura");
				fclose(tar);
				return EXIT_FAILURE;
			}
			//usamos la funciona auxiliar copynfiles para copiar todos los datos del .txt a .mtar
			array[i].size = copynFile(inputFile, tar, INT_MAX);
			//Cerramos el archivo
			fclose(inputFile);
	}

	//Nos situamos al principio del archivo para ir introduciendo los datos de la cabecera
	if(fseek(tar, 0, SEEK_SET) == -1){
			perror("Error in function fseek!\n");
			return EXIT_FAILURE;
	}

	//Metemos el nFiles
	if(fwrite(&nFiles, sizeof(int), 1, tar) < 0){
		perror("Error writing in tarFile\n");
	return EXIT_FAILURE;
	}

	//Introducimos cada uno de los campos del array de stHeaderEntry
	for (i = 0; i < nFiles; i++) {
		if(fwrite(array[i].name, strlen(array[i].name)+1, 1,tar) != -1){	//Escribimos el nombre
			if(fwrite(&array[i].size, sizeof(unsigned int), 1, tar) == -1){	//Escribimos el tamaño
				perror("Error escribiendo el tarFile\n");
				return EXIT_FAILURE;
			}
			free(array[i].name);	//Liberamos memoria del array
		}
		else{
			perror("Error escribiendo el tarFile\n");
			return EXIT_FAILURE;
		}
	}
	//Liberamos el array de stHeaderEntry
	free(array);

	//Cerramos el tar
	if(fclose(tar) != 0){
		perror("Error cerrando tarFile\n");
		return EXIT_FAILURE;
	}
	else{
		printf("Mtar creado correctamente\n");
	}
	// Complete the function
	return EXIT_SUCCESS;
}

/** Extract files stored in a tarball archive
 *
 * tarName: tarball's pathname
 *
 * On success, it returns EXIT_SUCCESS; upon error it returns EXIT_FAILURE. 
 * (macros defined in stdlib.h).
 *
 * HINTS: First load the tarball's header into memory.
 * After reading the header, the file position indicator will be located at the 
 * tarball's data section. By using information from the 
 * header --number of files and (file name, file size) pairs--, extract files 
 * stored in the data section of the tarball.
 *
 */
int
extractTar(char tarName[])
{
	FILE * tar;	//Filepointer al .mtar
	FILE * outputFile;	//Filepointer auxiliar para escibir en cada uno de los .txt
	stHeaderEntry* array = NULL;	//array de cabeceras
	int i, nFiles, wBytes;
	char *name;

	//Abrimos el .mtar
	if((tar=fopen(tarName, "r")) == NULL){
		perror("Error al abir el .mtar\n");
		return EXIT_FAILURE;
	}

	//Metemos en el array de cabeceras toda la informacion
	//necesaria con la funcion readHeader
	array = readHeader(tar, &nFiles);

	if(array == NULL){
		perror("Error al leer la cabecera del archivo .mtar\n");
		return EXIT_FAILURE;
	}

	for(i = 0; i < nFiles; i++){
		//Obtenemos la ruta de cada archivo (el nombre)
		name = array[i].name;
		printf("Abriendo/Creando archivo: %s\n", name);
		//	Abrimos de nuevo o por primera vez el filepointer de salida (para escribir los datos en el .txt)
		//	con el nombre name
		if((outputFile = fopen(name, "w")) == NULL){
			perror("Error al abrir el archivo\n");
			return EXIT_FAILURE;
		}

		//Introducimos los datos en el outputFile iesimo
		wBytes = copynFile(tar, outputFile, array[i].size);
		printf("Se han copiado %d caracteres\n", wBytes);

		printf("[%d] Creando archivo: %s\nTamaño: %d Bytes\n", i,name, array[i].size);

		//Cerramos el archivo actual (para poder abrir otro despues)
		if(fclose(outputFile) != 0){
			perror("Error al cerrar el archivo\n");
			return EXIT_FAILURE;
		}
		printf("Cerrando archivo: %s\n", name);
	}

	//Cerramos el .mtar
	if(fclose(tar) != 0){
		perror("Error cerrando el mtar\n");
		return EXIT_FAILURE;
	}

	//Liberamos la memoria de la cabecera
	free(name);
	free(array);

	// Complete the function
	return EXIT_SUCCESS;
}
