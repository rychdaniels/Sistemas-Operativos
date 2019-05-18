#include <stdio.h>
#include <time.h>
#include <stdlib.h> 
#include<string.h>

void Menu();
void ElegirOpcion(int*);
int comprobarDatos(char[]);
int suma(int *,int,int);
void ingresaDatos(char []);
void inicio(char [], char[]);
void charToInt(char [], int[]);


int main(int argc, char const *argv[]){
    int opcionMenu =0;
    do{
        Menu();
        scanf("%d",&opcionMenu);
        ElegirOpcion(&opcionMenu);
               
    }while(opcionMenu!=2);
}

void ElegirOpcion(int* opcionMenu){  
    if(*opcionMenu == 1){
        int resultado[5+1]; // De 5 en caso de que el resultado sea de 5 caracteres
        resultado[0] = 0;
        // num guarda el resultado de sumar dígito a dígito
        int num,ac = 0;    
        int N  = 5;
        char numero1[5];
        char numero2[5];
        inicio(numero1, numero2);
        printf("   %s  ", numero2);
        printf("\n+");
        printf("\n   %s  ", numero1);
        printf("\n__________\n  ");
        //printf("\n Conviertiendo a entero");
        int num1[4];
        charToInt(numero1, num1);
        int num2[4];
        charToInt(numero2, num2);
        
    
        for(int i = N; i > 0; i--){ // El recorrido de los arreglos se hace al revés por regla
	        // Se hace la suma y se guarda el resultado
	        num = suma(&ac, num1[i-1], num2[i-1]);
            //suma(ac,num1,num2);
	        resultado[i] = num;
            printf("%d",num);

        }

        
        
        

    }else if(*opcionMenu==2){
        system("clear");
        printf("Adios");
        exit(0);
    }
   
    
}
void charToInt(char num[], int numero[]){
    
    for (int i = 0; i <4; i++){
            int aski = (int)num[i];
            if(aski == 49){
                numero[i] = 1;
            }
            else{
                numero[i] = 0;
            }
            //printf("\n%d", numero[i]);
        }
}

void inicio(char numero1[], char numero2[]){
        printf ("\nIngrese el primer numero--> ");
        scanf("%s", numero1);
        ingresaDatos(numero1);
        printf ("\nIngrese el segundo numero--> ");
        scanf("%s", numero2);
        ingresaDatos(numero2);

}

void ingresaDatos(char numero[]){
    int bool = comprobarDatos(numero);
        if(bool ==1){
            do{
                system("clear");
                printf("Numero incorrecto:\n");
                printf("Ingrese un numero correcto de 4 bits\n");
                scanf("%s", numero);
                system("clear");
                bool = comprobarDatos(numero);

            }while(bool==1);
        }
}

int suma(int *ac, int a, int b){
	if(*ac == 0){ // EL ACARREO ES 0
		if((a + b) == 1){ //Casos: a = 0 y b = 1; a = 1 y b = 0
			*ac = 0;
			return 1;
		}
		if((a + b) == 2){ // Caso: a = b = 1
			*ac = 1;
			return 0;
		}
		*ac = 0; //Caso a = b = 0;
		return 0;
	}
	else{ // EL ACARREO ES 1
		if((a + b) == 1){ //Casos: a = 0 y b = 1; a = 1 y b = 0
			*ac = 1;
			return 0;
		}
		if((a + b) == 2){ // Caso: a = b = 1
			*ac = 1;
			return 1;
		}
		*ac = 0; //Caso a = b = 0;
		return 1;
	}
    
}


int comprobarDatos(char numero[]){
    int tamanio = 0;
    tamanio = strlen(numero);
    if(tamanio > 4 || tamanio < 4){
        return 1;            
    }else{
        for(int  i = 0; i < 4; i++){
        
        if(numero[i] != '1' && numero[i] != '0'){
            return 1;
        }       
    }
        
    }
    return 0;

}

void Menu(){
    printf("\nBienvenido al sumador binario de 4 bits\n");
    printf("1. Realizar una suma\n");
    printf("2. Salir del programa\n");
    printf("Elige una de las opciones anteriores\n\n");

}