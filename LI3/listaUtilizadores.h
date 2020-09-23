#ifndef __LISTAUTILIZADORES_H__
#define __LISTAUTILIZADORES_H__

typedef struct entry *ENTRY;

void initTab(ENTRY listaUtil[], int size);
int hash(char* key);
void inserirUtil(ENTRY listaUtil[], UTILIZADOR u);
UTILIZADOR getUtilizador(ENTRY listaUtil[], char* ID);

#endif
