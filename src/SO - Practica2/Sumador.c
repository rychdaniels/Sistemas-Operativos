#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include <conio.h>


//Prototipo de las funciones
void mostrarMenu();
void compruebaDatos();
char *compruebaTamanio(char numero[]);
int suma(int,int);


int main(){
    /*int opcion=0;
    
    while(opcion!=2){
        mostrarMenu();
        scanf("%d", &opcion);
        
    }*/
    compruebaDatos();

    return 0;
}

void mostrarMenu(){
    
    printf("Bienvenido al sumador binario de 4 bits\n");
    printf("1. Realizar una suma\n");
    printf("2. Salir del programa\n");
    printf("Elige una de las opciones anteriores\n\n");
    
    

}

int suma(int num1, int num2){
    
    printf("Entraste en suma\n");
    return 0;
     
}

    


void compruebaDatos(){

    char numero[3];    
    printf ("\nIngrese el numero--> ");
    scanf("%s", numero);
    
    
    for(int  i = 0; i < 4; i++){
        
        if(numero[i] != 1 && numero[i] != 0){
            
            printf("Valores incorrectos dentro del arreglo\n");
            printf("Valor distinto de 0 y 1\n");
            printf("Presione cualquier tecla:......\n");        
            getch();
            compruebaDatos();
        }else{
            printf("Numero Correcto");
            //compruebaTamanio(numero);
        }
        
    }

    
}


/*char *compruebaTamanio(char numero[]){
    int tamanio = 0;
    char *p = numero;
    tamanio = strlen(numero);
    if(tamanio > 4 || tamanio < 4){
            printf("Numero incorrecto:\n");
            printf("Ingrese un numero de 4 bits\n");
            printf("Presione cualquier tecla:......");        
            getch();
            compruebaDatos();
            
    }else{
        return numero;
    }
}
*/
        
    
    





