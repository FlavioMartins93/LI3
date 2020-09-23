#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "date.h"
#include "utilizador.h"
#include "listaUtilizadores.h"

struct entry {
	char* key;
	UTILIZADOR utilizador;
};

void initTab(ENTRY listaUtil[], int size){
	for(int i = 0; i<size; i++){
		listaUtil[i]->key = NULL;
		listaUtil[i]->utilizador = NULL;
	}
}

//apenas para testes!
int hash(char* key){
	int r;
	r = atoi(key);
	if (r = -1) return 0;
	return r;
}

void inserirUtil(ENTRY listaUtil[], UTILIZADOR u){
	char* key = malloc(strlen(getUtId(u)) + 1);
	key = getUtId(u);
	int i = hash(key);
	listaUtil[i]->key = malloc(strlen(getUtId(u)) + 1);
	listaUtil[i]->key = key;
	listaUtil[i]->utilizador = u;
	free(key);
}

UTILIZADOR getUtilizador(ENTRY listaUtil[], char* Id){
	int i = hash(Id);
	UTILIZADOR u = listaUtil[i]->utilizador;
	return u;
}

